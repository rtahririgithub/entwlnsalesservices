package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.EquipmentCategoryQualificationList;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCreditEligibilityAdapterResponse extends AdapterResponseBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EquipmentCategoryQualificationList equipmentQualificationList;
	private Boolean collectionInd;
	private Boolean fraudInd;
	private String eligibilityWarningMessageCd;

	public EquipmentCategoryQualificationList getEquipmentQualificationList() {
		return equipmentQualificationList;
	}

	public void setEquipmentQualificationList(EquipmentCategoryQualificationList equipmentQualificationList) {
		this.equipmentQualificationList = equipmentQualificationList;
	}

	public Boolean getCollectionInd() {
		return collectionInd;
	}

	public void setCollectionInd(Boolean collectionInd) {
		this.collectionInd = collectionInd;
	}

	public Boolean getFraudInd() {
		return fraudInd;
	}

	public void setFraudInd(Boolean fraudInd) {
		this.fraudInd = fraudInd;
	}

	public String getEligibilityWarningMessageCd() {
		return eligibilityWarningMessageCd;
	}

	public void setEligibilityWarningMessageCd(String eligibilityWarningMessageCd) {
		this.eligibilityWarningMessageCd = eligibilityWarningMessageCd;
	}
}
