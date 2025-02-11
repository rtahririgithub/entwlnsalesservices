package com.telus.csm.ewlnss.adapter.domain;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author x159015
 * 
 *         This class represent Locale specific message text configured for EnterpriseSalesServices
 * 
 */
public class AdapterResponseMessageDesc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Locale locale;

	private String messageDescriptionText;

	public AdapterResponseMessageDesc() {
		// nor arg constructor
	}

	public AdapterResponseMessageDesc(Locale locale, String messageDescriptionText) {
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
