package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByPromotionCodeForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAccessoryOfferListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetSweetenerOfferListForCustomerAdapterRequest;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetAccessoryOfferList;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetAccessoryOfferListResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByOfferIdentifierList;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByOfferIdentifierListResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByPromotionCodeForCustomer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByPromotionCodeForCustomerResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListForCustomer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListForCustomerResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetSweetenerListByOfferSummaryListForCustomer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetSweetenerListByOfferSummaryListForCustomerResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.ResponseMessage;

public class OfferInformationServiceTransformer {

	private OfferInformationServiceTransformer(){
	}
	
	
	/****************************************************************/
	/* transform request for GetOfferListByPromotionCodeForCustomer */
	/****************************************************************/
	public static GetOfferListByPromotionCodeForCustomer transform(GetOfferListByPromotionCodeForCustomerAdapterRequest adapterRequest){
		GetOfferListByPromotionCodeForCustomer request = new GetOfferListByPromotionCodeForCustomer();
		
		request.setCustomer(adapterRequest.getCustomer());
		request.setOutlet(adapterRequest.getOutlet());
		request.setPromotionCode(adapterRequest.getPromotionCode());
		request.setWirelineTransactionalContext(adapterRequest.getWirelineTransactionalContext());
		return request;
	}
	
	/******************************************************************/
	/* transform response from GetOfferListByPromotionCodeForCustomer */
	/******************************************************************/
	public static GetOfferListAdapterResponse transform(GetOfferListByPromotionCodeForCustomerResponse response){
		GetOfferListAdapterResponse adapterResponse = new GetOfferListAdapterResponse();
		
		//consider error if messageList is not empty or errorCode is not empty
		if(response!=null){
			if(response.getMessageList()!=null && !response.getMessageList().isEmpty() || (response.getErrorCode() != null && response.getErrorCode().trim().length() > 0)){
				adapterResponse.setMsgHasError(true);
				adapterResponse.setSuccessfulProcessInd(false);
				adapterResponse.setResponseMessage(response);
			}else{
				adapterResponse.setSuccessfulProcessInd(true);
				adapterResponse.setOfferList(response.getPromoCodeOfferListResponse().getOfferList());
				adapterResponse.setProgramPromotion(response.getPromoCodeOfferListResponse().getProgramPromotion());
			}
		}
		
		return adapterResponse;
	}
	
	
	
	public static GetOfferListByOfferIdentifierList transform(GetOfferListByOfferIdentifierListAdapterRequest request) {
		GetOfferListByOfferIdentifierList downstreamRequest = new GetOfferListByOfferIdentifierList();
		downstreamRequest.setBrandId(request.getBrandId());
		downstreamRequest.setOfferIdentifierList(request.getOfferIdentifierList());
		downstreamRequest.setOutlet(request.getOutlet());
		downstreamRequest.setWirelineTransactionalContext(request.getWirelineTransactionalContext());
		return downstreamRequest;
		
	}

	public static GetOfferListAdapterResponse transform(GetOfferListByOfferIdentifierListResponse response) {
		GetOfferListAdapterResponse adapterResponse = new GetOfferListAdapterResponse();
		if(response!=null){
			if(response.getMessageList()!=null && !response.getMessageList().isEmpty()){
				adapterResponse.setMsgHasError(true);
				adapterResponse.setResponseMessage(transformErrorMsgs(response));
			}else{
				adapterResponse.setSuccessfulProcessInd(true);
				adapterResponse.setOfferList(response.getOfferList());
			}
		}
		return adapterResponse;
	}

