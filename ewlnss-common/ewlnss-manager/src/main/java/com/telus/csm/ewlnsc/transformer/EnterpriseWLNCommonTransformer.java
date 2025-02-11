package com.telus.csm.ewlnsc.transformer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.ess.common.domain.ContextDataAttributesVO;
import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ess.rest.domain.CartItemIdentifier;
import com.telus.csm.ess.rest.domain.ContextAttribute;
import com.telus.csm.ess.rest.domain.ShoppingCartErrorResponse;
import com.telus.csm.ess.rest.domain.ShoppingCartIdentifier;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ResponseStatus;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ValidationMessage;
import com.telus.csm.ewlnsc.adapter.wbk.domain.InstallationRequirementsList;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductOrderTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ServiceTypeFeasibilityVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferProduct;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.rest.domain.MessageType;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProvisioningInfo;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecification;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

public class EnterpriseWLNCommonTransformer {
	
	private static OperationHeader opHeader = new OperationHeader();
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(EnterpriseWLNCommonTransformer.class);
	
	public static final Map<String, String> actionTypeCdMap = new HashMap<String, String>();
	
	static {
		actionTypeCdMap.put(EnterpriseWLNSalesServicesConstants.MODIFY, EnterpriseWLNSalesServicesConstants.CH);
		actionTypeCdMap.put(EnterpriseWLNSalesServicesConstants.CREATE, EnterpriseWLNSalesServicesConstants.PR);

		
	}
	
	private static final String HSIC = EnterpriseWLNSalesServicesConstants.HSIC;
	private static final String TTV = EnterpriseWLNSalesServicesConstants.TTV;
	private static final String SING = EnterpriseWLNSalesServicesConstants.SING;
	private static final String CH = EnterpriseWLNSalesServicesConstants.CH;
	private static final String PR = EnterpriseWLNSalesServicesConstants.PR;
	private static final String OFFER_OMS_TEMPLATE_ID = "1356100"; 
	private static final String TVX = EnterpriseWLNSalesServicesConstants.PRODUCT_TIER_CD_TVX;
	private static final String PV = "PV";
	
	public static GetAssignedAndPendingProductResponseVO transformExceptionForAssignedAndPendingProducts(Exception runtimeException, String salesTransactionId) {
		GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO = new GetAssignedAndPendingProductResponseVO();
		GetConsolidatedAccountProfileAdapterResponse downstreamResponse = new GetConsolidatedAccountProfileAdapterResponse();
		
		opHeader.setSalesTransactionId(salesTransactionId);
		if(runtimeException!=null){
			HttpStatusCodeException downstreamException = (HttpStatusCodeException) runtimeException; //HttpStatusCodeException has the exception for 400 and 500 error scenarios
			downstreamResponse = JsonUtil.parseJsonToObject(downstreamException.getResponseBodyAsString(), GetConsolidatedAccountProfileAdapterResponse.class);
			if(downstreamResponse!=null && downstreamResponse.getResponseStatus()!=null){
				String errorMessage="Error happened while retrieving information from SalesCustomerInfoSvc.getConsolidatedAccount, check RelatedMessageList for detail.";
				assignedAndPendingResponseVO.setMessageList(GetAvailableOfferSummaryTransformer.generateMessageList(opHeader, EnterpriseWLNSalesServicesErrorCodes.GAOSL_DOWN_STREAM_ERROR, errorMessage, null));
				assignedAndPendingResponseVO.getMessageList().get(0).setRelatedMessageList(getRelatedMsg(downstreamResponse.getResponseStatus()));
			}
		}
		return assignedAndPendingResponseVO;
	}
	
	public static GetProductQualificationAdapterResponse transformExceptionForProductQualification(
			Exception runtimeException, String salesTransactionId) {
		GetProductQualificationAdapterResponse productQualificationResponse = new GetProductQualificationAdapterResponse();
		opHeader.setSalesTransactionId(salesTransactionId);
		if(runtimeException!=null){
			HttpStatusCodeException downstreamException = (HttpStatusCodeException) runtimeException; //HttpStatusCodeException has the exception for 400 and 500 error scenarios
			productQualificationResponse = JsonUtil.parseJsonToObject(downstreamException.getResponseBodyAsString(), GetProductQualificationAdapterResponse.class);	
		}
		return productQualificationResponse;
	}
	
	public static List<RelatedMessage> getRelatedMsg(ResponseStatus responseStatus) {
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
		if(responseStatus.getMessages()!=null && !responseStatus.getMessages().isEmpty()){
			List<ValidationMessage> messages = responseStatus.getMessages();
			for(ValidationMessage validationMessage: messages){
				RelatedMessage msg = new RelatedMessage();
				msg.setRelatedErrorMessageTxt(validationMessage.getMessageTxt());
				msg.setRelatedErrorCd(validationMessage.getMessageCd());
				msg.setRelatedErrorTypeCd(responseStatus.getStatusTxt());
				relatedMessageList.add(msg);
			}
		}else if(!StringUtils.isEmpty(responseStatus.getSystemMessageTxt()) && (responseStatus.getMessages()==null || responseStatus.getMessages().isEmpty())){
			RelatedMessage relatedMsg = new RelatedMessage();
			relatedMsg.setRelatedErrorCd(responseStatus.getSystemMessageCd());
			relatedMsg.setRelatedErrorMessageTxt(responseStatus.getSystemMessageTxt());
			relatedMsg.setRelatedErrorTypeCd(responseStatus.getStatusTxt());
			relatedMessageList.add(relatedMsg);
		}
		return relatedMessageList;
	}

	public static List<MessageVO> transformToMessageListVO(String errorCode, String errorMessage) {
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		MessageVO messageVO = new MessageVO();
		messageVO.setErrCode(errorCode);
		messageVO.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		messageVO.setMessages(getMessagesFromErrorMessage(errorMessage));
		messageVOList.add(messageVO);
		return messageVOList;
	}

