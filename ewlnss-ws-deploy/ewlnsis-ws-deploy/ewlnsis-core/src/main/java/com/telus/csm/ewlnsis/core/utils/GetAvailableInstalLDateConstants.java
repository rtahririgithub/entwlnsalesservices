package com.telus.csm.ewlnsis.core.utils;

import java.util.Arrays;
import java.util.List;

import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.Constants;

public class GetAvailableInstalLDateConstants {

	public static final String BEST_AVAILABLE_CONFIGURATION_ID = "bestAvailableConfigurationInd";
	

	public static final String CH = "CH";
	public static final String PR = "PR";
	public static final String CHANGE = "change";
	public static final String PROVIDE = "provide";
	public static final String CREATE = "Create";
	public static final String MODIFY = "Modify";
	
	
	public static final String PRODUCT_REQUEST_ID   = "productRequestId";
	public static final String ORDER_ACTION_TYPE    = "orderActionType";
	public static final String SERVICE_PLAN         = "servicePlan";
	public static final String SERVICE_REQUEST_DATE = "serviceRequestDate";
	public static final String STB_ADDED_IND        = "STBAddedInd";
	public static final String NEW_STBS_TO_BE_INSTALLED_BY_TELUS = "newSTBsToBeInstalledByTELUS";
	public static final String TVX = "TVX"; //NWLN-4594
	
	public static final String DATE_FORMAT_PRODUCT_REQUEST = "yyyyMMddHHmmssS";
	public static final String DATE_FORMAT_SERVICE_REQUEST = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DATE_ONLY       = "yyyy-MM-dd";
	public static final String DATE_FORMAT_BOOKING_SERVICE = "yyyyMMdd"; 
	 
	public static final String AUTO_D2C = "AUTO__D2C";
	public static final String AUTO_CHN = "AUTO_CHN";
	public static final String CACHE_SEPARATOR="_";
	public static final String OPERATION_NAME="getAvailableInstallDates";
	public static final String SALES_TXN_ID="salexTxnId";
	public static final String CUSTOMER_ID="customerId";
	public static final String ADDRESS_ID="addressId";
	public static final String PROVINCE_STATE_CODE="provinceStateCode";
	public static final String PRODUCT_NAME="productName";
	public static final String BRAND_ID="brandId";
	public static final String SERVICE_PLAN_CD="servicePlan";
	public static final String PRODUCT_CATALOG_ID="productCatalogId";
	public static final String SERVICE_ID="serviceId";
	
	public static final String INSTALL_DATES_EXCLUSION = "INSTALL_DATES";
	
//	public static final Integer MAX_DAYS = 0;
//	public static final Integer CHUNK_SIZE = 0;
	
	public static List<String> getHsicTTVList(){
		List<String> HSIC_TTV = Arrays.asList(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS, 
                Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV);
		return HSIC_TTV;
	}
	
	public static List<String> getSingTTVList(){
		List<String> SING_TTV      = Arrays.asList(Constants.CUSTOMER_ODS_PRODUCT_TYPE_SL,
                Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV);
		return SING_TTV;
	}
	
	public static List<String> getTTVList(){
		List<String> TTV           = Arrays.asList(Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV);
		return TTV;
	}
	
	public static List<String> getHsicSingTtvList(){
		List<String> HSIC_SING_TTV = Arrays.asList(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS, 
                Constants.CUSTOMER_ODS_PRODUCT_TYPE_SL,
                Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV);
		return HSIC_SING_TTV;
	}

	public static final String FEASIBILITY_SERVICE = "FeasibilityService.checProductFeasibility";
	
	public static final String customerProductTypeTTV = ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeTTV}");
	public static final String customerProductTypeHS = ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeHSIC}");
	public static final String customerProductTypeSL = ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeSING}");
	public static final String customerProductTypeSTV = ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeSTV}");
	public static final Integer maxDays = ApplicationProperties.getConfigInteger("${common/getAvailableInstallDateParams/maxDays}");
	public static final Integer chunkSize = ApplicationProperties.getConfigInteger("${common/getAvailableInstallDateParams/chunkSize}");

	
	public static String getCustomerProductTypeTTV(){
		return customerProductTypeTTV;
	}
	
	public static String getCustomerProductTypeHS(){
		return customerProductTypeHS;
	}
	
	public static String getCustomerProductTypeSL(){
		return customerProductTypeSL;
	}
	
	public static String getCustomerProductTypeSTV(){
		return customerProductTypeSTV;
	}
	
	public static Integer getMaxDays(){ 
		return maxDays;
	}
	
	public static Integer getChunkSize(){
		return chunkSize;
	}
	
	
	
	
}
