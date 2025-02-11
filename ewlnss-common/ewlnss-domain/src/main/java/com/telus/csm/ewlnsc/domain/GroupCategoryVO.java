/**
 * 
 */
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class GroupCategoryVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String groupTypeCd = null;
	private String groupSubTypeCd = null;

	public String getGroupTypeCd() {
		return groupTypeCd;
	}

	public void setGroupTypeCd(String groupType) {
		this.groupTypeCd = groupType;
	}

	public String getGroupSubTypeCd() {
		return groupSubTypeCd;
	}

	public void setGroupSubTypeCd(String groupSubType) {
		this.groupSubTypeCd = groupSubType;
	}

}
