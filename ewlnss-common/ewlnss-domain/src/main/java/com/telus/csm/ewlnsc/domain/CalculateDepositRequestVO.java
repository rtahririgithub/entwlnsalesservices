package com.telus.csm.ewlnsc.domain;

import java.util.List;

import com.telus.csm.ewlnsc.adapter.scis.domain.AccountProfileRestVO;


public class CalculateDepositRequestVO {

	private List<AccountProfileRestVO>  customerProducts;
	private List<OrderProductVO> orderedProductsList;
	private String orderId;
	private String sourceSystemId;
	private long customerId;
	private String calulationTypeCode;
	private String userId;
	private String applicationId;
		
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCalulationTypeCode() {
		return calulationTypeCode;
	}

	public void setCalulationTypeCode(String calulationTypeCode) {
		this.calulationTypeCode = calulationTypeCode;
	}

	public void setCustomerProducts(List<AccountProfileRestVO>  customerProductsSummary) {
		this.customerProducts = customerProductsSummary;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public List<AccountProfileRestVO> getCustomerProducts() {
		return customerProducts;
	}

	public String getOrderId() {
		// TODO Auto-generated method stub
		return orderId;
	}

	public String getSourceSystemId() {
		// TODO Auto-generated method stub
		return sourceSystemId;
	}

	public long getCustomerId() {
		// TODO Auto-generated method stub
		return customerId;
	}

	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public List<OrderProductVO> getOrderedProductsList() {
		return orderedProductsList;
	}

	public void setOrderedProductsList(List<OrderProductVO> orderedProductsList) {
		this.orderedProductsList = orderedProductsList;
	}

	
	
}
