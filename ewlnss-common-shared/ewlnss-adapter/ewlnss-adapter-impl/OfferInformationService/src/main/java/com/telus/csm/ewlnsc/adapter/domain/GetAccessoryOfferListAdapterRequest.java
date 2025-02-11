package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.Customer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;


public class GetAccessoryOfferListAdapterRequest extends AdapterRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private OutletInfo outlet;
	private String lineOfBusinessCd;
	private Long brandId;

	private WirelineTransactionalContext wirelineTransactionalContext;

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public OutletInfo getOutlet() {
		return outlet;
	}
	public void setOutlet(OutletInfo outlet) {
		this.outlet = outlet;
	}
	public String getLineOfBusinessCd() {
		return lineOfBusinessCd;
	}
	public void setLineOfBusinessCd(String lineOfBusinessCd) {
		this.lineOfBusinessCd = lineOfBusinessCd;
	}
	public WirelineTransactionalContext getWirelineTransactionalContext() {
		return wirelineTransactionalContext;
	}
	public void setWirelineTransactionalContext(WirelineTransactionalContext wirelineTransactionalContext) {
		this.wirelineTransactionalContext = wirelineTransactionalContext;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
}