package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * ProductOrderType
 */

public class ProductOrderTypeVO   implements Serializable{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String orderTypeCd = null;

  
  private String orderSubTypeCd = null;

  /**
   * Get orderTypeCd
   * @return orderTypeCd
  **/


  public String getOrderTypeCd() {
    return orderTypeCd;
  }

  public void setOrderTypeCd(String orderTypeCd) {
    this.orderTypeCd = orderTypeCd;
  }

  /**
   * Get orderSubTypeCd
   * @return orderSubTypeCd
  **/


  public String getOrderSubTypeCd() {
    return orderSubTypeCd;
  }

  public void setOrderSubTypeCd(String orderSubTypeCd) {
    this.orderSubTypeCd = orderSubTypeCd;
  }


}


