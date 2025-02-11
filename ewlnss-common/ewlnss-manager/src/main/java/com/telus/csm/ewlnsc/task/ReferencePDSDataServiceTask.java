package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IReferencePDSDataServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;

public class ReferencePDSDataServiceTask extends TaskBase {
	
	private IReferencePDSDataServiceAdapter adapter;
	private GetReferencePDSDataServiceAdapterRequest input;
	private GetReferencePDSDataServiceAdapterResponse result;
	private String entityName;
	
	public void setAdapter(IReferencePDSDataServiceAdapter adapter) {
		this.adapter = adapter;
	}


	public void setInput(GetReferencePDSDataServiceAdapterRequest input) {
		this.input = input;
	}

	public GetReferencePDSDataServiceAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public void setResult(GetReferencePDSDataServiceAdapterResponse result) {
		this.result = result;
	}

	 public ReferencePDSDataServiceTask(IReferencePDSDataServiceAdapter adapter, GetReferencePDSDataServiceAdapterRequest input,String entityName) {
		 this.input=input;
		 this.adapter=adapter;
		 this.entityName=entityName;
	 }
	
	@Override
	protected void execute() {
		result = adapter.getReferenceData(input, entityName);
	}

}
