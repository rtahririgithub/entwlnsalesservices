package com.telus.csm.ewlnsc.grid.domain;

import java.io.Serializable;
import java.util.Date;

public class CatalogueItemDO implements Serializable, Entity<String> {
	
	private static final long serialVersionUID = 1L;
	
	private String catalogueItemId;
	private String masterSrcId;
	private String productCode;
	private String itemType;
	private String componentServiceType;
	private String dataSrcId;
	private String internalName;
	private Date effStartDt;
	private Date effEndDt;
	private String description;
	private String name;
	
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

	public String getMasterSrcId() {
		return masterSrcId;
	}

	public void setMasterSrcId(String masterSrcId) {
		this.masterSrcId = masterSrcId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String prodCode) {
		this.productCode = prodCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getComponentServiceType() {
		return componentServiceType;
	}

	public void setComponentServiceType(String componentServiceType) {
		this.componentServiceType = componentServiceType;
	}

	public String getDataSrcId() {
		return dataSrcId;
	}

	public void setDataSrcId(String dataSrcId) {
		this.dataSrcId = dataSrcId;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
