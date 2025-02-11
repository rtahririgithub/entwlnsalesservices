package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesItemPerk;

public class SalesItemPerkVO extends SalesItemDO  implements Serializable {
	
	public SalesItemPerkVO() {
		super( ITEM_TYPE_PERK );
	}

	private static final long serialVersionUID = 1L;
	
	private SalesItemPerk salesOrderPerk;
	private String errorCode;
	private String errorMessage;

	public SalesItemPerk getSalesOrderPerk() {
		return salesOrderPerk;
	}

	public void setSalesOrderPerk(SalesItemPerk salesOrderPerk) {
		this.salesOrderPerk = salesOrderPerk;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
