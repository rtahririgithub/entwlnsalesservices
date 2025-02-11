/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;

// TODO: Auto-generated Javadoc
/**
 * The Class WebSessionUsageStatistics.
 */
public class WebSessionUsageStatistics implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The execution statistics by request uri map. */
	private Map<String, RequestBySessionStatistics> executionStatisticsByRequestUriMap = Collections.synchronizedMap(new HashMap<String, RequestBySessionStatistics>());
	
	/** The idle statistics by request uri map. */
	private Map<String, RequestBySessionStatistics> idleStatisticsByRequestUriMap = Collections.synchronizedMap(new HashMap<String, RequestBySessionStatistics>());	
	
	/** The session. */
	private HttpSession session;
	
	/** The session start date. */
	private Calendar sessionStartDate = Calendar.getInstance();

	/** The last request uri. */
	private String lastRequestUri;
	
	/** The last request time millis. */
	private long lastRequestTimeMillis;
	
	/** The is marked for delete. */
	private boolean isMarkedForDelete = false;

	/**
	 * The Class RequestBySessionStatistics.
	 */
	public class RequestBySessionStatistics {
		
		/** The invocation count. */
		private long invocationCount = 0;
		
		/** The total execution time millis. */
		private long totalExecutionTimeMillis = 0;
		
		/** The error count. */
		private long errorCount = 0;
		
		/** The warning count. */
		private long warningCount = 0;
		
		/** The exception count. */
		private long exceptionCount = 0;
		
		/**
		 * Gets the invocation count.
		 *
		 * @return the invocation count
		 */
		public long getInvocationCount() {
			return invocationCount;
		}

		/**
		 * Sets the invocation count.
		 *
		 * @param invocationCount the new invocation count
		 */
		public void setInvocationCount(long invocationCount) {
			this.invocationCount = invocationCount;
		}

		/**
		 * Gets the total execution time millis.
		 *
		 * @return the total execution time millis
		 */
		public long getTotalExecutionTimeMillis() {
			return totalExecutionTimeMillis;
		}

		/**
		 * Sets the total execution time millis.
		 *
		 * @param totalExecutionTimeMillis the new total execution time millis
		 */
		public void setTotalExecutionTimeMillis(long totalExecutionTimeMillis) {
			this.totalExecutionTimeMillis = totalExecutionTimeMillis;
		}

		/**
		 * Gets the error count.
		 *
		 * @return the error count
		 */
		public long getErrorCount() {
			return errorCount;
		}

		/**
		 * Sets the error count.
		 *
		 * @param errorCount the new error count
		 */
		public void setErrorCount(long errorCount) {
			this.errorCount = errorCount;
		}

		/**
		 * Gets the warning count.
		 *
		 * @return the warning count
		 */
		public long getWarningCount() {
			return warningCount;
		}

		/**
		 * Sets the warning count.
		 *
		 * @param warningCount the new warning count
		 */
		public void setWarningCount(long warningCount) {
			this.warningCount = warningCount;
		}

		/**
		 * Gets the exception count.
		 *
		 * @return the exception count
		 */
		public long getExceptionCount() {
			return exceptionCount;
		}

		/**
		 * Sets the exception count.
		 *
		 * @param exceptionCount the new exception count
		 */
		public void setExceptionCount(long exceptionCount) {
			this.exceptionCount = exceptionCount;
		}
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * Sets the session.
	 *
	 * @param session the new session
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * Checks if is marked for delete.
	 *
	 * @return true, if is marked for delete
	 */
	public boolean isMarkedForDelete() {
		return isMarkedForDelete;
	}

	/**
	 * Sets the marked for delete.
	 *
	 * @param isMarkedForDelete the new marked for delete
	 */
	public void setMarkedForDelete(boolean isMarkedForDelete) {
		this.isMarkedForDelete = isMarkedForDelete;
	}

	/**
	 * Gets the execution statistics by request uri map.
	 *
	 * @return the execution statistics by request uri map
	 */
	public Map<String, RequestBySessionStatistics> getExecutionStatisticsByRequestUriMap() {
		return executionStatisticsByRequestUriMap;
	}

	/**
	 * Gets the idle statistics by request uri map.
	 *
	 * @return the idle statistics by request uri map
	 */
	public Map<String, RequestBySessionStatistics> getIdleStatisticsByRequestUriMap() {
		return idleStatisticsByRequestUriMap;
	}

	/**
	 * Gets the session start date.
	 *
	 * @return the session start date
	 */
	public Calendar getSessionStartDate() {
		return sessionStartDate;
	}
	
	/**
	 * Collect observation.
	 *
	 * @param requestUri the request uri
	 * @param requestProcessingStatus the request processing status
	 * @param currentTime the current time
	 * @param elapsedTime the elapsed time
	 */
	public synchronized void collectObservation(String requestUri, String requestProcessingStatus, long currentTime, long elapsedTime) {
		if( requestUri == null || requestUri.length() < 1 ) return;
				
		//execution time for request uri
		updateTotalElapseTimeMap(executionStatisticsByRequestUriMap, requestUri, requestProcessingStatus, elapsedTime);
		
		//idle times for request uri
		if( lastRequestUri != null ) { 
			updateTotalElapseTimeMap(idleStatisticsByRequestUriMap, lastRequestUri, requestProcessingStatus, currentTime - lastRequestTimeMillis);
		}
		
		//session tracking
		lastRequestUri = requestUri;
		lastRequestTimeMillis = currentTime;
	}
	
	/**
	 * Update total elapse time map.
	 *
	 * @param elapseTimeByRequestUriMap the elapse time by request uri map
	 * @param requestUri the request uri
	 * @param requestProcessingStatus the request processing status
	 * @param elapsedTime the elapsed time
	 */
	private synchronized void updateTotalElapseTimeMap(Map<String, RequestBySessionStatistics> elapseTimeByRequestUriMap, String requestUri, String requestProcessingStatus, long elapsedTime) {		
		RequestBySessionStatistics elapseTimeByRequestUri = elapseTimeByRequestUriMap.get(requestUri);
		if( elapseTimeByRequestUri == null ) {
			elapseTimeByRequestUri = new RequestBySessionStatistics();
			elapseTimeByRequestUriMap.put(requestUri, elapseTimeByRequestUri);
		} 		
		
		//totals
		elapseTimeByRequestUri.setInvocationCount(elapseTimeByRequestUri.getInvocationCount() + 1);
		elapseTimeByRequestUri.setTotalExecutionTimeMillis(elapseTimeByRequestUri.getTotalExecutionTimeMillis() + elapsedTime);
		
		//errors
		if( Aspect.GENERIC_ERROR.equalsIgnoreCase(requestProcessingStatus) ) {
			elapseTimeByRequestUri.setErrorCount(elapseTimeByRequestUri.getErrorCount() + 1);
		} else if( Aspect.GENERIC_WARN.equalsIgnoreCase(requestProcessingStatus) ) {
			elapseTimeByRequestUri.setWarningCount(elapseTimeByRequestUri.getWarningCount() + 1);
		} else if( Aspect.GENERIC_EXCEPTION.equalsIgnoreCase(requestProcessingStatus) ) {
			elapseTimeByRequestUri.setExceptionCount(elapseTimeByRequestUri.getExceptionCount() + 1);
		}
	}	
}
