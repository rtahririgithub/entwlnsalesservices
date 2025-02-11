package com.telus.csm.ewlnsc.delegate;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.LOCAL_LINE_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_3WAY_CALLING_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_CALL_DISPLAY_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_CALL_RETURN_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TELUS_HOME_CONNECTIONS_BUNDLE3_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TELUS_HOME_PHONE_LITE_EXTERNAL_CID;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemConstraintGroupVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemConstraintListVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemListVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.SalesCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ServiceConstraintVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.IncludedServiceConstraint;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
 
/**
 * 
 * @author GTam, CBrown
 *
 */
public class ProductItemConstraintGroupDelegate {

	private static Logger logger = Logger.getLogger(ProductItemConstraintGroupDelegate.class);
	
	/**
	 * groupProductItemConstraints
	 * @param String productType
	 * @param List<ProductItemVO> productItems
	 * @param ShoppingCartContextVO shoppingCartContextVO
	 * @return List<ProductItemConstraintGroupList>
	 */
	public List<ProductItemConstraintGroupVO> groupProductItemConstraints(String productType, List<ProductItemVO> productItems, ShoppingCartContextVO shoppingCartContextVO){			
		Offer offer = getOffer(productType, shoppingCartContextVO);
		return groupProductItemConstraints(productType, productItems, offer);
	}


