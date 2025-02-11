package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 * @author x159015
 * 
 */
public class ValidationMessageVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String messageCd;
	private String messageTxt;
	private List<String> relatedFields;
	
	public String getMessageCd() {
		return messageCd;
	}
	public void setMessageCd(String messageCd) {
		this.messageCd = messageCd;
	}
	public String getMessageTxt() {
		return messageTxt;
	}
	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}
	public List<String> getRelatedFields() {
		return relatedFields;
	}
	public void setRelatedFields(List<String> relatedFields) {
		this.relatedFields = relatedFields;
	}
}
