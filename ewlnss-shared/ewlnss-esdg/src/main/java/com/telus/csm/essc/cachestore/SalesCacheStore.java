package com.telus.csm.essc.cachestore;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tangosol.net.cache.CacheStore;
import com.tangosol.util.Base;
import com.telus.csm.esdg.database.EsdgDatabaseAdapter_1;
import com.telus.csm.esdg.database.EsdgDatabaseUCPConnectionFactory;
import com.telus.csm.esdg.database.IEsdgDatabaseConnectionFactory;

/**
 * The Class SalesCacheStore.
 */
public abstract class SalesCacheStore extends Base implements CacheStore {
	
	public static final String ESDG_REVISION = "ESDG.V5.2018_11";
	
	/** The Constant DB_USER_ESDG. */
	public static final String DB_USER_ESDG = "ESDG";
	
	/** The esdg database connection factory. */
	protected IEsdgDatabaseConnectionFactory esdgDatabaseConnectionFactory = null;
	
	/** The esdg database adapter. */
	protected EsdgDatabaseAdapter_1 esdgDatabaseAdapter;
	
	/**
	 * Instantiates a new sales cache store.
	 */
	public SalesCacheStore() {
		esdgDatabaseAdapter = new EsdgDatabaseAdapter_1(getEsdgDatabaseConnectionFactory());
		esdgDatabaseAdapter.setDbUser(DB_USER_ESDG);
	}
	
	protected IEsdgDatabaseConnectionFactory getEsdgDatabaseConnectionFactory() {
		
		if( esdgDatabaseConnectionFactory == null ) {
			Properties properties = new Properties();
			try{
			    final InputStream stream = this.getClass().getClassLoader().getResourceAsStream("ess.properties");
			    if( stream == null ) {
			    	log.info("--ESDG-- Failed to initialized ucp connection factory due to missing ess.properties file in classpath.");
			    	return null;
			    }
			    properties.load(stream);			    
			    stream.close();
			    String jdbcDriverClassName = properties.getProperty("jdbcDriverClassName");
			    String jdbcUrl = properties.getProperty("jdbcUrl");
			    String jdbcUser = properties.getProperty("jdbcUser");
			    String jdbcPassword = properties.getProperty("jdbcPassword");
			    String initialPoolSize = properties.getProperty("initialPoolSize");
			    esdgDatabaseConnectionFactory = new EsdgDatabaseUCPConnectionFactory(jdbcDriverClassName, jdbcUrl, jdbcUser, jdbcPassword, initialPoolSize);
			    
			    log.info("--ESDG-- initialized ucp connection factory. url = " + jdbcUrl);
			    log.info("--ESDG-- cacheStoreVersion = " + getEsdgVersion()  );
			}catch(Exception e){
			    e.printStackTrace();
			}
		}
		return esdgDatabaseConnectionFactory;
	}

	public static String getEsdgVersion() {
		
		String version = ESDG_REVISION; 
		
		try{

			String versionPath = "META-INF/esdgCacheStore.version";
			URL url = SalesCacheStore.class.getClassLoader().getResource(versionPath);
			
			if ( url !=null ) {

				InputStream stream = url.openStream();

			    if( stream == null ) {
			    	log.info("--ESDG-- Failed to load " + versionPath + "from classpath" );
			    }
			    Properties properties = new Properties();
			    properties.load(stream);			    
			    stream.close();
			    
			    version = properties.getProperty("esdgVersion");
			    if ( "BUILDLABEL".equals( version ) ) {
			    	version = ESDG_REVISION;
			    }
			    version += " build on [" + properties.getProperty("esdgBuildDate") + "]  ;  load from: " + url.toExternalForm();
			    
			} else {
		    	log.info("--ESDG-- Failed to load " + versionPath + "from classpath" );
			}
		    
		} catch(Exception e){
			
			log.info("--ESDG-- Unable to to load build label from esdgCacheStore.version file from classpath, erorr: " + e.getMessage() );		
		} 
		return version ;
	}
	
