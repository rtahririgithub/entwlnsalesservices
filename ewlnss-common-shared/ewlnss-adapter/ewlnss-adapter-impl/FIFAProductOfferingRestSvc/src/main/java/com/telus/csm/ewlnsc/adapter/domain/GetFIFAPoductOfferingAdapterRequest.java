package com.telus.csm.ewlnsc.adapter.domain;


import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.csm.ffpo.rest.domain.ProductOfferingQualification;


public class GetFIFAPoductOfferingAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	
	private boolean refreshCacheInd;
	private String originatorApplicationId;
	private boolean consolidatedCustomerProfileInd = true;
	private boolean allowProductTierUpgradeOnlyInd;
	
	private ProductOfferingQualification productOfferingQualification;
	
	private String productType;
	private String categoryType;
	private String categoryId;
	private String offerId;
	private String categoryName;
	
	
	public boolean isRefreshCacheInd() {
		return refreshCacheInd;
	}
	public void setRefreshCacheInd(boolean refreshCacheInd) {
		this.refreshCacheInd = refreshCacheInd;
	}
	public String getOriginatorApplicationId() {
		return originatorApplicationId;
	}
	public void setOriginatorApplicationId(String originatorApplicationId) {
		this.originatorApplicationId = originatorApplicationId;
	}
	public boolean isConsolidatedCustomerProfileInd() {
		return consolidatedCustomerProfileInd;
	}
	public void setConsolidatedCustomerProfileInd(boolean consolidatedCustomerProfileInd) {
		this.consolidatedCustomerProfileInd = consolidatedCustomerProfileInd;
	}
	public boolean isAllowProductTierUpgradeOnlyInd() {
		return allowProductTierUpgradeOnlyInd;
	}
	public void setAllowProductTierUpgradeOnlyInd(boolean allowProductTierUpgradeOnlyInd) {
		this.allowProductTierUpgradeOnlyInd = allowProductTierUpgradeOnlyInd;
	}
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public ProductOfferingQualification getProductOfferingQualification() {
		return productOfferingQualification;
	}
	public void setProductOfferingQualification(ProductOfferingQualification productOfferingQualification) {
		this.productOfferingQualification = productOfferingQualification;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	@Override
	public String getCacheKey() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sb.append(this.getSalesTransactionId());
		sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sb.append(this.productType);
		sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sb.append(getOfferId()); //offerId
		String categoryId = getCategoryId();
		if(StringUtils.isNotBlank(categoryId)) {
			sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sb.append(categoryId);
		}
		return sb.toString();
	}
	
//	public String getCategoryId() {
//		
//		if(this.getProductOfferingQualification().getProductOfferingQualificationItem().size()>1) {
//			for(ProductOfferingQualificationItem pqItem : this.getProductOfferingQualification().getProductOfferingQualificationItem()) {
//				if(CollectionUtils.isNotEmpty(pqItem.getQualificationItemRelationship())) {
//					if(CollectionUtils.isNotEmpty(pqItem.getProductOffering().getCategory())) {
//						return pqItem.getProductOffering().getCategory().get(0).getId();
//					}
//				}
//			}
//		}
//		return null;
//	}
//	
//	public String getOfferId() {
//		
//		if(CollectionUtils.isNotEmpty(this.getProductOfferingQualification().getProductOfferingQualificationItem())) {
//			
//			//For the summary call there will be just 1  ProductOfferingQualificationItem
//			
//			if(this.getProductOfferingQualification().getProductOfferingQualificationItem().size()==1) {
//				ProductOfferingQualificationItem productOfferingQualificationItem = this.getProductOfferingQualification().getProductOfferingQualificationItem().get(0);
//				return productOfferingQualificationItem.getProductOffering().getId();
//			}
//			else {
//				for(ProductOfferingQualificationItem pqItem : this.getProductOfferingQualification().getProductOfferingQualificationItem()) {
//					if(CollectionUtils.isNotEmpty(pqItem.getQualificationItemRelationship())) {
//						for(QualificationItemRelationship relation : pqItem.getQualificationItemRelationship()) {
//							if("1".equals(relation.getId()) && "withItem".equalsIgnoreCase(relation.getType())) {
//								return pqItem.getProductOffering().getId();
//							}
//						}
//					}
//				}
//			}
//			
//		}
//		
//		return "";
//	}
	
	public String getCategoryId() {
		return this.categoryId ;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String  getOfferId() {
		return this.offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}