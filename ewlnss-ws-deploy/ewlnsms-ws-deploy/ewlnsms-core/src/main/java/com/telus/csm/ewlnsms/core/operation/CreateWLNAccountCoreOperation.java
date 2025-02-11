package com.telus.csm.ewlnsms.core.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IConsumerBillingAccountManagementServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CreateBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateBillingAccountAdapterResponse;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.helper.AdapterResponseUtil;
import com.telus.csm.ewlnsc.helper.ValidationUtil;
import com.telus.csm.ewlnsc.task.EBillAdoptionAndActivationTask;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.ValidateFieldsUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreRequest;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreResponse;
import com.telus.csm.ewlnsms.core.transformer.CreateWLNAccountTransformer;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CustomerGuarantor;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.DriverLicense;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.HealthCard;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.Passport;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.PersonalInfo;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.ProvincialIdCard;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.Customer;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Account;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.NewCustomerInformation;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

import commonj.work.Work;

/**
 * @author x145592
 * 
 */
public class CreateWLNAccountCoreOperation {
	
	protected static final Logger LOGGER = Logger.getLogger(CreateWLNAccountCoreOperation.class);
	protected static final LoggerUtil loggerUtil = LoggerUtil.getLogger(CreateWLNAccountCoreOperation.class);
	
	protected String transId;
	protected OperationHeader header;
	protected NewCustomerInformation customerInfo;
	protected com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address mainAddress;
    protected static final String JWORK_MANAGER_EXCEPTION_MSG = "ICommonJWorkManager caught Exception.";
	private static final String MUNICIPALITY_NAME = "municipalityName";
	private static final String PROVINCE_STATE_CODE = "provinceStateCode";
	private static final String COUNTRY_CODE = "countryCode";
	private static final String STREET_NUMBER = "streetNumber";
	private static final String STREET_NAME = "streetName";
	private static final String RURAL_NUMBER = "ruralNumber";
	private static final String POBOX = "poBox";
	private static final String COMBINATION_SEPARATOR = "--> (";
	
	/**
	 * Entry point is this operation class.
	 * 
	 * @param requestBO
	 * @return
	 * @throws Throwable
	 */
	public CreateWLNAccountCoreResponse execute(final CreateWLNAccountCoreRequest requestBO) {
		CreateWLNAccountCoreResponse result = new CreateWLNAccountCoreResponse();
		
		header = requestBO.getOperationHeader();
		if (header != null){
			transId = header.getSalesTransactionId();
		}
		customerInfo = requestBO.getCustomerInfo();
		if (customerInfo != null){
			mainAddress = customerInfo.getMainAddress();
		}
		
		// validate Input
		List<String> missingElementList = new ArrayList<String>();
		List<String> invalidInputList = new ArrayList<String>();
		validateInput(requestBO, missingElementList, invalidInputList);
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
			loggerUtil.error("", "", "Input Validation failed.");
			result.setMessageList(generateMessageList(requestBO, missingElementList, invalidInputList));
			return result;
		}
		
		if (!StringUtils.isEmpty(requestBO.getCustomerId())) {
			CreateWLNAccountCoreForExistingCustomer createWlnAccountForExistingCust = new CreateWLNAccountCoreForExistingCustomer();
			result = createWlnAccountForExistingCust.createAccountForExistingCustomer(requestBO, result);
		} else {
			CreateWLNAccountCoreForNewCustomer createWLNAccountCoreForNewCust = new CreateWLNAccountCoreForNewCustomer();
			result = createWLNAccountCoreForNewCust.createAccountForNewCustomer(requestBO, result);
		} 
		
