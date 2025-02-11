package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class SubscriberInfoRestVO implements Serializable {

	/**
	 * 
	 */
	
//	protected List<SubscriberInfoRestVO.Subscriber> subscriberList;
	
	
//	public static class Subscriber{
	private static final long serialVersionUID = 1L;
	
		protected String subscriberId;
		protected String subscriptionId;
		protected String subscriberRoleCd;
		protected NameInfoRestVO consumerName;
		protected PhoneNumberRestVO phoneNum;
		protected String emailAddress;
		protected AddressRestVO address;
		protected String brandCd;
		protected String productTypeCd;
	
		public String getSubscriberId() {
			return subscriberId;
		}
	
		public void setSubscriberId(String subscriberId) {
			this.subscriberId = subscriberId;
		}
	
		public String getSubscriptionId() {
			return subscriptionId;
		}
	
		public void setSubscriptionId(String subscriptionId) {
			this.subscriptionId = subscriptionId;
		}
	
		public String getSubscriberRoleCd() {
			return subscriberRoleCd;
		}
	
		public void setSubscriberRoleCd(String subscriberRoleCd) {
			this.subscriberRoleCd = subscriberRoleCd;
		}
	
		public NameInfoRestVO getConsumerName() {
			return consumerName;
		}
	
		public void setConsumerName(NameInfoRestVO consumerName) {
			this.consumerName = consumerName;
		}
	
		public PhoneNumberRestVO getPhoneNum() {
			return phoneNum;
		}
	
		public void setPhoneNum(PhoneNumberRestVO phoneNum) {
			this.phoneNum = phoneNum;
		}
	
		public String getEmailAddress() {
			return emailAddress;
		}
	
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
	
		public AddressRestVO getAddress() {
			return address;
		}
	
		public void setAddress(AddressRestVO address) {
			this.address = address;
		}
	
		public String getBrandCd() {
			return brandCd;
		}
	
		public void setBrandCd(String brandCd) {
			this.brandCd = brandCd;
		}
	
		public String getProductTypeCd() {
			return productTypeCd;
		}
	
		public void setProductTypeCd(String productTypeCd) {
			this.productTypeCd = productTypeCd;
		}
//	}
//
//
//	public List<SubscriberInfoRestVO.Subscriber> getSubscriberList() {
//		return subscriberList;
//	}
//
//
//	public void setSubscriberList(
//			List<SubscriberInfoRestVO.Subscriber> subscriberList) {
//		this.subscriberList = subscriberList;
//	}
}
