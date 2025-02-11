package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;

public class CartItemSweetenerEligibilityRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(CartItemSweetenerEligibilityRule.class);
	private CartItemVO cartItem;
	
	
	public CartItemSweetenerEligibilityRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		
		String functionName = "CartItemSweetenerEligibilityRule.execute()";
		
		boolean isSatisfied=true;
		String cartItemRelationId= "";
		//List<String> nonStackableSweetenerDiscountCodeList = new ArrayList<String>();
		List<String> sweetenerDiscountNotFoundList = new ArrayList<String>();
		List<String> sweetenerOfferIsNotEligibleList = new ArrayList<String>();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(CartItemSweetenerEligibilityRule.class.getName());
		
		String shppoingCartId = ctxVO.getShoppingCartVO().getShoppingCartId();		
		
		if (cartItem.isSalesOrderItem() && !StringUtils.isEmpty(cartItem.getCartItemRelationId())) {
			
			cartItemRelationId = cartItem.getCartItemRelationId();
			
			ProductTypeVO cartItemProducts = cartItem.getProducts();
			
			if(cartItemProducts!=null){
				
			if(cartItemProducts.getInternetProduct()!=null){
					InternetProductTypeVO internetProduct = cartItemProducts.getInternetProduct();
					if(internetProduct.getSweeteners()!=null && !internetProduct.getSweeteners().isEmpty()){
						for(FFHSweetenerTypeVO internetSweetener : internetProduct.getSweeteners()){
							String cartSweetenrPromoId = internetSweetener.findPromotionId();
							List<SweetenerOfferSummary> sweetenerList = ctxVO.getSweetenersInShoppingCart();
							boolean matchOfferInd=false;
							if(!CollectionUtils.isEmpty(sweetenerList)){
								for(SweetenerOfferSummary sweetenerOfferSummary : sweetenerList){
									if(StringUtils.isNotBlank(cartSweetenrPromoId) && cartSweetenrPromoId.equals(String.valueOf(sweetenerOfferSummary.getPromotionId()))){
										matchOfferInd=true;										
										logger.info(functionName, "Sweetener was found in sweeteners with promotionId: " + cartSweetenrPromoId);
										if(internetSweetener.getDiscounts()!=null && !internetSweetener.getDiscounts().isEmpty()){
											for(FFHDiscountTypeVO discountVO : internetSweetener.getDiscounts()){
												if(!EnterpriseWLNOrderUtil.productComponentHasRemove(discountVO.getProductCatalogueIdentifiers())){
													String cartDiscountCode = discountVO.getDiscountCode();
													boolean matchFoundInd=false;
													if(sweetenerOfferSummary.getOfferProductSummary()!=null && !CollectionUtils.isEmpty(sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList())){
														for(WirelineOfferProductSummary sweetenerOffer : sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList()){
															if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(sweetenerOffer.getProductTypeCode())){

																if(sweetenerOffer.getDiscountList()!=null && !sweetenerOffer.getDiscountList().isEmpty()){						
																	for(Discount offerDiscount : sweetenerOffer.getDiscountList()){							
																		if(cartDiscountCode.equals(offerDiscount.getDiscountCode()) && EnterpriseWLNCommonTransformer.catalogueIdMatches(offerDiscount,discountVO)){
																			matchFoundInd=true;
//																			if(!offerDiscount.isStackableInd() && internetSweetener.getDiscounts().size()>1){
//
//																				nonStackableSweetenerDiscountCodeList.add(offerDiscount.getDiscountCode());
//																				break;
//																			}
																		}

																	}

																}

															}
														}
													}
													if(!matchFoundInd){
														sweetenerDiscountNotFoundList.add(cartDiscountCode);
													}
												}
											}
										}
										
									}
								}
							}
							if(!matchOfferInd){
								sweetenerOfferIsNotEligibleList.add(cartSweetenrPromoId);
							}
							
						}
						
					}
				}
				//checking Television
				if(cartItemProducts.getTelevisionProduct()!=null){
					TelevisionProductTypeVO televisionProduct = cartItemProducts.getTelevisionProduct();
					if(televisionProduct.getSweeteners()!=null && !televisionProduct.getSweeteners().isEmpty()){
						for(FFHSweetenerTypeVO televisionSweetener : televisionProduct.getSweeteners()){
							String cartSweetenrPromoId = televisionSweetener.findPromotionId();
							List<SweetenerOfferSummary> sweetenerList = ctxVO.getSweetnersByProduct(EnterpriseWLNSalesServicesConstants.TTV,cartItemRelationId);
							boolean matchOfferInd=false;
							if(!CollectionUtils.isEmpty(sweetenerList)){
								for(SweetenerOfferSummary sweetenerOfferSummary : sweetenerList){
									if(StringUtils.isNotBlank(cartSweetenrPromoId) && cartSweetenrPromoId.equals(String.valueOf(sweetenerOfferSummary.getPromotionId()))){
										matchOfferInd=true;
										logger.info(functionName, "Sweetener was found in sweeteners with promotionId: " + cartSweetenrPromoId);
										if(televisionSweetener.getDiscounts()!=null && !televisionSweetener.getDiscounts().isEmpty()){
											for(FFHDiscountTypeVO discountVO : televisionSweetener.getDiscounts()){
												if(!EnterpriseWLNOrderUtil.productComponentHasRemove(discountVO.getProductCatalogueIdentifiers())){
													String cartDiscountCode = discountVO.getDiscountCode();
													if(sweetenerOfferSummary.getOfferProductSummary()!=null && !CollectionUtils.isEmpty(sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList())){
														for(WirelineOfferProductSummary sweetenerOffer : sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList()){
															if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(sweetenerOffer.getProductTypeCode())){
																boolean matchFoundInd=false;
																if(sweetenerOffer.getDiscountList()!=null && !sweetenerOffer.getDiscountList().isEmpty()){						
																	for(Discount offerDiscount : sweetenerOffer.getDiscountList()){
																		if(cartDiscountCode.equals(offerDiscount.getDiscountCode()) && EnterpriseWLNCommonTransformer.catalogueIdMatches(offerDiscount,discountVO)){
																			matchFoundInd=true;
//																			if(!offerDiscount.isStackableInd() && televisionSweetener.getDiscounts().size()>1){
//																				nonStackableSweetenerDiscountCodeList.add(cartDiscountCode);
//																				break;
//																			}
																		}
																	}

																}
																if(!matchFoundInd){
																	sweetenerDiscountNotFoundList.add(cartDiscountCode);
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
							if(!matchOfferInd){
								sweetenerOfferIsNotEligibleList.add(cartSweetenrPromoId);
							}
						}
						
					}
				}
				//checking Home phone
				if(cartItemProducts.getHomePhoneProduct()!=null){
					HomePhoneProductTypeVO homePhoneProduct = cartItemProducts.getHomePhoneProduct();
					if(homePhoneProduct.getSweeteners()!=null && !homePhoneProduct.getSweeteners().isEmpty()){
						for(FFHSweetenerTypeVO singSweetener : homePhoneProduct.getSweeteners()){
							String cartSweetenrPromoId = singSweetener.findPromotionId();
							List<SweetenerOfferSummary> sweetenerList = ctxVO.getSweetnersByProduct(EnterpriseWLNSalesServicesConstants.SING,cartItemRelationId);
							boolean matchOfferInd=false;
							if(!CollectionUtils.isEmpty(sweetenerList)){
								
								for(SweetenerOfferSummary sweetenerOfferSummary : sweetenerList){
									
									if(StringUtils.isNotBlank(cartSweetenrPromoId) && cartSweetenrPromoId.equals(String.valueOf(sweetenerOfferSummary.getPromotionId()))){
										matchOfferInd=true;
										logger.info(functionName, "Sweetener was found in sweeteners with promotionId: " + cartSweetenrPromoId);
										if(singSweetener.getDiscounts()!=null && !singSweetener.getDiscounts().isEmpty()){

											for(FFHDiscountTypeVO discountVO : singSweetener.getDiscounts()){
												if(!EnterpriseWLNOrderUtil.productComponentHasRemove(discountVO.getProductCatalogueIdentifiers())){
													String cartDiscountCode = discountVO.getDiscountCode();
													if(sweetenerOfferSummary.getOfferProductSummary()!=null && !CollectionUtils.isEmpty(sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList())){
														for(WirelineOfferProductSummary sweetenerOffer : sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList()){
															if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(sweetenerOffer.getProductTypeCode())){
																boolean matchFoundInd=false;
																if(sweetenerOffer.getDiscountList()!=null && !sweetenerOffer.getDiscountList().isEmpty()){						
																	for(Discount offerDiscount : sweetenerOffer.getDiscountList()){
																		if(cartDiscountCode.equals(offerDiscount.getDiscountCode()) && EnterpriseWLNCommonTransformer.catalogueIdMatches(offerDiscount,discountVO)){
																			matchFoundInd=true;
//																			if(!offerDiscount.isStackableInd() && singSweetener.getDiscounts().size()>1){
//																				nonStackableSweetenerDiscountCodeList.add(cartDiscountCode);
//																				break;
//																			}
																		}
																	}
																}
																if(!matchFoundInd){
																	sweetenerDiscountNotFoundList.add(cartDiscountCode);
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
							if(!matchOfferInd){
								sweetenerOfferIsNotEligibleList.add(cartSweetenrPromoId);
							}

						}

					}
				}
				
			}
//Commented out. July, 2019. Bundle Builder redesign			
//			if(!CollectionUtils.isEmpty(nonStackableSweetenerDiscountCodeList)){
//				ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.NON_STACKABLE_SWEETENER_DISCOUNT);
//				shoppingCartValidationError.setMessage("SweetenerDiscountStackableRule:One or more sweetener in the cartItem " + cartItemRelationId + " is configured in the sweetener offer as non-stackable but the cartItem includes another sweetener.  Expected no other sweeteners. List of sweetener codes: " + nonStackableSweetenerDiscountCodeList.toString());
//				trace.setErrors(shoppingCartValidationError);
//				trace.setValidationPassedInd(false);
//				trace.setShoppingCartId(shppoingCartId);	
//				trace.setCartItemRelationId(cartItemRelationId);
//				traces.add(trace);
//				isSatisfied = false;
//			}
			
			if(!CollectionUtils.isEmpty(sweetenerDiscountNotFoundList)){
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.SWEETENER_DISCOUNT_CODES_NOT_FOUND);
				shoppingCartValidationError.setMessage("SweetenerOfferDiscountEligibilityRule: One or more sweetener discounts were not found for cartItem:" + cartItemRelationId + " discount codes: " + sweetenerDiscountNotFoundList);
				trace.setErrors(shoppingCartValidationError);
				trace.setValidationPassedInd(false);
				trace.setShoppingCartId(shppoingCartId);	
				trace.setCartItemRelationId(cartItemRelationId);
				traces.add(trace);
				isSatisfied=false;
			}
			
			if(!CollectionUtils.isEmpty(sweetenerOfferIsNotEligibleList)){
				ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.SWEETENER_OFFER_NOT_FOUND);
				shoppingCartValidationError.setMessage("SweetenerOfferEligibilityRule: sweetener offers were not found for cartItem: " + cartItemRelationId + " , sweetener offers: " + sweetenerOfferIsNotEligibleList);
				trace.setErrors(shoppingCartValidationError);
				trace.setValidationPassedInd(false);
				trace.setShoppingCartId(shppoingCartId);	
				trace.setCartItemRelationId(cartItemRelationId);
				traces.add(trace);
				isSatisfied=false;
			}

		}else{
			logger.debug(functionName, "Rule skipped. cartItem is: market Offer=" + cartItem.isSalesOrderItem() + " and cartItemRelationId=" + cartItem.getCartItemRelationId());
		}
		return isSatisfied;
	}

}
