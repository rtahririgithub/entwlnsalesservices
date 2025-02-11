package com.telus.csm.esdg.domain;

import java.io.Serializable;

public class EsdgShipmentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String salesShipmentId;
	private String salesContextId;
	private long salesInteractionStartTimeInMills = 0;
	private String salesShipmentStatusCd;
	private long statusEffectiveStartTs = 0;
	private String externalTransactionReference;
	private String shipmentTrackingNum;
	private String salesInteractionShipmentId;

	public String getSalesShipmentId() {
		return salesShipmentId;
	}

	public void setSalesShipmentId(String salesShipmentId) {
		this.salesShipmentId = salesShipmentId;
	}

	public String getSalesContextId() {
		return salesContextId;
	}

	public void setSalesContextId(String salesContextId) {
		this.salesContextId = salesContextId;
	}

	public long getSalesInteractionStartTimeInMills() {
		return salesInteractionStartTimeInMills;
	}

	public void setSalesInteractionStartTimeInMills(long salesInteractionStartTimeInMills) {
		this.salesInteractionStartTimeInMills = salesInteractionStartTimeInMills;
	}

	public String getSalesShipmentStatusCd() {
		return salesShipmentStatusCd;
	}

	public void setSalesShipmentStatusCd(String salesShipmentStatusCd) {
		this.salesShipmentStatusCd = salesShipmentStatusCd;
	}

	public long getStatusEffectiveStartTs() {
		return statusEffectiveStartTs;
	}

	public void setStatusEffectiveStartTs(long statusEffectiveStartTs) {
		this.statusEffectiveStartTs = statusEffectiveStartTs;
	}

	public String getExternalTransactionReference() {
		return externalTransactionReference;
	}

	public void setExternalTransactionReference(String externalTransactionReference) {
		this.externalTransactionReference = externalTransactionReference;
	}

	public String getShipmentTrackingNum() {
		return shipmentTrackingNum;
	}

	public void setShipmentTrackingNum(String shipmentTrackingNum) {
		this.shipmentTrackingNum = shipmentTrackingNum;
	}

	
	public String getSalesInteractionShipmentId() {
		return salesInteractionShipmentId;
	}

	public void setSalesInteractionShipmentId(String salesInteractionShipmentId) {
		this.salesInteractionShipmentId = salesInteractionShipmentId;
	}

	public String getJournal() {
		StringBuilder sb = new StringBuilder("--ESDG-- ShipmentContext JNL = ")
		.append(salesShipmentId)
		.append(":").append(salesShipmentId)
		.append(":").append(salesContextId)
		.append(":").append(Long.toString(salesInteractionStartTimeInMills))		
		.append(":").append(salesShipmentStatusCd)
		.append(":").append(Long.toString(statusEffectiveStartTs))
		.append(":").append(externalTransactionReference)
		.append(":").append(shipmentTrackingNum);
		return sb.toString();
	}

}
