package com.telus.csm.ewlnsc.domain.product;

public class MarketOfferClassificationVO {

  //@SerializedName("addOnInd")
  private Boolean addOnInd = null;
  
  //@SerializedName("sweetenerInd")
  private Boolean sweetenerInd = null;
  
  //@SerializedName("callingFeatureInd")
  private Boolean callingFeatureInd = null;
  
  //@SerializedName("productComponentInd")
  private Boolean productComponentInd = null;
  
  //@SerializedName("discountInd")
  private Boolean discountInd = null;
  
  public MarketOfferClassificationVO addOnInd(Boolean addOnInd) {
    this.addOnInd = addOnInd;
    return this;
  }

  
  /**
  * Get addOnInd
  * @return addOnInd
  **/
  //@ApiModelProperty(value = "")
  public Boolean isAddOnInd() {
    return addOnInd;
  }
  public void setAddOnInd(Boolean addOnInd) {
    this.addOnInd = addOnInd;
  }
  
  public MarketOfferClassificationVO sweetenerInd(Boolean sweetenerInd) {
    this.sweetenerInd = sweetenerInd;
    return this;
  }

  
  /**
  * Represents the product item is a sweetener
  * @return sweetenerInd
  **/
  //@ApiModelProperty(value = "Represents the product item is a sweetener")
  public Boolean isSweetenerInd() {
    return sweetenerInd;
  }
  public void setSweetenerInd(Boolean sweetenerInd) {
    this.sweetenerInd = sweetenerInd;
  }
  
  public MarketOfferClassificationVO callingFeatureInd(Boolean callingFeatureInd) {
    this.callingFeatureInd = callingFeatureInd;
    return this;
  }

  
  /**
  * Represents the product item is a calling feature
  * @return callingFeatureInd
  **/
  //@ApiModelProperty(value = "Represents the product item is a calling feature")
  public Boolean isCallingFeatureInd() {
    return callingFeatureInd;
  }
  public void setCallingFeatureInd(Boolean callingFeatureInd) {
    this.callingFeatureInd = callingFeatureInd;
  }
  
  public MarketOfferClassificationVO productComponentInd(Boolean productComponentInd) {
    this.productComponentInd = productComponentInd;
    return this;
  }

  
  /**
  * Represents the product item is a product component from per the market offer
  * @return productComponentInd
  **/
  //@ApiModelProperty(value = "Represents the product item is a product component from per the market offer")
  public Boolean isProductComponentInd() {
    return productComponentInd;
  }
  public void setProductComponentInd(Boolean productComponentInd) {
    this.productComponentInd = productComponentInd;
  }
  
  public MarketOfferClassificationVO discountInd(Boolean discountInd) {
    this.discountInd = discountInd;
    return this;
  }

  
  /**
  * Represents the product item is a dpiscount promotion
  * @return discountInd
  **/
  //@ApiModelProperty(value = "Represents the product item is a dpiscount promotion")
  public Boolean isDiscountInd() {
    return discountInd;
  }
  public void setDiscountInd(Boolean discountInd) {
    this.discountInd = discountInd;
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
    MarketOfferClassification marketOfferClassification = (MarketOfferClassification) o;
    return Objects.equals(this.addOnInd, marketOfferClassification.addOnInd) &&
        Objects.equals(this.sweetenerInd, marketOfferClassification.sweetenerInd) &&
        Objects.equals(this.callingFeatureInd, marketOfferClassification.callingFeatureInd) &&
        Objects.equals(this.productComponentInd, marketOfferClassification.productComponentInd) &&
        Objects.equals(this.discountInd, marketOfferClassification.discountInd);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(addOnInd, sweetenerInd, callingFeatureInd, productComponentInd, discountInd);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MarketOfferClassification {\n");
    
    sb.append("    addOnInd: ").append(toIndentedString(addOnInd)).append("\n");
    sb.append("    sweetenerInd: ").append(toIndentedString(sweetenerInd)).append("\n");
    sb.append("    callingFeatureInd: ").append(toIndentedString(callingFeatureInd)).append("\n");
    sb.append("    productComponentInd: ").append(toIndentedString(productComponentInd)).append("\n");
    sb.append("    discountInd: ").append(toIndentedString(discountInd)).append("\n");
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



