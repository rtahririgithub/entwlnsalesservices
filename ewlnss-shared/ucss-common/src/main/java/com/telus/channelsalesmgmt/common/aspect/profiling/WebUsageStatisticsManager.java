/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUsageStatisticsManager.
 */
public class WebUsageStatisticsManager implements HttpSessionListener {
	
	/** The logger. */
	static Logger logger = Logger.getLogger( WebUsageStatisticsManager.class );
	
	/**
	 * The Class Factory.
	 */
	public static final class Factory {
		
		/** The web usage statistics manager. */
		private static WebUsageStatisticsManager webUsageStatisticsManager = null;

		/**
		 * Gets the single instance of Factory.
		 *
		 * @return single instance of Factory
		 * @throws Exception the exception
		 */
		public static synchronized WebUsageStatisticsManager getInstance() throws Exception {
			if( webUsageStatisticsManager == null ) {
				webUsageStatisticsManager = new WebUsageStatisticsManager();
			}
			
			return webUsageStatisticsManager;
	    }
		
		/**
		 * Instantiates a new factory.
		 */
		private Factory() {
		} // No instance of this class allowed
	}
		
	/** The session statistics. */
	private Map<String, WebSessionUsageStatistics> sessionStatistics = Collections.synchronizedMap(new HashMap<String, WebSessionUsageStatistics>());
		
	/** The observation start date. */
	private Date observationStartDate = new Date();
	
	/** The is disabled. */
	private boolean isDisabled = false;
	
	/**
	 * Checks if is disabled.
	 *
	 * @return true, if is disabled
	 */
	public boolean isDisabled() {
		return isDisabled;
	}

	/**
	 * Sets the disabled.
	 *
	 * @param isDisabled the new disabled
	 */
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	/**
	 * Gets the session statistics.
	 *
	 * @return the session statistics
	 */
	public Map<String, WebSessionUsageStatistics> getSessionStatistics() {
		return sessionStatistics;
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
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent event) {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if( session != null ) {
			try {
				WebSessionUsageStatistics webSessionUsageStatistics = WebUsageStatisticsManager.Factory.getInstance().getSessionStatistics().get(session.getId());
				if( webSessionUsageStatistics != null ) {
					webSessionUsageStatistics.setMarkedForDelete(true);
				}
			} catch( Exception ex) {
				logger.error("Exception caught when processing destroyed session", ex);
			}
		}
	}

	/**
	 * Gets the collecting web usage statistics for session.
	 *
	 * @param session the session
	 * @return the collecting web usage statistics for session
	 */
	public synchronized WebSessionUsageStatistics getCollectingWebUsageStatisticsForSession(HttpSession session) {
		if( isDisabled ) return null;
		
		String sessionId = session.getId();
		if( sessionId == null || sessionId.length() < 1 ) return null;
		
		//session tracking
		WebSessionUsageStatistics webSessionUsageStatistics = sessionStatistics.get(session.getId());
		if( webSessionUsageStatistics == null ) {		
			webSessionUsageStatistics = new WebSessionUsageStatistics();
			sessionStatistics.put(sessionId, webSessionUsageStatistics);			
			webSessionUsageStatistics.setSession(session);
		}
		
		return webSessionUsageStatistics;
	}		
	
	/**
	 * Clear invalided web session usage statistics.
	 *
	 * @param webSessionUsageStatistics the web session usage statistics
	 * @throws Exception the exception
	 */
	public void clearInvalidedWebSessionUsageStatistics(WebSessionUsageStatistics webSessionUsageStatistics) throws Exception {
		DailyRollingWebUsageProfileManager.Factory.getInstance().rollupHourlyRequestStatistics(webSessionUsageStatistics);
						
		sessionStatistics.remove(webSessionUsageStatistics.getSession().getId());
	}	

	/**
	 * Removes the web session usage statistics.
	 *
	 * @param sessionId the session id
	 */
	public void removeWebSessionUsageStatistics(String sessionId) {
		sessionStatistics.remove(sessionId);
	}
	
