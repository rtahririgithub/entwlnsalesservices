package com.telus.csm.ewlnsc.transformer;

import static com.telus.csm.ewlnsc.util.Constants.FLAG_FALSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.domain.BookingServiceVO;
import com.telus.csm.ewlnsc.domain.ServiceTypeFeasibilityVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

public class WLNBookingSvcTransformer {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(WLNBookingSvcTransformer.class);
	private static final String BEST_AVAILABLE_CONFIGURATION_ID = "bestAvailableConfigurationInd";
	private static final String CLASS_NAME = "WLNBookingSvcTransformer";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
	
	public static final Integer chunkSize = ApplicationProperties.getConfigInteger("${common/getAvailableInstallDateParams/chunkSize}");
	
	public List<GetAvailableTimeSlotsAdapterRequest> execute(BookingServiceVO bookingVO){
		List<GetAvailableTimeSlotsAdapterRequest> requestList = new ArrayList<GetAvailableTimeSlotsAdapterRequest>();
		String functionName="WLNBookingSvcTransformer.execute()";
		logger.enter(functionName);
		
		
		/*
		 * 1. Creates a map that will hold the products and it's respective installationType
		 */
		
		Map<String,String> productsInstallationTypeMap = buildProductsServiceTypeMap(bookingVO.getFeasibilityAdapterResponse());
		
		/*
		 * 2. Filter any FW, SW products
		 */
		
		
		List<String> filteredProducts = getFilteredProducts(bookingVO,productsInstallationTypeMap);
		
		/*
		 * 3. Generate a combination of products with the remaining filtered products.
		 */
		List<List<String>> productsCombinationLists = generateCombination(bookingVO.isCombinationInd(), filteredProducts);
		
		/*
		 * 4. Adjust the combination list to fit the BookingService request.
		 */
		List<List<String>> finalCombinationList = filterSerivceTypeCombination(productsCombinationLists, productsInstallationTypeMap);
		
		
		if(finalCombinationList!=null && !finalCombinationList.isEmpty()){
			for(List<String> serviceTypeList : finalCombinationList){
				if(!serviceTypeList.isEmpty()){
					logger.info(functionName, "Bulding Request for combination : " + serviceTypeList);
					GetAvailableTimeSlotsAdapterRequest request = new GetAvailableTimeSlotsAdapterRequest();
					if(bookingVO.getServiceAddressResponseVO()!=null && bookingVO.getServiceAddressResponseVO().getServiceAddress()!=null){
						com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress serviceAddress = bookingVO.getServiceAddressResponseVO().getServiceAddress();
						request.setCity(serviceAddress.getMunicipalityName());
						request.setFmsAddressId(serviceAddress.getAddressId());
						request.setProvinceCd(serviceAddress.getProvinceStateCode());
					}
					
					request.setInstallationRequirementsList(EnterpriseWLNCommonTransformer.getInstallationReqListFromFeasibilityResponse(bookingVO.getFeasibilityAdapterResponse(), bookingVO.getFeasibilityRequest(), serviceTypeList,bookingVO.getExistingProductList(),bookingVO.isChangeOfTechnology()));
					/*
					 * get lag days to calculate bookingStartDate
					 */
					int lagDays = getLagDays(serviceTypeList, bookingVO.isInboundChannel(), bookingVO.getNguiInstallLagTime(), bookingVO.isDisconnectCartItem());
				
					//#NWLN-8372 - ESS sending dates to OP will not have an extra day for the End date
					bookingVO.setEndDate(bookingVO.getEndDate());
					
					/*******************************************/
					/* remove time from date                   */
					/*******************************************/
					
					bookingVO.setStartDate(EnterpriseSalesServiceUtil.removeTime(bookingVO.getStartDate()));
					bookingVO.setEndDate(EnterpriseSalesServiceUtil.removeTime(bookingVO.getEndDate()));
					
					Date bookingStartDate = calculateBookingStartDate(bookingVO.getStartDate(), lagDays);
					
					/*
					 * Split the date into 7 days chunks - TODO: the new range is 14 days chunks
					 */
					
					/**
					 * if startDate is after endDate, skip. 
					 */
					if(!bookingStartDate.after(bookingVO.getEndDate())){
						ArrayList[] chunkDates = getChunks(bookingStartDate, bookingVO.getEndDate());
						
						int chunkSize = chunkDates[0].size(); 
						for (int i = 0; i < chunkSize; i++){
							String fromDate = (String) chunkDates[0].get(i);
							String toDate = (String) chunkDates[1].get(i);
							
							fromDate = adjustStartingDate(fromDate, toDate);
							// Clone a new object and use the chunked start/end date
							GetAvailableTimeSlotsAdapterRequest chunkedRequest = request.cloneObject();
								 
							chunkedRequest.setFromDate(fromDate);
							chunkedRequest.setToDate(toDate);
							requestList.add(chunkedRequest);
					}
					
				}
			}
		}
		
		}
		logger.exit(functionName);
		return requestList;
	}

	
	private List<String> getFilteredProducts(BookingServiceVO bookingVO,Map<String,String> productsInstallationTypeMap) {
		List<String> filteredProducts = new ArrayList<String>();
		
		
		/*
		 * 1. Generate a map with Infeasible products
		 */
		Map<String,Boolean> infeasibleProductsMap = buildInfeasibleProductsMap(bookingVO.getFeasibilityAdapterResponse()); 
		
		/*
		 * 2. Build map with FeasibilityInfo per product
		 */
		Map<String, ServiceTypeFeasibilityVO> serviceTypeFeasbilitiesMap = EnterpriseWLNCommonTransformer.productWithFeasibilityInfo(bookingVO.getFeasibilityAdapterResponse());
		
		/*
		 * 3. filter serviceTypes:
		 *  a) filter out any product for which the installationType is SW.
		 *  b) filter out any infeasible product.
		 */
		
		filteredProducts = filterServiceTypes(productsInstallationTypeMap, infeasibleProductsMap, serviceTypeFeasbilitiesMap);
		
		
		return filteredProducts;
	}		




