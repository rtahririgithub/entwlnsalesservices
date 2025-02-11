package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;

public class SelectedCouponVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
    private Date perspectiveDate;
    private String validationCode;//validation code to redeem the coupon
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getPerspectiveDate() {
		return perspectiveDate;
	}
	public void setPerspectiveDate(Date perspectiveDate) {
		this.perspectiveDate = perspectiveDate;
	}
	public String getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
    
}
