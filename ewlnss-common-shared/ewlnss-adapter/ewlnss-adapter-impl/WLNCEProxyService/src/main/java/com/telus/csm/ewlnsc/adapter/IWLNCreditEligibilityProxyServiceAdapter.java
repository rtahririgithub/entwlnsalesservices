package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IWLNCreditEligibilityProxyServiceAdapter extends IAdapterBase {

	GetCreditEligibilityAdapterResponse getCreditEligibility(GetCreditEligibilityAdapterRequest request);
	
}