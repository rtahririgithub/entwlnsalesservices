package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

public class ProductFeasibilityInfoWrapper {

	private String feasibilityResult = null;
	private String servicePlan = null;
	private String bestAvailableConfigurationInd = null;
	CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse;

	public ProductFeasibilityInfoWrapper(CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse) {
		this.checkProductFeasibilityAdapterResponse = checkProductFeasibilityAdapterResponse;
		init();
	}

	private void init() {
		for (ProductFeasibilityInfo productFeasibilityInfo : getProductFeasibilityInfoList()) {
			feasibilityResult = productFeasibilityInfo.getFeasibilityResult();

			if (EnterpriseWLNSalesServicesConstants.HSIC
					.equalsIgnoreCase(productFeasibilityInfo.getProductSpecification().getName())) {
				for (ProductSpecificationCharacteristic productSpecificationCharacteristic : productFeasibilityInfo
						.getProductSpecification().getProductSpecificationCharacteristics()) {
					if ("servicePlan".equalsIgnoreCase(productSpecificationCharacteristic.getName())) {
						for (CompositeProductSpecificationCharacteristicValue value : productSpecificationCharacteristic
								.getProductSpecificationCharacteristicValues()) {
							servicePlan = value.getValue();
						}
					}

					if ("bestAvailableConfigurationInd"
							.equalsIgnoreCase(productSpecificationCharacteristic.getName())) {
						for (CompositeProductSpecificationCharacteristicValue value : productSpecificationCharacteristic
								.getProductSpecificationCharacteristicValues()) {
							bestAvailableConfigurationInd = value.getValue();
						}
					}
				}
			}
		}

	}

	private List<ProductFeasibilityInfo> getProductFeasibilityInfoList() {
		return checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList() == null
				|| checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList()
						.getProductFeasibilityInfo() == null ? new ArrayList<ProductFeasibilityInfo>()
								: checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList()
										.getProductFeasibilityInfo();

	}

	public boolean getFeasibilityResult() {
		return Boolean.parseBoolean(feasibilityResult);
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public boolean getBestAvailableConfigurationInd() {
		return Boolean.parseBoolean(bestAvailableConfigurationInd);
	}

}
