package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class CreditEligibilityResultRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Boolean fraudInd;
	protected Boolean inTreatmentInd;
	protected String eligibilityWarningMessageCd;
	protected List<EquipmentQualInfoRestVO> equipmentQualificationList;

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

	public String getEligibilityWarningMessageCd() {
		return eligibilityWarningMessageCd;
	}

	public void setEligibilityWarningMessageCd(
			String eligibilityWarningMessageCd) {
		this.eligibilityWarningMessageCd = eligibilityWarningMessageCd;
	}

	public List<EquipmentQualInfoRestVO> getEquipmentQualificationList() {
		return equipmentQualificationList;
	}

	public void setEquipmentQualificationList(
			List<EquipmentQualInfoRestVO> equipmentQualificationList) {
		this.equipmentQualificationList = equipmentQualificationList;
	}

}
