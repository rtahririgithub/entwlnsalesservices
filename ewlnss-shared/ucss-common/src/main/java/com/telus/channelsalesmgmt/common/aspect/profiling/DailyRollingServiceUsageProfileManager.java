/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatistics;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatisticsManager;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatistics.MethodExecutionStatistics;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyRollingServiceUsageProfileManager.
 */
public class DailyRollingServiceUsageProfileManager {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	static Logger logger = Logger.getLogger( DailyRollingServiceUsageProfileManager.class );
	
	/**
	 * The Class Factory.
	 */
	public static final class Factory {
		
		/** The daily rolling service usage profile manager. */
		private static DailyRollingServiceUsageProfileManager dailyRollingServiceUsageProfileManager = null;

		/**
		 * Gets the single instance of Factory.
		 *
		 * @return single instance of Factory
		 * @throws Exception the exception
		 */
		public static synchronized DailyRollingServiceUsageProfileManager getInstance() throws Exception {
			if( dailyRollingServiceUsageProfileManager == null ) {
				dailyRollingServiceUsageProfileManager = new DailyRollingServiceUsageProfileManager();
			}
			
			return dailyRollingServiceUsageProfileManager;
	    }
		
		/**
		 * Instantiates a new factory.
		 */
		private Factory() {
		} // No instance of this class allowed
	}
	
	/**
	 * Instantiates a new daily rolling service usage profile manager.
	 */
	private DailyRollingServiceUsageProfileManager() {}
	
	/** The daily statistics. */
	private Map<String, ServiceUsageStatistics> dailyStatistics = new TreeMap<String, ServiceUsageStatistics>();

	/** The daily avg execution time millis map. */
	private Map<Integer, Map<String, Map<String, Long[]>>> dailyAvgExecutionTimeMillisMap = new TreeMap<Integer, Map<String, Map<String, Long[]>>>();
	
	/** The daily statistics history list. */
	private LinkedList<DailyStatisticsHistory> dailyStatisticsHistoryList = new LinkedList<DailyStatisticsHistory>();
	
	/**
	 * The Class DailyStatisticsHistory.
	 */
	public class DailyStatisticsHistory {		
		
		/** The observation start date. */
		private Date observationStartDate;
		
		/** The daily statistics. */
		private Map<String, ServiceUsageStatistics> dailyStatistics;
		
		/**
		 * Instantiates a new daily statistics history.
		 *
		 * @param observationStartDate the observation start date
		 * @param dailyStatistics the daily statistics
		 */
		public DailyStatisticsHistory(Date observationStartDate,Map<String, ServiceUsageStatistics> dailyStatistics) {
			this.observationStartDate = observationStartDate;
			this.dailyStatistics = dailyStatistics;
		}
		
		/**
		 * Gets the observation start date.
		 *
		 * @return the observation start date
		 */
		public Date getObservationStartDate() {
			return observationStartDate;
		}

		/**
		 * Gets the daily statistics.
		 *
		 * @return the daily statistics
		 */
		public Map<String, ServiceUsageStatistics> getDailyStatistics() {
			return dailyStatistics;
		}
	}
	
	/** The observation start date. */
	private Date observationStartDate = new Date();
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.manager.DailyRollingServiceUsageProfileManager#getDailyStatistics()
	 */
	/**
	 * Gets the daily statistics.
	 *
	 * @return the daily statistics
	 * @throws Exception the exception
	 */
	public Map<String, ServiceUsageStatistics> getDailyStatistics() throws Exception {
		Map<String, ServiceUsageStatistics> currentDailyStatistics = new TreeMap<String, ServiceUsageStatistics>();
		
		//add current stats
		ServiceUsageStatisticsManager mgr = ServiceUsageStatisticsManager.Factory.getInstance();
		for( Iterator<ServiceUsageStatistics> i = mgr.getServiceUsageStatisticsSet().iterator(); i.hasNext(); ) {
			addStatistics(currentDailyStatistics, i.next());
		}
		//add daily stats
		for( Iterator<ServiceUsageStatistics> i = dailyStatistics.values().iterator(); i.hasNext(); ) {
			addStatistics(currentDailyStatistics, i.next());
		}
		
		return currentDailyStatistics;
	}
	
