package com.telus.csm.ewlnsc.adapter.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.LocaleUtils;

import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterResponse;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.enterpriseconsumerprofileregistrationsvcrequestresponse_v1.CreateProfileForNewCustomer;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.enterpriseconsumerprofileregistrationsvcrequestresponse_v1.CreateProfileForNewCustomerResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Message;

/**
 * 
 * @author Jose.Mena
 *
 */
public class EnterpriseConsumerProfRegSvcTransformer {

	public static CreateProfileForNewCustomer transformAdapterRequestForCreateProfForNewCust(
			CreateProfileForNewCustomerAdapterRequest request) {
		CreateProfileForNewCustomer req = new CreateProfileForNewCustomer();
		req.setFirstName(request.getFirstName());
		req.setLastName(request.getLastName());
		req.setEmail(request.getEmail());
		req.setNotificationLanguage(request.getNotificationLanguage());
		req.setCustomerId(request.getCustomerId());
		return req;
	}

	public static CreateProfileForNewCustomerAdapterResponse transformToAdapterResponse(
			CreateProfileForNewCustomerResponse response) {
		CreateProfileForNewCustomerAdapterResponse resp = new CreateProfileForNewCustomerAdapterResponse();
		AdapterResponseMessage respMessage = new AdapterResponseMessage();
		respMessage.setDateTimeStamp(response.getDateTimeStamp());
		respMessage.setMessageCode(response.getErrorCode());
		respMessage.setMessageType(response.getMessageType());
		respMessage.setTransactionId(response.getTransactionId());
		respMessage.setMesssageDescriptionTextList(transformMessageList(response.getMessageList()));
		respMessage.setContextData(response.getContextData());
		resp.setAdapterResponseMessage(respMessage);
		return resp;
	}

	private static List<AdapterResponseMessageDesc> transformMessageList(List<Message> messageList) {
		List<AdapterResponseMessageDesc> msgList = new ArrayList<AdapterResponseMessageDesc>();
		for (Message message : messageList){
			AdapterResponseMessageDesc desc = new AdapterResponseMessageDesc();
			desc.setLocale(LocaleUtils.toLocale(message.getLocale()));
			desc.setMessageDescriptionText(message.getMessage());
			msgList.add(desc);
		}
		return msgList;
	}

}
