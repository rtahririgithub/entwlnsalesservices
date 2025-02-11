package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.SearchServiceAddress;

public class SearchServiceAddressAdapterRequest extends AdapterRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5044597304193047279L;
	
	private SearchServiceAddress serviceAddress;

	public SearchServiceAddress getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(SearchServiceAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}


}
