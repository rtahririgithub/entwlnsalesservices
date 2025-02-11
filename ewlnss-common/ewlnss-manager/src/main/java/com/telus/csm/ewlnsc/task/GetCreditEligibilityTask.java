package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IWLNCreditEligibilityProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCreditEligibilityTask extends TaskBase {

	private GetCreditEligibilityAdapterRequest input;
	private GetCreditEligibilityAdapterResponse result;
	private IWLNCreditEligibilityProxyServiceAdapter adapter;

	public GetCreditEligibilityTask(IWLNCreditEligibilityProxyServiceAdapter adapter,
			GetCreditEligibilityAdapterRequest input) {
		this.adapter = adapter;
		this.input = input;
	}

	public GetCreditEligibilityAdapterRequest getInput() {
		return input;
	}

	public GetCreditEligibilityAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	@Override
	protected void execute() {
		result = adapter.getCreditEligibility(input);
	}

}
