package com.telus.csm.ewlnsc.delegate.response;

import java.util.List;

import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;

public class ProductItemDelegateResponse {

	private List<ProductItemVO> productItems;
	private List<FFHEquipmentItemVO> equipmentItems;

	public List<ProductItemVO> getProductItems() {
		return productItems;
	}

	public void setProductItems(
			List<ProductItemVO> productItems) {
		this.productItems = productItems;
	}

	public List<FFHEquipmentItemVO> getEquipmentItems() {
		return equipmentItems;
	}

	public void setEquipmentItems(
			List<FFHEquipmentItemVO> equipmentItems) {
		this.equipmentItems = equipmentItems;
	}
}
