package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IConsumerIdentityProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.UpdateOwnerIdentityCredentialAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateOwnerIdentityCredentialAdapterResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class UpdateOwnerIdentityCredentialTask extends TaskBase {

	private UpdateOwnerIdentityCredentialAdapterRequest input;
	private UpdateOwnerIdentityCredentialAdapterResponse result;
	private IConsumerIdentityProfileMgmtSvcAdapter adapter;
	
	public UpdateOwnerIdentityCredentialAdapterResponse getResult(){
		rethrowException();
		return result;
	}
	
	public void setAdapter(IConsumerIdentityProfileMgmtSvcAdapter adapter){
		this.adapter = adapter;
	}
	
	public void setInput(UpdateOwnerIdentityCredentialAdapterRequest input){
		this.input = input;
	}
	
	public UpdateOwnerIdentityCredentialTask(IConsumerIdentityProfileMgmtSvcAdapter adapter, UpdateOwnerIdentityCredentialAdapterRequest input){
		this.adapter = adapter;
		this.input = input;
	}

	@Override
	protected void execute() {
		result = adapter.updateOwnerIdentityCredential(input);
	}

}
