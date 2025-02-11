/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.telus.channelsalesmgmt.common.aspect.profiling.WebSessionUsageStatistics.RequestBySessionStatistics;

// TODO: Auto-generated Javadoc
/**
 * The Class AggregatedWebSessionUsageStatistics.
 */
public class AggregatedWebSessionUsageStatistics {
	
	/** The session count. */
	private long sessionCount = 0;
	
	/** The request execution statistics. */
	private Map<String, WebRequestUsageStatistics> requestExecutionStatistics = Collections.synchronizedMap(new HashMap<String, WebRequestUsageStatistics>());
	
	/** The request idle statistics. */
	private Map<String, WebRequestUsageStatistics> requestIdleStatistics = Collections.synchronizedMap(new HashMap<String, WebRequestUsageStatistics>());

	/**
	 * Gets the request execution statistics.
	 *
	 * @return the request execution statistics
	 */
	public Map<String, WebRequestUsageStatistics> getRequestExecutionStatistics() {
		return requestExecutionStatistics;
	}

	/**
	 * Gets the request idle statistics.
	 *
	 * @return the request idle statistics
	 */
	public Map<String, WebRequestUsageStatistics> getRequestIdleStatistics() {
		return requestIdleStatistics;
	}
	
	/**
	 * Gets the session count.
	 *
	 * @return the session count
	 */
	public long getSessionCount() {
		return sessionCount;
	}

	/**
	 * Adds the request statistics.
	 *
	 * @param webSessionUsageStatistics the web session usage statistics
	 */
	public void addRequestStatistics(WebSessionUsageStatistics webSessionUsageStatistics) {
		addRequestBySessionUsageStatistics(requestExecutionStatistics, webSessionUsageStatistics.getExecutionStatisticsByRequestUriMap());
		addRequestBySessionUsageStatistics(requestIdleStatistics, webSessionUsageStatistics.getIdleStatisticsByRequestUriMap());
		sessionCount += 1;
	}
	
	/**
	 * Adds the request by session usage statistics.
	 *
	 * @param requestStatistics the request statistics
	 * @param statisticsByRequestUriMap the statistics by request uri map
	 */
	private void addRequestBySessionUsageStatistics(Map<String, WebRequestUsageStatistics> requestStatistics, Map<String, RequestBySessionStatistics> statisticsByRequestUriMap) {
		if( statisticsByRequestUriMap == null || statisticsByRequestUriMap.size() < 1 ) return;
		
		for( Iterator<String> i = statisticsByRequestUriMap.keySet().iterator(); i.hasNext(); ) {
			String requestUri = i.next();
			
			WebRequestUsageStatistics webRequestUsageStatistics = requestStatistics.get(requestUri);
			if( webRequestUsageStatistics == null ) {
				webRequestUsageStatistics = new WebRequestUsageStatistics();
				requestStatistics.put(requestUri, webRequestUsageStatistics);
			}
			
			webRequestUsageStatistics.addRequestStatistics(statisticsByRequestUriMap.get(requestUri));
		}
	}

	/**
	 * Adds the request statistics.
	 *
	 * @param aggregatedWebSessionUsageStatistics the aggregated web session usage statistics
	 */
	public void addRequestStatistics(AggregatedWebSessionUsageStatistics aggregatedWebSessionUsageStatistics) {
		addRequestByRequestUsageStatistics(requestExecutionStatistics, aggregatedWebSessionUsageStatistics.getRequestExecutionStatistics());
		addRequestByRequestUsageStatistics(requestIdleStatistics, aggregatedWebSessionUsageStatistics.getRequestIdleStatistics());
		sessionCount += aggregatedWebSessionUsageStatistics.getSessionCount();
	}

	/**
	 * Adds the request by request usage statistics.
	 *
	 * @param requestStatisticsTarget the request statistics target
	 * @param requestStatisticsContrib the request statistics contrib
	 */
	private void addRequestByRequestUsageStatistics(Map<String, WebRequestUsageStatistics> requestStatisticsTarget, Map<String, WebRequestUsageStatistics> requestStatisticsContrib) {
		if( requestStatisticsContrib == null || requestStatisticsContrib.size() < 1 ) return;
		
		for( Iterator<String> i = requestStatisticsContrib.keySet().iterator(); i.hasNext(); ) {
			String requestUri = i.next();
			
			WebRequestUsageStatistics webRequestUsageStatistics = requestStatisticsTarget.get(requestUri);
			if( webRequestUsageStatistics == null ) {
				webRequestUsageStatistics = new WebRequestUsageStatistics();
				requestStatisticsTarget.put(requestUri, webRequestUsageStatistics);
			}
			
			webRequestUsageStatistics.addRequestStatistics(requestStatisticsContrib.get(requestUri));
		}
	}
}
