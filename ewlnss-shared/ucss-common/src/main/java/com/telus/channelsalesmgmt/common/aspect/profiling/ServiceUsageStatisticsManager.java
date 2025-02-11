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
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceUsageStatisticsManager.
 */
public class ServiceUsageStatisticsManager {
	
	/**
	 * The Class Factory.
	 */
	public static final class Factory {
		
		/** The service usage statistics manager. */
		private static ServiceUsageStatisticsManager serviceUsageStatisticsManager = null;

		/**
		 * Gets the single instance of Factory.
		 *
		 * @return single instance of Factory
		 * @throws Exception the exception
		 */
		public static synchronized ServiceUsageStatisticsManager getInstance() throws Exception {
			if( serviceUsageStatisticsManager == null ) {
				serviceUsageStatisticsManager = new ServiceUsageStatisticsManager();
			}
			
			return serviceUsageStatisticsManager;
	    }
		
		/**
		 * Instantiates a new factory.
		 */
		private Factory() {
		} // No instance of this class allowed
	}

	/** The statistics. */
	private Map<String, ServiceUsageStatistics> statistics = Collections.synchronizedMap(new TreeMap<String, ServiceUsageStatistics>());
		
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
	 * Gets the statistics.
	 *
	 * @return the statistics
	 */
	public Map<String, ServiceUsageStatistics> getStatistics() {
		return statistics;
	}

	/**
	 * Gets the service usage statistics set.
	 *
	 * @return the service usage statistics set
	 */
	public synchronized Set<ServiceUsageStatistics> getServiceUsageStatisticsSet() {
		Set<ServiceUsageStatistics> serviceUsageStatisticsSet = new HashSet<ServiceUsageStatistics>();
		for (Iterator<ServiceUsageStatistics> i = statistics.values().iterator(); i.hasNext(); ) {
			serviceUsageStatisticsSet.add(i.next());
		}
		return serviceUsageStatisticsSet;
	}

	/**
	 * Gets the collecting usage statistics for class.
	 *
	 * @param serviceClassName the service class name
	 * @return the collecting usage statistics for class
	 */
	public synchronized ServiceUsageStatistics getCollectingUsageStatisticsForClass(String serviceClassName) {
		if( isDisabled ) return null;
		
		ServiceUsageStatistics serviceUsageStatistics = null;
		
		if( !statistics.containsKey(serviceClassName) ) {
			serviceUsageStatistics = new ServiceUsageStatistics(serviceClassName);
			statistics.put(serviceClassName, serviceUsageStatistics);		
		} else {
			serviceUsageStatistics = statistics.get(serviceClassName);
		}
		
		return serviceUsageStatistics;
	}		
	
