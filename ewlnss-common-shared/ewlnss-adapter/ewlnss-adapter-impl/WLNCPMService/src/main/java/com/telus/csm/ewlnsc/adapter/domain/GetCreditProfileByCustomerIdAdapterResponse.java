package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.ConsumerCreditProfile;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCreditProfileByCustomerIdAdapterResponse extends AdapterResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConsumerCreditProfile creditProfile;

	public ConsumerCreditProfile getCreditProfile() {
		return creditProfile;
	}

	public void setCreditProfile(ConsumerCreditProfile creditProfile) {
		this.creditProfile = creditProfile;
	}

}
