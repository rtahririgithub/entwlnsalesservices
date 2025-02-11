package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileManagementProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.AssessCreditWorthinessTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementproxyservicerequestresponse_v2.AssessCreditWorthiness;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementproxyservicerequestresponse_v2.AssessCreditWorthinessResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementproxyservice_2.PolicyException;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementproxyservice_2.ServiceException;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementproxyservice_2.WLNCreditProfileManagementProxyServicePortType;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementproxyservice_2.WLNCreditProfileManagementProxyServiceStub;

/**
 * 
 * @author Jose.Mena
 *
 */
public class WLNCreditProfileManagementProxyServiceAdapter extends SOAPServiceAdapter implements IWLNCreditProfileManagementProxyServiceAdapter {

	private static Logger logger = Logger.getLogger(WLNCreditProfileManagementProxyServiceAdapter.class);
	private static final String ERROR_PREFIX = "WLNCreditProfileManagementProxyServiceAdapter_";
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/wlnCreditProfileManagementProxyService/wsdlUrl}");
	
	public WLNCreditProfileManagementProxyServiceAdapter() {
		super();
	}

	public WLNCreditProfileManagementProxyServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}
	
	@Override
	public AssessCreditWorthinessAdapterResponse assessCreditWorthiness(AssessCreditWorthinessAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".assessCreditWorthiness()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		AssessCreditWorthinessResponse result = new AssessCreditWorthinessResponse();
		AssessCreditWorthinessAdapterResponse response = new AssessCreditWorthinessAdapterResponse();
		AssessCreditWorthiness req = AssessCreditWorthinessTransformer.transformRequestDO(request);
		try {
			
			result = getPort().assessCreditWorthiness(req);
			response = AssessCreditWorthinessTransformer.transformResponseToDO(result);
			
		} catch (PolicyException e) {
			logger.error(e);
			return (AssessCreditWorthinessAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			logger.error(e);
			return (AssessCreditWorthinessAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			logger.error(e);
			return (AssessCreditWorthinessAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, response,
					contextData, methodName);
		}
		
		return response;
	}

	private synchronized WLNCreditProfileManagementProxyServicePortType getPort() {
		
		return (WLNCreditProfileManagementProxyServicePortType) getInitilizedPort();
		
	}
	
	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Override
	protected BindingProvider getNewPort() {
		return  (BindingProvider) new WLNCreditProfileManagementProxyServiceStub().getWLNCreditProfileManagementProxyServicePort();
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

}
