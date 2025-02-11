package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.ExistingFFHEquipmentTypeVO;

public class AvailableHomePhoneProductItemVO {

  //@SerializedName("productItemGroupCategories")
  private List<ProductItemGroupCategoryVO> productItemGroupCategories = null;
  
  private List<ExistingFFHEquipmentTypeVO> existingEquipment;
  
  public AvailableHomePhoneProductItemVO productItemGroupCategories(List<ProductItemGroupCategoryVO> productItemGroupCategories) {
    this.productItemGroupCategories = productItemGroupCategories;
    return this;
  }

  public AvailableHomePhoneProductItemVO addProductItemGroupCategoriesItem(ProductItemGroupCategoryVO productItemGroupCategoriesItem) {
    
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
  
  /*
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvailableHomePhoneProductItem availableHomePhoneProductItem = (AvailableHomePhoneProductItem) o;
    return Objects.equals(this.productItemGroupCategories, availableHomePhoneProductItem.productItemGroupCategories);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(productItemGroupCategories);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableHomePhoneProductItem {\n");
    
    sb.append("    productItemGroupCategories: ").append(toIndentedString(productItemGroupCategories)).append("\n");
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



