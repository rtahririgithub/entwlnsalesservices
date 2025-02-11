
package com.telus.csm.ewlnsc.domain;

public class ProductBaseTypeVO {

  //@SerializedName("productOrderType")
  private ProductOrderTypeVO productOrderType = null;
  
  //@SerializedName("selectedContractTermCd")
  private String selectedContractTermCd = null;
  
  public ProductBaseTypeVO productOrderType(ProductOrderTypeVO productOrderType) {
    this.productOrderType = productOrderType;
    return this;
  }

  
  /**
  * Get productOrderType
  * @return productOrderType
  **/
  //@ApiModelProperty(value = "")
  public ProductOrderTypeVO getProductOrderType() {
    return productOrderType;
  }
  public void setProductOrderType(ProductOrderTypeVO productOrderType) {
    this.productOrderType = productOrderType;
  }
  
  public ProductBaseTypeVO selectedContractTermCd(String selectedContractTermCd) {
    this.selectedContractTermCd = selectedContractTermCd;
    return this;
  }

  
  /**
  * Get selectedContractTermCd
  * @return selectedContractTermCd
  **/
  //@ApiModelProperty(value = "")
  public String getSelectedContractTermCd() {
    return selectedContractTermCd;
  }
  public void setSelectedContractTermCd(String selectedContractTermCd) {
    this.selectedContractTermCd = selectedContractTermCd;
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
    ProductBaseType productBaseType = (ProductBaseType) o;
    return Objects.equals(this.productOrderType, productBaseType.productOrderType) &&
        Objects.equals(this.selectedContractTermCd, productBaseType.selectedContractTermCd);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(productOrderType, selectedContractTermCd);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductBaseType {\n");
    
    sb.append("    productOrderType: ").append(toIndentedString(productOrderType)).append("\n");
    sb.append("    selectedContractTermCd: ").append(toIndentedString(selectedContractTermCd)).append("\n");
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



