package com.telus.csm.ewlnsc.aspect;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatistics;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatisticsManager;

/**
 * This aspect is a replicate of com.telus.channelsalesmgmt.common.aspect.aspect.FlatProfilingAspect from ucss-common-2.0.jar
 * 
 * The main purose of this aspect is for profiling a java dynamic proxy instance, in which case it accept the proxy interface.
 * 
 *  TODO: 
 *  Retrofit this logic back to FlatProfilingAspect to avoid duplication when we have the source code of ucss-common-2.0.jar
 *  
 * @author Michael Liao
 *
 */
public class ProxyProfilingAspect implements Aspect {
	private String aspectName;
	private String interfaceName;
	
	private static final Logger logger=Logger.getLogger(ProxyProfilingAspect.class);
	
	private static final String[] IGNORED_METHODS = new String[] {"setWorkManager", "getSimpleName", "getWsdlUrl", "setApplicationName", "setApplicationPwd"};

	public String getAspectName() {
		return this.aspectName;
	}

	public ProxyProfilingAspect() {
	}

	public ProxyProfilingAspect(Class<?> proxyInterfaceClass ) {
		this.interfaceName = proxyInterfaceClass.getName();
	}
	
	public ProxyProfilingAspect(String proxyInterface ) {
		this.interfaceName = proxyInterface;
	}

	public Object before(Object target, String methodName,
			Object[] actualParameters) {
		return System.currentTimeMillis();
	}

	public void after(Object target, String methodName,
			Object[] actualParameters, Object beforeResult,
			Object resultObject, boolean hasException) {
		
		if (isIgnored(methodName)) {
			return;
		}
		
		if (!hasException) {
			try {
				ServiceUsageStatistics stats = ServiceUsageStatisticsManager.Factory.getInstance().getCollectingUsageStatisticsForClass(getClassName(target));

				if ((beforeResult != null) ) {
					
					long elapsedTime = System.currentTimeMillis() - ((Long) beforeResult).longValue();
					
					logger.debug( getClassName(target)+"."+methodName + " , elapsedTime: " +  elapsedTime );
					
					if (stats != null) {
						stats.collectObservation(methodName + getMethodNameExtension(actualParameters), elapsedTime, false,	null);
					}
				}
			} catch (Exception ex) {
			}
		}
	}

	public void exception(Object target, String methodName,
			Object[] actualParameters, Object beforeResult, Object t) {

		if (isIgnored(methodName)) {
			return;
		}
		
		try {
			ServiceUsageStatistics stats = ServiceUsageStatisticsManager.Factory.getInstance().getCollectingUsageStatisticsForClass( getClassName(target));

			if (stats != null)
				stats.collectObservation(methodName + getMethodNameExtension(actualParameters), 0L, true, t);
		} catch (Exception ex) {
		}
	}

	public void setPointcutType(String pointcutType) {
		try {
			ServiceUsageStatisticsManager.Factory.getInstance().getCollectingUsageStatisticsForClass(interfaceName);
		} catch (Exception ex) {
		}
	}

	public static String getMethodNameExtension(Object[] actualParameters) {
		
		if (actualParameters != null) {
			for (Object arg : actualParameters) {
				if (arg instanceof ProfilingVariance) {
					String methodVariance = ((ProfilingVariance) arg).getMethodVariance();
					if (StringUtils.isBlank(methodVariance)) {
						return "";
					}
					return ":" + methodVariance;
				}
			}
		}

		return "";
		
	}

	private boolean isIgnored(String methodName) {
		
		return ArrayUtils.contains(IGNORED_METHODS, methodName);
	}

	private String getClassName(Object target) {
		
		if (target instanceof String) {
			return (String) target;
		}
		
		if (interfaceName != null) {
			return interfaceName;
		}

		return target.getClass().getName();
	}

}
