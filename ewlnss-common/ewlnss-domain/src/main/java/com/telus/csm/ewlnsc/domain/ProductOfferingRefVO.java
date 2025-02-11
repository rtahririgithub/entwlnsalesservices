package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * ProductOfferingRef
 */

public class ProductOfferingRefVO implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private FFHOfferHeaderVO offerHeader = null;

  /**
   * Get offerHeader
   * @return offerHeader
  **/

  public FFHOfferHeaderVO getOfferHeader() {
    return offerHeader;
  }

  public void setOfferHeader(FFHOfferHeaderVO offerHeaderVO) {
    this.offerHeader = offerHeaderVO;
  }

}

