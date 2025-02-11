package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;

public class ProductPromotionDiscountsVO {
	private List<Discount> hsicPromotionDiscountList;
	private List<Discount> singPromotionDiscountList;
	private List<Discount> ttvPromotionDiscountList;
	private List<Discount> cpePromotionDiscountList;
	
	public List<Discount> getHsicPromotionDiscountList() {
		if(hsicPromotionDiscountList==null) {
			hsicPromotionDiscountList = new ArrayList<Discount>();
		}
		return hsicPromotionDiscountList;
	}
	public void setHsicPromotionDiscountList(List<Discount> hsicPromotionDiscountList) {
		this.hsicPromotionDiscountList = hsicPromotionDiscountList;
	}
	public List<Discount> getSingPromotionDiscountList() {
		if(singPromotionDiscountList==null) {
			singPromotionDiscountList = new ArrayList<Discount>();
		}
		return singPromotionDiscountList;
	}
	public void setSingPromotionDiscountList(List<Discount> singPromotionDiscountList) {
		this.singPromotionDiscountList = singPromotionDiscountList;
	}
	public List<Discount> getTtvPromotionDiscountList() {
		if(ttvPromotionDiscountList==null) {
			ttvPromotionDiscountList = new ArrayList<Discount>();
		}
		return ttvPromotionDiscountList;
	}
	public void setTtvPromotionDiscountList(List<Discount> ttvPromotionDiscountList) {
		this.ttvPromotionDiscountList = ttvPromotionDiscountList;
	}
	public List<Discount> getCpePromotionDiscountList() {
		if(cpePromotionDiscountList==null) {
			cpePromotionDiscountList = new ArrayList<Discount>();
		}
		return cpePromotionDiscountList;
	}
	public void setCpePromotionDiscountList(List<Discount> cpePromotionDiscountList) {
		this.cpePromotionDiscountList = cpePromotionDiscountList;
	}
}
