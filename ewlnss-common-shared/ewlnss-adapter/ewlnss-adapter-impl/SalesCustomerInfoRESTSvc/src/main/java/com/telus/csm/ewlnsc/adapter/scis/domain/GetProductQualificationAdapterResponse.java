package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

public class GetProductQualificationAdapterResponse implements Serializable {

	private static final long serialVersionUID = -2379102094161569932L;

	private List<ProductQualification> productQualificationList;
	private ResponseStatus responseStatus;

	public List<ProductQualification> getProductQualificationList() {
		return productQualificationList;
	}
	public void setProductQualificationList(List<ProductQualification> productQualificationList) {
		this.productQualificationList = productQualificationList;
	}
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public boolean hasError() {
		return getResponseStatus().hasError();
	}

}
