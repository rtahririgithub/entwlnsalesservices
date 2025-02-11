/**
 * 
 */
package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.DISCOUNT_TYPE_P;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.LOCAL_LINE_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_3WAY_CALLING_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_CALL_DISPLAY_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SL_FEATURE_CALL_RETURN_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.STV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TELUS_HOME_CONNECTIONS_BUNDLE3_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TELUS_HOME_PHONE_LITE_EXTERNAL_CID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.adapter.IReferencePDSDataServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.BusinessRuleInstanceTypeVO;
import com.telus.csm.ewlnsc.adapter.domain.CodeValueTypeVO;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.AccountProfileRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.delegate.CalculateDepositDelegate;
import com.telus.csm.ewlnsc.delegate.ProductGroupCategorizationDelegate;
import com.telus.csm.ewlnsc.delegate.ProductItemDelegate;
import com.telus.csm.ewlnsc.delegate.response.ProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.AccessoryProductTypeVO;
import com.telus.csm.ewlnsc.domain.AdditionalProductItemTypeVO;
import com.telus.csm.ewlnsc.domain.AppointmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.CalculateDepositRequestVO;
import com.telus.csm.ewlnsc.domain.CalculateDepositResponseVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.GroupCategoryVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InstallmentOptionVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.MoneyVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ProdductDepositVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;
import com.telus.csm.ewlnsc.domain.ProductTypeBaseVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.price.AccessoryProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.AssociatedProductItemPriceVO;
import com.telus.csm.ewlnsc.domain.price.CartItemPriceVO;
import com.telus.csm.ewlnsc.domain.price.CombinedPriceVO;
import com.telus.csm.ewlnsc.domain.price.DepositVO;
import com.telus.csm.ewlnsc.domain.price.DurationVO;
import com.telus.csm.ewlnsc.domain.price.HomePhoneProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.InternetProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.PriceDurationVO;
import com.telus.csm.ewlnsc.domain.price.PriceResponseVO;
import com.telus.csm.ewlnsc.domain.price.PriceWithTaxVO;
import com.telus.csm.ewlnsc.domain.price.ProductItemGroupCategoryPriceVO;
import com.telus.csm.ewlnsc.domain.price.ProductItemPriceVO;
import com.telus.csm.ewlnsc.domain.price.ProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.TelevisionProductPriceVO;
import com.telus.csm.ewlnsc.domain.price.WirelineEquipmentPriceVO;
import com.telus.csm.ewlnsc.domain.price.rule.TTVEquipmentDiscountRuleVO;
import com.telus.csm.ewlnsc.domain.price.rule.InstallFeeRuleVO;
import com.telus.csm.ewlnsc.domain.product.AssociatedProductItemVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.MarketOfferClassificationVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemGroupCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.IncludedServiceConstraint;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.AccessoryOffer;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.transformer.RefPDSServiceDelegatorTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;

