package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.rest.domain.EntWLNSalesRestResponseBase;

/**
 * 
 * @author Jose.Mena
 *
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CustomerEligibilityRespRestDO extends EntWLNSalesRestResponseBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CreditEligibilityVO creditEligibility;
	private CreditAssessmentVO creditAssessment;

	//Stub related variables
	public static final String CUSTOMER_ELIGIBILITY_STUB_EST_MAX_99 = "EST_MAX_99";
	public static final String CUSTOMER_ELIGIBILITY_STUB_DEP_MAX_0 = "DEP_MAX_0";
	public static final String CUSTOMER_ELIGIBILITY_STUB_RESTRICTED = "RESTRICTED";
	public static final String CUSTOMER_ELIGIBILITY_STUB_FRAUD = "FRAUD";
	
	
	public CreditEligibilityVO getCreditEligibility() {
		return creditEligibility;
	}

	public void setCreditEligibility(CreditEligibilityVO creditEligibility) {
		this.creditEligibility = creditEligibility;
	}

	public CreditAssessmentVO getCreditAssessment() {
		return creditAssessment;
	}

	public void setCreditAssessment(CreditAssessmentVO creditAssessment) {
		this.creditAssessment = creditAssessment;
	}

	public static EntWLNSalesRestResponseBase getStub(CreditProfileVO creditProfile, String customerId, String appId, String channelOutletId, String salesTransactionId, String salesRepId, String extSalesRepId, String language, Boolean refreshInd, Boolean newCustomer, UriInfo uriInfo){
		String stubType;
		if (!StringUtils.isEmpty(salesTransactionId)){
			if (salesTransactionId.equals("1")){
				stubType = CUSTOMER_ELIGIBILITY_STUB_EST_MAX_99;
				return getStubDetailsEstMax99();
			} else if (salesTransactionId.equals("2")){
				stubType = CUSTOMER_ELIGIBILITY_STUB_DEP_MAX_0;
				return getStubDetailsDepMax0();
			} else if (salesTransactionId.equals("3")){
				stubType = CUSTOMER_ELIGIBILITY_STUB_RESTRICTED;
				return getStubDetailsRestricted();
			} else if (salesTransactionId.equals("4")){
				stubType = CUSTOMER_ELIGIBILITY_STUB_FRAUD;
				return getStubDetailsFraud();
			}
		}
		return new EntWLNSalesRestResponseBase();
	}
	
	public static EntWLNSalesRestResponseBase getStubDetailsEstMax99(){
		CustomerEligibilityRespRestDO customerEligResp = new CustomerEligibilityRespRestDO();
		CreditEligibilityVO creditEligibility = new CreditEligibilityVO();
		CreditAssessmentVO creditAssessment = new CreditAssessmentVO();
		List<EquipmentQualificationVO> equipmentQualificationList = new ArrayList<EquipmentQualificationVO>();
		List<ProductTypeQualificationVO> productTypeQualificationList = new ArrayList<ProductTypeQualificationVO>();
		//eligibility
		creditEligibility.setFraudInd(false);
		creditEligibility.setInTreatmentInd(false);
		
		EquipmentQualificationVO eq1 = new EquipmentQualificationVO();
		eq1.setProductTypeCd("HSIC");
		eq1.setMaxEquipmentNum(99);
		
		EquipmentQualificationVO eq2 = new EquipmentQualificationVO();
		eq2.setProductTypeCd("TTV");
		eq2.setMaxEquipmentNum(99);
		
		EquipmentQualificationVO eq3 = new EquipmentQualificationVO();
		eq3.setProductTypeCd("STV");
		eq3.setMaxEquipmentNum(99);
		
		equipmentQualificationList.add(eq1);
		equipmentQualificationList.add(eq2);
		equipmentQualificationList.add(eq3);
		
		creditEligibility.setEquipmentQualificationList(equipmentQualificationList);
		//assessment
		creditAssessment.setCreditValueCd("E");
		
		ProductTypeQualificationVO prod1 = new ProductTypeQualificationVO();
		prod1.setProductTypeCd("SING");
		prod1.setQualifiedInd(true);
		
		ProductTypeQualificationVO prod2 = new ProductTypeQualificationVO();
		prod2.setProductTypeCd("HSIC");
		prod2.setQualifiedInd(true);
		
		ProductTypeQualificationVO prod3 = new ProductTypeQualificationVO();
		prod3.setProductTypeCd("TTV");
		prod3.setQualifiedInd(true);
		
		productTypeQualificationList.add(prod1);
		productTypeQualificationList.add(prod2);
		productTypeQualificationList.add(prod3);
		
		creditAssessment.setProductTypeQualificationList(productTypeQualificationList);
		
		creditAssessment.setAssessmentMessageCd("REGCA01");
		creditAssessment.setPromotionalGiftInd(false);
		
		customerEligResp.setCreditEligibility(creditEligibility);
		customerEligResp.setCreditAssessment(creditAssessment);
		
		//message
		customerEligResp.setSuccessStatus(RESPONSE_OK);
		return customerEligResp;
	}
	
	public static EntWLNSalesRestResponseBase getStubDetailsDepMax0(){
		CustomerEligibilityRespRestDO customerEligResp = new CustomerEligibilityRespRestDO();
		CreditEligibilityVO creditEligibility = new CreditEligibilityVO();
		CreditAssessmentVO creditAssessment = new CreditAssessmentVO();
		List<EquipmentQualificationVO> equipmentQualificationList = new ArrayList<EquipmentQualificationVO>();
		List<ProductTypeQualificationVO> productTypeQualificationList = new ArrayList<ProductTypeQualificationVO>();
		//eligibility
		creditEligibility.setFraudInd(false);
		creditEligibility.setInTreatmentInd(false);
		
		EquipmentQualificationVO eq1 = new EquipmentQualificationVO();
		eq1.setProductTypeCd("HSIC");
		eq1.setMaxEquipmentNum(0);
		
		EquipmentQualificationVO eq2 = new EquipmentQualificationVO();
		eq2.setProductTypeCd("TTV");
		eq2.setMaxEquipmentNum(0);
		
		EquipmentQualificationVO eq3 = new EquipmentQualificationVO();
		eq3.setProductTypeCd("STV");
		eq3.setMaxEquipmentNum(0);
		
		equipmentQualificationList.add(eq1);
		equipmentQualificationList.add(eq2);
		equipmentQualificationList.add(eq3);
		
		creditEligibility.setEquipmentQualificationList(equipmentQualificationList);
		//assessment
		creditAssessment.setCreditValueCd("D");
		
		ProductTypeQualificationVO prod1 = new ProductTypeQualificationVO();
		prod1.setProductTypeCd("SING");
		prod1.setQualifiedInd(true);
		
		ProductTypeQualificationVO prod2 = new ProductTypeQualificationVO();
		prod2.setProductTypeCd("HSIC");
		prod2.setQualifiedInd(true);
		
		ProductTypeQualificationVO prod3 = new ProductTypeQualificationVO();
		prod3.setProductTypeCd("TTV");
		prod3.setQualifiedInd(true);
		
		productTypeQualificationList.add(prod1);
		productTypeQualificationList.add(prod2);
		productTypeQualificationList.add(prod3);
		
		creditAssessment.setProductTypeQualificationList(productTypeQualificationList);
		
		creditAssessment.setAssessmentMessageCd("REGCA02");
		creditAssessment.setPromotionalGiftInd(false);
		
		customerEligResp.setCreditEligibility(creditEligibility);
		customerEligResp.setCreditAssessment(creditAssessment);
		
		//message
		customerEligResp.setSuccessStatus(RESPONSE_OK);
		return customerEligResp;
	}
	
	public static EntWLNSalesRestResponseBase getStubDetailsRestricted(){
		CustomerEligibilityRespRestDO customerEligResp = new CustomerEligibilityRespRestDO();
		CreditEligibilityVO creditEligibility = new CreditEligibilityVO();
		CreditAssessmentVO creditAssessment = new CreditAssessmentVO();
		List<EquipmentQualificationVO> equipmentQualificationList = new ArrayList<EquipmentQualificationVO>();
		List<ProductTypeQualificationVO> productTypeQualificationList = new ArrayList<ProductTypeQualificationVO>();
		//eligibility
		creditEligibility.setFraudInd(false);
		creditEligibility.setInTreatmentInd(false);
		
//		EquipmentQualificationVO eq1 = new EquipmentQualificationVO();
//		eq1.setProductTypeCd("HSIC");
//		eq1.setMaxEquipmentNum(99);
//		
//		EquipmentQualificationVO eq2 = new EquipmentQualificationVO();
//		eq2.setProductTypeCd("TTV");
//		eq2.setMaxEquipmentNum(99);
//		
//		EquipmentQualificationVO eq3 = new EquipmentQualificationVO();
//		eq3.setProductTypeCd("STV");
//		eq3.setMaxEquipmentNum(99);
//		
//		equipmentQualificationList.add(eq1);
//		equipmentQualificationList.add(eq2);
//		equipmentQualificationList.add(eq3);
		
		creditEligibility.setEquipmentQualificationList(equipmentQualificationList);
		//assessment
		creditAssessment.setCreditValueCd("R");
		
		ProductTypeQualificationVO prod1 = new ProductTypeQualificationVO();
		prod1.setProductTypeCd("SLR");
		prod1.setQualifiedInd(true);
		
//		ProductTypeQualificationVO prod2 = new ProductTypeQualificationVO();
//		prod2.setProductTypeCd("HSIC");
//		prod2.setQualifiedInd(true);
//		
//		ProductTypeQualificationVO prod3 = new ProductTypeQualificationVO();
//		prod3.setProductTypeCd("TTV");
//		prod3.setQualifiedInd(true);
		
		productTypeQualificationList.add(prod1);
//		productTypeQualificationList.add(prod2);
//		productTypeQualificationList.add(prod3);
		
		creditAssessment.setProductTypeQualificationList(productTypeQualificationList);
		
		creditAssessment.setAssessmentMessageCd("REGCA03");
		creditAssessment.setPromotionalGiftInd(false);
		
		customerEligResp.setCreditEligibility(creditEligibility);
		customerEligResp.setCreditAssessment(creditAssessment);
		
		//message
		customerEligResp.setSuccessStatus(RESPONSE_OK);
		return customerEligResp;
	}
	
	public static EntWLNSalesRestResponseBase getStubDetailsFraud(){
		CustomerEligibilityRespRestDO customerEligResp = new CustomerEligibilityRespRestDO();
		CreditEligibilityVO creditEligibility = new CreditEligibilityVO();
		CreditAssessmentVO creditAssessment = new CreditAssessmentVO();
		List<EquipmentQualificationVO> equipmentQualificationList = new ArrayList<EquipmentQualificationVO>();
		List<ProductTypeQualificationVO> productTypeQualificationList = new ArrayList<ProductTypeQualificationVO>();
		//eligibility
		creditEligibility.setFraudInd(true);
		creditEligibility.setInTreatmentInd(false);
		creditEligibility.setEligibilityWarningMessageCd("ELIGWRNF");
		
		EquipmentQualificationVO eq1 = new EquipmentQualificationVO();
		eq1.setProductTypeCd("HSIC");
		eq1.setMaxEquipmentNum(99);
		
		EquipmentQualificationVO eq2 = new EquipmentQualificationVO();
		eq2.setProductTypeCd("TTV");
		eq2.setMaxEquipmentNum(99);
		
		EquipmentQualificationVO eq3 = new EquipmentQualificationVO();
		eq3.setProductTypeCd("STV");
		eq3.setMaxEquipmentNum(99);
		
		equipmentQualificationList.add(eq1);
		equipmentQualificationList.add(eq2);
		equipmentQualificationList.add(eq3);
		
		creditEligibility.setEquipmentQualificationList(equipmentQualificationList);
		//assessment
		creditAssessment.setCreditValueCd("F");
		
		ProductTypeQualificationVO prod1 = new ProductTypeQualificationVO();
		prod1.setProductTypeCd("SING");
		prod1.setQualifiedInd(true);
		
		ProductTypeQualificationVO prod2 = new ProductTypeQualificationVO();
		prod2.setProductTypeCd("HSIC");
		prod2.setQualifiedInd(true);
		
		ProductTypeQualificationVO prod3 = new ProductTypeQualificationVO();
		prod3.setProductTypeCd("TTV");
		prod3.setQualifiedInd(true);
		
		productTypeQualificationList.add(prod1);
		productTypeQualificationList.add(prod2);
		productTypeQualificationList.add(prod3);
		
		creditAssessment.setProductTypeQualificationList(productTypeQualificationList);
		
		creditAssessment.setAssessmentMessageCd("DFFRD03");
		creditAssessment.setPromotionalGiftInd(false);
		creditAssessment.setFraudMessageCdList(Arrays.asList("FRAUD01", "FRAUD08", "FRAUD03", "FRAUD02"));
		
		customerEligResp.setCreditEligibility(creditEligibility);
		customerEligResp.setCreditAssessment(creditAssessment);
		
		//message
		customerEligResp.setSuccessStatus(RESPONSE_OK);
		return customerEligResp;
	}
}
