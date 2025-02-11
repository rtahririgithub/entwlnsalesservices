package com.telus.csm.ewlnsc.domain;

import java.util.Date;

/**
 * FFHOfferHeader
 */

public class FFHOfferHeaderVO {
	private String offerId = null;

	private String offerCode = null;

	private String offerProgramId = null;

	private String systemId = null;

	private Date perspectiveDate = null;

	/**
	 * @return the offerId
	 */
	public String getOfferId() {
		return offerId;
	}

	/**
	 * @param offerId
	 *            the offerId to set
	 */
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}

	/**
	 * @param offerCode
	 *            the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	/**
	 * @return the offerProgramId
	 */
	public String getOfferProgramId() {
		return offerProgramId;
	}

	/**
	 * @param offerProgramId
	 *            the offerProgramId to set
	 */
	public void setOfferProgramId(String offerProgramId) {
		this.offerProgramId = offerProgramId;
	}

	/**
	 * @return the systemId
	 */
	public String getSystemId() {
		return systemId;
	}

	/**
	 * @param systemId
	 *            the systemId to set
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	/**
	 * @return the perspectiveDate
	 */
	public Date getPerspectiveDate() {
		return perspectiveDate;
	}

	/**
	 * @param perspectiveDate
	 *            the perspectiveDate to set
	 */
	public void setPerspectiveDate(Date perspectiveDate) {
		this.perspectiveDate = perspectiveDate;
	}

}
