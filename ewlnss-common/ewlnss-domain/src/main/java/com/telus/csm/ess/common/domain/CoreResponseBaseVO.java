package com.telus.csm.ess.common.domain;

import java.util.List;

public class CoreResponseBaseVO {

	private boolean hasError;
	private List<MessageVO> messageList;
	
	
	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public List<MessageVO> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageVO> messageList) {
		this.messageList = messageList;
	}

}
