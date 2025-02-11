package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;
 
public class ProductItemConstraintListVO {

	private Boolean eligibleForMinMaxPricingInd = null;
	private List<ProductItemListVO> productItemList = null;
	
	public Boolean getEligibleForMinMaxPricingInd() {
		return eligibleForMinMaxPricingInd;
	}
	public void setEligibleForMinMaxPricingInd(Boolean eligibleForMinMaxPricingInd) {
		this.eligibleForMinMaxPricingInd = eligibleForMinMaxPricingInd;
	}
	public List<ProductItemListVO> getProductItemList() {
		if (this.productItemList == null)
			this.productItemList = new ArrayList<ProductItemListVO>();
		
		return this.productItemList;
	}
	
	
	public void setProductItemList(List<ProductItemListVO> productItemList) {
		this.productItemList = productItemList;
	}
	
	
	
	
}