	/**
	 * Reset statistics.
	 */
	public synchronized void resetStatistics() {
		sessionStatistics.clear();
		observationStartDate = new Date();
	}
	
  public synchronized void resetWebUsageStatisticsStartDate() {
		observationStartDate = new Date();
	}

	/**
	 * Gets the active request statistics.
	 *
	 * @return the active request statistics
	 */
	public AggregatedWebSessionUsageStatistics getActiveRequestStatistics() {
		AggregatedWebSessionUsageStatistics aggregatedWebSessionUsageStatistics = new AggregatedWebSessionUsageStatistics();
		
		for( Iterator<WebSessionUsageStatistics> i = getWebSessionUsageStatisticsSet().iterator(); i.hasNext(); ) {
			WebSessionUsageStatistics webSessionUsageStatistics = i.next();
			aggregatedWebSessionUsageStatistics.addRequestStatistics(webSessionUsageStatistics);
		}
		
		return aggregatedWebSessionUsageStatistics;
	}
	
	/**
	 * Gets the web session usage statistics set.
	 *
	 * @return the web session usage statistics set
	 */
	public synchronized Set<WebSessionUsageStatistics> getWebSessionUsageStatisticsSet() {
		Set<WebSessionUsageStatistics> webSessionUsageStatisticsSet = new HashSet<WebSessionUsageStatistics>();
		for( Iterator<WebSessionUsageStatistics> i = sessionStatistics.values().iterator(); i.hasNext(); ) {
			webSessionUsageStatisticsSet.add(i.next());
		}
		return webSessionUsageStatisticsSet;
	}

	/**
	 * Gets the web usage statistics formatted string1.
	 *
	 * @param activeRequestStatistics the active request statistics
	 * @param observationStartDate the observation start date
	 * @return the web usage statistics formatted string1
	 * @throws Exception the exception
	 */
	public static String getWebUsageStatisticsFormattedString1(AggregatedWebSessionUsageStatistics activeRequestStatistics, Date observationStartDate) throws Exception {				
		StringBuffer formattedStatistics = new StringBuffer();
		
		try {
			formattedStatistics.append("<h2><strong>Host:</strong> ").append(InetAddress.getLocalHost().getHostName()).append("</h2>\r\n");
		} catch (UnknownHostException x) {
		}
		
		formattedStatistics.append("<h2><strong>Observation start date/time:</strong> " ).append(observationStartDate).append("</h2>\r\n");
		
		StringBuffer formattedStatisticsDetails = new StringBuffer();
		
		for (Iterator<String> i = activeRequestStatistics.getRequestExecutionStatistics().keySet().iterator(); i.hasNext(); ) {	
			String requestUri = (String)i.next();
			formattedStatisticsDetails.append(getWebUsageStatisticsFormattedString1(requestUri, activeRequestStatistics.getRequestExecutionStatistics().get(requestUri), activeRequestStatistics.getRequestIdleStatistics().get(requestUri))).append("\r\n");
		}
		
		formattedStatistics.append("<table><caption>Usage statistics (per session)</caption><thead><tr>")
		.append("<th>function</th>")
		.append("<th>session#</th>")
		.append("<th>click</th>")
		.append("<th>err</th>")
		.append("<th>warn</th>")
		.append("<th>ex</th>")
		.append("<th>avg exe</th>")
		.append("<th>min exe</th>")
		.append("<th>max exe</th>")
		.append("<th>avg wait</th>")
		.append("<th>min wait</th>")
		.append("<th>max wait</th>")
		.append("<th>wait%</th>")
		.append("</tr></thead>")
		.append("<tfoot><tr><th>Total</th><td colspan=\"12\">").append(activeRequestStatistics.getSessionCount()).append(" sessions</td></tr></tfoot>")
		.append("<tbody>").append(formattedStatisticsDetails).append("</tbody>")
		.append("</table>");
		
		return formattedStatistics.toString();
	}
	

