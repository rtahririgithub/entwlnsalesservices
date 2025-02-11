package com.telus.csm.ewlnsc.util.cacheadapter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.framework.util.cache.coherence.CoherenceCacheAdapter;

public class ESSCacheAdapter extends CoherenceCacheAdapter implements ICacheAdapter {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ESSCacheAdapter.class);

	private NamedCache namedCache;
 
	public ESSCacheAdapter(String cacheName) {
		setCacheName(cacheName);
		super.init();
		namedCache = CacheFactory.getCache(this.getCacheName());
	}

	@Override
	public void saveAllToCache(Map<String, Object> cacheMap) {
		if( cacheMap == null || cacheMap.size() < 1 ){
			return;
		}
		
		if (namedCache == null) {
			return;
		}
		
		namedCache.putAll(cacheMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAllFromCache(Collection<String> colKeys) {
		if( colKeys == null || colKeys.isEmpty() ) {
			return null;
		}
		
		if (namedCache == null) {
			return null;
		}
		return namedCache.getAll(colKeys);
		
	}

	@Override
	public boolean isInCache(final String cacheKey) {
		if (StringUtils.isEmpty(cacheKey)) {
			return false;
		}
		
		if (namedCache == null) {
			return false;
		}
		
		return namedCache.containsKey(cacheKey);
	}

	@Override
	public Set<Entry<?, ?>> getAllFromCache() {
		
		if (namedCache == null) {
			return null;
		}
		return namedCache.entrySet();
		
	}

	@Override
	public Set<?> getAllKeysFromCache() {
		
		return namedCache.keySet();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Serializable cacheKey, Class<T> objectType) {
		Object cachedObject = get(cacheKey);
		
		if (cachedObject == null) {
			return null;
		}
		
		if (!objectType.isInstance(cachedObject)) {
			LOGGER.error("", "get", "Expecting " + objectType.getSimpleName() + " found unmatched object type " + cachedObject.getClass().getSimpleName() + " for cacheKey: " + cacheKey);		
			return null;
		}	
		
		return (T) cachedObject;
	}
}
