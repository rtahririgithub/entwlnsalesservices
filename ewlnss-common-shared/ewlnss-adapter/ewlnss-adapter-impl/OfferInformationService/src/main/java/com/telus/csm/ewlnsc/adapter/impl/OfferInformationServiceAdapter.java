package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import com.telus.csm.ewlnsc.adapter.IOfferInformationServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetAccessoryOfferListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByPromotionCodeForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetSweetenerOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.transformer.OfferInformationServiceTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetAccessoryOfferList;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetAccessoryOfferListResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByOfferIdentifierList;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByOfferIdentifierListResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByPromotionCodeForCustomer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListByPromotionCodeForCustomerResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListForCustomer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetOfferListForCustomerResponse;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetSweetenerListByOfferSummaryListForCustomer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.GetSweetenerListByOfferSummaryListForCustomerResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.mso.massmarketsalesmgmt.offerinformationsvc_3.OfferInformationServicePortType;
import com.telus.wsdl.mso.massmarketsalesmgmt.offerinformationsvc_3.OfferInformationServiceStub;
import com.telus.wsdl.mso.massmarketsalesmgmt.offerinformationsvc_3.ServiceException;

public class OfferInformationServiceAdapter extends SOAPServiceAdapter implements IOfferInformationServiceAdapter{

	private static final LoggerUtil logger = LoggerUtil.getLogger(OfferInformationServiceAdapter.class);
	
	private static final String ERROR_PREFIX = "OfferInformationServiceAdapter_";
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/offerInformationService/wsdlUrl}");
	
	public OfferInformationServiceAdapter(){
		super();
	}
	
	public OfferInformationServiceAdapter(AdapterFeatureDriver featureDriver){
		super(featureDriver);
		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(cacheAdapter);
	}
	
	@Override
	public String ping() throws ServiceException {
		
		return getPort().ping(new Ping()).getVersion();
		
	}
	
	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new OfferInformationServiceStub().getOfferInformationSvcPort();
	}
	
	private synchronized OfferInformationServicePortType getPort(){
		return (OfferInformationServicePortType) getInitilizedPort();
	}

	@Override
	public GetOfferListAdapterResponse getOfferListByOfferIdentifierList(
			GetOfferListByOfferIdentifierListAdapterRequest request) {
		String functionName = "getOfferListByOfferIdentifierList";
		logger.enter(functionName);
		GetOfferListAdapterResponse result = null;
		GetOfferListByOfferIdentifierListResponse response = null;
		String contextData = "";
		try{
			
			//Transforming AdapterRequest to Downstream request
			GetOfferListByOfferIdentifierList getOfferListByOfferIdentifierListRequest = OfferInformationServiceTransformer.transform(request);
			contextData = functionName + " , TransactionId = " + request.getSalesTransactionId();
			response = getPort().getOfferListByOfferIdentifierList(getOfferListByOfferIdentifierListRequest);
			logger.info("getOfferListByOfferIdentifierList", "OIS returned " + response.getOfferList().size() + " Offers");
			//Transforming the response to AdapterResponse
			result = OfferInformationServiceTransformer.transform(response);
		}catch(ServiceException e){
			com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException sExc = new com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException();
			sExc.setErrorCode(e.getFaultInfo().getErrorCode());
			sExc.setErrorMessage(e.getFaultInfo().getErrorMessage());
			sExc.setMessageId(e.getFaultInfo().getMessageId());
			sExc.setVariables(e.getFaultInfo().getVariables());
			logger.error(null, functionName, e.getMessage(),e);
			result = new GetOfferListAdapterResponse();
			return (GetOfferListAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), (Exception) e,
							sExc, result,
							contextData, functionName, ERROR_PREFIX);
		}catch(Exception e){
			logger.error(null, functionName, e.getMessage(),e);
			result = new GetOfferListAdapterResponse();
			return (GetOfferListAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, result,
					contextData, functionName);
		}
		
		return result;
	}

	@Override
	public GetOfferListAdapterResponse getOfferListForCustomer(
			GetOfferListForCustomerAdapterRequest request) {
		String functionName="getOfferListForCustomer";
		logger.enter(functionName);
		GetOfferListAdapterResponse result = null;
		GetOfferListForCustomerResponse response = null;
		
		String contextData = "";
		try{

			//Transforming AdapterRequest to Service Request
			GetOfferListForCustomer getOfferListForCustomerRequest = OfferInformationServiceTransformer.transform(request);
			contextData = functionName + " , TransactionId = " + request.getSalesTransactionId();
			response = getPort().getOfferListForCustomer(getOfferListForCustomerRequest);
			logger.info("getOfferListForCustomer", "OIS returned " + response.getOfferList().size() + " Offers");
				result = OfferInformationServiceTransformer.transform(response);
				if(result!=null && !result.isMsgHasError()){
					logger.info(functionName, "GetOfferListForCustomerResponse was succesfully retrieved from OIS" /*, saving response into transactional cache with cacheKey=" + request.getCacheKey() */);
					//Saving offer detail into cache
					//saveOfferListInCache(request.getSalesTransactionId(),result.getOfferList());
				}
		}catch(ServiceException e){
			com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException sExc = new com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException();
			sExc.setErrorCode(e.getFaultInfo().getErrorCode());
			sExc.setErrorMessage(e.getFaultInfo().getErrorMessage());
			sExc.setMessageId(e.getFaultInfo().getMessageId());
			sExc.setVariables(e.getFaultInfo().getVariables());
			logger.error(null, functionName, e.getMessage(),e);
			result = new GetOfferListAdapterResponse();
			return (GetOfferListAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), (Exception) e,
							sExc, result,
							contextData, functionName, ERROR_PREFIX);
		}catch(Exception e){
			logger.error(null, functionName, e.getMessage(),e);
			result = new GetOfferListAdapterResponse();
			return (GetOfferListAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, result,
					contextData, functionName);
		}
		logger.exit(functionName);
		return result;
	}

