package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;

public class NoteVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String author = null;
	private Date date = null;
	private String type = null;
	private String text = null;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
