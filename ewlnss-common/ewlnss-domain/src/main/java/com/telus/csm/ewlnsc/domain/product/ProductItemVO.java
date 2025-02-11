package com.telus.csm.ewlnsc.domain.product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CharacteristicVO;

public class ProductItemVO extends ProductItemBaseVO {

  //@SerializedName("associatedProductItems")
  private List<AssociatedProductItemVO> associatedProductItems = null;
  private List<ServiceConstraintVO> serviceConstraints = null;
  private String serviceTypeCode = null;
  private Boolean packEligibleItemInd = null;  // Gary - 2018-09-26
  private String productSerialNumber;
  private BigInteger itemRankNum;
  private Boolean priceValueSetFinishedInd;   //a flag to check if this item is already being set values
  private String salesCategoryServiceTypeCd = null; //NWLN-7789
  private String[] categoryList;
  private List<CharacteristicVO> characteristics; //FIFA SHS changes: product level characteristic

  public List<CharacteristicVO> getCharacteristics() {
  	if (characteristics==null) {
  		characteristics =new ArrayList<CharacteristicVO>();
  	}
  	return characteristics;
  }

  public void setCharacteristics(List<CharacteristicVO> characteristics) {
  	this.characteristics = characteristics;
  }
  
  public String getProductSerialNumber() {
	return productSerialNumber;
}

public void setProductSerialNumber(String productSerialNumber) {
	this.productSerialNumber = productSerialNumber;
}

public Boolean getPackEligibleItemInd() {
	return packEligibleItemInd;
  }

  public void setPackEligibleItemInd(Boolean packEligibleItemInd) {
	this.packEligibleItemInd = packEligibleItemInd;
  }

  public ProductItemVO associatedProductItems(List<AssociatedProductItemVO> associatedProductItems) {
    this.associatedProductItems = associatedProductItems;
    return this;
  }

  public ProductItemVO addAssociatedProductItemsItem(AssociatedProductItemVO associatedProductItemsItem) {
    
    if (this.associatedProductItems == null) {
      this.associatedProductItems = new ArrayList<AssociatedProductItemVO>();
    }
    
    this.associatedProductItems.add(associatedProductItemsItem);
    return this;
  }
  
  public void setServiceConstraints(List<ServiceConstraintVO> serviceConstraints) {
	  this.serviceConstraints = serviceConstraints;
  }
  
  public List<ServiceConstraintVO> getServiceConstraints() {
	  return serviceConstraints;
  }
  
  public void setServiceTypeCode(String serviceTypeCode) {
	  this.serviceTypeCode = serviceTypeCode;
  }
  
  public String getServiceTypeCode() {
	  return serviceTypeCode;
  }
  
	public BigInteger getItemRankNum() {
		return itemRankNum;
	}

	public void setItemRankNum(BigInteger itemRankNum) {
		this.itemRankNum = itemRankNum;
	}
	
	public Boolean getPriceValueSetFinishedInd() {
		return priceValueSetFinishedInd;
	}

	public void setPriceValueSetFinishedInd(Boolean priceValueSetFinishedInd) {
		this.priceValueSetFinishedInd = priceValueSetFinishedInd;
	}

	public String getSalesCategoryServiceTypeCd() {
		return salesCategoryServiceTypeCd;
	}

	public void setSalesCategoryServiceTypeCd(String salesCategoryServiceTypeCd) {
		this.salesCategoryServiceTypeCd = salesCategoryServiceTypeCd;
	}

/**
  * Get associatedProductItems
  * @return associatedProductItems
  **/
  //@ApiModelProperty(value = "")
  public List<AssociatedProductItemVO> getAssociatedProductItems() {
    return associatedProductItems;
  }
  public void setAssociatedProductItems(List<AssociatedProductItemVO> associatedProductItems) {
    this.associatedProductItems = associatedProductItems;
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
    ProductItem productItem = (ProductItem) o;
    return Objects.equals(this.associatedProductItems, productItem.associatedProductItems) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(associatedProductItems, super.hashCode());
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductItem {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    associatedProductItems: ").append(toIndentedString(associatedProductItems)).append("\n");
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

public String[] getCategoryList() {
	return categoryList;
}

public void setCategoryList(String[] categoryList) {
	this.categoryList = categoryList;
}


  
}



