package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * InternetProductType
 */

public class InternetProductTypeVO extends ProductTypeBaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CompetitiveLocalExchageCarrierTypeVO competitiveExchangeCarrierInfo = null;

	public CompetitiveLocalExchageCarrierTypeVO getCompetitiveExchangeCarrierInfo() {
		return competitiveExchangeCarrierInfo;
	}

	public void setCompetitiveExchangeCarrierInfo(CompetitiveLocalExchageCarrierTypeVO competitiveExchangeCarrierInfo) {
		this.competitiveExchangeCarrierInfo = competitiveExchangeCarrierInfo;
	}

}
