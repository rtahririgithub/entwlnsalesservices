package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javax.xml.datatype.XMLGregorianCalendar;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class CustomerInfoSummaryVO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String customerId;
	private String firstName;
	private String lastName;
//	private XMLGregorianCalendar birthDt;
	private String birthDt;
	private Double totalBalance;
	private List<MatchingAccountVO> activeAccountList;
	private List<MatchingAccountVO> closedAccountWithBalanceList;
	private List<MatchingAccountVO> cancelledAccountList;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDt() {
		return birthDt;
	}
	public void setBirthDt(String birthDt) {
		this.birthDt = birthDt;
	}
	public Double getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}
	public List<MatchingAccountVO> getActiveAccountList() {
		
		if (activeAccountList == null) {
			activeAccountList = new ArrayList<MatchingAccountVO>();
		}
		
		return activeAccountList;
	}
	public void setActiveAccountList(List<MatchingAccountVO> activeAccountList) {
		this.activeAccountList = activeAccountList;
	}
	public List<MatchingAccountVO> getClosedAccountWithBalanceList() {
		
		if (closedAccountWithBalanceList == null) {
			closedAccountWithBalanceList = new ArrayList<MatchingAccountVO>();
		}
		
		return closedAccountWithBalanceList;
	}
	public void setClosedAccountWithBalanceList(List<MatchingAccountVO> closedAccountWithBalanceList) {
		this.closedAccountWithBalanceList = closedAccountWithBalanceList;
	}
	public List<MatchingAccountVO> getCancelledAccountList() {
		
		if (cancelledAccountList == null) {
			cancelledAccountList = new ArrayList<MatchingAccountVO>();
		}
		
		return cancelledAccountList;
	}
	public void setCancelledAccountList(List<MatchingAccountVO> cancelledAccountList) {
		this.cancelledAccountList = cancelledAccountList;
	}

}
