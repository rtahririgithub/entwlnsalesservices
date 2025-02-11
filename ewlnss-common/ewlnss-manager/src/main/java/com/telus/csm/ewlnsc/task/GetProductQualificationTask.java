package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.delegate.ProductQualificationDelegate;

public class GetProductQualificationTask extends TaskBase {
	
	private GetProductQualificationAdapterRequest request;
	private GetProductQualificationAdapterResponse result;
	
	public GetProductQualificationTask(final GetProductQualificationAdapterRequest request) {
		this.request = request;
	}
	
	public GetProductQualificationAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public void setResult(GetProductQualificationAdapterResponse result) {
		this.result = result;
	}

	@Override
	protected void execute() {
		result = new ProductQualificationDelegate().getProductQualification(request);
	}

	public Exception getRuntimeException(){
		if (this.runtimeException != null)
			return this.runtimeException;
		else
			return null;
	}
}