	/**
	 * Reset service usage statistics.
	 */
	public synchronized void resetServiceUsageStatistics() {
		for (Iterator<String> i = statistics.keySet().iterator(); i.hasNext(); ) {	
			resetServiceUsageStatistics((String)i.next());
		}
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

	/**
	 * Reset service usage statistics start date.
	 */
	public synchronized void resetServiceUsageStatisticsStartDate() {
		observationStartDate = new Date();
	}
	
	/**
	 * Reset service usage statistics.
	 *
	 * @param serviceClassName the service class name
	 */
	public synchronized void resetServiceUsageStatistics(String serviceClassName) {
		ServiceUsageStatistics stats = statistics.get(serviceClassName);
		if (stats != null) {
			stats.reset();
		}
	}
	
	/**
	 * Gets the service usage statistics formatted string1.
	 *
	 * @param statistics the statistics
	 * @param observationStartDate the observation start date
	 * @return the service usage statistics formatted string1
	 */
	public static String getServiceUsageStatisticsFormattedString1(Map<String, ServiceUsageStatistics> statistics, Date observationStartDate) {
		StringBuffer formattedStatistics = new StringBuffer("Usage statistics:").append("\r\n");
		
		try {
			formattedStatistics.append("* Host = ").append(InetAddress.getLocalHost().getHostName()).append("\r\n");
		} catch (UnknownHostException x) {
		}
		
		formattedStatistics.append("* Date/time = " ).append(observationStartDate).append(":\r\n\r\n");
		
		for (Iterator<String> i = statistics.keySet().iterator(); i.hasNext(); ) {	
			formattedStatistics.append(getServiceUsageStatisticsFormattedString1(statistics, (String)i.next())).append("\r\n");
		}
		
		return formattedStatistics.toString();
	}
	
	/**
	 * Gets the service usage statistics formatted string1.
	 *
	 * @param statistics the statistics
	 * @param serviceClassName the service class name
	 * @return the service usage statistics formatted string1
	 */
	public static String getServiceUsageStatisticsFormattedString1(Map<String, ServiceUsageStatistics> statistics, String serviceClassName) {
		
		StringBuffer formattedStatistics = new StringBuffer();
		ServiceUsageStatistics serviceUsageStatistics = statistics.get(serviceClassName);
		
		if (serviceUsageStatistics != null && serviceUsageStatistics.getServiceUsageStatistics() != null && serviceUsageStatistics.getServiceUsageStatistics().keySet() != null) {
			formattedStatistics.append("** Service class name = ").append(serviceUsageStatistics.getServiceClassName()).append("\r\n");
			formattedStatistics.append("** Observations start date/time = ").append(serviceUsageStatistics.getObservationsStartDate()).append("\r\n");
			
			for (Iterator<String> i = serviceUsageStatistics.getServiceUsageStatistics().keySet().iterator(); i.hasNext(); ) {
				String methodName = (String)i.next();
			
				formattedStatistics.append("***** Method name = ").append(methodName).append("\r\n")
					.append(" [")
					.append(getMethodUsageStatisticsFormattedString1(statistics, serviceClassName, methodName)).append(" ]\r\n");
			}
		}
		
		return formattedStatistics.toString();
	}
	
	/**
	 * Gets the method usage statistics formatted string1.
	 *
	 * @param statistics the statistics
	 * @param serviceClassName the service class name
	 * @param methodName the method name
	 * @return the method usage statistics formatted string1
	 */
	public static String getMethodUsageStatisticsFormattedString1(Map<String, ServiceUsageStatistics> statistics, String serviceClassName, String methodName) {
		
		StringBuffer formattedStatistics = new StringBuffer();
		ServiceUsageStatistics serviceUsageStatisticss = statistics.get(serviceClassName);
		
		if (serviceUsageStatisticss != null && serviceUsageStatisticss.getMethodUsageStatistics(methodName) != null) {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(2);
			
			ServiceUsageStatistics.MethodExecutionStatistics methodExecutionStatistics = serviceUsageStatisticss.getMethodUsageStatistics(methodName);
			formattedStatistics.append(" total cnt = ").append( methodExecutionStatistics.getInvocationCount());
			formattedStatistics.append(", err cnt = ").append( methodExecutionStatistics.getErrorCount());
			formattedStatistics.append(" [policy = ").append( methodExecutionStatistics.getPolicyExCount());
			formattedStatistics.append(", service = ").append( methodExecutionStatistics.getServiceExCount());
			formattedStatistics.append(", other = ").append( methodExecutionStatistics.getOtherErrorCount());
			formattedStatistics.append("], succ % = ").append( methodExecutionStatistics.getSuccessRatio()).append("%");
			formattedStatistics.append(", exec time (ms): [last = ").append(methodExecutionStatistics.getLastExecutionTimeMillis());
			//formattedStatistics.append(", total = ").append(methodExecutionStatistics.getTotalExecutionTimeMillis());
			formattedStatistics.append(", avg = ").append(methodExecutionStatistics.getAvgExecutionTimeMillis());
			formattedStatistics.append(", min = ").append(methodExecutionStatistics.getMinExecutionTimeMillis());
			formattedStatistics.append(", max = ").append(methodExecutionStatistics.getMaxExecutionTimeMillis());
			formattedStatistics.append("], tps = ").append(nf.format(methodExecutionStatistics.getTps()));
		}
		
		return formattedStatistics.toString();
	}
	
	/**
	 * Gets the service usage statistics formatted string2.
	 *
	 * @param statistics the statistics
	 * @param observationStartDate the observation start date
	 * @return the service usage statistics formatted string2
	 */
	public static String getServiceUsageStatisticsFormattedString2(Map<String, ServiceUsageStatistics> statistics, Date observationStartDate) {
		StringBuffer formattedStatistics = new StringBuffer("Usage statistics:").append("\r\n");
		
		try {
			formattedStatistics.append("* Host = ").append(InetAddress.getLocalHost().getHostName()).append("\r\n");
		} catch (UnknownHostException x) {
		}
		
		formattedStatistics.append("* Date/time = " ).append(observationStartDate).append(":\r\n\r\n");
		
		formattedStatistics.append("Observed class index:").append("\r\n");
		
		StringBuffer formattedStatisticsDetails = new StringBuffer();
		
		int classCount = 1;
		for (Iterator<String> i = statistics.keySet().iterator(); i.hasNext(); ) {	
			String serviceClassName = (String)i.next();
			formattedStatistics.append(classCount).append(") ").append(serviceClassName).append("\r\n");
			formattedStatisticsDetails.append(getServiceUsageStatisticsFormattedString2(statistics, classCount, serviceClassName)).append("\r\n");
			classCount += 1;
		}
		
		StringBuilder formattedStatisticsDetailHeader = new StringBuilder();
		Formatter formatter = new Formatter(formattedStatisticsDetailHeader, Locale.US);
		formatter.format("%1$-32s %2$8s %3$8s %4$8s %5$8s %6$8s %7$8s %8$8s %9$8s %10$8s %11$8s %12$8s"
				, "method"
				, "total"
				, "error"
				, "p-ex"
				, "s-ex"
				, "ex"
				, "succ%"
				, "last(ms)"		
				, "avg(ms)"
				, "min(ms)"
				, "max(ms)"
				, "tps");
		formattedStatistics
			.append("-----------------------------------------------------------------------------------------------------------------------------------\r\n")
			.append(formattedStatisticsDetailHeader.toString()).append("\r\n")
			.append("----------------------------------- ----- -------- -------- -------- -------- -------- -------- -------- -------- -------- --------\r\n")
			.append("\r\n\r\n")
			.append(formattedStatisticsDetails.toString())
			.append("-----------------------------------------------------------------------------------------------------------------------------------\r\n")
			.append("\r\n");
		
		return formattedStatistics.toString();
	}
	
	/**
	 * Gets the service usage statistics formatted string2.
	 *
	 * @param statistics the statistics
	 * @param classCount the class count
	 * @param serviceClassName the service class name
	 * @return the service usage statistics formatted string2
	 */
	public static String getServiceUsageStatisticsFormattedString2(Map<String, ServiceUsageStatistics> statistics, int classCount, String serviceClassName) {
		
		StringBuffer formattedStatistics = new StringBuffer();
		ServiceUsageStatistics serviceUsageStatistics = statistics.get(serviceClassName);
		
		if (serviceUsageStatistics != null && serviceUsageStatistics.getServiceUsageStatistics() != null && serviceUsageStatistics.getServiceUsageStatistics().keySet() != null) {		
			for (Iterator<String> i = serviceUsageStatistics.getServiceUsageStatistics().keySet().iterator(); i.hasNext(); ) {
				String methodName = (String)i.next();
			
				formattedStatistics.append(getMethodUsageStatisticsFormattedString2(statistics, classCount, serviceClassName, methodName)).append("\r\n");
			}
		}
		
		return formattedStatistics.toString();
	}

	/**
	 * Gets the method usage statistics formatted string2.
	 *
	 * @param statistics the statistics
	 * @param classCount the class count
	 * @param serviceClassName the service class name
	 * @param methodName the method name
	 * @return the method usage statistics formatted string2
	 */
	public static String getMethodUsageStatisticsFormattedString2(Map<String, ServiceUsageStatistics> statistics, int classCount, String serviceClassName, String methodName) {
		StringBuilder formattedStatistics = new StringBuilder();
		Formatter formatter = new Formatter(formattedStatistics, Locale.US);
		ServiceUsageStatistics serviceUsageStatisticss = statistics.get(serviceClassName);
		
		if (serviceUsageStatisticss != null && serviceUsageStatisticss.getMethodUsageStatistics(methodName) != null) {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(2);
			
			ServiceUsageStatistics.MethodExecutionStatistics methodExecutionStatistics = serviceUsageStatisticss.getMethodUsageStatistics(methodName);
			
			formatter.format("%1$-32s %2$8s %3$8s %4$8s %5$8s %6$8s %7$8s %8$8s %9$8s %10$8s %11$8s %12$8s"
					, classCount + "." + (methodName.length() > 30 ? methodName.substring(0, 29) : methodName)
					, methodExecutionStatistics.getInvocationCount()
					, methodExecutionStatistics.getErrorCount()
					, methodExecutionStatistics.getPolicyExCount()
					, methodExecutionStatistics.getServiceExCount()
					, methodExecutionStatistics.getOtherErrorCount()
					, methodExecutionStatistics.getSuccessRatio() + "%"
					, methodExecutionStatistics.getLastExecutionTimeMillis()		
					, methodExecutionStatistics.getAvgExecutionTimeMillis()
					, methodExecutionStatistics.getMinExecutionTimeMillis()
					, methodExecutionStatistics.getMaxExecutionTimeMillis()
					, nf.format(methodExecutionStatistics.getTps()));
		}
		
		return formattedStatistics.toString();
	}
	
	/**
	 * Gets the service usage statistics formatted string3.
	 *
	 * @param statistics the statistics
	 * @param observationStartDate the observation start date
	 * @return the service usage statistics formatted string3
	 */
	public static String getServiceUsageStatisticsFormattedString3(Map<String, ServiceUsageStatistics> statistics, Date observationStartDate) {
		StringBuffer formattedStatistics = new StringBuffer();
		
		try {
			formattedStatistics.append("<h2><strong>Host:</strong> ").append(InetAddress.getLocalHost().getHostName()).append("</h2>\r\n");
		} catch (UnknownHostException x) {
		}
		
		formattedStatistics.append("<h2><strong>Observation start date/time:</strong> " ).append(observationStartDate).append("</h2>\r\n");
		
		StringBuffer formattedStatisticsDetails = new StringBuffer();
		
		int classCount = 1;
		for (Iterator<String> i = statistics.keySet().iterator(); i.hasNext(); ) {	
			String serviceClassName = (String)i.next();
			formattedStatisticsDetails.append(getServiceUsageStatisticsFormattedString3(statistics, serviceClassName)).append("\r\n");
			classCount += 1;
		}
		
		formattedStatistics.append("<table><caption>Usage statistics</caption><thead><tr>")
		.append("<th>method</th>")
		.append("<th>total</th>")
		.append("<th>error</th>")
		.append("<th>p-ex</th>")
		.append("<th>s-ex</th>")
		.append("<th>ex</th>")
		.append("<th>succ%</th>")
		.append("<th>last(ms)</th>")
		.append("<th>avg(ms)</th>")
		.append("<th>min(ms)</th>")
		.append("<th>max(ms)</th>")
		.append("<th>95P(s)</th>")
		.append("<th>99P(s)</th>")
		.append("<th>tps</th>")
		.append("</tr></thead>")
		.append("<tfoot><tr><th>Total</th><td colspan=\"13\">").append(classCount - 1).append(" items</td></tr></tfoot>")
		.append("<tbody>").append(formattedStatisticsDetails).append("</tbody>")
		.append("</table>");
		
		return formattedStatistics.toString();
	}
	
	/**
	 * Gets the service usage statistics formatted string3.
	 *
	 * @param statistics the statistics
	 * @param serviceClassName the service class name
	 * @return the service usage statistics formatted string3
	 */
	public static String getServiceUsageStatisticsFormattedString3(Map<String, ServiceUsageStatistics> statistics, String serviceClassName) {
		
		StringBuffer formattedStatistics = new StringBuffer();
		ServiceUsageStatistics serviceUsageStatistics = statistics.get(serviceClassName);
		
		if (serviceUsageStatistics != null && serviceUsageStatistics.getServiceUsageStatistics() != null && serviceUsageStatistics.getServiceUsageStatistics().keySet() != null) {
			formattedStatistics.append("<tr><th colspan=\"14\">").append(serviceClassName).append("</th></tr>");
			
			int methodCount = 1;
			for (Iterator<String> i = serviceUsageStatistics.getServiceUsageStatistics().keySet().iterator(); i.hasNext(); ) {
				String methodName = (String)i.next();
			
				formattedStatistics.append(getMethodUsageStatisticsFormattedString3(statistics, methodCount, serviceClassName, methodName));
				
				methodCount += 1;
			}
		}
		
		return formattedStatistics.toString();
	}
	
	/**
	 * Gets the method usage statistics formatted string3.
	 *
	 * @param statistics the statistics
	 * @param methodCount the method count
	 * @param serviceClassName the service class name
	 * @param methodName the method name
	 * @return the method usage statistics formatted string3
	 */
	public static String getMethodUsageStatisticsFormattedString3(Map<String, ServiceUsageStatistics> statistics, int methodCount, String serviceClassName, String methodName) {
		
		StringBuffer formattedStatistics = new StringBuffer();
		ServiceUsageStatistics serviceUsageStatisticss = statistics.get(serviceClassName);
		
		if (serviceUsageStatisticss != null && serviceUsageStatisticss.getMethodUsageStatistics(methodName) != null) {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(2);
			
			ServiceUsageStatistics.MethodExecutionStatistics methodExecutionStatistics = serviceUsageStatisticss.getMethodUsageStatistics(methodName);
			formattedStatistics.append("<tr class=\"odd\"><th>").append(" - ").append(methodName).append("</th>");
			formattedStatistics.append("<td>").append(methodExecutionStatistics.getInvocationCount()).append("</td>");
			formattedStatistics.append("<td>").append(methodExecutionStatistics.getErrorCount()).append("</td>");
			formattedStatistics.append("<td>").append(methodExecutionStatistics.getPolicyExCount()).append("</td>");
			formattedStatistics.append(methodExecutionStatistics.getServiceExCount() > 20 ? "<td class=\"red\">" : methodExecutionStatistics.getServiceExCount() > 0 ? "<td class=\"amber\">" : "<td>").append(methodExecutionStatistics.getServiceExCount()).append("</td>");
			formattedStatistics.append(methodExecutionStatistics.getOtherErrorCount() > 0 ? "<td class=\"amber\">" : "<td>").append(methodExecutionStatistics.getOtherErrorCount()).append("</td>");
			formattedStatistics.append((methodExecutionStatistics.getInvocationCount() > 0 && methodExecutionStatistics.getSuccessRatioPercentage() < 60) ? "<td class=\"red\">" : (methodExecutionStatistics.getInvocationCount() > 0 && methodExecutionStatistics.getSuccessRatioPercentage() < 90) ? "<td class=\"amber\">" : "<td>").append( methodExecutionStatistics.getSuccessRatio()).append("%").append("</td>");
			formattedStatistics.append(methodExecutionStatistics.getLastExecutionTimeMillis() > 15000 ? "<td class=\"red\">" : methodExecutionStatistics.getLastExecutionTimeMillis() > 5000 ? "<td class=\"amber\">" : "<td>").append(methodExecutionStatistics.getLastExecutionTimeMillis()).append("</td>");
			formattedStatistics.append(methodExecutionStatistics.getAvgExecutionTimeMillis() > 15000 ? "<td class=\"red\">" : methodExecutionStatistics.getAvgExecutionTimeMillis() > 5000 ? "<td class=\"amber\">" : "<td>").append(methodExecutionStatistics.getAvgExecutionTimeMillis()).append("</td>");			
			formattedStatistics.append("<td>").append(methodExecutionStatistics.getMinExecutionTimeMillis()).append("</td>");
			formattedStatistics.append("<td>").append(methodExecutionStatistics.getMaxExecutionTimeMillis()).append("</td>");
			formattedStatistics.append("<td>").append(methodExecutionStatistics.getResponseTimePercentile().getPercentile(95)).append("</td>");
			formattedStatistics.append("<td>").append(methodExecutionStatistics.getResponseTimePercentile().getPercentile(99)).append("</td>");
			formattedStatistics.append("<td>").append(nf.format(methodExecutionStatistics.getTps())).append("</td></tr>");
		}
		
		return formattedStatistics.toString();
	}
}
