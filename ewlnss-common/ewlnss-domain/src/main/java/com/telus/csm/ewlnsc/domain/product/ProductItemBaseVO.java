
package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;

public class ProductItemBaseVO {

  //@SerializedName("productItemIdentifier")
  private ProductItemIdentifierVO productItemIdentifier = null;
  
  //@SerializedName("productCatalogueName")
  private List<MultilingualTextVO> productCatalogueName = null;
  
  //@SerializedName("productCatalogueDescription")
  private List<MultilingualTextVO> productCatalogueDescription = null;
  
  //@SerializedName("preSelectedInd")
  private Boolean preSelectedInd = null;
  
  //@SerializedName("mandatoryInd")
  private Boolean mandatoryInd = null;
  
  //@SerializedName("discountCode")
  private String discountCode = null;
  
  //@SerializedName("includedInd")
  private Boolean includedInd = null;
  
  private Boolean smartRingInd = null;
  
  //@SerializedName("eligibleForMinMaxPricingInd")
  private Boolean eligibleForMinMaxPricingInd = null;
  
  //@SerializedName("marketOfferClassification")
  private MarketOfferClassificationVO marketOfferClassification = null;
  
  //@SerializedName("productItemPriceCharge")
  private PriceVO productItemPriceCharge = null;
  
  //@SerializedName("productItemPriceDiscount")
  private PriceDiscountVO productItemPriceDiscount = null;
  
  //TODO: temporary fields, to be removed later on.
  private boolean carryOverInd;
  private boolean existingInd;
  private String action;
  
  
  public String getAction() {
	return action;
}


public void setAction(String action) {
	this.action = action;
}


public boolean isCarryOverInd() {
	return carryOverInd;
}


public void setCarryOverInd(boolean carryOverInd) {
	this.carryOverInd = carryOverInd;
}


public boolean isExistingInd() {
	return existingInd;
}


public void setExistingInd(boolean existingInd) {
	this.existingInd = existingInd;
}


public ProductItemBaseVO productItemIdentifier(ProductItemIdentifierVO productItemIdentifier) {
    this.productItemIdentifier = productItemIdentifier;
    return this;
  }

  
  /**
  * Get productItemIdentifier
  * @return productItemIdentifier
  **/
  //@ApiModelProperty(value = "")
  public ProductItemIdentifierVO getProductItemIdentifier() {
    return productItemIdentifier;
  }
  public void setProductItemIdentifier(ProductItemIdentifierVO productItemIdentifier) {
    this.productItemIdentifier = productItemIdentifier;
  }
  
  public ProductItemBaseVO productCatalogueName(List<MultilingualTextVO> productCatalogueName) {
    this.productCatalogueName = productCatalogueName;
    return this;
  }

  public ProductItemBaseVO addProductCatalogueNameItem(MultilingualTextVO productCatalogueNameItem) {
    
    if (this.productCatalogueName == null) {
      this.productCatalogueName = new ArrayList<MultilingualTextVO>();
    }
    
    this.productCatalogueName.add(productCatalogueNameItem);
    return this;
  }
  
  /**
  * Get productCatalogueName
  * @return productCatalogueName
  **/
  //@ApiModelProperty(value = "")
  public List<MultilingualTextVO> getProductCatalogueName() {
    return productCatalogueName;
  }
  public void setProductCatalogueName(List<MultilingualTextVO> productCatalogueName) {
    this.productCatalogueName = productCatalogueName;
  }
  
  public ProductItemBaseVO productCatalogueDescription(List<MultilingualTextVO> productCatalogueDescription) {
    this.productCatalogueDescription = productCatalogueDescription;
    return this;
  }

  public ProductItemBaseVO addProductCatalogueDescriptionItem(MultilingualTextVO productCatalogueDescriptionItem) {
    
    if (this.productCatalogueDescription == null) {
      this.productCatalogueDescription = new ArrayList<MultilingualTextVO>();
    }
    
    this.productCatalogueDescription.add(productCatalogueDescriptionItem);
    return this;
  }
  
  /**
  * Get productCatalogueDescription
  * @return productCatalogueDescription
  **/
  //@ApiModelProperty(value = "")
  public List<MultilingualTextVO> getProductCatalogueDescription() {
    return productCatalogueDescription;
  }
  public void setProductCatalogueDescription(List<MultilingualTextVO> productCatalogueDescription) {
    this.productCatalogueDescription = productCatalogueDescription;
  }
  
  public ProductItemBaseVO preSelectedInd(Boolean preSelectedInd) {
    this.preSelectedInd = preSelectedInd;
    return this;
  }

  
  /**
  * Get preSelectedInd
  * @return preSelectedInd
  **/
  //@ApiModelProperty(value = "")
  public Boolean isPreSelectedInd() {
    return preSelectedInd;
  }
  public void setPreSelectedInd(Boolean preSelectedInd) {
    this.preSelectedInd = preSelectedInd;
  }
  
  public ProductItemBaseVO mandatoryInd(Boolean mandatoryInd) {
    this.mandatoryInd = mandatoryInd;
    return this;
  }

  
  /**
  * Get mandatoryInd
  * @return mandatoryInd
  **/
  //@ApiModelProperty(value = "")
  public Boolean isMandatoryInd() {
    return mandatoryInd;
  }
  public void setMandatoryInd(Boolean mandatoryInd) {
    this.mandatoryInd = mandatoryInd;
  }
  
  public ProductItemBaseVO discountCode(String discountCode) {
    this.discountCode = discountCode;
    return this;
  }

  
  /**
  * Get discountCode
  * @return discountCode
  **/
  //@ApiModelProperty(value = "")
  public String getDiscountCode() {
    return discountCode;
  }
  public void setDiscountCode(String discountCode) {
    this.discountCode = discountCode;
  }
  
