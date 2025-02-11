/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class ProductPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	
	private InternetProductPriceVO internetProductPrice = null;
	private TelevisionProductPriceVO televisionProductPrice = null;
	private HomePhoneProductPriceVO homePhoneProductPrice = null;
	private AccessoryProductPriceVO accessoryProductPrice = null;

	public InternetProductPriceVO getInternetProductPrice() {
		return internetProductPrice;
	}

	public void setInternetProductPrice(InternetProductPriceVO internetProductPrice) {
		this.internetProductPrice = internetProductPrice;
	}

	public TelevisionProductPriceVO getTelevisionProductPrice() {
		return televisionProductPrice;
	}

	public void setTelevisionProductPrice(TelevisionProductPriceVO televisionProductPrice) {
		this.televisionProductPrice = televisionProductPrice;
	}

	public HomePhoneProductPriceVO getHomePhoneProductPrice() {
		return homePhoneProductPrice;
	}

	public void setHomePhoneProductPrice(HomePhoneProductPriceVO homePhoneProductPrice) {
		this.homePhoneProductPrice = homePhoneProductPrice;
	}
	
	//NWLN-10248
	public AccessoryProductPriceVO getAccessoryProductPrice() {
		return accessoryProductPrice;
	}

	public void setAccessoryProductPrice(AccessoryProductPriceVO accessoryProductPrice) {
		this.accessoryProductPrice = accessoryProductPrice;
	}

	
}
