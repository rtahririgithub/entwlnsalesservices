package com.telus.csm.ewlnsc.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CharacteristicVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;

public class FFHEquipmentItemVO {

	// @SerializedName("productItemIdentifier")
	private ProductItemIdentifierVO productItemIdentifier = null;

	// @SerializedName("equipmentType")
	private String equipmentType = null;

	// @SerializedName("equipmentSubType")
	private String equipmentSubType = null;

	// @SerializedName("modelName")
	private String modelName = null;

	// @SerializedName("materialItemCode")
	private String materialItemCode = null;

	// @SerializedName("includedInd")
	private String includedInd = null;

	// @SerializedName("equipmentDescription")
	private List<MultilingualTextVO> equipmentDescription = null;

	// @SerializedName("deliveryMethods")
	private List<String> deliveryMethods = null;

	// @SerializedName("equipmentPrice")
	private PriceVO equipmentPrice = null;

	// @SerializedName("equipmentPriceDiscount")
	private PriceDiscountVO equipmentPriceDiscount = null;
	
    //FIFA-SHS changes: equipment characteristic
    private List<CharacteristicVO> characteristics; 

	// @SerializedName("associatedProductItems")
	private List<AssociatedProductItemVO> associatedProductItems = null;

	private boolean existing;
	
	private boolean carryOver;
	
	public FFHEquipmentItemVO productItemIdentifier(ProductItemIdentifierVO productItemIdentifier) {
		this.productItemIdentifier = productItemIdentifier;
		return this;
	}

	/**
	 * Get productItemIdentifier
	 * 
	 * @return productItemIdentifier
	 **/
	// @ApiModelProperty(value = "")
	public ProductItemIdentifierVO getProductItemIdentifier() {
		return productItemIdentifier;
	}

	public void setProductItemIdentifier(ProductItemIdentifierVO productItemIdentifier) {
		this.productItemIdentifier = productItemIdentifier;
	}

