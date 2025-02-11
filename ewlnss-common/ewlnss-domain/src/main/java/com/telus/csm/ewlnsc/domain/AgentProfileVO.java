package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class AgentProfileVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String channelOrganizationTypeCd = null;
	private String channelOrganizationInternalId = null;
	private String channelOrganizationNumber = null;
	private List<String> outletAssociatedProvinces = null;
	private String loginId = null;
	private String salesRepId = null;
	private String salesRepInternalId = null;
	private List<SalesRepAssociatedOutletVO> salesRepAssociatedOutlet = null;
	private String employeeId = null;
	private List<String> userPrivilegeRoleCodes = null;

	public String getChannelOrganizationTypeCd() {
		return channelOrganizationTypeCd;
	}

	public void setChannelOrganizationTypeCd(String channelOrganizationTypeCd) {
		this.channelOrganizationTypeCd = channelOrganizationTypeCd;
	}

	public String getChannelOrganizationInternalId() {
		return channelOrganizationInternalId;
	}

	public void setChannelOrganizationInternalId(String channelOrganizationInternalId) {
		this.channelOrganizationInternalId = channelOrganizationInternalId;
	}

	public String getChannelOrganizationNumber() {
		return channelOrganizationNumber;
	}

	public void setChannelOrganizationNumber(String channelOrganizationNumber) {
		this.channelOrganizationNumber = channelOrganizationNumber;
	}

	public List<String> getOutletAssociatedProvinces() {
		return outletAssociatedProvinces;
	}

	public void setOutletAssociatedProvinces(List<String> outletAssociatedProvinces) {
		this.outletAssociatedProvinces = outletAssociatedProvinces;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSalesRepId() {
		return salesRepId;
	}

	public void setSalesRepId(String salesRepId) {
		this.salesRepId = salesRepId;
	}

	public String getSalesRepInternalId() {
		return salesRepInternalId;
	}

	public void setSalesRepInternalId(String salesRepInternalId) {
		this.salesRepInternalId = salesRepInternalId;
	}

	public List<SalesRepAssociatedOutletVO> getSalesRepAssociatedOutlet() {
		return salesRepAssociatedOutlet;
	}

	public void setSalesRepAssociatedOutlet(List<SalesRepAssociatedOutletVO> salesRepAssociatedOutlet) {
		this.salesRepAssociatedOutlet = salesRepAssociatedOutlet;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<String> getUserPrivilegeRoleCodes() {
		return userPrivilegeRoleCodes;
	}

	public void setUserPrivilegeRoleCodes(List<String> userPrivilegeRoleCodes) {
		this.userPrivilegeRoleCodes = userPrivilegeRoleCodes;
	}

}
