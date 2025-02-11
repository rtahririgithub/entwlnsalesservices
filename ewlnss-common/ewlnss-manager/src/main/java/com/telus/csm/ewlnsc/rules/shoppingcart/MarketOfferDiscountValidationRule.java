package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
public class MarketOfferDiscountValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(MarketOfferDiscountValidationRule.class);
	
	private CartItemVO cartItemVO;
	
	public MarketOfferDiscountValidationRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}


	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		
		String functionName="MarketOfferDiscountValidationRule";
		logger.enter(functionName);
		boolean isSatisfied=true;
		String offerId="";
		String cartItemRelationId="";
		GetSalesOfferDetailResponseVO2 offerDetailResponseVO=null;
		//List<String> nonStackableDiscountCodeList = new ArrayList<String>();
		List<String> notFoundDiscountList = new ArrayList<String>();
		List<String> nonValidWinbackList = new ArrayList<String>();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getSimpleName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();		
		
		if(shoppingCartVO.getCartItemList()!=null && !shoppingCartVO.getCartItemList().isEmpty()){
			 //the cartItem element means market offer
				if (cartItemVO.isSalesOrderItem() && !StringUtils.isEmpty(cartItemVO.getCartItemRelationId())) {
					
					cartItemRelationId=cartItemVO.getCartItemRelationId();
					
					offerDetailResponseVO = ctxVO.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());
					
					if(offerDetailResponseVO!=null && offerDetailResponseVO.getOffer()!=null && offerDetailResponseVO.getOffer().getOfferProduct()!=null
							&& !CollectionUtils.isEmpty(offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())){
						offerId=String.valueOf(offerDetailResponseVO.getOffer().getOfferId());
						List<WirelineOfferProduct> wirelineOfferProductList = offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList();
					
						ProductTypeVO cartItemProducts = cartItemVO.getProducts();	
						if(cartItemProducts!=null){
							
							if(cartItemProducts.getInternetProduct()!=null){
								InternetProductTypeVO internetProduct = cartItemProducts.getInternetProduct();
								if(!CollectionUtils.isEmpty(internetProduct.getDiscounts())){
									//Getting the discounts of Internet product for this offer
									for(WirelineOfferProduct wirelineProduct : wirelineOfferProductList){
										if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
											if(CollectionUtils.isEmpty(wirelineProduct.getDiscountList()) && !EnterpriseWLNOrderUtil.isRemoveDiscounts(internetProduct.getDiscounts())){
												notFoundDiscountList.add("There are Discounts in the CartItem with cartItemRelationId= " + cartItemRelationId + " for Internet product, however there are no discounts returned for Internet product for the Market Offer with offerId=" + offerId);
											} else {	
												for(FFHDiscountTypeVO cartItemInternetDiscount : internetProduct.getDiscounts()){
														if(!EnterpriseWLNOrderUtil.productComponentHasRemove(cartItemInternetDiscount.getProductCatalogueIdentifiers())){
															Discount offerDiscount = getOfferDiscount(wirelineProduct, cartItemInternetDiscount);
															if(offerDiscount == null){
																notFoundDiscountList.add(cartItemInternetDiscount.getDiscountCode());
															} else {
//																if(!offerDiscount.isStackableInd() && existsOtherOptionalDiscount(wirelineProduct, cartItemInternetDiscount, internetProduct.getDiscounts())){
//																	nonStackableDiscountCodeList.add(cartItemInternetDiscount.getDiscountCode());																			
//																}

																if(offerDiscount.isWinbackInd() && (internetProduct.getWinback()==null || !internetProduct.getWinback())){
																	nonValidWinbackList.add("Internet discount, with discount code = " + cartItemInternetDiscount.getDiscountCode());
																}
															}
														}
														
													
												}
											}
										}
									}									
								}
							}

							if(cartItemProducts.getTelevisionProduct()!=null){
								TelevisionProductTypeVO televisionProduct = cartItemProducts.getTelevisionProduct();
								if(!CollectionUtils.isEmpty(televisionProduct.getDiscounts())){
									//Getting the discounts of TTV product for this offer
									for(WirelineOfferProduct wirelineProduct : wirelineOfferProductList){
										if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
											if(CollectionUtils.isEmpty(wirelineProduct.getDiscountList())){
												notFoundDiscountList.add("There are Discounts in the CartItem with cartItemRelationId= " + cartItemRelationId + " for Television product, however there are no discounts returned for Television product for the Market Offer with offerId=" + offerId);
											} else {	
												for(FFHDiscountTypeVO cartItemTelevisionDiscount : televisionProduct.getDiscounts()){
													if(!EnterpriseWLNOrderUtil.productComponentHasRemove(cartItemTelevisionDiscount.getProductCatalogueIdentifiers())){
														Discount offerDiscount = getOfferDiscount(wirelineProduct, cartItemTelevisionDiscount);
														if(offerDiscount == null){
															notFoundDiscountList.add(cartItemTelevisionDiscount.getDiscountCode());
														} else {
//															if(!offerDiscount.isStackableInd() && existsOtherOptionalDiscount(wirelineProduct, cartItemTelevisionDiscount, televisionProduct.getDiscounts())){
//																nonStackableDiscountCodeList.add(cartItemTelevisionDiscount.getDiscountCode());																			
//															}

															if(offerDiscount.isWinbackInd() && (televisionProduct.getWinback()==null || !televisionProduct.getWinback())){
																nonValidWinbackList.add("Television discount, with discount code = " + cartItemTelevisionDiscount.getDiscountCode());
															}
														}
													}													
												}
											}											
										}									
									}									
								}
							}

							if(cartItemProducts.getHomePhoneProduct()!=null){
								HomePhoneProductTypeVO homePhoneProduct = cartItemProducts.getHomePhoneProduct();
								if(!CollectionUtils.isEmpty(homePhoneProduct.getDiscounts())){
									//Getting the discounts of SING product for this offer
									for(WirelineOfferProduct wirelineProduct : wirelineOfferProductList){
										if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
											if(CollectionUtils.isEmpty(wirelineProduct.getDiscountList())){
												notFoundDiscountList.add("There are Discounts in the CartItem with cartItemRelationId= " + cartItemRelationId + " for Home Phone product, however there are no discounts returned for Home Phone product for the Market Offer with offerId=" + offerId);
											} else {	
												for(FFHDiscountTypeVO cartItemHomePhoneDiscount : homePhoneProduct.getDiscounts()){
													if(!EnterpriseWLNOrderUtil.productComponentHasRemove(cartItemHomePhoneDiscount.getProductCatalogueIdentifiers())){
														Discount offerDiscount = getOfferDiscount(wirelineProduct, cartItemHomePhoneDiscount);
														if(offerDiscount == null){
															notFoundDiscountList.add(cartItemHomePhoneDiscount.getDiscountCode());
														} else {
//															if(!offerDiscount.isStackableInd() && existsOtherOptionalDiscount(wirelineProduct, cartItemHomePhoneDiscount, homePhoneProduct.getDiscounts())){
//																nonStackableDiscountCodeList.add(cartItemHomePhoneDiscount.getDiscountCode());																			
//															}

															if(offerDiscount.isWinbackInd() && (homePhoneProduct.getWinback()==null || !homePhoneProduct.getWinback())){
																nonValidWinbackList.add("Home Phone discount, with discount code = " + cartItemHomePhoneDiscount.getDiscountCode());
															}
														}
													}
												}
											}											
										}									
									}									
								}
							}
						}
				
						//Commented out. July, 2019. Bundle Builder redesign			