	private Map<String, Boolean> buildInfeasibleProductsMap(
			CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse) {

		String functionName = "buildInfeasibleServiceTypeMap";
		logger.enter(functionName);
		Map<String, Boolean> infeasibleProductsMap = new HashMap<String, Boolean>();

		if(checkProductFeasibilityAdapterResponse != null 
				&& checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList() != null
				&& checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo() != null) {
			for (ProductFeasibilityInfo info : checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo()) {
	
				String bestAvailableConfigurationInd = "";
	
				for (ProductSpecificationCharacteristic productChar : info.getProductSpecification()
						.getProductSpecificationCharacteristics()) {
	
					if (productChar.getName().equalsIgnoreCase(BEST_AVAILABLE_CONFIGURATION_ID)) {
	
						for (CompositeProductSpecificationCharacteristicValue charValues : productChar
								.getProductSpecificationCharacteristicValues()) {
							bestAvailableConfigurationInd = charValues.getValue();
						}
					}
				}
	
				// Get the workTime
				double defaultWorkTime = 0.0d;
				String feasibilityResult = "";
				if (info.getProvisioningInformation() != null) {
					defaultWorkTime = info.getProvisioningInformation().getWorkTimeDefault();
				}
				if (info.getFeasibilityResult() != null) {
					feasibilityResult = info.getFeasibilityResult().trim();
				}
	
				if (bestAvailableConfigurationInd != null && bestAvailableConfigurationInd.length() == 0)
					bestAvailableConfigurationInd = FLAG_FALSE;
	
				if (feasibilityResult.equalsIgnoreCase(FLAG_FALSE)
						&& bestAvailableConfigurationInd.equalsIgnoreCase(FLAG_FALSE)) {
					infeasibleProductsMap.put(info.getProductSpecification().getName(), true);
				} else if (feasibilityResult.length() == 0 && defaultWorkTime > 0.0d) {
					infeasibleProductsMap.put(info.getProductSpecification().getName(), false);
				} else {
					infeasibleProductsMap.put(info.getProductSpecification().getName(), false);
				}
			}
		}
		
		Set<String> keySetInf = infeasibleProductsMap.keySet();
		for (String serviceType : keySetInf) {
			if (infeasibleProductsMap.get(serviceType)) {
				logger.info(functionName, "List of Infeasible Products: " + serviceType);
			}
		}
		
		logger.exit(functionName);
		return infeasibleProductsMap;
	}
	