	/**
	 * groupProductItemConstraints
	 * @param String productType
	 * @param List<ProductItemVO> productItems
	 * @param Offer offer
	 * @return List<ProductItemConstraintGroupVO>
	 */
	public List<ProductItemConstraintGroupVO> groupProductItemConstraints(String productType, List<ProductItemVO> productItems, Offer offer){		
		List<ProductItemConstraintGroupVO> productItemConstraints = new ArrayList<ProductItemConstraintGroupVO>();
		if (offer != null) {
			List<ServiceConstraintVO> serviceConstraintList = getServiceConstraintList(productType, offer);
			productItemConstraints.addAll(getProductItemConstraintGroupList(productItems, serviceConstraintList));
		}
		return productItemConstraints;
	}
	
	
	/*****************************************************/
	/* Retrieve Service Constraints List from Offer      */
	/*****************************************************/
	private List<ServiceConstraintVO> getServiceConstraintList(String productType, Offer offer) {
		List<ServiceConstraintVO> serviceConstraintList = new ArrayList<ServiceConstraintVO>();
		if (offer.getOfferProduct() != null &&
			CollectionUtils.isNotEmpty(offer.getOfferProduct().getWirelineOfferProductList())) {	
			for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()){
				if (!wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(productType)) {
					continue;
				}
				for (ProductComponent prodComponent : wirelineOfferProduct.getProductComponentList()) {										
					List<IncludedServiceConstraint> inclServiceConstraintList = prodComponent.getIncludedServiceConstraintList();
					for (IncludedServiceConstraint inclServiceConstraint : inclServiceConstraintList == null ? Collections.<IncludedServiceConstraint>emptyList() : inclServiceConstraintList) {
						List<String> inclServiceTypeConstraintList = inclServiceConstraint.getServiceTypeCodeList();
						BigInteger minItemQty = inclServiceConstraint.getMinItemQty();
						BigInteger maxItemQty = inclServiceConstraint.getMaxItemQty();
						ServiceConstraintVO serviceConstraintVO = new ServiceConstraintVO();
						serviceConstraintVO.setServiceTypeCodeList(inclServiceTypeConstraintList);
						serviceConstraintVO.setMinItemQty(minItemQty != null ? minItemQty.intValue() : 0);
						serviceConstraintVO.setMaxItemQty(maxItemQty != null ? maxItemQty.intValue() : 0);
						//NWLN-7789
						if(inclServiceConstraint.getSalesCategory()!=null && inclServiceConstraint.getSalesCategory().getSalesCategoryCode()!=null) {
							SalesCategoryVO salesCategory = new SalesCategoryVO();
							salesCategory.setSalesCategoryCode(inclServiceConstraint.getSalesCategory().getSalesCategoryCode());
							serviceConstraintVO.setSalesCategory(salesCategory); 
						}
						
						serviceConstraintList.add(serviceConstraintVO);				
					}
				}
			}
		}
		return serviceConstraintList;
	}
	
	/*****************************************************/
	/* Retrieve Service Constraints List from Offer      */
	/*****************************************************/
	private List<ProductItemConstraintGroupVO> getProductItemConstraintGroupList(List<ProductItemVO> productItems, List<ServiceConstraintVO> serviceConstraints) {
		//Gary 2018-09-20
		/**************************************************/
		/*  transform the min/max constraint qty          */
		/**************************************************/
		// Gary - 2018-09-24 QC67396 

		//NWLN-7789
		boolean isNoSalesCategory = true;
		for (ServiceConstraintVO serviceConstraintVO : serviceConstraints == null ? Collections.<ServiceConstraintVO>emptyList() : serviceConstraints){
			if(serviceConstraintVO.getSalesCategory()!=null && StringUtils.isNotBlank(serviceConstraintVO.getSalesCategory().getSalesCategoryCode())) {
				isNoSalesCategory = false;
				break;
			}
		}
		
		List<ProductItemConstraintGroupVO> productItemConstraintGroupList = new ArrayList<ProductItemConstraintGroupVO>();
		 Set<String> extCatIds = new HashSet<String>();
		
		for (ServiceConstraintVO serviceConstraintVO : serviceConstraints == null ? Collections.<ServiceConstraintVO>emptyList() : serviceConstraints){
			int maxQty = serviceConstraintVO.getMaxItemQty();
			int minQty = serviceConstraintVO.getMinItemQty();
			SalesCategoryVO s = serviceConstraintVO.getSalesCategory();
			
			if (minQty == 0 && maxQty == 0) {
				logger.debug("getProductItemConstraintGroupList - bypass productItem minQty and maxQty = 0 serviceTypeCodeList=[" + StringUtils.join(serviceConstraintVO.getServiceTypeCodeList(), ",") + "]");
				continue;
			}
			logger.debug("getProductItemConstraintGroupList - productItem minQty=" + minQty + " maxQty = " + maxQty + "salescategory = "+(s!=null ? s.getSalesCategoryCode() : "") + " serviceTypeCodeList=[" + StringUtils.join(serviceConstraintVO.getServiceTypeCodeList(), ",") + "]");
			
			ProductItemConstraintListVO productItemConstraintListVO = new ProductItemConstraintListVO();			
			productItemConstraintListVO.setEligibleForMinMaxPricingInd(true);
		
			for (ProductItemVO prodItem  : productItems) {
				Boolean isPackEligible = prodItem.getPackEligibleItemInd() == null ? false : prodItem.getPackEligibleItemInd();
				if (!isPackEligible) {
					continue;
				}
				String parentProductCatalogueId = prodItem.getProductItemIdentifier().getParentProductCatalogueId();
				String productCatalogueId       = prodItem.getProductItemIdentifier().getProductCatalogueId();			
				//NWLN-7789
				if(isNoSalesCategory || serviceConstraintVO.getServiceTypeCodeList().contains(prodItem.getSalesCategoryServiceTypeCd())) {
					productItemConstraintListVO.getProductItemList().add(toConstraintItem(prodItem)); 
				}
				//All packeligible one's.
				extCatIds.add(prodItem.getProductItemIdentifier().getExternalId());
			}
			
			/*
			 *  Tier 1 rules: Add these items 
				When TOM.productComponent in the offer includes extCID: 40671421 TELUS Home Phone Lite (Forborne) (V1)
					If ES cart has carryover or added Feature with extCID 45 Call Display then set inpackInd=Yes / $0
				When TOM.productComponent in the offer includes extCID: 55064 Home Connections Bundle 3
					If ES cart has carryover or added Feature with extCID 45 Call Display then set inpackInd=Yes / $0
				When TOM.productComponent is anything other than extCID 32628 Local Line 
					If ES cart has carryover or added Feature with extCID 48 Call Return then set inpackInd=Yes / $0
					If ES cart has carryover or added Feature with extCID 55 3 Way Calling then set inpackInd=Yes / $0
			 */
			ProductItemVO matchProductItemVO = null;
			if(getProductItem(productItems, TELUS_HOME_PHONE_LITE_EXTERNAL_CID) != null || getProductItem(productItems, TELUS_HOME_CONNECTIONS_BUNDLE3_EXTERNAL_CID) != null) {
				matchProductItemVO = getProductItem(productItems, SL_FEATURE_CALL_DISPLAY_EXTERNAL_CID);
				productItemConstraintListVO.getProductItemList().add(toConstraintItem(matchProductItemVO));
				
			}else if(getProductItem(productItems, LOCAL_LINE_EXTERNAL_CID) != null) {
				matchProductItemVO = getProductItem(productItems, SL_FEATURE_CALL_RETURN_EXTERNAL_CID);
				productItemConstraintListVO.getProductItemList().add(toConstraintItem(matchProductItemVO));
				
				matchProductItemVO = getProductItem(productItems, SL_FEATURE_3WAY_CALLING_EXTERNAL_CID);
				productItemConstraintListVO.getProductItemList().add(toConstraintItem(matchProductItemVO));
			}
			
			
			ProductItemConstraintGroupVO productItemConstrantGroupVO = new ProductItemConstraintGroupVO();
			
			//NWLN-7789
			if(serviceConstraintVO.getSalesCategory()!=null && StringUtils.isNotBlank(serviceConstraintVO.getSalesCategory().getSalesCategoryCode())) {
				productItemConstrantGroupVO.setConstraintType(serviceConstraintVO.getSalesCategory().getSalesCategoryCode());
			}else {
				productItemConstrantGroupVO.setConstraintType(null);
			}
			
			productItemConstrantGroupVO.setFreePricingQuantityAllowed(serviceConstraintVO);
			productItemConstrantGroupVO.setProductItemConstraintListVO(productItemConstraintListVO);
			
			productItemConstraintGroupList.add(productItemConstrantGroupVO);
			
		}
		return productItemConstraintGroupList;
	}
	
	private ProductItemListVO toConstraintItem(ProductItemVO prodItem) {
		if(prodItem != null) {
			ProductItemListVO productItemListVO = new ProductItemListVO();
			productItemListVO.setParentProductCatalogueId(prodItem.getProductItemIdentifier().getParentProductCatalogueId());
			productItemListVO.setProductCatalogueId(prodItem.getProductItemIdentifier().getProductCatalogueId());
			return productItemListVO;
		}
		return null;
	}


	private ProductItemVO getProductItem(List<ProductItemVO> productItems, String extCatId) {
		for (ProductItemVO prodItem : productItems) {
			if(extCatId.equals(prodItem.getProductItemIdentifier().getExternalId())) {
				return prodItem;
			}
				
		}
		return null;
	}


	/*****************************************************/
	/* Retrieve Specific Product Offer                   */
	/*****************************************************/	
	private Offer getOffer(String productType, ShoppingCartContextVO shoppingCartContextVO){  
			
		Offer offer = null;
		
		ShoppingCartVO cart = shoppingCartContextVO.getShoppingCartVO();
		main:
		for (CartItemVO cartItem : cart.getCartItemList()) {
			if (!cartItem.isSalesOrderItem()) {
				continue;
			}
			String cartItemRelationId = cartItem.getCartItemRelationId();
			if (StringUtils.isBlank(cartItemRelationId)) {
				logger.error("missing cartItemRelationId. value=" + cartItemRelationId);
			}
			 
			String cartItemOfferId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId();
			GetSalesOfferDetailResponseVO2 getSalesOfferDetailResponseVO = shoppingCartContextVO.getOfferByCartItemOfferId(cartItemOfferId);
			if (getSalesOfferDetailResponseVO != null &&
				getSalesOfferDetailResponseVO.getOffer() != null &&
				getSalesOfferDetailResponseVO.getOffer().getOfferProduct() != null &&
				CollectionUtils.isNotEmpty(getSalesOfferDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())) {
				for (WirelineOfferProduct wirelineOfferProduct : getSalesOfferDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
					if (wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(productType)) {
						offer = getSalesOfferDetailResponseVO.getOffer();
						break main;
					}
				}				
			}				
		}
		
		return offer;
	}
}
