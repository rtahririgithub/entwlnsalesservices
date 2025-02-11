package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterResponse;

public class GetProductsByCustomerIdTask extends TaskBase {
	
	private GetProductsByCustomerIdAdapterRequest input;
	private GetProductsByCustomerIdAdapterResponse result;
	private IOrderQueryRestSvcAdapter adapter;
	
	public GetProductsByCustomerIdTask(IOrderQueryRestSvcAdapter adapter, GetProductsByCustomerIdAdapterRequest input) {
		this.adapter = adapter;
		this.input = input;
	}
	
	public GetProductsByCustomerIdAdapterRequest getInput() {
		return input;
	}

	public void setInput(GetProductsByCustomerIdAdapterRequest input) {
		this.input = input;
	}

	public GetProductsByCustomerIdAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public void setResult(GetProductsByCustomerIdAdapterResponse result) {
		this.result = result;
	}

	@Override
	protected void execute() {
		result = adapter.getProductsByCustomerId(input);
	}

}
