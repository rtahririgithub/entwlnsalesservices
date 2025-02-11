package com.telus.csm.ewlnssproxy.rest.transformer;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnssproxy.cpdsb.domain.Message;

public class GetReferencePDSMapTransformer extends BaseTransformer{
	public static List<Message> transformResponseMessage(List<String> messageList,String errorCode) {		
		List<Message> responseMessageList = new ArrayList<Message>();
		for(String message: messageList){
			Message responseMsg = new Message();
			responseMsg.setCode(errorCode);
			responseMsg.setType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
			responseMsg.setText(message);
			responseMessageList.add(responseMsg);
		}
		return responseMessageList;
	}
}
