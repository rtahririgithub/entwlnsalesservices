package com.telus.csm.esdg;

public final class EsdgConstants {
	//ESDG SALESORDERADM.SALES_CLASSIFIER.SALES_CLASSIFIER_TYP_CD  VARCHAR2(30 CHAR)
	public static final String ESDG_SALES_CLASSIFIER_ORDER_TYPE                 = "ORDER_TYPE";
	public static final String ESDG_SALES_CLASSIFIER_BRAND                      = "BRAND";
	public static final String ESDG_SALES_CLASSIFIER_APP                        = "APP";
	public static final String ESDG_SALES_CLASSIFIER_CHNL_ORG_TYPE              = "CHNL_ORG_TYPE";
	public static final String ESDG_SALES_CLASSIFIER_SALES_PERSON_ROLE          = "SALES_PERSON_ROLE";
	public static final String ESDG_SALES_CLASSIFIER_ACCOUNT_TYPE          		= "ACCOUNT_TYPE";
	public static final String ESDG_SALES_CLASSIFIER_FULFILLMENT_TYPE          	= "FULFILLMENT_TYPE";
	public static final String ESDG_SALES_CLASSIFIER_FULFILLMENT_TYPE_FDA       = "FDA";
	public static final String ESDG_SALES_CLASSIFIER_FULFILLMENT_TYPE_WH_SHIP   = "WH_SHIP";
	public static final String ESDG_SALES_CLASSIFIER_FULFILLMENT_TYPE_SHIP_SWAP = "SHIP_SWAP";
	public static final String ESDG_SALES_CLASSIFIER_UNKNOWN_VALUE              = "UNKNOWN";
	public static final String ESDG_SALES_CLASSIFIER_SAP_SHIMPMENT_STATUS           = "SAP_SHIPMENT_RELEASE_STATUS";
	public static final String ESDG_SALES_CLASSIFIER_SHIMPMENT_UNLOCKED_STATUS     = "UNLOCKED";
	public static final String ESDG_SALES_CLASSIFIER_SAP_MAX_RETRY_STATUS           = "SAP_UNLOCK_MAX_RETRY_STATUS";
	public static final String ESDG_SALES_CLASSIFIER_SAP_MAX_RETRY_TRUE_STATUS     = "TRUE";
	public static final String ESDG_SALES_CLASSIFIER_SHIMPMENT_FAILED_STATUS = "UNLOCK_FAILED";
	public static final String ESDG_SALES_CLASSIFIER_ORDER_STAGE_CD = "ORDER_STAGE_CD";
	public static final String ESDG_SALES_CLASSIFIER_ORDER_STAGE_VALUE = "COMMITED";
	
	//commerce shopping cart classifiers
	public static final String ESDG_SALES_CLASSIFIER_SALES_REP_ID = "SALES_REP_ID";
	public static final String ESDG_SALES_CLASSIFIER_OUTLET_ID = "CHNL_OUTLET_ID";
	public static final String ESDG_SALES_CLASSIFIER_PRODUCT_NAME = "PRODUCT_NAME"; //this usually represent the product line: TTV, HSIA, SING
	public static final String ESDG_SALES_CLASSIFIER_PRODUCT_OFFERING_NAME = "PRODUCT_OFFERING_NM"; //this represent the actual product offering
	
	//Cecil - new classifier
	
	public static final String ESDG_SALES_CLASSIFIER_EMAIL              		= "EMAIL";
	public static final String ESDG_SALES_CLASSIFIER_IMEI              			= "IMEI";
    //New Classifier for Retry scenario
	public static final String ESDG_SALES_CLASSIFIER_ERROR_TYPE                 =  "ERROR_TYPE";
    public static final String ESDG_SALES_CLASSIFIER_RETRY_VALUE                =  "RETRY";
	
	//ESDG SALESORDERADM.SALES_FULFILLMENT.SALES_FULFILLMENT_STATUS_CD  VARCHAR2(10 CHAR)
	public static final String ESDG_FULFILLMENT_STATUS_UNKNOWN                        = "UNKNOWN";
	public static final String ESDG_FULFILLMENT_STATUS_KB_SUCESSFUL                   = "KB_SUCC";
	public static final String ESDG_FULFILLMENT_STATUS_SUCESSFUL                      = "SUCC";
	public static final String ESDG_FULFILLMENT_STATUS_FAILED                         = "FAIL";
	public static final String ESDG_FULFILLMENT_STATUS_ERROR                          = "ERROR";
	public static final String ESDG_FULFILLMENT_STATUS_CANCEL                         = "CANCEL";
	
	//ESDG SALESORDERADM.SALES_FULFILLMENT_TXN_DATA.SALES_FULFILLMENT_TXN_TYP_CD  VARCHAR2(30 CHAR) 
	public static final String ESDG_FULFILLMENT_TXN_TYPE_APPLY_CHARGE                          = "APPLY_CHARGE";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_APPLY_CHARGE_CREDIT                   = "APPLY_CHARGE_CREDIT";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_APPLY_CREDIT                          = "APPLY_CREDIT";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_COMPLETE_EXCHANGE_TO_UNASSIGNED_OFFER = "CMPL_EX_TO_UNASSIGN_OFFER";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_COMPLETE_EXCHANGE_TO_ASSIGNED_OFFER   = "CMPL_EX_TO_ASSIGN_OFFER";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_UPDATE_TEA                            = "CREATE_TEA";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_APPLY_SSF_CHARGE                      = "APPLY_SFF";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_APPLY_HANDSET_DISCOUNT_FLAG           = "HANDSET_DISC_FLAG";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_NOTIFY_SUB_OFFER_FOR_EXCHANGE         = "NOTIFY_OFFER_FOR_EX";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_CREATE_MEMO                           = "CREATE_MEMO";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_STORE_SRPDS                           = "STORE_SRPDS";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_CREATE_FOLLOWUP                       = "CREATE_FOLLOWUP";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_REGISTER_FOR_PARTNER_INTEGRATION      = "REG_PI";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_UPDATE_PAYMENT_METHOD                 = "UPDT_PAYMENT_METHOD";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_RECORD_DEPOSIT_PAYMENT                = "RECORD_DEPOSIT_PAYMENT";
	public static final String ESDG_FULFILLMENT_TXN_TYPE_CREATE_CREDIT_BALANCE_TRANSFER        = "CREATE_CREDIT_BAL_TRANSFER";

	public static final String ESS_REFERENCE_DATA_SERVICE_ESDB_ACCESS_CACHE_NAME = "ESSReferenceDataService_ESDBAccess";
	public static final String ACCESS_ESDB_ENABLED = "AccessESDBEnabled";
	public static final String REFERENCE_DATA_PARTITION_CD = "ReferenceDataPartitionCd";

}
