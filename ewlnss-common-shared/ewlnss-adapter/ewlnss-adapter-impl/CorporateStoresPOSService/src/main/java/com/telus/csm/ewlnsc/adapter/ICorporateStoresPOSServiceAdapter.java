package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.ccm.domain.SubmitTransactionToPOSAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.SubmitTransactionToPOSAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface ICorporateStoresPOSServiceAdapter extends IAdapterBase {
	
	public SubmitTransactionToPOSAdapterResponse submitTransactionToPOS(final SubmitTransactionToPOSAdapterRequest param);
	
}
