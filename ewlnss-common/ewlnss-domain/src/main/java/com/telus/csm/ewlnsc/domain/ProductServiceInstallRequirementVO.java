package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class ProductServiceInstallRequirementVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productServiceTypeCd = null;
	private String installTypeCd = null;
	private Double estimatedAppointmentHours = null;

	public String getProductServiceTypeCd() {
		return productServiceTypeCd;
	}

	public void setProductServiceTypeCd(String productServiceTypeCd) {
		this.productServiceTypeCd = productServiceTypeCd;
	}

	public String getInstallTypeCd() {
		return installTypeCd;
	}

	public void setInstallTypeCd(String installTypeCd) {
		this.installTypeCd = installTypeCd;
	}

	public Double getEstimatedAppointmentHours() {
		return estimatedAppointmentHours;
	}

	public void setEstimatedAppointmentHours(Double estimatedAppointmentHours) {
		this.estimatedAppointmentHours = estimatedAppointmentHours;
	}

}
