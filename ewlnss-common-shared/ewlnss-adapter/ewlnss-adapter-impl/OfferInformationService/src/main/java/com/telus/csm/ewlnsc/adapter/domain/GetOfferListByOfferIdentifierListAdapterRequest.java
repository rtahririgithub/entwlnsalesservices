package com.telus.csm.ewlnsc.adapter.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferIdentifier;

public class GetOfferListByOfferIdentifierListAdapterRequest extends AdapterRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long brandId;
	private OutletInfo outlet;
	private List<OfferIdentifier> offerIdentifierList;
	private WirelineTransactionalContext wirelineTransactionalContext;
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public OutletInfo getOutlet() {
		return outlet;
	}
	public void setOutlet(OutletInfo outlet) {
		this.outlet = outlet;
	}
	public List<OfferIdentifier> getOfferIdentifierList() {
		if (offerIdentifierList == null) {
			offerIdentifierList = new ArrayList<OfferIdentifier>();
		}
		return offerIdentifierList;
	}
	public void setOfferIdentifierList(List<OfferIdentifier> offerIdentifierList) {
		this.offerIdentifierList = offerIdentifierList;
	}
	
	public WirelineTransactionalContext getWirelineTransactionalContext() {
		return wirelineTransactionalContext;
	}
	public void setWirelineTransactionalContext(WirelineTransactionalContext wirelineTransactionalContext) {
		this.wirelineTransactionalContext = wirelineTransactionalContext;
	}
	
	@Override
	public String getCacheKey(){
		if (getOfferIdentifierList() == null || getOfferIdentifierList().size() != 1) {
			// Only use cache if there is one offer in the request   
			return null;
		} 		
		
		return "GetOfferListByOfferIdentifierList"
			+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getSalesTransactionId()
			+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getOfferIdentifierList().get(0).getOfferId(); 
	}


}
