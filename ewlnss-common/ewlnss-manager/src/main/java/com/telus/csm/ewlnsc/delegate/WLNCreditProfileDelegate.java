package com.telus.csm.ewlnsc.delegate;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.ConsumerCreditProfile;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.CreditWorthiness;

/**
 * 
 * @author Jose.Mena
 *
 */
public class WLNCreditProfileDelegate {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(WLNCreditProfileDelegate.class);
	private static final String COMMENT_TXT = "Default - Credit Assessment Not Complete";
	private static final String DECISION_CD = "DFLTR0000001";
	private static final String CREDIT_VALUE_CD_R = "R";
	private static final String CREDIT_VALUE_CD_E = "E";
	
	public GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustomerId(GetCreditProfileByCustomerIdAdapterRequest request) {
		String functionName = "WLNCreditProfileDelegate.getCreditProfileByCustomerId()";
		LOGGER.enter(functionName);
		
		GetCreditProfileByCustomerIdAdapterResponse response;
		IWLNCreditProfileMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IWLNCreditProfileMgmtSvcAdapter.class);
		response = adapter.getCreditProfileByCustomerId(request);
		
		if (response.getCreditProfile() != null && response.getCreditProfile().getCreditWorthiness() != null && isAssessmentNotComplete(response.getCreditProfile())) {
			response.getCreditProfile().getCreditWorthiness().setCreditValueCd(CREDIT_VALUE_CD_E);
		}
		
		LOGGER.exit(functionName);
		return response;
	}

	private boolean isAssessmentNotComplete(ConsumerCreditProfile creditProfile) {
		return (commentMatch(creditProfile.getCommentTxt()) && 
				decisionCdMatch(creditProfile.getCreditWorthiness()) && 
				creditValueCdMatch(creditProfile.getCreditWorthiness()) && 
				firstCreditAssessmentDateIsMissing(creditProfile.getFirstCreditAssessmentDate()));
	}

	private boolean firstCreditAssessmentDateIsMissing(Date firstCreditAssessmentDate) {
		return firstCreditAssessmentDate == null;
	}

	private boolean creditValueCdMatch(CreditWorthiness creditWorthiness) {
		return (!StringUtils.isEmpty(creditWorthiness.getCreditValueCd()) && CREDIT_VALUE_CD_R.equalsIgnoreCase(creditWorthiness.getCreditValueCd()));
	}

	private boolean decisionCdMatch(CreditWorthiness creditWorthiness) {
		return (!StringUtils.isEmpty(creditWorthiness.getDecisionCd()) && DECISION_CD.equalsIgnoreCase(creditWorthiness.getDecisionCd()));
	}

	private boolean commentMatch(String commentTxt) {
		return (!StringUtils.isEmpty(commentTxt) && COMMENT_TXT.equalsIgnoreCase(commentTxt));
	}
	
}
