package com.telus.csm.ewlnsc.adapter.xfs.domain;

import java.util.Date;
import java.util.List;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

public class ExchangeForborneStatusAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;

	private List<String> npaNxxList;
    private String customerType;
    private Date effectiveDate;
    
	@Override
	public String getCacheKey(){
		StringBuilder sbCacheKey = new StringBuilder();
		sbCacheKey.append("GetForborneStatusByNpaNxxList");
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.SALES_TXN_ID);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(getSalesTransactionId());
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CUSTOMER_TYPE);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
		sbCacheKey.append(customerType);

		if ( (npaNxxList != null) && (npaNxxList.size() > 0) ) {
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.NPA_NXX_LIST);

			for (String npaNxx : npaNxxList) {
				sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
				sbCacheKey.append(npaNxx);
			}			
		}

		return sbCacheKey.toString();
	}

	public List<String> getNpaNxxList() {
		return npaNxxList;
	}
	public void setNpaNxxList(List<String> npaNxxList) {
		this.npaNxxList = npaNxxList;
	}
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
