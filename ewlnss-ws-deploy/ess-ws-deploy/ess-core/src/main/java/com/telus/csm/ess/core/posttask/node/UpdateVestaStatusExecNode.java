package com.telus.csm.ess.core.posttask.node;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.ucss.wirelinesales.domain.Order;
import com.telus.ucss.wirelinesales.domain.OrderStatus;
import com.telus.ucss.wirelinesales.ejb.OrderManager;

public class UpdateVestaStatusExecNode extends AbstractNode <Boolean> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private static final String OMS_ORDER_SUBMISSION = "OMS";
	public static final String VESTA_ORDER_STATUS_SUBMIT = "SUBMIT";
	public static final String VESTA_ORDER_STATUS_UNASSIGN = OrderManager.ORDER_STATUS_UNASSIGN;
	
	private ShoppingCartContextVO shoppingCartContextVO;
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	private String vestaOrderStatus;

	public UpdateVestaStatusExecNode(ShoppingCartContextVO shoppingCartContextVO, String vestaOrderStatus) {
		super("UpdateVestaStatusExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.vestaOrderStatus = vestaOrderStatus;
	}

	@Override
	public void run() {
		
		try {
			String orderId = shoppingCartContextVO.getOrderId();
			if(StringUtils.isEmpty(orderId)) {
				throw new Exception("Vesta orderID is missing.");
			} 
			logger.info("run", "Setting Vesta order [" + orderId + "] to status [" + vestaOrderStatus + "].");
			Order order =wirelineSalesEJBAdapter.getOrderByTransactionId(orderId);
			if (order != null) {
				wirelineSalesEJBAdapter.updateOrderStatus(orderId, getOrderStatus(Long.toString(order.getOrderId()), vestaOrderStatus), OrderManager.ORDER_STATUS_PROCESS_PENDING);
				wirelineSalesEJBAdapter.insertComment(orderId, "Automatic Order Status update to " + vestaOrderStatus, OrderManager.COMMENT_TYPE_USER);
				setResult(Boolean.TRUE);
				String msg = "Successeded to update the Vesta order [" + shoppingCartContextVO.getOrderId() + "] to status [" + vestaOrderStatus + "].";
				logger.info("run", msg);

			} else {
				String msg = "Vesta order for channelTransactionId[" + shoppingCartContextVO.getOrderId() + " is empty";
				logger.error("", "run", msg);
				
			}
		} catch (Exception e) {
			String msg = "Failed to update the Vesta order [" + shoppingCartContextVO.getOrderId() + "] to status [" + vestaOrderStatus + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}
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

}
