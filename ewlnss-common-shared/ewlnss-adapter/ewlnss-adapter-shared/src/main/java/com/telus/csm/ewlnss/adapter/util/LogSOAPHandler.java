package com.telus.csm.ewlnss.adapter.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;

/**
 * This class logs out the web services SOAP messages.
 *
 * Register this to the web service as one of its handlers,
 * All messages passing this service will be logged out.
 *
 * @author cbrown
 *
 */
public class LogSOAPHandler implements javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

    private static ThreadLocal<Long> requestTime = new ThreadLocal<Long>();
	/** the logger. */
    public static final Logger LOGGER = Logger.getLogger(LogSOAPHandler.class);
    
    @Override
    public void close(final MessageContext messagecontext) {
    	//
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(final SOAPMessageContext messageContext) {
    	log(messageContext, "Fault", 0);
        return true;
    }

    /**
     * Handle message.
     */
    @Override
	public boolean handleMessage(final SOAPMessageContext messageContext) {
		final Boolean outbound = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
        	log(messageContext, "Request", 0);
        	requestTime.set(System.currentTimeMillis());
        } else {
        	Long reqTime = requestTime.get();
        	String elapsedTimeStr = "";
        	if(reqTime != null) {
        		long elapsedTm = System.currentTimeMillis() - reqTime;
        		elapsedTimeStr = "DS elapse: " + elapsedTm + " ms, ";
        	}
        	log(messageContext, elapsedTimeStr + "Response", 5000);
        	requestTime.set(null);
        }
        return true;
    }
	
	/**
	 * Log a message.
	 * @param context
	 * @param messageType
	 * @param maxLength
	 */
    private void log(final MessageContext context, final String messageType, final int maxLength) {
    	long logStart = System.currentTimeMillis();
    	final SOAPMessageContext soapCtx = (SOAPMessageContext) context;
    	final SOAPMessage soapMessage = soapCtx.getMessage();
    	final ByteArrayOutputStream outStream;
    	try {
    		String adapterName = AdapterLogUtil.getAdapterMethodName();
    		outStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outStream);
			outStream.close();
    		String msg = outStream.toString("UTF8");
    		if (maxLength > 0  && msg.length() > maxLength) {
    			msg = "Truncated to " + maxLength  + ": "+ msg.substring(0, maxLength);    			
    		}
    		long logElapse = System.currentTimeMillis() - logStart;
    		String logElapseStr = "Log elapse: " + logElapse + " ms, ";
    		if ("Fault".equals(messageType)) {
    			LOGGER.error(adapterName + logElapseStr + messageType + ": " + msg);
    		} else {
    			LOGGER.info(adapterName + logElapseStr + messageType + ": " + msg);
    		}
    	} catch (SOAPException e) {
    		LOGGER.error(stack2string(e));
    	} catch (IOException e){
    		LOGGER.error(stack2string(e));
    	} catch (IndexOutOfBoundsException e){
    		LOGGER.error(stack2string(e));
    	} catch (RuntimeException e){
    		LOGGER.error(stack2string(e));
    	}
    }
    
    /**
     * Convert a stack trace to a String.
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
