package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreditEligibilityVO {

	private Boolean fraudInd;
	private Boolean inTreatmentInd;
	private List<EquipmentQualificationVO> equipmentQualificationList = new ArrayList<EquipmentQualificationVO>();
	private String eligibilityWarningMessageCd;
	
	public Boolean getFraudInd() {
		return fraudInd;
	}

	public void setFraudInd(Boolean fraudInd) {
		this.fraudInd = fraudInd;
	}

	public Boolean getInTreatmentInd() {
		return inTreatmentInd;
	}

	public void setInTreatmentInd(Boolean inTreatmentInd) {
		this.inTreatmentInd = inTreatmentInd;
	}

	public List<EquipmentQualificationVO> getEquipmentQualificationList() {
		return equipmentQualificationList;
	}

	public void setEquipmentQualificationList(List<EquipmentQualificationVO> equipmentQualificationList) {
		this.equipmentQualificationList = equipmentQualificationList;
	}

	public String getEligibilityWarningMessageCd() {
		return eligibilityWarningMessageCd;
	}

	public void setEligibilityWarningMessageCd(String eligibilityWarningMessageCd) {
		this.eligibilityWarningMessageCd = eligibilityWarningMessageCd;
	}

}
