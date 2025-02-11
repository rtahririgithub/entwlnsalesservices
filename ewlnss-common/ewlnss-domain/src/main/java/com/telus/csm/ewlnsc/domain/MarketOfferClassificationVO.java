package com.telus.csm.ewlnsc.domain;

public class MarketOfferClassificationVO {

	private boolean productComponentInd;
	private boolean discountInd;
	private boolean addOnInd;
	private boolean sweetenerInd;

	public boolean isProductComponentInd() {
		return productComponentInd;
	}

	public void setProductComponentInd(boolean productComponentInd) {
		this.productComponentInd = productComponentInd;
	}

	public boolean isDiscountInd() {
		return discountInd;
	}

	public void setDiscountInd(boolean discountInd) {
		this.discountInd = discountInd;
	}

	public boolean isAddOnInd() {
		return addOnInd;
	}

	public void setAddOnInd(boolean addOnInd) {
		this.addOnInd = addOnInd;
	}

	public boolean isSweetenerInd() {
		return sweetenerInd;
	}

	public void setSweetenerInd(boolean sweetenerInd) {
		this.sweetenerInd = sweetenerInd;
	}

	@Override
	public String toString() {
		return "MarketOfferClassificationVO [productComponentInd=" + productComponentInd + ", discountInd="
				+ discountInd + ", addOnInd=" + addOnInd + ", sweetenerInd=" + sweetenerInd + "]";
	}
	
	

}
