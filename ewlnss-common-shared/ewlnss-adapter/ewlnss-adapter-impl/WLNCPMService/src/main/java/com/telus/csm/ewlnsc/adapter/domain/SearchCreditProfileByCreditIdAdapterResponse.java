package com.telus.csm.ewlnsc.adapter.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.CreditProfileSearchResult;

/**
 * 
 * @author Jose.Mena
 *
 */
public class SearchCreditProfileByCreditIdAdapterResponse extends AdapterResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CreditProfileSearchResult> creditProfileSearchResultList = new ArrayList<CreditProfileSearchResult>();

	public List<CreditProfileSearchResult> getCreditProfileSearchResultList() {
		return creditProfileSearchResultList;
	}

	public void setCreditProfileSearchResultList(List<CreditProfileSearchResult> creditProfileSearchResultList) {
		this.creditProfileSearchResultList = creditProfileSearchResultList;
	}

}
