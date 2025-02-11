package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IWLNOrderChargingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;

import commonj.work.Work;


/*
 * This class is not used as the OP call is moved to the Post Task (WirelinePostTaskStrategy).
 */

public class GetOrderDepositTask extends TaskBase implements Work{

	private GetDepositInfoRequest input;
	private GetDepositInfoResponse result;
	private IWLNOrderChargingRestSvcAdapter adapter;
	
	public GetOrderDepositTask(GetDepositInfoRequest input, IWLNOrderChargingRestSvcAdapter adapter){
		this.input = input;
		this.adapter = adapter;
	}
	
	public GetDepositInfoResponse getResult() {
		rethrowException();
		return result;
	}
	
	@Override
	protected void execute() {
		result = this.adapter.getDepositInfo(input);
	}

}
