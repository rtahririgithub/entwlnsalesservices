package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;


public class FFHProductPlanAddOnTypeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private String action;
	  private ProductComponentVO productCatalogueIdentifier = null;

	  /**
	   * Get productCatalogueIdentifier
	   * @return productCatalogueIdentifier
	  **/

	  public ProductComponentVO getProductCatalogueIdentifier() {
	    return productCatalogueIdentifier;
	  }

	  public void setProductCatalogueIdentifier(ProductComponentVO productCatalogueIdentifier) {
	    this.productCatalogueIdentifier = productCatalogueIdentifier;
	  }

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}

	  