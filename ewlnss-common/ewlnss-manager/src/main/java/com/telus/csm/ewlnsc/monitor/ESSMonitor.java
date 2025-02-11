package com.telus.csm.ewlnsc.monitor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.CacheService;
import com.tangosol.net.Service;
import com.telus.csm.ewlnsc.grid.loader.WirelineLoader;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.framework.config.ConfigContext;

import commonj.work.Work;
import commonj.work.WorkException;

public class ESSMonitor {
	
	private static final String MONITOR_ROOT = "EWLNSC_MONITOR_ROOT";

	private static Logger logger = Logger.getLogger(ESSMonitor.class);

	private static ICacheAdapter sessionCache = CacheAdapterFactory.getSessionCacheAdapter();
	
	private ESSMonitor() {
		super();
	}

	public static void postStartup(Date startdate) {
		
		String server = "?";
		
		String t = System.getProperty("weblogic.Name");
		if (t != null) {
			server = t;
		}
		
		String machine;
		
		try {
			machine = InetAddress.getLocalHost().toString();
		} catch( UnknownHostException e1 ) {
			machine = "?";
			logger.error("Error running InetAddress.getLocalHost().toString()", e1);
		}

		ArrayList<String> msgs = new ArrayList<String>();

		long ts = startdate.getTime();
		
		String msg = new Timestamp(ts) + " " + ConfigContext.getProperty("fw_appName")
		+ " " + ConfigContext.getApplicationVersion() + " " + ConfigContext.getProperty("fw_buildLabel")
		+ " - Started on server " + server + " machine " + machine;
		
		msgs.add(msg);
		
		postAlert("startUp", ts, msgs);
		
	}
	

	@SuppressWarnings("unchecked")
	public static void postAlert(String op, long ts, List<String> msg) {

		MonitorAlert alert = new MonitorAlert();
		alert.setTimeStamp(ts);
		alert.setMessage(msg);
		
		String server;

		try {
			server = InetAddress.getLocalHost().toString();
		} catch( UnknownHostException e1 ) {
			server = "";
			logger.error("Error running InetAddress.getLocalHost().toString()", e1);

		}
		
		String alertKey = "EWLNSC_MONITOR_ALERT_" + server + alert.getTimeStamp();

		//critical section - begin
		// CACHE.get CACHE.put should be exclusively locked but this is just for logging so not much impact if alert is lost
		String operationKey = "EWLNSC_MONITOR_OP_" + op;
		MonitorOperation monitorOperation = (MonitorOperation) sessionCache.get(operationKey);
		
		if (monitorOperation == null) {
			monitorOperation = new MonitorOperation();
			monitorOperation.setName(op);
		}
		
		String nextKey = monitorOperation.getLastAlertKey();
		monitorOperation.setLastAlertKey(alertKey);
		sessionCache.put(operationKey, monitorOperation);
		//critical section - end
		
		alert.setNextAlertKey(nextKey);	
		sessionCache.put(alertKey, alert);
		
		HashMap<String, String> root = (HashMap<String, String>) sessionCache.get(MONITOR_ROOT);
		if (root == null) {
			root = new HashMap<String, String>();
		}
		
		if (root.get(operationKey) == null) {
			root.put(op, operationKey);
			sessionCache.put(MONITOR_ROOT, root);
		}

	}
	
