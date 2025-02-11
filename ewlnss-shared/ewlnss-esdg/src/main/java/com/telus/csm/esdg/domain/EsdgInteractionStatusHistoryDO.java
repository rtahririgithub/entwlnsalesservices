package com.telus.csm.esdg.domain;

import java.io.Serializable;

public class EsdgInteractionStatusHistoryDO implements Serializable {
	
	private static final long serialVersionUID = 8841874477441830346L;
	
	protected String interactionStatus;
	protected long interactionStatusTimeInMills;
	
	public String getInteractionStatus() {
		return interactionStatus;
	}
	public void setInteractionStatus(String interactionStatus) {
		this.interactionStatus = interactionStatus;
	}
	public long getInteractionStatusTimeInMills() {
		return interactionStatusTimeInMills;
	}
	public void setInteractionStatusTimeInMills(long interactionStatusTimeInMills) {
		this.interactionStatusTimeInMills = interactionStatusTimeInMills;
	}
	
	
}
