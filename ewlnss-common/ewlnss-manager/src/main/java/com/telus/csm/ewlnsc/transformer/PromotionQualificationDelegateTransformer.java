package com.telus.csm.ewlnsc.transformer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.ObjectMapper;

import com.telus.csm.cpq.rest.domain.Characteristic;
import com.telus.csm.cpq.rest.domain.LocaleDescription;
import com.telus.csm.cpq.rest.domain.ProductCharacteristic;
import com.telus.csm.cpq.rest.domain.ProductOffering;
import com.telus.csm.cpq.rest.domain.ProductSpecificationRef;
import com.telus.csm.cpq.rest.domain.PromoCodeRef;
import com.telus.csm.cpq.rest.domain.Promotion;
import com.telus.csm.cpq.rest.domain.PromotionAction;
import com.telus.csm.cpq.rest.domain.PromotionCriteria;
import com.telus.csm.cpq.rest.domain.PromotionCriteriaGroup;
import com.telus.csm.cpq.rest.domain.PromotionGroup;
import com.telus.csm.cpq.rest.domain.PromotionPattern;
import com.telus.csm.cpq.rest.domain.PromotionQualification;
import com.telus.csm.cpq.rest.domain.PromotionQualificationItem;
import com.telus.csm.ewlnsc.domain.ProductPromotionDiscountsVO;
import com.telus.csm.ewlnsc.domain.PromoCodeRefVO;

import com.telus.csm.ewlnsc.domain.PromotionGroupVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryGift;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryOfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Category;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductEligiblity;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Program;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.TransactionType;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.AccessoryOffer;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.GiftDiscountPaymentPlan;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferCategory;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.OfferHeader;


public class PromotionQualificationDelegateTransformer {
	public static String PROMOTION_ENFORCEMENT_TYPE_MANDATORY_UNLIMITED="MANDATORY_UNLIMITED";
	public static String DISCOUNT_TYPE_CODE_D = "D";
	public static final String PROMOTION_TYPE_GWP="GWP";
	public static final String PROMOTION_TYPE_REGULAR="REGULAR";
	public static final String PROMOTION_TYPE_MANUAL="MANUAL";
	public static final String TYPE_CODE_GWP="ACCESSORY_WLN_GWP";
	public static final String INSTALLATION = "installation";
	public static final String PRODUCT = "product";
	public static final String INTERNAL_SYSTEM_ID="10289";//cmdb id 13573(TOM), 10289(PODS), ?(FIFA), 1234(OMS)
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(PromotionQualificationDelegateTransformer.class);

	private PromotionQualificationDelegateTransformer() {}
	private static ObjectMapper mapper = new ObjectMapper();
	public static void populateDiscountToList(Promotion promotion, Map<String, ProductPromotionDiscountsVO> productPromotionDiscountsOferMap
												, List<ProductOffering> responseProductOfferingList, List<ProductOffering> requestProductOfferingList, PromotionQualification reqPromotionQualification, HashMap<String, String> orderTypeMap ) {
		final String functionName = "populateDiscountToList";
		logger.debug(functionName, "inside "+ functionName);
		logger.info(functionName, "Transofming to discount , promotion id="+ promotion.getId());
		Discount discount = new Discount();
		try {
			List<ProductOffering> mappedProductOfferingList = getMappedProductOfferingList(responseProductOfferingList,requestProductOfferingList);
			for( PromotionPattern pattern : promotion.getPattern()) {
				//TTV,SING, HSIC, STV, GWP, CPE?
				List<Discount> selectedList = null;
				for(ProductOffering promoProductOffering: mappedProductOfferingList) {
					String targetProductType= pattern.getProductType();
					ProductPromotionDiscountsVO productPromotionDiscountsVO = productPromotionDiscountsOferMap.get(promoProductOffering.getId());
						if( productPromotionDiscountsVO == null) {
							productPromotionDiscountsVO = new ProductPromotionDiscountsVO();
							productPromotionDiscountsOferMap.put(promoProductOffering.getId(), productPromotionDiscountsVO);
						}
						
						if("TTV".equals(targetProductType)){
							selectedList = productPromotionDiscountsVO.getTtvPromotionDiscountList();
						}
						else if("HSIC".equals(targetProductType)){
							selectedList = productPromotionDiscountsVO.getHsicPromotionDiscountList();
						}
						else if("SING".equals(targetProductType)){
							selectedList = productPromotionDiscountsVO.getSingPromotionDiscountList();
						}
						// NWLN-10301 load CPE discount from PQ 
						else if("CPE".equals(targetProductType)){
							selectedList = productPromotionDiscountsVO.getCpePromotionDiscountList();
						}
					}
	//			}
				if(selectedList != null) {
					selectedList.add(discount);
					populateDiscountDetails(discount, promotion, pattern, orderTypeMap);
					populateTransactionTypeAndContractTerm(discount,pattern.getProductType(),reqPromotionQualification);
					discount.setPromotionGroup(transform(promotion.getPromotionGroup()));
					discount.setPromoCodeRef(transform(promotion.getPromoCodeRef()));
					discount.setPromotionId(promotion.getId());
					discount.setPromotionPerspectiveDate(promotion.getPerspectiveDate().toDate());
					logger.debug(functionName, toJasonString(discount));
				}
				else {
					logger.info(functionName, "ignore promotion as productType="+ pattern.getProductType());
				}
			}
		}catch(Exception ex) {
			logger.info(functionName, ex.getMessage());
		}
		logger.debug(functionName, "exit "+ functionName);
	}

