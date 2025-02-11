package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class AdditionalProductItemTypeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductComponentVO productCatalogueIdentifier = null;

	public ProductComponentVO getProductCatalogueIdentifier() {
		return productCatalogueIdentifier;
	}

	public void setProductCatalogueIdentifier(ProductComponentVO productCatalogueIdentifier) {
		this.productCatalogueIdentifier = productCatalogueIdentifier;
	}
	

}
