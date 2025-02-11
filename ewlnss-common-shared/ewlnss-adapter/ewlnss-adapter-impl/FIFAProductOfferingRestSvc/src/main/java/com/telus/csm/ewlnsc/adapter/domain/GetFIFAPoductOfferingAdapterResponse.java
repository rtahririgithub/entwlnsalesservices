package com.telus.csm.ewlnsc.adapter.domain;




import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;
import com.telus.csm.ffpo.rest.domain.ProductOfferingQualification;


public class GetFIFAPoductOfferingAdapterResponse extends WlnOPRestSvcResponseBase {

	private static final long serialVersionUID = 1L;
	private GetFIFAPoductOfferingAdapterRequest request;
	private ProductOfferingQualification productOfferingQualification;
	
	public void setRequest( GetFIFAPoductOfferingAdapterRequest request) {
		this.request = request;
	}
	
	public ProductOfferingQualification getProductOfferingQualification() {
		return productOfferingQualification;
	}
	public void setProductOfferingQualification(ProductOfferingQualification productOfferingQualification) {
		this.productOfferingQualification = productOfferingQualification;
	}
	
	public String getMainOfferId() {
		return request.getOfferId();
	}
	public String getCategoryType() {
		return request.getCategoryType();
	}
	public String getCategoryId() {
		return request.getCategoryId();
	}
	public String getCategoryName() {
		return request.getCategoryName();
	}
	public String getProductType() {
		return request.getProductType();
	}
	public int getOfferCount() {
		int result = 0;
		if (productOfferingQualification!=null
				&& CollectionUtils.isNotEmpty(productOfferingQualification.getProductOfferingQualificationItem())) {
			result = productOfferingQualification.getProductOfferingQualificationItem().size();
		}
		return result;
	}
}
