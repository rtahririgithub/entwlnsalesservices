package com.telus.csm.ewlnsc.domain;

public class OriginalCarrierInfoTypeVO {

	private String providerNameTxt = null;

	private String accountNumber = null;

	private TelephoneNumberTypeVO telephoneNumber = null;

	private String reseller = null;

	private ClientNameVO customerName = null;

	// @SerializedName("disconnectServiceAddress")
	private DisconnectServiceAddressTypeVO disconnectServiceAddress = null;
	
	public TelephoneNumberTypeVO getTelephoneNumber() {
		return telephoneNumber;
	}
	
	public void setTelephoneNumber(TelephoneNumberTypeVO telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getProviderId() {
		return providerNameTxt;
	}

	public void setProviderId(String providerNameTxt) {
		this.providerNameTxt = providerNameTxt;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getReseller() {
		return reseller;
	}

	public void setReseller(String reseller) {
		this.reseller = reseller;
	}

	public ClientNameVO getCustomerName() {
		return customerName;
	}

	public void setCustomerName(ClientNameVO customerName) {
		this.customerName = customerName;
	}

	public DisconnectServiceAddressTypeVO getDisconnectServiceAddress() {
		return disconnectServiceAddress;
	}

	public void setDisconnectServiceAddress(DisconnectServiceAddressTypeVO disconnectServiceAddress) {
		this.disconnectServiceAddress = disconnectServiceAddress;
	}

}
