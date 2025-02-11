/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.webstats.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.DispatcherServlet;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.channelsalesmgmt.common.aspect.aspect.WebProfileSessionAttributeParser;

// TODO: Auto-generated Javadoc
/**
 * The Class AdvisableDispatcherServlet.
 */
public abstract class AdvisableDispatcherServlet extends DispatcherServlet {
	
	/** The logger. */
	static Logger logger = Logger.getLogger(AdvisableDispatcherServlet.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception { 		
		String applicationName = getApplicationName();
		if( applicationName == null ) applicationName = "Unknown application"; 
		Aspect[] aspects = getAspects();
		WebProfileSessionAttributeParser webProfileSessionAttributeParser = getWebProfileSessionAttributeParser();
		Object[] beforeAspectReturnValue = new Object[aspects == null ? 0 : aspects.length];
		boolean hasException = false;
		
		String requestUri = request.getRequestURI().substring(request.getContextPath().length());
		try {
			if( aspects != null ) {				
				// apply the before advice
	        	for (int i = 0; i < aspects.length; ++i) {
	        		beforeAspectReturnValue[i] = aspects[i].before(applicationName, requestUri, new Object[]{request, response});
	        	}
			}
			
			super.doDispatch(request, response);
			
		} catch (Exception ex) {
			if( aspects != null ) {
				// apply the exception handler advice
	        	for (int i = 0; i < aspects.length; ++i) {
	        		aspects[i].exception(applicationName, requestUri, new Object[]{request, response}, beforeAspectReturnValue[i], ex);
	    		}
			}
        	hasException = true;
			throw ex;
		} finally {	
			if( aspects != null ) {				
				String requestProcessingStatus = null;
				HttpSession session = request.getSession(false);
				if( webProfileSessionAttributeParser != null && session != null) {
					requestProcessingStatus = webProfileSessionAttributeParser.validateRequestProcessingStatus(request, session);
				}
				
				// apply the after advice in the reverse order
	    		for (int i = aspects.length - 1; i >= 0; --i) {
	    			if( requestProcessingStatus == null ) {
	    				aspects[i].after(applicationName, requestUri, new Object[]{request, response}, beforeAspectReturnValue[i], null, hasException);
	    			} else {
	    				aspects[i].exception(applicationName, requestUri, new Object[]{request, response}, beforeAspectReturnValue[i], requestProcessingStatus);
	    			}
	    		}
			}
		}		
	}
	
	/**
	 * Gets the web profile session attribute parser.
	 *
	 * @return the web profile session attribute parser
	 */
	public abstract WebProfileSessionAttributeParser getWebProfileSessionAttributeParser();

	/**
	 * Gets the aspects.
	 *
	 * @return the aspects
	 */
	public abstract Aspect[] getAspects();
	
	public abstract String getApplicationName();
}
