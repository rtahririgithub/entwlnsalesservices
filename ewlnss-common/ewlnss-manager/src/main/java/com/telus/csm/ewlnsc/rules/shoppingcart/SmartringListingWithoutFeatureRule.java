package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class SmartringListingWithoutFeatureRule
		extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {
	private static final LoggerUtil logger = LoggerUtil.getLogger(SmartringListingWithoutFeatureRule.class);

	private CartItemVO cartItemVO;
	
	private static final String SMART_RING_PRODUCT_CATALOGUE_ID = "101422";

	public SmartringListingWithoutFeatureRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);

		String functionName = "SmartringListingWithoutFeatureRule";
		logger.enter(functionName);
		boolean isSatisfied = true;
		String cartItemRelationId = "";
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(
				SmartringListingWithoutFeatureRule.class.getName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();

		if (shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty()) {
			
			
			if(cartItemVO != null
					&& cartItemVO.getProducts() != null
					&& cartItemVO.getProducts().getHomePhoneProduct() != null
					&& cartItemVO.getProducts().getHomePhoneProduct().getSmartRingPhoneList() != null
					&& ! cartItemVO.getProducts().getHomePhoneProduct().getSmartRingPhoneList().isEmpty()
					&& cartItemVO.getProducts().getHomePhoneProduct().getSmartRingPhoneList().get(0).getPhone() != null // for now we assume only one Smart Ring Phone
			){
				
				if( EnterpriseWLNSalesServicesConstants.ACTIVATION.equals(
						cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType().getOrderTypeCd()) ){
				
					FFHFeatureTypeVO smartRingFeature = getFeature(SMART_RING_PRODUCT_CATALOGUE_ID, cartItemVO.getProducts().getHomePhoneProduct().getFeatures());
					
					if(smartRingFeature == null) {
						ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
								.getErrorForCode(
										ShoppingCartValidationErrorCodes.SMART_RING_LISTING_WITHOUT_FEATURE);
						shoppingCartValidationError.setMessage(this.getClass().getSimpleName()
								+ ": The cartItem: " + cartItemRelationId
								+ " has a directory listing for SmartRing without the an existing or added SmartRing feature.");
						trace.setValidationPassedInd(false);
						trace.setErrors(shoppingCartValidationError);
						trace.setShoppingCartId(shppoingCartId);
						trace.setCartItemRelationId(cartItemRelationId);
						traces.add(trace);
						isSatisfied = false;

					}
				}
				
				if( EnterpriseWLNSalesServicesConstants.UPGRADE.equals(
						cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType().getOrderTypeCd()) ){
					
				}
			}
		}
		
		return isSatisfied;
	}

	private boolean isAddOrNoChange(FFHFeatureTypeVO parm) {
		if (parm == null) {
			return false;
		}
		
		ProductComponentVO pc = parm.getProductCatalogueIdentifier();
		
		return (!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(pc.getAction()));
	}

	private FFHFeatureTypeVO getFeature(String catalogueId, List<FFHFeatureTypeVO> features) {
		
		if (features == null) {
			return null;
		}
		
		for (FFHFeatureTypeVO feature : features) {
			ProductComponentVO pc = feature.getProductCatalogueIdentifier();
			if (pc != null && StringUtils.equalsIgnoreCase(catalogueId, pc.getProductCatalogueId())) {
				return feature;
			}
		}

		return null;
	}


}
