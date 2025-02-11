package com.telus.csm.ewlnsc.rest.exception;


//This Exception carries the responseObject to be returned as a JSON string  
public abstract class EssRestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object responseObject;


	public EssRestException(Object result) {
		responseObject = result;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}

	public Object getResponse() {
		return responseObject;
	}

}
