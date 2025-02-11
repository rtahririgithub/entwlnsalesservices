package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.IPromotion;
import com.telus.csm.ewlnsc.domain.ProductTypeBaseVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;

 
/** 
 * Validation promotion list against their groups (mandatory-unlimited, mandatory-optional and optional) 
 * properties like min, max, stack ability.
 * @author x145592
 *
 */
public class ValidatePromotionListRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {	
	
	private static final Logger LOGGER = Logger.getLogger(ValidatePromotionListRule.class);
	
	private CartItemVO cartItem;
	
	private int nonStackableGrpCount = 0;

	private Set<String> nonStackableGrpSet = new HashSet<String>();
	
	private String errMsg = null;
	
	public ValidatePromotionListRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	/**
	 * Entry point in this rule.
	 */
	@Override
	public boolean isSatisfiedBy(final ShoppingCartContextVO scCtx, final List<ShoppingCartValidationTraceVO> traces) {
		boolean isSatisfied = true;
		
		final List<IPromotion> allQualifiedPromoList = new ArrayList<IPromotion>();
		if (scCtx.getAvailableAccessoryResponse() != null) {
			allQualifiedPromoList.addAll(scCtx.getAvailableAccessoryResponse().getAccessoryOfferSummaryList());
		}
		
		if (scCtx.getAvailableSweetenerOfferListResponseVO() != null) {
			allQualifiedPromoList.addAll(scCtx.getAvailableSweetenerOfferListResponseVO().getSweetenerOfferSummaryList());
		}

		final List<GetSalesOfferDetailResponseVO2> offerList = scCtx.getOfferList();
		for (final GetSalesOfferDetailResponseVO2 offerElem : offerList) {
			Offer offer = offerElem.getOffer();
			if (offer != null) {
				for (final WirelineOfferProduct elem : offer.getOfferProduct().getWirelineOfferProductList()) {
					allQualifiedPromoList.addAll(new ArrayList<IPromotion>(elem.getDiscountList()));
				}
			}
		}
		
		final List<String> allRequestedPromoIds = getPromoListFromRequest(scCtx.getShoppingCartVO().getCartItemList());
	
		if (allRequestedPromoIds.size() > allQualifiedPromoList.size()) {
			errMsg = "customer is requesting for more promotions than he is qualified for.";
			isSatisfied = isNotSatisfied(traces, errMsg);			
		}
		
		if (!requestPromoIdsInAllQualifiedFound(allRequestedPromoIds, allQualifiedPromoList)) {
			errMsg = "customer is not qualified for all requested promotion ids.";
			isSatisfied = isNotSatisfied(traces, errMsg);
		}
		
		if (CollectionUtils.isEmpty(allQualifiedPromoList)) {
			LOGGER.info("subscriber is not qualified for any promotion");
			return true;
		}
		
		final Map<String, Map<String, IPromotion>> allMandUltMap = new HashMap<String, Map<String,IPromotion>>();
		final Map<String, Map<String, IPromotion>> allMandOptMap = new HashMap<String, Map<String,IPromotion>>();
		final Map<String, Map<String, IPromotion>> allOptionalMap = new HashMap<String, Map<String,IPromotion>>();

		final Map<String, List<String>> regMandUltMap = new HashMap<String, List<String>>();
		final Map<String, List<String>> reqMandOptMap = new HashMap<String, List<String>>();
		final Map<String, List<String>> reqOptionalMap = new HashMap<String, List<String>>();

		for (final IPromotion elem : allQualifiedPromoList) {
			if(elem.getPromotionGroup() != null && StringUtils.isNotBlank(elem.getPromotionGroup().getEnforcementType())) {
				String grpType = elem.getPromotionGroup().getEnforcementType();
				if ("MANDATORY_UNLIMITED".equalsIgnoreCase(grpType)) {
					addToGroupMap(allMandUltMap, elem, regMandUltMap, allRequestedPromoIds);
				} else if ("MANDATORY_CHOICE".equalsIgnoreCase(grpType)) {
					addToGroupMap(allMandOptMap, elem, reqMandOptMap, allRequestedPromoIds);
				} else if ("OPTIONAL".equalsIgnoreCase(grpType)) {
					addToGroupMap(allOptionalMap, elem, reqOptionalMap, allRequestedPromoIds);
				}
			}
		}
		
		if ((CollectionUtils.isEmpty(allRequestedPromoIds) && !allMandUltMap.isEmpty()) ||
				(!allMandUltMap.isEmpty() && regMandUltMap.isEmpty())) {
			errMsg = "customer is qualified for mandatory unlimited promotions but not requesting any";
			isSatisfied = isNotSatisfied(traces, errMsg);
		}
		
		for (final String grpKeyElem : allMandUltMap.keySet()) {
			Map<String, IPromotion> grpAllValue = allMandUltMap.get(grpKeyElem);
			List<String> grpIdsList = regMandUltMap.get(grpKeyElem);
			if (!compareMandUltGroups(grpAllValue, grpIdsList)) {
				errMsg = "promotion id list from request does not match the full list " + grpAllValue.keySet() + " of manadatory unlimited promotions under this group id: " + grpKeyElem;
				isSatisfied = isNotSatisfied(traces, errMsg);
			}
		}
		
		if (!allMandOptMap.isEmpty() && reqMandOptMap.isEmpty()) {
			errMsg = "customer is qualified for mandatory choice promotions but not requesting any";
			isSatisfied = isNotSatisfied(traces, errMsg);
		}
		
		for (final String grpKeyElem : reqMandOptMap.keySet()) {
			List<String> reqMandOptPromoIds = reqMandOptMap.get(grpKeyElem);
			Map<String, IPromotion> grpAllValue = allMandOptMap.get(grpKeyElem);
			checkMandOptGroupRules(grpAllValue, reqMandOptPromoIds, traces);
		}

		for (final String grpKeyElem : reqOptionalMap.keySet()) {
			List<String> reqOptionalIds = reqOptionalMap.get(grpKeyElem);
			Map<String, IPromotion> grpAllValue = allOptionalMap.get(grpKeyElem);	
			checkOptGroupRules(grpAllValue, reqOptionalIds, traces);
		}
		
		if (nonStackableGrpCount >= 2) {
			errMsg = "customer requested promotions from 2 or more optional non-stackable groups";
			isSatisfied = isNotSatisfied(traces, errMsg);
		} else if (nonStackableGrpCount == 1 && reqOptionalMap.size() >= 2) {
			errMsg = "customer requested promotions from at least 2 optional groups out of which one is non-stackable";
			isSatisfied = isNotSatisfied(traces, errMsg);
		}
		
		if (errMsg == null) {
			LOGGER.info("ValidatePromotionListRule Executed Successfully");
		} else {
			isSatisfied = false;
		}
		
		return isSatisfied;
	}

