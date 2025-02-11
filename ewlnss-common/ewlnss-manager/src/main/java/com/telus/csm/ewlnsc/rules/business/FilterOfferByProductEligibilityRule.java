package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductEligiblity;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferByProductEligibilityRule extends AbstractSpecification<Offer,TraceVO>{
	
	private SalesOfferCommonVO commonVO;
	private Map<String,List<WirelineOfferProduct>> offerProductsMap;
	Map<String,List<ProductEligiblity>> eligibilityProductsMap;
	private List<String> requestedProductList;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferByProductEligibilityRule.class);

	public FilterOfferByProductEligibilityRule(SalesOfferCommonVO commonVO,Map<String,List<ProductEligiblity>> eligibilityProductsMap,Map<String,List<WirelineOfferProduct>> offerProductsMap, List<String> requestedProductList) {
		this.commonVO = commonVO;
		this.offerProductsMap = offerProductsMap;
		this.eligibilityProductsMap = eligibilityProductsMap;
		this.requestedProductList = requestedProductList;
	}
	
	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		boolean isSatisfiedBy=false;
		String functionName = "FilterOfferByProductEligibilityRule->isSatisfiedBy";
		
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		// if productEligibility is not returned for this offer, skip the rule
		if(o.getProductEligiblityList()==null || o.getProductEligiblityList().isEmpty()) {
			logger.debug(functionName, "skipping rule because no productEligibilityList was returned for this offer");
			return true;
		}
		
		// NWLN-11627 if CPE offer under HSIC template skip the rule, and let it use FilterAccessoryOfferByTemplateProduct
		if(isCPEOfferUnderOtherTemplate(o)) {
			logger.debug(functionName, "skipping rule because this CPE offer under HSIC template");
			return true;
		}
		
		//There should be at least one offer returned for the product in the productEligibilityList 
		//or the product is an existing product
		
		if(o.getProductEligiblityList()!=null && !o.getProductEligiblityList().isEmpty()) {
			
			if(productsAreExistingFromReturnedOffers(o, o.getProductEligiblityList())) {
				logger.debug(functionName, "Required products for offer: " + String.valueOf(o.getOfferId()) + " were found among the list of offers returned by OIS.");
				return true;
			}else {
				String msg = "Required products for offer: " + String.valueOf(o.getOfferId()) + " were NOT found in the subscribed products of the customer nor Returned offers from OIS.";
				logger.debug(functionName, msg);
				isSatisfiedBy = false;
				log(o, traces, msg);
			}
		}
		
		return isSatisfiedBy;
	}
	
	

	/*private boolean pikTvValidation(Offer o) {
		String functionName="pikTvValidation";
		boolean validationResult=false;
		//-	If offer product is pikTV, productEligibilitylist has HSIC, then HSIC ranking must be equal or better than assigned HSIC product
		String templateId = null;
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(product.getProductTypeCode())) {
				if (product.getProductTemplateIdentifier() != null) {
					templateId = product.getProductTemplateIdentifier().getExternalProductCatalogId();
					break;
				}
			}
			
		}
		
		if(WLNOfferUtil.isPikTvCatalogId(templateId)) {
			//checking if productEligibilityList has HSIC.
			for(ProductEligiblity requiredProduct : o.getProductEligiblityList()) {
				if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(requiredProduct.getProductTypeCd())) {
					logger.debug("pikTvValidation","PikTv offer and HSIC is part of productEligibilityList... checking required HSIC ranking to be better or equal than the existing HSIC product");
					
					String hiSpeedPackCode = WLNOfferUtil.mapOmsCode(getExternalCidFromRequiredProduct(requiredProduct.getProductTemplateIdentifier()));
					
					String subscribedProductTierCd = WLNOfferUtil.getHsicSubscribedProductTierCdFromAssignedAndPendingProductsResponse(commonVO);
					
					if (hiSpeedPackCode == null || "".equals(hiSpeedPackCode)) {
						logger.debug(functionName, "HSIC Tier code from Required product is not available - not filtering rule");
						validationResult  =true;
						break;
					}else {
						logger.debug(functionName, "HSIC tier code was found for the required product: " + hiSpeedPackCode);
						
						int subscribedProductPlanRanking = WLNOfferUtil.calculateLowestRankingForPP(subscribedProductTierCd,commonVO);
						int offerPlanRanking = WLNOfferUtil.calculateLowestRankingForPP(hiSpeedPackCode,commonVO);
						String requiredProductTerm = null;
						if(requiredProduct.getContractTermList()!=null && !requiredProduct.getContractTermList().isEmpty()){
							requiredProductTerm = requiredProduct.getContractTermList().toString();
							
						}
						
						String currentTerm= WLNOfferUtil.getContracTermInMonthByYears(WLNOfferUtil.getCurrentHsicTerm(commonVO));
						
						if (requiredProductTerm != null){
			                int offerTerm = Integer.valueOf(requiredProductTerm);
			                
			                if(EnterpriseWLNSalesServicesConstants.ZERO.equals(currentTerm) && offerTerm > 0){
			                      if (offerPlanRanking <= subscribedProductPlanRanking) {
			                    	  validationResult  =true;
										break;
			                      }
			                }
						}

						if (offerPlanRanking < subscribedProductPlanRanking) {
							validationResult = true;
							break;
						}								
					}
				}
			}
			
		}else {
			validationResult = true;
		}
		return validationResult;
	}

	private boolean requiredProductsAreQualified(List<ProductEligiblity> productEligiblityList) {
		boolean productsAreQualified = false;
		for(ProductEligiblity requiredProduct : productEligiblityList) {
			//for each required product, we have to search for the product in the productQualificationList, if the product is not present, then return false
			if(requiredProductIsQualified(requiredProduct)) {
				productsAreQualified = true;
				logger.debug("requiredProductsAreQualified", "required product: " + requiredProduct.getProductTypeCd() + " is qualified");
			}
		}
		return productsAreQualified;
	}
*/
	/*private boolean requiredProductIsQualified(ProductEligiblity requiredProduct) {
		boolean productIsQualified = false;
		if(commonVO!=null && commonVO.getProductQualificationAdapterResponseVO()!=null
				&& commonVO.getProductQualificationAdapterResponseVO().getProductQualificationList()!=null) {
			List<ProductQualification> productQualificationList = commonVO.getProductQualificationAdapterResponseVO().getProductQualificationList();
			for(ProductQualification productQualification : productQualificationList) {
				List<Product> productList = productQualification.getProductList();
				for(Product product : productList) {
					if(requiredProduct.getProductTypeCd().equalsIgnoreCase(product.getProductTypeCd())) {
						if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(requiredProduct.getProductTypeCd())) {
							//when HSIC, need to compare the tier as well
							String externalCid = getExternalCidFromRequiredProduct(requiredProduct.getProductTemplateIdentifier());
							CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
							CatalogueItemDO catalogueItemById = gridHelper.getCatalogueItemById(requiredProduct.getProductTemplateIdentifier().getProductCatalogueId());
							logger.debug("rule checking", catalogueItemById.getProductCode());
							if(!StringUtils.isBlank(externalCid)) {
								String requiredProductHsicTierCode = WLNOfferUtil.mapOmsCode(externalCid);
								if(requiredProductHsicTierCode.equalsIgnoreCase(product.getProductTierCd()) &&
										(product.getUnavailableInd() == null
										|| !product.getUnavailableInd().booleanValue())){
											productIsQualified=true;
											break;
										}
							}
						}else {
							if(product.getUnavailableInd() == null
										|| !product.getUnavailableInd().booleanValue()) {
								productIsQualified=true;
								break;
							}
						}
					}

				}
				
			}
		}
		return productIsQualified;
	}
*/
	/*private String getExternalCidFromRequiredProduct(ProductCatalogueIdentifier productTemplateIdentifier) {
		String externalCid = null;
		if(productTemplateIdentifier!=null) {
			externalCid = productTemplateIdentifier.getExternalProductCatalogId();
		}
		
		return externalCid;
	}*/

	private List<SubscribedProductInfoRestVO> getAssignedProduct() {
		List<SubscribedProductInfoRestVO> subscribedProductList = new ArrayList<SubscribedProductInfoRestVO>();
		if(commonVO!=null && commonVO.getAssignedAndPendingProductsResponseVO()!=null) {
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = commonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			if(assignedProducts!=null && !assignedProducts.isEmpty()) {
				for(SubscribedProductInfoRestVO subscribedProduct : assignedProducts) {
					// NWLN-11627 HS0 do not count as existing product
					if(!Constants.HSZERO.equalsIgnoreCase(subscribedProduct.getProductTierCd()))
						subscribedProductList.add(subscribedProduct);
				}
				
			}
		}

		return subscribedProductList;
	}

	private boolean requiredProductExists(ProductEligiblity requiredProduct,
			List<SubscribedProductInfoRestVO> subscribedProductList) {
		boolean productExists = false;
		for (SubscribedProductInfoRestVO subscribedProduct : subscribedProductList) {
			if (requiredProduct.getProductTypeCd().equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
				// NWLN-11627 Existing HSIC, order TV and CPE only special rule, replacing HSIC, skip product template id check for HSIC
				if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(requiredProduct.getProductTypeCd()) 
						&& requestedProductList.contains(EnterpriseWLNSalesServicesConstants.TTV)
						&& !requestedProductList.contains(EnterpriseWLNSalesServicesConstants.HSIC)) {
					productExists = true;
					break;
				}
				
				// NWLN-10173 fix CPE product dones'nt have product template identifier
				if (requiredProduct.getProductTemplateIdentifier() != null) {
					if (WLNOfferUtil
							.getOmsOfferCatalogIdFromConsolidatedAccount(requiredProduct.getProductTypeCd(), commonVO)
							.equalsIgnoreCase(
									requiredProduct.getProductTemplateIdentifier().getExternalProductCatalogId())) {
						productExists = true;
						break;
					}
				} else {
					productExists = true;
					break;
				}
			}
		}
		return productExists;
	}

	protected boolean productsAreExistingFromReturnedOffers(Offer offer, List<ProductEligiblity> productEligibilityList) {
		Map<String, Boolean> oneOfList = new HashMap<String, Boolean>();
		String functionName = "productsAreExistingFromReturnedOffers";
		String offerId = String.valueOf(offer.getOfferId());
		
		for(ProductEligiblity requiredProduct : productEligibilityList) {
			String enforcementGroupCd = requiredProduct.getProductEnforcementGroupCd();
        	String productTypeCd = requiredProduct.getProductTypeCd();
        	
			boolean mandatory, oneof;
        	
        	// NWLN-10173 set default flag
        	if(StringUtils.isBlank(enforcementGroupCd)) {
        		mandatory = true;
        		oneof = false;
        	}else {
        		mandatory = EnterpriseWLNSalesServicesConstants.PRODUCT_ENFORCEMENT_GROUP_CD_MANDATORY.equalsIgnoreCase(enforcementGroupCd);
            	oneof = EnterpriseWLNSalesServicesConstants.PRODUCT_ENFORCEMENT_GROUP_CD_ONEOF.equalsIgnoreCase(enforcementGroupCd);
        	}
        	
        	List<SubscribedProductInfoRestVO> subscribedProductList = getAssignedProduct();
        	
        	// Check if product already existing
        	if(!requiredProductExists(requiredProduct, subscribedProductList)) {
        		// Product is not existing, check if product in request list and offer list
        		
        		// NWLN-10790 CPE offer, dependency product is not in request product list, skip offer list check and mark result as False
        		boolean skipOfferCheck = false;
        		if(isCPEOffer(offer) && requestedProductList != null && !requestedProductList.isEmpty() && !requestedProductList.contains(productTypeCd)) {
        			logger.info(functionName, "CPE offer " + offerId + "require " + productTypeCd + " is not in requested product list " + Arrays.toString(requestedProductList.toArray()));
        			skipOfferCheck = true;
        		}
        		
				if (skipOfferCheck || !isRequiredProductExisting(offerId, requiredProduct)) {
					if (mandatory) {
						logger.info(functionName,  "offer: " + offerId + " Mandatory rule failed, Return " + productTypeCd + "  as False");
						return false;
					}

					if (oneof) {
						logger.info(functionName, "offer: " + offerId + " Add " + productTypeCd + "  as False into OneOf list");
						oneOfList.put(productTypeCd, Boolean.FALSE);
						continue;
					}
				}
        	}else {
        		logger.info(functionName, productTypeCd + " is existing.");
        	}

			logger.info(functionName, "offer: " + offerId + " Pass all Mandatory/OneOf check for " + productTypeCd);
			// Pass all check set one of as true
			if (oneof) {
				logger.info(functionName, "offer: " + offerId + " Flag " + productTypeCd + " OneOf check as True ");
				oneOfList.put(productTypeCd, Boolean.TRUE);
			}
		}

		logger.info(functionName, "Start verify OneOf list");
		// check One of result
		if (!oneOfList.isEmpty()) {
			for (Boolean result : oneOfList.values()) {
				if (result) {
					logger.info(functionName,
							"offer: " + offerId + " There's a true result in OneOf list, return true");
					return true;
				}
			}
			logger.info(functionName, "offer: " + offerId + " No true result in OneOf list, return false");
			return false;
		} else {
			logger.info(functionName, "offer: " + offerId + " OneOf list is empty, every rule passed");
			return true;
		}
	}

	private boolean isRequiredProductExisting(String offerId, ProductEligiblity requiredProduct) {
		String functionName = "isRequiredProductExisting";
		boolean isRequiredProductExisting = false;
		if(!offerProductsMap.isEmpty()) {
			Set<String> offerIdList = offerProductsMap.keySet();
			for(String offerIdFromProductsMap : offerIdList) {
				List<WirelineOfferProduct> offerProductList = offerProductsMap.get(offerIdFromProductsMap);
				for(WirelineOfferProduct offerProduct : offerProductList) {
					if(productIsEqual(requiredProduct,offerProduct)) {
						isRequiredProductExisting = true;
						logger.info(functionName, "The required products for offer: " + offerId + ", were found in offer: " + offerIdFromProductsMap);
						break;
					}
				}
			}
		}
		return isRequiredProductExisting;
	}

	private boolean productIsEqual(ProductEligiblity requiredProduct,WirelineOfferProduct offerProduct) {
		boolean productIsEqual = false;
		if(requiredProduct.getProductTypeCd().equalsIgnoreCase(offerProduct.getProductTypeCode()) 
				&& productCatalogueMatches(offerProduct.getProductTemplateIdentifier(),requiredProduct.getProductTemplateIdentifier())) {
			productIsEqual = true;
		}
		return productIsEqual;
	}

	
	
	private boolean productCatalogueMatches(ProductCatalogueIdentifier offerProductTemplateIdentifier,
			ProductCatalogueIdentifier requiredProductTemplateIdentifier) {
		boolean productCatalogueIdMatches = false;
		//NWLN-10173 always return true if no required product template identifier
		if (requiredProductTemplateIdentifier == null) {
			productCatalogueIdMatches = true;
		}
		if(offerProductTemplateIdentifier!=null && requiredProductTemplateIdentifier!=null && offerProductTemplateIdentifier.getExternalProductCatalogId().equals(requiredProductTemplateIdentifier.getExternalProductCatalogId())) {
			productCatalogueIdMatches = true;
		}
		return productCatalogueIdMatches;
	}

	protected void log(final Offer o, final List<TraceVO> traces, final String msg) {
		logger.error("Offer id: " + o.getOfferId() + " was filterred out, because " + msg);

		TraceVO t = TraceVO.newInstance(this);
		t.setAction(TraceVO.DELETED);
		t.setElementType(TraceVO.OFFER);
		t.setOffer(o);
		t.setReason(msg);
		traces.add(t);
	}

	public List<String> getRequestedProductList() {
		return requestedProductList;
	}

	public void setRequestedProductList(List<String> requestedProductList) {
		this.requestedProductList = requestedProductList;
	}
	
	private boolean isCPEOffer(Offer offer) {
		boolean result = false;

		if(offer.getOfferProduct() != null &&  offer.getOfferProduct().getWirelineOfferProductList() != null) {
			for (WirelineOfferProduct offerProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
				if (EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(offerProduct.getProductTypeCode())) {
					result = true;
					break;
				}
			}
		}

		return result;
	}
	
	
	private boolean isCPEOfferUnderOtherTemplate(Offer offer) {
		boolean result = false;

		if (offer.getOfferProduct() != null && offer.getOfferProduct().getWirelineOfferProductList() != null) {
			for (WirelineOfferProduct offerProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
				if (EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(offerProduct.getProductTypeCode())
						&& !StringUtils.isEmpty(offerProduct.getProductTemplateProductTypeCode())
						&& !EnterpriseWLNSalesServicesConstants.CPE
								.equalsIgnoreCase(offerProduct.getProductTemplateProductTypeCode())) {
					result = true;
					break;
				}
			}
		}

		return result;
	}
	
}
