package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class SmartringFeatureWithoutListingRule
		extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {
	private static final LoggerUtil logger = LoggerUtil.getLogger(SmartringFeatureWithoutListingRule.class);

	private CartItemVO cartItemVO;
	
	private static final String SMART_RING_PRODUCT_CATALOGUE_ID = "101422";

	public SmartringFeatureWithoutListingRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);

		String functionName = "SmartringFeatureWithoutListingRule";
		logger.enter(functionName);
		boolean isSatisfied = true;
		String cartItemRelationId = "";
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(
				SmartringFeatureWithoutListingRule.class.getName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();

		if (shoppingCartVO.getCartItemList() != null 
				&& !shoppingCartVO.getCartItemList().isEmpty()
				&& cartItemVO != null
				&& cartItemVO.getProducts() != null
				&& cartItemVO.getProducts().getHomePhoneProduct() != null
			) {
			
			FFHFeatureTypeVO smartRingFeature = getFeature(SMART_RING_PRODUCT_CATALOGUE_ID, cartItemVO.getProducts().getHomePhoneProduct().getFeatures());

			if(smartRingFeature != null 
					&& ( cartItemVO.getProducts().getHomePhoneProduct().getSmartRingPhoneList() == null 
					|| (cartItemVO.getProducts().getHomePhoneProduct().getSmartRingPhoneList().isEmpty()) )
			) {
				ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
						.getErrorForCode(
								ShoppingCartValidationErrorCodes.SMART_RING_FEATURE_WITHOUT_LISTING);
				shoppingCartValidationError.setMessage(this.getClass().getSimpleName()
						+ ": The cartItem: " + cartItemRelationId
						+ " has an existing or added SmartRing feature without a directory listing for SmartRing.");
				trace.setValidationPassedInd(false);
				trace.setErrors(shoppingCartValidationError);
				trace.setShoppingCartId(shppoingCartId);
				trace.setCartItemRelationId(cartItemRelationId);
				traces.add(trace);
				isSatisfied = false;

			}
			
		}
		
		return isSatisfied;
	}


	private FFHFeatureTypeVO getFeature(String catalogueId, List<FFHFeatureTypeVO> features) {
		
		if (features == null) {
			return null;
		}
		
		for (FFHFeatureTypeVO feature : features) {
			ProductComponentVO pc = feature.getProductCatalogueIdentifier();
			if (pc != null && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(pc.getAction()) && StringUtils.equalsIgnoreCase(catalogueId, pc.getProductCatalogueId())) {
				return feature;
			}
		}

		return null;
	}


}
