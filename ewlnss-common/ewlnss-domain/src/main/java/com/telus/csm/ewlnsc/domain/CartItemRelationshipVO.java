package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Relationship among cart items mainly other than hierarchical relationships such as \&quot;RelyOn\&quot;, \&quot;DependentOn\&quot;, \&quot;Shipping\&quot; etc.
 */

public class CartItemRelationshipVO  implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


private String id = null;


  private String type = null;


  private List<CartItemRefVO> cartItem = null;

  /**
   * Get id
   * @return id
  **/


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * Type of the order item relationship
   * @return type
  **/

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * Get cartItem
   * @return cartItem
  **/

  public List<CartItemRefVO> getCartItem() {
    return cartItem;
  }

  public void setCartItem(List<CartItemRefVO> cartItem) {
    this.cartItem = cartItem;
  }

}


