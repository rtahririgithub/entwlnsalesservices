package com.telus.csm.ess.common.domain;

import java.io.Serializable;

public class ValidationMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String offerId;
	
	private String term;
	
	private String reason;
	
	private String action;
	
	private boolean isValid;
	
	private String ruleName;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("ruleName=" + ruleName);
		result.append(", action="  + action);
		result.append(", isValid=" + isValid);
		result.append(", offerId=" + offerId); 
		result.append(", term ="  + term);
		result.append(", reason ="  + reason);
		
		return result.toString();
	}
	
	

}
