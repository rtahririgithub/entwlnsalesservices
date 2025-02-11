package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class TelevisionProductTypeVO extends ProductTypeBaseVO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Boolean competitiveExchangeCarrierInd = null;

	/**
	 * Pass this indicator as true where the customer home phone is on a CLEC
	 * network
	 * 
	 * @return competitiveExchangeCarrierInd
	 **/

	public Boolean isCompetitiveExchangeCarrierInd() {
		return competitiveExchangeCarrierInd;
	}

	public void setCompetitiveExchangeCarrierInd(Boolean competitiveExchangeCarrierInd) {
		this.competitiveExchangeCarrierInd = competitiveExchangeCarrierInd;
	}

}
