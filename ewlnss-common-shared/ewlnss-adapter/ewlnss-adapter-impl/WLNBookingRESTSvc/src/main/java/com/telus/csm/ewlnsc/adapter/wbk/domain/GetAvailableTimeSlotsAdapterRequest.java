package com.telus.csm.ewlnsc.adapter.wbk.domain;

import java.util.ArrayList;
import java.util.List;

public class GetAvailableTimeSlotsAdapterRequest {
	
	

	private String orderId;
	private String fromDate;
	private String toDate;
	private String fmsAddressId;
	private String provinceCd;
	private String city;
	private String projectCd;
	private List<InstallationRequirementsList> installationRequirementsList;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFmsAddressId() {
		return fmsAddressId;
	}
	public void setFmsAddressId(String fmsAddressId) {
		this.fmsAddressId = fmsAddressId;
	}
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	public List<InstallationRequirementsList> getInstallationRequirementsList() {
		return installationRequirementsList;
	}
	public void setInstallationRequirementsList(List<InstallationRequirementsList> installationRequirementsList) {
		this.installationRequirementsList = installationRequirementsList;
	}

	/*******************************/
	/* deep cloning of object      */
	/*******************************/
	public GetAvailableTimeSlotsAdapterRequest cloneObject(){
		GetAvailableTimeSlotsAdapterRequest newRequest = new GetAvailableTimeSlotsAdapterRequest();
		newRequest.setCity(this.city);
		newRequest.setFmsAddressId(this.fmsAddressId);
		newRequest.setFromDate(this.fromDate); 
		newRequest.setOrderId(this.orderId);
		newRequest.setProjectCd(this.projectCd);
		newRequest.setProvinceCd(this.provinceCd);
		newRequest.setToDate(this.toDate);
		
		List<InstallationRequirementsList> newList = new ArrayList<InstallationRequirementsList>();
		for (InstallationRequirementsList item : this.installationRequirementsList){
			InstallationRequirementsList newItem = new InstallationRequirementsList();
			newItem.setActionType(item.getActionType());
			newItem.setProductServiceType(item.getProductServiceType());
			newItem.setRequestedBucketType(item.getRequestedBucketType());
			newItem.setRequestedInstallType(item.getRequestedInstallType());
			newItem.setRequestedWorkTime(item.getRequestedWorkTime());
			newList.add(newItem);
		}
		
		newRequest.setInstallationRequirementsList(newList);
		
		return newRequest;
	}
}
