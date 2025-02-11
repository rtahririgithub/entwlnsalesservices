package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterResponse;

/**
 * 
 * @author x145592
 */
public class UpdateContactIndividualTask extends TaskBase {

	private UpdateContactIndividualAdapterRequest input;
	private UpdateContactIndividualAdapterResponse result;
	private IConsumerCustomerMgmtSvcAdapter adapter;
	
	public UpdateContactIndividualAdapterResponse getResult() {
		rethrowException();
		return result;
	}
	
	public void setAdapter(final IConsumerCustomerMgmtSvcAdapter adapter) {
		this.adapter = adapter;
	}
	
	public void setInput(final UpdateContactIndividualAdapterRequest input) {
		this.input = input;
	}
	
	public UpdateContactIndividualTask(final IConsumerCustomerMgmtSvcAdapter adapter, final UpdateContactIndividualAdapterRequest input) {
		this.adapter = adapter;
		this.input = input;
	}
	
	@Override
	protected void execute() {
		result = adapter.updateContactIndividual(input);
	}

}
