package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class EquipmentQualInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String productTypeCd;
	protected int maxEquipmentNum;

	public String getProductTypeCd() {
		return productTypeCd;
	}

	public void setProductTypeCd(String productTypeCd) {
		this.productTypeCd = productTypeCd;
	}

	public int getMaxEquipmentNum() {
		return maxEquipmentNum;
	}

	public void setMaxEquipmentNum(int maxEquipmentNum) {
		this.maxEquipmentNum = maxEquipmentNum;
	}
	
}