	private static String getAlertSummary() {
		StringBuilder result = new StringBuilder();
		
		for (MonitorOperation monitorOperation : getAllMonitorOperations()) {
			result.append(monitorOperation.getSummaryPrintString()); 
		}
		
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getOperationAlerts(String op, Integer count) {
		
		if (op == null) {
			return getAlertSummary();
		}
		
		int maxCount = 50;
		if (count != null) {
			maxCount = count;
		}
		
		HashMap<String, String> root = (HashMap<String, String>) sessionCache.get(MONITOR_ROOT);
		if (root != null) {
			String opCacheKey = root.get(op);
			if (opCacheKey != null) {
				MonitorOperation monitorOperation  = (MonitorOperation)sessionCache.get(opCacheKey);
				if (monitorOperation != null) {
					return monitorOperation.getDetailPrintString(maxCount);
				}
			}
		}
		
		return "";

	}

	private static class MonitorAlert implements Serializable {

		private static final long serialVersionUID = 6328150855435635090L;

		private String nextAlertKey;
		
		private long timeStamp;
		private List<String> message;

		public long getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}

		public void setMessage(List<String> msg) {
			this.message = msg;
		}

		public List<String> getMessage() {
			return message;
		}

		public String getNextAlertKey() {
			return nextAlertKey;
		}

		public void setNextAlertKey(String nextAlert) {
			this.nextAlertKey = nextAlert;
		}

		public String toPrintString() {
			StringBuilder result = new StringBuilder();
			for (String m : message) {
				result.append(m +"\n");
			}
			
			return result.toString();
		}
		
	}

	private static class MonitorOperation implements Comparable<MonitorOperation>, Serializable {
		
		private static final long serialVersionUID = 8802059841901034553L;
		
		private String name;
		private String lastAlertKey;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLastAlertKey() {
			return lastAlertKey;
		}

		public void setLastAlertKey(String lastAlertKey) {
			this.lastAlertKey = lastAlertKey;
		}
		
		private MonitorAlert getLastAlert() {
			if (lastAlertKey == null) {
				return null;
			}
			return (MonitorAlert)sessionCache.get(lastAlertKey);
			
		}

		private String getSummaryPrintString() {
			String result = "* " + getName();
			
			MonitorAlert alert = getLastAlert();
			if (alert != null) {
				result += " - Last Alert: ";
				result += "\n" + alert.toPrintString() + "\n";
			}

			return result;
		}
		
		private String getDetailPrintString(int maxCount) {
			
			StringBuilder result = new StringBuilder();
			
			ArrayList<MonitorAlert> alerts = getAllAlerts(maxCount);
			
			result.append("* " + getName() + " - Last " + alerts.size() + " Alert:\n");
			
			for (MonitorAlert monitorAlert : alerts) {
				result.append(monitorAlert.toPrintString());
			}
			return result.toString();
		}
		
		private ArrayList<MonitorAlert> getAllAlerts(int maxCount) {
			ArrayList<MonitorAlert> result = new ArrayList<MonitorAlert>();
			String nextKey = lastAlertKey;
			while (nextKey != null && result.size() < maxCount) {
				MonitorAlert alert  = (MonitorAlert)sessionCache.get(nextKey);
				if (alert != null) {
					result.add(alert);
					nextKey = alert.getNextAlertKey();
				} else {
					nextKey = null;
				}
			}
			
			return result;
		}
		
		@Override
		public int compareTo(MonitorOperation param) {
			
			Long thisTS = (long) 0;
			MonitorAlert thisMonitorAlert = this.getLastAlert();
			if (thisMonitorAlert != null) {
				thisTS = thisMonitorAlert.getTimeStamp();
			}
			
			Long parmTS = (long) 0;
			MonitorAlert paramMonitorAlert = param.getLastAlert();
			if (paramMonitorAlert != null) {
				parmTS = paramMonitorAlert.getTimeStamp();
			}
			
			return thisTS.compareTo(parmTS);

		}
		
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList<MonitorOperation> getAllMonitorOperations() {
		ArrayList<MonitorOperation> result = new ArrayList<MonitorOperation>();
		
		HashMap<String, String> root = (HashMap<String, String>) sessionCache.get(MONITOR_ROOT);
		if (root != null) {
			Collection<String> opCacheKeys = root.values();
			for (String opCacheKey : opCacheKeys) {
				MonitorOperation monitorOperation  = (MonitorOperation)sessionCache.get(opCacheKey);
				if (monitorOperation != null) {
					result.add(monitorOperation);
				}
			}
		}
		
		Collections.sort(result);

		
		return result;

	}

