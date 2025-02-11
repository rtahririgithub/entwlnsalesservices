package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterResponse;

/**
 * 
 * @author x145592
 */
public class UpdateOfficialAddressTask extends TaskBase {

	private UpdateCustomerOfficialAddressAdapterRequest input;
	private UpdateCustomerOfficialAddressAdapterResponse result;
	private IConsumerCustomerMgmtSvcAdapter adapter;
	
	public UpdateCustomerOfficialAddressAdapterResponse getResult() {
		rethrowException();
		return result;
	}
	
	public void setAdapter(final IConsumerCustomerMgmtSvcAdapter adapter) {
		this.adapter = adapter;
	}
	
	public void setInput(final UpdateCustomerOfficialAddressAdapterRequest input) {
		this.input = input;
	}
	
	public UpdateOfficialAddressTask(final IConsumerCustomerMgmtSvcAdapter adapter, final UpdateCustomerOfficialAddressAdapterRequest input) {
		this.adapter = adapter;
		this.input = input;
	}
	
	@Override
	protected void execute() {
		result = adapter.updateCustomerOfficialAddress(input);
	}

}
