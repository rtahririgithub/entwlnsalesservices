/**
 * 
 */
package com.telus.csm.ewlnsms.core.domain;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Account;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.NewCustomerInformation;

/**
 * @author x145592
 *
 */
public class CreateWLNAccountCoreRequest extends CoreRequestBase {

	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private Address address;
	private NewCustomerInformation customerInfo;
	private Account accountInfo;

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public NewCustomerInformation getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(NewCustomerInformation customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	public Account getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(Account accountInfo) {
		this.accountInfo = accountInfo;
	}
	
	
}
