package com.telus.csm.ewlnsc.task;

public class GetServiceAddressTask extends TaskBase  {

	private Object input;
	private Object result;
	
	public GetServiceAddressTask(Object input){
		this.input = input;
	}
	
	public Object getResult(){
		rethrowException();
		return result;
	}
	
	@Override
	protected void execute() {
	}

}
