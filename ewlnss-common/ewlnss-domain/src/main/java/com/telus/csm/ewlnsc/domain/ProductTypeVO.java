package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

/**
 * ProductTypeVO
 */

public class ProductTypeVO implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private InternetProductTypeVO internetProduct = null;

  private TelevisionProductTypeVO televisionProduct = null;

  private HomePhoneProductTypeVO homePhoneProduct = null;

  private MobilityProductTypeVO mobilityProduct = null;
  
  private AccessoryProductTypeVO accessoryProduct = null;

  private HomeSecurityProductTypeVO homeSecurityProduct = null; //FIFA SHS
  
  /**
   * Get internetProduct
   * @return internetProduct
  **/
  public InternetProductTypeVO getInternetProduct() {
    return internetProduct;
  }

  public void setInternetProduct(InternetProductTypeVO internetProduct) {
    this.internetProduct = internetProduct;
  }

  /**
   * Get televisionProduct
   * @return televisionProduct
  **/
  public TelevisionProductTypeVO getTelevisionProduct() {
    return televisionProduct;
  }

  public void setTelevisionProduct(TelevisionProductTypeVO televisionProduct) {
    this.televisionProduct = televisionProduct;
  }

  /**
   * Get homePhoneProduct
   * @return homePhoneProduct
  **/

  public HomePhoneProductTypeVO getHomePhoneProduct() {
    return homePhoneProduct;
  }

  public void setHomePhoneProduct(HomePhoneProductTypeVO homePhoneProduct) {
    this.homePhoneProduct = homePhoneProduct;
  }

  /**
   * Get mobilityProduct
   * @return mobilityProduct
  **/
  public MobilityProductTypeVO getMobilityProductVO() {
    return mobilityProduct;
  }

  public void setMobilityProductVO(MobilityProductTypeVO mobilityProduct) {
    this.mobilityProduct = mobilityProduct;
  }
  
  /**
   * Get accessoryProduct
   * @return accessoryProduct
  **/
  public AccessoryProductTypeVO getAccessoryProduct() {
    return accessoryProduct;
  }

  public void setAccessoryProduct(AccessoryProductTypeVO accessoryProduct) {
    this.accessoryProduct = accessoryProduct;
  }

public HomeSecurityProductTypeVO getHomeSecurityProduct() {
	return homeSecurityProduct;
}

public void setHomeSecurityProduct(HomeSecurityProductTypeVO homeSecurityProduct) {
	this.homeSecurityProduct = homeSecurityProduct;
}
  
}

