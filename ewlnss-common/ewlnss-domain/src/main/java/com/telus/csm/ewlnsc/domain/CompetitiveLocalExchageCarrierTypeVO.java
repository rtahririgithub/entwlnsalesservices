package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class CompetitiveLocalExchageCarrierTypeVO implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String competitiveLocalExchageCarrierId = null;
	 
	 private TelephoneNumberTypeVO clecPhoneNumber = null;
	 

	  private Boolean competitiveExchangeCarrierInd = null;

	public String getCompetitiveLocalExchageCarrierId() {
		return competitiveLocalExchageCarrierId;
	}

	public void setCompetitiveLocalExchageCarrierId(String competitiveLocalExchageCarrierId) {
		this.competitiveLocalExchageCarrierId = competitiveLocalExchageCarrierId;
	}

	public TelephoneNumberTypeVO getClecPhoneNumber() {
		return clecPhoneNumber;
	}

	public void setClecPhoneNumber(TelephoneNumberTypeVO clecPhoneNumber) {
		this.clecPhoneNumber = clecPhoneNumber;
	}

	public Boolean getCompetitiveExchangeCarrierInd() {
		return competitiveExchangeCarrierInd;
	}

	public void setCompetitiveExchangeCarrierInd(Boolean competitiveExchangeCarrierInd) {
		this.competitiveExchangeCarrierInd = competitiveExchangeCarrierInd;
	}

}