	public FFHEquipmentItemVO equipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
		return this;
	}
	public boolean isCarryOver() {
		return carryOver;
	}

	public void setCarryOver(boolean carryOver) {
		this.carryOver = carryOver;
	}

	public boolean isExisting() {
		return existing;
	}

	public void setExisting(boolean existing) {
		this.existing = existing;
	}

	/**
	 * TTV_SETTOP_BOX, HIGH_SPEED_MODEM, WIFI_BOOST
	 * 
	 * @return equipmentType
	 **/
	// @ApiModelProperty(value = "TTV_SETTOP_BOX, HIGH_SPEED_MODEM, WIFI_BOOST")
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public FFHEquipmentItemVO equipmentSubType(String equipmentSubType) {
		this.equipmentSubType = equipmentSubType;
		return this;
	}

	/**
	 * STARTER, EXPANSION
	 * 
	 * @return equipmentSubType
	 **/
	// @ApiModelProperty(value = "STARTER, EXPANSION")
	public String getEquipmentSubType() {
		return equipmentSubType;
	}

	public void setEquipmentSubType(String equipmentSubType) {
		this.equipmentSubType = equipmentSubType;
	}

	public FFHEquipmentItemVO modelName(String modelName) {
		this.modelName = modelName;
		return this;
	}

	/**
	 * Get modelName
	 * 
	 * @return modelName
	 **/
	// @ApiModelProperty(value = "")
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public FFHEquipmentItemVO materialItemCode(String materialItemCode) {
		this.materialItemCode = materialItemCode;
		return this;
	}

	/**
	 * Get materialItemCode
	 * 
	 * @return materialItemCode
	 **/
	// @ApiModelProperty(value = "")
	public String getMaterialItemCode() {
		return materialItemCode;
	}

	public void setMaterialItemCode(String materialItemCode) {
		this.materialItemCode = materialItemCode;
	}

	public FFHEquipmentItemVO includedInd(String includedInd) {
		this.includedInd = includedInd;
		return this;
	}

	/**
	 * Get includedInd
	 * 
	 * @return includedInd
	 **/
	// @ApiModelProperty(value = "")
	public String getIncludedInd() {
		return includedInd;
	}

	public void setIncludedInd(String includedInd) {
		this.includedInd = includedInd;
	}

	public FFHEquipmentItemVO equipmentDescription(List<MultilingualTextVO> equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
		return this;
	}

	public FFHEquipmentItemVO addEquipmentDescriptionItem(MultilingualTextVO equipmentDescriptionItem) {

		if (this.equipmentDescription == null) {
			this.equipmentDescription = new ArrayList<MultilingualTextVO>();
		}

		this.equipmentDescription.add(equipmentDescriptionItem);
		return this;
	}

	/**
	 * Get equipmentDescription
	 * 
	 * @return equipmentDescription
	 **/
	// @ApiModelProperty(value = "")
	public List<MultilingualTextVO> getEquipmentDescription() {
		return equipmentDescription;
	}

	public void setEquipmentDescription(List<MultilingualTextVO> equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public FFHEquipmentItemVO deliveryMethods(List<String> deliveryMethods) {
		this.deliveryMethods = deliveryMethods;
		return this;
	}

	public FFHEquipmentItemVO addDeliveryMethodsItem(String deliveryMethodsItem) {

		if (this.deliveryMethods == null) {
			this.deliveryMethods = new ArrayList<String>();
		}

		this.deliveryMethods.add(deliveryMethodsItem);
		return this;
	}

	/**
	 * Get deliveryMethods
	 * 
	 * @return deliveryMethods
	 **/
	// @ApiModelProperty(value = "")
	public List<String> getDeliveryMethods() {
		return deliveryMethods;
	}

	public void setDeliveryMethods(List<String> deliveryMethods) {
		this.deliveryMethods = deliveryMethods;
	}

	public FFHEquipmentItemVO equipmentPrice(PriceVO equipmentPrice) {
		this.equipmentPrice = equipmentPrice;
		return this;
	}

	/**
	 * Get equipmentPrice
	 * 
	 * @return equipmentPrice
	 **/
	// @ApiModelProperty(value = "")
	public PriceVO getEquipmentPrice() {
		return equipmentPrice;
	}

	public void setEquipmentPrice(PriceVO equipmentPrice) {
		this.equipmentPrice = equipmentPrice;
	}

	public FFHEquipmentItemVO equipmentPriceDiscount(PriceDiscountVO equipmentPriceDiscount) {
		this.equipmentPriceDiscount = equipmentPriceDiscount;
		return this;
	}

	/**
	 * Get equipmentPriceDiscount
	 * 
	 * @return equipmentPriceDiscount
	 **/
	// @ApiModelProperty(value = "")
	public PriceDiscountVO getEquipmentPriceDiscount() {
		return equipmentPriceDiscount;
	}

	public void setEquipmentPriceDiscount(PriceDiscountVO equipmentPriceDiscount) {
		this.equipmentPriceDiscount = equipmentPriceDiscount;
	}

	public FFHEquipmentItemVO associatedProductItems(List<AssociatedProductItemVO> associatedProductItems) {
		this.associatedProductItems = associatedProductItems;
		return this;
	}

	public FFHEquipmentItemVO addAssociatedProductItemsItem(AssociatedProductItemVO associatedProductItemsItem) {

		if (this.associatedProductItems == null) {
			this.associatedProductItems = new ArrayList<AssociatedProductItemVO>();
		}

		this.associatedProductItems.add(associatedProductItemsItem);
		return this;
	}

	public List<CharacteristicVO> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicVO> characteristics) {
		this.characteristics = characteristics;
	}
	
	/**
	 * Get associatedProductItems
	 * 
	 * @return associatedProductItems
	 **/
	// @ApiModelProperty(value = "")
	public List<AssociatedProductItemVO> getAssociatedProductItems() {
		return associatedProductItems;
	}

	public void setAssociatedProductItems(List<AssociatedProductItemVO> associatedProductItems) {
		this.associatedProductItems = associatedProductItems;
	}

	/*
	 * @Override public boolean equals(java.lang.Object o) { if (this == o) {
	 * return true; } if (o == null || getClass() != o.getClass()) { return
	 * false; } FFHEquipmentItem ffHEquipmentItem = (FFHEquipmentItem) o; return
	 * Objects.equals(this.productItemIdentifier,
	 * ffHEquipmentItem.productItemIdentifier) &&
	 * Objects.equals(this.equipmentType, ffHEquipmentItem.equipmentType) &&
	 * Objects.equals(this.equipmentSubType, ffHEquipmentItem.equipmentSubType)
	 * && Objects.equals(this.modelName, ffHEquipmentItem.modelName) &&
	 * Objects.equals(this.materialItemCode, ffHEquipmentItem.materialItemCode)
	 * && Objects.equals(this.includedInd, ffHEquipmentItem.includedInd) &&
	 * Objects.equals(this.equipmentDescription,
	 * ffHEquipmentItem.equipmentDescription) &&
	 * Objects.equals(this.deliveryMethods, ffHEquipmentItem.deliveryMethods) &&
	 * Objects.equals(this.equipmentPrice, ffHEquipmentItem.equipmentPrice) &&
	 * Objects.equals(this.equipmentPriceDiscount,
	 * ffHEquipmentItem.equipmentPriceDiscount) &&
	 * Objects.equals(this.associatedProductItems,
	 * ffHEquipmentItem.associatedProductItems); }
	 * 
	 * @Override public int hashCode() { return
	 * java.util.Objects.hash(productItemIdentifier, equipmentType,
	 * equipmentSubType, modelName, materialItemCode, includedInd,
	 * equipmentDescription, deliveryMethods, equipmentPrice,
	 * equipmentPriceDiscount, associatedProductItems); }
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FFHEquipmentItem {\n");

		sb.append("    productItemIdentifier: ").append(toIndentedString(productItemIdentifier)).append("\n");
		sb.append("    equipmentType: ").append(toIndentedString(equipmentType)).append("\n");
		sb.append("    equipmentSubType: ").append(toIndentedString(equipmentSubType)).append("\n");
		sb.append("    modelName: ").append(toIndentedString(modelName)).append("\n");
		sb.append("    materialItemCode: ").append(toIndentedString(materialItemCode)).append("\n");
		sb.append("    includedInd: ").append(toIndentedString(includedInd)).append("\n");
		sb.append("    equipmentDescription: ").append(toIndentedString(equipmentDescription)).append("\n");
		sb.append("    deliveryMethods: ").append(toIndentedString(deliveryMethods)).append("\n");
		sb.append("    equipmentPrice: ").append(toIndentedString(equipmentPrice)).append("\n");
		sb.append("    equipmentPriceDiscount: ").append(toIndentedString(equipmentPriceDiscount)).append("\n");
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

}
