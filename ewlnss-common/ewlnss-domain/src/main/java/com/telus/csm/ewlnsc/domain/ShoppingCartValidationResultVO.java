package com.telus.csm.ewlnsc.domain;

import java.util.List;

public class ShoppingCartValidationResultVO {

	boolean isValid;
	List<ShoppingCartValidationTraceVO> validationResults;

	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public List<ShoppingCartValidationTraceVO> getValidationResults() {
		return validationResults;
	}
	public void setValidationResults(List<ShoppingCartValidationTraceVO> resultList) {
		this.validationResults = resultList;
	}
}
