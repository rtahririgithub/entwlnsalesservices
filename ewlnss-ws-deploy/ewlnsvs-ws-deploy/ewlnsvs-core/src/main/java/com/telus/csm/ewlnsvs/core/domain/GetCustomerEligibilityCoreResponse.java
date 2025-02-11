package com.telus.csm.ewlnsvs.core.domain;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.CreditAssessmentResult;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.CreditEligibilityResult;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCustomerEligibilityCoreResponse extends CoreResponseBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreditAssessmentResult creditAssessment;
	private CreditEligibilityResult creditEligibility;

	public CreditAssessmentResult getCreditAssessment() {
		return creditAssessment;
	}

	public void setCreditAssessment(CreditAssessmentResult creditAssessment) {
		this.creditAssessment = creditAssessment;
	}

	public CreditEligibilityResult getCreditEligibility() {
		return creditEligibility;
	}

	public void setCreditEligibility(CreditEligibilityResult creditEligibility) {
		this.creditEligibility = creditEligibility;
	}
}
