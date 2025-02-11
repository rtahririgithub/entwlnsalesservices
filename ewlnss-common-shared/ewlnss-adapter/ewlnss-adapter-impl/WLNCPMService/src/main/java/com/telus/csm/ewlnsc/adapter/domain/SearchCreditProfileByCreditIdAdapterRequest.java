package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditIdentification;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo;


/**
 * 
 * @author Jose.Mena
 *
 */
public class SearchCreditProfileByCreditIdAdapterRequest extends AdapterRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreditIdentification creditIdentification;
	private AuditInfo auditInfo;

	public CreditIdentification getCreditIdentification() {
		return creditIdentification;
	}

	public void setCreditIdentification(CreditIdentification creditIdentification) {
		this.creditIdentification = creditIdentification;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