	/** The log. */
	protected static Logger log = Logger.getLogger(SalesCacheStore.class);
	
	/**
	 * Do load.
	 *
	 * @param oKey the o key
	 * @return the object
	 * @throws Exception the exception
	 */
	protected abstract Object doLoad(Object oKey) throws Exception;
	
	/**
	 * Do load all.
	 *
	 * @param oCollection the o collection
	 * @return the map
	 * @throws Exception the exception
	 */
	protected abstract Map doLoadAll(Collection oCollection) throws Exception;
	
	/**
	 * Do erase.
	 *
	 * @param oKey the o key
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	protected abstract boolean doErase(Object oKey) throws Exception;
	
	/**
	 * Do erase all.
	 *
	 * @param oCollection the o collection
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	protected abstract boolean doEraseAll(Collection oCollection) throws Exception;
	
	/**
	 * Do store.
	 *
	 * @param oKey the o key
	 * @param oValue the o value
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	protected abstract boolean doStore(Object oKey, Object oValue) throws Exception;
	
	/**
	 * Do store all.
	 *
	 * @param oMap the o map
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	protected abstract boolean doStoreAll(Map oMap) throws Exception;

	/* (non-Javadoc)
	 * @see com.tangosol.net.cache.CacheLoader#load(java.lang.Object)
	 */
	@Override
	public Object load(Object oKey) {
		Object cachedObj = null;
		try {
			cachedObj = doLoad(oKey);
		} catch (DBAccessDisabledException ex) {
			log.info("--ESDG-- DB access disabled for retrieving sales data. key = " + oKey);
			return null;
		} catch (Exception ex) {
			log.error("--ESDG-- exception when retrieving sales data from DB. key = " + oKey, ex);
			return null;
		}
		
		if ( log.isDebugEnabled() ) log.debug("--ESDG-- retrieve sales data from DB. key = " + oKey + (cachedObj == null ? " : null" : ""));
		return cachedObj;
	}

	/* (non-Javadoc)
	 * @see com.tangosol.net.cache.CacheLoader#loadAll(java.util.Collection)
	 */
	@Override
	public Map loadAll(Collection oCollection) {
		Map cachedMap = null;
		
		StringBuffer sbKeys = new StringBuffer();
		for( Iterator i = oCollection.iterator(); i.hasNext(); ) {
			sbKeys.append(i.next()).append(", ");			
		}

		try {
			cachedMap = doLoadAll(oCollection);
		} catch (DBAccessDisabledException ex) {
			log.info("--ESDG-- DB access disabled for retrieving all sales data. size = " + oCollection.size() + " key = " + sbKeys.toString());
			return new HashMap();
		} catch (Exception ex) {
			log.error("--ESDG-- exception when retrieving all sales data from DB. size = " + oCollection.size() + " key = " + sbKeys.toString(), ex);
			return null;
		}
		
		if ( log.isDebugEnabled() ) {
			log.debug("--ESDG-- retrieve all sales data from DB. size = " + oCollection.size() + " key = " + sbKeys.toString() + (cachedMap == null || cachedMap.size() < 1 ? " : null" : ""));
		}
		return cachedMap;
	}

	/* (non-Javadoc)
	 * @see com.tangosol.net.cache.CacheStore#erase(java.lang.Object)
	 */
	@Override
	public void erase(Object oKey) {
		boolean bResult = true;
		try {
			bResult = doErase(oKey);
		} catch (DBAccessDisabledException ex) {
			log.info("--ESDG-- DB access disabled for erasing sales data. key = " + oKey);
			return;
		} catch (Exception ex) {
			log.error("--ESDG-- exception when erasing sales data from DB. key = " + oKey, ex);
			return;
		}
		if ( log.isDebugEnabled() ) log.debug("--ESDG-- " + (bResult ? "" : "failed to ") + "erase sales data from DB. key = " + oKey);		
	}

