package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class AddOnPackInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String componentId;
	private String addOnPackServiceTypeCd;
    private String componentServiceTypeCd;
    private String componentName;
    private String componentTypeCd;
    private List<EquipmentInfoRestVO> equipmentList;
    
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentServiceTypeCd() {
		return componentServiceTypeCd;
	}
	public void setComponentServiceTypeCd(String componentServiceTypeCd) {
		this.componentServiceTypeCd = componentServiceTypeCd;
	}
	public List<EquipmentInfoRestVO> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<EquipmentInfoRestVO> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public String getAddOnPackServiceTypeCd() {
		return addOnPackServiceTypeCd;
	}
	public void setAddOnPackServiceTypeCd(String addOnPackServiceTypeCd) {
		this.addOnPackServiceTypeCd = addOnPackServiceTypeCd;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentTypeCd() {
		return componentTypeCd;
	}
	public void setComponentTypeCd(String componentTypeCd) {
		this.componentTypeCd = componentTypeCd;
	}
	

    
}
