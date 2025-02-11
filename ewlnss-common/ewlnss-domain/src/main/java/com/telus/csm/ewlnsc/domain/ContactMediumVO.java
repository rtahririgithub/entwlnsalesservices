package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class ContactMediumVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean preferred = null;
	private String contactType = null;
	private MediumCharacteristicVO characteristic = null;

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public MediumCharacteristicVO getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(MediumCharacteristicVO characteristic) {
		this.characteristic = characteristic;
	}

}
