package com.telus.csm.ewlnsms.rest.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsms.rest.domain.MessageType;
import com.telus.csm.ewlnsms.rest.domain.ResponseMessage;

public class GetPriceTransformer {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetPriceTransformer.class);
//	
//	public static GetAvailableInstallDetailCoreRequest transformFromRest(GetAvailableInstallDetailRequest request) {
//		if(request == null) {
//			return null;
//		}
//		GetAvailableInstallDetailCoreRequest req = new GetAvailableInstallDetailCoreRequest();
//		req.setCombinationRequiredIndicator(request.isCombinationRequiredIndicator());
//		req.setFromDate(request.getFromDate());
//		req.setOperationHeader(transformFromRest(request.getOperationHeader()));
//		req.setShoppingCartId(request.getShoppingCartId());
//		req.setToDate(request.getToDate());
//		return req;
//	}
//
//	private static OperationHeaderVO transformFromRest(OperationHeader operationHeader) {
//		if(operationHeader == null) {
//			return null;
//		}
//		OperationHeaderVO operationHeaderVO = new OperationHeaderVO();
//		operationHeaderVO.setSalesTransactionId(operationHeader.getSalesTransactionId());
//		return operationHeaderVO;
//	}
//
//	public static GetAvailableInstallDetailResponse transformToRest(GetAvailableInstallDetailCoreResponse coreResponse) {
//		if(coreResponse == null) {
//			return null;
//		}
//		GetAvailableInstallDetailResponse resp = new GetAvailableInstallDetailResponse();
//		resp.setAvailableProductAppointments(transformToRestAvailableProductAppointments(coreResponse.getAvailableProductAppointments()));
//		resp.setInstallationRequiredIndicator(coreResponse.getInstallationRequiredIndicator());
//		resp.setProductServiceInstallRequirement(transformToRestProductServiceInstallRequirement(coreResponse.getProductServiceInstallRequirement()));
//		return resp;
//	}
//
//	private static List<ProductServiceInstallRequirement> transformToRestProductServiceInstallRequirement(List<ProductServiceInstallRequirementVO> productServiceInstallRequirement) {
//		if(productServiceInstallRequirement == null) {
//			return null;
//		}
//		ArrayList<ProductServiceInstallRequirement> list = new ArrayList<ProductServiceInstallRequirement>();
//		for(ProductServiceInstallRequirementVO reqVO: productServiceInstallRequirement) {
//			ProductServiceInstallRequirement req = transformToRest(reqVO);
//			list.add(req);
//		}
//		return list;
//	}
//
//	private static ProductServiceInstallRequirement transformToRest(ProductServiceInstallRequirementVO reqVO) {
//		if(reqVO == null) {
//			return null;
//		}
//		ProductServiceInstallRequirement req = new ProductServiceInstallRequirement();
//		req.setEstimatedAppointmentHours(reqVO.getEstimatedAppointmentHours());
//		req.setInstallTypeCd(reqVO.getInstallTypeCd());
//		req.setProductServiceTypeCd(reqVO.getProductServiceTypeCd());
//		return req;
//	}
//
//	private static List<AvailableProductAppointment> transformToRestAvailableProductAppointments(List<AvailableProductAppointmentVO> availableProductAppointments) {
//		if(availableProductAppointments == null) {
//			return null;
//		}
//		ArrayList<AvailableProductAppointment> list = new ArrayList<AvailableProductAppointment>();
//		for(AvailableProductAppointmentVO appVO: availableProductAppointments) {
//			AvailableProductAppointment app = transformToRest(appVO);
//			list.add(app);
//		}
//		return list;
//	}
//
//	private static AvailableProductAppointment transformToRest(AvailableProductAppointmentVO appVO) {
//		if(appVO == null) {
//			return null;
//		}
//		AvailableProductAppointment app = new AvailableProductAppointment();
//		app.setAvailableAppointments(transformToRestAvailableAppointments(appVO.getAvailableAppointments()));
//		app.setProductServiceTypeCdList(appVO.getProductServiceTypeCdList());
//		return app;
//	}
//
//	private static List<AvailableAppointment> transformToRestAvailableAppointments(List<AvailableAppointmentVO> availableAppointments) {
//		if(availableAppointments == null) {
//			return null;
//		}
//		ArrayList<AvailableAppointment> list = new ArrayList<AvailableAppointment>();
//		for(AvailableAppointmentVO appVO: availableAppointments) {
//			AvailableAppointment app = transformToRest(appVO);
//			list.add(app);
//		}
//		return list;
//	}
//
//	private static AvailableAppointment transformToRest(AvailableAppointmentVO appVO) {
//		if(appVO == null) {
//			return null;
//		}
//		AvailableAppointment app = new AvailableAppointment();
//		app.setAppointmentDate(appVO.getAppointmentDate());
//		app.setAvailableTimeSlots(transformToRestAvailableTimeSlots(appVO.getAvailableTimeSlots()));
//		app.setTimeZoneCd(appVO.getTimeZoneCd());
//		return app;
//	}
//
//	private static List<AppointmentTimeSlot> transformToRestAvailableTimeSlots(List<AppointmentTimeSlotVO> availableTimeSlots) {
//		if(availableTimeSlots == null) {
//			return null;
//		}
//		ArrayList<AppointmentTimeSlot> list = new ArrayList<AppointmentTimeSlot>();
//		for(AppointmentTimeSlotVO slotVO: availableTimeSlots) {
//			AppointmentTimeSlot slot = transformToRest(slotVO);
//			list.add(slot);
//		}
//		return list;
//	}
//
//	private static AppointmentTimeSlot transformToRest(AppointmentTimeSlotVO slotVO) {
//		if(slotVO == null) {
//			return null;
//		}
//		AppointmentTimeSlot slot = new AppointmentTimeSlot();
//		slot.setEndTime(slotVO.getEndTime());
//		slot.setStartTime(slotVO.getStartTime());
//		return slot;
//	}

	public static List<ResponseMessage> transformResponseMessages(List<String> messageList,String errorCode) {
		List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
		ResponseMessage responseMsg = new ResponseMessage();
		responseMsg.setErrorCode(errorCode);
		responseMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		responseMsg.setMessages(transformMessages(messageList));
		responseMessageList.add(responseMsg);
		return responseMessageList;
	}

	private static List<MessageType> transformMessages(List<String> messageList) {
		List<MessageType> messageTypeList = new ArrayList<MessageType>();
		if(messageList!=null && !messageList.isEmpty()){
			for(String errorMessage : messageList){
				MessageType messageType = new MessageType();
				messageType.setLocaleCd(Locale.CANADA.toString());
				messageType.setMessageTxt(errorMessage);
				messageTypeList.add(messageType);
			}
		}
		return messageTypeList;
	}
