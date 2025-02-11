package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IConsumerDiaryMgmtSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param CreateCustomerEventRequestDO
	 * @return
	 */
	public CreateCustomerEventAdapterResponse createCustomerEvent(final CreateCustomerEventAdapterRequest param);

}
