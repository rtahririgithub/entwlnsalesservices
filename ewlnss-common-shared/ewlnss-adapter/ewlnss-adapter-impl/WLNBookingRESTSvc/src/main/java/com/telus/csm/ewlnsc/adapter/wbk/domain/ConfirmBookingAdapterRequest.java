package com.telus.csm.ewlnsc.adapter.wbk.domain;

public class ConfirmBookingAdapterRequest {

	private String stickysessionid;
	private ConfirmBookingAdapterRequestBody body;
	
	public ConfirmBookingAdapterRequestBody getBody() {
		if (body == null) {
			body = new ConfirmBookingAdapterRequestBody();
		}
		return body;
	}
	public void setBody(ConfirmBookingAdapterRequestBody body) {
		this.body = body;
	}
	public String getStickysessionid() {
		return stickysessionid;
	}
	public void setStickysessionid(String stickysessionid) {
		this.stickysessionid = stickysessionid;
	}
	
}
