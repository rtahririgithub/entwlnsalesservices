/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

/**
 * @author x145592
 *
 */
public class TelevisionProductPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private List<ProductItemGroupCategoryPriceVO> productItemGroupCategoryPrice = null;
	private List<WirelineEquipmentPriceVO> equipmentPrice = null;
	private Boolean estimatedPriceInd = null;
	private CombinedPriceVO priceSummary = null;


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

	public Boolean getEstimatedPriceInd() {
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
	
	
}
