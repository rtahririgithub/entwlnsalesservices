package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.enterprisecreditprofilemanagementservicerequestresponse_v1.RemoveCreditIdentificationInfo;
import com.telus.tmi.xmlschema.xsd.customer.customer.enterprisecreditprofilemanagementservicetypes_v2.ConsumerCreditProfileInfo;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo;

/**
 * 
 * @author Jose.Mena
 *
 */
public class UpdateCreditProfileAdapterRequest extends AdapterRequestBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConsumerCreditProfileInfo consumerCreditProfileInfo;
	private RemoveCreditIdentificationInfo removeCreditIdentificationInfo;
	private AuditInfo auditInfo;

	public ConsumerCreditProfileInfo getConsumerCreditProfileInfo() {
		return consumerCreditProfileInfo;
	}

	public void setConsumerCreditProfileInfo(ConsumerCreditProfileInfo consumerCreditProfileInfo) {
		this.consumerCreditProfileInfo = consumerCreditProfileInfo;
	}

	public RemoveCreditIdentificationInfo getRemoveCreditIdentificationInfo() {
		return removeCreditIdentificationInfo;
	}

	public void setRemoveCreditIdentificationInfo(RemoveCreditIdentificationInfo removeCreditIdentificationInfo) {
		this.removeCreditIdentificationInfo = removeCreditIdentificationInfo;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
