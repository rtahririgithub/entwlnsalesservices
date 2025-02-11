package com.telus.csm.ewlnsms.core.domain;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.MatchingServiceAddress;

public class SearchServiceAddressCoreResponse extends CoreResponseBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MatchingServiceAddress matchingServiceAddress;

	public MatchingServiceAddress getMatchingServiceAddress() {
		return matchingServiceAddress;
	}

	public void setMatchingServiceAddress(MatchingServiceAddress matchingServiceAddress) {
		this.matchingServiceAddress = matchingServiceAddress;
	}

}
