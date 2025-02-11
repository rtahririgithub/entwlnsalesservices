package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.delegate.SalesContextDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class UpdateShoppingCartExecNode extends AbstractNode <Boolean> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;

	public UpdateShoppingCartExecNode(ShoppingCartContextVO shoppingCartContextVO) {
		super("UpdateShoppingCartExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Updating the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "] in cache.");
			SalesContextDelegate.getInstance().putShoppingCart(shoppingCartContextVO.getShoppingCartVO());
			setResult(Boolean.TRUE);
		} catch (Exception e) {
			String msg = "Failed to update the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. ";
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}
	}

}
