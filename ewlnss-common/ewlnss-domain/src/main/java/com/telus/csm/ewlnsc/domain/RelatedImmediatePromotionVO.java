package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;

public class RelatedImmediatePromotionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
    private String itemQualificationType;//The type of the qualification result for the item - trigger or benefit
    private String itemStatus;//status os the promotion can be add, revoke or keep
    private Date perspectiveDate;
    private String name;
    
    private SelectedCouponVO selectedCoupon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemQualificationType() {
		return itemQualificationType;
	}
	public void setItemQualificationType(String itemQualificationType) {
		this.itemQualificationType = itemQualificationType;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public Date getPerspectiveDate() {
		return perspectiveDate;
	}
	public void setPerspectiveDate(Date perspectiveDate) {
		this.perspectiveDate = perspectiveDate;
	}
	public SelectedCouponVO getSelectedCoupon() {
		return selectedCoupon;
	}
	public void setSelectedCoupon(SelectedCouponVO selectedCoupon) {
		this.selectedCoupon = selectedCoupon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
