package com.telus.csm.ewlnsc.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The Class LoggerUtil.
 */
public final class LoggerUtil {
	
	/** The logger. */
	protected Logger logger = null; 
	
	/** The logger map. */
	private static HashMap<String, LoggerUtil> loggerMap = null;
	
	/**
	 * Instantiates a new logger util.
	 *
	 * @param logger the logger
	 */
	private LoggerUtil(Logger logger) {
		this.logger = logger;
	}
	
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
		
		LoggerUtil logger = loggerMap.get(className);
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
     * @param exception
     */
    public void error(Object exception){
    	logger.error(exception);
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
    	StringBuilder sb = new StringBuilder();
    	sb.append(" [" + errorCode + "] [" + functionName + "] " + message + "\r\n");
    	sb.append("*** " + ReflectionUtil.getClassName(exc) + "\r\n");

    	if( exc != null ) {    		
    		if( ReflectionUtil.isTelusException(exc) ) {
    			sb.append("***   messageId = " + ReflectionUtil.getTelusExceptionMessageId(exc)).append("\r\n");
    			sb.append("***   errorCode = " + ReflectionUtil.getTelusExceptionErrorCode(exc)).append("\r\n");
    			sb.append("***   errorMessage = " + ReflectionUtil.getTelusExceptionMessage(exc)).append("\r\n");
    		} else {
       		sb.append("***   message = ").append(exc.getMessage()).append("\r\n");
    		}
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
	public static String getStackTrace(final Exception exc) {
		final StringWriter swr = new StringWriter();
		exc.printStackTrace(new PrintWriter(swr));
		return exc.getMessage() + " " + exc.getCause() + " " + swr.toString();
		
	}

public static String concatFaultInfo(List<String> list){
		
		StringBuilder strBuf = new StringBuilder();
		for (String str : list){
			strBuf.append(str);
			strBuf.append(" ");
		}
		
		return strBuf.toString();
	}
	
	public static String concatFaultInfo(String[] list){
        
        StringBuilder strBuf = new StringBuilder();
        for (String str : list){
            strBuf.append(str);
            strBuf.append(" ");
        }
        
        return strBuf.toString();
    }
}
