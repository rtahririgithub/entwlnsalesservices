/**
 * 
 */
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author x145592
 *
 */
public class PriceDiscountVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String pricingTypeCd = null;
	private MoneyVO basePriceAmount = null;
	private Integer recurrenceCount = null;
	private String priceAlterationTypeCd = null;
	private String discountTypeCode = null;
	private String promotionId;
	private Date perspectiveDate;
	
	public String getPricingTypeCd() {
		return pricingTypeCd;
	}
	
	public void setPricingTypeCd(String pricingType) {
		this.pricingTypeCd = pricingType;
	}
	
	public MoneyVO getBasePriceAmount() {
		return basePriceAmount;
	}
	
	public void setBasePriceAmount(MoneyVO basePriceAmount) {
		this.basePriceAmount = basePriceAmount;
	}
	
	public Integer getRecurrenceCount() {
		return recurrenceCount;
	}
	
	public void setRecurrenceCount(Integer recurrenceCount) {
		this.recurrenceCount = recurrenceCount;
	}
	
	public String getPriceAlterationTypeCd() {
		return priceAlterationTypeCd;
	}
	
	public void setPriceAlterationTypeCd(String priceAlterationType) {
		this.priceAlterationTypeCd = priceAlterationType;
	}

	public String getDiscountTypeCode() {
		return discountTypeCode;
	}

	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public Date getPerspectiveDate() {
		return perspectiveDate;
	}

	public void setPerspectiveDate(Date perspectiveDate) {
		this.perspectiveDate = perspectiveDate;
	}
	
}
