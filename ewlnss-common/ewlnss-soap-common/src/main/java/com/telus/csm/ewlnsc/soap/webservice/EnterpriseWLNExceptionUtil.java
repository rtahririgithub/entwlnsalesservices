package com.telus.csm.ewlnsc.soap.webservice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;


public class EnterpriseWLNExceptionUtil {

	private static LoggerUtil logger = LoggerUtil.getLogger(EnterpriseWLNExceptionUtil.class);
	
	public static SalesResponseMessage.MessageList getExceptionDetails(String transactionId,Throwable ex,String operationPrefixCd){
		String functionName = "getExceptionDetails";
		logger.enter(functionName);
		final StringWriter swr = new StringWriter();
		final PrintWriter pwr = new PrintWriter(swr);
		ex.printStackTrace(pwr);
		
		SalesResponseMessage.MessageList msg = new SalesResponseMessage.MessageList();
		msg.setContextData(swr.toString());
		msg.setDateTimeStamp(new Timestamp(new Date().getTime()));
		msg.setErrorCode(operationPrefixCd+EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR);
		msg.setTransactionId(transactionId);
		Message localizedMsg = new Message();
		localizedMsg.setMessage(ex.getLocalizedMessage());
		localizedMsg.setLocale(Constants.LANG_EN);
		msg.setMessageList(Arrays.asList(localizedMsg));
		msg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION); 
		logger.exit(functionName);
		return msg;
	}

}
