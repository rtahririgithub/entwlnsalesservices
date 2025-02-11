package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

/**
 * @author x159015
 * 
 *         Root response DO
 * 
 */
public abstract class ResponseDO implements Serializable {

	private String transactionId;
	
	public ResponseDO() {
	}
	
	// The cached responseDO need to be Serializable
	private static final long serialVersionUID = 1L;
	private List<MessageDO> msgList;
	private boolean msgHasError = false;
	private boolean msgHasWarning = false;
	private boolean msgHasMandatory = false;
	private boolean coherenceGridError = false;
	private String parallalExecutionToken;
	private long counter;
	private Date lastUpdateTS = null;
	private Date lastReadTS = null;

	public List<MessageDO> getMsgList() {
		if (msgList == null) {
			msgList = new ArrayList<MessageDO>();
		}
		return msgList;
	}

	public void addMessages(List<MessageDO> list) {
		for (MessageDO messageDO : list) {
			this.addMsg(messageDO);
		}
	}

	public void addMsg(MessageDO msg) {
		getMsgList().add(msg);
		if (msg.getMessageType().equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR)) {
			msgHasError = true;
		}

		if (msg.getMessageType().equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION)) {
			msgHasError = true;
		}

		if (msg.getMessageType().equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING)) {
			msgHasWarning = true;
			if (EnterpriseWLNSalesServicesConstants.VALIDATION_MANDATORY.equals(msg.getMessageCode())) {
				msgHasMandatory = true;
			}
		}
	}

	public boolean hasError() {
		return this.msgHasError;
	}

	/**
	 * @return the msgHasError
	 */
	public boolean isMsgHasError() {
		return msgHasError;
	}

	/**
	 * @param msgHasError
	 *            the msgHasError to set
	 */
	public void setMsgHasError(boolean msgHasError) {
		this.msgHasError = msgHasError;
	}

	/**
	 * @return the msgHasWarning
	 */
	public boolean isMsgHasWarning() {
		return msgHasWarning;
	}

	/**
	 * @param msgHasWarning
	 *            the msgHasWarning to set
	 */
	public void setMsgHasWarning(boolean msgHasWarning) {
		this.msgHasWarning = msgHasWarning;
	}

	/**
	 * @return the msgHasMandatory
	 */
	public boolean isMsgHasMandatory() {
		return msgHasMandatory;
	}

	/**
	 * @param msgHasMandatory
	 *            the msgHasMandatory to set
	 */
	public void setMsgHasMandatory(boolean msgHasMandatory) {
		this.msgHasMandatory = msgHasMandatory;
	}

	public boolean hasWarning() {
		return this.msgHasWarning;
	}

	public boolean hasMandatory() {
		return this.msgHasMandatory;
	}

	public boolean isCoherenceGridError() {
		return coherenceGridError;
	}

	public void setCoherenceGridError(boolean coherenceGridError) {
		this.coherenceGridError = coherenceGridError;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCacheKey() {
		return transactionId;
	}

	public boolean hasErrorCode(String errorCode) {
		boolean hasErrorCode = false;
		for (int i = 0; (msgList != null) && (i < msgList.size()); i++) {
			MessageDO messageDO = msgList.get(i);
			if (errorCode.equals(messageDO.getMessageCode())) {
				hasErrorCode = true;
			}
		}
		return hasErrorCode;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getParallalExecutionToken() {
		return parallalExecutionToken;
	}

	public void setParallalExecutionToken(String parallalExecutionToken) {
		this.parallalExecutionToken = parallalExecutionToken;
	}

	public void copyFrom(ResponseDO that) {
		this.coherenceGridError = that.coherenceGridError;
		this.msgHasError = that.msgHasError;
		this.msgHasMandatory = that.msgHasMandatory;
		this.msgHasWarning = that.msgHasWarning;
		this.msgList = that.msgList;
		this.parallalExecutionToken = that.parallalExecutionToken;
		if (this.transactionId == null) {
			this.transactionId = that.transactionId;
		}
	}

	/**
	 * @return the messageDOList
	 */
	public List<MessageDO> getMessageDOList() {
		return getMsgList();
	}

	/**
	 * @param messageDOList
	 *            the messageDOList to set
	 */
	public void setMessageDOList(List<MessageDO> messageDOList) {
		return;
	}

	public synchronized void addMessages(ResponseDO that) {
		if (that != null) {
			addMessages(that.getMsgList());
		}
	}

	public static String getEnglishText(Map<String, String> textMap) {
		if (textMap != null && textMap.isEmpty() == false) {
			for (Entry<String, String> entry : textMap.entrySet()) {
				if (EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE.equalsIgnoreCase(entry.getKey())) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	public void addInfoMessages(List<String> messsages) {

		if (messsages != null && messsages.isEmpty() == false) {

			StringBuilder sb = new StringBuilder();
			for (String message : messsages) {
				sb.append("  ").append(message).append("\n");
			}

			MessageDO messageDO = new MessageDO();
			messageDO.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO);
			messageDO.setContextData(sb.toString());
			getMsgList().add(messageDO);
		}
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public Date getLastUpdateTS() {
		return lastUpdateTS;
	}

	public void setLastUpdateTS(Date lastUpdateTS) {
		this.lastUpdateTS = lastUpdateTS;
	}

	public Date getLastReadTS() {
		return lastReadTS;
	}

	public void setLastReadTS(Date lastReadTS) {
		this.lastReadTS = lastReadTS;
	}

}
