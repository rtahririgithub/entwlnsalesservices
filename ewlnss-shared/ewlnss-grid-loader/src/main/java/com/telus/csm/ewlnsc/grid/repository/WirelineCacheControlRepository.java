package com.telus.csm.ewlnsc.grid.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.net.cache.CacheStore;
import com.telus.csm.ewlnsc.grid.domain.WirelineCacheControl;

public class WirelineCacheControlRepository extends DefaultRepository<String, WirelineCacheControl> {
	
	private static final NamedCache namedCache = CacheFactory.getCache("WirelineCacheControlcache");
	private static final String CACHE_SUFFIX_KEY = "CACHE_SUFFIX";
	private static final String LAST_UPDATE_DT = "LAST_UPDATE_DT";
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.CANADA);

    private static class SingletonHolder { 
        public static final WirelineCacheControlRepository INSTANCE = new WirelineCacheControlRepository();
	}
	
	public static WirelineCacheControlRepository getInstance() {
	        return SingletonHolder.INSTANCE;
	}

	public NamedCache getCache() {
		return namedCache;
	}

	public CacheStore getCacheStore() {
		return null;
	}
	
	public synchronized String getCacheSuffix() {
		WirelineCacheControl control =  get(CACHE_SUFFIX_KEY);
		if (control==null) {
			return null;
		}
		return control.getValue();
	}
	
	public synchronized void setCacheSuffix(String suffix) {
		WirelineCacheControl control = new WirelineCacheControl();
		control.setKey(CACHE_SUFFIX_KEY);
		control.setValue(suffix);
		put(control);
	}

	public synchronized Date getLastUpdated() {
		WirelineCacheControl control =  get(LAST_UPDATE_DT);
		if (control==null || control.getValue()==null) {
			return null;
		}
		Date date = null;
		try {
			date = dateFormat.parse(control.getValue());
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}
	
	public synchronized void setLastUpdated(Date lastUpdated) {
		WirelineCacheControl control = new WirelineCacheControl();
		control.setKey(LAST_UPDATE_DT);
		control.setValue(dateFormat.format(lastUpdated));
		put(control);
	}

	private static final String CATALOGUE_ITEM_CNT = "CatalogueItemCount";
	public synchronized int getCatalogueItemCount() {
		WirelineCacheControl control = get(CATALOGUE_ITEM_CNT);
		if (control == null)
			return 0;
		
		return Integer.parseInt(control.getValue());
	}

	public synchronized void setCatalogueItemCount(int cnt) {
		WirelineCacheControl control = new WirelineCacheControl();
		control.setKey(CATALOGUE_ITEM_CNT);
		control.setValue(Integer.toString(cnt));
		put(control);
	}

	private static final String HIERARCHY_COUNT = "HierarchyCount";
	public synchronized int getHierarchyCount() {
		WirelineCacheControl control = get(HIERARCHY_COUNT);
		if (control == null)
			return 0;
		
		return Integer.parseInt(control.getValue());
	}

	public synchronized void seHierarchyCount(int cnt) {
		WirelineCacheControl control = new WirelineCacheControl();
		control.setKey(HIERARCHY_COUNT);
		control.setValue(Integer.toString(cnt));
		put(control);
	}

	private static final String EQUIPMENT_COUNT = "EquipmentCount";
	public synchronized int getEquipmentCount() {
		WirelineCacheControl control = get(EQUIPMENT_COUNT);
		if (control == null)
			return 0;
		
		return Integer.parseInt(control.getValue());
	}

	public synchronized void setEquipmentCount(int cnt) {
		WirelineCacheControl control = new WirelineCacheControl();
		control.setKey(EQUIPMENT_COUNT);
		control.setValue(Integer.toString(cnt));
		put(control);
	}

	private static final String PROD_CHARACTERISTIC_COUNT = "ProductCharacteristicCount";
	public synchronized int getProductCharacteristicCount() {
		WirelineCacheControl control = get(PROD_CHARACTERISTIC_COUNT);
		if (control == null)
			return 0;
		
		return Integer.parseInt(control.getValue());
	}

	public synchronized void setProductCharacteristicCount(int cnt) {
		WirelineCacheControl control = new WirelineCacheControl();
		control.setKey(PROD_CHARACTERISTIC_COUNT);
		control.setValue(Integer.toString(cnt));
		put(control);
	}


}
