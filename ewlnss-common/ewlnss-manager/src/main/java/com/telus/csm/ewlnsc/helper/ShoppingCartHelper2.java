/**
 * 
 */
package com.telus.csm.ewlnsc.helper;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.adapter.scis.domain.AccountProfileRestVO;
import com.telus.csm.ewlnsc.delegate.AssignedAndPendingProductDelegate;
import com.telus.csm.ewlnsc.delegate.CalculateDepositDelegate;
import com.telus.csm.ewlnsc.delegate.GetAvailableProductItemDelegate;
import com.telus.csm.ewlnsc.domain.CalculateDepositRequestVO;
import com.telus.csm.ewlnsc.domain.CalculateDepositResponseVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.GroupCategoryVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.MoneyVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ProdductDepositVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.price.AssociatedProductItemPriceVO;
import com.telus.csm.ewlnsc.domain.price.CartItemPriceVO;
import com.telus.csm.ewlnsc.domain.price.CombinedPriceVO;
import com.telus.csm.ewlnsc.domain.price.DepositVO;
import com.telus.csm.ewlnsc.domain.price.DurationVO;
import com.telus.csm.ewlnsc.domain.price.EquipmentAcquisitionTypeVO;
import com.telus.csm.ewlnsc.domain.price.WirelineEquipmentPriceVO;
import com.telus.csm.ewlnsc.domain.price.HomePhoneProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.InternetProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.PriceDurationVO;
import com.telus.csm.ewlnsc.domain.price.PriceResponseVO;
import com.telus.csm.ewlnsc.domain.price.PriceWithTaxVO;
import com.telus.csm.ewlnsc.domain.price.ProductItemGroupCategoryPriceVO;
import com.telus.csm.ewlnsc.domain.price.ProductItemPriceVO;
import com.telus.csm.ewlnsc.domain.price.ProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.TelevisionProductPriceVO;
import com.telus.csm.ewlnsc.domain.product.AssociatedProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.AvailableHomePhoneProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableInternetProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableProductItemResponseVO;
import com.telus.csm.ewlnsc.domain.product.AvailableTelevisionProductItemVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemGroupCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.util.JsonUtil;


