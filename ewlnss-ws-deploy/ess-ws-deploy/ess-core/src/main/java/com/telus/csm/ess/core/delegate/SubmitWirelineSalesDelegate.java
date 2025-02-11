package com.telus.csm.ess.core.delegate;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_ALL;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.core.domain.SubmitSalesCoreResponse;
import com.telus.csm.ess.core.posttask.ProcessOrderPostTask;
import com.telus.csm.ess.core.posttask.node.AddNewVestaToSfdcStgExcecNode;
import com.telus.csm.ewlnsc.adapter.IEnterpriseSalesServiceFulfillmentSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IInventoryServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.adapter.domain.ReserveTelephoneNumberAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.ReserveTelephoneNumberAdapterResponse;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.HomePhoneNumberDetailTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.SalesItemPerkVO;
import com.telus.csm.ewlnsc.domain.SalesRepAssociatedOutletVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationResultVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.SmartRingTypeVO;
import com.telus.csm.ewlnsc.domain.SubmitWirelineOrderRequestVO;
import com.telus.csm.ewlnsc.domain.UpdateSaleResponseVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.helper.ShoppingCartValidationHelper;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.opshoppingcart.OPCommonProvideTransformer;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.ShoppingCartStatus;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateOrderItem;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateOrderItemTypeParameter;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateWirelessSalesOrderType;
import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.PolicyException;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.SalesRepAssociatedOutletType;
import com.telus.ucss.wirelinesales.domain.Order;
import com.telus.ucss.wirelinesales.domain.OrderStatus;
import com.telus.ucss.wirelinesales.ejb.OrderManager;
import com.telus.ucss.wirelinesales.exception.WLNSalesApplicationException;

import commonj.work.Work;

@Component
@Scope(SCOPE_PROTOTYPE)
public class SubmitWirelineSalesDelegate  {

	private static final LoggerUtil logger = LoggerUtil.getLogger(SubmitWirelineSalesDelegate.class);
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
	private static final String ORDER_STATUS_A = "A";
	

	public static final int UNSUCCESSFUL_REASONID = 1517;
	private static final String OMS_ORDER_SUBMISSION = "OMS";
	
	@Autowired
	IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;

	private IInventoryServiceAdapter inventoryServiceAdapter = null;
	
