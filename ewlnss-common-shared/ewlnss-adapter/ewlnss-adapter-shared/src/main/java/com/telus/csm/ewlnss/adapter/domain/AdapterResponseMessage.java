package com.telus.csm.ewlnss.adapter.domain;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.ESS_ERROR_CODE_UNKNOWN_ERROR;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
/**
 * @author x159015
 * 
 */
public class AdapterResponseMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * this is the unique messageCode defined in EnterpriseSalesServiceConstants
	 */
	private String messageCode;

	/**
	 * Input Transaction Id
	 */
	private String transactionId;

	/**
	 * date and time when this object was created
	 */
	private Date timestamp;

	/**
	 * this is one of the message types defined in
	 * EnterpriseSalesServiceConstants
	 * 
	 */
	private String messageType;

	/**
	 * this will include any useful data to be included with the message. the
	 * data included should be helpful for debugging purpose.
	 */
	private String contextData;

	/**
	 * this is a list of Locale specific message description text
	 */
	private List<AdapterResponseMessageDesc> messsageDescriptionTextList;

	/**
	 * this is a Locale specific message description text
	 */
	private String localeSpecificMessageDescText;

	/**
	 * any exception associated with this object
	 */
	private Exception exception;
	
	/**
	 * this is a list of argument passed to the message description
	 */
	private String[] arguments;
	
	private Date dateTimeStamp;
	
	private String validationOrderAction;
	
	private List<String> problematicSocs;
	
	private List<AdapterResponseRelatedMessage> relatedMessageList = new ArrayList<AdapterResponseRelatedMessage>();
	
	//QC56344
	private String subMessageCode;
	
	/**
	 * 
	 */
	public AdapterResponseMessage(){
		this.timestamp = new Date();
	}
	
	/**
	 * 
	 * @param exception
	 * @param contextData
	 * @param transactionId
	 */
	public AdapterResponseMessage(Exception exception, String contextData, String transactionId){
		this(exception, ESS_ERROR_CODE_UNKNOWN_ERROR, contextData, transactionId, MESSAGE_TYPE_ERROR);
	}
	
	/**
	 * 
	 * @param messageCode
	 * @param contextData
	 * @param transactionId
	 */
	public AdapterResponseMessage(String messageCode,String contextData, String transactionId){
		
		this(messageCode, contextData, transactionId, MESSAGE_TYPE_ERROR);
	}
	
	/**
	 * 
	 * @param exception
	 * @param exceptionCode
	 * @param contextData
	 * @param transactionId
	 */
	public AdapterResponseMessage(Exception exception, String messageCode, String contextData, String transactionId){
		this(exception, messageCode, contextData, transactionId, null);
	}
	
	/**
	 * 
	 * @param exception
	 * @param exceptionCode
	 * @param contextData
	 * @param transactionId
	 * @param messageType
	 */
	public AdapterResponseMessage(Exception exception, String messageCode, String contextData, String transactionId, String messageType){
		this.contextData = contextData;
		this.messageCode = messageCode;
		this.transactionId = transactionId;
		this.exception = exception;
		this.messageType = messageType;
		this.timestamp = new Date();
	}
	
	/**
	 * 
	 * @param messageCode
	 * @param contextData
	 * @param transactionId
	 */
	public AdapterResponseMessage(String messageCode,String contextData, String transactionId, String messageType){
		this.messageCode = messageCode;
		this.contextData =  contextData;
		this.transactionId = transactionId;
		this.messageType = messageType;
		this.timestamp = new Date();
	}

	public AdapterResponseMessage(String messageCode, String messageType){
		this.messageCode = messageCode;
		this.messageType = messageType;
		this.timestamp = new Date();
	}

	/**
	 * 
	 * @param locale - currently supported Locale.CANADA and Locale.CANADA_FRENCH
	 * @return String - locale specific message description text
	 */
	public String getLocaleSpecificMessageDescText(Locale locale) {
		List<AdapterResponseMessageDesc> msgDescTextList = getMesssageDescriptionTextList();
		if(msgDescTextList != null && !msgDescTextList.isEmpty()){
			for(AdapterResponseMessageDesc msgDescDO : msgDescTextList){
				if (msgDescDO!=null && locale.getLanguage().equals(msgDescDO.getLocale().getLanguage())) {
					if (StringUtils.isEmpty(transactionId)) {
						return msgDescDO.getMessageDescriptionText();
					} else {
						return msgDescDO.getMessageDescriptionText() + " [salesTransactionId=" + transactionId + "]";
					}
				}
			}
		}
		return null;
	}
	
	public String getSubMessageCode() {
		return subMessageCode;
	}
	public void setSubMessageCode(String messageCode) {
		this.subMessageCode = messageCode;
	}

	
	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode
	 *            the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType
	 *            the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the contextData
	 */
	public String getContextData() {
		return contextData;
	}

	/**
	 * @param contextData
	 *            the contextData to set
	 */
	public void setContextData(String contextData) {
		this.contextData = contextData;
	}

	/**
	 * @return the arguments
	 */
	public String[] getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the messsageDescriptionTextList
	 */
	public List<AdapterResponseMessageDesc> getMesssageDescriptionTextList() {
		return messsageDescriptionTextList;
	}

	/**
	 * @param messsageDescriptionTextList
	 *            the messsageDescriptionTextList to set
	 */
	public void setMesssageDescriptionTextList(
			List<AdapterResponseMessageDesc> messsageDescriptionTextList) {
		this.messsageDescriptionTextList = messsageDescriptionTextList;
	}
	
	public void addMessageDescDO(AdapterResponseMessageDesc messageDescDO){
		if(this.messsageDescriptionTextList != null){
			messsageDescriptionTextList.add(messageDescDO);
		}else{
			this.messsageDescriptionTextList = new ArrayList<AdapterResponseMessageDesc>();
			this.messsageDescriptionTextList.add(messageDescDO);
		}
		
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	
	/**
	 * @return the dateTimeStamp
	 */
	public Date getDateTimeStamp() {
		return dateTimeStamp;
	}

	/**
	 * @param dateTimeStamp the dateTimeStamp to set
	 */
	public void setDateTimeStamp(Date dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		if (messsageDescriptionTextList != null){
			for (AdapterResponseMessageDesc msgDesc : messsageDescriptionTextList){
				sb.append("[locale=" + msgDesc.getLocale().getCountry() + " msg=" + msgDesc.getMessageDescriptionText() + "]");
			}
		}
		
		
		return "MessageDO [messageCode=" + messageCode + ", transactionId="
				+ transactionId + ", timestamp=" + timestamp + ", messageType="
				+ messageType + ", contextData=" + contextData
				+ ", messsageDescriptionTextList="
				+ sb.toString()
				+ ", localeSpecificMessageDescText="
				+ localeSpecificMessageDescText + ", exception=" + exception
				+ "]";
	}
	/**
	 * cd .
	 * @return
	 */
	public String getValidationOrderAction() {
		return validationOrderAction;
	}
	/**
	 * 
	 * @param validationOrderAction
	 */
	public void setValidationOrderAction(String validationOrderAction) {
		this.validationOrderAction = validationOrderAction;
	}
	/**
	 * 
	 * @return
	 */
	public List<String> getProblematicSocs() {
		return problematicSocs;
	}
	/**
	 * 
	 * @param problematicSocs
	 */

	public void setProblematicSocs(List<String> problematicSocs) {
		this.problematicSocs = problematicSocs;
	}
	public List<AdapterResponseRelatedMessage> getRelatedMessageList() {
		return relatedMessageList;
	}
	public void setRelatedMessageList(List<AdapterResponseRelatedMessage> relatedMessageList) {
		this.relatedMessageList = relatedMessageList;
	}
}
