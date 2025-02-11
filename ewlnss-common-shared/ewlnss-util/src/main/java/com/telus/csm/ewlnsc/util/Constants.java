package com.telus.csm.ewlnsc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public interface Constants {

	public static final String CREDIT_ADDRESS_TYPE_CODE = "CB";

	public static final String ENABLER_BILLING_ACCOUNT_SYSTEM_CODE = "1001";

	public static final String ADDRESS_BILLING_ASSIGNMENT_TYPE = "M";

	// getAccountSummary Brand Id
    public static final int ACCT_SUM_BRAND_ID_TELUS = 1;
    public static final int ACCT_SUM_BRAND_ID_KOODO = 3;

    public static final String TELUS = "TELUS";

    // Credit Eligibility ejb error codes and descriptions
    public static final int 	ERROR_CREDIT_ELIGIBILITY_EJB_CONNECTION_ID = 3000;
    public static final String 	ERROR_CREDIT_ELIGIBILITY_EJB_CONNECTION_MSG = "Failed to initialize connection to Credit Eligibility EJB service.";

    public static final int 	ERROR_CREDIT_ELIGIBILITY_EJB_NOT_FOUND_ID = 3001;
    public static final String 	ERROR_CREDIT_ELIGIBILITY_EJB_NOT_FOUND_MSG = "Object not found thrown by calling on Credit Eligibility EJB service - checkCreditEligibility().";

    public static final int 	ERROR_CREDIT_ELIGIBILITY_EJB_SVC_EXCEPTION_ID = 3002;
    public static final String 	ERROR_CREDIT_ELIGIBILITY_EJB_SVC_EXCEPTION_MSG = "Service exception thrown by calling on Credit Eligibility EJB service - checkCreditEligibility().";

    public static final int 	ERROR_CREDIT_ELIGIBILITY_EJB_VALIDATION_EXCEPTION_ID = 3003;
    public static final String 	ERROR_CREDIT_ELIGIBILITY_EJB_VALIDATION_EXCEPTION_MSG = "Validation exception thrown by calling on Credit Eligibility EJB service - checkCreditEligibility().";

    public static final int 	ERROR_CREDIT_ELIGIBILITY_EJB_REMOTE_EXCEPTION_ID = 3004;
    public static final String 	ERROR_CREDIT_ELIGIBILITY_EJB_REMOTE_EXCEPTION_MSG = "Remote exception thrown by calling on Credit Eligibility EJB service - checkCreditEligibility().";

    // payment EJB error codes and descriptions
    public static final int		ERROR_PAYMENT_EJB_CONNECTION_ID = 3005;
    public static final String 	ERROR_PAYMENT_EJB_CONNECTION_MSG = "Failed to initialize connection to Payment EJB service.";

    public static final int 	ERROR_PAYMENT_SAVE_PAP_ID = 3006;
    public static final String 	ERROR_PAYMENT_SAVE_PAP_MSG = "Remote exception thrown by calling on ConsumerPaymentManagement service - savePreAuthorizedPayment().";

    // EnterpriseBillingAccountsConsolidation Service error codes and descriptions
    public static final int 	ERROR_ENTR_BILL_ACC_CONSOLIDATION_SERVICE_CONNECTION_ID = 3007;
    public static final String 	ERROR_ENTR_BILL_ACC_CONSOLIDATION_SERVICE_CONNECTION_MSG = "Failed to initialize connection to EnterpriseBillingAccountsConsolidation service.";

    public static final int 	ERROR_ENTR_BILL_ACC_CONSOLIDATION_SERVICE_CONSOLIDATE_BILL_ACC_ID = 3008;
    public static final String 	ERROR_ENTR_BILL_ACC_CONSOLIDATION_SERVICE_CONSOLIDATE_BILL_ACC_MSG = "exception thrown by calling on EnterpriseBillingAccountsConsolidation service - consolidateBillingAccounts().";

    // ConsumerCustomerManagement Service error codes and descriptions
    public static final int 	ERROR_CONS_CUST_MGMT_SERVICE_CONNECTION_ID = 3009;
    public static final String 	ERROR_CONS_CUST_MGMT_SERVICE_CONNECTION_MSG = "Failed to initialize connection to ConsumerCustomerManagement service.";

    public static final int 	ERROR_CONS_CUST_MGMT_SERVICE_CREATECUSTOMER_ID = 3010;
    public static final String 	ERROR_CONS_CUST_MGMT_SERVICE_CREATECUSTOMER_MSG = "exception thrown by calling on ConsumerCustomerManagement service - createCustomer().";

    public static final int 	ERROR_CONS_CUST_MGMT_SERVICE_CONSOLIDATECUSTOMER_ID = 3011;
    public static final String 	ERROR_CONS_CUST_MGMT_SERVICE_CONSOLIDATECUSTOMER_MSG = "exception thrown by calling on ConsumerCustomerManagement service - consolidateCustomer().";

    public static final int 	ERROR_CONS_CUST_MGMT_SERVICE_GETFULLCUSTOMERINFO_ID = 3012;
    public static final String 	ERROR_CONS_CUST_MGMT_SERVICE_GETFULLCUSTOMERINFO_MSG = "exception thrown by calling on ConsumerCustomerManagement service - getFullCustomerInfo().";

    // ConsumerIdentityProfileManagement Service error codes and descriptions
    public static final int 	ERROR_CONS_IDENTITY_PROF_MGMT_SERVICE_CONNECTION_ID = 3013;
    public static final String 	ERROR_CONS_IDENTITY_PROF_MGMT_SERVICE_CONNECTION_MSG = "Failed to initialize connection to ConsumerIdentityProfileManagement service.";

    public static final int 	ERROR_CONS_IDENTITY_PROF_MGMT_SERVICE_UPDATEOWNERIDENTITYCREDENTIAL_ID = 3014;
    public static final String 	ERROR_CONS_IDENTITY_PROF_MGMT_SERVICE_UPDATEOWNERIDENTITYCREDENTIAL_MSG = "exception thrown by calling on ConsumerIdentityProfileManagement service - updateOwnerIdentityCredential().";

    public static final int 	ERROR_CONS_IDENTITY_PROF_MGMT_SERVICE_GETCURRENTOWNERIDENTITYCREDENTIALBYTYPE_ID = 3015;
    public static final String 	ERROR_CONS_IDENTITY_PROF_MGMT_SERVICE_GETCURRENTOWNERIDENTITYCREDENTIALBYTYPE_MSG = "exception thrown by calling on ConsumerIdentityProfileManagement service - getCurrentOwnerIdentityCredentialByType().";

    // EnterpriseCreditProfileManagement Service error codes and descriptions
    public static final int 	ERROR_ENTR_CREDIT_PROF_MGMT_SERVICE_CONNECTION_ID = 3016;
    public static final String 	ERROR_ENTR_CREDIT_PROF_MGMT_SERVICE_CONNECTION_MSG = "Failed to initialize connection to EnterpriseCreditProfileManagement service.";

    public static final int 	ERROR_ENTR_CREDIT_PROF_MGMT_SERVICE_UPDATECREDITPROFILE_ID = 3017;
    public static final String 	ERROR_ENTR_CREDIT_PROF_MGMT_SERVICE_UPDATECREDITPROFILE_MSG = "exception thrown by calling on EnterpriseCreditProfileManagement service - updateCreditProfile().";

    // WLN credit profile management service error codes and descriptions
    public static final int 	ERROR_WLNCREDITPROFILE_MGMT_SERVICE_CONNECTION_ID = 3018;
    public static final String 	ERROR_WLNCREDITPROFILE_MGMT_SERVICE_CONNECTION_MSG = "Failed to initialize connection to WLN credit profile management service.";

    public static final int 	ERROR_WLNCREDITPROFILE_MGMT_SERVICE_SEARCHCREDITPROFILEBYCREDITID_ID = 3019;
    public static final String 	ERROR_WLNCREDITPROFILE_MGMT_SERVICE_SEARCHCREDITPROFILEBYCREDITID_MSG = "exception thrown by calling on WLN credit profile management service - searchCreditProfileByCreditId().";

    // ConsumerBillingAccountManagement
    public static final int 	ERROR_CONS_BILL_ACC_MGMT_SERVICE_CONNECTION_ID = 3020;
    public static final String 	ERROR_CONS_BILL_ACC_MGMT_SERVICE_CONNECTION_MSG = "Failed to initialize connection to ConsumerBillingAccountManagement service.";

    public static final int 	ERROR_CONS_BILL_ACC_MGMT_SERVICE_CREATE_BILL_ACC_ID = 3021;
    public static final String 	ERROR_CONS_BILL_ACC_MGMT_SERVICE_CREATE_BILL_ACC_MSG = "exception thrown by calling on ConsumerBillingAccountManagement service - createBillingAccount().";

    // create customer
    public static final int 	ERROR_CREATE_CUST_REQ_MISSING_WLNUSTOMERINFO_ID = 3022;
    public static final String 	ERROR_CREATE_CUST_REQ_MISSING_WLNUSTOMERINFO_MSG = "Invalid request. Missing WirelineCustomerInfo.";

    public static final int 	ERROR_CREATE_CUST_REQ_MISSING_PAYMENTCREDITCARD_ID = 3023;
    public static final String 	ERROR_CREATE_CUST_REQ_MISSING_PAYMENTCREDITCARD_MSG = "Invalid request. Missing PaymentCreditCard.";

    //public static final int 	ERROR_CREATE_CUST_REQ_MISSING_PAYMENTCREDITCARD_INFO_ID = 3024
    public static final String 	ERROR_CREATE_CUST_REQ_MISSING_PAYMENTCREDITCARD_INFO_MSG = "Invalid request. PaymentCreditCard information in not present or incomplete.";

    public static final int 	ERROR_CREATE_CUST_REQ_MISSING_CONSUMERCREDITPROFILEINFO_ID = 3025;
    public static final String 	ERROR_CREATE_CUST_REQ_MISSING_CONSUMERCREDITPROFILEINFO_MSG = "Invalid request. Missing ConsumerCreditProfileInfo.";

    public static final int 	ERROR_CREATE_CUST_GET_FULL_CUSTOMER_MISSING_ID = 3026;
    public static final String 	ERROR_CREATE_CUST_GET_FULL_CUSTOMER_MISSING_MSG = "Operation getFullCustomer return no customer.";

    public static final int 	ERROR_CUSTOMER_PREFERED_LANG_ID = 3027;
    public static final String 	ERROR_CUSTOMER_PREFERED_LANG_MSG = "invalid preferred language code; expected en or fr.";

    // consolidate customer and billing account operation error codes
    public static final int 	ERROR_CREATE_CUST_REQ_BILLING_ADDRESS_TYPE_ID = 3028;
    public static final String 	ERROR_CREATE_CUST_REQ_BILLING_ADDRESS_TYPE_MSG = "Create customer billing address type is invalid. Does not expect any one of Z, S, E, T, or X";
    public static final String 	ERROR_CREATE_CUST_REQ_PROVINCE_OF_CURRENT_RESIDENCE_CD_MSG = "Create customer province of current residence code type is invalid.";

    public static final int 	ERROR_CONSOLIDATE_RETURN_INVALID_ID = 3029;
    public static final String 	ERROR_CONSOLIDATE_RETURN_INVALID_MSG = "enterprise billing account consolidate return null";

    public static final int 	ERROR_CONSOLIDATE_REQ_MISSING_CUSTOMER_ID = 3030;
    public static final String 	ERROR_CONSOLIDATE_REQ_MISSING_CUSTOMER_MSG = "Invalid request. Missing customer id.";

    public static final String CORRELATION_ID = "correlationid";
    public static final String APPLICATION_ID = "applicationid";
    public static final String CHANNELOUTLET_ID = "channeloutletid";
    public static final String SALESREP_ID = "salesrepid";
    //OMNI-CR-013
    public static final String EXTERNAL_SALESREP_ID = "extsalesrepid";

    public static final String LOOKUP_ONLY = "lookupOnly";

	// Register Credentials ejb error codes and descriptions
	public static final int 	ERROR_REGISTER_CREDENTIALS_EJB_CONNECTION_ID = 3031;
	public static final String 	ERROR_REGISTER_CREDENTIALS_EJB_CONNECTION_MSG = "Failed to initialize connection to Subscriber Identity Profile Ordering EJB service.";

	public static final int 	ERROR_REGISTER_CREDENTIALS_EJB_REGISTERCREDENTIALS_SERVICE_ID = 3032;
	public static final String 	ERROR_REGISTER_CREDENTIALS_EJB_REGISTERCREDENTIALS_SERVICE_MSG = "Call failed on Subscriber Identity Profile Ordering EJB service - registerCredentials().";

	public static final int 	ERROR_REGISTER_CREDENTIALS_INVALID_ORDER_TYPE_ID = 3033;
	public static final String 	ERROR_REGISTER_CREDENTIALS_INVALID_ORDER_TYPE_MSG = "Trying to register an invalid order type. Expected values are STV or HS.";

    public static final int 	ERROR_CONSOLIDATE_INVALID_PIN_ID = 3034;
    public static final String 	ERROR_CONSOLIDATE_INVALID_PIN_MSG = "PIN is invalid.";

    public static final int 	ERROR_CREATE_CUST_REQ_CUSTOMER_ID_ID = 3035;
    public static final String 	ERROR_CREATE_CUST_REQ_CUSTOMER_ID_MSG = "Create customer request was using an invalid value. Number expected.";

    public static final int 	ERROR_CREATE_CUST_UPDATE_OFFICIAL_ADDRESS_ID = 3036;
    public static final String 	ERROR_CREATE_CUST_UPDATE_OFFICIAL_ADDRESS_MSG = "Update customer offical address failed.";

    public static final int 	ERROR_CREATE_CUST_UPDATE_CONTACT_INDIVIDUAL_ID = 3037;
    public static final String 	ERROR_CREATE_CUST_UPDATE_CONTACT_INDIVIDUAL_MSG = "Update customer contact individual failed.";

    // WLNCreditProfileManagementProxy Service error codes and descriptions
    public static final int 	ERROR_WLNCREDITPROFILE_MGMT_PROXY_SERVICE_CONNECTION_ID = 3038;
    public static final String 	ERROR_WLNCREDITPROFILE_MGMT_PROXY_SERVICE_CONNECTION_MSG = "SalesMgmtSvc failed to initialize connection to WLN credit profile management proxy service.";
    public static final int		ERROR_WLNCREDITPROFILE_MGMT_PROXY_SERVICE_ASSESSCREDITWORTHINESS_ID = 3039;
    public static final String 	ERROR_WLNCREDITPROFILE_MGMT_PROXY_SERVICE_ASSESSCREDITWORTHINESS_MSG = "exception thrown by WLNCreditProfileManagementProxy service when calling operation assessCreditWorthiness().";

    // ConsumerCustomerManagement Service error codes and descriptions
    public static final int 	ERROR_CONS_PAY_MGMT_SERVICE_CONNECTION_ID = 3040;
    public static final String 	ERROR_CONS_PAY_MGMT_SERVICE_CONNECTION_MSG = "Failed to initialize connection to ConsumerPaymentManagement service.";

    //Added for OR3-187
    public static final int 	ERROR_CREATE_CUST_UPDATE_CUSTOMER_TASK_ID = 3041;
    public static final String 	ERROR_CREATE_CUST_UPDATE_CUSTOMER_TASK_MSG = "Update customer task failed.";

    // WLNCreditEligibilityProxy Service error codes and descriptions
    public static final int 	ERROR_WLNCREDIT_ELIGIBILITY_PROXY_SERVICE_CONNECTION_ID = 3042;
    public static final String 	ERROR_WLNCREDIT_ELIGIBILITY_PROXY_SERVICE_CONNECTION_MSG = "SalesMgmtSvc failed to initialize connection to WLN credit eligibility proxy service.";

    public static final int		ERROR_WLNCREDIT_ELIGIBILITY_PROXY_SERVICE_GETCREDITELIGIBILITY_ID = 3043;
    public static final String 	ERROR_WLNCREDIT_ELIGIBILITY_PROXY_SERVICE_GETCREDITELIGIBILITY_MSG = "exception thrown by WLNCreditEligibilityProxy service when calling operation getCreditEligibility().";

    public static final int 	ERROR_CONSOLIDATE_REQ_PROVIDE_CUSTOMER_ID = 3050;
    public static final String 	ERROR_CONSOLIDATE_REQ_PROVIDE_CUSTOMER_MSG = "Invalid request. Providing customer id for new customer.";

    public static final int 	ERROR_PROFILE_RESP_SERVICE_ID = 3051;
    public static final String 	ERROR_PROFILE_RESP_SERVICE_MSG = "create profile exception.";

    // CPMS
    public static final String 	ERROR_CPMS_GET_CHNLORGID_BY_OUTLETID_ID = "3052";
    public static final String 	ERROR_CPMS_GET_CHNLORGID_BY_OUTLETID_MSG = "Invalid channelOrganizationId. CPMS return empty chnlOrgId.";

    public static final String 	ERROR_CPMS_GET_CHNLORGTYPECODE_BY_OUTLETID_ID = "3053";
    public static final String 	ERROR_CPMS_GET_CHNLORGTYPECODE_BY_OUTLETID_MSG = "Invalid channelOrganizationTypeCode. CPMS return empty chnlOrgTypeCd.";

    public static final String 	ERROR_CPMS_GET_SALES_REP_INTERNAL_ID_BY_SALESREPID_ID = "3054";
    public static final String 	ERROR_CPMS_GET_SALES_REP_INTERNAL_ID_BY_SALESREPID_MSG = "Invalid salesRepInternalId. CPMS return empty salesRepId.";


    // SOA constants
	public static final String WLN_CREDIT_PROFILE_SVC_BK 				= "WLNCreditProfileManagementService_v1_1_vs0_BK";
	public static final String WLN_CREDIT_PROFILE_PROXY_SVC_BK    	    = "WLNCreditProfileManagementProxyService_v1_0_vs0_BK";
	public static final String WLN_CREDIT_PROFILE_SVC_LDAP_KEY 			= "WLNCreditProfileManagementServiceUrl";
	public static final String WLN_CREDIT_PROFILE_PROXY_SVC_LDAP_KEY 	= "WLNCreditProfileManagementProxyServiceUrl";
	public static final String ENTERPRISE_BILL_ACC_CONSOLIDATE_SVC_BK 	= "EnterpriseBillingAcctConsolidationSvc_v1_0_LAIRD_vs0_BK";
	public static final String ENTERPRISE_BILL_ACC_CONSOLIDATE_SVC_LDAP_KEY = "EnterpriseBillingAccountsConsolidationServiceUrl";
	public static final String CONSUMER_CUSTOMER_MGMT_SVC_BK 			= "ConsumerCustomerManagementService_v2_1_vs0_BK";
	public static final String CONSUMER_CUSTOMER_MGMT_SVC_LDAP_KEY 		= "ConsumerCustomerManagementServiceUrl";
	public static final String CONSUMER_IDENTITY_PROF_MGMT_SVC_BK		= "ConsumerIdentityProfileManagementService_v1_0_vs0_BK";
	public static final String CONSUMER_IDENTITY_PROF_MGMT_SVC_LDAP_KEY = "ConsumerIdentityProfileManagementServiceUrl";
	public static final String ENTERPRISE_CREDIT_PROF_MGMT_SVC_BK		= "EnterpriseCreditProfileManagementService_v1_1_LAIRD_vs0_BK";
	public static final String ENTERPRISE_CREDIT_PROF_MGMT_SVC_LDAP_KEY = "EnterpriseCreditProfileMgmtSvcUrl";
	public static final String CONSUMER_PAYMENT_MGMT_SVC_BK 			= "ConsumerPaymentManagementSvc_v1_0_vs0_BK";
	public static final String CONSUMER_PAYMENT_MGMT_SVC_LDAP_KEY 		= "ConsumerPaymentManagementServiceUrl";
	public static final String PRIMARY_SINGLE_LINE_PHONE_NUMBER_TYPE = "WLN_PRIMARY";
	public static final String CLEARANCE_INFO_COMPONENT_NAME = "clearanceInfo";
	public static final String PRIMARY_TELEPHONE_NUMBER_CHARACTERISTIC_NAME = "primaryTN";
	public static final String LEASED_LOOP_FLAG_CHARACTERISTIC_NAME = "leasedLoopFlag";
	public static final String CLEC_SHAW_CARRIER_ID  = "497E";
	/**
	 * define constant for enabler billing system id.
	 */
	public static final int SALES_CUSTOMER_ODS_ADDRESS_MASTER_ID = 1285;

	/**
	 * define constant for credential type code.
	 */
	public static final String PIN_CREDENTIAL_TYPE_CODE = "OWN_PIN";

	/**
	 * define constant for credential type code.
	 */
	public static final String CREDIT_RESTRICT_CODE = "R";

	/**
	 * define constant for account status closed.
	 */
	public static final String ACC_STATUS_CLOSED = "C";

	/**
	 * define constant for account status cancelled.
	 */
	public static final String ACC_STATUS_CANCELLED = "N";

	/**
	 * define constant for account status suspended.
	 */
	public static final String ACC_STATUS_CLOSED_ENABLER = "D";

	/**
	 * define constant for account status suspended.
	 */
	public static final String ACC_STATUS_INACTIVE = "I";

	/**
	 * define constant for account status suspended.
	 */
	public static final String ACC_STATUS_CLOSED_L = "L";

	/**
	 * define constant for account status open.
	 */
	public static final String ACC_STATUS_OPEN = "O";

	/**
	 * define constant for account status tentative.
	 */
	public static final String ACC_STATUS_TENTATIVE = "T";

	/**
	 * define constant for account status suspended.
	 */
	public static final String ACC_STATUS_SUSPENDED = "S";

	/**
	 * define constant for account status suspended.
	 */
	public static final String ACC_STATUS_ACTIVE = "A";

	public static final String EAS_SERVICES = "easServices";
	public static final String[] EJB_CONFIG_DN = { EAS_SERVICES, "vestaEAS" };

	public static final String[] SALES_SERVICES_CONFIG_DN = {"SalesServices"};

	public static final String[] EAS_CONFIG_DN = {EAS_SERVICES};

	public static final String COMPASS_ENVIRONMENT_CONFIG_DN = "compassEnv";

	public static final String COMPASS_ENVIRONMENTS_CONFIG_DN = "compassEnvironments";

	public static final String[] SALES_MANAGEMENT_CONFIG = {"SalesServices", "SalesManagementService"};

    public static final String CREDIT_CARD_TYPE_VISA = "VI";

    public static final String CREDIT_CARD_TYPE_MASTER = "MC";

    public static final String CREDIT_CARD_TYPE_AMEX = "AM";

	public static final String EMPTY = "";

	public static final String SPACE = " ";

	public static final String NEWLINE = "\n";

	// OMS related constants
	public static final int OMS_VESTA_APPLICATION_ID  = 1285;

	public static final String ADDRESS_TYPE_CREDIT_BUREAU = "CB";

	public static final String POLICY_EXCEPTION = "Policy";
	public static final String SERVICE_EXCEPTION = "Service";
	public static final String REMOTE_EXCEPTION = "Remote";

	public static final String CREDIT_ASSESSMENT_TYPE_CD = "FULL_ASSESSMENT";
	public static final String CREDIT_ASSESSMENT_SUBTYPE_CD = "AUTO_ASSESSMENT";

	//validate service
	 public static final String OMS_PRODUCT_HSIC = "HSIC";
	 public static final String OMS_PRODUCT_TTV="TTV";
	 public static final String OMS_PRODUCT_SING="SING";

	 // Sales product line
	 /** The Constant SALES_PRODUCT_LINE_HOME_PHONE. */
	 public static final String SALES_PRODUCT_LINE_HOME_PHONE = "Home Phone";

	 /** The Constant SALES_PRODUCT_LINE_TTV. */
	 public static final String SALES_PRODUCT_LINE_TTV = "TELUS TV";

	 /** The Constant SALES_PRODUCT_LINE_HIGH_SPEED. */
	 public static final String SALES_PRODUCT_LINE_HIGH_SPEED = "High Speed Internet";

	 /** The Constant HSIC_PACKS_SERVICE_CODE. */
		public static final String HSIC_PACKS_SERVICE_CODE = "PCKS";

		/** The Constant HSIC_HIGH_SPEED_PACK_SERVICE_CODE. */
		public static final String HSIC_HIGH_SPEED_PACK_SERVICE_CODE = "ADSP";

		//for product category table
		public static final String PRODUCT_CATEGORY_ID_FOR_PARENT_CALLING_FEATURE = "CF";
		public static final String PRODUCT_CATEGORY_ID_FOR_CALLING_FEATURE = "CFCALLING";
		public static final String PRODUCT_CATEGORY_ID_FOR_VOICE_MAIL_FEATURE = "CFVOICEMAIL";
		public static final String PRODUCT_CATEGORY_ID_FOR_OTHER_FEATURE = "CFOTHER";
		public static final String PRODUCT_CATEGORY_ID_FOR_PRIVACY_FEATURE = "PF";
		public static final String PRODUCT_CATEGORY_ID_FOR_LONG_DISTANCE = "LD";
		public static final String PRODUCT_CATEGORY_ID_FOR_PRICE_PLAN = "PP";

		//Batch - Upgrade indicator
		public static final String UPGRADE_NEW = "new";
		public static final String UPGRADE_UPGRADE = "upgrade";
		public static final String BOOKED = "Booked";
		public static final String NOT_BOOKED = "Not Booked";
		public static final String TERM_CODE_MTM = "MTM";
		//display value for commission batch
		public static final String TERM_CODE_VALUE = "0";

	    // Flags
	    public static final String FLAG_TRUE = "true";
	    public static final String FLAG_FALSE = "false";
	    public static final String FLAG_YES = "Y";
	    public static final String FLAG_NO = "N";
	    public static final String SET_YES = "Yes";
	    public static final String SET_NO = "No";


		// for date utility
	    public static final String FORMAT_SHORT_DATE = "yyyy-MM-dd";
	    public static final String FORMAT_SHORT_DATE_AND_TIME = "yyyy-MM-dd HH:mm";
	    public static final String FORMAT_SHORT_DATE_AND_TIME_SEC = "yyyy-MM-dd HH:mm:ss";
	    public static final String FORMAT_SHORT_DATE_AND_TIME_MIL = "yyyyMMddHHmmssS";
	    public static final int OVERRIDE_ACTIVE_MINUTES = 60;
	    public static final String FORMAT_REPORT_DATE = "MM/dd";
	    public static final String FORMAT_DATE = "MM/dd/yyyy";


	    // account types value
		public static final String ACCOUNT_TYPE_CONSUMER = "CONSUMER";
		public static final String ACCOUNT_TYPE_BUSINESS = "BUSINESS";

		// credit check type
		public static final String PERSONAL_CREDIT_CHECK = "PERSONAL";
		public static final String BUSINESS_CREDIT_CHECK = "BUSINESS";


		// order status value
		public static final String ORDER_STATUS_UNASSIGN = "UNASSIGN";
		public static final String ORDER_STATUS_ASSIGN = "ASSIGN";
		public static final String ORDER_STATUS_CANCELLED = "CANCEL";
		public static final String ORDER_STATUS_PROCESS_SUCCESSFUL = "SUCCESS";
		public static final String ORDER_STATUS_PROCESS_UNSUCCESSFUL = "FAILED";
		public static final String ORDER_STATUS_PROCESS_PENDING = "PENDING";
		public static final String ORDER_STATUS_PROCESS_PREPARED = "PREPARED";

		public static final String SESSION_SELECTED_OUTLETID = "SELECTED_OUTLETID";

		// Installation Type
		public static final String FIELD_WORK = "FW";
		public static final String RACK_WORK = "RW";
		public static final String SOFTWARE = "SW";

		// Installing Party: "Dealer Install", "Telus Install"
		// Changed to "TELUS", "Retailer"
		public static final String TELUS_INSTALL = TELUS;
		public static final String DEALER_INSTALL = "Retailer";
		public static final String OUTLET_SELF_INSTALL = "Outlet_Self_Install";
		public static final String TELUS_INSTALL_FR = TELUS;
		public static final String DEALER_INSTALL_FR = "FR-Retailer";
		public static final String OUTLET_SELF_INSTALL_FR = "FR-Outlet_Self_Install";

		//Installation Work Bucket
		//GWT should be replaced with more meaningful name.
		public static final String GWT = "GWT";
		public static final String FWT= "FWT"; //consumer ETTS order CRS052
		public static final String BUSINESS_WORK_TIME = "BWT";
		public static final String STANDARD_WORK_TIME = "SWT";
		public static final String STV_WORK_TIME = "TWT";

		//table credit_card for salamander
		public static final int PURPOSE_TYPE_BILLING = 1;
		public static final int PURPOSE_TYPE_DELINQUENCY = 2;

		public static final int ADDRESS_UNIT_SIZE = 16;
		public static final int TITLE_SIZE = 5;

		// Language setting
		public static final String LANG_EN = "EN";
		public static final String LANG_FR = "FR";

		public static final String SESSION_SOURCE_LANGUAGE = "LANGUAGE";

		public static final String CUSTOMER_ODS_PRODUCT_TYPE_SL = "SING";
		public static final String CUSTOMER_ODS_PRODUCT_TYPE_TTV = "TTV";
		public static final String CUSTOMER_ODS_PRODUCT_TYPE_HS = "HSIC";
		public static final String CUSTOMER_ODS_PRODUCT_TYPE_STV = "STV";

		public static final String SESSION_CHANNEL_LANGUAGE = "LANGUAGE";


		// order comment type
		public static final String ORDER_COMMENT_SYSTEM_GENERATED = "S";
		public static final String ORDER_COMMENT_USER_GENERATED = "U";
		public static final String ORDER_COMMENT_OMS_GENERATED = "O";
		public static final String ORDER_COMMENT_ADDITIONAL = "A";
		public static final String ORDER_COMMENT_PROMOTION = "P";
		public static final String ORDER_COMMENT_COMMISSION = "C";
		public static final String SYSTEM_COMMENT_NEW_ORDER = "New Unassign Order";
		public static final String SYSTEM_COMMENT_CHANGE_STATUS = "Order Status Changed to ";

		// product types constants
		public static final String PRODUCT_TYPE_LL = "LL";
		public static final String PRODUCT_TYPE_TTV = "TTV";
		public static final String PRODUCT_TYPE_HDTV = "HDTV";
		public static final String PRODUCT_TYPE_IPTV = "IPTV";

		public static final String PRODUCT_CATEGORY_ID_FOR_PARENT_EQUIPMENT = "EQUIPMENT";
		public static final String PRODUCT_CATEGORY_ID_FOR_EQUIPMENT = "EQUIPMENT";
		public static final String PRODUCT_CATEGORY_ID_FOR_TTV = "TTV";
		public static final String PRODUCT_CATEGORY_ID_FOR_TTV_PACK = "TVPACKS";
		public static final String PRODUCT_CATEGORY_ID_FOR_HSIA = "HSIA";

		public static final String PRODUCT_CATEGORY_ID_FOR_STV = "STV";
		public static final String PRODUCT_CATEGORY_ID_FOR_SL = "LL";
		public static final String PRODUCT_CATEGORY_ID_FOR_CF_PACK = "CFPACKS";

		// product installation lag time constants, product type is passed in ApplicationProperties.java
		public static final String[] PRODUCT_INSTALLATION_LAG_TIME_CONFIG_XN = new String[] {"orderInstallationLagTimesSetting","","lagTime"};

		//env
		public static final String PCI_PARAMETERS = "pciParameters";
		public static final String[] ORDER_SEARCH_RESULTSET_LIMIT_MAX_CONFIG_XN = new String[] {"orderSearchResultSetSetting","max"};
		public static final String[] VESTA_EXPIRY_HOURS_SETTING_CONFIG_XN = new String[] {"vestaExpiryHoursSetting","hours"};
		public static final String[] PCI_USE_LOCAL_CERTIFICATE = new String[] {PCI_PARAMETERS,"useLocalCertificate"};
		public static final String[] PCI_LOCAL_CERTIFICATE = new String[] {PCI_PARAMETERS,"localCertificate"};
		public static final String[] PCI_APPLICATION_ID = new String[] {PCI_PARAMETERS,"applicationId"};
		public static final String[] PCI_ENCRYPT_USER = new String[] {PCI_PARAMETERS,"encryptUser"};
		public static final String[] PCI_ENCRYPT_PASSWORD = new String[] {PCI_PARAMETERS,"encryptPassword"};
		public static final String[] PCI_ENCRYPT_URL = new String[] {"connections","webServices","avalonEncryptionSvc","wsdlUrl"};
		public static final String[] PCI_DECRYPT_USER = new String[] {PCI_PARAMETERS,"decryptUser"};
		public static final String[] PCI_DECRYPT_PASSWORD = new String[] {PCI_PARAMETERS,"decryptPassword"};
		public static final String[] PCI_DECRYPT_URL = new String[] {"connections","webServices","avalonDecryptionSvc","wsdlUrl"};
		public static final String[] OMS_ORDER_REMAKR_LAN_SETTING = new String[] {"omsOrderRemarkLanguageSetting","lang"};
		public static final String[] OMS_TIMEZONE_SETTING = new String[] {"omsSetting","timezone"};
		public static final String[] CPMS_EAS_CONFIG_DN = new String[] {EAS_SERVICES,"cpmsEAS"};
		public static final String[] VESTA_EAS_CONFIG_DN = new String[] {EAS_SERVICES,"vestaEAS"};
		public static final String[] TMODS_APPS_EAS_CONFIG_DN = new String[] {EAS_SERVICES,"TMODSAPPSEAS"};
		public static final String[] TMODS_EAS_CONFIG_DN = new String[] {EAS_SERVICES,"tmodsEAS"};

		//for CPMS Sell ADSL Channel CategoryId value
		//public static String SELL_ADSL_CHANNEL_CATEGORY_ID = "SELL_ADSL_CHANNEL_CATEGORY_ID"; - LDAP
		public static final String[] VESTA_SELL_ADSL_CONFIG_NAME = {"CPMS","sellADSLChannelCategoryId"};

		public static final String CALLING_FEATURES = "callingFeatures";

		public static final String REMOVE_ME_FROM_MAILING_LIST = "removeMeFromMaillingList";
		public static final String[] CALLING_FEATURES_REMOVEME_NAME = new String[] {CALLING_FEATURES,REMOVE_ME_FROM_MAILING_LIST, "name"};
		public static final String[] CALLING_FEATURES_REMOVEME_NAME_FR = new String[] {CALLING_FEATURES,REMOVE_ME_FROM_MAILING_LIST, "nameFr"};
		public static final String[] CALLING_FEATURES_REMOVEME_DESCRIPTION = new String[] {CALLING_FEATURES,REMOVE_ME_FROM_MAILING_LIST, "description"};
		public static final String[] CALLING_FEATURES_REMOVEME_DESCRIPTION_FR = new String[] {CALLING_FEATURES,REMOVE_ME_FROM_MAILING_LIST, "descriptionFr"};
		public static final String[] CALLING_FEATURES_REMOVEME_MARKET_DESCRIPTION = new String[] {CALLING_FEATURES,REMOVE_ME_FROM_MAILING_LIST, "marketDefinitionDscription"};
		public static final String[] CALLING_FEATURES_REMOVEME_MARKET_DESCRIPTION_FR = new String[] {CALLING_FEATURES,REMOVE_ME_FROM_MAILING_LIST, "marketDefinitionDscriptionFr"};

		public static final String UNLISTED_PHONE_NUMBER = "unlistedPhoneNumber";
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_NAME = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "name"};
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_OFFERID = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "offerId"};
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_NAME_FR = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "nameFr"};
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_DESCRIPTION = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "description"};
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_DESCRIPTION_FR = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "descriptionFr"};
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_MARKET_DESCRIPTION = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "marketDefinitionDscription"};
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_MARKET_DESCRIPTION_FR = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "marketDefinitionDscriptionFr"};
		public static final String[] CALLING_FEATURES_UNLIST_PHONE_NUMBER_ADD_ON_PRICE = new String[] {CALLING_FEATURES,UNLISTED_PHONE_NUMBER, "addOnPrice"};


	    public static final int ORDER_STATUS_UNASSIGN_ID = 1;
	    public static final int ORDER_STATUS_ASSIGN_ID = 2;
	    public static final int ORDER_STATUS_CANCELLED_ID = 3;
	    public static final int ORDER_STATUS_PROCESS_SUCCESSFUL_ID = 4;
	    public static final int ORDER_STATUS_PROCESS_UNSUCCESSFUL_ID = 5;
	    public static final int ORDER_STATUS_PROCESS_PENDING_ID = 6;

		public static final String DASH = "-";

		/**
		 * const to put external parameters from viewSubmitted/viewTentative order
		 */
		public static final String SESSION_EXTRERNAL_PARM = "EXTERNAL_PARM";
		public static final String SESSION_CHANNEL_CHNLORGTYPECODE = "CHNLORGTYPECODE";
		public static final String SESSION_SELECTED_OUTLET_CD = "SELECTED_OUTLET_CD";
		public static final String SESSION_CHANNEL_RETAIL = "RT";
		public static final String SESSION_CHANNEL_DEALER = "DL";
		public static final String SESSION_CHANNEL_XDELER = "XDL";
		public static final String SESSION_SELECTED_OUTLET_NAME = "SELECTED_OUTLET_NAME";
		public static final String SESSION_CHANNEL_WBS = "CBCC";
		public static final String SESSION_CHANNEL_CORPSTORE = "KI";
		public static final String SESSION_SELECTED_OUTLET_NCCS_CODE = "SESSION_SELECTED_OUTLET_NCCS_CODE";
		public static final String SESSION_CHANNEL_SALESREPID = "SALESREPID";
		public static final String SESSION_NEXT_STEP = "nextToCloseBrowser";
		public static final String SESSION_OMS_SUBMISSION_ATTR_NAME = "completeSaleForWireline";
		public static final String SESSION_OMS_SUBMISSION_MANUAL = "WLNManualOrder";
		public static final String SESSION_OMS_SUBMISSION_ORDER_ID = "WLNOrderID";
		public static final String SESSION_OMS_SUBMISSION_POSREQUEST="WLNPOSREQUEST";

		public static final String SESSION_SELECTED_PARTNER_INFO = "SELECTED_PARTNER_INFO";
	    public static final String INCOMPLETE_ORDER_PARM_FIRST_NAME = "FIRSTNAME";
	    public static final String INCOMPLETE_ORDER_PARM_LAST_NAME = "LASTNAME";
	    public static final String INCOMPLETE_ORDER_PARM_BAN = "BAN";

		//for May release order detail
		public static final String ACQUISITION_TYPE_ID_FOR_PURCHASE = "1";
		public static final String ACQUISITION_TYPE_ID_FOR_RENT = "2";
		public static final String ACQUISITION_TYPE_ID_FOR_OWNED = "3";


		public static final String LOOKUP_PROVINCE ="LOOKUP_PROVINCE";
		public static final String LOOKUP_SERVICE_PROVIDER = "LOOKUP_SERVICE_PROVIDER";
		public static final String LOOKUP_DELIVERY_METHOD ="LOOKUP_DELIVERY_METHOD";
		public static final String LOOKUP_ACQUISITION_TYPE ="LOOKUP_ACQUISITION_TYPE";

		public static final String CALLING_FEATURE_ID_FOR_UNLISTED_PHONE_NUMBER = "999";
		public static final String CALLING_FEATURE_ID_FOR_REMOVE_ME_FROM_MAILING_LIST = "888";

		//message key prefixes
		public static final String MESSAGE_KEY_PREFIX_TITLE="title_";
		public static final String MESSAGE_KEY_PREFIX_ADDRESS_UNIT_TYPE="address_unit_";
		public static final String MESSAGE_KEY_PREFIX_PAYMENT_METHOD="payment_method_";

		//Error Code
		public static final String ERROR_CODE = "error_code";
		public static final String ERROR_MESSAGE = "error_message";
		public static final String ERROR_EXCEPTION= "error_exception";
		public static final String WLN_INVD_COMP = "wln.invd.comp";
		public static final String WLN_PART_INELIG = "wln.part.inelig";
		public static final String WLN_ALL_INELIG= "wln.all.inelig";
		public static final String WLN_APP_ERR= "wln.app.err";
		public static final String WLN_SYS_ERR= "wln.sys.err";
		public static final String WLN_APP_WARN= "wln.app.warn";
		public static final String NEXT_IGNORE_ERROR = "NEXT_IGNORE_ERROR";


	    // new or updat product
	    public static final String ACTION_TYPE_FOR_UPDATE = "PROVIDE";

	    // Available address type
	    public static final String ADDRESS_UNKNOWN = "UNKNOWN";
	    public static final String ADDRESS_SUGGESTED = "SUGGESTED";
	    public static final String ADDRESS_ENTERED = "ENTERED";

	    // for contact info.
	    public static final String CONTACT_TYPE_CUSTOMER = "CU";
	    public static final String CONTACT_CATEGORY_SALES = "SA";
	    public static final String CONTACT_TYPE_CODE_PH = "PH";
	    public static final String CONTACT_SUBTYPE_CODE_DYPH = "DYPH";
	    public static final String CONTACT_SUBTYPE_CODE_WKPH = "WKPH";
	    public static final String CONTACT_SUBTYPE_CODE_EVPH = "EVPH";
	    public static final String CONTACT_TYPE_CODE_CL = "CL";
	    public static final String CONTACT_SUBTYPE_CODE_CLPH = "CLPH";
	    public static final String CONTACT_PREFERRED_METHOD_EMAIL = "EM";
	    public static final String CONTACT_PREFERRED_METHOD_PHONE = "PH";
	    public static final String CONTACT_PREFERRED_METHOD_MOBILE = "MO";
	    public static final String CONTACT_ELECTRONIC_ADDRESS_TYPE_CODE_E = "E";


	    // public static final String OFFER_EPP_PROPERTY = "EPP"
