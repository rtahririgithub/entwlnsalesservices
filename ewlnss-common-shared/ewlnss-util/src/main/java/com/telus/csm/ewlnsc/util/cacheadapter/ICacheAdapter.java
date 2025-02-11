package com.telus.csm.ewlnsc.util.cacheadapter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.telus.framework.util.cache.GenericCache;

public interface ICacheAdapter extends GenericCache {

	@Override
	Serializable get(Serializable key);

	@Override
	void put(Serializable key, Serializable value);

	@Override
	boolean remove(Serializable value);

	@Override
	boolean removeByKey(Serializable key);

	@Override
	void removeAll();

	void saveAllToCache(Map<String, Object> cacheMap);

	Map<String, Object> getAllFromCache(Collection<String> colKeys);

	boolean isInCache(final String cacheKey);

	Set<?> getAllKeysFromCache();

	Set<Entry<?, ?>> getAllFromCache();

	String getCacheName();

	<T> T get(Serializable entityCacheKey, Class<T> objectType);

}
