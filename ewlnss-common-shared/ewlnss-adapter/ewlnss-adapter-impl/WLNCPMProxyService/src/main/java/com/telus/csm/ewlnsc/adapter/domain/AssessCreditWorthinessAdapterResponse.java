package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.CreditWorthiness;

/**
 * 
 * @author Jose.Mena
 *
 */
public class AssessCreditWorthinessAdapterResponse extends AdapterResponseBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreditWorthiness assessedCreditWorthiness;

	public CreditWorthiness getAssessedCreditWorthiness() {
		return assessedCreditWorthiness;
	}

	public void setAssessedCreditWorthiness(CreditWorthiness assessedCreditWorthiness) {
		this.assessedCreditWorthiness = assessedCreditWorthiness;
	}
}
