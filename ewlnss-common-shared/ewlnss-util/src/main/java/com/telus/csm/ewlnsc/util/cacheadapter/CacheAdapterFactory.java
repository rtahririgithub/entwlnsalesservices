package com.telus.csm.ewlnsc.util.cacheadapter;

import java.util.HashMap;

public class CacheAdapterFactory {
	
	public static final String SESSION_CACHE_NAME = "EntWLNSalesServicesCache.EWSS";
	private static HashMap<String, ESSCacheAdapter> cacheAdapterTable = new HashMap<String, ESSCacheAdapter>();

	private CacheAdapterFactory() {
		super();
	}

	public static ICacheAdapter getCacheAdapter(final String cacheName) {
		
		ESSCacheAdapter result = cacheAdapterTable.get(cacheName);
		
		if (result != null){
			return result;
		}
		
		result = new ESSCacheAdapter(cacheName);
		
		cacheAdapterTable.put(cacheName, result);
		
		return result;
		
	}
	
	public static ICacheAdapter getSessionCacheAdapter() {
		return getCacheAdapter(SESSION_CACHE_NAME);
	}

}
