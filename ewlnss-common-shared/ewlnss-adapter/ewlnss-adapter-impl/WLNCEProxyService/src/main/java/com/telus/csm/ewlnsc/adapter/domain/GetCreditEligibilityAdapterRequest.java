package com.telus.csm.ewlnsc.adapter.domain;


import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCreditEligibilityAdapterRequest extends AdapterRequestBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private AuditInfo auditInfo;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	@Override
	public String getCacheKey(){
		return "GetCreditEligibility"
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.SALES_TXN_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getSalesTransactionId()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.CUSTOMER_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getCustomerId();
	}
}
