/**
 * 
 */
package com.telus.csm.ewlnsc.adapter.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.telus.csm.ewlnsc.adapter.IReferencePDSDataServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.BusinessRuleInstanceTypeVO;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.ReferencePDSDataServiceTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetInstanceResponse;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetInstances;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetInstancesResponse;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetReferenceData;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetReferenceDataResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.erm.refpds.referencepdsdataservice_1.PolicyException;
import com.telus.wsdl.erm.refpds.referencepdsdataservice_1.ReferencePDSDataServicePortType;
import com.telus.wsdl.erm.refpds.referencepdsdataservice_1.ReferencePDSDataServiceStub;
import com.telus.wsdl.erm.refpds.referencepdsdataservice_1.ServiceException;

/**
 * @author Alejandro.Hernandez
 *
 */
public class ReferencePDSDataServiceAdapter extends SOAPServiceAdapter implements IReferencePDSDataServiceAdapter{

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ReferencePDSDataServiceAdapter.class);
	
	private static final String ERROR_PREFIX = "ReferencePDSDataServiceAdapter_";
	
	private ICacheAdapter cacheAdapter;

	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/refpds/wsdlUrl}");
	
	public ReferencePDSDataServiceAdapter() {
		super();
	}
	
	public ReferencePDSDataServiceAdapter(AdapterFeatureDriver featureDriver) {
		super(featureDriver);
		cacheAdapter = CacheAdapterFactory.getCacheAdapter(EnterpriseWLNSalesServicesConstants.REFPDS_DATA_SERVICE_CACHE_NAME);
	}
	
	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}
	
	private synchronized ReferencePDSDataServicePortType getPort(){
		return (ReferencePDSDataServicePortType) getInitilizedPort();
	}
	
	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

	



	/* (non-Javadoc)
	 * @see com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter#getNewPort()
	 */
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new ReferencePDSDataServiceStub().getReferencePDSDataServicePort();
	}
	
	/*
	 * Summary:
	 * Step 1 - check if requested table is present in the cache
	 * 		Step 1a - If requested table is in the cache, get Map from the cache
	 * Step 2 - If no requested table in the cache, check if ReferencePDSResponse object is in the cache
	 * 		Step 2a if ReferencePDSResponse object is in the cache, get the Response from the cache and save to cache the requested Entity information 
	 * Step 3 - If no RefPDSResponse object is in the cache, call downstream and cache the response and also save to cache the requested Entity information
	 * Step 4 - Return Entity as HashMap to the calling class.
	 */

	@Override
	public GetReferencePDSDataServiceAdapterResponse getReferenceData(
			GetReferencePDSDataServiceAdapterRequest getReferencePdsRequestDO, String entityName) {
		String functionName = "getReferenceData";
		//LOGGER.enter(functionName);
		GetReferencePDSDataServiceAdapterResponse result = null;
		GetReferenceDataResponse response = null;
		String contextData="";
		try{
			//CacheKey to get RefPDSResponseObject from the cache.
			String refPdsRespCacheKey = getReferencePdsRequestDO.getCacheKey();
			
			//Checking if the Entity or the Response object are stored in the cache.
			HashMap<String, String> refPdsExistingInCacheMap = checkRefPDSExistingCache(entityName,refPdsRespCacheKey);			
			
			//If returned HashMap is null, then call ReferencePDSDataService
			if(refPdsExistingInCacheMap!=null && refPdsExistingInCacheMap.size() > 0){
				result = new GetReferencePDSDataServiceAdapterResponse();
				result.setReferencePDSTableMap(refPdsExistingInCacheMap);
				return result;
			}
			//Transforming from Adapter Request to Downstream schema Request
			GetReferenceData request = ReferencePDSDataServiceTransformer.transformRequest(getReferencePdsRequestDO);
			contextData=functionName + "Call to Service made with AppId: [" + getReferencePdsRequestDO.getAppId() + "], RefPds table = " + entityName;
			response = getPort().getReferenceData(request);
			if(response!=null){
				//LOGGER.info(functionName, "Transforming GetReferenceDataResponse.");
				result = ReferencePDSDataServiceTransformer.transformResponse(response, entityName);
				//LOGGER.info(functionName, "Saving ReferencePDSDataServiceResponse in the cache with cacheKey: " + refPdsRespCacheKey + ", at : " + new Date());
				cacheAdapter.put(refPdsRespCacheKey, response);
				String entityCacheKey = getEntityCacheKey(entityName);
				//LOGGER.info(functionName, "Saving RefPDSEntity table in cache with cacheKey: " + entityCacheKey);
				HashMap<String,String> refPdsEntityMap = (HashMap<String, String>) result.getReferencePDSTableMap();
				if (refPdsEntityMap.size() > 0)
					cacheAdapter.put(entityCacheKey, refPdsEntityMap);
				else if (result.getReferencePDSTableObjectMap().size() > 0){
					cacheAdapter.put(entityCacheKey, refPdsEntityMap);
				}
			}
		}catch(PolicyException e){
			LOGGER.error(null,functionName,e.getMessage(),e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this
					.handlePolicyException(
							contextData, e,
							e.getFaultInfo(), result,
							contextData, functionName, ERROR_PREFIX);
		}catch(ServiceException e){
			LOGGER.error(null, functionName, e.getMessage(),e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this
					.handleServiceException(
							contextData, e,
							e.getFaultInfo(), result,
							contextData, functionName, ERROR_PREFIX);
		}catch(Exception e){
			LOGGER.error(null, functionName, e.getMessage(),e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this.handleException(
					contextData, e, result,
					contextData, functionName);
		}
		return result;
	}

	/*
	 * Summary:
	 * Step 1 - check if requested table is present in the cache
	 * 		Step 1a - If requested table is in the cache, get Map from the cache
	 * Step 2 - If no requested table in the cache, check if ReferencePDSResponse object is in the cache
	 * 		Step 2a if ReferencePDSResponse object is in the cache, get the Response from the cache and save to cache the requested Entity information 
	 * Step 3 - If no RefPDSResponse object is in the cache, call downstream and cache the response and also save to cache the requested Entity information
	 * Step 4 - Return Entity as HashMap to the calling class.
	 */

	@Override
	public GetReferencePDSDataServiceAdapterResponse getReferenceData(
			GetReferencePDSDataServiceAdapterRequest getReferencePdsRequestDO) {
		String functionName = "getReferenceData - without entityName";
		//LOGGER.enter(functionName);		
		
		GetReferencePDSDataServiceAdapterResponse result = null;
		GetReferenceDataResponse response = null;
		String contextData="";
		try{
			//CacheKey to get RefPDSResponseObject from the cache.
			String refPdsRespCacheKey = getReferencePdsRequestDO.getCacheKey();
			
			Object refPdsResponseObj = cacheAdapter.get(refPdsRespCacheKey);
			
			if(refPdsResponseObj!=null){
				result = new GetReferencePDSDataServiceAdapterResponse();
				result = ReferencePDSDataServiceTransformer.transformResponse((GetReferenceDataResponse)refPdsResponseObj);				
				return result;
			}
			
			//Transforming from Adapter Request to Downstream schema Request
			GetReferenceData request = ReferencePDSDataServiceTransformer.transformRequest(getReferencePdsRequestDO);
			contextData=functionName + "Call to Service made with AppId: [" + getReferencePdsRequestDO.getAppId() + "]";
			response = getPort().getReferenceData(request);
			if(response!=null){
				//LOGGER.info(functionName, "Transforming GetReferenceDataResponse.");
				
				result = ReferencePDSDataServiceTransformer.transformResponse(response);
				
				//LOGGER.info(functionName, "Saving ReferencePDSDataServiceResponse in the cache with cacheKey: " + refPdsRespCacheKey + ", at : " + new Date());
				cacheAdapter.put(refPdsRespCacheKey, response);
				
			}
		}catch(PolicyException e){
			LOGGER.error(null,functionName,e.getMessage(),e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this
					.handlePolicyException(
							contextData, e,
							e.getFaultInfo(), result,
							contextData, functionName, ERROR_PREFIX);
		}catch(ServiceException e){
			LOGGER.error(null, functionName, e.getMessage(),e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this
					.handleServiceException(
							contextData, e,
							e.getFaultInfo(), result,
							contextData, functionName, ERROR_PREFIX);
		}catch(Exception e){
			LOGGER.error(null, functionName, e.getMessage(),e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this.handleException(
					contextData, e, result,
					contextData, functionName);
		}
		return result;
	}
	
	private String getEntityCacheKey(String entityName){
		String ewssVersion = EnterpriseWLNSalesServicesConstants.EWSS_VERSION_VALUE;
		return "EWSS" + EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + this.getClass().getSimpleName().substring(0,26) + EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + ewssVersion + EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + "cacheKey" + EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + entityName;
	}
	
	private HashMap<String,String> checkRefPDSExistingCache(String refPDSTableName,String refPdsRespCacheKey){
		HashMap<String,String> refPdsEntity = null;
		String functionName = "checkRefPDSExistingCache";
		//LOGGER.enter(functionName);
		// Step 1 - check if requested table is present in the cache
		String entityCacheKey = getEntityCacheKey(refPDSTableName);
		
		Object entityObj = cacheAdapter.get(entityCacheKey);
		
		if(entityObj!=null && entityObj instanceof HashMap){
			//Step 1a - If requested table is in cache return Map from the cache
			refPdsEntity = (HashMap<String, String>) entityObj;
			//LOGGER.info(functionName, "Entity Map was found in the cache with cacheKey: " + entityCacheKey);
			return  refPdsEntity;
		}else{
			// Step 2 - If no requested table in the cache, check if ReferencePDSResponse object is in the cache
			//LOGGER.info(functionName, "Entity Map was not found in the cache. Checking if ReferencePDSResponse is in the cache");
			Object refPdsResponseObj = cacheAdapter.get(refPdsRespCacheKey);
			if(refPdsResponseObj!=null && refPdsResponseObj instanceof GetReferenceDataResponse){
				//2a if ReferencePDSResponse object is in the cache, get the Response from the cache and save to cache the requested Entity information 
				//LOGGER.info(functionName, "ReferencePDSResponse object found in cache with cacheKey: " + refPdsRespCacheKey);
				GetReferenceDataResponse refPdsResponse = (GetReferenceDataResponse) refPdsResponseObj;
				GetReferencePDSDataServiceAdapterResponse refPdsAdapterResponse = ReferencePDSDataServiceTransformer.transformResponse(refPdsResponse, refPDSTableName);
				refPdsEntity = (HashMap<String, String>) refPdsAdapterResponse.getReferencePDSTableMap();
				//Put entity back in the cache.
				cacheAdapter.put(entityCacheKey,refPdsEntity);
				//LOGGER.info(functionName, "RefPDS Entity saved in cache with cacheKey: " + entityCacheKey);
				return refPdsEntity;
			}else{
				LOGGER.info(functionName, "No ReferencePDSResponse found in cache, calling ReferencePDSDataService");
			}
		}
		//LOGGER.exit(functionName);
		return refPdsEntity;
	}

	@Override
	public GetReferencePDSDataServiceAdapterResponse getBusinessRuleInstances(
			GetReferencePDSDataServiceAdapterRequest getReferencePdsRequestDO, String entityName) {
		String functionName = "getInstances";
		GetReferencePDSDataServiceAdapterResponse result = new GetReferencePDSDataServiceAdapterResponse();
		GetInstancesResponse wsResponse = null;
		String contextData = functionName + "Call to Service made with AppId: [" + getReferencePdsRequestDO.getAppId()
				+ "], RefPds table = " + entityName;
		
		
		//Get From Cache.
		String entityCacheKey = getEntityCacheKey(entityName);
		Object entityObj = cacheAdapter.get(entityCacheKey);
		if(entityObj != null) {
			wsResponse = (GetInstancesResponse) entityObj;
			List<BusinessRuleInstanceTypeVO> businessRules = ReferencePDSDataServiceTransformer
					.transformBusinessRuleResponse(wsResponse);
			result.setRuleList(businessRules);
			return result;
		}
		
		//Get From service call.
		try {
			GetInstances wsRequest = new GetInstances();
			wsRequest.setAppId(getReferencePdsRequestDO.getAppId());
			wsRequest.setRefEntityId(entityName);
			wsResponse = getPort().getInstances(wsRequest);
			List<BusinessRuleInstanceTypeVO> businessRules = ReferencePDSDataServiceTransformer
					.transformBusinessRuleResponse(wsResponse);
			result.setRuleList(businessRules);
			cacheAdapter.put(entityCacheKey, wsResponse);
		} catch (PolicyException e) {
			LOGGER.error(null, functionName, e.getMessage(), e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this.handlePolicyException(contextData, e,
					e.getFaultInfo(), result, contextData, functionName, ERROR_PREFIX);
		} catch (ServiceException e) {
			LOGGER.error(null, functionName, e.getMessage(), e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this.handleServiceException(contextData, e,
					e.getFaultInfo(), result, contextData, functionName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(null, functionName, e.getMessage(), e);
			result = new GetReferencePDSDataServiceAdapterResponse();
			return (GetReferencePDSDataServiceAdapterResponse) this.handleException(contextData, e, result, contextData,
					functionName);
		}
		return result;
	}
	

}
