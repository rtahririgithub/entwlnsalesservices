package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class RetrieveOrderWLNExecNode extends AbstractNode <OPShoppingCartDelegateResponseVO> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private Integer opOrderId;
	public RetrieveOrderWLNExecNode(ShoppingCartContextVO shoppingCartContextVO,Integer opOrderId) {
		super("RetrieveOrderWLNExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Retrieving an OP order for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].");
			OPShoppingCartDelegateResponseVO response = new OPShoppingCartDelegate().retrieveOrder(shoppingCartContextVO.getShoppingCartVO(),opOrderId);

			if ( (response != null) && (response.getProductOrder() != null) ) {
				shoppingCartContextVO.setProductOrder(response.getProductOrder());
			}

			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to retrieve OP order for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].";
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