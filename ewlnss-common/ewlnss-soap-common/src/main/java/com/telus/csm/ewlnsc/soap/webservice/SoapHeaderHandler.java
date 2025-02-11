package com.telus.csm.ewlnsc.soap.webservice;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;
import org.w3c.dom.Comment;

import com.telus.csm.ewlnsc.util.App;

/**
 * This is the same implementation in SOS, but converted to JAX-WS (from JAX-RPC)
 * 
 * @author Chris Brown
 *
 */
public class SoapHeaderHandler implements javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

	public static final Logger logger = Logger.getLogger(SoapHeaderHandler.class);	

	@Override  
    public Set<QName> getHeaders() {  
    	return null;  
    }  
      
    @Override  
    public void close(MessageContext context) {  
    }
    
    @Override  
    public boolean handleFault(SOAPMessageContext context) {
		addWatermark(context);
    	return true;  
    }

	private void addWatermark(SOAPMessageContext context) {
		try {
			SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			
			XMLGregorianCalendar ts = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());

            Comment comment = envelope.getOwnerDocument().createComment("Watermark: " + ts + "; " + App.getWatermark());
            body.insertBefore(comment, body.getFirstChild());

		} catch (SOAPException e) {
			logger.error("Error adding Watermark.", e);
		} catch (DatatypeConfigurationException e) {
			logger.error("Error adding Watermark.", e);
		}

	}
	
	@Override  
    public boolean handleMessage(SOAPMessageContext context) {

    	Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);  
    	if (outboundProperty.booleanValue()) {
    		addWatermark(context);
    	}

    	return true;

	}

}
