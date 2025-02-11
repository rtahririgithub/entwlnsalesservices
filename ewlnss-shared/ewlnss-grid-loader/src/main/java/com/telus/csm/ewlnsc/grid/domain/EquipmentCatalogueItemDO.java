package com.telus.csm.ewlnsc.grid.domain;

import java.io.Serializable;
import java.util.Date;

public class EquipmentCatalogueItemDO implements Serializable, Entity<String> {
	
	private static final long serialVersionUID = 1L;
	
	//#PROD_CATLG_ITM_ID|PROD_CD|EFF_START_DT|EFF_END_DT|PROD_CATEGORY_TYPE_CD|ACQUISITION_TYPE|MODEL_NAME|
	//PURCHASE_PROD_PRICE_TYPE_CD|PURCHASE_CHARGE_TYPE_CD|PURCHASE_PRICE|
	//RENTAL_PROD_PRICE_TYPE_CD|RENTAL_CHARGE_TYPE_CD|RENTAL_PRICE|
	//LATE_RENTAL_PROD_PRICE_TYPE_CD|LATE_RENTAL_CHARGE_TYPE_CD|LATE_RENTAL_PRICE|
	//PROD_DESC_LANG_CD|PROD_DESC

	private String catalogueItemId;
	private String productCode;
	private String itemType;
	private String acqusitionType;
	private String modelName;
	private Date effStartDt;
	private Date effEndDt;
	
	private String purchasePriceTypeCode;
	private String purchaseChargeTypeCode;
	private Double purchasePrice;

	private String rentalPriceTypeCode;
	private String rentalChargeTypeCode;
	private Double rentalPrice;
	
	private String lateRentalPriceTypeCode;
	private String lateRentalChargeTypeCode;
	private Double lateRentalPrice;
	
	
	private String description;


	@Override
	public String getId() {
		return catalogueItemId;
	}


	public String getCatalogueItemId() {
		return catalogueItemId;
	}


	public void setCatalogueItemId(String catalogueItemId) {
		this.catalogueItemId = catalogueItemId;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getItemType() {
		return itemType;
	}


	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public String getAcqusitionType() {
		return acqusitionType;
	}


	public void setAcqusitionType(String acqusitionType) {
		this.acqusitionType = acqusitionType;
	}


	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public Date getEffStartDt() {
		return effStartDt;
	}


	public void setEffStartDt(Date effStartDt) {
		this.effStartDt = effStartDt;
	}


	public Date getEffEndDt() {
		return effEndDt;
	}


	public void setEffEndDt(Date effEndDt) {
		this.effEndDt = effEndDt;
	}


	public String getPurchasePriceTypeCode() {
		return purchasePriceTypeCode;
	}


	public void setPurchasePriceTypeCode(String purchasePriceTypeCode) {
		this.purchasePriceTypeCode = purchasePriceTypeCode;
	}


	public String getPurchaseChargeTypeCode() {
		return purchaseChargeTypeCode;
	}


	public void setPurchaseChargeTypeCode(String purchaseChargeTypeCode) {
		this.purchaseChargeTypeCode = purchaseChargeTypeCode;
	}


	public Double getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	public String getRentalPriceTypeCode() {
		return rentalPriceTypeCode;
	}


	public void setRentalPriceTypeCode(String rentalPriceTypeCode) {
		this.rentalPriceTypeCode = rentalPriceTypeCode;
	}


	public String getRentalChargeTypeCode() {
		return rentalChargeTypeCode;
	}


	public void setRentalChargeTypeCode(String rentalChargeTypeCode) {
		this.rentalChargeTypeCode = rentalChargeTypeCode;
	}


	public Double getRentalPrice() {
		return rentalPrice;
	}


	public void setRentalPrice(Double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}


	public String getLateRentalPriceTypeCode() {
		return lateRentalPriceTypeCode;
	}


	public void setLateRentalPriceTypeCode(String lateRentalPriceTypeCode) {
		this.lateRentalPriceTypeCode = lateRentalPriceTypeCode;
	}


	public String getLateRentalChargeTypeCode() {
		return lateRentalChargeTypeCode;
	}


	public void setLateRentalChargeTypeCode(String lateRentalChargeTypeCode) {
		this.lateRentalChargeTypeCode = lateRentalChargeTypeCode;
	}


	public Double getLateRentalPrice() {
		return lateRentalPrice;
	}


	public void setLateRentalPrice(Double lateRentalPrice) {
		this.lateRentalPrice = lateRentalPrice;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


}
