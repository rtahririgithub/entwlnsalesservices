package com.telus.csm.ewlnsc.adapter.domain;

import java.util.Date;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

public class ReserveTelephoneNumberAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	protected String userId;
	protected Date timestamp;
	protected String coid;
	protected String switchNumber;
	protected String referenceNumber;
	protected Long telephoneNumber;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getCoid() {
		return coid;
	}

	public void setCoid(String coid) {
		this.coid = coid;
	}

	public String getSwitchNumber() {
		return switchNumber;
	}

	public void setSwitchNumber(String switchNumber) {
		this.switchNumber = switchNumber;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

}