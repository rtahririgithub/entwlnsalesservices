package com.telus.csm.ewlnsc.transformer;

import java.util.List;

import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;

public class AvailableProductItemHolder {
	
	private List<ProductItemVO> productItems = null;
	private List<AvailableFFHEquipmentTypeVO> equipments = null;
	
	public List<ProductItemVO> getProductItems() {
		return productItems;
	}

	public void setProductItems(
			List<ProductItemVO> productItems) {
		this.productItems = productItems;
	}

	public List<AvailableFFHEquipmentTypeVO> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<AvailableFFHEquipmentTypeVO> equipments) {
		this.equipments = equipments;
	}
}
