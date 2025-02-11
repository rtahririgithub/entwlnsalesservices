package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IEnterpriseCreditProfileManagementServiceAdapter extends IAdapterBase {
	public UpdateCreditProfileAdapterResponse updateCreditProfile(UpdateCreditProfileAdapterRequest request);
}
