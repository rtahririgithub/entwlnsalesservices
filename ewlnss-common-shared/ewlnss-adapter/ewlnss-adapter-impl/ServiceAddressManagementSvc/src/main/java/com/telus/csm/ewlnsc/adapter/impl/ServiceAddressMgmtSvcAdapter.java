package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import com.telus.csm.ewlnsc.adapter.IServiceAddressMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.ServiceAddresesMgmSvcTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.GetServiceAddressDetails;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.GetServiceAddressDetailsResponse;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.SearchServiceAddress;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.SearchServiceAddressResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.smo.ordermgmt.serviceaddressmanagementservice_1.PolicyException;
import com.telus.wsdl.smo.ordermgmt.serviceaddressmanagementservice_1.ServiceAddressManagementServicePortType;
import com.telus.wsdl.smo.ordermgmt.serviceaddressmanagementservice_1.ServiceAddressManagementServiceStub;
import com.telus.wsdl.smo.ordermgmt.serviceaddressmanagementservice_1.ServiceException;

public class ServiceAddressMgmtSvcAdapter extends SOAPServiceAdapter implements IServiceAddressMgmtSvcAdapter {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ServiceAddressMgmtSvcAdapter.class);

	private static final String ERROR_PREFIX = "ServiceAddressMgmtSvcAdapter_";
	
	public ServiceAddressMgmtSvcAdapter(){
		super();
	}
	
	public ServiceAddressMgmtSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}

	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/serviceAddressMgmtSvc/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized ServiceAddressManagementServicePortType getPort() {
		
		return (ServiceAddressManagementServicePortType) getInitilizedPort();
		
	}

	@Override
	protected BindingProvider getNewPort() {
		
		return (BindingProvider) new ServiceAddressManagementServiceStub().getServiceAddressManagementServicePort();
		
	}
	
	
	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

	@Override
	public SearchServiceAddressAdapterResponse searchServiceAddress(final SearchServiceAddressAdapterRequest param) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".searchServiceAddress()"; 
		String contextDataPolicy = methodName;
		
		LOGGER.info(methodName, "Entering method-> searchServiceAddress");
		SearchServiceAddressAdapterResponse result = null;
		SearchServiceAddressResponse response = null;
		String contextData="";
		try {
			//transform requestDO to request
			ServiceAddresesMgmSvcTransformer transformer = new ServiceAddresesMgmSvcTransformer();
			SearchServiceAddress searchServiceAddressRequest = transformer.transformToSearchServiceAddress(param);
			LOGGER.info(methodName,"SearchServiceAddressRequest: " + EnterpriseSalesServiceUtil.getXmlString(searchServiceAddressRequest, SearchServiceAddress.class));
			contextData = methodName + " Transaction ID: " + param.getSalesTransactionId() + " ]";
			response = getPort().searchServiceAddress(searchServiceAddressRequest);
			if(!response.getResponseMessageList().getResponseMessage().isEmpty()){
				LOGGER.error(null, methodName, "Error found during call to searchServiceAddress operation.. Response: " + EnterpriseSalesServiceUtil.getXmlString(response, SearchServiceAddressResponse.class));
				result = transformer.transformToAdapterResponse(response);
			}else{
				LOGGER.info(methodName, "No errors found during call to SearchServiceAddress operation.. Response: " + EnterpriseSalesServiceUtil.getXmlString(response, SearchServiceAddressResponse.class));
				result = transformer.transformToAdapterResponse(response);
			}
		} catch (PolicyException e) {
			LOGGER.error(null,methodName,e.getMessage(),e);
			result = new SearchServiceAddressAdapterResponse();
			return (SearchServiceAddressAdapterResponse) this
					.handlePolicyException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			LOGGER.error(null, methodName, e.getMessage(),e);
			result = new SearchServiceAddressAdapterResponse();
			return (SearchServiceAddressAdapterResponse) this
					.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(null, methodName, e.getMessage(),e);
			result = new SearchServiceAddressAdapterResponse();
			return (SearchServiceAddressAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					contextData, methodName);
		}
		return result;
	}

	@Override
	public GetServiceAddressDetailsAdapterResponse getServiceAddressDetails(final GetServiceAddressDetailsAdapterRequest param) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".getServiceAddressDetails()"; 
		String contextDataPolicy = methodName;
		
		LOGGER.info(methodName, "Entering Method -> getServiceAddressDetails");
		GetServiceAddressDetailsAdapterResponse result;
		
		if (!param.isRefreshCache()) {
			result = super.getFromCache(param.getCacheKey(), GetServiceAddressDetailsAdapterResponse.class);

			if (result != null) {
				return result;
			}
		}
		
		GetServiceAddressDetailsResponse response = null;
		String contextData="";
		try {
			ServiceAddresesMgmSvcTransformer transformer = new ServiceAddresesMgmSvcTransformer();
			GetServiceAddressDetails getServiceAddressDetailsRequest = transformer.transformToGetServiceAddressDetails(param);
			contextData = methodName + " Transaction ID: " + param.getSalesTransactionId() + " ]";
			//LOGGER.info(methodName, "GetServiceAddressDetailsRequest: " + EnterpriseSalesServiceUtil.getXmlString(getServiceAddressDetailsRequest, GetServiceAddressDetails.class));
			response = getPort().getServiceAddressDetails(getServiceAddressDetailsRequest);
			if(!response.getResponseMessageList().getResponseMessage().isEmpty()){
				//.error(null, methodName, "Error found during call to getServiceAddressDetails operation.. Response: " + EnterpriseSalesServiceUtil.getXmlString(response, GetServiceAddressDetailsResponse.class));
				result = transformer.transformToAdapterResponse(response);
			}else{
				//LOGGER.info(methodName, "No errors found during call to getServiceAddressDetails operation.. Response: " + EnterpriseSalesServiceUtil.getXmlString(response, GetServiceAddressDetailsResponse.class));
				result = transformer.transformToAdapterResponse(response);
			}
		} catch (PolicyException e) {
			result = new GetServiceAddressDetailsAdapterResponse();
			LOGGER.error(null,methodName,e.getMessage(),e);
			return (GetServiceAddressDetailsAdapterResponse) this
					.handlePolicyException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			result = new GetServiceAddressDetailsAdapterResponse();
			LOGGER.error(null, methodName, e.getMessage(),e);
			return (GetServiceAddressDetailsAdapterResponse) this
					.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			result = new GetServiceAddressDetailsAdapterResponse();
			LOGGER.error(null, methodName, e.getMessage(),e);
			//port = null; TODO: add this to all adapters
			return (GetServiceAddressDetailsAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					contextData, methodName);
		}
		
		if (result != null && !result.isMsgHasError()) {
			super.saveToCache(param.getCacheKey(), result);
		}
		LOGGER.exit(methodName);
		return result;
	}

	
	
}
