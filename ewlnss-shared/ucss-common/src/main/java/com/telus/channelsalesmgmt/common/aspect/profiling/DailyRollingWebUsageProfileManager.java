/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyRollingWebUsageProfileManager.
 */
public class DailyRollingWebUsageProfileManager {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	static Logger logger = Logger.getLogger( DailyRollingWebUsageProfileManager.class );
	
	/**
	 * The Class Factory.
	 */
	public static final class Factory {
		
		/** The daily rolling web usage profile manager. */
		private static DailyRollingWebUsageProfileManager dailyRollingWebUsageProfileManager = null;

		/**
		 * Gets the single instance of Factory.
		 *
		 * @return single instance of Factory
		 * @throws Exception the exception
		 */
		public static synchronized DailyRollingWebUsageProfileManager getInstance() throws Exception {
			if( dailyRollingWebUsageProfileManager == null ) {
				dailyRollingWebUsageProfileManager = new DailyRollingWebUsageProfileManager();
			}
			
			return dailyRollingWebUsageProfileManager;
	    }
		
		/**
		 * Instantiates a new factory.
		 */
		private Factory() {
		} // No instance of this class allowed
	}
	
	/**
	 * Instantiates a new daily rolling web usage profile manager.
	 */
	private DailyRollingWebUsageProfileManager() {}
	
	/** The daily aggregated statistics. */
	private Map<Integer, AggregatedWebSessionUsageStatistics> dailyAggregatedStatistics = Collections.synchronizedMap(new HashMap<Integer, AggregatedWebSessionUsageStatistics>());
	
	/** The daily statistics history list. */
	private LinkedList<DailyStatisticsHistory> dailyStatisticsHistoryList = new LinkedList<DailyStatisticsHistory>();
	
	/**
	 * The Class DailyStatisticsHistory.
	 */
	public class DailyStatisticsHistory {		
		
		/** The observation start date. */
		private Date observationStartDate;
		
		/** The daily statistics. */
		AggregatedWebSessionUsageStatistics dailyStatistics;
		
