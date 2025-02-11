package com.telus.csm.ewlnsis.core.transformer;

import static com.telus.csm.ewlnsc.util.Constants.D2C_PARTNER;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils.isValidDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.AppointmentTimeDetail;
import com.telus.csm.ewlnsc.adapter.wbk.domain.AppointmentTimeList;
import com.telus.csm.ewlnsc.adapter.wbk.domain.AvailableAppointmentPeriods;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.InstallationRequirementsList;
import com.telus.csm.ewlnsc.domain.AvailableDateTimePeriodVO;
import com.telus.csm.ewlnsc.domain.AvailableInstallDateVO;
import com.telus.csm.ewlnsc.domain.AvailableTimePeriodVO;
import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreResponse;
import com.telus.csm.ewlnsc.domain.ServiceInstallDetailVO;
import com.telus.csm.ewlnsc.task.GetAvailableTimeSlotsTask;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreResponse;
import com.telus.csm.ewlnsis.core.exceptions.LagTimeException;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetInstallationDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetInstallationDetailResponseType.AvailableInstallDatesList;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetInstallationDetailType;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfoList;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceInstallDetail;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductSummary;

public class GetInstallationDetailCoreTransformer extends GetAvailableInstallDateCoreTransformer {
	
	private static final String CLASS_NAME = "GetInstallationDetailCoreTransformer";
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetInstallationDetailCoreTransformer.class);
	
	/*****************************************************/
	/* transformToCoreRequest                            */
	/*    transform ESIS request to CoreRequest          */
	/*****************************************************/
	public GetInstallationDetailCoreRequest  transformToCoreRequest(GetInstallationDetailType essRequest){
		
		String functionName = "transformToCoreRequest";
		logger.enter(CLASS_NAME + "." + functionName);
		
		GetInstallationDetailCoreRequest request = new GetInstallationDetailCoreRequest();
		
		request.setOperationHeader(essRequest.getOperationHeader());
		request.setEndDate(essRequest.getEndDate());
		request.setCombinationInd(essRequest.isCombinationInd());
		request.setOrderProductList(essRequest.getOrderedProductList());
		request.setServiceAddress(essRequest.getServiceAddress());
		request.setStartDate(essRequest.getStartDate());
		request.setSubscriberProductList(essRequest.getSubscribedProductList());
		request.setAttributeExclusionList(essRequest.getAttributeExclusionList());
		
		String salesPersonRoleCd = "";
		if (essRequest.getOperationHeader() != null && essRequest.getOperationHeader().getSalesPersonRoleCode() != null)
			salesPersonRoleCd = essRequest.getOperationHeader().getSalesPersonRoleCode();
			
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
	/* transformFromCoreResponse                         */
	/*****************************************************/
	public GetInstallationDetailResponseType transformFromCoreResponse(GetInstallationDetailCoreResponse coreResponse){
		
		GetInstallationDetailResponseType response = transformFromCoreResponse2(coreResponse);
				
		return this.transformToInstallationDetailResponseType(response, coreResponse);
		
	}
	
	/*****************************************************/
	/* transformToInstallationDetailRepsonseType         */
	/*****************************************************/
	private GetInstallationDetailResponseType transformToInstallationDetailResponseType(GetInstallationDetailResponseType response,
			                                                                            GetInstallationDetailCoreResponse coreResponse){

		GetInstallationDetailResponseType detailResponse = new GetInstallationDetailResponseType();
		
		List<GetInstallationDetailResponseType.AvailableInstallDatesList> list = response.getAvailableInstallDatesList();
		if (list != null && list.size() > 0){
			for (GetInstallationDetailResponseType.AvailableInstallDatesList item : list){
				GetInstallationDetailResponseType.AvailableInstallDatesList detail = new GetInstallationDetailResponseType.AvailableInstallDatesList();
				detail.setDateList(item.getDateList());
				detail.setInstallationRequiredInd(item.isInstallationRequiredInd());
				detail.setServiceTypeList(item.getServiceTypeList());
				detailResponse.getAvailableInstallDatesList().add(detail);
			}
		}
		detailResponse.setMessageList(response.getMessageList());
		
		if (!coreResponse.getServiceInstallDetail().isEmpty()){
			detailResponse.setServiceInstallDetailList(transformToServiceInstallDetailList(coreResponse.getServiceInstallDetail()));
		}
		
		return detailResponse;
	}
	
	private List<ServiceInstallDetail> transformToServiceInstallDetailList(List<ServiceInstallDetailVO> serviceInstallDetail) {
		if(serviceInstallDetail == null) {
			return null;
		}
		ArrayList<ServiceInstallDetail> list = new ArrayList<ServiceInstallDetail>();
		for(ServiceInstallDetailVO detVO: serviceInstallDetail) {
			ServiceInstallDetail detail = new ServiceInstallDetail();
			detail.setRecommendedInstallTypeCd(detVO.getRecommendedInstallTypeCd());
			detail.setServiceTypeCd(detVO.getServiceTypeCd());
			list.add(detail);
		}
		return list;
	}

	/*****************************************************/
	/* transformFeasibilityResponse                      */
	/*****************************************************/
	public void transformFeasibilityResponse(CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse, 
			                                                              GetInstallationDetailCoreResponse response){
		List<ServiceInstallDetailVO> serviceInstallDetail = new ArrayList<ServiceInstallDetailVO>();
		ProductFeasibilityInfoList infoList = checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList();
		if (infoList != null && ! infoList.getProductFeasibilityInfo().isEmpty()){
			for (ProductFeasibilityInfo info : infoList.getProductFeasibilityInfo()){
				String feasible = info.getFeasibilityResult();
				if (feasible != null && feasible.equalsIgnoreCase("true")){
					String serviceType = "";
					String recommendedInstallTypeCd = "";
					
					if (info.getProductSpecification() != null){
						serviceType = info.getProductSpecification().getName();
					}
					
					if (info.getProvisioningInformation() != null){
						recommendedInstallTypeCd = info.getProvisioningInformation().getRecommendedInstallType();
					}
					
					if (!StringUtils.isEmpty(serviceType) && !StringUtils.isEmpty(recommendedInstallTypeCd)){
						ServiceInstallDetailVO sid = new ServiceInstallDetailVO();
						sid.setServiceTypeCd(serviceType);
						sid.setRecommendedInstallTypeCd(recommendedInstallTypeCd);
						sid.setEstimatedAppointmentHours(info.getProvisioningInformation().getWorkTimeDefault());
						serviceInstallDetail.add(sid);
					}else{
						// Add message indicating there is no recommendedInstallTypeCd
						addInfoMsgWhenNoRecommendedInstallTypeCd(response, serviceType);
					}
				}
				 
			}
			response.setServiceInstallDetail(serviceInstallDetail);
		}
		
	}
	
	
	private void addInfoMsgWhenNoRecommendedInstallTypeCd(GetInstallationDetailCoreResponse response,
			String serviceType) {
		SalesResponseMessage.MessageList theMsg = new SalesResponseMessage.MessageList();
		theMsg.setDateTimeStamp(new Date());
		theMsg.setErrorCode("");
		theMsg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO);
		// msg.setTransactionId(value)
		Message message = new Message();
		message.setLocale(Locale.CANADA.toString());
		message.setMessage("No Recommended Install Type Cd was returned for this Service Type.");
		theMsg.setMessageList(Arrays.asList(message));
		
		theMsg.setContextData("ServiceType: " + serviceType);
		response.getMessageList().add(theMsg);
	}

	private String buildContext(GetAvailableTimeSlotsAdapterRequest req){
		StringBuffer sbContext = new StringBuffer();
		
		if (req.getOrderId() != null){
			sbContext.append("orderId=");
			sbContext.append(req.getOrderId());
		}
		
		if (req.getFmsAddressId() != null){
			sbContext.append(" FmsAddressId=");
			sbContext.append(req.getFmsAddressId());
		}
		
		if (req.getFromDate() != null) {
			sbContext.append(" FromDate=");
			sbContext.append(req.getFromDate());
		}
		
		if (req.getToDate() != null) {
			sbContext.append(" ToDate=");
			sbContext.append(req.getToDate());
		}
		return sbContext.toString();
	}

	public void transformToGetInstallationDetailResponse(List<GetAvailableTimeSlotsTask> taskInfoList, 
            GetInstallationDetailCoreRequest coreRequest,
            CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse,String salesTransactionId, GetInstallationDetailCoreResponse response){
		/**
		 * taskInfoList   - provides the input and result from bookingService
		 * installTypeSW  - provides list of serviceType that are SW
		 * coreRequest    - provides list of all orderedServiceType
		 */
		String functionName= CLASS_NAME + ".transformToGetInstallationDetailResponse2";
		
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
				availableInstallDateVO.setInstallationRequiredInd(Boolean.parseBoolean(result.getTechnicianVisitRequired()));
				availableDateVOMap.put(serviceTypeList, availableInstallDateVO);
			}
			
			//Gary add handling of exceptions
			if (task.getRuntimeException() != null){
				Exception exception = task.getRuntimeException();
				String context = "N/A";
				if (result != null)
					context = buildContext(input);
				
				logger.error("", functionName, "Exception has been returned from BookingSvc contextData: " + context + "\n" + 
						"messageList: " + exception.getMessage());
				
				response.setMessageList(transformBookingException(serviceTypeList, exception, salesTransactionId, task));
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
								availableDateTimePeriodVO.setTimeZone(timeDetail.getTimeZone());
								if(timeDetail.getAvailableAppointmentPeriods() != null) {
									for(AvailableAppointmentPeriods periods: timeDetail.getAvailableAppointmentPeriods()) {
										AvailableTimePeriodVO timePeriod = new AvailableTimePeriodVO(periods.getStartAppointmentTime(), periods.getEndAppointmentTime());
										availableDateTimePeriodVO.addAvailableTimePeriod(timePeriod);
									}
								}
								
								//Filter out any date beyond the endDate 
								if (availableDateTimePeriodVO.getFormattedAvailableDate().before(requestedEndDate))
									availableInstallDateVO.addAvailableDateTimePeriod(availableDateTimePeriodVO);
							} else {
								logger.error(EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, functionName, "Appointment date is not validate date from bookingService -> " + timeDetail.getAppointmentDate());
							}
						}
					}else{
						logger.info(functionName, "No Dates return for the Combination of ServiceType: "
								+ availableInstallDateVO.getServiceTypeList().toString());
//						// only set the DateList if it is null
//						if (availableInstallDateVO.getAvailableDateTimePeriodList() == null)
//							availableInstallDateVO.setDateList(new ArrayList<AvailableDateTimePeriodVO>());
					}
				}else{
					//Has error 
					logger.error("", functionName, "Error has been returned from BookingSvc \n" + 
					"messageList: " + result.getMessageList().toString());
					response.setMessageList(transformBookingException(serviceTypeList,result.getMessageList(),salesTransactionId));
				}
			}
