package com.telus.csm.ewlnsis.core.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.ess.common.domain.ContextDataAttributesVO;
import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.InstallationRequirementsList;
import com.telus.csm.ewlnsc.domain.AppointmentTimeSlotVO;
import com.telus.csm.ewlnsc.domain.AvailableAppointmentVO;
import com.telus.csm.ewlnsc.domain.AvailableDateTimePeriodVO;
import com.telus.csm.ewlnsc.domain.AvailableInstallCreditTypeVO;
import com.telus.csm.ewlnsc.domain.AvailableInstallDateVO;
import com.telus.csm.ewlnsc.domain.AvailableProductAppointmentVO;
import com.telus.csm.ewlnsc.domain.AvailableTimePeriodVO;
import com.telus.csm.ewlnsc.domain.BookingServiceVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductServiceInstallRequirementVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ServiceInstallDetailVO;
import com.telus.csm.ewlnsc.domain.ServiceTypeFeasibilityVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceVO;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.helper.OperationHeaderUtil;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.task.GetAvailableTimeSlotsTask;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreResponse;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreResponse;
import com.telus.csm.ewlnsis.core.exceptions.BookingDateCalculationException;
import com.telus.csm.ewlnsis.core.exceptions.LagTimeException;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductSummary;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductSummary.ProductInstance;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.AvailableAppointment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem;


import com.telus.csm.ewlnsc.adapter.wbk.domain.AppointmentTimeDetail;
import com.telus.csm.ewlnsc.adapter.wbk.domain.AvailableAppointmentPeriods;