	private static void populateTransactionTypeAndContractTerm(Discount discount, String targetProductTypeCd,	PromotionQualification reqPromotionQualification) {
		final String functionName = "populateTransactionTypeAndContractTerm";
		String transactionTypeCode = null;
		int contractTerm = -1;
		if( reqPromotionQualification.getPromotionQualificationItem() != null) {
			for(PromotionQualificationItem pqItem : reqPromotionQualification.getPromotionQualificationItem()) {
				if(pqItem.getProduct() != null && pqItem.getProduct().getProductSpecification() != null) {
					for( ProductSpecificationRef productSpecification : pqItem.getProduct().getProductSpecification()) {
						if("productTypeCd".equals(productSpecification.getName())) {
							if(productSpecification.getId().equals(targetProductTypeCd)) {
								if( pqItem.getProduct() != null && pqItem.getProduct().getCharacteristic() != null ) {
									for(ProductCharacteristic productCharacteristic : pqItem.getProduct().getCharacteristic()) {
										if("orderType".equals(productCharacteristic.getName())) {
											transactionTypeCode = productCharacteristic.getValue();
										}
									}
								}
							}
							
							if(pqItem.getContractTerm() != null) {
								contractTerm = pqItem.getContractTerm().getDuration().getAmount();
							}
						}
					}
				}
			}
		}
		if(transactionTypeCode != null) {
			TransactionType transactionType = new TransactionType();
			transactionType.setTransactionTypeCode(transactionTypeCode);
			discount.getTransactionTypeList().add(transactionType);
		}
		else {
			logger.info(functionName, "Product or orderType for product not found in request "+ targetProductTypeCd);
		}
		if(contractTerm > -1)
		{
			discount.getContractTermList().add(BigInteger.valueOf(contractTerm));
		}
		else {
			logger.info(functionName, "Product or contract term for product not found in request for "+ targetProductTypeCd);
		}
	}

	private static List<ProductOffering> getMappedProductOfferingList(List<ProductOffering> responseProductOfferingList,  List<ProductOffering> requestProductOfferingList) {
		final String functionName = "getMappedProductOfferingList";
		List<ProductOffering> mapped = new ArrayList<ProductOffering>();
		if( responseProductOfferingList != null && requestProductOfferingList != null) {
			for(ProductOffering offeringInReq: requestProductOfferingList) {
				for(ProductOffering offeringInRes: responseProductOfferingList) {
					if( offeringInReq.getId().equals(offeringInRes.getId())) {
						mapped.add(offeringInRes);
					}
				}
			}
		}
		else {
			logger.info(functionName, "product offerings from request or response is empty");
		}
		return mapped;
	}

