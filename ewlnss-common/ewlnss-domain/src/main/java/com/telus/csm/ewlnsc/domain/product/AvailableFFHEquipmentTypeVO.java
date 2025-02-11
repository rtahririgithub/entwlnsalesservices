package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

public class AvailableFFHEquipmentTypeVO {

  //@SerializedName("quantityAllowed")
  private QuantityAllowedVO quantityAllowed = null;
  
  //@SerializedName("buyEquipmentList")
  private List<FFHEquipmentItemVO> buyEquipmentList = null;
  
  //@SerializedName("rentalEquipmentList")
  private List<FFHEquipmentItemVO> rentalEquipmentList = null;
  
  //@SerializedName("clientOwnedEquipmentList")
  private List<FFHEquipmentItemVO> clientOwnedEquipmentList = null;
  
  //@SerializedName("clientOwnedEquipmentList")
  private List<FFHEquipmentItemVO> existingEquipmentList = null;
  
  public List<FFHEquipmentItemVO> getExistingEquipmentList() {
	  if(existingEquipmentList != null) return existingEquipmentList;
	  
	  List<FFHEquipmentItemVO> existingEquipmentList = new ArrayList<FFHEquipmentItemVO>();
	  if (buyEquipmentList != null) {
		  for (FFHEquipmentItemVO equipment : buyEquipmentList) {
			  if (equipment.isExisting()) {
				  existingEquipmentList.add(equipment);
			  }
		  }
	  }
	  if (rentalEquipmentList != null) {
		  for (FFHEquipmentItemVO equipment : rentalEquipmentList) {
			  if (equipment.isExisting()) {
				  existingEquipmentList.add(equipment);
			  }
		  }
	  }
	  if (clientOwnedEquipmentList != null) {
		  for (FFHEquipmentItemVO equipment : clientOwnedEquipmentList) {
			  if (equipment.isExisting()) {
				  existingEquipmentList.add(equipment);
			  }
		  }
	  }
	  
	  return existingEquipmentList;
}
/**
  * Get quantityAllowed
  * @return quantityAllowed
  **/
  //@ApiModelProperty(value = "")
  public QuantityAllowedVO getQuantityAllowed() {
    return quantityAllowed;
  }
  public void setQuantityAllowed(QuantityAllowedVO quantityAllowed) {
    this.quantityAllowed = quantityAllowed;
  }
  
  public AvailableFFHEquipmentTypeVO buyEquipmentList(List<FFHEquipmentItemVO> buyEquipmentList) {
    this.buyEquipmentList = buyEquipmentList;
    return this;
  }

  public AvailableFFHEquipmentTypeVO addBuyEquipmentListItem(FFHEquipmentItemVO buyEquipmentListItem) {
    
    if (this.buyEquipmentList == null) {
      this.buyEquipmentList = new ArrayList<FFHEquipmentItemVO>();
    }
    
    this.buyEquipmentList.add(buyEquipmentListItem);
    return this;
  }
  
  /**
  * Get buyEquipmentList
  * @return buyEquipmentList
  **/
  //@ApiModelProperty(value = "")
  public List<FFHEquipmentItemVO> getBuyEquipmentList() {
    return buyEquipmentList;
  }
  public void setBuyEquipmentList(List<FFHEquipmentItemVO> buyEquipmentList) {
    this.buyEquipmentList = buyEquipmentList;
  }
  
  public AvailableFFHEquipmentTypeVO rentalEquipmentList(List<FFHEquipmentItemVO> rentalEquipmentList) {
    this.rentalEquipmentList = rentalEquipmentList;
    return this;
  }

  public AvailableFFHEquipmentTypeVO addRentalEquipmentListItem(FFHEquipmentItemVO rentalEquipmentListItem) {
    
    if (this.rentalEquipmentList == null) {
      this.rentalEquipmentList = new ArrayList<FFHEquipmentItemVO>();
    }
    
    this.rentalEquipmentList.add(rentalEquipmentListItem);
    return this;
  }
  
  /**
  * Get rentalEquipmentList
  * @return rentalEquipmentList
  **/
  //@ApiModelProperty(value = "")
  public List<FFHEquipmentItemVO> getRentalEquipmentList() {
    return rentalEquipmentList;
  }
  public void setRentalEquipmentList(List<FFHEquipmentItemVO> rentalEquipmentList) {
    this.rentalEquipmentList = rentalEquipmentList;
  }
  
  public AvailableFFHEquipmentTypeVO clientOwnedEquipmentList(List<FFHEquipmentItemVO> clientOwnedEquipmentList) {
    this.clientOwnedEquipmentList = clientOwnedEquipmentList;
    return this;
  }

  public AvailableFFHEquipmentTypeVO addClientOwnedEquipmentListItem(FFHEquipmentItemVO clientOwnedEquipmentListItem) {
    
    if (this.clientOwnedEquipmentList == null) {
      this.clientOwnedEquipmentList = new ArrayList<FFHEquipmentItemVO>();
    }
    
    this.clientOwnedEquipmentList.add(clientOwnedEquipmentListItem);
    return this;
  }
  
  /**
  * Get clientOwnedEquipmentList
  * @return clientOwnedEquipmentList
  **/
  //@ApiModelProperty(value = "")
  public List<FFHEquipmentItemVO> getClientOwnedEquipmentList() {
    return clientOwnedEquipmentList;
  }
  public void setClientOwnedEquipmentList(List<FFHEquipmentItemVO> clientOwnedEquipmentList) {
    this.clientOwnedEquipmentList = clientOwnedEquipmentList;
  }

public AvailableFFHEquipmentTypeVO quantityAllowed(QuantityAllowedVO quantityAllowed) {
    this.quantityAllowed = quantityAllowed;
    return this;
  }

  /*
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvailableFFHEquipmentType availableFFHEquipmentType = (AvailableFFHEquipmentType) o;
    return Objects.equals(this.quantityAllowed, availableFFHEquipmentType.quantityAllowed) &&
        Objects.equals(this.buyEquipmentList, availableFFHEquipmentType.buyEquipmentList) &&
        Objects.equals(this.rentalEquipmentList, availableFFHEquipmentType.rentalEquipmentList) &&
        Objects.equals(this.clientOwnedEquipmentList, availableFFHEquipmentType.clientOwnedEquipmentList);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(quantityAllowed, buyEquipmentList, rentalEquipmentList, clientOwnedEquipmentList);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableFFHEquipmentType {\n");
    
    sb.append("    quantityAllowed: ").append(toIndentedString(quantityAllowed)).append("\n");
    sb.append("    buyEquipmentList: ").append(toIndentedString(buyEquipmentList)).append("\n");
    sb.append("    rentalEquipmentList: ").append(toIndentedString(rentalEquipmentList)).append("\n");
    sb.append("    clientOwnedEquipmentList: ").append(toIndentedString(clientOwnedEquipmentList)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

	public void setExistingEquipmentList(List<FFHEquipmentItemVO> existingEquipmentList) {
		this.existingEquipmentList = existingEquipmentList;
	}
	
	public void addExistingEquipment(FFHEquipmentItemVO existingEquipment) {
		if(existingEquipmentList == null)
			existingEquipmentList = new ArrayList<FFHEquipmentItemVO>();
		existingEquipmentList.add(existingEquipment);
	}
	
	public boolean hasExistingEquipment() {
		if(existingEquipmentList != null && !existingEquipmentList.isEmpty())
			return true;
		else
			return false;
	}

  
}



