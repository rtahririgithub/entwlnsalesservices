package com.telus.csm.ewlnsvs.core.operation;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.*;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IEnterpriseCreditProfileManagementServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNCreditEligibilityProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileManagementProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterResponse;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.helper.AdapterResponseUtil;
import com.telus.csm.ewlnsc.helper.ValidationUtil;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.ValidateFieldsUtil;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnsvs.core.domain.GetCustomerEligibilityCoreRequest;
import com.telus.csm.ewlnsvs.core.transformer.GetCustomerEligibilityCoreOperationTransformer;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityType;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CustomerGuarantor;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.DriverLicense;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.HealthCard;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.Passport;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.PersonalInfo;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.ProductCategory;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.ProvincialIdCard;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.EquipmentCategory;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.CreditWorthiness;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.CreditAssessmentResult;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.CreditEligibilityResult;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.PersonalCreditInformation;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductEquipment;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductTypeQualification;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCustomerEligibilityCoreOperation {
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(GetCustomerEligibilityCoreOperation.class);
	private GetCustomerEligibilityResponseType response = new GetCustomerEligibilityResponseType();
	private static final String COMBINATION_SEPARATOR = "--> (";
	
	public GetCustomerEligibilityResponseType execute(GetCustomerEligibilityType param) {
		final String methodName = "GetCustomerEligibilityCoreOperation.execute()";
		LOGGER.enter(methodName);
		GetCustomerEligibilityCoreRequest rq;
		// Transform Request
		rq = GetCustomerEligibilityCoreOperationTransformer.transformRequest(param);
		// validate Request
		List<String> missingElementList = new ArrayList<String>();
		List<String> invalidInputList = new ArrayList<String>();

		validateInput(rq, missingElementList, invalidInputList);
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
			response.setMessageList(generateMessageList(rq, missingElementList, invalidInputList));
			LOGGER.info(methodName, "Input Validation failed.");
			return response;
		}

		IEnterpriseCreditProfileManagementServiceAdapter entCreditProfMgSvcAdapter = AdapterFactory.getAdapter(IEnterpriseCreditProfileManagementServiceAdapter.class);
		IWLNCreditProfileManagementProxyServiceAdapter wlnCreditProfMgProxySvcAdapter = AdapterFactory.getAdapter(IWLNCreditProfileManagementProxyServiceAdapter.class);
		IWLNCreditEligibilityProxyServiceAdapter wlnCreditEligProxySvcAdapter = AdapterFactory.getAdapter(IWLNCreditEligibilityProxyServiceAdapter.class);
		IWLNCreditProfileMgmtSvcAdapter wlnCreditProfileMgmtSvcAdapter = AdapterFactory.getAdapter(IWLNCreditProfileMgmtSvcAdapter.class);

		AssessCreditWorthinessAdapterResponse respWorthinessDO;
		GetCreditEligibilityAdapterResponse respGetCreditEligDO;
		GetCreditProfileByCustomerIdAdapterResponse respGetCreditProfileByCustomerIdDO;
		// Credit Information Provided ?
		if (rq.getCreditProfile() != null) {
			LOGGER.info(methodName, "Credit Profile is provided.");
			UpdateCreditProfileAdapterResponse respDO = updateCreditProfile(rq, entCreditProfMgSvcAdapter);
			if (respDO.isMsgHasError()) {
				AdapterResponseUtil.propagateMessage(respDO, response.getMessageList());
				// check if policy/service or general exception
				changePolicyErrorCode(respDO);
				return response;
			}
			respWorthinessDO = assessCreditWorthiness(rq, wlnCreditProfMgProxySvcAdapter);
			if (respWorthinessDO.isMsgHasError()) {
				AdapterResponseUtil.propagateMessage(respWorthinessDO, response.getMessageList());
				changePolicyErrorCode(respWorthinessDO);
				return response;
			}

		} else if (rq.getNewCustomerInd()) {
			// Credit Information Not Provided and New Client Indicator = true
			LOGGER.info(methodName, "Credit Information Not Provided and New Customer Indicator = true.");
			respWorthinessDO = assessCreditWorthiness(rq, wlnCreditProfMgProxySvcAdapter);
			if (respWorthinessDO.isMsgHasError()) {
				AdapterResponseUtil.propagateMessage(respWorthinessDO, response.getMessageList());
				// check if policy/service or general exception
				changePolicyErrorCode(respWorthinessDO);
				return response;
			}

		} else {
			// Credit Information Not Provided and New Client Indicator = false
			LOGGER.info(methodName, "Credit Information Not Provided and New Customer Indicator = false.");
			respGetCreditProfileByCustomerIdDO = getCreditProfileByCustomerId(rq, wlnCreditProfileMgmtSvcAdapter);
			if (respGetCreditProfileByCustomerIdDO.isMsgHasError()) {
				AdapterResponseUtil.propagateMessage(respGetCreditProfileByCustomerIdDO, response.getMessageList());
				// check if policy/service or general exception
				changePolicyErrorCode(respGetCreditProfileByCustomerIdDO);
				return response;
			}
		}
		
		respGetCreditEligDO = getCreditEligibility(rq, wlnCreditEligProxySvcAdapter);
		setCreditEligibilityError(respGetCreditEligDO);
		LOGGER.exit(methodName);
		return response;

	}

	/**
	 * @param respGetCreditEligDO
	 */
	private void setCreditEligibilityError(GetCreditEligibilityAdapterResponse respGetCreditEligDO) {
		if (respGetCreditEligDO.isMsgHasError()) {
			AdapterResponseUtil.propagateMessage(respGetCreditEligDO, response.getMessageList());
			// check if policy/service or general exception
			changePolicyErrorCode(respGetCreditEligDO);
			// Remove previously set Credit Assessment
			response.setCreditAssessment(null);
		}
	}

	private List<MessageList> generateMessageList(GetCustomerEligibilityCoreRequest rq, List<String> missingElementList,
			List<String> invalidInputList) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();
		String salesTransactionId = "";
		// iterate over missing element list
		for (String msg : missingElementList) {
			sbMissing.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		// iterate over invalid input
		for (String msg : invalidInputList) {
			sbInvalid.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		if (rq.getOperationHeader() != null && rq.getOperationHeader().getSalesTransactionId() != null) {
			salesTransactionId = rq.getOperationHeader().getSalesTransactionId();
		}
		// create the message
		if (!missingElementList.isEmpty()) {
			MessageList messageMissing = new MessageList();
			messageMissing.setDateTimeStamp(new Date());
			messageMissing.setTransactionId(salesTransactionId);

			messageMissing.setErrorCode(CUSTOMER_ELIG_MISSING_MANDATORY_ELEMENTS);
			messageMissing.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage(EnterpriseWLNSalesServicesConstants.MANDATORY_INPUT_MESSAGE_TEXT);
			messageMissing.setMessageList(Arrays.asList(msg));

			// set context data
			messageMissing.setContextData(sbMissing.toString());

			messageList.add(messageMissing);
		}
		if (!invalidInputList.isEmpty()) {
			MessageList messageInvalid = new MessageList();
			messageInvalid.setDateTimeStamp(new Date());
			messageInvalid.setTransactionId(salesTransactionId);

			messageInvalid.setErrorCode(CUSTOMER_ELIG_INVALID_INPUT);
			messageInvalid.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage(EnterpriseWLNSalesServicesConstants.INVALID_INPUT_MESSAGE_TEXT);
			messageInvalid.setMessageList(Arrays.asList(msg));

			// set context data
			messageInvalid.setContextData(sbInvalid.toString());

			messageList.add(messageInvalid);
		}

		return messageList;
	}

	private GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustomerId(
			GetCustomerEligibilityCoreRequest rq, IWLNCreditProfileMgmtSvcAdapter wlnCreditProfileMgmtSvcAdapter) {
		String functionName = "getCreditProfileByCustomerId()";
		LOGGER.enter(functionName);
		GetCreditProfileByCustomerIdAdapterRequest getCreditProfileByCustIdRequestDO = GetCustomerEligibilityCoreOperationTransformer
				.buildGetCreditProfileByCustId(rq);
		GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustIdResponseDO = wlnCreditProfileMgmtSvcAdapter.getCreditProfileByCustomerId(getCreditProfileByCustIdRequestDO);
		// check for errors
		if (!getCreditProfileByCustIdResponseDO.isMsgHasError()
				&& getCreditProfileByCustIdResponseDO.getCreditProfile() != null
				&& getCreditProfileByCustIdResponseDO.getCreditProfile().getCreditWorthiness() != null) {
			// populate creditAssessment element in final response
			response.setCreditAssessment(getCreditAssessmentFromResponseDO(getCreditProfileByCustIdResponseDO.getCreditProfile().getCreditWorthiness()));
		}
		LOGGER.exit(functionName);
		return getCreditProfileByCustIdResponseDO;
	}

	private CreditAssessmentResult getCreditAssessmentFromResponseDO(CreditWorthiness creditWorthiness) {
		CreditAssessmentResult creditAssesment = new CreditAssessmentResult();
		creditAssesment.setCustomerId(Long.toString(creditWorthiness.getCustomerID()));
		creditAssesment.setCreditValueCode(creditWorthiness.getCreditValueCd());

		List<ProductTypeQualification> productTypeQualificationList = new ArrayList<ProductTypeQualification>();

		if (creditWorthiness.getProductCategoryQualification() != null
				&& creditWorthiness.getProductCategoryQualification().getProductCategoryList() != null
				&& !creditWorthiness.getProductCategoryQualification().getProductCategoryList().isEmpty()) {
			for (ProductCategory tempProd : creditWorthiness.getProductCategoryQualification().getProductCategoryList()) {
				ProductTypeQualification prod = new ProductTypeQualification();
				prod.setProductTypeCode(tempProd.getCategoryCd());
				prod.setQualifiedInd(tempProd.isQualified());
				productTypeQualificationList.add(prod);
			}
		}
		creditAssesment.setProductTypeQualificationList(productTypeQualificationList);
		creditAssesment.setAssessmentMessageCode(creditWorthiness.getAssessmentMessageCd());
		creditAssesment.setFraudIndicatorCode(creditWorthiness.getFraudIndicatorCd());
		// creditAssesment.setDepositAmount(value); // No DepositAmount related
		// property found in WLNCreditProfileManagementService Schema
		// creditAssesment.setRtcaSystemErrorCode(value); // No
		// RtcaSystemErrorCode related property found in
		// WLNCreditProfileManagementService Schema
		if (creditWorthiness.getProductCategoryQualification() != null) {
			creditAssesment.setPromotionalGiftInd(creditWorthiness.getProductCategoryQualification().isBoltOnInd());
		}
		creditAssesment.setFraudMessageCodeList(creditWorthiness.getFraudMessageCdList());
		return creditAssesment;
	}

	private GetCreditEligibilityAdapterResponse getCreditEligibility(GetCustomerEligibilityCoreRequest rq,
			IWLNCreditEligibilityProxyServiceAdapter wlnCreditEligProxySvcAdapter) {
		String functionName = "getCreditEligibility()";
		LOGGER.enter(functionName);
		// Call WLNCreditEligibilityProxyService.getCreditEligibility
		GetCreditEligibilityAdapterRequest getCreditEligibilityRequestDO = GetCustomerEligibilityCoreOperationTransformer.buildCreditEligibilityRequestDO(rq);
		GetCreditEligibilityAdapterResponse getCreditEligibilityResponseDO = wlnCreditEligProxySvcAdapter.getCreditEligibility(getCreditEligibilityRequestDO);
		// check for errors
		if (!getCreditEligibilityResponseDO.isMsgHasError()) {
			// populate creditEligibility element in final response
			response.setCreditEligibility(getCreditEligibilityFromDO(getCreditEligibilityResponseDO));
		}
		LOGGER.exit(functionName);
		return getCreditEligibilityResponseDO;
	}

	private AssessCreditWorthinessAdapterResponse assessCreditWorthiness(GetCustomerEligibilityCoreRequest rq,
			IWLNCreditProfileManagementProxyServiceAdapter wlnCreditProfMgProxySvcAdapter) {
		String functionName = "assessCreditWorthiness()";
		LOGGER.enter(functionName);
		// Call WLNCreditProfileManagementProxyService --> Assess Credit Worthiness
		AssessCreditWorthinessAdapterRequest assessCreditWorthinessRequestDO = GetCustomerEligibilityCoreOperationTransformer.buildAssessCreditWorthinessRequestDO(rq);
		AssessCreditWorthinessAdapterResponse assessCreditWorthinessResponseDO = wlnCreditProfMgProxySvcAdapter.assessCreditWorthiness(assessCreditWorthinessRequestDO);
		// Check for errors
		if (!assessCreditWorthinessResponseDO.isMsgHasError()) {
			// populate creditAssessment element in final response
			response.setCreditAssessment(getCreditAssessmentFromDO(assessCreditWorthinessResponseDO));
		}
		LOGGER.exit(functionName);
		return assessCreditWorthinessResponseDO;
	}

	private UpdateCreditProfileAdapterResponse updateCreditProfile(GetCustomerEligibilityCoreRequest rq,
			IEnterpriseCreditProfileManagementServiceAdapter entCreditProfMgSvcAdapter) {
		// Call EnterpriseCreditProfileManagementService --> updateCreditProfile
		String functionName = "updateCreditProfile()";
		LOGGER.enter(functionName);
		UpdateCreditProfileAdapterRequest reqDO = GetCustomerEligibilityCoreOperationTransformer.transformRequest(rq);
		UpdateCreditProfileAdapterResponse respDO = entCreditProfMgSvcAdapter.updateCreditProfile(reqDO);
		LOGGER.exit(functionName);
		return respDO;
	}

	private CreditEligibilityResult getCreditEligibilityFromDO(
			GetCreditEligibilityAdapterResponse getCreditEligibilityResponseDO) {
		CreditEligibilityResult credElig = new CreditEligibilityResult();

		List<ProductEquipment> equipList = new ArrayList<ProductEquipment>();
		if (getCreditEligibilityResponseDO.getEquipmentQualificationList() != null
				&& getCreditEligibilityResponseDO.getEquipmentQualificationList().getEquipmentCategory() != null
				&& !getCreditEligibilityResponseDO.getEquipmentQualificationList().getEquipmentCategory().isEmpty()) {
			for (EquipmentCategory tempEq : getCreditEligibilityResponseDO.getEquipmentQualificationList().getEquipmentCategory()) {
				ProductEquipment eq = new ProductEquipment();
				eq.setProductTypeCode(tempEq.getProductCd());
				eq.setMaxNumberOfEquipment(tempEq.getMaxCount());
				equipList.add(eq);
			}
		}
		credElig.setEquipmentQualificationList(equipList);
		credElig.setEligibilityWarningMessageCd(getCreditEligibilityResponseDO.getEligibilityWarningMessageCd());
		credElig.setFraudInd(getCreditEligibilityResponseDO.getFraudInd());
		credElig.setInTreatementInd(getCreditEligibilityResponseDO.getCollectionInd());

		return credElig;
	}

	private CreditAssessmentResult getCreditAssessmentFromDO(AssessCreditWorthinessAdapterResponse respDO) {
		CreditAssessmentResult resp = new CreditAssessmentResult();
		resp.setCreditValueCode(respDO.getAssessedCreditWorthiness().getCreditValueCd());
		resp.setAssessmentMessageCode(respDO.getAssessedCreditWorthiness().getAssessmentMessageCd());

		List<ProductTypeQualification> productTypeQualificationList = new ArrayList<ProductTypeQualification>();
		if (respDO.getAssessedCreditWorthiness() != null
				&& respDO.getAssessedCreditWorthiness().getProductCategoryQualification() != null
				&& respDO.getAssessedCreditWorthiness().getProductCategoryQualification().getProductCategoryList() != null) {
			for (ProductCategory tempProd : respDO.getAssessedCreditWorthiness().getProductCategoryQualification().getProductCategoryList()) {
				ProductTypeQualification prod = new ProductTypeQualification();
				prod.setProductTypeCode(tempProd.getCategoryCd());
				prod.setQualifiedInd(tempProd.isQualified());
				productTypeQualificationList.add(prod);
			}
		}
		if (!productTypeQualificationList.isEmpty()) {
			resp.setProductTypeQualificationList(productTypeQualificationList);
		}

		if (respDO.getAssessedCreditWorthiness() != null && respDO.getAssessedCreditWorthiness().getProductCategoryQualification() != null) {
			resp.setPromotionalGiftInd(respDO.getAssessedCreditWorthiness().getProductCategoryQualification().isBoltOnInd());
		}

		resp.setFraudMessageCodeList(respDO.getAssessedCreditWorthiness().getFraudMessageCdList());
		return resp;
	}

	private void validateInput(GetCustomerEligibilityCoreRequest rq, List<String> missingList, List<String> invalidInputList) {
		String functionName = "validateInput()";
		Map<String, Object> fields = new HashMap<String, Object>();
		LOGGER.enter(functionName);
		final String missingElementStr = "Missing Element(s): ";
		final String invalidElementStr = "Invalid Element(s): ";
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();
		// validate Header
		ValidationUtil.validateHeader(rq.getOperationHeader(), missingList, invalidInputList);
		
		// validate Customer ID
		validateCustomerId(rq, missingList, invalidInputList, sbMissing, sbInvalid);
		
		// validate New Customer Indicator
		validateNewCustomerInd(rq, missingList, sbMissing);
		
		//validate rq.getCreditProfile().getCreditAddress() is NOT null when creditProfile is provided (This requirement was suggested in code review)
		if (rq.getCreditProfile() != null){
			/* ****************************** Validation of Customer Guarantor: request.creditProfile.customerGuarantor() --- START --- ****************************** */
			validateCustomerGuarantor(rq, missingList);
			/* ****************************** Validation of Customer Guarantor: request.creditProfile.customerGuarantor() --- END --- ****************************** */
			
			Address creditAddress = rq.getCreditProfile().getCreditAddress();
			
			if (creditAddress == null){
				sbMissing.append(CUSTOMER_ELIG_MISSING_CREDIT_ADDRESS).append(NEWLINE);
				missingList.add(CUSTOMER_ELIG_MISSING_CREDIT_ADDRESS);
			}else{
				
				/* ****************************** Validation of Credit Address: request.creditProfile.creditAddress() --- START --- ****************************** */
				validateCreditAddress(missingList, invalidInputList, sbMissing, creditAddress);
				
				validateCreditAddressExclusiveElement(missingList, invalidInputList, sbMissing, sbInvalid, creditAddress);
				
				/* ****************************** Validation of Credit Address: request.creditProfile.creditAddress() --- END --- ****************************** */
			}
			
			//validate request.creditProfile.personalInfo() mandatory fields if personalInfo is passed
			/* ****************************** Validation of Personal Info: request.creditProfile.personalInfo() --- START --- ****************************** */
			
			PersonalInfo personalInfo = rq.getCreditProfile().getPersonalInfo();
			
			if (personalInfo == null){
				//validate personalInfo
				sbMissing.append(CUSTOMER_ELIG_MISSING_PERSONAL_INFO).append(NEWLINE);
				missingList.add(CUSTOMER_ELIG_MISSING_PERSONAL_INFO);
			}else{
				validatePersonalInfoElements(missingList, sbMissing, personalInfo);
				//validate creditProfile check
				if (rq.getCreditProfile().getCreditIdentification() != null){
					
					PersonalCreditInformation creditIdentification = rq.getCreditProfile().getCreditIdentification();
					ProvincialIdCard provincialIdCard = creditIdentification.getProvincialIdCard();
					HealthCard healthCard = creditIdentification.getHealthCard();
					DriverLicense driverLicense = creditIdentification.getDriverLicense();
					Passport passport = creditIdentification.getPassport();
					String sin = creditIdentification.getSin();
					// driversLicense
					validateDriversLicense(missingList, sbMissing, driverLicense);
					// passport
					validatePassport(missingList, sbMissing, passport);
					// healthCard

					// provincialIdCard
					validateProvincialIdCard(missingList, sbMissing, provincialIdCard);
					
					// at least one piece of Ids
					boolean areIdsNull = areIdsNull(provincialIdCard, healthCard, driverLicense, passport);
					validateAtLeastOnePieceId(invalidInputList, fields, sbInvalid, personalInfo, sin, areIdsNull);
					
				}
			}
			
			/* ****************************** Validation of Personal Info: request.creditProfile.personalInfo() --- END --- ****************************** */
			
		}
		
		//log messages
		if (sbMissing.length() > 0){
			LOGGER.error("", functionName, missingElementStr + " " + sbMissing.toString());
		}
		if (sbInvalid.length() > 0){
			LOGGER.error("", functionName, invalidElementStr + " " + sbInvalid.toString());
		}
		LOGGER.exit(functionName);
	}

	/**
	 * @param invalidInputList
	 * @param fields
	 * @param sbInvalid
	 * @param personalInfo
	 * @param sin
	 * @param areIdsNull
	 */
	private void validateAtLeastOnePieceId(List<String> invalidInputList, Map<String, Object> fields,
			StringBuilder sbInvalid, PersonalInfo personalInfo, String sin, boolean areIdsNull) {
		if (personalInfo != null && "Y".equalsIgnoreCase(personalInfo.getCreditCheckConsentCd())){
			boolean isValid = true;
			if (areIdsNull && StringUtils.isEmpty(sin)){
				isValid = false;
			}
			if (!isValid){
				sbInvalid.append(CUSTOMER_ELIG_INVALID_COMB_PIECE_OF_ID).append(NEWLINE);
				invalidInputList.add(CUSTOMER_ELIG_INVALID_COMB_PIECE_OF_ID + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	/**
	 * @param missingList
	 * @param sbMissing
	 * @param personalInfo
	 */
	private void validatePersonalInfoElements(List<String> missingList, StringBuilder sbMissing,
			PersonalInfo personalInfo) {
		if (StringUtils.isEmpty(personalInfo.getEmploymentStatusCd())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_PERS_EMPLOYMENT_STATUS_CD).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_PERS_EMPLOYMENT_STATUS_CD);
		}
		if (StringUtils.isEmpty(personalInfo.getResidencyCd())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_PERS_RESIDENCY_CD).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_PERS_RESIDENCY_CD);
		}
		if (StringUtils.isEmpty(personalInfo.getCreditCheckConsentCd())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_PERS_CREDIT_CHECK_CD).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_PERS_CREDIT_CHECK_CD);
		}
		if (personalInfo.getBirthDate() == null){
			sbMissing.append(CUSTOMER_ELIG_MISSING_PERS_BIRTHDT).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_PERS_BIRTHDT);
		}
		if (StringUtils.isEmpty(personalInfo.getProvinceOfCurrentResidenceCd())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_PERS_PROV_CUR_RES).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_PERS_PROV_CUR_RES);
		}
	}

	/**
	 * @param missingList
	 * @param invalidInputList
	 * @param sbMissing
	 * @param creditAddress
	 */
	private void validateCreditAddress(List<String> missingList, List<String> invalidInputList, StringBuilder sbMissing,
			Address creditAddress) {
		if (StringUtils.isEmpty(creditAddress.getMunicipalityName())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_CRED_MUNICIPALITY_NAME).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_CRED_MUNICIPALITY_NAME);
		}
		if (StringUtils.isEmpty(creditAddress.getProvinceStateCode())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_CRED_PROVINCE).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_CRED_PROVINCE);
		}
		if (StringUtils.isEmpty(creditAddress.getCountryCode())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_CRED_COUNTRY).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_CRED_COUNTRY);
		}else{
			validateCountryCodeAndProvince(invalidInputList, creditAddress);
		}
		// Allan: postalZipCode must be a mandatory input validation for accountInfo.billingAddress and customerInfo.creditProfile.creditAddress (but not customerInfo.mainAddress).
		if (StringUtils.isEmpty(creditAddress.getPostalZipCode())){
			sbMissing.append(CUSTOMER_ELIG_MISSING_CRED_POSTALZIPCODE).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_CRED_POSTALZIPCODE);
		}else if (!ValidateFieldsUtil.validateRegExp(POSTAL_ZIP_CODE_REG_EXP, creditAddress.getPostalZipCode())){
			invalidInputList.add(CUSTOMER_ELIG_INVALID_CUST_POSTAL_ZIP_CODE);
		}
	}

	/**
	 * @param invalidInputList
	 * @param creditAddress
	 */
	private void validateCountryCodeAndProvince(List<String> invalidInputList, Address creditAddress) {
		if (!ValidateFieldsUtil.validateRegExp("^(CAN|USA)$", creditAddress.getCountryCode())){
			invalidInputList.add(CUSTOMER_ELIG_INVALID_CUST_COUNTRY_CODE);
		}
		if (Constants.COUNTRY_CANADA_SHORT.equalsIgnoreCase(creditAddress.getCountryCode()) 
				&& !StringUtils.isEmpty(creditAddress.getProvinceStateCode())
				&& !isValidProvinceStateCode(creditAddress.getProvinceStateCode())){
			invalidInputList.add(CUSTOMER_ELIG_INVALID_CUST_PROVINCE_STATE_CODE);
		}
	}

	/**
	 * @param missingList
	 * @param fields
	 * @param sbMissing
	 * @param provincialIdCard
	 * @return
	 */
	private void validateProvincialIdCard(List<String> missingList,
			StringBuilder sbMissing, ProvincialIdCard provincialIdCard) {
		Map<String, Object> fields = new HashMap<String, Object>();
		if(provincialIdCard != null && !StringUtils.isEmpty(provincialIdCard.getProvincialIdNum())){
			fields.put("provinceCd", provincialIdCard.getProvinceCd());
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				sbMissing.append(CUSTOMER_ELIG_INVALID_COMB_HEALTH_PROVINCE_CD).append(NEWLINE);
				missingList.add(CUSTOMER_ELIG_INVALID_COMB_HEALTH_PROVINCE_CD + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	/**
	 * @param missingList
	 * @param fields
	 * @param sbMissing
	 * @param passport
	 * @return
	 */
	private void validatePassport(List<String> missingList,
			StringBuilder sbMissing, Passport passport) {
		Map<String, Object> fields = new HashMap<String, Object>();
		if(passport != null && !StringUtils.isEmpty(passport.getPassportNum())){
			fields.put("countryCd", passport.getCountryCd());
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				sbMissing.append(CUSTOMER_ELIG_INVALID_COMB_PASS_COUNTRY_CD).append(NEWLINE);
				missingList.add(CUSTOMER_ELIG_INVALID_COMB_PASS_COUNTRY_CD + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	private void validateDriversLicense(List<String> missingList,
			StringBuilder sbMissing, DriverLicense driverLicense) {
		Map<String, Object> fields = new HashMap<String, Object>();
		if(driverLicense != null && !StringUtils.isEmpty(driverLicense.getDriverLicenseNum())){
			
			fields.put("expiryDate", driverLicense.getExpiryDate());
			fields.put("provinceCd", driverLicense.getProvinceCd());
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				sbMissing.append(CUSTOMER_ELIG_INVALID_COMB_DRIVER_LICENSE).append(NEWLINE);
				missingList.add(CUSTOMER_ELIG_INVALID_COMB_DRIVER_LICENSE + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	private void validateCreditAddressExclusiveElement(List<String> missingList, List<String> invalidInputList,
			StringBuilder sbMissing, StringBuilder sbInvalid, Address creditAddress) {
		// Allan's comment: there are two rules.
		// 1.	these fields are mutually exclusive:  streetName,ruralNumber,poBoxNumber
		// 2.	if streetName is not null,  then streetNumber must also be not null.

		// validate only one of these is provided: streetName, ruralNumber, poBoxNumber.
		boolean valid = false;
		if (validateOnlyStreetName(creditAddress)) {
			valid = true;
			//validate StreetNumber is provided
			if (StringUtils.isEmpty(creditAddress.getStreetNumber())){
				sbMissing.append(CUSTOMER_ELIG_MISSING_STREET_NUMBER).append(NEWLINE);
				missingList.add(CUSTOMER_ELIG_MISSING_STREET_NUMBER);
			}
		} else if (validateOnlyRuralNumber(creditAddress) || validateOnlyPoBox(creditAddress)) {
			valid = true;
		}
		
		if (!valid) {
			sbInvalid.append(CUSTOMER_ELIG_EXCLUSIVE_STREETNAME_RURALNUMBER_POBOXNUMBER).append(NEWLINE);
			invalidInputList.add(CUSTOMER_ELIG_EXCLUSIVE_STREETNAME_RURALNUMBER_POBOXNUMBER);
		}
	}

	private void validateCustomerGuarantor(GetCustomerEligibilityCoreRequest rq,
			List<String> missingList) {
		if (rq.getCreditProfile().getCustomerGuarantor() != null){
			Map <String, Object> fields = new HashMap<String, Object>();
			CustomerGuarantor guarantor = rq.getCreditProfile().getCustomerGuarantor();
			fields.put("guarantorFullName", guarantor.getGuarantorFullName());
			fields.put("guarantorPhoneNum", guarantor.getGuarantorPhoneNum());
			fields.put("expiryDate", guarantor.getExpiryDate());
			if (guarantor.getGuaranteedAmt() != null && guarantor.getGuaranteedAmt().compareTo(BigDecimal.ZERO) > 0){
				fields.put("guaranteedAmt", guarantor.getGuaranteedAmt());
			}else{
				fields.put("guaranteedAmt", null);
			}
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				missingList.add(CUSTOMER_ELIG_INVALID_COMB_GUARANTOR + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	private void validateNewCustomerInd(GetCustomerEligibilityCoreRequest rq, List<String> missingList,
			StringBuilder sbMissing) {
		if (rq.getNewCustomerInd() == null) {
			sbMissing.append(CUSTOMER_ELIG_MISSING_NEW_CUST_IND).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_NEW_CUST_IND);
		}
	}

	private void validateCustomerId(GetCustomerEligibilityCoreRequest rq, List<String> missingList,
			List<String> invalidInputList, StringBuilder sbMissing, StringBuilder sbInvalid) {
		if (StringUtils.isBlank(rq.getCustomerId())) {
			sbMissing.append(CUSTOMER_ELIG_MISSING_CUSTOMER_ID).append(NEWLINE);
			missingList.add(CUSTOMER_ELIG_MISSING_CUSTOMER_ID);
		} else if (!StringUtils.isNumeric(rq.getCustomerId())) {
			sbInvalid.append(CUSTOMER_ELIG_CUSTOMER_ID_MUST_BE_NUMERIC).append(NEWLINE);
			invalidInputList.add(CUSTOMER_ELIG_CUSTOMER_ID_MUST_BE_NUMERIC);
		}
	}
	
	private boolean isValidProvinceStateCode(String provinceStateCode) {
		String newProvinceStateCode = ValidateFieldsUtil.convertOldProvinceCodeToNew(provinceStateCode);

		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();

		GetReferencePDSResponseDO response = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_PROVINCE_TABLE);

		if (response.isMsgHasError()) {
			for (MessageDO message : response.getMsgList()) {
				for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
					LOGGER.warn("isValidProvinceStateCode", messageDescription.getMessageDescriptionText());
				}
			}

			return true;
		}
		else {
			Map<String, Object> objResult = response.getRefpdsTable();
			
			Map<String, String> provinceStateCdMap = (Map<String, String>) objResult.get(EnterpriseWLNSalesServicesConstants.REFPDS_PROVINCE_TABLE);

			if (provinceStateCdMap.isEmpty()){
				LOGGER.info("isValidProvinceStateCode", "Province State Code was NOT found in RefPDS.");

				return true;
			}
			else if (provinceStateCdMap.containsKey(newProvinceStateCode)){
				LOGGER.info("isValidProvinceStateCode", "Province State Code was found in RefPDS.");

				return true;
			}
		}

		return false;
	}
	
	/**
	 * 
	 * @param provincialIdCard
	 * @param healthCard
	 * @param driverLicense
	 * @param passport
	 * @return true if all these variables are null
	 */
	private boolean areIdsNull(ProvincialIdCard provincialIdCard, HealthCard healthCard, DriverLicense driverLicense,
			Passport passport) {
		return provincialIdCard == null && healthCard == null && driverLicense == null && passport == null;
	}
	
	private boolean validateOnlyStreetName(Address creditAddress){
		return StringUtils.isNotEmpty(creditAddress.getStreetName())
				&& (StringUtils.isEmpty(creditAddress.getRuralNumber())
						&& StringUtils.isEmpty(creditAddress.getPoBox()));
	}
	
	private boolean validateOnlyRuralNumber(Address creditAddress){
		return StringUtils.isNotEmpty(creditAddress.getRuralNumber())
				&& (StringUtils.isEmpty(creditAddress.getPoBox())
						&& StringUtils.isEmpty(creditAddress.getStreetName()));
	}
	
	private boolean validateOnlyPoBox(Address creditAddress){
		return StringUtils.isNotEmpty(creditAddress.getPoBox())
				&& (StringUtils.isEmpty(creditAddress.getRuralNumber())
						&& StringUtils.isEmpty(creditAddress.getStreetName()));
	}
	
	private void changePolicyErrorCode(AdapterResponseBase responseDO){
		// check if policy/service or general exception
		if (!EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR.equals(responseDO.getAdapterResponseMessage().getMessageCode())) {
			response.getMessageList().get(0).setErrorCode(CUSTOMER_ELIG_DOWN_STREAM_ERROR);
		}
	}
}