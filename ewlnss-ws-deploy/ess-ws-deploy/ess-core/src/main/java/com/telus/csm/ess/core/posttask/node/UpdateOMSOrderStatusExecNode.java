package com.telus.csm.ess.core.posttask.node;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.ucss.wirelinesales.domain.OMSOrderStatus;
import com.telus.ucss.wirelinesales.domain.UpdateOMSOrderRequest;

public class UpdateOMSOrderStatusExecNode extends AbstractNode<Boolean> {

	private ShoppingCartContextVO shoppingCartContextVO;
	private List<OMSOrderStatus> omsOrderStatusList;
	private Map<String, String> productActionMap;
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;

	public UpdateOMSOrderStatusExecNode(ShoppingCartContextVO shoppingCartContextVO, List<OMSOrderStatus> omsOrderStatusList, Map<String, String> productActionMap) {
		super("UpdateOMSOrderStatusExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.omsOrderStatusList = omsOrderStatusList;
		this.productActionMap = productActionMap;
	}

	@Override
	public void run() {
		try {
			String orderId = shoppingCartContextVO.getOrderId();
			if(StringUtils.isEmpty(orderId)) {
				throw new Exception("Vesta orderID is missing.");
			}
			UpdateOMSOrderRequest updateOMSRequest = new UpdateOMSOrderRequest();
			updateOMSRequest.setOmsOrderStatusList(omsOrderStatusList);
			updateOMSRequest.setProductActionMap(productActionMap);
			updateOMSRequest.setChannelTransactionId(orderId);
			String msgBefore = "Calling updateOMSOrderStatus for channelTransactionId: "+  orderId;
			logger.info( "UpdateOMSOrderStatusExecNode.run", msgBefore);
			boolean result = wirelineSalesEJBAdapter.updateOMSOrderStatus(updateOMSRequest);
			setResult(Boolean.TRUE);
			String msgAfter = "update Order OMS status for order :"  + orderId+ " with result : " + result;
			logger.info( "UpdateOMSOrderStatusExecNode.run", msgAfter);

		} catch (Exception e) {
			String msg = "Failed to update Order OMS status for order :"  + shoppingCartContextVO.getOrderId();
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}
	}

}