//	
//	private void saveOfferListInCache(String salesTransactionId,List<Offer> offerList) {
//		String cacheSeparator="_";
//		if(offerList!=null && !offerList.isEmpty()){
//			for(Offer offer : offerList){
//				StringBuilder offerCacheKey = new StringBuilder();
//				offerCacheKey.append(EnterpriseWLNSalesServicesConstants.SALES_TXN_ID + cacheSeparator + salesTransactionId + cacheSeparator + EnterpriseWLNSalesServicesConstants.OFFER_ID + cacheSeparator + offer.getOfferId());
//				for(WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()){
//					List<BigInteger> temTermList = wirelineOfferProduct.getContractTermList();
//					
//					String contractTerm = "";
//					if(temTermList != null && !temTermList.isEmpty()){
//						contractTerm = temTermList.get(0).toString();
//					}
//					offerCacheKey.append(cacheSeparator + EnterpriseWLNSalesServicesConstants.PRODUCT_TYPE_CODE + cacheSeparator + wirelineOfferProduct.getProductTypeCode() +  cacheSeparator + EnterpriseWLNSalesServicesConstants.OFFER_TERM + cacheSeparator + contractTerm);
//				}				
//				super.saveToCache(offerCacheKey.toString(), offer);
//			}
//		}
//	}
//	
//	private void saveOffersByPromoCode(String salesTransactionId, List<Offer> offerList, ProgramPromotion programPromotion){
//		String cacheSeparator="_";
//		String promoCode=null;
//		if(programPromotion!=null && !StringUtils.isEmpty(programPromotion.getPromotionCode())){
//			promoCode = programPromotion.getPromotionCode();
//		}
//		if(offerList!=null && !offerList.isEmpty()){
//			for(Offer offer : offerList){
//				GetOfferListAdapterResponse result = new GetOfferListAdapterResponse();
//				result.setOfferList(Arrays.asList(offer));
//				result.setProgramPromotion(programPromotion);
//				result.setSuccessfulProcessInd(true);
//				StringBuilder sbCacheKey = new StringBuilder();
//				sbCacheKey.append(EnterpriseWLNSalesServicesConstants.SALES_TXN_ID + cacheSeparator + salesTransactionId + cacheSeparator + EnterpriseWLNSalesServicesConstants.OFFER_ID + cacheSeparator + offer.getOfferId() + cacheSeparator + EnterpriseWLNSalesServicesConstants.PROMO_CODE + cacheSeparator + promoCode);
//				for(WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()){
//					List<BigInteger> temTermList = wirelineOfferProduct.getContractTermList();
//					String contractTerm = "";
//					if(temTermList != null && !temTermList.isEmpty()){
//						contractTerm = temTermList.get(0).toString();
//					}
//					sbCacheKey.append(cacheSeparator + EnterpriseWLNSalesServicesConstants.PRODUCT_TYPE_CODE + cacheSeparator + wirelineOfferProduct.getProductTypeCode() + cacheSeparator + EnterpriseWLNSalesServicesConstants.OFFER_TERM + cacheSeparator + contractTerm);
//				}
//				super.saveToCache(sbCacheKey.toString(), result);
//			}
//		}
//	}

	/*****************************************************/
	/* getOfferListByPromotionCodeForCustomer            */
	/*****************************************************/
	public GetOfferListAdapterResponse getOfferListByPromotionCodeForCustomer(GetOfferListByPromotionCodeForCustomerAdapterRequest request){
		String functionName="getOfferListByPromotionCodeForCustomer";
		
		logger.enter(functionName);
		
		GetOfferListAdapterResponse result = null;
		GetOfferListByPromotionCodeForCustomerResponse response = null;
		
		String contextData = functionName + " , TransactionId = " + request.getSalesTransactionId();
		try{
			
			/**************************************/
			/* transform request, call downstream */
			/**************************************/
			GetOfferListByPromotionCodeForCustomer getOfferListByPromotionCodeForCustomer = OfferInformationServiceTransformer.transform(request);
			response = getPort().getOfferListByPromotionCodeForCustomer(getOfferListByPromotionCodeForCustomer);
			if(response.getPromoCodeOfferListResponse()!=null){
				logger.info("getOfferListByPromotionCodeForCustomer", "OIS returned " + response.getPromoCodeOfferListResponse().getOfferList().size() + " Offers");
			}
			
			
			result = OfferInformationServiceTransformer.transform(response);
			
			if(result!=null && result.isSuccessfulProcessInd()){
				logger.info(functionName, "GetOfferListForCustomerResponse was succesfully retrieved from OIS" /*, saving response into transactional cache with cacheKey=" + request.getCacheKey() */);
				
				//For each of the offers in the promocode response, I shall save the offer at detail level,
				//but at meanwhile I shall save the full promocode response, the cacheKey, shall be the txn_id + promocode 
				
				//level caching on this operation
				/*
				 * 
				 * Save offer detail and Program promotion object from getOfferListByPromotionCodeForCustomer into separate cache
				 */
				
				//saveOffersByPromoCode(request.getSalesTransactionId(),result.getOfferList(),result.getProgramPromotion());
				
			}
			
		} catch(ServiceException serivceException){
			com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException sExc = this.buildV1ServiceException(serivceException);
			logger.error(null, functionName, serivceException.getMessage(), serivceException); 
			
			result = (GetOfferListAdapterResponse) this.handleServiceException(request.getSalesTransactionId(), 
					                                                                                     (Exception) serivceException,
																	                                     sExc, 
																	                                     new GetOfferListAdapterResponse(),
																	                                     contextData, 
																	                                     functionName, 
																	                                     ERROR_PREFIX);
			result.setSuccessfulProcessInd(false);
			
		}catch(Exception exception){
			
			logger.error(null, functionName, exception.getMessage(), exception); 
			
			result =  (GetOfferListAdapterResponse) this.handleException(request.getSalesTransactionId(), 
					                                                                               exception, 
					                                                                               new GetOfferListAdapterResponse(),
					                                                                               contextData, 
					                                                                               functionName);
			
			result.setSuccessfulProcessInd(false);
		}
		logger.exit(functionName);
		return result;
	}


	@Override
	public GetOfferListAdapterResponse getSweetenerListByOfferSummaryListForCustomer(GetSweetenerOfferListForCustomerAdapterRequest request) {
		String functionName="getSweetenerListByOfferSummaryListForCustomer";
		logger.enter(functionName);
		GetOfferListAdapterResponse result = null;
		GetSweetenerListByOfferSummaryListForCustomerResponse response = null;

		String contextData = "";
		try{
			//Checking if the response already exists in the cache
//			GetOfferListAdapterResponse cachedResponse = super.getFromCache(request.getCacheKey(), GetOfferListAdapterResponse.class);
//			if(cachedResponse!=null){
//				logger.info(functionName, "GetSweetenerListByOfferSummaryListForCustomerResponse was found in the cache for cacheKey= " + request.getCacheKey());
//				return cachedResponse;
//			}else{
//				logger.info(functionName, "GetSweetenerListByOfferSummaryListForCustomerResponse was not found in the cache for cacheKey=" + request.getCacheKey() + " , will retrieve Response from Service.");
//			}

			//Transforming AdapterRequest to Service Request
			GetSweetenerListByOfferSummaryListForCustomer getSweetenerListByOfferSummaryListForCustomerRequest = OfferInformationServiceTransformer.transform(request);
			contextData = functionName + " , TransactionId = " + request.getSalesTransactionId();
			response = getPort().getSweetenerListByOfferSummaryListForCustomer(getSweetenerListByOfferSummaryListForCustomerRequest);
			logger.info("getSweetenerListByOfferSummaryListForCustomer", "OIS returned " + response.getSweetenerList().size() + " Sweeteners");
				result = OfferInformationServiceTransformer.transform(response);
				if(result!=null && !result.isMsgHasError()){
					logger.info(functionName, "GetSweetenerListByOfferSummaryListForCustomer was succesfully retrieved from OIS" /*, saving response into transactional cache with cacheKey=" + request.getCacheKey() */);
					//super.saveToCache(request.getCacheKey(), result);
				}
		}catch(ServiceException e){
			com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException sExc = new com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException();
			sExc.setErrorCode(e.getFaultInfo().getErrorCode());
			sExc.setErrorMessage(e.getFaultInfo().getErrorMessage());
			sExc.setMessageId(e.getFaultInfo().getMessageId());
			sExc.setVariables(e.getFaultInfo().getVariables());
			logger.error(null, functionName, e.getMessage(),e);
			result = new GetOfferListAdapterResponse();
			return (GetOfferListAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), (Exception) e,
							sExc, result,
							contextData, functionName, ERROR_PREFIX);
		}catch(Exception e){
			logger.error(null, functionName, e.getMessage(),e);
			result = new GetOfferListAdapterResponse();
			return (GetOfferListAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, result,
					contextData, functionName);
		}
		logger.exit(functionName);
		return result;
	}

	private com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException buildV1ServiceException(ServiceException serviceException){
		com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException sExc = new com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException();
		
		sExc.setErrorCode(serviceException.getFaultInfo().getErrorCode());
		sExc.setErrorMessage(serviceException.getFaultInfo().getErrorMessage());
		sExc.setMessageId(serviceException.getFaultInfo().getMessageId());
		sExc.setVariables(serviceException.getFaultInfo().getVariables());
		
		return sExc;
	}

	@Override
	public GetOfferListAdapterResponse getAccessoryOfferList(GetAccessoryOfferListAdapterRequest request) {
		String functionName="getAccessoryOfferList";
		
		logger.enter(functionName);
		
		GetOfferListAdapterResponse result = null;
		GetAccessoryOfferListResponse response = null;
		
		String contextData = functionName + " , TransactionId = " + request.getSalesTransactionId();

		try
		{
			/**************************************/
			/* transform request, call downstream */
			/**************************************/
		    GetAccessoryOfferList getAccessoryOfferList = OfferInformationServiceTransformer.transform(request);
			response = getPort().getAccessoryOfferList(getAccessoryOfferList);

			if ( (response != null) && (response.getOfferList() != null) && (!response.getOfferList().isEmpty()) ) {
				logger.info("getAccessoryOfferSummaryList", "OIS returned " + response.getOfferList().size() + " offers");
			}

			result = OfferInformationServiceTransformer.transform(response);
			
			if ( (result != null) && (result.isSuccessfulProcessInd())){
				logger.info(functionName, "GetAccessoryOfferListResponse was succesfully retrieved from OIS");
			}
			
		}
		catch (ServiceException serivceException){
			com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException sExc = this.buildV1ServiceException(serivceException);
			logger.error(null, functionName, serivceException.getMessage(), serivceException); 
			
			result = (GetOfferListAdapterResponse) this.handleServiceException(request.getSalesTransactionId(), 
					                                                                                     (Exception) serivceException,
																	                                     sExc, 
																	                                     new GetOfferListAdapterResponse(),
																	                                     contextData, 
																	                                     functionName, 
																	                                     ERROR_PREFIX);
			result.setSuccessfulProcessInd(false);
			
		}
		catch (Exception exception){
			
			logger.error(null, functionName, exception.getMessage(), exception); 
			
			result =  (GetOfferListAdapterResponse) this.handleException(request.getSalesTransactionId(), 
					                                                                               exception, 
					                                                                               new GetOfferListAdapterResponse(),
					                                                                               contextData, 
					                                                                               functionName);
			
			result.setSuccessfulProcessInd(false);
		}
		logger.exit(functionName);
		return result;
	}
	
	


}
