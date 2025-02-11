package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

public class ProductItemConstraintGroupVO {

	private String constraintType;
	private ProductItemConstraintListVO productItemConstraintListVO;
	private ServiceConstraintVO freePricingQuantityAllowed; 
	
	public String getConstraintType() {
		return constraintType;
	}
	public void setConstraintType(String constraintType) {
		this.constraintType = constraintType;
	}
	public ProductItemConstraintListVO getProductItemConstraintListVO() {
		return productItemConstraintListVO;
	}
	public void setProductItemConstraintListVO(
			ProductItemConstraintListVO productItemConstraintListVO) {
		this.productItemConstraintListVO = productItemConstraintListVO;
	}
	public ServiceConstraintVO getFreePricingQuantityAllowed() {
		return freePricingQuantityAllowed;
	}
	public void setFreePricingQuantityAllowed(ServiceConstraintVO freePricingQuantityAllowed) {
		this.freePricingQuantityAllowed = freePricingQuantityAllowed;
	}

	
	 
	
}
