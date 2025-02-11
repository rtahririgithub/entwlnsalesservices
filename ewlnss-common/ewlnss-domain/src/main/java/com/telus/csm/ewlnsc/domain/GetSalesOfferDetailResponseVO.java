package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProgramPromotion;

public class GetSalesOfferDetailResponseVO extends CoreResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Offer offer;
	private ProgramPromotion programPromotion;
	private List<String> recontractEligibleProductCodeList = new ArrayList<String>();
	private String cartItemOfferId;
	
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public ProgramPromotion getProgramPromotion() {
		return programPromotion;
	}
	public void setProgramPromotion(ProgramPromotion programPromotion) {
		this.programPromotion = programPromotion;
	}
	public List<String> getRecontractEligibleProductCodeList() {
		return recontractEligibleProductCodeList;
	}
	public void setRecontractEligibleProductCodeList(List<String> recontractEligibleProductCodeList) {
		this.recontractEligibleProductCodeList = recontractEligibleProductCodeList;
	}
	public String getCartItemOfferId() {
		return cartItemOfferId;
	}
	public void setCartItemOfferId(String offerId) {
		this.cartItemOfferId = offerId;
	}
	

}