	public static String unloadCache(String cacheName, String key) {
		ICacheAdapter eSSCacheAdapter = getCacheAdapter(cacheName);
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<String[]> entryList = new ArrayList<String[]>();

		if (!StringUtils.isBlank(key)) {
			Object obj = eSSCacheAdapter.get(key);
			if (obj != null) {
				String objJSONString;
				try {
					objJSONString = mapper.writeValueAsString(obj);
				} catch (JsonGenerationException e) {
					return e.toString();
				} catch (JsonMappingException e) {
					return e.toString();
				} catch (IOException e) {
					return e.toString();
				}
				String className = obj.getClass().getName();
				entryList.add(new String[]{key, className, objJSONString});
			}
		} else {
			Set<Entry<?, ?>> entrySet = eSSCacheAdapter.getAllFromCache();

			for (Entry<?, ?> entry : entrySet) {
				String entryKey = (String) entry.getKey();
				Object obj = entry.getValue();
				String objJSONString;
				try {
					objJSONString = mapper.writeValueAsString(obj);
				} catch (JsonGenerationException e) {
					return e.toString();
				} catch (JsonMappingException e) {
					return e.toString();
				} catch (IOException e) {
					return e.toString();
				}
				entryList.add(new String[]{entryKey, obj.getClass().getName(), objJSONString});
			}	
		}

		try {
			String result = mapper.writeValueAsString(entryList);
			return result;
		} catch (JsonGenerationException e) {
			return e.toString();
		} catch (JsonMappingException e) {
			return e.toString();
		} catch (IOException e) {
			return e.toString();
		}

	}

	public static String loadCache(String cacheName, InputStream inputStream) {
		
		//disable modifying cache entry
		if (!ApplicationProperties.isCacheMonitorEnabled()) {
			return "Loading cache is NOT ALLOWED!";
		}
		
		ICacheAdapter eSSCacheAdapter = getCacheAdapter(cacheName);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		ArrayList<List<String>> inputList;

		try {
			inputList = mapper.readValue(inputStream, ArrayList.class);
		} catch (JsonParseException e) {
			return e.toString();
		} catch (JsonMappingException e) {
			return e.toString();
		} catch (IOException e) {
			return e.toString();
		}
	
		HashMap<String, Object> loadMap = new HashMap<String, Object>();
		
		for (List<String> entry : inputList) {
			String key = entry.get(0);
			String type = entry.get(1);
			String objJSONString = entry.get(2);
			//remove enclosing " 
			if (!"java.lang.String".equals(type) && objJSONString.startsWith("\"") && objJSONString.endsWith("\"")) {
				objJSONString = objJSONString.substring(1, objJSONString.length());
			}
			
			Object value;
			try {
				value = mapper.readValue(objJSONString, Class.forName(type));
			} catch (JsonParseException e) {
				return e.toString();
			} catch (JsonMappingException e) {
				return e.toString();
			} catch (IOException e) {
				return e.toString();
			} catch (ClassNotFoundException e) {
				return e.toString();
			}
			loadMap.put(key, value);
		}
		
		eSSCacheAdapter.saveAllToCache(loadMap);
		
		StringBuilder result = new StringBuilder("Loaded " + loadMap.size() + " records to cache " + cacheName + " Keys:");
		
		for (String key : loadMap.keySet()) {
			result.append("\n" + key);
		}
		
		return result.toString();
	}
	
