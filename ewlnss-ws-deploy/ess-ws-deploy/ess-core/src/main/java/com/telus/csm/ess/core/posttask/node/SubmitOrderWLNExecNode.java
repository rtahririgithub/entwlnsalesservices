package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterResponse;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class SubmitOrderWLNExecNode extends AbstractNode <SubmitOrderAdapterResponse> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private boolean forceBackOffice;
	private Integer opOrderId;

	public SubmitOrderWLNExecNode(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId, boolean forceBackOffice) {
		super("SubmitOrderWLNExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.forceBackOffice = forceBackOffice;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Submitting the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].");
			SubmitOrderAdapterResponse response = new OPShoppingCartDelegate().submitOrder(shoppingCartContextVO, opOrderId, forceBackOffice);
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to submit to OP the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}
	
	private void processMessages(SubmitOrderAdapterResponse response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