/**
 * @author x145592
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class ShoppingCartHelper {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ShoppingCartHelper.class);

	private static final String RECURRING = "RECURRING";
	private static final String DISCOUNT2 = "DISCOUNT";
	private static final String ONE_TIME = "ONE_TIME";
	private static final String FINANCE = "FINANCE";
	private static final String RULE_CD_TTVINSTFEE = "TTVINSTFEE";
	private static final String RULE_CD_HSICINSTFEE = "HSICINSTFEE";
	private static final String RULE_CD_TTVDISC = "TTVDISC";
	private static final String RULE_KEY_OMSTEMPLATE_LIST = "omsTemplateList";
	private static final String RULE_KEY_TERMLIST = "TERMLIST";
	private static final String RULE_KEY_INSTALLFEECID = "INSTALLFEECID";
	private static final String RULE_KEY_INSTALLFEEPARENTCID = "INSTALLFEEPARENTCID";
	private static final String RULE_KEY_INSTALLFEEPRICE = "INSTALLFEEPRICE";
	private static final String RULE_KEY_PLAN = "PLAN";
	private static final String RULE_KEY_RENTMICLIST1 = "RENTMICLIST1";
	private static final String RULE_KEY_RENTMICLIST2 = "RENTMICLIST2";
	private static final String RULE_KEY_RENTQTY1 = "RENTQTY1";
	private static final String RULE_KEY_RENTQTY2 = "RENTQTY2";
	
	private Map<Map<Set<Integer>, String>, PriceDurationVO> totalMap;
	private int maxDuration;
	private float oneTimeCharge;
	private float oneTimeChargeForTTV;
	private float oneTimeChargeForSing;
	private float oneTimeChargeForHsic;
	private float oneTimeChargeForAccessory;
	private float oneTimeDiscount;
	private float oneTimeDiscountForTTV;
	private float oneTimeDiscountForSing;
	private float oneTimeDiscountForHsic;
	private float oneTimeDiscountForAccessory;
	private boolean installCreditAppliedInd;

	@Autowired
	CalculateDepositDelegate calculateDepositDelegate;

	/**
	 * Add PriceVO info to the given ShoppingCartContextVO.
	 * 
	 * @param param
	 */
	public void populatePrice(ShoppingCartContextVO param, String transactionId) {

		final PriceResponseVO prVO = new PriceResponseVO();
		param.setPriceResponseVO(prVO);

		// 1. populate cart item price list
		totalMap = new HashMap<Map<Set<Integer>, String>, PriceDurationVO>();
		final List<CartItemPriceVO> cartItemPriceList = getCartItemPriceList(param);
		prVO.setCartItemPrice(cartItemPriceList);

		// 2. populate total price
		final CombinedPriceVO totalPrice = calculateTotal();
		prVO.setTotalPrice(totalPrice);

		// 3. populate deposit list
		if (StringUtils.isNotEmpty(param.getCustomerId())) {
			final List<DepositVO> depositList = getDepositList(param);
			prVO.setDeposit(depositList);
		}
	}

	/**
	 * @param pigCategList
	 */
	private List<ProductItemGroupCategoryPriceVO> getPiGrpPriceList(final List<ProductItemGroupCategoryVO> pigCategList,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,String productType, boolean isLiketoLikeRecontract) {
		final List<ProductItemGroupCategoryPriceVO> result = new ArrayList<ProductItemGroupCategoryPriceVO>();

		for (final ProductItemGroupCategoryVO piGrpElem : pigCategList) {
			ProductItemGroupCategoryPriceVO piGrpVO = new ProductItemGroupCategoryPriceVO();

			piGrpVO.setGroupCategory(getGrpVO(piGrpElem.getGroupCategory()));
			Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap = new HashMap<Map<Set<Integer>, String>, PriceDurationVO>();
			piGrpVO.setProductItemPriceList(getPiPriceList(piGrpElem.getProductItems(), prodMap, prodGrpMap,productType, isLiketoLikeRecontract));

			CombinedPriceVO priceSummary = calculateProdPrice(prodGrpMap,productType);
			piGrpVO.setPriceSummary(priceSummary);

			result.add(piGrpVO);
		}

		return result;
	}

	/**
	 * @param piGrpElem
	 */
	private List<ProductItemPriceVO> getPiPriceList(final List<ProductItemVO> piList,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap,String productType, boolean isLiketoLikeRecontract) {
		final List<ProductItemPriceVO> result = new ArrayList<ProductItemPriceVO>();

		if (piList != null) {
			for (final ProductItemVO piElem : piList) {
				if (PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(piElem.getAction())) {
					continue;
				}
				if (piElem.isExistingInd() == false || piElem.isCarryOverInd()) {
					ProductItemPriceVO piPriceVO = getPiPriceVO(piElem, prodMap, prodGrpMap,productType, isLiketoLikeRecontract);
					result.add(piPriceVO);
				}
			}
		}

		return result;
	}

	/**
	 * @param piElem
	 */
	private ProductItemPriceVO getPiPriceVO(final ProductItemVO piElem,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap,String productType, boolean isLiketoLikeRecontract) {
		final ProductItemPriceVO result = new ProductItemPriceVO();

		// nwln-10248 - No Product Item price for Acessory
		if (!EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType)) {
			if (piElem.getProductItemIdentifier() != null) {
				result.setProductItemIdentifier(getIdentifier(piElem.getProductItemIdentifier()));
			}
			if (piElem.getProductCatalogueDescription() != null) {
				result.setProductItemDescription(getDesc(piElem.getProductCatalogueName()));
			}
			if (piElem.getAssociatedProductItems() != null) {
				result.setAssociatedProductItemPrice(getAiPriceList(piElem.getAssociatedProductItems(), prodMap,
						prodGrpMap, productType, isLiketoLikeRecontract));
			}

			PriceVO charge = piElem.getProductItemPriceCharge();
			PriceDiscountVO discount = piElem.getProductItemPriceDiscount();

			result.setPriceSummary(
					getPriceSummary(charge, discount, prodMap, prodGrpMap, productType, isLiketoLikeRecontract));
		}

		return result;
	}

	/**
	 * @param piElem
	 * @return
	 */
	private CombinedPriceVO getPriceSummary(final PriceVO charge, final PriceDiscountVO discount,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap,String productType, boolean isLiketoLikeRecontract) {
		
		//If discount is % then suppress price summary,
		if (discount != null && DISCOUNT_TYPE_P.equals(discount.getDiscountTypeCode())) {
			return null;
		}
		final CombinedPriceVO result = new CombinedPriceVO();

		final List<PriceDurationVO> billPrice = new ArrayList<PriceDurationVO>();

		if (charge != null) {
			final PriceDurationVO pdChargeVO;
			if(!EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType)) {
				logger.debug("getPriceSummary", "Not CPE product, calling getCharge");
				pdChargeVO = getCharge(charge, prodMap, prodGrpMap, productType,isLiketoLikeRecontract);
			}else {
				logger.debug("getPriceSummary", "CPE product, calling getChargeForAccessory");
				pdChargeVO = getChargeForAccessory(charge, prodMap, prodGrpMap, productType,isLiketoLikeRecontract);
			}
			billPrice.add(pdChargeVO);
		}
		if (discount != null) {
			final PriceDurationVO pdDiscountVO = getDiscount(discount, prodMap, prodGrpMap, productType, isLiketoLikeRecontract);
			billPrice.add(pdDiscountVO);
		}

		result.setBillPrice(billPrice);

		return result;
	}

	/**
	 * @param piDescList
	 * @return
	 */
	private List<MultilingualTextVO> getDesc(final List<MultilingualTextVO> piDescList) {
		final List<MultilingualTextVO> result = new ArrayList<MultilingualTextVO>();
		if(!CollectionUtils.isEmpty(piDescList)){
			for (final MultilingualTextVO elem : piDescList) {
				MultilingualTextVO piDescVO = new MultilingualTextVO();
				piDescVO.setLocaleTxt(elem.getLocaleTxt());
				piDescVO.setValueTxt(elem.getValueTxt());
				result.add(piDescVO);
			}
		}
		return result;
	}

	/**
	 * @param piElem
	 * @return
	 */
	private ProductItemIdentifierVO getIdentifier(final ProductItemIdentifierVO pii) {
		final ProductItemIdentifierVO result = new ProductItemIdentifierVO();

		result.setProductCatalogueId(pii.getProductCatalogueId());
		result.setParentProductCatalogueId(pii.getParentProductCatalogueId());

		return result;
	}

	private List<AssociatedProductItemPriceVO> getAiPriceList(final List<AssociatedProductItemVO> aiList,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap,String productType, boolean isLiketoLikeRecontract) {
		final List<AssociatedProductItemPriceVO> result = new ArrayList<AssociatedProductItemPriceVO>();

		for (final AssociatedProductItemVO aiElem : aiList) {
			if (PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(aiElem.getAction())) {
				continue;
			}
			if (aiElem.isExistingInd() == false || aiElem.isCarryOverInd()) {
				AssociatedProductItemPriceVO aiPriceVO = new AssociatedProductItemPriceVO();
				aiPriceVO.setProductItemIdentifier(getIdentifier(aiElem.getProductItemIdentifier()));
				aiPriceVO.setProductItemDescription(getDesc(aiElem.getProductCatalogueName()));
	
				PriceVO aiCharge = aiElem.getProductItemPriceCharge();
				PriceDiscountVO aiDiscount = aiElem.getProductItemPriceDiscount();
	
				aiPriceVO.setPriceSummary(getPriceSummary(aiCharge, aiDiscount, prodMap, prodGrpMap,productType, isLiketoLikeRecontract));
	
				result.add(aiPriceVO);
			}
		}

		return result;
	}

	/**
	 * @param piElem
	 * @param pdVO
	 */
	private PriceDurationVO getCharge(final PriceVO charge,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap,String productType, boolean isLiketoLikeRecontract) {
		final PriceDurationVO result = new PriceDurationVO();

		final PriceVO pVO = new PriceVO();
		final MoneyVO amtVO = new MoneyVO();
		
		if(charge.getBasePriceAmount() != null) {
			amtVO.setValueAmt(charge.getBasePriceAmount().getValueAmt());
		}
		pVO.setBasePriceAmount(amtVO);
		pVO.setPricingTypeCd(charge.getPricingTypeCd());
		result.setPriceCharge(pVO);

		if (ONE_TIME.equalsIgnoreCase(charge.getPricingTypeCd())) {
			oneTimeCharge += charge.getBasePriceAmount().getValueAmt();
			
			/**
			 * Defect 68137 - adding one time charge at product level
			 */
			if(productType.equalsIgnoreCase(HSIC)){
				oneTimeChargeForHsic+= charge.getBasePriceAmount().getValueAmt();
			}else if(productType.equalsIgnoreCase(TTV)){
				oneTimeChargeForTTV+=charge.getBasePriceAmount().getValueAmt();
			}else if(productType.equalsIgnoreCase(SING)){
				oneTimeChargeForSing+=charge.getBasePriceAmount().getValueAmt();
			}
			
		} else {
			final DurationVO dVO = new DurationVO();
			dVO.setStartMonthCnt(1);
			dVO.setEndMonthCnt(charge.getRecurrenceCount());
			int duration;
			if (charge.getRecurrenceCount() == null) {
				maxDuration = 39;
				duration = maxDuration;
			} else if (maxDuration < charge.getRecurrenceCount()) {
				maxDuration = charge.getRecurrenceCount();
				duration = maxDuration;
			} else {
				if (charge.getRecurrenceCount() == 0) { // means is one time
					duration = 1;
				} else {
					duration = charge.getRecurrenceCount();
				}
			}

			result.setDuration(dVO);

			final Map<Set<Integer>, String> map = new HashMap<Set<Integer>, String>();
			final Set<Integer> set = new HashSet<Integer>();
			for (int i = 1; i <= duration; i++) {
				set.add(i);
			}
			map.put(set, "C");

			addChargeToMaps(result, amtVO, map, prodMap, prodGrpMap, isLiketoLikeRecontract, productType);
		}
		return result;
	}
	
	
	/**
	 * @param piElem
	 * @param pdVO
	 */
	//NWLN-10248
	private PriceDurationVO getChargeForAccessory(final PriceVO charge,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap, String productType,
			boolean isLiketoLikeRecontract) {
		final PriceDurationVO result = new PriceDurationVO();
		final PriceVO pVO = new PriceVO();
		final MoneyVO amtVO = new MoneyVO();

		if(charge.getInstallmentOptions() != null) {
			logger.debug("getChargeForAccessory", "setting Accessory Price Finance charge");

			if(charge.getInstallmentOptions().get(0) != null){
				if(charge.getInstallmentOptions().get(0).getInstallmentAmount() != null && charge.getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt() != null) {
					amtVO.setValueAmt(charge.getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt());	
				} else {
					amtVO.setValueAmt(0f);
				}	
			}
				
			//setting Accessory base price to zero so it won't add to price total
			MoneyVO amtTemp = new MoneyVO();
			amtTemp.setValueAmt(0f);
			pVO.setBasePriceAmount(amtTemp);
				
			pVO.setInstallmentOptions(charge.getInstallmentOptions());
			// adding duration
			final DurationVO dVO = new DurationVO();
			dVO.setStartMonthCnt(1);
			int duration = 39;
			if(charge.getInstallmentOptions().get(0) != null) {
				duration = charge.getInstallmentOptions().get(0).getNumberOfInstallments();
				maxDuration = duration;
				dVO.setEndMonthCnt(duration);
					if(duration > 1) {
						maxDuration = duration;
					}
				}
			result.setDuration(dVO);
	
			// Add TotalPrice - no discount for now
			final PriceWithTaxVO totalPrice = new PriceWithTaxVO();
			totalPrice.setBasePriceAmount(charge.getBasePriceAmount());
			totalPrice.setPricingTypeCd(charge.getPricingTypeCd());
			totalPrice.setRecurrenceCount(charge.getRecurrenceCount());
			totalPrice.setInstallmentOptions(charge.getInstallmentOptions());
	
			result.setTotalPrice(totalPrice);
				
			//Create equipment map
			final Map<Set<Integer>, String> map = new HashMap<Set<Integer>, String>();
			final Set<Integer> set = new HashSet<Integer>();
			for (int i = 1; i <= duration; i++) {
				set.add(i);
			} //creating a set form 1-12 
			map.put(set, "C");
				
			result.setPriceCharge(pVO);
			addChargeToMaps(result, amtVO, map, prodMap, prodGrpMap, isLiketoLikeRecontract, productType);	
		} else {
			//get One Time charge for accessory 
			logger.debug("getChargeForAccessory", " setting Accessory Price One time charge");
			oneTimeChargeForAccessory += charge.getBasePriceAmount().getValueAmt();
			oneTimeCharge += charge.getBasePriceAmount().getValueAmt();
				
			// Add TotalPrice - no discount for now
			final PriceWithTaxVO totalPrice = new PriceWithTaxVO();
			totalPrice.setBasePriceAmount(charge.getBasePriceAmount());
			totalPrice.setPricingTypeCd(charge.getPricingTypeCd());
	
			result.setTotalPrice(totalPrice);
		}
		
		if(charge.getBasePriceAmount() != null) {
			amtVO.setValueAmt(charge.getBasePriceAmount().getValueAmt());
		}
		pVO.setBasePriceAmount(amtVO);
		pVO.setPricingTypeCd(charge.getPricingTypeCd());
		result.setPriceCharge(pVO);

		return result;
	}

	/**
	 * @param result
	 * @param amtVO
	 * @param map
	 */
	private void addChargeToMaps(final PriceDurationVO result, final MoneyVO amtVO, final Map<Set<Integer>, String> map,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap, boolean isLiketoLikeRecontract, String productType) {
		if (!isLiketoLikeRecontract){
			if (totalMap.get(map) == null) {
				//nwln-10248 creating another priceDuration so basePriceAmount on totalMap will not be overwritten 
				if(EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType) && result.getPriceCharge().getInstallmentOptions() != null) {
					    logger.debug("addChargeToMaps", "Adding Accessory finance charge to Totalmap, setting basePriceAmount to zero....");
						final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
						final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
						pdChargeNewVO.getPriceCharge().getBasePriceAmount().setValueAmt(0f);
						totalMap.put(map, pdChargeNewVO);
				} else {
					totalMap.put(map, result);
				}
			} else {
				//NWLN-10248 - For Accessory products get price from InstallmentOptions
				if(EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType)){
					if(totalMap.get(map).getPriceCharge().getInstallmentOptions() != null && totalMap.get(map).getPriceCharge().getInstallmentOptions().get(0) !=null
							&& totalMap.get(map).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount()!= null
							&& totalMap.get(map).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt()!= null) {
						logger.debug("addChargeToMaps", "Retrieving finance charge from Totalmap, updating Installment Amount....");
						final Float prevAmt = totalMap.get(map).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt();
						final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
						final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
						pdChargeNewVO.getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
						totalMap.put(map, pdChargeNewVO);
					}
				} else {
					final Float prevAmt = totalMap.get(map).getPriceCharge().getBasePriceAmount().getValueAmt();
					final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
					final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
					pdChargeNewVO.getPriceCharge().getBasePriceAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
					totalMap.put(map, pdChargeNewVO); 
				}
			}
		}

		if (prodMap.get(map) == null) {
			prodMap.put(map, result);
		} else {
			//NWLN-10248 - For Accessory products get price from InstallmentOptions
			if(EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType)){
				logger.debug("addChargeToMaps", "Retrieving finance charge from Prodmap, updating Installment Amount....");
				if(prodMap.get(map).getPriceCharge().getInstallmentOptions() != null && prodMap.get(map).getPriceCharge().getInstallmentOptions().get(0) !=null
						&& prodMap.get(map).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount()!= null
						&& totalMap.get(map).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt()!= null) {
					final Float prevAmt = prodMap.get(map).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt();
					final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
					final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
					pdChargeNewVO.getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
					prodMap.put(map, pdChargeNewVO);
				}
			} else {
				final Float prevAmt = prodMap.get(map).getPriceCharge().getBasePriceAmount().getValueAmt();
				final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
				final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
				pdChargeNewVO.getPriceCharge().getBasePriceAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
				prodMap.put(map, pdChargeNewVO);
			}
		}

		if (prodGrpMap != null) {
			if (prodGrpMap.get(map) == null) {
				prodGrpMap.put(map, result);
			} else {
				final Float prevAmt = prodGrpMap.get(map).getPriceCharge().getBasePriceAmount().getValueAmt();
				final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
				final PriceDurationVO pdChargeNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
				pdChargeNewVO.getPriceCharge().getBasePriceAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
				prodGrpMap.put(map, pdChargeNewVO);
			}
		}
	}

	private PriceDurationVO getDiscount(final PriceDiscountVO disc,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap, String productType, boolean isLiketoLikeRecontract) {
		final PriceDurationVO result = new PriceDurationVO();
		final PriceDiscountVO pdVO = new PriceDiscountVO();
		final MoneyVO amtVO = new MoneyVO();
		amtVO.setValueAmt(disc.getBasePriceAmount().getValueAmt());
		pdVO.setBasePriceAmount(amtVO);
		pdVO.setPricingTypeCd(disc.getPricingTypeCd());
		pdVO.setPriceAlterationTypeCd(disc.getPriceAlterationTypeCd());
		result.setPriceDiscount(pdVO);

		if (ONE_TIME.equalsIgnoreCase(disc.getPricingTypeCd())) {
			oneTimeDiscount += disc.getBasePriceAmount().getValueAmt();
			
			/**
			 * Defect 68137 - adding one time charge at product level
			 */
			if(productType.equalsIgnoreCase(HSIC)){
				oneTimeDiscountForHsic+= disc.getBasePriceAmount().getValueAmt();
			}else if(productType.equalsIgnoreCase(TTV)){
				oneTimeDiscountForTTV+=disc.getBasePriceAmount().getValueAmt();
			}else if(productType.equalsIgnoreCase(SING)){
				oneTimeDiscountForSing+=disc.getBasePriceAmount().getValueAmt();
			} else if (productType.equalsIgnoreCase(OIS_ACCESSORIES_CD)) {
				oneTimeDiscountForAccessory += disc.getBasePriceAmount().getValueAmt();
			}
		} else {
			final DurationVO dVO = new DurationVO();
			dVO.setStartMonthCnt(1);
			dVO.setEndMonthCnt(disc.getRecurrenceCount());
			int duration;
			if (disc.getRecurrenceCount() == null) {
				maxDuration = 39;
				duration = maxDuration;
			} else if (maxDuration < disc.getRecurrenceCount()) {
				maxDuration = disc.getRecurrenceCount();
				duration = maxDuration;
			} else {
				if (disc.getRecurrenceCount() == 0) { // means is one time
					duration = 1;
				} else {
					duration = disc.getRecurrenceCount();
				}
			}

			result.setDuration(dVO);

			final Map<Set<Integer>, String> map = new HashMap<Set<Integer>, String>();
			final Set<Integer> set = new HashSet<Integer>();
			for (int i = 1; i <= duration; i++) {
				set.add(i);
			}
			map.put(set, "D");

			addDiscountToMaps(result, amtVO, map, prodMap, prodGrpMap, isLiketoLikeRecontract);
		}

		return result;
	}

	/**
	 * @param result
	 * @param amtVO
	 * @param map
	 */
	private void addDiscountToMaps(final PriceDurationVO result, final MoneyVO amtVO,
			final Map<Set<Integer>, String> map, final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodGrpMap, boolean isLiketoLikeRecontract) {
		if (!isLiketoLikeRecontract){
			if (totalMap.get(map) == null) {
				totalMap.put(map, result);
			} else {
				final Float prevAmt = totalMap.get(map).getPriceDiscount().getBasePriceAmount().getValueAmt();
				final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
				final PriceDurationVO pdNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
				pdNewVO.getPriceDiscount().getBasePriceAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
				totalMap.put(map, pdNewVO);
			}
		}

		if (prodMap.get(map) == null) {
			prodMap.put(map, result);
		} else {
			final Float prevAmt = prodMap.get(map).getPriceDiscount().getBasePriceAmount().getValueAmt();
			final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
			final PriceDurationVO pdNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
			pdNewVO.getPriceDiscount().getBasePriceAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
			prodMap.put(map, pdNewVO);
		}

		if (prodGrpMap != null) {
			if (prodGrpMap.get(map) == null) {
				prodGrpMap.put(map, result);
			} else {
				final Float prevAmt = prodGrpMap.get(map).getPriceDiscount().getBasePriceAmount().getValueAmt();
				final String jsonTot = JsonUtil.getJsonFromObjectNonNUll(result);
				final PriceDurationVO pdNewVO = JsonUtil.parseJsonToObject(jsonTot, PriceDurationVO.class);
				pdNewVO.getPriceDiscount().getBasePriceAmount().setValueAmt(prevAmt + amtVO.getValueAmt());
				prodGrpMap.put(map, pdNewVO);
			}
		}
	}

	/**
	 * @param piGrpElem
	 * @param piGrpVO
	 */
	private GroupCategoryVO getGrpVO(final GroupCategoryVO grp) {
		GroupCategoryVO result = new GroupCategoryVO();

		result.setGroupTypeCd(grp.getGroupTypeCd());
		result.setGroupSubTypeCd(grp.getGroupSubTypeCd());

		return result;
	}

	private CombinedPriceVO calculateTotal() {
		final CombinedPriceVO result = new CombinedPriceVO();
		final List<PriceDurationVO> billPrice = new ArrayList<PriceDurationVO>();
		result.setBillPrice(billPrice);
		boolean isFinanceCharge = false;

		if (oneTimeCharge > 0 || oneTimeDiscount > 0) {
			PriceDurationVO pdVO = new PriceDurationVO();
			billPrice.add(pdVO);
			// 1. set the one-time priceCharge
			final PriceVO pVO = new PriceVO();
			final MoneyVO amtVO = new MoneyVO();
			amtVO.setValueAmt(oneTimeCharge);
			pVO.setBasePriceAmount(amtVO);
			pVO.setPricingTypeCd(ONE_TIME);
			pdVO.setPriceCharge(pVO);

			// 2. set the one-time discount
			final PriceDiscountVO pDiscVO = new PriceDiscountVO();
			final MoneyVO mVO = new MoneyVO();
			mVO.setValueAmt(oneTimeDiscount);
			pDiscVO.setBasePriceAmount(mVO);
			pDiscVO.setPricingTypeCd(ONE_TIME);
			pDiscVO.setPriceAlterationTypeCd(DISCOUNT2);
			pdVO.setPriceDiscount(pDiscVO);

			// 3. set the one-time totalPrice
			final PriceWithTaxVO totalPrice = new PriceWithTaxVO();
			totalPrice.setPricingTypeCd(ONE_TIME);
			final MoneyVO tVO = new MoneyVO();
			tVO.setValueAmt(oneTimeCharge - oneTimeDiscount);
			totalPrice.setBasePriceAmount(tVO);
			pdVO.setTotalPrice(totalPrice);
		}
		
		//calculate regular recurring charges
		Float prev = 0f, prevCr = 0f, prevDb = 0f;
		int prevIndex = 1;
		for (int j = 1; j <= maxDuration + 1; j++) {
			Float amt = 0f, crg = 0f, deb = 0f;
			for (Map<Set<Integer>, String> keyMap : totalMap.keySet()) {
				for (Set<Integer> set : keyMap.keySet())
					if (set.contains(j)) {
						if ("C".equals(keyMap.get(set))) {
							amt += totalMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValueAmt();
							crg += totalMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValueAmt();
						} else {
							amt -= totalMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValueAmt();
							deb += totalMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValueAmt();
						}
					}
			}
			if (j == 1) {
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
			int retval = Float.compare(prev, amt);
			if (retval != 0) {
				PriceDurationVO pdVO = getPD(prev, prevCr, prevDb, prevIndex, j - 1, isFinanceCharge);
				billPrice.add(pdVO);
				prevIndex = j;
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
		}
		
		//calculate Accessory finance charges
		prev = 0f;
		prevCr = 0f; 
		prevDb = 0f;
		prevIndex = 1;
		for (int j = 1; j <= maxDuration + 1; j++) {
			Float amt = 0f, crg = 0f, deb = 0f;
			for (Map<Set<Integer>, String> keyMap : totalMap.keySet()) {
				for (Set<Integer> set : keyMap.keySet())
					if (set.contains(j)) {
						if ("C".equals(keyMap.get(set))) {
							if(totalMap.get(keyMap).getPriceCharge().getInstallmentOptions() != null && totalMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0) != null && totalMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount() != null 
									&& totalMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt() != null) {
									amt += totalMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt();
									crg += totalMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt();
								} 
						}//no discount for finance payment 		
					}
			}
			if (j == 1) {
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
			int retval = Float.compare(prev, amt);
			if (retval != 0) {
				isFinanceCharge = true;
				PriceDurationVO pdVO = getPD(prev, prevCr, prevDb, prevIndex, j - 1, isFinanceCharge);
				billPrice.add(pdVO);
				prevIndex = j;
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
		}
		
		return result;
	}

	private CombinedPriceVO calculateProdPrice(final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			String productType) {
		final CombinedPriceVO result = new CombinedPriceVO();
		final List<PriceDurationVO> billPrice = new ArrayList<PriceDurationVO>();
		result.setBillPrice(billPrice);
		boolean isFinanceCharge = false;

		/**
		 * Defect 68137 - adding one time charge at product level
		 */
		if(HSIC.equalsIgnoreCase(productType) && (oneTimeChargeForHsic > 0 || oneTimeDiscountForHsic > 0)){
			populateOneTimeAmount(billPrice,HSIC);
		}
		
		if(TTV.equalsIgnoreCase(productType) && (oneTimeChargeForTTV > 0 || oneTimeDiscountForTTV > 0)){
			populateOneTimeAmount(billPrice,TTV);
		}
		
		if(SING.equalsIgnoreCase(productType) && (oneTimeChargeForSing > 0 || oneTimeDiscountForSing > 0)){
			populateOneTimeAmount(billPrice, SING);
		}
		//NWLN-10248
		if (OIS_ACCESSORIES_CD.equalsIgnoreCase(productType) && (oneTimeChargeForAccessory > 0 || oneTimeDiscountForAccessory > 0)) {
			populateOneTimeAmount(billPrice, OIS_ACCESSORIES_CD);
		}


		Float prev = 0f, prevCr = 0f, prevDb = 0f;
		int prevIndex = 1;
		int prodMaxDuration = getMaxDuration(prodMap);
		for (int j = 1; j <= prodMaxDuration + 1; j++) {
			Float amt = 0f, crg = 0f, deb = 0f;
			for (Map<Set<Integer>, String> keyMap : prodMap.keySet()) {
				for (Set<Integer> set : keyMap.keySet())
					if (set.contains(j)) {
						if ("C".equals(keyMap.get(set))) {
							amt += prodMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValueAmt();
							crg += prodMap.get(keyMap).getPriceCharge().getBasePriceAmount().getValueAmt();
						} else {
							amt -= prodMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValueAmt();
							deb += prodMap.get(keyMap).getPriceDiscount().getBasePriceAmount().getValueAmt();
						}
					}
			}
			if (j == 1) {
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
			int retval = Float.compare(prev, amt);
			if (retval != 0) {
				PriceDurationVO pdVO = getPD(prev, prevCr, prevDb, prevIndex, j - 1, isFinanceCharge);
				billPrice.add(pdVO);
				prevIndex = j;
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
		}
		return result;
	}
	
	//NWLN-10248
	private CombinedPriceVO calculateAccessoryProdPrice(final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,
			String productType) {
		final CombinedPriceVO result = new CombinedPriceVO();
		final List<PriceDurationVO> billPrice = new ArrayList<PriceDurationVO>();
		result.setBillPrice(billPrice);
		boolean isFinanceCharge = true;

		/**
		 * Defect 68137 - adding one time charge at product level
		 */
		if (oneTimeChargeForAccessory > 0 || oneTimeDiscountForAccessory > 0) {
			logger.debug("calculateAccessoryProdPrice", "Populating One Time Amount");
			populateOneTimeAmount(billPrice, OIS_ACCESSORIES_CD);
		}
		
		
		Float prev = 0f, prevCr = 0f, prevDb = 0f;
		int prevIndex = 1;
		int prodMaxDuration = getMaxDuration(prodMap);
		for (int j = 1; j <= prodMaxDuration + 1; j++) {
			Float amt = 0f, crg = 0f, deb = 0f;
			for (Map<Set<Integer>, String> keyMap : prodMap.keySet()) {
				for (Set<Integer> set : keyMap.keySet())
					if (set.contains(j)) {
						if ("C".equals(keyMap.get(set))) {
							if(prodMap.get(keyMap).getPriceCharge().getInstallmentOptions() != null && prodMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0) != null 
									&& prodMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount() != null && prodMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt() != null) {
									amt += prodMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt();
									crg += prodMap.get(keyMap).getPriceCharge().getInstallmentOptions().get(0).getInstallmentAmount().getValueAmt(); 
							}
						} //No discount for finance payment
					}
			}
			if (j == 1) {
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
			int retval = Float.compare(prev, amt);
			if (retval != 0) {
				PriceDurationVO pdVO = getPD(prev, prevCr, prevDb, prevIndex, j - 1, isFinanceCharge);
				billPrice.add(pdVO);
				prevIndex = j;
				prev = amt;
				prevCr = crg;
				prevDb = deb;
			}
		}
		return result;
	}

	private void populateOneTimeAmount(List<PriceDurationVO> billPrice, String productType) {
		PriceDurationVO pdVO = new PriceDurationVO();
		billPrice.add(pdVO);
		// 1. set the one-time priceCharge
		final PriceVO pVO = new PriceVO();
		final MoneyVO amtVO = new MoneyVO();
		if(productType.equalsIgnoreCase(HSIC)){
			amtVO.setValueAmt(oneTimeChargeForHsic);
		}else if(productType.equalsIgnoreCase(TTV)){
			amtVO.setValueAmt(oneTimeChargeForTTV);
		}else if(productType.equalsIgnoreCase(SING)){
			amtVO.setValueAmt(oneTimeChargeForSing);
			// nwln-10248
		} else if (productType.equalsIgnoreCase(OIS_ACCESSORIES_CD)) {
			amtVO.setValueAmt(oneTimeChargeForAccessory);
		}
		
		pVO.setBasePriceAmount(amtVO);
		pVO.setPricingTypeCd(ONE_TIME);
		pdVO.setPriceCharge(pVO);

		// 2. set the one-time discount
		final PriceDiscountVO pDiscVO = new PriceDiscountVO();
		final MoneyVO mVO = new MoneyVO();
		if(productType.equalsIgnoreCase(HSIC)){
			mVO.setValueAmt(oneTimeDiscountForHsic);
		}else if(productType.equalsIgnoreCase(TTV)){
			mVO.setValueAmt(oneTimeDiscountForTTV);
		}else if(productType.equalsIgnoreCase(SING)){
			mVO.setValueAmt(oneTimeDiscountForSing);
		} // NWLN-10248
		else if (productType.equalsIgnoreCase(OIS_ACCESSORIES_CD)) {
			mVO.setValueAmt(oneTimeDiscountForAccessory);
		}
		
		pDiscVO.setBasePriceAmount(mVO);
		pDiscVO.setPricingTypeCd(ONE_TIME);
		pDiscVO.setPriceAlterationTypeCd(DISCOUNT2);
		pdVO.setPriceDiscount(pDiscVO);

		// 3. set the one-time totalPrice
		final PriceWithTaxVO totalPrice = new PriceWithTaxVO();
		totalPrice.setPricingTypeCd(ONE_TIME);
		final MoneyVO tVO = new MoneyVO();
		if(productType.equalsIgnoreCase(HSIC)){
			tVO.setValueAmt(oneTimeChargeForHsic- oneTimeDiscountForHsic);
		}else if(productType.equalsIgnoreCase(TTV)){
			tVO.setValueAmt(oneTimeChargeForTTV- oneTimeDiscountForTTV);
		}else if(productType.equalsIgnoreCase(SING)){
			tVO.setValueAmt(oneTimeChargeForSing- oneTimeDiscountForSing);
		} // NWLN-10248
		else if (productType.equalsIgnoreCase(OIS_ACCESSORIES_CD)) {
			tVO.setValueAmt(oneTimeChargeForAccessory - oneTimeDiscountForAccessory);
		}
		
		totalPrice.setBasePriceAmount(tVO);
		pdVO.setTotalPrice(totalPrice);
		
	}

	private int getMaxDuration(final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap) {
		int result = -1;

		for (final Map<Set<Integer>, String> keyMap : prodMap.keySet()) {
			Integer endMonthCnt = prodMap.get(keyMap).getDuration().getEndMonthCnt();

			if (endMonthCnt == null) {
				result = 39;
				break;
			} else if (result < endMonthCnt) {
				result = endMonthCnt;
			}
		}

		return result;
	}

	private PriceDurationVO getPD(final Float tot, final Float charge, final Float discount, final int start,
			final int end, boolean isFinanceCharge) {
		final PriceDurationVO result = new PriceDurationVO();
		
		//NWLN-10248 - add installmentOptions to total Price
		InstallmentOptionVO installmentOption = new InstallmentOptionVO();
		if(isFinanceCharge){
			final MoneyVO amtVO = new MoneyVO();
			amtVO.setValueAmt(charge);
			MoneyVO downPaymentAmount = new MoneyVO();
			downPaymentAmount.setValueAmt(0f);
			
			installmentOption.setInstallmentAmount(amtVO);
			installmentOption.setNumberOfInstallments(end);
			installmentOption.setDownPaymentAmount(downPaymentAmount);
			installmentOption.setInstallmentOptionCd(EnterpriseWLNSalesServicesConstants.INSTALLMENT_OPTION_CD_OPTIONAL);			
		}
		
		// 1. set the priceCharge
		final PriceVO pVO = new PriceVO();
		final MoneyVO amtVO = new MoneyVO();
		amtVO.setValueAmt(charge);
		pVO.setBasePriceAmount(amtVO);
		pVO.setPricingTypeCd(RECURRING);
		if(isFinanceCharge && installmentOption != null){
			pVO.setInstallmentOptions(Arrays.asList(installmentOption));
		}
		result.setPriceCharge(pVO);

		// 2. set the priceCredit
		final PriceDiscountVO pdVO = new PriceDiscountVO();
		final MoneyVO mVO = new MoneyVO();
		mVO.setValueAmt(discount);
		pdVO.setBasePriceAmount(mVO);
		pdVO.setPricingTypeCd(RECURRING);
		pdVO.setPriceAlterationTypeCd(DISCOUNT2);
		result.setPriceDiscount(pdVO);

		// 3. set the totalPrice
		final PriceWithTaxVO totalPrice = new PriceWithTaxVO();
		totalPrice.setPricingTypeCd(RECURRING);
		final MoneyVO tVO = new MoneyVO();
		tVO.setValueAmt(tot);
		totalPrice.setBasePriceAmount(tVO);
		if(isFinanceCharge && installmentOption != null){
			totalPrice.setInstallmentOptions(Arrays.asList(installmentOption));
		}
		result.setTotalPrice(totalPrice);

		// 4. set the duration
		DurationVO dVO = new DurationVO();
		dVO.setStartMonthCnt(start);
		if (end == 39) {
			dVO.setEndMonthCnt(null);
		} else {
			dVO.setEndMonthCnt(end);
		}
		result.setDuration(dVO);

		return result;
	}

	private List<DepositVO> getDepositList(final ShoppingCartContextVO param) {
		final List<DepositVO> result = new ArrayList<DepositVO>();
		
		final ShoppingCartVO scVO = param.getShoppingCartVO();
		if (scVO.getDepositAssessed() != null && scVO.getDepositAssessed().getDepositAmt() != null) {
			final DepositVO depositVO = new DepositVO();
			depositVO.setDepositAmt(BigDecimal.valueOf(scVO.getDepositAssessed().getDepositAmt()));
			result.add(depositVO);
		} else if (scVO.isWirelineProspectCustomer() && !scVO.isCustomerCreditCompleted()) {
			final DepositVO depositVO = new DepositVO();
			depositVO.setDepositAmt(BigDecimal.valueOf(0.0));
			result.add(depositVO);
		} else {
			if (isEstimatedDeposits(param)) {
				final CalculateDepositRequestVO calculateDepositRequestVO = new CalculateDepositRequestVO();
				//If no FFH product ordered skip call to deposit calculation - Accessory only order
				if(param.getOrderedProducts() == null || param.getOrderedProducts().isEmpty()) {
					final DepositVO depositVO = new DepositVO();
					depositVO.setDepositAmt(BigDecimal.valueOf(0.0));
					result.add(depositVO);
					return result;
				}
				calculateDepositRequestVO.setOrderedProductsList(param.getOrderedProducts());
				if (!StringUtils.isEmpty(param.getCustomerId())) {
					calculateDepositRequestVO.setCustomerId(Long.parseLong(param.getCustomerId()));
				}
				String externalOrderDetail = EnterpriseWLNOrderUtil.getExternalOrderIdBySystem(scVO, EnterpriseWLNSalesServicesConstants.EXTERNAL_SYSTEM_OMS);
				if(!StringUtils.isBlank(externalOrderDetail)) {
					calculateDepositRequestVO.setOrderId(externalOrderDetail);
				}
				calculateDepositRequestVO.setSourceSystemId("VESTA");
				calculateDepositRequestVO.setCalulationTypeCode("ESTIMATE");
				calculateDepositRequestVO.setApplicationId("TelusOrdering");
				calculateDepositRequestVO.setUserId("ICS_3");
	
				if (param.getAssignedAndPendingProductResponseVO() != null) {
					List<AccountProfileRestVO> customerProductsSummary = param.getAssignedAndPendingProductResponseVO().getCustomerProductSummaryList();
					calculateDepositRequestVO.setCustomerProducts(customerProductsSummary);
				}
	
				CalculateDepositResponseVO calculateDepositResponseVO = calculateDepositDelegate.execute(calculateDepositRequestVO);
	
				return transform(calculateDepositResponseVO);
	
			} else {
				return transform(param.getOmsDepositResponse());
			}
		}
		
		return result;
	}

	private List<DepositVO> transform(GetDepositInfoResponse omsDepositResponse) {
		List<DepositVO> depositList = new ArrayList<DepositVO>();
		DepositVO depositVO = new DepositVO();
		depositVO.setEstimatedDepositInd(false);
		depositVO.setDepositAmt(BigDecimal.valueOf(omsDepositResponse.getTotalRequiredDeposit()));
		depositList.add(depositVO);
		return depositList;
	}

	private List<DepositVO> transform(CalculateDepositResponseVO calculateDepositResponseVO) {

		List<DepositVO> depositList = new ArrayList<DepositVO>();

		if (calculateDepositResponseVO != null && calculateDepositResponseVO.getProductDepositList() != null) {
			List<ProdductDepositVO> productDepositList = calculateDepositResponseVO.getProductDepositList();
			for (ProdductDepositVO productDeposit : productDepositList) {
				DepositVO depositVO = new DepositVO();
				depositVO.setDepositAmt(productDeposit.getAssessedDepositAmt());
				// depositVO.setDepositType(productDeposit.get);
				depositVO.setEstimatedDepositInd(true);
				depositList.add(depositVO);
			}
		}
		return depositList;
	}

	private List<CartItemPriceVO> getCartItemPriceList(final ShoppingCartContextVO scContext) {
		final List<CartItemPriceVO> result = new ArrayList<CartItemPriceVO>();

		for (final CartItemVO cartItemElem : scContext.getShoppingCartVO().getCartItemList()) {
			CartItemPriceVO cartItemPrice = getCartPrice(cartItemElem, scContext);
			result.add(cartItemPrice);
		}

		return result;

	}

	private CartItemPriceVO getCartPrice(final CartItemVO cartItem, final ShoppingCartContextVO scContext) {
		final CartItemPriceVO result = new CartItemPriceVO();
		
		result.setCartItemId(cartItem.getCartItemId());
		
		final List<ProductPriceVO> productPriceList = new ArrayList<ProductPriceVO>();
		final ProductTypeVO cartProduct = cartItem.getProducts();
		
		if (cartProduct != null) {
			final ProductPriceVO productPrice = new ProductPriceVO();
			
			final InternetProductPriceVO internetPrice = getInternetProductPrice(cartItem, scContext);
			productPrice.setInternetProductPrice(internetPrice);

			final TelevisionProductPriceVO tvPrice = getTvProductPrice(cartItem, scContext);
			productPrice.setTelevisionProductPrice(tvPrice);

			final HomePhoneProductPriceVO singPrice = getSingProductPrice(cartItem, scContext);
			productPrice.setHomePhoneProductPrice(singPrice);

			// NWLN-10248
			final AccessoryProductPriceVO accessoryPrice = getAccessoryProductPrice(cartItem, scContext);
			productPrice.setAccessoryProductPrice(accessoryPrice);

			productPriceList.add(productPrice);
			result.setProductPriceList(productPriceList);
		}

		return result;
	}

	private InternetProductPriceVO getInternetProductPrice(final CartItemVO cartItem, final ShoppingCartContextVO scContext) {
		final InternetProductPriceVO result = new InternetProductPriceVO();
		
		final InternetProductTypeVO ciHsVO = cartItem.getProducts().getInternetProduct();
		if (ciHsVO != null) {
			// components, addon, discounts, sweetener discounts.
			final List<ProductItemVO> ciPiList = buildProductItems(ciHsVO.getProductComponents(), ciHsVO.getAddOns(), ciHsVO.getDiscounts(), ciHsVO.getSweeteners(), ciHsVO.getAdditionalProductItemList());
			
			//Installl Credit Sweetener.
			final ProductItemVO installCreditProductItem = buildInstallCredit(HSIC, ciHsVO.getAppointmentDetail(), scContext); 
			if (installCreditProductItem != null) {
				ciPiList.add(installCreditProductItem);
			}
			// Equipments
			final List<FFHEquipmentItemVO> ciEquipList = transformCiEquipments(ciHsVO.getEquipments());

			final ProductItemDelegate piDelegate = new ProductItemDelegate();
			final ProductItemDelegateResponse piDelegateResp = piDelegate.populateProductItemsForPrice(HSIC, cartItem, ciPiList, ciEquipList, scContext);
			Map<String, CatalogueItemDO> productCataloguesFromGrid = WirelineProductHelper.getProductIdsFromGrid(scContext.getAssignedProducts());
			final WirelineProductHelper helper = new WirelineProductHelper();
			final List<ProductItemVO> allPiList = helper.getAssignedProductItems(scContext, piDelegateResp.getProductItems(), HSIC, productCataloguesFromGrid);
			final List<FFHEquipmentItemVO> assgnEquipList = helper.getAssignedEquipments(scContext, HSIC, false, productCataloguesFromGrid);
			
			final List<FFHEquipmentItemVO> allEquipList = mergeEquipments(piDelegateResp.getEquipmentItems(), assgnEquipList);

			final boolean isLiketoLikeRecontract = WirelineProductHelper.isLikeToLikeReContracting(EnterpriseWLNSalesServicesConstants.HSIC, scContext, productCataloguesFromGrid);
			
			// apply grouping
			final ProductGroupCategorizationDelegate grpCategDelegate = new ProductGroupCategorizationDelegate();
			final List<ProductItemGroupCategoryVO> groupCategories = grpCategDelegate.groupProductItems(allPiList);
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap = new HashMap<Map<Set<Integer>, String>, PriceDurationVO>();
			final List<ProductItemGroupCategoryPriceVO> piGrpPriceList = getPiGrpPriceList(groupCategories, prodMap,HSIC, isLiketoLikeRecontract);
			
			// NWLN-10766 HSIC INSTALLATION FEE RULE, only Apply to new customer without bundle of SING
			if(!hasAssignedAndPendingService(scContext, cartItem) && !hasProductInCart(scContext, SING)) {
				final GetSalesOfferDetailResponseVO2 offerDetailVO = scContext.getOfferByCartItemOfferId(cartItem.getProductMarketOffering().getOfferHeader().getOfferId());
				applyInstallationFeeRule(piDelegateResp.getEquipmentItems(), cartItem.getProducts(), offerDetailVO, HSIC);
			}
			final List<WirelineEquipmentPriceVO> equipmentPriceList = getEquipPriceList(allEquipList, prodMap,HSIC, isLiketoLikeRecontract);

			result.setProductItemGroupCategoryPrice(piGrpPriceList);
			result.setEquipmentPrice(equipmentPriceList);
			result.setEstimatedPriceInd(true);

			final CombinedPriceVO priceSummary = calculateProdPrice(prodMap,HSIC);
			result.setPriceSummary(priceSummary);
		}

		return result;
	}

	private ProductItemVO buildInstallCredit(final String productType, AppointmentDetailTypeVO apptDetail, ShoppingCartContextVO scContext) {
		
		if (apptDetail == null || apptDetail.getBookedAppointmentDate() == null || apptDetail.getBookedAppointmentDate().getAppointmentDate() == null){
			return null;
		}
		
		final Date appointmentDate = apptDetail.getBookedAppointmentDate().getAppointmentDate();
		
		if (!installCreditAppliedInd &&  scContext.isEligibleForInstallCredit(appointmentDate)) {
			SweetenerOfferSummary sweetener = scContext.getInstallionCreditSweetener();
			if (sweetener != null) {
				for (final WirelineOfferProductSummary elem : sweetener.getOfferProductSummary().getWirelineOfferProductSummaryList()) {
					if (productType.equals(elem.getProductTypeCode())) {
						ProductItemVO productItemVO = new ProductItemVO();
						ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
						MarketOfferClassificationVO offerClassification = new MarketOfferClassificationVO();
						offerClassification.setSweetenerInd(true);
						productItemVO.setMarketOfferClassification(offerClassification);
						productItemVO.setProductItemIdentifier(itemIdentifier);
						// TODO get(0)
						String parentId = elem.getDiscountList().get(0).getDiscountProductCatalogueItemList().get(0).getParentProductCatalogueIdentifier().getProductCatalogueId(); 
						String catalogId = elem.getDiscountList().get(0).getDiscountProductCatalogueItemList().get(0).getProductCatalogueIdentifier().getProductCatalogueId();
						itemIdentifier.setParentProductCatalogueId(parentId);
						itemIdentifier.setProductCatalogueId(catalogId);
						
						installCreditAppliedInd = true;
						return productItemVO;
					}
				}
			}
		}
		
		return null;
	}

	private boolean isEstimatedPricing(ShoppingCartContextVO scContext) {

		if (scContext.isManualOrder() 
				|| CollectionUtils.isEmpty(scContext.getShoppingCartVO().getExternalOrderDetailList())
				|| scContext.getQuoteResponse() == null 
				|| scContext.getQuoteResponse().hasError()) {
			return true;
		}
		return false;

	}

	private boolean isEstimatedDeposits(ShoppingCartContextVO scContext) {
		if (scContext.isManualOrder() 
				|| CollectionUtils.isEmpty(scContext.getShoppingCartVO().getExternalOrderDetailList())
				|| scContext.getOmsDepositResponse() == null 
				|| scContext.getOmsDepositResponse().hasError()) {
			return true;
		}
		return false;
	}

	private TelevisionProductPriceVO getTvProductPrice(final CartItemVO cartItem, final ShoppingCartContextVO scContext) {
		String functionName = "getTvProductPrice";
		final TelevisionProductPriceVO result = new TelevisionProductPriceVO();
		
		final TelevisionProductTypeVO ciTtvVO = cartItem.getProducts().getTelevisionProduct();
		if (ciTtvVO != null) {
			// components, addon, discounts, sweetener discounts.
			final List<ProductItemVO> ciPiList = buildProductItems(ciTtvVO.getProductComponents(), ciTtvVO.getAddOns(), ciTtvVO.getDiscounts(), ciTtvVO.getSweeteners(), ciTtvVO.getAdditionalProductItemList());
			
			//Install Credit Sweetener.
			final ProductItemVO installCreditProductItem = buildInstallCredit(TTV, ciTtvVO.getAppointmentDetail(), scContext); 
			if (installCreditProductItem != null) {
				ciPiList.add(installCreditProductItem);
			}

			// Equipments
			final List<FFHEquipmentItemVO> ciEquipList = transformCiEquipments(ciTtvVO.getEquipments());
			Map<String, CatalogueItemDO> productCataloguesFromGrid = WirelineProductHelper.getProductIdsFromGrid(scContext.getAssignedProducts());
			final WirelineProductHelper helper = new WirelineProductHelper();
			final List<ProductItemVO> allPiList = helper.getAssignedProductItems(scContext, ciPiList, TTV, productCataloguesFromGrid);
			final List<FFHEquipmentItemVO> assgnEquipList = helper.getAssignedEquipments(scContext, TTV, false, productCataloguesFromGrid);
			
			final List<FFHEquipmentItemVO> allEquipList = mergeEquipments(ciEquipList, assgnEquipList);

			final ProductItemDelegate piDelegate = new ProductItemDelegate();
			final ProductItemDelegateResponse piDelegateResp = piDelegate.populateProductItemsForPrice(TTV, cartItem, allPiList, allEquipList, scContext);
			//
			final GetSalesOfferDetailResponseVO2 offerDetailVO = scContext.getOfferByCartItemOfferId(cartItem.getProductMarketOffering().getOfferHeader().getOfferId());
			if (isEstimatedPricing(scContext)) {
				logger.debug(functionName, "apply free pricing rule for estimated Pricing");
				/********************************************************************************/
				/*********** This can be removed once the ProductItemChargeDelegate *************/
				applyConstraintPricing(piDelegateResp.getProductItems(), offerDetailVO);
				/********************************************************************************/
				
				/********************************************************************************
				 * Switch to use the ProductItemChargeDelegate at some point
				 * ProductItemChargeDelegate productItemChargeDelegate = new
				 * ProductItemChargeDelegate();
				 * productItemChargeDelegate.applyConstraintPricing(EnterpriseWLNSalesServicesConstants.TTV,
				 * piDelegateResp.getProductItems(), scContext);
				 ***********************************************************************************/
			}

			boolean isLiketoLikeRecontract = WirelineProductHelper.isLikeToLikeReContracting(
					EnterpriseWLNSalesServicesConstants.TTV, scContext, productCataloguesFromGrid);

			// apply grouping
			final ProductGroupCategorizationDelegate grpCategDelegate = new ProductGroupCategorizationDelegate();
			final List<ProductItemGroupCategoryVO> pigCategList = grpCategDelegate
					.groupProductItems(piDelegateResp.getProductItems());
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap = new HashMap<Map<Set<Integer>, String>, PriceDurationVO>();
			final List<ProductItemGroupCategoryPriceVO> piGrpPriceList = getPiGrpPriceList(pigCategList, prodMap, TTV,
					isLiketoLikeRecontract);

			// TTV EQUIPMENT DISCOUNT RULE.
			// QC70694 - internet product is not in ttv cartItem
			applyTtvEquipmentDiscountsRule(piDelegateResp.getEquipmentItems(), scContext, offerDetailVO,
					scContext.getProductQualificationAdapterResponseVO());

			// TTV INSTALLATION FEE RULE
			applyInstallationFeeRule(piDelegateResp.getEquipmentItems(), cartItem.getProducts(), offerDetailVO, TTV);

			final List<WirelineEquipmentPriceVO> equipmentPriceList = getEquipPriceList(
					piDelegateResp.getEquipmentItems(), prodMap, TTV, isLiketoLikeRecontract);

			result.setProductItemGroupCategoryPrice(piGrpPriceList);
			result.setEquipmentPrice(equipmentPriceList);
			result.setEstimatedPriceInd(true);

			final CombinedPriceVO priceSummary = calculateProdPrice(prodMap, TTV);
			result.setPriceSummary(priceSummary);
		}

		return result;
	}

	// NWLN-10248
	private AccessoryProductPriceVO getAccessoryProductPrice(final CartItemVO cartItem,
			final ShoppingCartContextVO scContext) {
		
		//reseting oneTimeChargeForAccessory to zero for each accessory Product
		oneTimeChargeForAccessory = 0;
		final AccessoryProductPriceVO result = new AccessoryProductPriceVO();

		final AccessoryProductTypeVO ciAccessoryVO = cartItem.getProducts().getAccessoryProduct();
		if (ciAccessoryVO != null) {
			logger.debug("getAccessoryProductPrice", "AccessoryProduct found in shoppingcart");
			// components, addon, discounts, sweetener discounts.
			final List<ProductItemVO> ciPiList = buildProductItems(ciAccessoryVO.getProductComponents(),
					ciAccessoryVO.getAddOns(), ciAccessoryVO.getDiscounts(), ciAccessoryVO.getSweeteners(),
					ciAccessoryVO.getAdditionalProductItemList());

			// Installl Credit Sweetener.
			final ProductItemVO installCreditProductItem = buildInstallCredit(OIS_ACCESSORIES_CD,
					ciAccessoryVO.getAppointmentDetail(), scContext);
			if (installCreditProductItem != null) {
				ciPiList.add(installCreditProductItem);
			}
			// Equipments
			final List<FFHEquipmentItemVO> ciEquipList = transformCiEquipments(ciAccessoryVO.getEquipments());

			final ProductItemDelegate piDelegate = new ProductItemDelegate();
			final ProductItemDelegateResponse piDelegateResp = piDelegate
					.populateProductItemsForPrice(OIS_ACCESSORIES_CD, cartItem, ciPiList, ciEquipList, scContext);
			Map<String, CatalogueItemDO> productCataloguesFromGrid = WirelineProductHelper
					.getProductIdsFromGrid(scContext.getAssignedProducts());
			final WirelineProductHelper helper = new WirelineProductHelper();
			final List<ProductItemVO> allPiList = helper.getAssignedProductItems(scContext,
					piDelegateResp.getProductItems(), OIS_ACCESSORIES_CD, productCataloguesFromGrid);
			final List<FFHEquipmentItemVO> assgnEquipList = helper.getAssignedEquipments(scContext, OIS_ACCESSORIES_CD,
					false, productCataloguesFromGrid);

			final List<FFHEquipmentItemVO> allEquipList = mergeEquipments(piDelegateResp.getEquipmentItems(),
					assgnEquipList);

			final boolean isLiketoLikeRecontract = WirelineProductHelper.isLikeToLikeReContracting(
					EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD, scContext, productCataloguesFromGrid);

			// apply grouping
			final ProductGroupCategorizationDelegate grpCategDelegate = new ProductGroupCategorizationDelegate();
			final List<ProductItemGroupCategoryVO> groupCategories = grpCategDelegate.groupProductItems(allPiList);
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap = new HashMap<Map<Set<Integer>, String>, PriceDurationVO>();
			final List<ProductItemGroupCategoryPriceVO> piGrpPriceList = getPiGrpPriceList(groupCategories, prodMap,
					OIS_ACCESSORIES_CD, isLiketoLikeRecontract);
			final List<WirelineEquipmentPriceVO> equipmentPriceList = getEquipPriceList(allEquipList, prodMap,
					OIS_ACCESSORIES_CD, isLiketoLikeRecontract);

			result.setProductItemGroupCategoryPrice(piGrpPriceList);
			result.setEquipmentPrice(equipmentPriceList);
			result.setEstimatedPriceInd(true);

			final CombinedPriceVO priceSummary = calculateAccessoryProdPrice(prodMap, EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD);
			result.setPriceSummary(priceSummary);
		}

		return result;
	}
	

	/** NWLN-10766 Change installation fee rule to support both HS and TV
	 * If TTV product transactionType is: activation 
			AND OMS Template is in [omsTemplate]  
			AND TTV committmentTerm is in [TERMLIST] 
			AND THERE ARE ITEMS IN THE TTV cartItem.television.rentalEquipmentList 
			AND THERE ARE NO ITEMS IN THE cartItem.television.buyEquipmentList  
			AND THERE ARE NO ITEMS IN THE cartItem.television.customerOwnedEquipmentList   

	 * @param equipments
	 */
	private void applyInstallationFeeRule(List<FFHEquipmentItemVO> equipments, ProductTypeVO products, GetSalesOfferDetailResponseVO2 offerDetailVO, String productType ) {
		final String functionName  = "applyInstallationFeeRule"; 
		logger.debug(functionName, " Checking if " + productType + " Installation Fee pricing rule can be applied.");
		
		ProductTypeBaseVO product = null;
		
		if (HSIC.equalsIgnoreCase(productType)) {
			product = products.getInternetProduct();
		} else if (TTV.equalsIgnoreCase(productType)) {
			product = products.getTelevisionProduct();
		} else {
			logger.error(productType + " is not HSIC or TTV, no installation fee rule");
			return;
		}
		
		//product -> activation
		if(product == null || !EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(product.getProductOrderType().getOrderTypeCd())) {
			logger.debug(functionName, productType + " EQPT INSTALL FEE RULE NOT APPLICABLE - " + productType + " in cart as be of activation");
			return;
		}
		
		//Get config from refpds.
		InstallFeeRuleVO ruleVO = getInstallFeetRuleVO(productType);
		
		if(ruleVO == null) {
			logger.error( productType + " EQPT INSTALL FEE RULE NOT APPLICABLE - RefPDS config not found for the rule."  );
			return;
		}
		
		if(!(ruleVO.getTermList() == null || ruleVO.getTermList().contains(product.getSelectedContractTermCd()))) {
			logger.debug(functionName, productType + " EQPT INSTALL FEE RULE NOT APPLICABLE - " + productType + " selected term " +  product.getSelectedContractTermCd() + " not in configured terms " + ruleVO.getTermList());
			return;
		}
		
		//Offer OMS template is in configured list
		String offerOmsTemplateId = getOfferTemplate(productType, offerDetailVO);
		if(offerOmsTemplateId == null) {
			logger.error(productType + " EQPT DISC RULE CANNOT BE APPLIED - offer oms template not found for " + productType);
			return;
		}
		if(ruleVO.getOmsTemplates() == null || !ruleVO.getOmsTemplates().contains(offerOmsTemplateId)) {
			logger.debug(functionName, productType + " EQPT DISC RULE CANNOT BE APPLIED - offer oms template " + offerOmsTemplateId + " not found in configured list " + ruleVO.getOmsTemplates()  );
			return;
		}
		
		if( product.getEquipments() != null && !product.getEquipments().isEmpty() ) {
			for(FFHEquipmentTypeVO equipment : product.getEquipments()) {
				if( (equipment.getAcquisitionType().isBuyIndicator() != null && equipment.getAcquisitionType().isBuyIndicator())
						||(equipment.getAcquisitionType().isCustomerOwnedIndicator() != null && equipment.getAcquisitionType().isCustomerOwnedIndicator())) {
					//If buy or client owned equipment found, rule cannot be applied.
					return;
				}
			}
		}
		
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		CatalogueItemDO result = gridHelper.getCatalogueItemById(ruleVO.getInstallFeeProductCatalogueId());
		
		ListIterator<FFHEquipmentItemVO> iterator = equipments.listIterator();
		while (iterator.hasNext()) {
			FFHEquipmentItemVO item = iterator.next();
			List<AssociatedProductItemVO> associatedProductItems = item.getAssociatedProductItems();
			if(associatedProductItems == null) {
				associatedProductItems =  new ArrayList<AssociatedProductItemVO>();
			}
			AssociatedProductItemVO pi = new AssociatedProductItemVO();
			pi.setExistingInd(false);
			
			ProductItemIdentifierVO catalogIdentifier = new ProductItemIdentifierVO();
			catalogIdentifier.setProductCatalogueId(ruleVO.getInstallFeeProductCatalogueId());
			catalogIdentifier.setParentProductCatalogueId(ruleVO.getInstallFeeParentProductCatalogueId());
			pi.setProductItemIdentifier(catalogIdentifier);
			
			PriceVO price = new PriceVO();
			MoneyVO money = new MoneyVO();
			money.setValueAmt(ruleVO.getPrice().floatValue());
			price.setBasePriceAmount(money);
			price.setPricingTypeCd(ONE_TIME);
			pi.setProductItemPriceCharge(price);
			
			List<MultilingualTextVO> nameList = new ArrayList<MultilingualTextVO>();
			MultilingualTextVO name = new MultilingualTextVO();
			name.setLocaleTxt("EN");
			name.setValueTxt(result.getName());
			nameList.add(name);
			pi.setProductCatalogueName(nameList);
			
			List<MultilingualTextVO> descList = new ArrayList<MultilingualTextVO>();
			MultilingualTextVO desc = new MultilingualTextVO();
			desc.setLocaleTxt("EN");
			desc.setValueTxt(result.getName());
			descList.add(desc);
			pi.setProductCatalogueDescription(descList);
			
			associatedProductItems.add(pi);
			
			item.setAssociatedProductItems(associatedProductItems);

			iterator.set(item);
			
			break;

		}
		
	}
	
	private InstallFeeRuleVO getInstallFeetRuleVO(String productType) {
		InstallFeeRuleVO ruleVO = null;
		List<BusinessRuleInstanceTypeVO> ruleList = getBusinessRule(EnterpriseWLNSalesServicesConstants.REFPDS_PRCG_RULE_REF);
		if(ruleList != null && !ruleList.isEmpty()) {
			ruleVO = new InstallFeeRuleVO();
			Map<String, List<String>> ruleOutput = null;
			
			if(HSIC.equalsIgnoreCase(productType)) {
				ruleOutput = evaluateRule(RULE_CD_HSICINSTFEE, ruleList);
			}else if(TTV.equalsIgnoreCase(productType)) {
				ruleOutput = evaluateRule(RULE_CD_TTVINSTFEE, ruleList);
			}
			
			ruleVO.setOmsTemplates(ruleOutput.get(RULE_KEY_OMSTEMPLATE_LIST));
			ruleVO.setTermList(ruleOutput.get(RULE_KEY_TERMLIST));
			if(ruleOutput.get(RULE_KEY_INSTALLFEECID) != null) {
				ruleVO.setInstallFeeProductCatalogueId(ruleOutput.get(RULE_KEY_INSTALLFEECID).get(0));
			}
			if(ruleOutput.get(RULE_KEY_INSTALLFEEPARENTCID) != null) {
				ruleVO.setInstallFeeParentProductCatalogueId(ruleOutput.get(RULE_KEY_INSTALLFEEPARENTCID).get(0));
			}
			if(ruleOutput.get(RULE_KEY_INSTALLFEEPRICE) != null) {
				ruleVO.setPrice(Double.valueOf(ruleOutput.get(RULE_KEY_INSTALLFEEPRICE).get(0)));
			}
			
		}
		
		
		return ruleVO;
	}
	
	/*
	 * 
	 *  !!! This method needs to be converted to a generic multi-input and mulkti-output rule evaluation. No time now!!! 
	 */
	private Map<String, List<String>> evaluateRule(String ruleCd, List<BusinessRuleInstanceTypeVO> ruleList) {
		Map<String, List<String>> ruleOutput = new HashMap<String, List<String>>();
		String key = null;
 		for(BusinessRuleInstanceTypeVO rule: ruleList) {
			boolean match = false;
			for(CodeValueTypeVO input : rule.getInput()) {
				if(ruleCd.equalsIgnoreCase(input.getValue().get(0))) {
					match = true;
				}
				if(match && !ruleCd.equalsIgnoreCase(input.getCode())){
					key = input.getValue().get(0);
					for(CodeValueTypeVO output : rule.getOutput()) {
						ruleOutput.put(key, output.getValue());
					}
				}
			}
		}
	
		return ruleOutput;
	}

	private List<BusinessRuleInstanceTypeVO> getBusinessRule(String tableName) {
		IReferencePDSDataServiceAdapter adapter = AdapterFactory.getAdapter(IReferencePDSDataServiceAdapter.class);
		
		String appId = EnterpriseWLNSalesServicesConstants.EWLNSS_APP_ID;
		GetReferencePDSDataServiceAdapterRequest adapterRequest = RefPDSServiceDelegatorTransformer.transformRequest(appId);
		
		GetReferencePDSDataServiceAdapterResponse adapterResponse = null; 
		adapterResponse = adapter.getBusinessRuleInstances(adapterRequest, tableName);
		
		return adapterResponse.getRuleList();
	}

	/**
	 *  If HSIC product transactionType is: upgrade or activation 
			AND TTV product transactionType is activation 
			AND OMS Template is in [omsTemplate]  
			AND HSIC productComponent has a >= rank than the rank of [PLAN] 
			AND TTV committmentTerm is in [TERMLIST] 
		THEN:
			1 - For the list of cartItem.television.rentalEquipmentList filtered by modelName=[RENTMICLIST1] ordered by rentalPrice, set the rentalPrice to $0 for the first [RENTQTY1] items in the list 
			2 - For the list of cartItem.television.rentalEquipmentList (excluding the ones set to $0 above) filtered by modelName=[ RENTMICLIST2] ordered by rentalPrice descending,set the rentalPrice to $0 for the first [RENTQTY2] items in the list

	 * @param list
	 * @param products
	 * @param offerDetailVO 
	 * @param getProductQualificationAdapterResponse 
	 */

	private void applyTtvEquipmentDiscountsRule(List<FFHEquipmentItemVO> equipments, ShoppingCartContextVO scContext, GetSalesOfferDetailResponseVO2 offerDetailVO, GetProductQualificationAdapterResponse getProductQualificationAdapterResponse) {
		final String functionName  = "applyTtvEquipmentDiscountsRule"; 
		logger.debug(functionName, "Checking if TTV Equipment discount pricing rule can be applied.");
		
		// QC70694 - internet product is not in ttv cartItem
		InternetProductTypeVO hsicProducts = null;
		TelevisionProductTypeVO ttvProducts = null;
		
		for (final CartItemVO cartItemElem : scContext.getShoppingCartVO().getCartItemList()) {
			if(cartItemElem.getProducts() != null){
				if(cartItemElem.getProducts().getInternetProduct() != null){
					hsicProducts = cartItemElem.getProducts().getInternetProduct();
				}
				if(cartItemElem.getProducts().getTelevisionProduct() != null){
					ttvProducts = cartItemElem.getProducts().getTelevisionProduct();
				}
			}
		}
		
		//HSIC-> ACT or UPGRADE.
		if( hsicProducts == null || 
				!( EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(hsicProducts.getProductOrderType().getOrderTypeCd()) || EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(hsicProducts.getProductOrderType().getOrderTypeCd()) ) ){
			logger.debug(functionName, " TV EQPT DISC RULE NOT APPLICABLE - HSIC in cart" + (hsicProducts != null)  + " order type = " + (hsicProducts != null ? hsicProducts.getProductOrderType().getOrderTypeCd() : " --" ) + " must be activation or upgrade." );
			return;
		}
		
		//TV -> ACT
		if( ttvProducts == null || 
				!EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(ttvProducts.getProductOrderType().getOrderTypeCd()) ){
			logger.debug(functionName, " TV EQPT DISC RULE NOT APPLICABLE - TV in cart" + (ttvProducts != null)  + " order type = " + (ttvProducts != null ? ttvProducts.getProductOrderType().getOrderTypeCd() : " --" ) + " must be activation." );
			return;
		}
		
		logger.debug(functionName, "TTV Term = " +  ttvProducts.getSelectedContractTermCd() );
		
		//Get config from refpds.
		TTVEquipmentDiscountRuleVO ruleVO = getTTVEquipmentDiscountRuleVO();
		
		if(ruleVO == null) {
			logger.error( " TV EQPT DISC RULE CANNOT BE APPLICED - RefPDS config not found for the rule."  );
			return;
		}
		
		if(!(ruleVO.getTermList() == null || ruleVO.getTermList().contains(ttvProducts.getSelectedContractTermCd()))) {
			logger.debug(functionName, " TV EQPT DISC RULE NOT APPLICABLE - TV  selected term " +  ttvProducts.getSelectedContractTermCd() + " not in configured terms " + ruleVO.getTermList());
			return;
		}
		
		//Offer OMS template is in configured list
		String offerOmsTemplateId = getOfferTemplate(TTV, offerDetailVO);
		if(offerOmsTemplateId == null) {
			logger.error(" TV EQPT DISC RULE CANNOT BE APPLIED - offer oms template not found for TTV."  );
			return;
		}
		if(!ruleVO.getOmsTemplates().contains(offerOmsTemplateId)) {
			logger.debug(functionName, " TV EQPT DISC RULE CANNOT BE APPLIED - offer oms template " + offerOmsTemplateId + " not found in configured list " + ruleVO.getOmsTemplates()  );
			return;
		}
		
		//Selected HSIC PC's ranking is higher than the ranking of configured plan.
		int selectedPlanRank = getRankByCatalogId(hsicProducts.getProductComponents().get(0).getProductCatalogueId(), getProductQualificationAdapterResponse);
		int configuredPlanRank = getRankByCatalogId(ruleVO.getBasePlanProductCatalogueId(), getProductQualificationAdapterResponse);
		if(selectedPlanRank < 0 || configuredPlanRank < 0) {
			logger.error(" TV EQPT DISC RULE CANNOT BE APPLIED - could not get ranks.");
			return;
		}
		if(selectedPlanRank > configuredPlanRank) {
			logger.debug(functionName, " TV EQPT DISC RULE CANNOT BE APPLIED - selected plan with rank " + configuredPlanRank + " is lower than the configured plan " + ruleVO.getBasePlanProductCatalogueId() +" rank " +  configuredPlanRank);
			return;
		}
		
		//Sort equipments by price high to low.
		EquipmentItemPriceComparator eqptPriceComparator = new EquipmentItemPriceComparator();
		Collections.sort(equipments, eqptPriceComparator);
		
		//zero out based on rental list1 and qty
		updateEquipmentPriceByRule(equipments, ruleVO.getRentalMicList1(), ruleVO.getMaxQtyForRentalMicList1());
		
		//zero out based on rental list2 and qty
		updateEquipmentPriceByRule(equipments, ruleVO.getRentalMicList2(), ruleVO.getMaxQtyForRentalMicList2());
	}
	
	private void updateEquipmentPriceByRule(List<FFHEquipmentItemVO> equipments, List<String> micList,
			int maxQty) {
		
		if (maxQty == 0 || micList == null || micList.isEmpty()) {
			// do nothing.
			return;
		}

		ListIterator<FFHEquipmentItemVO> iterator = equipments.listIterator();
		while (iterator.hasNext()) {
			FFHEquipmentItemVO item = iterator.next();
				if ( micList.contains( item.getMaterialItemCode() )) {
					if (maxQty > 0) {
						PriceVO charge = new PriceVO();
						MoneyVO amount = new MoneyVO();
						charge.setBasePriceAmount(amount);
						charge.setPricingTypeCd(item.getEquipmentPrice().getPricingTypeCd());
						charge.setRecurrenceCount(item.getEquipmentPrice().getRecurrenceCount());
						amount.setValueAmt(0f);
						item.setEquipmentPrice(charge);

						maxQty = maxQty - 1;
					}
				}

			iterator.set(item);

		}
		
	}

	private int getRankByCatalogId(String catalogueId, GetProductQualificationAdapterResponse getProductQualificationAdapterResponse) {
		//oms productInternal name
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		CatalogueItemDO catItem = gridHelper.getCatalogueItemById(catalogueId);
		String pqPackCode = WLNOfferUtil.mapOmsCode(catItem.getProductCode());

		for (ProductQualification productQualification : getProductQualificationAdapterResponse.getProductQualificationList()) {
			for (Product product : productQualification.getProductList()) {
				if (pqPackCode.equalsIgnoreCase(product.getProductTierCd())) {
					return product.getProductRanking();
				}
			}
		}
		
		//this means data errors.
		return -999;
	}

	private String getOfferTemplate(String productType, GetSalesOfferDetailResponseVO2 offerDetailVO) {
		for( WirelineOfferProduct wlnOfferProduct : offerDetailVO.getOffer().getOfferProduct().getWirelineOfferProductList() ) {
			if(productType.equals(wlnOfferProduct.getProductTypeCode())) {
				return wlnOfferProduct.getProductTemplateIdentifier().getProductCatalogueId();
			}
			
		}
		return null;
	}

	private TTVEquipmentDiscountRuleVO getTTVEquipmentDiscountRuleVO() {
		TTVEquipmentDiscountRuleVO ruleVO = null; 
		List<BusinessRuleInstanceTypeVO> ruleList = getBusinessRule(EnterpriseWLNSalesServicesConstants.REFPDS_PRCG_RULE_REF);
		if(ruleList != null && !ruleList.isEmpty()) {
			ruleVO = new TTVEquipmentDiscountRuleVO(); 
			Map<String, List<String>> ruleOutput = evaluateRule(RULE_CD_TTVDISC, ruleList);
			ruleVO.setOmsTemplates(ruleOutput.get(RULE_KEY_OMSTEMPLATE_LIST));
			ruleVO.setTermList(ruleOutput.get(RULE_KEY_TERMLIST));
			if(ruleOutput.get(RULE_KEY_PLAN) != null) {
				ruleVO.setBasePlanProductCatalogueId(ruleOutput.get(RULE_KEY_PLAN).get(0));
			}
			ruleVO.setRentalMicList1(ruleOutput.get(RULE_KEY_RENTMICLIST1));
			ruleVO.setRentalMicList2(ruleOutput.get(RULE_KEY_RENTMICLIST2));
			if(ruleOutput.get(RULE_KEY_RENTQTY1) != null) {
				ruleVO.setMaxQtyForRentalMicList1(Integer.valueOf(ruleOutput.get(RULE_KEY_RENTQTY1).get(0)));
			}
			if(ruleOutput.get(RULE_KEY_RENTQTY2) != null) {
				ruleVO.setMaxQtyForRentalMicList2(Integer.valueOf(ruleOutput.get(RULE_KEY_RENTQTY2).get(0)));
			}
			
			
		}
		
		
		return ruleVO;
	}
	

	/***************************************************************************
	 This can be removed once the ProductItemChargeDelegate is used 
	 ***************************************************************************/
	private void applyConstraintPricing(List<ProductItemVO> productItems, GetSalesOfferDetailResponseVO2 salesOfferDetailVO) {
		//Sort Product Items by Price...High to Low. 
		ProductItemPriceComparator priceComparator = new ProductItemPriceComparator();
		Collections.sort(productItems, priceComparator);
		
		List<IncludedServiceConstraint> constraintList = new ArrayList<IncludedServiceConstraint>(); 
		
		//Build all constraints from offer. Do this here to make the code more readble instead of a loops hell :)
		for(WirelineOfferProduct wlnOfferProduct: salesOfferDetailVO.getOffer().getOfferProduct().getWirelineOfferProductList()) {
			for(ProductComponent wlnProductComponent : wlnOfferProduct.getProductComponentList()) {
				constraintList.addAll(wlnProductComponent.getIncludedServiceConstraintList());
			}
		}
		
		//sort constraintList by the size of serviceTypeCodeList (size sort by low to high)
		IncludedConstraintListComparator constraintListComparator = new IncludedConstraintListComparator();
		Collections.sort(constraintList, constraintListComparator);
		for(IncludedServiceConstraint serviceConstraint : constraintList) {
			updateProductPricingByConstraintRule(serviceConstraint, productItems);
		}
	}

	/***************************************************************************
	 This can be removed once the ProductItemChargeDelegate is used 
	 ***************************************************************************/
	private void updateProductPricingByConstraintRule(IncludedServiceConstraint serviceConstraint,
			List<ProductItemVO> productItems) {

		if (serviceConstraint.getMaxItemQty() == null) {
			// do nothing.
			return;
		}
		
		int maxQty = serviceConstraint.getMaxItemQty().intValue();
		
		/*
		 *  Tier 1 rules:  
			When TOM.productComponent in the offer includes extCID: 40671421 TELUS Home Phone Lite (Forborne) (V1)
				If ES cart has carryover or added Feature with extCID 45 Call Display then set inpackInd=Yes / $0
			When TOM.productComponent in the offer includes extCID: 55064 Home Connections Bundle 3
				If ES cart has carryover or added Feature with extCID 45 Call Display then set inpackInd=Yes / $0
			When TOM.productComponent is anything other than extCID 32628 Local Line 
				If ES cart has carryover or added Feature with extCID 48 Call Return then set inpackInd=Yes / $0
				If ES cart has carryover or added Feature with extCID 55 3 Way Calling then set inpackInd=Yes / $0
		 */
		
		ListIterator<ProductItemVO> iterator = productItems.listIterator();
		while (iterator.hasNext()) {
			boolean impactPricing = false;
			ProductItemVO item = iterator.next();
			if (getProductItem(productItems, TELUS_HOME_PHONE_LITE_EXTERNAL_CID) != null
					|| getProductItem(productItems, TELUS_HOME_CONNECTIONS_BUNDLE3_EXTERNAL_CID) != null) {
				if (SL_FEATURE_CALL_DISPLAY_EXTERNAL_CID.equals(item.getProductItemIdentifier().getExternalId())) {
					impactPricing = true;
				}

			} else if (getProductItem(productItems, LOCAL_LINE_EXTERNAL_CID) != null) {
				if (SL_FEATURE_CALL_RETURN_EXTERNAL_CID.equals(item.getProductItemIdentifier().getExternalId())) {
					impactPricing = true;
				}

				if (SL_FEATURE_3WAY_CALLING_EXTERNAL_CID.equals(item.getProductItemIdentifier().getExternalId())) {
					impactPricing = true;
				}

			}
			if (impactPricing) {
				if (maxQty > 0) {
					PriceVO charge = new PriceVO();
					MoneyVO amount = new MoneyVO();
					charge.setBasePriceAmount(amount);
					charge.setPricingTypeCd(item.getProductItemPriceCharge().getPricingTypeCd());
					charge.setRecurrenceCount(item.getProductItemPriceCharge().getRecurrenceCount());
					amount.setValueAmt(0f);
					item.setProductItemPriceCharge(charge);

					maxQty = maxQty - 1;
				}
			}

			iterator.set(item);
		}

		//TIER 2 
		
		iterator = productItems.listIterator();
		while (iterator.hasNext()) {

			ProductItemVO item = iterator.next();

			if (item.getSalesCategoryServiceTypeCd() != null) {  //handle "PICK_ELIG", "MORE_ELIG" senario
				if (!Boolean.TRUE.equals(item.getPriceValueSetFinishedInd())) {
					if (serviceConstraint.getServiceTypeCodeList().contains(item.getSalesCategoryServiceTypeCd())
							&& (item.isEligibleForMinMaxPricingInd() != null && item.isEligibleForMinMaxPricingInd())) {
						if (maxQty > 0) {
							PriceVO charge = new PriceVO();
							MoneyVO amount = new MoneyVO();
							charge.setBasePriceAmount(amount);
							charge.setPricingTypeCd(item.getProductItemPriceCharge().getPricingTypeCd());
							charge.setRecurrenceCount(item.getProductItemPriceCharge().getRecurrenceCount());
							amount.setValueAmt(0f);
							item.setProductItemPriceCharge(charge);

							item.setPriceValueSetFinishedInd(Boolean.TRUE);

							maxQty = maxQty - 1;
						}
					}
				}
			} else {
				String parentServiceTypeCode = getServiceType(
						item.getProductItemIdentifier().getParentProductCatalogueId());
				if (parentServiceTypeCode != null) {
					if (serviceConstraint.getServiceTypeCodeList().contains(parentServiceTypeCode)
							&& (item.isEligibleForMinMaxPricingInd() != null && item.isEligibleForMinMaxPricingInd())) {
						if (maxQty > 0) {
							PriceVO charge = new PriceVO();
							MoneyVO amount = new MoneyVO();
							charge.setBasePriceAmount(amount);
							charge.setPricingTypeCd(item.getProductItemPriceCharge().getPricingTypeCd());
							charge.setRecurrenceCount(item.getProductItemPriceCharge().getRecurrenceCount());
							amount.setValueAmt(0f);
							item.setProductItemPriceCharge(charge);

							maxQty = maxQty - 1;
						}
					}
				}
			}
			iterator.set(item);
		}
	}
	
	private ProductItemVO getProductItem(List<ProductItemVO> productItems, String extCatId) {
		for (ProductItemVO prodItem : productItems) {
			if(extCatId.equals(prodItem.getProductItemIdentifier().getExternalId())) {
				return prodItem;
			}
				
		}
		return null;
	}

	/***************************************************************************
	 This can be removed once the ProductItemChargeDelegate is used 
	 ***************************************************************************/
	private String getServiceType(String productcatalogId) {
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		CatalogueItemDO result = gridHelper.getCatalogueItemById(productcatalogId);
		if ( result != null ) {
			return result.getComponentServiceType();
		}
		return null;
		
	}

	private HomePhoneProductPriceVO getSingProductPrice(final CartItemVO cartItem, final ShoppingCartContextVO scContext) {
		final HomePhoneProductPriceVO result = new HomePhoneProductPriceVO();
		
		final HomePhoneProductTypeVO ciSingVO = cartItem.getProducts().getHomePhoneProduct();
		if (ciSingVO != null) {
			// components, addon, discounts, sweetener discounts.
			final List<ProductItemVO> ciPiList = buildProductItems(ciSingVO.getProductComponents(), ciSingVO.getAddOns(), ciSingVO.getDiscounts(), ciSingVO.getSweeteners(), 
					ciSingVO.getAdditionalProductItemList(), ciSingVO.getFeatures());
			//Install Credit Sweetener.
			final ProductItemVO installCreditProductItem = buildInstallCredit(SING, ciSingVO.getAppointmentDetail(), scContext); 
			if (installCreditProductItem != null) {
				ciPiList.add(installCreditProductItem);
			}
			// Equipments
			final List<FFHEquipmentItemVO> ciEquipList = transformCiEquipments(ciSingVO.getEquipments());
			Map<String, CatalogueItemDO> productCataloguesFromGrid = WirelineProductHelper.getProductIdsFromGrid(scContext.getAssignedProducts());
			final WirelineProductHelper helper = new WirelineProductHelper();
			final List<ProductItemVO> allPiList = helper.getAssignedProductItems(scContext, ciPiList, SING, productCataloguesFromGrid);
			final List<FFHEquipmentItemVO> assgnEquipList = helper.getAssignedEquipments(scContext, SING, false, productCataloguesFromGrid);
			
			final List<FFHEquipmentItemVO> allEquipList = mergeEquipments(ciEquipList, assgnEquipList);

			final ProductItemDelegate piDelegate = new ProductItemDelegate();
			final ProductItemDelegateResponse piDelegateResp = piDelegate.populateProductItemsForPrice(SING, cartItem, allPiList, allEquipList, scContext);
			
			if (isEstimatedPricing(scContext)) {
				/********************************************************************************/
				/*********** This can be removed once the ProductItemChargeDelegate *************/
				final GetSalesOfferDetailResponseVO2 offerDetailVO = scContext.getOfferByCartItemOfferId(cartItem.getProductMarketOffering().getOfferHeader().getOfferId());
				applyConstraintPricing(piDelegateResp.getProductItems(), offerDetailVO);
				/********************************************************************************/
				
				/*********************************************************************************
				* Switch to use the ProductItemChargeDelegate at some point
				ProductItemChargeDelegate productItemChargeDelegate = new ProductItemChargeDelegate();
				productItemChargeDelegate.applyConstraintPricing(EnterpriseWLNSalesServicesConstants.SING, piDelegateResp.getProductItems(), scContext);
				**********************************************************************************/
			}
			// apply grouping
			final ProductGroupCategorizationDelegate grpCategDelegate = new ProductGroupCategorizationDelegate();
			final List<ProductItemGroupCategoryVO> pigCategList = grpCategDelegate.groupProductItems(piDelegateResp.getProductItems());
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap = new HashMap<Map<Set<Integer>, String>, PriceDurationVO>();
			final List<ProductItemGroupCategoryPriceVO> piGrpPriceList = getPiGrpPriceList(pigCategList, prodMap,SING, false);

			final List<WirelineEquipmentPriceVO> equipmentPriceList = getEquipPriceList(piDelegateResp.getEquipmentItems(), prodMap,SING, false);

			result.setProductItemGroupCategoryPrice(piGrpPriceList);
			result.setEquipmentPrice(equipmentPriceList);
			result.setEstimatedPriceInd(true);

			final CombinedPriceVO priceSummary = calculateProdPrice(prodMap,SING);
			result.setPriceSummary(priceSummary);
		}

		return result;
	}

	/*
	 * Build list of product items which can be component, add-on, discount,
	 * sweetener.
	 */
	private List<ProductItemVO> buildProductItems(final List<ProductComponentVO> productComponents,
			final List<FFHProductPlanAddOnTypeVO> addOns, final List<FFHDiscountTypeVO> discounts,
			final List<FFHSweetenerTypeVO> sweeteners, final List<AdditionalProductItemTypeVO> others, final List<FFHFeatureTypeVO> features) {
		final List<ProductItemVO> result = new ArrayList<ProductItemVO>();

		final List<ProductItemVO> componentProductItems = buildProductComponentProductItems(productComponents);
		if (!componentProductItems.isEmpty()) {
			result.addAll(componentProductItems);
		}

		final List<ProductItemVO> addOnProductItems = buildAddOnProductItems(addOns);
		if (!addOnProductItems.isEmpty()) {
			result.addAll(addOnProductItems);
		}

		final List<ProductItemVO> discountProductItems = buildDiscountProductItems(discounts);
		if (!discountProductItems.isEmpty()) {
			result.addAll(discountProductItems);
		}

		final List<ProductItemVO> sweetenerProductItems = buildSweetenerProductItems(sweeteners);
		if (!sweetenerProductItems.isEmpty()) {
			result.addAll(sweetenerProductItems);
		}

		final List<ProductItemVO> otherList = buildOtherProductItems(others);
		if (!otherList.isEmpty()) {
			result.addAll(otherList);
		}

		final List<ProductItemVO> featureProductItems = buildFeaturesProductItems(features);
		if (!featureProductItems.isEmpty()) {
			result.addAll(featureProductItems);
		}

		return result;
	}
	
	private List<ProductItemVO> buildProductItems(final List<ProductComponentVO> productComponents,
			final List<FFHProductPlanAddOnTypeVO> addOns, final List<FFHDiscountTypeVO> discounts,
			final List<FFHSweetenerTypeVO> sweeteners, final List<AdditionalProductItemTypeVO> others) {
		return buildProductItems(productComponents, addOns, discounts, sweeteners, others, null);
	}

	/*
	 * Build list of FFH Equipment Items.
	 */
	private List<FFHEquipmentItemVO> transformCiEquipments(final List<FFHEquipmentTypeVO> ciEquipments) {
		final List<FFHEquipmentItemVO> result = new ArrayList<FFHEquipmentItemVO>();
		if (ciEquipments != null && !ciEquipments.isEmpty()) {
			for (final FFHEquipmentTypeVO elem : ciEquipments) {
				FFHEquipmentItemVO equipment = new FFHEquipmentItemVO();
				ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
				itemIdentifier.setParentProductCatalogueId(elem.getProductCatalogueIdentifier().getParentProductCatalogueId());
				itemIdentifier.setProductCatalogueId(elem.getProductCatalogueIdentifier().getProductCatalogueId());
				equipment.setMaterialItemCode(elem.getMaterialItemCode());
				equipment.setProductItemIdentifier(itemIdentifier);

				// NWLN-10248 - If finance get payOptionTerm from shopping cart and set to Equipment
				if (elem.getPaymentOption() != null && elem.getPaymentOption().getPayOptionType().equalsIgnoreCase(FINANCE)) {
					String paymentOptionTerm = elem.getPaymentOption().getPayOptionTerm();
					logger.debug("transformCiEquipments", "equipment has Finance payment option with numberOfInstallments= " + paymentOptionTerm);
					PriceVO price = new PriceVO();
					InstallmentOptionVO installmentOption = new InstallmentOptionVO();
					installmentOption.setNumberOfInstallments(Integer.valueOf(paymentOptionTerm));
					price.setInstallmentOptions(Arrays.asList(installmentOption));
					equipment.setEquipmentPrice(price);
				}

				// equipment.setAcquisitionType(cartEquipmentItem.getAcquisitionType());
				result.add(equipment);
			}
		}

		return result;
	}

	private List<ProductItemVO> buildSweetenerProductItems(final List<FFHSweetenerTypeVO> sweeteners) {
		final List<ProductItemVO> result = new ArrayList<ProductItemVO>();
		if (sweeteners != null) {
			for (final FFHSweetenerTypeVO sweetenerElem : sweeteners) {
				if (sweetenerElem.getDiscounts() != null) {
					for (final FFHDiscountTypeVO discountElem : sweetenerElem.getDiscounts()) {
						// Discount can have multiple product component identifiers. We need to split
						// them into individual product items.
						if (discountElem.getProductCatalogueIdentifiers() != null) {
							for (final ProductComponentVO discountCompElem : discountElem.getProductCatalogueIdentifiers()) {
								ProductItemVO productItemVO = new ProductItemVO();
								ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
								MarketOfferClassificationVO offerClassification = new MarketOfferClassificationVO();
								productItemVO.setProductItemIdentifier(itemIdentifier);
								productItemVO.setMarketOfferClassification(offerClassification);

								itemIdentifier.setParentProductCatalogueId(discountCompElem.getParentProductCatalogueId());
								itemIdentifier.setProductCatalogueId(discountCompElem.getProductCatalogueId());

								offerClassification.setSweetenerInd(true);

								productItemVO.setAction(discountCompElem.getAction());
								
								result.add(productItemVO);
							}
						}
					}
				}
			}
		}

		return result;
	}

	private List<ProductItemVO> buildDiscountProductItems(final List<FFHDiscountTypeVO> discounts) {
		final List<ProductItemVO> result = new ArrayList<ProductItemVO>();
		if (discounts != null) {
			for (final FFHDiscountTypeVO discountElem : discounts) {
				// Discount can have multiple product component identifiers. We need to split
				// them into individual product items.
				if (discountElem.getProductCatalogueIdentifiers() != null) {
					for (final ProductComponentVO discountCompElem : discountElem.getProductCatalogueIdentifiers()) {
						ProductItemVO productItemVO = new ProductItemVO();
						ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
						MarketOfferClassificationVO offerClassification = new MarketOfferClassificationVO();
						productItemVO.setProductItemIdentifier(itemIdentifier);
						productItemVO.setMarketOfferClassification(offerClassification);

						itemIdentifier.setParentProductCatalogueId(discountCompElem.getParentProductCatalogueId());
						itemIdentifier.setProductCatalogueId(discountCompElem.getProductCatalogueId());

						offerClassification.setDiscountInd(true);
						
						productItemVO.setAction(discountCompElem.getAction());

						result.add(productItemVO);
					}
				}
			}
		}

		return result;
	}

	private List<ProductItemVO> buildAddOnProductItems(final List<FFHProductPlanAddOnTypeVO> addOns) {
		final List<ProductItemVO> result = new ArrayList<ProductItemVO>();
		if (addOns != null) {
			for (final FFHProductPlanAddOnTypeVO elem : addOns) {
				ProductItemVO productItemVO = new ProductItemVO();
				ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
				MarketOfferClassificationVO offerClassification = new MarketOfferClassificationVO();
				productItemVO.setProductItemIdentifier(itemIdentifier);
				productItemVO.setMarketOfferClassification(offerClassification);

				itemIdentifier.setParentProductCatalogueId(elem.getProductCatalogueIdentifier().getParentProductCatalogueId());
				itemIdentifier.setProductCatalogueId(elem.getProductCatalogueIdentifier().getProductCatalogueId());

				offerClassification.setAddOnInd(true);

				productItemVO.setAction(elem.getProductCatalogueIdentifier().getAction());
				
				result.add(productItemVO);
			}
		}

		return result;
	}

	private List<ProductItemVO> buildFeaturesProductItems(final List<FFHFeatureTypeVO> features) {
		final List<ProductItemVO> result = new ArrayList<ProductItemVO>();
		if (features != null) {
			for (final FFHFeatureTypeVO elem : features) {
				ProductItemVO productItemVO = new ProductItemVO();
				ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
				MarketOfferClassificationVO offerClassification = new MarketOfferClassificationVO();
				productItemVO.setProductItemIdentifier(itemIdentifier);
				productItemVO.setMarketOfferClassification(offerClassification);

				itemIdentifier.setParentProductCatalogueId(elem.getProductCatalogueIdentifier().getParentProductCatalogueId());
				itemIdentifier.setProductCatalogueId(elem.getProductCatalogueIdentifier().getProductCatalogueId());

				offerClassification.setCallingFeatureInd(true);

				productItemVO.setAction(elem.getProductCatalogueIdentifier().getAction());
				
				result.add(productItemVO);
			}
		}

		return result;
	}

	private List<ProductItemVO> buildProductComponentProductItems(final List<ProductComponentVO> productComponents) {
		final List<ProductItemVO> result = new ArrayList<ProductItemVO>();
		if (productComponents != null) {
			for (final ProductComponentVO elem : productComponents) {
				ProductItemVO productItemVO = new ProductItemVO();
				ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
				MarketOfferClassificationVO offerClassification = new MarketOfferClassificationVO();
				productItemVO.setProductItemIdentifier(itemIdentifier);
				productItemVO.setMarketOfferClassification(offerClassification);

				itemIdentifier.setParentProductCatalogueId(elem.getParentProductCatalogueId());
				itemIdentifier.setProductCatalogueId(elem.getProductCatalogueId());

				offerClassification.setProductComponentInd(true);

				productItemVO.setAction(elem.getAction());
				
				result.add(productItemVO);
			}
		}

		return result;
	}

	private List<ProductItemVO> buildOtherProductItems(final List<AdditionalProductItemTypeVO> param) {
		final List<ProductItemVO> result = new ArrayList<ProductItemVO>();
		if (param != null) {
			for (final AdditionalProductItemTypeVO elem : param) {
				ProductItemVO productItemVO = new ProductItemVO();
				ProductItemIdentifierVO itemIdentifier = new ProductItemIdentifierVO();
				productItemVO.setProductItemIdentifier(itemIdentifier);

				itemIdentifier.setParentProductCatalogueId(elem.getProductCatalogueIdentifier().getParentProductCatalogueId());
				itemIdentifier.setProductCatalogueId(elem.getProductCatalogueIdentifier().getProductCatalogueId());

				productItemVO.setAction(elem.getProductCatalogueIdentifier().getAction());
				
				result.add(productItemVO);
			}
		}

		return result;
	}

	private List<WirelineEquipmentPriceVO> getEquipPriceList(final List<FFHEquipmentItemVO> list,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,String productType, boolean isLiketoLikeRecontract) {
		final List<WirelineEquipmentPriceVO> result = new ArrayList<WirelineEquipmentPriceVO>();
		populateEquipPriceList(result, list, prodMap,productType, isLiketoLikeRecontract);
		return result;
	}

	/**
	 * @param result
	 * @param equipList
	 */
	private void populateEquipPriceList(final List<WirelineEquipmentPriceVO> result, List<FFHEquipmentItemVO> equipList,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,String productType, boolean isLiketoLikeRecontract) {
		for (final FFHEquipmentItemVO elem : equipList) {
			if (elem.isExisting() == false || elem.isCarryOver()) {
				WirelineEquipmentPriceVO equipPriceVO = new WirelineEquipmentPriceVO();
				equipPriceVO.setMaterialItemCode(elem.getMaterialItemCode());
	
				ProductItemPriceVO equipmentPrice = getEquipmentPiPriceVO(elem, prodMap,productType, isLiketoLikeRecontract);
				equipPriceVO.setEquipmentPrice(equipmentPrice);
	
				result.add(equipPriceVO);
			}
		}
	}

	private ProductItemPriceVO getEquipmentPiPriceVO(final FFHEquipmentItemVO equipItem,
			final Map<Map<Set<Integer>, String>, PriceDurationVO> prodMap,String productType, boolean isLiketoLikeRecontract) {
		final ProductItemPriceVO result = new ProductItemPriceVO();

		if (equipItem.getProductItemIdentifier() != null) {
			result.setProductItemIdentifier(getIdentifier(equipItem.getProductItemIdentifier()));
		}
		if (equipItem.getEquipmentDescription() != null) {
			result.setProductItemDescription(getDesc(equipItem.getEquipmentDescription()));
		}
		if (equipItem.getAssociatedProductItems() != null) {
			result.setAssociatedProductItemPrice(getAiPriceList(equipItem.getAssociatedProductItems(), prodMap, null,productType, isLiketoLikeRecontract));
		}

		final PriceVO charge = equipItem.getEquipmentPrice();
		final PriceDiscountVO discount = equipItem.getEquipmentPriceDiscount();

		result.setPriceSummary(getPriceSummary(charge, discount, prodMap, null,productType, isLiketoLikeRecontract));
		
		return result;
	}
	
	private List<FFHEquipmentItemVO> mergeEquipments(final List<FFHEquipmentItemVO> ciEquipList, final List<FFHEquipmentItemVO> assignedEquipList) {
		final List<FFHEquipmentItemVO> result = new ArrayList<FFHEquipmentItemVO>();
		
		if (CollectionUtils.isEmpty(ciEquipList)) {
			if (assignedEquipList != null) { 
				result.addAll(assignedEquipList);
			}
		} else if (CollectionUtils.isEmpty(assignedEquipList)) {
			if (ciEquipList != null) {
				result.addAll(ciEquipList);
			}
		} else {
			final Map<String, FFHEquipmentItemVO> ciMap = new HashMap<String, FFHEquipmentItemVO>();
			final Map<String, FFHEquipmentItemVO> assgnMap = new HashMap<String, FFHEquipmentItemVO>();
			
			for (final FFHEquipmentItemVO elem : ciEquipList) {
				ciMap.put(elem.getMaterialItemCode(), elem);
			}
			
			for (final FFHEquipmentItemVO elem : assignedEquipList) {
				assgnMap.put(elem.getMaterialItemCode(), elem);
			}

			final Map<String, FFHEquipmentItemVO> mergedMap = new HashMap<String, FFHEquipmentItemVO>();
			mergedMap.putAll(ciMap);
			mergedMap.putAll(assgnMap);
			
			result.addAll(mergedMap.values());
		}
		
		return result;
	}
	
	//NWLN-7957 find all the existing gift in shopping cart and then find the incompatible price plan for those gift in offer response
	public static List<ProductCatalogueIdentifier> getInCompatibleDiscountList(ShoppingCartContextVO shoppingCartContextVO) {
		List<ProductCatalogueIdentifier> inCompatibleDiscountList = new ArrayList<ProductCatalogueIdentifier>();
		List<RelatedImmediatePromotionVO> existingGWPPromoList = new ArrayList<RelatedImmediatePromotionVO>();
		
		List<CartItemVO> cartItemList = new ArrayList<CartItemVO>();
		if (shoppingCartContextVO != null && shoppingCartContextVO.getShoppingCartVO() != null) {
			cartItemList = shoppingCartContextVO.getShoppingCartVO().getCartItemList();
			if (cartItemList != null) {
				for (CartItemVO cartItem : cartItemList) {
					if (cartItem != null && cartItem.isGwpOrderItem()) {
						if (cartItem.getRelatedPromotionList()!= null && cartItem.getRelatedPromotionList().size()>0){
							existingGWPPromoList.addAll(cartItem.getRelatedPromotionList());
						}
					}
				}
			}
		}

		if (existingGWPPromoList != null && existingGWPPromoList.size() > 0
				&& shoppingCartContextVO.getAvailableAccessoryResponse() != null
				&& shoppingCartContextVO.getAvailableAccessoryResponse().getAccessoryOfferSummaryList() != null
				&& shoppingCartContextVO.getAvailableAccessoryResponse().getAccessoryOfferSummaryList().size() > 0) {
			for (RelatedImmediatePromotionVO cartPromoVO : existingGWPPromoList) {
				if (StringUtils.isNotBlank(cartPromoVO.getId())) {
					for (AccessoryOffer offer : shoppingCartContextVO.getAvailableAccessoryResponse()
							.getAccessoryOfferSummaryList()) {
						if (cartPromoVO.getId().equalsIgnoreCase(offer.getPromotionId()) && offer.getOfferProduct()
								.getAccessoryOfferProduct().getIncompatibleDiscountIdList() != null) {
							inCompatibleDiscountList.addAll(
									offer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList());
						}
					}
				}
			}
		}
		return inCompatibleDiscountList;
	}

	
	//NWLN-7815 find all the discounts in shopping cart
	public static List<FFHDiscountTypeVO> getExistingDiscountListInShoppingCart(ShoppingCartContextVO shoppingCartContextVO, String productType) {
		List<FFHDiscountTypeVO> existingDiscountList = new ArrayList<FFHDiscountTypeVO>();
		
		List<CartItemVO> cartItemList = new ArrayList<CartItemVO>();
		if (shoppingCartContextVO != null && shoppingCartContextVO.getShoppingCartVO() != null) {
			cartItemList = shoppingCartContextVO.getShoppingCartVO().getCartItemList();
			if (cartItemList != null) {
				for (CartItemVO cartItem : cartItemList) {
					if (cartItem != null) {
						if (cartItem.getProducts() != null){
							if (productType.equalsIgnoreCase(HSIC) && cartItem.getProducts().getInternetProduct() != null) {
								if (cartItem.getProducts().getInternetProduct().getDiscounts() != null) {
									existingDiscountList.addAll(cartItem.getProducts().getInternetProduct().getDiscounts());
								}
							}
							if (productType.equalsIgnoreCase(SING) && cartItem.getProducts().getHomePhoneProduct() != null) {
								if (cartItem.getProducts().getHomePhoneProduct().getDiscounts() != null) {
									existingDiscountList.addAll(cartItem.getProducts().getHomePhoneProduct().getDiscounts());
								}
							}
							if ((productType.equalsIgnoreCase(TTV) || productType.equalsIgnoreCase(STV)) && cartItem.getProducts().getTelevisionProduct() != null) {
								if (cartItem.getProducts().getTelevisionProduct().getDiscounts() != null) {
									existingDiscountList.addAll(cartItem.getProducts().getTelevisionProduct().getDiscounts());
								}
							}
						}
					}
				}
			}
		}		
		return existingDiscountList;
	}
	/**
	 * 
	 * @param discount
	 * @param shoppingCartContextVO
	 * @return
	 */
	public static boolean isDiscountInShoppingCart(Discount discount, ShoppingCartContextVO shoppingCartContextVO) {
		List<CartItemVO> cartItemList = new ArrayList<CartItemVO>();
		if (shoppingCartContextVO != null && shoppingCartContextVO.getShoppingCartVO() != null) {
			cartItemList = shoppingCartContextVO.getShoppingCartVO().getCartItemList();
			if (cartItemList != null) {
				for (CartItemVO cartItem : cartItemList) {
					if (cartItem != null) {
						if (cartItem.getProducts() != null){
							if (cartItem.getProducts().getInternetProduct() != null) {
								List<FFHDiscountTypeVO> addedDiscounts = cartItem.getProducts().getInternetProduct().getDiscounts();
								if (addedDiscounts != null) {
									for (FFHDiscountTypeVO addedDiscount : addedDiscounts) {
										if (addedDiscount.getDiscountCode().equalsIgnoreCase(discount.getDiscountCode())) return true;
									}
								}
							}
							if (cartItem.getProducts().getHomePhoneProduct() != null) {
								List<FFHDiscountTypeVO> addedDiscounts = cartItem.getProducts().getHomePhoneProduct().getDiscounts();
								if (addedDiscounts != null) {
									for (FFHDiscountTypeVO addedDiscount : addedDiscounts) {
										if (addedDiscount.getDiscountCode().equalsIgnoreCase(discount.getDiscountCode())) return true;
									}
								}
							}
							if (cartItem.getProducts().getTelevisionProduct() != null) {
								List<FFHDiscountTypeVO> addedDiscounts = cartItem.getProducts().getTelevisionProduct().getDiscounts();
								if (addedDiscounts != null) {
									for (FFHDiscountTypeVO addedDiscount : addedDiscounts) {
										if (addedDiscount.getDiscountCode().equalsIgnoreCase(discount.getDiscountCode())) return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean hasAssignedAndPendingService(ShoppingCartContextVO request,
			CartItemVO cartItemVO) {
		List<SubscribedProductInfoRestVO> subscribedProductInfoRestVOList = new ArrayList<SubscribedProductInfoRestVO>();
		
		if(request.getAssignedAndPendingProductResponseVO() != null
				&& request.getAssignedAndPendingProductResponseVO().getSubscribedProductList() != null) {
			ServiceAddressVO serviceAddressVO = new ServiceAddressVO();
			serviceAddressVO.setServiceAddressId(request.getServiceAddressResponseVO().getServiceAddress().getAddressId());
			serviceAddressVO.setCityCode(request.getServiceAddressResponseVO().getServiceAddress().getMunicipalityName());
			serviceAddressVO.setProvinceCode(request.getServiceAddressResponseVO().getServiceAddress().getProvinceStateCode());
			
			List<SubscribedServiceIdentifierVO> subscribedServiceIdentifierVOList = new ArrayList<SubscribedServiceIdentifierVO>();

			for(SubscribedServiceVO existingServiceIdentifier : cartItemVO.getExistingServiceIdentifier()) {
				SubscribedServiceIdentifierVO subscribedServiceIdentifierVO = new SubscribedServiceIdentifierVO();
				subscribedServiceIdentifierVO.setServiceId(existingServiceIdentifier.getServiceId());
				subscribedServiceIdentifierVO.setServiceReferenceId(existingServiceIdentifier.getServiceReferenceId());
				
				subscribedServiceIdentifierVOList.add(subscribedServiceIdentifierVO);
			}

			subscribedProductInfoRestVOList = request.getAssignedAndPendingProductResponseVO().getAssignedProductListByServiceAddressAndServiceId(serviceAddressVO, subscribedServiceIdentifierVOList);
		}
		return subscribedProductInfoRestVOList.size() > 0;
	}
	
	private boolean hasProductInCart(ShoppingCartContextVO request, String productType) {

		if(request.getShoppingCartVO() != null) {
			if(request.getShoppingCartVO().getCartItemList() != null) {
				for(CartItemVO cartItem: request.getShoppingCartVO().getCartItemList()) {
					if(cartItem.getProducts() != null) {
						if(SING.equalsIgnoreCase(productType) && cartItem.getProducts().getHomePhoneProduct() != null) {
							return true;
						}
						if(HSIC.equalsIgnoreCase(productType) && cartItem.getProducts().getInternetProduct() != null) {
							return true;
						}
						if(TTV.equalsIgnoreCase(productType) && cartItem.getProducts().getTelevisionProduct() != null) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
}
