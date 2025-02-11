package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class OrderCommentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String noteText;
	private String noteTypeCode;
	private String orderEntityTypeCode;
	private String originatorId;
	private String productType;

	public String getNoteText() {
		return noteText;
	}
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public String getNoteTypeCode() {
		return noteTypeCode;
	}
	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}
	public String getOrderEntityTypeCode() {
		return orderEntityTypeCode;
	}
	public void setOrderEntityTypeCode(String orderEntityTypeCode) {
		this.orderEntityTypeCode = orderEntityTypeCode;
	}
	public String getOriginatorId() {
		return originatorId;
	}
	public void setOriginatorId(String originatorId) {
		this.originatorId = originatorId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
}