package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class GetOrderWLNDepositExecNode extends AbstractNode <GetDepositInfoResponse> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private Integer opOrderId;

	public GetOrderWLNDepositExecNode(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		super("GetOrderWLNDepositExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Getting an OP deposit info the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].");
			GetDepositInfoResponse response = new OPShoppingCartDelegate().getDepositInfo(shoppingCartContextVO,opOrderId);
			shoppingCartContextVO.setGetDepositInfoResponse(response);
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to get deposit info for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}

	private void processMessages(GetDepositInfoResponse response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
