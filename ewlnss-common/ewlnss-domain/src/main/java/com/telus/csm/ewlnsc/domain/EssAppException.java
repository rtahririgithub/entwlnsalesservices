package com.telus.csm.ewlnsc.domain;

public class EssAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public EssAppException( String errorCode, String message ) {
		super( message );
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}