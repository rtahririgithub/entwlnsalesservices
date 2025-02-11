package com.telus.csm.essc.cachestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.telus.csm.esdg.domain.EsdgInteractionKeyValueDO;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EsdgInteractionKeyValueCacheStore extends SalesCacheStore {

	@Override
	protected Object doLoad(Object oKey) throws Exception {
		try {
			String key = (String) oKey;
			int pos = key.indexOf("_");
			String salesInteractionId = key.substring(0, pos);
			String keyName = key.substring(pos + 1);
			EsdgInteractionKeyValueDO ret = esdgDatabaseAdapter.selectEsdgInteractionKeyValue(salesInteractionId, keyName);
			return ret;
		} catch(Exception e) {
			log.error("--ESDG-- Failed to get EsdgInteractionKeyValueDO for key: " +  oKey);
			throw e;
		}
	}

	@Override
	protected Map doLoadAll(Collection oCollection) throws Exception {
		HashMap orderMap = new HashMap();
		for( Iterator i = oCollection.iterator(); i.hasNext(); ) {
			Object oKey = i.next();
			Object oValue = doLoad(oKey);
			if( oValue != null ) orderMap.put(oKey, oValue);
		}
		if( orderMap.size() > 0 ) {
			return orderMap;
		} else {
			return null;
		}
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
		esdgDatabaseAdapter.saveEsdgInteractionKeyValue((EsdgInteractionKeyValueDO) oValue);
		return true;
	}

	@Override
	protected boolean doStoreAll(Map oMap) throws Exception {
		boolean bResult = true;
		for (Iterator i = oMap.keySet().iterator(); i.hasNext();) {
			Object oKey = i.next();
			if (!doStore(oKey, oMap.get(oKey))) {
				bResult = false;
			}
		}
		return bResult;
	}

	public static String generateCacheKey(String salesInteractionId, String keyName) {
		if (salesInteractionId != null && keyName != null) {
			return salesInteractionId + "_" + keyName;
		}
		return null;
	}
}
