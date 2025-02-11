/**
 * 
 */
package com.telus.csm.ewlnsms.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.MatchingCustomerInfo;

/**
 * @author x145592
 *
 */
public class CreateWLNAccountCoreResponse extends CoreResponseBase {

	private static final long serialVersionUID = 1L;

    private String customerId;
    private String billingAccountNumber;
    private String payChannelNumber;
    private String billCycleCd;
    private List<MatchingCustomerInfo> matchingCustomerInfoList;
    
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}
	
	public String getPayChannelNumber() {
		return payChannelNumber;
	}
	public void setPayChannelNumber(String payChannelNumber) {
		this.payChannelNumber = payChannelNumber;
	}
	
	public String getBillCycleCd() {
		return billCycleCd;
	}
	public void setBillCycleCd(String billCycleCd) {
		this.billCycleCd = billCycleCd;
	}
	
	public List<MatchingCustomerInfo> getMatchingCustomerInfoList() {
		if (matchingCustomerInfoList == null) {
			matchingCustomerInfoList = new ArrayList<MatchingCustomerInfo>();
		}
		return matchingCustomerInfoList;
	}
	public void setMatchingCustomerInfoList(List<MatchingCustomerInfo> matchingCustomerInfoList) {
		this.matchingCustomerInfoList = matchingCustomerInfoList;
	}
	


    
}
