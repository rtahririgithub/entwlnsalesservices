package com.telus.csm.ewlnsc.domain;

public class MobilityProductTypeVO   {
	  private ProductOrderTypeVO productOrderType = null;

	  private String selectedContractTermCd = null;

	  /**
	   * Get productOrderType
	   * @return productOrderType
	  **/

	  public ProductOrderTypeVO getProductOrderType() {
	    return productOrderType;
	  }

	  public void setProductOrderType(ProductOrderTypeVO productOrderType) {
	    this.productOrderType = productOrderType;
	  }

	  /**
	   * Get selectedContractTermCd
	   * @return selectedContractTermCd
	  **/
	  public String getSelectedContractTermCd() {
	    return selectedContractTermCd;
	  }

	  public void setSelectedContractTermCd(String selectedContractTermCd) {
	    this.selectedContractTermCd = selectedContractTermCd;
	  }

	}
