package com.telus.csm.ewlnsis.core.transformer;

import static com.telus.csm.ewlnsc.util.Constants.D2C_PARTNER;
import static com.telus.csm.ewlnsc.util.Constants.FLAG_FALSE;
import static com.telus.csm.ewlnsc.util.Constants.TELUS;
import static com.telus.csm.ewlnsc.util.Constants.TELUS_BRAND_ID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SERVICE_TVTYPE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.BEST_AVAILABLE_CONFIGURATION_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.CH;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.CHANGE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.CREATE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.DATE_FORMAT_PRODUCT_REQUEST;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.DATE_FORMAT_SERVICE_REQUEST;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.MODIFY;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.NEW_STBS_TO_BE_INSTALLED_BY_TELUS;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.ORDER_ACTION_TYPE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.PR;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.PRODUCT_REQUEST_ID;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.PROVIDE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.SERVICE_PLAN;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.SERVICE_REQUEST_DATE;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.STB_ADDED_IND;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants.TVX;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils.generationCombination;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils.isCollectionHasValues;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils.isValidDate;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils.stringToLong;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.AppointmentTimeDetail;
import com.telus.csm.ewlnsc.adapter.wbk.domain.AppointmentTimeList;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.InstallationRequirementsList;
import com.telus.csm.ewlnsc.domain.AvailableDateTimePeriodVO;
import com.telus.csm.ewlnsc.domain.AvailableInstallDateVO;
import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreRequest;
import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreResponse;
import com.telus.csm.ewlnsc.domain.ServiceTypeFeasibilityVO;
import com.telus.csm.ewlnsc.task.GetAvailableTimeSlotsTask;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.exceptions.BookingDateCalculationException;
import com.telus.csm.ewlnsis.core.exceptions.LagTimeException;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateResponseType.AvailableInstallDatesList;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateType;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.AccessCriteria;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductInstanceList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductServiceInstance;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductSpecificationList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProvisioningInfo;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.base_types_2_0.ValueType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductSummary;
import com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v2.ProductParameter;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecification;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;


/**
 * GetAvailableInstallDateCoreTransformer
 * 
 * @author x159148
 *
 */
public class GetAvailableInstallDateCoreTransformer {
	
	private static final String CLASS_NAME = "GetAvailableInstallDateCoreTransformer";
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableInstallDateCoreTransformer.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
	
