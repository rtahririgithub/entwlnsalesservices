package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.Customer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader;

public class GetSweetenerOfferListForCustomerAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	private Customer customer;
	private List<OfferHeader> offerHeaderList;
	private OutletInfo outlet;
	private WirelineTransactionalContext wirelineTransactionalContext;

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OfferHeader> getOfferHeaderList() {
		return offerHeaderList;
	}

	public void setOfferHeaderList(List<OfferHeader> offerHeaderList) {
		this.offerHeaderList = offerHeaderList;
	}

	public OutletInfo getOutlet() {
		return outlet;
	}

	public void setOutlet(OutletInfo outlet) {
		this.outlet = outlet;
	}

	public WirelineTransactionalContext getWirelineTransactionalContext() {
		return wirelineTransactionalContext;
	}

	public void setWirelineTransactionalContext(WirelineTransactionalContext wirelineTransactionalContext) {
		this.wirelineTransactionalContext = wirelineTransactionalContext;
	}
	
	@Override
	public String getCacheKey(){
		// Leaving currently the default behavior as it's not finalized yet.
		return super.getCacheKey();
	}
}
