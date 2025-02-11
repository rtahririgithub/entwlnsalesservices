package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

/**
 * Base class for all Core Response classes.
 * 
 * @author x145592
 *
 */
public abstract class CoreResponseBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean msgHasError = false;
	private boolean msgHasWarning = false;
	private boolean msgHasMandatory = false;
	private String errorCode = null;

	private String transactionId;

	private List<SalesResponseMessage.MessageList> messageList;

	public List<SalesResponseMessage.MessageList> getMessageList() {
		if (messageList == null){
			messageList = new ArrayList<SalesResponseMessage.MessageList>();
		}
		return messageList;
	}

	public void setMessageList(List<SalesResponseMessage.MessageList> messageList) {
		for (SalesResponseMessage.MessageList msg : messageList) {
			this.addMsg(msg);
		}
		// this.messageList = messageList;
	}

	public void addMessageList(List<SalesResponseMessage.MessageList> messageList) {
		for (SalesResponseMessage.MessageList msg : messageList) {
			this.addMsg(msg);
		}
	}

	public void addMsg(SalesResponseMessage.MessageList msg) {
		getMessageList().add(msg);
		if (EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR.equals(msg.getMessageType())) {
			msgHasError = true;
		}

		if (EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION.equals(msg.getMessageType())) {
			msgHasError = true;
		}

		if (EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING.equals(msg.getMessageType())) {
			msgHasWarning = true;
			if (EnterpriseWLNSalesServicesConstants.VALIDATION_MANDATORY.equals(msg.getErrorCode())) {
				msgHasMandatory = true;
			}
		}
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean hasError() {
		return this.msgHasError;
	}

	public boolean hasWarning() {
		return this.msgHasWarning;
	}

	public boolean hasMandatory() {
		return this.msgHasMandatory;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isMsgHasError() {
		return msgHasError;
	}

	public void setMsgHasError(boolean msgHasError) {
		this.msgHasError = msgHasError;
	}

	public boolean isMsgHasWarning() {
		return msgHasWarning;
	}

	public void setMsgHasWarning(boolean msgHasWarning) {
		this.msgHasWarning = msgHasWarning;
	}

	public boolean isMsgHasMandatory() {
		return msgHasMandatory;
	}

	public void setMsgHasMandatory(boolean msgHasMandatory) {
		this.msgHasMandatory = msgHasMandatory;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public boolean hasErrorCode(String errorCode) {
		boolean hasErrorCode = false;
		for (int i = 0; (messageList != null) && (i < messageList.size()); i++) {
			MessageDO messageDO = transformMsgListToMessageDO(messageList.get(i));
			if (errorCode.equals(messageDO.getMessageCode())) {
				hasErrorCode = true;
			}
		}
		return hasErrorCode;
	}

	private MessageDO transformMsgListToMessageDO(MessageList msgList) {
		MessageDO msg = new MessageDO();
		msg.setMessageCode(msgList.getErrorCode());
		msg.setTransactionId(msgList.getTransactionId());
		msg.setTimestamp(msgList.getDateTimeStamp());
		msg.setMessageType(msgList.getMessageType());
		msg.setContextData(msgList.getContextData());
		msg.setMesssageDescriptionTextList(transformMessageList(msgList.getMessageList()));

		return msg;
	}

	private List<MessageDescDO> transformMessageList(List<Message> message) {
		List<MessageDescDO> msgDescDOList = new ArrayList<MessageDescDO>();
		for (Message msg : message) {
			MessageDescDO msgDO = new MessageDescDO();
			msgDO.setLocale(new Locale(msg.getLocale()));
			msgDO.setMessageDescriptionText(msg.getMessage());
			msgDescDOList.add(msgDO);
		}
		return msgDescDOList;
	}

}
