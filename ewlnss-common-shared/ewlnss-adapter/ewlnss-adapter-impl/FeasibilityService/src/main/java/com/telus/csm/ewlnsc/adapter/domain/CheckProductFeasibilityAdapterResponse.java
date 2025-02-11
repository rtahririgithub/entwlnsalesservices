package com.telus.csm.ewlnsc.adapter.domain;

import java.util.ArrayList;
import java.util.List;

import static com.telus.csm.ewlnsc.util.Constants.FIELD_WORK;
import static com.telus.csm.ewlnsc.util.Constants.SOFTWARE;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfoList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessageList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAccessList;

public class CheckProductFeasibilityAdapterResponse extends AdapterResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServiceAccessList serviceAccessList;
	private ProductFeasibilityInfoList productFeasibilityInfoList;
	private ResponseMessageList responseMessageList;
	public ServiceAccessList getServiceAccessList() {
		return serviceAccessList;
	}
	public void setServiceAccessList(ServiceAccessList serviceAccessList) {
		this.serviceAccessList = serviceAccessList;
	}
	public ProductFeasibilityInfoList getProductFeasibilityInfoList() {
		return productFeasibilityInfoList;
	}
	public void setProductFeasibilityInfoList(ProductFeasibilityInfoList productFeasibilityInfoList) {
		this.productFeasibilityInfoList = productFeasibilityInfoList;
	}
	public ResponseMessageList getResponseMessageList() {
		return responseMessageList;
	}
	public void setResponseMessageList(ResponseMessageList responseMessageList) {
		this.responseMessageList = responseMessageList;
	}
	
	/*******************************************/
	/* getInstallTypeSW                        */ 
	/*******************************************/
	public List<String> getInstallTypeSW(){
		List<String> list = new ArrayList<String>();
		if (this.productFeasibilityInfoList != null){
			if (! this.productFeasibilityInfoList.getProductFeasibilityInfo().isEmpty()){ 
				for (ProductFeasibilityInfo info : this.productFeasibilityInfoList.getProductFeasibilityInfo()){
					if (info.getProvisioningInformation().getRecommendedInstallType() != null &&
						info.getProvisioningInformation().getRecommendedInstallType().equalsIgnoreCase(SOFTWARE))
							list.add(info.getProductSpecification().getName());
				} 
			}
		}
		return list;
	}

	/*******************************************/
	/* getInstallTypeFW                        */ 
	/*******************************************/
	public List<String> getInstallTypeFW(){
		List<String> list = new ArrayList<String>();
		if (this.productFeasibilityInfoList != null){
			if (! this.productFeasibilityInfoList.getProductFeasibilityInfo().isEmpty()){ 
				for (ProductFeasibilityInfo info : this.productFeasibilityInfoList.getProductFeasibilityInfo()){
					if (info.getProvisioningInformation().getRecommendedInstallType() != null &&
						info.getProvisioningInformation().getRecommendedInstallType().equalsIgnoreCase(FIELD_WORK))
							list.add(info.getProductSpecification().getName());
				} 
			}
		}
		return list;
	}

	/*******************************************/
	/* getInstallTypeRWFW                      */ 
	/*******************************************/
	public List<ProductFeasibilityInfo> getInstallTypeRWFW(){
		List<ProductFeasibilityInfo> list = new ArrayList<ProductFeasibilityInfo>();
		if (this.productFeasibilityInfoList != null){
			if (! this.productFeasibilityInfoList.getProductFeasibilityInfo().isEmpty()){
				for (ProductFeasibilityInfo info : this.productFeasibilityInfoList.getProductFeasibilityInfo()){
					// if recommendedInstallType is not SW, add to list and will call booking service
					if (info.getProvisioningInformation().getRecommendedInstallType() != null &&
						! info.getProvisioningInformation().getRecommendedInstallType().equalsIgnoreCase(SOFTWARE)){
							list.add(info);
					}
				}
			}
		}
		return list;
	}

}
