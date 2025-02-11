package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IEnterpriseCreditProfileManagementServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.UpdateCreditProfileTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.enterprisecreditprofilemanagementservicerequestresponse_v1.UpdateCreditProfile;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.ordermgmt.enterprisecreditprofilemanagementservice_1.EnterpriseCreditProfileManagementServicePortType;
import com.telus.wsdl.cmo.ordermgmt.enterprisecreditprofilemanagementservice_1.EnterpriseCreditProfileManagementServiceStub;
import com.telus.wsdl.cmo.ordermgmt.enterprisecreditprofilemanagementservice_1.PolicyException;
import com.telus.wsdl.cmo.ordermgmt.enterprisecreditprofilemanagementservice_1.ServiceException;

/**
 * 
 * @author Jose.Mena
 *
 */

public class EnterpriseCreditProfileManagementServiceAdapter extends SOAPServiceAdapter implements IEnterpriseCreditProfileManagementServiceAdapter {

	private static Logger logger = Logger.getLogger(EnterpriseCreditProfileManagementServiceAdapter.class);
	private static final String ERROR_PREFIX = "EnterpriseCreditProfileManagementServiceAdapter_";
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/enterpriseCreditProfileManagementService/wsdlUrl}");

	public EnterpriseCreditProfileManagementServiceAdapter() {
		super();
	}

	
	public EnterpriseCreditProfileManagementServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}

	private synchronized EnterpriseCreditProfileManagementServicePortType getPort() {
		return (EnterpriseCreditProfileManagementServicePortType) getInitilizedPort();
	}

	@Override
	public UpdateCreditProfileAdapterResponse updateCreditProfile(UpdateCreditProfileAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".updateCreditProfile()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + " [ ";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + " ]";
		}
		UpdateCreditProfile req = UpdateCreditProfileTransformer.transformRequestDO(request);
		// UpdateCreditProfileResponse result = new UpdateCreditProfileResponse()
		UpdateCreditProfileAdapterResponse resp = new UpdateCreditProfileAdapterResponse();
		
		try {
			
			getPort().updateCreditProfile(req);
			resp = UpdateCreditProfileTransformer.transformResponseToDO();
			
		} catch (PolicyException e) {
			logger.error(e);
			return (UpdateCreditProfileAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			logger.error(e);
			return (UpdateCreditProfileAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			logger.error(e);
			return (UpdateCreditProfileAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, resp,
					contextData, methodName);
		}
		
		return resp;
	}
	
	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}
	
	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new EnterpriseCreditProfileManagementServiceStub().getEnterpriseCreditProfileManagementServicePort();
	}

	
}
