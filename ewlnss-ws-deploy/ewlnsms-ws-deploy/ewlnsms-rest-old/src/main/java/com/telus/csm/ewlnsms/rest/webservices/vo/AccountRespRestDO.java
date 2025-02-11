package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.rest.domain.EntWLNRestResponseStatus;
import com.telus.csm.ewlnsc.rest.domain.EntWLNSalesRestResponseBase;



@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class AccountRespRestDO extends EntWLNSalesRestResponseBase implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private String ban;
	private String payChannelNum;
	private String billCycleCd;
	private AddressVerificationSummaryVO creditAddressVerification;
	private AddressVerificationSummaryVO billingAddressVerification;
	private List<CustomerInfoSummaryVO> matchingCustomerInfoList;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getBan() {
		return ban;
	}
	public void setBan(String ban) {
		this.ban = ban;
	}
	public String getPayChannelNum() {
		return payChannelNum;
	}
	public void setPayChannelNum(String payChannelNum) {
		this.payChannelNum = payChannelNum;
	}
	public String getBillCycleCd() {
		return billCycleCd;
	}
	public void setBillCycleCd(String billCycleCd) {
		this.billCycleCd = billCycleCd;
	}
	public AddressVerificationSummaryVO getCreditAddressVerification() {
		return creditAddressVerification;
	}
	public void setCreditAddressVerification(AddressVerificationSummaryVO creditAddressVerification) {
		this.creditAddressVerification = creditAddressVerification;
	}
	public AddressVerificationSummaryVO getBillingAddressVerification() {
		return billingAddressVerification;
	}
	public void setBillingAddressVerification(AddressVerificationSummaryVO billingAddressVerification) {
		this.billingAddressVerification = billingAddressVerification;
	}
	public List<CustomerInfoSummaryVO> getMatchingCustomerInfoList() {
		
		if (matchingCustomerInfoList == null) {
			matchingCustomerInfoList = new ArrayList<CustomerInfoSummaryVO>();
		}
		
		return matchingCustomerInfoList;
	}
	public void setMatchingCustomerInfoList(List<CustomerInfoSummaryVO> matchingCustomerInfoList) {
		this.matchingCustomerInfoList = matchingCustomerInfoList;
	}
	public static String STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_ACTIVE = "1";
	public static String STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_NON_POS_BAL = "2";
	public static String STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_POS_BAL = "3";
	public static String STUB_TYPE_CUSTOMER_ACCOUNT_CREATED = "4";
	public static String STUB_TYPE_ACCOUNT_CREATED = "5";

	public static EntWLNSalesRestResponseBase getStub(String type, String custId) {
		
		AccountRespRestDO result = new AccountRespRestDO();
		if (STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_ACTIVE.equals(type)) {
			CustomerInfoSummaryVO e = new CustomerInfoSummaryVO();
			result.getMatchingCustomerInfoList().add(e);
			e.setCustomerId("88351941");
			e.setFirstName("John");
			e.setLastName("Brown");
			try {
//				e.setBirthDt(MapperUtil.toXMLGregorianCalendar("1988-03-14"));
				e.setBirthDt("1988-03-14");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.setTotalBalance((double) 100);
			MatchingAccountVO a = new MatchingAccountVO();
			e.getActiveAccountList().add(a);
			a.setBillingAccountNum("602192300");
			AddressVO ba = new AddressVO();
			a.setBillingAddress(ba);
			ba.setStreetNum(4813);
			ba.setStreetName("50 ST");
			ba.setCity("BERWYN");
			ba.setProvinceCd("AB");
			ba.setCountryCd("CAN");
			ba.setPostalCd("T2P2P2");
			
			MatchingAccountVO a2 = new MatchingAccountVO();
			e.getActiveAccountList().add(a2);
			a2.setBillingAccountNum("602192309");
			AddressVO ba2 = new AddressVO();
			a2.setBillingAddress(ba2);
			ba2.setStreetNum(3988);
			ba2.setStreetName("50 ST");
			ba2.setCity("BERWYN");
			ba2.setProvinceCd("AB");
			ba2.setCountryCd("CAN");
			ba2.setPostalCd("T2P2P1");
			
			result.setSuccessStatus(RESPONSE_OK);

		} else if (STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_NON_POS_BAL.equals(type)) {
			CustomerInfoSummaryVO e = new CustomerInfoSummaryVO();
			result.getMatchingCustomerInfoList().add(e);
			e.setCustomerId("88351941");
			e.setFirstName("John");
			e.setLastName("Brown");
			try {
//				e.setBirthDt(MapperUtil.toXMLGregorianCalendar("1988-03-14"));
				e.setBirthDt("1988-03-14");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.setTotalBalance((double) 0);
			MatchingAccountVO a = new MatchingAccountVO();
			e.getCancelledAccountList().add(a);
			a.setBillingAccountNum("602192300");
			AddressVO ba = new AddressVO();
			a.setBillingAddress(ba);
			ba.setStreetNum(4813);
			ba.setStreetName("50 ST");
			ba.setCity("BERWYN");
			ba.setProvinceCd("AB");
			ba.setCountryCd("CAN");
			ba.setPostalCd("T2P2P2");
			
			MatchingAccountVO a2 = new MatchingAccountVO();
			e.getCancelledAccountList().add(a2);
			a2.setBillingAccountNum("602192309");
			AddressVO ba2 = new AddressVO();
			a2.setBillingAddress(ba2);
			ba2.setStreetNum(3988);
			ba2.setStreetName("50 ST");
			ba2.setCity("BERWYN");
			ba2.setProvinceCd("AB");
			ba2.setCountryCd("CAN");
			ba2.setPostalCd("T2P2P1");
			
			result.setSuccessStatus(RESPONSE_OK);
		} else if (STUB_TYPE_MATCHING_CREADIT_PROFILE_BAN_POS_BAL.equals(type)) {
			CustomerInfoSummaryVO e = new CustomerInfoSummaryVO();
			result.getMatchingCustomerInfoList().add(e);
			e.setCustomerId("88351941");
			e.setFirstName("John");
			e.setLastName("Brown");
			try {
//				e.setBirthDt(MapperUtil.toXMLGregorianCalendar("1988-03-14"));
				e.setBirthDt("1988-03-14");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.setTotalBalance((double) 200);
			MatchingAccountVO a = new MatchingAccountVO();
			e.getCancelledAccountList().add(a);
			a.setBillingAccountNum("602192300");
			AddressVO ba = new AddressVO();
			a.setBillingAddress(ba);
			ba.setStreetNum(4813);
			ba.setStreetName("50 ST");
			ba.setCity("BERWYN");
			ba.setProvinceCd("AB");
			ba.setCountryCd("CAN");
			ba.setPostalCd("T2P2P2");
			
			MatchingAccountVO a2 = new MatchingAccountVO();
			e.getCancelledAccountList().add(a2);
			a2.setBillingAccountNum("602192309");
			AddressVO ba2 = new AddressVO();
			a2.setBillingAddress(ba2);
			ba2.setStreetNum(3988);
			ba2.setStreetName("50 ST");
			ba2.setCity("BERWYN");
			ba2.setProvinceCd("AB");
			ba2.setCountryCd("CAN");
			ba2.setPostalCd("T2P2P1");
			
			result.setSuccessStatus(RESPONSE_OK);
		} else if (STUB_TYPE_CUSTOMER_ACCOUNT_CREATED.equals(type)) {
			result.setCustomerId("88351940");
			result.setBan("602192399");
			result.setPayChannelNum("602569339");
			result.setBillCycleCd("1");
			result.setSuccessStatus(RESPONSE_OK);
		} else if (STUB_TYPE_ACCOUNT_CREATED.equals(type)) {
			result.setCustomerId(custId);
			result.setBan("602192399");
			result.setPayChannelNum("602569339");
			result.setBillCycleCd("1");
			result.setSuccessStatus(RESPONSE_OK);

		}					

		return result;
	}

	
}
