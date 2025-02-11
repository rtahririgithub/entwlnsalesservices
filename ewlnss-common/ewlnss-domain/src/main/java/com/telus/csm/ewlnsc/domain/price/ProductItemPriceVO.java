/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;

/**
 * @author x145592
 *
 */
public class ProductItemPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private ProductItemIdentifierVO productItemIdentifier = null;
	private CombinedPriceVO priceSummary = null;
	private List<AssociatedProductItemPriceVO> associatedProductItemPrice = null;
	private List<MultilingualTextVO> productItemDescription = null;


	public ProductItemIdentifierVO getProductItemIdentifier() {
		return productItemIdentifier;
	}

	public void setProductItemIdentifier(ProductItemIdentifierVO productItemIdentifier) {
		this.productItemIdentifier = productItemIdentifier;
	}

	public CombinedPriceVO getPriceSummary() {
		return priceSummary;
	}

	public void setPriceSummary(CombinedPriceVO priceSummary) {
		this.priceSummary = priceSummary;
	}

	public List<AssociatedProductItemPriceVO> getAssociatedProductItemPrice() {
		return associatedProductItemPrice;
	}

	public void setAssociatedProductItemPrice(List<AssociatedProductItemPriceVO> associatedProductItemPrice) {
		this.associatedProductItemPrice = associatedProductItemPrice;
	}

	public List<MultilingualTextVO> getProductItemDescription() {
		return productItemDescription;
	}

	public void setProductItemDescription(List<MultilingualTextVO> productItemDescription) {
		this.productItemDescription = productItemDescription;
	}


}
