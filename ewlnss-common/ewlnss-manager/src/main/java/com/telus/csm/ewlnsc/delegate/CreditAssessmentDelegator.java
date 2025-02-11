package com.telus.csm.ewlnsc.delegate;

import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileManagementProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterResponse;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreditAssessmentDelegator {
	
	private CreditAssessmentDelegator(){
		
	}

	public static AssessCreditWorthinessAdapterResponse getCreditAssessment(AssessCreditWorthinessAdapterRequest request) {
		
		IWLNCreditProfileManagementProxyServiceAdapter wlnCreditProfMgProxySvcAdapter = AdapterFactory.getAdapter(IWLNCreditProfileManagementProxyServiceAdapter.class);
		return wlnCreditProfMgProxySvcAdapter.assessCreditWorthiness(request);
		
	}
}
