package com.telus.csm.ewlnsc.util;

import static com.telus.csm.ewlnsc.util.InputValidationErrorMessages.*;

public abstract class EnterpriseWLNSalesServicesErrorCodes {

	//generic error code for ESS
	public static final String UNKNOWN_EWLNSS_ERROR = "90000001";
	// generic error code for downstream service
	public static final String UNKNOWN_SERVICE_ERROR = "90000002";

	//General Missing Elements constants
	public static final String MISSING_OP_HEADER_STR = "{request.operationHeader}";
	public static final String MISSING_APP_ID_STR = "{request.operationHeader.originatorApplicationId}";
	public static final String MISSING_OP_HEADER_USER_PROFILE_STR = "{request.operationHeader.userProfile}";
	public static final String MISSING_OP_HEADER_AGENT_PROFILE_STR = "{request.operationHeader.agentProfile}";
	public static final String MISSING_CHANNEL_OUTLET_ID_STR = "{request.operationHeader.userProfile.salesRepAssociatedOutletList.salesRepAssociatedOutletInternalId}";
	public static final String MISSING_SALES_REP_INTERNAL_ID_STR = "{request.operationHeader.userProfile.salesRepInternalId}";
	public static final String MISSING_CHANNEL_ORG_ID_STR = "{request.operationHeader.userProfile.chnlOrgInternalId}";
	public static final String MISSING_AGENT_PROFILE_CHANNEL_ORG_ID_STR = "{request.operationHeader.agentProfile.chnlOrgInternalId}";
	public static final String MISSING_SALES_TRANSAC_ID_STR = "{request.operationHeader.salesTransactionId}";
	public static final String MISSING_BRAND_CODE_STR = "{request.operationHeader.brandCode}";
	public static final String MISSING_AGENT_PROFILE_CHANNEL_ORG_TYPE_CODE_STR = "{request.operationHeader.agentProfile.channelOrganizationTypeCd}";
	public static final String MISSING_AGENT_PROFILE_USER_PRIVILEGE_ROLE_CODE_LIST_STR = "{request.operationHeader.agentProfile.userPrivilegeRoleCodeList}";
	
	public static final String MISSING_OP_HEADER = MISSING_OP_HEADER_STR;
	public static final String MISSING_APP_ID = MISSING_APP_ID_STR;
	public static final String MISSING_OP_HEADER_USER_PROFILE = MISSING_OP_HEADER_USER_PROFILE_STR;
	public static final String MISSING_OP_HEADER_USER_PROFILE_AND_AGENT_PROFILE = MISSING_OP_HEADER_USER_PROFILE_STR + ", " + MISSING_OP_HEADER_AGENT_PROFILE_STR;
	public static final String MISSING_SALES_REP_INTERNAL_ID = MISSING_SALES_REP_INTERNAL_ID_STR;
	public static final String MISSING_CHANNEL_ORG_ID = MISSING_CHANNEL_ORG_ID_STR;
	public static final String MISSING_CHANNEL_OUTLET_ID = MISSING_CHANNEL_OUTLET_ID_STR;
	public static final String MISSING_SALES_TRANSAC_ID = MISSING_SALES_TRANSAC_ID_STR;
	public static final String MISSING_AGENT_PROFILE_CHANNEL_ORG_ID = MISSING_AGENT_PROFILE_CHANNEL_ORG_ID_STR;
	public static final String MISSING_AGENT_PROFILE_CHANNEL_ORG_TYPE_CODE = MISSING_AGENT_PROFILE_CHANNEL_ORG_TYPE_CODE_STR;
	public static final String MISSING_AGENT_PROFILE_USER_PRIVILEGE_ROLE_CODE_LIST = MISSING_AGENT_PROFILE_USER_PRIVILEGE_ROLE_CODE_LIST_STR;
	
	//Invalid elements constants
	public static final String INVALID_BRAND_CODE_STR = "{request.operationHeader.brandCode " + IS_NOT_VALID + "}";
	public static final String INVALID_OUTLET_ASSOCIATED_PROVINCE= "{request.operationHeader.userProfile.outletAssociatedProvinces() contains invalid province code.}";

	
	// EWLNSMS.searchServiceAddress
	public static final String SEARCH_ADDRESS_INPUT_VALIDATION_ERROR = "11200001";
	public static final String SEARCH_ADDRESS_DOWNSTREAM_ERROR = "11200002";

	// EWLNSVS.getCustomerEligibility
	public static final String CUSTOMER_ELIG_EXCEPTION_ERROR="ESS_GET.CUST.ELIG_";
	public static final String CUSTOMER_ELIG_MISSING_MANDATORY_ELEMENTS = "ESS_GET.CUST.ELIG_00001";
	public static final String CUSTOMER_ELIG_INVALID_INPUT = "ESS_GET.CUST.ELIG_00002";
	public static final String CUSTOMER_ELIG_DOWN_STREAM_ERROR = "ESS_GET.CUST.ELIG_00003";
	//missing element
	public static final String CUSTOMER_ELIG_MISSING_OP_HEADER = MISSING_OP_HEADER_STR;
	public static final String CUSTOMER_ELIG_MISSING_OP_HEADER_USER_PROFILE = MISSING_OP_HEADER_USER_PROFILE_STR;
	public static final String CUSTOMER_ELIG_MISSING_CUSTOMER_ID = "{request.customerId}";
	public static final String CUSTOMER_ELIG_MISSING_APP_ID = MISSING_APP_ID_STR;
	public static final String CUSTOMER_ELIG_MISSING_CHANNEL_ORG_ID = MISSING_CHANNEL_ORG_ID_STR;
	public static final String CUSTOMER_ELIG_MISSING_CHANNEL_OUTLET_ID = MISSING_CHANNEL_OUTLET_ID_STR;
	public static final String CUSTOMER_ELIG_MISSING_SALES_TRANSAC_ID = MISSING_SALES_TRANSAC_ID_STR;
	public static final String CUSTOMER_ELIG_MISSING_SALES_REP_INTERNAL_ID = MISSING_SALES_REP_INTERNAL_ID_STR;
	public static final String CUSTOMER_ELIG_MISSING_NEW_CUST_IND = "{request.newCustomerInd}";
	public static final String CUSTOMER_ELIG_MISSING_CREDIT_ADDRESS = "{request.creditProfile.creditAddress}";
	public static final String CUSTOMER_ELIG_MISSING_STREET_NUMBER = "{request.creditProfile.creditAddress.streetNumber}";
	
