/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.aspect;

import com.telus.channelsalesmgmt.common.util.LoggerUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class LoggingAspect.
 */
public class LoggingAspect implements Aspect {
	
	/** The aspect name. */
	private String aspectName;

	/** The pointcut type class name. */
	private String pointcutTypeClassName;
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.aspect.Aspect#getAspectName()
	 */
	public String getAspectName() {
		return aspectName;
	}
	
	/**
	 * Instantiates a new logging aspect.
	 *
	 * @param aspectName the aspect name
	 */
	public LoggingAspect(String aspectName) {
		this.aspectName = aspectName;
	}
	
	/**
	 * Instantiates a new logging aspect.
	 *
	 * @param aspectName the aspect name
	 * @param pointcutType the pointcut type
	 */
	public LoggingAspect(String aspectName, String pointcutType) {
		this.aspectName = aspectName;
		setPointcutType(pointcutType);
	}
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#before(java.lang.String, java.lang.Object[])
	 */
	public Object before(Object target, String methodName, Object[] actualParameters) {
		
		StringBuffer commaSeparatedActualParameters = new StringBuffer();
		if (actualParameters != null) {
			for (int i = 0; i < actualParameters.length; ++i) {
				commaSeparatedActualParameters.append(i ==  0 ? actualParameters[i] : ", " + actualParameters[i]);
			}
		}
		
		// since we want the pointcut type class name logged, rather than the LoggingAspect class name
		LoggerUtil.getLogger(pointcutTypeClassName == null ? this.getClass().getName() : pointcutTypeClassName).
			enter(methodName, "actual parameters: " + commaSeparatedActualParameters);
        
		return new Long(System.currentTimeMillis());        
	}
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#after(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object, java.lang.Object, boolean)
	 */
	public void after(Object target, String methodName, Object[] actualParameters,	Object beforeResult, Object resultObject, boolean hasException) {
		long elapsedTimeMillis = beforeResult == null ? 0 : System.currentTimeMillis() - ((Long)beforeResult).longValue();
		
		LoggerUtil.getLogger(pointcutTypeClassName == null ? this.getClass().getName() : pointcutTypeClassName).
			exit(methodName, " elapsed time ms: " + elapsedTimeMillis);
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#exception(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Throwable)
	 */
	public void exception(Object target, String methodName, Object[] actualParametersm, Object beforeResult, Object t) {
		LoggerUtil logger = LoggerUtil.getLogger(pointcutTypeClassName == null ? this.getClass().getName() : pointcutTypeClassName);
		if( t instanceof Throwable ) {
			Throwable ex = (Throwable)t;
			logger.error(-1, methodName, ex.getMessage(), ex);
		} else {
			logger.error(-1, methodName, t.toString());
		}
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.aspect.aspect.Aspect#setPointcutType(java.lang.String)
	 */
	public void setPointcutType(String pointcutTypeClassName) {
		this.pointcutTypeClassName = pointcutTypeClassName;
	}
	
}
