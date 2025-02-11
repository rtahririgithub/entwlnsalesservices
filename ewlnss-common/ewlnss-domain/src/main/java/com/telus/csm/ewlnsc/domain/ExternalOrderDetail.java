package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class ExternalOrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String EXTERNAL_SYSTEM_OMS = "OMS";
	public static final String OMS_STATUS_SUCCESS = "SUCCESS";
	public static final String OMS_STATUS_FAIL = "FAIL";
	public static final String OMS_STATUS_BACK_OFFICE = "BK_OFFICE";
	public static final String OMS_STATUS_IN_PROGRESS = "IN_PROGRESS";
	
	private String externalOrderSystem;
	private String externalOrderId;
	private String externalOrderStatus;
	private List<RelatedCartItemVO> relatedCartItemList;
	
	public String getExternalOrderSystem() {
		return externalOrderSystem;
	}
	public void setExternalOrderSystem(String externalOrderSystem) {
		this.externalOrderSystem = externalOrderSystem;
	}
	public String getExternalOrderId() {
		return externalOrderId;
	}
	public void setExternalOrderId(String externalOrderId) {
		this.externalOrderId = externalOrderId;
	}
	public String getExternalOrderStatus() {
		return externalOrderStatus;
	}
	public void setExternalOrderStatus(String externalOrderStatus) {
		this.externalOrderStatus = externalOrderStatus;
	}
	public List<RelatedCartItemVO> getRelatedCartItemList() {
		return relatedCartItemList;
	}
	public void setRelatedCartItemList(List<RelatedCartItemVO> relatedCartItemList) {
		this.relatedCartItemList = relatedCartItemList;
	}
	@Override
	public String toString() {		
		return "externalOrderId: " + externalOrderId + " , externalOrderSystem: " + externalOrderSystem + ", externalOrderStatus: " + externalOrderStatus;
		
	}
	
	

}
