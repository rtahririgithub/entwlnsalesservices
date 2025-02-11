/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class WirelineEquipmentPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	
	private String materialItemCode = null;
	private EquipmentAcquisitionTypeVO acquisitionType = null;
	private ProductItemPriceVO equipmentPrice = null;

	public String getMaterialItemCode() {
		return materialItemCode;
	}

	public void setMaterialItemCode(String materialItemCode) {
		this.materialItemCode = materialItemCode;
	}

	public EquipmentAcquisitionTypeVO getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(EquipmentAcquisitionTypeVO acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

	public ProductItemPriceVO getEquipmentPrice() {
		return equipmentPrice;
	}

	public void setEquipmentPrice(ProductItemPriceVO equipmentPrice) {
		this.equipmentPrice = equipmentPrice;
	}

}
