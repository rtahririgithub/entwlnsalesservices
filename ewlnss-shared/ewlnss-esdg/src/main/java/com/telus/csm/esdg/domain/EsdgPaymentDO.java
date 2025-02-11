package com.telus.csm.esdg.domain;

public class EsdgPaymentDO extends EsdgDO {
	private static final long serialVersionUID = 1L;
	private String salesPaymentId;
	private String salesPaymentReasonCd;
	private String salesPaymentStatusCd;
	private String jsonPaymentTXNData;
		
	public String getSalesPaymentId() {
		return salesPaymentId;
	}

	public void setSalesPaymentId(String salesPaymentId) {
		this.salesPaymentId = salesPaymentId;
	}

	public String getSalesPaymentReasonCd() {
		return salesPaymentReasonCd;
	}

	public void setSalesPaymentReasonCd(String salesPaymentReasonCd) {
		this.salesPaymentReasonCd = salesPaymentReasonCd;
	}

	public String getSalesPaymentStatusCd() {
		return salesPaymentStatusCd;
	}

	public void setSalesPaymentStatusCd(String salesPaymentStatusCd) {
		this.salesPaymentStatusCd = salesPaymentStatusCd;
	}

	@Override
	public boolean isWriteToDatabase() {
		return true;
	}

	public String getJsonPaymentTXNData() {
		return jsonPaymentTXNData;
	}

	public void setJsonPaymentTXNData(String jsonPaymentTXNData) {
		this.jsonPaymentTXNData = jsonPaymentTXNData;
	}

	public String getJournal() {
		
		StringBuilder sb = new StringBuilder("--ESDG-- PaymentContext JNL = ")
		.append(salesPaymentId)
		.append(":").append(salesInteractionId)
		.append(":").append(salesContextId)
		.append(":").append(Long.toString(salesInteractionStartTimeInMills))		
		.append(":").append(salesPaymentReasonCd)
		.append(":").append(salesPaymentStatusCd)
		.append(":").append(jsonPaymentTXNData);
		
		return sb.toString();
	}

}
