package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class EquipmentInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String equipmentName;
	protected String equipmentAcquisitionTypeCd;

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentAcquisitionTypeCd() {
		return equipmentAcquisitionTypeCd;
	}

	public void setEquipmentAcquisitionTypeCd(String equipmentAcquisitionTypeCd) {
		this.equipmentAcquisitionTypeCd = equipmentAcquisitionTypeCd;
	}

}