	/**
	 * Gets the daily statistics.
	 *
	 * @param daysBackForHistory the days back for history
	 * @return the daily statistics
	 * @throws Exception the exception
	 */
	public DailyStatisticsHistory getDailyStatistics(String daysBackForHistory) throws Exception {
		int index = 0;
		try {
			index = Integer.parseInt(daysBackForHistory) - 1;
		} catch (Exception ex) {
			return null;
		}
		if( index < 0 || index >= dailyStatisticsHistoryList.size() ) {
			return null;
		}
		return dailyStatisticsHistoryList.get(index);
	}
	
	/**
	 * Gets the daily avg execution time millis map.
	 *
	 * @return the daily avg execution time millis map
	 * @throws Exception the exception
	 */
	public Map<Integer, Map<String, Map<String, Long[]>>> getDailyAvgExecutionTimeMillisMap() throws Exception {
		Calendar now = Calendar.getInstance();
		Integer hour = now.get(Calendar.HOUR_OF_DAY);
		Map<String, Map<String, Long[]>> hourlyServiceAvgExecutionTimeMillisMap = dailyAvgExecutionTimeMillisMap.get(hour);
		if( hourlyServiceAvgExecutionTimeMillisMap == null ) {
			hourlyServiceAvgExecutionTimeMillisMap = new TreeMap<String, Map<String, Long[]>>();
			dailyAvgExecutionTimeMillisMap.put(hour, hourlyServiceAvgExecutionTimeMillisMap);
		}
			
		ServiceUsageStatisticsManager mgr = ServiceUsageStatisticsManager.Factory.getInstance();
		for( Iterator<ServiceUsageStatistics> i = mgr.getServiceUsageStatisticsSet().iterator(); i.hasNext(); ) {
			overwriteAvgExecutionTimeMap(hourlyServiceAvgExecutionTimeMillisMap, i.next());
		}
		
		return dailyAvgExecutionTimeMillisMap;
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.manager.DailyRollingServiceUsageProfileManager#resetDailyStatistics()
	 */
	/**
	 * Reset daily statistics.
	 */
	public synchronized void resetDailyStatistics() {	
		Map<String, ServiceUsageStatistics> savedDailyStatistics = new TreeMap<String, ServiceUsageStatistics>();
		
		for (Iterator<ServiceUsageStatistics> i = dailyStatistics.values().iterator(); i.hasNext(); ) {	
			ServiceUsageStatistics stats = i.next();
			savedDailyStatistics.put(stats.getServiceClassName(), stats.clone());
			
			if (stats != null) {
				stats.reset();
			}
		}
		
		if( dailyStatisticsHistoryList.size() > 4 ) {
			dailyStatisticsHistoryList.removeLast();
		}
		DailyStatisticsHistory dailyStatisticsHistory = new DailyStatisticsHistory(observationStartDate, savedDailyStatistics);
		dailyStatisticsHistoryList.addFirst(dailyStatisticsHistory);
		
		observationStartDate = new Date();
	}
	
	/**
	 * Gets the observation start date.
	 *
	 * @return the observation start date
	 */
	public Date getObservationStartDate() {
		return observationStartDate;
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.manager.DailyRollingServiceUsageProfileManager#takeHourlyProfileSnapshot()
	 */
	/**
	 * Take hourly profile snapshot.
	 */
	public void takeHourlyProfileSnapshot() {
		try {
			Calendar now = Calendar.getInstance();
			Integer hour = now.get(Calendar.HOUR_OF_DAY);
			Map<String, Map<String, Long[]>> hourlyServiceAvgExecutionTimeMillisMap = dailyAvgExecutionTimeMillisMap.get(hour);
			if( hourlyServiceAvgExecutionTimeMillisMap == null ) {
				hourlyServiceAvgExecutionTimeMillisMap = new TreeMap<String, Map<String, Long[]>>();
				dailyAvgExecutionTimeMillisMap.put(hour, hourlyServiceAvgExecutionTimeMillisMap);
			}
			
			ServiceUsageStatisticsManager mgr = ServiceUsageStatisticsManager.Factory.getInstance();
			for( Iterator<ServiceUsageStatistics> i = mgr.getServiceUsageStatisticsSet().iterator(); i.hasNext(); ) {
				ServiceUsageStatistics lastServiceUsageStatistics = i.next();
				addStatistics(dailyStatistics, lastServiceUsageStatistics);
				overwriteAvgExecutionTimeMap(hourlyServiceAvgExecutionTimeMillisMap, lastServiceUsageStatistics);
				mgr.resetServiceUsageStatistics(lastServiceUsageStatistics.getServiceClassName());
			}
			mgr.resetServiceUsageStatisticsStartDate();
		} catch (Exception ex) {
			logger.error("Exception caught when adding up hourly usage snapshot.", ex);
		}
	}
	
	/**
	 * Save avg execution time.
	 *
	 * @param serviceAvgExecutionTimeMillisMap the service avg execution time millis map
	 * @param serviceClassName the service class name
	 * @param lastServiceUsageStatistics the last service usage statistics
	 */
	private void overwriteAvgExecutionTimeMap(Map<String, Map<String, Long[]>> serviceAvgExecutionTimeMillisMap, ServiceUsageStatistics lastServiceUsageStatistics) {
		if( lastServiceUsageStatistics == null ) return;
		
		Map<String, Long[]> serviceAvgExecutionTimeMap = serviceAvgExecutionTimeMillisMap.get(lastServiceUsageStatistics.getServiceClassName());
		if( serviceAvgExecutionTimeMap == null ) {
			serviceAvgExecutionTimeMap = new TreeMap<String, Long[]>();
			serviceAvgExecutionTimeMillisMap.put(lastServiceUsageStatistics.getServiceClassName(), serviceAvgExecutionTimeMap);
			
		}
		
		Map<String, MethodExecutionStatistics> newStats = lastServiceUsageStatistics.getServiceUsageStatistics();
		for( Iterator<String> i = newStats.keySet().iterator(); i.hasNext(); ) {
			String methodName = i.next();
			MethodExecutionStatistics lastMethodExecutionStatistics = newStats.get(methodName);
			
			serviceAvgExecutionTimeMap.put(methodName, new Long[]{Long.valueOf(lastMethodExecutionStatistics.getInvocationCount()), Long.valueOf(lastMethodExecutionStatistics.getTotalExecutionTimeMillis())});
		}
	}
	
	/**
	 * Adds the statistics.
	 *
	 * @param addedStatistics the added statistics
	 * @param serviceClassName the service class name
	 * @param lastServiceUsageStatistics the last service usage statistics
	 */
	private void addStatistics(Map<String, ServiceUsageStatistics> addedStatistics, ServiceUsageStatistics lastServiceUsageStatistics) {
		if( lastServiceUsageStatistics == null ) return;
				
		ServiceUsageStatistics serviceUsageStatistics = addedStatistics.get(lastServiceUsageStatistics.getServiceClassName());		
		
		if( serviceUsageStatistics == null ) {
			serviceUsageStatistics = new ServiceUsageStatistics(lastServiceUsageStatistics.getServiceClassName());
			addedStatistics.put(lastServiceUsageStatistics.getServiceClassName(), serviceUsageStatistics);		
		} 
		
		Map<String, MethodExecutionStatistics> newStats = lastServiceUsageStatistics.getServiceUsageStatistics();
		Map<String, MethodExecutionStatistics> addedStats = serviceUsageStatistics.getServiceUsageStatistics();
		for( Iterator<String> i = newStats.keySet().iterator(); i.hasNext(); ) {
			String methodName = i.next();
			MethodExecutionStatistics lastMethodExecutionStatistics = newStats.get(methodName);
			MethodExecutionStatistics addedMethodExecutionStatistics = addedStats.get(methodName);
			
			if( addedMethodExecutionStatistics == null ) {
				addedMethodExecutionStatistics = serviceUsageStatistics.new MethodExecutionStatistics(methodName);
				addedStats.put(methodName, addedMethodExecutionStatistics);
			} 
			
			addedMethodExecutionStatistics.addUsage(lastMethodExecutionStatistics);
		}
	}
}
