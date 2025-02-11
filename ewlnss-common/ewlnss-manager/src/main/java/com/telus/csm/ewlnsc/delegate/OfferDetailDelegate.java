package com.telus.csm.ewlnsc.delegate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IOfferInformationServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.transformer.GetSalesOfferDetailTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;

public class OfferDetailDelegate {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(OfferDetailDelegate.class);

	final IOfferInformationServiceAdapter offerInformationServiceAdapter = AdapterFactory
			.getAdapter(IOfferInformationServiceAdapter.class);

	/**************************************************/
	/* getOfferDetail */
	/**
	 * @param commonVO ************************************************/
	public GetOfferListAdapterResponse getOfferDetail(SalesOfferCommonVO commonVO, GetSalesOfferDetailRequestVO request) {
		GetOfferListAdapterResponse result = null;
		
		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		
		boolean isPromotionalOffer = !StringUtils.isEmpty(request.getPromotionCode());
		
		//Setting GetSalesOfferDetailRequest to commonOfferObject
		commonVO.setOfferDetailRequestVO(request);

		if (isPromotionalOffer) {
			
			/*
			 * checking if the promotional offer exist's in the cache
			 */
			String cacheKey = getCacheKeyForPromoCdOffers(request.getOperationHeader().getSalesTransactionId(), request.getOfferId(), request.getPromotionCode(), request.getSalesOfferCriteria().getOfferFilter().getProductList());
			
			Serializable cachedOffer = cacheAdapter.get(cacheKey);
			
			if(cachedOffer!=null && cachedOffer instanceof GetOfferListAdapterResponse){
				result = (GetOfferListAdapterResponse) cachedOffer;
				LOGGER.info("getOfferDetail", "PromoCode offer was found in cache with cacheKey=" +cacheKey);
				return result;
			}
			
			result = (new OfferSummaryListDelegate()).getOfferSummaryList(commonVO,false,"GSOD");
			filterByOfferId(result, request.getOfferIdLong());	
		} else {
			
			/*
			 * Checking if the offer detail exist in the cache.
			 */
			String cacheKey = getCacheKeyForOfferDetail(request.getOperationHeader().getSalesTransactionId(), request.getOfferId(), request.getSalesOfferCriteria().getOfferFilter().getProductList());
			
			Serializable cachedOffer = cacheAdapter.get(cacheKey);
			
			if(cachedOffer!=null && cachedOffer instanceof Offer){
				Offer offer = (Offer) cachedOffer;
				result = new GetOfferListAdapterResponse();
				List<Offer> offerList = new ArrayList<Offer>();
				offerList.add(offer);
				result.setOfferList(offerList);
				LOGGER.info("getOfferDetail", "OfferDetail was found in cache with cacheKey=" +cacheKey);
				return result;
			}
			
			final GetOfferListByOfferIdentifierListAdapterRequest oisRequest = GetSalesOfferDetailTransformer
					.transformToGetOfferRequest(request,commonVO);
				oisRequest.setWirelineTransactionalContext(WLNOfferUtil.getWirelineTransactionalContext(request, commonVO,false));
			result = offerInformationServiceAdapter
					.getOfferListByOfferIdentifierList(oisRequest);

		}

		return result;

	}

	private void filterByOfferId(GetOfferListAdapterResponse response, long requestOfferId) {
		List<Offer> offers = response.getOfferList();
		if (offers == null || offers.isEmpty()) {
			return;
		}
		
		ArrayList<Offer> matchedOffer = new ArrayList<Offer>();
		
		for (Offer o : offers) {
			if (requestOfferId == o.getOfferId()) {
				matchedOffer.add(o);
			} else {
				LOGGER.info("filterByOfferId", "Promotion offer filtered out. Offer Id=" + o.getOfferId());
			}
		}
		response.setOfferList(matchedOffer);
	}

		// 1. Look for cached GetOfferListForCustomerAdapterResponse by
		// transaction Id

		//TODO - add cache
//		GetOfferListForCustomerAdapterRequest getOfferListForCustomerAdapterRequest = new GetOfferListForCustomerAdapterRequest();
//		getOfferListForCustomerAdapterRequest.setSalesTransactionId(adapterRequest.getSalesTransactionId());
//		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
//		GetOfferListForCustomerAdapterResponse cachedResponse = cacheAdapter
//				.get(getOfferListForCustomerAdapterRequest.getCacheKey(), GetOfferListForCustomerAdapterResponse.class);

		// 2. Search for the offer in the cached
		// GetOfferListForCustomerAdapterResponse by offer Id
//		if (cachedResponse != null && cachedResponse.getOfferList() != null) {
//			long requestedOfferId = adapterRequest.getOfferIdentifierList().get(0).getOfferId();
//			for (Offer offer : cachedResponse.getOfferList()) {
//				if (offer.getOfferId() == requestedOfferId) {
//					result.add(offer);
//				}
//			}
//		}
		
//		if (!result.isEmpty()) {
//			return result; 
//		}

		// 3. Offer not found in cache - call
		// OIS.getOfferListByOfferIdentifierList

	public static String getCacheKeyForPromoCdOffers(String salesTxnId,String offerId,String promoCode, List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader> productList){
		StringBuilder sbCacheKey = new StringBuilder();
		String cacheKeySeparator="_";
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.SALES_TXN_ID);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(salesTxnId);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.OFFER_ID);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(offerId);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PROMO_CODE);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(promoCode);
		if(productList!=null && !productList.isEmpty()){
			for(com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader productHeader : productList){
				if(!StringUtils.isEmpty(productHeader.getContractTermCd())){
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_TYPE_CODE);
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(productHeader.getProductTypeCd());
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.OFFER_TERM);
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(productHeader.getContractTermCd());
				}
			}
		}
		return sbCacheKey.toString();
	}
	
	public static String getCacheKeyForOfferDetail(String salesTxnId,String offerId, List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader> productList){
		StringBuilder sbCacheKey = new StringBuilder();
		String cacheKeySeparator="_";
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.SALES_TXN_ID);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(salesTxnId);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(EnterpriseWLNSalesServicesConstants.OFFER_ID);
		sbCacheKey.append(cacheKeySeparator);
		sbCacheKey.append(offerId);
		if(productList!=null && !productList.isEmpty()){
			for(com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader productHeader : productList){
				if(!StringUtils.isEmpty(productHeader.getContractTermCd())){
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_TYPE_CODE);
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(productHeader.getProductTypeCd());
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.OFFER_TERM);
					sbCacheKey.append(cacheKeySeparator);
					sbCacheKey.append(productHeader.getContractTermCd());
				}
			}
		}
		return sbCacheKey.toString();
	}

}
