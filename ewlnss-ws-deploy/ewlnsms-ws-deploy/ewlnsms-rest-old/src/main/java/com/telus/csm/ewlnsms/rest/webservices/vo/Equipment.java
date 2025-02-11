package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String productTypeCd;
	private int maxEquipmentNum;
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
