package com.telus.csm.ewlnsc.domain;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;

public class ServiceAddressResponseVO extends CoreResponseBase {

	private static final long serialVersionUID = 1L;
	
	private ServiceAddress serviceAddress;
	private boolean exchangeForborneStatusInd;
	
	public ServiceAddress getServiceAddress() {
		return serviceAddress;
	}
	
	public void setServiceAddress(ServiceAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	
	public boolean isExchangeForborneStatusInd() {
		return exchangeForborneStatusInd;
	}
	
	public void setExchangeForborneStatusInd(boolean exchangeForborneStatusInd) {
		this.exchangeForborneStatusInd = exchangeForborneStatusInd;
	}

}
