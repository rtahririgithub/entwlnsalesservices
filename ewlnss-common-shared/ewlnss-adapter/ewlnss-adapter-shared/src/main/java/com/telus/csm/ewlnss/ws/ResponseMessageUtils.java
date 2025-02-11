package com.telus.csm.ewlnss.ws;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;


/**
 *  The new guidance from SOA team is : Web service shall stop throwing PolicyException, instead, a Web service operation's Response shall be 
 *  sub class of ResponseMessage, in which, the operation can populate the error information in case of Exception. 
 *   
 * This Utility class introspect an Object to see if that object represent a ResponseMessage instance and its errorCode is null or not 
 */
public class ResponseMessageUtils {

	private static Class<?>[] paramTypes =  new Class[0];
	private static Object[] arguments =  new Object[0];
	private static Logger logger = Logger.getLogger(ResponseMessageUtils.class);
	
	private ResponseMessageUtils(){
		
	}
			
	/**
	 * This method will check web service response, to see if the response is a instance of SOA ResponseMessage,
	 * if so, if its errorCode is not null, then raise EndpointException
	 *  
	 * @param wsResponse
	 */
	public static void raiseExceptionForErrorResponse( Object wsResponse ) {
			
		EndpointException endpointEx = null;
		
		try {
			
			String methodName = "get"+wsResponse.getClass().getSimpleName()+"Type";
			Object realWSResponse = wsResponse.getClass().getMethod( methodName, paramTypes ).invoke( wsResponse,arguments );
	
			if (realWSResponse!=null ) {
				for( Method m : realWSResponse.getClass().getMethods() ) {
					endpointEx = getEndpointEx(realWSResponse, m);
				}
			}
		} catch (NoSuchMethodException e) {
			logger.error(e);
		} catch (SecurityException e){
			logger.error(e);
		} catch (InvocationTargetException e){
			logger.error(e);
		} catch (IllegalAccessException e){
			logger.error(e);
		} catch (NullPointerException e){
			logger.error(e);
		}
		
		if ( endpointEx!=null) {
			throw endpointEx;
		}
	}

	private static EndpointException getEndpointEx(Object realWSResponse, Method m)
			throws IllegalAccessException, InvocationTargetException {
		EndpointException endpointEx = null;
		if ( "getErrorCode".equals(m.getName()) ) {
			String errorCode = (String) m.invoke(realWSResponse, arguments );
			if ( errorCode!=null ) {
				endpointEx = new EndpointException( realWSResponse );
			}
		}
		return endpointEx;
	}
}