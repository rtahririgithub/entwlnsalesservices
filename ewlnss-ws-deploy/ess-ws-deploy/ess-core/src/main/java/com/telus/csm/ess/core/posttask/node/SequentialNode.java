package com.telus.csm.ess.core.posttask.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.csm.ewlnss.adapter.domain.ValidationMessage;

public abstract class SequentialNode {
	
	public abstract void callSvc();
	
	
	protected void handleException(Exception e, String msg,List<MessageVO> messages) {
		MessageVO msgVO = new MessageVO();
		msgVO.setMessageType(MessageVO.TYPE_ERROR);
		MessageDetailVO msgDetailVO = new MessageDetailVO();
		msgDetailVO.setMessagetTxt((msg == null? "": msg) + " Exception: " + e.getMessage());
		msgDetailVO.setLocaleCd(Locale.CANADA.toString());
		if(msgVO.getMessages() == null) {
			msgVO.setMessages(new ArrayList<MessageDetailVO>());
		}
		msgVO.getMessages().add(msgDetailVO);
		messages.add(msgVO);
	}

	protected void processMessages(List<Message> respMessages,List<MessageVO> messages) {
		if(respMessages != null && !respMessages.isEmpty()) {
			for(Message msg: respMessages) {
				if(msg.hasError()) {
					MessageVO msgVO = new MessageVO();
					msgVO.setMessageType(MessageVO.TYPE_ERROR);
					MessageDetailVO msgDetailVO = new MessageDetailVO();
					msgDetailVO.setMessagetTxt(msg.getMessage());
					msgDetailVO.setLocaleCd(Locale.CANADA.toString());
					if(msgVO.getMessages() == null) {
						msgVO.setMessages(new ArrayList<MessageDetailVO>());
					}
					msgVO.getMessages().add(msgDetailVO);
					if(msg.getValidationMessageList() != null) {
						for(ValidationMessage valMsg: msg.getValidationMessageList()) {
							MessageDetailVO msgDetVO = new MessageDetailVO();
							msgDetVO.setMessagetTxt(valMsg.getMessage());
							msgDetVO.setLocaleCd(Locale.CANADA.toString());
							msgVO.getMessages().add(msgDetVO);
						}
					}
					messages.add(msgVO);
				}
			}
		}
	}


}
