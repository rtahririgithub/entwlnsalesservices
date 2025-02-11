package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IWLNCreditEligibilityProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.GetCreditEligibilityProxyServiceTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncrediteligibilityproxyservicerequestresponse_v1.GetCreditEligibility;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncrediteligibilityproxyservicerequestresponse_v1.GetCreditEligibilityResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.ordermgmt.wlncrediteligibilityproxyservice_1.PolicyException;
import com.telus.wsdl.cmo.ordermgmt.wlncrediteligibilityproxyservice_1.ServiceException;
import com.telus.wsdl.cmo.ordermgmt.wlncrediteligibilityproxyservice_1.WLNCreditEligibilityProxyServicePortType;
import com.telus.wsdl.cmo.ordermgmt.wlncrediteligibilityproxyservice_1.WLNCreditEligibilityProxyServiceStub;

/**
 * 
 * @author Jose.Mena
 *
 */

public class WLNCreditEligibilityProxyServiceAdapter extends SOAPServiceAdapter implements IWLNCreditEligibilityProxyServiceAdapter {

	private static LoggerUtil logger = LoggerUtil.getLogger(WLNCreditEligibilityProxyServiceAdapter.class);
	private static final String ERROR_PREFIX = "WLNCreditEligibilityProxyServiceAdapter_";
	public WLNCreditEligibilityProxyServiceAdapter() {
		super();
	}

	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/wlnCreditEligibilityProxyService/wsdlUrl}");
	
	public WLNCreditEligibilityProxyServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}

	private synchronized WLNCreditEligibilityProxyServicePortType getPort() {
		
		return (WLNCreditEligibilityProxyServicePortType) getInitilizedPort();
		
	}
	
	@Override
	public GetCreditEligibilityAdapterResponse getCreditEligibility(GetCreditEligibilityAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		String functionName = "getCreditEligibility()";
		final String methodName = downStreamSvcName + "." + functionName; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		
		// Check if response already exists in the cache
		GetCreditEligibilityAdapterResponse cachedResponse = null;

		if (!request.isRefreshCache()) {
			cachedResponse = super.getFromCache(request.getCacheKey(), GetCreditEligibilityAdapterResponse.class);

			if (cachedResponse != null) {
				return cachedResponse;
			}
		}
		
		GetCreditEligibility req = GetCreditEligibilityProxyServiceTransformer.transformRequestDO(request);
		GetCreditEligibilityResponse result = new GetCreditEligibilityResponse();
		GetCreditEligibilityAdapterResponse resp = new GetCreditEligibilityAdapterResponse();
		try {
			
			result = getPort().getCreditEligibility(req);
			resp = GetCreditEligibilityProxyServiceTransformer.transformResponseToDO(result);
			
			// Save response to cache
			if (resp != null && !resp.isMsgHasError()) {
				super.saveToCache(request.getCacheKey(), resp);
			}

		} catch (PolicyException e) {
			logger.error(e);
			return (GetCreditEligibilityAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			logger.error(e);
			return (GetCreditEligibilityAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			logger.error(e);
			return (GetCreditEligibilityAdapterResponse) this.handleException(
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
	protected BindingProvider getNewPort() {
		return (BindingProvider) new WLNCreditEligibilityProxyServiceStub().getWLNCreditEligibilityProxyServicePort();
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

}
