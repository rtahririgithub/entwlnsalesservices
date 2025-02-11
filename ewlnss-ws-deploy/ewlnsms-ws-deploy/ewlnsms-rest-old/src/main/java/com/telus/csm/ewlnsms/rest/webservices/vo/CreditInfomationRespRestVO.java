package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.rest.domain.EntWLNSalesRestRequestBase;



@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class CreditInfomationRespRestVO extends EntWLNSalesRestRequestBase implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean fraudInd;
	private boolean inTreatementInd;
	private String eligibilityWarningMessageCd;
	private List<Equipment> equipmentQualificationList;
	public boolean isFraudInd() {
		return fraudInd;
	}
	public void setFraudInd(boolean fraudInd) {
		this.fraudInd = fraudInd;
	}
	public boolean isInTreatementInd() {
		return inTreatementInd;
	}
	public void setInTreatementInd(boolean inTreatementInd) {
		this.inTreatementInd = inTreatementInd;
	}
	public String getEligibilityWarningMessageCd() {
		return eligibilityWarningMessageCd;
	}
	public void setEligibilityWarningMessageCd(String eligibilityWarningMessageCd) {
		this.eligibilityWarningMessageCd = eligibilityWarningMessageCd;
	}
	public List<Equipment> getEquipmentQualificationList() {
		return equipmentQualificationList;
	}
	public void setEquipmentQualificationList(
			List<Equipment> equipmentQualificationList) {
		this.equipmentQualificationList = equipmentQualificationList;
	}
}
