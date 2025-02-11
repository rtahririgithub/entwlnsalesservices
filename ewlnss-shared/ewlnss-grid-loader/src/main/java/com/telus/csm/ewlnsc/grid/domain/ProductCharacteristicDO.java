package com.telus.csm.ewlnsc.grid.domain;

import java.io.Serializable;

public class ProductCharacteristicDO implements Serializable, Entity<String> {
	
	private static final long serialVersionUID = 1L;
	
	private String characteristicId;
	
	private String catalogueItemId;
	private String name;
	private String code;
	private String value;
	private boolean staticInd;
	private String elementDomainId;

	@Override
	public String getId() {
		return characteristicId;
	}

	public String getCharacteristicId() {
		return characteristicId;
	}

	public void setCharacteristicId(String characteristicId) {
		this.characteristicId = characteristicId;
	}

	public String getCatalogueItemId() {
		return catalogueItemId;
	}

	public void setCatalogueItemId(String catalogueItemId) {
		this.catalogueItemId = catalogueItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isStaticInd() {
		return staticInd;
	}

	public void setStaticInd(boolean staticInd) {
		this.staticInd = staticInd;
	}

	public String getElementDomainId() {
		return elementDomainId;
	}

	public void setElementDomainId(String elementDomainId) {
		this.elementDomainId = elementDomainId;
	}


	
}