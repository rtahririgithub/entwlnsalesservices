/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class EquipmentAcquisitionTypeVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	
	private Boolean buyIndicator = null;
	private Boolean customerOwnedIndicator = null;
	private Boolean rentalEquipmentIndicator = null;

	public Boolean isBuyIndicator() {
		return buyIndicator;
	}

	public void setBuyIndicator(Boolean buyIndicator) {
		this.buyIndicator = buyIndicator;
	}

	public Boolean isCustomerOwnedIndicator() {
		return customerOwnedIndicator;
	}

	public void setCustomerOwnedIndicator(Boolean customerOwnedIndicator) {
		this.customerOwnedIndicator = customerOwnedIndicator;
	}

	public Boolean isRentalEquipmentIndicator() {
		return rentalEquipmentIndicator;
	}

	public void setRentalEquipmentIndicator(Boolean rentalEquipmentIndicator) {
		this.rentalEquipmentIndicator = rentalEquipmentIndicator;
	}

	
}
