package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Program;

public class BaseOfferTransformer {

	public BaseOfferTransformer() {
	}
	
	public static List<MessageList> generateMessageList(OperationHeader operationHeader, String errorCode, String errorMessage, List<TraceVO> traces) {
		String salesTransactionId = "";
		if (operationHeader != null && operationHeader.getSalesTransactionId() != null) {
			salesTransactionId = operationHeader.getSalesTransactionId();
		}
		
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList message = new MessageList();
		message.setDateTimeStamp(new Date());
		message.setTransactionId(salesTransactionId);
		message.setErrorCode(errorCode);
		message.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		msg.setMessage(errorMessage);
		message.setMessageList(Arrays.asList(msg));
	
		// set context data with the traces - Do this only based on SIP 
		StringBuilder trace = new StringBuilder(); 
		if (traces != null && !traces.isEmpty()) {
			for (TraceVO x : traces) {
				trace.append(x.toLogMessage() + "\n");
			}
			message.setContextData(trace.toString());
		}
		messageList.add(message);
	
		return messageList;
	}

	public static void transform(SalesOfferCommonVO commonVO, GetOffersResponseVO offersResponseVO) {
		if (commonVO.getOfferListAdapterResponse()!=null &&
			commonVO.getOfferListAdapterResponse().isMsgHasError() &&
			commonVO.getOfferListAdapterResponse().getResponseMessage() != null) {
			String errorMessage="OfferInformationService returned error, check RelatedMessageList for detail.";
			offersResponseVO.setMessageList(generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), EnterpriseWLNSalesServicesErrorCodes.GAOSL_DOWN_STREAM_ERROR, errorMessage, null));
			offersResponseVO.getMessageList().get(0).setRelatedMessageList(getRelatedMsg(commonVO.getOfferListAdapterResponse()));
			offersResponseVO.setMsgHasError(true);
		}
		else if (commonVO.getOfferListAdapterResponse()!=null &&
				 commonVO.getOfferListAdapterResponse().getAdapterResponseMessage() != null) {
			String errorMessage="OfferInformationService returned error, check RelatedMessageList for details.";
			offersResponseVO.setMsgHasError(true);
			offersResponseVO.setMessageList(generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), EnterpriseWLNSalesServicesErrorCodes.GAOSL_DOWN_STREAM_ERROR, errorMessage, null));
			offersResponseVO.getMessageList().get(0).setRelatedMessageList(getRelatedMsg(commonVO.getOfferListAdapterResponse()));
		}
	}

	public static List<RelatedMessage> getRelatedMsg(GetOfferListAdapterResponse getOfferListAdapterResponse) {
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
		if(getOfferListAdapterResponse!=null && getOfferListAdapterResponse.getResponseMessage()!=null){
			ResponseMessage responseMessage = getOfferListAdapterResponse.getResponseMessage();
			for(com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Message msg : responseMessage.getMessageList()){
				RelatedMessage relatedMsg = new RelatedMessage();
				relatedMsg.setRelatedErrorCd(responseMessage.getErrorCode());
				relatedMsg.setRelatedErrorTypeCd(responseMessage.getMessageType());
				relatedMsg.setRelatedErrorMessageTxt(msg.getMessage());
				relatedMessageList.add(relatedMsg);
			}
		}else if(getOfferListAdapterResponse!=null && getOfferListAdapterResponse.getAdapterResponseMessage()!=null){
			//ServiceException,PolicyException scenarios
			AdapterResponseMessage adapterResponseMessage = getOfferListAdapterResponse.getAdapterResponseMessage();
			RelatedMessage relatedMessage = new RelatedMessage();
			relatedMessage.setRelatedErrorCd(adapterResponseMessage.getMessageCode());
			relatedMessage.setRelatedErrorMessageTxt(adapterResponseMessage.getContextData());
			relatedMessage.setRelatedErrorTypeCd(adapterResponseMessage.getMessageType());
			relatedMessageList.add(relatedMessage);
		}
		return relatedMessageList;
	}

	public static List<AccessoryOffer> getAccessoryOfferSummaryList(List<Offer> offerList) {
		List<AccessoryOffer> accessoryOfferList = new ArrayList<AccessoryOffer>();
	
		if (offerList!=null && !offerList.isEmpty()) {
			for (Offer offer : offerList) {
				AccessoryOffer accessoryOffer = new AccessoryOffer();
				accessoryOffer.setOfferProgram(getOfferProgram(offer));
				accessoryOffer.setOfferHeader(getOfferHeader(offer));
				accessoryOffer.setOfferProduct(offer.getOfferProduct());
				accessoryOfferList.add(accessoryOffer);
			}
		}
	
		return accessoryOfferList;
	}

	public static OfferHeader getOfferHeader(Offer offer) {
		OfferHeader offerHeader = new OfferHeader();
		offerHeader.setBasePriceAmt(offer.getBasePriceAmt());
		offerHeader.setOfferCategoryList(offer.getOfferCategoryList());
		offerHeader.setOfferDescriptionList(transformOfferDescriptionList(offer.getOfferDescriptionList()));
		offerHeader.setOfferId(String.valueOf(offer.getOfferId()));
		offerHeader.setOfferTierId(offer.getOfferTierId());
		offerHeader.setPaymentSupportTypeCode(offer.getPaymentSupportTypeCode());
		offerHeader.setPerspectiveDate(offer.getPerspectiveDate());
		offerHeader.setProductEligiblityList(offer.getProductEligiblityList());
		offerHeader.setRankingNum(offer.getRankingNum());
		offerHeader.setWirelessOfferCode(offer.getWirelessOfferCode());
//		offerHeader.setOfferReferenceId(offer.getOfferReferenceId());
//		offerHeader.setAssignedOfferInd(offer.isAssignedOfferInd());
//		offerHeader.setOfferMarketingMessageList(offer.getOfferMarketingMessageList());
		return offerHeader;
	}

	public static Program getOfferProgram(Offer offer) {
		Program program = new Program();
		program.setBrandId(offer.getBrandId());
		program.setEffectiveDate(offer.getEffectiveDate());
		program.setExpiryDate(offer.getExpiryDate());
		program.setPerspectiveDate(offer.getPerspectiveDate());
		program.setProgramCode(offer.getProgramCode());
		program.setProgramDescriptionList(offer.getProgramDescriptionList());
		program.setProgramId(offer.getProgramId());
		program.setPromotionCode(offer.getPromotionCode());
		program.setSourceSystemId(offer.getSourceSystemId());
		program.setTypeCode(offer.getTypeCode());
		return program;
	}

	
	public static List<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description> transformOfferDescriptionList(List<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description> inputOfferDescriptionList) {
		List <com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description> outputOfferDescriptionList = null;
	
		if ( (inputOfferDescriptionList != null) && (!inputOfferDescriptionList.isEmpty()) ) {
			outputOfferDescriptionList = new ArrayList<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description>();
	
			for (com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description inputOfferDescription : inputOfferDescriptionList) {
				com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description outputOfferDescription = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description();
				outputOfferDescription.setDescriptionText(inputOfferDescription.getDescriptionText());
				outputOfferDescription.setLocale(inputOfferDescription.getLocale());
				outputOfferDescriptionList.add(outputOfferDescription);
			}
		}
	
		return outputOfferDescriptionList;
	}
}