	private static Discount populateDiscountDetails(Discount discount, Promotion promotion, PromotionPattern pattern, HashMap<String, String> orderTypeMap) {
		final String functionName = "populateDiscountDetails";
		discount.setWinbackInd(getWinbackInd(pattern.getCriteriaGroup()));
		discount.setInstallCredit(getInstallCredit(pattern.getCriteriaGroup()));
//		discount.setIncludedInd("PROMOTION_ENFORCEMENT_TYPE_MANDATORY_UNLIMITED".equals(promotion.getPromotionGroup().getEnforcementType()));
		discount.setProductCriteriaValue(getProductCriteriaValue(pattern.getCriteriaGroup()));
		discount.setIncludedInd(false);
		discount.setStackableInd(promotion.getPromotionGroup().getStackable());
		discount.setMarketingDescriptionList(transformOfferDescriptionList(promotion.getDescription()));
		discount.setDiscountedComponentTypeCd(promotion.getPromotionGroup().getEnforcementType());
		if(pattern.getAction() != null) {
			for( PromotionAction action: pattern.getAction()) {
				discount.setDiscountCode(action.getName());
				DiscountProductCatalogueItem discountProductCatalogueItem = new DiscountProductCatalogueItem();
				ProductCatalogueIdentifier  productCatalogueIdentifier = new ProductCatalogueIdentifier();
				ProductCatalogueIdentifier  parentProductCatalogueIdentifier = new ProductCatalogueIdentifier();
				if( action.getCharacteristic() != null) {
					for(Characteristic charecterstic : action.getCharacteristic()) {
						if("productCatalogId".equals(charecterstic.getName())) {
							productCatalogueIdentifier.setProductCatalogueId(charecterstic.getValue());
							discountProductCatalogueItem.setProductCatalogueIdentifier(productCatalogueIdentifier);
						}
						else if("omsId".equals(charecterstic.getName())) {
							productCatalogueIdentifier.setExternalProductCatalogId(charecterstic.getValue());
							discountProductCatalogueItem.setProductCatalogueIdentifier(productCatalogueIdentifier);
						}
						else if("parentProductCatalogId".equals(charecterstic.getName())) {
							parentProductCatalogueIdentifier.setProductCatalogueId(charecterstic.getValue());
							discountProductCatalogueItem.setParentProductCatalogueIdentifier(parentProductCatalogueIdentifier);	
						}
						else if("parentOmsId".equals(charecterstic.getName())) {
							parentProductCatalogueIdentifier.setExternalProductCatalogId(charecterstic.getValue());
							discountProductCatalogueItem.setParentProductCatalogueIdentifier(parentProductCatalogueIdentifier);
						}
					}
				}
				discountProductCatalogueItem.setDiscountPriceAmt(action.getActionValue());
				if(action.getDuration() != null && action.getDuration().getAmount() != null) {
					if(action.getDuration().getAmount().intValue() > -1 )
					{
					discountProductCatalogueItem.setRecurringCount(BigInteger.valueOf(action.getDuration().getAmount())); //If duration is blank, its one time. -1 for on-going
				}
				}
				else {
					discountProductCatalogueItem.setRecurringCount(BigInteger.valueOf(0));
				}
				if("PRICE_REDUCTION".equals(action.getActionType())) {
					discountProductCatalogueItem.setDiscountType(new DiscountType());
					discountProductCatalogueItem.getDiscountType().setDiscountTypeCode(DISCOUNT_TYPE_CODE_D);
				}
				discount.getDiscountProductCatalogueItemList().add(discountProductCatalogueItem);
			}
		}
		else {
			logger.debug(functionName, "patter.action list is null for promotionid="+ promotion.getId());
		}
		//populate productTypeCd,contractTermList,recontractingInd from pattern.criteriaGroup
		
		populateRecontractingInd(discount, pattern, orderTypeMap);
		
		return discount;
		
	}
	
	

	/**
	 * Set the re-contracting indicator based on transaction types combination.
	 * 
	 * @param discount
	 * @param pattern
	 */
	private static void populateRecontractingInd(final Discount discount, final PromotionPattern pattern, HashMap<String, String> orderTypeMap) {
		//populate productTypeCd,contractTermList,recontractingInd from pattern.criteriaGroup
		boolean isTxnTypeRenewal = false;
		boolean isTxnTypeUpgrade = false;
		String productType = null;
		if (pattern.getCriteriaGroup() != null) {
			for (final PromotionCriteriaGroup criteriaGroup : pattern.getCriteriaGroup()) {

				if ("TRANSACTION".equalsIgnoreCase(criteriaGroup.getGroupName()) && criteriaGroup.getCriteriaGroup() != null) {
					//QC75921: get criteria group's product type
					PromotionCriteriaGroup scopeCriteria = criteriaGroup.getScopeCriteria();
					for(PromotionCriteria criteria: scopeCriteria.getCriteria()) {
						if(PRODUCT.equalsIgnoreCase(criteria.getCriteriaParam())) {
							productType = criteria.getCriteriaValue();
						}
					}

					// continue check transaction type if product type match between pattern's product type and criteria group's product type
					// pattern product type is promotion transaction dependent product 
					// criteria.product is promotion target product 
					if(!pattern.getProductType().equalsIgnoreCase(productType)) continue;
					
					for (final PromotionCriteriaGroup nestCriteriaGroup : criteriaGroup.getCriteriaGroup()) {
						if ("TRANSACTION".equalsIgnoreCase(nestCriteriaGroup.getGroupName())) {
							for (final PromotionCriteria promoCriteria :nestCriteriaGroup.getCriteria()) {
								if ("transaction.type".equalsIgnoreCase(promoCriteria.getCriteriaParam()) && promoCriteria.getCriteriaValue()!= null && promoCriteria.getCriteriaValue().contains("upgrade")) {
									isTxnTypeUpgrade = true;
								}
								if ("transaction.type".equalsIgnoreCase(promoCriteria.getCriteriaParam()) && promoCriteria.getCriteriaValue()!= null && promoCriteria.getCriteriaValue().contains("renewal")) {
									isTxnTypeRenewal = true;
								}
							}
						}
					}
				}
			}
		}
		
		//QC77530 Ignore this if order type is ACTIVATION
		if (isTxnTypeRenewal && !isTxnTypeUpgrade && !EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(orderTypeMap.get(productType))) {
			discount.setRecontractingInd(Boolean.TRUE);
		} else {
			discount.setRecontractingInd(Boolean.FALSE);
		}
	}

