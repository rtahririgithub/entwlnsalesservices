package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

public class GetMarketOfferDetailAdapterRequest extends AdapterRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Long> offerIdList;

	public List<Long> getOfferIdList() {
		return offerIdList;
	}

	public void setOfferIdList(List<Long> offerIdList) {
		this.offerIdList = offerIdList;
	}

	
	

}
