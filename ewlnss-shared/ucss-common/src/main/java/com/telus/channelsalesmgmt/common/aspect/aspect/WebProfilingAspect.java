/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.channelsalesmgmt.common.aspect.profiling.WebSessionUsageStatistics;
import com.telus.channelsalesmgmt.common.aspect.profiling.WebUsageStatisticsManager;

// TODO: Auto-generated Javadoc
/**
 * The Class WebProfilingAspect.
 */
public class WebProfilingAspect implements Aspect {
	
	/** The aspect name. */
	private String aspectName = null;
	
	/** The request uri parser. */
	private WebProfileRequestUriParser requestUriParser = null;
	
	/**
	 * Instantiates a new web profiling aspect.
	 *
	 * @param requestUriParser the request uri parser
	 */
	public WebProfilingAspect(WebProfileRequestUriParser requestUriParser) {
		this.requestUriParser = requestUriParser;
	}
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.aspect.Aspect#getAspectName()
	 */
	public String getAspectName() {
		return aspectName;
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.aspect.Aspect#before(java.lang.Object, java.lang.String, java.lang.Object[])
	 */
	public Object before(Object target, String methodName, Object[] actualParameters) {
		Long currentTime = Long.valueOf(System.currentTimeMillis());
		HttpSession session = null;
		
		if( actualParameters != null && actualParameters[0] != null && actualParameters[0] instanceof HttpServletRequest ) {
			HttpServletRequest request = (HttpServletRequest)actualParameters[0];
			session = request.getSession(false);
		}
		
		return new Object[]{currentTime, session};
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.aspect.Aspect#exception(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Throwable)
	 */
	public void exception(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object t) {
		try {
			handleExceptionOrAfter(target, methodName, actualParameters, beforeResult, t instanceof Throwable ? Aspect.GENERIC_EXCEPTION : (String)t);
		} catch (Exception ex) {}
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.aspect.Aspect#after(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object, java.lang.Object, boolean)
	 */
	public void after(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object resultObject, boolean hasException) {
		if( hasException ) {
			//if exception, do not collect usage info.
		} else {
		try {
				handleExceptionOrAfter(target, methodName, actualParameters, beforeResult, null);
			} catch (Exception ex) {}
		}
	}

	private void handleExceptionOrAfter(Object target, String methodName, Object[] actualParameters, Object beforeResult, String requestProcessingStatus) throws Exception {
		long currentTime = System.currentTimeMillis();			
		long elapsedTime = currentTime - ((Long)(((Object[])beforeResult)[0])).longValue();
			
		//session before request is handled
		HttpSession session = (HttpSession)(((Object[])beforeResult)[1]);
			
		if( actualParameters == null || actualParameters[0] == null || !(actualParameters[0] instanceof HttpServletRequest) ) return;
		HttpServletRequest request = (HttpServletRequest)actualParameters[0];
		
		//session may be invalidated after request is handled 
		if( session == null || session.getId() == null ) {
			session = request.getSession(false);
		}
		
		//if no session, nothing to be monitored
		if( session == null || session.getId() == null ) return;
			
		//get usage statistics
		WebUsageStatisticsManager mgr = WebUsageStatisticsManager.Factory.getInstance();
		WebSessionUsageStatistics webSessionUsageStatistics = mgr.getCollectingWebUsageStatisticsForSession(session);			
		if( webSessionUsageStatistics == null ) return;

		//parse request uri
		String requestUri = request.getRequestURI().substring(request.getContextPath().length());									
		if( requestUriParser != null ) {
			requestUri = requestUriParser.getMappedRequestUri(requestUri);
		}
					
		//collect statistics for request uri
		if( requestUri != null && requestUri.length() > 0 ) {								
			webSessionUsageStatistics.collectObservation(requestUri, requestProcessingStatus, currentTime, elapsedTime);
		}
		
		//roll up/clean up
		if( webSessionUsageStatistics.isMarkedForDelete() ) {
			mgr.clearInvalidedWebSessionUsageStatistics(webSessionUsageStatistics);
		}
	}

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.aspect.Aspect#setPointcutType(java.lang.String)
	 */
	public void setPointcutType(String pointcutType) {
	}

}
