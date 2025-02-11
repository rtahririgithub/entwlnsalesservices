package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IWLNCreditProfileManagementProxyServiceAdapter extends IAdapterBase {

	public AssessCreditWorthinessAdapterResponse assessCreditWorthiness(AssessCreditWorthinessAdapterRequest request);
}
