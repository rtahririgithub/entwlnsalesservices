package com.telus.csm.ewlnsc.domain.product;

public class QuantityAllowedVO {

  //@SerializedName("minimumQty")
  private Integer minimumQty = null;
  
  //@SerializedName("maximumQty")
  private Integer maximumQty = null;
  
  public QuantityAllowedVO minimumQty(Integer minimumQty) {
    this.minimumQty = minimumQty;
    return this;
  }

  
  /**
  * Get minimumQty
  * @return minimumQty
  **/
  //@ApiModelProperty(value = "")
  public Integer getMinimumQty() {
    return minimumQty;
  }
  public void setMinimumQty(Integer minimumQty) {
    this.minimumQty = minimumQty;
  }
  
  public QuantityAllowedVO maximumQty(Integer maximumQty) {
    this.maximumQty = maximumQty;
    return this;
  }

  
  /**
  * Get maximumQty
  * @return maximumQty
  **/
  //@ApiModelProperty(value = "")
  public Integer getMaximumQty() {
    return maximumQty;
  }
  public void setMaximumQty(Integer maximumQty) {
    this.maximumQty = maximumQty;
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
    QuantityAllowed quantityAllowed = (QuantityAllowed) o;
    return Objects.equals(this.minimumQty, quantityAllowed.minimumQty) &&
        Objects.equals(this.maximumQty, quantityAllowed.maximumQty);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(minimumQty, maximumQty);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantityAllowed {\n");
    
    sb.append("    minimumQty: ").append(toIndentedString(minimumQty)).append("\n");
    sb.append("    maximumQty: ").append(toIndentedString(maximumQty)).append("\n");
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



