package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.CrossProductDependency;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;


@Deprecated
public class DiscountCrossProductDependencyRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(DiscountCrossProductDependencyRule.class);
	
	private CartItemVO cartItem;
	

	public DiscountCrossProductDependencyRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied=true;
//		String functionName = "DiscountCrossProductDependencyRule.isSatisfied()";
//		logger.enter(functionName);
//		String cartItemRelationId = null;
//		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(DiscountCrossProductDependencyRule.class.getName());
//		if(cartItem!=null){
//			
//			if(!StringUtils.isBlank(cartItem.getCartItemRelationId())){
//				cartItemRelationId = cartItem.getCartItemRelationId();
//			}
//			
//			if(cartItem.getProductMarketOffering()!=null && cartItem.getProductMarketOffering().getOfferHeader()!=null && !StringUtils.isBlank(cartItem.getProductMarketOffering().getOfferHeader().getOfferId())){
//				String selectedOfferId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId();
//				GetSalesOfferDetailResponseVO2 offerDetailResponseVO = shoppingCartContextVO.getOfferByCartItemOfferId(selectedOfferId);
//				if(offerDetailResponseVO!=null && offerDetailResponseVO.getOffer()!=null){
//					/************************************************* Getting the cross product dependency discounts from the selected market offer *******************************************/
//					Map<String,List<Discount>> crossProductDiscountMap = getCrossProductDependencyDiscountsFromCart(offerDetailResponseVO,shoppingCartContextVO.getShoppingCartVO());
//					
//					if(!CollectionUtils.isEmpty(crossProductDiscountMap.values())){
//						/************************************************* Getting the filtered cross product discounts from the selected market offer plus the following criteria ******************/
//						
//						List<String> invalidCrossProductDiscountList = validateCrossProductDiscounts(crossProductDiscountMap,shoppingCartContextVO);
//						
//						
//						if(!CollectionUtils.isEmpty(invalidCrossProductDiscountList)){
//							isSatisfied = false;
//							trace.setValidationPassedInd(isSatisfied);
//							trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.DISCOUNT_CROSS_PRODUCT_DEPENDENCY, this.getClass().getSimpleName() + "One or more discounts in the cartItem with cartItemRelationId=" + cartItemRelationId + " requires that a customer be ordering or have other product(s) on specific terms however that product and term was not found in the cart, nor the list of pending orders nor the list of existing services, details: " + EnterpriseWLNOrderUtil.getFormatedStringList(invalidCrossProductDiscountList)));
//							traces.add(trace);
//						}
//					}else{
//						logger.debug(functionName, "Rule skipped as there is no cross product dependency among the discounts for this cartItem, cartItemRelationId= " + cartItemRelationId);
//					}
//					
//				}
//			}else{
//				logger.debug(functionName, "Skipping rule since there is no offerId passed in the request, for cartItemRelationId=" + cartItemRelationId);
//			}
//			
//			
//		}
//		
//		logger.exit(functionName);
//		
		return isSatisfied;
	}