	/**
	 * Add a trace to the list of traces for a given error message.
	 * 
	 * @param traces
	 */
	private boolean isNotSatisfied(final List<ShoppingCartValidationTraceVO> traces, final String msg) {
		LOGGER.error(errMsg);
		final ShoppingCartValidationErrors scErr = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.PROMOTIONS_CART);
		scErr.setMessage(this.getClass().getSimpleName() + msg);
		final ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getName());
		trace.setValidationPassedInd(false);
		trace.setErrors(scErr);
		trace.setCartItemRelationId(cartItem.getCartItemRelationId());
		trace.setShoppingCartItemId(cartItem.getCartItemId());
		traces.add(trace);
		
		return false;
	}		
	
	private void addToGroupMap(final Map<String, Map<String, IPromotion>> grpMap, final IPromotion elem, final Map<String, List<String>> reqMap, final List<String> reqPromoIds) {
		final String grpCodeKey = elem.getPromotionGroup().getCode();
		final String promoIdKey = elem.getPromotionId();
		final String grpType = elem.getPromotionGroup().getEnforcementType();
		
		if (grpMap.get(grpCodeKey) == null) {
			final Map<String, IPromotion> grpValue = new HashMap<String, IPromotion>();
			grpValue.put(promoIdKey, elem);
			grpMap.put(grpCodeKey, grpValue);
			
			if (reqPromoIds.contains(elem.getPromotionId())) {
				final List<String> promoIdList = new ArrayList<String>();
				promoIdList.add(promoIdKey);
				reqMap.put(grpCodeKey, promoIdList);
			}
		} else {
			final Map<String, IPromotion> grpValue = grpMap.get(grpCodeKey);
			grpValue.put(promoIdKey, elem);
			
			if (reqPromoIds.contains(elem.getPromotionId())) {
				List<String> reqValue = reqMap.get(grpCodeKey);
				if (reqValue == null) {
					reqValue = new ArrayList<String>();
				}
				reqValue.add(promoIdKey);
				reqMap.put(grpCodeKey, reqValue);
			}
		}
		
		// check if request promotion is non-stackable and part of OPTIONAL group
		if (!elem.getPromotionGroup().isStackable() && "OPTIONAL".equalsIgnoreCase(grpType) && reqPromoIds.contains(elem.getPromotionId())) {
			LOGGER.info("requested non-stackable promotion=" + elem.getPromotionId() + " from OPTIONAL group=" + grpCodeKey);
			// make sure we only count once non-stackable requested promotions if they are from the same optional group
			if (!nonStackableGrpSet.contains(grpCodeKey)) {
				nonStackableGrpCount++;
				nonStackableGrpSet.add(grpCodeKey);
			}
		}
	}

	private boolean compareMandUltGroups(final Map<String, IPromotion> grpAll, final List<String> reqPromoIds) {
		boolean result;
		
		if (grpAll.isEmpty() && CollectionUtils.isEmpty(reqPromoIds)) {
			result = true;
			LOGGER.info("mandatory unlimited group all null and request promo ids is null");			
		} else if (grpAll.isEmpty() || CollectionUtils.isEmpty(reqPromoIds)) {
			result = false;
			if (!grpAll.isEmpty()) {
				LOGGER.error("mandatory unlimited group all is not null while request promo ids is null");
			} else {
				LOGGER.error("mandatory unlimited group all is null while request promo ids is not null");
			}
		} else if (grpAll.size() != reqPromoIds.size()) {
			result = false;
			LOGGER.error("mandatory unlimited group all and request promo ids are different size");
		} else if (!grpAll.keySet().containsAll(reqPromoIds)) {
			result = false;
			LOGGER.error("mandatory unlimited group all does not contain all request promo ids");
		} else if (!reqPromoIds.containsAll(grpAll.keySet())) {
			result = false;
			LOGGER.error("request promo ids does not contain all mandatory unlimited group");
		} else {
			result = true;
			LOGGER.info("request promo ids match mandatory unlimited group all");
		}
		
		return result;
	}
	
	private void checkMandOptGroupRules(final Map<String, IPromotion> allMandOptMap, final List<String> reqMandOptIds, final List<ShoppingCartValidationTraceVO> traces) {
		if (allMandOptMap.isEmpty() && reqMandOptIds.isEmpty()) {
			LOGGER.info("mandatory choice group all is null and request promo ids is null");
		} else if (allMandOptMap.isEmpty() || reqMandOptIds.isEmpty()) {
			if (!reqMandOptIds.isEmpty()) {
				errMsg = "no mandatory choice group were retrived and but customer requested for mandatory choice promo ids";
				isNotSatisfied(traces, errMsg);
			} else {
				errMsg = "mandatory choice group were retrived but customer did not request for any of them";
				isNotSatisfied(traces, errMsg);
			}
		} else {
			if (!allMandOptMap.keySet().containsAll(reqMandOptIds)) {
				errMsg = "there is at least one mandatory choice promotion in the request that the customer is not qualified for";
				isNotSatisfied(traces, errMsg);
			} else {
				final IPromotion firstPromoDO = (IPromotion)allMandOptMap.values().toArray()[0];
				final int min = firstPromoDO.getPromotionGroup().getMinQty();
				final int max = firstPromoDO.getPromotionGroup().getMaxQty();
				
				if (reqMandOptIds.size() < min) {
					errMsg = "mandatory choice group ids size is less than MIN configured in the group";
					isNotSatisfied(traces, errMsg);
				}
				if (reqMandOptIds.size() > max) {
					errMsg = "mandatory choice group ids size is more than MAX configured in the group";
					isNotSatisfied(traces, errMsg);
				}
			}
		}
	}
	
	private void checkOptGroupRules(final Map<String, IPromotion> allOptionalMap, final List<String> reqOptionalIds, final List<ShoppingCartValidationTraceVO> traces) {
		if (reqOptionalIds.isEmpty() && allOptionalMap.isEmpty()) {
			LOGGER.info("request optional group ids is null");
		} else if (reqOptionalIds.isEmpty() || allOptionalMap.isEmpty()) {
			if (!reqOptionalIds.isEmpty()) {
				errMsg = "at leat one optional promo is requested but customer is not qualified for any optional groups";
				isNotSatisfied(traces, errMsg);
			} else {
				LOGGER.info("customer is qualified for optional promotions but is not requesting any");				
			}
		} else {
			if (!allOptionalMap.keySet().containsAll(reqOptionalIds)) {
				errMsg = "there is at least one optional promotion in the request that the customer is not qualified for";
				isNotSatisfied(traces, errMsg);
			} else {
				final IPromotion firstPromoDO = (IPromotion)allOptionalMap.values().toArray()[0]; 
				final int min = firstPromoDO.getPromotionGroup().getMinQty();
				final int max = firstPromoDO.getPromotionGroup().getMaxQty();
				final String groupName = firstPromoDO.getPromotionGroup().getCode();
				
				if (reqOptionalIds.size() < min) {
					errMsg = "optional group "+ groupName + " ids (" + Arrays.toString(reqOptionalIds.toArray())  + ") size is less than MIN " + min + " configured in the group";
					isNotSatisfied(traces, errMsg);
				}
				if (reqOptionalIds.size() > max) {
					errMsg = "optional group "+ groupName + " ids (" + Arrays.toString(reqOptionalIds.toArray())  + ") size is more than MAX " + max + " configured in the group";
					isNotSatisfied(traces, errMsg);
				}
			}
		}
	}
	
	private List<String> getPromoListFromRequest(final List<CartItemVO> ciList) {
		final List<String> result = new ArrayList<String>();
		
		for (final CartItemVO elem : ciList) {
			if (elem.getRelatedPromotionList() != null) {
				for (final RelatedImmediatePromotionVO ripElem : elem.getRelatedPromotionList()) {
					result.add(ripElem.getId());
				}
			}
			
			// get the promotions id from the sweetener level
			if (elem.isSalesOrderItem() && elem.getProducts() != null) {
				final List<ProductTypeBaseVO> prodVOs = new ArrayList<ProductTypeBaseVO>();
				
				prodVOs.add((elem.getProducts().getHomePhoneProduct() != null) ? elem.getProducts().getHomePhoneProduct() : null);
				prodVOs.add((elem.getProducts().getInternetProduct() != null) ? elem.getProducts().getInternetProduct() : null);
				prodVOs.add((elem.getProducts().getTelevisionProduct() != null) ? elem.getProducts().getTelevisionProduct() : null);
				
				result.addAll(getPromosFromProducts(prodVOs));
			}
		}
		
		return result;
	}

	private List<String> getPromosFromProducts(final List<ProductTypeBaseVO> prodVOs) {
		final List<String> result = new ArrayList<String>();
		
		for (final ProductTypeBaseVO prodElem : prodVOs) {
			if (prodElem != null && prodElem.getSweeteners() != null) {
				for (final FFHSweetenerTypeVO sweetElem : prodElem.getSweeteners()) {
					result.addAll(getPromosId(sweetElem.getRelatedPromotionList()));
				}
			}
		}
		
		return result;
	}

	private List<String> getPromosId(final List<RelatedImmediatePromotionVO> param) {
		final List<String> result = new ArrayList<String>();
		
		if (param != null) {
			for (final RelatedImmediatePromotionVO ripElem : param) {
				result.add(ripElem.getId());
			}
		}
		
		return result;
	}
	
	private boolean requestPromoIdsInAllQualifiedFound(final List<String> allRequestedPromoIds, final List<IPromotion> allQualifiedPromoList) {
		boolean result = true;
		
		final Set<String> allQualifiedIds = new HashSet<String>();
		
		for (final IPromotion elem : allQualifiedPromoList) {
			allQualifiedIds.add(elem.getPromotionId());
		}
		
		LOGGER.info("ALL qualified promos: " + allQualifiedIds);
		
		if (!allQualifiedIds.containsAll(allRequestedPromoIds)) {
			result = false;
			final Set<String> allReqSet = new HashSet<String>(allRequestedPromoIds);
			allReqSet.removeAll(allQualifiedIds);
			LOGGER.error("ALL requested promos that are not qualified: " + allReqSet);
		}
		
		return result;
	}

}
