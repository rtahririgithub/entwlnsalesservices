/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
//import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.PolicyException;
//import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceUsageStatistics.
 */
public class ServiceUsageStatistics {

	/** The service class name. */
	private String serviceClassName = null;
	
	/** The observations start date. */
	private Date observationsStartDate = null;
	
	/** The stats. */
	private Map<String, MethodExecutionStatistics> stats = new TreeMap<String, MethodExecutionStatistics>();
	
	/**
	 * Instantiates a new service usage statistics.
	 *
	 * @param serviceClassName the service class name
	 */
	public ServiceUsageStatistics(String serviceClassName) {
		this.serviceClassName = serviceClassName;
		observationsStartDate = new Date();
	}
	
	/**
	 * The Class MethodExecutionStatistics.
	 */
	public class MethodExecutionStatistics {

		/** The method name. */
		public String methodName;
		
		/** The invocation count. */
		private long invocationCount = 0;
		
		/** The error count. */
		private long errorCount = 0;
		
		/** The policy ex count. */
		private long policyExCount = 0;
		
		/** The service ex count. */
		private long serviceExCount = 0;

		/** The last execution time millis. */
		private long lastExecutionTimeMillis = 0;
		
		/** The total execution time millis. */
		private long totalExecutionTimeMillis = 0;
		
		/** The min execution time millis. */
		private long minExecutionTimeMillis = Long.MAX_VALUE;
		
		/** The max execution time millis. */
		private long maxExecutionTimeMillis = Long.MIN_VALUE;
		
		/** The response time percentile. */
		private UsagePercentile responseTimePercentile = new ResponseTimePercentile();
		
