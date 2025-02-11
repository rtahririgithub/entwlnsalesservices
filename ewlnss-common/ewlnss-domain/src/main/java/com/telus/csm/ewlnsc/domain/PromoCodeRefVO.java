package com.telus.csm.ewlnsc.domain;

import java.util.Date;

public class PromoCodeRefVO {
    private String promoCodeId;
    private String promoCode;
    private Date perspectiveDate;
    private String href;
    private String validationCode;
    
	public String getPromoCodeId() {
		return promoCodeId;
	}
	public void setPromoCodeId(String promoCodeId) {
		this.promoCodeId = promoCodeId;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public Date getPerspectiveDate() {
		return perspectiveDate;
	}
	public void setPerspectiveDate(Date perspectiveDate) {
		this.perspectiveDate = perspectiveDate;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
}
