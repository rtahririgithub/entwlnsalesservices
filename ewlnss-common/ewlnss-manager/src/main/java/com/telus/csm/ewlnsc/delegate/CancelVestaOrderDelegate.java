package com.telus.csm.ewlnsc.delegate;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterResponse;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.PolicyException;
import com.telus.ucss.wirelinesales.domain.OrderStatus;
import com.telus.ucss.wirelinesales.ejb.OrderManager;
import com.telus.ucss.wirelinesales.exception.WLNSalesApplicationException;

@Component
@Scope(SCOPE_PROTOTYPE)
public class CancelVestaOrderDelegate {
	
	@Autowired
	IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(CancelVestaOrderDelegate.class);
	private static final String ERROR_VESTA_EJB_UPDATE_STATUS_ORDER_FAILED_ID = null;
	private static final String ERROR_VESTA_EJB_UPDATE_STATUS_ORDER_FAILED_MSG = null;
	private static final String ORDER_STATUS_UNASSIGN = "UNASSIGN";
	private static final String ORDER_STATUS_ASSIGN = "ASSIGN";
	private static final String ORDER_STATUS_CANCELLED = "CANCEL";
	private static final String ORDER_STATUS_PROCESS_SUCCESSFUL = "SUCCESS";
	private static final String ORDER_STATUS_PROCESS_SUBMIT = "SUBMIT";
	private static final String ORDER_STATUS_FAILED = "FAILED";
	private static final String ORDER_STATUS_PROCESS_UNSUCCESSFUL = "INCMPLTE"; // "UNSUCCESS";
	private static final String ORDER_STATUS_PROCESS_PENDING = "PENDING";
	private static final String ORDER_STATUS_PROCESS_PREPARED = "PREPARED";

	public static final int UNSUCCESSFUL_REASONID = 1517;
	private static final String OMS_ORDER_SUBMISSION = "OMS";
	/**
	 * 
	 * @param ShoppingCartVO
	 * @return
	 * @throws WLNSalesApplicationException 
	 */
	public void execute(ShoppingCartVO shoppingCartVO,String cancellationSystem)  {
		
		//this shCartContextVO is not fully loaded contextVO, creating here only to use its convenient methods: getOrderId(), isManualOrder()
		ShoppingCartContextVO shCartContextVO = new ShoppingCartContextVO( shoppingCartVO );

		for (String bookingId : findBookingIds(shoppingCartVO)) {
			CancelBookingAdapterResponse cancelBookingAdapterResponse = new OPShoppingCartDelegate().cancelBooking(shoppingCartVO, bookingId,cancellationSystem);
			try {
				wirelineSalesEJBAdapter.insertComment(shCartContextVO.getOrderId(), "Unreserve Due Date " + (cancelBookingAdapterResponse.isCallSuccessfulInd() ? "Successful" : "Failed"), OrderManager.COMMENT_TYPE_USER);
			} catch (WLNSalesApplicationException e) {
				logger.error("Exeception", "execute", "Exception from wirelineSalesEJBAdapter.insertComment", e);
			}
		}

		try {
			wirelineSalesEJBAdapter.updateOrderStatus(shCartContextVO.getOrderId(), getOrderStatus(shCartContextVO.getOrderId(), ORDER_STATUS_PROCESS_UNSUCCESSFUL), null);
		} catch (WLNSalesApplicationException e) {
			logger.error("Exeception", "execute", "Exception from wirelineSalesEJBAdapter.updateOrderStatus", e);
			throw new RuntimeException(e);
		}

		
		try {
			if (shCartContextVO.isManualOrder()) {
				wirelineSalesEJBAdapter.insertComment(shCartContextVO.getOrderId(), "Manual Order Status updated to Incomplete", OrderManager.COMMENT_TYPE_USER);
			} else {
				wirelineSalesEJBAdapter.insertComment(shCartContextVO.getOrderId(), "Auto Order Status updated to Incomplete", OrderManager.COMMENT_TYPE_USER);
			}
		} catch (WLNSalesApplicationException e) {
			logger.error("Exeception", "execute", "Exception from wirelineSalesEJBAdapter.insertComment", e);
		}
	}
	