	public static final Map<String, String> actionTypeCdMap = new HashMap<String, String>();
	public static final Map<String, String> lastOrderActionTypeCdMap = new HashMap<String, String>();
	public static final Map<String, String> productNumberMap = new HashMap<String, String>();
	static{
		actionTypeCdMap.put(CHANGE.toLowerCase(), CH);
		actionTypeCdMap.put(PROVIDE.toLowerCase(), PR);
		
		lastOrderActionTypeCdMap.put(PROVIDE.toLowerCase(), CREATE);
		lastOrderActionTypeCdMap.put(CREATE.toLowerCase(), CREATE);
		lastOrderActionTypeCdMap.put(CHANGE.toLowerCase(), MODIFY);
		lastOrderActionTypeCdMap.put(MODIFY.toLowerCase(), MODIFY);
		
		
		productNumberMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV, GetAvailableInstalLDateConstants.getCustomerProductTypeTTV());
		productNumberMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS, GetAvailableInstalLDateConstants.getCustomerProductTypeHS());
		productNumberMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_SL, GetAvailableInstalLDateConstants.getCustomerProductTypeSL());
		productNumberMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_STV, GetAvailableInstalLDateConstants.getCustomerProductTypeSTV());
		int maxDays = GetAvailableInstalLDateConstants.getMaxDays();
		int chunkSize = GetAvailableInstalLDateConstants.getChunkSize();
		logger.info("GetAvailableInstallDateCoreTransformer: ",maxDays + " " + chunkSize);
	}
	

 
	

	/*************************************************************/
	/* transformCoreRequestToFeastibilityRequest                 */ 
	/*************************************************************/
	public CheckProductFeasibilityAdapterRequest transformCoreRequestToFeastibilityRequest(GetAvailableInstallDateCoreRequest req){
		
		List<String> hsicTTVList = GetAvailableInstalLDateConstants.getHsicTTVList();
		List<String> hsicSingTtvList = GetAvailableInstalLDateConstants.getHsicSingTtvList();
		List<String> ttvList = GetAvailableInstalLDateConstants.getTTVList();
		List<String> singTTVList = GetAvailableInstalLDateConstants.getSingTTVList();
		logger.info("", hsicTTVList.toString());
		logger.info("", hsicSingTtvList.toString());
		logger.info("", ttvList.toString());
		logger.info("", singTTVList.toString());
		String functionName = CLASS_NAME + ".transformCoreRequestToFeastibilityRequest";
		logger.enter(functionName);
		logger.info(functionName, "SalesTransactionId = " + req.getOperationHeader().getSalesTransactionId());
		logger.info(functionName, "combinationInd     = " + req.getCombinationInd());
		
		for (WirelineProductSummary orderedProduct : req.getOrderProductList()){
			logger.info(functionName, "serviceType = " + orderedProduct.getServiceType() + " actionTypeCd= " + orderedProduct.getActionTypeCd()); 
		}
		
		
		CheckProductFeasibilityAdapterRequest feasibilityRequest = new CheckProductFeasibilityAdapterRequest();
		
		/**
		 * transform OperationHeader
		 */
		feasibilityRequest.setUserId(String.valueOf(req.getOperationHeader().getUserProfile().getSalesRepInternalId()));  
		feasibilityRequest.setTimeStamp(new Timestamp(req.getOperationHeader().getSalesTransactionTimestamp().getTime()));  
		 
		feasibilityRequest.setSalesTransactionId(req.getOperationHeader().getSalesTransactionId());
		
		/**
		 * transform ServiceAddress
		 */
		com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress feasibilityAddress = 
				transformServiceAddress(req.getServiceAddress());
		
		feasibilityRequest.setAddress(feasibilityAddress);
		
		
		/**
		 * transform AccessCriteria
		 */
		feasibilityRequest.setAccessCriteria(this.transformAccessCriteria());
		
		
		/**
		 * transform orderedProductList to productSpecificationList
		 *     note: the requestIdDate must be consistent for the entire request, otherwise feasibilityService will throw EJB exception
		 */
		String requestIdDate = transformCurrentDate(DATE_FORMAT_PRODUCT_REQUEST);
		ProductSpecificationList productSpecificationList = transformOrderedProductList(req.getOrderProductList(), 
				                                                                        req.getOperationHeader().getBrandCode(), 
				                                                                        requestIdDate);
		feasibilityRequest.setProductSpecificationList(productSpecificationList);
		
		/**
		 * transform subscriberProductList to productServiceInstance
		 */
		ProductInstanceList productInstancelist = transformProductServiceInsance(req.getSubscriberProductList(), requestIdDate);
		feasibilityRequest.setProductInstanceList(productInstancelist);
		
		/**
		 * Set the cacheKey for CheckProductFeasibility
		 */
		/*String cacheKey = GetAvailableInstallDateUtils.buildCacheKeyForCheckProductFeasibility(req);
		if (StringUtils.isNotBlank(cacheKey)) {
			feasibilityRequest.setCacheKey(cacheKey);
		}*/
		logger.exit(functionName);
		return feasibilityRequest;
	}
	

 
	/***********************************************/
	/* transformToGetAvailableInstallDateResponse  */
	/***********************************************/
	public GetAvailableInstallDateCoreResponse transformToGetAvailableInstallDateResponse(List<GetAvailableTimeSlotsTask> taskInfoList, 
			                                                                              GetAvailableInstallDateCoreRequest coreRequest,
			                                                                              CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse,String salesTransactionId){ 

		/**
		 * taskInfoList   - provides the input and result from bookingService
		 * installTypeSW  - provides list of serviceType that are SW
		 * coreRequest    - provides list of all orderedServiceType
		 */
		String functionName= CLASS_NAME + ".transformToGetAvailableInstallDateResponse";
		GetAvailableInstallDateCoreResponse response = new GetAvailableInstallDateCoreResponse();
		
		Date requestedEndDate = coreRequest.getEndDate();
		
		/*****************************************************/
		/* merge the result of bookingService tasks and      */
		/* populate response.AvailableInstallDateVO          */
		/*****************************************************/
		Map<List<String>, AvailableInstallDateVO> availableDateVOMap = new HashMap<List<String>, AvailableInstallDateVO>();
		
		for (GetAvailableTimeSlotsTask task : taskInfoList)
		{ 
			GetAvailableTimeSlotsAdapterRequest input     = task.getInput();
			GetAvailableTimeSlotsAdapterResponse result   = task.getResult();
			
			
			/**
			 * get the serviceTypes from input to bookingService for the response
			 */
			List<String> serviceTypeList = new ArrayList<String>();
			for (InstallationRequirementsList requirement : input.getInstallationRequirementsList()){
				serviceTypeList.add(requirement.getProductServiceType());
			}
			Collections.sort(serviceTypeList); //Sorting to match the Combinations in the map bookingServiceRequestsMap
			
			/**
			 * find a matching AvailableInstallDateVO object from map, if not found, create a new object
			 */
			AvailableInstallDateVO availableInstallDateVO = availableDateVOMap.get(serviceTypeList);
			if (availableInstallDateVO == null){
				availableInstallDateVO = new AvailableInstallDateVO();
				availableInstallDateVO.setServiceTypeList(serviceTypeList);
				availableDateVOMap.put(serviceTypeList, availableInstallDateVO);
			}
			
			/**
			 * appointment date and time period
			 */
			if(result!=null){
				if(!result.hasError()){
					if(result.getAppointmentTimeList()!=null && result.getAppointmentTimeList().getAppointmentTimeList()!=null && ! result.getAppointmentTimeList().getAppointmentTimeList().isEmpty()){
						AppointmentTimeList appointmentTimeList = result.getAppointmentTimeList();
						for (AppointmentTimeDetail timeDetail : appointmentTimeList.getAppointmentTimeList()){
							// get the available appointment date from response and set to AvailableDateTimePeriodVO
							AvailableDateTimePeriodVO availableDateTimePeriodVO = new AvailableDateTimePeriodVO(); 
							if (isValidDate(timeDetail.getAppointmentDate()))
							{
								availableDateTimePeriodVO.setAvailableDate(timeDetail.getAppointmentDate());
								
								//Filter out any date beyond the endDate
								if (availableDateTimePeriodVO.getFormattedAvailableDate().before(requestedEndDate)){
									availableInstallDateVO.addAvailableDateTimePeriod(availableDateTimePeriodVO);
								}
							} else {
								logger.error(EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, functionName, "Appointment date is not validate date from bookingService -> " + timeDetail.getAppointmentDate());
							}
						}
					}else{
						logger.info(functionName, "No Dates return for the Combination of ServiceType: "
								+ availableInstallDateVO.getServiceTypeList().toString());
					}
				}else{
					//Has error
					logger.error("", functionName, "Error has been returned from BookingSvc messageList: " + result.getMessageList().toString());
					response.setMessageList(transformBookingException(serviceTypeList,result.getMessageList(),salesTransactionId));
				}
			}
		}
		
		for (AvailableInstallDateVO vo : availableDateVOMap.values()){
			vo.getAvailableDateTimePeriodList();
			response.addAvailableInstallDates(vo);
		}
		
		/*****************************************************************/
		/*  Build additional info necessary to transform result to ESIS  */
		/*****************************************************************/
		
		/**
		 * generate a complete list of combination of ordered products
		 */
		List<String> orderedProducts = new ArrayList<String>();
		for (WirelineProductSummary product : coreRequest.getOrderProductList()){
			orderedProducts.add(product.getServiceType());
		}
 
		List<List<String>>  serviceTypeCombinationLists =  generateCombination(coreRequest.getCombinationInd(), orderedProducts);
		
		
		/**
		 * build the infeasibleProductsMap for transformer to determine installationRequiredInd
		 */
		Map<String, Boolean> infeasibleProductsMap = this.buildInfeasibleServiceTypeMap(checkProductFeasibilityAdapterResponse);
		response.setInfeasibleProductsMap(infeasibleProductsMap);
		
		/**
		 * build a list of serviceTypes that are SW
		 */
		List<String> serviceTypeSW = new ArrayList<String>();
		for (String serviceType : checkProductFeasibilityAdapterResponse.getInstallTypeSW()){
			serviceTypeSW.add(serviceType);
		}
		
		response.setServiceTypeSWList(serviceTypeSW);
		response.setOrderedProductCombination(serviceTypeCombinationLists);
		return response;
	}
	
 
	/***********************************************/
	/* transformBookingException                   */
	/***********************************************/
	protected List<MessageList> transformBookingException(List<String> serviceTypeList, Exception exception,String salesTransactionId, GetAvailableTimeSlotsTask task) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList msg = new MessageList();
		msg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
		msg.setTransactionId(salesTransactionId);
		msg.setContextData(buildContext(task, serviceTypeList)); 
		msg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_DOWNSTREAM_EXCEPTION);
		msg.setRelatedMessageList(transformDownstreamResponse(exception));
		
		if (msg.getRelatedMessageList().size() > 0){
			List<Message> msgList = new ArrayList<Message>();
			Message downstreamErrorMsg = new Message();
			downstreamErrorMsg.setLocale(Locale.CANADA.toString());
			downstreamErrorMsg.setMessage(EnterpriseWLNSalesServicesConstants.DOWNSTREAM_ERROR_SEE_RELATED_MSG);
			msgList.add(downstreamErrorMsg);
			msg.setMessageList(msgList);
		}
		
		messageList.add(msg);		
		return messageList;
	}
	
	private String buildContext(GetAvailableTimeSlotsTask task, List<String> serviceTypeList) {
		
		String fromDate = ""; 
		String toDate   = "";
		if (task.getInput() != null){
			fromDate = task.getInput().getFromDate();
			toDate = task.getInput().getToDate();
		}
		
		String context = "The following product combination: ["+ serviceTypeList.toString() + "] returned Exception when trying to get Dates [" +
						fromDate + "] to [" + toDate + "] from WLNOrderBookingSvc";
		
		
		
		return context;
	}
 
	private List<RelatedMessage> transformDownstreamResponse(Exception exception) {
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
		RelatedMessage relatedMessage = new RelatedMessage(); 
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		String sStackTrace = sw.toString(); // stack trace as a string
		
		
		relatedMessage.setRelatedErrorMessageTxt(sStackTrace);
		relatedMessageList.add(relatedMessage);
		return relatedMessageList;
	}


	/***********************************************/
	/* transformBookingException                   */
	/***********************************************/
	protected List<MessageList> transformBookingException(List<String> serviceTypeList, List<com.telus.csm.ewlnss.adapter.domain.Message> list,String salesTransactionId) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList msg = new MessageList();
		msg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
		msg.setTransactionId(salesTransactionId);
		msg.setContextData("The following product combination: ["+ serviceTypeList.toString() + "] returned Exception when trying to get Dates from WLNOrderBookingSvc");
		msg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_DOWNSTREAM_EXCEPTION);
		msg.setRelatedMessageList(transformDownstreamResponse(list));
		
		if (!msg.getRelatedMessageList().isEmpty()){
			List<Message> msgList = new ArrayList<Message>();
			Message downstreamErrorMsg = new Message();
			downstreamErrorMsg.setLocale(Locale.CANADA.toString());
			downstreamErrorMsg.setMessage(EnterpriseWLNSalesServicesConstants.DOWNSTREAM_ERROR_SEE_RELATED_MSG);
			msgList.add(downstreamErrorMsg);
			msg.setMessageList(msgList);
		}
		
		messageList.add(msg);		
		return messageList;
	}



	private List<RelatedMessage> transformDownstreamResponse(List<com.telus.csm.ewlnss.adapter.domain.Message> list) {
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
		for(com.telus.csm.ewlnss.adapter.domain.Message entWlnResponseMsg : list){
			RelatedMessage relatedMessage = new RelatedMessage();
			relatedMessage.setRelatedErrorCd("");
			relatedMessage.setRelatedErrorTypeCd(entWlnResponseMsg.getMessageType());
			
			if(!StringUtils.isBlank(entWlnResponseMsg.getMessage())){
				if (entWlnResponseMsg.getMessage().length() > 5000){
					relatedMessage.setRelatedErrorMessageTxt(entWlnResponseMsg.getMessage().substring(0, 5000));
				}
				else{
					relatedMessage.setRelatedErrorMessageTxt(entWlnResponseMsg.getMessage());
				}
			}
			relatedMessageList.add(relatedMessage);
		}
		return relatedMessageList;
	}



	/***********************************************/
	/* transformServiceTypeActionCd                */
	/***********************************************/
	public Map<String, String>  buildServiceTypeActionCdMap(GetAvailableInstallDateCoreRequest request){
		Map<String, String> map = new HashMap<String, String>();
		
		for (WirelineProductSummary product : request.getOrderProductList()){
			map.put(product.getServiceType(), product.getActionTypeCd());
		}
		
		return map;
	}
	
 
	/***********************************************/
	/* transformAccessCriteria                     */
	/***********************************************/
	private AccessCriteria transformAccessCriteria(){
		AccessCriteria accessCriteria = new AccessCriteria();
		
		accessCriteria.setMultiUnitDwellingInd(false);
		accessCriteria.setNewResidentAtServiceAddressInd(true);
		accessCriteria.setPrimaryLineHolderInd(true);
		
		return accessCriteria;
	}
	
	/***********************************************/
	/* transformServiceAddress                     */
	/***********************************************/
	private com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress transformServiceAddress(ServiceAddress addr){ 
		 
		com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress address = 
				new com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress();
		
		address.setAddressId(addr.getAddressId());
		address.setCountryCode(addr.getCountryCode()); 
		address.setMunicipalityName(addr.getMunicipalityName());
		address.setPostalZipCode(addr.getPostalZipCode());
		address.setProvinceStateCode(addr.getProvinceStateCode());
		address.setStreetName(addr.getStreetName());
		address.setStreetNumber(addr.getStreetNumber());
		address.setStreetTypeCode(addr.getStreetTypeSuffixCode());
		address.setUnitNumber(addr.getUnitName());
		address.setCLLICode(addr.getClliCd());
		address.setCOID(addr.getCOID());
		
		return address;
	}
	
	/***********************************************/
	/* buildProductSpecificationCharacteristic     */
	/***********************************************/
	private ProductSpecificationCharacteristic buildProductSpecificationCharacteristic(String name, String value, ValueType valueType){
		ProductSpecificationCharacteristic characteristic = new ProductSpecificationCharacteristic();
		
		characteristic.setName(name);
		characteristic.setValueType(valueType);
		
		CompositeProductSpecificationCharacteristicValue compositeProductSpecificationCharacteristicValue = new CompositeProductSpecificationCharacteristicValue();
		compositeProductSpecificationCharacteristicValue.setValueType(valueType);
		compositeProductSpecificationCharacteristicValue.setDefault(false);
		compositeProductSpecificationCharacteristicValue.setValue(value);
		List<CompositeProductSpecificationCharacteristicValue> list = new ArrayList<CompositeProductSpecificationCharacteristicValue>();
		list.add(compositeProductSpecificationCharacteristicValue);
		characteristic.setProductSpecificationCharacteristicValues(list); 
		
		return characteristic;
	}
	
	/***********************************************/
	/* buildProductSpecificationCharacteristic     */
	/***********************************************/	
	private ProductSpecificationCharacteristic buildProductSpecificationCharacteristic(String name, String value){
		 
		return buildProductSpecificationCharacteristic(name, value, ValueType.STRING);
	}
	
	/***********************************************/
	/* transformCurrentDate                        */
	/***********************************************/	
	private String transformCurrentDate(String format){
		return this.transformDateToString(format, new Date());
	}
	
	private String transformDateToString(String format, Date date){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
 
	
	/***********************************************/
	/* transformOrderedProductList                 */
	/***********************************************/
	private ProductSpecificationList transformOrderedProductList(List<WirelineProductSummary> productList, String brandCode, String requestIdDate){
		ProductSpecificationList productSpecificationList = new ProductSpecificationList();
		
		List<ProductSpecification> list = new ArrayList<ProductSpecification>();
		for (WirelineProductSummary prod: productList){
			ProductSpecification productSpecification = new ProductSpecification();
			
			productSpecification.setName(prod.getServiceType()); 
			if (brandCode.trim().equalsIgnoreCase(TELUS)){
				productSpecification.setBrandID(new BigInteger(TELUS_BRAND_ID));
			}
			productSpecification.setProductNumber(productNumberMap.get(prod.getServiceType()));
			
			List<ProductSpecificationCharacteristic> productSpecificationCharacteristicsList = new ArrayList<ProductSpecificationCharacteristic>();
			
			productSpecificationCharacteristicsList.add(this.buildProductSpecificationCharacteristic(PRODUCT_REQUEST_ID,  requestIdDate + prod.getServiceType()));
			productSpecificationCharacteristicsList.add(this.buildProductSpecificationCharacteristic(ORDER_ACTION_TYPE, lastOrderActionTypeCdMap.get(prod.getActionTypeCd().toLowerCase())));
			productSpecificationCharacteristicsList.add(this.buildProductSpecificationCharacteristic(SERVICE_PLAN, prod.getServicePlanCd()));  
			productSpecificationCharacteristicsList.add(this.buildProductSpecificationCharacteristic(SERVICE_REQUEST_DATE, transformCurrentDate(DATE_FORMAT_SERVICE_REQUEST)));
			
			
			/**
			 * only create STB_ADDED_IND and NEW_STBS_TO_BE_INSTALLED_BY_TELUS if orderProductList=>productInstance=>equipmentList not null
			 */
			//TODODONE double check the 2 new entries
			//TODODONE take a second look for these 
			int equipListSize = 0;
			boolean isEquipmentListNotNull = false;
			if (prod.getProductInstance() != null && prod.getProductInstance().getEquipmentList() != null &&  prod.getProductInstance().getEquipmentList().getEquipment() != null){
				equipListSize = prod.getProductInstance().getEquipmentList().getEquipment().size();
				isEquipmentListNotNull = true;
			}
			
			if(!(TVX.equalsIgnoreCase(prod.getServicePlanCd()))){ //NWLN-4594
				if (isEquipmentListNotNull)
				{
					productSpecificationCharacteristicsList.add(this.buildProductSpecificationCharacteristic(STB_ADDED_IND, "true", ValueType.BOOLEAN));
					productSpecificationCharacteristicsList.add(this.buildProductSpecificationCharacteristic(NEW_STBS_TO_BE_INSTALLED_BY_TELUS, String.valueOf(equipListSize), ValueType.NUMERIC));
				}
			} else {
				// 2018 Aug release for Pik TV
				// add TVType to FeasibilityService as TVX for Pik TV
				ProductSpecificationCharacteristic prodSpecCharacTVTYPE = this.buildProductSpecificationCharacteristic(SERVICE_TVTYPE, prod.getServicePlanCd(), ValueType.STRING);
				productSpecificationCharacteristicsList.add(prodSpecCharacTVTYPE);
			}
			
			productSpecification.setProductSpecificationCharacteristics(productSpecificationCharacteristicsList);
			list.add(productSpecification);
 
		}
		
		productSpecificationList.setProductSpecification(list);
		return productSpecificationList;
	}
		
	
	/***********************************************/
	/* transformProductServiceInsance              */
	/***********************************************/
	private ProductInstanceList transformProductServiceInsance(List<WirelineProductSummary> subscriberProductList, String requestIdDate){
		ProductInstanceList productInstanceList = new ProductInstanceList();
		
		List<ProductServiceInstance> list = new ArrayList<ProductServiceInstance>();
		
		for (WirelineProductSummary prod: subscriberProductList){
			
			ProductServiceInstance productServiceInstance = new ProductServiceInstance();
			
			productServiceInstance.setServiceId(prod.getProductInstance().getServiceId());
			productServiceInstance.setProductCatalogId(stringToLong(productNumberMap.get(prod.getServiceType())));
			productServiceInstance.setProductTypeCode(prod.getServiceType());
			productServiceInstance.setProductParameters(Arrays.asList(buildProductParameter(requestIdDate, prod)));
		
			list.add(productServiceInstance);
		}
		
		productInstanceList.setProductInstance(list);
		return productInstanceList;
	}


	/*****************************************************/
	/* buildProductParameter                             */
	/*****************************************************/
	private ProductParameter buildProductParameter(String requestIdDate, WirelineProductSummary prod) {
		ProductParameter productParameter = new ProductParameter();
		productParameter.setParameterName(PRODUCT_REQUEST_ID);
		
		String parameterValue = requestIdDate + prod.getServiceType();
		productParameter.setParameterValue(parameterValue);
		return productParameter;
	}
	
	
	/*****************************************************/
	/* transformToCoreRequest                            */
	/*    transform ESIS request to CoreRequest          */
	/*****************************************************/
	public GetAvailableInstallDateCoreRequest  transformToCoreRequest(GetAvailableInstallDateType essRequest){
		
		String functionName = "GetAvailableInstallDateCoreRequest";
		logger.enter(CLASS_NAME + "." + functionName);
		GetAvailableInstallDateCoreRequest request = new GetAvailableInstallDateCoreRequest();
		
		request.setOperationHeader(essRequest.getOperationHeader());
		request.setEndDate(essRequest.getEndDate());
		request.setCombinationInd(essRequest.isCombinationInd());
		request.setOrderProductList(essRequest.getOrderedProductList());
		request.setServiceAddress(essRequest.getServiceAddress());
		request.setStartDate(essRequest.getStartDate());
		request.setSubscriberProductList(essRequest.getSubscribedProductList());
		
		
		String salesPersonRoleCd = "";
		if (essRequest.getOperationHeader() != null && essRequest.getOperationHeader().getSalesPersonRoleCode() != null){
			salesPersonRoleCd = essRequest.getOperationHeader().getSalesPersonRoleCode();
		}
		if (salesPersonRoleCd.equalsIgnoreCase(D2C_PARTNER)){
			request.setOutboundChannel(true);
			request.setInboundChannel(false);
		} else{
			request.setOutboundChannel(false);
			request.setInboundChannel(true);
		} 
		
		
		if (request.getCombinationInd() == null){
			request.setCombinationInd(false);
		}
		
		logger.exit(CLASS_NAME + "." + functionName);
		return request;
	}
	
	
	/*****************************************************/
	/* generationCombination                             */
	/*    if combination request, generate cartesian     */
	/*       product of RecommendedInstallType           */
	/*****************************************************/
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
		logger.info(functionName, GetAvailableInstallDateUtils.buildStringFromList(combinationList));
		logger.info(functionName, "*********************************************************************************************\n");
		return combinationList;
	}
	 
	/**************************************************/
	/* buildServiceTypeFeasibilityMap                 */
	/*    build a map based on serviceType            */
	/**************************************************/
	public Map<String, ServiceTypeFeasibilityVO> buildServiceTypeFeasibilityMap(List<ProductFeasibilityInfo> feasibilityInfoList){
		
		Map<String, ServiceTypeFeasibilityVO> serviceTypeFeasibilityMap = new HashMap<String, ServiceTypeFeasibilityVO>();
		
		for (ProductFeasibilityInfo info : feasibilityInfoList){
			
			ServiceTypeFeasibilityVO vo = new ServiceTypeFeasibilityVO();
			
			ProvisioningInfo provisioningInformation = info.getProvisioningInformation();
			if (provisioningInformation != null && provisioningInformation.getRecommendedInstallType()!=null)
			{
				vo.setDefaultBucket(provisioningInformation.getDefaultBucket());
				vo.setRecommendedInstallType(provisioningInformation.getRecommendedInstallType());
				vo.setWorkTimeDefault(provisioningInformation.getWorkTimeDefault());
				vo.setWorkTimeMax(provisioningInformation.getWorkTimeMax());
				vo.setWorkTimeMin(provisioningInformation.getWorkTimeMin());
				
				vo.setProductSpecificationName(info.getProductSpecification().getName());
				
				serviceTypeFeasibilityMap.put(vo.getProductSpecificationName(), vo);
			}
			
		} 
		
		return serviceTypeFeasibilityMap;
	}
	
	/*****************************************************/
	/* buildServiceTypeInstallationTypeMap               */  
	/*****************************************************/ 
	public Map<String, String> buildServiceTypeInstallationTypeMap(CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse){
		Map<String, String> map = new HashMap<String, String>();
		String functionName="buildServiceTypeInstallationTypeMap";
		if (checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList() != null &&
			! checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo().isEmpty()){
			
			for (ProductFeasibilityInfo info : checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo()){
				if(info.getProvisioningInformation()!=null ){
					if (!StringUtils.isBlank(info.getProvisioningInformation().getRecommendedInstallType())) {
						String installType = info.getProvisioningInformation().getRecommendedInstallType();
						String serviceType = info.getProductSpecification().getName();
						map.put(serviceType, installType);
					} else {
						logger.info(functionName,"No recommended installType for serviceType: "+ info.getProductSpecification().getName()+ " was returned in checkProductFeasibilityResponse");
					}
				}
			}
		}
		
		return map;
	}
	
	//TODODONE clean up this method
	//     move the filtering before calling cartisan products, should save a few steps
	// DONE - refactored code to simpler flow
	
	/*****************************************************/
	/* filterSerivceTypeCombination                      */  
	/*****************************************************/  
	public List<List<String>> filterSerivceTypeCombination(List<List<String>> serviceTypeCombinationLists, 
			                                                Map<String, String> serviceTypeInstallationTypeMap)
    {
		/**
		 * 
		 * If request service type has TTV and HSIC
		 *    do not call bookingService for TTV, TTV's booking dates will be replace by HSIC+TTV  
		 *    
		 * IF request service type has TTV, HSIC and SING
		 *    do not call bookingService for TTV, TTV's booking dates will be replace by HSIC+TTV  
		 *    do not call bookingService for TTV + SING, replace result from HSIC+SING+TTV
		 * 
		 */
		
		String functionName = "filterSerivceTypeCombination";
		logger.enter(functionName);
		boolean removeTtvInd = this.hasTtvHsic(serviceTypeInstallationTypeMap);
		
		if (removeTtvInd){			 
			serviceTypeCombinationLists.remove(GetAvailableInstalLDateConstants.getTTVList());
			serviceTypeCombinationLists.remove(GetAvailableInstalLDateConstants.getSingTTVList());
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
	
	
	

	/*************************************************/
	/* buildInfeasibleServiceTypeMap                 */
	/*************************************************/
	protected Map<String,Boolean> buildInfeasibleServiceTypeMap(CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse) {
		
		String functionName = "buildInfeasibleServiceTypeMap";
		logger.enter(functionName);
		Map<String, Boolean> infeasibleProductsMap = new HashMap<String, Boolean>();
		if (isCollectionHasValues(checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo())){
			
			for (ProductFeasibilityInfo info : checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo()){ 
			
				String bestAvailableConfigurationInd = "";
				
				for (ProductSpecificationCharacteristic productChar : info.getProductSpecification().getProductSpecificationCharacteristics()){
				
					if (productChar.getName().equalsIgnoreCase(BEST_AVAILABLE_CONFIGURATION_ID)){
					
						for (CompositeProductSpecificationCharacteristicValue charValues : productChar.getProductSpecificationCharacteristicValues()){
							bestAvailableConfigurationInd = charValues.getValue();
						}
					}
				}
				
				//TODO think about if the info.getProductSpecification().getName() does not contain any value
				
				// Get the workTime
				double defaultWorkTime = 0.0d;
				String feasibilityResult = "";
				if (info.getProvisioningInformation() != null){
					defaultWorkTime = info.getProvisioningInformation().getWorkTimeDefault();
				}
				if (info.getFeasibilityResult() != null){
					feasibilityResult = info.getFeasibilityResult().trim();
				}
				
				if (bestAvailableConfigurationInd != null && bestAvailableConfigurationInd.length() == 0)
					bestAvailableConfigurationInd = FLAG_FALSE;
				
				if (feasibilityResult.equalsIgnoreCase(FLAG_FALSE) && bestAvailableConfigurationInd.equalsIgnoreCase(FLAG_FALSE)){
					infeasibleProductsMap.put(info.getProductSpecification().getName(), true); 
				} 
				else if (feasibilityResult.length() == 0 && defaultWorkTime > 0.0d){
					infeasibleProductsMap.put(info.getProductSpecification().getName(), false); 
				} 
				else {
					infeasibleProductsMap.put(info.getProductSpecification().getName(), false);
				}
			}
		}
		Set<String> keySetInf = infeasibleProductsMap.keySet();
		for(String serviceType : keySetInf){
			if(infeasibleProductsMap.get(serviceType)){
				logger.info(functionName, "List of Infeasible Products: " + serviceType);
			}
		}
		
		logger.exit(functionName);
		return infeasibleProductsMap;
	}
	
	
	
 
	/*****************************************************/
	/* buildGetTimeSlotRequests                          */ 
	/*     for RW and FW                                 */
	/*****************************************************/ 
	public List<GetAvailableTimeSlotsAdapterRequest> buildGetTimeSlotRequests( GetAvailableInstallDateCoreRequest request, 
																                            CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse,
																                            Map<String,String> nguiLagTimeMap) throws LagTimeException,
																                                                                      BookingDateCalculationException{
		
		String functionName = "buildGetTimeSlotRequests";
		/**
		 * - Build 3 maps key by serviceType
		 *      1 - serivceType, serviceTypeAction
		 *      2 - serviceType, recommendedInstallType
		 *      3 - serviceType, ServiceTypeFeasibilityVO
		 *      
		 * - if combination = true, generate all possible combinations of service types
		 *          
		 * - use the maps and combinations to create a list of time slots adapter requests
		 * 
		 */
		
		// Step 1 - create map of serviceType and actionTypeCd 
		//           to populate - getAvailableTimeSlot.InstallationRequirementsList.actionType
		Map<String, String> serviceTypeActionCdMap = this.buildServiceTypeActionCdMap(request);
		
		
		
		// Step 2 - create map of serviceType and recommendedInstallType
		Map<String, String> serviceTypeInstallationTypeMap = this.buildServiceTypeInstallationTypeMap(checkProductFeasibilityAdapterResponse);
				
		
		
		// Step 3 - transform response from feastabilityAdapter to ServiceTypeFeasibilityVO map key by SerivceType 
		List<ProductFeasibilityInfo> productFeasiblityInfoList           = checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo();
		Map<String, ServiceTypeFeasibilityVO> serviceTypeFeasbilitiesMap = this.buildServiceTypeFeasibilityMap(productFeasiblityInfoList);
		
		
		
		// Step 4 - create map of serviceType and feasibility indicator
		Map<String, Boolean> infeasibleProductsMap = this.buildInfeasibleServiceTypeMap(checkProductFeasibilityAdapterResponse);
		
		
		// Step 5 - perform filtering of service types
		//          1 - filter out any service type installationType is SW
		//          2 - filter out any service type has feasibilityInd = false
		List<String> filteredServiceTypes = this.filterServiceTypes(serviceTypeInstallationTypeMap, 
				                                                    infeasibleProductsMap, 
				                                                    serviceTypeFeasbilitiesMap);
		
		
		// Step 6 - generate a combination list of serviceType
		//           if combination = true, list will be all possible combinations of serviceType 
		//           otherwise, it will be a single list of filtered serviceType
		List<List<String>> serviceTypeCombinationLists = this.generateCombination(request.getCombinationInd(), 
				                                                                  filteredServiceTypes);
		
		// Step 7 - apply business rules on which combination should be allow to call bookingService
		List<List<String>> finalCombinationList = this.filterSerivceTypeCombination(serviceTypeCombinationLists, 
				                                                                    serviceTypeInstallationTypeMap);
		
		logger.info(functionName, "Filter Combinations ---> ");
		logger.info(functionName, GetAvailableInstallDateUtils.buildStringFromList(finalCombinationList));
		
		// Step 6 - build list of GetAvailableTimeSlotsAdapterRequest
		return this.transformFeastibilityResponseToBookingReqeust(request, 
	                                                                                                                          finalCombinationList, 
	                                                                                                                          serviceTypeFeasbilitiesMap, 
	                                                                                                                          serviceTypeActionCdMap,
	                                                                                                                          nguiLagTimeMap);
		
		

	}
	
	/**********************************************   FILTER ***************************************************************/
	
	/*****************************************************/
	/* filterServiceTypes                                */ 
	/*****************************************************/ 
	public List<String> filterServiceTypes(Map<String, String >serviceTypeInstallationTypeMap, 
			                                Map<String, Boolean> infeasibleProductsMap, 
			                                Map<String, ServiceTypeFeasibilityVO> serviceTypeFeasibilityMap){
		List<String> serviceTypes = new ArrayList<String>();
		
		/**
		 * build a list of service types that return from feasibilityService.
		 */
		for (ServiceTypeFeasibilityVO feasibilityVO : serviceTypeFeasibilityMap.values()){ 
			if (feasibilityVO != null && feasibilityVO.getProductSpecificationName() != null /*&& feasibilityVO.getRecommendedInstallType()!=null*/){
				serviceTypes.add(feasibilityVO.getProductSpecificationName());
			}
		}
		
		serviceTypes = this.filterSW(serviceTypes, serviceTypeInstallationTypeMap);
		serviceTypes = this.filterInfeasibleServiceTypes(serviceTypes, infeasibleProductsMap);
		return serviceTypes;
	}
	
	/*****************************************************/
	/* filterSW - remove all installationType = SW       */ 
	/*****************************************************/ 
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
	
	/*****************************************************/
	/* filterInfeasibleServiceTypes                      */ 
	/*****************************************************/ 
	private List<String> filterInfeasibleServiceTypes(List<String> serviceTypes, Map<String, Boolean> infeasibleProductsMap){
		
		List<String> feasibleServiceTypeList = new ArrayList<String>();
		
		for (String serviceType : serviceTypes){ 
			 
			if ( ! infeasibleProductsMap.get(serviceType)){
				feasibleServiceTypeList.add(serviceType);
			}
			
		}
		return feasibleServiceTypeList;
	} 
	
	
	/*****************************************************/
	/* transformException                                */ 
	/*****************************************************/ 
	public GetAvailableInstallDateCoreResponse transformException(Throwable ex, String salesTransactionId){
		GetAvailableInstallDateCoreResponse response = new GetAvailableInstallDateCoreResponse();
		
		response.setMsgHasError(true);
		
		response.setTransactionId(salesTransactionId);
		
		SalesResponseMessage.MessageList msg = new SalesResponseMessage.MessageList();
		msg.setContextData(ex.getMessage()); 
		if(ex instanceof LagTimeException){
			msg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_REFPDS_EXCEPTION);
		}else{
		msg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR);
		}
		Message localizedMsg = new Message();
		localizedMsg.setMessage(ex.getLocalizedMessage());
		localizedMsg.setLocale(Constants.LANG_EN);
		msg.setMessageList(Arrays.asList(localizedMsg));
		msg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR); 
		 
		response.addMsg(msg);
		
		return response;
	}
	
	
	/***********************************************/
	/* getLagDays                                  */
	/***********************************************/
	public int getLagDays(List<String> serviceTypes, boolean isInboundChannel,Map<String,String> lagTimeMap) throws LagTimeException{
		String functionName="getLagDays";
		int maxLagDays = 0;
		for (String serviceType : serviceTypes){
			if (isInboundChannel){
				serviceType += "_IN";
			}
			else{
				serviceType += "_OUT";
			}
			/**
			 * ensure lag time is there, otherwise raise exception 
			 */
			if (! lagTimeMap.containsKey(serviceType)){
				throw new LagTimeException("fail to get lagtime for serviceType => " + serviceType);
			}
			
			try{
				int lagDays = Integer.parseInt(lagTimeMap.get(serviceType));
				
				if (lagDays > maxLagDays){
					maxLagDays = lagDays;
				}
			} catch(Exception e){
				logger.error("", functionName, e.getMessage(),e);
				throw new LagTimeException("invalid lag time value (" + lagTimeMap.get(serviceType) +") for serviceType =>" + serviceType);
			}
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
	
	/*****************************************************/
	/* calculateBookingStartDate                         */ 
	/*****************************************************/  
	public Date calculateBookingStartDate(Date startDate, int lagDays) throws BookingDateCalculationException{ 
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
			throw new BookingDateCalculationException(e.getMessage());
		}
			
		return bookingDate;
	}
	
	
	/*****************************************************/
	/* transform FeastibilityResponse To BookingReqeust  */ 
	/*****************************************************/  
	/*
	 * coreRequest -> city, addressId, startDate, endDate
	 * recommendInstallTypeList -> 
	 */
	public List<GetAvailableTimeSlotsAdapterRequest> transformFeastibilityResponseToBookingReqeust(GetAvailableInstallDateCoreRequest coreRequest,
																												List<List<String>> finalCombinationList,
																										        Map<String, ServiceTypeFeasibilityVO> serviceFeasibilityMap,
																										        Map<String, String> serviceActionTypeCodeMap,
																										        Map<String,String> nguiLagTimeMap) throws LagTimeException, 
																										                                                  BookingDateCalculationException{	
		
		String functionName="transformFeastibilityResponseToBookingReqeust";
		List<GetAvailableTimeSlotsAdapterRequest> result = new ArrayList<GetAvailableTimeSlotsAdapterRequest>();

		/**
		 * BookingService consider endDate is endDate at time 00:00:00.  Thus we need to add one day to coreRequest.getEndDate() 
		 * in order to fetch the availableInstallDate for the last day.
		 */
		coreRequest.setEndDate(this.addDays(coreRequest.getEndDate(), 1));
		
		
		/**
		 * build list of request based on serviceType and chunks
		 */
		for (List<String> serviceTypeList : finalCombinationList){
			if (!serviceTypeList.isEmpty()) { 
				logger.info(functionName, "Bulding Request for combination : " + serviceTypeList);
			
				GetAvailableTimeSlotsAdapterRequest request = new GetAvailableTimeSlotsAdapterRequest();
				request.setCity(coreRequest.getServiceAddress().getMunicipalityName());
				request.setFmsAddressId(coreRequest.getServiceAddress().getAddressId()); 
				request.setProvinceCd(coreRequest.getServiceAddress().getProvinceStateCode());
			
				List<InstallationRequirementsList> list = new ArrayList<InstallationRequirementsList>();
				
				/**********************************************/
				/* build list of InstallationRequirementsList */
				/**********************************************/
				for (String serviceType: serviceTypeList){
					
					ServiceTypeFeasibilityVO feasibilityVO = serviceFeasibilityMap.get(serviceType);
					 
					String actionTypeCd = serviceActionTypeCodeMap.get(serviceType);
					
					InstallationRequirementsList installation = new InstallationRequirementsList();
					
					installation.setActionType(actionTypeCdMap.get(actionTypeCd.toLowerCase()));
					
					installation.setProductServiceType(feasibilityVO.getProductSpecificationName());
					installation.setRequestedBucketType(feasibilityVO.getDefaultBucket());
					installation.setRequestedInstallType(feasibilityVO.getRecommendedInstallType());
					installation.setRequestedWorkTime(Double.toString(feasibilityVO.getWorkTimeDefault()));
					
					list.add(installation);
				
				
					request.setInstallationRequirementsList(list);
				}
				
				/***********************************************/
				/* get lag days to calculate bookingStartdate  */
				/***********************************************/
				int lagDays = this.getLagDays(serviceTypeList, coreRequest.isInboundChannel(), nguiLagTimeMap); 
				
				/*******************************************/
				/* remove time from date                   */
				/*******************************************/
				
				coreRequest.setStartDate(EnterpriseSalesServiceUtil.removeTime(coreRequest.getStartDate()));
				coreRequest.setEndDate(EnterpriseSalesServiceUtil.removeTime(coreRequest.getEndDate()));
				

				Date bookingStartDate = this.calculateBookingStartDate(coreRequest.getStartDate(), lagDays);
				
				/***********************************************/
				/* split the date into 7 days chunk            */
				/***********************************************/
				
				/**
				 * if startDate is after endDate, skip. 
				 */
				if (! bookingStartDate.after(coreRequest.getEndDate())){
					ArrayList[] chunkDates = getChunks(bookingStartDate, coreRequest.getEndDate());
					
					int chunkSize = chunkDates[0].size(); 
					for (int i = 0; i < chunkSize; i++){
						String fromDate = (String) chunkDates[0].get(i);
						String toDate = (String) chunkDates[1].get(i);
						
						fromDate = this.adjustStartingDate(fromDate, toDate);
						
						// Clone a new object and use the chunked start/end date
						GetAvailableTimeSlotsAdapterRequest chunkedRequest = request.cloneObject();
							 
						chunkedRequest.setFromDate(fromDate);
						chunkedRequest.setToDate(toDate);
						result.add(chunkedRequest);

					}
				}	
			}
		}		
		logger.info(functionName, "*************************************************************************************"); 
		logger.info(functionName, "GetAvailableTimeSlotsAdapterRequest List ---> \n" );
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (GetAvailableTimeSlotsAdapterRequest requests: result){
			String serviceTypes = "";
			for (InstallationRequirementsList req : requests.getInstallationRequirementsList()){
				serviceTypes = serviceTypes + " " + req.getProductServiceType();
			}
			sb.append("[" + serviceTypes + "] - fromDate: " + requests.getFromDate() + " - toDate: " + requests.getToDate() + "\n");
			
		}
		logger.info(functionName, sb.toString());
		
		return result;
	}
	
	/********************************/
	/* adjustStartingDate           */
	/********************************/
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
	
	// split up the startDate and endDate into 7 days chunk
	// Note: bookingService treat endDate as of hour 00 of endDate
	
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
				Date batchEndDate = this.addDays(startDate, GetAvailableInstalLDateConstants.getChunkSize());
				
				if (batchEndDate.after(toDate)){
					batchEndDate = toDate;
				}
				batchEndDates.add(sdf.format(batchEndDate));
				
				/**
				 * the next startDate is current startDate + CHUNK_SIZE.  Cannot use batchEndDate cause it will cause an endless loop
				 */
				startDate = this.addDays(startDate, GetAvailableInstalLDateConstants.getChunkSize());
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

	
	/********************************************************/
	/* transform ResponseMessage To SalesResponseMessage    */ 
	/********************************************************/ 
	public List<SalesResponseMessage.MessageList> transformResponseMessageToSalesResponseMessage(List<ResponseMessage> msgs, String transactionId){
		  
		List<SalesResponseMessage.MessageList> resultList = new ArrayList<SalesResponseMessage.MessageList>();
		for (ResponseMessage msg : msgs){
			SalesResponseMessage.MessageList salesResponseMessage = new SalesResponseMessage.MessageList();
			salesResponseMessage.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_DOWNSTREAM_EXCEPTION);
			salesResponseMessage.setContextData(msg.getContextData());
			salesResponseMessage.setDateTimeStamp(msg.getDateTimeStamp());
			
			Message responseMessageMsg = new Message();
			responseMessageMsg.setLocale(msg.getMessageList().getLocale());
			responseMessageMsg.setMessage(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_DOWNSTREAM_ERROR_TXT + GetAvailableInstalLDateConstants.FEASIBILITY_SERVICE);
			salesResponseMessage.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
			
			
			salesResponseMessage.setMessageList(new ArrayList<Message>(Arrays.asList(responseMessageMsg)));
			
			salesResponseMessage.setTransactionId(transactionId);
			List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
			RelatedMessage relatedMessage = new RelatedMessage();
			relatedMessage.setRelatedErrorCd(msg.getErrorCode());
			relatedMessage.setRelatedErrorMessageTxt(msg.getMessageList().getMessage());
			relatedMessage.setRelatedErrorTypeCd(msg.getMessageType());
			relatedMessageList.add(relatedMessage);
			salesResponseMessage.setRelatedMessageList(relatedMessageList);
			
			if (salesResponseMessage.getMessageType().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR)){
				resultList.add(salesResponseMessage);
				logger.info("transformResponseMessageToSalesResponseMessage", "Found error message from response -> " + msg.getMessageList().getMessage());
			}
		}
		
		return resultList;
	}
	

	
	/*****************************************************/
	/* findAvailableInstallDateVO                        */
	/*****************************************************/
	protected AvailableInstallDateVO findAvailableInstallDateVO(List<String> serviceTypeList, 
			                                                  List<String> serviceTypeSWList, 
			                                                  Map<List<String>, AvailableInstallDateVO> coreResponseMap,
			                                                  List<String> infeasibleProducts){
		
		
		/**
		 * Look for downstream response based on request serviceType. the list must be sorted
		 * 
		 * 1 - if request has only 1 serviceType
		 *     - if serviceType is SW 
		 *          return an empty vo
		 *       else
		 *          return vo from downstream response.
		 *          
		 *  2 - if request has more than 1 serviceType
		 *      - build searchArgument
		 *      - append service type to search argument unless the serviceType is SW 
		 *      - if one of the serviceType is not feasible, return empty vo
		 *      - return vo from downstream response that matches the search argument.
		 * 
		 */
		AvailableInstallDateVO vo = null;
		String functionName = CLASS_NAME + "." + "findAvailableInstallDateVO";
		String serviceTypeListJoined = StringUtils.join(serviceTypeList);
		logger.info(functionName, "Find availabeInstallDates for ServiceType => " +serviceTypeListJoined);
		
		if (serviceTypeList.size() == 1){
			String serviceType = serviceTypeList.get(0);
			if (serviceTypeSWList.contains(serviceType) || infeasibleProducts.contains(serviceType)){
				logger.info(functionName, serviceType + " is SW or not feasible");
			}
			else { 
				if (coreResponseMap.containsKey(serviceTypeList)){
					vo = coreResponseMap.get(serviceTypeList);
				}
			}
		} else {
			/**
			 * build a list of serivceType by excluding service type SW. Then use the list to search coreResponseMap.   
			 */
			List<String> serviceTypeCombine = new ArrayList<String>();
			
			for (String serviceType : serviceTypeList){ 
				if ( ! serviceTypeSWList.contains(serviceType) ){
					serviceTypeCombine.add(serviceType);
				}
				/**
				 *  If any product has feasibilityResult=false && bestAvailableConfigurationInd==false then 
				 *  installationRequiredInd=true (although no dates will be returned)
				 */
				if (infeasibleProducts.contains(serviceType)){
					logger.info(functionName, serviceType + " is not feasible, no date will be return"); 
					break;
				}
			}
			
			if (coreResponseMap.containsKey(serviceTypeCombine)){
				vo = coreResponseMap.get(serviceTypeCombine);
			}  
		}
		
		return vo;
		
	}
	/*****************************************************/
	/* transformFromCoreResponse                         */
	/*    summarize all responses                        */
	/*****************************************************/
	public GetAvailableInstallDateResponseType transformFromCoreResponse(GetAvailableInstallDateCoreResponse bookingServiceResponse){
		String functionName= CLASS_NAME + ".transformFromCoreResponse";
		
		logger.enter(functionName);
		GetAvailableInstallDateResponseType response = new GetAvailableInstallDateResponseType();
		
		Map<String, Boolean> infeasibleProductsMap = bookingServiceResponse.getInfeasibleProductsMap();
		
		
		List<AvailableInstallDatesList> availableInstallDatesList = response.getAvailableInstallDatesList();
		Map<List<String>, AvailableInstallDateVO> coreResponseMap = new HashMap<List<String>, AvailableInstallDateVO>();
		
		if ( ! bookingServiceResponse.getMessageList().isEmpty() && bookingServiceResponse.getAvailableInstallDates().isEmpty()){
			response.setMessageList(bookingServiceResponse.getMessageList());
			return response;
		}
		
		
		/**
		 *  Step 1 - build map coreResponseMap using sorted serviceTypes as key
		 */ 
		if (! bookingServiceResponse.getAvailableInstallDates().isEmpty()){
			
			for (AvailableInstallDateVO availableDates : bookingServiceResponse.getAvailableInstallDates()){ 
				Collections.sort(availableDates.getServiceTypeList());
				coreResponseMap.put(availableDates.getServiceTypeList(), availableDates );
			}
		}
		
		
		/**
		 * Step 2 - Special rule 
		 */
		// If response has HSIC+TTV,  TTV will use result from HSIC+TTV 
		if (coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getHsicTTVList()) && !coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getTTVList())){
			coreResponseMap.put(GetAvailableInstalLDateConstants.getTTVList(), coreResponseMap.get(GetAvailableInstalLDateConstants.getHsicTTVList()));
		}
		// If request has TTV and HSIC and SING, replace SINGTTV with HSIC SING TTV 
		if (coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getHsicSingTtvList())){
			coreResponseMap.put(GetAvailableInstalLDateConstants.getSingTTVList(), coreResponseMap.get(GetAvailableInstalLDateConstants.getHsicSingTtvList()));
		}
		
		
		
		/**
		 * Step 3 - setup list of infeasibleProducts
		 */
		Set<String> keySet = infeasibleProductsMap.keySet();
		List<String> infeasibleProducts = new ArrayList<String>();
		for(String keyValue : keySet){
			if(infeasibleProductsMap.get(keyValue)){
				infeasibleProducts.add(keyValue);
			}
		}
		
		
		/**
		 *  Step 4 - populate result with all combinations of serviceTypes
		 */
		for ( List<String> combinationList : bookingServiceResponse.getOrderedProductCombination()){
			Collections.sort(combinationList);
			AvailableInstallDatesList availableInstallDates = new AvailableInstallDatesList();
			availableInstallDates.setServiceTypeList(combinationList);
			
			/**
			 * find dates from coreReponse - the list of serviceType from coreResponse is already sorted
			 * TTV - search for TTV, HSIC + TTV, HSIC + SING + TTV in sequence
			 */  
			AvailableInstallDateVO availableInstallDateVO = this.findAvailableInstallDateVO(combinationList, 
					                                                 bookingServiceResponse.getServiceTypeSWList(), 
					                                                 coreResponseMap,
					                                                 infeasibleProducts);
 
			
			
			if (availableInstallDateVO != null){
				
				List<Date> dateList = new ArrayList<Date>();
				for (AvailableDateTimePeriodVO availTimePeriod : availableInstallDateVO.getAvailableDateTimePeriodList()){
					Date formattedDate = availTimePeriod.getFormattedAvailableDate();
					if (!dateList.contains(formattedDate)){
						dateList.add(formattedDate);
					}
				}
				availableInstallDates.setDateList(dateList);
				
				availableInstallDatesList.add(availableInstallDates);
			} else {
				
				/**
				 * did not find a downstream response for the serviceType combination.  Return installation = false and empty dates
				 */
				AvailableInstallDatesList emptyDatesList = new AvailableInstallDatesList();
				emptyDatesList.setDateList(new ArrayList<Date>());
				emptyDatesList.setInstallationRequiredInd(false);
				emptyDatesList.setServiceTypeList(combinationList);
				availableInstallDatesList.add(emptyDatesList); 
			}
		}
	
		
		/**
		 *  Step 3 - if all service type within the combination are SW, then installationInd = false, else true;
		 */
		
		for (AvailableInstallDatesList installDates : availableInstallDatesList){
			if (bookingServiceResponse.getServiceTypeSWList().containsAll(installDates.getServiceTypeList())){
				installDates.setInstallationRequiredInd(false);
			} 
			else {
				installDates.setInstallationRequiredInd(true);
			}
		}
		
		//If ResponseMessage is not null, then populate the ResponseMessage with 
		if(! bookingServiceResponse.getMessageList().isEmpty()){
			response.setMessageList(bookingServiceResponse.getMessageList());
		}
		logger.exit(functionName);
		return response;
	}
	
	

}
