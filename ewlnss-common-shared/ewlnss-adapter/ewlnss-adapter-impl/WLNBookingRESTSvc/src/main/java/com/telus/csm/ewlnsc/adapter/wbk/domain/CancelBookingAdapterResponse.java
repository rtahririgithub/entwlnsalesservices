package com.telus.csm.ewlnsc.adapter.wbk.domain;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class CancelBookingAdapterResponse extends WlnOPRestSvcResponseBase {
	
	private static final long serialVersionUID = 1L;
    
	private boolean callSuccessfulInd;

	public boolean isCallSuccessfulInd() {
		return callSuccessfulInd;
	}

	public void setCallSuccessfulInd(boolean callSuccessfulInd) {
		this.callSuccessfulInd = callSuccessfulInd;
	}
	
	
}
