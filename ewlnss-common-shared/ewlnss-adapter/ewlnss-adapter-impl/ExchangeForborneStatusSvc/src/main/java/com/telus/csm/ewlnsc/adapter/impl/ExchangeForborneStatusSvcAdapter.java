package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IExchangeForborneStatusSvcAdapter;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterRequest;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterResponse;
import com.telus.csm.ewlnsc.adapter.xfs.transformer.Transformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.rmo.ordermgmt.exchangeforbornestatussvcrequestresponse_v1.GetForborneStatusByNpaNxxList;
import com.telus.tmi.xmlschema.srv.rmo.ordermgmt.exchangeforbornestatussvcrequestresponse_v1.GetForborneStatusByNpaNxxListResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.rmo.ordermgmt.exchangeforebornestatussvc_1.ExchangeForeborneStatusServicePortType;
import com.telus.wsdl.rmo.ordermgmt.exchangeforebornestatussvc_1.ExchangeForeborneStatusServiceStub;
import com.telus.wsdl.rmo.ordermgmt.exchangeforebornestatussvc_1.PolicyException;
import com.telus.wsdl.rmo.ordermgmt.exchangeforebornestatussvc_1.ServiceException;

public class ExchangeForborneStatusSvcAdapter extends SOAPServiceAdapter implements IExchangeForborneStatusSvcAdapter {
	
	private static final Logger LOGGER = Logger.getLogger(ExchangeForborneStatusSvcAdapter.class);
	private static final String ERROR_PREFIX = "ExchangeForborneStatusSvcAdapter_";
	private static final String TRANS_ID_STR = ", transId=";
	
	public ExchangeForborneStatusSvcAdapter() {
		super();
	}
	
	public ExchangeForborneStatusSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(cacheAdapter);
	}
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/exchangeForborneStatusSvc/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized ExchangeForeborneStatusServicePortType getPort() {
		return (ExchangeForeborneStatusServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new ExchangeForeborneStatusServiceStub().getExchangeForeborneStatusSvcPort();
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}
	
	@Override
	public ExchangeForborneStatusAdapterResponse getForborneStatusByNpaNxxList(final ExchangeForborneStatusAdapterRequest param) {
		ExchangeForborneStatusAdapterResponse result;
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".getForborneStatusByNpaNxxList()"; 
		String contextDataPolicy = methodName;
		
		result = super.getFromCache(param.getCacheKey(), ExchangeForborneStatusAdapterResponse.class);
		if (result != null) {
			return result;
		}
		
		LOGGER.info("calling down stream createCustomer");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId();
		
		result = new ExchangeForborneStatusAdapterResponse();
		final GetForborneStatusByNpaNxxList request = Transformer.transform(param);
		try {
			
			final GetForborneStatusByNpaNxxListResponse resp = getPort().getForborneStatusByNpaNxxList(request);
			result = Transformer.transform(resp);
			
		} catch (PolicyException e) {
			LOGGER.error(e);
			return (ExchangeForborneStatusAdapterResponse) this.handlePolicyException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);		
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (ExchangeForborneStatusAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (ExchangeForborneStatusAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		if (result != null) {
			super.saveToCache(param.getCacheKey(), result);
		}
		
		return result;
	}
	
}
