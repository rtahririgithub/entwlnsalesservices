package com.telus.csm.essc.cachestore;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.telus.csm.esdg.domain.EsdgSubscriberContextDO;


@SuppressWarnings({"rawtypes","unchecked"})
public class EsdgSubscriberContextCacheStore extends SalesCacheStore {
	
	public EsdgSubscriberContextCacheStore() {
		super();
	}

	@Override
	protected Object doLoad(Object oKey) throws Exception {
		return esdgDatabaseAdapter.selectSubscriberContext((String)oKey);
	}

	@Override
	protected Map doLoadAll(Collection oCollection) throws Exception {
		return esdgDatabaseAdapter.selectSubscriberContexts ( oCollection );
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
		esdgDatabaseAdapter.saveSubscirberContext((EsdgSubscriberContextDO) oValue);
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
