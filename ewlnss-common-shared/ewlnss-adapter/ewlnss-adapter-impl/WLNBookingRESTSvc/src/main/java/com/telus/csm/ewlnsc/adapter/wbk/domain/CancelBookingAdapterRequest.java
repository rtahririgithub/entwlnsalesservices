package com.telus.csm.ewlnsc.adapter.wbk.domain;

public class CancelBookingAdapterRequest {
	private String bookingId;
	private String orderId;

	
	public String getBookingId(){
		return bookingId;
	}
	
	public void setBookingId(String bookingId){
		this.bookingId = bookingId;
	}
	
	public String getOrderId() {
	    return orderId;
	}
	
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
    }
}
