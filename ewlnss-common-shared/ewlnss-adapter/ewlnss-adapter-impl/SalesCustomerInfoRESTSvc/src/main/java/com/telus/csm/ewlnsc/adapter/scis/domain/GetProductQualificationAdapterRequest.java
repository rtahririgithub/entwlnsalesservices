package com.telus.csm.ewlnsc.adapter.scis.domain;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

public class GetProductQualificationAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = -7809265406915361627L;

	private String addressId;
	private String provinceCd;
	private String city;
	private Boolean qualByServiceId;
	private String correlationId;
	private String channelOutletId;
	private String salesRepId;
	private String applicationId;
	private String accountId;
	
	private String customerId;
	private String billingAccountNumber;
	
	@Override
	public String getCacheKey(){
		return "GetProductQualification"
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.SALES_TXN_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getSalesTransactionId()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.ADDRESS_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getAddressId()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.PROVINCE_STATE_CODE
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getProvinceCd()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getCustomerId()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getBillingAccountNumber();
	}

	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Boolean getQualByServiceId() {
		return qualByServiceId;
	}
	public void setQualByServiceId(Boolean qualByServiceId) {
		this.qualByServiceId = qualByServiceId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getChannelOutletId() {
		return channelOutletId;
	}
	public void setChannelOutletId(String channelOutletId) {
		this.channelOutletId = channelOutletId;
	}
	public String getSalesRepId() {
		return salesRepId;
	}
	public void setSalesRepId(String salesRepId) {
		this.salesRepId = salesRepId;
	}
	
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}

	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}
	
}
