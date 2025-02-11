/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.webstats.beehivepageflow;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import org.apache.beehive.netui.pageflow.PageFlowActionServlet;
import org.apache.log4j.Logger;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.channelsalesmgmt.common.aspect.aspect.WebProfileSessionAttributeParser;

// TODO: Auto-generated Javadoc
/**
 * The Class AdvisablePageFlowActionServlet.
 */
public abstract class AdvisablePageFlowActionServlet extends PageFlowActionServlet {
	
	/** The logger. */
	static Logger logger = Logger.getLogger(AdvisablePageFlowActionServlet.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
			
			super.process(request, response);
			
		} catch (Exception ex) {
			if( aspects != null ) {
				// apply the exception handler advice
	        	for (int i = 0; i < aspects.length; ++i) {
	        		aspects[i].exception(applicationName, requestUri, new Object[]{request, response}, beforeAspectReturnValue[i], ex);
	    		}
			}
        	hasException = true;
      if( ex instanceof IOException ) {
        throw (IOException)ex;
      } else if( ex instanceof ServletException ) {
        throw (ServletException)ex;
      } else {
        throw new ServletException(ex);
      }
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
