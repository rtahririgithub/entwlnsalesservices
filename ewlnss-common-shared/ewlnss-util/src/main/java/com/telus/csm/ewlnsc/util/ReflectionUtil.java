package com.telus.csm.ewlnsc.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class ReflectionUtil {
	
    private static Logger logger = Logger.getLogger( ReflectionUtil.class );

    private ReflectionUtil(){
    	
    }
	
	public static boolean isPolicyException(Object obj) {
		if( obj == null ) {
			return false;
		}
		return obj.getClass().getName().endsWith("PolicyException");
	}
	
	public static boolean isServiceException(Object obj) {
		if( obj == null ){
			return false;
		}
		return obj.getClass().getName().endsWith("ServiceException");
	}
	
	public static boolean isTelusException(Object obj) {
		if( obj == null ) {
			return false;
		}
		return isPolicyException(obj) || isServiceException(obj);
	}
	
	public static String getClassName(Object obj) {
		if( obj == null ) {
			return "";
		}
		
		return obj.getClass().getSimpleName();
	}
	
	private static Object getFaultInfo(Object obj) {
		try {
			Method m = obj.getClass().getMethod("getFaultInfo");
			return m.invoke(obj);
		} catch (Exception ex) {
			logger.error(ex);
			return obj;
		}
	}
	
	private static String getFaultField(Object obj, String methodName) {

		if( obj == null ) {
			return null;
		}

		try {
			Object fault = getFaultInfo(obj);
			if (fault != null) { // fix to avoid NPE
				Method m = fault.getClass().getMethod(methodName);
				return m.invoke(fault).toString();
			}
			return "";
		} catch (Exception ex) {
			logger.info("Error executing " + methodName, ex);
			return null;
		}

	}
	
	public static String getTelusExceptionErrorCode(Object obj) {
		return getFaultField(obj, "getErrorCode");
	}
	
	public static String getTelusExceptionMessageId(Object obj) {
		return getFaultField(obj, "getMessageId");
	}
	
	public static String getTelusExceptionMessage(Object obj) {
		return getFaultField(obj, "getErrorMessage");
	}
}
