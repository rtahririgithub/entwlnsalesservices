package com.telus.csm.ewlnsis.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreResponse;
import com.telus.csm.ewlnsc.domain.ServiceInstallDetailVO;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceInstallDetail;

public class GetInstallationDetailCoreResponse extends GetAvailableInstallDateCoreResponse  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ServiceInstallDetailVO> serviceInstallDetail = new ArrayList<ServiceInstallDetailVO>();

	public List<ServiceInstallDetailVO> getServiceInstallDetail() {
		return serviceInstallDetail;
	}

	public void setServiceInstallDetail(List<ServiceInstallDetailVO> serviceInstallDetail) {
		this.serviceInstallDetail = serviceInstallDetail;
	}
	
	
}
