package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementproxyservicerequestresponse_v2.AssessCreditWorthinessRequest;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo;

/**
 * 
 * @author Jose.Mena
 *
 */
public class AssessCreditWorthinessAdapterRequest extends AdapterRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AssessCreditWorthinessRequest assessCreditWorthinessRequest;
	private AuditInfo auditInfo;

	public AssessCreditWorthinessRequest getAssessCreditWorthinessRequest() {
		return assessCreditWorthinessRequest;
	}

	public void setAssessCreditWorthinessRequest(AssessCreditWorthinessRequest assessCreditWorthinessRequest) {
		this.assessCreditWorthinessRequest = assessCreditWorthinessRequest;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
}
