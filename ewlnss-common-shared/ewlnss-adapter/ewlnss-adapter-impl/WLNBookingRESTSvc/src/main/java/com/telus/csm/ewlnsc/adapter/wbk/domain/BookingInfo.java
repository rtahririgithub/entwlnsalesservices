package com.telus.csm.ewlnsc.adapter.wbk.domain;

/**
 * 
 * @author Jose.Mena
 *
 */
public class BookingInfo {

	private String orderActionId;
	private String installType;
	private String actualBucketType;
	private String actualWorkTime;
	private String appointmentDate;
	private String bookingId;
	private String dueDate;
	private String message;
	private String status;

	public String getOrderActionId() {
		return orderActionId;
	}

	public void setOrderActionId(String orderActionId) {
		this.orderActionId = orderActionId;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public String getActualBucketType() {
		return actualBucketType;
	}

	public void setActualBucketType(String actualBucketType) {
		this.actualBucketType = actualBucketType;
	}

	public String getActualWorkTime() {
		return actualWorkTime;
	}

	public void setActualWorkTime(String actualWorkTime) {
		this.actualWorkTime = actualWorkTime;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
