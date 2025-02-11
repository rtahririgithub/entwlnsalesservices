package com.telus.csm.ewlnsc.rules.base;

/**
 * If no error messages in the response, then status will be SUCCESS_STATUS
 * If any errors, then status will be ERROR_STATUS
 * If only warnings presented in response, then status is WARNING_STATUS 
 * @author Adrian
 *
 */
public enum RESPONSE_STATUS {
	  ERROR_STATUS("ERROR"),
	  WARNING_STATUS("WARNING"),
	  SUCCESS_STATUS("SUCCESS")
	  ;
	  
	  private String status;
	  private RESPONSE_STATUS(String status) {
		  this.status = status;
	  }
	  public String getStatus() {
		return status;
	  }
}