public class GetAvailableInstallDetailCoreTransformer {
	private static final String DATE_FORMAT = "yyyyMMdd"; 

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableInstallDetailCoreTransformer.class);
	
	public static GetInstallationDetailCoreRequest transfromToPrevImpl(GetAvailableInstallDetailCoreRequest request, ShoppingCartVO shoppingCartVO) {
		if(request == null || shoppingCartVO == null) {
			return null;
		}
		ShoppingCartContextHelper contextHelper = new ShoppingCartContextHelper();
		ShoppingCartContextVO contextVO = contextHelper.execute(request.getShoppingCartId());
		GetInstallationDetailCoreRequest req = new GetInstallationDetailCoreRequest();
		//TODO req.setAttributeExclusionList(attributeExclusionList); //TODO We don't do it for now
		req.setCombinationInd(request.getCombinationRequiredIndicator());
		req.setEndDate(request.getToDate());
		if(shoppingCartVO.getShoppingProfile() != null && shoppingCartVO.getShoppingProfile().getAgentProfile() != null && shoppingCartVO.getShoppingProfile().getAgentProfile().getUserPrivilegeRoleCodes() !=null) {
			if(shoppingCartVO.getShoppingProfile().getAgentProfile().getUserPrivilegeRoleCodes().contains(Constants.D2C_PARTNER)) {
				req.setOutboundChannel(true);
				req.setInboundChannel(false);
			} else {
				req.setOutboundChannel(false);
				req.setInboundChannel(true);
			}
		}
		req.setOperationHeader(OperationHeaderUtil.buildOperationHeader(request.getOperationHeader().getSalesTransactionId(), shoppingCartVO));
		req.setOrderProductList(transformToPrevImplOrderProductList(shoppingCartVO.getCartItemList()));
		req.setStartDate(request.getFromDate());
		if(contextVO != null) {
			req.setServiceAddress(transformToPrevImpl(contextVO.getServiceAddressResponseVO()));
			req.setSubscriberProductList(transformToPrevImpl(shoppingCartVO, contextVO.getAssignedAndPendingProductResponseVO(), contextVO.getServiceAddressResponseVO()));
		}
		return req;
	}

	private static List<WirelineProductSummary> transformToPrevImpl(ShoppingCartVO shoppingCartVO, GetAssignedAndPendingProductResponseVO assignedAndPendingProductResponseVO, ServiceAddressResponseVO serviceAddress) {
		ArrayList<WirelineProductSummary> list = new ArrayList<WirelineProductSummary>();
		if(assignedAndPendingProductResponseVO == null) {
			return list; //Empty list
		}
		
		//filter by account
		ServiceAddressVO servAddr = new ServiceAddressVO();
		if(serviceAddress.getServiceAddress() != null) {
			servAddr.setServiceAddressId(serviceAddress.getServiceAddress().getAddressId());
		}
		
		ArrayList<SubscribedServiceIdentifierVO> subscribedServiceList = new ArrayList<SubscribedServiceIdentifierVO>();
		if(shoppingCartVO.getCartItemList() != null) {
			for(CartItemVO item: shoppingCartVO.getCartItemList()) {
				if(item.isSalesOrderItem() && item.getExistingServiceIdentifier() != null) {
					for(SubscribedServiceVO serv: item.getExistingServiceIdentifier()) {
						SubscribedServiceIdentifierVO servIdent = new SubscribedServiceIdentifierVO();
						servIdent.setServiceId(serv.getServiceId());
						servIdent.setServiceReferenceId(serv.getServiceReferenceId());
					}
				}
			}
		}
		List<SubscribedProductInfoRestVO> subscribedProds = assignedAndPendingProductResponseVO.getAssignedProductListByServiceAddressAndServiceId(servAddr, subscribedServiceList);
		
		if(subscribedProds != null) {
			for(SubscribedProductInfoRestVO prod: subscribedProds) {
				WirelineProductSummary prodSum = transformToPrevImpl(prod);
				list.add(prodSum);
			}
		}
		
		List<SubscribedProductInfoRestVO> pendingProds = assignedAndPendingProductResponseVO.getPendingProductListByServiceAddress(servAddr, subscribedServiceList, serviceAddress);
		if(pendingProds != null) {
			for(SubscribedProductInfoRestVO prod: assignedAndPendingProductResponseVO.getPendingProductList()) {
				WirelineProductSummary prodSum = transformToPrevImpl(prod);
				list.add(prodSum);
			}
		}
		return list;
	}

	private static WirelineProductSummary transformToPrevImpl(SubscribedProductInfoRestVO prod) {
		if(prod == null) {
			return null;
		}
		WirelineProductSummary prodSum = new WirelineProductSummary();
		prodSum.setServiceType(prod.getProductTypeCd());
		
		if(prod.getProductInstance() != null && prod.getProductInstance().size() > 0) {
			WirelineProductSummary.ProductInstance prodInst = transformToPrevImpl(prod.getProductInstance().get(0));
			prodSum.setProductInstance(prodInst);
		}
		return prodSum;
	}

	private static ProductInstance transformToPrevImpl(ProductInstanceInfoRestVO productInstanceInfoRestVO) {
		if(productInstanceInfoRestVO == null) {
			return null;
		}
		ProductInstance prodInst = new ProductInstance();
		prodInst.setServiceId(productInstanceInfoRestVO.getServiceId());
		prodInst.setProductCatalogId(productInstanceInfoRestVO.getProductCatalogId());
		return prodInst;
	}

	private static List<WirelineProductSummary> transformToPrevImplOrderProductList(List<CartItemVO> cartItems) {
		if(cartItems == null) {
			return null;
		}
		ArrayList<WirelineProductSummary> list = new ArrayList<WirelineProductSummary>();
		for(CartItemVO cartItem: cartItems) {
			//if(cartItem instanceof SalesOrderCartItemVO && cartItem.getProducts() != null) {
			if(cartItem.getProducts() != null) {

				List<WirelineProductSummary> prodSums = transformToPrevImpl(cartItem.getProducts(), cartItem.getAction());
					list.addAll(prodSums);

			}
		}
		return list;
	}

	private static List<WirelineProductSummary> transformToPrevImpl(ProductTypeVO prod, String action) {
		if(prod == null) {
			return null;
		}
		ArrayList<WirelineProductSummary> list = new ArrayList<WirelineProductSummary>();
		String actionType = null;
		if(EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(action)) {
			actionType = GetAvailableInstalLDateConstants.PROVIDE;
		} else if(EnterpriseWLNSalesServicesConstants.CART_ACTION_MODIFY.equalsIgnoreCase(action)) {
			actionType = GetAvailableInstalLDateConstants.CHANGE;
		}
		
		if(prod.getHomePhoneProduct() != null) {
			if(prod.getHomePhoneProduct().getProductComponents() != null && prod.getHomePhoneProduct().getProductComponents().size() > 0) {
				list.add(transformToPrevImpl(actionType, Constants.OMS_PRODUCT_SING, prod.getHomePhoneProduct().getProductComponents().get(0)));
			}
		}
		if(prod.getInternetProduct() != null) {
			if(prod.getInternetProduct().getProductComponents() != null && prod.getInternetProduct().getProductComponents().size() > 0) {
				list.add(transformToPrevImpl(actionType, Constants.OMS_PRODUCT_HSIC, prod.getInternetProduct().getProductComponents().get(0)));
			}
		}
		if(prod.getMobilityProductVO() != null) {
			//Not implemented yet
		}
		if(prod.getTelevisionProduct() != null) {
			if(prod.getTelevisionProduct().getProductComponents() != null && prod.getTelevisionProduct().getProductComponents().size() > 0) {
				list.add(transformToPrevImpl(actionType, Constants.OMS_PRODUCT_TTV, prod.getTelevisionProduct().getProductComponents().get(0)));
			}
		}
		
		return list;
	}
	
	private static WirelineProductSummary transformToPrevImpl(String actionType, String serviceType, ProductComponentVO prodComp) {
		WirelineProductSummary prodSum = new WirelineProductSummary();
		prodSum.setActionTypeCd(actionType);
		prodSum.setServiceType(serviceType);
		prodSum.setProductType("TBD-temp, productType");//TODO Just to pass validation. Used in GetAvailableInstallDateCoreOperation.validateCoreInput BUT it does not look that it is used anywhere
		
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(prodComp.getProductCatalogueId());
		String prodTier = null;
		if(catalogueItemDO!=null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())){
			prodTier = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
		}
		prodSum.setServicePlanCd(prodTier);
		return prodSum;
	}

	private static ServiceAddress transformToPrevImpl(ServiceAddressResponseVO servAddrRespVO) {
		if(servAddrRespVO == null) {
			return null;
		}
		ServiceAddress addr = new ServiceAddress();
//		addr.setAddressId(serviceAddress.getServiceAddressId());
//		addr.setMunicipalityName(serviceAddress.getCityCode());
//		addr.setProvinceStateCode(serviceAddress.getProvinceCode());
		if(servAddrRespVO != null && servAddrRespVO.getServiceAddress() != null) {
			addr.setClliCd(servAddrRespVO.getServiceAddress().getCLLICode());
			addr.setCOID(servAddrRespVO.getServiceAddress().getCOID());
			addr.setCountryCode(servAddrRespVO.getServiceAddress().getCountryCode());
			addr.setAddressId(servAddrRespVO.getServiceAddress().getAddressId());
			addr.setMunicipalityName(servAddrRespVO.getServiceAddress().getMunicipalityName());
			addr.setPostalZipCode(servAddrRespVO.getServiceAddress().getPostalZipCode());
			addr.setProvinceStateCode(servAddrRespVO.getServiceAddress().getProvinceStateCode());
			addr.setStreetName(servAddrRespVO.getServiceAddress().getStreetName());
			addr.setStreetNumber(servAddrRespVO.getServiceAddress().getStreetNumber());
			addr.setUnitName(servAddrRespVO.getServiceAddress().getUnitNumber());
		}
		return addr;
	}

	public static GetAvailableInstallDetailCoreResponse transfromFromPrevImpl(GetInstallationDetailCoreResponse res) {
		if(res == null) {
			return null;
		}
		GetAvailableInstallDetailCoreResponse response = new GetAvailableInstallDetailCoreResponse();
		if(res.getMessageList() != null && !res.getMessageList().isEmpty()) {
			response.setHasError(true);
			response.setMessageList(getMessageListFromPrevImpl(res.getMessageList()));
			return response;
		}
		response.setAvailableProductAppointments(transformFromPrevImplAvailableProductAppointmentList(res.getAvailableInstallDates()));
		response.setProductServiceInstallRequirement(transformFromPrevImplProductServiceInstallRequirementList(res.getServiceInstallDetail()));
		
		if(res.getAvailableInstallDates() != null) {
			for(AvailableInstallDateVO vo: res.getAvailableInstallDates()) {
				if(vo.isInstallationRequiredInd()) {
					response.setInstallationRequiredIndicator(true);
					break;
				}
			}
		}
		
		return response;
	}

	private static List<MessageVO> getMessageListFromPrevImpl(List<MessageList> messageList) {
		List<MessageVO> msgVOList = new ArrayList<MessageVO>();
		if(messageList!=null && !messageList.isEmpty()){
			for(MessageList msgList : messageList){
				MessageVO msgVO = new MessageVO();
				msgVO.setErrCode(msgList.getErrorCode());
				msgVO.setMessages(getMsgListFromPrevImpl(msgList.getMessageList()));
				msgVO.setContextDataAttributes(getRelatedMsgListFromPrevImpl(msgList.getRelatedMessageList()));
				msgVO.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
				msgVOList.add(msgVO);
			}
		}
		return msgVOList;
	}

	private static List<ContextDataAttributesVO> getRelatedMsgListFromPrevImpl(
			List<RelatedMessage> relatedMessageList) {
		List<ContextDataAttributesVO> contextDataAttributeList = new ArrayList<ContextDataAttributesVO>();
		if(relatedMessageList!=null && !relatedMessageList.isEmpty()){
			for(RelatedMessage relatedMsg : relatedMessageList){
				ContextDataAttributesVO ctxAttributesVO = new ContextDataAttributesVO();
				ctxAttributesVO.setAttributeTypeTxt(relatedMsg.getRelatedErrorTypeCd());
				ctxAttributesVO.setAttributeValueTxt(relatedMsg.getRelatedErrorMessageTxt());
				contextDataAttributeList.add(ctxAttributesVO);
			}
		}
		return contextDataAttributeList;
	}

	private static List<MessageDetailVO> getMsgListFromPrevImpl(List<Message> messageList) {
		List<MessageDetailVO> msgDetailVOList = new ArrayList<MessageDetailVO>();
		if(messageList!=null && !messageList.isEmpty()){
			for(Message msg : messageList){
				MessageDetailVO msgDetailVO = new MessageDetailVO();
				msgDetailVO.setMessagetTxt(msg.getMessage());
				msgDetailVO.setLocaleCd(msg.getLocale());
				msgDetailVOList.add(msgDetailVO);
			}
		}
		return msgDetailVOList;
	}

	private static List<ProductServiceInstallRequirementVO> transformFromPrevImplProductServiceInstallRequirementList(List<ServiceInstallDetailVO> serviceInstallDetail) {
		if(serviceInstallDetail == null) {
			return null;
		}
		ArrayList<ProductServiceInstallRequirementVO> list = new ArrayList<ProductServiceInstallRequirementVO>();
		for(ServiceInstallDetailVO detail: serviceInstallDetail) {
			ProductServiceInstallRequirementVO req = transfromFromPrevImpl(detail);
			list.add(req);
		}
		return list;
	}

	private static ProductServiceInstallRequirementVO transfromFromPrevImpl(ServiceInstallDetailVO detail) {
		if(detail == null) {
			return null;
		}
		ProductServiceInstallRequirementVO requirement = new ProductServiceInstallRequirementVO();
		requirement.setEstimatedAppointmentHours(detail.getEstimatedAppointmentHours());
		requirement.setInstallTypeCd(detail.getRecommendedInstallTypeCd());
		requirement.setProductServiceTypeCd(detail.getServiceTypeCd());
		return requirement;
	}

	private static List<AvailableProductAppointmentVO> transformFromPrevImplAvailableProductAppointmentList(List<AvailableInstallDateVO> availableInstallDates) {
		if(availableInstallDates == null) {
			return null;
		}
		ArrayList<AvailableProductAppointmentVO> list = new ArrayList<AvailableProductAppointmentVO>();
		for(AvailableInstallDateVO availDate: availableInstallDates) {
			AvailableProductAppointmentVO availableProductAppointment = transformFromPrevImpl(availDate);
			list.add(availableProductAppointment);
		}
		return list;
	}

	private static AvailableProductAppointmentVO transformFromPrevImpl(AvailableInstallDateVO availDate) {
		if(availDate == null) {
			return null;
		}
		AvailableProductAppointmentVO appointmentVO = new AvailableProductAppointmentVO();
		appointmentVO.setAvailableAppointments(transformFromPrevImpAvailableAppointments(availDate.getAvailableDateTimePeriodList()));
		appointmentVO.setProductServiceTypeCdList(availDate.getServiceTypeList());
		return appointmentVO;
	}

	private static List<AvailableAppointmentVO> transformFromPrevImpAvailableAppointments(List<AvailableDateTimePeriodVO> availableDateTimePeriodList) {
		if(availableDateTimePeriodList == null) {
			return null;
		}
		ArrayList<AvailableAppointmentVO> list = new ArrayList<AvailableAppointmentVO>();
		for(AvailableDateTimePeriodVO period: availableDateTimePeriodList) {
			AvailableAppointmentVO appointmentVO = transformFromPrevImp(period);
			list.add(appointmentVO);
		}
		return list;
	}

	private static AvailableAppointmentVO transformFromPrevImp(AvailableDateTimePeriodVO period) {
		if(period == null) {
			return null;
		}
		AvailableAppointmentVO appointmentVO = new AvailableAppointmentVO();
		appointmentVO.setAppointmentDate(period.getFormattedAvailableDate());
		appointmentVO.setAvailableTimeSlots(transformFromPrevImplAvailableTimeSlots(period.getAvailableTimePeriodList()));
		appointmentVO.setTimeZoneCd(period.getTimeZone());
		return appointmentVO;
	}

	private static List<AppointmentTimeSlotVO> transformFromPrevImplAvailableTimeSlots(List<AvailableTimePeriodVO> availableTimePeriodList) {
		if(availableTimePeriodList == null) {
			return null;
		}
		ArrayList<AppointmentTimeSlotVO> list = new ArrayList<AppointmentTimeSlotVO>();
		for(AvailableTimePeriodVO period: availableTimePeriodList) {
			AppointmentTimeSlotVO slot = transformFromPrevImpl(period);
			list.add(slot);
		}
		return list;
	}

	private static AppointmentTimeSlotVO transformFromPrevImpl(AvailableTimePeriodVO period) {
		if(period == null) {
			return null;
		}
		AppointmentTimeSlotVO slot = new AppointmentTimeSlotVO();
		slot.setEndTime(period.getEndTime());
		slot.setStartTime(period.getStartTime());
		return slot;
	}

	public static List<MessageVO> validateFeasibilitySvcResponse(
			CheckProductFeasibilityAdapterResponse feasibilityResponse) {
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		
		return messageVOList;
	}


	/**
	 * 
	 * This method will generate a list of requests for BookingService.availableTimeSlots
	 * 
	 * Considerations: 
	 * 
	 * 1) No SW products
	 * 2) no infeasible products
	 * 
	 * @param req
	 * @param checkProductFeasibilityAdapterResponse
	 * @param referencePDSAdapterResponse
	 * @param ctxVO
	 * @return
	 */
	public static List<GetAvailableTimeSlotsAdapterRequest> transformToAvailableTimeSlotsRequest(
			GetInstallationDetailCoreRequest req,
			CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse,
			GetReferencePDSDataServiceAdapterResponse referencePDSAdapterResponse, ShoppingCartContextVO ctxVO,CheckProductFeasibilityAdapterRequest feasibilityRequest) {
		List<GetAvailableTimeSlotsAdapterRequest> timeSlotsRequestList = new ArrayList<GetAvailableTimeSlotsAdapterRequest>();
		String functionName="transformToAvailableTimeSlotsRequest";
		GetAvailableInstallDateCoreTransformer availableInstallDateCoreTransformer =new GetAvailableInstallDateCoreTransformer();
		
		//get LAG_TIME_DAYS from RefPDS response.
		Map<String,String> nguiInstallLagTime = null;
		if(referencePDSAdapterResponse!=null && !referencePDSAdapterResponse.getReferencePDSTableMap().isEmpty()){
			nguiInstallLagTime = referencePDSAdapterResponse.getReferencePDSTableMap();
		}
		
		//Filter any SW or infeasible product.
		List<String> filteredProducts = getFilteredProducts(checkProductFeasibilityAdapterResponse);		
		
		//Generate a combination for products  with the remaining products.
		List<List<String>> serviceTypeCombinationLists = availableInstallDateCoreTransformer.generateCombination(req.getCombinationInd(), 
				filteredProducts);
		
		Map<String, String> serviceTypeInstallationTypeMap = availableInstallDateCoreTransformer.buildServiceTypeInstallationTypeMap(checkProductFeasibilityAdapterResponse);
		
		List<List<String>> finalCombinationList = availableInstallDateCoreTransformer.filterSerivceTypeCombination(serviceTypeCombinationLists, 
                serviceTypeInstallationTypeMap);
		
		List<SubscribedProductInfoRestVO> assignedProductList = EnterpriseWLNCommonTransformer.getAssignedProductsFromVO(ctxVO);
		
		boolean changeOfTechnology = EnterpriseWLNOrderUtil.getCotIndicator(ctxVO.getProductQualificationAdapterResponseVO());
		
		if(finalCombinationList!=null && !finalCombinationList.isEmpty()){
			for(List<String> serviceTypeList : finalCombinationList){
				if(!serviceTypeList.isEmpty()){
					logger.info(functionName, "Bulding Request for combination : " + serviceTypeList);
					GetAvailableTimeSlotsAdapterRequest request = new GetAvailableTimeSlotsAdapterRequest();
					if(ctxVO.getServiceAddressResponseVO()!=null && ctxVO.getServiceAddressResponseVO().getServiceAddress()!=null){
						com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress serviceAddress = ctxVO.getServiceAddressResponseVO().getServiceAddress();
						request.setCity(serviceAddress.getMunicipalityName());
						request.setFmsAddressId(serviceAddress.getAddressId());
						request.setProvinceCd(serviceAddress.getProvinceStateCode());
					}
					
					request.setInstallationRequirementsList(EnterpriseWLNCommonTransformer.getInstallationReqListFromFeasibilityResponse(checkProductFeasibilityAdapterResponse, feasibilityRequest, serviceTypeList,assignedProductList,changeOfTechnology));
				
					/*
					 * get lag days to calculate bookingStartDate
					 */
					int lagDays = 0;
					try {
						lagDays = availableInstallDateCoreTransformer.getLagDays(serviceTypeList, req.isInboundChannel(), nguiInstallLagTime);
					} catch (LagTimeException e) {
						logger.error(e);
					}
					
					/**
					 * BookingService consider endDate is endDate at time 00:00:00.  Thus we need to add one day to coreRequest.getEndDate() 
					 * in order to fetch the availableInstallDate for the last day.
					 */
					req.setEndDate(availableInstallDateCoreTransformer.addDays(req.getEndDate(), 1));
					
					/*******************************************/
					/* remove time from date                   */
					/*******************************************/
					
					req.setStartDate(EnterpriseSalesServiceUtil.removeTime(req.getStartDate()));
					req.setEndDate(EnterpriseSalesServiceUtil.removeTime(req.getEndDate()));
					Date bookingStartDate=null;
					try {
						bookingStartDate = availableInstallDateCoreTransformer.calculateBookingStartDate(req.getStartDate(), lagDays);
					} catch (BookingDateCalculationException e) {
						logger.error(e);
					}
					
					/*
					 * Split the date into 7 days chunks
					 */
					
					/**
					 * if startDate is after endDate, skip. 
					 */
					if (! bookingStartDate.after(req.getEndDate())){
						ArrayList[] chunkDates = availableInstallDateCoreTransformer.getChunks(bookingStartDate, req.getEndDate());
						
						int chunkSize = chunkDates[0].size(); 
						for (int i = 0; i < chunkSize; i++){
							String fromDate = (String) chunkDates[0].get(i);
							String toDate = (String) chunkDates[1].get(i);
							
							fromDate = availableInstallDateCoreTransformer.adjustStartingDate(fromDate, toDate);
							
							// Clone a new object and use the chunked start/end date
							GetAvailableTimeSlotsAdapterRequest chunkedRequest = request.cloneObject();
								 
							chunkedRequest.setFromDate(fromDate);
							chunkedRequest.setToDate(toDate);
							timeSlotsRequestList.add(chunkedRequest);

						}
					}	
				}
			}
		}
		
		return timeSlotsRequestList;
	}

	private static List<String> getFilteredProducts(
			CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse) {
		List<String> filteredServiceTypes = new ArrayList<String>();
		/*
		 * 1. create a map that will hold the products and its respective installationType
		 */
		GetAvailableInstallDateCoreTransformer availableInstallDateCoreTransformer = new GetAvailableInstallDateCoreTransformer();
		Map<String, String> serviceTypeInstallationTypeMap = availableInstallDateCoreTransformer.buildServiceTypeInstallationTypeMap(checkProductFeasibilityAdapterResponse);
		
		/*
		 * 2. Generate a map with the infeasible products 
		 */
		Map<String, Boolean> infeasibleProductsMap = availableInstallDateCoreTransformer.buildInfeasibleServiceTypeMap(checkProductFeasibilityAdapterResponse);
		
		/*
		 * 3. Build map with FesibilityInfo per serviceType  
		 */
		 
		List<ProductFeasibilityInfo> productFeasiblityInfoList           = checkProductFeasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo();
		Map<String, ServiceTypeFeasibilityVO> serviceTypeFeasbilitiesMap = availableInstallDateCoreTransformer.buildServiceTypeFeasibilityMap(productFeasiblityInfoList);
		
		
		// Step 4 - perform filtering of service types
				//          1 - filter out any service type installationType is SW
				//          2 - filter out any service type has feasibilityInd = false
		filteredServiceTypes = availableInstallDateCoreTransformer.filterServiceTypes(serviceTypeInstallationTypeMap, 
						                                                    infeasibleProductsMap, 
				                                                    serviceTypeFeasbilitiesMap);
		return filteredServiceTypes;
	}

	public static List<MessageVO> validateBookingServiceResponse(
			List<GetAvailableTimeSlotsTask> getAvailableTimeSlotsTaskResult) {
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		if( getAvailableTimeSlotsTaskResult!=null && !getAvailableTimeSlotsTaskResult.isEmpty()) {
		  for(GetAvailableTimeSlotsTask timeSlotsTask: getAvailableTimeSlotsTaskResult ) {
			  if (timeSlotsTask!=null) {
				  if(timeSlotsTask.getRuntimeException()!=null) {
					  HttpStatusCodeException downstreamException = (HttpStatusCodeException) timeSlotsTask.getRuntimeException();
					  if(downstreamException.getStatusCode().toString().equals("404")){
							messageVOList = EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_BOOK_APPOINTMENT_0004","BookingService is down.");
                            return messageVOList;
						}
					  }
				  if(timeSlotsTask.getResult().getMessageList()!=null && !timeSlotsTask.getResult().getMessageList().isEmpty()) {
					  		messageVOList = EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_BOOK_APPOINTMENT_0004",timeSlotsTask.getResult().getMessageList());
                            return messageVOList;						
					  }
			  }
		  }
		}
		
		return messageVOList;
	}

	public static BookingServiceVO transformToBookingVO(GetAvailableInstallDetailCoreRequest request,
			CheckProductFeasibilityAdapterRequest feasibilitySvcRequest,
			CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse,
			ShoppingCartContextVO ctxVO, Map<String, String> nguiInstallLagTime) {
		BookingServiceVO bookingVO = new BookingServiceVO();
		bookingVO.setStartDate(request.getFromDate());
		bookingVO.setEndDate(request.getToDate());
		bookingVO.setFeasibilityAdapterResponse(checkProductFeasibilityAdapterResponse);
		bookingVO.setFeasibilityRequest(feasibilitySvcRequest);
		bookingVO.setNguiInstallLagTime(nguiInstallLagTime);
		bookingVO.setCombinationInd(request.getCombinationRequiredIndicator());
		bookingVO.setServiceAddressResponseVO(ctxVO.getServiceAddressResponseVO());
		ShoppingProfileVO shoppingProfile = ctxVO.getShoppingCartVO().getShoppingProfile();
		if(shoppingProfile != null && shoppingProfile.getAgentProfile() != null && shoppingProfile.getAgentProfile().getUserPrivilegeRoleCodes() !=null) {
			if(shoppingProfile.getAgentProfile().getUserPrivilegeRoleCodes().contains(Constants.D2C_PARTNER)) {
				bookingVO.setOutboundChannel(true);
				bookingVO.setInboundChannel(false);
			} else {
				bookingVO.setOutboundChannel(false);
				bookingVO.setInboundChannel(true);
			}
		}
		
		//qc76909 appointment time didn't use the disconnect lag time because the cart don't have disconnect item
		//disconnect item will be added to shopping cart only after disconnect page filled in HS3
		//before that, we need to use the product's winback indicator
		List<CartItemVO> cartItemList = ctxVO.getShoppingCartVO().getCartItemList();
		if ( (cartItemList != null) && (!cartItemList.isEmpty()) ) {
			for (CartItemVO cartItem : cartItemList) {
				if ( (cartItem != null) && (cartItem.isDisconnectOrderItem() || EnterpriseWLNCommonTransformer.isCartItemWinback(cartItem)) ) {
					bookingVO.setDisconnectCartItem(true);
					break;
				}
			}
		}
		
		List<SubscribedProductInfoRestVO> assignedProductList = EnterpriseWLNCommonTransformer.getAssignedProductsFromVO(ctxVO);
		
		boolean changeOfTechnologyInd = EnterpriseWLNOrderUtil.getCotIndicator(ctxVO.getProductQualificationAdapterResponseVO());
		
		bookingVO.setChangeOfTechnology(changeOfTechnologyInd);
		
		bookingVO.setExistingProductList(assignedProductList);

		return bookingVO;
	}

	private static List<String> getProductList(List<Product> orderedProductFromVO) {
		List<String> products = new ArrayList<String>();
		if(orderedProductFromVO!=null && !orderedProductFromVO.isEmpty()){
			for(Product product : orderedProductFromVO){
				products.add(product.getProductTypeCd());
			}
		}
		return products;
	}

	public static List<ProductServiceInstallRequirementVO> getProductServiceInstallRequirement(
			CheckProductFeasibilityAdapterRequest feasibilitySvcRequest,
			CheckProductFeasibilityAdapterResponse feasibilitySvcResponse) {
		List<ProductServiceInstallRequirementVO> productServiceInstallRequirementList = new ArrayList<ProductServiceInstallRequirementVO>();
		
		Map<String, ServiceTypeFeasibilityVO> productsWithFeasibilityInfoMap = EnterpriseWLNCommonTransformer.productWithFeasibilityInfo(feasibilitySvcResponse);
		
		Set<String> productList = productsWithFeasibilityInfoMap.keySet();
		
		if(productList!=null && !productList.isEmpty()){
			for(String product : productList){
				ServiceTypeFeasibilityVO productFeasibilityVO = productsWithFeasibilityInfoMap.get(product);
				ProductServiceInstallRequirementVO productServiceInstallRequirementVO = new ProductServiceInstallRequirementVO();
				productServiceInstallRequirementVO.setEstimatedAppointmentHours(productFeasibilityVO.getWorkTimeDefault());
				productServiceInstallRequirementVO.setInstallTypeCd(productFeasibilityVO.getRecommendedInstallType());
				productServiceInstallRequirementVO.setProductServiceTypeCd(product);
				productServiceInstallRequirementList.add(productServiceInstallRequirementVO);
			}
		}
		
		return productServiceInstallRequirementList;
	}

	public static GetAvailableInstallDetailCoreResponse transformToCoreResponse(
			List<GetAvailableTimeSlotsTask> getAvailableTimeSlotsTaskResult, BookingServiceVO bookingVO,
			CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse,ShoppingCartContextVO shoppingCartCtxVO, CheckProductFeasibilityAdapterRequest feasibilitySvcRequest) {
		
		ShoppingCartVO shoppingCartVO = shoppingCartCtxVO.getShoppingCartVO();
		
		GetAvailableInstallDetailCoreResponse coreResponse = new GetAvailableInstallDetailCoreResponse();
		
		/**
		 * getAvailableTimeSlotsTaskResult - to get the input and result from bookingService
		 * BookingServiceVO - to get startDate, endDate, combinationInd
		 */
		
		Date requestEndDate = bookingVO.getEndDate();
		
		/**
		 * Merge the results from BookingService tasks and populate the 
		 */
		
		Map<List<String>,AvailableProductAppointmentVO> availableDateVOMap = new HashMap<List<String>, AvailableProductAppointmentVO>();
		
		if(getAvailableTimeSlotsTaskResult!=null && !getAvailableTimeSlotsTaskResult.isEmpty()){
			for(GetAvailableTimeSlotsTask task : getAvailableTimeSlotsTaskResult){
				
				GetAvailableTimeSlotsAdapterRequest input = task.getInput();
				GetAvailableTimeSlotsAdapterResponse result = task.getResult();
				
				/**
				 * get the serviceTypes from input to bookingService for the response
				 */
				List<String> serviceTypeList = new ArrayList<String>();
				if(input.getInstallationRequirementsList()!=null && !input.getInstallationRequirementsList().isEmpty()){
					for(InstallationRequirementsList requirement : input.getInstallationRequirementsList()){
						serviceTypeList.add(requirement.getProductServiceType());
					}
					
					Collections.sort(serviceTypeList);
					
					/**
					 * find a matching AvailableProducAppointmentVO from map, if not found create new object
					 */
					
					AvailableProductAppointmentVO availableProductAppVO = availableDateVOMap.get(serviceTypeList);
					if(availableProductAppVO == null){
						availableProductAppVO = new AvailableProductAppointmentVO();
						availableProductAppVO.setProductServiceTypeCdList(serviceTypeList);
						availableDateVOMap.put(serviceTypeList, availableProductAppVO);
					}
					
					/**
					 * appointment date and time period
					 */
					if(result!=null){
						if(!result.hasError()){
							if(result.getAppointmentTimeList()!=null && result.getAppointmentTimeList().getAppointmentTimeList()!=null && !result.getAppointmentTimeList().getAppointmentTimeList().isEmpty()){
								for(AppointmentTimeDetail timeDetail : result.getAppointmentTimeList().getAppointmentTimeList()){
									//get the available appointment date from response and set to AvailableProductAppointmentVO
									AvailableAppointmentVO availableAppointmentVO = new AvailableAppointmentVO();
									if(isValidDate(timeDetail.getAppointmentDate())){
										availableAppointmentVO.setAppointmentDate(EnterpriseWLNCommonTransformer.parseStringToDate(timeDetail.getAppointmentDate(), DATE_FORMAT));
										List<AppointmentTimeSlotVO> appointmentTimeSlotList = new ArrayList<AppointmentTimeSlotVO>();
										if(timeDetail.getAvailableAppointmentPeriods()!=null && !timeDetail.getAvailableAppointmentPeriods().isEmpty()){
											for(AvailableAppointmentPeriods availableAppointment : timeDetail.getAvailableAppointmentPeriods()){
												AppointmentTimeSlotVO appointmentTimeSlot = new AppointmentTimeSlotVO();
												appointmentTimeSlot.setStartTime(availableAppointment.getStartAppointmentTime());
												appointmentTimeSlot.setEndTime(availableAppointment.getEndAppointmentTime());
												appointmentTimeSlotList.add(appointmentTimeSlot);
											}
											availableAppointmentVO.setAvailableTimeSlots(appointmentTimeSlotList);
											availableAppointmentVO.setAvailableInstallCreditList(buildavailableInstallCreditList(availableAppointmentVO.getAppointmentDate(), shoppingCartCtxVO));
										}
										if(!StringUtils.isBlank(timeDetail.getTimeZone())){
											availableAppointmentVO.setTimeZoneCd(timeDetail.getTimeZone());
										}
										
										//filter out any date beyond endDate
										if(availableAppointmentVO.getAppointmentDate().before(requestEndDate)){
											availableProductAppVO.getAvailableAppointments().add(availableAppointmentVO);
											
										}
										
										
									}else{
											logger.error("Appointment date is not validate from BookingService -> " + timeDetail.getAppointmentDate());
										}	
									}
								}else{
									logger.error("No dates returned for the combination " + availableProductAppVO.getProductServiceTypeCdList().toString());
								}
							}else{
								//has error
								logger.error("Error has been returned from BookingSvc messageList " + result.getMessageList().toString());
							}
					}
					
				}
			}
			
			 
			for(AvailableProductAppointmentVO vo : availableDateVOMap.values()){
				coreResponse.addAvailableProductAppointmentVO(vo);
			}
			
			/**
			 * Build additional info necessary to build the response for ESS
			 */
			
			/**
			 * generate a complete list of combination ordered products
			 */
			List<String> orderedProducts = getProductList(EnterpriseWLNCommonTransformer.getOrderedProductFromVO(shoppingCartVO,shoppingCartCtxVO));
			
			/**
			 * enrich the orderedProducts: this will happen in the scenario of a customer upgrading a existing product, then the orderedProduct must have the existing product plus the new ordered products.
			 */
			List<String> existingProductsToUpgradeList = EnterpriseWLNCommonTransformer.getExistingProductsToUpgrade(shoppingCartCtxVO);
			
			if(!CollectionUtils.isEmpty(existingProductsToUpgradeList)){
				for(String existingProduct : existingProductsToUpgradeList){
					if(!orderedProducts.contains(existingProduct)){
						orderedProducts.add(existingProduct);
					}
				}
			}

			//checking if the COT is true
			boolean cotIndicator = EnterpriseWLNOrderUtil.getCotIndicator(shoppingCartCtxVO.getProductQualificationAdapterResponseVO());
			
			if(cotIndicator){
				logger.debug("transformToCoreResponse", "Change of technology scenario: enriching the ordered products with the products from the FeasibilitySvc response");
				enrichOrderedProducts(orderedProducts,checkProductFeasibilityAdapterResponse);
			}
			
			
			GetAvailableInstallDateCoreTransformer availableInstallDateCoreTransformer =new GetAvailableInstallDateCoreTransformer();
			
			List<List<String>> serviceTypeCombinationLists = availableInstallDateCoreTransformer.generateCombination(bookingVO.isCombinationInd(), orderedProducts);
			
			/**
			 * build the infeasibleProductsMap for transformer to determine the installationRequiredInd.
			 */
			Map<String,Boolean> infeasibleProductsMap = availableInstallDateCoreTransformer.buildInfeasibleServiceTypeMap(checkProductFeasibilityAdapterResponse);
			coreResponse.setInfeasibleProductsMap(infeasibleProductsMap);
			
			/**
			 * Build a List of serviceTypes that are SW
			 */
			List<String> serviceTypeSW = new ArrayList<String>();
			for(String serviceType : checkProductFeasibilityAdapterResponse.getInstallTypeSW()){
				serviceTypeSW.add(serviceType);
			}
			
			coreResponse.setServiceTypeSWList(serviceTypeSW);
			coreResponse.setOrderedProductCombination(serviceTypeCombinationLists);
			
			coreResponse.setProductServiceInstallRequirement(GetAvailableInstallDetailCoreTransformer.getProductServiceInstallRequirement(feasibilitySvcRequest, checkProductFeasibilityAdapterResponse));
		}
		
		
		return coreResponse;
	}
	
	private static void enrichOrderedProducts(List<String> orderedProducts,CheckProductFeasibilityAdapterResponse feasibilitySvcResponse) {
		//Getting the Products returned by FeasibilitySvc
		List<ProductFeasibilityInfo> productFeasiblityInfoList = feasibilitySvcResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo();
		List<String> feasibilityProductList = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(productFeasiblityInfoList)){
			for(ProductFeasibilityInfo productFeasibility : productFeasiblityInfoList){
				if(productFeasibility.getProductSpecification()!=null && StringUtils.isNotBlank(productFeasibility.getProductSpecification().getName())){
					feasibilityProductList.add(productFeasibility.getProductSpecification().getName());
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(feasibilityProductList)){
			logger.debug("enrichOrderedProducts", "COT products from Feasibility Response: " + feasibilityProductList.toString());
			for(String productToChange : feasibilityProductList){
				if(!orderedProducts.contains(productToChange)){
					orderedProducts.add(productToChange);
				}
			}
		}
		
	}

	private static List<AvailableInstallCreditTypeVO> buildavailableInstallCreditList(Date appointmentDate,
			ShoppingCartContextVO shoppingCartCtxVO) {
		List<AvailableInstallCreditTypeVO> creditsList = new ArrayList<AvailableInstallCreditTypeVO>();
		if (shoppingCartCtxVO.isEligibleForInstallCredit(appointmentDate)) {
			SweetenerOfferSummary sweetener = shoppingCartCtxVO.getInstallionCreditSweetener();
			if (sweetener != null) {
				for (WirelineOfferProductSummary offerProductSummary : sweetener.getOfferProductSummary()
						.getWirelineOfferProductSummaryList()) {
					AvailableInstallCreditTypeVO creditItem = buildAvailableInstallCredit(sweetener,
							offerProductSummary);
					creditsList.add(creditItem);
				}

			}

		}
		return creditsList;
	}

	private static AvailableInstallCreditTypeVO buildAvailableInstallCredit(SweetenerOfferSummary sweetener,
			WirelineOfferProductSummary offerProductSummary) {
		AvailableInstallCreditTypeVO creditItem = new AvailableInstallCreditTypeVO();
		creditItem.setProductType(offerProductSummary.getProductTypeCode());
		creditItem.setSweetener(transform(sweetener, offerProductSummary.getDiscountList()));
		return creditItem;
	}

	private static FFHSweetenerTypeVO transform(SweetenerOfferSummary sweetener, List<Discount> discountList) {
		FFHSweetenerTypeVO sweetenerVO = new FFHSweetenerTypeVO();
		sweetenerVO.setOfferHeader(transform(sweetener));
		sweetenerVO.setDiscounts(tramsform(discountList));
		
		return sweetenerVO;
	}

	private static List<FFHDiscountTypeVO> tramsform(List<Discount> discountList) {
		List<FFHDiscountTypeVO> result = new ArrayList<FFHDiscountTypeVO>();
		if(discountList != null) {
			for(Discount discountItem :  discountList) {
				FFHDiscountTypeVO discountVO = new FFHDiscountTypeVO();
				discountVO.setDiscountCode(discountItem.getDiscountCode());
				discountVO.setProductCatalogueIdentifiers(transform(discountItem.getDiscountProductCatalogueItemList()));
				result.add(discountVO);
			}
		}
		
		return result;
	}

	private static List<ProductComponentVO> transform(
			List<DiscountProductCatalogueItem> discountProductCatalogueItemList) {
		List<ProductComponentVO> result = new ArrayList<ProductComponentVO>();
		for(DiscountProductCatalogueItem catItem : discountProductCatalogueItemList) {
			ProductComponentVO catVo = new ProductComponentVO();
			catVo.setProductCatalogueId(catItem.getProductCatalogueIdentifier().getProductCatalogueId());
			catVo.setParentProductCatalogueId(catItem.getParentProductCatalogueIdentifier().getProductCatalogueId());
			result.add(catVo);
		}
		return result;
	}

	private static FFHOfferHeaderVO transform(SweetenerOfferSummary sweetener) {
		FFHOfferHeaderVO offerHeader = new FFHOfferHeaderVO();
		offerHeader.setOfferCode(sweetener.getWirelessOfferCode());
		offerHeader.setOfferId(String.valueOf(sweetener.getOfferId()));
		offerHeader.setOfferProgramId(String.valueOf(sweetener.getProgramId()));
		offerHeader.setSystemId(sweetener.getSourceSystemId());
		offerHeader.setPerspectiveDate(sweetener.getPerspectiveDate());
		return offerHeader;
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

	public static GetAvailableInstallDetailCoreResponse transformToCoreResponse(final FieldWorkAppointmentAdapterResponse input) {
		GetAvailableInstallDetailCoreResponse result = new GetAvailableInstallDetailCoreResponse();
		
		result.setWorkOrder(input.getWorkOrder());
		result.setAvailableAppointmentList(input.getAvailableAppointmentList());
		
		return result;
		
	}
}