	private Map<String, String> buildProductsServiceTypeMap(
			CheckProductFeasibilityAdapterResponse feasibilityAdapterResponse) {
		String functionName="buildProductsServiceTypeMap";
		Map<String, String> productsInstallationTypeMap = new HashMap<String, String>();
			if (feasibilityAdapterResponse!=null && feasibilityAdapterResponse.getProductFeasibilityInfoList() != null &&
					! feasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo().isEmpty()){
					
					for (ProductFeasibilityInfo info : feasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo()){
						if(info.getProvisioningInformation()!=null ){
							if (!StringUtils.isBlank(info.getProvisioningInformation().getRecommendedInstallType())) {
								String installType = info.getProvisioningInformation().getRecommendedInstallType();
								String serviceType = info.getProductSpecification().getName();
								productsInstallationTypeMap.put(serviceType, installType);
							} else {
								logger.info(functionName,"No recommended installType for serviceType: "+ info.getProductSpecification().getName()+ " was returned in checkProductFeasibilityResponse");
							}
						}
					}
				}
		
		return productsInstallationTypeMap;
	}
	
	public List<String> filterServiceTypes(Map<String, String> serviceTypeInstallationTypeMap,
			Map<String, Boolean> infeasibleProductsMap,
			Map<String, ServiceTypeFeasibilityVO> serviceTypeFeasibilityMap) {
		List<String> serviceTypes = new ArrayList<String>();

		/**
		 * build a list of service types that return from feasibilityService.
		 */
		for (ServiceTypeFeasibilityVO feasibilityVO : serviceTypeFeasibilityMap.values()) {
			if (feasibilityVO != null && feasibilityVO
					.getProductSpecificationName() != null ) {
				serviceTypes.add(feasibilityVO.getProductSpecificationName());
			}
		}

		serviceTypes = this.filterSW(serviceTypes, serviceTypeInstallationTypeMap);
		serviceTypes = this.filterInfeasibleServiceTypes(serviceTypes, infeasibleProductsMap);
		return serviceTypes;
	}
	
	private List<String> filterSW(List<String> serviceTypes, Map<String, String >serviceTypeInstallationTypeMap){
		String functionName = "filterSW";
		List<String> nonSWList = new ArrayList<String>();
		List<String> swList = new ArrayList<String>();
		logger.info(functionName, "Installation Type per ServiceType: " + serviceTypeInstallationTypeMap.toString());
		for (String serviceType : serviceTypes){
			String installationType = serviceTypeInstallationTypeMap.get(serviceType);
			
			// for infeasible service, installation type is null
			if (installationType == null){
				installationType = "";
			}
			if (! installationType.equalsIgnoreCase(Constants.SOFTWARE)){
				nonSWList.add(serviceType);
			}else{
				swList.add(serviceType);
			}
			
		}
		if (!swList.isEmpty()) {
			logger.info(functionName, "List of SW products: " + swList.toString());
		}
		logger.info(functionName, "List of Non SW products: " + nonSWList.toString());
		return nonSWList;
	}
	
