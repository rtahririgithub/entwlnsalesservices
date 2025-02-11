package com.telus.csm.ewlnsvs.core.domain;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.CustomerCreditInformation;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCustomerEligibilityCoreRequest extends CoreRequestBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private CustomerCreditInformation creditProfile;
	private Boolean newCustomerInd;
	private Boolean refreshCreditResultInd;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public CustomerCreditInformation getCreditProfile() {
		return creditProfile;
	}

	public void setCreditProfile(CustomerCreditInformation creditProfile) {
		this.creditProfile = creditProfile;
	}

	public Boolean getNewCustomerInd() {
		return newCustomerInd;
	}

	public void setNewCustomerInd(Boolean newCustomerInd) {
		this.newCustomerInd = newCustomerInd;
	}

	public Boolean getRefreshCreditResultInd() {
		return refreshCreditResultInd;
	}

	public void setRefreshCreditResultInd(Boolean refreshCreditResultInd) {
		this.refreshCreditResultInd = refreshCreditResultInd;
	}
}
