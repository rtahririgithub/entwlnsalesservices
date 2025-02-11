package com.telus.csm.ewlnsc.domain;

public class BillingAccountIdentificationVO {

	/**
	 * define constant for enabler billing system id.
	 */
	
	public static final String ENABLER_BILLING_ACCOUNT_SYSTEM_CODE = "1001";

	/**
	 * define constant for knowbility billing system id.
	 */
	public static final String KB_BILLING_ACCOUNT_SYSTEM_CODE = "130";
	
	protected String billingAccountNumber;
	
	protected String billingSystemId;

	public BillingAccountIdentificationVO() {
		super();
	}

	public BillingAccountIdentificationVO(String billingSystemId, String billingAccountNumber) {
		this();
		
		this.billingAccountNumber = billingAccountNumber;
		this.billingSystemId = billingSystemId;
	}

	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}
	
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}
	
	public String getBillingSystemId() {
		return billingSystemId;
	}

	public void setBillingSystemId(String billingSystemId) {
		this.billingSystemId = billingSystemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingAccountNumber == null) ? 0 : billingAccountNumber.hashCode());
		result = prime * result + ((billingSystemId == null) ? 0 : billingSystemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingAccountIdentificationVO other = (BillingAccountIdentificationVO) obj;
		if (billingAccountNumber == null) {
			if (other.billingAccountNumber != null)
				return false;
		} else if (!billingAccountNumber.equals(other.billingAccountNumber))
			return false;
		if (billingSystemId == null) {
			if (other.billingSystemId != null)
				return false;
		} else if (!billingSystemId.equals(other.billingSystemId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BillingAccountIdentificationVO [billingAccountNumber=" + billingAccountNumber + ", billingSystemId="
				+ billingSystemId + "]";
	}

}