//	    public static final String OFFER_DEFAULT_PASSWD = "DEFAULT_PASSWORD_TXT"
	    public static final String OFFER_LISTING_TYPE = "ListingType";
	    public static final String OFFER_UNLISTING_OFFERID = "UnlistedOffer";
	    public static final String OFFER_ADDITIONAL_OFFERID = "RemoveMailingListOffer";

	    public static final String OFFER_ACQUISITIONTYPE = "ACQUISITION_TYPE";
		public static final String OFFER_DELIVERTYPE = "DELIVERY_METHOD";
		public static final String OFFER_PROPERTY_UNLISTED = "unlisted";
		public static final String OFFER_PROPERTY_ADDITIONALLIST = "Additional Unlisted";
		public static final String OFFER_PPROMOTION_CODE ="EQUIPMENT_PURCHASE_PROMOTION";

	    public static final String TELUS_BRAND_ID = "1";
	    public static final String SYSTEM_CODE = "VESTA";

	    //submitTransactionToPOS
	    public static final String SUBMIT_TRANSACTION_POS_APP_ID = "11";
	    public static final String SUBMIT_TRANSACTION_POS_APP_TRANS_ID = "0";
	    public static final String SUBMIT_TRANSACTION_POS_LINE_TYPE = "SALES";
	    public static final String SUBMIT_TRANSACTION_POS_SERVICE_CODE = "FFH_ORDER";
	    public static final double SUBMIT_TRANSACTION_POS_AMOUNT = 0;

		//order status updating
		public static final String STATUS_CHANGE_TEXT_EN = "This order was updated from Prepared to Pending";
		public static final String STATUS_CHANGE_TEXT_FR = "Cette ordonnance a été mis à jour à partir préparée à l'attente de";

		//contact preference
		public static final String CONTACT_PREFERENCE_1 = "8-12";
		public static final String CONTACT_PREFERENCE_2 = "12-17";
		public static final String CONTACT_PREFERENCE_3 = "17-21";

		//For TTV questionnaire page
		public static final int TTV_QUESION_PHONE_NUM_DISPLAY_ORDER_ID = 19;
		public static final Integer[] TTV_QUESION_FREEFORM_FIELDS_DISPLAY_ORDER_ID =
			new Integer[]{4, 5, 6, 7};

		public static final int TTV_QUESION_CONCIERGE_YES_NO_DISPLAY_ORDER_ID = 3;
		public static final int TTV_QUESION_CONTACT_NAME_DISPLAY_ORDER_ID = 4;
		public static final int TTV_QUESION_CONCIERGE_CONTACT_NUM_DISPLAY_ORDER_ID = 5;
		public static final int TTV_QUESION_MAIN_CONTACT_NUM_DISPLAY_ORDER_ID = 6;
		public static final int TTV_QUESION_ALTERNATE_CONTACT_NUM_DISPLAY_ORDER_ID = 7;

		public static final Long ID_1 = Long.valueOf(1L);
		public static final Long ID_2 = Long.valueOf(2L);
		public static final Long ID_3 = Long.valueOf(3L);

		public static final String ORDER_REMARK_TYPE_FF = "FF";
		public static final String ORDER_REMARK_CONTACT_ID_UNKNOWN = "unknown";

		//saving comments
		public static final String STATUS_ADD_TEXT_1 = "new ";
		public static final String STATUS_ADD_TEXT_2 = " order. ";
		public static final String STATUS_ADD_TEXT_FR_1 = "autre ";
		public static final String STATUS_ADD_TEXT_FR_2 = " commande. ";
		public static final String STATUS_CHANGE_TEXT_1 = "This order was updated from ";
		public static final String STATUS_CHANGE_TEXT_2 = " to ";
		public static final String STATUS_CHANGE_TEXT_FR_1 = "Le statut de cette commande est passé de  ";
		public static final String STATUS_CHANGE_TEXT_FR_2 = " à ";
		public static final String COMMENT_SPECIAL_LABEL_BILLING_OPTION ="Billing Option:";
		public static final String COMMENT_SPECIAL_LABEL_BILLING_OPTION_FR ="FRBilling Option:";
		public static final String COMMENT_SPECIAL_LABEL_BOOKING_ID = "Booking ID:";
		public static final String COMMENT_SPECIAL_LABEL_BOOKING_ID_FR = "FRBooking ID:";
		public static final String COMMENT_SPECIAL_LABEL_SERVICE_INSTALL_DATE = "Service Install Date:";
		public static final String COMMENT_SPECIAL_LABEL_SERVICE_INSTALL_DATE_FR = "FRService Install Date:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_DATE = "Requested Install Date:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_DATE_FR = "FRRequested Install Date:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_TYPE = "Installation Type:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_TYPE_FR = "FRInstallation Type:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_WORK_TIME = "Work Time:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_WORK_TIME_FR = "FRWork Time:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_PARTY = "Installing Party:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_PARTY_FR = "FRInstalling Party:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_TIME = "Install Time:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_TIME_FR = "FRInstall Time:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_STATUS = "Status:";
		public static final String COMMENT_SPECIAL_LABEL_REQUESTED_INSTALL_STATUS_FR = "FRStatus:";
		public static final String COMMENT_SPECIAL_LABEL_PROMOTION = "Promotion ID:";
		public static final String COMMENT_SPECIAL_LABEL_PROMOTION_FR = "FRPromotion ID:";
		public static final String COMMENT_SPECIAL_LABEL_PROMOTION_CODE = "Promotion Code:";
		public static final String COMMENT_SPECIAL_LABEL_PROMOTION_DESCRIPTION = "Promotion Description:";
		public static final String COMMENT_SPECIAL_LABEL_PROMOTION_CODE_FR = "FRPromotion Code:";
		public static final String COMMENT_SPECIAL_LABEL_PROMOTION_DESCRIPTION_FR = "FRPromotion Description:";
		public static final String COMMENT_SPECIAL_LABEL_OFFER_EXTERNAL_ID = "Offer External ID:";
		public static final String COMMENT_SPECIAL_LABEL_OFFER_EXTERNAL_ID_FR = "FROffer External ID:";
		public static final String COMMENT_SPECIAL_LABEL_PRODUCT_EXTERNAL_ID = "Product External ID:";
		public static final String COMMENT_SPECIAL_LABEL_PRODUCT_EXTERNAL_ID_FR = "FRProduct External ID:";
		public static final String COMMENT_SPECIAL_LABEL_PRODUCT_TYPE = "Product Type:";
		public static final String COMMENT_SPECIAL_LABEL_PRODUCT_TYPE_FR = "FRProduct Type:";
		public static final String COMMENT_SPECIAL_LABEL_MONTHLY_RECURRING_CHARGE = "Monthly Recurring Charge:";
		public static final String COMMENT_SPECIAL_LABEL_MONTHLY_RECURRING_CHARGE_FR = "FRMonthly Recurring Charge:";
		public static final String COMMENT_SPECIAL_LABEL_TERM = "Term:";
		public static final String COMMENT_SPECIAL_LABEL_TERM_FR = "FRTerm:";
		public static final String COMMENT_SPECIAL_LABEL_ONE_TIME_CHARGE = "One Time Charge:";
		public static final String COMMENT_SPECIAL_LABEL_ONE_TIME_CHARGE_FR = "FROne Time Charge:";
		public static final String COMMENT_SPECIAL_LABEL_CANCELLATION_FEE_MINIMUM_AMOUNT = "Cancellation Fee Minimum Amount:";
		public static final String COMMENT_SPECIAL_LABEL_CANCELLATION_FEE_MINIMUM_AMOUNT_FR = "FRCancellation Fee Minimum Amount:";
		public static final String COMMENT_SPECIAL_LABEL_CANCELLATION_FEE_MONTHLY_AMOUNT = "Cancellation Fee Monthly Amount:";
		public static final String COMMENT_SPECIAL_LABEL_CANCELLATION_FEE_MONTHLY_AMOUNT_FR = "FRCancellation Fee Monthly Amount:";
		public static final String COMMENT_SPECIAL_LABEL_TOP_LEVEL_OFFER = "Top Level Offer ID:";
		public static final String COMMENT_SPECIAL_LABEL_TOP_LEVEL_OFFER_FR = "FRTop Level Offer ID:";

		public static final String BILLING_OPTION_EBILL = "eBill";
		public static final String BILLING_OPTION_EBILL_FR = "FReBill";
		public static final String BILLING_OPTION_PAPER_BILL = "Paper Bill";
		public static final String BILLING_OPTION_PAPER_BILL_FR = "FRPaper Bill";
		public static final String UNASSIGN = "Unassigned";
		public static final String ASSIGN = "Assigned";
		public static final String CANCEL = "Cancelled";
		public static final String SUCCESS = "Successful";
		public static final String UNSUCCESS = "Unsuccessful";
		public static final String PENDING = "Pending";
		public static final String PREPARED = "Prepared";

		public static final String UNASSIGN_FR = "Non attribuée";
		public static final String ASSIGN_FR = "Attribuée";
		public static final String CANCEL_FR = "Annulée";
		public static final String SUCCESS_FR = "Acceptée";
		public static final String UNSUCCESS_FR = "Non acceptée";
		public static final String PENDING_FR = "attente";
		public static final String PREPARED_FR = "préparé";


		public static final String WIRELINE_DEFAULT_USER = "wirelinesales";
		public static final String CREDIT_VALUE_D = "D";
		public static final String CREDIT_VALUE_RESTRICTED = "R";
		public static final String CREDIT_VALUE_CODE_UNESTABLISHED = "U";
		public static final String CREDIT_VALUE_NONE = "N";

		public static final String OUTLET_LOCATION_DEFAULT_FOR_NULL = "Some value from the session";
		public static final String OUTLET_LOCATION_DEFAULT_FOR_DEALER = "Some outlet ID";
		public static final String OUTLET_LOCATION_DEFAULT_FOR_RETAILER = "A retailer with its CD plus its name";


		public static final String DESTROY_ORDER = "destroyOrder";

		public static final String UNKNOWN = "unknown";
		public static final String COMMISSION_HW = "LANDLINE_HW";
		public static final String COMMISSION_PROMO = "LANDLINE_PROMO";
		public static final String COMMISSION_ENROLL = "LANDLINE_ENROLL";
		public static final String COMMISSION_BDA = "LANDLINE_BONUS";
		public static final String COMMISSION_DEPOSITE = "LANDLINE_DP";

		public static final String COMMISSION_TYPE_HW = "HW";
		public static final String COMMISSION_TYPE_PROMO = "PROMO";
		public static final String COMMISSION_TYPE_ENROLL = "ENROLL";
		public static final String COMMISSION_TYPE_BDA = "BDA";


		public static final String SERVICE_TYPE_CALLING_FEATURE_PACK = "SFPK";
		public static final String SERVICE_TYPE_HIGH_SPEED_PACK = "ADSP";
		public static final String COUNTRY_CANADA_SHORT = "CAN";
		public static final String COUNTRY_CANADA_LONG = "CANADA";

		//reference pds code

		public static final String SSM_ID_NAME = "SSMSessionID";
		public static final String SSM_VER_NAME = "SSMVersion";
	    public static final String ALL_DAY_WINDOW = "8 am - 8 pm";

	    //hard-code order submission response XML
	    public static final String SUBMISSION_RESPONSE_CONTENT_TYPE = "application/xml";
	    public static final String SUBMISSION_RESPONSE_CHARACTER_ENCODING = "UTF-8";
	    public static final String SUBMISSION_RESPONSE_OPEN_TAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns:OrderCompletionResponse xsi:schemaLocation=\"http://www.example.org/OrderCompletionResponse Untitled2.xsd\" xmlns:tns=\"http://www.example.org/OrderCompletionResponse\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";

	    public static final String SUBMISSION_RESPONSE_CONTENT = "<tns:response><tns:item>#######1#</tns:item><tns:status>#######2#</tns:status></tns:response>";
	    public static final String SUBMISSION_RESPONSE_CLOSE_TAG = "</tns:OrderCompletionResponse>";
	    public static final String SUBMISSION_RESPONSE_CONTENT_PLACEHOLDER = "#######1#";
	    public static final String SUBMISSION_RESPONSE_STATUS_PLACEHOLDER = "#######2#";
	    public static final String SUBMISSION_RESPONSE_STATUS="SUCCESS";
	    public static final String SUBMISSION_RESPONSE_STATUS_ERROR="FAIL";
	    public static final String SUBMISSION_RESPONSE_STATUS_TIMEOUT="TIMEOUT";

	    //for outlet category
	    public static final String ORG_LEVEL_OUTLET = "Outlet";
	    public static final String ADSL_LEVEL_OUTLET = "Sell ADSL-Outlet";

	    // Booking Status (Reserve Due Date)
	    public static final String BOOKING_STATUS_SUCCESSFUL = "SUCCESSFUL";
	    public static final String BOOKING_STATUS_UNSUCCESSFUL = "UNSUCESSFUL";
	    public static final String BOOKING_STATUS_NOT_NEEDED = "NOTNEEDED";

	    // Due Date Install Status
	    // The constant values below (e.g. "0", "1", etc) coincides with the index number of the INSTALL_STATUS
	    public static final String INSTALL_STATUS_SCHEDULED = "0";
	    public static final String INSTALL_STATUS_SYSTEM_UNAVAILABLE = "1";
	    public static final String INSTALL_STATUS_DATES_UNAVAILABLE = "2";
	    public static final String INSTALL_STATUS_NO_SELECT = "3";
	    public static final String INSTALL_STATUS_WRONG_ADDRESS = "4";
	    public static final String INSTALL_STATUS_NOT_REQUIRED = "5"; // equivalent Booking not needed status
	    public static final String INSTALL_STATUS_NO_NEED_TO_BOOK = "6";
	    public static final String[] INSTALL_STATUS = new String[]{"Due date scheduled", "No Installation selected - Due Date System unavailable", "No Installation selected - No available dates found", "No Installation selected - Appointment not selected by user", "No Installation selected - Service address not fully configured", "No field work installation required"};
		public static final String[] INSTALL_STATUS_FR = new String[]{"FRDue date scheduled", "FRNo Installation selected - Due Date System unavailable", "FRNo Installation selected - No available dates found", "FRNo Installation selected - Appointment not selected by user", "FRNo Installation selected - Service address not fully configured", "FRNo field work installation required"};

		public static final String NOT_APPLICABLE = "N/A";

	    //saving order
	    public static final String ORDER_ITEM_DISCRIMINATION_CD_PRODUCT = "SPC";
	    public static final String ORDER_ITEM_DISCRIMINATION_CD_ORDER_ITEM = "CMP";
	    public static final String ACTUAL_ITEM_ORDERED_INDICATOR_TRUE = "Y";
	    public static final String ACTUAL_ITEM_ORDERED_INDICATOR_FALSE = "N";
	    public static final String PROVISION_ONLY_INDICATOR_TRUE = "Y";
	    public static final String PROVISION_ONLY_INDICATOR_FALSE = "N";
	    public static final String PRODUCT_TYPE_CD_MARKET_OFFER = "MKTOFFER";
	    public static final String PRODUCT_TYPE_CD_OFFER_DEFINITION = "OFFERDEF";
	    public static final String PRODUCT_TYPE_CD_PRODUCT_DEFINITION = "PRODDEF";
	    public static final String PRODUCT_TYPE_CD_PRODUCT = "PRODUCT";
	    public static final String PRODUCT_TYPE_CD_EQUIPMENT = "EQPMNT";
	    public static final String PRODUCT_TYPE_CD_SWEETENER = "SWTOFFER";
	    public static final String PRODUCT_TYPE_CD_PRICE_PLAN = "PPLAN";
	    public static final String PRODUCT_TYPE_CD_PROMOTION = "PROMO";
	    public static final String PRODUCT_TYPE_CD_ORDER_ITEM = "ORDITEM";
	    public static final String PRODUCT_TYPE_CD_PHONELISTING = "PhoneLis";
	    public static final String PRODUCT_TYPE_CD_SUBSCRIPTION = "Subscrip";
	    public static final String PRODUCT_TYPE_CD_SMART_RING = "SmartRin";
	    public static final String PRODUCT_TYPE_ORDER_ITEM = "OrderItem";
	    public static final String QUESTION_TYPE_CD_TTV = "TTV";
	    public static final String QUESTION_TYPE_CD_CLEARANCEPATH_QUESTIONAIRE = "CPQ";
	    public static final String QUESTION_ANSWER_TYPE_CD_SINGLE_SELECTION = "SINGLE";
	    public static final String CREDIT_CARD_PURPOSE_CODE_BILLING = "B";
	    public static final String CREDIT_CARD_PURPOSE_CODE_DELINQUENCY = "D";

	    public static final String ORG_LEVEL_CHANNEL = "Channel";
	    public static final String OMS = "OMS";

	    public static final String BUSINESS_ERROR_CODE= "CSI-00004";
	    public static final String UPDATE_STATUS_EXTRACTED_ERROR = "CSI-10185";

		public static final String REASON_TYPE_PROCESS_CD = "P";

		public static final String DELIMITER = "/t";
		public static final String INTERNET_TYPE_TELUS_NO_LOCAL = "NON";
		public static final String INTERNET_TYPE_NON_TELUS = "NT";
		public static final String INTERNET_TYPE_TELUS = "TUS";

		public static final String METHOD_ENTERING = "entering : ";
		public static final String METHOD_EXITING = "exiting : ";
		public static final String OMS_ORDER_SUBMISSION = "OMS";

		//PRODUCTLINE
		public static final String PRODUCTLINE_HOMEHPONE = "Home Phone";
		public static final String PRODUCTLINE_HS = "High Speed Internet";
		public static final String PRODUCTLINE_TELUSTV = "TELUS TV";

		//PRODUCTTYPE
