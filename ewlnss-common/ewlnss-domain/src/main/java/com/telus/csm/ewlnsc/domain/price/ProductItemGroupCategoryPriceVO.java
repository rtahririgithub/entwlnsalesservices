/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.domain.GroupCategoryVO;

/**
 * @author x145592
 *
 */
public class ProductItemGroupCategoryPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private GroupCategoryVO groupCategory = null;
	private CombinedPriceVO priceSummary = null;
	private List<ProductItemPriceVO> productItemPriceList = null;


	public GroupCategoryVO getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(GroupCategoryVO groupCategory) {
		this.groupCategory = groupCategory;
	}

	public CombinedPriceVO getPriceSummary() {
		return priceSummary;
	}

	public void setPriceSummary(CombinedPriceVO priceSummary) {
		this.priceSummary = priceSummary;
	}

	public List<ProductItemPriceVO> getProductItemPriceList() {
		return productItemPriceList;
	}

	public void setProductItemPriceList(List<ProductItemPriceVO> productItemPriceList) {
		this.productItemPriceList = productItemPriceList;
	}

	
}