//
//	public static GetAvailableInstallDetailResponse transformToRestFromCore(
//			GetAvailableInstallDetailCoreResponse coreResponse) {
//		
//		GetAvailableInstallDetailResponse response = new GetAvailableInstallDetailResponse();
//		
//		Map<String,Boolean> infeasibleProductsMap = coreResponse.getInfeasibleProductsMap();
//		
//		List<AvailableProductAppointment> availableProductAppointments = new ArrayList<AvailableProductAppointment>();
//		
//		Map<List<String>,AvailableProductAppointmentVO> coreResponseMap = new HashMap<List<String>, AvailableProductAppointmentVO>();
//		
//		/**
//		 * step 1: build map coreResponseMap using sorted serviceTypes as key
//		 */
//		if(coreResponse.getAvailableProductAppointments()!=null && !coreResponse.getAvailableProductAppointments().isEmpty()){
//			for(AvailableProductAppointmentVO availableAppointment : coreResponse.getAvailableProductAppointments()){
//				Collections.sort(availableAppointment.getProductServiceTypeCdList());
//				coreResponseMap.put(availableAppointment.getProductServiceTypeCdList(), availableAppointment);
//			}
//		}
//		
//		/**
//		 * step 2: Special rule for TTV
//		 * 
//		 *  a) if response has HSIC+TTV =, TTV will use result from HSIC+TTV
//		 *  b) if request has TTV and HSIC and SING, replace SINGTTV with HSICSINGTTV
//		 */
//		
//		// a)
//		if(coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getHsicTTVList()) && !coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getTTVList())){
//			coreResponseMap.put(GetAvailableInstalLDateConstants.getTTVList(), coreResponseMap.get(GetAvailableInstalLDateConstants.getHsicTTVList()));
//		}
//		
//		// b)
//		if(coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getHsicSingTtvList())){
//			coreResponseMap.put(GetAvailableInstalLDateConstants.getSingTTVList(), coreResponseMap.get(GetAvailableInstalLDateConstants.getHsicSingTtvList()));
//		}
//		
//		
//		/**
//		 * step 3: setup list of infeasible products
//		 */
//		List<String> infeasibleProducts = new ArrayList<String>();
//		if(infeasibleProductsMap != null) {
//			Set<String> keySet = infeasibleProductsMap.keySet();
//			for(String keyValue : keySet){
//				if(infeasibleProductsMap.get(keyValue)){
//					infeasibleProducts.add(keyValue);
//				}
//			}
//		}
//		
//		/**
//		 * step 4: populate result with all combinations of serviceTypes
//		 */
//		if(coreResponse.getOrderedProductCombination()!=null){
//			for(List<String> combinationList : coreResponse.getOrderedProductCombination()){
//				Collections.sort(combinationList);
//				AvailableProductAppointment availableProductAppointment = new AvailableProductAppointment();
//				availableProductAppointment.setProductServiceTypeCdList(combinationList);
//				
//				/**
//				 * find dates from coreReponse - the list of serviceType from coreResponse is already sorted
//				 * TTV - search for TTV, HSIC + TTV, HSIC + SING + TTV in sequence
//				 */
//				AvailableProductAppointmentVO availableProductAppointmentVO = findAvailableProductAppointmentVO(combinationList,coreResponse.getServiceTypeSWList(),coreResponseMap,infeasibleProducts);
//			
//				if(availableProductAppointmentVO!=null){
//					//check possible duplication at this point.
//					if(availableProductAppointmentVO.getAvailableAppointments()!=null && !availableProductAppointmentVO.getAvailableAppointments().isEmpty()){
//						availableProductAppointment.setAvailableAppointments(getAvailableProductAppointmentVO(availableProductAppointmentVO.getAvailableAppointments()));
//						availableProductAppointments.add(availableProductAppointment);
//					}
//					
//				}else{
//					/**
//					 * did not find a downstream response for the serviceType combination.  Return installation = false and empty dates
//					 */
//					AvailableProductAppointment emptyProductAppointment = new AvailableProductAppointment();
//					emptyProductAppointment.setProductServiceTypeCdList(combinationList);
//					availableProductAppointments.add(emptyProductAppointment);
//				}
//			}
//		}
//		
//		response.setAvailableProductAppointments(availableProductAppointments);
//		
//		if(response.getAvailableProductAppointments()!=null && !response.getAvailableProductAppointments().isEmpty()){
//			response.setInstallationRequiredIndicator(Boolean.TRUE);
//		}else{
//			response.setInstallationRequiredIndicator(Boolean.FALSE);
//		}
//		
//		response.setProductServiceInstallRequirement(getProductServiceInstallRequirementFromCore(coreResponse.getProductServiceInstallRequirement()));
//		
//		return response;
//	}
//
//
//	private static List<ProductServiceInstallRequirement> getProductServiceInstallRequirementFromCore(
//			List<ProductServiceInstallRequirementVO> productServiceInstallRequirementVO) {
//		List<ProductServiceInstallRequirement> productServiceInstallRequirementList = new ArrayList<ProductServiceInstallRequirement>();
//		if(productServiceInstallRequirementVO!=null && !productServiceInstallRequirementVO.isEmpty()){
//			for(ProductServiceInstallRequirementVO productServiceInstallReqVO : productServiceInstallRequirementVO){
//				ProductServiceInstallRequirement serviceInstallRequirement = new ProductServiceInstallRequirement();
//				if(productServiceInstallReqVO.getEstimatedAppointmentHours()!=null){
//					serviceInstallRequirement.setEstimatedAppointmentHours(productServiceInstallReqVO.getEstimatedAppointmentHours());
//				}
//				if(productServiceInstallReqVO.getInstallTypeCd()!=null){
//					serviceInstallRequirement.setInstallTypeCd(productServiceInstallReqVO.getInstallTypeCd());
//				}
//				if(productServiceInstallReqVO.getProductServiceTypeCd()!=null){
//					serviceInstallRequirement.setProductServiceTypeCd(productServiceInstallReqVO.getProductServiceTypeCd());
//				}
//				productServiceInstallRequirementList.add(serviceInstallRequirement);
//				
//			}
//		}
//		return productServiceInstallRequirementList;
//	}
//
//	private static List<AvailableAppointment> getAvailableProductAppointmentVO(
//			List<AvailableAppointmentVO> availableAppointments) {
//		List<AvailableAppointment> availableAppointmentList = new ArrayList<AvailableAppointment>();
//		if(availableAppointments!=null && !availableAppointments.isEmpty()){
//			for(AvailableAppointmentVO availableAppointmentVO : availableAppointments){
//				AvailableAppointment availableAppointment = new AvailableAppointment();
//				availableAppointment.setAppointmentDate(availableAppointmentVO.getAppointmentDate());
//				availableAppointment.setAvailableTimeSlots(getAvailableTimeSlotsToRest(availableAppointmentVO.getAvailableTimeSlots()));
//				availableAppointment.setTimeZoneCd(availableAppointmentVO.getTimeZoneCd());
//				availableAppointmentList.add(availableAppointment);
//			}
//		}
//		
//		return availableAppointmentList;
//	}
//
//	private static List<AppointmentTimeSlot> getAvailableTimeSlotsToRest(
//			List<AppointmentTimeSlotVO> availableTimeSlots) {
//		List<AppointmentTimeSlot> appointmentSlotList = new ArrayList<AppointmentTimeSlot>();
//		if(availableTimeSlots!=null && !availableTimeSlots.isEmpty()){
//			for(AppointmentTimeSlotVO slotVO : availableTimeSlots){
//				AppointmentTimeSlot appointmentTimeSlot = new AppointmentTimeSlot();
//				appointmentTimeSlot.setStartTime(slotVO.getStartTime());
//				appointmentTimeSlot.setEndTime(slotVO.getEndTime());
//				appointmentSlotList.add(appointmentTimeSlot);
//			}
//		}
//		return appointmentSlotList;
//	}
//
//	private static AvailableProductAppointmentVO findAvailableProductAppointmentVO(List<String> combinationList,
//			List<String> serviceTypeSWList, Map<List<String>, AvailableProductAppointmentVO> coreResponseMap,
//			List<String> infeasibleProducts) {
//		/**
//		 * Look for downstream response based on request serviceType. the list must be sorted
//		 * 
//		 * 1 - if request has only 1 serviceType
//		 *     - if serviceType is SW 
//		 *          return an empty vo
//		 *       else
//		 *          return vo from downstream response.
//		 *          
//		 *  2 - if request has more than 1 serviceType
//		 *      - build searchArgument
//		 *      - append service type to search argument unless the serviceType is SW 
//		 *      - if one of the serviceType is not feasible, return empty vo
//		 *      - return vo from downstream response that matches the search argument.
//		 * 
//		 */
//		AvailableProductAppointmentVO vo = null;
//		String functionName = "findAvailableProductAppointmentVO";
//		String serviceTypeListJoined = StringUtils.join(combinationList);
//		logger.info(functionName, "Find availabeInstallDates for ServiceType => " +serviceTypeListJoined);
//		
//		if (combinationList.size() == 1){
//			String serviceType = combinationList.get(0);
//			if (serviceTypeSWList.contains(serviceType) || infeasibleProducts.contains(serviceType)){
//				logger.info(functionName, serviceType + " is SW or not feasible");
//			}
//			else { 
//				if (coreResponseMap.containsKey(combinationList)){
//					vo = coreResponseMap.get(combinationList);
//				}
//			}
//		}else {
//			/**
//			 * build a list of serivceType by excluding service type SW. Then use the list to search coreResponseMap.   
//			 */
//			List<String> serviceTypeCombine = new ArrayList<String>();
//			
//			for (String serviceType : combinationList){ 
//				if ( ! serviceTypeSWList.contains(serviceType) ){
//					serviceTypeCombine.add(serviceType);
//				}
//				/**
//				 *  If any product has feasibilityResult=false && bestAvailableConfigurationInd==false then 
//				 *  installationRequiredInd=true (although no dates will be returned)
//				 */
//				if (infeasibleProducts.contains(serviceType)){
//					logger.info(functionName, serviceType + " is not feasible, no date will be return"); 
//					break;
//				}
//			}
//			
//			if (coreResponseMap.containsKey(serviceTypeCombine)){
//				vo = coreResponseMap.get(serviceTypeCombine);
//			}  
//		}
//		
//		return vo;
//	}
//

}
