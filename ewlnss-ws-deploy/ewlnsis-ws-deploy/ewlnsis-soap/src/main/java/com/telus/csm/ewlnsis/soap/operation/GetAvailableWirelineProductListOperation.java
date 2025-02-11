package com.telus.csm.ewlnsis.soap.operation;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.REFPDS_WLN_SALES_PRODUCT_FAMILY_GROUP_RUL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.ValidationUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableProductComponentResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableProductComponentType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProduct;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductComponent;

public class GetAvailableWirelineProductListOperation {
	
	/**
	 * WLN_SALES_PRODUCT_FAMILY_GROUP_RUL attributes
	 */
	private static final String PROD_CATALOGUE_ID    = "PROD_CATALOGUE_ID";
	private static final String PROD_TYPE_CD         = "PROD_TYPE_CD";
	private static final String PROD_FAMILY_GROUP_NM = "PROD_FAMILY_GROUP_NM";
	private static final String CLASSIFIER_CD        = "CLASSIFIER_CD";

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableWirelineProductListOperation.class);
 
	public GetAvailableProductComponentResponseType execute(GetAvailableProductComponentType parameters) {
		String functionName = "execute()";
		logger.enter(functionName);

		GetAvailableProductComponentResponseType response = new GetAvailableProductComponentResponseType();
		List<MessageList> errorMessages = new ArrayList<MessageList>();
		
		List<String> missingList = new ArrayList<String>();	
		List<String> invalidInputList = new ArrayList<String>();
		ValidationUtil.validateHeader(parameters.getOperationHeader(), missingList, invalidInputList);

		if (!missingList.isEmpty() || !invalidInputList.isEmpty()) {
			response.setMessageList(generateMessageList(missingList, invalidInputList,
					parameters.getOperationHeader()));
			logger.info(functionName, "Input Validation failed.");
			return response;
		}

		// Gary 2018-03-12 fetch wireline product from RefPDS Begin
		
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		List<WirelineProduct> productList                 = new ArrayList<WirelineProduct>();
		
		GetReferencePDSResponseDO rsp                     = refPdsBusDelegate.getReferencePDSTableObjectByName(REFPDS_WLN_SALES_PRODUCT_FAMILY_GROUP_RUL);
		Map<String, Object> refPdsMap                     = rsp.getRefpdsTable();
		 
		List<Map<String, String>> refWLNProductList       = (List<Map<String, String>>)refPdsMap.get(REFPDS_WLN_SALES_PRODUCT_FAMILY_GROUP_RUL);
		
		if (refWLNProductList != null){ 
			
			/*********************************/
			/* get related REFPDS tabes      */
			/*********************************/
			GetReferencePDSResponseDO cidMapResponse      = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP);
			GetReferencePDSResponseDO productTierResponse = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER);
			
			if (cidMapResponse.hasError() || productTierResponse.hasError()){
				errorMessages.addAll(this.generateDownstreamMessage(cidMapResponse.getMessageDOList()));
				errorMessages.addAll(this.generateDownstreamMessage(productTierResponse.getMessageDOList()));
			} else {
				productList = getWirelineProductList(refWLNProductList,
						                             (Map<String, String>) cidMapResponse.getRefpdsTable().get(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP),
						                             (Map<String, String>) productTierResponse.getRefpdsTable().get(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER));
			}
		}
	
		response.setProductList(productList);
		
		if (rsp.hasError()){
			if (rsp.getMessageDOList() != null && rsp.getMessageDOList().size() > 0){
				errorMessages.addAll(this.generateDownstreamMessage(rsp.getMessageDOList()));
				
				
			}
		}
		
		if (errorMessages.size() > 0){
			response.setMessageList(errorMessages);
		}
		//Gary 2018-03-12 fetch wireline product from RefPDS End
		return response;
	}
	
	protected List<MessageList> generateDownstreamMessage(List<MessageDO> downstreamMessages){
		List<MessageList> messageList = new ArrayList<MessageList>();
		
		for (MessageDO messageDO : downstreamMessages){
			MessageList msgList = new MessageList();
			msgList.setContextData(messageDO.getContextData());
			msgList.setDateTimeStamp(messageDO.getTimestamp());
			msgList.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_WLN_PRODUCT_LIST_DOWNSTREAM_ERROR);
			
			List<Message> messages = new ArrayList<Message>();
			for (MessageDescDO messageDescDO : messageDO.getMesssageDescriptionTextList()){
				Message message = new Message();
				message.setLocale(messageDescDO.getLocale().getCountry());
				message.setMessage(messageDescDO.getMessageDescriptionText());
				messages.add(message);
			}
			msgList.setMessageList(messages);
			msgList.setMessageType(messageDO.getMessageType());
			msgList.setTransactionId(messageDO.getTransactionId());
			
			List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
			RelatedMessage relatedMessage = new RelatedMessage();
			relatedMessage.setRelatedErrorCd(messageDO.getMessageCode());
			relatedMessage.setRelatedErrorMessageTxt(messageDO.getException().getMessage());
			relatedMessage.setRelatedErrorTypeCd(messageDO.getMessageType());
			
			relatedMessageList.add(relatedMessage);
			msgList.setRelatedMessageList(relatedMessageList);
			messageList.add(msgList);
		}
		
		
		
		return messageList;
	}

	/*****************************************/
	/* generateMessageList                   */
	/*****************************************/
	protected List<MessageList> generateMessageList(List<String> missingElementList, List<String> invalidInputList,
			OperationHeader operaionHeader) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();

		// iterate over missing element list
		for (String msg : missingElementList) {
			sbMissing.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		// iterate over invalid input
		for (String msg : invalidInputList) {
			sbInvalid.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}

		if (!missingElementList.isEmpty()) {
			buildMessageList(messageList, sbMissing,
					EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_WLN_PRODUCT_LIST_MISSING_MANDATORY_ELEMENTS,
					operaionHeader);
		}

		if (!invalidInputList.isEmpty()) {
			buildMessageList(messageList, sbInvalid,
					EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_WLN_PRODUCT_LIST_INVALID_INPUT, operaionHeader);
		}

		return messageList;
	}

	private void buildMessageList(List<MessageList> messageList, StringBuilder sbMissing, String errorCode,
			OperationHeader operationHeader) {
		MessageList messageMissing = new MessageList();
		if (sbMissing.toString().contains(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_END_DATE)
				|| sbMissing.toString().startsWith("GET_AVAIL_INST_DATE_INVALID_DATE_RANGE")) {
			// The Error code for this combination will be 007
			messageMissing.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_INVALID_END_DATE);
		} else {
			messageMissing.setErrorCode(errorCode);
		}
		messageMissing.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);

		if (errorCode.equals(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_INVALID_INPUT))
			msg.setMessage(EnterpriseWLNSalesServicesConstants.INVALID_INPUT_MESSAGE_TEXT);
		else
			msg.setMessage(EnterpriseWLNSalesServicesConstants.MANDATORY_INPUT_MESSAGE_TEXT);

		messageMissing.setMessageList(Arrays.asList(msg));

		// set context data
		messageMissing.setContextData(sbMissing.toString());

		messageMissing.setTransactionId(operationHeader.getSalesTransactionId());

		messageList.add(messageMissing);
	}
	
	public static List<WirelineProduct> getWirelineProductList(List<Map<String, String>> refPdsList,
			                                                   Map<String, String> cidMap,
			                                                   Map<String, String> wssProductTier){
		
		String functionName = "transformWirelineProductList()";

		logger.enter(functionName);

		List<WirelineProduct> productList = new ArrayList<WirelineProduct>();
		
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		
		Map<String, String> wssProdOmsCidMap = MapUtils.invertMap(cidMap);
		 
		for (Map<String, String> productMap : refPdsList){
			WirelineProduct wirelineProduct = new WirelineProduct();
			wirelineProduct.setProductTypeCd(productMap.get(PROD_TYPE_CD));
			
			WirelineProductComponent component = new WirelineProductComponent();
			
			ProductCatalogueIdentifier componentId = new ProductCatalogueIdentifier(); 
			String productCatalogueId = productMap.get(PROD_CATALOGUE_ID);
			componentId.setProductCatalogueItemId(productCatalogueId); 

			component.setProductComponentFamilyCd(productMap.get(PROD_FAMILY_GROUP_NM)); 
 
			// Get Tier, description and name for Coherence
			if (productCatalogueId != null){
				logger.info(functionName, "productCatalogueId=" + productCatalogueId);
				CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
				
				if (catalogueItemDO != null){
					
					componentId.setExternalProductCatalogueItemId(catalogueItemDO.getProductCode());	
					
					if (catalogueItemDO.getProductCode() != null){
						String tier = mapOmsCode(catalogueItemDO.getProductCode(), wssProdOmsCidMap, wssProductTier );
						component.setProductTierCode(tier);
					}
					
					if (catalogueItemDO.getDescription() != null){
						component.setProductComponentDescription(buildDescriptList(catalogueItemDO.getDescription()));
					}
					
					if (catalogueItemDO.getName() != null){
						component.setProductComponentNameTxt(buildDescriptList(catalogueItemDO.getName()));
					}
				}	
			}
			component.setProductCatalogueIdentifier(componentId);
			
			wirelineProduct.setProductComponentList(Arrays.asList(component));
			productList.add(wirelineProduct);
		}
		logger.exit(functionName);
		return productList;
	}	
	
	
	private static String mapOmsCode(String productCode, Map<String, String> wssProdOmsCidMap, Map<String, String> wssProductTier){ 
		String functionName = "mapOmsCode";
		String cidCode = "";
		String omsCode = "";  
		
		if (wssProdOmsCidMap != null) {
			cidCode = wssProdOmsCidMap.get(productCode);
		}
	
		if (cidCode != null){ 
			
			if (wssProductTier != null) {
				omsCode = wssProductTier.get(cidCode);
			}
			
			if(omsCode == null) { 
				logger.info(functionName, "CID Code " + cidCode + " was NOT found in WSS_PRODUCT_TIER.");
			}
	
		}else{
			logger.info(functionName, "Product Code " + productCode + " was NOT found in WSS_PROD_OMS_CID_MAP.");
		}
		return omsCode;
	}
	
	private static MultilingualDescriptionList buildDescriptList(String desc){
		MultilingualDescriptionList list = new MultilingualDescriptionList();
		
		Description description = buildEnglishDescription(desc);
		list.setDescription(Arrays.asList(description));
		
		return list;
		
	}	
	
	private static Description buildEnglishDescription(String desc){
		Description description = new Description();
		description.setDescriptionText(desc);
		description.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE.toString());
		
		return description;
	}	
}