	public static String getCacheNames(String prefix) {

		StringBuilder result = new StringBuilder();

		Enumeration<?> serviceNames = CacheFactory.getCluster().getServiceNames();
		while (serviceNames.hasMoreElements()) {
			String serviceName = (String) serviceNames.nextElement();
			Service service;
			try {
				service = CacheFactory.getService(serviceName);
			} catch (Exception e) {
				continue;
			}
			if (service instanceof CacheService) {
				CacheService cacheService = (CacheService) service;
				Enumeration<?> cacheNames = cacheService.getCacheNames();
				while (cacheNames.hasMoreElements()) {
					String cacheName = (String) cacheNames.nextElement();
					if (StringUtils.isBlank(prefix) || cacheName.startsWith(prefix)) {
						result.append("\n" + cacheName);
					}
				}
			}
		}

		result.append("\n");

		return result.toString();
	}

	public static String getCacheKeys(String cacheName, String prefix) {
		
		ICacheAdapter eSSCacheAdapter = getCacheAdapter(cacheName);
		
		if (CacheAdapterFactory.SESSION_CACHE_NAME.equals(eSSCacheAdapter.getCacheName())
				&& 	!ApplicationProperties.isCacheMonitorEnabled() ) {
			return "Listing session cache keys has been disabled";
		}

		StringBuilder result  = new StringBuilder();
		
		Set<?> keys = eSSCacheAdapter.getAllKeysFromCache();
		ArrayList<String> keyList = new ArrayList<String>();
		for (Object key : keys) {
			if (prefix == null || key.toString().startsWith(prefix)) {
				keyList.add(key.toString());
			}
		}

		result.append(eSSCacheAdapter.getCacheName() + " has " + keyList.size() + " cache keys");
		
		if (prefix != null) {
			result.append(" with prefix=" + prefix);
		}
		
		result.append("\n");
		
		Collections.sort(keyList);
		
		for (String key : keyList) {
			result.append("\n" + key);
		}
		
		result.append("\n");

		return result.toString();
	}

