package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.List;

public class GetPromotionRequestVO extends CoreRequestBase {
	
	private static final long serialVersionUID = 1L;
	
	private CustomerVO customer = null;
	private BillingAccountVO billingAccount = null;
	private ServiceAddressVO serviceAddress = null;
	private RelatedPartyRefVO relatedParty = null;
	private OperationHeaderVO operationHeaderVO;
	private List<CartItemVO> cartItemList = new ArrayList<CartItemVO>();
	private boolean couponInd;
	
	public CustomerVO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}
	public BillingAccountVO getBillingAccount() {
		return billingAccount;
	}
	public void setBillingAccount(BillingAccountVO billingAccount) {
		this.billingAccount = billingAccount;
	}
	public ServiceAddressVO getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(ServiceAddressVO serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public RelatedPartyRefVO getRelatedParty() {
		return relatedParty;
	}
	public void setRelatedParty(RelatedPartyRefVO relatedParty) {
		this.relatedParty = relatedParty;
	}

	public OperationHeaderVO getOperationHeaderVO() {
		return operationHeaderVO;
	}
	public void setOperationHeaderVO(OperationHeaderVO operationHeaderVO) {
		this.operationHeaderVO = operationHeaderVO;
	}
	public List<CartItemVO> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItemVO> cartItemList) {
		this.cartItemList = cartItemList;
	}
	public boolean isCouponInd() {
		return couponInd;
	}
	public void setCouponInd(boolean couponInd) {
		this.couponInd = couponInd;
	}
	
	
}
