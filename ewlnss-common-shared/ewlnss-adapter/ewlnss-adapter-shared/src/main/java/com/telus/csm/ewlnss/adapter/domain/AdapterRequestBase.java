package com.telus.csm.ewlnss.adapter.domain;

import java.io.Serializable;

public abstract class AdapterRequestBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6988382048504456802L;
	
	private String salesTransactionId= null;
	
	private String cacheKey;
	private boolean refreshCache = false;

	public boolean isRefreshCache() {
		return refreshCache;
	}

	public void setRefreshCache(boolean refreshCache) {
		this.refreshCache = refreshCache;
	}

	public String getSalesTransactionId() {
		return salesTransactionId;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public void setSalesTransactionId(String salesTransactionId) {
		this.salesTransactionId = salesTransactionId;
	}


}
