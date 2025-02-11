package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class UserProfileVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String channelOrganizationTypeCd = null;
	private String channelOrganizationInternalId = null;
	private String channelOrganizationNumber = null;
	private List<String> outletAssociatedProvinces = null;
	private String loginId = null;
	private String salesRepId = null;
	private String salesRepInternalId = null;
	private List<SalesRepAssociatedOutletVO> salesRepAssociatedOutlet = null;
	private String salesPersonRoleCd;
	
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

	public String getSalesPersonRoleCd() {
		return salesPersonRoleCd;
	}

	public void setSalesPersonRoleCd(String salesPersonRoleCd) {
		this.salesPersonRoleCd = salesPersonRoleCd;
	}

}
