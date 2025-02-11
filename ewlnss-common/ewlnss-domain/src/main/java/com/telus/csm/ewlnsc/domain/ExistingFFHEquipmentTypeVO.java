package com.telus.csm.ewlnsc.domain;

import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;

public class ExistingFFHEquipmentTypeVO extends FFHEquipmentItemVO {

	private EquipmentAcquisitionTypeVO acquisitionType = null;

	public ExistingFFHEquipmentTypeVO acquisitionType(EquipmentAcquisitionTypeVO acquisitionType) {
		this.acquisitionType = acquisitionType;
		return this;
	}

	/**
	 * Get acquisitionType
	 * 
	 * @return acquisitionType
	 **/
	public EquipmentAcquisitionTypeVO getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(EquipmentAcquisitionTypeVO acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

}
