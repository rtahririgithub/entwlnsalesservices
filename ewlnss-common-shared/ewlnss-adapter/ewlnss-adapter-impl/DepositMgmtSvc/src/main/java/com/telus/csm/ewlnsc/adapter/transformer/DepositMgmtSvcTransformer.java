package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.CancelDepositAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CancelDepositAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CreateDepositAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateDepositAdapterResponse;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CancelDeposit;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CancelDepositResponse;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CreateDeposit;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CreateDepositResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class DepositMgmtSvcTransformer {

	private DepositMgmtSvcTransformer(){
		
	}
	
	public static CreateDeposit transformRequest(CreateDepositAdapterRequest param){
		CreateDeposit request = new CreateDeposit();
		request.setInput(param.getInput());
		return request;
	}
	
	public static CreateDepositAdapterResponse transformResponse (CreateDepositResponse param){
		CreateDepositAdapterResponse response = new CreateDepositAdapterResponse();
		response.setOutput(param.getOutput());
		return response;
	}
	
	public static CancelDeposit transformRequest(CancelDepositAdapterRequest param){
		CancelDeposit request = new CancelDeposit();
		request.setInput(param.getInput());
		return request;
	}
	
	public static CancelDepositAdapterResponse transformResponse(CancelDepositResponse param){
		CancelDepositAdapterResponse response = new CancelDepositAdapterResponse();
		response.setOutput(param.getOutput());
		return response;
	}
}
