package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseStatusRestVO {
	private int statusCd;
	private String statusTxt;
	private String systemTimeStamp;
	private String systemMessageCd;
	private String systemMessageTxt;
	//QC-62330
//	private String transactionId;
	private String correlationId;
	private StackTraceElement[] stackTrace;
	private String systemErrorId;
	private List<ValidationMessageVO> messages;

	public String getSystemTimeStamp() {
		return systemTimeStamp;
	}
	public void setSystemTimeStamp(String systemTimeStamp) {
		this.systemTimeStamp = systemTimeStamp;
	}

	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
//	public String getTransactionId() {
//		return transactionId;
//	}
//	public void setTransactionId(String transactionId) {
//		this.transactionId = transactionId;
//	}
	public int getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(int statusCd) {
		this.statusCd = statusCd;
	}
	public String getStatusTxt() {
		return statusTxt;
	}
	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}
	public String getSystemMessageCd() {
		return systemMessageCd;
	}
	public void setSystemMessageCd(String systemMessageCd) {
		this.systemMessageCd = systemMessageCd;
	}
	public String getSystemMessageTxt() {
		return systemMessageTxt;
	}
	public void setSystemMessageTxt(String systemMessageTxt) {
		this.systemMessageTxt = systemMessageTxt;
	}
	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}
	public String getSystemErrorId() {
		return systemErrorId;
	}
	public void setSystemErrorId(String systemErrorId) {
		this.systemErrorId = systemErrorId;
	}
	public List<ValidationMessageVO> getMessages() {
		return messages;
	}
	public void setMessages(List<ValidationMessageVO> messageList) {
		this.messages = messageList;
	}

	public void addMessage(ValidationMessageVO message) {
		if (this.messages == null) {
			this.messages = new ArrayList<ValidationMessageVO>();
		}
		this.messages.add(message);
	}
}
