package com.telus.channelsalesmgmt.common.aspect.alert;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExceptionAlertManager.
 */
public interface ExceptionAlertManager {
	
	/**
	 * Notify exception.
	 *
	 * @param target the target
	 * @param methodName the method name
	 * @param actualParameters the actual parameters
	 * @param t the t
	 * @throws Exception the exception
	 */
	public abstract void notifyException(Object target, String methodName, Object[] actualParameters, Object t) throws Exception;
	
}
