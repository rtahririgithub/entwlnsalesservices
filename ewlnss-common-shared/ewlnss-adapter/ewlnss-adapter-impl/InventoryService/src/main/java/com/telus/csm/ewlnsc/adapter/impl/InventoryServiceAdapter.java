package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.Locale;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IInventoryServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.ReserveTelephoneNumberAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.ReserveTelephoneNumberAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.tmi.xmlschema.srv.smo.inventorymgmt.inventoryservicerequestresponse_v2.ReserveTelephoneNumber;
import com.telus.tmi.xmlschema.srv.smo.inventorymgmt.inventoryservicerequestresponse_v2.ReserveTelephoneNumberResponse;
import com.telus.tmi.xmlschema.srv.smo.inventorymgmt.inventoryservicerequestresponse_v2.TelephoneNumber;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.Message;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.smo.inventorymgmt.inventoryservice_2.InventoryServicePortType;
import com.telus.wsdl.smo.inventorymgmt.inventoryservice_2.InventoryServiceStub;
import com.telus.wsdl.smo.inventorymgmt.inventoryservice_2.PolicyException;
import com.telus.wsdl.smo.inventorymgmt.inventoryservice_2.ServiceException;

public class InventoryServiceAdapter extends SOAPServiceAdapter implements IInventoryServiceAdapter {

	private static LoggerUtil logger = LoggerUtil.getLogger(InventoryServiceAdapter.class);
	private static final String ERROR_PREFIX = "InventoryServiceAdapter_";

	private static String wsdlUrl = ApplicationProperties
			.getConfigString("${connections/webServices/InventoryService/wsdlUrl}");

	public InventoryServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}

	@Override
	public String ping() throws ServiceException, PolicyException {
		return getPort().ping(new Ping()).toString();
	}

	@Override
	public ReserveTelephoneNumberAdapterResponse reserveTelephoneNumber(ReserveTelephoneNumberAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".reserveTelephoneNumber()";

		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())) {
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}

		ReserveTelephoneNumber req = transformRequest(request);
		ReserveTelephoneNumberAdapterResponse adapterResponse = null;

		try {
			ReserveTelephoneNumberResponse response = getPort().reserveTelephoneNumber(req);
			adapterResponse = transformResponse(response, request.getSalesTransactionId());
		}
		catch (ServiceException e) {
			logger.error(e);

			return (ReserveTelephoneNumberAdapterResponse) handleServiceException(
					request.getSalesTransactionId(), e, null, adapterResponse, contextData, methodName, ERROR_PREFIX);
		}
		catch (Exception e) {
			logger.error(e);

			return (ReserveTelephoneNumberAdapterResponse) this.handleException(request.getSalesTransactionId(),
					e, adapterResponse, contextData, methodName);
		}

		return adapterResponse;
	}

	private ReserveTelephoneNumber transformRequest(ReserveTelephoneNumberAdapterRequest request) {
		ReserveTelephoneNumber result = new ReserveTelephoneNumber();
		
		result.setUserId(request.getUserId());
		result.setTimestamp(request.getTimestamp());

		result.setCoid(request.getCoid());
		result.setSwitchNumber(request.getSwitchNumber());
		
		String referenceNumber = request.getReferenceNumber();
		if (referenceNumber != null && referenceNumber.length() > 18) {
			referenceNumber = referenceNumber.substring(0, 18);
		}
		
		result.setReferenceNumber(referenceNumber);
		
		if (request.getTelephoneNumber() != null) {
			
			String phoneNum = request.getTelephoneNumber().toString();
			
			phoneNum = StringUtils.leftPad(phoneNum, 10, '0');
			
			TelephoneNumber telephoneNumber = new TelephoneNumber();
			telephoneNumber.setNpa(phoneNum.substring(0, 3));
			telephoneNumber.setNxx(phoneNum.substring(3, 6));
			telephoneNumber.setLineNumber(phoneNum.substring(6));
			result.setTelephoneNumber(telephoneNumber);

		}
		
		
		return result;
	}

	private ReserveTelephoneNumberAdapterResponse transformResponse(ReserveTelephoneNumberResponse response, String transactionId) {
		
		ReserveTelephoneNumberAdapterResponse result = new ReserveTelephoneNumberAdapterResponse();
		
		if (response != null && response.getResponseMessageList() != null && response.getResponseMessageList().getResponseMessage() != null) {
			for (ResponseMessage responseMsg : response.getResponseMessageList().getResponseMessage()) {
				
				AdapterResponseMessage messageDO = new AdapterResponseMessage(responseMsg.getErrorCode(), responseMsg.getContextData(), transactionId, responseMsg.getMessageType());
				if ("ERROR".equals(responseMsg.getMessageType())) {
					result.setMsgHasError(true);
				}
				messageDO.setMesssageDescriptionTextList(new ArrayList<AdapterResponseMessageDesc>());
				AdapterResponseMessageDesc adapterMsg = new AdapterResponseMessageDesc();
				for (Message msg : responseMsg.getMessageList()) {
					adapterMsg.setLocale(Locale.CANADA);
					adapterMsg.setMessageDescriptionText(msg.getMessage());
				}
				
				messageDO.getMesssageDescriptionTextList().add(adapterMsg);
				result.setAdapterResponseMessage(messageDO);
			}
			
		}
		
		return result;
	}

	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new InventoryServiceStub().getInventoryServicePort();
	}

	private InventoryServicePortType getPort() {
		return (InventoryServicePortType) getInitilizedPort();
	}

	protected synchronized String getApplicationName() {
		return "APP_ENTRSALESSVC";
	}

	protected synchronized String getApplicationPwd() {
		return "soaorgid";
	}

}
