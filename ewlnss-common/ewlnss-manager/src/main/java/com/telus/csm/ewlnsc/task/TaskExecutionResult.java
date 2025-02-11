package com.telus.csm.ewlnsc.task;

import java.util.ArrayList;
import java.util.List;


public class TaskExecutionResult<S> 
{
	boolean taskCompletionFlag = false;
	boolean serviceCallSuccessFlag = false;
	S serviceCallResponse;
	List<Throwable> errors = new ArrayList<Throwable>();
	
	public boolean isTaskCompletionFlag() 
	{
		return taskCompletionFlag;
	}

	public void setTaskCompletionFlag( boolean taskCompletionFlag ) 
	{
		this.taskCompletionFlag = taskCompletionFlag;
	}

	public boolean isServiceCallSuccessFlag() 
	{
		return serviceCallSuccessFlag;
	}
	
	public void setServiceCallSuccessFlag(boolean serviceCallSuccessFlag) 
	{
		this.serviceCallSuccessFlag = serviceCallSuccessFlag;
	}
	
	public S getServiceCallResponse() 
	{
		return serviceCallResponse;
	}
	
	public void setServiceCallResponse(S serviceCallResponse) 
	{
		this.serviceCallResponse = serviceCallResponse;
	}
	
	public List<Throwable> getErrors() 
	{
		return errors;
	}
	
	public void setErrors(List<Throwable> errors) 
	{
		this.errors = errors;
	}

}