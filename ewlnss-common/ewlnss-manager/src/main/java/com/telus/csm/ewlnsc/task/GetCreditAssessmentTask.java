package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterResponse;
import com.telus.csm.ewlnsc.delegate.CreditAssessmentDelegator;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCreditAssessmentTask extends TaskBase {

	private AssessCreditWorthinessAdapterRequest input;
	private AssessCreditWorthinessAdapterResponse result;

	public GetCreditAssessmentTask(AssessCreditWorthinessAdapterRequest input){
		this.input = input;
	}
	
	public AssessCreditWorthinessAdapterResponse getResult(){
		rethrowException();
		return result;
	}
	
	@Override
	protected void execute() {
		result = CreditAssessmentDelegator.getCreditAssessment(input);
	}
	
}
