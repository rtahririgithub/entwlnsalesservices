package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class FFHSweetenerTypeVO implements Serializable{
	  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FFHOfferHeaderVO offerHeader = null;

	  private List<FFHDiscountTypeVO> discounts = null;
	  private List<RelatedImmediatePromotionVO> relatedPromotionList;
	  private PriceVO price = null;

	  /**
	   * Get offerHeader
	   * @return offerHeader
	  **/

	  public FFHOfferHeaderVO getOfferHeader() {
	    return offerHeader;
	  }

	  public void setOfferHeader(FFHOfferHeaderVO offerHeader) {
	    this.offerHeader = offerHeader;
	  }


	  /**
	   * Get discounts
	   * @return discounts
	  **/


	  public List<FFHDiscountTypeVO> getDiscounts() {
	    return discounts;
	  }

	  public void setDiscounts(List<FFHDiscountTypeVO> discounts) {
	    this.discounts = discounts;
	  }

	public List<RelatedImmediatePromotionVO> getRelatedPromotionList() {
		return relatedPromotionList;
	}

	public void setRelatedPromotionList(List<RelatedImmediatePromotionVO> relatedPromotionList) {
		this.relatedPromotionList = relatedPromotionList;
	}


	public PriceVO getPrice() {
		return price;
	}

	public void setPrice(PriceVO price) {
		this.price = price;
	}

	public String findPromotionId() {
		if(relatedPromotionList != null && !relatedPromotionList.isEmpty()) {
			return relatedPromotionList.get(0).getId();
		}
		return null;
	}

}