	/**
	 * Gary 2019-07-30 WEEKDAY Installation Credit 
	 */
	private static boolean getInstallCredit(List<PromotionCriteriaGroup> criteriaGroups){ 
		
		if (criteriaGroups == null){
			return false;
		}
		
		for (PromotionCriteriaGroup criteriaGroup : criteriaGroups){
			if (INSTALLATION.equals(criteriaGroup.getGroupName()) &&
					INSTALLATION.equals(criteriaGroup.getCriteriaType() )){
				for (PromotionCriteria criteria : criteriaGroup.getCriteria()){
					if ("weekdayCredit".equals(criteria.getCriteriaParam()) && "=".equals(criteria.getCriteriaOperator())){
						return Boolean.parseBoolean(criteria.getCriteriaValue());
					}
				}
			}
		}
		
		return false;
	}
	

	private static String getProductCriteriaValue(List<PromotionCriteriaGroup> criteriaGroups){ 
		String value = null;
		
		if (criteriaGroups == null){
			return value;
		}
		
		for (PromotionCriteriaGroup criteriaGroup : criteriaGroups){
			if (PRODUCT.equals(criteriaGroup.getGroupName()) &&
					PRODUCT.equals(criteriaGroup.getCriteriaType() )){
				for (PromotionCriteria criteria : criteriaGroup.getCriteria()){
					if (value == null){
						value = criteria.getCriteriaValue();
					}else {
						value = value + "," + criteria.getCriteriaValue();
					}
				}
			}
		}
		
		return value;
	}
	
	private static boolean getWinbackInd(List<PromotionCriteriaGroup> criteriaGroups) {

		if(criteriaGroups != null) {
			for(PromotionCriteriaGroup criteriaGroup: criteriaGroups) {
				if("winback".equals(criteriaGroup.getCriteriaType() )){
					if(criteriaGroup.getCriteria() != null) {
						for(PromotionCriteria criteria : criteriaGroup.getCriteria()) {
							if("winback".equals(criteria.getCriteriaParam())) {
								return Boolean.parseBoolean(criteria.getCriteriaValue());
							}
						}
					}
				}
			}
		}
		return false;
	}

	
	public static PromoCodeRefVO transform(PromoCodeRef promoCodeRef) {
		PromoCodeRefVO promoCodeRefVo= new PromoCodeRefVO();
		if (promoCodeRef != null) {
			promoCodeRefVo.setPromoCodeId(promoCodeRef.getPromoCodeId());
			promoCodeRefVo.setPromoCode(promoCodeRef.getPromoCode());
			promoCodeRefVo.setHref(promoCodeRef.getHref());
		}
		return promoCodeRefVo;
	}

	public static PromotionGroupVO transform(PromotionGroup promotionGroup) {
		PromotionGroupVO promoGroup = new PromotionGroupVO();
		if( promotionGroup != null) {
			promoGroup.setAvailability(promotionGroup.getAvailability());
			promoGroup.setCode(promotionGroup.getCode());
			promoGroup.setDescription(transformOfferDescriptionList(promotionGroup.getDescription())); //TODO
			promoGroup.setEnforcementType(promotionGroup.getEnforcementType());
			promoGroup.setId(promotionGroup.getId().toString());
			promoGroup.setMaxQty(promotionGroup.getMaxQty());
			promoGroup.setMinQty(promotionGroup.getMinQty());
			promoGroup.setPromotionGroupType(promotionGroup.getEnforcementType());
			if (promotionGroup.getRanking() != null) {
				promoGroup.setRanking(promotionGroup.getRanking());
			}
			promoGroup.setStackable(promotionGroup.getStackable());
		}
		return promoGroup;
	}
	public static  List<Description> transformOfferDescriptionList(List<LocaleDescription> promotionDescription) {
		List <Description> outputOfferDescriptionList = null;
		
		if ( (promotionDescription != null) && (!promotionDescription.isEmpty()) ) {
			outputOfferDescriptionList = new ArrayList<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description>();
	
			for (LocaleDescription inputOfferDescription : promotionDescription) {
				com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description outputOfferDescription = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description();
				outputOfferDescription.setDescriptionText(inputOfferDescription.getDescription());
				outputOfferDescription.setLocale(inputOfferDescription.getLocale());
				outputOfferDescriptionList.add(outputOfferDescription);
			}
		}
	
		return outputOfferDescriptionList;
	}

