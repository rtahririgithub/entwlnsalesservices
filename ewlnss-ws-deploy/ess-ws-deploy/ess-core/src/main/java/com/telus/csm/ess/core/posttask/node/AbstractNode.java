package com.telus.csm.ess.core.posttask.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.csm.ewlnss.adapter.domain.ValidationMessage;

import commonj.work.Work;

public abstract class AbstractNode <T> extends SpringBeanAutowiringSupport implements Work {
	private String name;
	private List<MessageVO> messages = new ArrayList<MessageVO>();
	private T result;
	
	protected AbstractNode(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isDaemon() {
		return false;
	}

	@Override
	public void release() {
	}

	public List<MessageVO> getMessages() {
		return messages;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	protected void handleException(Exception e, String msg) {
		MessageVO msgVO = new MessageVO();
		msgVO.setMessageType(MessageVO.TYPE_ERROR);
		MessageDetailVO msgDetailVO = new MessageDetailVO();
		msgDetailVO.setMessagetTxt((msg == null? "": msg) + " Exception: " + e.getMessage());
		msgDetailVO.setLocaleCd(Locale.CANADA.toString());
		if(msgVO.getMessages() == null) {
			msgVO.setMessages(new ArrayList<MessageDetailVO>());
		}
		msgVO.getMessages().add(msgDetailVO);
		getMessages().add(msgVO);
	}

	protected void processMessages(List<Message> respMessages) {
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
					getMessages().add(msgVO);
				}
			}
		}
	}


}
