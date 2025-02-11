package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IEnterpriseConsumerProfileRegistrationSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreateProfileForNewCustomerTask extends TaskBase {

	private CreateProfileForNewCustomerAdapterRequest input;
	private CreateProfileForNewCustomerAdapterResponse result;
	private IEnterpriseConsumerProfileRegistrationSvcAdapter adapter;
	
	public CreateProfileForNewCustomerAdapterResponse getResult(){
		rethrowException();
		return result;
	}
	
	public void setAdapter(IEnterpriseConsumerProfileRegistrationSvcAdapter adapter){
		this.adapter = adapter;
	}
	
	public void setInput(CreateProfileForNewCustomerAdapterRequest input){
		this.input = input;
	}
	
	public CreateProfileForNewCustomerTask(IEnterpriseConsumerProfileRegistrationSvcAdapter adapter, CreateProfileForNewCustomerAdapterRequest input){
		this.adapter = adapter;
		this.input = input;
	}
	
	@Override
	protected void execute() {
		result = adapter.createProfileForNewCustomer(input);
	}

}
