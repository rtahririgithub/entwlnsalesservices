package com.telus.csm.ess.core.posttask.node;

import java.util.List;

import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class UpdateOrderWLNExecNode extends AbstractNode <OPShoppingCartDelegateResponseVO> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private List<OrderCommentVO> comments;
	private int opOrderId;
	public UpdateOrderWLNExecNode(ShoppingCartContextVO shoppingCartContextVO, List<OrderCommentVO> comments,int opOrderId) {
		super("UpdateOrderWLNExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.comments = comments;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Updating an OP order [" + opOrderId + "] for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "].");
			OPShoppingCartDelegateResponseVO response = new OPShoppingCartDelegate().updateOrder(shoppingCartContextVO, comments, shoppingCartContextVO.getProductOrder(),opOrderId);
			if(response != null) {
				logger.info("run", "OP Shopping cart with ID [" + response.getOpShoppingCartId() 
				+ "] for ESS Shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "] has been updated");
			}
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to update the shopping cart [" +opOrderId + "]. ";
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