package com.telus.csm.ewlnsc.domain;

public class GetCatalogueHierarchyResponseVO extends CoreResponseBase {


	private static final long serialVersionUID = 1L;
	
	private ProductCatalogueItemVO catalogueItem;

	public ProductCatalogueItemVO getCatalogueItem() {
		return catalogueItem;
	}

	public void setCatalogueItem(ProductCatalogueItemVO catalogueItem) {
		this.catalogueItem = catalogueItem;
	}

}
