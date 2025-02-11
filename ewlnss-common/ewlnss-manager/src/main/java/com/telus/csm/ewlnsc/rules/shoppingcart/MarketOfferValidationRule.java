package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;

/**
 * this is the domain Rule to validate different components within an offer. addOns,discounts,equipments,feature.
 *  
 * @author Alejandro.Hernandez
 *
 */
public class MarketOfferValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{
	
	private CartItemVO cartItem;
	private static final LoggerUtil logger = LoggerUtil.getLogger(MarketOfferValidationRule.class);
	
	public MarketOfferValidationRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}
	
	private static String ALL = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_ALL;
	private static String PROMOTION = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_PROMOTION;
	private static String EQUIPMENT = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_EQUIPMENT;
	private static String ADDON = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_ADDON;
	private static String OFFER = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_OFFER;
	private static String FEATURE = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_FEATURE;

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartCtxVO, List<ShoppingCartValidationTraceVO> traces) {
		boolean isSatisfied = true;
		super.isSatisfiedBy(shoppingCartCtxVO, traces);
		String functionName="MarketOfferValidationRule.isSatisfiedBy()";
		logger.enter(functionName);
		String cartItemRelationId=null;
		String offerId=null;
		GetSalesOfferDetailResponseVO2 offerDetailResponseVO=null;
		/**
		 * check if the OfferDetail response is successful.
		 */
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(MarketOfferValidationRule.class.getName());
		
		List<AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>> specList = new ArrayList<AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO>>();


		List<SubscribedProductInfoRestVO> subscribedProducts = EnterpriseWLNCommonTransformer.getAssignedProductsFromVO(shoppingCartCtxVO);

		List<Offer> allOffers = new ArrayList<Offer>();
		for ( CartItemVO cartItem : shoppingCartCtxVO.getShoppingCartVO().getCartItemList()) {
			if (cartItem.getProductMarketOffering() != null && cartItem.getProductMarketOffering().getOfferHeader() != null){
				offerId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId();
				offerDetailResponseVO = shoppingCartCtxVO.getOfferByCartItemOfferId(offerId);
				if (offerDetailResponseVO != null){
					allOffers.add(offerDetailResponseVO.getOffer());
				}
			}
		}

		InternetProductTypeVO internetProductType = null;
		
		for(CartItemVO cartItem: shoppingCartCtxVO.getShoppingCartVO().getCartItemList()){
			if(cartItem.getProducts() != null && cartItem.getProducts().getInternetProduct() != null){
				internetProductType = cartItem.getProducts().getInternetProduct();
			}
		}
		
		//Getting the OfferDetail per cartItem.
			
			if(cartItem.isSalesOrderItem() && cartItem.getProductMarketOffering()!=null && cartItem.getProductMarketOffering().getOfferHeader()!=null && !StringUtils.isBlank(cartItem.getProductMarketOffering().getOfferHeader().getOfferId())){
				offerId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId();
				
				offerDetailResponseVO = shoppingCartCtxVO.getOfferByCartItemOfferId(offerId);
				
				if(offerDetailResponseVO!=null && offerDetailResponseVO.getOffer()!=null){

					logger.debug(functionName, "Market offer was found for cartItem with CartItemRelationId=" + cartItemRelationId);


					// NWLN-7614 - split offer project.
					// added to validate the product eligibility of current offer.
					// this check should work for both split and joined offers
					List<String> offerProductEligibilityErrorMsg = new ArrayList<String>();
					if (!EnterpriseWLNOrderUtil.isOfferProductEligibilityValid(offerDetailResponseVO.getOffer(), subscribedProducts, allOffers, cartItem, internetProductType, offerProductEligibilityErrorMsg)) {
						String errorMsg = "";
						if(!offerProductEligibilityErrorMsg.isEmpty()){
							errorMsg = offerProductEligibilityErrorMsg.toString();
						}
					    String msg = "Product eligibility check for Offer with id=" + offerId + " failed: " + errorMsg;
						logger.error(this.getClass().getSimpleName() + ":" + msg);
                        addTrace(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.MARKET_OFFER_VALIDATION, msg), traces, trace, offerDetailResponseVO,cartItem);
						isSatisfied = false;
					}

					//1. check if there are products coming from the MarketOffer

					else if(offerDetailResponseVO.getOffer().getOfferProduct()!=null && !CollectionUtils.isEmpty(offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())){

						// Iterating over the product list returned
						for(WirelineOfferProduct offerProduct : offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
							
							if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, PROMOTION})) {
								specList.add(new ValidateMarketOfferDiscountRule(offerProduct, cartItem,offerDetailResponseVO.getRecontractEligibleProductCodeList(),offerId));
							}
							
							if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, EQUIPMENT})) {
								specList.add(new ValidateMarketOfferEquipmentRule(offerProduct, cartItem,offerId));
							}

							if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, OFFER})) {
								specList.add(new ValidateProductComponentMarketOffeRule(offerProduct, cartItem,offerId));
							}

							if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, ADDON})) {
								specList.add(new ValidateMandatoryAddOnsNotInCartRule(offerProduct,cartItem,offerId));
							}
							
							if(EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, FEATURE})){
								specList.add(new ValidateMarketOfferFeatureValidationRule(cartItem, offerProduct,offerId));
							}						
						}	

						/**
						 * Processing the tasks
						 */
						for (AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> spec: specList){ 
							isSatisfied = isSatisfied && spec.isSatisfiedBy(shoppingCartCtxVO, traces);
							if(!CollectionUtils.isEmpty(getResult())){
								traces = getResult();
							}
						}

					}else{
						//return error
						addTrace(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.MARKET_OFFER_VALIDATION, "Market offer has no Wireline products returned."),traces,trace,offerDetailResponseVO,cartItem);
						isSatisfied = false;
					}
					
					
					
				}else{
					logger.error(this.getClass().getSimpleName() + ": failed, Market Offer with id=" + offerId + " was not retrieved for cartItemRelationId=" + cartItemRelationId);
					String msg;
					if(offerDetailResponseVO!=null && CollectionUtils.isNotEmpty(offerDetailResponseVO.getMessageList())){
						msg = this.getClass().getSimpleName() + ": Call to OIS to get market offer detail failed, check ContextDataAttributes for detail information.";
					}else{
						msg = this.getClass().getSimpleName() + "Call to OIS to get market offer detail failed";
					}
					addTrace(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.MARKET_OFFER_VALIDATION, msg), traces, trace, offerDetailResponseVO,cartItem);
					isSatisfied = false;					
				}
			}
		
		return isSatisfied; 
	}
	
	private void addTrace(ShoppingCartValidationErrors errorDetail, List<ShoppingCartValidationTraceVO> traces, ShoppingCartValidationTraceVO trace,GetSalesOfferDetailResponseVO2 offerDetailResponseVO, CartItemVO cartItem2){
		if(offerDetailResponseVO!=null && !CollectionUtils.isEmpty(offerDetailResponseVO.getMessageList())){
			trace.setMessageList(offerDetailResponseVO.getMessageList());
		}
		trace.setErrors(errorDetail);
		trace.setValidationPassedInd(false);
		trace.setCartItemRelationId(cartItem.getCartItemRelationId());
		traces.add(trace);
	}
	
	

}