	private List<String> filterInfeasibleServiceTypes(List<String> serviceTypes, Map<String, Boolean> infeasibleProductsMap){
		
		List<String> feasibleServiceTypeList = new ArrayList<String>();
		
		for (String serviceType : serviceTypes){ 
			 
			if ( ! infeasibleProductsMap.get(serviceType)){
				feasibleServiceTypeList.add(serviceType);
			}
			
		}
		return feasibleServiceTypeList;
	}
	
	
	public List<List<String>> generateCombination(Boolean combinationInd, List<String> requestList){
		List<List<String>> combinationList = new ArrayList<List<String>>();
		
		/**
		 * if combinationInd = true, call generationCombination to generate all combination of the serviceType
		 * otherwise, it will just be a single list of service type.  Ensure the list of serviceTypes is sorted
		 */
		if (combinationInd != null && combinationInd){
			combinationList = generationCombination(requestList);
			for (List<String> serviceList : combinationList){
				Collections.sort(serviceList);
			}
		} else {
			combinationList.add(requestList);
		}
		 
		String functionName = CLASS_NAME + ".generateCombination"; 
		logger.info(functionName, "*************************** Combination Generated *******************************\n"); 
		logger.info(functionName,buildStringFromList(combinationList));
		logger.info(functionName, "*********************************************************************************************\n");
		return combinationList;
	}
	
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
	
	public static String buildStringFromList(List<List<String>> stringList){
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (List<String> item : stringList ){
			sb.append("* ");
			sb.append(StringUtils.join(item) + " *\n");
		}
		
		return sb.toString();
	}
	
	public List<List<String>> filterSerivceTypeCombination(List<List<String>> serviceTypeCombinationLists,
			Map<String, String> serviceTypeInstallationTypeMap) {
		/**
		 * 
		 * If request service type has TTV and HSIC do not call bookingService
		 * for TTV, TTV's booking dates will be replace by HSIC+TTV
		 * 
		 * IF request service type has TTV, HSIC and SING do not call
		 * bookingService for TTV, TTV's booking dates will be replace by
		 * HSIC+TTV do not call bookingService for TTV + SING, replace result
		 * from HSIC+SING+TTV
		 * 
		 */

		String functionName = "filterSerivceTypeCombination";
		logger.enter(functionName);
		boolean removeTtvInd = this.hasTtvHsic(serviceTypeInstallationTypeMap);

		if (removeTtvInd) {
			serviceTypeCombinationLists.remove(Arrays.asList(EnterpriseWLNSalesServicesConstants.TTV));
			serviceTypeCombinationLists.remove(Arrays.asList(EnterpriseWLNSalesServicesConstants.SING,EnterpriseWLNSalesServicesConstants.TTV));
		}
		if (serviceTypeCombinationLists.isEmpty()) {
			logger.info(functionName, "No combinations are available to call BookingSvc");
		}
		return serviceTypeCombinationLists;
	}
	
	/*************************************************/
	/* check if combination TTV, HSIC(non SW) exists */
	/*************************************************/
	private boolean hasTtvHsic(Map<String, String> serviceTypeInstallationType){
 		
		boolean hasTTV = serviceTypeInstallationType.containsKey(Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV);
		boolean hasHSIC = serviceTypeInstallationType.containsKey(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS);
		
		//Gary fix 2018-01-09
		if (hasTTV && serviceTypeInstallationType.get(Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV).equals(Constants.SOFTWARE))
			hasTTV = false;
		
		if (hasHSIC && serviceTypeInstallationType.get(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS).equals(Constants.SOFTWARE))
			hasHSIC = false;
		
		if (hasTTV && hasHSIC){ 
			return true;
		}
		return false;
	}
	
