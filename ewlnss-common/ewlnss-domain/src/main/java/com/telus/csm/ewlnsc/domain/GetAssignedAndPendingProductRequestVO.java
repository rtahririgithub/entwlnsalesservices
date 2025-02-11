package com.telus.csm.ewlnsc.domain;



public class GetAssignedAndPendingProductRequestVO extends CoreRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerId;
	private String billingAccountNumber;
	private boolean splitOfferInd;
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}
	
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}

	public boolean isSplitOfferInd() {
		return splitOfferInd;
	}

	public void setSplitOfferInd(boolean splitOfferInd) {
		this.splitOfferInd = splitOfferInd;
	}

	
}
