package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class PrimaryContactInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AddressRestVO address;
	protected String emailAddress;
	protected String emailAddressMissingReasonCd;

	public AddressRestVO getAddress() {
		return address;
	}

	public void setAddress(AddressRestVO address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailAddressMissingReasonCd() {
		return emailAddressMissingReasonCd;
	}

	public void setEmailAddressMissingReasonCd(
			String emailAddressMissingReasonCd) {
		this.emailAddressMissingReasonCd = emailAddressMissingReasonCd;
	}

}
