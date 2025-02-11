package com.telus.csm.esdg.domain;

import java.io.Serializable;
import java.util.List;

public class EsdgShipmentInteractionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String salesInteractionShipmentId;
	private String salesInteractionId;
	private long salesInteractionStartTimeInMills = 0;
	private String salesShipmentStatusCd;
	private String externalTransactionReference;
	private String shipmentTrackingNum;
	private List<EsdgShipmentDO> shipments;
	private String jsonShipmentTXNData;
	
	public String getSalesInteractionShipmentId() {
		return salesInteractionShipmentId;
	}
	public void setSalesInteractionShipmentId(String salesInteractionShipmentId) {
		this.salesInteractionShipmentId = salesInteractionShipmentId;
	}
	public String getSalesInteractionId() {
		return salesInteractionId;
	}
	public void setSalesInteractionId(String salesInteractionId) {
		this.salesInteractionId = salesInteractionId;
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
	public List<EsdgShipmentDO> getShipments() {
		return shipments;
	}
	public void setShipments(List<EsdgShipmentDO> shipments) {
		this.shipments = shipments;
	}
	public String getJsonShipmentTXNData() {
		return jsonShipmentTXNData;
	}
	public void setJsonShipmentTXNData(String jsonShipmentTXNData) {
		this.jsonShipmentTXNData = jsonShipmentTXNData;
	}
	public String getJournal() {
		StringBuilder sb = new StringBuilder("--ESDG-- ShipmentInteractionContext JNL = ")
		.append(salesInteractionShipmentId)
		.append(":").append(salesInteractionId)
		.append(":").append(Long.toString(salesInteractionStartTimeInMills))		
		.append(":").append(salesShipmentStatusCd)
		.append(":").append(externalTransactionReference)
		.append(":").append(shipmentTrackingNum);
		if(shipments != null) {
			sb.append("[");
			for(EsdgShipmentDO shipment: shipments) {
				sb.append(shipment.getJournal());
			}
			sb.append("]");
		}
		return sb.toString();
	}

}
