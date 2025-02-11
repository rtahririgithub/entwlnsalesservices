/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;

//import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.PolicyException;
//import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException;

// TODO: Auto-generated Javadoc
/**
 * The Class LoggerUtil.
 */
public final class LoggerUtil {
	
	/** The logger. */
	protected Logger logger = null; 
	
	/** The logger map. */
	private static HashMap<String, LoggerUtil> loggerMap = null;
	
	/**
	 * Gets the logger.
	 *
	 * @param className the class name
	 * @return the logger
	 */
	public static synchronized LoggerUtil getLogger(String className) {
		if( loggerMap == null ) {
			loggerMap = new HashMap<String, LoggerUtil>();
		}
		
		LoggerUtil logger = (LoggerUtil)loggerMap.get(className);
		if( logger == null ) {
			logger = new LoggerUtil(Logger.getLogger(className));			
			loggerMap.put(className, logger);
		}
		return logger;
	}
	
	/**
	 * Gets the logger.
	 *
	 * @param obj the obj
	 * @return the logger
	 */
	public static LoggerUtil getLogger(Object obj) {
		return getLogger(obj.getClass().getName());
	}
	
	/**
	 * Gets the logger.
	 *
	 * @param clazz the clazz
	 * @return the logger
	 */
	public static LoggerUtil getLogger(Class clazz) {
		return getLogger(clazz.getName());
	}
	
	/**
	 * Instantiates a new logger util.
	 *
	 * @param logger the logger
	 */
	private LoggerUtil(Logger logger) {
		this.logger = logger;
	}
	
	/**
	 * Enter.
	 *
	 * @param functionName the function name
	 */
	public void enter(String functionName) {
		logger.debug(" [" + functionName + "] -- begin ");
    }

	/**
	 * Enter.
	 *
	 * @param functionName the function name
	 * @param message the message
	 */
	public void enter(String functionName, String message) {
		logger.info(" [" + functionName + "] -- begin, " + message);
    }
	
	/**
	 * Exit.
	 *
	 * @param functionName the function name
	 */
	public void exit(String functionName) {
		logger.debug(" [" + functionName + "] -- end ");
    }

	/**
	 * Exit.
	 *
	 * @param functionName the function name
	 * @param message the message
	 */
	public void exit(String functionName, String message) {
		logger.info(" [" + functionName + "] -- end, " + message);
    }
	
    /**
     * Info.
     *
     * @param functionName the function name
     * @param message the message
     */
    public void info(String functionName, String message) {
    	logger.info(" [" + functionName + "] " + message);
    }

    /**
     * Debug.
     *
     * @param functionName the function name
     * @param message the message
     */
    public void debug(String functionName, String message) {
    	logger.debug(" [" + functionName + "] " + message);
    }

    /**
     * Warn.
     *
     * @param functionName the function name
     * @param message the message
     */
    public void warn(String functionName, String message) {
    	logger.warn(" [" + functionName + "] " + message);
    }

    /**
     * Warn.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     */
    public void warn(String errorCode, String functionName, String message) {
    	logger.warn(" [" + errorCode + "] [" + functionName + "] " + message);
    }

    /**
     * Warn.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     * @param exc the exc
     */
    public void warn(String errorCode, String functionName, String message, Exception exc) {
    	logger.warn(" [" + errorCode + "] [" + functionName + "] " + message, exc);
    }
    
    /**
     * Warn.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     */
    public void warn(int errorCode, String functionName, String message) {
    	logger.warn(" [" + errorCode + "] [" + functionName + "] " + message);
    }

    /**
     * Warn.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     * @param exc the exc
     */
    public void warn(int errorCode, String functionName, String message, Exception exc) {
    	logger.warn(" [" + errorCode + "] [" + functionName + "] " + message, exc);
    }

    /**
     * Error.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     */
    public void error(String errorCode, String functionName, String message) {
    	logger.error(" [" + errorCode + "] [" + functionName + "] " + message);
    }
    
    /**
     * Error.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     * @param ex the ex
     */
    public void error(int errorCode, String functionName, String message, Throwable ex) {
    	error(String.valueOf(errorCode), functionName, message, ex);
    } 
    /**
     * Error.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     */
    public void error(int errorCode, String functionName, String message) {
    	logger.error(" [" + errorCode + "] [" + functionName + "] " + message);
    }

    /**
     * Error.
     *
     * @param errorCode the error code
     * @param functionName the function name
     * @param message the message
     * @param exc the exc
     */
    public void error(String errorCode, String functionName, String message, Throwable exc) {   
    	StringBuffer sb = new StringBuffer();
    	sb.append(" [").append(errorCode).append("] [").append(functionName).append("] ").append(message);
    	if( exc != null ) {    		
//    		if( exc instanceof PolicyException ) {
//    			PolicyException pEx = (PolicyException)exc;
//    			sb.append("*** PolicyException\r\n");
//    			sb.append("***   messageId = " + pEx.getMessageId()).append("\r\n");
//    			sb.append("***   errorCode = " + pEx.getErrorCode()).append("\r\n");
//    			sb.append("***   errorMessage = " + pEx.getErrorMessage()).append("\r\n");
//    			sb.append("***   message = " + pEx.getMessage()).append("\r\n");
//    			if( pEx.getVariables() != null ) {
//    				for( int i = 0; i < pEx.getVariables().length; i++ ) {
//    					sb.append("***   more = " + pEx.getVariables()[i]).append("\r\n");
//    				}
//    			}
//    		} else if( exc instanceof ServiceException ) {
//    			ServiceException pEx = (ServiceException)exc;
//    			sb.append("*** ServiceException\r\n");
//    			sb.append("***   messageId = " + pEx.getMessageId()).append("\r\n");
//    			sb.append("***   errorCode = " + pEx.getErrorCode()).append("\r\n");
//    			sb.append("***   errorMessage = " + pEx.getErrorMessage()).append("\r\n");
//    			sb.append("***   message = " + pEx.getMessage()).append("\r\n");
//    			if( pEx.getVariables() != null ) {
//    				for( int i = 0; i < pEx.getVariables().length; i++ ) {
//    					sb.append("***   more = " + pEx.getVariables()[i]).append("\r\n");
//    				}
//    			}
//    		} else {
        		sb.append("*** Exception\r\n");
        		sb.append("***   message = ").append(exc.getMessage()).append("\r\n");
//    		}
    		sb.append("******   stack trace\r\n" + getStackTrace(exc));
    	}
    	logger.error(sb.toString());
    }
    
    /**
     * Gets the stack trace.
     *
     * @param t the t
     * @return the stack trace
     */
    public static String getStackTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		t.printStackTrace(printWriter);
		return stringWriter.toString();
	}
}
