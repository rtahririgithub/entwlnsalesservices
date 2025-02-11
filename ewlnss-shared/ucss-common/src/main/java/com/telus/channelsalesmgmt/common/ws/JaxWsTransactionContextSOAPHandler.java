package com.telus.channelsalesmgmt.common.ws;

import java.util.Collections;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.telus.channelsalesmgmt.common.monitoring.ResourceMonitor;
import com.telus.channelsalesmgmt.common.monitoring.TransactionContextProvider;

public class JaxWsTransactionContextSOAPHandler implements SOAPHandler<SOAPMessageContext> {
	
	@Override
	public boolean handleMessage(SOAPMessageContext smc) {
		ServletContext sc = (ServletContext) smc.get(MessageContext.SERVLET_CONTEXT);
		BeanFactory bf = WebApplicationContextUtils.getWebApplicationContext(sc);
		ResourceMonitor resourceMonitor = bf.getBean(ResourceMonitor.class);
		
		if ((Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
			resourceMonitor.setTransactionContext(null);
		} else {
			TransactionContextProvider tcp = bf.getBean(TransactionContextProvider.class);
			String tc = tcp.getTransactionContext(smc);
			resourceMonitor.setTransactionContext(tc);
		}
		
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext smc) {
		ServletContext sc = (ServletContext) smc.get(MessageContext.SERVLET_CONTEXT);
		BeanFactory bf = WebApplicationContextUtils.getWebApplicationContext(sc);
		ResourceMonitor resourceMonitor = bf.getBean(ResourceMonitor.class);
		resourceMonitor.setTransactionContext(null);
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

}
