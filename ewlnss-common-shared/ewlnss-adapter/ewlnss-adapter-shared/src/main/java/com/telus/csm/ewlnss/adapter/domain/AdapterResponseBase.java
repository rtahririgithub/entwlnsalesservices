package com.telus.csm.ewlnss.adapter.domain;

import java.io.Serializable;

public abstract class AdapterResponseBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AdapterResponseMessage adapterResponseMessage;
	
	private boolean successfulProcessInd = true;
	private boolean msgHasError = false;

	public boolean isSuccessfulProcessInd() {
		return successfulProcessInd;
	}

	public void setSuccessfulProcessInd(boolean successfulProcessInd) {
		this.successfulProcessInd = successfulProcessInd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isMsgHasError() {
		return msgHasError;
	}

	public void setMsgHasError(boolean msgHasError) {
		this.msgHasError = msgHasError;
	}

	public AdapterResponseMessage getAdapterResponseMessage() {
		return adapterResponseMessage;
	}

	public void setAdapterResponseMessage(AdapterResponseMessage adapterResponseMessage) {
		this.adapterResponseMessage = adapterResponseMessage;
	}

}
