package com.telus.csm.ewlnss.adapter.domain;


import java.io.Serializable;
import java.util.List;

public abstract class WlnOPRestSvcResponseBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Message> messageList;

	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	public boolean hasError() {
		if (messageList == null) {
			return false;
		}
		
		for (Message msg : messageList) {
			if (msg.hasError()) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
