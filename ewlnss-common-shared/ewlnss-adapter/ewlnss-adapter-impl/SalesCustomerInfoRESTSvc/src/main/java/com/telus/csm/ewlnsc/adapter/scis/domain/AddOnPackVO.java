package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

public class AddOnPackVO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String componentId;
    private String componentServiceTypeCd;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((componentId == null) ? 0 : componentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOnPackVO other = (AddOnPackVO) obj;
		if (componentId == null) {
			if (other.componentId != null)
				return false;
		} else if (!componentId.equals(other.componentId))
			return false;
		return true;
	}

}

