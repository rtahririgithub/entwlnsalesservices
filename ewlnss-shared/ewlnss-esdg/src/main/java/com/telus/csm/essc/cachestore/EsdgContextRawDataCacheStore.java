package com.telus.csm.essc.cachestore;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.telus.csm.esdg.database.EsdgDatabaseAdapter_1;
import com.telus.csm.esdg.domain.EsdgContextRawDataDO_1;

public class EsdgContextRawDataCacheStore extends SalesCacheStore {
	private static Logger log = Logger.getLogger(EsdgContextRawDataCacheStore.class);
	
	@Override
	protected Object doLoad(Object oKey) throws Exception {
		return esdgDatabaseAdapter.selectSalesContextRawData((String)oKey);
	}

	@Override
	protected Map doLoadAll(Collection oCollection) throws Exception {
		return esdgDatabaseAdapter.selectSalesContextRawData(oCollection);
	}

	@Override
	protected boolean doErase(Object oKey) throws Exception {
		return true;
	}

	@Override
	protected boolean doEraseAll(Collection oCollection) throws Exception {
		return true;
	}

	@Override
	protected boolean doStore(Object oKey, Object oValue) throws Exception {
		return esdgDatabaseAdapter.insertSalesContextRawData((EsdgContextRawDataDO_1)oValue);
	}

	@Override
	protected boolean doStoreAll(Map oMap) {
		return esdgDatabaseAdapter.insertSalesContextRawData(oMap.values());
	}	
	
}
