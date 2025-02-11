package com.telus.csm.ewlnssproxy.rest.transformer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnssproxy.ccb.domain.ConsumerCreditEligibility;
import com.telus.csm.ewlnssproxy.ccb.domain.EquipmentCategory;
import com.telus.csm.ewlnssproxy.ccb.domain.EquipmentCategoryQualificationList;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo;

public class GetCustomerCreditEligibilityTransformer extends BaseTransformer {
	
	private static Logger logger = Logger.getLogger(GetCustomerCreditEligibilityTransformer.class);

	public static GetCreditEligibilityAdapterRequest transform(String transactionId, String customerId, boolean refreshCache, AuditInfo auditInfo) {
		
		GetCreditEligibilityAdapterRequest request = new GetCreditEligibilityAdapterRequest();
		
		request.setSalesTransactionId(transactionId);
		request.setCustomerId(customerId);
		request.setRefreshCache(refreshCache);
		request.setAuditInfo(auditInfo);
		
		return request;
	}

	public static ConsumerCreditEligibility transform(GetCreditEligibilityAdapterResponse soapResp) {
		
		if (null != soapResp) {
			ConsumerCreditEligibility restResponse = new ConsumerCreditEligibility();
			
			restResponse.setCollectionInd(soapResp.getCollectionInd());
			restResponse.setFraudInd(soapResp.getFraudInd());
			if (null!=soapResp.getEquipmentQualificationList() && CollectionUtils.isNotEmpty(soapResp.getEquipmentQualificationList().getEquipmentCategory())) {
				logger.debug("EquipmentQualificationList:\n" + soapResp.getEquipmentQualificationList().toString());
				for (com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.EquipmentCategory ec : soapResp.getEquipmentQualificationList().getEquipmentCategory()) {
					EquipmentCategory equipmentCategory = new EquipmentCategory();
					equipmentCategory.setMaxCount(ec.getMaxCount());
					equipmentCategory.setProductCd(ec.getProductCd());
					if (null == restResponse.getEquipmentQualificationList())
						restResponse.setEquipmentQualificationList(new EquipmentCategoryQualificationList());
					restResponse.getEquipmentQualificationList().addEquipmentCategoryItem(equipmentCategory);
				}
			}
			
			return restResponse;
		}
		
		return null;
	}
	
}
