package com.telus.csm.ewlnsc.adapter.domain.quote;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyInteractionRole {
	
	private List<ProductCharacteristicNameValue> productCharacteristics;
//    private String actionType;
//    private String catalogId;
//    private String description;
//    private String name;
//    private List<ProductComponent> productComponents;
 
	public List<ProductCharacteristicNameValue> getProductCharacteristics() {
		return productCharacteristics;
	}
	public void setProductCharacteristics(List<ProductCharacteristicNameValue> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}

	

}