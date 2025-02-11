package com.telus.csm.ewlnssproxy.rest.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.ValidationMessage;
import com.telus.csm.ewlnssproxy.cpib.domain.Message;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo;


public class GetCustomerCreditProfileTransformer extends BaseTransformer {

	private static Logger logger = Logger.getLogger(GetCustomerCreditProfileTransformer.class);
	
	public static GetCreditProfileByCustomerIdAdapterRequest transform (String transactionId, String customerId, Boolean refreshCache, AuditInfo auditInfo) {
		GetCreditProfileByCustomerIdAdapterRequest request = new GetCreditProfileByCustomerIdAdapterRequest();
		
		request.setSalesTransactionId(transactionId);
		request.setCustomerId(customerId);
		request.setRefreshCache(refreshCache);
		request.setAuditInfo(auditInfo);

		return request;
	}
	
	public static List<Message> transformMessage(List<ValidationMessage> messages) {
		List<Message> msgList = null;
		if (messages != null && messages.size() > 0) {

			msgList = new ArrayList<Message>();

			for (ValidationMessage vMessage : messages) {
				Message msg = new Message();
				msg.setCode(vMessage.getMessageCd());
				msg.setText(vMessage.getMessageTxt());
				msgList.add(msg);
			}
		}
		return msgList;
	}
	
	
	public static String customerCreditProfileTransformResponse(GetCreditProfileByCustomerIdAdapterResponse response, String dateFormat) throws Exception {
		return replaceValue(transformResponseWithDateFormat(response.getCreditProfile(), dateFormat), "birthDate");
	}
	
    public static String replaceValue( String json, String field ) {
    	int lastIndex = 0;
    	List<String> oldValueList = new ArrayList<String>(0);

    	while(lastIndex != -1){
    	    lastIndex = json.indexOf(field, lastIndex);

    	    if(lastIndex != -1){
    	        lastIndex += field.length()+3;
    	        int indexOf = json.indexOf("\"", lastIndex);
				String substring = json.substring(lastIndex, indexOf);
				oldValueList.add(substring);
    	    }
    	}
    	
    	for (String s : oldValueList) 
    		json = StringUtils.replace(json, s, s.substring(0, s.indexOf("T")));
    	
    	return json;
    }

}
