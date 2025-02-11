package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.WLNCreditProfileMgmtSvcTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.GetCreditProfileByCustomerId;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.GetCreditProfileByCustomerIdResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.SearchCreditProfileByCreditId;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.SearchCreditProfileByCreditIdResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementservice_2.PolicyException;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementservice_2.ServiceException;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementservice_2.WLNCreditProfileManagementServicePortType;
import com.telus.wsdl.cmo.ordermgmt.wlncreditprofilemanagementservice_2.WLNCreditProfileManagementServiceStub;

public class WLNCreditProfileMgmtSvcAdapter extends SOAPServiceAdapter implements IWLNCreditProfileMgmtSvcAdapter {

	private static LoggerUtil logger = LoggerUtil.getLogger(WLNCreditProfileMgmtSvcAdapter.class);
	private static final String ERROR_PREFIX = "WLNCreditProfileMgmtSvcAdapter_";
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/wlnCreditProfileManagementService/wsdlUrl}");
	
	public WLNCreditProfileMgmtSvcAdapter() {
		super();
	}

	public WLNCreditProfileMgmtSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}


	@Override
	public SearchCreditProfileByCreditIdAdapterResponse searchCreditProfileByCreditId(final SearchCreditProfileByCreditIdAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".searchCreditProfileByCreditId()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		SearchCreditProfileByCreditId req = WLNCreditProfileMgmtSvcTransformer.transformAdapterRequestForSearchCredit(request);
		SearchCreditProfileByCreditIdResponse resp = new SearchCreditProfileByCreditIdResponse();
		SearchCreditProfileByCreditIdAdapterResponse response = new SearchCreditProfileByCreditIdAdapterResponse();
		
		try {
			
			resp = getPort().searchCreditProfileByCreditId(req);
			response = WLNCreditProfileMgmtSvcTransformer.transformToAdapterResponseForSearchClient(resp);
			
		} catch (PolicyException e) {
			logger.error(e);
			return (SearchCreditProfileByCreditIdAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			logger.error(e);
			return (SearchCreditProfileByCreditIdAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			logger.error(e);
			return (SearchCreditProfileByCreditIdAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, response,
					contextData, methodName);
		}
	
		return response;
	}

	@Override
	public GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustomerId(final GetCreditProfileByCustomerIdAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		String functionName = "getCreditProfileByCustomerId()";
		final String methodName = downStreamSvcName + "." + functionName; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		
		// Check if response already exists in the cache
		GetCreditProfileByCustomerIdAdapterResponse cachedResponse = null;
		if (!request.isRefreshCache()) {
			cachedResponse = super.getFromCache(request.getCacheKey(), GetCreditProfileByCustomerIdAdapterResponse.class);
			if (cachedResponse != null) {
				return cachedResponse;
			}
		}
		
		GetCreditProfileByCustomerId req = WLNCreditProfileMgmtSvcTransformer.transformRequestDO(request);
		GetCreditProfileByCustomerIdResponse result = new GetCreditProfileByCustomerIdResponse();
		GetCreditProfileByCustomerIdAdapterResponse resp = new GetCreditProfileByCustomerIdAdapterResponse();
		try {
			
			result = getPort().getCreditProfileByCustomerId(req);
			resp = WLNCreditProfileMgmtSvcTransformer.transformResponseToDO(result);
			
			// Save response to cache
			if (resp != null && !resp.isMsgHasError()) {
				super.saveToCache(request.getCacheKey(), resp);
			}
		} catch (PolicyException e) {
			logger.error(e);
			return (GetCreditProfileByCustomerIdAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			logger.error(e);
			return (GetCreditProfileByCustomerIdAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			logger.error(e);
			return (GetCreditProfileByCustomerIdAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, resp,
					contextData, methodName);
		}
		
		return resp;
	}

	private synchronized WLNCreditProfileManagementServicePortType getPort() {
		
		return (WLNCreditProfileManagementServicePortType) getInitilizedPort();
		
	}

	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new WLNCreditProfileManagementServiceStub().getWLNCreditProfileManagementServicePort();
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

}
