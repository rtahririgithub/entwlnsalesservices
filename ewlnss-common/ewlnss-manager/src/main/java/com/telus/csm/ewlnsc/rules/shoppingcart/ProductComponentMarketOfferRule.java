package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.TransactionType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;


public class ProductComponentMarketOfferRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(ProductComponentMarketOfferRule.class);
	private CartItemVO cartItemVO;

	public ProductComponentMarketOfferRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		
		String functionName = "ProductComponentMarketOfferRule.isSatisfiedBy";
		
		logger.enter(functionName);
		boolean isSatisfied = true;
		String cartItemRelationId = "";
		GetSalesOfferDetailResponseVO2 offerDetailResponseVO = null;
		List<String> unmatchedProductComponentProductList = new ArrayList<String>();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();

		if (shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty()) {
			// the cartItem element means market offer
			if (cartItemVO.isSalesOrderItem() && !StringUtils.isEmpty(cartItemVO.getCartItemRelationId())) {

				cartItemRelationId = cartItemVO.getCartItemRelationId();

				offerDetailResponseVO = ctxVO.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());

				if (offerDetailResponseVO != null && offerDetailResponseVO.getOffer() != null
						&& offerDetailResponseVO.getOffer().getOfferProduct() != null && !CollectionUtils.isEmpty(
								offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())) {
					List<WirelineOfferProduct> wirelineOfferProductList = offerDetailResponseVO.getOffer()
							.getOfferProduct().getWirelineOfferProductList();

					ProductTypeVO cartItemProducts = cartItemVO.getProducts();
					if (cartItemProducts != null) {

						// HSIC
						if (cartItemProducts.getInternetProduct() != null) {
							InternetProductTypeVO internetProduct = cartItemProducts.getInternetProduct();
							for (WirelineOfferProduct wirelineProduct : wirelineOfferProductList) {
								if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineProduct.getProductTypeCode())) {
									if (isOfferProductActivationOrUpgrade(wirelineProduct) && !isProductComponentExactMatch(wirelineProduct, internetProduct.getProductComponents())) {
										unmatchedProductComponentProductList.add(wirelineProduct.getProductTypeCode());
									}
								}
							}
						}

						// TTV
						if (cartItemProducts.getTelevisionProduct() != null) {
							TelevisionProductTypeVO televisionProduct = cartItemProducts.getTelevisionProduct();
							for (WirelineOfferProduct wirelineProduct : wirelineOfferProductList) {
								if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineProduct.getProductTypeCode())) {
									if (isOfferProductActivationOrUpgrade(wirelineProduct) && !isProductComponentExactMatch(wirelineProduct, televisionProduct.getProductComponents())) {
										unmatchedProductComponentProductList.add(wirelineProduct.getProductTypeCode());
									}
								}
							}
						}

						// SING
						if (cartItemProducts.getHomePhoneProduct() != null) {
							HomePhoneProductTypeVO homePhoneProduct = cartItemProducts.getHomePhoneProduct();
							for (WirelineOfferProduct wirelineProduct : wirelineOfferProductList) {
								if (EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineProduct.getProductTypeCode())) {
									if (isOfferProductActivationOrUpgrade(wirelineProduct) && !isProductComponentExactMatch(wirelineProduct, homePhoneProduct.getProductComponents())) {
										unmatchedProductComponentProductList.add(wirelineProduct.getProductTypeCode());
									}
								}
							}
						}
					}

					if (!CollectionUtils.isEmpty(unmatchedProductComponentProductList)) {
						ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
								.getErrorForCode(ShoppingCartValidationErrorCodes.MARKET_OFFER_PRODUCT_COMPONENT_NOT_MATCHED);
						shoppingCartValidationError.setMessage(this.getClass().getSimpleName()
								+ ": The cartItem's productComponent do not match the existing market offer's productComponent for that product: "
								+ unmatchedProductComponentProductList.toString());
						trace.setValidationPassedInd(false);
						trace.setErrors(shoppingCartValidationError);
						trace.setShoppingCartId(shppoingCartId);
						trace.setCartItemRelationId(cartItemRelationId);
						traces.add(trace);
						isSatisfied = false;
					}

				} else {
					logger.debug(functionName, "No offer was found for cartItemRelationId: " + cartItemRelationId);
				}
			} else {
				logger.debug(functionName, "Rule skipped. cartItem is: market Offer=" + cartItemVO.isSalesOrderItem()
						+ " and cartItemRelationId=" + cartItemVO.getCartItemRelationId());
			}
		}
		return isSatisfied;
	}

	private boolean isOfferProductActivationOrUpgrade(WirelineOfferProduct wirelineProduct) {

		if (wirelineProduct == null || wirelineProduct.getTransactionTypeList() == null) {
			return false;
		}
		
		for (TransactionType tranType : wirelineProduct.getTransactionTypeList()) {
			if (EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION.equalsIgnoreCase(tranType.getTransactionTypeCode())
					|| EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE.equalsIgnoreCase(tranType.getTransactionTypeCode())) {
				return true;
			}
		}
		
		return false;
	}

	private boolean isProductComponentExactMatch(WirelineOfferProduct offerProduct,
			List<ProductComponentVO> cartProductComponents) {
		
		List<String> offerProductCompIds = new ArrayList<String>();
		List<String> cartProductcompIds = new ArrayList<String>();
		
		for (ProductComponent pc: offerProduct.getProductComponentList()) {
			for (ProductCatalogueItem pci : pc.getProductCatalogueItemList()) {
				offerProductCompIds.add(pci.getProductCatalogueIdentifier().getProductCatalogueId());
			}
		}
		
		for (ProductComponentVO pc : cartProductComponents) {
			if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(pc.getAction())){
				cartProductcompIds.add(pc.getProductCatalogueId());
			}
			
		}
		
		return offerProductCompIds.containsAll(cartProductcompIds) && cartProductcompIds.containsAll(offerProductCompIds);
		
	}

}
