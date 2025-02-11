package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.SearchServiceAddressResponse;
 

public class SearchServiceAddressAdapterResponse extends AdapterResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1286343595987119820L;
	
	private SearchServiceAddressResponse response;

	public SearchServiceAddressResponse getResponse() {
		return response;
	}

	public void setResponse(SearchServiceAddressResponse response) {
		this.response = response;
	}
	

}
