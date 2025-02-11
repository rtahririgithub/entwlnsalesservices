package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IConsumerBillingAccountManagementServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterResponse;

import commonj.work.Work;

public class GetBillingAccountTask extends TaskBase implements Work{

	private GetBillingAccountAdapterRequest input;
	private GetBillingAccountAdapterResponse result;
	private IConsumerBillingAccountManagementServiceAdapter adapter;
	
	public GetBillingAccountTask(GetBillingAccountAdapterRequest input, IConsumerBillingAccountManagementServiceAdapter adapter){
		this.input = input;
		this.adapter = adapter;
	}
	
	public GetBillingAccountAdapterResponse getResult() {
		rethrowException();
		return result;
	}
	
	@Override
	protected void execute() {
		result = this.adapter.getBillingAccount(input);
	}

}
