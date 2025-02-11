package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * FFHProductBaseType
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaClientCodegen", date = "2018-09-06T15:45:57.895-04:00[America/New_York]")
public class FFHProductBaseTypeVO extends ProductBaseTypeVO {

  //@SerializedName("productComponents")
  private List<ProductComponentVO> productComponents = null;
  
  //@SerializedName("equipments")
  private List<FFHEquipmentTypeVO> equipments = null;
  
  //@SerializedName("addOns")
  private List<FFHProductPlanAddOnTypeVO> addOns = null;
  
  //@SerializedName("discounts")
  private List<FFHDiscountTypeVO> discounts = null;
  
  //@SerializedName("sweeteners")
  private List<FFHSweetenerTypeVO> sweeteners = null;
  
  //@SerializedName("appointmentDetail")
  private AppointmentDetailTypeVO appointmentDetail = null;
  
  private List<AdditionalProductItemTypeVO> additionalProductItemList;
  
  public FFHProductBaseTypeVO productComponents(List<ProductComponentVO> productComponents) {
    this.productComponents = productComponents;
    return this;
  }

  public FFHProductBaseTypeVO addProductComponentsItem(ProductComponentVO productComponentsItem) {
    
    if (this.productComponents == null) {
      this.productComponents = new ArrayList<ProductComponentVO>();
    }
    
    this.productComponents.add(productComponentsItem);
    return this;
  }
  
  /**
  * Get productComponents
  * @return productComponents
  **/
  //@ApiModelProperty(value = "")
  public List<ProductComponentVO> getProductComponents() {
    return productComponents;
  }
  public void setProductComponents(List<ProductComponentVO> productComponents) {
    this.productComponents = productComponents;
  }
  
  public FFHProductBaseTypeVO equipments(List<FFHEquipmentTypeVO> equipments) {
    this.equipments = equipments;
    return this;
  }

  public FFHProductBaseTypeVO addEquipmentsItem(FFHEquipmentTypeVO equipmentsItem) {
    
    if (this.equipments == null) {
      this.equipments = new ArrayList<FFHEquipmentTypeVO>();
    }
    
    this.equipments.add(equipmentsItem);
    return this;
  }
  
  /**
  * Get equipments
  * @return equipments
  **/
  //@ApiModelProperty(value = "")
  public List<FFHEquipmentTypeVO> getEquipments() {
    return equipments;
  }
  public void setEquipments(List<FFHEquipmentTypeVO> equipments) {
    this.equipments = equipments;
  }
  
  public FFHProductBaseTypeVO addOns(List<FFHProductPlanAddOnTypeVO> addOns) {
    this.addOns = addOns;
    return this;
  }

  public FFHProductBaseTypeVO addAddOnsItem(FFHProductPlanAddOnTypeVO addOnsItem) {
    
    if (this.addOns == null) {
      this.addOns = new ArrayList<FFHProductPlanAddOnTypeVO>();
    }
    
    this.addOns.add(addOnsItem);
    return this;
  }
  
  /**
  * Get addOns
  * @return addOns
  **/
  //@ApiModelProperty(value = "")
  public List<FFHProductPlanAddOnTypeVO> getAddOns() {
    return addOns;
  }
  public void setAddOns(List<FFHProductPlanAddOnTypeVO> addOns) {
    this.addOns = addOns;
  }
  
  public FFHProductBaseTypeVO discounts(List<FFHDiscountTypeVO> discounts) {
    this.discounts = discounts;
    return this;
  }

  public FFHProductBaseTypeVO addDiscountsItem(FFHDiscountTypeVO discountsItem) {
    
    if (this.discounts == null) {
      this.discounts = new ArrayList<FFHDiscountTypeVO>();
    }
    
    this.discounts.add(discountsItem);
    return this;
  }
  
  /**
  * Get discounts
  * @return discounts
  **/
  //@ApiModelProperty(value = "")
  public List<FFHDiscountTypeVO> getDiscounts() {
    return discounts;
  }
  public void setDiscounts(List<FFHDiscountTypeVO> discounts) {
    this.discounts = discounts;
  }
  
  public FFHProductBaseTypeVO sweeteners(List<FFHSweetenerTypeVO> sweeteners) {
    this.sweeteners = sweeteners;
    return this;
  }

  public FFHProductBaseTypeVO addSweetenersItem(FFHSweetenerTypeVO sweetenersItem) {
    
    if (this.sweeteners == null) {
      this.sweeteners = new ArrayList<FFHSweetenerTypeVO>();
    }
    
    this.sweeteners.add(sweetenersItem);
    return this;
  }
  
  /**
  * Get sweeteners
  * @return sweeteners
  **/
  //@ApiModelProperty(value = "")
  public List<FFHSweetenerTypeVO> getSweeteners() {
    return sweeteners;
  }
  public void setSweeteners(List<FFHSweetenerTypeVO> sweeteners) {
    this.sweeteners = sweeteners;
  }
  
  public FFHProductBaseTypeVO appointmentDetail(AppointmentDetailTypeVO appointmentDetail) {
    this.appointmentDetail = appointmentDetail;
    return this;
  }

  
  /**
  * Get appointmentDetail
  * @return appointmentDetail
  **/
  //@ApiModelProperty(value = "")
  public AppointmentDetailTypeVO getAppointmentDetail() {
    return appointmentDetail;
  }
  public void setAppointmentDetail(AppointmentDetailTypeVO appointmentDetail) {
    this.appointmentDetail = appointmentDetail;
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
    FFHProductBaseType ffHProductBaseType = (FFHProductBaseType) o;
    return Objects.equals(this.productComponents, ffHProductBaseType.productComponents) &&
        Objects.equals(this.equipments, ffHProductBaseType.equipments) &&
        Objects.equals(this.addOns, ffHProductBaseType.addOns) &&
        Objects.equals(this.discounts, ffHProductBaseType.discounts) &&
        Objects.equals(this.sweeteners, ffHProductBaseType.sweeteners) &&
        Objects.equals(this.appointmentDetail, ffHProductBaseType.appointmentDetail) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(productComponents, equipments, addOns, discounts, sweeteners, appointmentDetail, super.hashCode());
  }
  
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FFHProductBaseType {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    productComponents: ").append(toIndentedString(productComponents)).append("\n");
    sb.append("    equipments: ").append(toIndentedString(equipments)).append("\n");
    sb.append("    addOns: ").append(toIndentedString(addOns)).append("\n");
    sb.append("    discounts: ").append(toIndentedString(discounts)).append("\n");
    sb.append("    sweeteners: ").append(toIndentedString(sweeteners)).append("\n");
    sb.append("    appointmentDetail: ").append(toIndentedString(appointmentDetail)).append("\n");
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

public List<AdditionalProductItemTypeVO> getAdditionalProductItemList() {
	return additionalProductItemList;
}

public void setAdditionalProductItemList(List<AdditionalProductItemTypeVO> additionalProductItemList) {
	this.additionalProductItemList = additionalProductItemList;
}

  
}



