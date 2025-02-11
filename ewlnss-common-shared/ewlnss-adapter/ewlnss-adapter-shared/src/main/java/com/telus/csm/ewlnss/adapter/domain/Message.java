package com.telus.csm.ewlnss.adapter.domain;

import java.util.List;

public class Message {
	
	private String messageType;
	private String message;
	private String validationItem;
    private String status;
    private String orderItemId;
    private List<ValidationMessage> validationMessageList;
    
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
	public List<ValidationMessage> getValidationMessageList() {
		return validationMessageList;
	}
	public void setValidationMessageList(List<ValidationMessage> validationMessageList) {
		this.validationMessageList = validationMessageList;
	}

	public String getValidationItem() {
		return validationItem;
	}
	public void setValidationItem(String validationItem) {
		this.validationItem = validationItem;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
    
	public boolean hasError() {
		
		if ("error".equalsIgnoreCase(messageType) || "exception".equalsIgnoreCase(messageType)) {
			return true;
		}
		
		if (validationMessageList == null) {
			return false;
		}
		
		for (ValidationMessage msg : validationMessageList) {
			if (msg.hasError()) {
				return true;
			}
		}
		
		return false;
	}
	
}