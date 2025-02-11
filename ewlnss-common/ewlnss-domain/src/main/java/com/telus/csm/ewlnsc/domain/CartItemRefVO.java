package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * CartIIem reference. A CartItem is an identified part of the shopping cart.
 */

public class CartItemRefVO implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String id = null;

  /**
   * Unique identifier of the channel
   * @return id
  **/

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}

