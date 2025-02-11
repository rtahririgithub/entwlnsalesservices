package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.List;

public class InteractionRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected List<ProductCharacteristicValue> productCharacteristics;

	public List<ProductCharacteristicValue> getProductCharacteristics() {
		return productCharacteristics;
	}

	public void setProductCharacteristics(
			List<ProductCharacteristicValue> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}
	
	
}