	public int getLagDays(List<String> serviceTypes, boolean isInboundChannel, Map<String, String> lagTimeMap, boolean isDisconnectCartItem) {
		String functionName="getLagDays";
		int maxLagDays = 0;
		Integer lagDays = null;
//assumption is the the disconnect lag time is greater the normal install lag time.
		try {
			if (isDisconnectCartItem) {
				if (isInboundChannel) {
					lagDays = Integer.parseInt(lagTimeMap.get("SIMPLE_SWITCH_IN"));
				}
				else {
					lagDays = Integer.parseInt(lagTimeMap.get("SIMPLE_SWITCH_OUT"));
				}

				if ( (lagDays != null) && (lagDays > maxLagDays) ) {
					maxLagDays = lagDays;
				}
			}
			else {
				for (String serviceType : serviceTypes) {
					if (isInboundChannel) {
						serviceType += "_IN";
					}
					else {
						serviceType += "_OUT";
					}
					/**
					 * ensure lag time is there, otherwise raise exception 
					 */
					if (!lagTimeMap.containsKey(serviceType)) {
						logger.error("fail to get lagtime for serviceType => " + serviceType);
					}
					
					lagDays = Integer.parseInt(lagTimeMap.get(serviceType));
						
					if ( (lagDays != null) && (lagDays > maxLagDays) ){
						maxLagDays = lagDays;
					}
				}
			}
		}
		catch(Exception e) {
			logger.error("", functionName, e.getMessage(),e);
		}

		return maxLagDays;
	}
	
	public Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public Date calculateBookingStartDate(Date startDate, int lagDays) { 
		String functionName="calculateBookingStartDate";
		Date bookingDate = new Date();
		
		try{ 
			Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
			  
			Date currentDatePlusLag = this.addDays(dateWithoutTime, lagDays);
			int dayDiff = currentDatePlusLag.compareTo(startDate);
			if(dayDiff>0){
				//Meaning: currentDatePlusLag is after ESS startDate
				bookingDate = currentDatePlusLag;
			}else if(dayDiff<0){
				//Meaning: currentDatePlusLag is before ESS startDate
				bookingDate = startDate;
			}else{
				//Meaning: currentDatePlusLag is equal to ESS startDate
				bookingDate = startDate;
			}
			
 
		} catch(Exception e){
			logger.error("", functionName, e.getMessage(),e);
		}
			
		return bookingDate;
	}
	
	public ArrayList[] getChunks(Date fromDate, Date toDate) {
		
		ArrayList[] result = new ArrayList[2];
		
		try { 
			
			Date startDate = fromDate; 
			
			ArrayList<String> batchBeginDates = new ArrayList<String>();
			ArrayList<String> batchEndDates = new ArrayList<String>();
			
			while (startDate.before(toDate) || startDate.equals(toDate)) { 
				
				batchBeginDates.add(sdf.format(startDate));
				
				/**
				 * add CHUNK_SIZE(7 days) to endDate
				 */
				Date batchEndDate = this.addDays(startDate, chunkSize);
				
				if (batchEndDate.after(toDate)){
					batchEndDate = toDate;
				}
				batchEndDates.add(sdf.format(batchEndDate));
				
				/**
				 * the next startDate is current startDate + CHUNK_SIZE.  Cannot use batchEndDate cause it will cause an endless loop
				 */
				startDate = this.addDays(startDate, chunkSize);
			}
			
			result[0] = batchBeginDates;
			result[1] = batchEndDates;
			
		} catch(Exception e) {
			result[0] = new ArrayList<String>();
			result[1] = new ArrayList<String>();
			logger.error(null,"getChunks","Exception happened while calculating days chunks -> " + e.getMessage(),e);
		}
		return result;
	}
	
	public String adjustStartingDate(String fromDate, String toDate){
		/**
		 * if the fromDate and toDate are same, subtract 1 day from fromDate
		 */
		try{
			if (! fromDate.equals(toDate)){
				return fromDate;
			}
			Date newFromDate = sdf.parse(fromDate);
			newFromDate = this.addDays(newFromDate, -1);
			
			return sdf.format(newFromDate);
			
		} catch(Exception e){
			logger.error(null,"adjustStartingDate","Fail to adjust startdate exception -> " + e.getMessage(),e);
		}
		return fromDate;
		
	}
	
}