	private static List<MessageDetailVO> getMessagesFromErrorMessage(String errorMessage) {
		List<MessageDetailVO> messageDetailVO = new ArrayList<MessageDetailVO>();
		MessageDetailVO msgDetail = new MessageDetailVO();
		msgDetail.setMessagetTxt(errorMessage);
		msgDetail.setLocaleCd(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
		messageDetailVO.add(msgDetail);
		return messageDetailVO;
	}

	public static List<MessageVO> transformToMessageListVO(String errorCode, List<Message> messageList) {
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		if(messageList!=null && !messageList.isEmpty()){
			for(Message msg : messageList){
				MessageVO msgVO = new MessageVO();
				msgVO.setErrCode(errorCode);
				msgVO.setMessages(getMessagesFromDownstream(msg));
				msgVO.setContextDataAttributes(getValidateMsgsFromDownstream(msg));
				messageVOList.add(msgVO);
			}
		}
		return messageVOList;
	}

	private static List<ContextDataAttributesVO> getValidateMsgsFromDownstream(Message msg) {
		List<ContextDataAttributesVO> contextDataAttributesVOList = new ArrayList<ContextDataAttributesVO>();
		if(msg.getValidationMessageList()!=null && !msg.getValidationMessageList().isEmpty()){
			for(com.telus.csm.ewlnss.adapter.domain.ValidationMessage validationMsg : msg.getValidationMessageList()){
				ContextDataAttributesVO contextDataAttributeVO = new ContextDataAttributesVO();
				contextDataAttributeVO.setAttributeTypeTxt(validationMsg.getMessageType());
				contextDataAttributeVO.setAttributeValueTxt(validationMsg.getMessage());
				contextDataAttributesVOList.add(contextDataAttributeVO);
			}
		}
		return contextDataAttributesVOList;
	}

	private static List<MessageDetailVO> getMessagesFromDownstream(Message msg) {
		List<MessageDetailVO> msgDetailVOList = new ArrayList<MessageDetailVO>();
		MessageDetailVO msgDetailVO = new MessageDetailVO();
		msgDetailVO.setLocaleCd(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
		msgDetailVO.setMessagetTxt(msg.getMessage());
		msgDetailVOList.add(msgDetailVO);
		return msgDetailVOList;
	}

	public static List<InstallationRequirementsList> getInstallationReqListForAppointment(CheckProductFeasibilityAdapterResponse response,CheckProductFeasibilityAdapterRequest feasibilityRequest,List<SubscribedProductInfoRestVO> existingProductList, boolean changeOfTechnologyInd){
		List<InstallationRequirementsList> installationRequirementList = new ArrayList<InstallationRequirementsList>();
		
		List<ProductFeasibilityInfo> productFeasiblityInfoList = response.getProductFeasibilityInfoList().getProductFeasibilityInfo();
		
		Map<String,String> productTypeActionMap = new HashMap<String,String>();
		
		for(ProductSpecification productSpecification : feasibilityRequest.getProductSpecificationList().getProductSpecification()){
			productTypeActionMap.put(productSpecification.getName(), getActionTypeFromProductSpec(productSpecification));
		}
		
		/* November 26th, 2018 - if Change Of technology scenario, productActionType should be as follows:
		 * 1.  Any product which the customer already has should be passed with order action "PV"
		   2.  Any product which the customer does not already have should be passed with order action "PR"
		   3.  Any product which is not existing and not being ordered should be omitted from the request.

		 */
		
		adjustProductTypeActionMap(productTypeActionMap,existingProductList,changeOfTechnologyInd);
		
		Set<String> productList = productTypeActionMap.keySet();
		
		if(productList!=null && !productList.isEmpty()){
			for(String product : productList){
				if(response!=null && response.getProductFeasibilityInfoList()!=null && productFeasiblityInfoList!=null && !productFeasiblityInfoList.isEmpty()){
					for(ProductFeasibilityInfo productFeasibilityInfo : productFeasiblityInfoList){
						if(product.equalsIgnoreCase(productFeasibilityInfo.getProductSpecification().getName())){
							InstallationRequirementsList installation = new InstallationRequirementsList();
							String productActionType;							
							if(HSIC.equalsIgnoreCase(product)){
								productActionType = getProductTypeForHsic(product,productTypeActionMap);
							}else{
								productActionType = productTypeActionMap.get(product);
							}							
							installation.setActionType(productActionType);
							installation.setProductServiceType(product);
							installation.setRequestedWorkTime(String.valueOf(productFeasibilityInfo.getProvisioningInformation().getWorkTimeDefault()));
							installation.setRequestedBucketType(productFeasibilityInfo.getProvisioningInformation().getDefaultBucket());
							installation.setRequestedInstallType(productFeasibilityInfo.getProvisioningInformation().getRecommendedInstallType());
							
							installationRequirementList.add(installation);
						}
					}
				}
			}
		}
		
		return installationRequirementList;
	}
	
	
	private static void adjustProductTypeActionMap(Map<String, String> productTypeActionMap, List<SubscribedProductInfoRestVO> existingProductList, boolean changeOfTechnologyInd) {
		String functionName = "adjustProductTypeActionMap";
		logger.enter(functionName);
		/**
		 *  1.  Any product which the customer already has should be passed with order action "PV"
		    2.  Any product which the customer does not already have should be passed with order action "PR"
		    3.  Any product which is not existing and not being ordered should be omitted from the request.
		 */
		Set<String> keySet = productTypeActionMap.keySet();
		for(String product : keySet){
			for(SubscribedProductInfoRestVO subscribedProduct : existingProductList){
				if(product.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					if(changeOfTechnologyInd){
						productTypeActionMap.put(product, PV);
						logger.info(functionName, "************** Change of technology scenario and product already existing, sending PV as productActionTypeCd to OP for product= " + product + " ****************");
					}
				}
			}
		}
		logger.exit(functionName);
	}

	/**
	 * 
	 * 1. when HSIC product is CHANGE and TTV product is PROVIDE then
		   --> pass actionType of "RG" instead of "CH" for HSIC 
		   --> pass actionType of "PR" for TTV
	   2. when HSIC product is CHANGE and TTV product is CHANGE then
		   --> pass actionType of "CH" for HSIC 
		   --> pass actionType of "CH" for TTV
	   3. when HSIC product is PROVIDE and TTV product is PROVIDE then
		   --> pass actionType of "PR" for HSIC 
		   --> pass actionType of "PR" for TTV

	 * 
	 * @param productType
	 * @param productTypeActionMap
	 * @return
	 */
	public static String getProductTypeForHsic(String productType, Map<String, String> productTypeActionMap) {
		
			String hsicActionType = productTypeActionMap.get(productType);
			
			//checking if TTV is present.
			String ttvActionType = productTypeActionMap.get(TTV);
			
			if(!StringUtils.isBlank(hsicActionType)){
				if(!StringUtils.isBlank(ttvActionType)){
					if(CH.equalsIgnoreCase(hsicActionType) && PR.equalsIgnoreCase(ttvActionType)){
						return "RG";
					}
				}
				return hsicActionType;
			}
		return null;
	}

	public static List<InstallationRequirementsList> getInstallationReqListFromFeasibilityResponse(CheckProductFeasibilityAdapterResponse response,CheckProductFeasibilityAdapterRequest feasibilityRequest,List<String> serviceTypeList,List<SubscribedProductInfoRestVO> existingProductList, boolean changeOfTechnologyInd){
		List<InstallationRequirementsList> installationRequirementList = new ArrayList<InstallationRequirementsList>();
		
		List<ProductFeasibilityInfo> productFeasiblityInfoList = response.getProductFeasibilityInfoList().getProductFeasibilityInfo();
		
		Map<String,String> productTypeActionMap = new HashMap<String,String>();
		
		for(ProductSpecification productSpecification : feasibilityRequest.getProductSpecificationList().getProductSpecification()){
			productTypeActionMap.put(productSpecification.getName(), getActionTypeFromProductSpec(productSpecification));
		}
		
		/* November 26th, 2018 - if Change Of technology scenario, productActionType should be as follows:
		 * 1.  Any product which the customer already has should be passed with order action "PV"
		   2.  Any product which the customer does not already have should be passed with order action "PR"
		   3.  Any product which is not existing and not being ordered should be omitted from the request.

		 */
		
		adjustProductTypeActionMap(productTypeActionMap,existingProductList,changeOfTechnologyInd);
		
		if(serviceTypeList!=null && !serviceTypeList.isEmpty()){
			for(String product : serviceTypeList){
				if(response!=null && response.getProductFeasibilityInfoList()!=null && productFeasiblityInfoList!=null && !productFeasiblityInfoList.isEmpty()){
					for(ProductFeasibilityInfo productFeasibilityInfo : productFeasiblityInfoList){
						if(product.equalsIgnoreCase(productFeasibilityInfo.getProductSpecification().getName())){
							InstallationRequirementsList installation = new InstallationRequirementsList();
							String productActionType;							
							if(HSIC.equalsIgnoreCase(product)){
								productActionType = getProductTypeForHsic(product,productTypeActionMap);
							}else{
								productActionType = productTypeActionMap.get(product);
							}
							installation.setActionType(productActionType);
							installation.setProductServiceType(product);
							installation.setRequestedWorkTime(String.valueOf(productFeasibilityInfo.getProvisioningInformation().getWorkTimeDefault()));
							installation.setRequestedBucketType(productFeasibilityInfo.getProvisioningInformation().getDefaultBucket());
							installation.setRequestedInstallType(productFeasibilityInfo.getProvisioningInformation().getRecommendedInstallType());
							
							installationRequirementList.add(installation);
						}
					}
				}
			}
		}
		
		return installationRequirementList;
	}


	private static String getActionTypeFromProductSpec(ProductSpecification productSpecification) {
		for(ProductSpecificationCharacteristic characteristic : productSpecification.getProductSpecificationCharacteristics()){
			if(EnterpriseWLNSalesServicesConstants.ORDER_ACTION_TYPE.equalsIgnoreCase(characteristic.getName())){
				if(EnterpriseWLNSalesServicesConstants.MODIFY.equalsIgnoreCase(characteristic.getProductSpecificationCharacteristicValues().get(0).getValue())){
					return EnterpriseWLNSalesServicesConstants.CH;
				}
				if(EnterpriseWLNSalesServicesConstants.CREATE.equalsIgnoreCase(characteristic.getProductSpecificationCharacteristicValues().get(0).getValue())){					
					return EnterpriseWLNSalesServicesConstants.PR;
				}
			}
		}
		
		return null;
	}
	
	
	public static Map<String, ServiceTypeFeasibilityVO> productWithFeasibilityInfo(CheckProductFeasibilityAdapterResponse feasibilityAdapterResponse) {
		Map<String, ServiceTypeFeasibilityVO> serviceTypeFeasibilityMap = new HashMap<String, ServiceTypeFeasibilityVO>();
		
		if(feasibilityAdapterResponse!=null && feasibilityAdapterResponse.getProductFeasibilityInfoList() != null) {
			for (ProductFeasibilityInfo info : feasibilityAdapterResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo()){
			
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
		} 
		
		return serviceTypeFeasibilityMap;
}
	
	public static Date parseStringToDate(String date, String pattern){
		DateFormat format = new SimpleDateFormat(pattern);
		Date resultDate=null;
		try {
			resultDate = format.parse(date);
		} catch (ParseException e) {
			logger.error(e);
		}
		return resultDate;
	}

	public static List<MessageVO> transformFromFeasibilityErrorResponse(List<ResponseMessage> responseMsg,
			String errorCode) {
		
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		
		if(responseMsg!=null && !responseMsg.isEmpty()){
			for(ResponseMessage downstreamMsg : responseMsg){
				MessageVO messageVO = new MessageVO();
				messageVO.setMessages(getMessagesFromFeasibility("FeasibilityService returned exception."));
				messageVO.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
				messageVO.setContextDataAttributes(getContextDataAttributesFromFeasibility(downstreamMsg.getMessageList()));
				messageVO.setErrCode(errorCode);
				messageVOList.add(messageVO);
			}
			
		}
		
		return messageVOList;
	}

	private static List<ContextDataAttributesVO> getContextDataAttributesFromFeasibility(
			com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.Message messageList) {
		List<ContextDataAttributesVO> contextAttributeList = new ArrayList<ContextDataAttributesVO>();
		ContextDataAttributesVO ctxAtributeVO = new ContextDataAttributesVO();
		ctxAtributeVO.setAttributeTypeTxt(messageList.getLocale());
		ctxAtributeVO.setAttributeValueTxt(messageList.getMessage());
		contextAttributeList.add(ctxAtributeVO);
		return contextAttributeList;
	}

	private static List<MessageDetailVO> getMessagesFromFeasibility(String errorMsg) {
		List<MessageDetailVO> messageDetailList = new ArrayList<MessageDetailVO>();
		MessageDetailVO msgDetailVO = new MessageDetailVO();
		msgDetailVO.setLocaleCd(Locale.CANADA.toString());
		msgDetailVO.setMessagetTxt(errorMsg);
		messageDetailList.add(msgDetailVO);
		return messageDetailList;
	}
	
	public static List<Product> getOrderedProductFromVO(ShoppingCartVO shoppingCartVO,ShoppingCartContextVO contextVO) {
		List<Product> productList = new ArrayList<Product>();
		
		if (shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty()) {
			for (CartItemVO cartItemVO : shoppingCartVO.getCartItemList()) {
				
				if (cartItemVO.isSalesOrderItem()) {
				
				ProductTypeVO productTypeVO = cartItemVO.getProducts();
				
				if(productTypeVO.getHomePhoneProduct()!=null){
					Product homePhone = new Product();
					if(customerIsAddingProduct(productTypeVO.getHomePhoneProduct().getProductOrderType())){
						homePhone.setProductTypeCd(EnterpriseWLNSalesServicesConstants.SING);
						homePhone.setProductTierCd(EnterpriseWLNSalesServicesConstants.SING);
						homePhone.setProductCatalogueId(getProductCatalogueIdFromVO(productTypeVO.getHomePhoneProduct().getProductComponents()));
						productList.add(homePhone);
					}									
				}
				if(productTypeVO.getInternetProduct()!=null){
					Product internet = new Product();
					if(customerIsAddingProduct(productTypeVO.getInternetProduct().getProductOrderType())){
						internet.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);
						String hsicTier = null;
						String productCatalogueId = null;
						if(productTypeVO.getInternetProduct().getProductComponents()!=null && !productTypeVO.getInternetProduct().getProductComponents().isEmpty()){
							productCatalogueId = getProductCatalogueIdFromVO(productTypeVO.getInternetProduct().getProductComponents());
						}
						CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
						CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
						
						if(catalogueItemDO!=null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())){
							hsicTier = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
						}
						if(!StringUtils.isBlank(hsicTier)){
							internet.setProductTierCd(hsicTier);
						}
						internet.setProductCatalogueId(productCatalogueId);
						productList.add(internet);
					}					
				}
				if(productTypeVO.getTelevisionProduct()!=null){
					Product television = new Product();
					if(customerIsAddingProduct(productTypeVO.getTelevisionProduct().getProductOrderType())){
						television.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);
						String ttvTier = null;
						String productCatalogueId=null;
						if(productTypeVO.getTelevisionProduct().getProductComponents()!=null && !productTypeVO.getTelevisionProduct().getProductComponents().isEmpty()){
							productCatalogueId = getProductCatalogueIdFromVO(productTypeVO.getTelevisionProduct().getProductComponents());
						}
						CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
						CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
						
						if(catalogueItemDO!=null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())){
							ttvTier = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
						}
						if(!StringUtils.isBlank(ttvTier)){
							television.setProductTierCd(ttvTier);
						}else{
							String ttvTierCodeFromCtxVO = getTTVTierCodeFromCtxVO(cartItemVO, contextVO);
							if(StringUtils.isNotBlank(ttvTierCodeFromCtxVO)){
								television.setProductTierCd(ttvTierCodeFromCtxVO);
							}
							
						}
						television.setProductCatalogueId(productCatalogueId);
						productList.add(television);
					}
				}
			}
		  }
		}
		

			
		
		
		return productList;
	}
	
	/**
	* If the cartItem has TTV product.   Then get the OMS Template ID of the TTV product from the market offer.    If OMStempalteId= 1356100 then:
	  		> add tvType=tvx to the checkProductFeasibility request.
		if the cartItem does not have TTV product but existing customer has TTV product,  then get the OMS Template Id of the TTV product from GCAP.  If the OMStemplateId=40937304 then:
			> add tvType=tvx to the checkProductFeasibility request.
	
	internalCatalogId 1356100 maps to external catalog id 40937304 .

	 * @param cartItemId
	 * @param contextVO
	 * @return
	 */
	public static String getTTVTierCodeFromCtxVO(CartItemVO cartItem, ShoppingCartContextVO contextVO) {
		String functionName="getTTVTierCodeFromCtxVO";
		logger.enter(functionName);
		String ttvTierCd = null;
		
		String cartItemOfferId = null;
		//checking the offer first
		if(cartItem!=null && cartItem.getProductMarketOffering()!=null && cartItem.getProductMarketOffering().getOfferHeader()!=null && StringUtils.isNotBlank(cartItem.getProductMarketOffering().getOfferHeader().getOfferId())){
			cartItemOfferId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId(); 
		
			GetSalesOfferDetailResponseVO2 offer = contextVO.getOfferByCartItemOfferId(cartItemOfferId);
			
			if(offer!=null && offer.getOffer()!=null){
				String omsTemplateId = getOmsTemplateIdFromOffer(offer);
				if(!StringUtils.isBlank(omsTemplateId)){
					logger.debug(functionName, "OMS template Id was found from the offer with code: " + omsTemplateId);
					
					if(OFFER_OMS_TEMPLATE_ID.equals(omsTemplateId)){
						ttvTierCd = TVX;
					}
					
					return ttvTierCd;
				}
			}
		}
		
		

		
		logger.debug(functionName, "TTV tier code was not found from marketOffer, checking subscribed products.");
		
		List<SubscribedProductInfoRestVO> assignedProducts = EnterpriseWLNCommonTransformer.getAssignedProductsFromVO(contextVO);
		
		if(!CollectionUtils.isEmpty(assignedProducts)){
			String omsOfferCatalogId = getOmsCatalogueIdFromExistingProducts(assignedProducts);
			if(!StringUtils.isEmpty(omsOfferCatalogId)){
				logger.debug(functionName, "OMS offer catalogue Id found from subscribedProducts with value: " + omsOfferCatalogId);
				
				if(WLNOfferUtil.isPikTvCatalogId(omsOfferCatalogId)){
					ttvTierCd = TVX;
				}
			}
		}
		
			
		return ttvTierCd;
	}

	private static String getOmsCatalogueIdFromExistingProducts(List<SubscribedProductInfoRestVO> assignedProducts) {
		for(SubscribedProductInfoRestVO subscribedProduct : assignedProducts){
			if(TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && CollectionUtils.isNotEmpty(subscribedProduct.getProductInstance()) && StringUtils.isNotBlank(subscribedProduct.getProductInstance().get(0).getOmsOfferCatalogId())){
				return subscribedProduct.getProductInstance().get(0).getOmsOfferCatalogId();
			}
		}
		return null;
	}

	private static String getOmsTemplateIdFromOffer(GetSalesOfferDetailResponseVO2 offer) {
		
		OfferProduct offerProduct = offer.getOffer().getOfferProduct();

		if(offerProduct!=null && CollectionUtils.isNotEmpty(offerProduct.getWirelineOfferProductList())){
			for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct wirelineProduct : offerProduct.getWirelineOfferProductList()){
				if(TTV.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
					if(wirelineProduct.getProductTemplateIdentifier()!=null && !StringUtils.isBlank(wirelineProduct.getProductTemplateIdentifier().getProductCatalogueId())){
						return wirelineProduct.getProductTemplateIdentifier().getProductCatalogueId();
					}
				}
			}			
		}
		
		return null;
	}

	public static boolean customerIsAddingProduct(ProductOrderTypeVO productOrderType) {
		
		if(productOrderType!=null && StringUtils.isNotBlank(productOrderType.getOrderTypeCd()) && EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(productOrderType.getOrderTypeCd())){
			return true;
		}
		
		return false;
	}

	public static boolean homePhoneIsNewProduct(HomePhoneProductTypeVO homePhoneProduct) {
		
		if(componentsContainsActionAdd(homePhoneProduct.getProductComponents(), homePhoneProduct.getProductOrderType().getOrderTypeCd())){
			return true;
		}
		return false;
	}
	
	public static boolean internetIsNewProduct(InternetProductTypeVO internetProduct) {
		
		if(componentsContainsActionAdd(internetProduct.getProductComponents(), internetProduct.getProductOrderType().getOrderTypeCd())){
			return true;
		}
		return false;
	}
	
	public static boolean televisionIsNewProduct(TelevisionProductTypeVO televisionProduct) {
		
		if(componentsContainsActionAdd(televisionProduct.getProductComponents(), televisionProduct.getProductOrderType().getOrderTypeCd())){
			return true;
		}
		return false;
	}


	public static boolean componentsContainsActionAdd(List<ProductComponentVO> productComponents, String productOrderType) {
		if(!CollectionUtils.isEmpty(productComponents)){
			for(ProductComponentVO productComponentVO : productComponents){
				//TODO:: this logic will be removed once we start returning enriched VO.
				if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(productOrderType)) {
					return true;
				}
				if(EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(productComponentVO.getAction())){
					return true;
				}
			}
		}
		return false;
	}

	public static String getProductCatalogueIdFromVO(List<ProductComponentVO> productComponentList){
		String productCatalogueId=null;
		if(productComponentList!=null && !productComponentList.isEmpty()){
			for(ProductComponentVO productComponent : productComponentList){
				if(!StringUtils.isBlank(productComponent.getProductCatalogueId())){
					productCatalogueId = productComponent.getProductCatalogueId();
					break;
				}
			}
		}
		return productCatalogueId;
	}

	public static List<MessageVO> getMessageVOListFromList(List<String> validationMsgList, String errorCode) {
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		MessageVO msg = new MessageVO();
		msg.setErrCode(errorCode);
		msg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		msg.setMessages(getMessagesFromValidationList(validationMsgList));
		messageVOList.add(msg);
		return messageVOList;
	}

	private static List<MessageDetailVO> getMessagesFromValidationList(List<String> validationMsgList) {
		List<MessageDetailVO> messageDetailList = new ArrayList<MessageDetailVO>();
		if(validationMsgList!=null && !validationMsgList.isEmpty()){
			for(String message : validationMsgList){
				MessageDetailVO messageDetailVO = new MessageDetailVO();
				messageDetailVO.setLocaleCd(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
				messageDetailVO.setMessagetTxt(message);
				messageDetailList.add(messageDetailVO);
			}
		}
		return messageDetailList;
	}

	public static List<com.telus.csm.ewlnsis.rest.domain.ResponseMessage> transformToResponseMessagesFromCore(
			List<MessageVO> messageList) {
		List<com.telus.csm.ewlnsis.rest.domain.ResponseMessage> responseMessageList = new ArrayList<com.telus.csm.ewlnsis.rest.domain.ResponseMessage>();
		if(messageList!=null && !messageList.isEmpty()){
			for(MessageVO msgVO : messageList){
				com.telus.csm.ewlnsis.rest.domain.ResponseMessage responseMsg = new com.telus.csm.ewlnsis.rest.domain.ResponseMessage();
				responseMsg.setErrorCode(msgVO.getErrCode());
				responseMsg.setMessageTypeCd(msgVO.getMessageType());
				responseMsg.setMessages(getMessageListFromCore(msgVO.getMessages()));
				responseMessageList.add(responseMsg);
			}
		}
		return responseMessageList;
	}

	private static List<MessageType> getMessageListFromCore(List<MessageDetailVO> messages) {
		List<MessageType> messageTypeList = new ArrayList<MessageType>();
		if(messages!=null && !messages.isEmpty()){
			for(MessageDetailVO msgDetailVO : messages){
				MessageType msgType = new MessageType();
				msgType.setLocaleCd(msgDetailVO.getLocaleCd());
				msgType.setMessageTxt(msgDetailVO.getMessagetTxt());
				messageTypeList.add(msgType);
			}
		}
		return messageTypeList;
	}

	public static ShoppingCartErrorResponse transformToShoppingCartErrorResponse(List<MessageVO> messageList, ShoppingCartVO shoppingCartVO) {
		ShoppingCartErrorResponse errorResponse = new ShoppingCartErrorResponse();
		if(shoppingCartVO != null) {
			errorResponse.setShoppingCartIdentifier(getShoppingCartIdentifier(shoppingCartVO));
		}
		
		if(messageList != null) {
			for(MessageVO msgVO: messageList) {
				com.telus.csm.ess.rest.domain.ResponseMessage responseMsg = new com.telus.csm.ess.rest.domain.ResponseMessage();
				responseMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
				responseMsg.setErrorCode(msgVO.getErrCode());
				if(msgVO.getMessages() != null){
					for(MessageDetailVO msgDetVO: msgVO.getMessages()) {
						com.telus.csm.ess.rest.domain.MessageType msgType = new com.telus.csm.ess.rest.domain.MessageType();
						msgType.messageTxt(msgDetVO.getMessagetTxt());
						msgType.setLocaleCd(msgDetVO.getLocaleCd());
						responseMsg.addMessagesItem(msgType);
					}
				}
				errorResponse.addResponseMessagesItem(responseMsg);
			}
		}

		return errorResponse;
	}

	public static ShoppingCartErrorResponse transformToShoppingCartErrorResponse(Exception exc, String errorCode, ShoppingCartVO shoppingCartVO) {
		ShoppingCartErrorResponse errorResponse = new ShoppingCartErrorResponse();
		if(shoppingCartVO != null) {
			errorResponse.setShoppingCartIdentifier(getShoppingCartIdentifier(shoppingCartVO));
		}

		com.telus.csm.ess.rest.domain.ResponseMessage responseMsg = new com.telus.csm.ess.rest.domain.ResponseMessage();
		responseMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		responseMsg.setErrorCode(errorCode);
		
		com.telus.csm.ess.rest.domain.MessageType msgType = new com.telus.csm.ess.rest.domain.MessageType();
		StringWriter writer = new StringWriter();
		PrintWriter printWriter= new PrintWriter(writer);
		exc.printStackTrace(printWriter);
		String excMsg = exc.getMessage() + "; Trace: " + writer.toString();
		msgType.messageTxt(excMsg);
		msgType.setLocaleCd(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
		responseMsg.addMessagesItem(msgType);

		errorResponse.addResponseMessagesItem(responseMsg);
		return errorResponse;
	}

	public static ShoppingCartErrorResponse transformShoppingCartException(ShoppingCartVO shoppingCartVO) {
		ShoppingCartErrorResponse errorResponse = new ShoppingCartErrorResponse();
		List<com.telus.csm.ess.rest.domain.ResponseMessage> responseMessages = new ArrayList<com.telus.csm.ess.rest.domain.ResponseMessage>();
		if(shoppingCartVO!=null && !CollectionUtils.isEmpty(shoppingCartVO.getValidationResultList())){
			for(ShoppingCartValidationTraceVO trace : shoppingCartVO.getValidationResultList()){
				com.telus.csm.ess.rest.domain.ResponseMessage responseMsg = new com.telus.csm.ess.rest.domain.ResponseMessage();
				if(trace.getErrors()!=null){
					responseMsg.setErrorCode(trace.getErrors().getCode());
					if(trace.isValidationPassedInd()){
						responseMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
					}else{
						responseMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
					}
					responseMsg.setMessages(getMessagesFromTrace(trace.getErrors()));
					responseMsg.setShoppingCartIdentifier(getCartItemIdentifier(trace));
					if (trace.getProductCode() != null) {
						ContextAttribute contextAttribute = new ContextAttribute();
						contextAttribute.setAttributeTypeTxt("PRODUCT_TYPE");
						contextAttribute.setAttributeValueTxt(trace.getProductCode());
						responseMsg.addContextDataAttributesItem(contextAttribute);
					}
					if(trace.getOfferList() != null && !trace.getOfferList().isEmpty()) {
						for (String offerId : trace.getOfferList()) {
							ContextAttribute contextAttribute = new ContextAttribute();
							contextAttribute.setAttributeTypeTxt("OFFER_ID");
							contextAttribute.setAttributeValueTxt(offerId);
							responseMsg.addContextDataAttributesItem(contextAttribute);
						}
					}
					if(CollectionUtils.isNotEmpty(trace.getMessageList())){
						for(MessageList messageList : trace.getMessageList()){
							ContextAttribute ctx = new ContextAttribute();
							ctx.setAttributeTypeTxt(messageList.getErrorCode());
							ctx.setAttributeValueTxt(getTraceMsg(messageList));
							responseMsg.addContextDataAttributesItem(ctx);
						}
					}
					responseMessages.add(responseMsg);
				}
			}
		}
		errorResponse.setResponseMessages(responseMessages);
		errorResponse.setShoppingCartIdentifier(getShoppingCartIdentifier(shoppingCartVO));
		return errorResponse;
	}

	private static String getTraceMsg(MessageList messageList) {
		StringBuilder sb = new StringBuilder();
		
		if(CollectionUtils.isNotEmpty(messageList.getMessageList())){
			for(com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message msg : messageList.getMessageList()){
				sb.append(msg.getMessage() + messageList.getContextData()!=null ? " - " + messageList.getContextData() : "");				
			}
		}
		
		return sb.toString();
	}

	private static CartItemIdentifier getCartItemIdentifier(ShoppingCartValidationTraceVO traceVO) {
		CartItemIdentifier cartItemIdentifier = new CartItemIdentifier();
		if(!StringUtils.isBlank(traceVO.getShoppingCartId()))
			cartItemIdentifier.setShoppingCartId(traceVO.getShoppingCartId());
		if(!StringUtils.isBlank(traceVO.getCartItemRelationId()))
			cartItemIdentifier.setCartItemRelationId(traceVO.getCartItemRelationId());
		return cartItemIdentifier;
	}

	private static ShoppingCartIdentifier getShoppingCartIdentifier(ShoppingCartVO shoppingCartVO) {
		ShoppingCartIdentifier scIdentifier = new ShoppingCartIdentifier();
		scIdentifier.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		if(!StringUtils.isBlank(shoppingCartVO.getShoppingCartId()))
		scIdentifier.setShoppingCartId(shoppingCartVO.getShoppingCartId());
		
		return scIdentifier;
	}

	private static List<com.telus.csm.ess.rest.domain.MessageType> getMessagesFromTrace(
			ShoppingCartValidationErrors errors) {
		List<com.telus.csm.ess.rest.domain.MessageType> messageTypeList = new ArrayList<com.telus.csm.ess.rest.domain.MessageType>();
		for (String message : errors.getMessages()) {
			com.telus.csm.ess.rest.domain.MessageType msgType = new com.telus.csm.ess.rest.domain.MessageType();
			msgType.messageTxt(message);
			msgType.setLocaleCd(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
			messageTypeList.add(msgType);
		}

		return messageTypeList;
	}
	
	public static List<SubscribedProductInfoRestVO> getAssignedProductsFromVO(ShoppingCartContextVO contextVO){
		List<SubscribedProductInfoRestVO> subscribedProducts = new ArrayList<SubscribedProductInfoRestVO>();
		if(contextVO.getAssignedAndPendingProductResponseVO()!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProductResponseVO = contextVO.getAssignedAndPendingProductResponseVO();
			ServiceAddressBase serviceAddress=new ServiceAddressBase();
			List<ServiceIdentifier> serviceIdentifierList = new ArrayList<ServiceIdentifier>();
			transformToServiceAddressBase(serviceAddress,contextVO.getServiceAddressResponseVO());
			transformToServiceIdentifierList(serviceIdentifierList,contextVO.getShoppingCartVO().getCartItemList());
			if(serviceAddressBaseIsValid(serviceAddress)){
				subscribedProducts = assignedAndPendingProductResponseVO.getAssignedProductListByServiceAddressAndServiceId(serviceAddress, serviceIdentifierList);
			}
			
		}
		return subscribedProducts;
	}
	
	private static boolean serviceAddressBaseIsValid(ServiceAddressBase serviceAddress) {
		if(serviceAddress ==null || serviceAddress.getCityCode()==null
				|| serviceAddress.getProvinceCode()==null || serviceAddress.getServiceAddressId()==null){
			return false;
		}
		return true;
	}

	private static void transformToServiceAddressBase(ServiceAddressBase serviceAddress,
			ServiceAddressResponseVO serviceAddressResponseVO) {
		if(serviceAddressResponseVO!=null && serviceAddressResponseVO.getServiceAddress()!=null){
			if(!StringUtils.isEmpty(serviceAddressResponseVO.getServiceAddress().getMunicipalityName()))
			serviceAddress.setCityCode(serviceAddressResponseVO.getServiceAddress().getMunicipalityName());
			
			if(!StringUtils.isEmpty(serviceAddressResponseVO.getServiceAddress().getProvinceStateCode()))
			serviceAddress.setProvinceCode(serviceAddressResponseVO.getServiceAddress().getProvinceStateCode());
			
			if(!StringUtils.isEmpty(serviceAddressResponseVO.getServiceAddress().getAddressId()))
			serviceAddress.setServiceAddressId(serviceAddressResponseVO.getServiceAddress().getAddressId());
		}
	}
	
	private static void transformToServiceIdentifierList(List<ServiceIdentifier> serviceIdentifierList,
			List<CartItemVO> cartItems) {
		if(cartItems!=null && !cartItems.isEmpty()){
			for(CartItemVO cartItemVO : cartItems){
				if(cartItemVO.getExistingServiceIdentifier()!=null && !cartItemVO.getExistingServiceIdentifier().isEmpty()){
					for(SubscribedServiceVO subscribedServiceVO : cartItemVO.getExistingServiceIdentifier()){
						ServiceIdentifier serviceIdentifier = new ServiceIdentifier();
						if(subscribedServiceVO.getServiceId()!=null){
							serviceIdentifier.setServiceId(subscribedServiceVO.getServiceId());
						}
						if(subscribedServiceVO.getServiceReferenceId()!=null){
							serviceIdentifier.setServiceReferenceId(subscribedServiceVO.getServiceReferenceId());
						}
						serviceIdentifierList.add(serviceIdentifier);
					}
				}
			}
		}
		
	}

	public static List<String> getExistingProductsToUpgrade(ShoppingCartContextVO contextVO) {
		List<String> existingProductList = new ArrayList<String>();
		
		List<SubscribedProductInfoRestVO> assignedProductsFromVO = getAssignedProductsFromVO(contextVO);
		
		if(!CollectionUtils.isEmpty(assignedProductsFromVO)){
			for(SubscribedProductInfoRestVO product : assignedProductsFromVO){				
				if(EnterpriseWLNOrderUtil.isUpgradingExistingProduct(product, contextVO.getShoppingCartVO()) || EnterpriseWLNOrderUtil.isCustomerRecontractingProduct(product, contextVO.getShoppingCartVO())){
					existingProductList.add(product.getProductTypeCd());
				}
			}
		}
		
		return existingProductList;
	}

	public static List<com.telus.csm.ess.rest.domain.ResponseMessage> transformWarnMessages(
			List<ShoppingCartValidationTraceVO> validationResultList) {
		List<com.telus.csm.ess.rest.domain.ResponseMessage> responseMsgList = new ArrayList<com.telus.csm.ess.rest.domain.ResponseMessage>();
		if(!CollectionUtils.isEmpty(validationResultList)){
			for(ShoppingCartValidationTraceVO trace : validationResultList){
				com.telus.csm.ess.rest.domain.ResponseMessage responseMsg = new com.telus.csm.ess.rest.domain.ResponseMessage();
				if(trace.getErrors()!=null){
					responseMsg.setErrorCode(trace.getErrors().getCode());
					if(trace.isValidationPassedInd()){
						responseMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
					}else{
						responseMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
					}
					responseMsg.setMessages(getMessagesFromTrace(trace.getErrors()));
					responseMsg.setShoppingCartIdentifier(getCartItemIdentifier(trace));
					responseMsgList.add(responseMsg);
				}
			}
		}
		return responseMsgList;
	}

	public static boolean catalogueIdMatches(Discount tomDiscount, FFHDiscountTypeVO cartItemInternetDiscount) {
		/**
		 * checking if the productCatalogueId from the shoppingCart matches the catalogueId from the TOM Discount
		 */
		/**
		 * Alejandro: Re-factoring this code on October 14, 2018 to consider multiple catalogueIds and multiple parentCatalogueIds  from cartItem and offer
		 */
		
		List<String> cartProductCatalogueIdList = new ArrayList<String>();
		List<String> cartParentProductCatalogueIdList = new ArrayList<String>();
		
		List<String> offerProductCatalogueIdList = new ArrayList<String>();
		List<String> offerParentProductCatalogueIdList = new ArrayList<String>();
		
		if(!CollectionUtils.isEmpty(cartItemInternetDiscount.getProductCatalogueIdentifiers())){
			for(ProductComponentVO productComponent : cartItemInternetDiscount.getProductCatalogueIdentifiers()){
				if(!StringUtils.isBlank(productComponent.getParentProductCatalogueId())){
					cartParentProductCatalogueIdList.add(productComponent.getParentProductCatalogueId());
				}
				if(!StringUtils.isEmpty(productComponent.getProductCatalogueId())){
					cartProductCatalogueIdList.add(productComponent.getProductCatalogueId());
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(tomDiscount.getDiscountProductCatalogueItemList())){
			for(DiscountProductCatalogueItem discountProductComponent : tomDiscount.getDiscountProductCatalogueItemList()){
				if(discountProductComponent.getProductCatalogueIdentifier()!=null && !StringUtils.isBlank(discountProductComponent.getProductCatalogueIdentifier().getProductCatalogueId())){
					offerProductCatalogueIdList.add(discountProductComponent.getProductCatalogueIdentifier().getProductCatalogueId());
				}
				
				if(discountProductComponent.getParentProductCatalogueIdentifier()!=null && !StringUtils.isBlank(discountProductComponent.getParentProductCatalogueIdentifier().getProductCatalogueId())){
					offerParentProductCatalogueIdList.add(discountProductComponent.getParentProductCatalogueIdentifier().getProductCatalogueId());
				}
					
			}
		}
		
		return cartProductCatalogueIdList.containsAll(offerProductCatalogueIdList) && offerProductCatalogueIdList.containsAll(cartProductCatalogueIdList) && cartParentProductCatalogueIdList.containsAll(offerParentProductCatalogueIdList) && offerParentProductCatalogueIdList.containsAll(cartParentProductCatalogueIdList);
		
	}
	
	public static boolean discountCatalogueIdMatches(Discount tomDiscount, ProductItemVO productItemDiscount) {
		/**
		 * checking if the productCatalogueId from the ProductItemVO matches the catalogueId from the TOM Discount
		 */
		String tomProductCId=null;
		String tomParentCId=null;
		boolean isMatched = false;
		
		if(!CollectionUtils.isEmpty(tomDiscount.getDiscountProductCatalogueItemList())){
			for(DiscountProductCatalogueItem discountProductComponent : tomDiscount.getDiscountProductCatalogueItemList()){
				if(discountProductComponent.getProductCatalogueIdentifier()!=null && !StringUtils.isBlank(discountProductComponent.getProductCatalogueIdentifier().getProductCatalogueId())){
					tomProductCId = discountProductComponent.getProductCatalogueIdentifier().getProductCatalogueId();
				}
				
				if(discountProductComponent.getParentProductCatalogueIdentifier()!=null && !StringUtils.isBlank(discountProductComponent.getParentProductCatalogueIdentifier().getProductCatalogueId())){
					tomParentCId = discountProductComponent.getParentProductCatalogueIdentifier().getProductCatalogueId();
				}

				if (tomProductCId!=null && tomProductCId.equals(productItemDiscount.getProductItemIdentifier().getProductCatalogueId()) && 
						tomParentCId!=null && tomParentCId.equals(productItemDiscount.getProductItemIdentifier().getParentProductCatalogueId()) && 
						tomDiscount.getDiscountCode().equalsIgnoreCase(productItemDiscount.getDiscountCode())){
					isMatched = true;
				}
			}
		}
		return isMatched;
	}

	public static boolean isCartItemWinback(CartItemVO cartItem) {
		if (cartItem !=null && cartItem.getProducts() !=null) {
			if (cartItem.getProducts().getHomePhoneProduct() !=null && 
					Boolean.TRUE.equals(cartItem.getProducts().getHomePhoneProduct().getWinback())  ) {
				return true;
			}
			if (cartItem.getProducts().getInternetProduct() !=null && 
					Boolean.TRUE.equals(cartItem.getProducts().getInternetProduct().getWinback())  ) {
				return true;
			}
			if (cartItem.getProducts().getTelevisionProduct() !=null && 
					Boolean.TRUE.equals(cartItem.getProducts().getTelevisionProduct().getWinback())  ) {
				return true;
			}
		}
		return false;
	}

}