	public static String getCacheObject(String cacheName, String key) {

		if (!ApplicationProperties.isCacheMonitorEnabled()) {
			return "Getting Cache Object is disabled";
		}
		
		ICacheAdapter eSSCacheAdapter = getCacheAdapter(cacheName); 
		
		String result = eSSCacheAdapter.getCacheName() + " key=" + key + "\n\n";

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL);
		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));

		Object obj = eSSCacheAdapter.get(key);
		
		if (obj == null) {
			return result + "Object not found";
		}
		
		if (!(obj instanceof Serializable)) {
			return obj.toString();
		}
			
		result += obj.getClass().getName() + "\n";
		try {
			result += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			return result;
		} catch (IOException e) {
			logger.info("Error in building JSON string", e);
			return result + "Error building JSON string: " + e.toString();
		}
		
	}

	public static String searchCacheObject(String cacheName, String searchString) {

		ICacheAdapter eSSCacheAdapter = getCacheAdapter(cacheName);
		
		if (CacheAdapterFactory.SESSION_CACHE_NAME.equals(eSSCacheAdapter.getCacheName())
				&& 	!ApplicationProperties.isCacheMonitorEnabled() ) {
			return "Listing session cache keys has been disabled";
		}

		StringBuilder result  = new StringBuilder();
		
		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL);
		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));

		Set<?> keys = eSSCacheAdapter.getAllKeysFromCache();

		int ct = 0;
		for (Object key : keys) {
			Object obj = eSSCacheAdapter.get((Serializable) key);
			
			String objString = null;
			try {
				objString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			} catch (Exception e) {
			} 
			
			if (objString != null && objString.contains(searchString)) {
				ct++;
				result.append("\n row=" + ct + " key=" + key + " record=" + objString);
			}
		}
		
		result.append("\n");

		return result.toString();

		
	}

	private static ICacheAdapter getCacheAdapter(String cacheName) {
		
		if (StringUtils.isBlank(cacheName)) {
			return CacheAdapterFactory.getSessionCacheAdapter();
		}

		return CacheAdapterFactory.getCacheAdapter(cacheName);
	}


	public static String deleteCacheObject(String cacheName, String key) {
		
		//disable modifying cache entry
		if (!ApplicationProperties.isCacheMonitorEnabled()) {
			return "Deleting cache entry is NOT ALLOWED!";
		}

		ICacheAdapter eSSCacheAdapter = getCacheAdapter(cacheName); 

		String result = eSSCacheAdapter.getCacheName() + " key=" + key + "\n\n";
		
		if (key == null) {
			String keys = getCacheKeys(cacheName, null);
			eSSCacheAdapter.removeAll();
			result += "Deleted from cache " + cacheName + " keys:\n" + keys;
			return result;
		}
		
		if (eSSCacheAdapter.removeByKey(key)) {
			return result + "Object deleted";
		} else {
			return result + "Object not found";
		}
	}

	public static String getAdapterList(boolean ping, List<String> adapterNames) {

		List<IAdapterBase> adaptors = new ArrayList<IAdapterBase>();
		
		for (String adapterName : adapterNames) {					
			if ("*".equals(adapterName)) {
				adaptors = AdapterFactory.getAllAdapters();
				break;
			} else {
				try {
					adaptors.add(AdapterFactory.getAdapter(adapterName));
				} catch (NoClassDefFoundError e) {
					logger.info(e.getMessage());
					return "Adapter not found: " + adapterName;
				}
			}
		}
		
		//support adapter-list without pinging
		if (!ping) {
			StringBuilder result = new StringBuilder();
			int adapterCount = 0;
			for (IAdapterBase adapter : adaptors) {
				result.append("\n[" + ++adapterCount +"] *" + adapter.getClass().getInterfaces()[0].getSimpleName() + ":" + "\n" + adapter.getEndpointAddress() + "\n");
			}
			return result.toString();
		}
		
		List<Work> inputWorkTaskList = new ArrayList<Work>(); 
		Collection<Work> outputWorkTaskList = null;
		ComponentMonitorTask componentMonitorTask;
		for (IAdapterBase adaptor : adaptors) {				
			componentMonitorTask = new ComponentMonitorTask(adaptor);
			inputWorkTaskList.add(componentMonitorTask);
			logger.debug("pingAdapters" + " Pinging component: " + componentMonitorTask.getName());
		}	

		StringBuilder response = new StringBuilder();
		response.append("\nPinging " + inputWorkTaskList.size() + " services...\n");

		try {
			ICommonJWorkManager workManager = WorkManagerFactory.getCommonJWorkManager();
			outputWorkTaskList = workManager.processTasks(inputWorkTaskList);
		} catch (NamingException e) {
			logger.error("Naming exception in Pinging component in executing workManager", e);
			return "Naming exception in Pinging component in executing workManager";
		} catch (WorkException e) {
			logger.error("Workexception in Pinging component in executing workManager", e);
			return "Workexception in Pinging component in executing workManager";
		} catch (InterruptedException e) {
			logger.error("interrupted Exception in Pinging component in executing workManager", e);
			Thread.currentThread().interrupt();
		}
		
		int adapterCount = 0;

		if (outputWorkTaskList != null) {
			for(Work workTask : outputWorkTaskList){
				if(workTask instanceof ComponentMonitorTask){
					response.append("\n[" + ++adapterCount +"] " + ((ComponentMonitorTask) workTask).getResult(true) + "\n");
				}
			}
		}

		return response.toString();
	}    		

	public static String runWirelineLoader(String dir) {
		
		if (!ApplicationProperties.isCacheMonitorEnabled()) {
			return "Updating cache entry is NOT ALLOWED!";
		}
		
		if (!new File(dir).isDirectory()) {
			return "dir not found: " + dir;
		}
		
		WirelineLoader wirelineLoader = new WirelineLoader(dir, true, 500, 10);
		try {
			int result = wirelineLoader.execute();
			return "WirelineLoader.execute() Return Code=" + result;
		} catch (Throwable e) {
			return e.toString();
		}
	}
}
