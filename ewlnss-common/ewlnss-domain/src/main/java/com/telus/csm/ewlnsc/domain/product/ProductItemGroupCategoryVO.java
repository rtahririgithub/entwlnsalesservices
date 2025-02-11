package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.GroupCategoryVO;

public class ProductItemGroupCategoryVO {

  //@SerializedName("groupCategory")
  private GroupCategoryVO groupCategory = null;
  
  //@SerializedName("productItems")
  private List<ProductItemVO> productItems = null;
  
  //@SerializedName("quantityAllowed")
  private QuantityAllowedVO quantityAllowed = null;
  
  public ProductItemGroupCategoryVO groupCategory(GroupCategoryVO groupCategory) {
    this.groupCategory = groupCategory;
    return this;
  }

  
  /**
  * Get groupCategory
  * @return groupCategory
  **/
  //@ApiModelProperty(value = "")
  public GroupCategoryVO getGroupCategory() {
    return groupCategory;
  }
  public void setGroupCategory(GroupCategoryVO groupCategory) {
    this.groupCategory = groupCategory;
  }
  
  public ProductItemGroupCategoryVO productItems(List<ProductItemVO> productItems) {
    this.productItems = productItems;
    return this;
  }

  public ProductItemGroupCategoryVO addProductItemsItem(ProductItemVO productItemsItem) {
    
    if (this.productItems == null) {
      this.productItems = new ArrayList<ProductItemVO>();
    }
    
    this.productItems.add(productItemsItem);
    return this;
  }
  
  /**
  * Get productItems
  * @return productItems
  **/
  //@ApiModelProperty(value = "")
  public List<ProductItemVO> getProductItems() {
    return productItems;
  }
  public void setProductItems(List<ProductItemVO> productItems) {
    this.productItems = productItems;
  }
  
  public ProductItemGroupCategoryVO quantityAllowed(QuantityAllowedVO quantityAllowed) {
    this.quantityAllowed = quantityAllowed;
    return this;
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
  
  /*
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductItemGroupCategory productItemGroupCategory = (ProductItemGroupCategory) o;
    return Objects.equals(this.groupCategory, productItemGroupCategory.groupCategory) &&
        Objects.equals(this.productItems, productItemGroupCategory.productItems) &&
        Objects.equals(this.quantityAllowed, productItemGroupCategory.quantityAllowed);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(groupCategory, productItems, quantityAllowed);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductItemGroupCategory {\n");
    
    sb.append("    groupCategory: ").append(toIndentedString(groupCategory)).append("\n");
    sb.append("    productItems: ").append(toIndentedString(productItems)).append("\n");
    sb.append("    quantityAllowed: ").append(toIndentedString(quantityAllowed)).append("\n");
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

  
}



