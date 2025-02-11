package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.rest.domain.EntWLNSalesRestRequestBase;



@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class AccountReqRestDO extends EntWLNSalesRestRequestBase implements Serializable{
	private static final long serialVersionUID = 1L;

	private Boolean validatebillingaddressind;
	private Boolean validatecreditaddressind;
	private AddressVO serviceaddress;
	private CustomerInfoVO customerinfo;
	private AccountInfoVO accountinfo;

	public Boolean getValidatebillingaddressind() {
		return validatebillingaddressind;
	}
	public void setValidatebillingaddressind(Boolean validatebillingaddressind) {
		this.validatebillingaddressind = validatebillingaddressind;
	}
	public Boolean getValidatecreditaddressind() {
		return validatecreditaddressind;
	}
	public void setValidatecreditaddressind(Boolean validatecreditaddressind) {
		this.validatecreditaddressind = validatecreditaddressind;
	}
	public AddressVO getServiceaddress() {
		return serviceaddress;
	}
	public void setServiceaddress(AddressVO serviceaddress) {
		this.serviceaddress = serviceaddress;
	}
	public CustomerInfoVO getCustomerinfo() {
		return customerinfo;
	}
	public void setCustomerinfo(CustomerInfoVO customerinfo) {
		this.customerinfo = customerinfo;
	}
	public AccountInfoVO getAccountinfo() {
		return accountinfo;
	}
	public void setAccountinfo(AccountInfoVO accountinfo) {
		this.accountinfo = accountinfo;
	}

//	@Override
	public void setupDemo() {
		
//		super.setupDemo();
		setValidatebillingaddressind(false);
		setValidatecreditaddressind(false);
		AddressVO addressVO = new AddressVO();
		addressVO.setupDemo();
		setServiceaddress(addressVO);
		CustomerInfoVO customerInfoVO = new CustomerInfoVO();
		customerInfoVO.setupDemo();
		setCustomerinfo(customerInfoVO);
		AccountInfoVO accountInfoVO = new AccountInfoVO();
		accountInfoVO.setupDemo();
		setAccountinfo(accountInfoVO);
		
	}	

}
