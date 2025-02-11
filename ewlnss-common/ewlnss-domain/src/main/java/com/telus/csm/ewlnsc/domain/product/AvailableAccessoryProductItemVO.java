package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.ExistingFFHEquipmentTypeVO;

public class AvailableAccessoryProductItemVO {

  //@SerializedName("productItemGroupCategories")
  private List<ProductItemGroupCategoryVO> productItemGroupCategories = null;
  
  //@SerializedName("productItemContstraintGroupList")
  private List<ProductItemConstraintGroupVO> productItemConstraintGroupList = null;
  
  //@SerializedName("equipments")
  private List<AvailableFFHEquipmentTypeVO> equipments = null;
  
  private List<ExistingFFHEquipmentTypeVO> existingEquipment;

  public List<ProductItemConstraintGroupVO> getProductItemConstraintGroupList() {
	  if (this.productItemConstraintGroupList == null)
		  this.productItemConstraintGroupList = new ArrayList<ProductItemConstraintGroupVO>();
	
	  return productItemConstraintGroupList;
  }
	
  public void setProductItemConstraintGroupList(
			List<ProductItemConstraintGroupVO> productItemConstraintGroupList) {
	  this.productItemConstraintGroupList = productItemConstraintGroupList;
  }


	public AvailableAccessoryProductItemVO productItemGroupCategories(List<ProductItemGroupCategoryVO> productItemGroupCategories) {
    this.productItemGroupCategories = productItemGroupCategories;
    return this;
  }

  public AvailableAccessoryProductItemVO addProductItemGroupCategoriesItem(ProductItemGroupCategoryVO productItemGroupCategoriesItem) {
    
    if (this.productItemGroupCategories == null) {
      this.productItemGroupCategories = new ArrayList<ProductItemGroupCategoryVO>();
    }
    
    this.productItemGroupCategories.add(productItemGroupCategoriesItem);
    return this;
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
  
  public AvailableAccessoryProductItemVO equipments(List<AvailableFFHEquipmentTypeVO> equipments) {
    this.equipments = equipments;
    return this;
  }

  public AvailableAccessoryProductItemVO addEquipmentsItem(AvailableFFHEquipmentTypeVO equipmentsItem) {
    
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
    AvailableTelevisionProductItem availableTelevisionProductItem = (AvailableTelevisionProductItem) o;
    return Objects.equals(this.productItemGroupCategories, availableTelevisionProductItem.productItemGroupCategories) &&
        Objects.equals(this.equipments, availableTelevisionProductItem.equipments);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(productItemGroupCategories, equipments);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableAccessoryProductItem {\n");
    
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

public List<ExistingFFHEquipmentTypeVO> getExistingEquipment() {
	return existingEquipment;
}

public void setExistingEquipment(List<ExistingFFHEquipmentTypeVO> existingEquipment) {
	this.existingEquipment = existingEquipment;
}

  
}



