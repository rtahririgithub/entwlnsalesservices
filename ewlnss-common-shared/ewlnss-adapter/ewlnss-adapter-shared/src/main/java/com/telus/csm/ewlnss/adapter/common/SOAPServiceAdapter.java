package com.telus.csm.ewlnss.adapter.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.channelsalesmgmt.common.aspect.AspectWeaver;
import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.csm.ewlnsc.aspect.ProxyProfilingAspect;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.TelusExceptionHelper;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseRelatedMessage;
import com.telus.csm.ewlnss.adapter.helper.MessageMappingHelper;
import com.telus.csm.ewlnss.adapter.util.LogSOAPHandler;
import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.PolicyException;
import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException;

public abstract class SOAPServiceAdapter extends ServiceAdapterBase {
	
	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(SOAPServiceAdapter.class);
	protected static MessageMappingHelper messageHelper;
	private static final String CONTEXT_DATA_STR = "- Context Data - ";
	
	// This is a HashMap of HashMap
	//
	// The first level hashMap's entries have the adapter class as key and hashMap as value
	//
	// Then the second level hashMap entries have the feature set as key (can be empty set if no customized feature added) and the corresponding adapter instance as value.
	//  
	private static HashMap<Class<? extends SOAPServiceAdapter>, Map<String, BindingProvider>> adapterPortsMaps = new HashMap<Class<? extends SOAPServiceAdapter>, Map<String, BindingProvider>>();

	static {
		messageHelper = MessageMappingHelper.getInstance();
	}

	public SOAPServiceAdapter() {
	}
	
