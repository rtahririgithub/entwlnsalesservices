package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public abstract class SalesItemDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String ITEM_TYPE_PERK = "PERK";
	public final static String ITEM_TYPE_EXTENDED_WARRANTY = "EXTENDED WARRANTY";
	
	public final static String STATUS_CD_NEW = "NEW";
	public final static String STATUS_CD_ACTIVE = "ACTIVE";
	public final static String STATUS_CD_COMPLETED = "COMPLETED";
	public final static String STATUS_CD_ERROR = "ERROR";
	public final static String STATUS_CD_CANCEL = "CANCEL";
	
	protected String itemId;
	protected String itemType;
	protected String itemStatus;
	protected String salesOrderId;
	protected String accountMasterSourceTypeCd;
	
	protected OperationHeader operationHeader;
	
	public SalesItemDO ( String itemType ) {
		this.itemType = itemType;
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

//	public void setItemType(String itemType) {
//		this.itemType = itemType;
//	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public OperationHeader getOperationHeader() {
		return operationHeader;
	}

	public void setOperationHeader(OperationHeader operationHeader) {
		this.operationHeader = operationHeader;
	}
	
	public String getAccountMasterSourceTypeCd() {
		return accountMasterSourceTypeCd;
	}
	
	public void setAccountMasterSourceTypeCd(String accountMasterSourceTypeCd) {
		this.accountMasterSourceTypeCd = accountMasterSourceTypeCd;
	}
}