	/* (non-Javadoc)
	 * @see com.tangosol.net.cache.CacheStore#eraseAll(java.util.Collection)
	 */
	@Override
	public void eraseAll(Collection oCollection) {
		boolean bResult = true;
		StringBuffer sbKeys = new StringBuffer();
		for( Iterator i = oCollection.iterator(); i.hasNext(); ) {
			sbKeys.append(i.next()).append(", ");			
		}
		try {
			bResult = doEraseAll(oCollection);
		} catch (DBAccessDisabledException ex) {
			log.info("--ESDG-- DB access disabled for erasing sales data. size = " + oCollection.size() + " key = " + sbKeys.toString());
			return;
		} catch (Exception ex) {
			
			log.error("--ESDG-- exception when erasing all sales data from DB. size = " + oCollection.size() + " key = " + sbKeys.toString(), ex);
			return;
		}
		if ( log.isDebugEnabled() ) {
			log.debug("--ESDG-- " + (bResult ? "" : "failed to ") + "erase all sales data from DB. size = " + oCollection.size() + " key = " + sbKeys.toString());
		}
	}

	/* (non-Javadoc)
	 * @see com.tangosol.net.cache.CacheStore#store(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void store(Object oKey, Object oValue) {
		boolean bResult = true;
		try {
			if( oValue instanceof ICacheAware ) {
				ICacheAware cacheAware = (ICacheAware)oValue;
				if( !cacheAware.isWriteToDatabase() ) {
					log.warn("--ESDG-- bypass storing sales data to DB. key = " + oKey);
					return;
				}
			}
			bResult = doStore(oKey, oValue);
		} catch (DBAccessDisabledException ex) {
			log.info("--ESDG-- DB access disabled for storing sales data. key = " + oKey);
			return;
		} catch (Exception ex) {
			log.error("--ESDG-- exception when storing sales data from DB. key = " + oKey, ex);
			return;
		}
		if ( log.isDebugEnabled() ) log.debug("--ESDG-- " + (bResult ? "" : "failed to ") + "store sales data to DB. key = " + oKey);
	}

	/* (non-Javadoc)
	 * @see com.tangosol.net.cache.CacheStore#storeAll(java.util.Map)
	 */
	@Override
	public void storeAll(Map oMap) {
		boolean bResult = true;
		HashMap newMap = new HashMap();
		StringBuffer sbKeys = new StringBuffer();
		for( Iterator i = newMap.keySet().iterator(); i.hasNext(); ) {
			sbKeys.append(i.next()).append(", ");			
		}
		try {
			for( Iterator i = oMap.keySet().iterator(); i.hasNext(); ) {
				Object oKey = i.next();
				Object oValue = oMap.get(oKey);
				boolean writeToDatabase = true;
				if( oValue instanceof ICacheAware ) {
					ICacheAware cacheAware = (ICacheAware)oValue;
					if( !cacheAware.isWriteToDatabase() ) {
						writeToDatabase = false;
						log.warn("--ESDG-- bypass storing sales data to DB. key = " + oKey);
					} 
				}
				if (writeToDatabase) {
					newMap.put(oKey, oValue);
				}
			}
			if( newMap.size() > 0 ) {
				bResult = doStoreAll(newMap);
			}
		} catch (DBAccessDisabledException ex) {
			log.info("--ESDG-- DB access disabled for storing all sales data. size = " + newMap.size() + " key = " + sbKeys.toString());
			return;
		} catch (Exception ex) {
			
			log.error("--ESDG-- exception when storing all sales data to DB. size = " + newMap.size() + " key = " + sbKeys.toString(), ex);
			return;
		}
		if ( log.isDebugEnabled() ) {
			log.debug("--ESDG-- " + (bResult ? "" : "failed to ") + "store all sales data to DB. size = " + newMap.size() + " key = " + sbKeys.toString());
		}
	}

}