//	private List<String> validateCrossProductDiscounts(Map<String, List<Discount>> crossProductDiscountMap,ShoppingCartContextVO contextVO) {
//		List<String> invalidCrossProductDiscountList = new ArrayList<String>();
//		for(String productType : crossProductDiscountMap.keySet()){
//			List<Discount> discountList = crossProductDiscountMap.get(productType);
//			for(Discount discount : discountList){
//				if(!EnterpriseWLNOrderUtil.isDiscountAvailableForCrossProduct(contextVO, productType, discount)){
//					invalidCrossProductDiscountList.add("the " + productType + " Discount with discountCode=" + discount.getDiscountCode() + " is not valid since it does not complies with the cross product dependency products required by this discount. Product dependency for this discount: product=" + getDependencyDetails(discount.getCrossProductDependencyList()));
//				}
//			}
//		}
//		return invalidCrossProductDiscountList;
//	}
//
//	private Map<String, List<Discount>> getCrossProductDependencyDiscountsFromCart(GetSalesOfferDetailResponseVO2 offerDetailResponseVO, ShoppingCartVO shoppingCartVO) {
//		Map<String,List<Discount>> crossProductDiscountMap = new HashMap<String, List<Discount>>();
//		
//		List<WirelineOfferProduct> wirelineOfferProductList = offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList();
//		
//		for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
//			if(cartItem.isSalesOrderItem() && cartItem.getProducts()!=null){
//				for(WirelineOfferProduct wirelineOfferProduct : wirelineOfferProductList){
//					if(cartItem.getProducts().getInternetProduct()!=null){
//						InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
//						if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
//							List<Discount> internetCrossProductDiscountList = getCrossProductDiscountListForProduct(internetProduct.getDiscounts(), wirelineOfferProduct.getDiscountList());
//							if(!CollectionUtils.isEmpty(internetCrossProductDiscountList)){
//								crossProductDiscountMap.put(EnterpriseWLNSalesServicesConstants.HSIC, internetCrossProductDiscountList);
//							}
//						}
//					}
//
//					if(cartItem.getProducts().getTelevisionProduct()!=null){
//						TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();					
//						if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
//							List<Discount> televisionCrossProductDiscountList = getCrossProductDiscountListForProduct(televisionProduct.getDiscounts(), wirelineOfferProduct.getDiscountList());
//							if(!CollectionUtils.isEmpty(televisionCrossProductDiscountList)){
//								crossProductDiscountMap.put(EnterpriseWLNSalesServicesConstants.TTV, televisionCrossProductDiscountList);
//							}							
//						}						
//					}
//
//					if(cartItem.getProducts().getHomePhoneProduct()!=null){
//						HomePhoneProductTypeVO homePhoneProduct = cartItem.getProducts().getHomePhoneProduct();					
//						if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
//							List<Discount> homePhoneCrossProductDiscountList = getCrossProductDiscountListForProduct(homePhoneProduct.getDiscounts(), wirelineOfferProduct.getDiscountList());
//							if(!CollectionUtils.isEmpty(homePhoneCrossProductDiscountList)){
//								crossProductDiscountMap.put(EnterpriseWLNSalesServicesConstants.SING, homePhoneCrossProductDiscountList);
//							}							
//						}
//					}
//				}			
//			}
//		}
//		return crossProductDiscountMap;
//	}
//
//	
//
//	private String getDependencyDetails(List<CrossProductDependency> crossProductDependencyList) {
//		StringBuilder sb = new StringBuilder();
//		if(!CollectionUtils.isEmpty(crossProductDependencyList)){
//			for(CrossProductDependency crossProductDependency : crossProductDependencyList){
//				sb.append("ProductType=" + crossProductDependency.getProductCode() + " , term: " + crossProductDependency.getContractTermList().toString());
//			}
//		}
//		return sb.toString();
//	}
//
//	private List<Discount> getCrossProductDiscountListForProduct(List<FFHDiscountTypeVO> cartDiscounts,List<Discount> offerDiscountList) {
//		List<Discount> crossProductDiscountList = new ArrayList<Discount>();
//		
//		if(!CollectionUtils.isEmpty(cartDiscounts)){
//			for(FFHDiscountTypeVO cartItemDiscount : cartDiscounts){
//				String cartDiscountCode = cartItemDiscount.getDiscountCode();
//				if(!CollectionUtils.isEmpty(offerDiscountList)){
//					for(Discount offerDiscount : offerDiscountList){
//						if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartItemDiscount.getProductCatalogueIdentifiers().get(0).getAction())){
//							if(cartDiscountCode.equalsIgnoreCase(offerDiscount.getDiscountCode()) && EnterpriseWLNCommonTransformer.catalogueIdMatches(offerDiscount, cartItemDiscount) && !CollectionUtils.isEmpty(offerDiscount.getCrossProductDependencyList())){
//								crossProductDiscountList.add(offerDiscount);
//							}
//						}
//						
//					}
//				}
//
//			}
//		}
//		return crossProductDiscountList;
//	}
	
}
