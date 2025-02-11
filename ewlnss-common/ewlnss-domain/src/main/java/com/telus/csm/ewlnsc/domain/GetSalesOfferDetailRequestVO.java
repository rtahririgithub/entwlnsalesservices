package com.telus.csm.ewlnsc.domain;

import java.util.Date;

public class GetSalesOfferDetailRequestVO extends GetOffersRequestVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String offerInstanceId;
	private String offerId;
	private String offerCode;
	private String offerProgramId;
	private String systemID;
    
	private Date perspectiveDate;

	public String getOfferInstanceId() {
		return offerInstanceId;
	}

	public void setOfferInstanceId(String offerInstanceId) {
		this.offerInstanceId = offerInstanceId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public long getOfferIdLong() {
		
		try {
			return Long.parseLong(offerId);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getOfferProgramId() {
		return offerProgramId;
	}

	public void setOfferProgramId(String offerProgramId) {
		this.offerProgramId = offerProgramId;
	}

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public Date getPerspectiveDate() {
		return perspectiveDate;
	}

	public void setPerspectiveDate(Date perspectiveDate) {
		this.perspectiveDate = perspectiveDate;
	}

	public String getPromotionCode() {
		return getSalesOfferCriteria().getPromotionCd();
	}

}