  public ProductItemBaseVO includedInd(Boolean includedInd) {
    this.includedInd = includedInd;
    return this;
  }

  
  /**
  * Get includedInd
  * @return includedInd
  **/
  //@ApiModelProperty(value = "")
  public Boolean isIncludedInd() {
    return includedInd;
  }
  public void setIncludedInd(Boolean includedInd) {
    this.includedInd = includedInd;
  }
  
	public ProductItemBaseVO smartRingInd(Boolean smartRingInd) {
		this.smartRingInd = smartRingInd;
		return this;
	}

	/**
	 * Get smartRingInd
	 * 
	 * @return smartRingInd
	 **/
	// @ApiModelProperty(value = "")
	public Boolean isSmartRingInd() {
		return smartRingInd;
	}

	public void setSmartRingInd(Boolean smartRingInd) {
		this.smartRingInd = smartRingInd;
	}


public ProductItemBaseVO marketOfferClassification(MarketOfferClassificationVO marketOfferClassification) {
    this.marketOfferClassification = marketOfferClassification;
    return this;
  }
  
  /**
   * Get eligibleForMinMaxPricingInd
   * @return eligibleForMinMaxPricingInd
   **/
   //@ApiModelProperty(value = "")
   public Boolean isEligibleForMinMaxPricingInd() {
     return eligibleForMinMaxPricingInd;
   }
   public void setEligibleForMinMaxPricingInd(Boolean eligibleForMinMaxPricingInd) {
     this.eligibleForMinMaxPricingInd = eligibleForMinMaxPricingInd;
   }
  
  /**
  * Get marketOfferClassification
  * @return marketOfferClassification
  **/
  //@ApiModelProperty(value = "")
  public MarketOfferClassificationVO getMarketOfferClassification() {
    return marketOfferClassification;
  }
  public void setMarketOfferClassification(MarketOfferClassificationVO marketOfferClassification) {
    this.marketOfferClassification = marketOfferClassification;
  }
  
  public ProductItemBaseVO productItemPriceCharge(PriceVO productItemPriceCharge) {
    this.productItemPriceCharge = productItemPriceCharge;
    return this;
  }

  
  /**
  * Get productItemPriceCharge
  * @return productItemPriceCharge
  **/
  //@ApiModelProperty(value = "")
  public PriceVO getProductItemPriceCharge() {
    return productItemPriceCharge;
  }
  public void setProductItemPriceCharge(PriceVO productItemPriceCharge) {
    this.productItemPriceCharge = productItemPriceCharge;
  }
  
  public ProductItemBaseVO productItemPriceDiscount(PriceDiscountVO productItemPriceDiscount) {
    this.productItemPriceDiscount = productItemPriceDiscount;
    return this;
  }

  
  /**
  * Get productItemPriceDiscount
  * @return productItemPriceDiscount
  **/
  //@ApiModelProperty(value = "")
  public PriceDiscountVO getProductItemPriceDiscount() {
    return productItemPriceDiscount;
  }
  public void setProductItemPriceDiscount(PriceDiscountVO productItemPriceDiscount) {
    this.productItemPriceDiscount = productItemPriceDiscount;
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
    ProductItemBase productItemBase = (ProductItemBase) o;
    return Objects.equals(this.productItemIdentifier, productItemBase.productItemIdentifier) &&
        Objects.equals(this.productCatalogueName, productItemBase.productCatalogueName) &&
        Objects.equals(this.productCatalogueDescription, productItemBase.productCatalogueDescription) &&
        Objects.equals(this.preSelectedInd, productItemBase.preSelectedInd) &&
        Objects.equals(this.mandatoryInd, productItemBase.mandatoryInd) &&
        Objects.equals(this.discountCode, productItemBase.discountCode) &&
        Objects.equals(this.includedInd, productItemBase.includedInd) &&
        Objects.equals(this.marketOfferClassification, productItemBase.marketOfferClassification) &&
        Objects.equals(this.productItemPriceCharge, productItemBase.productItemPriceCharge) &&
        Objects.equals(this.productItemPriceDiscount, productItemBase.productItemPriceDiscount);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(productItemIdentifier, productCatalogueName, productCatalogueDescription, preSelectedInd, mandatoryInd, discountCode, includedInd, marketOfferClassification, productItemPriceCharge, productItemPriceDiscount);
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductItemBase {\n");
    
    sb.append("    productItemIdentifier: ").append(toIndentedString(productItemIdentifier)).append("\n");
    sb.append("    productCatalogueName: ").append(toIndentedString(productCatalogueName)).append("\n");
    sb.append("    productCatalogueDescription: ").append(toIndentedString(productCatalogueDescription)).append("\n");
    sb.append("    preSelectedInd: ").append(toIndentedString(preSelectedInd)).append("\n");
    sb.append("    mandatoryInd: ").append(toIndentedString(mandatoryInd)).append("\n");
    sb.append("    discountCode: ").append(toIndentedString(discountCode)).append("\n");
    sb.append("    includedInd: ").append(toIndentedString(includedInd)).append("\n");
    sb.append("    smartRingInd: ").append(toIndentedString(smartRingInd)).append("\n");
    sb.append("    marketOfferClassification: ").append(toIndentedString(marketOfferClassification)).append("\n");
    sb.append("    productItemPriceCharge: ").append(toIndentedString(productItemPriceCharge)).append("\n");
    sb.append("    productItemPriceDiscount: ").append(toIndentedString(productItemPriceDiscount)).append("\n");
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



