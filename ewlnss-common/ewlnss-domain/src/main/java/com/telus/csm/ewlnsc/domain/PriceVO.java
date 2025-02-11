/**
 * 
 */
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author x145592
 *
 */
public class PriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String pricingTypeCd = null;
	private MoneyVO basePriceAmount = null;
	private Integer recurrenceCount = null;
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
	public List<InstallmentOptionVO> getInstallmentOptions() {
		return installmentOptions;
	}
	public void setInstallmentOptions(List<InstallmentOptionVO> installmentOptions) {
		this.installmentOptions = installmentOptions;
	}

	
}
