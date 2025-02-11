/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.aspect;

// TODO: Auto-generated Javadoc
/**
 * The Interface Aspect.
 */
public interface Aspect {
	
	/** The Constant GENERIC_ERROR. */
	public static final String GENERIC_ERROR = "ERR";
	
	/** The Constant GENERIC_WARN. */
	public static final String GENERIC_WARN = "WARN";
	
	/** The Constant GENERIC_EXCEPTION. */
	public static final String GENERIC_EXCEPTION = "EX";
	
	/**
	 * Gets the aspect name.
	 *
	 * @return the aspect name
	 */
	String getAspectName();
	
	/**
	 * Before.
	 *
	 * @param target the target
	 * @param methodName the method name
	 * @param actualParameters the actual parameters
	 * @return the object
	 */
	Object before(Object target, String methodName, Object[] actualParameters);
	
	/**
	 * Exception.
	 *
	 * @param target the target
	 * @param methodName the method name
	 * @param actualParameters the actual parameters
	 * @param beforeResult the before result
	 * @param errorOrException the error or exception
	 */
	void exception(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object errorOrException);
	
	/**
	 * After.
	 *
	 * @param target the target
	 * @param methodName the method name
	 * @param actualParameters the actual parameters
	 * @param beforeResult the before result
	 * @param resultObject the result object
	 * @param hasException the has exception
	 */
	void after(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object resultObject, boolean hasException);
	
	/**
	 * Sets the pointcut type.
	 *
	 * @param pointcutType the new pointcut type
	 */
	void setPointcutType(String pointcutType);

}
