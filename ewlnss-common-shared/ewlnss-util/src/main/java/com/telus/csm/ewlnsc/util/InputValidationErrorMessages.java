package com.telus.csm.ewlnsc.util;

/**
 * 
 * @author Jose.Mena
 *
 */
public class InputValidationErrorMessages {
	public static final String MANDATORY = " must be provided";
	public static final String MUST_BE_NULL = " must be null";
	public static final String ALPHABETIC = " must be alphabetic";
	public static final String EN_OR_FR = " must be EN or FR";
	public static final String NUMERIC = " must be numeric";
	public static final String ALPHANUMERIC = " must be alphanumeric";
	public static final String PHONE_NUMBER = " must be 10 digits or 11 digits which start with 1";
	public static final String FOUR_TO_SIX_DIGITS = " must be 4 to 6 digits";
	public static final String EMAIL = " must be in email format";
	public static final String YEAR = " must be year";
	public static final String MONTH = " must be month";
	public static final String CREDIT_CARD_EXPIRY_DATE = " must be mm/yy";
	public static final String PAPER_OR_EBILL = " must be Paper or E.Bill";
	public static final String POST_CODE = " must be Canadian postal code";
	public static final String CREDIT_NUMBER = " must be credit number";
	public static final String COMBINATION_FIELDS_MISSING = " - combination of fields is missing";
	public static final String COMBINATION_FIELDS_ADDRESS_MISSING = " - combination of addressid, city and provincecd are missing";
	public static final String FIELDS_MISSING = " - missing";
	public static final String COUNTRY_CODE = " must be 'CAN' or 'USA'.";
	public static final String PIECE_OF_ID_MSG = " at least one piece of id need to be provided.";
	
	public static final String DATE_FORMAT_VALIDATION_FAILURE = "DATE_FORMAT_VALIDATION_FAILURE";
	public static final String INVALID_DATE_FORMAT = "Invalid date format, should be ";
	public static final String INVALID_DATE_OR= " or " ;
	public static final String IS_NOT_VALID = " is not valid";
	public static final String INVALID_CREDIT_CARD_TYPE_MSG ="The valid values for the CreditCardType are 'VI','MC' and 'AM'.";
	
	public static final String INVALID_START_DATE="Invalid start date";
	public static final String INVALID_END_DATE="Invalid end date";
	public static final String INVALID_ADDRESS_ID = "Invalid address - Missing addrressId";
	public static final String INVALID_MUNICIPALITY_NAME="Invalid address - missing municipality name";
	public static final String INVALID_PROVINCE_CODE = "Invalid address - missing provinceStateCode";
	public static final String INVALID_STREET_NAME="Invalid address - missing street name";
	public static final String INVALID_SERVICE_TYPE="Missing service type";
	public static final String INVALID_EXISTING_SERVICE_TYPE="Invalid Service Type";
	public static final String MISSING_SERVICE_PLAN_HSIC="Missing servicePlanCd for HSIC";
	public static final String MISSING_SERVICE_ID="Missing serviceId from subscriber current products";
	public static final String MISSING_PRODUCT_CATALOGUE_ID="Missing productCatalogueId from subscriber current products";
	public static final String SUB_PROD_INVALID_SERVICE_TYPE=" Invalid ServiceType from subscriber current products";
	public static final String MISSING_SERVICE_TYPE_IN_SUBSCRIBER_PRODUCTS = " There is a missing product in subscriberProductList, if the orderedProduct has orderActionType as 'Change', the existing product should be present in subscriberProductList";
}
