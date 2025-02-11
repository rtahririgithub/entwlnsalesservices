package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

/**
 * 
 * @author Igal Katz
 *
 */
public class GetAvailableServiceInstanceListAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
    protected String customerId;
	protected String applicationId;
    protected String roleId;

    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String getCacheKey(){
		if (customerId == null || customerId.isEmpty()) {
			// Only use cache if there is one offer in the request   
			return null;
		} 		
		
		return "GetAvailableServiceInstanceList"
			+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.SALES_TXN_ID + getSalesTransactionId()
			+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.CUSTOMER_ID + getCustomerId()
			+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.APPLICATION_ID + getApplicationId()
			+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.ROLE_ID + getRoleId();
	}
}