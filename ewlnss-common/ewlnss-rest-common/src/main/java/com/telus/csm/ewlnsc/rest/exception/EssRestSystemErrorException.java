package com.telus.csm.ewlnsc.rest.exception;

//for error 500
public class EssRestSystemErrorException extends EssRestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EssRestSystemErrorException(Object result) {
		super(result);
	}

}
