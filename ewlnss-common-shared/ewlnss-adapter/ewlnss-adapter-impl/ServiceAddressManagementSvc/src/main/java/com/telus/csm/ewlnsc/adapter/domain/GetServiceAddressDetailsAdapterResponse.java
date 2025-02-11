package com.telus.csm.ewlnsc.adapter.domain;



import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.GetServiceAddressDetailsResponse;
 
public class GetServiceAddressDetailsAdapterResponse extends AdapterResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 208769627051305356L;
	private GetServiceAddressDetailsResponse response;
	
	public GetServiceAddressDetailsResponse getResponse() {
		return response;
	}
	public void setResponse(GetServiceAddressDetailsResponse response) {
		this.response = response;
	}


}