	private static ResponseMessage transformErrorMsgs(GetOfferListByOfferIdentifierListResponse response) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setContextData(response.getContextData());
		responseMessage.setDateTimeStamp(response.getDateTimeStamp());
		responseMessage.setErrorCode(response.getErrorCode());
		responseMessage.setMessageType(response.getMessageType());
		responseMessage.setTransactionId(response.getTransactionId());
		responseMessage.setMessageList(response.getMessageList());
		return responseMessage;
	}
	
	private static ResponseMessage transformErrorMsgs(GetOfferListForCustomerResponse response) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setContextData(response.getContextData());
		responseMessage.setDateTimeStamp(response.getDateTimeStamp());
		responseMessage.setErrorCode(response.getErrorCode());
		responseMessage.setMessageType(response.getMessageType());
		responseMessage.setTransactionId(response.getTransactionId());
		responseMessage.setMessageList(response.getMessageList());
		return responseMessage;
	}

	public static GetOfferListForCustomer transform(GetOfferListForCustomerAdapterRequest request) {
		GetOfferListForCustomer downstreamRequest = new GetOfferListForCustomer();
		if(request!=null){
			downstreamRequest.setCustomer(request.getCustomer());
			downstreamRequest.setOutlet(request.getOutlet());
			downstreamRequest.setWirelineTransactionalContext(request.getWirelineTransactionalContext());
		}
		return downstreamRequest;
	}

	public static GetOfferListAdapterResponse transform(GetOfferListForCustomerResponse response) {
		GetOfferListAdapterResponse adapterResponse = new GetOfferListAdapterResponse();
		if(response!=null){
			if(response.getMessageList()!=null && !response.getMessageList().isEmpty()){
				adapterResponse.setMsgHasError(true);
				adapterResponse.setResponseMessage(transformErrorMsgs(response));
			}else{
				adapterResponse.setSuccessfulProcessInd(true);
				adapterResponse.setOfferList(response.getOfferList());
			}
		}
		return adapterResponse;
	}

	public static GetSweetenerListByOfferSummaryListForCustomer transform(GetSweetenerOfferListForCustomerAdapterRequest request) {
		GetSweetenerListByOfferSummaryListForCustomer downstreamRequest = new GetSweetenerListByOfferSummaryListForCustomer();
		if(request!=null){
			downstreamRequest.setCustomer(request.getCustomer());
			downstreamRequest.setOfferHeaderList(request.getOfferHeaderList());
			downstreamRequest.setOutlet(request.getOutlet());
			downstreamRequest.setWirelineTransactionalContext(request.getWirelineTransactionalContext());
		}
		return downstreamRequest;
	}

	public static GetOfferListAdapterResponse transform(GetSweetenerListByOfferSummaryListForCustomerResponse response) {
		GetOfferListAdapterResponse adapterResponse = new GetOfferListAdapterResponse();
		if(response!=null){
			if(response.getMessageList()!=null && !response.getMessageList().isEmpty()){
				adapterResponse.setMsgHasError(true);
				adapterResponse.setResponseMessage(transformErrorMsgs(response));
			}else{
				adapterResponse.setSuccessfulProcessInd(true);
				adapterResponse.setSweetenerList(response.getSweetenerList());
			}
		}
		return adapterResponse;
	}

	private static ResponseMessage transformErrorMsgs(GetSweetenerListByOfferSummaryListForCustomerResponse response) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setContextData(response.getContextData());
		responseMessage.setDateTimeStamp(response.getDateTimeStamp());
		responseMessage.setErrorCode(response.getErrorCode());
		responseMessage.setMessageType(response.getMessageType());
		responseMessage.setTransactionId(response.getTransactionId());
		responseMessage.setMessageList(response.getMessageList());
		return responseMessage;
	}

	public static GetAccessoryOfferList transform(GetAccessoryOfferListAdapterRequest request) {
		GetAccessoryOfferList getAccessoryOfferyList = null;

		if (request != null) {
			getAccessoryOfferyList = new GetAccessoryOfferList();
			getAccessoryOfferyList.setOutlet(request.getOutlet());
			getAccessoryOfferyList.setCustomer(request.getCustomer());
			getAccessoryOfferyList.setLineOfBusinessCd(request.getLineOfBusinessCd());
			getAccessoryOfferyList.setBrandId(request.getBrandId());
			getAccessoryOfferyList.setWirelineTransactionalContext(request.getWirelineTransactionalContext());
		}

		return getAccessoryOfferyList;
	}

	public static GetOfferListAdapterResponse transform(GetAccessoryOfferListResponse response) {
		GetOfferListAdapterResponse adapterResponse = null;

		if (response != null) {
			adapterResponse = new GetOfferListAdapterResponse();

			if (response.getMessageList() != null && !response.getMessageList().isEmpty()) {
				adapterResponse.setMsgHasError(true);
				adapterResponse.setSuccessfulProcessInd(false);
				adapterResponse.setResponseMessage(response);
			}
			else {
				adapterResponse.setSuccessfulProcessInd(true);
				adapterResponse.setOfferList(response.getOfferList());
			}
		}

		return adapterResponse;
	}
}
