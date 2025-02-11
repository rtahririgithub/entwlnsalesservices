/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.aspect;

import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatistics;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatisticsManager;

// TODO: Auto-generated Javadoc
/**
 * The Class FlatProfilingAspect.
 */
public class FlatProfilingAspect implements Aspect {
	
	/** The aspect name. */
	private String aspectName;
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.aspect.Aspect#getAspectName()
	 */
	public String getAspectName() {
		return aspectName;
	}
	
  /**
	 * Instantiates a new flat profiling aspect.
	 */
	public FlatProfilingAspect() {
		this.aspectName = null;
	}

	/**
	 * Instantiates a new flat profiling aspect.
	 *
	 * @param aspectName the aspect name
	 * @param pointcutType the pointcut type
	 */
	public FlatProfilingAspect(String aspectName, String pointcutType) {
		this.aspectName = aspectName;
		setPointcutType(pointcutType);
	}
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#before(java.lang.String, java.lang.Object[])
	 */
	public Object before(Object target, String methodName, Object[] actualParameters) {
        return new Long(System.currentTimeMillis());    
	}
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#after(java.lang.String, java.lang.Object[], java.lang.Object, java.lang.Object, boolean)
	 */
	public void after(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object resultObject, boolean hasException) {
		if( hasException ) {
			//if exception, do not collect usage info.
		} else {
			try {
				ServiceUsageStatistics stats = ServiceUsageStatisticsManager.Factory.getInstance().getCollectingUsageStatisticsForClass(target instanceof String ? (String)target : target.getClass().getName());
				
				if (beforeResult != null && stats != null) { 
					long elapsedTime = System.currentTimeMillis() - ((Long)beforeResult).longValue();
					stats.collectObservation(methodName, elapsedTime, false, null);
				}
			} catch(Exception ex) {}
		}
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#exception(java.lang.String, java.lang.Object[], java.lang.Throwable)
	 */
	public void exception(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object t) {
		try {
			ServiceUsageStatistics stats = ServiceUsageStatisticsManager.Factory.getInstance().getCollectingUsageStatisticsForClass(target instanceof String ? (String)target : target.getClass().getName());
			
			if( stats != null ) {
			stats.collectObservation(methodName, 0, true, t);			
			}
		} catch(Exception ex) {}
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#setPointcutType(java.lang.String)
	 */
	public void setPointcutType(String pointcutType) {
		try {
			ServiceUsageStatisticsManager.Factory.getInstance().getCollectingUsageStatisticsForClass(pointcutType);
		} catch( Exception ex ) {}
	}
	
}
