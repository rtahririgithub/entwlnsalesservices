/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.csm.ewlnsc.util;


/**
 * The Interface SalesServiceConstants.
 */
public abstract class SalesServiceConstants {

	public static final String ENABLER_BILLING_ACCOUNT_SYSTEM_CODE = "1001";
	
	public static final String APPLICATION_ID_DEFAULT = "11271";
	
	/** The Constant PRESALES_SVC_CONFIG. */
	protected static final String[] PRESALES_SVC_CONFIG = {"SalesServices", "SalesPresaleService"};

	/** The Constant SUBSCRIPTION_NUMBER_TYPE_CODE_PRIMARY. */
	public static final String SUBSCRIPTION_NUMBER_TYPE_CODE_PRIMARY = "Primary Subscription Number";

	/** The Constant CODS_BILLING_ADDRESS_TYPE_CODE_CANADA. */
	public static final String CODS_BILLING_ADDRESS_TYPE_CODE_CANADA = "C";

	/** The Constant CODS_BILLING_ADDRESS_TYPE_CODE_USA. */
	public static final String CODS_BILLING_ADDRESS_TYPE_CODE_USA = "U";

	/** The Constant ACCOUNT_STATUS_OPEN. */
	public static final String ACCOUNT_STATUS_OPEN = "O";

	/** The Constant ACCOUNT_STATUS_TENTATIVE. */
	public static final String ACCOUNT_STATUS_TENTATIVE = "T";

	/** The Constant ACCOUNT_STATUS_SUSPEND. */
	public static final String ACCOUNT_STATUS_SUSPEND = "S";

	/** The Constant CODS_ACCOUNT_STATUS_CANCELLED. */
	public static final String CODS_ACCOUNT_STATUS_CANCELLED = "C";

	/** The Constant KB_ACCOUNT_STATUS_CANCELLED. */
	public static final String KB_ACCOUNT_STATUS_CANCELLED = "N";

	/** The Constant CODS_ACCOUNT_STATUS_CLOSED. */
	public static final String CODS_ACCOUNT_STATUS_CLOSED = "L";

	/** The Constant KB_ACCOUNT_STATUS_CLOSED. */
	public static final String KB_ACCOUNT_STATUS_CLOSED = "C";

	/** The Constant ACCOUNT_TYPE_BUSINESS. */
	public static final String ACCOUNT_TYPE_BUSINESS = "BUSINESS";

    /** The Constant ACCOUNT_TYPE_CONSUMER. */
    public static final String ACCOUNT_TYPE_CONSUMER = "CONSUMER";

    /** The Constant Wireless_ProductType_PCS. */
    public static final String WIRELESS_PRODUCTTYPE_PCS = "C";

    /** The Constant Wireless_ProductType_IDEN. */
    public static final String WIRELESS_PRODUCTTYPE_IDEN = "I";

    /** The Constant Wireless_ProductType_PAGER. */
    public static final String WIRELESS_PRODUCTTYPE_PAGER = "P";

	/** The Constant LOCAL_EN_CA. */
	public static final String LOCAL_EN_CA = "EN_CA";

	/** The Constant HSIC_PACKS_SERVICE_CODE. */
	public static final String HSIC_PACKS_SERVICE_CODE = "PCKS";

	/** The Constant HSIC_HIGH_SPEED_PACK_SERVICE_CODE. */
	public static final String HSIC_HIGH_SPEED_PACK_SERVICE_CODE = "ADSP";

	/** The Constant SING_CALLING_FEATURES_SERVICE_CODE. */
	public static final String SING_CALLING_FEATURES_SERVICE_CODE = "FTR";

	/** The Constant SING_SWITCH_FEATURE_SERVICE_CODE. */
	public static final String SING_SWITCH_FEATURE_SERVICE_CODE = "SF";

	/** The Constant SING_TTV_NO_LOCAL_PRICE_PLAN_NAME. */
	public static final String SING_TTV_NO_LOCAL_PRICE_PLAN_NAME = "TTV no local";



//	/** The Constant TTV_CODE_TV15. */

    // OMS product
    /** The Constant OMS_PRODUCT_SING. */
    public static final String OMS_PRODUCT_SING = "SING";

    /** The Constant OMS_PRODUCT_TTV. */
    public static final String OMS_PRODUCT_TTV = "TTV";

    /** The Constant OMS_PRODUCT_STV. */
    public static final String OMS_PRODUCT_STV = "STV";

