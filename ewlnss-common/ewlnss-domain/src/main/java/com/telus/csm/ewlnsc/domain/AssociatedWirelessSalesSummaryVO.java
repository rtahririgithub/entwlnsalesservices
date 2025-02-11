package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.MobilityOfferHeader;

public class AssociatedWirelessSalesSummaryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billingAccountNumber;
	private PhoneNumberVO phoneNumber;
	private MobilityOfferHeader offerHeader;
	private String handsetProductCd;
	
	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}
	public PhoneNumberVO getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumberVO phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public MobilityOfferHeader getOfferHeader() {
		return offerHeader;
	}
	public void setOfferHeader(MobilityOfferHeader offerHeader) {
		this.offerHeader = offerHeader;
	}
	public String getHandsetProductCd() {
		return handsetProductCd;
	}
	public void setHandsetProductCd(String handsetProductCd) {
		this.handsetProductCd = handsetProductCd;
	}
	
}
