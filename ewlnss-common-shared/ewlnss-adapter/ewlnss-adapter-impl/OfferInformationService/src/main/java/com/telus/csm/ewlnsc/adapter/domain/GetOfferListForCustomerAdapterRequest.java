package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.Customer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;

public class GetOfferListForCustomerAdapterRequest extends AdapterRequestBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private OutletInfo outlet;
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
	
	public WirelineTransactionalContext getWirelineTransactionalContext() {
		return wirelineTransactionalContext;
	}
	public void setWirelineTransactionalContext(WirelineTransactionalContext wirelineTransactionalContext) {
		this.wirelineTransactionalContext = wirelineTransactionalContext;
	}
	
	@Override
	public String getCacheKey(){
		//TODO tranID + offer Id + (products : code + term + tran type)
		return "GetOfferListForCustomer" + EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER +  getSalesTransactionId(); 
	}

	
}
