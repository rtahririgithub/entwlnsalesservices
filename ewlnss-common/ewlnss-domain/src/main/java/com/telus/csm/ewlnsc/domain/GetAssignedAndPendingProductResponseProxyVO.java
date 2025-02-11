package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.List;
import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.AccountProfileRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;

public class GetAssignedAndPendingProductResponseProxyVO {

	/**
	 * used for ESS rest proxy mapping
	 */
	private static final long serialVersionUID = 1L;

	private List<SubscribedProductInfoRestVO> subscribedProductList = new ArrayList<SubscribedProductInfoRestVO>();
	private List<SubscribedProductInfoRestVO> pendingProductList= new ArrayList<SubscribedProductInfoRestVO>();
	private List<AccountProfileRestVO> customerAccountProfileList;
	private List<Product> customerProducts;
	private AccountProfileRestVO primaryAccountProfile;

	
	public List<Product> getCustomerProducts() {
		return customerProducts;
	}

	public void setCustomerProducts(List<Product> customerProducts) {
		this.customerProducts = customerProducts;
	}

	public List<AccountProfileRestVO> getCustomerAccountProfileList() {
		return customerAccountProfileList;
	}

	public void setCustomerAccountProfileList(List<AccountProfileRestVO> customerAccountProfileList) {
		this.customerAccountProfileList = customerAccountProfileList;
	}

	public List<SubscribedProductInfoRestVO> getPendingProductList() {
		return pendingProductList;
	}

	public void setPendingProductList(List<SubscribedProductInfoRestVO> pendingProductList) {
		this.pendingProductList = pendingProductList;
	}

	public List<SubscribedProductInfoRestVO> getSubscribedProductList() {
		return subscribedProductList;
	}

	public void setSubscribedProductList(List<SubscribedProductInfoRestVO> subscribedProductList) {
		this.subscribedProductList = subscribedProductList;
	}
	
	public AccountProfileRestVO getPrimaryAccountProfile() {
		return primaryAccountProfile;
	}

	public void setPrimaryAccountProfile(AccountProfileRestVO primaryAccountProfile) {
		this.primaryAccountProfile = primaryAccountProfile;
	}
}
