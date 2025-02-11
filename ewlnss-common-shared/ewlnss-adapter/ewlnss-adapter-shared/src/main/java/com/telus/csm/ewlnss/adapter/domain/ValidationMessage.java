package com.telus.csm.ewlnss.adapter.domain;

public class ValidationMessage {
	
    private String messageType;
    private String message;
    
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean hasError() {
		return "error".equalsIgnoreCase(getMessageType()) || "exception".equalsIgnoreCase(getMessageType());
	}
	
}