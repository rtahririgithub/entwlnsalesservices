/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

/**
 * @author x145592
 *
 */
public class CombinedPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private List<PriceDurationVO> billPrice = null;

	public List<PriceDurationVO> getBillPrice() {
		return billPrice;
	}

	public void setBillPrice(List<PriceDurationVO> billPrice) {
		this.billPrice = billPrice;
	}

	
}