		/**
		 * Instantiates a new daily statistics history.
		 *
		 * @param observationStartDate the observation start date
		 * @param dailyStatistics the daily statistics
		 */
		public DailyStatisticsHistory(Date observationStartDate, AggregatedWebSessionUsageStatistics dailyStatistics) {
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
		public AggregatedWebSessionUsageStatistics getDailyStatistics() {
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
	public AggregatedWebSessionUsageStatistics getDailyStatistics() throws Exception {
		AggregatedWebSessionUsageStatistics currentDailyStatistics = WebUsageStatisticsManager.Factory.getInstance().getActiveRequestStatistics();

		for( Iterator<AggregatedWebSessionUsageStatistics> i = getAggregatedWebSessionUsageStatisticsSet().iterator(); i.hasNext(); ) {
			AggregatedWebSessionUsageStatistics aggregatedWebSessionUsageStatistics = i.next();
			currentDailyStatistics.addRequestStatistics(aggregatedWebSessionUsageStatistics);
		}
		
		return currentDailyStatistics;
	}
	
	/**
	 * Gets the daily statistics by hour.
	 *
	 * @return the daily statistics by hour
	 * @throws Exception the exception
	 */
	public Map<Integer, AggregatedWebSessionUsageStatistics> getDailyStatisticsByHour() throws Exception {
		Map<Integer, AggregatedWebSessionUsageStatistics> dailyStatisticsByHour = new HashMap<Integer, AggregatedWebSessionUsageStatistics>();
		for( Iterator<Integer> i = dailyAggregatedStatistics.keySet().iterator(); i.hasNext(); ) {
			Integer hour = i.next();
			dailyStatisticsByHour.put(hour, dailyAggregatedStatistics.get(hour));
		}
		AggregatedWebSessionUsageStatistics currentDailyStatistics = WebUsageStatisticsManager.Factory.getInstance().getActiveRequestStatistics();
		if( currentDailyStatistics != null ) {
			Integer thisHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			AggregatedWebSessionUsageStatistics currentlySavedDailyStatistics = dailyStatisticsByHour.get(thisHour);
			if( currentlySavedDailyStatistics == null ) {
				dailyStatisticsByHour.put(thisHour, currentDailyStatistics);
			} else {
				currentlySavedDailyStatistics.addRequestStatistics(currentDailyStatistics);
			}
		}
		return dailyStatisticsByHour;
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
	 * Gets the aggregated web session usage statistics set.
	 *
	 * @return the aggregated web session usage statistics set
	 */
	private synchronized Set<AggregatedWebSessionUsageStatistics> getAggregatedWebSessionUsageStatisticsSet() {
		Set<AggregatedWebSessionUsageStatistics> aggregatedWebSessionUsageStatisticsSet = new HashSet<AggregatedWebSessionUsageStatistics>();
		for( Iterator<AggregatedWebSessionUsageStatistics> i = dailyAggregatedStatistics.values().iterator(); i.hasNext(); ) {
			aggregatedWebSessionUsageStatisticsSet.add(i.next());
		}
		return aggregatedWebSessionUsageStatisticsSet;
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
			long now = Calendar.getInstance().getTimeInMillis();
			WebUsageStatisticsManager mgr = WebUsageStatisticsManager.Factory.getInstance();
			for( Iterator<WebSessionUsageStatistics> i = mgr.getWebSessionUsageStatisticsSet().iterator(); i.hasNext(); ) {
				WebSessionUsageStatistics webSessionUsageStatistics = i.next();
				boolean isExpiredSession = false;
				
				//remove invalidated session
				if( webSessionUsageStatistics.getSession() == null || webSessionUsageStatistics.isMarkedForDelete() ) {
					isExpiredSession = true;
				}
				//remove expired session
				else {
					try {
						webSessionUsageStatistics.getSession().getCreationTime();
					} catch( IllegalStateException ex ) {
						isExpiredSession = true;
					}
				}
				//remove orphan session after 1hr
				if( !isExpiredSession &&
					now - webSessionUsageStatistics.getSessionStartDate().getTimeInMillis() > 3600 * 1000 ) {
					isExpiredSession = true;
				}
				
				if( isExpiredSession ) {
					rollupHourlyRequestStatistics(webSessionUsageStatistics);
				
					mgr.removeWebSessionUsageStatistics(webSessionUsageStatistics.getSession().getId());
				}
			}
      mgr.resetWebUsageStatisticsStartDate();
		} catch (Exception ex) {
			logger.error("Exception caught when adding up hourly usage snapshot.", ex);
		}
	}

	/**
	 * Rollup hourly request statistics.
	 *
	 * @param webSessionUsageStatistics the web session usage statistics
	 */
	public synchronized void rollupHourlyRequestStatistics(WebSessionUsageStatistics webSessionUsageStatistics) {
		Integer sessionStartHour = webSessionUsageStatistics.getSessionStartDate().get(Calendar.HOUR_OF_DAY);
		
		AggregatedWebSessionUsageStatistics aggregatedWebSessionUsageStatistics = dailyAggregatedStatistics.get(sessionStartHour);
		if( aggregatedWebSessionUsageStatistics == null ) {
			aggregatedWebSessionUsageStatistics = new AggregatedWebSessionUsageStatistics();
			dailyAggregatedStatistics.put(sessionStartHour, aggregatedWebSessionUsageStatistics);
		}
		
		aggregatedWebSessionUsageStatistics.addRequestStatistics(webSessionUsageStatistics);
	}

	/**
	 * Reset daily statistics.
	 *
	 * @throws Exception the exception
	 */
	public synchronized void resetDailyStatistics() throws Exception {
		AggregatedWebSessionUsageStatistics savedDailyStatistics = getDailyStatistics();
		
		if( dailyStatisticsHistoryList.size() > 4 ) {
			dailyStatisticsHistoryList.removeLast();
		}
		DailyStatisticsHistory dailyStatisticsHistory = new DailyStatisticsHistory(observationStartDate, savedDailyStatistics);
		dailyStatisticsHistoryList.addFirst(dailyStatisticsHistory);
		
		WebUsageStatisticsManager.Factory.getInstance().resetStatistics();
		dailyAggregatedStatistics.clear();
		
		observationStartDate = new Date();
	}
	
}
