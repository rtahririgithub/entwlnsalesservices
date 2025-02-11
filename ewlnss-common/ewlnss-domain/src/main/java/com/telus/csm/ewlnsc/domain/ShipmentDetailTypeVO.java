package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class ShipmentDetailTypeVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ShipmentAddressTypeVO shipmentAddress = null;

	public ShipmentAddressTypeVO getShipmentAddress() {
		return shipmentAddress;
	}

	public void setShipmentAddress(ShipmentAddressTypeVO shipmentAddress) {
		this.shipmentAddress = shipmentAddress;
	}
}
