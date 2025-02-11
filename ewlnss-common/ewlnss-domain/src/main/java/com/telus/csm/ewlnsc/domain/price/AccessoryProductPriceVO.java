/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.domain.PriceVO;


/**
 * @author x145592
 *
 */
public class AccessoryProductPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	
	private List<ProductItemGroupCategoryPriceVO> productItemGroupCategoryPrice = null;
	private List<WirelineEquipmentPriceVO> equipmentPrice = null;
	private Boolean estimatedPriceInd = null;
	private CombinedPriceVO priceSummary = null;
	private PriceVO installationCredit = null; //double check - necessary? 


	
	public List<ProductItemGroupCategoryPriceVO> getProductItemGroupCategoryPrice() {
		return productItemGroupCategoryPrice;
	}

	public void setProductItemGroupCategoryPrice(List<ProductItemGroupCategoryPriceVO> productItemGroupCategoryPrice) {
		this.productItemGroupCategoryPrice = productItemGroupCategoryPrice;
	}

	public List<WirelineEquipmentPriceVO> getEquipmentPrice() {
		return equipmentPrice;
	}

	public void setEquipmentPrice(List<WirelineEquipmentPriceVO> equipmentPrice) {
		this.equipmentPrice = equipmentPrice;
	}

	public Boolean isEstimatedPriceInd() {
		return estimatedPriceInd;
	}

	public void setEstimatedPriceInd(Boolean estimatedPriceInd) {
		this.estimatedPriceInd = estimatedPriceInd;
	}

	public CombinedPriceVO getPriceSummary() {
		return priceSummary;
	}

	public void setPriceSummary(CombinedPriceVO priceSummary) {
		this.priceSummary = priceSummary;
	}
	
	public PriceVO getInstallationCredit() {
		return installationCredit;
	}

	public void setInstallationCredite(PriceVO installationCredit) {
		this.installationCredit = installationCredit;
	}

}