	public static  List<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description> transformOfferDescriptionList8(List<LocaleDescription> promotionDescription) {
		List <com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description> outputOfferDescriptionList = null;
		
		if ( (promotionDescription != null) && (!promotionDescription.isEmpty()) ) {
			outputOfferDescriptionList = new ArrayList<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description>();
	
			for (LocaleDescription inputOfferDescription : promotionDescription) {
				com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description outputOfferDescription = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description();
				outputOfferDescription.setDescriptionText(inputOfferDescription.getDescription());
				outputOfferDescription.setLocale(inputOfferDescription.getLocale());
				outputOfferDescriptionList.add(outputOfferDescription);
			}
		}
	
		return outputOfferDescriptionList;
	}
	
	public static SweetenerOfferSummary transform2Sweetener(Promotion promotion, HashMap<String, String> orderTypeMap) {
		final String functionName = "transform2Sweetener";
		logger.info(functionName, "Transofming to sweetener , promotion id="+ promotion.getId());
		SweetenerOfferSummary sweetenerOfferSummary = new SweetenerOfferSummary();
		try {
			sweetenerOfferSummary.setProgramId(Long.parseLong(promotion.getProgramId()));
			sweetenerOfferSummary.setProgramCode(promotion.getName());
			if (promotion.getPerspectiveDate() != null) {
				sweetenerOfferSummary.setPerspectiveDate(promotion.getPerspectiveDate().toDate());
			}
			if(promotion.getValidFor() != null) {
				sweetenerOfferSummary.setEffectiveDate(promotion.getValidFor().getStartDateTime().toDate());
				sweetenerOfferSummary.setExpiryDate(promotion.getValidFor().getEndDateTime().toDate());
			}
			sweetenerOfferSummary.setTypeCode("SWEETENER"); 
			sweetenerOfferSummary.setOfferId(Long.parseLong(promotion.getId()));
			sweetenerOfferSummary.setOfferDescriptionList(transformOfferDescriptionList(promotion.getDescription()));
	
			//populate category code only if its MANUAL
			Discount discount = new Discount();
			if("MANUAL".equals(promotion.getPromotionType())) {
				OfferCategory offerCategory = new OfferCategory();
				offerCategory.setCategoryTypeCode("OFFER_CATEGORY");
				Category category = new Category();
				category.setCategoryCode("MANUAL_SWEETENER");
				offerCategory.getCategoryList().add(category);
				sweetenerOfferSummary.getOfferCategoryList().add(offerCategory);
				discount.setManualSweetener(true);
			}
			
			WirelineOfferProductSummary wirelineOfferProductSummary = new WirelineOfferProductSummary();
			sweetenerOfferSummary.setOfferProductSummary(new SweetenerOfferSummary.OfferProductSummary());
			sweetenerOfferSummary.getOfferProductSummary().getWirelineOfferProductSummaryList().add(wirelineOfferProductSummary);
			wirelineOfferProductSummary.getDiscountList().add(discount);
	
			if( promotion.getPattern() != null) {
				for( PromotionPattern pattern : promotion.getPattern()) {
					wirelineOfferProductSummary.setProductTypeCode(pattern.getProductType());
					populateDiscountDetails(discount, promotion, pattern, orderTypeMap);
					
				}
			}
			sweetenerOfferSummary.setPromotionGroup(transform(promotion.getPromotionGroup()));
			if (promotion.getPromoCodeRef() != null) {
				sweetenerOfferSummary.setPromoCodeRef(transform(promotion.getPromoCodeRef()));
			}
			sweetenerOfferSummary.setPromotionId(promotion.getId());
			if (promotion.getPerspectiveDate() != null) {
				sweetenerOfferSummary.setPromotionPerspectiveDate(promotion.getPerspectiveDate().toDate());
			}
			
			sweetenerOfferSummary.setInstallCredit(discount.hasInstallCredit());
		}catch(Exception ex) {
			logger.info(functionName, ex.getMessage());
		}
		logger.debug(functionName,toJasonString(sweetenerOfferSummary));
		return sweetenerOfferSummary;
	}
	/**
	 * Transform promotion from Promotion Qualification service to Accessory Offer ie GWP
	 * @param promotion
	 * @return
	 */
	public static AccessoryOffer transform2GWP(Promotion promotion, PromotionPattern pattern) {
		final String functionName = "transform2GWP";
		logger.info(functionName, "Transofming to GWP , promotion id="+ promotion.getId());
		AccessoryOffer accessoryOffer = new AccessoryOffer();
		try {
			accessoryOffer.setOfferProgram(getOfferProgram(promotion));
	//		accessoryOffer.getOfferProduct().setAccessoryOfferProduct(getAccessoryOfferProduct(promotion));
			accessoryOffer.setPromotionGroup(transform(promotion.getPromotionGroup()));
			accessoryOffer.setPromoCodeRef(transform(promotion.getPromoCodeRef()));
			accessoryOffer.setPromotionId(promotion.getId());
			OfferHeader offerHeader = new OfferHeader();
			accessoryOffer.setOfferHeader(offerHeader);
			offerHeader.setOfferId(promotion.getId());
			if( promotion.getPerspectiveDate() != null) {
				accessoryOffer.setPromotionPerspectiveDate(promotion.getPerspectiveDate().toDate());
				offerHeader.setPerspectiveDate(promotion.getPerspectiveDate().toDate());
			}
			// set the promotion description
			final List<LocaleDescription> promoDescList = promotion.getDescription();
			if (promoDescList != null) {
				final List<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description> offerDescriptionList = 
						new ArrayList<com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description>();
				for (final LocaleDescription elem : promoDescList) {
					com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description desc = 
							new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description(); 
					desc.setDescriptionText(elem.getDescription());
					desc.setLocale(elem.getLocale());
					offerDescriptionList.add(desc);
				}
				offerHeader.setOfferDescriptionList(offerDescriptionList);
			}
			
			populateOfferProductAndProductEligiblityList(accessoryOffer,promotion, pattern);
		}catch(Exception ex) {
			logger.info(functionName, ex.getMessage());
		}
		logger.debug(functionName,toJasonString(accessoryOffer));
		return accessoryOffer;
	}
	
