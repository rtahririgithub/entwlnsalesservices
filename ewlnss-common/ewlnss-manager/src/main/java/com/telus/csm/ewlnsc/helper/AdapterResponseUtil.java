package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseRelatedMessage;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class AdapterResponseUtil {

	public static void propagateMessage(AdapterResponseBase respDO, List<MessageList> messageList) {
		
		if (respDO == null || respDO.getAdapterResponseMessage() == null || respDO.getAdapterResponseMessage().getMesssageDescriptionTextList() == null || respDO.getAdapterResponseMessage().getMesssageDescriptionTextList().isEmpty())
			return;
		
		
		messageList.addAll(transformMsgList(respDO.getAdapterResponseMessage()));
		return;
		
		
//			private MessageList transform(AdapterResponseMessage messageDO) {
//			MessageList msg = new MessageList();
//			msg.setContextData(messageDO.getContextData());
//			msg.setDateTimeStamp(messageDO.getDateTimeStamp());
//			msg.setErrorCode(messageDO.getMessageCode());
//			msg.setMessageList(transformMsgList(messageDO.getMesssageDescriptionTextList()));
//			msg.setMessageType(messageDO.getMessageType());
//			msg.setTransactionId(messageDO.getTransactionId());
//			return msg;
//		}

//		private List<Message> transformMsgList(List<AdapterResponseMessageDesc> messsageDescriptionTextList) {
//			List<Message> msgList = new ArrayList<Message>();
//			for (AdapterResponseMessageDesc msgDescDO : messsageDescriptionTextList){
//				Message msg = new Message();
//				msg.setLocale(msgDescDO.getLocale().toString());
//				msg.setMessage(msgDescDO.getMessageDescriptionText());
//				msgList.add(msg);
//			}
//			return msgList;
//		}
	}

	private static Collection<? extends MessageList> transformMsgList(AdapterResponseMessage messageDO) {

		ArrayList<MessageList> result = new ArrayList<MessageList>();
		
		MessageList msg = new MessageList();
		msg.setContextData(messageDO.getContextData());
		msg.setDateTimeStamp(messageDO.getDateTimeStamp());
		msg.setErrorCode(messageDO.getMessageCode());
		msg.setMessageList(transformMsgList(messageDO.getMesssageDescriptionTextList()));
		msg.setMessageType(messageDO.getMessageType());
		msg.setTransactionId(messageDO.getTransactionId());
		// Populate related message list if any
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
		if (!messageDO.getRelatedMessageList().isEmpty()){
			for (AdapterResponseRelatedMessage rMsg : messageDO.getRelatedMessageList()){
				RelatedMessage relatedMsg = new RelatedMessage();
				relatedMsg.setRelatedErrorCd(rMsg.getRelatedErrorCd());
				relatedMsg.setRelatedErrorTypeCd(rMsg.getRelatedErrorTypeCd());
				relatedMsg.setRelatedErrorMessageTxt(rMsg.getRelatedErrorMessageTxt());
				relatedMessageList.add(relatedMsg);
			}
			msg.setRelatedMessageList(relatedMessageList);
		}
		result.add(msg);
		return result;
	}

	private static List<Message> transformMsgList(List<AdapterResponseMessageDesc> messsageDescriptionTextList) {
		List<Message> msgList = new ArrayList<Message>();
		for (AdapterResponseMessageDesc msgDescDO : messsageDescriptionTextList){
			Message msg = new Message();
			msg.setLocale(msgDescDO.getLocale().toString());
			msg.setMessage(msgDescDO.getMessageDescriptionText());
			msgList.add(msg);
		}
	return msgList;
	}

}
