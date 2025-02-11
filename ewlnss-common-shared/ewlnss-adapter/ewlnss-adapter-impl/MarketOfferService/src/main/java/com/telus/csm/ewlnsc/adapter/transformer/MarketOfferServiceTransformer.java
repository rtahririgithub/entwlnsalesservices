package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.marketofferservicerequestresponse_v3.GetMarketOfferDetail;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.marketofferservicerequestresponse_v3.GetMarketOfferDetailResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.ResponseMessage;

public class MarketOfferServiceTransformer {
	
	private MarketOfferServiceTransformer(){
	}

	public static GetMarketOfferDetail transform(GetMarketOfferDetailAdapterRequest request) {
		GetMarketOfferDetail getMarketOfferDetailRequest = new GetMarketOfferDetail();
		getMarketOfferDetailRequest.setOfferIdList(request.getOfferIdList());
		return getMarketOfferDetailRequest;
	}

	public static GetMarketOfferDetailAdapterResponse transform(GetMarketOfferDetailResponse response) {
		GetMarketOfferDetailAdapterResponse adapterResponse = new GetMarketOfferDetailAdapterResponse();
		if(response!=null){
			if(response.getMessageList()!=null && !response.getMessageList().isEmpty()){
				adapterResponse.setMsgHasError(true);
				adapterResponse.setResponseMessage(transformErrorMsgs(response));
			}else{
				adapterResponse.setMarketOfferDetailList(response.getMarketOfferDetailList());
				adapterResponse.setSuccessfulProcessInd(true);
			}
		}else{
			adapterResponse.setMsgHasError(true);
		}
		return adapterResponse;
	}

	private static ResponseMessage transformErrorMsgs(GetMarketOfferDetailResponse response) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setContextData(response.getContextData());
		responseMessage.setDateTimeStamp(response.getDateTimeStamp());
		responseMessage.setErrorCode(response.getErrorCode());
		responseMessage.setMessageType(response.getMessageType());
		responseMessage.setTransactionId(response.getTransactionId());
		responseMessage.setMessageList(response.getMessageList());
		return responseMessage;
	}

}
