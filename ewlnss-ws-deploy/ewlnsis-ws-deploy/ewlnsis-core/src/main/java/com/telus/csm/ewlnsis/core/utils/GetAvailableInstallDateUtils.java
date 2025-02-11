package com.telus.csm.ewlnsis.core.utils;

import static com.telus.csm.ewlnsc.util.Constants.TELUS_BRAND_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.CACHE_SEPARATOR;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.OPERATION_NAME;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.SALES_TXN_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.CUSTOMER_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.ADDRESS_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.PROVINCE_STATE_CODE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.PRODUCT_NAME;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.BRAND_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.SERVICE_PLAN_CD;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.ORDER_ACTION_TYPE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.PRODUCT_CATALOG_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.SERVICE_ID;
import static com.telus.csm.ewlnsis.core.transformer.GetAvailableInstallDateCoreTransformer.productNumberMap;
import static com.telus.csm.ewlnsis.core.transformer.GetAvailableInstallDateCoreTransformer.lastOrderActionTypeCdMap;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.impl.FeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreRequest;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductSummary;

public class GetAvailableInstallDateUtils {

	private static LoggerUtil logger             = LoggerUtil.getLogger("GetAvailableInstallDateUtils");
	
	/**********************************************************/
	/* Generate all combinations of a list                    */
	/*   e.g. if input is TTV, HSIC and SING, will generate   */
	/*        [TTV], [HSIC], [SING], [TTV, HSIC], [TTV, SING] */
	/*        [HSIC, SING], [TTV, HSIC, SING]                 */
	/* code 'borrowed' from                                   */
	/*    https://stackoverflow.com/questions/5162254/all-    */
	/*             possible-combinations-of-an-array          */
	/**********************************************************/
	public static <T> List<List<T>> generationCombination(List<T> list){
		List<List<T>> result = new LinkedList<List<T>>();
	      
	    for (int i = 1; i <= list.size(); i++)
	    	result.addAll(combination(list, i));
	    
	    return result;
	}
	
	private static <T> List<List<T>> combination(List<T> values, int size) {

	    if (0 == size) {
	        return Collections.singletonList(Collections.<T> emptyList());
	    }

	    if (values.isEmpty()) {
	        return Collections.emptyList();
	    }

	    List<List<T>> combination = new LinkedList<List<T>>();

	    T actual = values.iterator().next();

	    List<T> subSet = new LinkedList<T>(values);
	    subSet.remove(actual);

	    List<List<T>> subSetCombination = combination(subSet, size - 1);

	    for (List<T> set : subSetCombination) {
	        List<T> newSet = new LinkedList<T>(set);
	        newSet.add(0, actual);
	        combination.add(newSet);
	    }

	    combination.addAll(combination(subSet, size));

	    return combination;
	}
	
	/*************************************************/
	/* I hate to check for null and empty repeatedly */
	/*************************************************/
	public static boolean isCollectionHasValues(Collection collection){
		if (collection == null || collection.isEmpty())
			return false;
		else
			return true;
	}
	
	
	/*************************************************/
	/* stringToLong                                  */
	/*************************************************/
	public static Long stringToLong(String str){
		long longVal = 0l;
		
		try {
			longVal = Long.parseLong(str.trim()); 
	      } catch (NumberFormatException nfe) {
	    	  logger.error(nfe.getMessage(), "stringToLong", "Fail to convert (" + str + ") to long"); 
	      }
		
		return longVal;
	}
	
	/*************************************************/
	/* if date is valid date                         */
	/*************************************************/
	public static boolean isValidDate(String dateString) {
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	    try {
	    	
	    	if (dateString == null || dateString.trim().length() == 0)
	    		return false;
	    	
	        df.parse(dateString);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
	
	/*************************************************/
	/* if String is valid integer                    */
	/*************************************************/
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	    	logger.error("", "isInteger", e.getMessage(),e);
	    	return false; 
	    } catch(NullPointerException e) {
	    	logger.error("", "isInteger", e.getMessage(),e);
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public static String buildStringFromList(List<List<String>> stringList){
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (List<String> item : stringList ){
			sb.append("* ");
			sb.append(StringUtils.join(item) + " *\n");
		}
		
		return sb.toString();
	}
	
	public static String buildCacheKeyForCheckProductFeasibility(GetAvailableInstallDateCoreRequest req) {
		StringBuilder sbCacheKey = new StringBuilder();
		String customerId = req.getOrderProductList().get(0).getCustomerId();
		String addressId = req.getServiceAddress().getAddressId();
		String proviceStateCd = req.getServiceAddress().getProvinceStateCode();
		String transactionId = req.getOperationHeader().getSalesTransactionId();
			sbCacheKey.append(FeasibilityServiceAdapter.class.getSimpleName());
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(OPERATION_NAME);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(SALES_TXN_ID);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(transactionId);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(CUSTOMER_ID);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(customerId);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(ADDRESS_ID);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(addressId);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(PROVINCE_STATE_CODE);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(proviceStateCd);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(BRAND_ID);
			sbCacheKey.append(CACHE_SEPARATOR);
			sbCacheKey.append(new BigInteger(TELUS_BRAND_ID));
			
			//Iterating over orderedProductList
			for(WirelineProductSummary wlnProduct : req.getOrderProductList()){
				sbCacheKey.append(CACHE_SEPARATOR);
				sbCacheKey.append(PRODUCT_NAME);
				sbCacheKey.append(CACHE_SEPARATOR);
				sbCacheKey.append(wlnProduct.getServiceType());
				sbCacheKey.append(CACHE_SEPARATOR);
				sbCacheKey.append(ORDER_ACTION_TYPE);
				sbCacheKey.append(CACHE_SEPARATOR);
				sbCacheKey.append(lastOrderActionTypeCdMap.get(wlnProduct.getActionTypeCd().toLowerCase()));
				sbCacheKey.append(CACHE_SEPARATOR);
				sbCacheKey.append(SERVICE_PLAN_CD);
				sbCacheKey.append(CACHE_SEPARATOR);
				sbCacheKey.append(wlnProduct.getServicePlanCd());
				
			}
			//Iterating over subscribedProductList
			if (req.getSubscriberProductList() != null && !req.getSubscriberProductList().isEmpty()) { 
				for (WirelineProductSummary existingProduct : req.getSubscriberProductList()) {
					sbCacheKey.append(CACHE_SEPARATOR);
					sbCacheKey.append(PRODUCT_CATALOG_ID);
					sbCacheKey.append(CACHE_SEPARATOR);
					sbCacheKey.append(productNumberMap.get(existingProduct.getServiceType()));
					sbCacheKey.append(CACHE_SEPARATOR);
					sbCacheKey.append(SERVICE_ID);
					sbCacheKey.append(CACHE_SEPARATOR);
					sbCacheKey.append(existingProduct.getProductInstance().getServiceId());
				}
		}
		
		return sbCacheKey.toString();
		
	}

	
}
