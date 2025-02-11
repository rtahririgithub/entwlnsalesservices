/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;

import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;

/**
 * @author x145592
 *
 */
public class PriceDurationVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private PriceVO priceCharge = null;
	private PriceDiscountVO priceDiscount = null;
	private PriceWithTaxVO totalPrice = null;
	private DurationVO duration = null;

	public PriceVO getPriceCharge() {
		return priceCharge;
	}

	public void setPriceCharge(PriceVO priceCharge) {
		this.priceCharge = priceCharge;
	}

	public PriceDiscountVO getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(PriceDiscountVO priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public PriceWithTaxVO getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(PriceWithTaxVO totalPrice) {
		this.totalPrice = totalPrice;
	}

	public DurationVO getDuration() {
		return duration;
	}

	public void setDuration(DurationVO duration) {
		this.duration = duration;
	}

	
}
