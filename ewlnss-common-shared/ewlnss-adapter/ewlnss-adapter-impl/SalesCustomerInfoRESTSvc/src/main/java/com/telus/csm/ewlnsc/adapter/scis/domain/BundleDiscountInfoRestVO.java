package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class BundleDiscountInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String bundleDiscountCd;
	protected String bundleDiscountDesc;

	public String getBundleDiscountCd() {
		return bundleDiscountCd;
	}

	public void setBundleDiscountCd(String bundleDiscountCd) {
		this.bundleDiscountCd = bundleDiscountCd;
	}

	public String getBundleDiscountDesc() {
		return bundleDiscountDesc;
	}

	public void setBundleDiscountDesc(String bundleDiscountDesc) {
		this.bundleDiscountDesc = bundleDiscountDesc;
	}
}
