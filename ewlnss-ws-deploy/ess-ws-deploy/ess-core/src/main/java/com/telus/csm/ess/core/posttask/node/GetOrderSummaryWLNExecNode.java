package com.telus.csm.ess.core.posttask.node;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterResponse;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ExternalOrderDetail;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;

@Deprecated
public class GetOrderSummaryWLNExecNode extends AbstractNode <GetOrderSummaryByOrderIdAdapterResponse> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;

	public GetOrderSummaryWLNExecNode(ShoppingCartContextVO shoppingCartContextVO) {
		super("GetOrderSummaryWLNExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Getting an OP Order Summary for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId List [" + EnterpriseWLNOrderUtil.getOpOrderList(shoppingCartContextVO.getShoppingCartVO().getExternalOrderDetailList())+ "].");
			GetOrderSummaryByOrderIdAdapterResponse response = new OPShoppingCartDelegate().getOrderSummaryByOrderId(shoppingCartContextVO);
			if ( (response != null) && (response.getProductOrderDetail() != null) ) {
				shoppingCartContextVO.setProductOrder(response.getProductOrderDetail());
			}
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to an OP Order Summary for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId List [" + EnterpriseWLNOrderUtil.getOpOrderList(shoppingCartContextVO.getShoppingCartVO().getExternalOrderDetailList()) + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}

	private void processMessages(GetOrderSummaryByOrderIdAdapterResponse response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