/**
 * @author x145592
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class ShoppingCartHelper2 {
//	
//	private Map<Map<Set<Integer>, String>, PriceDurationVO> totalMap;
//	private int maxDuration;
//	private float oneTimeCharge;
//	private float oneTimeDiscount;
//	
//	@Autowired
//	CalculateDepotitDelegate calculateDepotitDelegate;
//	
//	/**
//	 * Add assigned and pending products to the given ShoppingCartContextVO.
//	 * 
//	 * @param param
//	 */
//	public void populateAssignAndPendingProducts(ShoppingCartContextVO shoppingCartContextVO) {
//		GetAssignedAndPendingProductRequestVO getAssignedAndPendingProductRequestVO = buildAssignedAndPendingProductRequestVO(shoppingCartContextVO);
//		GetAssignedAndPendingProductResponseVO getAssignedAndPendingProductResponseVO = AssignedAndPendingProductDelegate.execute(getAssignedAndPendingProductRequestVO);
//		shoppingCartContextVO.setAssignedAndPendingProductResponseVO(getAssignedAndPendingProductResponseVO);
//	}
//	
//	private GetAssignedAndPendingProductRequestVO buildAssignedAndPendingProductRequestVO(
//			ShoppingCartContextVO shoppingCartContextVO) {
//		if (shoppingCartContextVO != null && shoppingCartContextVO.getShoppingCartVO()!= null)  {
//			GetAssignedAndPendingProductRequestVO getAssignedAndPendingProductRequestVO = new GetAssignedAndPendingProductRequestVO();
//			getAssignedAndPendingProductRequestVO.setBillingAccountNumber(shoppingCartContextVO.getAccountNumber());
//			getAssignedAndPendingProductRequestVO.setCustomerId(shoppingCartContextVO.getCustomerId());
//		}
//		return null;
//	}
//
//	/**
//	 * Add price info to the given ShoppingCartContextVO.
//	 * 	
//	 * @param param
//	 */
//	public void populatePrice(ShoppingCartContextVO param, String transactionId) {
//		
//		final PriceResponseVO prVO = new PriceResponseVO();
//		param.setPriceResponseVO(prVO);
//		
//		final GetAvailableProductItemDelegate delegate = new GetAvailableProductItemDelegate();
//		final GetAvailableProductItemDelegateResponse resp = delegate.execute(transactionId, param);
//		
//		if (resp.getErrorResponse() == null && resp.getResponse() != null) {
//			final AvailableProductItemResponseVO filterredItems = filterApiByCartContent(param.getShoppingCartVO(), resp.getResponse());
//			
//			// 1. populate cart item price list
//			totalMap = new HashMap<Map<Set<Integer>,String>, PriceDurationVO>();
//			final List<CartItemPriceVO> cartItemPriceList = getCartItemPriceList(filterredItems);
//			prVO.setCartItemPrice(cartItemPriceList);
//	
//			// 2. populate total price
//			final CombinedPriceVO totalPrice = calculateTotal();
//			prVO.setTotalPrice(totalPrice);
//			
//			// 3. populate deposit list
//			final List<DepositVO> depositList = getDepositList(param);
//	        prVO.setDeposit(depositList);
//		}
//	}
//
//	private List<CartItemPriceVO> getCartItemPriceList(final AvailableProductItemResponseVO apiResp) {
//		final List<CartItemPriceVO> result = new ArrayList<CartItemPriceVO>();
//
//		final List<AvailableInternetProductItemVO> apiRespHsicList = apiResp.getInternetProductItems();
//		final List<AvailableTelevisionProductItemVO> apiRespTtvList = apiResp.getTelevisionProductItems();
//		final List<AvailableHomePhoneProductItemVO> apiRespSingList = apiResp.getHomePhoneProductItems();
//		
//		if (apiRespHsicList != null) {
//			final List<ProductPriceVO> internetPriceList = getInternetPriceList(apiRespHsicList);
//			final CartItemPriceVO ciInternetPriceVO = new CartItemPriceVO();
//			ciInternetPriceVO.setProductPriceList(internetPriceList);
//			result.add(ciInternetPriceVO);
//		}
//		
//		if (apiRespTtvList != null) {
//			final List<ProductPriceVO> ttvPriceList = getTtvPriceList(apiRespTtvList);
//			final CartItemPriceVO ciTtvPriceVO = new CartItemPriceVO();
//			ciTtvPriceVO.setProductPriceList(ttvPriceList);
//			result.add(ciTtvPriceVO);
//		}
//		
//		if (apiRespSingList != null) {
//			final List<ProductPriceVO> singPriceList = getSingPriceList(apiRespSingList);
//			final CartItemPriceVO ciSingPriceVO = new CartItemPriceVO();
//			ciSingPriceVO.setProductPriceList(singPriceList);
//			result.add(ciSingPriceVO);
//		}
//		
//		return result;
//	}
//	
//	private List<ProductPriceVO> getSingPriceList(List<AvailableHomePhoneProductItemVO> apiRespSingList) {
//		final List<ProductPriceVO> result = new ArrayList<ProductPriceVO>();
//
//		for (final AvailableHomePhoneProductItemVO singElem : apiRespSingList) {
//			ProductPriceVO prodPriceVO = new ProductPriceVO();
//			
//			HomePhoneProductPriceVO singPriceVO = new HomePhoneProductPriceVO();
//			prodPriceVO.setHomePhoneProductPrice(singPriceVO);
//			
//			Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap = new HashMap<Map<Set<Integer>,String>, PriceDurationVO>();
//			
//			List<ProductItemGroupCategoryPriceVO> piGrpPriceList = getPiGrpPriceList(singElem.getProductItemGroupCategories(), prodMap);
//
//			singPriceVO.setProductItemGroupCategoryPrice(piGrpPriceList);
//			singPriceVO.setEstimatedPriceInd(true);
//			
//			CombinedPriceVO singCombinedPriceVO = calculateTotal();
//			singPriceVO.setPriceSummary(singCombinedPriceVO);
//			
//			result.add(prodPriceVO);
//		}
//		
//		return result;
//	}
//
//	private List<ProductPriceVO> getTtvPriceList(final List<AvailableTelevisionProductItemVO> apiRespTtvList) {
//		final List<ProductPriceVO> result = new ArrayList<ProductPriceVO>();
//
//		for (final AvailableTelevisionProductItemVO ttvElem : apiRespTtvList) {
//			ProductPriceVO prodPriceVO = new ProductPriceVO();
//			
//			TelevisionProductPriceVO ttvPriceVO = new TelevisionProductPriceVO();
//			prodPriceVO.setTelevisionProductPrice(ttvPriceVO);
//			
//			Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap = new HashMap<Map<Set<Integer>,String>, PriceDurationVO>();
//			
//			List<ProductItemGroupCategoryPriceVO> piGrpPriceList = getPiGrpPriceList(ttvElem.getProductItemGroupCategories(), prodMap);
//			List<FFHEquipmentPriceVO> equipmentPriceList = getEquipmentPriceList(ttvElem.getEquipments(), prodMap);
//
//			ttvPriceVO.setProductItemGroupCategoryPrice(piGrpPriceList);
//			ttvPriceVO.setEquipmentPrice(equipmentPriceList);
//			ttvPriceVO.setEstimatedPriceInd(true);
//			
//			CombinedPriceVO hsicCombinedPriceVO = calculateTotal();
//			ttvPriceVO.setPriceSummary(hsicCombinedPriceVO);
//			
//			result.add(prodPriceVO);
//		}
//		
//		return result;
//	}
//
//	private List<ProductPriceVO> getInternetPriceList(final List<AvailableInternetProductItemVO> apiRespHsicList) {
//		final List<ProductPriceVO> result = new ArrayList<ProductPriceVO>();
//
//		for (final AvailableInternetProductItemVO hsicElem : apiRespHsicList) {
//			ProductPriceVO prodPriceVO = new ProductPriceVO();
//			
//			InternetProductPriceVO hsicPriceVO = new InternetProductPriceVO();
//			prodPriceVO.setInternetProductPrice(hsicPriceVO);
//			
//			Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap = new HashMap<Map<Set<Integer>,String>, PriceDurationVO>();
//			
//			List<ProductItemGroupCategoryPriceVO> piGrpPriceList = getPiGrpPriceList(hsicElem.getProductItemGroupCategories(), prodMap);
//			List<FFHEquipmentPriceVO> equipmentPriceList = getEquipmentPriceList(hsicElem.getEquipments(), prodMap);
//
//			hsicPriceVO.setProductItemGroupCategoryPrice(piGrpPriceList);
//			hsicPriceVO.setEquipmentPrice(equipmentPriceList);
//			hsicPriceVO.setEstimatedPriceInd(true);
//			
//			CombinedPriceVO hsicCombinedPriceVO = calculateProdPrice(prodMap);
//			hsicPriceVO.setPriceSummary(hsicCombinedPriceVO);
//			
//			result.add(prodPriceVO);
//		}
//		
//		return result;
//	}
//
//	/**
//	 * @param pigCategList
//	 */
//	private List<ProductItemGroupCategoryPriceVO> getPiGrpPriceList(final List<ProductItemGroupCategoryVO> pigCategList, 
//																	final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap) {
//		final List<ProductItemGroupCategoryPriceVO> result = new ArrayList<ProductItemGroupCategoryPriceVO>();
//		
//		for (final ProductItemGroupCategoryVO piGrpElem : pigCategList) {
//			ProductItemGroupCategoryPriceVO piGrpVO = new ProductItemGroupCategoryPriceVO();
//			
//			piGrpVO.setGroupCategory(getGrpVO(piGrpElem.getGroupCategory()));
//			Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap = new HashMap<Map<Set<Integer>,String>, PriceDurationVO>();
//			piGrpVO.setProductItemPriceList(getPiPriceList(piGrpElem.getProductItems(), prodMap, prodGrpMap));
//			
//			CombinedPriceVO priceSummary = calculateProdPrice(prodGrpMap); 
//			piGrpVO.setPriceSummary(priceSummary);
//			
//			result.add(piGrpVO);
//		}
//		
//		return result;
//	}
//
//	/**
//	 * @param piGrpElem
//	 */
//	private List<ProductItemPriceVO> getPiPriceList(final List<ProductItemVO> piList, 
//													final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//													final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		final List<ProductItemPriceVO> result = new ArrayList<ProductItemPriceVO>();
//		
//		if (piList != null) {
//			for (final ProductItemVO piElem : piList) {
//				ProductItemPriceVO piPriceVO = getPiPriceVO(piElem, prodMap, prodGrpMap);
//				
//				result.add(piPriceVO);
//			}
//		}
//		
//		return result;
//	}
//
//	/**
//	 * @param piElem
//	 */
//	private ProductItemPriceVO getPiPriceVO(final ProductItemVO piElem, 
//											final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//											final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		final ProductItemPriceVO result = new ProductItemPriceVO();
//
//		if (piElem.getProductItemIdentifier() != null) {
//			result.setProductItemIdentifier(getIdentifier(piElem.getProductItemIdentifier()));
//		}
//		if (piElem.getProductCatalogueDescription() != null) {
//			result.setProductItemDescription(getDesc(piElem.getProductCatalogueDescription()));
//		}
//		if (piElem.getAssociatedProductItems() != null) {
//			result.setAssociatedProductItemPrice(getAiPriceList(piElem.getAssociatedProductItems(), prodMap, prodGrpMap));
//		}
//		
//		PriceVO charge = piElem.getProductItemPriceCharge();
//		PriceDiscountVO discount = piElem.getProductItemPriceDiscount();
//		
//		result.setPriceSummary(getPriceSummary(charge, discount, prodMap, prodGrpMap)); 
//		
//		return result;
//	}
//	
//	/**
//	 * @param piElem
//	 * @return
//	 */
//	private CombinedPriceVO getPriceSummary(final PriceVO charge, final PriceDiscountVO discount, 
//											final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//											final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		final CombinedPriceVO result = new CombinedPriceVO();
//		
//		final List<PriceDurationVO> billPrice = new ArrayList<PriceDurationVO>();
//		
//		if (charge != null) {
//			final PriceDurationVO pdChargeVO = getCharge(charge, prodMap, prodGrpMap);
//			billPrice.add(pdChargeVO);
//		}
//		if (discount != null) {
//			final PriceDurationVO pdDiscountVO = getDiscount(discount, prodMap, prodGrpMap);
//			billPrice.add(pdDiscountVO);
//		}
//		
//		result.setBillPrice(billPrice);
//		
//		return result;
//	}
//
//	/**
//	 * @param piDescList
//	 * @return
//	 */
//	private List<MultilingualTextVO> getDesc(final List<MultilingualTextVO> piDescList) {
//		final List<MultilingualTextVO> result = new ArrayList<MultilingualTextVO>();
//		
//		for (final MultilingualTextVO elem : piDescList) {
//			MultilingualTextVO piDescVO = new MultilingualTextVO();
//			piDescVO.setLocaleTxt(elem.getLocaleTxt());
//			piDescVO.setValueTxt(elem.getValueTxt());
//			result.add(piDescVO);
//		}
//		
//		return result;
//	}
//
//	/**
//	 * @param piElem
//	 * @return
//	 */
//	private ProductItemIdentifierVO getIdentifier(final ProductItemIdentifierVO pii) {
//		final ProductItemIdentifierVO result = new ProductItemIdentifierVO();
//		
//		result.setProductCatalogueId(pii.getProductCatalogueId());
//		result.setParentProductCatalogueId(pii.getParentProductCatalogueId());
//		
//		return result;
//	}
//
//	private List<AssociatedProductItemPriceVO> getAiPriceList(final List<AssociatedProductItemVO> aiList, 
//																final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//																final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		final List<AssociatedProductItemPriceVO> result = new ArrayList<AssociatedProductItemPriceVO>();
//		
//		for (final AssociatedProductItemVO aiElem : aiList) {
//			AssociatedProductItemPriceVO aiPriceVO = new AssociatedProductItemPriceVO();
//			aiPriceVO.setProductItemIdentifier(getIdentifier(aiElem.getProductItemIdentifier()));
//			aiPriceVO.setProductItemDescription(getDesc(aiElem.getProductCatalogueDescription()));
//			
//			PriceVO aiCharge = aiElem.getProductItemPriceCharge();
//			PriceDiscountVO aiDiscount = aiElem.getProductItemPriceDiscount();
//			
//			aiPriceVO.setPriceSummary(getPriceSummary(aiCharge, aiDiscount, prodMap, prodGrpMap));
//			
//			result.add(aiPriceVO);
//		}
//		
//		return result;
//	}
//	
//	/**
//	 * @param piElem
//	 * @param pdVO
//	 */
//	private PriceDurationVO getCharge(final PriceVO charge, 
//										final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//										final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		final PriceDurationVO result = new PriceDurationVO();
//
//		final PriceVO pVO = new PriceVO();
//		final MoneyVO amtVO = new MoneyVO();
//		amtVO.setValue(charge.getBasePriceAmount().getValue());
//		pVO.setBasePriceAmount(amtVO);
//		pVO.setPricingType(charge.getPricingType());
//		result.setPriceCharge(pVO);
//		
//		if ("ONE_TIME".equalsIgnoreCase(charge.getPricingType())) {
//			oneTimeCharge += charge.getBasePriceAmount().getValue();
//		} else {
//			final DurationVO dVO = new DurationVO();
//			dVO.setStartMonthCnt(1);
//			dVO.setEndMonthCnt(charge.getRecurrenceCount());
//			int duration;
//			if (charge.getRecurrenceCount() == null) {
//				maxDuration = 999;
//				duration = maxDuration;
//			} else if (maxDuration < charge.getRecurrenceCount()) {
//				maxDuration = charge.getRecurrenceCount();
//				duration = maxDuration;
//			} else {
//				if (charge.getRecurrenceCount() == 0) { // means is one time
//					duration = 1;
//				} else {
//					duration = charge.getRecurrenceCount();
//				}
//			}
//			
//			result.setDuration(dVO);
//			
//			final Map<Set<Integer>, String> map = new HashMap<Set<Integer>, String>();
//			final Set<Integer> set = new HashSet<Integer>();
//			for (int i = 1 ; i <= duration; i++) {
//				set.add(i);
//			}
//			map.put(set, "C");
//			
//			addChargeToMaps(result, amtVO, map, prodMap, prodGrpMap);
//		}
//		
//		return result;
//	}
//
//	/**
//	 * @param result
//	 * @param amtVO
//	 * @param map
//	 */
//	private void addChargeToMaps(final PriceDurationVO result, final MoneyVO amtVO, final Map<Set<Integer>, String> map, 
//							final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//							final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		if (totalMap.get(map) == null) {
//			totalMap.put(map, result);
//		} else {
//			final Float prevAmt = totalMap.get(map).getPriceCharge().getBasePriceAmount().getValue();
//			final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
//			final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
//			pdChargeNewVO.getPriceCharge().getBasePriceAmount().setValue(prevAmt + amtVO.getValue());
//			totalMap.put(map, pdChargeNewVO);
//		}
//		
//		if (prodMap.get(map) == null) {
//			prodMap.put(map, result);
//		} else {
//			final Float prevAmt = prodMap.get(map).getPriceCharge().getBasePriceAmount().getValue();
//			final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
//			final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
//			pdChargeNewVO.getPriceCharge().getBasePriceAmount().setValue(prevAmt + amtVO.getValue());
//			prodMap.put(map, pdChargeNewVO);
//		}
//		
//		if (prodGrpMap != null) {
//			if (prodGrpMap.get(map) == null) {
//				prodGrpMap.put(map, result);
//			} else {
//				final Float prevAmt = prodGrpMap.get(map).getPriceCharge().getBasePriceAmount().getValue();
//				final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
//				final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
//				pdChargeNewVO.getPriceCharge().getBasePriceAmount().setValue(prevAmt + amtVO.getValue());
//				prodGrpMap.put(map, pdChargeNewVO);
//			}			
//		}
//	}
//
//	private PriceDurationVO getDiscount(final PriceDiscountVO disc, 
//										final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//										final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		final PriceDurationVO result = new PriceDurationVO();
//		final PriceDiscountVO pdVO = new PriceDiscountVO();
//		final MoneyVO amtVO = new MoneyVO();
//		amtVO.setValue(disc.getBasePriceAmount().getValue());
//		pdVO.setBasePriceAmount(amtVO);
//		pdVO.setPricingType(disc.getPricingType());
//		pdVO.setPriceAlterationType(disc.getPriceAlterationType());
//		result.setPriceDiscount(pdVO);
//		
//		if ("ONE_TIME".equalsIgnoreCase(disc.getPricingType())) {
//			oneTimeDiscount += disc.getBasePriceAmount().getValue();
//		} else {
//			final DurationVO dVO = new DurationVO();
//			dVO.setStartMonthCnt(1);
//			dVO.setEndMonthCnt(disc.getRecurrenceCount());
//			int duration;
//			if (disc.getRecurrenceCount() == null) {
//				maxDuration = 999;
//				duration = maxDuration;
//			} else if (maxDuration < disc.getRecurrenceCount()) {
//				maxDuration = disc.getRecurrenceCount();
//				duration = maxDuration;
//			} else {
//				if (disc.getRecurrenceCount() == 0) { // means is one time
//					duration = 1;
//				} else {
//					duration = disc.getRecurrenceCount();
//				}
//			}
//
//			result.setDuration(dVO);
//			
//			final Map<Set<Integer>, String> map = new HashMap<Set<Integer>, String>();
//			final Set<Integer> set = new HashSet<Integer>();
//			for (int i = 1 ; i <= duration; i++) {
//				set.add(i);
//			}
//			map.put(set, "D");
//			
//			addDiscountToMaps(result, amtVO, map, prodMap, prodGrpMap);
//		}
//		
//		return result;
//	}
//	
//	/**
//	 * @param result
//	 * @param amtVO
//	 * @param map
//	 */
//	private void addDiscountToMaps(final PriceDurationVO result, final MoneyVO amtVO, final Map<Set<Integer>, String> map, 
//							final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap, 
//							final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap) {
//		if (totalMap.get(map) == null) {
//			totalMap.put(map, result);
//		} else {
//			final Float prevAmt = totalMap.get(map).getPriceDiscount().getBasePriceAmount().getValue();
//			final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
//			final PriceDurationVO pdNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
//			pdNewVO.getPriceDiscount().getBasePriceAmount().setValue(prevAmt + amtVO.getValue());
//			totalMap.put(map, pdNewVO);
//		}
//		
//		if (prodMap.get(map) == null) {
//			prodMap.put(map, result);
//		} else {
//			final Float prevAmt = prodMap.get(map).getPriceDiscount().getBasePriceAmount().getValue();
//			final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
//			final PriceDurationVO pdNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
//			pdNewVO.getPriceDiscount().getBasePriceAmount().setValue(prevAmt + amtVO.getValue());
//			prodMap.put(map, pdNewVO);
//		}
//		
//		if (prodGrpMap != null) {
//			if (prodGrpMap.get(map) == null) {
//				prodGrpMap.put(map, result);
//			} else {
//				final Float prevAmt = prodGrpMap.get(map).getPriceDiscount().getBasePriceAmount().getValue();
//				final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
//				final PriceDurationVO pdNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
//				pdNewVO.getPriceDiscount().getBasePriceAmount().setValue(prevAmt + amtVO.getValue());
//				prodGrpMap.put(map, pdNewVO);
//			}			
//		}
//	}
//	
//	/**
//	 * @param piGrpElem
//	 * @param piGrpVO
//	 */
//	private GroupCategoryVO getGrpVO(final GroupCategoryVO grp) {
//		GroupCategoryVO result = new GroupCategoryVO();
//		
//		result.setGroupType(grp.getGroupType());
//		result.setGroupSubType(grp.getGroupSubType());
//		
//		return result;
//	}
//	
//	private CombinedPriceVO getTotalPrice(final List<CartItemPriceVO> cartItemPriceList) {
//		final CombinedPriceVO result = new CombinedPriceVO();
//		
//		for (final CartItemPriceVO ciPriceElem : cartItemPriceList) {
//			List<ProductPriceVO> ppList = ciPriceElem.getProductPriceList();
//			for (final ProductPriceVO ppElem : ppList) {
//				List<ProductItemGroupCategoryPriceVO> piGrpPriceList = ppElem.getInternetProductPrice().getProductItemGroupCategoryPrice();
//				for (final ProductItemGroupCategoryPriceVO piGrpElem : piGrpPriceList) {
//					List<ProductItemPriceVO> piPriceList = piGrpElem.getProductItemPriceList();
//					for (final ProductItemPriceVO piElem : piPriceList) {
//						List<PriceDurationVO> pdList = piElem.getPriceSummary().getBillPrice();
//						for (PriceDurationVO pdElem : pdList) {
//							Float piCharge = pdElem.getPriceCharge().getBasePriceAmount().getValue();
//						}
//						for (final AssociatedProductItemPriceVO aiElem : piElem.getAssociatedProductItemPrice()) {
//							List<PriceDurationVO> aiPdList = aiElem.getPriceSummary().getBillPrice();
//							for (PriceDurationVO aiPdElem : aiPdList) {
//								Float aiCharge = aiPdElem.getPriceCharge().getBasePriceAmount().getValue();
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		return result;
//	}
//	
//	private CombinedPriceVO calculateTotal() {
//		final CombinedPriceVO result = new CombinedPriceVO();
//		final List<PriceDurationVO> billPrice = new ArrayList<PriceDurationVO>();
//		result.setBillPrice(billPrice);
//		
//		if (oneTimeCharge > 0 || oneTimeDiscount > 0) {
//			PriceDurationVO pdVO = new PriceDurationVO();
//			billPrice.add(pdVO);
//			// 1. set the one-time priceCharge
//			final PriceVO pVO = new PriceVO();
//			final MoneyVO amtVO = new MoneyVO();
//			amtVO.setValue(oneTimeCharge);
//			pVO.setBasePriceAmount(amtVO);
//			pVO.setPricingType("ONE-TIME");
//			pdVO.setPriceCharge(pVO);
//
//			// 2. set the one-time discount
//			final PriceDiscountVO pDiscVO = new PriceDiscountVO();
//			final MoneyVO mVO = new MoneyVO();
//			mVO.setValue(oneTimeDiscount);
//			pDiscVO.setBasePriceAmount(mVO);
//			pDiscVO.setPricingType("ONE-TIME");
//			pDiscVO.setPriceAlterationType("DISCOUNT");
//			pdVO.setPriceDiscount(pDiscVO);
//
//			// 3. set the one-time totalPrice
//			final PriceWithTaxVO totalPrice = new PriceWithTaxVO();
//			totalPrice.setPricingType("ONE-TIME");
//			final MoneyVO tVO = new MoneyVO();
//			tVO.setValue(oneTimeCharge - oneTimeDiscount);
//			totalPrice.setBasePriceAmount(tVO);
//			pdVO.setTotalPrice(totalPrice);
//		}
//		
//		Float prev = 0f, prevCr = 0f, prevDb = 0f;
//		int prevIndex = 1;
//		for (int j = 1; j <= maxDuration + 1; j++) {
//			Float amt = 0f, crg = 0f, deb = 0f;
//		    for (Map<Set<Integer>, String> keyMap : totalMap.keySet()) {
//		    	for (Set<Integer> set : keyMap.keySet())
//			    	if (set.contains(j)) {
//				    	if ("C".equals(keyMap.get(set))) {
//				    		amt += totalMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValue();
//				    		crg += totalMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValue();
//				    	} else {
//				    		amt -= totalMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValue();
//				    		deb += totalMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValue();
//				    	}
//			    	}
//		    }
//		    if (j == 1) { 
//		    	prev = amt; 
//		    	prevCr = crg; 
//		    	prevDb = deb; 
//		    }
//		    int retval = Float.compare(prev, amt);
//			if (retval != 0) {
//				PriceDurationVO pdVO = getPD(prev, prevCr, prevDb, prevIndex, j - 1);
//				billPrice.add(pdVO);
//				prevIndex = j;
//				prev = amt;
//				prevCr = crg;
//				prevDb = deb;
//			}
//		}
//		return result;
//	}
//	
//	private CombinedPriceVO calculateProdPrice(final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap) {
//		final CombinedPriceVO result = new CombinedPriceVO();
//		final List<PriceDurationVO> billPrice = new ArrayList<PriceDurationVO>();
//		result.setBillPrice(billPrice);
//
//		Float prev = 0f, prevCr = 0f, prevDb = 0f;
//		int prevIndex = 1;
//		int prodMaxDuration = getMaxDuration(prodMap); 
//		for (int j = 1; j <= prodMaxDuration + 1; j++) {
//			Float amt = 0f, crg = 0f, deb = 0f;
//		    for (Map<Set<Integer>, String> keyMap : prodMap.keySet()) {
//		    	for (Set<Integer> set : keyMap.keySet())
//			    	if (set.contains(j)) {
//				    	if ("C".equals(keyMap.get(set))) {
//				    		amt += prodMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValue();
//				    		crg += prodMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValue();
//				    	} else {
//				    		amt -= prodMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValue();
//				    		deb += prodMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValue();
//				    	}
//			    	}
//		    }
//		    if (j == 1) { 
//		    	prev = amt; 
//		    	prevCr = crg; 
//		    	prevDb = deb; 
//		    }
//		    int retval = Float.compare(prev, amt);
//			if (retval != 0) {
//				PriceDurationVO pdVO = getPD(prev, prevCr, prevDb, prevIndex, j - 1);
//				billPrice.add(pdVO);
//				prevIndex = j;
//				prev = amt;
//				prevCr = crg;
//				prevDb = deb;
//			}
//		}
//		return result;
//	}
//	
//	private int getMaxDuration(final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap) {
//		int result = -1;
//		
//		for (final Map<Set<Integer>, String> keyMap : prodMap.keySet()) {
//			Integer endMonthCnt = prodMap.get(keyMap).getDuration().getEndMonthCnt();
//			
//			if (endMonthCnt == null) {
//				result = 999;
//				break;
//			} else if (result < endMonthCnt) {
//				result = endMonthCnt;
//			}
//		}
//		
//		return result;
//	}
//	
//	private PriceDurationVO getPD(final Float tot, final Float charge, final Float discount, final int start, final int end) {
//		final PriceDurationVO result = new PriceDurationVO();
//		
//		// 1. set the priceCharge
//		final PriceVO pVO = new PriceVO();
//		final MoneyVO amtVO = new MoneyVO();
//		amtVO.setValue(charge);
//		pVO.setBasePriceAmount(amtVO);
//		pVO.setPricingType("RECURRING");
//		result.setPriceCharge(pVO);
//
//		// 2. set the priceCredit
//		final PriceDiscountVO pdVO = new PriceDiscountVO();
//		final MoneyVO mVO = new MoneyVO();
//		mVO.setValue(discount);
//		pdVO.setBasePriceAmount(mVO);
//		pdVO.setPricingType("RECURRING");
//		pdVO.setPriceAlterationType("DISCOUNT");
//		result.setPriceDiscount(pdVO);
//
//		// 3. set the totalPrice
//		final PriceWithTaxVO totalPrice = new PriceWithTaxVO();
//		totalPrice.setPricingType("RECURRING");
//		final MoneyVO tVO = new MoneyVO();
//		tVO.setValue(tot);
//		totalPrice.setBasePriceAmount(tVO);
//		result.setTotalPrice(totalPrice);
//		
//		// 4. set the duration
//		DurationVO dVO = new DurationVO();
//		dVO.setStartMonthCnt(start);
//		if (end == 999) {
//			dVO.setEndMonthCnt(null);
//		} else {
//			dVO.setEndMonthCnt(end);
//		}
//		result.setDuration(dVO);
//		
//		return result;
//	}
//	
//	private List<DepositVO> getDepositList(final ShoppingCartContextVO param) {
//		final List<DepositVO> result = new ArrayList<DepositVO>();
//		CalculateDepositRequestVO  calculateDepositRequestVO = new CalculateDepositRequestVO();
//		calculateDepositRequestVO.setOrderedProductsList(param.getOrderedProducts());
//		if (!StringUtils.isEmpty(param.getCustomerId())) {
//			calculateDepositRequestVO.setCustomerId(Long.parseLong(param.getCustomerId()));
//		}
//		calculateDepositRequestVO.setOrderId(param.getShoppingCartVO().getOpShoppingCartId());
//		calculateDepositRequestVO.setSourceSystemId("VESTA");
//		calculateDepositRequestVO.setCalulationTypeCode("ESTIMATE");
//		calculateDepositRequestVO.setApplicationId("TelusOrdering");
//		calculateDepositRequestVO.setUserId("ICS_3");
//		
//		
//		if (param.getAssignedAndPendingProductResponseVO()!=null){
//			List<AccountProfileRestVO> customerProductsSummary = param.getAssignedAndPendingProductResponseVO().getCustomerProductSummaryList();
//			calculateDepositRequestVO.setCustomerProducts(customerProductsSummary);
//		}
//		//calculateDepotitDelegate = new CalculateDepotitDelegate(); 
//		CalculateDepositResponseVO calculateDepositResponseVO = calculateDepotitDelegate.execute(calculateDepositRequestVO);
//		return transform(calculateDepositResponseVO);
//	}
//		
//	private List<DepositVO> transform(CalculateDepositResponseVO calculateDepositResponseVO) {
//		
//		List<DepositVO> depositList = new ArrayList<DepositVO>();
//				
//		if (calculateDepositResponseVO != null && calculateDepositResponseVO.getProductDepositList()!=null ) {
//			List<ProdductDepositVO> productDepositList = calculateDepositResponseVO.getProductDepositList();
//			for (ProdductDepositVO productDeposit : productDepositList) {
//				DepositVO depositVO  = new DepositVO();
//				depositVO.setDepositAmt(productDeposit.getAssessedDepositAmt());
//				//depositVO.setDepositType(productDeposit.get);
//				depositVO.setEstimatedDepositInd(true);
//				depositList.add(depositVO);
//			}
//		}
//		return depositList;
//	}  
//	
//	private List<FFHEquipmentPriceVO> getEquipmentPriceList(final List<AvailableFFHEquipmentTypeVO> inList, 
//															final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap) {
//		final List<FFHEquipmentPriceVO> result = new ArrayList<FFHEquipmentPriceVO>();
//		if (inList != null) {
//			for (final AvailableFFHEquipmentTypeVO equipElem : inList) {
//				List<FFHEquipmentItemVO> buyEquipList = equipElem.getBuyEquipmentList();
//				List<FFHEquipmentItemVO> rentEquipList = equipElem.getRentalEquipmentList();
//				List<FFHEquipmentItemVO> ownEquipList = equipElem.getClientOwnedEquipmentList();
//				
//				if (buyEquipList != null) {
//					populateEquipPriceList(result, buyEquipList, 0, prodMap);
//				}
//				if (rentEquipList != null) {
//					populateEquipPriceList(result, rentEquipList, 1, prodMap);
//				}
//				if (ownEquipList != null) {
//					populateEquipPriceList(result, ownEquipList, 2, prodMap);
//				}
//			}
//		}
//		
//		return result;
//	}
//
//	/**
//	 * @param result
//	 * @param buyEquipList
//	 * @param buyRentOwn will have one of these 3 values:
//	 *  0 - buy; 
//	 *  1 - rent; 
//	 *  2 - own
//	 */
//	private void populateEquipPriceList(final List<FFHEquipmentPriceVO> result, List<FFHEquipmentItemVO> buyEquipList, final int buyRentOwn,
//										final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap) {
//		for (final FFHEquipmentItemVO equipTypeElem : buyEquipList) {
//			FFHEquipmentPriceVO equipPriceVO = new FFHEquipmentPriceVO();
//			EquipmentAcquisitionTypeVO acquisitionTypeVO = new EquipmentAcquisitionTypeVO();
//			if (buyRentOwn == 0) {
//				acquisitionTypeVO.setBuyIndicator(true);
//			} else if (buyRentOwn == 1) {
//				acquisitionTypeVO.setRentalEquipmentIndicator(true);
//			} else if (buyRentOwn == 2) {
//				acquisitionTypeVO.setCustomerOwnedIndicator(true);
//			} 
//			equipPriceVO.setAcquisitionType(acquisitionTypeVO);
//			equipPriceVO.setMaterialItemCode(equipTypeElem.getMaterialItemCode());
//			
//			ProductItemPriceVO equipmentPrice = getEquipPiPriceVO(equipTypeElem, buyRentOwn, prodMap);
//			equipPriceVO.setEquipmentPrice(equipmentPrice);
//			
//			result.add(equipPriceVO);
//		}
//	}
//
//	private ProductItemPriceVO getEquipPiPriceVO(final FFHEquipmentItemVO equipItem, final int buyOrRent,
//												final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap) {
//		final ProductItemPriceVO result = new ProductItemPriceVO();
//
//		if (equipItem.getProductItemIdentifier() != null) {
//			result.setProductItemIdentifier(getIdentifier(equipItem.getProductItemIdentifier()));
//		}
//		if (equipItem.getEquipmentDescription() != null) {
//			result.setProductItemDescription(getDesc(equipItem.getEquipmentDescription()));
//		}
//		if (equipItem.getAssociatedProductItems() != null) {
//			result.setAssociatedProductItemPrice(getAiPriceList(equipItem.getAssociatedProductItems(), prodMap, null));
//		}
//		
//		final PriceVO charge = equipItem.getEquipmentPrice();
//		final PriceDiscountVO discount = equipItem.getEquipmentPriceDiscount();
//		if (buyOrRent == 0) {
//			if (charge != null) {
//				charge.setRecurrenceCount(1);
//			}
//			if (discount != null) {
//				discount.setRecurrenceCount(1);
//			}
//		}
//		
//		result.setPriceSummary(getPriceSummary(charge, discount, prodMap, null)); 
//		
//		return result;
//	}
//
//	private AvailableProductItemResponseVO filterApiByCartContent(final ShoppingCartVO shoppingCart, final AvailableProductItemResponseVO param) {
//		
//		AvailableProductItemResponseVO result = new AvailableProductItemResponseVO();
//		
//		//filter InternetProductItems
//		if (param.getInternetProductItems() != null) {
//			for (AvailableInternetProductItemVO paramInternetProductItem : param.getInternetProductItems()) {
//				AvailableInternetProductItemVO resultInternetProductItem = new AvailableInternetProductItemVO();
//				result.addInternetProductItemsItem(resultInternetProductItem);
//				
//				//filter Internet ProductItemGroupCategories
//				for (ProductItemGroupCategoryVO paramProductItemGroupCategory : paramInternetProductItem.getProductItemGroupCategories()) {
//					ProductItemGroupCategoryVO newProductItemGroupCategory = new ProductItemGroupCategoryVO();
//					newProductItemGroupCategory.setGroupCategory(paramProductItemGroupCategory.getGroupCategory());
//					resultInternetProductItem.addProductItemGroupCategoriesItem(newProductItemGroupCategory);
//					for (ProductItemVO paramProductItem : paramProductItemGroupCategory.getProductItems() ) {
//						if (isInternetProductItemSelected(shoppingCart, paramProductItem)) {
//							newProductItemGroupCategory.addProductItemsItem(paramProductItem);
//						}
//					}	
//				}
//				
//				//filter Internet Equipments
//				for (AvailableFFHEquipmentTypeVO equipmentType : paramInternetProductItem.getEquipments()) {
//					AvailableFFHEquipmentTypeVO newEquipmentsItem = new AvailableFFHEquipmentTypeVO();
//					resultInternetProductItem.addEquipmentsItem(newEquipmentsItem);
//					newEquipmentsItem.setQuantityAllowed(equipmentType.getQuantityAllowed());
//					//Rental Equipments
//					if (equipmentType.getRentalEquipmentList() != null) {
//						for (FFHEquipmentItemVO paramRentalEquipment : equipmentType.getRentalEquipmentList()) {
//							if (isInternetEquipmentSelected(shoppingCart, paramRentalEquipment, true)) {
//								newEquipmentsItem.addRentalEquipmentListItem(paramRentalEquipment);
//							}
//						}
//					}
//					
//					//Rental Equipments
//					if (equipmentType.getBuyEquipmentList() != null) {
//						for (FFHEquipmentItemVO paramBuyEquipment : equipmentType.getBuyEquipmentList()) {
//							if (isInternetEquipmentSelected(shoppingCart, paramBuyEquipment, false)) {
//								newEquipmentsItem.addRentalEquipmentListItem(paramBuyEquipment);
//							}
//						}
//					}
//				}
//			}
//		}
//
//		//filter TelevisionProductItems
//		if (param.getTelevisionProductItems() != null) {
//			for (AvailableTelevisionProductItemVO paramTTVProductItem : param.getTelevisionProductItems()) {
//				AvailableTelevisionProductItemVO resultTTVProductItem = new AvailableTelevisionProductItemVO();
//				result.addTelevisionProductItemsItem(resultTTVProductItem);
//				
//				//filter Internet ProductItemGroupCategories
//				for (ProductItemGroupCategoryVO paramProductItemGroupCategory : paramTTVProductItem.getProductItemGroupCategories()) {
//					ProductItemGroupCategoryVO newProductItemGroupCategory = new ProductItemGroupCategoryVO();
//					newProductItemGroupCategory.setGroupCategory(paramProductItemGroupCategory.getGroupCategory());
//					resultTTVProductItem.addProductItemGroupCategoriesItem(newProductItemGroupCategory);
//					//System.out.println(paramProductItemGroupCategory.getProductItems());
//					for (ProductItemVO paramProductItem : paramProductItemGroupCategory.getProductItems() ) {
//						if (isTTVProductItemSelected(shoppingCart, paramProductItem)) {
//							newProductItemGroupCategory.addProductItemsItem(paramProductItem);
//						}
//					}	
//				}
//				
//				//filter Internet Equipments
//				for (AvailableFFHEquipmentTypeVO equipmentType : paramTTVProductItem.getEquipments()) {
//					AvailableFFHEquipmentTypeVO newEquipmentsItem = new AvailableFFHEquipmentTypeVO();
//					resultTTVProductItem.addEquipmentsItem(newEquipmentsItem);
//					newEquipmentsItem.setQuantityAllowed(equipmentType.getQuantityAllowed());
//					//Rental Equipments
//					if (equipmentType.getRentalEquipmentList() != null) {
//						for (FFHEquipmentItemVO paramRentalEquipment : equipmentType.getRentalEquipmentList()) {
//							if (isTTVEquipmentSelected(shoppingCart, paramRentalEquipment, true)) {
//								newEquipmentsItem.addRentalEquipmentListItem(paramRentalEquipment);
//							}
//						}
//					}
//					
//					//Rental Equipments
//					if (equipmentType.getBuyEquipmentList() != null) {
//						for (FFHEquipmentItemVO paramBuyEquipment : equipmentType.getBuyEquipmentList()) {
//							if (isTTVEquipmentSelected(shoppingCart, paramBuyEquipment, false)) {
//								newEquipmentsItem.addRentalEquipmentListItem(paramBuyEquipment);
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		//filter HomePhoneProductItems
//		if (param.getHomePhoneProductItems() != null) {
//			for (final AvailableHomePhoneProductItemVO paramSingProductElem : param.getHomePhoneProductItems()) {
//				AvailableHomePhoneProductItemVO resultSingProductItem = new AvailableHomePhoneProductItemVO();
//				result.addHomePhoneProductItemsItem(resultSingProductItem);
//				
//				//filter SING ProductItemGroupCategories
//				for (final ProductItemGroupCategoryVO grpElem : paramSingProductElem.getProductItemGroupCategories()) {
//					ProductItemGroupCategoryVO newGrp = new ProductItemGroupCategoryVO();
//					newGrp.setGroupCategory(grpElem.getGroupCategory());
//					resultSingProductItem.addProductItemGroupCategoriesItem(newGrp);
//					for (final ProductItemVO paramPiElem : grpElem.getProductItems() ) {
//						if (isSingProductItemSelected(shoppingCart, paramPiElem)) {
//							newGrp.addProductItemsItem(paramPiElem);
//						}
//					}	
//				}
//			}
//		}		
//
//		return result;
//		
//	}
//
//	private boolean isInternetProductItemSelected(final ShoppingCartVO shoppingCart, final ProductItemVO paramProductItem) {
//		
//		boolean isProductComponent = false;
//		boolean isDiscount = false;
//		boolean isSweetDiscount = false;
//		if (paramProductItem.getMarketOfferClassification() != null) {
//			isProductComponent = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isProductComponentInd());
//			isDiscount = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isDiscountInd());
//			isSweetDiscount = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isSweetenerInd());
//		}
//		
//		String discountCode = paramProductItem.getDiscountCode();
//
//		String productCatalogueId = null;
//		if (paramProductItem.getProductItemIdentifier() != null) {
//			productCatalogueId = paramProductItem.getProductItemIdentifier().getProductCatalogueId();
//		}
//
//		for (CartItemVO cartItem : shoppingCart.getCartItemList()) {
//			if (cartItem.isSalesOrderIem()) {
//			InternetProductTypeVO internetProductType = cartItem.getProducts().getInternetProduct();
//			if (internetProductType != null) { 
//				if (isProductComponent) {
//					for (ProductComponentVO productComponent : internetProductType.getProductComponents()) {
//						if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//							return true;
//						}
//					}
//				}
//				if (isDiscount) {
//					for (FFHDiscountTypeVO discountType : internetProductType.getDiscounts()) {
//						if (StringUtils.equals(discountType.getDiscountCode(), discountCode)) {
//							for (ProductComponentVO productComponent : discountType.getProductCatalogueIdentifiers()) {
//								if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//									return true;
//								}
//							}
//						}
//					}
//				}
//				if(isSweetDiscount) {
//					for (FFHSweetenerTypeVO sweetner : internetProductType.getSweeteners()) {
//						if(sweetner != null && !sweetner.getDiscounts().isEmpty()) {
//							for(FFHDiscountTypeVO discountType: sweetner.getDiscounts()) {
//								if (StringUtils.equals(discountType.getDiscountCode(), discountCode)) {
//									for (ProductComponentVO productComponent : discountType.getProductCatalogueIdentifiers()) {
//										if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//											return true;
//										}
//									}
//								}
//							}
//						}
//						
//						
//					}
//				}
//			}
//			}
//		}
//				
//		return false;
//	}
//
//	private boolean isTTVProductItemSelected(final ShoppingCartVO shoppingCart, final ProductItemVO paramProductItem) {
//		
//		boolean isProductComponent = false;
//		boolean isDiscount = false;
//		boolean isSweetDiscount = false;
//		if (paramProductItem.getMarketOfferClassification() != null) {
//			isProductComponent = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isProductComponentInd());
//			isDiscount = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isDiscountInd());
//			isSweetDiscount = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isSweetenerInd());
//		}
//		
//		String discountCode = paramProductItem.getDiscountCode();
//
//		String productCatalogueId = null;
//		if (paramProductItem.getProductItemIdentifier() != null) {
//			productCatalogueId = paramProductItem.getProductItemIdentifier().getProductCatalogueId();
//		}
//
//		for (CartItemVO cartItem : shoppingCart.getCartItemList()) {
//			if (cartItem.isSalesOrderIem()) {
//			if(cartItem.getProducts() != null) {	
//			    TelevisionProductTypeVO ttvProductType = cartItem.getProducts().getTelevisionProduct();
//			    if (ttvProductType != null) {
//					if (isProductComponent) {
//						if (ttvProductType != null && ttvProductType.getProductComponents() != null) {
//							for (ProductComponentVO productComponent : ttvProductType.getProductComponents()) {
//								if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//									return true;
//								}
//							}
//						}
//					}
//					if (isDiscount) {
//						for (FFHDiscountTypeVO discountType : ttvProductType.getDiscounts()) {
//							if (StringUtils.equals(discountType.getDiscountCode(), discountCode)) {
//								for (ProductComponentVO productComponent : discountType.getProductCatalogueIdentifiers()) {
//									if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//										return true;
//									}
//								}
//							}
//						}
//					}
//					if(isSweetDiscount) {
//						for (FFHSweetenerTypeVO sweetner : ttvProductType.getSweeteners()) {
//							if(sweetner != null && !sweetner.getDiscounts().isEmpty()) {
//								for(FFHDiscountTypeVO discountType: sweetner.getDiscounts()) {
//									if (StringUtils.equals(discountType.getDiscountCode(), discountCode)) {
//										for (ProductComponentVO productComponent : discountType.getProductCatalogueIdentifiers()) {
//											if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//												return true;
//											}
//										}
//									}
//								}
//							}
//							
//							
//						}
//					}
//			    }
//			}	
//			}
//		}
//		return false;
//	}
//	
//	private boolean isSingProductItemSelected(final ShoppingCartVO shoppingCart, final ProductItemVO paramProductItem) {
//		boolean isProductComponent = false;
//		boolean isDiscount = false;
//		boolean isSweetDiscount = false;
//		boolean isCallingFeature = false;
//		if (paramProductItem.getMarketOfferClassification() != null) {
//			isProductComponent = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isProductComponentInd());
//			isDiscount = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isDiscountInd());
//			isSweetDiscount = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isSweetenerInd());
//			isCallingFeature = Boolean.TRUE.equals(paramProductItem.getMarketOfferClassification().isCallingFeatureInd());
//		}
//		
//		final String discountCode = paramProductItem.getDiscountCode();
//
//		String productCatalogueId = null;
//		if (paramProductItem.getProductItemIdentifier() != null) {
//			productCatalogueId = paramProductItem.getProductItemIdentifier().getProductCatalogueId();
//		}
//
//		for (final CartItemVO cartItem : shoppingCart.getCartItemList()) {
//			
//			if (cartItem.isSalesOrderIem() && cartItem.getProducts() != null) {	
//				HomePhoneProductTypeVO singProductType = cartItem.getProducts().getHomePhoneProduct();
//			    if (singProductType != null) {
//					if (isProductComponent) {
//						if (singProductType != null && singProductType.getProductComponents() != null) {
//							for (ProductComponentVO productComponent : singProductType.getProductComponents()) {
//								if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//									return true;
//								}
//							}
//						}
//					}
//					if (isDiscount) {
//						for (FFHDiscountTypeVO discountType : singProductType.getDiscounts()) {
//							if (StringUtils.equals(discountType.getDiscountCode(), discountCode)) {
//								for (ProductComponentVO productComponent : discountType.getProductCatalogueIdentifiers()) {
//									if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//										return true;
//									}
//								}
//							}
//						}
//					}
//					if (isCallingFeature && singProductType.getFeatures() != null) {
//						for (final FFHFeatureTypeVO featureElem : singProductType.getFeatures()) {
//							ProductComponentVO pcVO = featureElem.getProductCatalogueIdentifier();
//							if (StringUtils.equals(pcVO.getProductCatalogueId(), productCatalogueId)) {
//								return true;
//							}
//						}
//					}					
//					if (isSweetDiscount) {
//						for (FFHSweetenerTypeVO sweetnerElem : singProductType.getSweeteners()) {
//							if (sweetnerElem.getDiscounts() != null) {
//								for (FFHDiscountTypeVO discountElem: sweetnerElem.getDiscounts()) {
//									if (StringUtils.equals(discountElem.getDiscountCode(), discountCode)) {
//										for (ProductComponentVO productComponent : discountElem.getProductCatalogueIdentifiers()) {
//											if (StringUtils.equals(productComponent.getProductCatalogueId(), productCatalogueId)) {
//												return true;
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//			    }
//			}	
//		}
//       
//		return false;
//	}
//	
//	private boolean isInternetEquipmentSelected(final ShoppingCartVO shoppingCart, final FFHEquipmentItemVO paramEquipment, final boolean isRental) {
//		
//		String materialItemCode = paramEquipment.getMaterialItemCode();
//		String productCatalogueId = null;
//		if (paramEquipment.getProductItemIdentifier() != null) {
//			productCatalogueId = paramEquipment.getProductItemIdentifier().getProductCatalogueId();
//		}
//
//		for (CartItemVO cartItem : shoppingCart.getCartItemList()) {
//			if (cartItem.isSalesOrderIem() && cartItem.getProducts()!= null) {
//				InternetProductTypeVO internetProductType = cartItem.getProducts().getInternetProduct();
//				if (internetProductType != null && internetProductType.getEquipments() != null) { 
//					for (FFHEquipmentTypeVO equipment : internetProductType.getEquipments()) {
//						if (StringUtils.equals(equipment.getMaterialItemCode(), materialItemCode)) {
//							ProductComponentVO productCatalogueIdentifier = equipment.getProductCatalogueIdentifier();
//							if (productCatalogueIdentifier != null && StringUtils.equals(productCatalogueIdentifier.getProductCatalogueId(), productCatalogueId)) {
//								if (equipment.getAcquisitionType() != null) {
//									if ((isRental && Boolean.TRUE.equals(equipment.getAcquisitionType().isRentalEquipmentIndicator()))
//										|| (!isRental && Boolean.TRUE.equals(equipment.getAcquisitionType().isBuyIndicator()))) {
//										return true;
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return false;
//	}
//	
//	private boolean isTTVEquipmentSelected(final ShoppingCartVO shoppingCart, final FFHEquipmentItemVO paramEquipment, final boolean isRental) {
//		
//		String materialItemCode = paramEquipment.getMaterialItemCode();
//		String productCatalogueId = null;
//		if (paramEquipment.getProductItemIdentifier() != null) {
//			productCatalogueId = paramEquipment.getProductItemIdentifier().getProductCatalogueId();
//		}
//
//		for (CartItemVO cartItem : shoppingCart.getCartItemList()) {
//		  if (cartItem.isSalesOrderIem()) {		
//			TelevisionProductTypeVO ttvProductType = cartItem.getProducts().getTelevisionProduct();
//				if (ttvProductType != null && ttvProductType.getEquipments() != null) {
//					for (FFHEquipmentTypeVO equipment : ttvProductType.getEquipments()) {
//						if (StringUtils.equals(equipment.getMaterialItemCode(), materialItemCode)) {
//							ProductComponentVO productCatalogueIdentifier = equipment.getProductCatalogueIdentifier();
//							if (productCatalogueIdentifier != null && StringUtils.equals(productCatalogueIdentifier.getProductCatalogueId(), productCatalogueId)) {
//								if (equipment.getAcquisitionType() != null) {
//									if ((isRental && Boolean.TRUE.equals(equipment.getAcquisitionType().isRentalEquipmentIndicator()))
//										|| (!isRental && Boolean.TRUE.equals(equipment.getAcquisitionType().isBuyIndicator()))) {
//										return true;
//									}
//								}
//							}
//						}
//					}
//				}
//		}
//		}
//		return false;
//	}	
}

