/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// TODO: Auto-generated Javadoc
/**
 * The Interface WebProfileSessionAttributeParser.
 */
public interface WebProfileSessionAttributeParser {
	
	/**
	 * Validate request processing status.
	 *
	 * @param session the session
	 * @return the string
	 */
	public String validateRequestProcessingStatus(HttpServletRequest request, HttpSession session);
	
}