	static AccessoryOfferProduct populateOfferProductAndProductEligiblityList(AccessoryOffer accessoryOffer,Promotion promotion, PromotionPattern pattern) {
		final String functionName = "populateOfferProductAndProductEligiblityList";
		AccessoryOfferProduct accessoryOfferProduct = new AccessoryOfferProduct();
		OfferProduct offerProduct = new OfferProduct();
		accessoryOffer.setOfferProduct(offerProduct);
		accessoryOffer.getOfferProduct().setAccessoryOfferProduct(accessoryOfferProduct);
		accessoryOfferProduct.setStackableInd(promotion.getPromotionGroup().getStackable());
		//TODO: populate contractTerm
		
//		accessoryOfferProduct.setContractTermList(contractTermList);

		// one promotion - one action for GWP
		
		List<String> contractEnforcedProductCdList = new ArrayList<String>();
//		List<AccessoryGift> accessoryGiftList = new ArrayList<AccessoryGift>();
		

			if( pattern != null) {
				if( pattern.getAction() != null) {
					for( PromotionAction action: pattern.getAction()) {
						//      accessoryOfferCode = Gift.productCode
						accessoryOfferProduct.setAccessoryOfferCode(getValueForKey("productOffering.code", action.getCharacteristic())); //pattern.action.characteristics.name=productOffering.code
						accessoryOfferProduct.setEligibilityTypeCd("TRANSACTIONAL"); 
						List<BigInteger> contractTermList = new ArrayList<BigInteger>();
						AccessoryGift gift = new AccessoryGift();
				        List<GiftDiscountPaymentPlan> giftDiscountPaymentPlanList = new ArrayList<GiftDiscountPaymentPlan>();
				        GiftDiscountPaymentPlan paymentPlanSubsidy = new GiftDiscountPaymentPlan();
				        GiftDiscountPaymentPlan paymentPlanFinance = new GiftDiscountPaymentPlan();
				        BigInteger paymentInstallmentCntForFinance = new BigInteger("-1") ;
				        BigInteger financeAmount = new BigInteger("-1") ;
				        
				        if(action.getActionObject() != null) {
						        for( PromotionCriteriaGroup criteriaGrp :action.getActionObject()) {
						        	if(criteriaGrp.getCriteria() != null) {
										for( PromotionCriteria criteria :criteriaGrp.getCriteria()) {											
											if("productOffering.code".equals(criteria.getCriteriaParam())) {
												accessoryOfferProduct.setAccessoryOfferCode(criteria.getCriteriaValue());
												gift.setProductCode(criteria.getCriteriaValue());
											}
											
											else if("productOffering.product.constraint".equals(criteria.getCriteriaParam())){
												contractEnforcedProductCdList.add(criteria.getCriteriaValue());
											}
//											accessoryGiftList.add(gift);
											accessoryOfferProduct.getGiftList().add(gift);
										}
						           }
								}
				           
				        }
				        if( action.getCharacteristic() != null) {
							for(Characteristic characteristic:action.getCharacteristic()) {
								String name = characteristic.getName();
								String value=characteristic.getValue();
								
								if("productOffering.value".equals(name)) {
										gift.setGiftAmount(new Double(value));
								}
								else if ("productOffering.term".equals(name)) {
									contractTermList.add(new BigInteger(value));
									paymentInstallmentCntForFinance = new BigInteger(value);
								}	
								else if("productOffering.finance.amount".equalsIgnoreCase(name)) {
									financeAmount = new BigInteger(value);
								}	
							}
						}
						
						if(action.getActionValue() != null) {
							paymentPlanSubsidy.setPaymentTypeCd("subsidy");
							paymentPlanSubsidy.setPaymentAmt(action.getActionValue());
							giftDiscountPaymentPlanList.add(paymentPlanSubsidy);
						}
						//TODO: uncomment once PQ publish new yaml
						if(financeAmount.intValue() > -1) {
							paymentPlanFinance.setPaymentTypeCd("finance");
							paymentPlanFinance.setPaymentAmt(financeAmount.intValue());
							paymentPlanFinance.setPaymentInstallmentCnt(paymentInstallmentCntForFinance);
							if( paymentInstallmentCntForFinance.intValue() == 0) {
								paymentPlanFinance.setImmediatePaymentInd(true);
							}
							giftDiscountPaymentPlanList.add(paymentPlanFinance);
						}
						accessoryOfferProduct.setIncompatibleDiscountIdList(getIncompatibleDiscountIdList(pattern.getCriteriaGroup(),INTERNAL_SYSTEM_ID));
						accessoryOfferProduct.setContractEnforcedProductCdList(getContractEnforcedProductCdList(action.getActionObject()));
						
						//set only if list is not empty
						if(giftDiscountPaymentPlanList.size()>0) gift.setGiftDiscountPaymentPlanList(giftDiscountPaymentPlanList);
						if(contractTermList.size() > 0) accessoryOfferProduct.setContractTermList(contractTermList);
					}
			}
				//productEligibility
				Map<String,ProductEligiblity> productEligiblityMap = new HashMap<String,ProductEligiblity>();
				String product= "";
				ProductEligiblity selectProductEligiblityObj = null;
				
				accessoryOffer.setInstallCredit(getInstallCredit(pattern.getCriteriaGroup()));
				
				for(PromotionCriteriaGroup promotionCriteriaGroup : pattern.getCriteriaGroup()) {
					if("productOfferingIdentifier".equals(promotionCriteriaGroup.getCriteriaType())){
						for( PromotionCriteriaGroup promotionCriteriaGroup2:promotionCriteriaGroup.getCriteriaGroup()) {
							if("productOfferingIdentifier".equals(promotionCriteriaGroup.getCriteriaType())){
								for(PromotionCriteria criteria:promotionCriteriaGroup2.getCriteria()) {
									if("productOfferingIdentifier.type".equals(criteria.getCriteriaParam())) {
										product=criteria.getCriteriaValue();
										if(productEligiblityMap.get(product) == null) {
											productEligiblityMap.put(product, new ProductEligiblity());
										}
									}
								}
							}
						}
					}
					else if("transaction".equals(promotionCriteriaGroup.getCriteriaType())){
						if("product".equals(promotionCriteriaGroup.getScopeCriteria().getCriteriaType())){
							for( PromotionCriteria criteria: promotionCriteriaGroup.getScopeCriteria().getCriteria()) {
								if("product".equals(criteria.getCriteriaParam())){
									selectProductEligiblityObj = productEligiblityMap.get(criteria.getCriteriaValue());
									if(selectProductEligiblityObj == null) {
										selectProductEligiblityObj = new ProductEligiblity();
										productEligiblityMap.put(criteria.getCriteriaValue(), selectProductEligiblityObj);
									}
								}
							}
						}
						//populate term and transaction type eligible for this product
						for( PromotionCriteriaGroup promotionCriteriaGroup2:promotionCriteriaGroup.getCriteriaGroup()) {
							if("transaction".equals(promotionCriteriaGroup2.getCriteriaType())){
								for(PromotionCriteria criteria:promotionCriteriaGroup2.getCriteria()) {
									if("transaction.term".equals(criteria.getCriteriaParam())) {
										selectProductEligiblityObj.setContractTermList(convetCommaSeperatedListToInt(criteria.getCriteriaValue()));
									}
									else if("transaction.type".equals(criteria.getCriteriaParam())) {
										TransactionType transactionType = new TransactionType();
										transactionType.setTransactionTypeCode(criteria.getCriteriaValue());
										selectProductEligiblityObj.setTransactionType(transactionType);
									}
								}
							}
						}
					}
				}
				if( ! productEligiblityMap.values().isEmpty() ) {
					accessoryOffer.getOfferHeader().setProductEligiblityList(new ArrayList<ProductEligiblity>(productEligiblityMap.values()));
				}
		}
		else {
			logger.info(functionName, "pattern  is empty or null");
		}
		return accessoryOfferProduct;
	}
	