    /** The Constant OMS_PRODUCT_HSIC. */
    public static final String OMS_PRODUCT_HSIC = "HSIC";

    // Sales product type
    /** The Constant SALES_PRODUCT_TYPE_HOME. */
    public static final String SALES_PRODUCT_TYPE_HOME = "SL";

    /** The Constant SALES_PRODUCT_TYPE_SAT_TV. */
    public static final String SALES_PRODUCT_TYPE_SAT_TV = "STV";

    /** The Constant SALES_PRODUCT_TYPE_OPT_TV. */
    public static final String SALES_PRODUCT_TYPE_OPT_TV = "TTV";

    public static final String SALES_PRODUCT_TYPE_SINGLE_LINE = "SING";

//
    /** The Constant SALES_PACK_HS_ZERO_NAME. */
    public static final String SALES_PACK_HS_ZERO_NAME = "HS0";
//
    /** The Constant SALES_PACK_HS_INTERNET_NAME. */
    public static final String SALES_PACK_HS_INTERNET_NAME = "HSR";
//
    /** The Constant SALES_PACK_HS_ENHANCED_NAME. */
    public static final String SALES_PACK_HS_ENHANCED_NAME = "HSEN";
//
//    /** The Constant SALES_PACK_HS_OPTIK_TURBO_NAME. */
//
    /** The Constant SALES_PACK_HS_EXTREME_NAME. */
    public static final String SALES_PACK_HS_EXTREME_NAME = "HSEX";

    /** The Constant SALES_PACK_HS_TURBO50_NAME. */
    public static final String SALES_PACK_HS_TURBO50_NAME = "HST50";

    /** The Constant SALES_PACK_HS_OPTIK_TURBO50_NAME. */
    public static final String SALES_PACK_HS_OPTIK_TURBO50_NAME = "HSOPT50";

    /**
     * Constants used for PhoneNumberSearchOption by Business Connect account types.
     */
    public static final String BC_PHONE_TYPE_WLS = "WLS";
    public static final String BC_PHONE_TYPE_WLN = "WLN";
    public static final String BC_PHONE_TYPE_VOIP_LOCAL = "VOIP-LOCAL";
    public static final String BC_PHONE_TYPE_VOIP_TOLL_FREE = "VOIP-TOLL-FREE";
    
    //////////////////////////////////////////////////////////////////////////
    
    public static final String COUNTRY_CODE_CAN = "CAN";
    public static final String COUNTRY_CODE_US = "US";
    public static final String COUNTRY_CODE_UN = "UN";
    
	public static final String PROPERTY_NAME_SERVICE_ID = "serviceId";
	public static final String PROPERTY_NAME_EXTERNAL_ORDER_SOURCE = "externalRefSrc";
	public static final String PROPERTY_NAME_EXTERNAL_ORDER_ID = "externalRefNum";
	public static final String PROPERTY_NAME_OFFER_CATALOG_ID = "offerCatalogId";
	public static final String PROPERTY_NAME_SERVICE_INSTANCE_ID = "serviceInstanceId";
	public static final String PROPERTY_NAME_PRODUCT_DEPOSIT_AMOUNT = "productDepositAmount";
	
	public static final String WIRELINE_SALES_EXTERNAL_ORDER_SOURCE = "VESTA";
	
	public static final String ORDER_QUERY_STATUS_EXECUTING = "EX";
	public static final String ORDER_QUERY_STATUS_INITIAL = "IN";
	public static final String ORDER_QUERY_STATUS_TO_BE_CANCELLED = "TC";
	public static final String ORDER_QUERY_STATUS_DONE = "DO";
	public static final String ORDER_QUERY_STATUS_CANCELLED = "CA";
	public static final String ORDER_QUERY_STATUS_DISCONTINUED = "DC";
	
	public static final String SYSTEM_ERROR_CODE_300001 = "300001";
	public static final String SYSTEM_ERROR_TXT_300001 = "Dependant service exception";
	
	public static final String RESIDENTIAL = "R";
	
	public static final String ACQUISITION_OFFER_PRODUCTS = "ACQUISITION_OFFER_PRODUCTS";
	public static final String EXISTING_OFFER_PRODUCT_TERMS = "EXISTING_OFFER_PRODUCT_TERMS";
	
	public static final String PRODUCT_INFORMATION="productInformation";


	private SalesServiceConstants (){
		
	}
}
