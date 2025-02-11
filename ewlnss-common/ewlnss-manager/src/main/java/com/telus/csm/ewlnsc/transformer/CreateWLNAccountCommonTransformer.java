package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.List;


import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterResponse;
import com.telus.csm.ewlnsc.domain.CreateProfileForNewCustomerRequestCommonVO;
import com.telus.csm.ewlnsc.domain.CreateProfileForNewCustomerResponseCommonVO;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;


public class CreateWLNAccountCommonTransformer {
	
	private CreateWLNAccountCommonTransformer(){
		
	}
	
	public static CreateProfileForNewCustomerAdapterRequest transformCreateProfForNewCustCommonVO(
			CreateProfileForNewCustomerRequestCommonVO request) {
		CreateProfileForNewCustomerAdapterRequest req = new CreateProfileForNewCustomerAdapterRequest();
		req.setFirstName(request.getFirstName());
		req.setLastName(request.getLastName());
		req.setEmail(request.getEmail());
		req.setNotificationLanguage(request.getNotificationLanguage());
		req.setCustomerId(request.getCustomerId());
		return req;
	}

	public static CreateProfileForNewCustomerResponseCommonVO transformCreateProfForNewCustAdapterResponse(
			CreateProfileForNewCustomerAdapterResponse adapterResponse) {
		CreateProfileForNewCustomerResponseCommonVO resp = new CreateProfileForNewCustomerResponseCommonVO();
		if (adapterResponse.getAdapterResponseMessage() != null){
			resp.setTransactionId(adapterResponse.getAdapterResponseMessage().getTransactionId());
		}
		resp.setMessageList(transformAdapterResponseMessage(adapterResponse.getAdapterResponseMessage()));
		return resp;
	}

	private static List<MessageList> transformAdapterResponseMessage(AdapterResponseMessage adapterResponseMessage) {
		List<MessageList> msgList = new ArrayList<MessageList>();
		MessageList msg = new MessageList();
		msg.setDateTimeStamp(adapterResponseMessage.getDateTimeStamp());
		msg.setErrorCode(adapterResponseMessage.getMessageCode());
		msg.setMessageType(adapterResponseMessage.getMessageType());
		msg.setTransactionId(adapterResponseMessage.getTransactionId());
		msg.setMessageList(transformMessageDescText(adapterResponseMessage.getMesssageDescriptionTextList()));
		msg.setContextData(adapterResponseMessage.getContextData());
		msgList.add(msg);
		return msgList;
	}

	private static List<Message> transformMessageDescText(
			List<AdapterResponseMessageDesc> messsageDescriptionTextList) {
		List<Message> msgList = new ArrayList<Message>();
		for (AdapterResponseMessageDesc msgDesc : messsageDescriptionTextList){
			Message msg = new Message();
			msg.setLocale(msgDesc.getLocale().toString());
			msg.setMessage(msgDesc.getMessageDescriptionText());
			msgList.add(msg);
		}
		return msgList;
	}
	
}