	private static List<BigInteger> convetCommaSeperatedListToInt(String commaSeperatedStr) {
		List<BigInteger> list = new ArrayList<BigInteger>();
		for (String s : commaSeperatedStr.split(","))
		    list.add(new BigInteger(s));
		return list;
	}

	static List<String> getContractEnforcedProductCdList(List<PromotionCriteriaGroup> actionObjectList) {
		List<String> result = null;
		String commaSeperatedStr = "";
		if( actionObjectList != null) {
			for(PromotionCriteriaGroup actionObj : actionObjectList) {
				if("productOffering".equals(actionObj.getCriteriaType())) {
					if(actionObj.getCriteria() != null) {
						for(PromotionCriteria criteria:actionObj.getCriteria()) {
							if("productOffering.product.constraint".equals(criteria.getCriteriaParam())){
								commaSeperatedStr = criteria.getCriteriaValue() ;
								
							}
						}
					}
				}
			}
		}
		return Arrays.asList(commaSeperatedStr.split("\\s*,\\s*"));
	}

	
	//product catalogue ids are pods id
	static List<ProductCatalogueIdentifier> getIncompatibleDiscountIdList(List<PromotionCriteriaGroup> criteriaGroupList, String internalSystemId) {
		String listOfIds = "";
		String systemId = "";
		List<ProductCatalogueIdentifier> productCatalogueIdentifierList = new ArrayList<ProductCatalogueIdentifier>();
		if(criteriaGroupList != null) {
			for(PromotionCriteriaGroup criteriaGroup: criteriaGroupList) {
				if("incompatiblePlan".equalsIgnoreCase(criteriaGroup.getCriteriaType())) {
					for(PromotionCriteriaGroup nextCriteriaGroup: criteriaGroup.getCriteriaGroup()) {
						if(nextCriteriaGroup.getCriteriaType().contains("incompatiblePlan")) {
							for( PromotionCriteria criteria : nextCriteriaGroup.getCriteria()) {
								if("incompatiblePlan.id".equals(criteria.getCriteriaParam())){
									listOfIds = criteria.getCriteriaValue() ;
								}
								if("incompatiblePlan.systemId".equals(criteria.getCriteriaParam())){
									systemId = criteria.getCriteriaValue() ;
								}
							}
							if( internalSystemId.equals(systemId) && listOfIds != null) {
								List<String> prodCatalogueIds = Arrays.asList(listOfIds.split("\\s*,\\s*"));
								for(String id : prodCatalogueIds) {
									ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier(); 
									productCatalogueIdentifier.setProductCatalogueId(id);
									productCatalogueIdentifierList.add(productCatalogueIdentifier);
								}
							}
						}
					}
					
					
				}
				
				
			}
		}
		return productCatalogueIdentifierList.size()>0 ? productCatalogueIdentifierList :null;
	}
	static Program getOfferProgram(Promotion promotion) {
		Program program = new Program();
		if(promotion.getProgramId() != null) {
			program.setProgramId(Long.parseLong(promotion.getProgramId()));
		}
		program.setProgramCode(promotion.getName());
		program.setPerspectiveDate(promotion.getPerspectiveDate().toDate());
		if(promotion.getValidFor() != null) {
			program.setEffectiveDate(promotion.getValidFor().getStartDateTime().toDate());
			program.setExpiryDate(promotion.getValidFor().getEndDateTime().toDate());
		}
		program.setTypeCode(TYPE_CODE_GWP);
		
//		program.setProgramDescriptionList(offer.getProgramDescriptionList());
//		program.setPromotionCode(offer.getPromotionCode());
//		program.setSourceSystemId(offer.getSourceSystemId());

		return program;
	}
	static String getValueForKey(String name, List<Characteristic> chracteristicList) {
		String result = null;
		if( name== null || chracteristicList == null || chracteristicList.size() ==0) {
			result = null;
		}
		else {
			for(Characteristic characteristic: chracteristicList) {
				if(name.equals(characteristic.getName()))
					result = characteristic.getValue() ;
			}
		}
		return result;
	}

	public static List<MessageList> transformMsgs(List<Message> adptrMessageList) {
		List<MessageList> messageListList = new ArrayList<MessageList>();
		MessageList messageList = new MessageList();
		if(adptrMessageList != null) {
			for(Message adptrMessage : adptrMessageList) {
				com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message msg = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message();
				msg.setMessage(adptrMessage.getMessage());
				messageList.getMessageList().add(msg);
			}
		}
		messageListList.add(messageList);
		return messageListList;
	}

	public static String toJasonString(Object obj) {		
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            ;
        }
        return "";
	}
	
}
