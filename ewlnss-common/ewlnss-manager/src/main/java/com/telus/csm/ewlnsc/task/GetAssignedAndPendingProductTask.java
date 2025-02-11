package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.delegate.AssignedAndPendingProductDelegate;

import com.telus.csm.ewlnsc.delegate.AssignedAndPendingProductDelegate2;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;


import commonj.work.Work;

public class GetAssignedAndPendingProductTask extends TaskBase implements Work {

	private GetAssignedAndPendingProductRequestVO input;
	private GetOffersRequestVO offersRequest;
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	private boolean isCallingOMS = false;

	private GetAssignedAndPendingProductResponseVO result;


	//QC74221 - Constructor for calling CSS
	public GetAssignedAndPendingProductTask(GetAssignedAndPendingProductRequestVO input) {
		this.input = input;
	}

	//QC74221 - Constructor for calling OP/OMS
	public GetAssignedAndPendingProductTask(GetAssignedAndPendingProductRequestVO input, GetOffersRequestVO offersRequest, IWirelineSalesEJBAdapter wirelineSalesEJBAdapter) {
		this.input = input;
		this.offersRequest = offersRequest;
		this.wirelineSalesEJBAdapter = wirelineSalesEJBAdapter;
		this.isCallingOMS = true;
	}

	public boolean isCallingOMS() {
		return isCallingOMS;
	}

	public GetAssignedAndPendingProductResponseVO getResult(){
		return result;
	}
	
	@Override
	public void execute() {

		if (isCallingOMS()) {
			result = new AssignedAndPendingProductDelegate2(wirelineSalesEJBAdapter).execute(input, offersRequest);
		} else {
			result = AssignedAndPendingProductDelegate.execute(input);
		}
	}
	
	public Exception getRuntimeException(){
		if (this.runtimeException != null)
			return this.runtimeException;
		else
			return null;
	}

}