	public SOAPServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
		//add universal features for all SOAP adapters
	}
	
	protected final BindingProvider getInitilizedPort() {

		// look for the port in the static cache
		Map<String, BindingProvider> adapterPortsMap = adapterPortsMaps.get(this.getClass());
		if (adapterPortsMap == null) {
			adapterPortsMap = new HashMap<String, BindingProvider>();
			adapterPortsMaps.put(this.getClass(), adapterPortsMap);
		}
		
		BindingProvider port = adapterPortsMap.get(getFeatures().getFeaturesString());
		
		if (port == null ) {
			port = getNewPort();
			port = initPort(port);
			adapterPortsMap.put(getFeatures().getFeaturesString(), port);
		}
		
		return port;
	}

	public abstract String getWsdlUrl();
	
	protected abstract BindingProvider getNewPort();
	
	public String getEndpointAddress() {
		return getWsdlUrl();
	}

	private BindingProvider initPort(BindingProvider port) {
		
		BindingProvider result = port;
		
		//add SOAP handler to log request/response
		Binding binding = port.getBinding();
		List<Handler> handlerList = binding.getHandlerChain();
		handlerList.add(new LogSOAPHandler());
		binding.setHandlerChain(handlerList);
		
		//set the end point address
		Map<String, Object> requestContext = port.getRequestContext();
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getWsdlUrl());

		//set the basic auth credentials
		//hardoded for now. It will be removed after acquiring the contract for OrderDepositCalculatorProxy
		if (getWsdlUrl().contains("OrderDepositCalculator")) {
			requestContext.put(BindingProvider.USERNAME_PROPERTY, "APP_WLINESALESCAPTURE");
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, getApplicationPwd());
		} else if (getWsdlUrl().contains("FieldWorkAppointment")) {
			requestContext.put(BindingProvider.USERNAME_PROPERTY, "NetCracker");
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, getApplicationPwd());			
		} else if (getWsdlUrl().startsWith("https") || getWsdlUrl().contains("_vs")) {
			requestContext.put(BindingProvider.USERNAME_PROPERTY, getApplicationName());
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, getApplicationPwd());
		}

		getFeatures().apply(port);

		//apply profiling aspect on the portType
		try {
			Aspect[] profileAspects = null;
			profileAspects = new Aspect[] { new ProxyProfilingAspect(this.getClass().getSimpleName() + " " + getWsdlUrl()) };
			result = (BindingProvider) AspectWeaver.weave(port, profileAspects);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;

	}

	protected AdapterResponseBase handlePolicyException(
			final String transactionId,
			final Exception exception,
			final PolicyException pEx,
			final AdapterResponseBase responseDO, final String contextData, final String methodName,
			final String errorPrefix) {

		String errorCode = null != pEx? pEx.getErrorCode() : EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR;
		String errorMsg = null != pEx? pEx.getErrorMessage() : "";

		loggerUtil.error(errorCode, methodName, errorMsg + CONTEXT_DATA_STR + contextData, exception);

		final String ctx = contextData;

		final AdapterResponseMessage messageDO = new AdapterResponseMessage(exception,
				messageHelper.getMappedMessageCode(errorPrefix + errorCode),
				ctx, transactionId, EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		messageHelper.populateErrorMessageDescTextListForPolicyAndService(messageDO);
		responseDO.setAdapterResponseMessage(messageDO);
		
		//populate relatedMessages element
		AdapterResponseRelatedMessage relatedMessage = new AdapterResponseRelatedMessage();
		if (pEx != null){
			relatedMessage.setRelatedErrorTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
			relatedMessage.setRelatedErrorCd(pEx.getErrorCode());
			if (!StringUtils.isEmpty(pEx.getErrorMessage())){
				relatedMessage.setRelatedErrorMessageTxt(pEx.getErrorMessage());
			}else{
				relatedMessage.setRelatedErrorMessageTxt(EnterpriseSalesServiceUtil.getStackTrace(exception));
			}
			
			List<AdapterResponseRelatedMessage> relatedMessageList = new ArrayList<AdapterResponseRelatedMessage>();
			relatedMessageList.add(relatedMessage);
			messageDO.setRelatedMessageList(relatedMessageList);
		}
		
		responseDO.setMsgHasError(true);

		return responseDO;
	}
	
	protected AdapterResponseBase handleServiceException(
			final String transactionid,
			final Exception exception,
			final ServiceException sEx,
			final AdapterResponseBase responseDO, final String contextData, final String methodName,
			final String errorPrefix) {
		String separatorStr = " --- ";
		loggerUtil.error(sEx.getErrorCode(), methodName, sEx.getErrorMessage()
				+ CONTEXT_DATA_STR + contextData, exception);
		final String ctx = contextData + separatorStr + exception.getMessage() + separatorStr
				+ sEx.getErrorCode() + separatorStr + sEx.getErrorMessage() + separatorStr
				+ getVariables(sEx);

		final AdapterResponseMessage messageDO = new AdapterResponseMessage(exception,
				messageHelper.getMappedMessageCode(errorPrefix + sEx.getErrorCode()), 
				ctx, transactionid, EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
		messageHelper.populateErrorMessageDescTextListForPolicyAndService(messageDO);
		responseDO.setAdapterResponseMessage(messageDO);
		
		//populate relatedMessages element
		AdapterResponseRelatedMessage relatedMessage = new AdapterResponseRelatedMessage();
		relatedMessage.setRelatedErrorTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		relatedMessage.setRelatedErrorCd(sEx.getErrorCode());
		if (!StringUtils.isEmpty(sEx.getErrorMessage())){
			relatedMessage.setRelatedErrorMessageTxt(sEx.getErrorMessage());
		}else{
			relatedMessage.setRelatedErrorMessageTxt(EnterpriseSalesServiceUtil.getStackTrace(exception));
		}
		List<AdapterResponseRelatedMessage> relatedMessageList = new ArrayList<AdapterResponseRelatedMessage>();
		relatedMessageList.add(relatedMessage);
		messageDO.setRelatedMessageList(relatedMessageList);
				
		responseDO.setMsgHasError(true);

		return responseDO;
	}

	protected AdapterResponseBase handleException(final String transactionid, final Exception exc,
			final AdapterResponseBase responseDO, final String contextData, final String methodName) {
		loggerUtil.error(null, methodName, exc.getMessage() + CONTEXT_DATA_STR
				+ contextData, exc);
		final String stackTrace = EnterpriseSalesServiceUtil.getStackTrace(exc);
		final String ctx = contextData + " " + stackTrace;

		final AdapterResponseMessage messageDO = new AdapterResponseMessage(exc, EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR,
				ctx, transactionid, EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
		messageHelper.populateErrorMessageDescTextList(messageDO);
		responseDO.setAdapterResponseMessage(messageDO);
		responseDO.setMsgHasError(true);

		return responseDO;
	}
	
	private String getVariables(final ServiceException sEx) {
		String exceptionMsg = "";

		if (sEx != null && CollectionUtils.isNotEmpty(sEx.getVariables())) {
			final String trace = TelusExceptionHelper.concatFaultInfo(sEx.getVariables());
			exceptionMsg = exceptionMsg + " - " + trace;
		}

		return exceptionMsg;
	}


	protected String buildPolicyExceptionContextMsg(final PolicyException pEx) {
		String exceptionMsg = "";

		if (pEx != null && CollectionUtils.isNotEmpty(pEx.getVariables())) {
			final String trace = TelusExceptionHelper.concatFaultInfo(pEx.getVariables());
			exceptionMsg = exceptionMsg + " - " + trace;
		}

		return exceptionMsg;
	}
	
}