//			response.addAvailableInstallDates(availableInstallDateVO);
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
	}
	
	/*****************************************************/
	/* transformException                                */ 
	/*****************************************************/ 
	public GetInstallationDetailCoreResponse transformException(Throwable ex, String salesTransactionId){
		GetInstallationDetailCoreResponse response = new GetInstallationDetailCoreResponse();
		
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
	
	
	public GetAvailableInstallDateResponseType transformServiceInstallDetailFromCoreResponse(GetAvailableInstallDateCoreResponse bookingServiceResponse){
		//if (bookingServiceResponse != null && bookingServiceResponse.gets)
		return null;
	}
	
	/*****************************************************/
	/* transformFromCoreResponse                         */
	/*    summarize all responses                        */
	/*****************************************************/
	public GetInstallationDetailResponseType transformFromCoreResponse2(GetInstallationDetailCoreResponse bookingServiceResponse){
		String functionName= CLASS_NAME + ".transformFromCoreResponse";
		
		logger.enter(functionName);
		GetInstallationDetailResponseType response = new GetInstallationDetailResponseType();
		
		Map<String, Boolean> infeasibleProductsMap = bookingServiceResponse.getInfeasibleProductsMap();
		
		
		List<AvailableInstallDatesList> availableInstallDatesList = response.getAvailableInstallDatesList();
		Map<List<String>, AvailableInstallDateVO> coreResponseMap = new HashMap<List<String>, AvailableInstallDateVO>();
		
		if ( ! bookingServiceResponse.getMessageList().isEmpty() && bookingServiceResponse.getAvailableInstallDates().isEmpty() && bookingServiceResponse.hasError()){
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
		if (bookingServiceResponse.getOrderedProductCombination() != null){
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
						if (!dateList.contains(formattedDate))
							dateList.add(formattedDate);
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