	/**
	 * 
	 * @param vestaOrderId
	 * @param newComment
	 * @param commentType
	 * @throws Exception
	 */
	protected void insertOrderComment(String vestaOrderId, String newComment, String commentType) throws Exception {
		//logger.info("insertOrderComment", "calling <vestaEjb>.insertOrderComment for orderId=" + vestaOrderId + " and comment=" + newComment + " commentType="+commentType);
		wirelineSalesEJBAdapter.insertComment(vestaOrderId, newComment, commentType);
		logger.info("insertOrderComment", "<vestaEjb>.insertOrderComment completed for order=" + vestaOrderId);
	}
	
	/**
	 * The wrapper to vesta ejb updateOrderStatus.
	 *
	 * @param vestaOrderId
	 * @throws PolicyException
	 */
	protected void updateOrderStatus(String vestaOrderId, OrderStatus orderStatus, String oldStatus) throws Exception {
		//logger.debug("updateOrderStatus", "calling <vestaEjb>.updateOrderStatus for orderId=" + vestaOrderId + " from status=" + oldStatus +
		//		" with new OrderStatus=\n" + UcssXStream.toString(orderStatus) );
		Boolean updateResult = wirelineSalesEJBAdapter.updateOrderStatus(vestaOrderId, orderStatus, oldStatus);
		//TODO: add the condition of updateResult == false later
		if (updateResult == null /*|| updateResult == false*/) {
			//logThrowPolicyEx(null, ERROR_VESTA_EJB_UPDATE_STATUS_ORDER_FAILED_ID, ERROR_VESTA_EJB_UPDATE_STATUS_ORDER_FAILED_MSG);
		}
		logger.info("updateOrderStatus","<vestaEjb>.updateOrderStatus completed for order=" + vestaOrderId);
	}

	  protected OrderStatus getOrderStatus(String orderId, String status) {
	        OrderStatus orderStatus = new OrderStatus();

	        orderStatus.setStatusActorId(OMS_ORDER_SUBMISSION);
	        orderStatus.setStatusActorName(OMS_ORDER_SUBMISSION);
	        orderStatus.setStatusCode(status);
	        orderStatus.setUpdateUser(OMS_ORDER_SUBMISSION);
	        //orderStatus.setProvisioningOrderRefId(orderId);
	        orderStatus.setInstallationDate(null);

	        return orderStatus;
	    }

		private List<String> findBookingIds(ShoppingCartVO shoppingCartVO) {
			HashSet<String> uniqueBookingIds = new HashSet<String>();
			if(shoppingCartVO.getCartItemList() != null) {
				for(CartItemVO itmVO: shoppingCartVO.getCartItemList()) {
					if(itmVO.getProducts() != null 
							&& itmVO.getProducts().getHomePhoneProduct() != null 
							&& itmVO.getProducts().getHomePhoneProduct().getAppointmentDetail() != null
							&& itmVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate() != null) {
						uniqueBookingIds.add(itmVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId());
					}
					if(itmVO.getProducts() != null 
							&& itmVO.getProducts().getInternetProduct() != null 
							&& itmVO.getProducts().getInternetProduct().getAppointmentDetail() != null
							&& itmVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate() != null) {
						uniqueBookingIds.add(itmVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId());
					}
					if(itmVO.getProducts() != null 
							&& itmVO.getProducts().getTelevisionProduct() != null 
							&& itmVO.getProducts().getTelevisionProduct().getAppointmentDetail() != null
							&& itmVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate() != null) {
						uniqueBookingIds.add(itmVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId());
					}
				}
			}
			
			return new ArrayList<String>(uniqueBookingIds);
		}

}