	/**
	 * Gets the web usage statistics formatted string1.
	 *
	 * @param requestUri the request uri
	 * @param execUsageStatisticsData the exec usage statistics data
	 * @param idleUsageStatisticsData the idle usage statistics data
	 * @return the web usage statistics formatted string1
	 */
	private static String getWebUsageStatisticsFormattedString1(String requestUri,	WebRequestUsageStatistics execUsageStatisticsData, WebRequestUsageStatistics idleUsageStatisticsData) {
		StringBuffer formattedStatisticsDetails = new StringBuffer();
		NumberFormat nf = NumberFormat.getNumberInstance();
		
		formattedStatisticsDetails.append("<tr class=\"odd\"><th>").append(" - ").append(requestUri).append("</th>");
		formattedStatisticsDetails.append("<td>").append(execUsageStatisticsData.getUniqueSessionCount()).append("</td>");
		nf.setMaximumFractionDigits(1);
		formattedStatisticsDetails.append("<td>").append(execUsageStatisticsData.getUniqueSessionCount() == 0 ? 0 : nf.format((double)execUsageStatisticsData.getInvocationCount() / (double)execUsageStatisticsData.getUniqueSessionCount())).append("</td>");
		formattedStatisticsDetails.append("<td>").append(execUsageStatisticsData.getUniqueSessionCount() == 0 ? 0 : nf.format((double)execUsageStatisticsData.getErrorCount() / (double)execUsageStatisticsData.getUniqueSessionCount())).append("</td>");
		formattedStatisticsDetails.append("<td>").append(execUsageStatisticsData.getUniqueSessionCount() == 0 ? 0 : nf.format((double)execUsageStatisticsData.getWarningCount() / (double)execUsageStatisticsData.getUniqueSessionCount())).append("</td>");
		formattedStatisticsDetails.append("<td>").append(execUsageStatisticsData.getUniqueSessionCount() == 0 ? 0 : nf.format((double)execUsageStatisticsData.getExceptionCount() / (double)execUsageStatisticsData.getUniqueSessionCount())).append("</td>");
		nf.setMaximumFractionDigits(2);
		formattedStatisticsDetails.append("<td>").append(convertTimeMillisToString(execUsageStatisticsData.getAvgTimeMillis(), nf)).append("</td>");
		formattedStatisticsDetails.append("<td>").append(convertTimeMillisToString(execUsageStatisticsData.getMinTimeMillis(), nf)).append("</td>");
		formattedStatisticsDetails.append("<td>").append(convertTimeMillisToString(execUsageStatisticsData.getMaxTimeMillis(), nf)).append("</td>");
		formattedStatisticsDetails.append("<td>").append(idleUsageStatisticsData == null ? 0 : convertTimeMillisToString(idleUsageStatisticsData.getAvgTimeMillis(), nf)).append("</td>");
		formattedStatisticsDetails.append("<td>").append(idleUsageStatisticsData == null ? 0 : convertTimeMillisToString(idleUsageStatisticsData.getMinTimeMillis(), nf)).append("</td>");
		formattedStatisticsDetails.append("<td>").append(idleUsageStatisticsData == null ? 0 : convertTimeMillisToString(idleUsageStatisticsData.getMaxTimeMillis(), nf)).append("</td>");				
		formattedStatisticsDetails.append("<td>").append(idleUsageStatisticsData == null ? 0 : execUsageStatisticsData.getAvgTimeMillis() < 1 ? 100.00 : nf.format(idleUsageStatisticsData.getAvgTimeMillis() * 100.00 / (execUsageStatisticsData.getAvgTimeMillis() + idleUsageStatisticsData.getAvgTimeMillis()))).append("%").append("</td>");
		
		return formattedStatisticsDetails.toString();
	}

	private static String convertTimeMillisToString(long millis, NumberFormat nf) {
		double convertedValue = (double)millis / 1000.00;
		if( convertedValue > 60 ) {
			return nf.format(convertedValue / 60.00) + " m";
		} else {
			return nf.format(convertedValue) + " s";
		}
	}
}
