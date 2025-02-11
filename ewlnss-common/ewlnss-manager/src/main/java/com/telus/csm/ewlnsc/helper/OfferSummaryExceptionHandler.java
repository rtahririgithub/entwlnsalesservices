package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ResponseStatus;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ValidationMessage;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.GetAvailableOfferSummaryTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class OfferSummaryExceptionHandler {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(OfferSummaryExceptionHandler.class);
	
	private OfferSummaryExceptionHandler(){
	}

	public static boolean getAssignedAndPendingProductsResult(GetAssignedAndPendingProductResponseVO assignedAndPendingProductResponseVO) {
		boolean result=false;
		String functionName="getAssignedAndPendingProductsResult";
		logger.enter(functionName);
		if(assignedAndPendingProductResponseVO!=null && assignedAndPendingProductResponseVO.isMsgHasError()){
			result=true;
			logger.info(functionName, "AssignedAndPendingProductsResponse has error, preparing Response with details");
		}
		logger.exit(functionName);
		return result;
	}

	public static List<MessageList> transformMessage(GetAssignedAndPendingProductResponseVO result) {
		List<MessageList> resultMessageList = new ArrayList<SalesResponseMessage.MessageList>();
		if(result!=null && result.hasError() && result.getMessageList()!=null && !result.getMessageList().isEmpty()){
			resultMessageList.addAll(result.getMessageList());
		}
		return resultMessageList;
	}

	public static boolean getProductQualificationResult(GetProductQualificationAdapterResponse productQualificationResponse) {
		boolean result=false;
		if(productQualificationResponse!=null && productQualificationResponse.hasError() && productQualificationResponse.getResponseStatus()!=null){
			int statusCd = productQualificationResponse.getResponseStatus().getStatusCd();
			boolean responseHasError = WLNOfferUtil.responseHasError(statusCd);
			if(responseHasError){
				result=true;
			}
		}
		return result;
	}

	public static GetAssignedAndPendingProductResponseVO transformExceptionForAssignedAndPendingProducts(Exception runtimeException, OperationHeader operationHeader) {
		GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO = new GetAssignedAndPendingProductResponseVO();
		GetConsolidatedAccountProfileAdapterResponse downstreamResponse = new GetConsolidatedAccountProfileAdapterResponse();
		if(runtimeException!=null){
			HttpStatusCodeException downstreamException = (HttpStatusCodeException) runtimeException; //HttpStatusCodeException has the exception for 400 and 500 error scenarios
			downstreamResponse = JsonUtil.parseJsonToObject(downstreamException.getResponseBodyAsString(), GetConsolidatedAccountProfileAdapterResponse.class);
			if(downstreamResponse!=null && downstreamResponse.getResponseStatus()!=null){
				String errorMessage="Error happened while retrieving information from SalesCustomerInfoSvc.getConsolidatedAccount, check RelatedMessageList for detail.";
				assignedAndPendingResponseVO.setMessageList(GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GAOSL_DOWN_STREAM_ERROR, errorMessage, null));
				assignedAndPendingResponseVO.getMessageList().get(0).setRelatedMessageList(EnterpriseWLNCommonTransformer.getRelatedMsg(downstreamResponse.getResponseStatus()));
			}
		}
		return assignedAndPendingResponseVO;
	}

	public static GetAssignedAndPendingProductResponseVO transformExceptionForAssignedAndPendingProducts(Exception runtimeException, OperationHeader operationHeader, String errorCode) {
		GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO = new GetAssignedAndPendingProductResponseVO();
		GetConsolidatedAccountProfileAdapterResponse downstreamResponse = new GetConsolidatedAccountProfileAdapterResponse();
		if(runtimeException!=null){
			HttpStatusCodeException downstreamException = (HttpStatusCodeException) runtimeException; //HttpStatusCodeException has the exception for 400 and 500 error scenarios
			downstreamResponse = JsonUtil.parseJsonToObject(downstreamException.getResponseBodyAsString(), GetConsolidatedAccountProfileAdapterResponse.class);

			if ( (downstreamResponse != null) && (downstreamResponse.getResponseStatus() != null) ) {
				String errorMessage="Error happened while retrieving information from SalesCustomerInfoSvc.getConsolidatedAccount, check RelatedMessageList for detail.";
				assignedAndPendingResponseVO.setMessageList(GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, errorCode, errorMessage, null));
				assignedAndPendingResponseVO.getMessageList().get(0).setRelatedMessageList(EnterpriseWLNCommonTransformer.getRelatedMsg(downstreamResponse.getResponseStatus()));
			}
			else {
				String relatedErrorCode = StringUtility.getNeededTagContentFromSoapException(downstreamException.getResponseBodyAsString(), "faultcode");
				String relatedErrorMessageTxt = StringUtility.getNeededTagContentFromSoapException(downstreamException.getResponseBodyAsString(), "faultstring");

				if ( (relatedErrorCode != null) && (!relatedErrorCode.isEmpty()) && (relatedErrorMessageTxt != null) && (!relatedErrorMessageTxt.isEmpty()) ) {
					String relatedErrorTypeCd = downstreamException.getStatusText();

					String errorMessage="Error happened while retrieving information from SalesCustomerInfoSvc.getConsolidatedAccount, check RelatedMessageList for detail.";
					assignedAndPendingResponseVO.setMessageList(GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, errorCode, errorMessage, null));
					assignedAndPendingResponseVO.getMessageList().get(0).setRelatedMessageList(generateRelatedMsg(relatedErrorCode, relatedErrorMessageTxt, relatedErrorTypeCd));
				}
			}
		}

		return assignedAndPendingResponseVO;
	}

	

	private static List<RelatedMessage> generateRelatedMsg(String relatedErrorCode, String relatedErrorMessageTxt, String relatedErrorTypeCd) {
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();

		RelatedMessage relatedMsg = new RelatedMessage();
		relatedMsg.setRelatedErrorCd(relatedErrorCode);
		relatedMsg.setRelatedErrorMessageTxt(relatedErrorMessageTxt);
		relatedMsg.setRelatedErrorTypeCd(relatedErrorTypeCd);
		relatedMessageList.add(relatedMsg);

		return relatedMessageList;
	}

	public static List<MessageList> prevalidate(SalesOfferCommonVO commonVO) {
		//ERROR scenario ESS_GAOSL_00005: if call to AddressManagement is successful but service does not find the address then halt with an error.
		String errorMessage;
		OperationHeader operationHeader=null;
		List<MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
		List<SubscribedServiceIdentifierVO> subscribedServiceIdentifierList=null;
		if(commonVO.getOfferDetailRequestVO()!=null){
			operationHeader = commonVO.getOfferDetailRequestVO().getOperationHeader();
			subscribedServiceIdentifierList = commonVO.getOfferDetailRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier();
		}
		if(commonVO.getOffersRequestVO()!=null){
			operationHeader = commonVO.getOffersRequestVO().getOperationHeader();
			subscribedServiceIdentifierList = commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier();
		}
		if(commonVO.getServiceAddressResponseVO()!=null && commonVO.getServiceAddressResponseVO().getServiceAddress()==null){
			errorMessage="Call to ServiceAddressMgmtSvc didnt returned Address related information.";
			messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GAOSL_NO_ADDRESS_FOUND, errorMessage, null);
			return messageList;
		}
		
		//ERROR SCENARIO [ESS_GAOSL_00007]:  If there is a serviceIdentifier listed in the ESS request but that service is not found from the OP Order Query service (or getConsolidatedAccountProfile service) then throw an error and fail.  
		//ERROR SCENARIO [ESS_GAOSL_00007]:  If there is a existing subscribed product (referenced by serviceIdentifier.serviceId) in the ESS request that is found but doesn’t match the service address then fail with error.
		if(commonVO.getAssignedAndPendingProductsResponseVO()!=null && !CollectionUtils.isEmpty(commonVO.getAssignedAndPendingProductsResponseVO().getMessageList()) && !commonVO.getAssignedAndPendingProductsResponseVO().hasError()){
			boolean serviceIdNotFoundInd=false;
			boolean serviceAddressNotMatchInd=false;
			String requestServiceAddressId=null;
			
			if(commonVO.getOfferDetailRequestVO()!=null){
				
				requestServiceAddressId = commonVO.getOfferDetailRequestVO().getSalesOfferCriteria().getServiceAddress().getServiceAddressId();
			}
			else if(commonVO.getOffersRequestVO()!=null){
				
				requestServiceAddressId = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress().getServiceAddressId();
			}
			//Getting the assigned products of the customer.
			if(subscribedServiceIdentifierList!=null && !subscribedServiceIdentifierList.isEmpty()){
				GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = commonVO.getAssignedAndPendingProductsResponseVO();
				List<SubscribedProductInfoRestVO> subscribedProductList = assignedAndPendingProducts.getSubscribedProductList();
				List<SubscribedProductInfoRestVO> pendingProductList = assignedAndPendingProducts.getPendingProductList();
				if(subscribedProductList!=null && !subscribedProductList.isEmpty()){
					List<List<ProductInstanceInfoRestVO>> productInstanceList=new ArrayList<List<ProductInstanceInfoRestVO>>();	
					//Collecting all ProductInstances returned in consolidateAccResponse
					
					for(SubscribedProductInfoRestVO subscribedProduct : subscribedProductList){
						if(subscribedProduct.getProductInstance()!=null && !subscribedProduct.getProductInstance().isEmpty()){
							productInstanceList.add(subscribedProduct.getProductInstance());
						}
					}
					if(!CollectionUtils.isEmpty(pendingProductList)){
						for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
							if(!CollectionUtils.isEmpty(pendingProduct.getProductInstance())){
								productInstanceList.add(pendingProduct.getProductInstance());
							}
						}
					}
					
					if(productInstanceList!=null && !productInstanceList.isEmpty()){
						for(SubscribedServiceIdentifierVO subscribedService : subscribedServiceIdentifierList){
							String requestServiceId = subscribedService.getServiceId();
							if(!checkExistingServiceId(requestServiceId,productInstanceList)){
								serviceIdNotFoundInd=true;
								break;
							}
						}
					}
					
					if(serviceIdNotFoundInd){
						errorMessage="There is a subscribedServiceId in the request but the product was not found from consolidatedAccount response (Assigned and Pending products response)";

						if (Thread.currentThread().getStackTrace()[2].getClassName().matches(".*GetSalesOfferDetailHelper.*")) {
//							 (commonVO.getOfferDetailRequestVO() != null) &&
//							 (commonVO.getOfferDetailRequestVO().getOfferId() != null) &&
//							 (!commonVO.getOfferDetailRequestVO().getOfferId().isEmpty()) ) {
							// We're in the context of getSalesOfferDetail
							messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GSOD_NO_PRODUCT_FOUND, errorMessage, null);
						}
						else if (Thread.currentThread().getStackTrace()[2].getClassName().matches(".*GetAvailableOfferSummaryHelper.*")) {
//						else {
							// We're in the context of getAvailableOfferSummaryList 
							messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GAOSL_NO_PRODUCT_FOUND, errorMessage, null);							
						}

						return messageList;
					}
					
					for(SubscribedProductInfoRestVO subscribedProduct : assignedAndPendingProducts.getSubscribedProductList()){
						for(SubscribedServiceIdentifierVO subscribedService : subscribedServiceIdentifierList){
							String requestServiceId = subscribedService.getServiceId();
							if(subscribedProduct.getProductInstance()!=null && !subscribedProduct.getProductInstance().isEmpty()){
								for(ProductInstanceInfoRestVO productInstance : subscribedProduct.getProductInstance()){
									if(requestServiceId.equals(productInstance.getServiceId())){
										if (!requestServiceAddressId.equals(subscribedProduct.getServiceAddress().getServiceAddressId())) {
											serviceAddressNotMatchInd = true;
											break;
										}
									}
								}
							}
								
						}
						
					}
						
					
					if(serviceAddressNotMatchInd){
						errorMessage="The serviceAddressId from the request doesnt match the subscribed serviceAddressId";

						if (Thread.currentThread().getStackTrace()[2].getClassName().matches(".*GetSalesOfferDetailHelper.*")) {
//							 (commonVO.getOfferDetailRequestVO() != null) &&
//							 (commonVO.getOfferDetailRequestVO().getOfferId() != null) &&
//							 (!commonVO.getOfferDetailRequestVO().getOfferId().isEmpty()) ) {
							// We're in the context of getSalesOfferDetail
							messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GSOD_NO_PRODUCT_FOUND, errorMessage, null);
						}
						else if (Thread.currentThread().getStackTrace()[2].getClassName().matches(".*GetAvailableOfferSummaryHelper.*")) {
//						else {
							// We're in the context of getAvailableOfferSummaryList 
							messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GAOSL_NO_PRODUCT_FOUND, errorMessage, null);
						}

						return messageList;
					}
						
				}
			}
		
		}else if(commonVO.getAssignedAndPendingProductsResponseVO()!=null && !CollectionUtils.isEmpty(commonVO.getAssignedAndPendingProductsResponseVO().getMessageList()) && commonVO.getAssignedAndPendingProductsResponseVO().hasError()){
			if(WLNOfferUtil.banNotFoundError(commonVO.getAssignedAndPendingProductsResponseVO()) && !CollectionUtils.isEmpty(subscribedServiceIdentifierList)){
				if (Thread.currentThread().getStackTrace()[2].getClassName().matches(".*GetSalesOfferDetailHelper.*")) {
					errorMessage="Call for SalesCustomerInfoService.consolidatedAcc failed with error for account not found and not and subscribedServiceIdentifierList was passed.";
					messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GSOD_DOWN_STREAM_ERROR, errorMessage, null);
				}else if(Thread.currentThread().getStackTrace()[2].getClassName().matches(".*GetAvailableOfferSummaryHelper.*")) {
					errorMessage="Call for SalesCustomerInfoService.consolidatedAcc failed with error for account not found and not and subscribedServiceIdentifierList was passed.";
					messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, EnterpriseWLNSalesServicesErrorCodes.GAOSL_DOWN_STREAM_ERROR, errorMessage, null);
				}
				
				return messageList;
			}
		}
		
		return messageList;
	}
	
	public static List<MessageList> validateRefPDSStatus(OperationHeader operationHeader,String errorCode){
		List<MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
		//Checking if RefPDSService is down, if it is then end the flow.
		String errorMessage=null;
		ReferencePDSDataSvcBusDelegator refPDSBusDelegator = ReferencePDSDataSvcBusDelegator.getInstance();
				
		GetReferencePDSResponseDO referencePDSTableObjectByName = refPDSBusDelegator.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER);
				
		if(referencePDSTableObjectByName.isMsgHasError()){
					
			errorMessage = "ReferencePDSService returned error, please see RelatedMessageList for details.";
			messageList = GetAvailableOfferSummaryTransformer.generateMessageList(operationHeader, errorCode, errorMessage, null);
			messageList.get(0).setRelatedMessageList(getExceptionFromRefPDSSvc(referencePDSTableObjectByName.getMsgList()));
		}
		return messageList;
	}

	private static List<RelatedMessage> getExceptionFromRefPDSSvc(List<MessageDO> messageDOList) {
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
		if(messageDOList!=null && !messageDOList.isEmpty()){
			for(MessageDO messageDO : messageDOList){
				RelatedMessage relatedMessage = new RelatedMessage();
				relatedMessage.setRelatedErrorCd(messageDO.getMessageCode());
				relatedMessage.setRelatedErrorTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
				relatedMessage.setRelatedErrorMessageTxt(messageDO.getException().getMessage());
				relatedMessageList.add(relatedMessage);
			}
		}
		return relatedMessageList;
	}

	private static boolean checkExistingServiceId(String requestServiceId,
			List<List<ProductInstanceInfoRestVO>> consolidateAccProductInstances) {
		boolean result=false;
		List<String> consolidateAccServiceIdList = new ArrayList<String>();
		for(List<ProductInstanceInfoRestVO> productInstanceList : consolidateAccProductInstances){
			for(ProductInstanceInfoRestVO productInstance : productInstanceList){
				consolidateAccServiceIdList.add(productInstance.getServiceId());
			}
		}
		
		if(consolidateAccServiceIdList.contains(requestServiceId)){
			result=true;
		}
		
		return result;
	}




}
