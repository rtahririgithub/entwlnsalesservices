package com.telus.csm.ewlnsc.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public interface EnterpriseWLNSalesServicesConstants {

	public static final String ENABLER_BILLING_ACCOUNT_SYSTEM_CODE = "1001";

	public static final String BRAND_CODE_TELUS = "TELUS";
	public static final String BRAND_CODE_KOODO = "KOODO";
	public static final BigInteger BRAND_ID_TELUS = BigInteger.ONE;
	
	public static final String UNKNOWN_EWLNSS_ERROR = "Unknown EWSS Error";
	public static final String EWSS_APP_ID = "11271";
	public static final String MESSAGE_TYPE_ERROR = "ERROR";
	public static final String MESSAGE_TYPE_ERROR_RELAY = "ERROR_RELAY";
	public static final String MESSAGE_TYPE_ERROR_RETRY_SAFE = "ERROR_RETRY_SAFE";
	public static final String MESSAGE_TYPE_EXCEPTION = "EXCEPTION";
	public static final String MESSAGE_TYPE_WARNING = "WARNING";
	
	public static final String LOCALE_EN_CA = "en_CA";
	public static final String LOCALE_FR_CA = "fr_CA";
	public static final String SING = "SING";
	public static final String TTV = "TTV";
	public static final String STV = "STV";
	public static final String HSIC = "HSIC";
	public static final String HOME_SECURITY = "SHS";
	public static final String CPE = "CPE";
	public static final String OIS_ACCESSORIES_CD = "CPE";
	public static final String SALES_PACK_HS_ZERO_NAME = "HS0";
	public static final String ACTIVATION = "ACTIVATION";
	public static final String RENEWAL = "RENEWAL";
	public static final String UPGRADE = "UPGRADE";
	public static final String PR = "PR"; // PROVIDE
	public static final String CH = "CH"; //CHANGE
	public static final String SALES_PRODUCT_TYPE_HOME = "SL";
	/** The Constant SUBSCRIPTION_NUMBER_TYPE_CODE_PRIMARY. */
	public static final String SUBSCRIPTION_NUMBER_TYPE_CODE_PRIMARY = "Primary Subscription Number";
	
	//Accessory equipment service type code
	public static final String DWSP = "DWSP";
	public static final String DWEP = "DWEP";
	public static final String CAXP = "CAXP";
	public static final String MAXP = "MAXP";
	
	public static final String DOWNSTREAM_ERROR_SEE_RELATED_MSG = "Downstream call failed. See RelatedMessageList element for more details.";
	
	public static final String PROV_CD_QUEBEC = "QC";
	public static final String PROV_CD_NEWFOUNDLAND = "NL";
	public static final String PROV_CD_QUEBEC_OLD = "PQ";
	public static final String PROV_CD_NEWFOUNDLAND_OLD = "NF";

	public static final String MANDATORY_INPUT_MESSAGE_TEXT = "Mandatory service request element(s) were missing from the request.";
	public static final String INVALID_INPUT_MESSAGE_TEXT = "Request contains element(s) with invalid input.";
	
	public static final String REQUESTED_PRODUCTS_ARE_NOT_AVAILABLE = "The following requested products are not available: ";
	
	public static final String VALIDATION_MANDATORY = "VALIDATION_MANDATORY";
	
	public static final String INPUT_VALIDATION_FAILED_OR_WARNING_MSG = "Input Validation failed or warning.";

	public static final String LANG_FR = "fr";
	public static final String LANG_EN = "en";

	public static final String CUSTOMER_STATUS_CODE = "AC";
	public static final String CUSTOMER_TYPE_INDIVIDUAL = "R";
	public static final String CUSTOMER_SUB_TYPE_RESIDENTIAL = "I";

	public static final String BILLING_MEDIA_DETAIL_LEVEL_CODE = "AS";
	public static final String BILLING_RETURN_ENVELOPE_CODE = "B";
	public static final String BILLING_INSERT_SUPPRESSION_INDICATOR = "N";
	public static final String BILLING_ACCOUNT_NUMBER = "0";
	public static final int BILLING_MEDIA_COPY_COUNT = 1;

	public static final String ADDRESS_BILLING_ASSIGNMENT_TYPE = "M";
	public static final String ADDRESS_TYPE_TAX = "X";
	public static final String ADDRESS_ASSIGNMENT_TYPE_TAX = "T";
	public static final String ADDRESS_ASSIGNMENT_TYPE_SERVICE = "S";
	public static final String ADDRESS_ASSIGNMENT_TYPE_OFFICIAL = "C";

	// invalid billing address types
	public static final String ADDRESS_TYPE_OFFICIAL = "Z"; // official address
	public static final String ADDRESS_TYPE_SERVICE = "S";
	public static final String ADDRESS_TYPE_EMAIL = "E";
	public static final String ADDRESS_TYPE_CONTACT = "T";
	public static final String ADDRESS_TYPE_RR = "R";
	public static final String ADDRESS_TYPE_POBOX = "P";
	public static final String ADDRESS_TYPE_CANADA = "C";
	public static final String ADDRESS_TYPE_US = "U";

	/** The Constant CODS_INVOICE_MEDIA_TYPE_EBILL. */
	public static final String CODS_INVOICE_MEDIA_TYPE_EBILL = "E.Bill";
	public static final String CODS_INVOICE_MEDIA_TYPE_PAPER = "Paper";
	public static final String EBILL_NOTIFICATION_TYPE_EMAIL = "EMAIL";
	public static final String EBILL_NOTIFICATION_TYPE_SMS = "SMS";
	public static final String EBILL_NOTIFICATION_TYPE_EMAIL_SMS = "EMAIL_SMS";
	public static final String EBILL_DECLINE_RSN = "EBILL_DECLINE_RSN";
	public static final String EBILL_NOTIF_MTHD = "EBILL_NOTIF_MTHD";
	
	public static final String CODS_MASTER_SOURCE_ID = "1012";
	public static final String EBILL_ENTITY_TYPE_CD = "ACCOUNT";
	
	/* Downstream related Constants */
	public static final String UPDATE_OWNER_IDENTITY_CREDENTIAL_TYPE_CODE = "OWN_PIN";
	
	public static final String ESS_ERROR_CODE_FEATURE_CONFLICT_FEATURE = "ess.service.error.feature.conflict.feature";
	
	// Warning message code
	public static final String ESS_WARN_CODE_INCOMPLETE_CALLING_CIRCLE_PHONE_NUMBERS_LIST = "ESS_3221";
	public static final String ESS_WARN_CODE_BLANK_CALLING_CIRCLE_PHONE_NUMBERS_LIST = "ess.service.warn.blank.calling.circle.phone.numbers.list";
	public static final String ESS_WARN_CODE_CDMA_SUBSCRIBER_WARN_MESSAGE = "ESS_3304";
	public static final String ESS_WARN_CODE_HSPA_MULTIPLE_EQUIPMENT_LIST = "ESS_3305";

	// Information message code
	public static final String ESS_INFO_CODE_PAYMENT_FAIL_PROVISIONING_SUCCEED = "ess.service.info.payment.fail.provisioning.succeed";
	public static final String ESS_INFO_CODE_FEATURE_CALLING_CIRCLE_INCLUDED_TELUS = "ess.service.info.feature.callingcircle.included.telus";

	public static final String TELECOM_CONTACT_DAY_PHONE = "DYPH";
	public static final String TELECOM_CONTACT_EVENING_PHONE = "EVPH";
	public static final String TELECOM_CONTACT_WORK_PHONE = "WKPH";
	public static final String TELECOM_CONTACT_CELL_PHONE = "CLPH";
	public static final String TELECOM_CONTACT_CELL = "CL";
	public static final String TELECOM_CONTACT_PHONE = "PH";
	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String ELECTRONIC_CONTACT_EMAIL = "E";
	public static final String ELECTRONIC_CONTACT_EMAIL_PRIMARY = "EPR";
	public static final String CONTACT_ASSIGNMENT_PRIMARY_ROLE_TYPE_CODE = "N";
	public static final String CONTACT_ASSIGNMENT_AUTHORIZED_ROLE_TYPE_CODE = "SPOUSE";
	public static final String CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE = "N";
	public static final String CONTACT_ASSIGNMENT_AUTHORIZED_TYPE_CODE = "R";
	public static final String CONTACT_INDIVIDUAL_ENTITY_TYPE_CODE = "CUSTOMER";
	public static final String CREDIT_PROFILE_STATUS = "A";
	public static final String CREDIT_PROFILE_FORMAT = "P";
	public static final String CREDIT_PROFILE_ESTABLISHED = "E";

	public static final String RESOURCE_TYPE_CODE_TN = "TN";
	public static final String PAP_STATUS_ACTIVE = "ACTIVE";
	public static final String PAP_TYPE = "CreditCard";
	public static final String LINE_OF_BUSINESS_WIRELINE = "WIRELINE";

	public static final int AVSMAXADDRESSNUMBER = 10;
	public static final int MAX_POBOX_LEN = 10;

	public static final String BC_PROVINCE_CD = "BC";
	public static final String AB_PROVINCE_CD = "AB";

	public static final String CANADIAN_ENGLISH_LOCALE = Locale.CANADA.toString();
	public static final String MESSAGE_TYPE_INFO = "INFO";

	// Error message code
	public static final String ESS_ERROR_CODE_UNKNOWN_ERROR = "ESS_3021";
	
	//For Comment
	public static final String NEWLINE = "\n";
	public static final String DOT = ".";
	public static final String EWLNSS_APP_ID="SalesServices";
	public static final String REFPDS_LAG_TIME_TABLE="NGUI_INSTALL_LAG_TIME";
	public static final String REFPDS_DECLINED_REASON_TABLE = "EBILL_DECLINE_RSN";
	public static final String REFPDS_EMAIL_DECLINED_REASON_TABLE = "EMAIL_COLLECTION_SUPPRESSION_REASON";
	public static final String REFPDS_EBILL_NOTIF_TYPECD_TABLE = "EBILL_NOTIF_MTHD";
	public static final String REFPDS_PREFERRED_LANGUAGE_CD_TABLE = "BI_CM_LANGUAGE";
	public static final String REFPDS_PROVINCE_TABLE = "PROVINCE";
	public static final String REFPDS_WSS_PROD_OMS_CID_MAP = "WSS_PROD_OMS_CID_MAP";
	public static final String REFPDS_WSS_PRODUCT_TIER = "WSS_PRODUCT_TIER";
	public static final String REFPDS_PRODUCT_FAMILY_GROUP = "PRODUCT_FAMILY_GROUP";
	public static final String REFPDS_WSS_PRODUCT_OPTIK = "WSS_PRODUCT_OPTIK";
	public static final String REFPDS_WSS_PROD_OMS_PROD = "WSS_PROD_OMS_PROD";
	public static final String REFPDS_WLN_SALES_PRODUCT_FAMILY_GROUP_RUL = "WLN_SALES_PRODUCT_FAMILY_GROUP_RUL";
	public static final String REFPDS_WSS_PROD_X_PROD_LINE = "WSS_PROD_X_PROD_LINE";
	public static final String REFPDS_PRCG_RULE_REF = "PRCG_RULE_REF";
	
	// Validation Reg Exp
	public static final String PHONE_NUMBER_REG_EXP = "^[0-9]{10}|[1][0-9]{10}$";
	public static final String POSTAL_ZIP_CODE_REG_EXP = "^(\\d{5}(?:[-\\s]\\d{4})?$)|(^(?!.*[DFIOQU])[A-VXYa-vxy][0-9][A-Za-z] ?[0-9][A-Za-z][0-9])$";
	public static final String NPC_POSTAL_ZIP_CODE = "NPC";
	
	//for Create Customer Event downstream call
	public static final long EVENT_TYPE_ID_EMAIL_EBILL = 129;
	public static final long EVENT_TYPE_ID_SMS_EBILL = 128;
	public static final long EVENT_TYPE_ID_EMAIL_SMS_EBILL = 130;
	public static final long EVENT_TYPE_ID_DECLINE_EBILL = 131;
	public static final String EVENT_TYPE_CATEGORY_CD = "BILLING";
	public static final String EVENT_TYPE_REF_ENT_TYPE_CD = "BACCT";
	public static final Long EVENT_TYPE_REF_ENT_SOURCE_ID = 1001L;
	public static final String EVENT_TYPE_RETENTION_CD = "PRM";
	public static final long EVENT_TYPE_SOURCE_ID = 9922;
	public static final String EVENT_TYPE_USER_ID = "T000002";
	public static final Boolean EVENT_TYPE_HIGHLIGHTED_IND = true;
	public static final Boolean EVENT_TYPE_SYSTEM_GEN_IND = true;
	public static final String EVENT_TYPE_PARAMETER_NAME = "DeclineReason";
	public static final String CREDIT_ASSESSMENT_TYPE_CD = "FULL_ASSESSMENT";
	public static final String CREDIT_ASSESSMENT_SUBTYPE_CD = "AUTO_ASSESSMENT";
			
	public static final String EWSS_VERSION_VALUE="v1.0";
	
	public static final String CACHE_KEY_DELIMITER="_";
	
	public static final String REFPDS_DATA_SERVICE_CACHE_NAME="ReferencePDSDataServiceCache";
	public static final String ESDG_SALES_CONTEXT_CACHE_NAME="ESDG_SalesContext";
	
	public static final String WLN_OFFER_TYPE_CD="WLN";
	
	public static final String SALES_ACTIVITY_ACTIVATION="ACTIVATION";
	public static final String SALES_ACTIVITY_RENEWAL="RENEWAL";
	
	// Create Wireline Sales Order related
	public static final String TYPE_DE = "DE";
	public static final String STORE_NAME = "storeName";
	public static final String SALES_REP = "salesRep";
	public static final String SALES_REP_ID = "salesRepID";
	public static final String DEALER_SALES_REP_ID = "dealerSalesRepID";
	public static final String STORE_ID = "storeID";
	public static final String EXTERNAL_REF_NUM = "externalRefNum";
	public static final String EXTERNAL_REF_SRC = "externalRefSrc";
	public static final String EMAIL = "EMAIL";
	public static final String EM = "EM";
	public static final String LETTER = "LETTER";
	public static final String LE = "LE";
	public static final String PREFERRED_CONTACT_METHOD = "preferredContactMethod";
	public static final String CSAG = "CSAG";
	public static final String CONTACT_CATEGORY = "contactCategory";
	public static final String CU = "CU";
	public static final String CONTACT_TYPE = "contactType";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String OFFER_CATALOG_ID = "offerCatalogId";
	public static final String ORIGINAL_OFFER_CATALOG_ID = "originalOfferCatalogId";
	public static final String OFFER_NAME = "offerName";
	public static final String MARKET_OFFER_NAME = "marketOfferName";
	public static final String MARKET_OFFER_ID = "marketOfferId";
	public static final String ORDER_ACTION_ATTRIBUTES = "orderActionAttributes";
	public static final String BYPASS_CLEARANCE_IND = "bypassClearanceInd";
	public static final String SERVICE_REQUIRED_DATE = "serviceRequiredDate";
	public static final String SERVICE_ADDRESS = "serviceAddress";
	public static final String STATE = "state";
	public static final String FMS_ADDRESS_ID = "fmsAddressId";
	public static final String PRODUCT_INFORMATION = "productInformation";
	public static final String AD = "AD";
	public static final String UP = "UP";	
	public static final String RM = "RM";	
	//GetAvailableOfferSummaryList
	public static final String NON_CREDIT_RESTRICTED_PROFILE="E";
	public static final String TECHNOLOGY_TYPE_FIBER="fiber";
	public static final String TECHNOLOGY_TYPE_COPPER="copper";
	public static final String OIS_PRODUCT_INSTANCE_ACTIVATION="activation";
	public static final String OIS_PRODUCT_INSTANCE_UPGRADE="upgrade";
	public static final String OIS_PRODUCT_INSTANCE_BUNDLE="BUNDLE";
	public static final String OMS_OFFER_CAT_ID_PIK_TV="40937304";
	public static final String PRODUCT_TYPE_TVX="TVX";
	public static final String PRODUCT_TIER_CD_TVX="TVX";
	
	public static String NO_PORT_AVAILABLE = "FCCP06_E1";
	public static String NO_PORT_REASON_CD = "NOPORT";
	public static String HS_PLAN_CD = "PDSC01_E4";
	public static String HS_PLAN_REASON_CD = "HS_TIER_NOT_Feasible";
	public static String PRODUCT_QUALIFICATION_LIST_IS_EMPTY = "Product Qualification list is empty.";
	public static String SERVICE_ADDRESS_IS_MANDATORY = "Service Address is mandatory.";
	public static String SKIP_FEASIBILITY_IF_NO_HS_PRODUCT_QUALIFIED = "Skipping Feasibility check because there is no HS product qualified.";
	public static String PRODUCT_REQUEST_ID = "productRequestId";
	public static String CREATE = "Create";
	public static String MODIFY = "Modify";
	public static String SKIP_FEASIBILITY_IF_NO_TTV_QUALIFIED = "No call to Check Product Feasibility was made because TTV is unqualified.";
	public static String ORDER_ACTION_TYPE = "orderActionType";
	public static String SERVICE_PLAN = "servicePlan";
	public static String SERVICE_REQUEST_DATE = "serviceRequestDate";
	public static String NEW_STB_TO_BE_INSTALLED_BY_TELUS = "newSTBsToBeInstalledByTELUS";
	public static String NEW_WLS_STB_TO_BE_INSTALLED_BY_TELUS = "newWlsSTBsToBeInstalledByTELUS";
	public static String TOTAL_NUMBER_OF_STBS="totalNumberOfSTBs";
	public static String STB_ADDED_IND="STBAddedInd";
	public static String YES = "Yes";
	public static final List<String> NO_PORTS_AVAILABLE_ERROR_CODES = Arrays.asList("FCCP06_E1", "PDPS02_E1", "PDPS02_E3", "PDPS02_E4");
	
	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String THREE = "3";
	public static final String ONE_YEAR_IN_MONTHS="12";
	public static final String TWO_YEARS_IN_MONTHS="24";
	public static final String THREE_YEARS_IN_MONTHS="36";
	public static final String MTM_TERM="MTM";
	
	public static final String CUSTOMER_ID = "customerId";
	public static final String BAN = "ban";
	public static final String SALES_TXN_ID = "salesTxnId";
	public static final String ADDRESS_ID = "addressId";
	public static final String PROVINCE_STATE_CODE = "provinceStateCode";
	public static final String CUSTOMER_TYPE = "customerType";
	public static final String NPA_NXX_LIST = "npaNxxList";
	public static final String BRAND_ID = "brandId";
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_CATALOG_ID = "productCatalogId";
	public static final String PRODUCT_NUMBER = "productNumber";
	public static final String COT_IND="cotInd";
	public static final String PRODUCT_CHARACTERISTIC_NAME="productCharacteristicName";
	public static final String PRODUCT_CHARACTERISTIC_VALUE_TYPE="productCharacteristicValueType";
	public static final String PRODUCT_CHARACTERISTIC_VALUE="productCharacteristicValue";
	public static final String PRODUCT_TYPE_CODE="productTypeCode";
	public static final String SERVICE_ID = "serviceId";
	
	public static final String OFFER_ID="offerId";
	public static final String OFFER_TERM="offerTerm";
	public static final String PROMO_CODE="PROMO_CODE";

	public static final String DISCOUNT_TYPE_AMT="AMT";
	public static final String DISCOUNT_TYPE_D="D";
	public static final String DISCOUNT_TYPE_P="P";
	
	public static final String FEASIBILITY_ERROR_TIER_NOT_FEASIBLE="PDSC01_E4";
	
	//NWLN-4541
	public static final String OFFER_CATEGORY = "OFFER_CATEGORY";
	public static final String MANUAL_SWEETENER = "MANUAL_SWEETENER";

	// 2018 Aug release for Pik TV and "nochange"
	public static final String TVX = "TVX";
	public static final String OIS_PRODUCT_INSTANCE_NOCHANGE="nochange";
	
	// NWLN-4580
	public static String SERVICE_TVTYPE = "TVType";

	public static final String BOLT_ON_OFFER_APPLICATION_ID_CHANNEL = "3";
	public static final String BOLT_ON_OFFER_ROLE_ID_REGULAR = "1";
	public static final String CREDIT_VALUE_ESTABLISHED_CUSTOMER = "E";
	public static final String CREDIT_VALUE_RESTRICTED_CUSTOMER = "R";

	public static final String APPLICATION_ID = "applicationId";
	public static final String ROLE_ID = "roleId";
	
	public static final String OFFER_TYPE_CODE_ACCESSORY_WLN_GWP = "ACCESSORY_WLN_GWP";
	
	public static final String OFFER_VALIDATION_RULE_MESSAGE = "Accessory Offer is ineligible for the customer";
	
	public static final String UNKNOWN_ACCOUNT = "99999";
	
	public static final String SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER = "JOIN_SALES_OFFER";
	public static final String SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE = "TRUE";
	public static final String SYSTEM_INTEGRATION_PARAMETER_SUPRESS_CAMPAIGN_OFFER = "SUPRESS_CAMPAIGN_OFFER";
	
	public static final String ORDER_ID = "orderId";
	public static final String SYSTEM_INTEGRATION_PARAMETER_BY_PASS_VALIDATION = "BYPASS_VALIDATION_IND";
	public static final String SYSTEM_INTEGRATION_PARAMETER_BYPASSIND_VALUE = "true";
	
	public static final String SHOPPING_CART_CTX_TYPE_ALL="ALL";
	public static final String SHOPPING_CART_CTX_TYPE_NEW="NEW";
	public static final String SHOPPING_CART_CTX_TYPE_UPDATE="UPDATE";

	//EQUIPMENT, OFFER, ADDON, PROMOTION, SWEETENER, ACCESSORY
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_EQUIPMENT = "EQUIPMENT";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_OFFER = "OFFER";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_ADDON = "ADDON";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_PROMOTION = "PROMOTION";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_SWEETENER = "SWEETENER";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_ACCESSORY = "ACCESSORY";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_PRODUCT_ITEMS="PRODUCT_ITEMS";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_FEATURE = "FEATURE";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_ALL = "ALL";
	
	//Atlas
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_PACK = "PACKS";
	public static final String SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY="SUMMARY";
	
	
	public static final String PRICING_TYPE_RECURRING = "RECURRING";
	public static final String PRICING_TYPE_ONE_TIME = "ONE_TIME"; 
	public static final String ACQUISITION_TYPE_RENT = "RENT";
	public static final String ACQUISITION_TYPE_BUY = "BUY";
	public static final String PRICE_TYPE_DISCOUNT = "DISCOUNT";
	
	public static final String DEPOSIT_CALCULATION_TYPE_ESTIMATED = "ESTIMATE";
	public static final String DEPOSIT_CALCULATION_TYPE_FINAL = "FINAL";
	

	public static final String COMP_PRODUCT_INFORMATION = "productInformation";
	public static final String COMP_GENERAL_PRODUCT_INFO = "generalProductInfo";
	public static final String COMP_MARKET_OFFER_INFO = "MarketOfferInfo";
	public static final String COMP_ORDER_ACTION_ATTRIBUTES = "orderActionAttributes";
	public static final String COMP_SERVICE_ADDRESS = SERVICE_ADDRESS;
	public static final String COMP_DIRECTORY_LISTING_COMPONENT = "Directory Listing Component";
	public static final String COMP_SHIPPING_ADDRESS = "shippingAddress";
	public static final String COMP_LSR_DATA = "lsrData";
	
	public static final String OP_STREET_NAME = "streetName";
	public static final String OP_STREET_NUMBER = "streetNumber";
	public static final String OP_VECTOR = "vector";
	public static final String OP_APPARTMENT = "apartment";
	public static final String OP_CITY = "city";
	public static final String OP_STATE = "state";
	public static final String OP_COUNTRY = "country";
	public static final String OP_POSTALCODE = "postcode";
	public static final String OP_POSTOFFICEBOX = "postOfficeBox";
	public static final String OP_FMS_ADDRESS_ID = "fmsAddressId";
	public static final String OP_RATECENTRE = "rateCentre";
	public static final String OP_CITY_CLICODE = "cityClliCode";
	public static final String OP_RETURN_METHOD = "returnMethod";
	
	public static String OP_CLEC = "CLEC";
	public static String OP_CLEC_CAT_ID = "21074428";
	public static String OP_CLEC_OFFER_CAT_ID = "21074368";
	public static enum OP_CLEC_PROD_CHARS {importLSPName, loopMigrationInd, hsClecInd, tvClecInd, tnClecInd, secTnClecInd, secondaryName, hsEAN, tvEAN, oldServiceAddressHouseNumber, 
								oldServiceAddressStreetName, oldServiceAddressCity, oldServiceAddressProvince, oldServiceAddressPostalCode, tnPrimary, reseller, importPrimaryTN, tnEanPrimary, csProvider, importSmartRingTN, tnEanSmartRing, csProviderSmartRing};
	public static final String FULFILLMENT_MANUAL = "MANUAL";

	public static final String FEASIBILITY = "FEASIBILITY";
	public static final String O_OA_CONFIG = "O_OA_CONFIG";
	public static final String PRICING = "PRICING";
	public static final String FAILURE = "Failure";
	
	public static final String CART_ACTION_ADD = "ADD";
	public static final String CART_ACTION_MODIFY = "MODIFY";
	
	public static final String CART_ITEM_ACTION_ADD = "ADD";
	public static final String CART_ITEM_ACTION_MODIFY = "CHANGE";
	
	public static final String PRODUCT_ITEM_ACTION_ADD = "ADD";
	public static final String PRODUCT_ITEM_ACTION_REMOVE = "REMOVE";
	public static final String PRODUCT_ITEM_ACTION_NO_CHANGE = "NO_CHANGE";
	

	public static final String ESDG_CONTEXT_ID_PREFIX = "ES";
	public static final String ESDG_INTERACTION_ID_PREFIX = "SC";
	public static final String ESDG_SALES_ITEM_ID_PREFIX = "CSI";
	
	// Wireless STB 
    public static final String WIRELESS_STB_MIC_BUY = "1726174"; 
    public static final String WIRELESS_STB_MIC_RENT = "1726175"; 
    public static final String WIRELESS_OPTIK_TV_4K_STB_MIC_BUY = "2314989"; 
    public static final String WIRELESS_OPTIK_TV_4K_STB_MIC_RENT = "2314990";
    public static final String WIRED_EQUIPMENT = "WIRED";
    public static final String WLS_EQUIPMENT = "WLS";
	
    public static final String LINE_OF_BUSINESS = "LINE_OF_BUSINESS";
    public static final String SALES_ITEM_FULFILLMENT = "SALES_ITEM_FULFILLMENT";
    public static final String MASTER_SOURCEID_CODS = "1001";
    public static final Long APPLICATION_ID_WLN_SALES = Long.valueOf(9922);
	
	
	 //SL EPC Rules constants.
    public static final String TELUS_HOME_PHONE_LITE_EXTERNAL_CID = "40671421";
    public static final String TELUS_HOME_CONNECTIONS_BUNDLE3_EXTERNAL_CID = "55064";
    public static final String LOCAL_LINE_EXTERNAL_CID = "32628";
    public static final String SL_FEATURE_CALL_DISPLAY_EXTERNAL_CID = "45";
    public static final String SL_FEATURE_CALL_RETURN_EXTERNAL_CID = "48";
    public static final String SL_FEATURE_3WAY_CALLING_EXTERNAL_CID = "53";
    
    public static final String OFFERS = "OFFERS";
    public static final String SHOPPING_CART = "SHOPPING_CART";
    public static final String INSTALLATION_DETAILS = "SOAP_INST_DETAILS";
    public static final String FEASIBILITY_CALL_ORIGINATOR = "originator";
    public static final String DELIVERY_METHOD_INSTALLER="INSTALLER";
    
    //Rest specific constants.
	public static final String SL = "SL";
	public static final String ACTION_CD = "ACTION_CD";
	public static final String ACTION_ADD = "ADD";
	public static final String ACTION_REMOVE = "REMOVE";
	public static final String ACTION_NO_CHANGE = "NO_CHANGE";
	public static final String MARKET_OFFER_CLASSIFICATION = "MARKET_OFFER_CLASSIFICATION";
	public static final String CLS_PRODUCT_COMPONENT = "PRODUCT_COMPONENT";
	public static final String CLS_ADD_ON = "ADD_ON";
	public static final String CLS_DISCOUNT = "DISCOUNT";
	public static final String CLS_SWEETENER = "SWEETENER";
	public static final String CLS_CALLING_FEATURE = "CALLING_FEATURE";
	public static final String DISCOUNT_CODE = "DISCOUNT_CODE";
	
	public static final String EXTERNAL_SYSTEM_OMS = "OMS";
	
	public static final Long ORIGINATOR_APPLICATION_ID_9919 = 9919L;
	public static final Long ORIGINATOR_APPLICATION_ID_9922 = 9922L;
	
	public static final String DISCOUNT_TYPE_CD_ADD_ON = "ADD-ON";

	public static final String BOOST_WIFI_STARTER_PRODUCT_CATALOGUE_ID = "1543027";
	public static final String BOOST_WIFI_EXPANSION_PRODUCT_CATALOGUE_ID = "1543028";
	
	public static final String PROD_CATLG_ITM_TYPE_CD_PPL = "PPL";
	
	public static final String PAYMENT_OPTION_TYPE_CD_FINANCE = "FINANCE";
	public static final String PAYMENT_OPTION_TYPE_CD_ONETIME = "ONE_TIME";
	public static final String INSTALLMENT_OPTION_CD_OPTIONAL = "OPTIONAL";
	public static final String INSTALLMENT_OPTION_CD_MANDATORY = "MANDATORY";
	public static final String PRODUCT_ENFORCEMENT_GROUP_CD_MANDATORY = "MANDATORY";
	public static final String PRODUCT_ENFORCEMENT_GROUP_CD_ONEOF = "ONE_OF";
	
	public static final String ACQUISITION_TYPE_CD_BUY = "SA";
	public static final String ACQUISITION_TYPE_CD_RENTAL = "RE";
	
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_PRODCUT_CATEGORY_CD = "9153916063813425135";
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_JOB_TYPE_CD = "9153916063813425138";
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_WORDORDER_ACTION_CD = "9153916063813425145";
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_PRODDCT_TECHNOLOGY_CD = "9153916063813425147";
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_INSTALLATION_TYPE_CD = "9153916063813425150";
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_ESTIMATED_DURATION = "9153916075013425223";
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_ESTIMATED_DURATION_UNIT_CD = "9153915818913425108";
	public static final String FIFA_WORK_OFFER_PROD_CHARAC_ORIGINATING_SYSTEM_ID = "9152983464813388207";

	public static final String FIFA_EQUIPMENT_OFFER_CHARAC_ACQUISISION_TYPE = "9148273745313924544";
	public static final String FIFA_EQUIPMENT_OFFER_ACQUISISION_TYPE_PURCHASED = "9148273745313924550";
	public static final String FIFA_EQUIPMENT_OFFER_ACQUISISION_TYPE_RENTED= "9148273745313924553";
	public static final String FIFA_EQUIPMENT_OFFER_ACQUISISION_TYPE_WARRENTY= "9154712835413660097";
	
	public static final String FIFA_EQUIPMENT_OFFER_CHARAC_PURCHASE_TYPE = "9151550795513408112";
	public static final String FIFA_EQUIPMENT_OFFER_PURCHASE_TYPE_ONETIME = "9151619944313285435";
	public static final String FIFA_EQUIPMENT_OFFER_PURCHASE_TYPE_EASYPAY= "9151550795513408113";
	
	public static final String FIFA_EQUIPMENT_OFFER_CHARAC_DELIVERY_MOD = "9147992184813847362";
	public static final String FIFA_EQUIPMENT_OFFER_DELIVERY_MOD_INSTALLER_SUPPLIED = "9148051600313877248";
	public static final String FIFA_EQUIPMENT_OFFER_DELIVERY_MOD_SHIPPING = "9148051600313877245";
	public static final String FIFA_EQUIPMENT_OFFER_DELIVERY_MOD_MANUAL = "9148811676713984635";
	public static final String FIFA_EQUIPMENT_OFFER_DELIVERY_MOD_NO_NEED = "9149491760913078912"; //No equipment delivery needed 
	public static final String FIFA_EQUIPMENT_OFFER_CHARAC_MIC_CODE = "9144689223813944271";

	public static final String FIFA_EQUIPMENT_OFFER_CHARAC_AUTO_ADD_IND = "9151911739813302300";
	public static final String FIFA_EQUIPMENT_OFFER_AUTO_ADD_TRUE = "9151911928813302515";
	public static final String FIFA_EQUIPMENT_OFFER_AUTO_ADD_FALSE = "9151911928813302517";
	public static final String FIFA_CHAR_ACQUIRED_FROM = "9152694600113929802";
	public static final String FIFA_CHAR_DELIVERY_METHOD = "9155793580913292047";
	public static final String FIFA_CHAR_YOUR_PICK = "9157532415513055907";
	
}