		return result;
	}

	public void createNewCustomer(final CreateWLNAccountCoreRequest requestVO, CreateWLNAccountCoreResponse result) {
		final CreateWLNAccountTransformer transformer = new CreateWLNAccountTransformer();
		final CreateNewCustomerAdapterRequest reqDO = transformer.getCreateCustReqDO(requestVO);

		final IConsumerCustomerMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
		final CreateNewCustomerAdapterResponse adapterResp = adapter.createNewCustomer(reqDO);
		
		if (adapterResp.isMsgHasError()) {
			AdapterResponseUtil.propagateMessage(adapterResp, result.getMessageList());	
			// check if policy/service or general exception
			if (!EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR.equals(adapterResp.getAdapterResponseMessage().getMessageCode())) {
				result.getMessageList().get(0).setErrorCode(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_DOWN_STREAM_ERROR);
			}
		} else {
			final Customer cust = adapterResp.getNewCustomer();
			if (cust != null && cust.getCustomerId() != null) {
				result.setCustomerId(String.valueOf(cust.getCustomerId()));
			}	    
		}	    
	    
	}
	
	public void createAccount(final CreateWLNAccountCoreRequest requestVO, final Long customerId, CreateWLNAccountCoreResponse result) {

		LOGGER.info("execute delegator to createBillingAccount for customer id=" + customerId);
		
		final CreateWLNAccountTransformer transformer = new CreateWLNAccountTransformer();
		final CreateBillingAccountAdapterRequest accReqDO = transformer.getBillAccReqDO(requestVO, customerId);
		
		final IConsumerBillingAccountManagementServiceAdapter adapter = AdapterFactory.getAdapter(IConsumerBillingAccountManagementServiceAdapter.class);
		final CreateBillingAccountAdapterResponse adapterResp = adapter.createBillingAccount(accReqDO);
		
		if (adapterResp.getResponse() != null && adapterResp.getResponse().getBillingAccount() != null) {
			final BillingAccount billAcc = adapterResp.getResponse().getBillingAccount();
			
			result.setBillingAccountNumber(billAcc.getBillingAccountNumber());
			result.setBillCycleCd(billAcc.getBillCycleCode());
			if (billAcc.getPayChannel() != null) {
				result.setPayChannelNumber(billAcc.getPayChannel().getPayChannelNumber());
			}
		} else { // return error from down stream
			AdapterResponseUtil.propagateMessage(adapterResp, result.getMessageList());
			// check if policy/service or general exception
			if (!EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR.equals(adapterResp.getAdapterResponseMessage().getMessageCode())) {
				result.getMessageList().get(0).setErrorCode(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_DOWN_STREAM_ERROR);
			}
		}
		
		result.setCustomerId(String.valueOf(customerId));
		
	}

	private boolean isDeclinedReasonCdAllowed(String declineReasonCd) {
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		
		GetReferencePDSResponseDO response = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_DECLINED_REASON_TABLE);

		if (response.isMsgHasError()) {
			for (MessageDO message : response.getMsgList()) {
				for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
					LOGGER.warn(messageDescription.getMessageDescriptionText());
				}
			}

			return true;
		}
		else {
			Map<String, Object> objResult = response.getRefpdsTable();
			
			Map<String, String> declinedReasonCdMap = (Map<String, String>) objResult.get(EnterpriseWLNSalesServicesConstants.REFPDS_DECLINED_REASON_TABLE);

			if (declinedReasonCdMap.isEmpty()) {
				loggerUtil.info("isDeclinedReasonCdAllowed", "Reason Code was NOT found in RefPDS");

				return true;
			}
			else if (declinedReasonCdMap.containsKey(declineReasonCd)) {
				loggerUtil.info("isDeclinedReasonCdAllowed", "Reason Code was found in RefPDS: " + declinedReasonCdMap.get(declineReasonCd));

				return true;
			}
		}		

		return false;
	}

	public GetFullCustomerInfoAdapterResponse getFullCustomerInfo(final GetFullCustomerInfoAdapterRequest requestVO) {
		LOGGER.info("execute delegator to get full customer info.");
	
		final IConsumerCustomerMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
		
		return adapter.getFullCustomerInfo(requestVO);
		
	}
	
	private void validateInput(final CreateWLNAccountCoreRequest coreRequest, List<String> missingElementList, List<String> invalidInputList) {
		validateMandatory(coreRequest, missingElementList, invalidInputList);
		validateLength(coreRequest, invalidInputList);
		validateCombination(coreRequest, invalidInputList);
	}

	private void validateCombination(CreateWLNAccountCoreRequest coreRequest, List<String> invalidInputList) {
		Map<String, Object> fields = null;
		if (customerInfo != null){
			//serviceAddress
			validateCombServiceAddress(invalidInputList);
			
			if (isNotEmptyFirstLastName() && isNotEmptyPinPrefLangSvcPhone()){ //when customerInfo is NOT passed in, validateLength and validateCombination are no need
				
				// creditAddress
				validateCombCreditAddress(invalidInputList);
				// personalInfo
				com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.PersonalInfo personalInfo = customerInfo.getCreditProfile().getPersonalInfo();

				// guarantor
				validateCombGuarantor(invalidInputList);
				// creditProfile check
				if (customerInfo.getCreditProfile() != null &&
						customerInfo.getCreditProfile().getCreditIdentification() != null){
					// CreditCard will not be validated
					com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.PersonalCreditInformation creditIdentification = customerInfo.getCreditProfile().getCreditIdentification();
					ProvincialIdCard provincialIdCard = creditIdentification.getProvincialIdCard();
					HealthCard healthCard = creditIdentification.getHealthCard();
					DriverLicense driverLicense = creditIdentification.getDriverLicense();
					Passport passport = creditIdentification.getPassport();
					String sin = creditIdentification.getSin();
					// driversLicense
					validateCombDriversLicense(invalidInputList, driverLicense);
					// passport
					validateCombPassport(invalidInputList, passport);
					// healthCard

					// provincialIdCard
					validateCombProvincialIdCard(invalidInputList, provincialIdCard);
					
					// at least one piece of Ids
					boolean areIdsNull = areIdsNull(provincialIdCard, healthCard, driverLicense, passport);
					validateCombAtLeastOnePieceId(invalidInputList, fields, personalInfo, sin, areIdsNull);
				}
				
			}
			
		}
		
		Account accountInfo = coreRequest.getAccountInfo();
		//billingAddress
		validateCombBillingAddress(invalidInputList, accountInfo);
		
	}

	private void validateCombBillingAddress(List<String> invalidInputList, Account accountInfo) {
		Map<String, Object> fields;
		if (accountInfo != null && accountInfo.getBillingAddress() != null){
			com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address billingAddress = accountInfo.getBillingAddress();
			fields = new HashMap<String, Object>();
			fields.put(MUNICIPALITY_NAME, billingAddress.getMunicipalityName());
			fields.put(PROVINCE_STATE_CODE, billingAddress.getProvinceStateCode());
			fields.put(COUNTRY_CODE, billingAddress.getCountryCode());			
			// (Defect 58803)
			if (StringUtils.isEmpty(billingAddress.getRuralNumber()) && StringUtils.isEmpty(billingAddress.getPoBox())){
				fields.put(STREET_NUMBER, billingAddress.getStreetNumber());
				fields.put(STREET_NAME, billingAddress.getStreetName());
			} else if (StringUtils.isNotEmpty(billingAddress.getRuralNumber()) && 
					StringUtils.isEmpty(billingAddress.getPoBox())) {
				fields.put(RURAL_NUMBER, billingAddress.getRuralNumber());
			} else if (StringUtils.isEmpty(billingAddress.getRuralNumber()) && 
					StringUtils.isNotEmpty(billingAddress.getPoBox())) {
				fields.put(POBOX, billingAddress.getPoBox());
			} 
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_BILLING_ADDRESS + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	private void validateCombCreditAddress(List<String> invalidInputList) {
		if (customerInfo.getCreditProfile() != null && 
				customerInfo.getCreditProfile().getCreditAddress() != null){
			com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address creditAddress = customerInfo.getCreditProfile().getCreditAddress();
			Map<String, Object> fields = new HashMap<String, Object>();
			fields.put(MUNICIPALITY_NAME, creditAddress.getMunicipalityName());
			fields.put(PROVINCE_STATE_CODE, creditAddress.getProvinceStateCode());
			fields.put(COUNTRY_CODE, creditAddress.getCountryCode());
			//(Defect 58803)
			if (StringUtils.isEmpty(creditAddress.getRuralNumber()) && StringUtils.isEmpty(creditAddress.getPoBox())){
				fields.put(STREET_NUMBER, creditAddress.getStreetNumber());
				fields.put(STREET_NAME, creditAddress.getStreetName());
			} else if (StringUtils.isNotEmpty(creditAddress.getRuralNumber()) && StringUtils.isEmpty(creditAddress.getPoBox())) {
				fields.put(RURAL_NUMBER, creditAddress.getRuralNumber());
			} else if (StringUtils.isEmpty(creditAddress.getRuralNumber()) && StringUtils.isNotEmpty(creditAddress.getPoBox())) {
				fields.put(POBOX, creditAddress.getPoBox());
			}
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_SERVICE_ADDRESS + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	private void validateCombGuarantor(List<String> invalidInputList) {
		if (customerInfo.getCreditProfile() != null &&
				customerInfo.getCreditProfile().getCustomerGuarantor() != null){
			CustomerGuarantor guarantor = customerInfo.getCreditProfile().getCustomerGuarantor();
			Map<String, Object> fields = new HashMap<String, Object>();
			fields.put("guarantorFullName", guarantor.getGuarantorFullName());
			fields.put("guarantorPhoneNum", guarantor.getGuarantorPhoneNum());
			fields.put("expiryDate", guarantor.getExpiryDate());
			if (guarantor.getGuaranteedAmt() != null && guarantor.getGuaranteedAmt().compareTo(BigDecimal.ZERO) > 0){
				fields.put("guaranteedAmt", guarantor.getGuaranteedAmt());
			}else{
				fields.put("guaranteedAmt", null);
			}
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_GUARANTOR + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	private void validateCombDriversLicense(List<String> invalidInputList, DriverLicense driverLicense) {
		if(driverLicense != null && !StringUtils.isEmpty(driverLicense.getDriverLicenseNum())){ 
			Map<String, Object> fields = new HashMap<String, Object>();
			fields.put("expiryDate", driverLicense.getExpiryDate());
			fields.put("provinceCd", driverLicense.getProvinceCd());
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_DRIVER_LICENSE + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	private void validateCombPassport(List<String> invalidInputList, Passport passport) {
		if(passport != null && !StringUtils.isEmpty(passport.getPassportNum())){
			Map<String, Object> fields = new HashMap<String, Object>();
			fields.put("countryCd", passport.getCountryCd());
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_PASS_COUNTRY_CD + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	/**
	 * @param invalidInputList
	 * @param fields
	 * @param provincialIdCard
	 * @return
	 */
	private void validateCombProvincialIdCard(List<String> invalidInputList,
			ProvincialIdCard provincialIdCard) {
		if(provincialIdCard != null && !StringUtils.isEmpty(provincialIdCard.getProvincialIdNum())){
			Map<String, Object> fields = new HashMap<String, Object>();
			fields.put("provinceCd", provincialIdCard.getProvinceCd());
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_PROVINCIAL_COUNTRY_CD + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	/**
	 * @param invalidInputList
	 * @param fields
	 * @param personalInfo
	 * @param provincialIdCard
	 * @param healthCard
	 * @param driverLicense
	 * @param passport
	 * @param sin
	 */
	private void validateCombAtLeastOnePieceId(List<String> invalidInputList, Map<String, Object> fields,
			com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.PersonalInfo personalInfo,
			String sin, boolean areIdsNull) {
		if (personalInfo != null && "Y".equalsIgnoreCase(personalInfo.getCreditCheckConsentCd())){
			boolean isValid = true;
			if (areIdsNull && StringUtils.isEmpty(sin)){
				isValid = false;
			}
			if (!isValid){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_PIECE_OF_ID + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
	}

	/**
	 * @param invalidInputList
	 * @param fields
	 * @return
	 */
	private void validateCombServiceAddress(List<String> invalidInputList) {
		if (mainAddress != null){
			Map<String, Object> fields = new HashMap<String, Object>();
			fields.put(MUNICIPALITY_NAME, mainAddress.getMunicipalityName());
			fields.put(PROVINCE_STATE_CODE, mainAddress.getProvinceStateCode());
			fields.put(COUNTRY_CODE, mainAddress.getCountryCode());
			//fields.put("postalZipCode", coreRequest.getCustomerInfo().getMainAddress().getPostalZipCode()); // Allan: Slight revision... we actually must allow customerInfo.mainAddress.postalZipCd to be null. I remember Olivia saying that postalCd might be omitted.
			if (StringUtils.isEmpty(mainAddress.getRuralNumber()) && StringUtils.isEmpty(mainAddress.getPoBox())){
				fields.put(STREET_NUMBER, mainAddress.getStreetNumber());
				fields.put(STREET_NAME, mainAddress.getStreetName());
			}else{
				fields.put(RURAL_NUMBER, mainAddress.getRuralNumber());
				fields.put(POBOX, mainAddress.getPoBox());
			}
			if (!ValidateFieldsUtil.isAllFieldsExist(fields)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_COMB_MAIN_ADDRESS + COMBINATION_SEPARATOR + fields.toString() + ")");
			}
		}
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

	private void validateLength(CreateWLNAccountCoreRequest coreRequest, List<String> invalidInputList) {
		if (customerInfo != null){
			validateLengthEmailAddressTxt(invalidInputList);
			// If mobilePhoneNumber if present it should be valid
			validateLengthMobilePhoneNumber(invalidInputList);
			
			//validate customerInfo.emailDeclinedReasonCd is valid
			validateEmailDeclinedReasonCd(invalidInputList);
			
			if (isNotEmptyFirstLastName() && isNotEmptyPinPrefLangSvcPhone()){

				validateLengthCustomerInfoPinPrefLangPhone(invalidInputList);
				
				//creditAddress
				validateLengthCreditAddress(invalidInputList);
			}
			
			//serviceAddress
			validateLengthServiceAddress(invalidInputList);
		}
		
		Account accountInfo = coreRequest.getAccountInfo();
		if (accountInfo != null) {
			validateLengthAccountInfo(invalidInputList, accountInfo);
			
			//billingAddress
			com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address billingAddress = accountInfo.getBillingAddress();
			validateLengthBillingAddress(invalidInputList, billingAddress);
			
			//accountInfo       //getWirelineCustomerInfo in old code
			if (!StringUtils.isEmpty(accountInfo.getPreferredContactLanguageCode()) 
					&& !ValidateFieldsUtil.validateRegExp("^(?i)EN|FR+$", accountInfo.getPreferredContactLanguageCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_ACCINFO_CONTACT_LANG);
			}
			
		}
		
		//if customerId is provided, it should be numeric
		if (!StringUtils.isEmpty(coreRequest.getCustomerId()) && !StringUtils.isNumeric(coreRequest.getCustomerId())){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUSTOMER_ID);
		}
	}

	private void validateLengthCustomerInfoPinPrefLangPhone(List<String> invalidInputList) {
		if (!ValidateFieldsUtil.validateRegExp("^((?!(0))[0-9]{4,6})$", customerInfo.getPin())){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_PIN);
		}
		// restrict customerInfo.preferredLanguageCD to those values in refpds (BI_CM_LANGUAGE table)
		if (!StringUtils.isEmpty(customerInfo.getPreferredLanguageCd()) && !isValidReferencePDSCode(customerInfo.getPreferredLanguageCd(), EnterpriseWLNSalesServicesConstants.REFPDS_PREFERRED_LANGUAGE_CD_TABLE)){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_PREF_LANG);
		}
		if (!ValidateFieldsUtil.validateRegExp(EnterpriseWLNSalesServicesConstants.PHONE_NUMBER_REG_EXP, customerInfo.getServicePhoneNumber())){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_SERV_PHONE_NUMBER);
		}
		if (!StringUtils.isEmpty(customerInfo.getBusinessPhoneNumber()) && !ValidateFieldsUtil.validateRegExp(EnterpriseWLNSalesServicesConstants.PHONE_NUMBER_REG_EXP, customerInfo.getBusinessPhoneNumber())){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_BUSINESS_PHONE_NUMBER);
		}
		if (!StringUtils.isEmpty(customerInfo.getBusinessPhoneExtensionNumber()) && !ValidateFieldsUtil.validateRegExp("^[0-9]+$", customerInfo.getBusinessPhoneExtensionNumber())){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_BUSINESS_PHONE_EXTENSION);
		}
	}

	private void validateEmailDeclinedReasonCd(List<String> invalidInputList) {
		if (StringUtils.isEmpty(customerInfo.getEmailAddressTxt()) 
				&& !StringUtils.isEmpty(customerInfo.getEmailDeclinedReasonCd()) 
				&& !isValidReferencePDSCode(customerInfo.getEmailDeclinedReasonCd(), EnterpriseWLNSalesServicesConstants.REFPDS_EMAIL_DECLINED_REASON_TABLE)){
			// Allan: Please validate customerInfo.emailDeclinedReasonCd value against REFPDS table EMAIL_COLLECTION_SUPPRESSION_REASON.
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_EMAIL_DECLINED_RSN_CD);
		}
	}

	private void validateLengthMobilePhoneNumber(List<String> invalidInputList) {
		if (!StringUtils.isEmpty(customerInfo.getMobilePhoneNumber()) 
				&& (!ValidateFieldsUtil.validateRegExp(EnterpriseWLNSalesServicesConstants.PHONE_NUMBER_REG_EXP, customerInfo.getMobilePhoneNumber()))){
			//validate mobilePhoneNumber is valid
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_MOBILE_PHONE_NUMBER);
		}
	}

	private void validateLengthEmailAddressTxt(List<String> invalidInputList) {
		if (!StringUtils.isEmpty(customerInfo.getEmailAddressTxt()) 
				&& (customerInfo.getEmailAddressTxt().indexOf('@') == -1)){
			//If EmailAddressTxt is present it should be valid
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_EMAIL);
		}
	}

	private void validateLengthAccountInfo(List<String> invalidInputList, Account accountInfo) {
		if (accountInfo.getBillingDeliveryMethodList() != null && !accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().isEmpty()){
			//validate fields for eBill (E.Bill)
			validateLengthEbill(invalidInputList, accountInfo);
			// validate fields for eBill (Paper)
			validateLengthEPaper(invalidInputList, accountInfo);
			
			if (!ValidateFieldsUtil.validateRegExp("^(?i)Paper|E.Bill+$", accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().get(0))){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_ACCINFO_MEDIA_TYPE);
			}
			
		}
	}

	private void validateLengthCreditAddress(List<String> invalidInputList) {
		if (customerInfo.getCreditProfile() != null && customerInfo.getCreditProfile().getCreditAddress() !=  null){
			if (!ValidateFieldsUtil.validateRegExp("^(CAN|USA)$", customerInfo.getCreditProfile().getCreditAddress().getCountryCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_COUNTRY_CODE);
			}
			if (Constants.COUNTRY_CANADA_SHORT.equalsIgnoreCase(customerInfo.getCreditProfile().getCreditAddress().getCountryCode()) 
					&& !isValidProvinceStateCode(customerInfo.getCreditProfile().getCreditAddress().getProvinceStateCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_PROVINCE_STATE_CODE);
			}
			if (!ValidateFieldsUtil.validateRegExp(EnterpriseWLNSalesServicesConstants.POSTAL_ZIP_CODE_REG_EXP, customerInfo.getCreditProfile().getCreditAddress().getPostalZipCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_POSTAL_ZIP_CODE);
			}
		}
	}

	private void validateLengthServiceAddress(List<String> invalidInputList) {
		if (mainAddress != null){
			//Defect 58832: customerInfo.mainAddress.countryCode should not allow USA.
			if (!Constants.COUNTRY_CANADA_SHORT.equals(mainAddress.getCountryCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_MAIN_COUNTRY_CODE);
			}
			if(!isValidProvinceStateCode(mainAddress.getProvinceStateCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_MAIN_PROVINCE_STATE_CODE);
			}
			if (StringUtils.isEmpty(mainAddress.getPostalZipCode()) || EnterpriseWLNSalesServicesConstants.NPC_POSTAL_ZIP_CODE.equalsIgnoreCase(mainAddress.getPostalZipCode().trim())){
				// valid scenario
			} else if (!ValidateFieldsUtil.validateRegExp(EnterpriseWLNSalesServicesConstants.POSTAL_ZIP_CODE_REG_EXP, mainAddress.getPostalZipCode())) {
				// invalid scenario
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_MAIN_POSTAL_ZIP_CODE);
			}
		}
	}

	private void validateLengthBillingAddress(List<String> invalidInputList,
			com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address billingAddress) {
		if (billingAddress != null){
			if (!ValidateFieldsUtil.validateRegExp("^(CAN|USA)$", billingAddress.getCountryCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_BILL_COUNTRY_CODE);
			}
			if (Constants.COUNTRY_CANADA_SHORT.equalsIgnoreCase(billingAddress.getCountryCode()) 
					&& !isValidProvinceStateCode(billingAddress.getProvinceStateCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_BILL_PROVINCE_STATE_CODE);
			}
			if (!ValidateFieldsUtil.validateRegExp(EnterpriseWLNSalesServicesConstants.POSTAL_ZIP_CODE_REG_EXP, billingAddress.getPostalZipCode())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_CUST_BILL_POSTAL_ZIP_CODE);
			}
		}
	}

	private void validateLengthEPaper(List<String> invalidInputList, Account accountInfo) {
		if (EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_PAPER.equalsIgnoreCase(accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().get(0))){
			if (!StringUtils.isEmpty(accountInfo.getEbillDeclinedReasonCd())){
				//validate mediaType = Paper and ebillDeclineReasonCd is a number. 
				if (!ValidateFieldsUtil.validateRegExp("^[0-9]+$",accountInfo.getEbillDeclinedReasonCd())){
					invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_EBILL_DECLINEREASONCD_MUST_BE_NUMERIC);
				}
				// validate ebillDeclinedReasonCd is in allowable range.
				if (!isDeclinedReasonCdAllowed(accountInfo.getEbillDeclinedReasonCd())){
					invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_EBILL_DECLINEREASONCD_IS_NOT_ALLOWED);
				}
			}
			//validate eBillNotificationTypeCd should not be passed when mediaTypeCode = Paper (Defect 58812)
			if (!StringUtils.isEmpty(accountInfo.getEBillNotificationTypeCd())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_EBILL_EBILLNOTIF_SHOULD_NOT_BE_PASSED);
			}
			
		}
	}

	private void validateLengthEbill(List<String> invalidInputList, Account accountInfo) {
		if (EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_EBILL.equalsIgnoreCase(accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().get(0))){
			// mediaType=eBill and declinedReasonCd is not null
			if (!StringUtils.isEmpty(accountInfo.getEbillDeclinedReasonCd())){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_EBILL_DECLINEREASONCD_MUST_BE_NULL);
			}
			//validate eBillNotificationTypeCd is valid. RefPDS table: EBILL_NOTIF_MTHD (Defect 58812)
			if (!StringUtils.isEmpty(accountInfo.getEBillNotificationTypeCd()) 
					&& !isValidReferencePDSCode(accountInfo.getEBillNotificationTypeCd(), EnterpriseWLNSalesServicesConstants.REFPDS_EBILL_NOTIF_TYPECD_TABLE)){
				invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_EBILL_NOTIF_TYPECD);
			}

		}
	}

	private boolean isValidReferencePDSCode(String refPdsCode, String refPdsTable){
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();

		GetReferencePDSResponseDO response = refPdsBusDelegate.getReferencePDSTableObjectByName(refPdsTable);

		if (response.isMsgHasError()) {
			for (MessageDO message : response.getMsgList()) {
				for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
					LOGGER.warn(messageDescription.getMessageDescriptionText());
				}
			}

			return true;
		}
		else {
			Map<String, Object> objResult = response.getRefpdsTable();
			
			Map<String, String> refPDSCodeMap = (Map<String, String>) objResult.get(refPdsTable);

			if (refPDSCodeMap.isEmpty()) {
				loggerUtil.info("isValidReferencePDSCode", refPdsCode + " was NOT found in RefPDS");

				return true;
			}
			else if (refPDSCodeMap.containsKey(refPdsCode)) {
				String value = refPDSCodeMap.get(refPdsCode);
				
				loggerUtil.info("isValidReferencePDSCode", refPdsCode + " was found in RefPDS: " + value);

				return true;
			}
		}

		return false;
	}

	private void validateMandatory(CreateWLNAccountCoreRequest coreRequest, List<String> mandatoryList, List<String> invalidInputList) {
		StringBuilder sbMandatory = new StringBuilder();
		//validate Header
		ValidationUtil.validateHeader(coreRequest.getOperationHeader(), mandatoryList, invalidInputList);
		
		//validate customerInfo
		if (customerInfo == null){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUSTOMER_INFO).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUSTOMER_INFO);
		}else if (customerInfo.getCreditProfile() == null){
			//validate creditProfile
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CREDIT_PROFILE).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CREDIT_PROFILE);
		}else if (customerInfo.getCreditProfile().getPersonalInfo() == null){
			//validate personalInfo
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERSONAL_INFO).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERSONAL_INFO);
		}else{
			validateMandPersonalInfo(mandatoryList, sbMandatory);
			
			// Allan: Expected Result: -          when customerInfo.emailAddress = null, then customerInfo.emailDeclinedReasonCd is mandatory
			// NWLN-9928 relax the validation rule, email declined reason code need only when mobile phone and email are not provided.
			if (StringUtils.isEmpty(customerInfo.getEmailAddressTxt()) 
					&& StringUtils.isEmpty(customerInfo.getMobilePhoneNumber())
					&& StringUtils.isEmpty(customerInfo.getEmailDeclinedReasonCd())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_EMAIL_DECLINEDREASONCD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_EMAIL_DECLINEDREASONCD);
			}
			
			//customerInfo.mainAddress
			validateMandMainAddress(mandatoryList, sbMandatory);
			
			//customerInfo
			validateMandCustomerInfo(coreRequest, mandatoryList, sbMandatory);
			
			//customerInfo.creditProfile.creditAddress
			validateMandCreditAddress(coreRequest, mandatoryList, sbMandatory);
		}
		
		validateMandAccountInfo(coreRequest, mandatoryList, sbMandatory);
		
		if (sbMandatory.length() > 0){
			LOGGER.error("Missing Element(s): " + sbMandatory.toString());
		}
	}

	private void validateMandAccountInfo(CreateWLNAccountCoreRequest coreRequest, List<String> mandatoryList,
			StringBuilder sbMandatory) {
		Account accountInfo = coreRequest.getAccountInfo();
		if (accountInfo == null){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO);
		}else{
			//validate fields for E.Bill and Paper
			validateMandAccountInfoEBillPaper(coreRequest, mandatoryList, sbMandatory, accountInfo);
			
			//accountInfo   accountHolderName
			validateMandAccountHolderName(mandatoryList, sbMandatory, accountInfo);
			
			if (StringUtils.isEmpty(accountInfo.getPreferredContactLanguageCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_LANG).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_LANG);
			}
			if (accountInfo.getBillingDeliveryMethodList() == null 
					|| accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().isEmpty()){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_MEDIA_TYPE).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_MEDIA_TYPE);
			}
			
			//accountInfo.billingAddress
			validateMandBillingAddress(mandatoryList, sbMandatory, accountInfo);
		}
	}

	private void validateMandAccountInfoEBillPaper(CreateWLNAccountCoreRequest coreRequest, List<String> mandatoryList,
			StringBuilder sbMandatory, Account accountInfo) {
		if (accountInfo.getBillingDeliveryMethodList() != null && !accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().isEmpty()){
			//validate fields for eBill (E.Bill)
			validateMandAccountInfoEbill(coreRequest, mandatoryList, sbMandatory, accountInfo);
			
			// validate fields for eBill (Paper)
			if (EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_PAPER.equalsIgnoreCase(accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().get(0)) 
					&& StringUtils.isEmpty(accountInfo.getEbillDeclinedReasonCd())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_DECLINEDREASONCD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_DECLINEDREASONCD);
			}
		}
	}

	private void validateMandAccountInfoEbill(CreateWLNAccountCoreRequest coreRequest, List<String> mandatoryList,
			StringBuilder sbMandatory, Account accountInfo) {
		if (EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_EBILL.equalsIgnoreCase(accountInfo.getBillingDeliveryMethodList().getMediaTypeCode().get(0))){
			if (StringUtils.isEmpty(accountInfo.getEBillNotificationTypeCd())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_NOTIF_TYPECD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_NOTIF_TYPECD);
			}else {
				validateMandAccountInfoEbillRelatedFields(coreRequest, mandatoryList, sbMandatory, accountInfo);
			}
			
		}
	}

	private void validateMandAccountInfoEbillRelatedFields(CreateWLNAccountCoreRequest coreRequest,
			List<String> mandatoryList, StringBuilder sbMandatory, Account accountInfo) {
		if ((EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL.equalsIgnoreCase(accountInfo.getEBillNotificationTypeCd())
				|| (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL_SMS.equalsIgnoreCase(accountInfo.getEBillNotificationTypeCd()))) 
				&&  (coreRequest.getCustomerInfo() == null || StringUtils.isEmpty(coreRequest.getCustomerInfo().getEmailAddressTxt()))){
			//emailAddressTxt is required
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_EMAILADDRESSTXT).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_EMAILADDRESSTXT);
		}
		if ((EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_SMS.equalsIgnoreCase(accountInfo.getEBillNotificationTypeCd())
				|| (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL_SMS.equalsIgnoreCase(accountInfo.getEBillNotificationTypeCd()))) 
				&& (coreRequest.getCustomerInfo() == null || StringUtils.isEmpty(coreRequest.getCustomerInfo().getMobilePhoneNumber()))){
			//mobilePhoneNumber is required
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_MOBILEPHONENUMBER).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_EBILL_MOBILEPHONENUMBER);
		}
	}

	private void validateMandAccountHolderName(List<String> mandatoryList, StringBuilder sbMandatory,
			Account accountInfo) {
		if (accountInfo.getAccountHolderName() == null){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER);
		}else{
			if (StringUtils.isEmpty(accountInfo.getAccountHolderName().getFirstName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_FNAME).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_FNAME);
			}
			if (StringUtils.isEmpty(accountInfo.getAccountHolderName().getLastName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_LNAME).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_LNAME);
			}
			if (StringUtils.isEmpty(accountInfo.getAccountHolderName().getFullName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_FULLNAME).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_FULLNAME);
			}
		}
	}

	private void validateMandBillingAddress(List<String> mandatoryList, StringBuilder sbMandatory,
			Account accountInfo) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address billingAddress = accountInfo.getBillingAddress();
		if (billingAddress != null){
			if (StringUtils.isEmpty(billingAddress.getMunicipalityName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_MUNICIPALITY).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_MUNICIPALITY);
			}
			if (StringUtils.isEmpty(billingAddress.getProvinceStateCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_PROVINCE).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_PROVINCE);
			}
			if (StringUtils.isEmpty(billingAddress.getCountryCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_COUNTRY).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_COUNTRY);
			}
			// Allan: postalZipCode must be a mandatory input validation for accountInfo.billingAddress and customerInfo.creditProfile.creditAddress (but not customerInfo.mainAddress).
			if (StringUtils.isEmpty(billingAddress.getPostalZipCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_POSTALZIPCODE).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ACCINFO_POSTALZIPCODE);
			}
		}
	}

	private void validateMandPersonalInfo(List<String> mandatoryList, StringBuilder sbMandatory) {
		PersonalInfo personalInfo = customerInfo.getCreditProfile().getPersonalInfo();
		if (StringUtils.isEmpty(personalInfo.getEmploymentStatusCd())){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_EMPLOYMENT_STATUS_CD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_EMPLOYMENT_STATUS_CD);
		}
		if (StringUtils.isEmpty(personalInfo.getResidencyCd())){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_RESIDENCY_CD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_RESIDENCY_CD);
		}
		if (StringUtils.isEmpty(personalInfo.getCreditCheckConsentCd())){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_CREDIT_CHECK_CD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_CREDIT_CHECK_CD);
		}
		if (personalInfo.getBirthDate() == null){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_BIRTHDT).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_BIRTHDT);
		}
		if (StringUtils.isEmpty(personalInfo.getProvinceOfCurrentResidenceCd())){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_PROV_CUR_RES).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_PERS_PROV_CUR_RES);
		}
	}

	private void validateMandMainAddress(List<String> mandatoryList, StringBuilder sbMandatory) {
		if (mainAddress == null){
			// mainAddress must not be null
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS);
		}else{
			if (StringUtils.isEmpty(mainAddress.getMunicipalityName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS_MUNICIPALITY_NAME).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS_MUNICIPALITY_NAME);
			}
			if (StringUtils.isEmpty(mainAddress.getProvinceStateCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS_PROVINCE_STATE_CD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS_PROVINCE_STATE_CD);
			}
			if (StringUtils.isEmpty(mainAddress.getCountryCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS_COUNTRY_CD).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_ADDRESS_COUNTRY_CD);
			}
		}
	}

	private void validateMandCustomerInfo(CreateWLNAccountCoreRequest coreRequest, List<String> mandatoryList,
			StringBuilder sbMandatory) {
		if (StringUtils.isEmpty(coreRequest.getCustomerId())
				|| customerInfo.getCreditProfile() != null){
			if (StringUtils.isEmpty(customerInfo.getFirstName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_FNAME).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_FNAME);
			}
			if (StringUtils.isEmpty(customerInfo.getLastName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_LNAME).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_LNAME);
			}
			if (StringUtils.isEmpty(customerInfo.getPin())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_PIN).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_PIN);
			}
			if (StringUtils.isEmpty(customerInfo.getPreferredLanguageCd())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_PREF_LANG).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_PREF_LANG);
			}
			if (StringUtils.isEmpty(customerInfo.getServicePhoneNumber())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_SERV_PHONE_NUMBER).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CUST_SERV_PHONE_NUMBER);
			}
		}
	}

	/**
	 * @param coreRequest
	 * @param mandatoryList
	 * @param sbMandatory
	 */
	private void validateMandCreditAddress(CreateWLNAccountCoreRequest coreRequest, List<String> mandatoryList,
			StringBuilder sbMandatory) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address creditAddress = coreRequest.getCustomerInfo().getCreditProfile().getCreditAddress();
		if (creditAddress == null){
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED);
		}else{
			if (StringUtils.isEmpty(creditAddress.getMunicipalityName())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_MUNICIPALITY_NAME).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_MUNICIPALITY_NAME);
			}
			if (StringUtils.isEmpty(creditAddress.getProvinceStateCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_PROVINCE).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_PROVINCE);
			}
			if (StringUtils.isEmpty(creditAddress.getCountryCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_COUNTRY).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_COUNTRY);
			}
			// Allan: postalZipCode must be a mandatory input validation for accountInfo.billingAddress and customerInfo.creditProfile.creditAddress (but not customerInfo.mainAddress).
			if (StringUtils.isEmpty(creditAddress.getPostalZipCode())){
				sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_POSTALZIPCODE).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
				mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_CRED_POSTALZIPCODE);
			}
		}
	}
	
	private List<MessageList> generateMessageList(CreateWLNAccountCoreRequest requestBO,
			List<String> missingElementList, List<String> invalidInputList) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();
		String salesTransactionId = "";
		sbMissing.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		sbInvalid.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		// iterate over missing element list
		for (String msg : missingElementList) {
			sbMissing.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		// iterate over invalid input
		for (String msg : invalidInputList) {
			sbInvalid.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		if (requestBO.getOperationHeader() != null && requestBO.getOperationHeader().getSalesTransactionId() != null) {
			salesTransactionId = requestBO.getOperationHeader().getSalesTransactionId();
		}
		// create the message
		if (!missingElementList.isEmpty()) {
			MessageList messageMissing = new MessageList();
			messageMissing.setDateTimeStamp(new Date());
			messageMissing.setTransactionId(salesTransactionId);

			messageMissing.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_MISSING_MANDATORY_ELEMENTS);
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

			messageInvalid.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_INVALID_INPUT);
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
	
	private boolean isValidProvinceStateCode(String provinceStateCode) {
		String newProvinceStateCode = ValidateFieldsUtil.convertOldProvinceCodeToNew(provinceStateCode);

		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		
		GetReferencePDSResponseDO response = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_PROVINCE_TABLE);

		if (response.isMsgHasError()) {
			for (MessageDO message : response.getMsgList()) {
				for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
					LOGGER.warn(messageDescription.getMessageDescriptionText());
				}
			}

			return true;
		}
		else {
			Map<String, Object> objResult = response.getRefpdsTable();
			
			Map<String, String> provinceStateCdMap = (Map<String, String>) objResult.get(EnterpriseWLNSalesServicesConstants.REFPDS_PROVINCE_TABLE);

			if (provinceStateCdMap.isEmpty()) {
				loggerUtil.info("isValidProvinceStateCode", "Province State Code was NOT found in RefPDS. ");

				return true;
			}
			else if (provinceStateCdMap.containsKey(newProvinceStateCode)){
				loggerUtil.info("isValidProvinceStateCode", "Province State Code was found in RefPDS. ");

				return true;
			}
		}

		return false;
	}
	
	/**
	 * 
	 * @return true if both customerInfo.getFirstName() && customerInfo.getLastName() are not empty.
	 */
	private boolean isNotEmptyFirstLastName(){
		return !StringUtils.isEmpty(customerInfo.getFirstName()) 
				&& !StringUtils.isEmpty(customerInfo.getLastName());
	}

	private boolean isNotEmptyPinPrefLangSvcPhone(){
		return !StringUtils.isEmpty(customerInfo.getPin()) 
				&& !StringUtils.isEmpty(customerInfo.getPreferredLanguageCd()) 
				&& !StringUtils.isEmpty(customerInfo.getServicePhoneNumber());
	}
	
	protected void changePolicyErrorCode(CreateWLNAccountCoreResponse result,
			final AdapterResponseBase responseDO) {
		// check if policy/service or general exception
		if (!EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR.equals(responseDO.getAdapterResponseMessage().getMessageCode())) {
			result.getMessageList().get(0).setErrorCode(EnterpriseWLNSalesServicesErrorCodes.CREATE_WLN_ACCOUNT_DOWN_STREAM_ERROR);
		}
	}
	
	protected void eBillAdoptionAndActivationTask(OperationHeader header, String billingAccountNumber, Long customerId,
			String eBillDeclinedReasonCd, String eBillNotificationTypeCd){
		String functionName = "eBillAdoptionAndActivationTask()";
		List<Work> tasksList = new ArrayList<Work>();
		tasksList.add(new EBillAdoptionAndActivationTask(header, billingAccountNumber, customerId, eBillDeclinedReasonCd, eBillNotificationTypeCd));
		if(!tasksList.isEmpty()){
			try {
				ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
				commonJMgr.processAsyncTasks(tasksList);
			} catch (NamingException e) {
				loggerUtil.error("", functionName, JWORK_MANAGER_EXCEPTION_MSG + EnterpriseWLNSalesServicesConstants.SPACE + e);
			}
		}
	}
}
