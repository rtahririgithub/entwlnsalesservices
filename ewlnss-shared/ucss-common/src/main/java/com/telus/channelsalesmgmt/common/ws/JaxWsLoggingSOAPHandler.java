package com.telus.channelsalesmgmt.common.ws;

import static com.telus.channelsalesmgmt.common.monitoring.ResourceMonitor.resource_log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class logs out the web services SOAP messages.
 * 
 * Register this to the web service as one of its handlers, All messages passing
 * this service will be logged out.
 */
public class JaxWsLoggingSOAPHandler implements SOAPHandler<SOAPMessageContext> {
    private final Log log = LogFactory.getLog(JaxWsLoggingSOAPHandler.class);
    
	public JaxWsLoggingSOAPHandler() {
		log.warn("JaxWsLoggingSOAPHandler is instantiated");
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		log.warn("JaxWsLoggingSOAPHandler handleMessage begin");
		
		try {
			if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
				log(context, "Outbound Message");
			} else {
				log(context, "Inbound Message");
			}
		} catch (RuntimeException e) {
			log.error("JaxWsLoggingSOAPHandler exception", e);
			throw e;
		} catch (Error e) {
			log.error("JaxWsLoggingSOAPHandler exception", e);
			throw e;
		} catch (Throwable t) {
			log.error("JaxWsLoggingSOAPHandler exception", t);
		} finally {
			log.warn("JaxWsLoggingSOAPHandler handleMessage end");
		}
		
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
			log(context, "Outbound Fault");
		} else {
			log(context, "Inbound Fault");
		}

		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	private void log(SOAPMessageContext soapCtx, String messageType) {
		SOAPMessage soapMessage = soapCtx.getMessage();

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);

		try {
			// logger.debug(messageType + ": \n");
			soapMessage.writeTo(ps);
			resource_log.info(messageType + ": \n" + os.toString("UTF8") + "\n");
			// logger.debug("\n");
		} catch (Exception e) {
		    resource_log.error("soap logging error", e);
		}
	}

}
