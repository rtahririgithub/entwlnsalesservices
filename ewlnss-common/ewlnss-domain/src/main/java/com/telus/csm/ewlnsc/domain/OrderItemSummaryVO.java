package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 *
 */
public class OrderItemSummaryVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemId;
	private String itemStatus ;
	private String productName;

	private String poId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

}
