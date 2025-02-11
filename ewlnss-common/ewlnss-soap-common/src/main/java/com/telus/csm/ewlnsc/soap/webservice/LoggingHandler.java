package com.telus.csm.ewlnsc.soap.webservice;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.util.StringUtils;

import com.telus.csm.ewlnsc.util.ExecutionLogUtil;

/**
 * This class logs out the web services SOAP messages.
 *
 * Register this to the web service as one of its handlers,
 * All messages passing this service will be logged out.
 *
 * @author cbrown
 *
 */
public class LoggingHandler implements javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {
	
	/** the logger. */
	static Logger logger = Logger.getLogger(LoggingHandler.class);

    /** constant for incoming message. */
    private static final String IN = "Incoming: ";

    /** constant for outgoing message. */
    private static final String OUT = "Outgoing: ";
    
    /** constant for fault message. */
    private static final String FAULT = "Fault: ";

    @Override  
    public Set<QName> getHeaders() {  
    	return null;  
    }  
      
    @Override  
    public void close(final MessageContext context) {
    	// 
    }  
      
    @Override  
    public boolean handleFault(final SOAPMessageContext context) {  
    	log(context, FAULT);  
    	return true;  
    }  
      
    @Override  
    public boolean handleMessage(final SOAPMessageContext context) {  
    	final Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);  
    	String messageType;
    	if (outboundProperty.booleanValue()) {
    		messageType = OUT;
//    		AbstractRepository.logCacheStat(LOGGER)
    	} else {
    		initLogContext(context);
    		messageType = IN;
//    		AbstractRepository.clearCacheStat()
    	}
    	log(context, messageType);
    	return true;  
    }

    /*
     * this method inspect the input soap message to extract the operation name and transaction ID for logging 
     */
	private static void initLogContext(SOAPMessageContext context) {
		
		try {

			ExecutionLogUtil.initThreadLog();

	    	SOAPMessage soapMessage = context.getMessage();

			if (soapMessage == null || soapMessage.getSOAPBody() == null)
				return;

			Iterator<?> childrenOfBody = soapMessage.getSOAPBody().getChildElements();
			while (childrenOfBody.hasNext()) {
				Object childOfBody = childrenOfBody.next();
				if (childOfBody instanceof SOAPElement) {
					//The first node of BODY is the operation
					SOAPElement operationElement = (SOAPElement) childOfBody;
					String operationName = operationElement.getLocalName();
					if (!StringUtils.isEmpty(operationName)) {
						MDC.put("operation", operationName);
											
						//look for operationHeader under the operation
						Iterator<?> childrenOfOperation = operationElement.getChildElements();
						while (childrenOfOperation.hasNext()) {
							Object childOfOperation = childrenOfOperation.next();
							if (childOfOperation instanceof SOAPElement) {
								SOAPElement headerElement = (SOAPElement) childOfOperation;
								if ("operationHeader".equals(headerElement.getLocalName())) {
									
									//look for salesTransactionId under the operationHeader
									Iterator<?> childrenOfHeader = headerElement.getChildElements();
									while (childrenOfHeader.hasNext()) {
										Object childOfHeader = childrenOfHeader.next();
										if (childOfHeader instanceof SOAPElement) {
											SOAPElement tranIdElement = (SOAPElement) childOfHeader;
											if ("salesTransactionId".equals(tranIdElement.getLocalName())) {
												String transactionId = tranIdElement.getTextContent().trim();
												if (!StringUtils.isEmpty(transactionId)) {
													MDC.put("transactionId", " Tran:" + transactionId);
												}
											} else if ("salesId".equals(tranIdElement.getLocalName())) {
												String salesId = tranIdElement.getTextContent().trim();
												if (!StringUtils.isEmpty(salesId)) {
													MDC.put("salesId", " Sales:" + salesId);
												}												
											}
										}
									}
									break;
								}
							}
						}
						break;
					}
				}
			} 
			
			
    	} catch(Exception e) {
    		logger.error(e);
    	}
		
	}

    /**
     * Log the Fault message.
     * @param context
     */
    private void log(final SOAPMessageContext context, final String messageType) {

    	final SOAPMessage soapMessage = context.getMessage();

    	final ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
    	final PrintStream pStream = new PrintStream(byteOS);

    	try {
    		soapMessage.writeTo(pStream);
    		if (FAULT.equals(messageType)) {
    			logger.error(messageType + byteOS.toString("UTF8"));
    		} else {
    			logger.info(messageType + byteOS.toString("UTF8").replaceAll("(\\r|\\n)", ""));
    		}
    	} catch (Exception e) {
    		logger.error(stack2string(e));
    	}
    }
    
    /**
     * Convert exception stack trace to a string.
     * @param thr
     * @return
     */
	private static String stack2string(final Throwable thr) {
		final StringWriter swr = new StringWriter();
		final PrintWriter pwr = new PrintWriter(swr);
		thr.printStackTrace(pwr);
		return swr.toString();
	}

}
