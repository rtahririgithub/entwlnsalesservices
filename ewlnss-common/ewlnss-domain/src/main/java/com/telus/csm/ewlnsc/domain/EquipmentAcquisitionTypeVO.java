package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class EquipmentAcquisitionTypeVO  implements Serializable{
	  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Boolean buyIndicator = null;

	  
	  private Boolean customerOwnedIndicator = null;

	  
	  private Boolean rentalEquipmentIndicator = null;

	  /**
	   * Get buyIndicator
	   * @return buyIndicator
	  **/
	  public Boolean isBuyIndicator() {
	    return buyIndicator;
	  }

	  public void setBuyIndicator(Boolean buyIndicator) {
	    this.buyIndicator = buyIndicator;
	  }

	  /**
	   * Get customerOwnedIndicator
	   * @return customerOwnedIndicator
	  **/

	  public Boolean isCustomerOwnedIndicator() {
	    return customerOwnedIndicator;
	  }

	  public void setCustomerOwnedIndicator(Boolean customerOwnedIndicator) {
	    this.customerOwnedIndicator = customerOwnedIndicator;
	  }

	  /**
	   * Get rentalEquipmentIndicator
	   * @return rentalEquipmentIndicator
	  **/


	  public Boolean isRentalEquipmentIndicator() {
	    return rentalEquipmentIndicator;
	  }

	  public void setRentalEquipmentIndicator(Boolean rentalEquipmentIndicator) {
	    this.rentalEquipmentIndicator = rentalEquipmentIndicator;
	  }

	}
