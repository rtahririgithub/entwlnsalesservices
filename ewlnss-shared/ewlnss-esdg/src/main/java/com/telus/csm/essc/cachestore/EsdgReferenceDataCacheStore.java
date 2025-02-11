package com.telus.csm.essc.cachestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tangosol.net.CacheFactory;
import com.telus.csm.esdg.EsdgConstants;
import com.telus.csm.esdg.database.EsdgReferenceDataAdapter;
import com.telus.csm.esdg.domain.EsdgReferenceDataDO;

@SuppressWarnings({"rawtypes","unchecked"})
public class EsdgReferenceDataCacheStore extends SalesCacheStore {
	
	/** The logger. */
	static private Logger logger = Logger.getLogger(EsdgReferenceDataCacheStore.class);
	
	static private String partitionCd = null;

	static private boolean savedIsESDBEnabled = true;
	
	/** The esdg reference data database adapter. */
	protected EsdgReferenceDataAdapter esdgReferenceDataAdapter;

	
	public EsdgReferenceDataCacheStore() {
		esdgReferenceDataAdapter = new EsdgReferenceDataAdapter(getEsdgDatabaseConnectionFactory());
		esdgReferenceDataAdapter.setDbUser(DB_USER_ESDG);
	}

	@Override
	protected Object doLoad(Object oKey) throws Exception {
		
		Collection keys = new ArrayList();
		keys.add(oKey);

		Map objects = doLoadAll(keys);
		return objects.get(oKey);
		
	}

	@Override
	protected Map doLoadAll(Collection oCollection) throws Exception {
		
		Map result = new HashMap();
		
		if (oCollection == null || oCollection.isEmpty() ) {
			return result;
		}
		
		Boolean isESDBEnabledCacheValue = isESDBEnabledCacheValue();
		
		// In case Coherence is restarted, the partition code and isESDBEnabled saved in cache ServiceOrderReferenceServiceESDBAccess would be removed (null).
		// The following code is to return a specific String to signal the SalesReferenceDataManager to do an initServiceOrderReferenceServiceESDBAccess() 
		if (getPartitionCd() == null || isESDBEnabledCacheValue == null) {
			logger.error(EsdgConstants.ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME + " not initialized");
			for (Object key : oCollection) {
				result.put(key, EsdgConstants.ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME);
			}
			return result;
		}
		
		if (!isESDBEnabledCacheValue) {
			logger.info("doLoadAll - database access is disabled");
			throw new DBAccessDisabledException();
		}

		result = esdgReferenceDataAdapter.selectReferenceData(getPartitionCd(), oCollection);
		
		return result;
	}

	@Override
	protected boolean doErase(Object oKey) throws Exception {
		throw new DBAccessDisabledException();
	}

	@Override
	protected boolean doEraseAll(Collection oCollection) throws Exception {
		throw new DBAccessDisabledException();
	}

	@Override
	protected boolean doStore(Object oKey, Object oValue) throws Exception {

		Map oMap = new HashMap();
		oMap.put(oKey, oValue);
		return doStoreAll(oMap );
	}

	@Override
	protected boolean doStoreAll(Map oMap) throws Exception {
		
		if (!isESDBEnabled()) {
			logger.info("doStoreAll - database access is disabled");
			throw new DBAccessDisabledException();
		}
		
		Collection<EsdgReferenceDataDO> esdgReferenceDataDOs = oMap.values();

		esdgReferenceDataAdapter.saveReferenceData(esdgReferenceDataDOs);

		return true;
	}
	
	private Boolean isESDBEnabledCacheValue() {
		
		Boolean result = null;
		
		try {
			result = (Boolean) CacheFactory.getCache(EsdgConstants.ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME).get(EsdgConstants.ACCESS_ESDB_ENABLED);
		} catch (Exception e) {
			logger.error("Failed to read Cache name = " + EsdgConstants.ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME + " key = " + EsdgConstants.ACCESS_ESDB_ENABLED, e);
		}
		
		return result;
	}

	private boolean isESDBEnabled() {
		
		Boolean result = isESDBEnabledCacheValue();
		
		if (result != null) {
			savedIsESDBEnabled = result;
		}
		
		return savedIsESDBEnabled;
	}

	
	private String getPartitionCd() {
		
		if (partitionCd != null) {
			return partitionCd;
		}
		
		try {
			partitionCd = (String) CacheFactory.getCache(EsdgConstants.ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME).get(EsdgConstants.REFERENCE_DATA_PARTITION_CD);
			logger.info(EsdgConstants.ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME + " key=" + EsdgConstants.REFERENCE_DATA_PARTITION_CD + " value=" + partitionCd);
		} catch (Exception e) {
			partitionCd = null;
			logger.error("Failed to read Cache name = " + EsdgConstants.ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME + " key = " + EsdgConstants.REFERENCE_DATA_PARTITION_CD, e);
		}
		
		return partitionCd;
	}
	
	
}
