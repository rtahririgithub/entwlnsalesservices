package com.telus.csm.ewlnsms.core.domain;


import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address;

public class SearchServiceAddressCoreRequest extends CoreRequestBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Address serviceAddress;
	private Long maxMatchingLikeAddressCount;
	
	
	
	public Long getMaxMatchingLikeAddressCount() {
		return maxMatchingLikeAddressCount;
	}
	public void setMaxMatchingLikeAddressCount(Long maxMatchingLikeAddressCount) {
		this.maxMatchingLikeAddressCount = maxMatchingLikeAddressCount;
	}
	public Address getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(Address serviceAddress) {
		this.serviceAddress = serviceAddress;
	}


}
