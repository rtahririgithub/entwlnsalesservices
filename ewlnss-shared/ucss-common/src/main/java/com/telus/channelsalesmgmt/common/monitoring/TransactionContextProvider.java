package com.telus.channelsalesmgmt.common.monitoring;

import javax.xml.ws.handler.soap.SOAPMessageContext;

public interface TransactionContextProvider {

	String getTransactionContext(SOAPMessageContext context);

}
