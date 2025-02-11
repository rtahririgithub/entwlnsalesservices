package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class SubscriptionNumberInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String subscriptionNumTxt;
	protected String phoneNum;

	public String getSubscriptionNumTxt() {
		return subscriptionNumTxt;
	}

	public void setSubscriptionNumTxt(String subscriptionNumTxt) {
		this.subscriptionNumTxt = subscriptionNumTxt;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
