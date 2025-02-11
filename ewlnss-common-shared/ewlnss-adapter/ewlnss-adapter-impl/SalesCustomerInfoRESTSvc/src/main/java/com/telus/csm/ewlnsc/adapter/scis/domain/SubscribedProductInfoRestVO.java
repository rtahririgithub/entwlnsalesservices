package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class SubscribedProductInfoRestVO implements Serializable {

		private static final long serialVersionUID = 1L;
	
		protected String productLine;
		protected String productTierCd;
		protected String productTypeCd;
		protected String recurringPayChannelNum;
		protected String oneTimePayChannelNum;
		protected List<ProductInstanceInfoRestVO> productInstance;
		protected String subscriptionStatusCd;
		protected Boolean productInPendingOrderStatusInd;
		protected AddressRestVO serviceAddress;
		protected List<String> pricePlanId;
	
		public String getProductLine() {
			return productLine;
		}
	
		public void setProductLine(String productLine) {
			this.productLine = productLine;
		}
	
		public String getProductTierCd() {
			return productTierCd;
		}
	
		public void setProductTierCd(String productTierCd) {
			this.productTierCd = productTierCd;
		}
	
		public String getProductTypeCd() {
			return productTypeCd;
		}
	
		public void setProductTypeCd(String productTypeCd) {
			this.productTypeCd = productTypeCd;
		}
	
		public String getRecurringPayChannelNum() {
			return recurringPayChannelNum;
		}
	
		public void setRecurringPayChannelNum(String recurringPayChannelNum) {
			this.recurringPayChannelNum = recurringPayChannelNum;
		}
	
		public String getOneTimePayChannelNum() {
			return oneTimePayChannelNum;
		}
	
		public void setOneTimePayChannelNum(String oneTimePayChannelNum) {
			this.oneTimePayChannelNum = oneTimePayChannelNum;
		}
	
		public List<ProductInstanceInfoRestVO> getProductInstance() {
			return productInstance;
		}
	
		public void setProductInstance(
				List<ProductInstanceInfoRestVO> productInstance) {
			this.productInstance = productInstance;
		}
	
		public String getSubscriptionStatusCd() {
			return subscriptionStatusCd;
		}
	
		public void setSubscriptionStatusCd(String subscriptionStatusCd) {
			this.subscriptionStatusCd = subscriptionStatusCd;
		}
	
		public Boolean getProductInPendingOrderStatusInd() {
			return productInPendingOrderStatusInd;
		}
	
		public void setProductInPendingOrderStatusInd(
				Boolean productInPendingOrderStatusInd) {
			this.productInPendingOrderStatusInd = productInPendingOrderStatusInd;
		}
	
		public AddressRestVO getServiceAddress() {
			return serviceAddress;
		}
	
		public void setServiceAddress(AddressRestVO serviceAddress) {
			this.serviceAddress = serviceAddress;
		}

		public List<String> getPricePlanIdList() {
			return pricePlanId;
		}

		public void setPricePlanIdList(List<String> pricePlanId) {
			this.pricePlanId = pricePlanId;
		}
		
		public void setPricePlanId(List<String> pricePlanId) {
			this.pricePlanId = pricePlanId;
		}

}