//						if(!CollectionUtils.isEmpty(nonStackableDiscountCodeList)){
//							ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.NON_STACKABLE_MARKET_OFFER_DISCOUNT);
//							shoppingCartValidationError.setMessage(this.getClass().getSimpleName()+ ": One or more discounts in the cartItem: " + cartItemRelationId + " is configured in the market offer: " + offerId + " as non-stackable but the cartItem includes another discount.  Expected no other discounts. List of non stackable discounts: " + nonStackableDiscountCodeList.toString());							
//							trace.setValidationPassedInd(false);
//							trace.setErrors(shoppingCartValidationError);							
//							trace.setShoppingCartId(shppoingCartId);	
//							trace.setCartItemRelationId(cartItemRelationId);
//							traces.add(trace);
//							isSatisfied = false;
//						}
						
						if(!CollectionUtils.isEmpty(notFoundDiscountList)){
							ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.MARKET_DISCOUNT_CODES_NOT_EXISTING);
							shoppingCartValidationError.setMessage(this.getClass().getSimpleName() + ": One or more discounts in the cartItem: " + cartItemRelationId + " is not found in the market offer:" + offerId + " List of discounts not found: " + notFoundDiscountList);							
							trace.setValidationPassedInd(false);
							trace.setErrors(shoppingCartValidationError);
							trace.setShoppingCartId(shppoingCartId);	
							trace.setCartItemRelationId(cartItemRelationId);
							traces.add(trace);
							isSatisfied=false;
						}
						
						if(!CollectionUtils.isEmpty(nonValidWinbackList)){
							ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.MARKET_OFFER_WINBACK_DISCOUNT);
							shoppingCartValidationError.setMessage("DiscountWinbackRule" + ": One or more discounts in the cartItem: " + cartItemRelationId + " requires that a customer be performing a winback/disconnect of that productType however that productType is not specified as winback, list of winback discounts: " + nonValidWinbackList);							
							trace.setValidationPassedInd(false);
							trace.setErrors(shoppingCartValidationError);
							trace.setShoppingCartId(shppoingCartId);	
							trace.setCartItemRelationId(cartItemRelationId);
							traces.add(trace);
							isSatisfied=false;
						}
				}else{
					ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.MARKET_DISCOUNT_CODES_NOT_EXISTING);
					shoppingCartValidationError.setMessage(this.getClass().getSimpleName() + ": offer is not found for cartItem: " + cartItemRelationId);					
					trace.setValidationPassedInd(false);
					trace.setErrors(shoppingCartValidationError);					
					trace.setShoppingCartId(shppoingCartId);	
					trace.setCartItemRelationId(cartItemRelationId);
					traces.add(trace);
					isSatisfied=false;
				}
			}else{
				logger.debug(functionName, "Rule skipped. cartItem is: market Offer=" + cartItemVO.isSalesOrderItem() + " and cartItemRelationId=" + cartItemVO.getCartItemRelationId());
			}
		}
		return isSatisfied;
	}


	


	private Discount getOfferDiscount(WirelineOfferProduct wirelineProduct, FFHDiscountTypeVO cartItemDiscount) {
		
		for(Discount offerDiscount : wirelineProduct.getDiscountList()){
			String offerDiscountCode = offerDiscount.getDiscountCode();
			if(cartItemDiscount.getDiscountCode().equalsIgnoreCase(offerDiscountCode) && EnterpriseWLNCommonTransformer.catalogueIdMatches(offerDiscount,cartItemDiscount)){
				return offerDiscount;
			}
		}
		
		return null;
	}
	

	private boolean existsOtherOptionalDiscount(WirelineOfferProduct wirelineProduct,
			FFHDiscountTypeVO cartItemDiscount, List<FFHDiscountTypeVO> discounts) {
		
		for (FFHDiscountTypeVO otherDiscount : discounts) {
			if (!cartItemDiscount.getDiscountCode().equals(otherDiscount.getDiscountCode())) {
				 Discount otherTomDiscount = getOfferDiscount(wirelineProduct, otherDiscount);
				 if (otherTomDiscount != null && !otherTomDiscount.isIncludedInd()) {
					 return true;
				 }
			}
		}
		
		return false;
	}


}
