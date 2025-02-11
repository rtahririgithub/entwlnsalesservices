package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IEnterpriseConsumerProfileRegistrationSvcAdapter extends IAdapterBase {
	
	public CreateProfileForNewCustomerAdapterResponse createProfileForNewCustomer(CreateProfileForNewCustomerAdapterRequest param);

}
