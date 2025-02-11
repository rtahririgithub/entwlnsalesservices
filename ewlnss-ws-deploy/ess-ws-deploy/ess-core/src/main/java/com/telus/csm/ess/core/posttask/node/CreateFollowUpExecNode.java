package com.telus.csm.ess.core.posttask.node;

import java.util.List;

import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class CreateFollowUpExecNode extends AbstractNode <Boolean> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private List<MessageVO> messages;

	public CreateFollowUpExecNode(ShoppingCartContextVO shoppingCartContextVO, List<MessageVO> messages) {
		super("CreateFollowUpExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.messages = messages;
	}

	@Override
	public void run() {
		logger.error("", "run", "Some post task(s) failed for the shopping cart: " + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId());
		if(messages != null) {
			for(MessageVO msg: messages) {
				if(msg.getMessages() != null) {
					for(MessageDetailVO msgDet: msg.getMessages()) {
						logger.error("", "run", "message: " + msgDet.getMessagetTxt());
					}
				}
			}
		}
	}

}
