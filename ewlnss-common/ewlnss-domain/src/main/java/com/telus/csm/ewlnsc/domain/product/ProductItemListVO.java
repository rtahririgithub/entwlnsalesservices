package com.telus.csm.ewlnsc.domain.product;

public class ProductItemListVO {
 
	private String productCatalogueId = null;
   
	private String parentProductCatalogueId = null;

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

	public ProductItemListVO(String productCatalogueId,
			String parentProductCatalogueId) {
		super();
		this.productCatalogueId = productCatalogueId;
		this.parentProductCatalogueId = parentProductCatalogueId;
	}
	  
	  
	public ProductItemListVO() {}
	
}
