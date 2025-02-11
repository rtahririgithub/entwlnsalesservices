package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;

public class FeasibilityServiceTask extends TaskBase {

	private CheckProductFeasibilityAdapterRequest input;
	private CheckProductFeasibilityAdapterResponse result;
	private IFeasibilityServiceAdapter adapter;

	public CheckProductFeasibilityAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public FeasibilityServiceTask(IFeasibilityServiceAdapter adapter, CheckProductFeasibilityAdapterRequest input) {
		this.input=input;
		this.adapter=adapter;
	}

	@Override
	protected void execute() {
		result = adapter.checkProductFeasibility(input);
	}

}
