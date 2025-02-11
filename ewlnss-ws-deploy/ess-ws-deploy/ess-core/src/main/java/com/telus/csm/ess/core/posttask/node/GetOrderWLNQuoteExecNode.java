package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class GetOrderWLNQuoteExecNode extends AbstractNode <QuoteResponse> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private Integer opOrderId;

	public GetOrderWLNQuoteExecNode(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		super("GetOrderWLNQuoteExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Getting the Quote info for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].");
			QuoteResponse response = new OPShoppingCartDelegate().getQuote(shoppingCartContextVO, opOrderId);
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to get Quote info for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}

	private void processMessages(QuoteResponse response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
