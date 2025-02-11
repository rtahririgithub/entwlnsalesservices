package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class ServiceInstallDetailVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String serviceTypeCd;
	private String recommendedInstallTypeCd;
	private double estimatedAppointmentHours;

	public String getServiceTypeCd() {
		return serviceTypeCd;
	}

	public void setServiceTypeCd(String serviceTypeCd) {
		this.serviceTypeCd = serviceTypeCd;
	}

	public String getRecommendedInstallTypeCd() {
		return recommendedInstallTypeCd;
	}

	public void setRecommendedInstallTypeCd(String recommendedInstallTypeCd) {
		this.recommendedInstallTypeCd = recommendedInstallTypeCd;
	}

	public double getEstimatedAppointmentHours() {
		return estimatedAppointmentHours;
	}

	public void setEstimatedAppointmentHours(double estimatedAppointmentHours) {
		this.estimatedAppointmentHours = estimatedAppointmentHours;
	}

}
