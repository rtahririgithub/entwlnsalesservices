/*
 * 
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.io.Serializable;

import com.telus.channelsalesmgmt.common.aspect.profiling.WebSessionUsageStatistics.RequestBySessionStatistics;

// TODO: Auto-generated Javadoc
/**
 * The Class WebRequestUsageStatistics.
 */
public class WebRequestUsageStatistics implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The unique session count. */
	private long uniqueSessionCount = 0;
	
	/** The invocation count. */
	private long invocationCount = 0;
	
	/** The error count. */
	private long errorCount = 0;
	
	/** The warning count. */
	private long warningCount = 0;
	
	/** The exception count. */
	private long exceptionCount = 0;
	
	/** The min time millis. */
	private long minTimeMillis = Long.MAX_VALUE;
	
	/** The max time millis. */
	private long maxTimeMillis = Long.MIN_VALUE;
	
	/** The total time millis. */
	private long totalTimeMillis = 0;
	
	/**
	 * Gets the unique session count.
	 *
	 * @return the unique session count
	 */
	public long getUniqueSessionCount() {
		return uniqueSessionCount;
	}


	/**
	 * Sets the unique session count.
	 *
	 * @param uniqueSessionCount the new unique session count
	 */
	public void setUniqueSessionCount(long uniqueSessionCount) {
		this.uniqueSessionCount = uniqueSessionCount;
	}


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
	 * Gets the min time millis.
	 *
	 * @return the min time millis
	 */
	public long getMinTimeMillis() {
		return (minTimeMillis == Long.MAX_VALUE) ? 0 : minTimeMillis;
	}

	
	/**
	 * Sets the min time millis.
	 *
	 * @param minTimeMillis the new min time millis
	 */
	public void setMinTimeMillis(long minTimeMillis) {
		this.minTimeMillis = minTimeMillis;
	}


	/**
	 * Gets the max time millis.
	 *
	 * @return the max time millis
	 */
	public long getMaxTimeMillis() {
		return (maxTimeMillis == Long.MIN_VALUE) ? 0 : maxTimeMillis;
	}
	
	
	/**
	 * Sets the max time millis.
	 *
	 * @param maxTimeMillis the new max time millis
	 */
	public void setMaxTimeMillis(long maxTimeMillis) {
		this.maxTimeMillis = maxTimeMillis;
	}


	/**
	 * Gets the total time millis.
	 *
	 * @return the total time millis
	 */
	public long getTotalTimeMillis() {
		return totalTimeMillis;
	}


	/**
	 * Sets the total time millis.
	 *
	 * @param totalTimeMillis the new total time millis
	 */
	public void setTotalTimeMillis(long totalTimeMillis) {
		this.totalTimeMillis = totalTimeMillis;
	}

	/**
	 * Gets the avg time millis.
	 *
	 * @return the avg time millis
	 */
	public long getAvgTimeMillis() {
		return uniqueSessionCount == 0 ? 0 : totalTimeMillis / uniqueSessionCount;
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


	/**
	 * Adds the request statistics.
	 *
	 * @param requestBySessionStatistics the request by session statistics
	 */
	public void addRequestStatistics(RequestBySessionStatistics requestBySessionStatistics) {
		if( minTimeMillis > requestBySessionStatistics.getTotalExecutionTimeMillis() ) {
			minTimeMillis = requestBySessionStatistics.getTotalExecutionTimeMillis();
		}
		
		if( maxTimeMillis < requestBySessionStatistics.getTotalExecutionTimeMillis() ) {
			maxTimeMillis = requestBySessionStatistics.getTotalExecutionTimeMillis();
		}
		
		uniqueSessionCount += 1;
		invocationCount += requestBySessionStatistics.getInvocationCount();
		errorCount += requestBySessionStatistics.getErrorCount();
		warningCount += requestBySessionStatistics.getWarningCount();
		exceptionCount += requestBySessionStatistics.getExceptionCount();
		totalTimeMillis += requestBySessionStatistics.getTotalExecutionTimeMillis();
	}

	/**
	 * Adds the request statistics.
	 *
	 * @param webRequestUsageStatistics the web request usage statistics
	 */
	public void addRequestStatistics(WebRequestUsageStatistics webRequestUsageStatistics) {
		if( minTimeMillis > webRequestUsageStatistics.getMinTimeMillis() ) {
			minTimeMillis = webRequestUsageStatistics.getMinTimeMillis();
		}
		
		if( maxTimeMillis < webRequestUsageStatistics.getMaxTimeMillis() ) {
			maxTimeMillis = webRequestUsageStatistics.getMaxTimeMillis();
		}
		
		uniqueSessionCount += webRequestUsageStatistics.getUniqueSessionCount();
		invocationCount += webRequestUsageStatistics.getInvocationCount();
		errorCount += webRequestUsageStatistics.getErrorCount();
		warningCount += webRequestUsageStatistics.getWarningCount();
		exceptionCount += webRequestUsageStatistics.getExceptionCount();
		totalTimeMillis += webRequestUsageStatistics.getTotalTimeMillis();
	}
}
