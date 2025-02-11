package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class GeoSpatialTypeVO implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String xCoordinate = null;
	  private String yCoordinate = null;
	  private String geocodingcalculationMethodCode = null;
	  private String geocodingMatchTypeCode = null;
	  
	  
	  public String getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public String getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public String getGeocodingcalculationMethodCode() {
		return geocodingcalculationMethodCode;
	}
	public void setGeocodingcalculationMethodCode(String geocodingcalculationMethodCode) {
		this.geocodingcalculationMethodCode = geocodingcalculationMethodCode;
	}
	public String getGeocodingMatchTypeCode() {
		return geocodingMatchTypeCode;
	}
	public void setGeocodingMatchTypeCode(String geocodingMatchTypeCode) {
		this.geocodingMatchTypeCode = geocodingMatchTypeCode;
	}
	public String getConfidenceLevelCode() {
		return confidenceLevelCode;
	}
	public void setConfidenceLevelCode(String confidenceLevelCode) {
		this.confidenceLevelCode = confidenceLevelCode;
	}
	private String confidenceLevelCode = null;
}