//		public static final String PRODUCTTYPE_HOMEHPONE = "Home Phone"
//		public static final String PRODUCTTYPE_HS = "High Speed Internet"
//		public static final String PRODUCTTYPE_HS_HSL = "High Speed Lite"
//		public static final String PRODUCTTYPE_HS_HSEH = "High Speed Enhanced"
//		public static final String PRODUCTTYPE_HS_HSEX = "High Speed Extreme"
//		public static final String PRODUCTTYPE_HS_HST = "High Speed Turbo"
//		public static final String PRODUCTTYPE_HS_HST25 = "High Speed Turbo 25"
//		public static final String PRODUCTTYPE_HS_HSZ = "High Speed Zero"
//		public static final String PRODUCTTYPE_HS_HSO = "TELUS Internet 15"
//		public static final String PRODUCTTYPE_HS_HSOT = "TELUS Internet 25"
//		public static final String PRODUCTTYPE_OTV = "Optik TV"
//		public static final String PRODUCTTYPE_STV = "TELUS Satellite TV"
//		public static final String PRODUCTTYPE_HS_HST50 = "Internet 50"
//		public static final String PRODUCTTYPE_HS_HSOT50 = "TELUS Internet 50"

		public static final String ADDRESS_VALIDATION_TYPELIST_DETAILS = "DETAILS";
		public static final String ADDRESS_VALIDATION_TYPELIST_LIKEAPTS = "LIKE_APTS";

		public static final String COUNTRY_CODE_CANADA = "CA";

		public static final String SESSION_SOURCE_DISPLAY_NAME = "SESSION_SOURCE_DISPLAY_NAME";
		public static final String SESSION_SOURCE_UID = "SESSION_SOURCE_UID";
		public static final String SESSION_SOURCE_EMPLOYEE_NUMBER = "SESSION_SOURCE_EMPLOYEE_NUMBER";
		public static final String SESSION_SOURCE_DN = "SESSION_SOURCE_DN";
		public static final String SESSION_LOGIN_USER = "LoginUser";

		public static final String VESTA_PAY_USER_ROLE = "Vesta_Pay";

		public static final String FORWARD_SUCCESS = "success";
		public static final String FORWARD_FAILURE = "failure";

		public static final String REASON_TYPE_OVERRIDE_CD = "O";
		public static final String CALL_CHNL_CARE = "CALL_CHNL_CARE";





		public static final String MDC_ID_TAG = "transaction";

		//CRS065
		public static final String POSTAL_CODE_NPC = "NPC";
		public static final String POSTAL_CODE_FILLER = "V1V1V1";

		// high speed installer types
		public static final String HS_INSTALLER_SELF = "SELFIN";
		public static final String HS_INSTALLER_TECHNICIAN = "TLSSPC";

	    public static final String PKG_UPGRADE_MESSAGE_ID="20839018_20820428_0";

	    public static final String SERVICE_TIER_TV_26B = "TV26B";
	    public static final String STV_ECHELON_OFFERS_CONFIG = "stvEchelonOffers";
	    public static final String SSM_INFO_MESSAGES_CONFIG = "ssmInfoMessages";
	    public static final String[] WLN_INFO_MESSAGES_CONFIG = new String[] {"wlnInfoMessages","messageList"};
	    public static final String[] WLN_INFO_MESSAGES_FLAG_CONFIG = new String[] {"wlnInfoMessages","display","indicator"};

		public static final String DELIVERY_METHOD_NA = "NA";

		public static final String MISSING_DATA="MISSING_DATA";
		public static final String INCLUDED_IN_ORDERDETAIL = "order.detail.equipment.cost.included";

		public static final String CLIENT_ITEM_ACTION_TYPE_AD = "AD";
		public static final String SYSTEM_ITEM_ACTION_TYPE_RM = "RM";
		public static final String SYSTEM_ITEM_ACTION_TYPE_AD = "AD";
		public static final Object SERVICE_TYPE_TV_VALUEPACK = "VP";
		public static final String PRODUCT_TYPE_STV = "STV";

		// Component and Relation IDs
		public static final String SLMAINCOMPONENTCID = "44";
		public static final String HSMAINCOMPONENTCID = "22";
		public static final String CALLINGFEATURECID = "41";
		public static final String CALLINGFEATUREPATH = "44:41";

		public static final String LDCID = "1227";
		public static final String STANDARD_TOLL_PLANS_PATH = "44:1227";
		public static final String WINBACK_TOLL_PLANS_PATH = "44:10040";

		public static final String HSCOMPONENTCID="1";
		public static final String HSCOMPONENTPATH="22:1";
		public static final String HSPACKPATH = "22:25";
		public static final String HSPACKCID = "25";
		public static final String HSPACK_ZEROPATH = "22:25:64945"; //high speed zero
		public static final String HSPACK_OPTIKPATH = "22:25:64905"; //high speed optik
		public static final String HSPACK_OPTIKTUBROPATH = "22:25:64906"; //high speed optik turbo
		public static final String HSQUESTIONPATH = "22:1:50514";
		public static final String HSTURBO15PATH = "22:25:61551";
		public static final String HSTURBO25PATH = "22:25:61611";
		public static final String HSTURBO50PATH = "22:25:20860979";
		//CR 5793 - T&M for all other HSIA - HSL, HSE, HSEX
		public static final String HSENHANCEDV1PATH = "22:25:54155";
		public static final String HSEXTREMEPATH = "22:25:54255";
		public static final String HSLITEPATH = "22:25:50502";
		//CR 5793 - T&M for all other HSIA - HSL, HSE, HSEX
		public static final String DIRECTORYLISTINGCID = "38";
		public static final String DIRECTORYLISTINGPATH = "44:59:38";
		public static final String PRIMARYSUBSCRIPTIONNUMPATH = "44:59";
		public static final String SMARTRINGSUBSCRIPTIONNUMPATH = "44:649";
		public static final String TTVREGISTRATIONNUMBERCID = "33";
		public static final String QUESTIONNAIREPATH = "62:50380";
		public static final String STVREGISTRATIONNUMBERCID = "62756";
		public static final String INTERNETEQCID = "34";
		public static final String INTERNETEQPATH = "22:1:34";
		public static final String SMARTRINGCID = "54";
		public static final String SMARTRINGPATH = "44:41:54";
		public static final String SMARTRINGRID = "15";
		public static final String STVMAINPATH = "62753";
		public static final String STVEQUIPMENTPATH = "62753:62756:62814";
		public static final String STVREGNUMPATH = "62753:62756";
		public static final String TTVMAINPATH = "62";
		public static final String TTVEQUIPMENTPATH = "62:33:51360";
		public static final String TTVREGNUMPATH = "62:33";
		public static final String DIRASSISTBLOCKPATH = "44:41:572";

		public static final String MOVIE_PACKS_PATH = "62:322";
		public static final String FRENCH_PACKS_PATH = "62:50464";
		public static final String THEME_PACKS_PATH = "62:64";
		public static final String ADDON_PACKS_PATH = "62:50480";

		public static final String SERVICETMPATH = "44:36";
		public static final String SERVICETM_HSPATH = "22:1:36";
		public static final String SERVICETM_FEATURE_HSPATH = "22:1:36:39";
		public static final String SERVICE_TM_CID = "36";
		public static final String SERVICE_TM_FEATURE_CID = "39";

		//hs child component for determine if username need to be registered
		//they are directly under hs component 22
		public static final String HS_EMAIL_CID = "40";
		public static final String HS_WEBSPACE_CID = "58";

		// Property IDs
		public static final String INPACKPID = "31124";
		public static final String LISTFIRSTNAMEPID = "30674";
		public static final String LISTLASTNAMEPID = "30675";
		public static final String LISTTITLEPID = "31123";
		public static final String LISTADDRESSINDPID = "82927";
		public static final String SUBSCRIPTIONNUMPID = "30694";
		public static final String DECLINETOLLPLANPID = "30687";
		public static final String DECLINETOLLPLANREASONPID = "31192";
		public static final String DECLINETOLLPLANREASONOTHERPID = "35020";
		public static final String DECLINETOLLPLANPATH = "44:30687";
		public static final String HSQUESTIONLANPID = "50586";
		public static final String HSQUESTIONDISKSPID = "50587";
		public static final String HSQUESTIONJACKPID = "50588";
		public static final String INSTALLINGPARTYPID = "30901";

		//the following two is for service T&M
		public static final String TYPEOFWORKPID = "30709";
		public static final String SERVICETM_QUANTITYPID = "30707";

		public static final String LISTINGTYPEPID = "30683";
		/**
		 * possible value of the listing type
		 *                          LIS - Listed
	                                NONL - Additional Unlisted
	                                NONP - Non Post
	                                NONPUB - Unlisted
	                                PUB - Published

		 */
		public static final String LISTINGTYPE_LISTED="LIS";
		public static final String LISTINGTYPE_UNLISTED="NONPUB";
		public static final String LISTINGTYPE_ADDITIONAL="NONL";

		public static final String SMARTRINGNUMPID = "30694";
		public static final String MICCODEPID = "30661";
		public static final String NAMEDISPLAYPID = "30684"; //name display of SL
		public static final String OMITDIRACCESSPID = "31284";
		//the TTV question pid in the order of the question on page
	    //comment this as it is from old template fro HS0-TTV
		public static final String[] TTVQUESTIONSPID = new String[]{"83508","83488","83510","20410268", "20410278","20410288","20410298","20410308","20410318"};
		public static final String COMMITMENTINYEARSPID="30627";
		public static final String INSTALLINDICATORPID="30506";
		public static final String ACQUISITIONPID="30470";
		public static final String EQUIPDESCPID="31163"; //of Equipment
		public static final String EQUIPMODELPID="30699"; //of registration number
		public static final String EQUIPMAKEPID="30764"; //of registration number
		public static final String MICPID="30661";
		public static final String DELIVERYMETHODPID="30465";
		public static final String INTERNETTYPEPID="30614"; //local service provider for HS
		public static final String PRIMARYTN_PID = "31325";

		//propertys to get the information for feasibility
		public static final String ADDITIONALLINE_PID = "31187";

		//offer IDS
		public static final String SL_OMS_OFFER_ID = "83";
		public static final String HS_OMS_OFFER_ID = "50580";
		public static final String TTV_OMS_OFFER_ID = "64965";
		public static final String STV_OMS_OFFER_ID = "62801";

		public  static final String SLCR_OFFERID= "53394";

		public static final String SERVICE_TYPE_TV_COMBO_PACK = "VP";
		public static final String SERVICE_TYPE_TV_PACK1 = "TVCG";
		public static final String SERVICE_TYPE_TV_PACK2 = "TVPC";
		public static final String SERVICE_TYPE_TV_PACK3 = "DGS";


		// HS Service Tier IDS
		public static final String HIGH_SPEED_ENHANCED = "0"; // Grandfathered
		public static final String HOME_NETWORKING_HN = "0"; // Grandfathered
		public static final String HIGH_SPEED_TELUS_GF = "0"; // Grandfathered
		public static final String HIGH_SPEED_ENHANCED_GF = "0"; // Grandfathered

		//Oct - Echelon constants (Offers / packs / regionCode

		public static final String SERVICE_TYPE_TV_ECHELON_PACK1 = "TVMP";
		public static final String SERVICE_TYPE_TV_ECHELON_PACK2 = "TVAP";
		public static final String REGION_CD_PROPERTY_ID = "30701";

		public static final int STREET_NAME_MAXLENGTH=18;
		public static final int STREET_NUMBER_MAXLENGTH=6;
		public static final int UNIT_NUMBER_MAXLENGTH=5;


		public static final String[] STV_ECHELON_OFFER_IDS = {
			"20820938", "20820939"
		};

		//Oct - end

		public static final String SL_REF = "ref_1";
		public static final String HS_REF = "ref_2";
		public static final String TTV_REF = "ref_3";
		public static final String STV_REF = "ref_4";

		// For equipment charges
		public static final String ONE_TIME_CHARGE_CODE = "OC";
		public static final String RECURRING_CHARGE_CODE = "RC";

		public static final String LOCAL_EN_CA = "EN_CA";

		public static final String ACQUISITION_TYPE_RENTAL = "RE";

		public static final String FILTERED_EQUIPMENT_MESSAGE_1 = "Adding ";
		public static final String FILTERED_EQUIPMENT_MESSAGE_2 = " to promotion is not allowed.";
		public static final String FILTERED_EQUIPMENT_MESSAGE_3 = " to offer or sweetener is not allowed.";
		public static final String FILTERED_EQUIPMENT_MESSAGE_4 = " is not in the Feasibility Service Return list.";
		public static final String FILTERED_EQUIPMENT_TYPE_DEFAULT = "this item" + FILTERED_EQUIPMENT_MESSAGE_4;

		public static final String FILTERED_EQUIPMENT_TYPE_HSIC = "this High Speed product";
		public static final String FILTERED_EQUIPMENT_TYPE_INEQ = "this internet equipment";
		public static final String FILTERED_EQUIPMENT_TYPE_SETTOPBOX = "rental STB";
		public static final String FILTERED_EQUIPMENT_TYPE_HS_MODEM = "rental HS modem";

		public static final String ERROR_ID_SERVICE_EXCEPTION = "300000";

		/***********************************/
		/* GetAvailableDate                */
		/***********************************/
		public static final String D2C_PARTNER = "D2C_PARTNER";

		/* Constants for FeasibilityService **/
		public static final String PRODUCT_REQUEST_ID   = "productRequestId";
		public static final String ORDER_ACTION_TYPE    = "orderActionType";
		public static final String SERVICE_PLAN         = "servicePlan";
		public static final String SERVICE_REQUEST_DATE = "serviceRequestDate";
		public static final String STB_ADDED_IND        = "STBAddedInd";
		public static final String NEW_STBS_TO_BE_INSTALLED_BY_TELUS = "newSTBsToBeInstalledByTELUS";

		public static final String DATE_FORMAT_PRODUCT_REQUEST = "yyyyMMddHHmmssS";

		public static final String ORDER_ACTION_TYPE_CREATE="Create";
		public static final String ORDER_ACTION_TYPE_MODIFY="Modify";

		/**
		 * Constants for CWSO-> ProductCharacteristicsMap
		 */

		public static final String CONTRACT_TERM="contractTerm";
		public static final String KEEP_COMMITMENT = "Keepcommitment";
		public static final String EQUIPMENT_DESCRIPTION="equipmentDescription";
		public static final String ACQUISITION_TYPE="acquisitionType";
		public static final String DELIVERY_METHOD="deliveryMethod";
		public static final String MIC="mic";
	    public static final String SET_TOP_BOX_MODEL="setTopBoxModel";
	    public static final String SET_TOP_BOX_MAKE="setTopBoxMake";
	    public static final String PRODUCT_TEMPLATE_ID="productTemplateId";
	    public static final String TELEPHONE_NUMBER="telephoneNumber";
	    public static final String TTVQ_RENT_OR_OWN="ttvqRentOrOwn";
	    public static final String TTVQ_ON_SITE_MAN="ttvqOnSiteMan";
	    public static final String TTVQ_CBR="ttvqCBR";
	    public static final String TTVQ_CBRALT= "ttvqCBRalt";
	    public static final String TTVQ_ADULT_PRESENT="ttvqAdultPresent";
	    public static final String TTVQ_TV_SET="ttvqTVSet";
	    public static final String INSTALL_TYPE="installType";
	    public static final String RECOMMENDED_INSTALL_TYPE="recommendedInstallType";
	    public static final String MAX_WORK_TIME="maxWorkTime";
	    public static final String MIN_WORK_TIME="minWorkTime";
	    public static final String DEFAULT_WORK_BUCKET="defaultWorkBucket";
	    public static final String DEFAULT_WORK_TIME ="defaultWorkTime";
	    public static final String BOOKING_REQUIRED="bookingRequired";
	    public static final String PRIMARY_SUB_NUMBER = "primarySubNumber";
	    public static final String SMARTRING_SUB_NUMBER = "smartRingSubNumber";
	    public static final String PHONE_NUMBER = "phoneNumber";
	    public static final String LISTING_TYPE = "listingType";
	    public static final String NAME_DISPLAY = "nameDisplay";
	    public static final String DECLINE_TOLL_PLANS = "declineTollPlans";
	    public static final String DECLINE_TOLL_PLAN_REASON = "declineTollPlanReason";
	    public static final String DIRECTORY_TYPE = "DirectoryType";
	    public static final String DIRECTORY_ACCESS = "directoryAccess";
	    public static final String OMIT_DIRECTORY_ADDRESS = "omitDirectoryAddress";
	    public static final String IN_PACK = "inPack";
	    public static final String DIRECTORY_LIST_COMP = "directoryListComp";
	    public static final String LISTING_FIRST_NAME = "listingFirstName";
	    public static final String LISTING_LAST_NAME = "listingLastName";
	    public static final String SALES_CHANNEL = "salesChannel";
	    public static final String UNDERSCORE = "_";

	    public static final String PRODUCT_INFORMATION="productInformation";
	    public static final String PACKS="packs";
	    public static final String HS_PACK="hsPack";
	    public static final String EQUIPMENT="equipment";
	    public static final String HS_EQUIPMENT = "hsEquipment";
	    public static final String REGISTRATION_NUMBER = "Registration Number";
	    public static final String GENERAL_PRODUCT_INFO="generalProductInfo";
	    public static final String TTV_QUESTIONNAIRE="ttvQuestionnaire";

	    public static final String PRODUCT_COMPONENT_NAME = ".productComponentName";
	    public static final String PRODUCT_COMPONENT_CATALOG_ID=".productComponentCatalogId";
	    public static final String PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID=".productComponentCatalogAttributeId";
	    public static final String PRODUCT_COMPONENT_ATTRIBUTE_NAME=".productComponentAttributeName";
	    public static final String PRODUCT_CHARACTERISTICS_EXTERNAL_FILE_NAME="productCharacteristics.properties";

	    public static final String DELIVERY_METHOD_INSTALLER = "INSTALLER";
	    public static final String DELIVERY_METHOD_INSTORE="INSTORE";
	    public static final String DELIVERY_METHOD_SHIPPED = "SHIPPED";

	    public static final String DELIVERY_METHOD_INS="INS";
	    public static final String DELIVERY_METHOD_CST="CST";
	    public static final String DELIVERY_METHOD_SHP="SHP";

	    public static final String COMPONENT_COMMENT_NAME = "comment";
	    public static final String COMPONENT_COMMENTS_NAME = "comments";
		public static final String HS0_EXTERNAL_CATALOGUE_ID = "64945";
		public static final String D2C_ORDER = "Dir";
		public static final String VESTA_ORDER = "VESTA";
		public static final String OP_D2C_ORDER = "D2C";
	    public static final String NOTE_TEXT = "noteText";
	    public static final String NOTE_TYPE_CODE = "noteTypeCode";
	    //public static final String NOTE_TYPE_CODE_SALES = "SA"; //To fix it, please change the ESS code to send "FF" (free format) as comment type (noteTypeCode) instead of "SA".
	    public static final String NOTE_TYPE_CODE_FREE_FORMAT = "FF";
	    public static final String NOTE_TYPE_CODE_CLEC_COMMENT = "CC";
	    public static final String NOTE_TYPE_CODE_INSTALLER = "IN";
	    public static final String NOTE_TYPE_CODE_FREE_TEXT = "FT";
	    public static final String ORDER_ENTITY_TYPE_CODE = "orderEntityTypeCode";
	    public static final String ORDER_ENTITY_TYPE_CODE_OA = "OA";
	    public static final String ORIGINATOR_ID = "originatorID";
	    public static final String ORDER_ENTITY_ID = "orderEntityID";
	    public static final String NOTE_TYPE_ORDER_COMMENTS = "ORDER_COMMENTS";

		public static final ArrayList<String> serviceTypeCdComponentNameList = new ArrayList<String>(Arrays.asList("TVCG", "TVPC", "DGS", "TVES", "VP"));

		public static final HashMap<String, String> serviceTypeCdMapping = new HashMap<String, String>()
				{{
					put("TPLN", "Long Distance Plans");
					put("SF", "Calling Features");
					put("OTHER", "OTHER");
					//put("A La Carte", "A La Carte");
				}};

		public static final String INEQ = "INEQ";

		public static final String TVEQ = "TVEQ";

		public static final String ADSP = "ADSP";
		
		public static final String SFPK = "SFPK";

		public static final String ENGLISH = "EN";

		public static final String DISCOUNT = "Discount";

		public static final String RECURRING = "RECURRING";

		public static final String EQUIPMENT_NAME_ATTRIBUTE_ID = "31163";

		public static final String EQUIPMENT_NAME_DESCRIPTION_ATTRIBUTE = "Description";

		public static final String EQUIPMENT_MIC_ATTRIBUTE_ID =  "cpeMicCode";

		public static final String EQUIPMENT_ACQUISITION_TYPE_ATTRIBUTE_ID =  "acquisitionType";

		public static final String EQUIPMENT_ACQUISITION_TYPE_BUY = "SA";

		public static final String EQUIPMENT_ACQUISITION_TYPE_RENTAL = "RE";

		public static final String HSIC = "HSIC";

		public static final String TTV = "TTV";

		// public static final String SING = "SING"; it's already defined in EnterpriseWLNSalesServicesConstants 

		public static final String RETURN_METHOD = "returnMethod";
		public static final String RETURN_METHOD_RETURNED_TO_TECHNICIAN = "ReturnedToTechnician";
		public static final String RETURN_METHOD_RECOVERY_KIT_BY_MAIL = "RecoveryKitByMail";

		public static final String SHAW_PROVIDER_ID = "497E";

		public static final String HSZERO = "HSZERO";
		
}