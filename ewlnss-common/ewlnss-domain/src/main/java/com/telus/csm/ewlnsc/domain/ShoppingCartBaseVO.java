package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * ShoppingCartBaseVO mimics the Rest Shopping Cart.
 * Any enhancements outside the Rest cart HAVE to go to the child class ShoppingCartVO
 * @author x171813
 *
 */
public class ShoppingCartBaseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private OperationHeaderVO operationHeader;
	private List<CartItemVO> cartItemList = new ArrayList<CartItemVO>();
	private ShoppingProfileVO shoppingProfile = null;
	private List<NoteVO> note = null;
	private List<ContactMediumVO> contactMedium = null;
	private CustomerVO customer = null;
	private BillingAccountVO billingAccount = null;
	private ServiceAddressVO serviceAddress = null;
	private RelatedPartyRefVO relatedParty = null;
	private DepositAssessedTypeVO depositAssessed = null;
	private FulfillmentOptionTypeVO fulfillmentOption = null;
	private List<String> cartContextTypeList = null;
	private String shoppingCartId;

	public List<CartItemVO> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItemVO> cartItems) {
		this.cartItemList = cartItems;
	}

	public ShoppingProfileVO getShoppingProfile() {
		return shoppingProfile;
	}

	public void setShoppingProfile(ShoppingProfileVO shoppingProfile) {
		this.shoppingProfile = shoppingProfile;
	}

	public List<NoteVO> getNote() {
		return note;
	}

	public void setNote(List<NoteVO> note) {
		this.note = note;
	}

	public List<ContactMediumVO> getContactMedium() {
		return contactMedium;
	}

	public void setContactMedium(List<ContactMediumVO> contactMedium) {
		this.contactMedium = contactMedium;
	}

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

	public DepositAssessedTypeVO getDepositAssessed() {
		return depositAssessed;
	}

	public void setDepositAssessed(DepositAssessedTypeVO depositAssessed) {
		this.depositAssessed = depositAssessed;
	}

	public FulfillmentOptionTypeVO getFulfillmentOption() {
		return fulfillmentOption;
	}

	public void setFulfillmentOption(FulfillmentOptionTypeVO fulfillmentOption) {
		this.fulfillmentOption = fulfillmentOption;
	}

	public List<String> getCartContextTypeList() {
		return cartContextTypeList;
	}

	public void setCartContextTypeList(List<String> cartContextTypeList) {
		this.cartContextTypeList = cartContextTypeList;
	}

	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public OperationHeaderVO getOperationHeader() {
		return operationHeader;
	}

	public void setOperationHeader(OperationHeaderVO operationHeader) {
		this.operationHeader = operationHeader;
	}	

}
