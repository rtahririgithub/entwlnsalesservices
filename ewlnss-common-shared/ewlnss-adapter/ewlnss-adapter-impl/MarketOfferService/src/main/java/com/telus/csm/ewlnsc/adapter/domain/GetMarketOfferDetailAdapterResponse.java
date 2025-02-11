package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.product.productoffering.marketofferingcommon_v2.MarketOfferDetail;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.ResponseMessage;

public class GetMarketOfferDetailAdapterResponse extends AdapterResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private List<MarketOfferDetail> marketOfferDetailList; 
	private ResponseMessage responseMessage;
	
	public List<MarketOfferDetail> getMarketOfferDetailList() {
		return marketOfferDetailList;
	}
	public void setMarketOfferDetailList(List<MarketOfferDetail> marketOfferDetailList) {
		this.marketOfferDetailList = marketOfferDetailList;
	}
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}
	

}
