package com.telus.csm.ewlnsc.adapter.wbk.domain;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class ConfirmBookingAdapterResponse  extends WlnOPRestSvcResponseBase {

	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String bookingId;
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	
	
	
}
