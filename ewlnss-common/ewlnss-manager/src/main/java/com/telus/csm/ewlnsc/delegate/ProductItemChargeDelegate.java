package com.telus.csm.ewlnsc.delegate;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.LOCAL_LINE_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_3WAY_CALLING_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_CALL_DISPLAY_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_CALL_RETURN_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TELUS_HOME_CONNECTIONS_BUNDLE3_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TELUS_HOME_PHONE_LITE_EXTERNAL_CID;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ewlnsc.domain.MoneyVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemConstraintGroupVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemConstraintListVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemListVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.ServiceConstraintVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.ProductItemPriceComparator;

/**
 *
 * @author CBrown, VKatariya
 *
 */
public class ProductItemChargeDelegate extends SpringBeanAutowiringSupport {

	private static Logger logger = Logger.getLogger(ProductItemChargeDelegate.class);

	private final String CALLING_FEATURE_SERVICE_TYPE_CODE = "FTR";
	private final String CALLING_FEATURE_PACK_CODE = "SFPK";

	/**
	 * Determine ProductItems that are free and are not free based on Included Constraint in Offer
	 * @param productType String
	 * @param productItems List<ProductItemVO>
	 * @param scContext ShoppingCartContextVO
	 */
	public void applyConstraintPricing(String productType, List<ProductItemVO> productItems, ShoppingCartContextVO scContext) {
		//Sort Product Items by Price...High to Low.
		ProductItemPriceComparator priceComparator = new ProductItemPriceComparator();
		Collections.sort(productItems, priceComparator);

		ProductItemConstraintGroupDelegate piConstraintGroupDelegate = new ProductItemConstraintGroupDelegate();
		List<ProductItemConstraintGroupVO> piConstraintGroups = piConstraintGroupDelegate.groupProductItemConstraints(productType, productItems, scContext);

		for (ProductItemConstraintGroupVO piConstraintGroup : piConstraintGroups == null ? Collections.<ProductItemConstraintGroupVO>emptyList() : piConstraintGroups) {
			updateProductPricingByConstraintRule(piConstraintGroup, productItems);
		}
	}

	private void updateProductPricingByConstraintRule(ProductItemConstraintGroupVO piConstraintGroup, List<ProductItemVO> productItems) {
		String functionName = "updateProductPricingByConstraintRule";
		ServiceConstraintVO serviceConstraint = piConstraintGroup.getFreePricingQuantityAllowed();
		ProductItemConstraintListVO piConstraintListVO = piConstraintGroup.getProductItemConstraintListVO();

		if (serviceConstraint.getMaxItemQty() == 0) {
			// do nothing.
			return;
		}
		if (serviceConstraint.getServiceTypeCodeList().contains(CALLING_FEATURE_PACK_CODE)) {
			serviceConstraint.getServiceTypeCodeList().add(CALLING_FEATURE_SERVICE_TYPE_CODE);
		}

		int maxQty = serviceConstraint.getMaxItemQty();
		
		/*
		 *  Tier 1 rules:  
			When TOM.productComponent in the offer includes extCID: 40671421 TELUS Home Phone Lite (Forborne) (V1)
				If ES cart has carryover or added Feature with extCID 45 Call Display then set inpackInd=Yes / $0
			When TOM.productComponent in the offer includes extCID: 55064 Home Connections Bundle 3
				If ES cart has carryover or added Feature with extCID 45 Call Display then set inpackInd=Yes / $0
			When TOM.productComponent is anything other than extCID 32628 Local Line 
				If ES cart has carryover or added Feature with extCID 48 Call Return then set inpackInd=Yes / $0
				If ES cart has carryover or added Feature with extCID 55 3 Way Calling then set inpackInd=Yes / $0
		 */
		
		ListIterator<ProductItemVO> iterator = productItems.listIterator();
		while (iterator.hasNext()) {
			boolean impactPricing = false;
			ProductItemVO item = iterator.next();
			if(getProductItem(productItems, TELUS_HOME_PHONE_LITE_EXTERNAL_CID) != null || getProductItem(productItems, TELUS_HOME_CONNECTIONS_BUNDLE3_EXTERNAL_CID) != null) {
				if(SL_FEATURE_CALL_DISPLAY_EXTERNAL_CID.equals(item.getProductItemIdentifier().getExternalId())) {
					impactPricing = true;
				}
				
			}else if(getProductItem(productItems, LOCAL_LINE_EXTERNAL_CID) != null) {
				if(SL_FEATURE_CALL_RETURN_EXTERNAL_CID.equals(item.getProductItemIdentifier().getExternalId())) {
					impactPricing = true;
				}
				
				if(SL_FEATURE_3WAY_CALLING_EXTERNAL_CID.equals(item.getProductItemIdentifier().getExternalId())) {
					impactPricing = true;
				}
				
			}
			if(impactPricing) {
				if (maxQty > 0) {
					PriceVO charge = new PriceVO();
					MoneyVO amount = new MoneyVO();
					charge.setBasePriceAmount(amount);
					charge.setPricingTypeCd(item.getProductItemPriceCharge().getPricingTypeCd());
					charge.setRecurrenceCount(item.getProductItemPriceCharge().getRecurrenceCount());
					amount.setValueAmt(0f);
					item.setProductItemPriceCharge(charge);
					item.setPackEligibleItemInd(Boolean.TRUE);
					item.setEligibleForMinMaxPricingInd(Boolean.TRUE);

					maxQty = maxQty - 1;
				}
			}
			iterator.set(item);
		}

		
		//TIER 2
		
		iterator = productItems.listIterator();
		while (iterator.hasNext()) {
			ProductItemVO item = iterator.next();
			String parentServiceTypeCode = getServiceType(item.getProductItemIdentifier().getParentProductCatalogueId());
			logger.debug(functionName + ": parentServiceTypeCode=" + parentServiceTypeCode);
			if (parentServiceTypeCode != null) {
				if (serviceConstraint.getServiceTypeCodeList().contains(parentServiceTypeCode) &&
					isEligibleForMinMax(item, piConstraintListVO)) {
					if (maxQty > 0) {
						PriceVO charge = new PriceVO();
						MoneyVO amount = new MoneyVO();
						charge.setBasePriceAmount(amount);
						charge.setPricingTypeCd(item.getProductItemPriceCharge().getPricingTypeCd());
						charge.setRecurrenceCount(item.getProductItemPriceCharge().getRecurrenceCount());
						amount.setValueAmt(0f);
						item.setProductItemPriceCharge(charge);
						item.setPackEligibleItemInd(Boolean.TRUE);
						item.setEligibleForMinMaxPricingInd(Boolean.TRUE);

						maxQty = maxQty - 1;
					} else {
						item.setPackEligibleItemInd(Boolean.FALSE);
						item.setEligibleForMinMaxPricingInd(Boolean.TRUE);
					}
				}
			}
			iterator.set(item);
		}
	}

	private boolean isEligibleForMinMax(ProductItemVO productItem, ProductItemConstraintListVO piConstraintListVO) {
		for (ProductItemListVO productItemList : piConstraintListVO.getProductItemList() == null ? Collections.<ProductItemListVO>emptyList() : piConstraintListVO.getProductItemList()) {
			if (productItemList.getProductCatalogueId().equals(productItem.getProductItemIdentifier().getProductCatalogueId())) {
				return true;
			}
		}
		return false;
	}

	private String getServiceType(String productcatalogId) {
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		CatalogueItemDO result = gridHelper.getCatalogueItemById(productcatalogId);
		if ( result != null ) {
			return result.getComponentServiceType();
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
}
