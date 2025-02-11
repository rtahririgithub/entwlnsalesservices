package com.telus.csm.ewlnsc.adapter.wbk.domain;

import java.util.List;

/**
 * 
 * @author Jose.Mena
 *
 */
public class BookAppointmentAdapterRequestBody {

	private String orderId;
	private String projectCode;
	private String canBeReachedNumber;
	private boolean appointmentValidation;
	private String validHours;
	private String serviceClassCd;
	private String apptDate;
	private String apptStartTime;
	private String apptEndTime;
	private String timeZone;
	private List<InstallationRequirementsList> installationRequirementsList;
	private AddressInfo addressInfo;
	private String orderTypeCd;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getCanBeReachedNumber() {
		return canBeReachedNumber;
	}

	public void setCanBeReachedNumber(String canBeReachedNumber) {
		this.canBeReachedNumber = canBeReachedNumber;
	}

	public boolean isAppointmentValidation() {
		return appointmentValidation;
	}

	public void setAppointmentValidation(boolean appointmentValidation) {
		this.appointmentValidation = appointmentValidation;
	}

	public String getValidHours() {
		return validHours;
	}

	public void setValidHours(String validHours) {
		this.validHours = validHours;
	}

	public String getServiceClassCd() {
		return serviceClassCd;
	}

	public void setServiceClassCd(String serviceClassCd) {
		this.serviceClassCd = serviceClassCd;
	}

	public String getApptDate() {
		return apptDate;
	}

	public void setApptDate(String apptDate) {
		this.apptDate = apptDate;
	}

	public String getApptStartTime() {
		return apptStartTime;
	}

	public void setApptStartTime(String apptStartTime) {
		this.apptStartTime = apptStartTime;
	}

	public String getApptEndTime() {
		return apptEndTime;
	}

	public void setApptEndTime(String apptEndTime) {
		this.apptEndTime = apptEndTime;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public List<InstallationRequirementsList> getInstallationRequirementsList() {
		return installationRequirementsList;
	}

	public void setInstallationRequirementsList(List<InstallationRequirementsList> installationRequirementsList) {
		this.installationRequirementsList = installationRequirementsList;
	}

	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getOrderTypeCd() {
		return orderTypeCd;
	}

	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}
	
	

}
