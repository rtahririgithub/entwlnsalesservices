package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class SalesRepAssociatedOutletVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String outletInternalId = null;
	private String channelOutletId = null;

	public String getOutletInternalId() {
		return outletInternalId;
	}

	public void setOutletInternalId(String outletInternalId) {
		this.outletInternalId = outletInternalId;
	}

	public String getChannelOutletId() {
		return channelOutletId;
	}

	public void setChannelOutletId(String channelOutletId) {
		this.channelOutletId = channelOutletId;
	}

}
