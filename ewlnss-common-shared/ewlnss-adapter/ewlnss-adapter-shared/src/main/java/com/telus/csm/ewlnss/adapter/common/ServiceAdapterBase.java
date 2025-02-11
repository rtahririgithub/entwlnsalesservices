package com.telus.csm.ewlnss.adapter.common;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

public abstract class ServiceAdapterBase {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ServiceAdapterBase.class);

	protected AdapterFeatureDriver adapterFeatureDriver = new AdapterFeatureDriver();
	
	private AdapterFeatureSet features = new AdapterFeatureSet();

	private static String applicationName = ApplicationProperties.getConfigString("${privileges/virtualService/applicationName}");
	private static String applicationPwd = ApplicationProperties.getConfigString("${privileges/virtualService/applicationPwd}");
	private ICacheAdapter cacheAdapter;
	
	public ServiceAdapterBase() {
	}
	
	public ServiceAdapterBase(AdapterFeatureDriver adapterFeatureDriverIn) {
		if (adapterFeatureDriverIn != null){
			adapterFeatureDriver = adapterFeatureDriverIn;
		}
	}
	
	public ICacheAdapter getCacheAdapter() {
		if (cacheAdapter == null) {
			cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		}
		return cacheAdapter;
	}

	public void setCacheAdapter(ICacheAdapter cacheAdapter) {
		this.cacheAdapter = cacheAdapter;
	}

	protected AdapterFeatureSet getFeatures() {
		return features;
	}

	public void addFeature(AdapterFeature adapterFeature) {

		// add adapter specific feature for an adapter
		if (adapterFeature.isApplicable()) {
			features.add(adapterFeature);
		}
	}

	protected synchronized String getApplicationName() {
		return applicationName;
	}

	protected synchronized String getApplicationPwd() {
		return applicationPwd;
	}
	
	protected <T> T getFromCache(final AdapterRequestBase request, final Class<T> objectType) {
		String functionName = this.getClass().getSimpleName() + ".getFromCache";
		if (request.isRefreshCache()) {
			LOGGER.info(functionName, "Refresh Cache for cacheKey: " + request.getCacheKey());
			return null;
		}
		
		return getFromCache(request.getCacheKey(), objectType);
	}

	/** Method to get from cache for transaction cache*/
	protected <T> T getFromCache(final String cacheKey, final Class<T> objectType) {
		String functionName = this.getClass().getSimpleName() + ".getFromCache";
		if (StringUtils.isBlank(cacheKey)) {
			LOGGER.info(functionName, objectType.getSimpleName() + " cache not read for blank cacheKey: " + cacheKey);
			return null;
		}
		
		T cachedObject = getCacheAdapter().get(cacheKey, objectType);
		
		if (cachedObject == null){
			LOGGER.info(functionName, objectType.getSimpleName() + " was not found for cacheKey: " + cacheKey);
			return null;	
		}
		
		LOGGER.info(functionName, objectType.getSimpleName() +  " was found for cacheKey: " + cacheKey);
		return cachedObject;
	}
	
	protected void saveToCache(final String cacheKey, final Serializable response){
		String functionName = this.getClass().getSimpleName() + ".saveToCache";
		LOGGER.enter(functionName);
		try{
			if(StringUtils.isNotBlank(cacheKey) && response!=null){
				getCacheAdapter().put(cacheKey, response);
				LOGGER.info(functionName, response.getClass().getSimpleName() + " saved to cache with cacheKey: " + cacheKey);
			}
		}catch(RuntimeException e){
			LOGGER.error(functionName, e.getMessage(), LOGGER.getStackTrace(e));
		}
		LOGGER.exit(functionName);
	}

}
