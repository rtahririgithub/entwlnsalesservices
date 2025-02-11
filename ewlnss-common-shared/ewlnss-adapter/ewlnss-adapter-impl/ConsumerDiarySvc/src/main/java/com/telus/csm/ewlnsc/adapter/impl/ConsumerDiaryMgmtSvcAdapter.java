package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IConsumerDiaryMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.CreateCustomerEventTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerdiaryservicerequestresponse_v1.CreateCustomerEvent;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerdiaryservicerequestresponse_v1.CreateCustomerEventResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.informationmgmt.consumerdiaryservice_1.ConsumerDiaryServicePortType;
import com.telus.wsdl.cmo.informationmgmt.consumerdiaryservice_1.ConsumerDiaryServiceStub;
import com.telus.wsdl.cmo.informationmgmt.consumerdiaryservice_1.PolicyException;
import com.telus.wsdl.cmo.informationmgmt.consumerdiaryservice_1.ServiceException;

public class ConsumerDiaryMgmtSvcAdapter extends SOAPServiceAdapter implements IConsumerDiaryMgmtSvcAdapter {
	
	private static final String ERROR_PREFIX = "ConsumerDiaryMgmtSvcAdapter_";
	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(ConsumerDiaryMgmtSvcAdapter.class);
	
	public ConsumerDiaryMgmtSvcAdapter() {
		super();
	}

	public ConsumerDiaryMgmtSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/consumerDiaryMgmtSvc/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized ConsumerDiaryServicePortType getPort() {
		
		return (ConsumerDiaryServicePortType) getInitilizedPort();
		
	}

	@Override
	protected BindingProvider getNewPort() {
		
		return (BindingProvider) new ConsumerDiaryServiceStub().getConsumerDiaryServicePort();
		
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}


	@Override
	public CreateCustomerEventAdapterResponse createCustomerEvent(final CreateCustomerEventAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".createCustomerEvent()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		
		CreateCustomerEvent req = CreateCustomerEventTransformer.transformAdapterRequest(request);
		CreateCustomerEventResponse result = new CreateCustomerEventResponse();
		CreateCustomerEventAdapterResponse resp = new CreateCustomerEventAdapterResponse();
		
		try {
			
			result =  getPort().createCustomerEvent(req);
			resp =  CreateCustomerEventTransformer.transformToAdapterResponse(result);
			
		} catch (PolicyException e) {
			loggerUtil.error("", methodName, "", e);
			return (CreateCustomerEventAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			loggerUtil.error("", methodName, "", e);
			return (CreateCustomerEventAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			loggerUtil.error("", methodName, "", e);
			return (CreateCustomerEventAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, resp,
					contextData, methodName);
		}
		
		return resp;
	}

}
