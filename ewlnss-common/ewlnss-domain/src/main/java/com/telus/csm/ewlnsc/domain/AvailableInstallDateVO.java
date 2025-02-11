package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class AvailableInstallDateVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> serviceTypeList;
	private boolean installationRequiredInd;
	
	private List<AvailableDateTimePeriodVO> availableDateTimePeriodList;
	private List<String> availableDates;
	
	public List<String> getServiceTypeList() {
		return serviceTypeList;
	}
	public void setServiceTypeList(List<String> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}
	public boolean isInstallationRequiredInd() {
		return installationRequiredInd;
	}
	public void setInstallationRequiredInd(boolean installationRequiredInd) {
		this.installationRequiredInd = installationRequiredInd;
	}
	public List<AvailableDateTimePeriodVO> getAvailableDateTimePeriodList() {
		if (this.availableDateTimePeriodList == null)
			this.availableDateTimePeriodList = new ArrayList<AvailableDateTimePeriodVO>();
		//Return a sorted list of dates
		Collections.sort(this.availableDateTimePeriodList);
		return this.availableDateTimePeriodList;
	}
	
	public void addAvailableDateTimePeriod(AvailableDateTimePeriodVO vo){
		if (this.availableDates == null)
			this.availableDates = new ArrayList<String>();
		
		if (! availableDates.contains(vo.getAvailableDate())){
			this.getAvailableDateTimePeriodList().add(vo);
			this.availableDates.add(vo.getAvailableDate());
		}
		
	}
	public void setDateList(List<AvailableDateTimePeriodVO> availableDateTimePeriodList) {
		this.availableDateTimePeriodList = availableDateTimePeriodList;
	}
	
	
}
