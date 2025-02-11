package com.telus.csm.ewlnsc.rest.util;

import java.util.Locale;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsis.rest.domain.ContextAttribute;
import com.telus.csm.ewlnsis.rest.domain.MessageType;
import com.telus.csm.ewlnsis.rest.domain.ResponseMessage;

public class RESTResponseMessageUtil {

	public static String getResponseMessageJson(Exception e) {
		
		ResponseMessage x = new ResponseMessage();
		x.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SYSTEM_ERROR);
		x.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
		MessageType messagesItem = new MessageType();
		x.addMessagesItem(messagesItem);
		messagesItem.setLocaleCd(Locale.ENGLISH.toString());
		messagesItem.setMessageTxt("Unknown Exception");
		
		ContextAttribute contextDataAttributesItem = new ContextAttribute();
		x.addContextDataAttributesItem(contextDataAttributesItem);
		contextDataAttributesItem.setAttributeTypeTxt("Exception");
		contextDataAttributesItem.setAttributeValueTxt(ExceptionUtils.getFullStackTrace(e));
		
//		contextDataAttributesItem = new ContextAttribute();
//		x.addContextDataAttributesItem(contextDataAttributesItem);
//		contextDataAttributesItem.setAttributeTypeTxt("Watermark");
//		contextDataAttributesItem.setAttributeValueTxt(App.getWatermark());
		
		
		return JsonUtil.getJsonFromObjectNonNUll(x);
	}
	
}
