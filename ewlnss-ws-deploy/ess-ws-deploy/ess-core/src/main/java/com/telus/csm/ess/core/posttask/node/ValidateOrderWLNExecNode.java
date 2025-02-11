package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ValidateOrderWLNExecNode extends AbstractNode <OPShoppingCartDelegateResponseVO> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private Integer opOrderId;

	public ValidateOrderWLNExecNode(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		super("ValidateOrderWLNExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Validating the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].");
			OPShoppingCartDelegateResponseVO response = new OPShoppingCartDelegate().validateOrder(shoppingCartContextVO, opOrderId);
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to validate in OP the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}

	private void processMessages(OPShoppingCartDelegateResponseVO response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
