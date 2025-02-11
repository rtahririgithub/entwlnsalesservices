package com.telus.csm.ess.common.domain;

import java.io.Serializable;
import java.util.List;

public class MessageVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TYPE_ERROR = "ERROR";
	
	private List<MessageDetailVO> messages;
	private String errCode;
	private String messageType;
	private List<ContextDataAttributesVO> contextDataAttributes;
	
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public List<MessageDetailVO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDetailVO> messages) {
		this.messages = messages;
	}

	public List<ContextDataAttributesVO> getContextDataAttributes() {
		return contextDataAttributes;
	}

	public void setContextDataAttributes(List<ContextDataAttributesVO> contextDataAttributes) {
		this.contextDataAttributes = contextDataAttributes;
	}


}
