package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class CallRevealWithoutCallDisplayRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {

	private static final String CALL_DISPLAY_EXT_ID = "45";
	private static final String CALL_REVEAL_EXT_ID = "49";
	private static final String ERROR_MSG_1 = " includes Call Reveal feature but the existing profile does not contain the dependant Call Display feature.";
	private static final String ERROR_MSG_2 = " includes Call Reveal feature but does not contain the dependant Call Display feature.";

	private static final LoggerUtil logger = LoggerUtil.getLogger(CallRevealWithoutCallDisplayRule.class);

	private CartItemVO cartItemVO;

	private String catIdCallReveal;
	private String catIdCallDisplay;
	
	public CallRevealWithoutCallDisplayRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(final ShoppingCartContextVO ctxVO, final List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		String functionName = "CallRevealWithoutCallDisplayRule";
		logger.enter(functionName);
		boolean isSatisfied = true;
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(CallRevealWithoutCallDisplayRule.class.getName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();

		if (CollectionUtils.isNotEmpty(shoppingCartVO.getCartItemList()) && cartItemVO.isSalesOrderItem() && !StringUtils.isEmpty(cartItemVO.getCartItemRelationId())) {
			final String ciRelationId = cartItemVO.getCartItemRelationId();
			final ProductTypeVO cartItemProducts = cartItemVO.getProducts();
			if (cartItemProducts != null && cartItemProducts.getHomePhoneProduct() != null) {
				populateFeatures();
				final HomePhoneProductTypeVO slProduct = cartItemProducts.getHomePhoneProduct();
				final FFHFeatureTypeVO callRevealVO = getFeature(catIdCallReveal, slProduct.getFeatures());
				final FFHFeatureTypeVO callDisplayVO = getFeature(catIdCallDisplay, slProduct.getFeatures());
				if (callRevealVO != null && isAddOrNoChange(callRevealVO)) {
					if (callDisplayVO == null) {
						final GetAvailableProductItemDelegateResponse piDelegateResp = ctxVO.getAvailableProductItemDelegateResponse();
						if (piDelegateResp != null && piDelegateResp.getProductItems() != null) {
							for (final ProductItemVO piElem : piDelegateResp.getProductItems()) {
								String existCatId = piElem.getProductItemIdentifier().getProductCatalogueId();
								if (catIdCallDisplay.equalsIgnoreCase(existCatId) && piElem.isExistingInd() && piElem.isCarryOverInd()) {
									isSatisfied = true;
									logger.info(functionName, "passed.");
									break;
								} else if (catIdCallDisplay.equalsIgnoreCase(existCatId)) {
									ShoppingCartValidationErrors scvErr = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.CALL_REVEAL_WITHOUT_CALL_DISPLAY);
									scvErr.setMessage(this.getClass().getSimpleName() + ": The cartItem: " + ciRelationId + ERROR_MSG_1);
									trace.setValidationPassedInd(false);
									trace.setErrors(scvErr);
									trace.setShoppingCartId(shppoingCartId);
									trace.setCartItemRelationId(ciRelationId);
									traces.add(trace);
									isSatisfied = false;											
								}
							}
						}
					} else if (!isAddOrNoChange(callDisplayVO)) {
						ShoppingCartValidationErrors scvErr = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.CALL_REVEAL_WITHOUT_CALL_DISPLAY);
						scvErr.setMessage(this.getClass().getSimpleName() + ": The cartItem: " + ciRelationId + ERROR_MSG_2);
						trace.setValidationPassedInd(false);
						trace.setErrors(scvErr);
						trace.setShoppingCartId(shppoingCartId);
						trace.setCartItemRelationId(ciRelationId);
						traces.add(trace);
						isSatisfied = false;
					}
				}
			}
		}
		return isSatisfied;
	}

	private void populateFeatures() {
		final CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		
		final CatalogueItemDO gridCallReveal = gridHelper.getCatalogueItemByExternalId(CALL_REVEAL_EXT_ID);
		if (gridCallReveal != null) {
			catIdCallReveal = gridCallReveal.getCatalogueItemId();
		}
		
		final CatalogueItemDO gridCallDisplay = gridHelper.getCatalogueItemByExternalId(CALL_DISPLAY_EXT_ID);
		if (gridCallDisplay != null) {
			catIdCallDisplay = gridCallDisplay.getCatalogueItemId();
		}
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
