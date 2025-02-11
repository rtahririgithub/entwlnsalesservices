package com.telus.csm.ewlnsis.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreRequest;

public class GetInstallationDetailCoreRequest extends GetAvailableInstallDateCoreRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> attributeExclusionList = new ArrayList<String>();

	public List<String> getAttributeExclusionList() {
		return attributeExclusionList;
	}

	public void setAttributeExclusionList(List<String> attributeExclusionList) {
		this.attributeExclusionList = attributeExclusionList;
	}
	
	
	

}
