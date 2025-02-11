/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.domain.InstallmentOptionVO;
import com.telus.csm.ewlnsc.domain.MoneyVO;

/**
 * @author x145592
 *
 */
public class PriceWithTaxVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String pricingTypeCd = null;
	private MoneyVO basePriceAmount = null;
	private Integer recurrenceCount = null;
	private TotalTaxVO taxPrice = null;  
	private MoneyVO taxIncludedPrice = null;
	private List<InstallmentOptionVO> installmentOptions = null;

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

	public TotalTaxVO getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(TotalTaxVO taxPrice) {
		this.taxPrice = taxPrice;
	}

	public MoneyVO getTaxIncludedPrice() {
		return taxIncludedPrice;
	}

	public void setTaxIncludedPrice(MoneyVO taxIncludedPrice) {
		this.taxIncludedPrice = taxIncludedPrice;
	}
	
	//NWLN-10248
	public List<InstallmentOptionVO> getInstallmentOptions() {
		return installmentOptions;
	}
	public void setInstallmentOptions(List<InstallmentOptionVO> installmentOptions) {
		this.installmentOptions = installmentOptions;
	}
	  
	  
}
