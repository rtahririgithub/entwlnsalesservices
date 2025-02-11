package com.telus.csm.ess.common.domain;

import java.io.Serializable;

public class MessageDetailVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String messagetTxt;
	private String localeCd;
	public String getMessagetTxt() {
		return messagetTxt;
	}
	public void setMessagetTxt(String messagetTxt) {
		this.messagetTxt = messagetTxt;
	}
	public String getLocaleCd() {
		return localeCd;
	}
	public void setLocaleCd(String localeCd) {
		this.localeCd = localeCd;
	}

}
