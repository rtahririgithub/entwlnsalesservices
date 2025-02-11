package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IEnterpriseConsumerProfileRegistrationSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.EnterpriseConsumerProfRegSvcTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.enterpriseconsumerprofileregistrationsvcrequestresponse_v1.CreateProfileForNewCustomer;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.enterpriseconsumerprofileregistrationsvcrequestresponse_v1.CreateProfileForNewCustomerResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v2.Ping;
import com.telus.wsdl.cmo.informationmgmt.enterpriseconsumerprofileregistrationsvc_1.EnterpriseConsumerProfileRegistrationSvcPortType;
import com.telus.wsdl.cmo.informationmgmt.enterpriseconsumerprofileregistrationsvc_1.EnterpriseConsumerProfileRegistrationSvcStub;
import com.telus.wsdl.cmo.informationmgmt.enterpriseconsumerprofileregistrationsvc_1.ServiceException;

public class EnterpriseConsumerProfileRegistrationSvcAdapter
	extends SOAPServiceAdapter 
	implements IEnterpriseConsumerProfileRegistrationSvcAdapter {
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/enterpriseConsumerProfileRegistrationSvc/wsdlUrl}");
	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(EnterpriseConsumerProfileRegistrationSvcAdapter.class);
	
	public EnterpriseConsumerProfileRegistrationSvcAdapter() {
		super();
	}
	
	public EnterpriseConsumerProfileRegistrationSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}
	
	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized EnterpriseConsumerProfileRegistrationSvcPortType getPort() {
		return (EnterpriseConsumerProfileRegistrationSvcPortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		
		return (BindingProvider) new EnterpriseConsumerProfileRegistrationSvcStub().getEnterpriseConsumerProfileRegistrationSvcPort();
		
	}
	
	@Override
	public String ping() throws ServiceException {
		return JsonUtil.getJsonFromObject(getPort().ping(new Ping()));
	}

	@Override
	public CreateProfileForNewCustomerAdapterResponse createProfileForNewCustomer(
			CreateProfileForNewCustomerAdapterRequest request) {
		final String methodName = "EnterpriseConsumerProfileRegistrationSvcAdapter.createProfileForNewCustomer()"; 
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		
		loggerUtil.enter(methodName);
		CreateProfileForNewCustomer req = EnterpriseConsumerProfRegSvcTransformer.transformAdapterRequestForCreateProfForNewCust(request);
		CreateProfileForNewCustomerResponse resp = new CreateProfileForNewCustomerResponse();
		CreateProfileForNewCustomerAdapterResponse response = new CreateProfileForNewCustomerAdapterResponse();
		
		try {
			loggerUtil.info(methodName, "Calling downstream service... Context data: " + contextData);
			resp = getPort().createProfileForNewCustomer(req);
			response = EnterpriseConsumerProfRegSvcTransformer.transformToAdapterResponse(resp);
			
		} catch (Exception e) {
			LoggerUtil.getStackTrace(e);
			return (CreateProfileForNewCustomerAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, response,
					contextData, methodName);
		}
		
		loggerUtil.exit(methodName);
		return response;
	}


}
