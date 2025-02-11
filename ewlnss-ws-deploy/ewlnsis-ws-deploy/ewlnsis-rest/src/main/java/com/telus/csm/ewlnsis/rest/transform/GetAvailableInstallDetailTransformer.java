package com.telus.csm.ewlnsis.rest.transform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.AppointmentTimeSlotVO;
import com.telus.csm.ewlnsc.domain.AvailableAppointmentVO;
import com.telus.csm.ewlnsc.domain.AvailableInstallCreditTypeVO;
import com.telus.csm.ewlnsc.domain.AvailableProductAppointmentVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.OperationHeaderVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductServiceInstallRequirementVO;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreResponse;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants;
import com.telus.csm.ewlnsis.rest.domain.AppointmentTimeSlot;
import com.telus.csm.ewlnsis.rest.domain.AvailableAppointment;
import com.telus.csm.ewlnsis.rest.domain.AvailableInstallCreditType;
import com.telus.csm.ewlnsis.rest.domain.AvailableProductAppointment;
import com.telus.csm.ewlnsis.rest.domain.DiscountItem;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentRequest;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentResponse;
import com.telus.csm.ewlnsis.rest.domain.WirelineDiscountType;
import com.telus.csm.ewlnsis.rest.domain.WirelineOfferHeader;
import com.telus.csm.ewlnsis.rest.domain.WirelineSweetenerType;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentRequest;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentResponse;
import com.telus.csm.ewlnsis.rest.domain.MessageType;
import com.telus.csm.ewlnsis.rest.domain.OperationHeader;
import com.telus.csm.ewlnsis.rest.domain.ProductItemIdentifier;
import com.telus.csm.ewlnsis.rest.domain.ProductServiceInstallRequirement;
import com.telus.csm.ewlnsis.rest.domain.ResponseMessage;
import com.telus.csm.ewlnss.rest.time.DateTime;
import com.telus.csm.ewlnss.rest.time.LocalDate;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.Component;

