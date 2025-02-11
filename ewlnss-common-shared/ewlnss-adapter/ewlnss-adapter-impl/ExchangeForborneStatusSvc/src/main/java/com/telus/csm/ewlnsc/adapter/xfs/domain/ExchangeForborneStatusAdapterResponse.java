package com.telus.csm.ewlnsc.adapter.xfs.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.resource.resource.rategroupinfotypes_v1.ExchangeForborneStatus;

public class ExchangeForborneStatusAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;

	private List<ExchangeForborneStatus> exchangeForborneStatusList;

	public List<ExchangeForborneStatus> getExchangeForborneStatusList() {
		return exchangeForborneStatusList;
	}

	public void setExchangeForborneStatusList(List<ExchangeForborneStatus> exchangeForborneStatusList) {
		this.exchangeForborneStatusList = exchangeForborneStatusList;
	}
	
}
