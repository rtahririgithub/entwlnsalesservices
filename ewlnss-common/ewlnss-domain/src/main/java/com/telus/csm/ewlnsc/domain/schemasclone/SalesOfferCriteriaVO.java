package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;


public class SalesOfferCriteriaVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String billingAccountNumber;
	private ServiceAddressVO serviceAddress;
	private List<SubscribedServiceIdentifierVO> subscribedServiceIdentifier;
	private WirelineOfferFilter offerFilter;
	private String promotionCd;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}
	public List<SubscribedServiceIdentifierVO> getSubscribedServiceIdentifier() {
		return subscribedServiceIdentifier;
	}
	public void setSubscribedServiceIdentifier(List<SubscribedServiceIdentifierVO> subscribedServiceIdentifier) {
		this.subscribedServiceIdentifier = subscribedServiceIdentifier;
	}

	public ServiceAddressVO getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(ServiceAddressVO serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public WirelineOfferFilter getOfferFilter() {
		return offerFilter;
	}
	public void setOfferFilter(WirelineOfferFilter offerFilter) {
		this.offerFilter = offerFilter;
	}
	public String getPromotionCd() {
		return promotionCd;
	}
	public void setPromotionCd(String promotionCd) {
		this.promotionCd = promotionCd;
	}
	
	
}