	/**
	 * 
	 * @param submitWirelineOrderRequestVO
	 * @return
	 * @throws WLNSalesApplicationException 
	 */
	public  SubmitSalesCoreResponse execute(final SubmitWirelineOrderRequestVO submitWirelineOrderRequestVO)  {
		final SubmitSalesCoreResponse result = new SubmitSalesCoreResponse();
		final String shoppingCartId = submitWirelineOrderRequestVO.getShoppingCartId();
		result.setShoppingCartId(shoppingCartId);
		ShoppingCartContextHelper contextHelper = new ShoppingCartContextHelper();
		final ShoppingCartContextVO shCartContextVO = contextHelper.execute(shoppingCartId);
		ShoppingCartVO shoppingCartVO = shCartContextVO.getShoppingCartVO();
		if (shoppingCartVO == null) { 
			logger.error("shopping cart [" + shoppingCartId + "] does not exist.");
			result.setHasError(true);
			result.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SHOPPING_CART_NOT_FOUND,"shopping cart [" + shoppingCartId + "] does not exist."));
			return result;
		} else {
			
			//set cart and item's status, and update shopping cart in cache
			shoppingCartVO.setStatus(ShoppingCartStatus.IN_PROCESS);
			shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.IN_PROCESS.getCode() );

			//SalesContextDelegate.getInstance().putShoppingCart(shoppingCartVO);
			ShoppingCartDelegate.getInstance().updateShoppingCart(shoppingCartVO);
			
			
			final List<String> ctxTypeList = shoppingCartVO.getCartContextTypeList();
			boolean isValid = shoppingCartVO.isValidShoppingCart();
			if (!isValid || !ctxTypeList.contains(SHOPPING_CART_CTX_TYPE_ALL)) {
				logger.error(shoppingCartId + " shopping cart was valid=" + isValid + " and contains=" + ctxTypeList + ". Re-trigger validation...");
				final ShoppingCartValidationHelper validationHelper = new ShoppingCartValidationHelper();
				final ShoppingCartValidationResultVO validationResult = validationHelper.execute(shCartContextVO);
				isValid = validationResult.isValid();
			}
			
			final String orderId = shCartContextVO.getOrderId();
			if (isValid) {
				// hard reserve phone number
				reservePhoneNumber(shCartContextVO);
				
				//Disconnect Orders: After TN reservation (HARD) in post task
				
				
				
				logger.error("order validation passed.");
				
				/*
				1-If scenario is a business defined manual orders (such as COT),  the comment should be this:  Business Defined Manual Order (where Fulfillment option = MANUAL)
				2-If scenario is that the call to CreateOrder failed, then the comment should be this: OP Shopping Cart Creation  Failure
				3- If scenario is that Customer or Account is not in the cart, or is not valid or experiences error loading the customer then the comment should be this: Customer create Failure
				 */
				if (shCartContextVO.isManualOrder()) {
					doManualOrder(shoppingCartVO, orderId, "Business Defined Manual Order (where Fulfillment option = MANUAL)");
				  //NWLN-10336 - Shopping cart is set to manual if contains product type code 'CPE' 
				} else if(isAccessoryOrder(shoppingCartVO, orderId)) {
					doManualOrder(shoppingCartVO, orderId, "Shopping cart contains product type 'CPE'");
				} else if(!canDoPostTask(shCartContextVO)) {
					doManualOrder(shoppingCartVO, orderId, "Customer create Failure");
				} else {
					doAutomatedOrder(shoppingCartId, shoppingCartVO, orderId);
				}
			} else {
				logger.error("order validation failed.");
				doInvalidOrder(shoppingCartVO, orderId);
			}
			
		}
		
		return result;
	}
	
	//NWLN-10336
	private boolean isAccessoryOrder(ShoppingCartVO shoppingCartVO, final String orderId){
		logger.info("isAccessoryOrder", "check if shopping cart has CPE product, orderId=" + orderId);
		for(CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
			if(cartItem.getProducts() != null && cartItem.getProducts().getAccessoryProduct() != null ) {
				logger.info("isAccessoryOrder", "CPE product found");
				return true;
			}
		} 
		logger.info("isAccessoryOrder", "CPE product not found");
		return false;
	}


	private void doInvalidOrder(ShoppingCartVO shoppingCartVO, final String orderId) {
		boolean isUpdateVestaSuccessful = false;
		try {
			logger.info("doInvalidOrder", "updating order status in vesta to Unassign for invalid orderId=" + orderId);
			wirelineSalesEJBAdapter.updateOrderStatus(orderId, getOrderStatus(orderId, ORDER_STATUS_UNASSIGN), ORDER_STATUS_PROCESS_PENDING );
		    wirelineSalesEJBAdapter.insertComment(orderId, "Manual Order Status update to Unassign", OrderManager.COMMENT_TYPE_USER);
		    
		    isUpdateVestaSuccessful=true;
		    
		} catch (WLNSalesApplicationException e) {
			logger.error("", "doInvalidOrder", "Failed to update status in VESTA to UNASSIGN for invalid orderId=" + orderId, e);
		}			
		updateShoppingCart(shoppingCartVO, isUpdateVestaSuccessful);
	}


	private void doAutomatedOrder(final String shoppingCartId, ShoppingCartVO shoppingCartVO, final String orderId) {
		boolean isUpdateVestaSuccessful = false;
		try {
			logger.info("doAutomatedOrder", "updating order status in vesta to Pending for automatic orderId=" + orderId);
			wirelineSalesEJBAdapter.updateOrderStatus(orderId, getOrderStatus(orderId, ORDER_STATUS_PROCESS_PENDING), ORDER_STATUS_PROCESS_PENDING );
		    wirelineSalesEJBAdapter.insertComment(orderId, "Automatic Order Status update to Pending", OrderManager.COMMENT_TYPE_USER);
		    isUpdateVestaSuccessful=true;
		    
		} catch (WLNSalesApplicationException e) {
			logger.error("", "doAutomatedOrder", "Failed to update status in VESTA to PENDING for Automatic orderId=" + orderId, e);
		}
		try {
			ProcessOrderPostTask task = new ProcessOrderPostTask(shoppingCartId);
			WorkManagerFactory.getCommonJWorkManager().processAsyncTasks(Arrays.asList(new Work[]{task}));
		} catch(Exception e) {
			throw new RuntimeException("failed to submit a post task for shopping cart: " + shoppingCartId, e);
		}
		updateShoppingCart(shoppingCartVO, isUpdateVestaSuccessful);
	}


	public void doManualOrder(ShoppingCartVO shoppingCartVO, final String orderId, String vestaComment) {
		boolean isUpdateVestaSuccessful = false;
		try {
			logger.info("update status", "updating order status in vesta to Unassign for manual orderId=" + orderId);

			if (Constants.D2C_ORDER.equalsIgnoreCase(shoppingCartVO.getShoppingProfile().getUserProfile().getChannelOrganizationTypeCd())) {            
            	addNewVestaToSfdcStg(orderId);
            }
           	submitPerk(shoppingCartVO);
			wirelineSalesEJBAdapter.updateOrderStatus(orderId, getOrderStatus(orderId, ORDER_STATUS_UNASSIGN), ORDER_STATUS_PROCESS_PENDING );
		    wirelineSalesEJBAdapter.insertComment(orderId, "Manual Order Status update to Unassign", OrderManager.COMMENT_TYPE_USER);
		    
		    /**
		     * Insert comment for the scenario: When ESS determines that an order is a manual order.
		     */
		    if(!StringUtils.isEmpty(vestaComment)) {
		    	wirelineSalesEJBAdapter.insertComment(orderId, vestaComment, ORDER_STATUS_A);
		    }
		    
		    isUpdateVestaSuccessful=true;

		} catch (WLNSalesApplicationException e) {
			logger.error("", "doManualOrder", "Failed to update status in VESTA to UNASSIGN for Manual orderId=" + orderId, e);
		}
		updateShoppingCart(shoppingCartVO, isUpdateVestaSuccessful);
	}


	private void updateShoppingCart(ShoppingCartVO shoppingCartVO, boolean isUpdateVestaSuccessful) {
		if (isUpdateVestaSuccessful) {
			//set cart and item's status, and update shopping cart in cache
			shoppingCartVO.setStatus(ShoppingCartStatus.INTERMEDIATE_COMPLETED);
			shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.INTERMEDIATE_ORDER_COMPLETED.getCode() );
			
		} else {
			//set cart and item's status, and update shopping cart in cache
			shoppingCartVO.setStatus(ShoppingCartStatus.FAILED);
			shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.FAILED.getCode() );
		}
		//SalesContextDelegate.getInstance().putShoppingCart(shoppingCartVO);
		ShoppingCartDelegate.getInstance().updateShoppingCart(shoppingCartVO);
	}


	private void reservePhoneNumber(ShoppingCartContextVO shCartContextVO) {
		
		ShoppingCartVO shoppingCartVO = shCartContextVO.getShoppingCartVO();
		
		for (CartItemVO ci : shoppingCartVO.getCartItemList()) {
			if (!ci.isSalesOrderItem()) continue;
			ProductTypeVO ciProduct = ci.getProducts();
			HomePhoneProductTypeVO hpProduct = ciProduct.getHomePhoneProduct();
			if (hpProduct != null) {
				if(hpProduct.getProductOrderType() != null) { 
					boolean doSmartRing = false;
					if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(hpProduct.getProductOrderType().getOrderTypeCd())) {
						reservePhoneNumber(shCartContextVO, hpProduct.getPhoneNumber());
						doSmartRing = true;
					} else {
						//change
						//find existing smartringSubscriptionNumberComp
						Product existingProd = OPCommonProvideTransformer.findExistingProduct(shCartContextVO, EnterpriseWLNSalesServicesConstants.SING);
						if(existingProd != null) {
							Product existingSmartringSubscriptionNumberComp = OPCommonProvideTransformer.findOpProductComponentByName(OPCommonProvideTransformer.PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.SMARTRING_SUB_NUMBER + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.SING + Constants.PRODUCT_COMPONENT_NAME), existingProd.getProductComponents());
							doSmartRing = existingSmartringSubscriptionNumberComp == null;
						}
					}
					
					if(doSmartRing && hpProduct.getSmartRingPhoneList() != null) {//do reserve if we don't have a smart ring yet.
						for (SmartRingTypeVO smartRing : hpProduct.getSmartRingPhoneList()) {
							reservePhoneNumber(shCartContextVO, smartRing.getPhone());
						}
					}
				}
			}
		}
	}


	private void reservePhoneNumber(ShoppingCartContextVO shCartContextVO, HomePhoneNumberDetailTypeVO hpPhoneNumberDetail) {
		
		if (hpPhoneNumberDetail == null) {
			return; 
		}
		
		if (hpPhoneNumberDetail.getPortRequestType() != null && Boolean.TRUE.equals(hpPhoneNumberDetail.getPortRequestType().isPortinInd())) {
			return;
		}
		
		if (hpPhoneNumberDetail.getTelephoneNumber() == null || hpPhoneNumberDetail.getTelephoneNumber().getPhoneNumber() == null) {
			return;
		}
		
		if (inventoryServiceAdapter == null) {
			inventoryServiceAdapter = AdapterFactory.getAdapter(IInventoryServiceAdapter.class);
		}
		
		ReserveTelephoneNumberAdapterRequest adapterRequest = new ReserveTelephoneNumberAdapterRequest();
		
		ShoppingCartVO shoppingCartVO = shCartContextVO.getShoppingCartVO();
		
		if (shoppingCartVO.getShoppingProfile() != null && shoppingCartVO.getShoppingProfile().getUserProfile() != null) {
			adapterRequest.setUserId(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepId());
		}
		adapterRequest.setTimestamp(new Date());
		
		ServiceAddressResponseVO serviceAddressResponseVO = shCartContextVO.getServiceAddressResponseVO();
		if (serviceAddressResponseVO != null && serviceAddressResponseVO.getServiceAddress() != null) {
			adapterRequest.setCoid(serviceAddressResponseVO.getServiceAddress().getCOID());
			adapterRequest.setSwitchNumber(serviceAddressResponseVO.getServiceAddress().getSwitchNumber());
		}

		adapterRequest.setReferenceNumber(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		adapterRequest.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		
		adapterRequest.setTelephoneNumber(hpPhoneNumberDetail.getTelephoneNumber().getPhoneNumber());
		
		logger.debug("reservePhoneNumber", "ReserveTelephoneNumberAdapterRequest: " + JsonUtil.getJsonFromObject(adapterRequest));
		
		ReserveTelephoneNumberAdapterResponse adapterResponse = inventoryServiceAdapter.reserveTelephoneNumber(adapterRequest);
		
		if (adapterResponse.isMsgHasError()) {
			logger.info("reservePhoneNumber", "Failed to reserve Phone Number.");
		}else{
			logger.info("reservePhoneNumber", "Phone number=" + hpPhoneNumberDetail.getTelephoneNumber().getPhoneNumber() + " was successfully reserved, updating vesta with comment for reserverdPhoneNumber" );
			String vestaOrderId = shCartContextVO.getOrderId();
			if(StringUtils.isNotBlank(vestaOrderId)){
				String newComment = "Reserved " + hpPhoneNumberDetail.getTelephoneNumber().getPhoneNumber();
				try {
					insertOrderComment(vestaOrderId, newComment, OrderManager.COMMENT_TYPE_TN);
				} catch (Exception e) {
					logger.error("", "reservePhoneNumber", "Error while adding Vesta comment: ", e);
				}
			}			
		}
		
	}


	public UpdateSaleResponseVO executeUpate(String shoppingCartId) {

		final UpdateSaleResponseVO result = new UpdateSaleResponseVO();
		result.setShoppingCartId(shoppingCartId);
		ShoppingCartContextHelper contextHelper = new ShoppingCartContextHelper();
		final ShoppingCartContextVO shCartContextVO = contextHelper.execute(shoppingCartId);
		if (shCartContextVO.getShoppingCartVO() == null) {
			logger.error(shoppingCartId + " shopping cart does not exist.");
			result.setSuccess(false);
			result.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SHOPPING_CART_NOT_FOUND);
			result.setErrorMessage(shoppingCartId + " shopping cart does not exist.");
		} else {
			if (!ShoppingCartStatus.INTERMEDIATE_COMPLETED.equals(shCartContextVO.getShoppingCartVO().getStatus())) {
				logger.error(shoppingCartId + " shopping cart cannot be updated. Status is " + shCartContextVO.getShoppingCartVO().getStatus());
				result.setSuccess(false);
				result.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SHOPPING_CART_NOT_ITERMEDIATE_COMPLETE);
				result.setErrorMessage(shoppingCartId + " shopping cart cannot be updated. Status is " + shCartContextVO.getShoppingCartVO().getStatus());
			} else {

				try {
					ProcessOrderPostTask task = new ProcessOrderPostTask(shoppingCartId);
					WorkManagerFactory.getCommonJWorkManager().processAsyncTasks(Arrays.asList(new Work[] { task }));
				} catch (Exception e) {
					throw new RuntimeException("failed to submit a post task for shopping cart: " + shoppingCartId, e);
				}
			}

		}

		return result;
	}	
	
	private boolean canDoPostTask(ShoppingCartContextVO shoppingCartContextVO) {
		StringHolder errorHolder = new StringHolder();
		boolean canDoPostTask = OPShoppingCartDelegate.canCreateOPShoppingCart(shoppingCartContextVO, errorHolder);
		if(!canDoPostTask) {
			logger.warn("canDoPostTask", "Will switch to Manual order. Cannot proceed with the post task of creating OP order because of: " + errorHolder.value);
		}
		return canDoPostTask;
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

	  public void addNewVestaToSfdcStg(String orderId) {
				try {
					if(StringUtils.isEmpty(orderId)) {
						throw new Exception("Vesta orderID is missing.");
					}
					Order order = wirelineSalesEJBAdapter.getOrderByTransactionId(orderId);
					if (order != null) {
						boolean result = wirelineSalesEJBAdapter.addNewVestaToSfdcStg(order.getOrderId());
						String msg = "Succesful call to addNewVestaToSfdcStg for order :"  + orderId+ " result "+ result;
						logger.error("", "AddNewVestaToSfdcStgExcecNode.run", msg);
					}
				} catch (Exception e) {
					String msg = "Failed to addNewVestaToSfdcStg for order :"  + orderId;
					logger.error("", "run", msg, e);
				}
		}
	  
	  public void submitPerk(ShoppingCartVO shoppingCartVO) {
			String functionName = "SubmitPerkWLNExecNode.run()";
			if (shoppingCartVO != null) {
				ShoppingProfileVO shoppingProfileVO = shoppingCartVO.getShoppingProfile();
				List<CartItemVO> cartItemList = shoppingCartVO.getCartItemList();
				
				if (cartItemList != null && shoppingProfileVO != null) {
					logger.info(functionName, "Start processing perk for shoppingCart id: "+ shoppingCartVO.getShoppingCartId());
					IEnterpriseSalesServiceFulfillmentSvcAdapter enterpriseSalesServiceFulfillmentSvcAdapter = AdapterFactory.getAdapter(IEnterpriseSalesServiceFulfillmentSvcAdapter.class);
					UserProfileVO userProfile = shoppingProfileVO.getUserProfile();
					for (CartItemVO cartItem : cartItemList) {
						logger.info(functionName, "Start processing perk for cartItemId id: "+ cartItem.getCartItemId());
						if (cartItem.isSalesOrderItem()) {
							SalesItemPerkVO salesItem = ShoppingCartDelegate.getInstance().getPerkByCartItemId(cartItem.getCartItemId());
							if (salesItem != null) {
								UpdateSalesOrderAdapterRequest updateSalesOrderRequest = new UpdateSalesOrderAdapterRequest();
								updateSalesOrderRequest.setOperationHeader(buildOperationHeader(cartItem, userProfile));
								updateSalesOrderRequest.setCreateUpdateWirelessSalesOrderType(createUpdateWirelessSalesOrderType());
								logger.info(functionName, "Begin: Call updateSalesOrder to submit  perk for cartItemId id: "+ cartItem.getCartItemId());
								UpdateSalesOrderAdapterResponse updateSalesOrderResponse = enterpriseSalesServiceFulfillmentSvcAdapter.updateSalesOrder(updateSalesOrderRequest );
								logger.info(functionName, "End: Call updateSalesOrder to submit  perk for cartItemId id: "+ cartItem.getCartItemId());
							} else {
								logger.info(functionName, "salesItem returned esdb is empty");
							}
						}
					}
				}
			}
			
		}
	  /**
		 * 
		 * @param cartItemVO
		 * @return
		 */
		private com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader buildOperationHeader(
				CartItemVO cartItemVO, UserProfileVO userProfileVO) {
			com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader operationHeader = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader();
			if (cartItemVO != null) {
				operationHeader.setSalesId(cartItemVO.getCartItemId());
				operationHeader.setSalesTransactionId(cartItemVO.getCartItemId());
				operationHeader.setSalesTransactionTimestamp(new Date());
				operationHeader.setUserProfile(buildProfile(userProfileVO));
				operationHeader.setOriginatorApplicationId(EnterpriseWLNSalesServicesConstants.APPLICATION_ID_WLN_SALES);
				operationHeader.setBrandCode(Constants.TELUS_INSTALL);
				return operationHeader;
			}

			return operationHeader;
		}
		/**
		 * 
		 * @param operationHeaderVO
		 * @param userProfileVO
		 * @return
		 */
		private ChannelPartnerUserProfileType buildProfile(UserProfileVO userProfileVO) {
			ChannelPartnerUserProfileType userProfile = new ChannelPartnerUserProfileType();
			if (userProfileVO != null) {
				userProfile.setChnlOrgTypeCode(userProfileVO.getChannelOrganizationTypeCd());
				userProfile.setChnlOrgInternalId(!StringUtils.isEmpty(userProfileVO.getChannelOrganizationInternalId())?Long.parseLong(userProfileVO.getChannelOrganizationInternalId()):0);
				userProfile.setChnlOrgNumber(userProfileVO.getChannelOrganizationNumber());
				userProfile.setSalesRepId(userProfileVO.getSalesRepId());
				userProfile.setOutletAssociatedProvinces(userProfileVO.getOutletAssociatedProvinces());
				userProfile.setSalesRepAssociatedOutletList(createSalesRepAssociatedOutletList(userProfileVO));
			}
			return userProfile;
		}
		/**
		 * 
		 * @param userProfileVO
		 * @return
		 */
		private List<SalesRepAssociatedOutletType> createSalesRepAssociatedOutletList(UserProfileVO userProfileVO) {
			List<SalesRepAssociatedOutletType> associatedOutletLis = new ArrayList<SalesRepAssociatedOutletType>();
			if (userProfileVO != null) {
				List<SalesRepAssociatedOutletVO> associatedProvinces = userProfileVO.getSalesRepAssociatedOutlet();
				if (associatedProvinces != null) {
					for (SalesRepAssociatedOutletVO outlet : associatedProvinces) {
						SalesRepAssociatedOutletType salesRepAssociatedOutletType = new SalesRepAssociatedOutletType();
						salesRepAssociatedOutletType.setSalesRepAssociatedChannelOutletId(outlet.getChannelOutletId());
						salesRepAssociatedOutletType.setSalesRepAssociatedOutletInternalId(!StringUtils.isEmpty(outlet.getOutletInternalId())?Long.parseLong(outlet.getOutletInternalId()):0);
					}
				}
			}
			return associatedOutletLis;
		}

		private UpdateWirelessSalesOrderType createUpdateWirelessSalesOrderType() {
			UpdateWirelessSalesOrderType salesOrderType = new UpdateWirelessSalesOrderType();
			UpdateOrderItem updateOrderItem = new UpdateOrderItem();
			updateOrderItem.setUpdateOrderItemType(EnterpriseWLNSalesServicesConstants.SALES_ITEM_FULFILLMENT);

			UpdateOrderItemTypeParameter updateOrderItemParam = new UpdateOrderItemTypeParameter();
			updateOrderItemParam.setName(EnterpriseWLNSalesServicesConstants.LINE_OF_BUSINESS);
			updateOrderItemParam.setValue(EnterpriseWLNSalesServicesConstants.APPLICATION_ID);

			updateOrderItem.setUpdateOrderItemTypeParamList(Collections.singletonList(updateOrderItemParam));
			salesOrderType.setUpdateOrderItemList(Collections.singletonList(updateOrderItem));

			return salesOrderType;
		}	
	  
}