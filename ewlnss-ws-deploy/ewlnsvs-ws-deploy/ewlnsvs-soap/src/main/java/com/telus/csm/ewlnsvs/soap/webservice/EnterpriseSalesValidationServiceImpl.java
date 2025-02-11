package com.telus.csm.ewlnsvs.soap.webservice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.soap.webservice.EnterpriseWLNExceptionUtil;
import com.telus.csm.ewlnsc.util.App;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsvs.soap.operation.GetCustomerEligibilityOperation;
import com.telus.csm.ewlnsvs.ws.EnterpriseSalesValidationServicePortType;
import com.telus.csm.ewlnsvs.ws.ServiceException;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetFutureDatedSalesEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetFutureDatedSalesEligibilityType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetNumberPortInEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetNumberPortInEligibilityType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetSalesEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetSalesEligibilityType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetServiceEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetServiceEligibilityType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetSubscriberEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetSubscriberEligibilityType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.ValidatePendingTransactionForResumeResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.ValidatePendingTransactionForResumeType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.ValidateSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.ValidateSalesOrderItemResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.ValidateSalesOrderItemType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.ValidateSalesOrderResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.PingResponse;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class EnterpriseSalesValidationServiceImpl implements EnterpriseSalesValidationServicePortType {

	Logger logger = Logger.getLogger(EnterpriseSalesValidationServiceImpl.class);
	private static final Date startDate = new Date();
	
	@Override
	public GetSalesEligibilityResponseType getSalesEligibility(GetSalesEligibilityType parameters)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetSubscriberEligibilityResponseType getSubscriberEligibility(GetSubscriberEligibilityType parameters)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateSalesOrderResponse validateSalesOrder(ValidateSalesOrder parameters) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetNumberPortInEligibilityResponseType getNumberPortInEligibility(GetNumberPortInEligibilityType parameters)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetFutureDatedSalesEligibilityResponseType getFutureDatedSalesEligibility(
			GetFutureDatedSalesEligibilityType parameters) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidatePendingTransactionForResumeResponseType validatePendingTransactionForResume(
			ValidatePendingTransactionForResumeType parameters) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateSalesOrderItemResponseType validateSalesOrderItem(ValidateSalesOrderItemType parameters)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetCustomerEligibilityResponseType getCustomerEligibility(GetCustomerEligibilityType parameters)
			throws ServiceException {
		try {
			GetCustomerEligibilityOperation operation = new GetCustomerEligibilityOperation();
			return operation.execute(parameters);
		} catch (Exception e) {
			String transId = "";
			if(!StringUtils.isBlank(parameters.getOperationHeader().getSalesTransactionId())){
				transId = parameters.getOperationHeader().getSalesTransactionId();
			}
			GetCustomerEligibilityResponseType result = new GetCustomerEligibilityResponseType();
			List<SalesResponseMessage.MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
			MessageList exceptionDetails = EnterpriseWLNExceptionUtil.getExceptionDetails(transId, e,EnterpriseWLNSalesServicesErrorCodes.CUSTOMER_ELIG_EXCEPTION_ERROR);
			messageList.add(exceptionDetails);
			result.setMessageList(messageList);
			return result;

		}
		
	}

	public String getStackTrace(final Exception exc) {
		final StringWriter swr = new StringWriter();
		exc.printStackTrace(new PrintWriter(swr));
		return exc.getMessage() + " " + exc.getCause() + " " + swr.toString();
		
	}
	
	@Override
	public PingResponse ping(Ping parameters) throws ServiceException {
		
		PingResponse response = new PingResponse();
		response.setVersion(App.getPingInfo(startDate)) ;
		return response;

	}

	@Override
	public GetServiceEligibilityResponseType getServiceEligibility(GetServiceEligibilityType parameters)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
