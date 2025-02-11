package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.UpdateOwnerIdentityCredentialAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateOwnerIdentityCredentialAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IConsumerIdentityProfileMgmtSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param UpdateOwnerIdentityCredentialRequestDO
	 * @return
	 */
	public UpdateOwnerIdentityCredentialAdapterResponse updateOwnerIdentityCredential(final UpdateOwnerIdentityCredentialAdapterRequest createCustomerRequestDO);

}
