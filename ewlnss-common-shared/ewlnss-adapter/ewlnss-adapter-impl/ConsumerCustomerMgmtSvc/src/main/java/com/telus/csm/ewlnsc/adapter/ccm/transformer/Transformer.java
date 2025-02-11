package com.telus.csm.ewlnsc.adapter.ccm.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerPreferenceAdapterResponse;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.CreateCustomer;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.CreateCustomerResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.GetFullCustomerInfo;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.GetFullCustomerInfoResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.InsertCustomerPreference;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.InsertCustomerPreferenceResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateContactIndividual;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateContactIndividualResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateCustomerOfficialAddress;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateCustomerPreference;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateCustomerPreferenceResponse;

public class Transformer {
	
	private Transformer(){
		
	}

	public static CreateCustomer transform(final CreateNewCustomerAdapterRequest param) {
		final CreateCustomer result = new CreateCustomer();
		
		result.setCustomer(param.getCustomer());
		result.setCreditProfile(param.getCreditProfile());
		result.setCustomerContacts(param.getCustomerContactsList());
		result.setAuditInfo(param.getAuditInfo());
	    
	    return result;
	}

	public static CreateNewCustomerAdapterResponse transform(final CreateCustomerResponse param) {
		final CreateNewCustomerAdapterResponse result = new CreateNewCustomerAdapterResponse();
		
		result.setNewCustomer(param.getNewCustomer());
		result.setSuccessfulProcessInd(true);
		
		return result;
	}

	
	public static GetFullCustomerInfo transform(final GetFullCustomerInfoAdapterRequest param) {
		final GetFullCustomerInfo result = new GetFullCustomerInfo();
		
		result.setCustomerId(param.getCustomerId());
	    
	    return result;
	}

	public static GetFullCustomerInfoAdapterResponse transform(final GetFullCustomerInfoResponse param) {
		final GetFullCustomerInfoAdapterResponse result = new GetFullCustomerInfoAdapterResponse();
		
		result.setFullCustomer(param.getFullCustomer());
		
		return result;
	}

	
	public static UpdateContactIndividual transform(final UpdateContactIndividualAdapterRequest param) {
		final UpdateContactIndividual result = new UpdateContactIndividual();
		
		result.setCustomerContact(param.getCustomerContact());
		result.setAuditInfo(param.getAuditInfo());
		
		return result;
	}

	public static UpdateContactIndividualAdapterResponse transform(final UpdateContactIndividualResponse param) {
		final UpdateContactIndividualAdapterResponse result = new UpdateContactIndividualAdapterResponse();
		
		result.setCustomerContactIndividual(param.getCustomerContactIndividual());
		result.setEmailSyncSystemList(param.getEmailSyncSystemList());
		
		return result;
	}

	public static UpdateCustomerOfficialAddress transform(final UpdateCustomerOfficialAddressAdapterRequest param) {
		final UpdateCustomerOfficialAddress result = new UpdateCustomerOfficialAddress();
		
		result.setCustomerId(param.getCustomerId());
		result.setOfficialAddress(param.getOfficialAddress());
		result.setAuditInfo(param.getAuditInfo());
		
		return result;
	}

	public static UpdateCustomerOfficialAddressAdapterResponse transform() {
		return null;
	}

	public static InsertCustomerPreference transform(InsertCustomerPreferenceAdapterRequest request) {
		InsertCustomerPreference req = new InsertCustomerPreference();
		req.setAuditInfo(request.getAuditInfo());
		req.setCustomerPreference(request.getCustomerPreference());
		return req;
	}
	
	public static UpdateCustomerPreference transform(UpdateCustomerPreferenceAdapterRequest request) {
		UpdateCustomerPreference req = new UpdateCustomerPreference();
		req.setAuditInfo(request.getAuditInfo());
		req.setCustomerPreference(request.getCustomerPreference());
		return req;
	}

	public static InsertCustomerPreferenceAdapterResponse transform(InsertCustomerPreferenceResponse result) {
		InsertCustomerPreferenceAdapterResponse resp = new InsertCustomerPreferenceAdapterResponse();
		resp.setAdapterResponseMessage(buildResponseMessage(result));
		resp.setCustomerPreference(result.getCustomerPreference());
		resp.setMsgHasError(!StringUtils.isEmpty(result.getErrorCode()));
		return resp;
	}

	private static AdapterResponseMessage buildResponseMessage(InsertCustomerPreferenceResponse result) {
		AdapterResponseMessage responseMessage = new AdapterResponseMessage();
		if (!StringUtils.isEmpty(result.getErrorCode()) || result.getMessageList() != null){
			responseMessage.setDateTimeStamp(result.getDateTimeStamp());
			responseMessage.setMessageCode(result.getErrorCode());
			responseMessage.setMessageType(result.getMessageType());
			responseMessage.setTransactionId(result.getTransactionId());
			responseMessage.setMesssageDescriptionTextList(buildMessageDescriptionTextList(result));
			responseMessage.setContextData(result.getContextData());
		}
		return responseMessage;
	}

	public static UpdateCustomerPreferenceAdapterResponse transform(UpdateCustomerPreferenceResponse result) {
		UpdateCustomerPreferenceAdapterResponse resp = new UpdateCustomerPreferenceAdapterResponse();
		resp.setAdapterResponseMessage(buildResponseMessage(result));
		return resp;
	}

	private static AdapterResponseMessage buildResponseMessage(UpdateCustomerPreferenceResponse result) {
		AdapterResponseMessage responseMessage = new AdapterResponseMessage();
		if (!StringUtils.isEmpty(result.getErrorCode()) || result.getMessageList() != null){
			responseMessage.setDateTimeStamp(result.getDateTimeStamp());
			responseMessage.setMessageCode(result.getErrorCode());
			responseMessage.setMessageType(result.getMessageType());
			responseMessage.setTransactionId(result.getTransactionId());
			responseMessage.setMesssageDescriptionTextList(buildMessageDescriptionTextList(result));
			responseMessage.setContextData(result.getContextData());
		}
		return responseMessage;
	}

	
	private static List<AdapterResponseMessageDesc> buildMessageDescriptionTextList(
			InsertCustomerPreferenceResponse result) {
		List<AdapterResponseMessageDesc> messageDescList = new ArrayList<AdapterResponseMessageDesc>();
		AdapterResponseMessageDesc msgDesc = new AdapterResponseMessageDesc();
		if (result.getMessageList() != null){
			msgDesc.setLocale(new Locale(result.getMessageList().getLocale()));
			msgDesc.setMessageDescriptionText(result.getMessageList().getMessage());
			messageDescList.add(msgDesc);
		}
		return messageDescList;
	}

	private static List<AdapterResponseMessageDesc> buildMessageDescriptionTextList(
			UpdateCustomerPreferenceResponse result) {
		List<AdapterResponseMessageDesc> messageDescList = new ArrayList<AdapterResponseMessageDesc>();
		AdapterResponseMessageDesc msgDesc = new AdapterResponseMessageDesc();
		if (result.getMessageList() != null){
			msgDesc.setLocale(new Locale(result.getMessageList().getLocale()));
			msgDesc.setMessageDescriptionText(result.getMessageList().getMessage());
			messageDescList.add(msgDesc);
		}
		return messageDescList;
	}
}
