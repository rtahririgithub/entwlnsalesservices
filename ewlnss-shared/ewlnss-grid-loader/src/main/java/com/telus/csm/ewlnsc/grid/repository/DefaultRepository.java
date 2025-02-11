package com.telus.csm.ewlnsc.grid.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.ValueExtractor;
import com.telus.csm.ewlnsc.grid.domain.Entity;

@SuppressWarnings("unchecked")
public class DefaultRepository<K, V extends Entity<K>> {
	
	private static Logger LOG = Logger.getLogger(DefaultRepository.class);
	
	private NamedCache namedCache;
	
	private static ThreadLocal<Hashtable<String, Long[]>> stat = new ThreadLocal<Hashtable<String, Long[]>>() {
		protected Hashtable<String, Long[]> initialValue() {
			return  new Hashtable<String, Long[]>();
		}
	};
	
	public void initCache(String cacheName) {
		namedCache = CacheFactory.getCache( cacheName );
	}

	public NamedCache getCache() {
		return namedCache;
	}
	
	public String getCacheName() {
		return getCache().getCacheName();
	}

	public void createIndexes() {
		// default empty implementation
	}

	public V get(K id) {
		
		long start = System.currentTimeMillis();
		V result = (V) getCache().get(id);
		long end = System.currentTimeMillis();
		addStat( getCache().getCacheName() + "-get", end - start);
		
		if ( result==null ) {
			LOG.warn( getCacheName() + " missing key: " + id );
		}
		
		return result;
	}

	public Collection<V> getAll(Collection<K> ids) {

//		long start = System.currentTimeMillis();
//		Collection<V> result = (Collection<V>) getCache().getAll(ids).values();
//		long end = System.currentTimeMillis();
//		addStat(getCache().getCacheName() + "-getAllBykey", end - start);
//		return result;
		
		//refactoring above code to take benefit of logging in getAllEntries method
		return  getAllEntries( ids ).values();
	}
	
	public Map<K,V> getAllEntries(Collection<K> keys) {
		long start = System.currentTimeMillis();
		 Map<K,V> result = ( Map<K,V>) getCache().getAll(keys);
		long end = System.currentTimeMillis();
		addStat(getCache().getCacheName() + "-getAllBykey", end - start);
		
		if ( result.size()!=keys.size() ) {
			ArrayList<K> newKeys = new ArrayList<K>(keys);
			newKeys.removeAll( result.keySet() );
			LOG.warn( getCacheName() + " missing keys: " + newKeys );
		}
		
		return result;
	}	

	public Collection<V> getAll() {
		long start = System.currentTimeMillis();
		Collection<V> result = (Collection<V>) getCache().values();
		long end = System.currentTimeMillis();
		addStat(getCache().getCacheName() + "-getAll", end - start);
		return result;
	}

	public Set<K> getAllKeys() {
		long start = System.currentTimeMillis();
		Set<K> result = (Set<K>) getCache().keySet();
		long end = System.currentTimeMillis();
		addStat(getCache().getCacheName() + "-getAllKeys", end - start);
		return result;
	}

	public void put(V obj) {
		if (obj==null) return;

		long start = System.currentTimeMillis();
		getCache().putAll(Collections.singletonMap(((Entity<K>) obj).getId(), obj));
		long end = System.currentTimeMillis();
		addStat("put", end - start);
	}

	@SuppressWarnings({ "rawtypes" })
	public void putAll(Collection<V> objs) {
		if (objs==null) return;
		Map<K, V> entries = new HashMap();
		for (V obj : objs) {
			entries.put(((Entity<K>) obj).getId(), obj);
		}
		long start = System.currentTimeMillis();
		getCache().putAll(entries);
		long end = System.currentTimeMillis();
		addStat("putAll", end - start);
	}

	public void remove(K id) {
		long start = System.currentTimeMillis();
		getCache().remove(id);
		long end = System.currentTimeMillis();
		addStat("remove", end - start);
	}

	public void removeValue(V obj) {
		long start = System.currentTimeMillis();
		remove(obj.getId());
		long end = System.currentTimeMillis();
		addStat("removeValue", end - start);
	}

	public boolean isItemInCache(final K id) {
		if (id == null) {
			return false;
		}
		
		NamedCache namedCache = getCache();
		if (namedCache == null) return false;
		
		//return namedCache.containsKey(id);
		long start = System.currentTimeMillis();
		boolean result = namedCache.keySet().contains(id);
		long end = System.currentTimeMillis();
		addStat("removeValue", end - start);
		return result;
	}
	
	public 	Collection<K> findItemsNotInCache(Map<K, V> itemGrp, List<V> missedItemList) {				
		long start = System.currentTimeMillis();
		Map<K, V> result = getCache().getAll(itemGrp.keySet());
		long end = System.currentTimeMillis();
		addStat("findItemsNotInCache", end - start);
		
		Set<K> missedIds = new HashSet<K>();
		if (itemGrp.size() > result.size()) {
			for (K id : itemGrp.keySet()) {
				if (result.get(id) == null) {
					missedIds.add(id);
					missedItemList.add(itemGrp.get(id));
				}
			}
		}
		
		return missedIds;
	}

	private void addStat(String method, long elapse) {
		Long[] entry = stat.get().get(method);
		if (entry == null) {
			entry = new Long[2];
			entry[0] = (long) 0;
			entry[1] = (long) 0;	
		}

		entry[0]++;
		entry[1] += elapse;
		stat.get().put(method, entry);
	}

	public static void clearCacheStat() {
		
		stat.set(new Hashtable<String, Long[]>());
		
	}
	
	public static void logCacheStat(Logger logger) {

		StringBuffer msg = new StringBuffer(); 
		
		boolean isWarn = false;
		long totalElapse = 0;
		
		for (Entry<String, Long[]> statEntry : stat.get().entrySet()) {
			msg.append(" " + statEntry.getKey() + "=" + statEntry.getValue()[0] + "(" + statEntry.getValue()[1] + "ms)");
			totalElapse += statEntry.getValue()[1];
			if (statEntry.getValue()[0] > 10)
				isWarn = true;
		}
		if (totalElapse > 50)
			isWarn = true;
		
		if (msg.length() == 0)
			return;
		
		msg.insert(0, "Cache activity counts:");

		if (isWarn)
			logger.warn(msg.toString());
		else 	
			logger.debug(msg.toString());
		
	}
	
	public void clearCache() {
		NamedCache nameCache = getCache();
		int cacheSize = nameCache.size();
		getCache().clear();
		
		LOG.info(  "cache[" + nameCache.getCacheName() + "], size:" + cacheSize + " is cleard.");
		
	}

	protected void createIndex(String indexName, ValueExtractor ve) {
		
		long startTime = System.currentTimeMillis();
		try {
			getCache().addIndex( ve, false, null);
		} finally {
			long timeElapsed =  (System.currentTimeMillis()- startTime );
			LOG.debug( getCache().getCacheName()  + "  createIndex: " + indexName + ", timeElapsed(ms):" + timeElapsed  );
		}
	}

}
