package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;


import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;

public class AvailableInternetProductItemVO {

  //@SerializedName("productItemGroupCategories")
  private List<ProductItemGroupCategoryVO> productItemGroupCategories = null;
  
  //@SerializedName("equipments")
  private List<AvailableFFHEquipmentTypeVO> equipments = null;
  
  private List<FFHEquipmentTypeVO> existingEquipment;
  
  public AvailableInternetProductItemVO productItemGroupCategories(List<ProductItemGroupCategoryVO> productItemGroupCategories) {
    this.productItemGroupCategories = productItemGroupCategories;
    return this;
  }

  public AvailableInternetProductItemVO addProductItemGroupCategoriesItem(ProductItemGroupCategoryVO productItemGroupCategoriesItem) {
    
    if (this.productItemGroupCategories == null) {
      this.productItemGroupCategories = new ArrayList<ProductItemGroupCategoryVO>();
    }
    
    this.productItemGroupCategories.add(productItemGroupCategoriesItem);
    return this;
  }
  
  // Gary - 2018-09-24 QC67396 
  //@SerializedName("productItemContstraintGroupList")
  private List<ProductItemConstraintGroupVO> productItemConstraintGroupList = null;
  
  public List<ProductItemConstraintGroupVO> getProductItemConstraintGroupList() {
	  if (this.productItemConstraintGroupList == null)
		  this.productItemConstraintGroupList = new ArrayList<ProductItemConstraintGroupVO>();
	  
	  return productItemConstraintGroupList;
  }
	
  public void setProductItemConstraintGroupList(
			List<ProductItemConstraintGroupVO> productItemConstraintGroupList) {
	  this.productItemConstraintGroupList = productItemConstraintGroupList;
  }
  
  /**
  * Get productItemGroupCategories
  * @return productItemGroupCategories
  **/
  //@ApiModelProperty(value = "")
  public List<ProductItemGroupCategoryVO> getProductItemGroupCategories() {
    return productItemGroupCategories;
  }
  public void setProductItemGroupCategories(List<ProductItemGroupCategoryVO> productItemGroupCategories) {
    this.productItemGroupCategories = productItemGroupCategories;
  }
  
  public AvailableInternetProductItemVO equipments(List<AvailableFFHEquipmentTypeVO> equipments) {
    this.equipments = equipments;
    return this;
  }

  public AvailableInternetProductItemVO addEquipmentsItem(AvailableFFHEquipmentTypeVO equipmentsItem) {
    
    if (this.equipments == null) {
      this.equipments = new ArrayList<AvailableFFHEquipmentTypeVO>();
    }
    
    this.equipments.add(equipmentsItem);
    return this;
  }
  
  /**
  * Get equipments
  * @return equipments
  **/
  //@ApiModelProperty(value = "")
  public List<AvailableFFHEquipmentTypeVO> getEquipments() {
    return equipments;
  }
  public void setEquipments(List<AvailableFFHEquipmentTypeVO> equipments) {
    this.equipments = equipments;
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
    AvailableInternetProductItem availableInternetProductItem = (AvailableInternetProductItem) o;
    return Objects.equals(this.productItemGroupCategories, availableInternetProductItem.productItemGroupCategories) &&
        Objects.equals(this.equipments, availableInternetProductItem.equipments);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(productItemGroupCategories, equipments);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableInternetProductItem {\n");
    
    sb.append("    productItemGroupCategories: ").append(toIndentedString(productItemGroupCategories)).append("\n");
    sb.append("    equipments: ").append(toIndentedString(equipments)).append("\n");
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

public List<FFHEquipmentTypeVO> getExistingEquipment() {
	return existingEquipment;
}

public void setExistingEquipment(List<FFHEquipmentTypeVO> existingEquipment) {
	this.existingEquipment = existingEquipment;
}

  
}



