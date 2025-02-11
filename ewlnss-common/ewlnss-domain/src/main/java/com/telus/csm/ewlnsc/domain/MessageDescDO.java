package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author x159015
 * 
 *         This class represent Locale specific message text configured for EnterpriseSalesServices
 * 
 */
public class MessageDescDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Locale locale;

	private String messageDescriptionText;

	public MessageDescDO() {
	}

	public MessageDescDO(Locale locale, String messageDescriptionText) {
		this.locale = locale;
		this.messageDescriptionText = messageDescriptionText;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getMessageDescriptionText() {
		return messageDescriptionText;
	}

	public void setMessageDescriptionText(String messageDescriptionText) {
		this.messageDescriptionText = messageDescriptionText;
	}

}