		/**
		 * Instantiates a new method execution statistics.
		 *
		 * @param methodName the method name
		 */
		public MethodExecutionStatistics(String methodName) {
			this.methodName = methodName;
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
		 * Gets the invocation count.
		 *
		 * @return the invocation count
		 */
		public long getInvocationCount() {
			return invocationCount;
		}

		/**
		 * Gets the policy ex count.
		 *
		 * @return the policy ex count
		 */
		public long getPolicyExCount() {
			return policyExCount;
		}

		/**
		 * Gets the service ex count.
		 *
		 * @return the service ex count
		 */
		public long getServiceExCount() {
			return serviceExCount;
		}

		/**
		 * Gets the other error count.
		 *
		 * @return the other error count
		 */
		public long getOtherErrorCount() {
			return errorCount - policyExCount - serviceExCount;
		}
		
		/**
		 * Gets the success ratio.
		 *
		 * @return the success ratio
		 */
		public String getSuccessRatio() {
			if( invocationCount < 1 ) return "0.00";
			
			return String.format("%.2f", (invocationCount - errorCount) * 100.00 / invocationCount);
		}
		
		/**
		 * Gets the success ratio percentage.
		 *
		 * @return the success ratio percentage
		 */
		public double getSuccessRatioPercentage() {
			if( invocationCount < 1 ) return 0;
			
			return (invocationCount - errorCount) * 100.00 / invocationCount;
		}
		
		/**
		 * Gets the last execution time millis.
		 *
		 * @return the last execution time millis
		 */
		public long getLastExecutionTimeMillis() {
			return lastExecutionTimeMillis;
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
		 * Gets the min execution time millis.
		 *
		 * @return the min execution time millis
		 */
		public long getMinExecutionTimeMillis() {
			return (minExecutionTimeMillis == Long.MAX_VALUE) ? 0 : minExecutionTimeMillis;
		}

		/**
		 * Gets the max execution time millis.
		 *
		 * @return the max execution time millis
		 */
		public long getMaxExecutionTimeMillis() {
			return (maxExecutionTimeMillis == Long.MIN_VALUE) ? 0 : maxExecutionTimeMillis;
		}
		
		/**
		 * Gets the avg execution time millis.
		 *
		 * @return the avg execution time millis
		 */
		public long getAvgExecutionTimeMillis() {
			return (invocationCount - errorCount) <= 0 ? 0 : totalExecutionTimeMillis/(invocationCount - errorCount);
		}
	
		/**
		 * Gets the tps.
		 *
		 * @return the tps
		 */
		public double getTps() {
			return totalExecutionTimeMillis == 0 ? 0 : (invocationCount - errorCount)*1000.0/totalExecutionTimeMillis;
		}
		
		
		/**
		 * Gets the response time percentile.
		 *
		 * @return the response time percentile
		 */
		public UsagePercentile getResponseTimePercentile() {
			return responseTimePercentile;
		}

		/**
		 * Collect observation.
		 *
		 * @param lastExecutionTimeMillis the last execution time millis
		 * @param isError the is error
		 * @param errorObject the error object
		 */
		public synchronized void collectObservation(long lastExecutionTimeMillis, boolean isError, Object errorObject) {
			invocationCount++;
			try {
				responseTimePercentile.resample(Long.toString(lastExecutionTimeMillis));
			} catch( Exception ex ) {}
			
			if( isError ) {
				errorCount++;
//				if( errorObject instanceof PolicyException ) {
//					policyExCount++;
//				} else if( errorObject instanceof ServiceException ) {
//					serviceExCount++;
//				} else if( Aspect.GENERIC_WARN.equals(errorObject) ) {
				if( Aspect.GENERIC_WARN.equals(errorObject) ) {
					policyExCount++;
				} else if( Aspect.GENERIC_ERROR.equals(errorObject) ) {
					serviceExCount++;
				}
			} else {
				this.lastExecutionTimeMillis = lastExecutionTimeMillis;
				
				totalExecutionTimeMillis += lastExecutionTimeMillis;
				
				if (minExecutionTimeMillis > lastExecutionTimeMillis) {
					minExecutionTimeMillis = lastExecutionTimeMillis;
				}
				
				if (maxExecutionTimeMillis < lastExecutionTimeMillis) {
					maxExecutionTimeMillis = lastExecutionTimeMillis;
				}
			}
		}
		
		/**
		 * Reset.
		 */
		public synchronized void reset() {
			invocationCount = 0;
			errorCount = 0;
			policyExCount = 0;
			serviceExCount = 0;
			lastExecutionTimeMillis = 0;
			totalExecutionTimeMillis = 0;
			minExecutionTimeMillis = Long.MAX_VALUE;
			maxExecutionTimeMillis = Long.MIN_VALUE;
			responseTimePercentile = new ResponseTimePercentile();
		}

		/**
		 * Adds the usage.
		 *
		 * @param anotherMethodExecutionStatistics the another method execution statistics
		 */
		public synchronized void addUsage(MethodExecutionStatistics anotherMethodExecutionStatistics) {
			invocationCount += anotherMethodExecutionStatistics.invocationCount;
			errorCount += anotherMethodExecutionStatistics.errorCount;
			policyExCount += anotherMethodExecutionStatistics.policyExCount;
			serviceExCount += anotherMethodExecutionStatistics.serviceExCount;			
			totalExecutionTimeMillis += anotherMethodExecutionStatistics.totalExecutionTimeMillis;
			
			if( anotherMethodExecutionStatistics.lastExecutionTimeMillis > 0 ) {
				lastExecutionTimeMillis = anotherMethodExecutionStatistics.lastExecutionTimeMillis;
			}
			
			if (minExecutionTimeMillis > anotherMethodExecutionStatistics.minExecutionTimeMillis) {
				minExecutionTimeMillis = anotherMethodExecutionStatistics.minExecutionTimeMillis;
			}
			
			if (maxExecutionTimeMillis < anotherMethodExecutionStatistics.maxExecutionTimeMillis) {
				maxExecutionTimeMillis = anotherMethodExecutionStatistics.maxExecutionTimeMillis;
			}
			
			responseTimePercentile.addUsage(anotherMethodExecutionStatistics.getResponseTimePercentile());
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#clone()
		 */
		public MethodExecutionStatistics clone() {
			MethodExecutionStatistics clonedMethodExecutionStatistics = new MethodExecutionStatistics(this.methodName);
			clonedMethodExecutionStatistics.invocationCount = this.invocationCount;
			clonedMethodExecutionStatistics.errorCount = this.errorCount;
			clonedMethodExecutionStatistics.policyExCount = this.policyExCount;
			clonedMethodExecutionStatistics.serviceExCount = this.serviceExCount;
			clonedMethodExecutionStatistics.lastExecutionTimeMillis = this.lastExecutionTimeMillis;
			clonedMethodExecutionStatistics.totalExecutionTimeMillis = this.totalExecutionTimeMillis;
			clonedMethodExecutionStatistics.minExecutionTimeMillis = this.minExecutionTimeMillis;
			clonedMethodExecutionStatistics.maxExecutionTimeMillis = this.maxExecutionTimeMillis;
			clonedMethodExecutionStatistics.responseTimePercentile.addUsage(this.responseTimePercentile);
			return clonedMethodExecutionStatistics;
		}
	}
	
	/**
	 * Collect observation.
	 *
	 * @param methodName the method name
	 * @param lastExecutionTimeMillis the last execution time millis
	 * @param isError the is error
	 * @param errorObj the error obj
	 */
	public synchronized void collectObservation(String methodName, long lastExecutionTimeMillis, boolean isError, Object errorObj) {
		
		MethodExecutionStatistics methodExecutionStatistics = null;
		
		if( !stats.containsKey(methodName) ) {
			methodExecutionStatistics = new MethodExecutionStatistics(methodName);
			stats.put(methodName, methodExecutionStatistics);
			
		} else {
			methodExecutionStatistics = (MethodExecutionStatistics)stats.get(methodName);

		}
		
		methodExecutionStatistics.collectObservation(lastExecutionTimeMillis, isError, errorObj);
	}
	
	/**
	 * Gets the method usage statistics.
	 *
	 * @param methodName the method name
	 * @return the method usage statistics
	 */
	public  MethodExecutionStatistics getMethodUsageStatistics(String methodName) {
		return (MethodExecutionStatistics)stats.get(methodName);
	}
	
	/**
	 * Gets the service usage statistics.
	 *
	 * @return the service usage statistics
	 */
	public Map<String, MethodExecutionStatistics> getServiceUsageStatistics() {
		return stats;
	}
	
	/**
	 * Gets the observations start date.
	 *
	 * @return the observations start date
	 */
	public Date getObservationsStartDate() {
		return observationsStartDate;
	}
	
	/**
	 * Gets the service class name.
	 *
	 * @return the service class name
	 */
	public String getServiceClassName() {
		return serviceClassName;
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		if (stats.keySet() != null) {
			for (Iterator<String> i = stats.keySet().iterator(); i.hasNext();) {
				MethodExecutionStatistics methodExecutionStats = (MethodExecutionStatistics)stats.get((String)i.next());
				methodExecutionStats.reset();
			}
		}
	
		observationsStartDate = new Date();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public ServiceUsageStatistics clone() {
		ServiceUsageStatistics clonedServiceUsageStatistics = new ServiceUsageStatistics(this.serviceClassName);
		clonedServiceUsageStatistics.observationsStartDate = new Date(this.observationsStartDate.getTime());
		if (stats.keySet() != null) {
			for (Iterator<String> i = stats.keySet().iterator(); i.hasNext();) {
				String methodName = i.next();
				MethodExecutionStatistics methodExecutionStats = (MethodExecutionStatistics)stats.get(methodName);
				clonedServiceUsageStatistics.stats.put(methodName, methodExecutionStats.clone());
			}
		}
		
		return clonedServiceUsageStatistics;
	}
}