	//customerInfo.creditProfile.creditAddress
	public static final String CUSTOMER_ELIG_MISSING_CRED_MUNICIPALITY_NAME = "{request.creditProfile.creditAddress.municipalityName}"; //city
	public static final String CUSTOMER_ELIG_MISSING_CRED_PROVINCE = "{request.creditProfile.creditAddress.provinceStateCode}";
	public static final String CUSTOMER_ELIG_MISSING_CRED_COUNTRY = "{request.creditProfile.creditAddress.countryCode}";
	public static final String CUSTOMER_ELIG_MISSING_CRED_POSTALZIPCODE = "{request.creditProfile.creditAddress.postalZipCode}";
	
	//customerInfo.creditProfile.personalInfo
	public static final String CUSTOMER_ELIG_MISSING_PERSONAL_INFO = "{request.creditProfile.personalInfo}";
	public static final String CUSTOMER_ELIG_MISSING_PERS_EMPLOYMENT_STATUS_CD = "{request.creditProfile.personalInfo.employmentStatusCd}";
	public static final String CUSTOMER_ELIG_MISSING_PERS_RESIDENCY_CD = "{request.creditProfile.personalInfo.residencyCd}";
	public static final String CUSTOMER_ELIG_MISSING_PERS_CREDIT_CHECK_CD = "{request.creditProfile.personalInfo.creditCheckConsentCd}";
	public static final String CUSTOMER_ELIG_MISSING_PERS_BIRTHDT = "{request.creditProfile.personalInfo.birthDate}";
	public static final String CUSTOMER_ELIG_MISSING_PERS_PROV_CUR_RES = "{request.creditProfile.personalInfo.provinceOfCurrentResidenceCd}";
	//creditProfile check
	public static final String CUSTOMER_ELIG_INVALID_COMB_DRIVER_LICENSE = "{request.creditProfile.creditIdentification.driverLicense " + FIELDS_MISSING + "}";
	public static final String CUSTOMER_ELIG_INVALID_COMB_PASS_COUNTRY_CD = "{request.creditProfile.creditIdentification.passport.countryCd " + FIELDS_MISSING + "}";
	public static final String CUSTOMER_ELIG_INVALID_COMB_HEALTH_PROVINCE_CD = "{request.creditProfile.creditIdentification.provincialIdCard.provinceCd " + FIELDS_MISSING + "}";
	public static final String CUSTOMER_ELIG_INVALID_COMB_PROVINCIAL_COUNTRY_CD = "{request.creditProfile.creditIdentification.provincialIdCard.provinceCd " + FIELDS_MISSING + "}";//
	public static final String CUSTOMER_ELIG_INVALID_COMB_PIECE_OF_ID = "{request.creditProfile.creditIdentification.provincialIdCard / healthCard / driverLicense / passport / sin " + PIECE_OF_ID_MSG + "}";
	
	//invalid input
	public static final String CUSTOMER_ELIG_CUSTOMER_ID_MUST_BE_NUMERIC = "{request.customerId must be a numeric}";
	public static final String CUSTOMER_ELIG_EXCLUSIVE_STREETNAME_RURALNUMBER_POBOXNUMBER = "{creditProfile.creditAddress.streetName; creditProfile.creditAddress.ruralNumber;creditProfile.creditAddress.poBoxNumber: address can contain only one of these values.}";
	
	public static final String CUSTOMER_ELIG_INVALID_CUST_COUNTRY_CODE = "{request.creditProfile.creditAddress.countryCode " + COUNTRY_CODE + "}";
	public static final String CUSTOMER_ELIG_INVALID_CUST_PROVINCE_STATE_CODE = "{request.creditProfile.creditAddress.provinceStateCode " + IS_NOT_VALID + "}";
	public static final String CUSTOMER_ELIG_INVALID_CUST_POSTAL_ZIP_CODE = "{request.creditProfile.creditAddress.postalZipCode " + POST_CODE + "}";
	
