package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.RequiredService;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetAvailableServiceInstanceListAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;
	private List<RequiredService> serviceList;

	public List<RequiredService> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<RequiredService> serviceList) {
		this.serviceList = serviceList;
	}
}