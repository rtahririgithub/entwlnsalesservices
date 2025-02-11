package com.telus.csm.ewlnsc.domain.product;

public class ContextAttributeVO {

  //@SerializedName("attributeTypeTxt")
  private String attributeTypeTxt = null;
  
  //@SerializedName("attributeValueTxt")
  private String attributeValueTxt = null;
  
  public ContextAttributeVO attributeTypeTxt(String attributeTypeTxt) {
    this.attributeTypeTxt = attributeTypeTxt;
    return this;
  }

  
  /**
  * Get attributeTypeTxt
  * @return attributeTypeTxt
  **/
  //@ApiModelProperty(value = "")
  public String getAttributeTypeTxt() {
    return attributeTypeTxt;
  }
  public void setAttributeTypeTxt(String attributeTypeTxt) {
    this.attributeTypeTxt = attributeTypeTxt;
  }
  
  public ContextAttributeVO attributeValueTxt(String attributeValueTxt) {
    this.attributeValueTxt = attributeValueTxt;
    return this;
  }

  
  /**
  * Get attributeValueTxt
  * @return attributeValueTxt
  **/
  //@ApiModelProperty(value = "")
  public String getAttributeValueTxt() {
    return attributeValueTxt;
  }
  public void setAttributeValueTxt(String attributeValueTxt) {
    this.attributeValueTxt = attributeValueTxt;
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
    ContextAttribute contextAttribute = (ContextAttribute) o;
    return Objects.equals(this.attributeTypeTxt, contextAttribute.attributeTypeTxt) &&
        Objects.equals(this.attributeValueTxt, contextAttribute.attributeValueTxt);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(attributeTypeTxt, attributeValueTxt);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContextAttribute {\n");
    
    sb.append("    attributeTypeTxt: ").append(toIndentedString(attributeTypeTxt)).append("\n");
    sb.append("    attributeValueTxt: ").append(toIndentedString(attributeValueTxt)).append("\n");
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