public class GetAvailableInstallDetailTransformer {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableInstallDetailTransformer.class);
	
	public static GetAvailableInstallDetailCoreRequest transformFromRest(GetAvailableAppointmentRequest request) {
		if(request == null) {
			return null;
		}
		GetAvailableInstallDetailCoreRequest req = new GetAvailableInstallDetailCoreRequest();
		req.setCombinationRequiredIndicator(request.isCombinationRequiredIndicator());
		req.setFromDate(request.getFromDate() != null? request.getFromDate().toDate(): null);
		req.setOperationHeader(transformFromRest(request.getOperationHeader()));
		req.setShoppingCartId(request.getShoppingCartId());
		req.setToDate(request.getToDate() != null? request.getToDate().toDate(): null);
		return req;
	}

	private static OperationHeaderVO transformFromRest(OperationHeader operationHeader) {
		if(operationHeader == null) {
			return null;
		}
		OperationHeaderVO operationHeaderVO = new OperationHeaderVO();
		operationHeaderVO.setSalesTransactionId(operationHeader.getSalesTransactionId());
		operationHeaderVO.setSourceSystemId(operationHeader.getSourceSystemId());
		operationHeaderVO.setUserId( operationHeader.getUserId());
		return operationHeaderVO;
	}

	public static GetAvailableAppointmentResponse transformToRest(GetAvailableInstallDetailCoreResponse coreResponse) {
		if(coreResponse == null) {
			return null;
		}
		GetAvailableAppointmentResponse resp = new GetAvailableAppointmentResponse();
		resp.setAvailableProductAppointments(transformToRestAvailableProductAppointments(coreResponse.getAvailableProductAppointments()));
		resp.setInstallationRequiredIndicator(coreResponse.getInstallationRequiredIndicator());
		resp.setProductServiceInstallRequirement(transformToRestProductServiceInstallRequirement(coreResponse.getProductServiceInstallRequirement()));
		return resp;
	}

	private static List<ProductServiceInstallRequirement> transformToRestProductServiceInstallRequirement(List<ProductServiceInstallRequirementVO> productServiceInstallRequirement) {
		if(productServiceInstallRequirement == null) {
			return null;
		}
		ArrayList<ProductServiceInstallRequirement> list = new ArrayList<ProductServiceInstallRequirement>();
		for(ProductServiceInstallRequirementVO reqVO: productServiceInstallRequirement) {
			ProductServiceInstallRequirement req = transformToRest(reqVO);
			list.add(req);
		}
		return list;
	}

	private static ProductServiceInstallRequirement transformToRest(ProductServiceInstallRequirementVO reqVO) {
		if(reqVO == null) {
			return null;
		}
		ProductServiceInstallRequirement req = new ProductServiceInstallRequirement();
		req.setEstimatedAppointmentHoursAmt(reqVO.getEstimatedAppointmentHours());
		req.setInstallTypeCd(reqVO.getInstallTypeCd());
		req.setProductServiceTypeCd(reqVO.getProductServiceTypeCd());
		return req;
	}

	private static List<AvailableProductAppointment> transformToRestAvailableProductAppointments(List<AvailableProductAppointmentVO> availableProductAppointments) {
		if(availableProductAppointments == null) {
			return null;
		}
		ArrayList<AvailableProductAppointment> list = new ArrayList<AvailableProductAppointment>();
		for(AvailableProductAppointmentVO appVO: availableProductAppointments) {
			AvailableProductAppointment app = transformToRest(appVO);
			list.add(app);
		}
		return list;
	}

	private static AvailableProductAppointment transformToRest(AvailableProductAppointmentVO appVO) {
		if(appVO == null) {
			return null;
		}
		AvailableProductAppointment app = new AvailableProductAppointment();
		app.setAvailableAppointments(transformToRestAvailableAppointments(appVO.getAvailableAppointments()));
		app.setProductServiceTypeCdList(appVO.getProductServiceTypeCdList());
		return app;
	}

	private static List<AvailableAppointment> transformToRestAvailableAppointments(List<AvailableAppointmentVO> availableAppointments) {
		if(availableAppointments == null) {
			return null;
		}
		ArrayList<AvailableAppointment> list = new ArrayList<AvailableAppointment>();
		for(AvailableAppointmentVO appVO: availableAppointments) {
			AvailableAppointment app = transformToRest(appVO);
			list.add(app);
		}
		return list;
	}

	private static AvailableAppointment transformToRest(AvailableAppointmentVO appVO) {
		if(appVO == null) {
			return null;
		}
		AvailableAppointment app = new AvailableAppointment();
		app.setAppointmentDate(appVO.getAppointmentDate() != null? new LocalDate(appVO.getAppointmentDate()): null);
		app.setAvailableTimeSlots(transformToRestAvailableTimeSlots(appVO.getAvailableTimeSlots()));
		app.setTimeZoneCd(appVO.getTimeZoneCd());
		return app;
	}

	private static List<AppointmentTimeSlot> transformToRestAvailableTimeSlots(List<AppointmentTimeSlotVO> availableTimeSlots) {
		if(availableTimeSlots == null) {
			return null;
		}
		ArrayList<AppointmentTimeSlot> list = new ArrayList<AppointmentTimeSlot>();
		for(AppointmentTimeSlotVO slotVO: availableTimeSlots) {
			AppointmentTimeSlot slot = transformToRest(slotVO);
			list.add(slot);
		}
		return list;
	}

	private static AppointmentTimeSlot transformToRest(AppointmentTimeSlotVO slotVO) {
		if(slotVO == null) {
			return null;
		}
		AppointmentTimeSlot slot = new AppointmentTimeSlot();
		slot.setEndTime(slotVO.getEndTime());
		slot.setStartTime(slotVO.getStartTime());
		return slot;
	}

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

	public static GetAvailableAppointmentResponse transformToRestFromCore(
			GetAvailableInstallDetailCoreResponse coreResponse) {
		
		GetAvailableAppointmentResponse response = new GetAvailableAppointmentResponse();
		
		Map<String,Boolean> infeasibleProductsMap = coreResponse.getInfeasibleProductsMap();
		
		List<AvailableProductAppointment> availableProductAppointments = new ArrayList<AvailableProductAppointment>();
		
		Map<List<String>,AvailableProductAppointmentVO> coreResponseMap = new HashMap<List<String>, AvailableProductAppointmentVO>();
		
		/**
		 * step 1: build map coreResponseMap using sorted serviceTypes as key
		 */
		if(coreResponse.getAvailableProductAppointments()!=null && !coreResponse.getAvailableProductAppointments().isEmpty()){
			for(AvailableProductAppointmentVO availableAppointment : coreResponse.getAvailableProductAppointments()){
				Collections.sort(availableAppointment.getProductServiceTypeCdList());
				coreResponseMap.put(availableAppointment.getProductServiceTypeCdList(), availableAppointment);
			}
		}
		
		/**
		 * step 2: Special rule for TTV
		 * 
		 *  a) if response has HSIC+TTV =, TTV will use result from HSIC+TTV
		 *  b) if request has TTV and HSIC and SING, replace SINGTTV with HSICSINGTTV
		 */
		
		// a)
		if(coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getHsicTTVList()) && !coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getTTVList())){
			coreResponseMap.put(GetAvailableInstalLDateConstants.getTTVList(), coreResponseMap.get(GetAvailableInstalLDateConstants.getHsicTTVList()));
		}
		
		// b)
		if(coreResponseMap.containsKey(GetAvailableInstalLDateConstants.getHsicSingTtvList())){
			coreResponseMap.put(GetAvailableInstalLDateConstants.getSingTTVList(), coreResponseMap.get(GetAvailableInstalLDateConstants.getHsicSingTtvList()));
		}
		
		
		/**
		 * step 3: setup list of infeasible products
		 */
		List<String> infeasibleProducts = new ArrayList<String>();
		if(infeasibleProductsMap != null) {
			Set<String> keySet = infeasibleProductsMap.keySet();
			for(String keyValue : keySet){
				if(infeasibleProductsMap.get(keyValue)){
					infeasibleProducts.add(keyValue);
				}
			}
		}
		
		/**
		 * step 4: populate result with all combinations of serviceTypes
		 */
		if(coreResponse.getOrderedProductCombination()!=null){
			for(List<String> combinationList : coreResponse.getOrderedProductCombination()){
				Collections.sort(combinationList);
				AvailableProductAppointment availableProductAppointment = new AvailableProductAppointment();
				availableProductAppointment.setProductServiceTypeCdList(combinationList);
				
				/**
				 * find dates from coreReponse - the list of serviceType from coreResponse is already sorted
				 * TTV - search for TTV, HSIC + TTV, HSIC + SING + TTV in sequence
				 */
				AvailableProductAppointmentVO availableProductAppointmentVO = findAvailableProductAppointmentVO(combinationList,coreResponse.getServiceTypeSWList(),coreResponseMap,infeasibleProducts);
			
				if(availableProductAppointmentVO!=null){
					//check possible duplication at this point.
					if(availableProductAppointmentVO.getAvailableAppointments()!=null && !availableProductAppointmentVO.getAvailableAppointments().isEmpty()){
						availableProductAppointment.setAvailableAppointments(getAvailableProductAppointmentVO(availableProductAppointmentVO.getAvailableAppointments()));
						availableProductAppointments.add(availableProductAppointment);
					}
					
				}else{
					/**
					 * did not find a downstream response for the serviceType combination.  Return installation = false and empty dates
					 */
					AvailableProductAppointment emptyProductAppointment = new AvailableProductAppointment();
					emptyProductAppointment.setProductServiceTypeCdList(combinationList);
					availableProductAppointments.add(emptyProductAppointment);
				}
			}
		}
		
		response.setAvailableProductAppointments(availableProductAppointments);
		
		if(response.getAvailableProductAppointments()!=null && !response.getAvailableProductAppointments().isEmpty()){
			response.setInstallationRequiredIndicator(Boolean.TRUE);
		}else{
			response.setInstallationRequiredIndicator(Boolean.FALSE);
		}
		
		response.setProductServiceInstallRequirement(getProductServiceInstallRequirementFromCore(coreResponse.getProductServiceInstallRequirement()));
		
		return response;
	}

	public static GetAvailableAppointmentResponse transformToRestFromCoreFifaFlow(final GetAvailableInstallDetailCoreResponse coreResponse) {
		final GetAvailableAppointmentResponse response = new GetAvailableAppointmentResponse();
		
		final List<AvailableProductAppointment> availableProductAppointments = new ArrayList<AvailableProductAppointment>();

		if (coreResponse.getAvailableAppointmentList() != null) {
			final AvailableProductAppointment availableProductAppointment = new AvailableProductAppointment();
			final List<AvailableAppointment> availableAppointments = new ArrayList<AvailableAppointment>();
			availableProductAppointment.setAvailableAppointments(availableAppointments);
			
			final String FORMAT_DATE = "yyyy-MM-dd";
			final String FORMAT_TIME = "HH:mm:ss";
			final String FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
			final Map<String, List<AppointmentTimeSlot>> map = new TreeMap<String, List<AppointmentTimeSlot>>();

			SimpleDateFormat gmtDateFormat = new SimpleDateFormat(FORMAT_DATE_TIME); 
			gmtDateFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

			for (final com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.AvailableAppointment elem : coreResponse.getAvailableAppointmentList()) {
				String ymd = (new SimpleDateFormat(FORMAT_DATE)).format(elem.getStartDate()); 
				if (map.get(ymd) == null) {
					map.put(ymd, new ArrayList<AppointmentTimeSlot>());
				}
				
				AppointmentTimeSlot ats = new AppointmentTimeSlot();
				ats.setStartTime(gmtDateFormat.format(elem.getStartDate())); 
				ats.setEndTime(gmtDateFormat.format(elem.getEndDate())); 
				
				map.get(ymd).add(ats);
			}
			
			for (final String elemKey : map.keySet()) {
				AvailableAppointment aaa = new AvailableAppointment();
				Date elemKeyDate = null;
				try {
					elemKeyDate = new SimpleDateFormat(FORMAT_DATE).parse(elemKey);
				} catch (ParseException e) {
				}
				if (elemKeyDate != null && EnterpriseSalesServiceUtil.isEligibleForInstallCredit(elemKeyDate)) {
					List<AvailableInstallCreditType> credits = new ArrayList<AvailableInstallCreditType>();
					AvailableInstallCreditType aicType = new AvailableInstallCreditType();
					aicType.setProductTypeCd("eligible for credit");
					credits.add(aicType);
					aaa.setAvailableInstallCreditList(credits);
				}
				aaa.setAppointmentDate(new LocalDate(elemKey));
				aaa.setAvailableTimeSlots(map.get(elemKey));
				if (coreResponse.getWorkOrder() != null && coreResponse.getWorkOrder().getLocation() != null) {
					aaa.setTimeZoneCd(coreResponse.getWorkOrder().getLocation().getTimezoneCd());
				}
				availableAppointments.add(aaa);
			}
			
			availableProductAppointments.add(availableProductAppointment);
		}
		
		response.setAvailableProductAppointments(availableProductAppointments);
		
		if (coreResponse.getWorkOrder() != null) {
			response.setInstallationRequiredIndicator(Boolean.TRUE);
			if (coreResponse.getWorkOrder().getComponentList() != null) {
				final List<ProductServiceInstallRequirement> productServiceInstallRequirementList = new ArrayList<ProductServiceInstallRequirement>();
				for (final Component elem : coreResponse.getWorkOrder().getComponentList().getComponent()) {
					ProductServiceInstallRequirement productServiceInstallRequirement = new ProductServiceInstallRequirement();
					productServiceInstallRequirement.setProductServiceTypeCd(elem.getProductCategoryCd());
					productServiceInstallRequirement.setInstallTypeCd(elem.getInstallationTypeCd());
					productServiceInstallRequirement.setEstimatedAppointmentHoursAmt(Double.valueOf(elem.getEstimatedDurationNum()));
					productServiceInstallRequirementList.add(productServiceInstallRequirement);
				}
				response.setProductServiceInstallRequirement(productServiceInstallRequirementList);
			}
		} else {
			response.setInstallationRequiredIndicator(Boolean.FALSE);
		}
				
		return response;
	}
	
	private static List<ProductServiceInstallRequirement> getProductServiceInstallRequirementFromCore(
			List<ProductServiceInstallRequirementVO> productServiceInstallRequirementVO) {
		List<ProductServiceInstallRequirement> productServiceInstallRequirementList = new ArrayList<ProductServiceInstallRequirement>();
		if(productServiceInstallRequirementVO!=null && !productServiceInstallRequirementVO.isEmpty()){
			for(ProductServiceInstallRequirementVO productServiceInstallReqVO : productServiceInstallRequirementVO){
				ProductServiceInstallRequirement serviceInstallRequirement = new ProductServiceInstallRequirement();
				if(productServiceInstallReqVO.getEstimatedAppointmentHours()!=null){
					serviceInstallRequirement.setEstimatedAppointmentHoursAmt(productServiceInstallReqVO.getEstimatedAppointmentHours());
				}
				if(productServiceInstallReqVO.getInstallTypeCd()!=null){
					serviceInstallRequirement.setInstallTypeCd(productServiceInstallReqVO.getInstallTypeCd());
				}
				if(productServiceInstallReqVO.getProductServiceTypeCd()!=null){
					serviceInstallRequirement.setProductServiceTypeCd(productServiceInstallReqVO.getProductServiceTypeCd());
				}
				productServiceInstallRequirementList.add(serviceInstallRequirement);
				
			}
		}
		return productServiceInstallRequirementList;
	}

	private static List<AvailableAppointment> getAvailableProductAppointmentVO(
			List<AvailableAppointmentVO> availableAppointments) {
		List<AvailableAppointment> availableAppointmentList = new ArrayList<AvailableAppointment>();
		if(availableAppointments!=null && !availableAppointments.isEmpty()){
			for(AvailableAppointmentVO availableAppointmentVO : availableAppointments){
				AvailableAppointment availableAppointment = new AvailableAppointment();
				availableAppointment.setAppointmentDate(availableAppointmentVO.getAppointmentDate() != null? new LocalDate(availableAppointmentVO.getAppointmentDate()): null);
				availableAppointment.setAvailableTimeSlots(getAvailableTimeSlotsToRest(availableAppointmentVO.getAvailableTimeSlots()));
				availableAppointment.setTimeZoneCd(availableAppointmentVO.getTimeZoneCd());
				availableAppointment.setAvailableInstallCreditList(getAvailableTInstallCreditsToRest(availableAppointmentVO.getAvailableInstallCreditList()));
				availableAppointmentList.add(availableAppointment);
			}
		}
		
		return availableAppointmentList;
	}

	private static List<AvailableInstallCreditType> getAvailableTInstallCreditsToRest(
			List<AvailableInstallCreditTypeVO> availableInstallCreditList) {
		List<AvailableInstallCreditType> result = new ArrayList<AvailableInstallCreditType>();
		if(availableInstallCreditList != null && !availableInstallCreditList.isEmpty()) {
			for(AvailableInstallCreditTypeVO item: availableInstallCreditList) {
				AvailableInstallCreditType resultItem = new AvailableInstallCreditType();
				resultItem.setProductTypeCd(item.getProductType());
				resultItem.setSweetener(getFfhSweetenerToRest(item.getSweetener()));
				result.add(resultItem);
			}
		}
		
		return result;
	}

	private static WirelineSweetenerType getFfhSweetenerToRest(FFHSweetenerTypeVO sweetener) {
		WirelineSweetenerType result = new WirelineSweetenerType();
		result.setOfferHeader(getOfferHeaderToRest(sweetener.getOfferHeader()));
		result.setDiscounts(getDiscountsToRest(sweetener.getDiscounts()));
		return result;
	}

	private static List<WirelineDiscountType> getDiscountsToRest(List<FFHDiscountTypeVO> discounts) {
		List<WirelineDiscountType> result = new ArrayList<WirelineDiscountType>();
		if(discounts != null && !discounts.isEmpty()) {
			for(FFHDiscountTypeVO discount : discounts) {
				WirelineDiscountType item = new WirelineDiscountType();
				item.setDiscountCode(discount.getDiscountCode());
				item.setDiscountItemList(getDiscountItemListToRest(discount.getProductCatalogueIdentifiers()));
				result.add(item);
			}
		}
		return result;
	}
	
	private static List<DiscountItem> getDiscountItemListToRest(List<ProductComponentVO> productCatalogueIdentifiers) {
		List<DiscountItem> discountItemList = new ArrayList<DiscountItem>();
		if(productCatalogueIdentifiers != null) {
			for(ProductComponentVO productCatalougueIdentifier : productCatalogueIdentifiers) {
				DiscountItem discountItem = new DiscountItem();
				discountItem.setActionCd(productCatalougueIdentifier.getAction());
				discountItem.setProductCatalogueIdentifier(mapProductItemIdentifier(productCatalougueIdentifier));
				discountItemList.add(discountItem);
			}
		}
		return discountItemList;
	}
	
	private static ProductItemIdentifier mapProductItemIdentifier(ProductComponentVO productComponentVO) {
		if(productComponentVO == null) {
			return null;
		}
		ProductItemIdentifier productItemIdentifier = new ProductItemIdentifier();
		productItemIdentifier.setProductCatalogueId(productComponentVO.getProductCatalogueId());
		productItemIdentifier.setParentProductCatalogueId(productComponentVO.getParentProductCatalogueId());
		return productItemIdentifier;
		
	}

	private static WirelineOfferHeader getOfferHeaderToRest(FFHOfferHeaderVO offerHeader) {
		WirelineOfferHeader result = new WirelineOfferHeader();
		result.setOfferCode(offerHeader.getOfferCode());
		result.setOfferId(offerHeader.getOfferId());
		result.setOfferProgramId(offerHeader.getOfferProgramId());
		//TODO:: result.setOfferPromotionCode(offerHeader.get);
		result.setPerspectiveDate(offerHeader.getPerspectiveDate() != null? new DateTime(offerHeader.getPerspectiveDate()): null);
		result.setSystemId(offerHeader.getSystemId());
		return result;
	}

	private static List<AppointmentTimeSlot> getAvailableTimeSlotsToRest(
			List<AppointmentTimeSlotVO> availableTimeSlots) {
		List<AppointmentTimeSlot> appointmentSlotList = new ArrayList<AppointmentTimeSlot>();
		if(availableTimeSlots!=null && !availableTimeSlots.isEmpty()){
			for(AppointmentTimeSlotVO slotVO : availableTimeSlots){
				AppointmentTimeSlot appointmentTimeSlot = new AppointmentTimeSlot();
				appointmentTimeSlot.setStartTime(slotVO.getStartTime());
				appointmentTimeSlot.setEndTime(slotVO.getEndTime());
				appointmentSlotList.add(appointmentTimeSlot);
			}
		}
		return appointmentSlotList;
	}

	private static AvailableProductAppointmentVO findAvailableProductAppointmentVO(List<String> combinationList,
			List<String> serviceTypeSWList, Map<List<String>, AvailableProductAppointmentVO> coreResponseMap,
			List<String> infeasibleProducts) {
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
		AvailableProductAppointmentVO vo = null;
		String functionName = "findAvailableProductAppointmentVO";
		String serviceTypeListJoined = StringUtils.join(combinationList);
		logger.info(functionName, "Find availabeInstallDates for ServiceType => " +serviceTypeListJoined);
		
		if (combinationList.size() == 1){
			String serviceType = combinationList.get(0);
			if ((serviceTypeSWList!=null && serviceTypeSWList.contains(serviceType)) || (infeasibleProducts!=null && infeasibleProducts.contains(serviceType))){
				logger.info(functionName, serviceType + " is SW or not feasible");
			}
			else { 
				if (coreResponseMap.containsKey(combinationList)){
					vo = coreResponseMap.get(combinationList);
				}
			}
		}else {
			/**
			 * build a list of serivceType by excluding service type SW. Then use the list to search coreResponseMap.   
			 */
			List<String> serviceTypeCombine = new ArrayList<String>();
			
			for (String serviceType : combinationList){ 
				if (serviceTypeSWList!=null &&  ! serviceTypeSWList.contains(serviceType) ){
					serviceTypeCombine.add(serviceType);
				}
				/**
				 *  If any product has feasibilityResult=false && bestAvailableConfigurationInd==false then 
				 *  installationRequiredInd=true (although no dates will be returned)
				 */
				if (infeasibleProducts!=null && infeasibleProducts.contains(serviceType)){
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


}
