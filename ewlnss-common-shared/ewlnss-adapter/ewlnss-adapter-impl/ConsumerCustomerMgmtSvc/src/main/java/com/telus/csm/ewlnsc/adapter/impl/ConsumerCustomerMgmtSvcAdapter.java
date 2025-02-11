package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerPreferenceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.transformer.Transformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.CreateCustomer;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.CreateCustomerResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.GetFullCustomerInfo;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.GetFullCustomerInfoResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.InsertCustomerPreference;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.InsertCustomerPreferenceResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateContactIndividual;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateContactIndividualResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateCustomerOfficialAddress;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateCustomerPreference;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.UpdateCustomerPreferenceResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.informationmgmt.consumercustomermanagementservice_2.ConsumerCustomerManagementServicePortType;
import com.telus.wsdl.cmo.informationmgmt.consumercustomermanagementservice_2.ConsumerCustomerManagementServiceStub;
import com.telus.wsdl.cmo.informationmgmt.consumercustomermanagementservice_2.PolicyException;
import com.telus.wsdl.cmo.informationmgmt.consumercustomermanagementservice_2.ServiceException;

public class ConsumerCustomerMgmtSvcAdapter	extends SOAPServiceAdapter implements IConsumerCustomerMgmtSvcAdapter {
	
	private static final Logger LOGGER = Logger.getLogger(ConsumerCustomerMgmtSvcAdapter.class);
	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(ConsumerCustomerMgmtSvcAdapter.class);
	private static final String ERROR_PREFIX = "ConsumerCustomerMgmtSvcAdapter_";
	private static final String TRANS_ID_STR = ", transId=";
	public ConsumerCustomerMgmtSvcAdapter() {
		super();
	}
	
	public ConsumerCustomerMgmtSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(cacheAdapter);
	}
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/consumerCustomerMgmtSvc/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized ConsumerCustomerManagementServicePortType getPort() {
		return (ConsumerCustomerManagementServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new ConsumerCustomerManagementServiceStub().getConsumerCustomerManagementServicePort();
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}
	
	@Override
	public GetFullCustomerInfoAdapterResponse getFullCustomerInfo(final GetFullCustomerInfoAdapterRequest param) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".getFullCustomerInfo()"; 
		String contextDataPolicy = methodName;
		LOGGER.info("calling down stream getFullCustomerInfo");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId() + ", custId=" + param.getCustomerId();
		//Checking if the response already exists in the cache
		//QC60892 - rollback caching logic
  		GetFullCustomerInfoAdapterResponse result = super.getFromCache(param.getCacheKey(), GetFullCustomerInfoAdapterResponse.class);
		if (result != null) {
			return result;
		} else {
			result = new GetFullCustomerInfoAdapterResponse();	
		}
		
		final GetFullCustomerInfo request = Transformer.transform(param);
		try {
			
			GetFullCustomerInfoResponse resp = getPort().getFullCustomerInfo(request);
			result = Transformer.transform(resp);
			if (result != null && result.getFullCustomer() != null) {
				super.saveToCache(param.getCacheKey(), result);
			}
		} catch (PolicyException e) {
			LOGGER.error(e);
			return (GetFullCustomerInfoAdapterResponse) this.handlePolicyException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);		
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (GetFullCustomerInfoAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (GetFullCustomerInfoAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}

	@Override
	public CreateNewCustomerAdapterResponse createNewCustomer(final CreateNewCustomerAdapterRequest param) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".createCustomer()"; 
		String contextDataPolicy = methodName;
		
		LOGGER.info("calling down stream createCustomer");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId();
		
		CreateNewCustomerAdapterResponse result = new CreateNewCustomerAdapterResponse();
		final CreateCustomer request = Transformer.transform(param);
		try {
			
			final CreateCustomerResponse resp = getPort().createCustomer(request);
			result = Transformer.transform(resp);
			
		} catch (PolicyException e) {
			LOGGER.error(e);
			return (CreateNewCustomerAdapterResponse) this.handlePolicyException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);		
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (CreateNewCustomerAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (CreateNewCustomerAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}

	@Override
	public UpdateContactIndividualAdapterResponse updateContactIndividual(final UpdateContactIndividualAdapterRequest param) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".updateContactIndividual()"; 
		String contextDataPolicy = methodName;
		
		LOGGER.info("calling down stream updateContactIndividual");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId();

		UpdateContactIndividualAdapterResponse result = new UpdateContactIndividualAdapterResponse();
		final UpdateContactIndividual request = Transformer.transform(param);
		try {
			
			final UpdateContactIndividualResponse resp = getPort().updateContactIndividual(request);
			result = Transformer.transform(resp);
			
		} catch (PolicyException e) {
			LOGGER.error(e);
			return (UpdateContactIndividualAdapterResponse) this.handlePolicyException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);		
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (UpdateContactIndividualAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (UpdateContactIndividualAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}

	@Override
	public UpdateCustomerOfficialAddressAdapterResponse updateCustomerOfficialAddress(final UpdateCustomerOfficialAddressAdapterRequest param) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".updateCustomerOfficialAddress()"; 
		String contextDataPolicy = methodName;
		
		LOGGER.info("calling down stream updateCustomerOfficialAddress");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId() + ", custId=" + param.getCustomerId();

		UpdateCustomerOfficialAddressAdapterResponse result = new UpdateCustomerOfficialAddressAdapterResponse();
		final UpdateCustomerOfficialAddress request = Transformer.transform(param);
		try {
			
			getPort().updateCustomerOfficialAddress(request);
			result = Transformer.transform();
			
		} catch (PolicyException e) {
			LOGGER.error(e);
			return (UpdateCustomerOfficialAddressAdapterResponse) this.handlePolicyException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);		
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (UpdateCustomerOfficialAddressAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (UpdateCustomerOfficialAddressAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}

	@Override
	public UpdateCustomerPreferenceAdapterResponse updateCustomerPreference(final UpdateCustomerPreferenceAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".updateCustomerPreference()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		
		UpdateCustomerPreferenceAdapterResponse resp = new UpdateCustomerPreferenceAdapterResponse();
		UpdateCustomerPreference req = Transformer.transform(request); 
		UpdateCustomerPreferenceResponse result = new UpdateCustomerPreferenceResponse();
		
		try {
			result = getPort().updateCustomerPreference(req);
			resp = Transformer.transform(result);
			
		} catch (PolicyException e) {
			loggerUtil.error("", methodName, "", e);
			return (UpdateCustomerPreferenceAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			loggerUtil.error("", methodName, "", e);
			return (UpdateCustomerPreferenceAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			loggerUtil.error("", methodName, "", e);
			return (UpdateCustomerPreferenceAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, resp,
					contextData, methodName);
		}
		return resp;
	
	}
	
	@Override
	public InsertCustomerPreferenceAdapterResponse insertCustomerPreference(final InsertCustomerPreferenceAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".insertCustomerPreference()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())){
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}
		
		InsertCustomerPreferenceAdapterResponse resp = new InsertCustomerPreferenceAdapterResponse();
		InsertCustomerPreference req = Transformer.transform(request);
		InsertCustomerPreferenceResponse result = new InsertCustomerPreferenceResponse();
		
		try {
			result = getPort().insertCustomerPreference(req);
			resp = Transformer.transform(result);
			
		} catch (PolicyException e) {
			loggerUtil.error("", methodName, "", e);
			return (InsertCustomerPreferenceAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			loggerUtil.error("", methodName, "", e);
			return (InsertCustomerPreferenceAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), resp,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			loggerUtil.error("", methodName, "", e);
			return (InsertCustomerPreferenceAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, resp,
					contextData, methodName);
		}
		return resp;
	}


}
