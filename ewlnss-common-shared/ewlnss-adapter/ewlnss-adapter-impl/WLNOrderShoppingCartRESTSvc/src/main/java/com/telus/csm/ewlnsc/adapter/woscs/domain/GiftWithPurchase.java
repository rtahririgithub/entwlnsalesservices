package com.telus.csm.ewlnsc.adapter.woscs.domain;

public class GiftWithPurchase {

	private String promoId;
	private String actionName;
	private String description;
	private String perspectiveDate;
	private String originalCostAmount;
	private String originalCommitmentTerm;

	public String getPromoId() {
		return promoId;
	}
	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPerspectiveDate() {
		return perspectiveDate;
	}
	public void setPerspectiveDate(String perspectiveDate) {
		this.perspectiveDate = perspectiveDate;
	}
	public String getOriginalCostAmount() {
		return originalCostAmount;
	}
	public void setOriginalCostAmount(String originalCostAmount) {
		this.originalCostAmount = originalCostAmount;
	}
	public String getOriginalCommitmentTerm() {
		return originalCommitmentTerm;
	}
	public void setOriginalCommitmentTerm(String originalCommitmentTerm) {
		this.originalCommitmentTerm = originalCommitmentTerm;
	}
}