package com.telus.csm.essc.cachestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.telus.csm.esdg.domain.EsdgInteractionDO_1;
import com.telus.csm.esdg.domain.EsdgSalesItemDO;

@SuppressWarnings({"rawtypes","unchecked"})
public class EsdgSalesItemCacheStore extends SalesCacheStore {
	
	public EsdgSalesItemCacheStore() {
		super();
	}

	@Override
	protected Object doLoad(Object oKey) throws Exception {
		return esdgDatabaseAdapter.selectSalesItem((String)oKey);
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
		esdgDatabaseAdapter.saveSalesItem((EsdgSalesItemDO) oValue);
		return true;
	}

	@Override
	protected boolean doStoreAll(Map oMap) throws Exception {
		boolean bResult = true;
		for( Iterator i = oMap.keySet().iterator(); i.hasNext(); ) {
			Object oKey = i.next();
			if( !doStore(oKey, oMap.get(oKey)) ) {
				bResult = false;
			}
		}
		return bResult;
	}

}
