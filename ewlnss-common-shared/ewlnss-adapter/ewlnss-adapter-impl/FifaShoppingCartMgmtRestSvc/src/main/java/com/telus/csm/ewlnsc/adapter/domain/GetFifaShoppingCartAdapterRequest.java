package com.telus.csm.ewlnsc.adapter.domain;


import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;


public class GetFifaShoppingCartAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	
	private boolean refreshCacheInd;
	private String shoppingCartId;
	
	StringBuilder sb = new StringBuilder();
	
	public boolean isRefreshCacheInd() {
		return refreshCacheInd;
	}
	public void setRefreshCacheInd(boolean refreshCacheInd) {
		this.refreshCacheInd = refreshCacheInd;
	}
	public String getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getCacheKey() {
		
		return sb.toString();
	}
	
	private void addToCacheKey(String key, String value) {
		if(value != null) {
			sb.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER)
			.append(key)
			.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER)
			.append(value);
		}
	}
	
}