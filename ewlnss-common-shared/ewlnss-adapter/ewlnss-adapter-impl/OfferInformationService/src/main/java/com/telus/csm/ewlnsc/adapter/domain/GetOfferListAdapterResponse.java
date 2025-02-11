package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProgramPromotion;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;

public class GetOfferListAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;
	private List<Offer> offerList;
	private ProgramPromotion programPromotion;
	private ResponseMessage responseMessage;
	private List<Sweetener> sweetenerList;
	

	public ProgramPromotion getProgramPromotion() {
		return programPromotion;
	}
	public void setProgramPromotion(ProgramPromotion programPromotion) {
		this.programPromotion = programPromotion;
	}
	
	public List<Offer> getOfferList() {
		return offerList;
	}
	public void setOfferList(List<Offer> offerList) {
		this.offerList = offerList;
	}
	
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public List<Sweetener> getSweetenerList() {
		return sweetenerList;
	}
	public void setSweetenerList(List<Sweetener> sweetenerList) {
		this.sweetenerList = sweetenerList;
	}
}