	public static final String CUSTOMER_ELIG_INVALID_COMB_GUARANTOR = "{request.creditProfile.customerGuarantor " + COMBINATION_FIELDS_MISSING + "}";
	//EWLNSMS
	// ****************** Create Wireline Account ***********************
	public static final String CREATE_WLN_ACCOUNT_EXCEPTION_ERROR="ESS_CREATE.WLN.ACCT_";
	public static final String CREATE_WLN_ACCOUNT_MISSING_MANDATORY_ELEMENTS = "ESS_CREATE.WLN.ACCT_0001";
	public static final String CREATE_WLN_ACCOUNT_INVALID_INPUT = "ESS_CREATE.WLN.ACCT_0002";
	public static final String CREATE_WLN_ACCOUNT_DOWN_STREAM_ERROR = "ESS_CREATE.WLN.ACCT_00003";
	//missing elements
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCOUNT_INFO = "{request.accountInfo}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCOUNT_INFO_ACCOUNTTYPECODE = "{request.accountInfo.accountTypeCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCOUNT_INFO_ACCOUNTSUBTYPECODE = "{request.accountInfo.accountSubTypeCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_OPERATION_HEADER = MISSING_OP_HEADER_STR;
	public static final String CREATE_WLN_ACCOUNT_MISSING_HEADER_SALES_TRANSACTION_ID = MISSING_SALES_TRANSAC_ID_STR;
	public static final String CREATE_WLN_ACCOUNT_MISSING_HEADER_SALES_APP_ID = MISSING_APP_ID_STR;
	public static final String CREATE_WLN_ACCOUNT_MISSING_HEADER_USER_PROFILE = MISSING_OP_HEADER_USER_PROFILE_STR;
	public static final String CREATE_WLN_ACCOUNT_MISSING_HEADER_USER_PROFILE_SALES_REP_ID = MISSING_SALES_REP_INTERNAL_ID_STR;
	public static final String CREATE_WLN_ACCOUNT_MISSING_HEADER_USER_PROFILE_SALES_CHANNEL_ORG_ID = "{request.operationHeader.userProfile.channelOrgInternalId}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_HEADER_USER_PROFILE_SALES_CHANNEL_ORG_NUMBER = "{request.operationHeader.userProfile.channelOrgNumber}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_HEADER_USER_PROFILE_SALES_REP_ASSOC_OUTLET_ID = MISSING_CHANNEL_OUTLET_ID_STR;
	//customerInfo.mainAddress
	public static final String CREATE_WLN_ACCOUNT_MISSING_ADDRESS = "{request.customerInfo.mainAddress}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ADDRESS_MUNICIPALITY_NAME = "{request.customerInfo.mainAddress.municipalityName}";//city
	public static final String CREATE_WLN_ACCOUNT_MISSING_ADDRESS_PROVINCE_STATE_CD = "{request.customerInfo.mainAddress.provinceStateCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ADDRESS_COUNTRY_CD = "{request.customerInfo.mainAddress.countryCode}";
	//customerInfo
	public static final String CREATE_WLN_ACCOUNT_MISSING_CUSTOMER_INFO = "{request.customerInfo}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CUST_FNAME = "{request.customerInfo.firstName}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CUST_LNAME = "{request.customerInfo.lastName}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CUST_PIN = "{request.customerInfo.pin}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CUST_PREF_LANG = "{request.customerInfo.preferredLanguageCd}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CUST_SERV_PHONE_NUMBER = "{request.customerInfo.servicePhoneNumber}";
	
	//creditProfile
	public static final String CREATE_WLN_ACCOUNT_MISSING_CREDIT_PROFILE = "{request.customerInfo.creditProfile}";
	//customerInfo.creditProfile.creditAddress
	public static final String CREATE_WLN_ACCOUNT_MISSING_CRED = "{request.customerInfo.creditProfile.creditAddress}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CRED_MUNICIPALITY_NAME = "{request.customerInfo.creditProfile.creditAddress.municipalityName}"; //city
	public static final String CREATE_WLN_ACCOUNT_MISSING_CRED_PROVINCE = "{request.customerInfo.creditProfile.creditAddress.provinceStateCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CRED_COUNTRY = "{request.customerInfo.creditProfile.creditAddress.countryCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_CRED_POSTALZIPCODE = "{request.customerInfo.creditProfile.creditAddress.postalZipCode}";
	//customerInfo.creditProfile.personalInfo
	public static final String CREATE_WLN_ACCOUNT_MISSING_PERSONAL_INFO = "{request.customerInfo.creditProfile.personalInfo}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_PERS_EMPLOYMENT_STATUS_CD = "{request.customerInfo.creditProfile.personalInfo.employmentStatusCd}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_PERS_RESIDENCY_CD = "{request.customerInfo.creditProfile.personalInfo.residencyCd}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_PERS_CREDIT_CHECK_CD = "{request.customerInfo.creditProfile.personalInfo.creditCheckConsentCd}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_PERS_BIRTHDT = "{request.customerInfo.creditProfile.personalInfo.birthDate}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_PERS_PROV_CUR_RES = "{request.customerInfo.creditProfile.personalInfo.provinceOfCurrentResidenceCd}";
	//accountInfo
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO = "{request.accountInfo}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER = "{request.accountInfo.accountHolderName}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_FNAME = "{request.accountInfo.accountHolderName.firstName}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_LNAME = "{request.accountInfo.accountHolderName.lastName}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_HOLDER_FULLNAME = "{request.accountInfo.accountHolderName.fullName}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_LANG = "{request.accountInfo.preferredContactLanguageCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_MEDIA_TYPE = "{request.accountInfo.billingDeliveryMethodList.mediaTypeCode}";
	//accountInfo.billingAddress
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_MUNICIPALITY = "{request.accountInfo.billingAddress.municipalityName}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_PROVINCE = "{request.accountInfo.billingAddress.provinceStateCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_COUNTRY = "{request.accountInfo.billingAddress.countryCode}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_ACCINFO_POSTALZIPCODE = "{request.accountInfo.billingAddress.postalZipCode}";
	//mediaTypeCode: coreRequest.getAccountInfo().getBillingDeliveryMethodList().getMediaTypeCode()
	public static final String CREATE_WLN_ACCOUNT_MISSING_MEDIA_TYPE_CODE = "{request.accountInfo.billingDeliveryMethodList.mediaTypeCode}";
	
	//invalid input (Length validation)
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUSTOMER_ID = "{request.customerId " + NUMERIC + "}";
	//for Ebill
	public static final String ACCOUNTINFO_EBILLDECLINEDREASONCD_STR = "request.accountInfo.ebillDeclinedReasonCd ";
	public static final String CREATE_WLN_ACCOUNT_INVALID_EBILL_DECLINEREASONCD_MUST_BE_NULL = "{" + ACCOUNTINFO_EBILLDECLINEDREASONCD_STR + MUST_BE_NULL + " when request.accountInfo.billingDeliveryMethodList.mediaTypeCode=E.Bill}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_EBILL_DECLINEREASONCD_MUST_BE_NUMERIC = "{" + ACCOUNTINFO_EBILLDECLINEDREASONCD_STR + NUMERIC + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_EBILL_DECLINEREASONCD_IS_NOT_ALLOWED = "{" + ACCOUNTINFO_EBILLDECLINEDREASONCD_STR + "value is not allowed.}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_EBILL_EBILLNOTIF_SHOULD_NOT_BE_PASSED = "{request.accountInfo.eBillNotificationTypeCd should not be passed when mediaTypeCode = Paper.}";
	//customerInfo
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_FNAME = "{request.customerInfo.firstName " + ALPHABETIC + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_LNAME = "{request.customerInfo.lastName " + ALPHABETIC + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_PIN = "{request.customerInfo.pin " + FOUR_TO_SIX_DIGITS + " and must not start with 0}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_PREF_LANG = "{request.customerInfo.preferredLanguageCd}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_SERV_PHONE_NUMBER = "{request.customerInfo.servicePhoneNumber " + PHONE_NUMBER + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_BUSINESS_PHONE_NUMBER = "{request.customerInfo.businessPhoneNumber " + PHONE_NUMBER + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_BUSINESS_PHONE_EXTENSION = "{request.customerInfo.businessPhoneExtensionNumber " + NUMERIC + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_MOBILE_PHONE_NUMBER = "{request.customerInfo.mobilePhoneNumber " + PHONE_NUMBER + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_EMAIL = "{request.customerInfo.emailAddressTxt " + EMAIL + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_AUTHORIZED_NAME = "{request.customerInfo.authorizedName " + ALPHABETIC + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_PROVINCE_STATE_CODE = "{request.customerInfo.creditProfile.creditAddress.provinceStateCode " + IS_NOT_VALID + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_COUNTRY_CODE = "{request.customerInfo.creditProfile.creditAddress.countryCode " + COUNTRY_CODE + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_POSTAL_ZIP_CODE = "{request.customerInfo.creditProfile.creditAddress.postalZipCode " + POST_CODE + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_EMAIL_DECLINED_RSN_CD = "{request.customerInfo.emailDeclinedReasonCd}";
	
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_MAIN_PROVINCE_STATE_CODE = "{request.customerInfo.mainAddress.provinceStateCode " + IS_NOT_VALID + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_MAIN_COUNTRY_CODE = "{request.customerInfo.mainAddress.countryCode must be CAN}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_MAIN_POSTAL_ZIP_CODE = "{request.customerInfo.mainAddress.postalZipCode " + POST_CODE + "}";
	//billingAddress
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_BILL_PROVINCE_STATE_CODE = "{request.accountInfo.billingAddress.provinceStateCode " + IS_NOT_VALID + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_BILL_COUNTRY_CODE = "{request.accountInfo.billingAddress.countryCode " + COUNTRY_CODE + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_CUST_BILL_POSTAL_ZIP_CODE = "{request.accountInfo.billingAddress.postalZipCode " + POST_CODE + "}";
	//accountInfo
	public static final String CREATE_WLN_ACCOUNT_INVALID_ACCINFO_CONTACT_LANG = "{request.accountInfo.preferredContactLanguageCode " + EN_OR_FR + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_ACCINFO_MEDIA_TYPE = "{request.accountInfo.billingDeliveryMethodList.mediaTypeCode " + PAPER_OR_EBILL + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_EBILL_NOTIF_TYPECD = "{request.accountInfo.eBillNotificationTypeCd}";
	//paymentInfo
	public static final String CREATE_WLN_ACCOUNT_INVALID_PAY_CREDIT_CARD_TYPE = "{request.customerInfo.creditProfile.creditIdentification.creditCard.cardType " + INVALID_CREDIT_CARD_TYPE_MSG + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_PAY_TOKEN = "{request.customerInfo.creditProfile.creditIdentification.creditCard.token " + CREDIT_NUMBER + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_PAY_EXPIRY_MONTH_YEAR = "{request.customerInfo.creditProfile.creditIdentification.creditCard.expiryMonth / expiryYear " + CREDIT_CARD_EXPIRY_DATE + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_PAY_LAST4 = "{request.customerInfo.creditProfile.creditIdentification.creditCard.last4 " + NUMERIC + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_PAY_FIRST6 = "{request.customerInfo.creditProfile.creditIdentification.creditCard.first6 " + NUMERIC + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_PAY_EXPIRY_YEAR = "{request.customerInfo.creditProfile.creditIdentification.creditCard.expiryYear " + YEAR + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_PAY_EXPIRY_MONTH = "{request.customerInfo.creditProfile.creditIdentification.creditCard.expiryMonth " + MONTH + "}";
	//Field Combination validation
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_MAIN_ADDRESS = "{request.customerInfo.mainAddress " + COMBINATION_FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_BILLING_ADDRESS = "{request.accountInfo.billingAddress " + COMBINATION_FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_SERVICE_ADDRESS = "{request.customerInfo.creditProfile.creditAddress " + COMBINATION_FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_PERSONAL_INFO = "{request.customerInfo.creditProfile.personalInfo " + COMBINATION_FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_GUARANTOR = "{request.customerInfo.creditProfile.customerGuarantor " + COMBINATION_FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_DRIVER_LICENSE = "{request.customerInfo.creditProfile.creditIdentification.driverLicense " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_PASS_COUNTRY_CD = "{request.customerInfo.creditProfile.creditIdentification.passport.countryCd " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_HEALTH_PROVINCE_CD = "{request.customerInfo.creditProfile.creditIdentification.healthCard.provinceCd " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_PROVINCIAL_COUNTRY_CD = "{request.customerInfo.creditProfile.creditIdentification.provincialIdCard.provinceCd " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_INVALID_COMB_PIECE_OF_ID = "{request.customerInfo.creditProfile.creditIdentification.provincialIdCard / healthCard / driverLicense / passport / sin " + PIECE_OF_ID_MSG + "}";
	
	// for eBill
	public static final String CREATE_WLN_ACCOUNT_MISSING_EBILL_EMAILADDRESSTXT = "{request.customerInfo.emailAddressTxt " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_EBILL_MOBILEPHONENUMBER = "{request.customerInfo.mobilePhoneNum " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_EBILL_DECLINEDREASONCD = "{request.accountInfo.ebillDeclinedReasonCd " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_EBILL_EMAIL_DECLINEDREASONCD = "{request.customerInfo.emailDeclinedReasonCd " + FIELDS_MISSING + "}";
	public static final String CREATE_WLN_ACCOUNT_MISSING_EBILL_NOTIF_TYPECD = "{request.accountInfo.eBillNotificationTypeCd " + FIELDS_MISSING + "}";
	
	//EWLNSIS.getAvailableInstallDates
	public static final String GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR="ESS_GT.INST.DT_";
	public static final String GET_AVAIL_INSTALL_DATE_MISSING_MANDATORY_ELEMENTS = "ESS_GT.INST.DTL_00001";
	public static final String GET_AVAIL_INSTALL_DATE_INVALID_INPUT              = "ESS_GT.INST.DTL_00002";
	public static final String GET_AVAIL_INSTALL_DATE_DOWNSTREAM_EXCEPTION="ESS_GT.INST.DTL_00003";
	public static final String GET_AVAIL_INSTALL_DATE_INVALID_END_DATE="ESS_GT.INST.DTL_00006";
	public static final String GET_AVAIL_INSTALL_DATE_REFPDS_EXCEPTION="ESS_GT.INST.DTL_00007";
	public static final String GET_AVAIL_INSTALL_DATE_NOT_FEASIBLE_SERVICE="ESS_GT.INST.DTL_00008";
	
	public static final String GET_AVAIL_INSTALL_DATE_DOWNSTREAM_ERROR_TXT="EWNSIS.getInstallationDetail received exception when calling Downstream Service: ";

	
	public static final String GET_AVAIL_INST_DATE_MISSING_OP_HEADER = MISSING_OP_HEADER_STR;
	public static final String GET_AVAIL_INST_DATE_MISSING_APP_ID = MISSING_APP_ID_STR;
	public static final String GET_AVAIL_INST_DATE_MISSING_CHANNEL_ORG_ID = MISSING_CHANNEL_ORG_ID_STR;
	public static final String GET_AVAIL_INST_DATE_MISSING_CHANNEL_OUTLET_ID = "{request.operationHeader.userProfile.salesRepAssociatedOutletList}";
	public static final String GET_AVAIL_INST_DATE_MISSING_SALES_TRANSAC_ID = MISSING_SALES_TRANSAC_ID_STR;
	public static final String GET_AVAIL_INST_DATE_MISSING_SALES_REP_ID = "{request.operationHeader.userProfile.salesRepId}";
	public static final String GET_AVAIL_INST_DATE_MISSING_START_DATE = "{request.startDate}";
	public static final String GET_AVAIL_INST_DATE_MISSING_END_DATE = "{request.endDate}";
	public static final String GET_AVAIL_INST_DATE_MISSING_ORDER_PRODUCT_LIST = "{request.orderProductList}";
	public static final String GET_AVAIL_INST_DATE_MISSING_ACTION_TYPE_CD = "{request.orderProductList.actionTypeCd}";
	public static final String GET_AVAIL_INST_DATE_INVALID_ACTION_TYPE_CD = "{request.orderProductList.actionTypeCd}";
	public static final String GET_AVAIL_INST_DATE_MISSING_PRODUCT_TYPE = "{request.orderProductList.product_type}";
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS = "{request.serviceAddress}";
	public static final String GET_AVAIL_INST_DATE_MISSING_SUBSCRIBER_PRODUCTS="{request.subscribedProductList}";
	public static final String GET_AVAIL_INST_DATE_MISSING_SUBSCRIBER_PRODUCT_SERVICE_TYPE="{request.subscribedProductList.serviceType}" + MISSING_SERVICE_TYPE_IN_SUBSCRIBER_PRODUCTS;
	
	public static final String GET_AVAIL_INST_DATE_INVALID_START_DATE = "{request.startDate} " + INVALID_START_DATE;
	public static final String GET_AVAIL_INST_DATE_INVALID_END_DATE = "{request.endDate} " + INVALID_END_DATE;
	public static final String GET_AVAIL_INST_DATE_INVALID_DATE_RANGE = "The maximum range of dates available is %s days ";
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_ID = "{request.serviceAddress.addressId} " + INVALID_ADDRESS_ID;
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_MUNICIPALITY_NAME = "{request.serviceAddress.municipalityName} "+ INVALID_MUNICIPALITY_NAME;
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_PROVINCE_STATE_CODE = "{request.serviceAddress.provinceStateCode} " + INVALID_PROVINCE_CODE;
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_STREET_NAME = "{request.serviceAddress.streetName} " + INVALID_STREET_NAME;
	 
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_TYPE = "{request.orderedProductList.serviceType} " + INVALID_SERVICE_TYPE;
	public static final String GET_AVAIL_INST_DATE_MISSING_SUB_PROD_SERVICE_TYPE = "{request.subscribedProductList.serviceType} " + INVALID_SERVICE_TYPE;
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_PLAN_CD_FOR_HSIC = "{request.orderedProductList.servicePlan} " + MISSING_SERVICE_PLAN_HSIC;
	public static final String GET_AVAIL_INST_DATE_MISSING_SERVICE_ID = "{request.subscribedProductList.productInstance.serviceId} " + MISSING_SERVICE_ID;
	public static final String GET_AVAIL_INST_DATE_INVALID_ORDER_SERVICE_TYPE = "{request.orderedProductList.serviceType} " + INVALID_EXISTING_SERVICE_TYPE;
	public static final String GET_AVAIL_INST_DATE_MISSING_SUB_PROD_INV_SERVICE_TYPE = "{request.subscribedProductList.serviceType} " + INVALID_EXISTING_SERVICE_TYPE;
	public static final String GET_AVAIL_INST_DATE_INVALID_BRAND_CODE = "{request.operationHeader.brand.code}" + IS_NOT_VALID;
	public static final String GET_AVAIL_INST_DATE_INVALID_EXCLUSION_ELEMENT = "{request.attributeExclusionList}" + IS_NOT_VALID;
	public static final String GET_AVAIL_INST_DATE_MISSING_TRANSACTION_TIMESTAMP = "{request.operationHeader.transaction.timestamp}" + IS_NOT_VALID;
	public static final String GET_AVAIL_INST_DATE_MISSING_PRODUCT_CATALOG_ID = "{request.orderedProductList.productInstance.serviceId} " + MISSING_PRODUCT_CATALOGUE_ID;
	public static final String GET_AVAIL_INST_DATE_INVALID_SERVICE_TYPE = "{request.orderedProductList.productInstance.serviceType} " + SUB_PROD_INVALID_SERVICE_TYPE;
	
	public static final String GET_AVAIL_INST_DATE_INVALID_EXCLUSION_LIST = "{request.attributeExclusionList} must contain just one element.";
	public static final String GET_AVAIL_INST_DATE_NOT_FEASIBLE_MSG = "Could not retrieve recommended installation type for one or more serivce types(see contextData for service type";
	
	//EWLNSIS.getAvailableOfferSummaryList
	public static final String GAOSL_EXCEPTION_ERROR="ESS_GAOSL_";
	public static final String GAOSL_GENERIC_ERROR_CODE = "ESS_GAOSL_00000"; // TODO:
	public static final String GAOSL_MISSING_MANDATORY_ELEMENTS = "ESS_GAOSL_00001";
	public static final String GAOSL_INVALID_INPUT = "ESS_GAOSL_00002";
	public static final String GAOSL_DOWN_STREAM_ERROR = "ESS_GAOSL_00003";
	public static final String GAOSL_OFFER_NOT_APPLICABLE="ESS_GAOSL_00004";
	public static final String GAOSL_NO_ADDRESS_FOUND = "ESS_GAOSL_00005";
	public static final String GAOSL_NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY = "ESS_GAOSL_00006";
	public static final String GAOSL_NO_PRODUCT_FOUND = "ESS_GAOSL_00007";
	public static final String NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY_MSG = "This address has no ports available. HSIC and TTV offers are unavailable.";
	
	public static final String GET_AVAIL_OFFER_SUMMARY_LIST_EXCEPTION_ERROR="ESS_GAOSL_";
	public static final String GET_AVAIL_WLS_PRODUCT_LIST_EXCEPTION_ERROR="EWSS_GT.AVAIL.WLS_PRODUCT_LIST_";
	
	//EWLNSIS.getSalesOfferDetail
	public static final String GSOD_MISSING_MANDATORY_ELEMENTS = "ESS_GSOD_00001";
	public static final String GSOD_INVALID_INPUT = "ESS_GSOD_00002";
	public static final String GSOD_DOWN_STREAM_ERROR = "ESS_GSOD_00003";
	public static final String GSOD_OFFER_NOT_FOUND = "ESS_GSOD_00004";
	public static final String GSOD_OFFER_NOT_APPLICABLE = "ESS_GSOD_00005";
	public static final String GSOD_NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY = "ESS_GSOD_00006";
	public static final String GSOD_NO_PRODUCT_FOUND = "ESS_GSOD_00007";

	// missing/invalid elements
	public static final String GAOSL_MISSING_OPERATION_HEADER_BRAND_CODE = "{request.operationHeader.brandCode}";
	public static final String GAOSL_MISSING_SALES_OFFER_CRITERIA = "{request.salesOfferCriteria}";
	public static final String GAOSL_MISSING_SERVICE_ADDRESS = "{request.salesOfferCriteria.serviceAddress}";
	public static final String GAOSL_MISSING_SERVICE_ADDRESS_PROVINCE_CD = "{request.salesOfferCriteria.serviceAddress.provinceCode}";
	public static final String GAOSL_INVALID_SERVICE_ADDRESS_PROVINCE_CD = "{request.salesOfferCriteria.serviceAddress.provinceCode}";
	public static final String GAOSL_MISSING_SERVICE_ADDRESS_CITY_CD = "{request.salesOfferCriteria.serviceAddress.cityCode}";
	public static final String GAOSL_INVALID_SERVICE_ADDRESS_CITY_CD = "{request.salesOfferCriteria.serviceAddress.cityCode must contain only alphabetic characters.}";
	public static final String GAOSL_MISSING_SERVICE_ADDRESS_ID = "{request.salesOfferCriteria.serviceAddress.serviceAddressId}";
	public static final String GAOSL_MISSING_SUBSCRIBED_SERVICE_ID = "{request.salesOfferCriteria.subscribedServiceIdentifier.serviceId}";
	public static final String GAOSL_MISSING_OFFER_FILTER = "{request.salesOfferCriteria.offerFilter}";
	public static final String GAOSL_MISSING_OFFER_FILTER_ACCOUNT_TYPE_CD = "{request.salesOfferCriteria.offerFilter.accountTypeCode}";
	public static final String GAOSL_MISSING_OFFER_FILTER_ELEMENTS_ALL_NULL = "{request.salesOfferCriteria.offerFilter.offerContractTerm, request.salesOfferCriteria.offerFilter.productList() and request.salesOfferCriteria.offerFilter.bundleInd can NOT be all null at the same time when promotionCd is not passed.}";
	public static final String GAOSL_INVALID_DUPLICATE_SERVICE_IDS = "{request.salesOfferCriteria.subscribedServiceIdentifier() can NOT contain duplicate serviceId elements.}";
	public static final String GAOSL_MISSING_BUNDLE_IND="{request.salesOfferCriteria.offerFilter.bundleInd}";
	public static final String GAOSL_MISSING_BILLING_ACCOUNT_NUMBER="{request.salesOfferCriteria.billingAccountNumber} can NOT be null if {request.salesOfferCriteria.customerId} is passed.";
	public static final String GAOSL_MISSING_CUSTOMER_ID="{request.salesOfferCriteria.customerId} can NOT be null if {request.salesOfferCriteria.billingAccountNumber} is passed";
	public static final String GAOSL_INVALID_PRODUCT_ORDER_TYPE = "{request.salesOfferCriteria.offerFilter.productList.productOrderType.productOrderType is not valid.}";
	public static final String GAOSL_MISSING_PRODUCT_COMP_CONTTTERM_OFFERCONTTERM_ARE_NULL = "{request.salesOfferCriteria.offerFilter.offerContractTerm, request.salesOfferCriteria.offerFilter.productList.contractTerm, request.salesOfferCriteria.offerFilter.productList.productComponent can NOT be all null.}";
	public static final String GAOSL_MISSING_PRODUCT_TYPE = "{request.salesOfferCriteria.offerFilter.productList.productType}";
	public static final String GAOSL_INVALID_PRODUCT_TYPE = "{request.salesOfferCriteria.offerFilter.productList.productType is not valid.}";
	public static final String GAOSL_INVALID_SERVICEIDLIST = "if customerId and billingAccount number are not present in request, subscribedServiceIdentifier list can NOT be present in the request : {request.salesOfferCriteria.subscribedServiceIdentifier}";
	
	// EWSIS.getSalesOfferDetail 
	public static final String GET_SALES_OFFER_DETAIL_EXCEPTION_ERROR = "EWSS_GT.SALES_OFFER_DETAIL_";
	// EWSIS.getWirelineProductComponentList
	public static final String GET_WLN_PRODUCT_COMPONENT_LIST_EXCEPTION_ERROR = "EWSS_GT.WLN_PROD_COMP_LIST_";
	
	//EWSIS.getAvailableWirelineProductList
	public static final String GET_AVAIL_WLN_PRODUCT_LIST_MISSING_MANDATORY_ELEMENTS = "ESS_GAWPL_00001";
	public static final String GET_AVAIL_WLN_PRODUCT_LIST_INVALID_INPUT = "ESS_GAWPL_00002";
	public static final String GET_AVAIL_WLN_PRODUCT_LIST_DOWNSTREAM_ERROR = "ESS_GAWPL_00003";
	public static final String GET_AVAIL_WLN_PRODUCT_LIST_EXCEPTION_ERROR = "ESS_GAWPL_"; // Not used
	
	
	// EWLNSIS.getWirelineProductComponentList
//	public static final String GWPCL_EXCEPTION_ERROR="ESS_GWPCL_";
	public static final String GWPCL_MISSING_MANDATORY_ELEMENTS = "ESS_GWPCL_00001";
	public static final String GWPCL_INVALID_INPUT = "ESS_GWPCL_00002";
	public static final String GWPCL_UNIDENTIFIABLE_HIERARCHY_NODE = "ESS_GWPCL_00003";
	// missing/invalid elements
	public static final String GWPCL_MISSING_SALES_PROD_COMP_LIST = "{request.salesProductComponentList}";
	public static final String GWPCL_MISSING_PRODUCT_TYPE = "{request.salesProductComponentList.productTypeCode}";
	public static final String GWPCL_INVALID_PRODUCT_TYPE = "{request.salesProductComponentList.productTypeCode is not valid.}";
	public static final String GWPCL_MISSING_PRODUCT_TEMPLATE_IDENTIF = "{request.salesProductComponentList.productTemplateIdentifier}";
	public static final String GWPCL_MISSING_MAIN_COMPONENT_IDENTIF = "{request.salesProductComponentList.mainComponentIdentifier}";
	public static final String GWPCL_MISSING_PRODUCT_COMPONENT_LIST = "{request.salesProductComponentList.productComponentList}";
	public static final String GWPCL_INVALID_PRODUCT_COMPONENT_LIST = "{request.salesProductComponentList.productComponentList size exceeds the maximum allowed.}";
	public static final String GWPCL_MISSING_PRODUCT_CATALOG_IDENTIF = "{request.salesProductComponentList.productComponentList.productCatalogueIdentifier}";
	public static final String GWPCL_MISSING_PARENT_PRODUCT_CATALOG_IDENTIF = "{request.salesProductComponentList.productComponentList.parentProductCatalogueIdentifier}";
	public static final String GWPCL_INVALID_PRODUCT_CATALOG_IDENTIF = "{request.salesProductComponentList.productComponentList.productCatalogueIdentifier must be numeric and non zero.}";
	public static final String GWPCL_INVALID_PARENT_PRODUCT_CATALOG_IDENTIF = "{request.salesProductComponentList.productComponentList.parentProductCatalogueIdentifier must be numeric and non zero.}";
	
	// missing/invalid elements
	public static final String GASOL_MISSING_SWEETENER_OFFER_FILTER_CRITERIA = "{request.sweetnerOfferFilterCriteria}";
	public static final String GASOL_MISSING_ASSOCIATED_WRIELINE_SALES_SUMMARY = "{request.associatedWirelineSalesSummary}";
	public static final String GASOL_MISSING_OFFER_HEADER = "{request.associatedWirelineSalesSummary.offerHeader}";
	public static final String GASOL_MISSING_OFFER_ID = "{request.associatedWirelineSalesSummary.offerHeader.offerId}";
	public static final String GASOL_MISSING_OFFER_PROGRAM_ID = "{request.associatedWirelineSalesSummary.offerHeader.offerProgramId}";
	public static final String GASOL_MISSING_OFFER_CODE = "{request.associatedWirelineSalesSummary.offerHeader.offerCode}";
	public static final String GASOL_MISSING_OFFER_PERSPECTIVE_DATE = "{request.associatedWirelineSalesSummary.offerHeader.perspectiveDate}";
	public static final String GASOL_MISSING_OFFER_FILTER = "{request.associatedWirelineSalesSummary.offerHeader.offerFilter}";
	public static final String GASOL_MISSING_ACCOUNT_TYPE_CODE = "{request.associatedWirelineSalesSummary.offerHeader.offerFilter.accountTypeCode}";
	public static final String GASOL_MISSING_PRODUCT_LIST = "{request.associatedWirelineSalesSummary.productList}";
	public static final String GASOL_MISSING_PRODUCT_ORDER_TYPE = "{request.associatedWirelineSalesSummary.productList.productOrderType}";
	public static final String GASOL_MISSING_PRODUCT_ORDER_TYPE_CODE = "{request.associatedWirelineSalesSummary.productList.productOrderType.productOrderTypeCd}";
	public static final String GASOL_INVALID_PRODUCT_ORDER_TYPE = "{request.associatedWirelineSalesSummary.productList.productOrderType.productOrderTypeCd is not valid.}";
	public static final String GASOL_MISSING_PRODUCT_TYPE_CODE = "{request.associatedWirelineSalesSummary.productList.productTypeCd}";
	public static final String GASOL_MISSING_PRODUCT_COMPONENT = "{request.associatedWirelineSalesSummary.productList.productComponent}";
	public static final String GASOL_MISSING_PRODUCT_CATALOGUE_IDENTIFIER = "{request.associatedWirelineSalesSummary.productList.productComponent.productCatalogueIdentifier}";
	public static final String GASOL_MISSING_CONTRACT_TERM_CODE = "{request.associatedWirelineSalesSummary.productList.productComponent.contractTermCd}";
	public static final String GASOL_MISSING_SERVICE_ADDRESS = "{request.sweetnerOfferFilterCriteria.serviceAddress}";
	public static final String GASOL_MISSING_PROVINCE_CODE = "{request.sweetnerOfferFilterCriteria.serviceAddress.provinceCode}";
	public static final String GASOL_MISSING_CITY_CODE = "{request.sweetnerOfferFilterCriteria.serviceAddress.cityCode}";

	//EWLNSIS.getAvailableSweetenerOfferList
	public static final String GASOL_EXCEPTION_ERROR="ESS_GASOL_";
	public static final String GASOL_GENERIC_ERROR_CODE = "ESS_GASOL_00000"; // TODO:
	public static final String GASOL_MISSING_MANDATORY_ELEMENTS = "ESS_GASOL_00001";
	public static final String GASOL_INVALID_INPUT = "ESS_GASOL_00002";
	public static final String GASOL_DOWN_STREAM_ERROR = "ESS_GASOL_00003";
	public static final String GASOL_OFFER_NOT_APPLICABLE="ESS_GASOL_00004";

	//EWLNSIS.getAccessoryOfferList
	public static final String GAOL_MISSING_OPERATION_HEADER_BRAND_CODE = "{request.operationHeader.brandCode}";
	public static final String GAOL_MISSING_SERVICE_ADDRESS = "{request.accessoryOfferCriteria.serviceAddress}";
	public static final String GAOL_MISSING_SERVICE_ADDRESS_PROVINCE_CD = "{request.accessoryOfferCriteria.serviceAddress.provinceCode}";
	public static final String GAOL_INVALID_SERVICE_ADDRESS_PROVINCE_CD = "{request.accessoryOfferCriteria.serviceAddress.provinceCode}";
	public static final String GAOL_MISSING_SERVICE_ADDRESS_CITY_CD = "{request.accessoryOfferCriteria.serviceAddress.cityCode}";
	public static final String GAOL_MISSING_SERVICE_ADDRESS_ID = "{request.accessoryOfferCriteria.serviceAddress.serviceAddressId}";
	public static final String GAOL_MISSING_SUBSCRIBED_SERVICE_ID = "{request.accessoryOfferCriteria.subscribedServiceIdentifierList.serviceId}";
	public static final String GAOL_MISSING_ASSOCIATED_WIRELINE_SALES_SUMMARY_LIST = "{request.accessoryOfferCriteria.associatedWirelineSalesSummaryList}";
	public static final String GAOL_MISSING_ASSOCIATED_WIRELINE_SALES_SUMMARY_LIST_PRODUCT_LIST = "{request.accessoryOfferCriteria.associatedWirelineSalesSummaryList.productList}";
	public static final String GAOL_MISSING_OFFER_FILTER = "{request.accessoryOfferCriteria.offerFilter}";
	public static final String GAOL_MISSING_OFFER_FILTER_ACCOUNT_TYPE_CD = "{request.accessoryOfferCriteria.offerFilter.accountTypeCode}";
	public static final String GAOL_MISSING_CUSTOMER_ID="{request.accessoryOfferCriteria.customerId} can NOT be null if {request.accessoryOfferCriteria.billingAccountNumber} is passed";
	public static final String GAOL_MISSING_CUSTOMER_ID_AND_BILLING_ACCOUNT_NUMBER="{request.accessoryOfferCriteria.customerId} and {request.accessoryOfferCriteria.billingAccountNumber} can NOT be null both";
	public static final String GAOL_MISSING_SERVICEIDLIST = "if billingAccount number is present in request, subscribedServiceIdentifierList can NOT be missing in the request : {request.accessoryOfferCriteria.subscribedServiceIdentifierList}";
	public static final String GAOL_INVALID_PRODUCT_ORDER_TYPE = "{request.accessoryOfferCriteria.associatedWirelineSalesSummaryList.productList.productOrderType.productOrderType is not valid.}";
	public static final String GAOL_MISSING_CONTRACT_TERM_AND_PRODUCT_COMPONENT_LIST_ARE_NULL = "{request.accessoryOfferCriteria.associatedWirelineSalesSummaryList.productList.contractTerm, request.accessoryOfferCriteria.associatedWirelineSalesSummary.productList.productComponentList can NOT be all null.}";
	public static final String GAOL_MISSING_PRODUCT_TYPE = "{request.accessoryOfferCriteria.associatedWirelineSalesSummaryList.productList.productType}";
	public static final String GAOL_INVALID_PRODUCT_TYPE = "{request.accessoryOfferCriteria.associatedWirelineSalesSummaryList.productList.productType is not valid.}";
	public static final String GAOL_INVALID_DUPLICATE_SERVICE_IDS = "{request.accessoryOfferCriteria.subscribedServiceIdentifierList() can NOT contain duplicate serviceId elements.}";

	public static final String GAOL_MISSING_MANDATORY_ELEMENTS = "ESS_GAOL_00001";
	public static final String GAOL_INVALID_INPUT = "ESS_GAOL_00002";
	public static final String GAOL_DOWN_STREAM_ERROR = "ESS_GAOL_00003";
	public static final String GAOL_OFFER_NOT_APPLICABLE="ESS_GAOL_00004";
	public static final String GAOL_NO_SERVICE_ADDRESS_FOUND = "ESS_GAOL_00005";
	
	// NWLN-9103 Update Account ERROR code
	public static final String UPDATE_ACCOUNT_EXCEPTION_ERROR="ESS_UPDATE.ACCT_";
	public static final String UPDATE_ACCOUNT_MISSING_MANDATORY_ELEMENTS = "ESS_UPDATE.ACCT_0001";
	public static final String UPDATE_ACCOUNT_INVALID_INPUT = "ESS_UPDATE.ACCT_0002";
	public static final String UPDATE_ACCOUNT_DOWN_STREAM_ERROR = "ESS_UPDATE.ACCT_00003";
	public static final String UPDATE_ACCOUNT_MISSING_BILLING_ACCOUNT = "{request.billingAccount}";
	public static final String UPDATE_ACCOUNT_MISSING_BILLING_ACCOUNT_NUM = "{request.billingAccount.billingAccountNumber}";
	public static final String UPDATE_ACCOUNT_MISSING_CUSTOMER = "{request.customer}";
	public static final String UPDATE_ACCOUNT_MISSING_CUSTOMER_ID = "{request.customer.customerId}";
	public static final String UPDATE_ACCOUNT_MISSING_BILL_NOTIFY = "{request.billNotification}";
	public static final String UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_CODE = "{request.billNotification.notificationMethodCd}";
	public static final String UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_EMAIL = "{request.billNotification.notificationEmailAddressTxt}";
	public static final String UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_SMS = "{request.billNotification.notificationSmsNum}";

	
	//*** ESS FFH message code ***
	//
	//General format: ESS-FFH-ggggtnnn
	//
	// gggg=group code
	// 0000 - common
	//
	// t=type
	// 9 - error
	// 8 - warning
	// 0 - info
	//
	// nnn=serial

	public static final String ESS_FFH_MISSING_MANDATORY_ELEMENTS = "ESS-FFH-00009001";
	public static final String ESS_FFH_INVALID_INPUT = "ESS-FFH-00009002";
	public static final String ESS_FFH_RECORD_NOT_FOUND = "ESS-FFH-00009003";
	public static final String ESS_FFH_SYSTEM_ERROR = "ESS-FFH-00009901";
	
	//SalesValidationHelper
	public static final String ESS_FFH_SALES_VALIDATION_HELPER_ERROR = "ESS-FFH-10019003";
	public static final String ESS_FFH_SALES_VALIDATION_HELPER_INEG_OFFER = "ESS-FFH-10019004";
	
	//EnterpriseSalesService.createSales (gggg = 0001)
	public static final String ESS_FFH_SALES_ERROR = "ESS-FFH-00019003";
			
	
	//EnterpriseSalesService.createSalesItem (gggg = 0002)
	public static final String ESS_FFH_SALES_ITEM_ERROR = "ESS-FFH-00029003";
	
	//Error code for Billing Account not found in GCAP operation.
	public static final String GAOL_ACCOUNT_NOT_FOUND_GCAP = "303002";

	// Error code for failing to find OMS pending offer in VESTA in GetOrderListByCustomerFromOrderQueryServiceTask
	public final static String GET_ACCT_PROFILE_ERROR_303005_ID_MISSING_PENDING_ORDER_DETAILS = "303005";
	
	// Error codes for update sale
	public static final String ESS_FFH_SHOPPING_CART_NOT_FOUND = "ESS-FFH-10019001";
	public static final String ESS_FFH_SHOPPING_CART_NOT_ITERMEDIATE_COMPLETE = "ESS-FFH-10019002";
	
	// Error codes for cancel sale
	public static final String ESS_FFH_SALE_STATUS_CANNOT_BE_CANCELLED = "ESS-FFH-10029002";
	
	
	/**
	 * DUPLICATE_INTERNET_PRODUCT_IN_CART("ESS_SC_VALIDATION_00001", "duplicate internet product in cart"),
	DUPLICATE_TV_PRODUCT_IN_CART("ESS_SC_VALIDATION_00002", "duplicate tv product in cart"),
	DUPLICATE_SINGLE_LINE_PRODUCT_IN_CART("ESS_SC_VALIDATION_00003", "duplicate single line product in cart"),
	SERVICE_ADDRESS_NOT_PROVIDED("ESS_SC_VALIDATION_00004", "service address not provided in cart"),
	CUSTOMER_NOT_FOUND("ESS_SC_VALIDATION_00005", "customer not found"),
	PAY_CHANNEL_ID_NOT_FOUND("ESS_SC_VALIDATION_00006", "pay channel id not found"),
	SERVICE_ADDRESS_NOT_FOUND("ESS_SC_VALIDATION_00007", "service address not found"),
	NON_STACKABLE_DISCOUNT_HSIC("ESS_SC_VALIDATION_0008", "discount code={discountCode} for HSIC from the cartItemRelationId={itemRelationId} is not fround from the market offer={offerId}"),
	NON_STACKABLE_DISCOUNT_TTV("ESS_SC_VALIDATION_00008", "ttv discounts non stackable"),
	NON_STACKABLE_DISCOUNT_SING("ESS_SC_VALIDATION_00008", "sing discounts non stackable"),
	NON_STAKABLE_SWEETENER_HSIC("ESS_SC_VALIDATION_00009", "hsic sweeteners non stackable"),
	NON_STAKABLE_SWEETENER_TTV("ESS_SC_VALIDATION_00009", "ttv sweeteners non stackable"),
	NON_STAKABLE_SWEETENER_SING("ESS_SC_VALIDATION_00009", "sing sweeteners non stackable"),
	DISCOUNT_NON_EXISTING_IN_OFFER("ESS_SC_VALIDATION_00008","One or more discounts in the cartItem is not found in the market offer")
	 */
	
	//Error codes for WLN VSO rules

	
	
	//EWLNSMS.getSalesOrderHistory
	// ****************** Create Wireline Account ***********************
	public static final String GSOH_ERROR_MISSING_START_DATE="ESS_GSOH_WLN_001";
	public static final String GSOH_ERROR_MISSING_END_DATE="ESS_GSOH_WLN_002";
	public static final String GSOH_ERROR_MISSING_OUTLETID_SALESREPID="ESS_GSOH_WLN_003";
	
	private EnterpriseWLNSalesServicesErrorCodes(){
		
		
	}
}
