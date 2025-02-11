package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.ProductSpecificationMapper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductEligiblity;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;

public class FilterOfferByProductEligibilityProductComponentRule extends AbstractSpecification<Offer,TraceVO>{
	
	private SalesOfferCommonVO commonVO;
	private Map<String,List<WirelineOfferProduct>> offerProductsMap;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferByProductEligibilityProductComponentRule.class);

	public FilterOfferByProductEligibilityProductComponentRule(SalesOfferCommonVO commonVO,Map<String,List<WirelineOfferProduct>> offerProductsMap) {
		this.commonVO = commonVO;
		this.offerProductsMap = offerProductsMap;
	}
	
	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		boolean isSatisfiedBy=true;
		String functionName = "FilterOfferByProductEligibilityProductComponentRule->isSatisfiedBy";
		
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		// if productEligibility is not returned for this offer, skip the rule
		if(o.getProductEligiblityList()==null || o.getProductEligiblityList().isEmpty()) {
			logger.debug(functionName, "skipping rule because no productEligibilityList was returned for this offer");
			return true;
		}
		
		//There should be at least one offer returned for the product in the productEligibilityList 
		//or the product is an existing product
		// NWLN-7743 - Filter out the market offer if the required HS component from the offer's productEligibiltyList is not the matching HS from the offer request or from the existing product list
		if(o.getProductEligiblityList()!=null && !o.getProductEligiblityList().isEmpty()) {
			boolean requiredHSIC = false;
			for(ProductEligiblity requiredProduct : o.getProductEligiblityList()) {
				if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(requiredProduct.getProductTypeCd()) &&
						!requiredProduct.getProductComponentIdentifierList().isEmpty()){
					requiredHSIC = true;
				}
			}
			
			if(requiredHSIC){
				if(offerProductsMap != null || !getSubscribedProductList().isEmpty() ){
					logger.debug(functionName, "Offer has cross product dependency of HSIC product.");
					// NWLN-7743 - check is the depending HSIC product is in other offer
					if(productIsIncludedInProductEligiblityList(o.getProductEligiblityList())){
						logger.debug(functionName, "ProductCatalogueIdentifier from the request list were found among the list of ProductComponentIdentifierList in the ProductEligiblityList of the offer.");
						isSatisfiedBy = true;
					} else {
						logger.debug(functionName, "ProductCatalogueIdentifier from the request list were not found among the list of ProductComponentIdentifierList in the ProductEligiblityList of the offer.");
						if(isRequiredProductsCIDAlreadyAssigned(o.getProductEligiblityList())) {
							logger.debug(functionName, "ProductCatalogueIdentifier from the subscribed products were found among the list of ProductComponentIdentifierList in the ProductEligiblityList of the offer.");
							isSatisfiedBy = true;
						}else {
							String msg = "ProductCatalogueIdentifier from the subscribed products were not found among the list of ProductComponentIdentifierList in the ProductEligiblityList of the offer.";
							logger.debug(functionName, msg);
							isSatisfiedBy = false;
							log(o, traces, msg);
						}
					}
				}
			}
		}
		
		return isSatisfiedBy;
	}
	
	private List<SubscribedProductInfoRestVO> getSubscribedProductList(){
		List<SubscribedProductInfoRestVO> subscribedProductList = new ArrayList<SubscribedProductInfoRestVO>();
		if(commonVO!=null && commonVO.getAssignedAndPendingProductsResponseVO()!=null) {
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = commonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			if(assignedProducts!=null && !assignedProducts.isEmpty()) {
				for(SubscribedProductInfoRestVO subscribedProduct : assignedProducts) {
					subscribedProductList.add(subscribedProduct);
				}
			}
		}
		
		return subscribedProductList;
	}
	
	// NWLN-7743 - check is the depending HSIC product is in other offer
	private boolean productIsIncludedInProductEligiblityList(List<ProductEligiblity> productEligibilityList) {
		String functionName = "productIsIncludedInProductEligiblityList";
		boolean productIncluded = false;
		if(offerProductsMap != null && !offerProductsMap.isEmpty()) {
			for (Map.Entry<String, List<WirelineOfferProduct>> entry : offerProductsMap.entrySet()) {
				for(WirelineOfferProduct offerProduct: entry.getValue()){
					for(ProductEligiblity requiredProduct : productEligibilityList) {
						if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(requiredProduct.getProductTypeCd()) &&
							productIsIncludedInProductEligiblity(offerProduct,requiredProduct)) {
							logger.debug(functionName, "Offer: " + entry.getKey() + " has cross product dependency of HSIC product.");
							productIncluded = true;
							break;
						} else {
							logger.debug(functionName, "Offer: " + entry.getKey() + " does not has cross product dependency of HSIC product.");
						}
					}			
				}
			}
		}
		return productIncluded;
	}
	
	// NWLN-7743 - check is the depending HSIC product is in other offer
	private boolean productIsIncludedInProductEligiblity(WirelineOfferProduct offerProduct, ProductEligiblity requiredProduct) {
		String functionName = "productIsIncludedInProductEligiblity";
		List<String> requiredCid = new ArrayList<String>();
		List<String> offerCid = new ArrayList<String>();
		boolean productIsIncludedInProductEligiblity = false;
		if(requiredProduct.getProductTypeCd().equalsIgnoreCase(offerProduct.getProductTypeCode()) &&
				!requiredProduct.getProductComponentIdentifierList().isEmpty() &&
				!offerProduct.getProductComponentList().isEmpty()) {
			
			for(ProductCatalogueIdentifier productCatalogueIdentifier: requiredProduct.getProductComponentIdentifierList()){
				if(!StringUtils.isEmpty(productCatalogueIdentifier.getProductCatalogueId())){
					requiredCid.add(productCatalogueIdentifier.getProductCatalogueId());
				}
			}
			
			for(ProductComponent productComponent: offerProduct.getProductComponentList()){
				for(ProductCatalogueItem productCatalogueItem: productComponent.getProductCatalogueItemList()){
					if(productCatalogueItem.getProductCatalogueIdentifier() != null &&
							!StringUtils.isEmpty(productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId())){
						offerCid.add(productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId());
					}
				}
			}
			
			if(!requiredCid.isEmpty() && requiredCid.containsAll(offerCid)){
				productIsIncludedInProductEligiblity = true;
			}
		}
		return productIsIncludedInProductEligiblity;
	}

	// NWLN-7743 - check is the depending HSIC product is in customer existing product
	private boolean isRequiredProductsCIDAlreadyAssigned(List<ProductEligiblity> productEligibilityList) {
		String functionName = "isRequiredProductsCIDAlreadyAssigned";
		Map<String,List<String>> prodReqMap = new HashMap<String,List<String>>();
		if(!getSubscribedProductList().isEmpty()) {
			for(SubscribedProductInfoRestVO subscribedProduct : getSubscribedProductList()) {
				List<String> subscribedProductCatalogIdList = new ArrayList<String>();
				if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					String existingProductCatalogueId = WLNOfferUtil.getInternalCidFromProductTierCd(subscribedProduct.getProductTierCd());
					subscribedProductCatalogIdList.add(existingProductCatalogueId);
					prodReqMap.put(EnterpriseWLNSalesServicesConstants.HSIC, subscribedProductCatalogIdList);
				}
			}
		}
		
		if(!prodReqMap.isEmpty()){
			logger.debug(functionName, "Existing customer have the following cid for HS product: " + prodReqMap.get(EnterpriseWLNSalesServicesConstants.HSIC));
			return ExistingProductIsIncludedInProductEligiblityList(productEligibilityList, prodReqMap);	
		} else {
			return false; //qc71562
		}
	}
	
	// NWLN-7743 - check is the depending HSIC product is in customer existing product
	private boolean ExistingProductIsIncludedInProductEligiblityList(List<ProductEligiblity> productEligibilityList, Map<String,List<String>> prodReqMap) {
		String functionName = "ExistingProductIsIncludedInProductEligiblityList";
		if(!prodReqMap.isEmpty()) {
			for (Map.Entry<String, List<String>> entry : prodReqMap.entrySet()) {
				for(ProductEligiblity requiredProduct : productEligibilityList) {
					if(requiredProduct.getProductTypeCd().equalsIgnoreCase(entry.getKey()) && 
							!requiredProduct.getProductComponentIdentifierList().isEmpty() &&
							!requiredProduct.getProductComponentIdentifierList().containsAll(prodReqMap.get(entry.getKey()))){
						logger.debug(functionName, "Offer cid:" + requiredProduct.getProductComponentIdentifierList() +" doens't contain Existing customer HISC product cid: " + entry.getValue());
						return false;
					}
				}
			}
		}
		return true;
	}

	private void log(final Offer o, final List<TraceVO> traces, final String msg) {
		logger.error("Offer id: " + o.getOfferId() + " was filterred out, because " + msg);

		TraceVO t = TraceVO.newInstance(this);
		t.setAction(TraceVO.DELETED);
		t.setElementType(TraceVO.OFFER);
		t.setOffer(o);
		t.setReason(msg);
		traces.add(t);
	}
	
}
