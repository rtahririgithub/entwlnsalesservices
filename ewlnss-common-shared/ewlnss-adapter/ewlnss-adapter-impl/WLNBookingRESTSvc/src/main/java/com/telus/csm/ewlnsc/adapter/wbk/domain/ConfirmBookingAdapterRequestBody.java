package com.telus.csm.ewlnsc.adapter.wbk.domain;

public class ConfirmBookingAdapterRequestBody {

	private String orderId;
	private String bookingId;
	private String canBeReachedNumber;

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getCanBeReachedNumber() {
		return canBeReachedNumber;
	}
	public void setCanBeReachedNumber(String canBeReachedNumber) {
		this.canBeReachedNumber = canBeReachedNumber;
	}
}
