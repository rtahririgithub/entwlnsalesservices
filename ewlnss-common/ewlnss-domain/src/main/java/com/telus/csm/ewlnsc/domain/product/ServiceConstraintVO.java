package com.telus.csm.ewlnsc.domain.product;

import java.util.List;

public class ServiceConstraintVO {

	private int minItemQty;
	private int maxItemQty;
	private List<String> serviceTypeCodeList;
	private SalesCategoryVO salesCategory; //NWLN-7789
	
	public ServiceConstraintVO(){
	}

	public ServiceConstraintVO(List<String> serviceTypeCodeList, int minItemQty, int maxItemQty) {
		super();
		this.serviceTypeCodeList = serviceTypeCodeList;
		this.minItemQty = minItemQty;
		this.maxItemQty = maxItemQty;
	}

	public int getMinItemQty() {
		return minItemQty;
	}
	public void setMinItemQty(int minItemQty) {
		this.minItemQty = minItemQty;
	}
	public int getMaxItemQty() {
		return maxItemQty;
	}
	public void setMaxItemQty(int maxItemQty) {
		this.maxItemQty = maxItemQty;
	}
	public List<String> getServiceTypeCodeList() {
		return serviceTypeCodeList;
	}
	public void setServiceTypeCodeList(List<String> serviceTypeCodeList) {
		this.serviceTypeCodeList = serviceTypeCodeList;
	}

	public SalesCategoryVO getSalesCategory() {
		return salesCategory;
	}

	public void setSalesCategory(SalesCategoryVO salesCategory) {
		this.salesCategory = salesCategory;
	}
	
}
