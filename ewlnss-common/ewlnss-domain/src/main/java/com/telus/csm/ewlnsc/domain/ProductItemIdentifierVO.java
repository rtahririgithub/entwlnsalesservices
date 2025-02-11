/**
 * 
 */
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class ProductItemIdentifierVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String productCatalogueId = null;
	private String parentProductCatalogueId = null;
	private String externalId = null; //a.k.a productCode on grid.

	public String getProductCatalogueId() {
		return productCatalogueId;
	}

	public void setProductCatalogueId(String productCatalogueId) {
		this.productCatalogueId = productCatalogueId;
	}

	public String getParentProductCatalogueId() {
		return parentProductCatalogueId;
	}

	public void setParentProductCatalogueId(String parentProductCatalogueId) {
		this.parentProductCatalogueId = parentProductCatalogueId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
	

}
