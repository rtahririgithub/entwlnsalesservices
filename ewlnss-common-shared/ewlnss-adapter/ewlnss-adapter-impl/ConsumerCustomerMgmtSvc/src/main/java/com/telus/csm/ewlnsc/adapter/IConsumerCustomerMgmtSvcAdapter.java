package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerPreferenceAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IConsumerCustomerMgmtSvcAdapter extends IAdapterBase {
	
	public CreateNewCustomerAdapterResponse createNewCustomer(final CreateNewCustomerAdapterRequest param);
	
	public UpdateContactIndividualAdapterResponse updateContactIndividual(final UpdateContactIndividualAdapterRequest param);
	
	public UpdateCustomerOfficialAddressAdapterResponse updateCustomerOfficialAddress(final UpdateCustomerOfficialAddressAdapterRequest param);

	public InsertCustomerPreferenceAdapterResponse insertCustomerPreference(final InsertCustomerPreferenceAdapterRequest param);

	public GetFullCustomerInfoAdapterResponse getFullCustomerInfo(final GetFullCustomerInfoAdapterRequest param);

	public UpdateCustomerPreferenceAdapterResponse updateCustomerPreference(final UpdateCustomerPreferenceAdapterRequest param);
	
}
