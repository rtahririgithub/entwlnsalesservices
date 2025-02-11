package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IConsumerIdentityProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.UpdateOwnerIdentityCredentialAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateOwnerIdentityCredentialAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.ConsumerIdentityProfileMgmtSvcTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumeridentityprofilemgmtsvcrequestresponse_v1.UpdateOwnerIdentityCredential;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumeridentityprofilemgmtsvcrequestresponse_v1.UpdateOwnerIdentityCredentialResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.informationmgmt.consumeridentityprofilemanagementservice_1.ConsumerIdentityProfileManagementServicePortType;
import com.telus.wsdl.cmo.informationmgmt.consumeridentityprofilemanagementservice_1.ConsumerIdentityProfileManagementServiceStub;
import com.telus.wsdl.cmo.informationmgmt.consumeridentityprofilemanagementservice_1.PolicyException;
import com.telus.wsdl.cmo.informationmgmt.consumeridentityprofilemanagementservice_1.ServiceException;

public class ConsumerIdentityProfileMgmtSvcAdapter extends SOAPServiceAdapter implements IConsumerIdentityProfileMgmtSvcAdapter {

	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/consumerIdentityProfileMgmtSvc/wsdlUrl}");
	private LoggerUtil loggerUtil = LoggerUtil.getLogger(ConsumerIdentityProfileMgmtSvcAdapter.class);
	private static final String ERROR_PREFIX = "ConsumerIdentityProfileMgmtSvcAdapter_";
	
	public ConsumerIdentityProfileMgmtSvcAdapter() {
		super();
	}
	
	public ConsumerIdentityProfileMgmtSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}
	
	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized ConsumerIdentityProfileManagementServicePortType getPort() {
		return (ConsumerIdentityProfileManagementServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		
		return (BindingProvider) new ConsumerIdentityProfileManagementServiceStub().getConsumerIdentityProfileManagementServicePort();
		
	}
	
	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

	
	@Override
	public UpdateOwnerIdentityCredentialAdapterResponse updateOwnerIdentityCredential(final UpdateOwnerIdentityCredentialAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".updateOwnerIdentityCredential()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		loggerUtil.enter(methodName);
		UpdateOwnerIdentityCredential req = ConsumerIdentityProfileMgmtSvcTransformer.transformAdapterRequestForUpdOwner(request);
		UpdateOwnerIdentityCredentialResponse resp = new UpdateOwnerIdentityCredentialResponse();
		UpdateOwnerIdentityCredentialAdapterResponse response = new UpdateOwnerIdentityCredentialAdapterResponse();
		
		try {
			resp = getPort().updateOwnerIdentityCredential(req);
			response = ConsumerIdentityProfileMgmtSvcTransformer.transformToAdapterResponseForUpdOwner(resp);
			
		} catch (PolicyException e) {
			LoggerUtil.getStackTrace(e);
			return (UpdateOwnerIdentityCredentialAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			LoggerUtil.getStackTrace(e);
			return (UpdateOwnerIdentityCredentialAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LoggerUtil.getStackTrace(e);
			return (UpdateOwnerIdentityCredentialAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, response,
					contextData, methodName);
		}
		
		loggerUtil.exit(methodName);
		return response;
	}

}
