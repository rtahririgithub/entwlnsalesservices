package com.telus.channelsalesmgmt.common.handler.soap;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.telus.channelsalesmgmt.common.util.LoggerUtil;

public class SoapLoggingHandler extends GenericHandler {
	static Logger logger = Logger.getLogger(SoapLoggingHandler.class);
	
	private HandlerInfo handlerInfo;
	private Transformer tf = null;
	private boolean logRequest = true;
	private boolean logResponse = true;
	private boolean logFault = true;
	
	public void init(HandlerInfo hi) {
		handlerInfo = hi;
		Map configMap = hi.getHandlerConfig();
		if( configMap != null ) {
			String logRequestStr = (String)configMap.get("logRequest");
			if( logRequestStr != null && "false".equalsIgnoreCase(logRequestStr) ) logRequest = false;
			String logResponseStr = (String)configMap.get("logResponse");
			if( logResponseStr != null && "false".equalsIgnoreCase(logResponseStr) ) logResponse = false;
			String logFaultStr = (String)configMap.get("logFault");
			if( logFaultStr != null && "false".equalsIgnoreCase(logFaultStr) ) logFault = false;
		}
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			tff.setAttribute("indent-number", new Integer(2));
			tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch(Exception ex) {}
	}
		  
	/**
	 * Handles incoming web service requests and outgoing callback requests
	 */
	public boolean handleRequest(MessageContext mc) {
		if( logRequest && logger.isEnabledFor(Priority.INFO) ) {
			LoggerUtil.getLogger(this).info("handleRequest", logSoapMessage(mc));
		}
		return true;
	}

	/**
	 * Handles outgoing web service responses and incoming callback responses
	 */
	public boolean handleResponse(MessageContext mc) {
		if( logResponse && logger.isDebugEnabled() ) {
			LoggerUtil.getLogger(this).debug("handleResponse", logSoapMessage(mc));
		}
		return true;
	}

	/**
	 * Handles SOAP Faults that may occur during message processing
	 */
	public boolean handleFault(MessageContext mc) {
		if( logFault ) {
			LoggerUtil.getLogger(this).error("-1", "handleFault", logSoapMessage(mc));
		}
		return true;
	}

	/**
	 * Log the message to the server console using System.out
	 */
	protected String logSoapMessage(MessageContext mc) {
		String logMsg = null;
		StringWriter out = null;
		try {
			SOAPMessageContext soapCtx = (SOAPMessageContext)mc;
			SOAPMessage soapMessage = soapCtx.getMessage();
			out = new StringWriter();
			Source sc = soapMessage.getSOAPPart().getContent();
			StreamResult result = new StreamResult(out);
			tf.transform(sc, result);
			logMsg = out.toString();
		} catch (Exception ex) {			
		} finally {
			if( out != null ) {
				try {
					out.close();
				} catch (Exception ex) {}
			}
		}
		return logMsg;
	}
	
	public QName[] getHeaders() {
		return handlerInfo.getHeaders();
	}
}
