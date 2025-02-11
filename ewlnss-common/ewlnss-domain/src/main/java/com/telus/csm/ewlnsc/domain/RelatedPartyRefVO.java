package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class RelatedPartyRefVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String href = null;
	private String id = null;
	private String name = null;
	private String role = null;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
