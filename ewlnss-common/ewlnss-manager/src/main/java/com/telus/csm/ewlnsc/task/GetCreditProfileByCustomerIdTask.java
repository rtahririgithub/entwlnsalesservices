package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.delegate.WLNCreditProfileDelegate;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCreditProfileByCustomerIdTask extends TaskBase {

	private GetCreditProfileByCustomerIdAdapterRequest input;
	private GetCreditProfileByCustomerIdAdapterResponse result;

	public GetCreditProfileByCustomerIdTask(GetCreditProfileByCustomerIdAdapterRequest input) {
		this.input = input;
	}

	public GetCreditProfileByCustomerIdAdapterRequest getInput() {
		return input;
	}

	public GetCreditProfileByCustomerIdAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	@Override
	protected void execute() {
		result = new WLNCreditProfileDelegate().getCreditProfileByCustomerId(input);
	}

}
