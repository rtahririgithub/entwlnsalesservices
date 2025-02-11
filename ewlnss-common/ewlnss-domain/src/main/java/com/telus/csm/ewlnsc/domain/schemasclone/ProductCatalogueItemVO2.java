package com.telus.csm.ewlnsc.domain.schemasclone;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CharacteristicVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;

public class ProductCatalogueItemVO2 extends ProductCatalogueItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Description> productCatalogueDescriptionList;
	private List<CharacteristicVO> characteristics; //FIFA-SHS changes: to hold child offer level characteristic
	
	public List<Description> getProductCatalogueDescriptionList() {
		return productCatalogueDescriptionList;
	}
	public void setProductCatalogueDescriptionList(List<Description> productCatalogueDescriptionList) {
		this.productCatalogueDescriptionList = productCatalogueDescriptionList;
	}

	public List<CharacteristicVO> getCharacteristics() {
		if (characteristics==null) {
			characteristics =new ArrayList<CharacteristicVO>();
		}
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicVO> characteristics) {
		this.characteristics = characteristics;
	}

	public void addCharacteristic(CharacteristicVO characteristic) {
		getCharacteristics().add( characteristic );
	}
	
}
