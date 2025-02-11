package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;


public class FFHDiscountTypeVO implements Serializable  {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private String discountCode = null;

	  private List<ProductComponentVO> productCatalogueIdentifiers = null;

	  /**
	   * discount code received from offer
	   * @return discountCode
	  **/

	  public String getDiscountCode() {
	    return discountCode;
	  }

	  public void setDiscountCode(String discountCode) {
	    this.discountCode = discountCode;
	  }

	  /**
	   * Get productCatalogueIdentifiers
	   * @return productCatalogueIdentifiers
	  **/

	  public List<ProductComponentVO> getProductCatalogueIdentifiers() {
	    return productCatalogueIdentifiers;
	  }

	  public void setProductCatalogueIdentifiers(List<ProductComponentVO> productCatalogueIdentifiers) {
	    this.productCatalogueIdentifiers = productCatalogueIdentifiers;
	  }

	}


