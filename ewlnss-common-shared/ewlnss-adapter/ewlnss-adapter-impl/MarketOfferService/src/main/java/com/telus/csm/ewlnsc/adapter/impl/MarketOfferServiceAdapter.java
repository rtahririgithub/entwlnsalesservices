package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import com.telus.csm.ewlnsc.adapter.IMarketOfferServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.MarketOfferServiceTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.marketofferservicerequestresponse_v3.GetMarketOfferDetail;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.marketofferservicerequestresponse_v3.GetMarketOfferDetailResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v2.NameValuePair;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v2.Ping;
import com.telus.wsdl.mso.massmarketsalesmgmt.marketofferservice_3.MarketOfferServicePortType;
import com.telus.wsdl.mso.massmarketsalesmgmt.marketofferservice_3.MarketOfferServiceStub;
import com.telus.wsdl.mso.massmarketsalesmgmt.marketofferservice_3.ServiceException;

public class MarketOfferServiceAdapter extends SOAPServiceAdapter implements IMarketOfferServiceAdapter{

	private static final LoggerUtil logger = LoggerUtil.getLogger(MarketOfferServiceAdapter.class);
	
	private static final String ERROR_PREFIX = "MarketOfferServiceAdapter_";
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/marketOfferService/wsdlUrl}");
	
	public MarketOfferServiceAdapter(){
		super();
	}
	
	public MarketOfferServiceAdapter(AdapterFeatureDriver featureDriver){
		super(featureDriver);
		ICacheAdapter feasibilitySvcCacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(feasibilitySvcCacheAdapter);
	}
	
	@Override
	protected synchronized String getApplicationName() {
		return "APP_ENTRSALESSVC";
	}
	
	@Override
	public String getWsdlUrl(){
		return wsdlUrl;
	}
	
	
	private synchronized MarketOfferServicePortType getPort(){
		return (MarketOfferServicePortType) getInitilizedPort(); 
	}
	
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new MarketOfferServiceStub().getMarketOfferServicePort();
	}
	
	@Override
	public String ping() throws ServiceException {
		StringBuilder result = new StringBuilder();
		for (NameValuePair buildInfo : getPort().ping(new Ping()).getPingStats().getBuildInfo().getBuildProperty()) {
			result.append(buildInfo.getName() + "=" + buildInfo.getValue() +"\n");
		}
		return result.toString();
	}

	@Override
	public GetMarketOfferDetailAdapterResponse getMarketOfferDetail(GetMarketOfferDetailAdapterRequest request) {
		String functionName="getMarketOfferDetail";
		logger.enter(functionName);
		GetMarketOfferDetailAdapterResponse result = null;
		GetMarketOfferDetailResponse response = null;
		String contextData="";
		try{
			//Checking if Response already exist in the cache
			GetMarketOfferDetailAdapterResponse cachedResponse = super.getFromCache(request.getSalesTransactionId(),GetMarketOfferDetailAdapterResponse.class);
			if(cachedResponse!=null){
				return cachedResponse;
			}
			//Transforming AdapterRequest to downstream request
			GetMarketOfferDetail getMarketOfferDetailRequest = MarketOfferServiceTransformer.transform(request);
			contextData = functionName + " , TransactionId = " + request.getSalesTransactionId();
			response = getPort().getMarketOfferDetail(getMarketOfferDetailRequest);
			// Transforming the response to AdapterResponse
			result = MarketOfferServiceTransformer.transform(response);
			if (result != null && result.isSuccessfulProcessInd()) {
				super.saveToCache(request.getSalesTransactionId(), result);
			}
		}catch (ServiceException e){
			com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException sExc = new com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException();
			sExc.setErrorCode(e.getFaultInfo().getErrorCode());
			sExc.setErrorMessage(e.getFaultInfo().getErrorMessage());
			sExc.setMessageId(e.getFaultInfo().getMessageId());
			sExc.setVariables(e.getFaultInfo().getVariables());
			logger.error(null,functionName,e.getMessage(),e);
			result = new GetMarketOfferDetailAdapterResponse();
			return (GetMarketOfferDetailAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), (Exception) e,
							sExc, result,
							contextData, functionName, ERROR_PREFIX);
		}catch(Exception e){
			logger.error(null, functionName, e.getMessage(),e);
			result = new GetMarketOfferDetailAdapterResponse();
			return (GetMarketOfferDetailAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, result,
					contextData, functionName);
		}
		return result;
	}




}
