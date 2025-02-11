package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class AvailableDateTimePeriodVO implements Comparable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String availableDate;
	private static final String DATE_FORMAT_AVAILABLE_DATE = "yyyyMMdd";
	private final String className      = this.getClass().getSimpleName();
	private transient LoggerUtil logger           = LoggerUtil.getLogger(className);
	
	private List<AvailableTimePeriodVO> availableTimePeriodList;
	private String timeZone;
	
	public Date getFormattedAvailableDate(){
		
		Date formattedAvailableDate = null;
		
		try{
			formattedAvailableDate = new SimpleDateFormat(DATE_FORMAT_AVAILABLE_DATE).parse(this.availableDate);
		} catch (Exception e){
			logger.error(EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, 
					this.className + "::getFormattedAvailableDate", 
					"Fail to parse availableDate -> " + this.availableDate,e);
		}
		
		return formattedAvailableDate;
	}
	
	

	public String getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(String availableDate) {
		this.availableDate = availableDate;
	}

	public List<AvailableTimePeriodVO> getAvailableTimePeriodList() {
		return availableTimePeriodList;
	}

	public void setAvailableTimePeriodList(
			List<AvailableTimePeriodVO> availableTimePeriodList) {
		this.availableTimePeriodList = availableTimePeriodList;
	}
	
	public void addAvailableTimePeriod(AvailableTimePeriodVO vo){
		if (this.getAvailableTimePeriodList() == null){
			this.availableTimePeriodList = new ArrayList<AvailableTimePeriodVO>();
		}
		this.availableTimePeriodList.add(vo);
	}
	
	// Implement compareTo in order to sort by availableDate
	@Override
	public int compareTo(Object obj) {
		if (! (obj instanceof AvailableDateTimePeriodVO)){
			return -1;
		}
		
		AvailableDateTimePeriodVO vo = (AvailableDateTimePeriodVO) obj;
		return this.getAvailableDate().compareTo(vo.getAvailableDate());
	}
	
	public boolean equals(Object obj){
		if (obj instanceof AvailableDateTimePeriodVO){
			AvailableDateTimePeriodVO compareObj = (AvailableDateTimePeriodVO) obj;
			if (compareObj.availableDate.equals(this.availableDate)){
				return true;
			}
			
		}
		return false;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}
