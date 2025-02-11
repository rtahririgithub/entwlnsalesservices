package com.telus.csm.ewlnsc.delegate;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ewlnsc.adapter.domain.quote.ProductOffering;
import com.telus.csm.ewlnsc.adapter.domain.quote.ProductOfferingPrice;
import com.telus.csm.ewlnsc.adapter.domain.quote.ProductOrderItem;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;
import com.telus.csm.ewlnsc.delegate.response.ProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.InstallmentOptionVO;
import com.telus.csm.ewlnsc.domain.MoneyVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.product.AssociatedProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.ServiceConstraintVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.PaymentOption;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

/**
 * 
 * @author ChrisB
 *
 */
public class ProductItemDelegate extends SpringBeanAutowiringSupport {

	private static Logger logger = Logger.getLogger(ProductItemDelegate.class);

	private final String LOCALE_EN = "EN";
	private final String SERVICE_TYPE_INTERNET_EQUIPMENT = "INEQ";
	private final String EQUIPMENT_TYPE_MODEM = "HIGH_SPEED_MODEM";
	private final String GROUP_TYPE_BOOST_WIFI = "BOOST_WIFI";
	private final String GROUP_SUBTYPE_BOOST_WIFI_STARTER = "STARTER";
	private final String GROUP_SUBTYPE_BOOST_WIFI_EXPANSION = "EXPANSION";
	private final String SERVICE_TYPE_BOOST_WIFI_STARTER = "DWSP";
	private final String SERVICE_TYPE_BOOST_WIFI_EXPANSION = "DWEP";
	private final String GROUP_TYPE_BOOST_WIFI_2 = "BOOST_WIFI_2";
	private final String GROUP_SUBTYPE_BOOST_WIFI_CENTRAL = "CENTRAL";
	private final String GROUP_SUBTYPE_BOOST_WIFI_MINI = "MINI";
	private final String SERVICE_TYPE_BOOST_WIFI_CENTRAL = "CAXP";
	private final String SERVICE_TYPE_BOOST_WIFI_MINI = "MAXP";
	private final String SERVICE_TYPE_TV_EQUIPMENT = "TVEQ";
	private final String EQUIPMENT_TYPE_TV = "SET_TOP_BOX";
	
	/**
	 * populateProductItems
	 * @param String productType
	 * @param List<ProductItem> productItems
	 * @param List<FFHEquipmentItem> equipmentItems
	 * @param ShoppingCartContextVO shoppingCartContext
	 * @return ProductItemDelegateResponse
	 */
	public ProductItemDelegateResponse populateProductItemsForPrice(String productType, CartItemVO cartItem, List<ProductItemVO> productItems, List<FFHEquipmentItemVO> equipmentItems, ShoppingCartContextVO shoppingCartContext) {
		GetSalesOfferDetailResponseVO2 salesOfferDetailVO = shoppingCartContext.getOfferByCartItemOfferId(cartItem.getProductMarketOffering().getOfferHeader().getOfferId()); // get from ShoppingCartContextVO
		List<SweetenerOfferSummary> sweeteners = shoppingCartContext.getSweetenersInShoppingCart(); // get from
		ProductItemDelegateResponse piResponse = null;
		if (isEstimatedPricing(shoppingCartContext)) {
			//Enrich PIs with grid + offer data. 
			piResponse =  populateProductItems(productType, productItems, equipmentItems, salesOfferDetailVO, sweeteners);
		}else {
			//Enrich PIs with grid only.
			piResponse = populateProductItems(productType, productItems, equipmentItems, null, null);
			//apply quote pricing.
			piResponse = populateOpQuotePricing(productType, piResponse.getProductItems(), piResponse.getEquipmentItems(), shoppingCartContext) ;
		}
		return piResponse;
		
	}
	
	/**
	 * populateProductItems
	 * @param String productType
	 * @param List<ProductItem> productItems
	 * @param List<FFHEquipmentItem> equipmentItems
	 * @param ShoppingCartContextVO shoppingCartContext
	 * @return ProductItemDelegateResponse
	 */
	public ProductItemDelegateResponse populateProductItems(String productType, CartItemVO cartItem, List<ProductItemVO> productItems, List<FFHEquipmentItemVO> equipmentItems, ShoppingCartContextVO shoppingCartContext, boolean estimatedPricingInd) {
		GetSalesOfferDetailResponseVO2 salesOfferDetailVO = shoppingCartContext.getOfferByCartItemOfferId(cartItem.getProductMarketOffering().getOfferHeader().getOfferId()); // get from ShoppingCartContextVO
		List<SweetenerOfferSummary> sweeteners = shoppingCartContext.getSweetenersInShoppingCart(); // get from
		ProductItemDelegateResponse piResponse = null;
		if(estimatedPricingInd) {
			//Enrich PIs with grid + offer data. 
			piResponse =  populateProductItems(productType, productItems, equipmentItems, salesOfferDetailVO, sweeteners);
		}else {
			//Enrich PIs with grid only.
			piResponse = populateProductItems(productType, productItems, equipmentItems, null, null);
			//apply quote pricing.
			piResponse = populateOpQuotePricing(productType, piResponse.getProductItems(), piResponse.getEquipmentItems(), shoppingCartContext) ;
		}
		return piResponse;
		
	}
	
	public ProductItemDelegateResponse populateProductItems(String productType, CartItemVO cartItem, List<ProductItemVO> productItems, List<FFHEquipmentItemVO> equipmentItems, ShoppingCartContextVO shoppingCartContext) {
		GetSalesOfferDetailResponseVO2 salesOfferDetailVO = shoppingCartContext.getOfferByCartItemOfferId(cartItem.getProductMarketOffering().getOfferHeader().getOfferId()); // get from ShoppingCartContextVO
		List<SweetenerOfferSummary> sweeteners = shoppingCartContext.getSweetenersInShoppingCart(); // get from
		ProductItemDelegateResponse piResponse = null;
		piResponse =  populateProductItems(productType, productItems, equipmentItems, salesOfferDetailVO, sweeteners);
		return piResponse;
		
	}

	private ProductItemDelegateResponse populateOpQuotePricing(String productType, List<ProductItemVO> productItems,
			List<FFHEquipmentItemVO> equipmentItems, ShoppingCartContextVO shoppingCartContext) {
		ProductItemDelegateResponse productItemResponse = new ProductItemDelegateResponse();
		
		QuoteResponse quoteResponse = shoppingCartContext.getQuoteResponse();
		
		//Key=externalProductCatalogueId.
		Map<String, PriceHolder> catalogueItems = new HashMap<String, ProductItemDelegate.PriceHolder>();
		//Build map of catalougeIds in Quote response.
		if(quoteResponse != null && quoteResponse.getProductOrderItems() != null) {
			if( quoteResponse.getProductOrderItems() != null ) {
				for( ProductOrderItem orderItem : quoteResponse.getProductOrderItems() ) {
					if( orderItem.getProduct() != null && orderItem.getProduct().getProductComponents()  != null ) {
						if(orderItem.getProduct().getServiceType().equals(productType)) {
							for(com.telus.csm.ewlnsc.adapter.domain.quote.ProductComponent component : orderItem.getProduct().getProductComponents()) {
								Map<String, PriceHolder> cataloguePriceMap = getOfferingPriceForProductComponent(component);
								catalogueItems.putAll(cataloguePriceMap);
							}
						}
					}
					
				}
			}
			
		}
		
		if( !catalogueItems.isEmpty() ) {
			//Product Item=components, add-on, discounts, sweetener, features.
			ListIterator<ProductItemVO> iterator = productItems.listIterator();
		      while (iterator.hasNext()){
		    	  ProductItemVO item = iterator.next();
		    	  PriceHolder priceItem = catalogueItems.get(item.getProductItemIdentifier().getExternalId());
		    	  if(priceItem != null) {
		    		  if( ( item.getMarketOfferClassification().isDiscountInd() != null && item.getMarketOfferClassification().isDiscountInd() ) ||  (item.getMarketOfferClassification().isSweetenerInd() != null && item.getMarketOfferClassification().isSweetenerInd())) {
		    			  PriceDiscountVO discount = new PriceDiscountVO();
		    			  MoneyVO amount = new MoneyVO();
		    			  discount.setBasePriceAmount(amount);
		    			  discount.setPricingTypeCd(priceItem.getPricingType());
		    			  discount.setRecurrenceCount(priceItem.getRecurrenceCount());
		    			  amount.setValueAmt(priceItem.getAmount().floatValue());
		    			  item.setProductItemPriceDiscount(discount);
		    		  }else {
		    			  PriceVO charge = new PriceVO();
		    			  MoneyVO amount = new MoneyVO();
		    			  charge.setBasePriceAmount(amount);
		    			  charge.setPricingTypeCd(priceItem.getPricingType());
		    			  charge.setRecurrenceCount(priceItem.getRecurrenceCount());
		    			  amount.setValueAmt(priceItem.getAmount().floatValue());
		    			  item.setProductItemPriceCharge(charge);
		    		  }
		    	  }
		          iterator.set(item);
		      }
			
			
			//equipments.
		      ListIterator<FFHEquipmentItemVO> it = equipmentItems.listIterator();
		      while (it.hasNext()){
		    	  FFHEquipmentItemVO item = it.next();
		    	  PriceHolder priceItem = catalogueItems.get(item.getProductItemIdentifier().getExternalId());
		    	  if(priceItem != null) {
		    			  PriceVO charge = new PriceVO();
		    			  MoneyVO amount = new MoneyVO();
		    			  charge.setBasePriceAmount(amount);
		    			  charge.setPricingTypeCd(priceItem.getPricingType());
		    			  charge.setRecurrenceCount(priceItem.getRecurrenceCount());
		    			  amount.setValueAmt(priceItem.getAmount().floatValue());
		    			  item.setEquipmentPrice(charge);
		    	  }
		          it.set(item);
		      }
		}
		productItemResponse.setEquipmentItems(equipmentItems);
		productItemResponse.setProductItems(productItems);
		
		return productItemResponse;
	}

	private Map<String, PriceHolder> getOfferingPriceForProductComponent(
			com.telus.csm.ewlnsc.adapter.domain.quote.ProductComponent component) {
		Map<String, PriceHolder> catalougePriceMap = new HashMap<String, ProductItemDelegate.PriceHolder>();

		if(component.getProductOffering() != null) {
			for(ProductOffering productOffering: component.getProductOffering()) {
				if(productOffering.getProductOfferingPrice() != null && !productOffering.getProductOfferingPrice().isEmpty()) {
					PriceHolder priceholder = getProductComponentPrice(productOffering.getProductOfferingPrice());
					if(component.getIsEnabled() != null && component.getIsEnabled()) {
						catalougePriceMap.put(component.getCatalogId(), priceholder);
					}
				}
			}
		}else if(component.getProductComponents() != null) {
			for(com.telus.csm.ewlnsc.adapter.domain.quote.ProductComponent subComponent : component.getProductComponents()) {
				Map<String, PriceHolder> ciPriceMap = getOfferingPriceForProductComponent(subComponent);
				if(ciPriceMap != null && !ciPriceMap.isEmpty()) {
					catalougePriceMap.putAll(ciPriceMap);
				}
			}
			
		}
		
		return catalougePriceMap;
	}

	private PriceHolder getProductComponentPrice(List<ProductOfferingPrice> productOfferingPriceList) {
		Double price = 0.0;
		PriceHolder holder = new PriceHolder();
		//default to recurring 
		holder.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
		
		
		for(ProductOfferingPrice priceItem : productOfferingPriceList) {
			if(priceItem != null) {
				price = Double.valueOf(price) + Double.valueOf(priceItem.getAmount());
				holder.setPricingType(priceItem.getPriceType());
			}
			 
		}
		holder.setAmount(price);
		
		
		return holder;
	}

	/**
	 * populateProductItems
	 * @param String productType
	 * @param List<ProductItem> productItems
	 * @param List<FFHEquipmentItem> equipmentItems
	 * @param GetSalesOfferDetailResponseVO salesOfferDetailVO
	 * @param GetOffersResponseVO sweetenersVO
	 * @return ProductItemDelegateResponse
	 */
	public ProductItemDelegateResponse populateProductItems(String productType, List<ProductItemVO> productItems, List<FFHEquipmentItemVO> equipmentItems, GetSalesOfferDetailResponseVO2 salesOfferDetailVO, List<SweetenerOfferSummary> sweeteners) {
		List<ProductItemVO> productItemList = new ArrayList<ProductItemVO>();
		List<FFHEquipmentItemVO> equipmentItemList = new ArrayList<FFHEquipmentItemVO>();
		//1. Prepare CatalogueId List
		//1a. From ProductItem List
		List<String> catalogueIdList = new ArrayList<String>();
		Map<String, ProductItemVO> prodItemsMap = new HashMap<String, ProductItemVO>();
		for (ProductItemVO prodItem : productItems) {
			if (prodItem.getProductItemIdentifier() != null && StringUtils.isNotBlank(prodItem.getProductItemIdentifier().getProductCatalogueId())) {
				//discount count or sweetener should part of product item
				//add the parent key to the map since same discount can be under different parent
				if(prodItem.getMarketOfferClassification() != null &&
						((prodItem.getMarketOfferClassification().isDiscountInd() != null && prodItem.getMarketOfferClassification().isDiscountInd()) || 
								(prodItem.getMarketOfferClassification().isSweetenerInd() != null && prodItem.getMarketOfferClassification().isSweetenerInd()))) { //QC-69953
					prodItemsMap.put(createProductItemIdForDiscount(prodItem.getProductItemIdentifier().getProductCatalogueId(), prodItem.getProductItemIdentifier().getParentProductCatalogueId()), prodItem);
					catalogueIdList.add(createProductItemIdForDiscount(prodItem.getProductItemIdentifier().getProductCatalogueId(), prodItem.getProductItemIdentifier().getParentProductCatalogueId()));
				}else {
					prodItemsMap.put(prodItem.getProductItemIdentifier().getProductCatalogueId(), prodItem);
					catalogueIdList.add(prodItem.getProductItemIdentifier().getProductCatalogueId());
				}
				// This is the same ProductItem instance that is in the prodItemsMap
				// This means that when the ProductItem in the prodItemsMap get populated, it will also populate the one in the productItemList
				productItemList.add(prodItem);
			}
		}
		//1b. From FFHEquipmentItem List
		List<String> materialItemCodes = new ArrayList<String>();
		Map<String, FFHEquipmentItemVO> equipItemsMap = new HashMap<String, FFHEquipmentItemVO>();
		for (FFHEquipmentItemVO equipmentItem : equipmentItems == null ? Collections.<FFHEquipmentItemVO>emptyList() : equipmentItems) {
			if (equipmentItem.getProductItemIdentifier() != null && StringUtils.isNotBlank(equipmentItem.getProductItemIdentifier().getParentProductCatalogueId())) {
				//NWLN-10248 fix for finance payment being overwritten
				if(equipmentItem.getEquipmentPrice() != null && equipmentItem.getEquipmentPrice().getInstallmentOptions() != null
					&& equipmentItem.getEquipmentPrice().getInstallmentOptions().size() > 0 && equipmentItem.getEquipmentPrice().getInstallmentOptions().get(0) != null
					&& equipmentItem.getEquipmentPrice().getInstallmentOptions().get(0).getNumberOfInstallments()!=null) {
						Integer installmentOption = equipmentItem.getEquipmentPrice().getInstallmentOptions().get(0).getNumberOfInstallments().intValue();
						equipItemsMap.put(equipmentItem.getProductItemIdentifier().getProductCatalogueId()+"_"+equipmentItem.getMaterialItemCode()+"_"+installmentOption, equipmentItem);
				}else {
					equipItemsMap.put(equipmentItem.getProductItemIdentifier().getProductCatalogueId()+"_"+equipmentItem.getMaterialItemCode(), equipmentItem);
				}
				// This is the same FFHEquipmentItem instance that is in the equipItemsMap
				// This means that when the FFHEquipmentItem in the equipItemsMap get populated, it will also populate the one in the equipmentItemList
				equipmentItemList.add(equipmentItem);
				catalogueIdList.add(equipmentItem.getProductItemIdentifier().getProductCatalogueId()+"_"+equipmentItem.getMaterialItemCode());				
				materialItemCodes.add(equipmentItem.getMaterialItemCode());
			}
		}
		//1c. Populate model name of a equipment from grid.
		if (CollectionUtils.isNotEmpty(materialItemCodes)) {
			Map<String, EquipmentCatalogueItemDO> equipGridMap = this.retrieveEquipments(materialItemCodes);
			for (FFHEquipmentItemVO equipmentItem : equipmentItems) {
				EquipmentCatalogueItemDO gridEquipmentDO = equipGridMap.get(equipmentItem.getMaterialItemCode());
				if(gridEquipmentDO!=null){
					if(!StringUtils.isBlank(gridEquipmentDO.getModelName())){
						equipmentItem.setModelName(gridEquipmentDO.getModelName());
					}
					if(!StringUtils.isBlank(gridEquipmentDO.getDescription())){
						equipmentItem.setEquipmentDescription(transformMultilingual(LOCALE_EN, gridEquipmentDO.getDescription()));
					}
					
				}
			}
		}		

		//2. Populate Name, Description and Service Type from Grid
		Map<String,CatalogueItemDO> catalogueItemMap = retrieveCatalogueItems(catalogueIdList);
		List<String> serviceConstraintCodes = getProductComponentServiceConstraintCodes(productItemList);
		
		//2a. Populate in ProductItem List
		Iterator<String> it = prodItemsMap.keySet().iterator();
		while (it.hasNext()) {
			String catalogueId = it.next();
			ProductItemVO productItem = prodItemsMap.get(catalogueId); // This references the same ProductItem instance
			if (productItem != null) {
				CatalogueItemDO catalogueItem = catalogueItemMap.get(catalogueId);
				if (catalogueItem != null) {
					if (CollectionUtils.isEmpty(productItem.getProductCatalogueName()) || StringUtils.isEmpty(productItem.getProductCatalogueName().get(0).getValueTxt())) {
						productItem.setProductCatalogueName(transformMultilingual(LOCALE_EN, catalogueItem.getName()));
					}
					if (CollectionUtils.isEmpty(productItem.getProductCatalogueDescription()) || StringUtils.isEmpty(productItem.getProductCatalogueDescription().get(0).getValueTxt())) {
						productItem.setProductCatalogueDescription(transformMultilingual(LOCALE_EN, catalogueItem.getDescription()));	
					}
					
					productItem.setServiceTypeCode(catalogueItem.getComponentServiceType());
					productItem.getProductItemIdentifier().setExternalId(catalogueItem.getProductCode());
					/*
					if (serviceConstraintCodes.contains(productItem.getServiceTypeCode() )) {
						productItem.setEligibleForMinMaxPricingInd(true);
					}
					*/
				}
			}
		}
		
		//2b. Populate in FFHEquipmentItem List
		it = equipItemsMap.keySet().iterator();
		while (it.hasNext()) {
			String catalogueId = it.next();
			FFHEquipmentItemVO equipmentItem = equipItemsMap.get(catalogueId); // This references the same FFHEquipmentItem instance
			if (equipmentItem != null) {
				CatalogueItemDO catalogueItem = catalogueItemMap.get(catalogueId);
				if (catalogueItem != null) {
					equipmentItem.setEquipmentType(catalogueItem.getComponentServiceType());
					equipmentItem.getProductItemIdentifier().setExternalId(catalogueItem.getProductCode());
					if (SERVICE_TYPE_INTERNET_EQUIPMENT.equalsIgnoreCase(catalogueItem.getComponentServiceType())) {
						equipmentItem.setEquipmentType(EQUIPMENT_TYPE_MODEM);
					}
					if (SERVICE_TYPE_BOOST_WIFI_STARTER.equalsIgnoreCase(catalogueItem.getComponentServiceType())) {
						equipmentItem.setEquipmentType(GROUP_TYPE_BOOST_WIFI);
						equipmentItem.setEquipmentSubType(GROUP_SUBTYPE_BOOST_WIFI_STARTER);
					} 
					if (SERVICE_TYPE_BOOST_WIFI_EXPANSION.equalsIgnoreCase(catalogueItem.getComponentServiceType())) {
						equipmentItem.setEquipmentType(GROUP_TYPE_BOOST_WIFI);
						equipmentItem.setEquipmentSubType(GROUP_SUBTYPE_BOOST_WIFI_EXPANSION);
					}
					if (SERVICE_TYPE_BOOST_WIFI_CENTRAL.equalsIgnoreCase(catalogueItem.getComponentServiceType())) {
						equipmentItem.setEquipmentType(GROUP_TYPE_BOOST_WIFI_2);
						equipmentItem.setEquipmentSubType(GROUP_SUBTYPE_BOOST_WIFI_CENTRAL);
					} 
					if (SERVICE_TYPE_BOOST_WIFI_MINI.equalsIgnoreCase(catalogueItem.getComponentServiceType())) {
						equipmentItem.setEquipmentType(GROUP_TYPE_BOOST_WIFI_2);
						equipmentItem.setEquipmentSubType(GROUP_SUBTYPE_BOOST_WIFI_MINI);
					}
					if (SERVICE_TYPE_TV_EQUIPMENT.equalsIgnoreCase(catalogueItem.getComponentServiceType())) {
						equipmentItem.setEquipmentType(EQUIPMENT_TYPE_TV);
					}
				}
			}
		}

		//3. Populate Price from Offer Detail and Sweetener
		if (salesOfferDetailVO != null) {
			Map<String, PriceHolder> priceCataloguItemsMap = retrievePriceCatalogueItems(productType, catalogueIdList,
					salesOfferDetailVO, sweeteners);
			
			//3a. Populate in ProductItem List
			it = prodItemsMap.keySet().iterator();
			while (it.hasNext()) { 
				String catalogueId = it.next();
				ProductItemVO productItem = prodItemsMap.get(catalogueId); // This references the same ProductItem
																			// instance
				if (productItem != null && priceCataloguItemsMap.get(catalogueId) != null) {
					PriceHolder priceHolderItem = priceCataloguItemsMap.get(catalogueId);
					productItem.setEligibleForMinMaxPricingInd(priceHolderItem.isPackeligibleInd());
					productItem.setPackEligibleItemInd(priceHolderItem.isPackeligibleInd());
					//set ItemRankNum in ProductItemVO
					productItem.setItemRankNum(priceHolderItem.getItemRankNum());
					
					MoneyVO priceAmount = new MoneyVO();
					if (priceHolderItem != null && priceHolderItem.getAmount() != null) {
						priceAmount.setValueAmt(priceHolderItem.getAmount().floatValue());
					} else {
						priceAmount.setValueAmt(0f);
					}
					
					if(priceHolderItem != null && StringUtils.isNotBlank(priceHolderItem.getSalesCategoryServiceTypeCd())) {
						productItem.setSalesCategoryServiceTypeCd(priceHolderItem.getSalesCategoryServiceTypeCd()); //NWLN-7789 - for price service only
					}
				
					if (productItem.getMarketOfferClassification() != null
							&& ( ( productItem.getMarketOfferClassification().isDiscountInd() != null &&  productItem.getMarketOfferClassification().isDiscountInd() )
							|| ( productItem.getMarketOfferClassification().isSweetenerInd() != null && productItem.getMarketOfferClassification().isSweetenerInd()))) {
						PriceDiscountVO productItemPriceDiscount = new PriceDiscountVO();
						productItemPriceDiscount.setBasePriceAmount(priceAmount);
						if(priceHolderItem != null) {
							productItemPriceDiscount.setPromotionId(priceHolderItem.getPromotionId());
							productItemPriceDiscount.setPerspectiveDate(priceHolderItem.getPromotionPerspectiveDate());
							productItemPriceDiscount.setPricingTypeCd(priceHolderItem.getPricingType());
							productItemPriceDiscount.setRecurrenceCount(priceHolderItem.getRecurrenceCount());
							productItemPriceDiscount.setDiscountTypeCode(priceHolderItem.getDiscountTypeCode());
						}
						productItem.setProductItemPriceDiscount(productItemPriceDiscount);
						
						
					} else {
						PriceVO productItemPrice = new PriceVO();
						productItemPrice.setBasePriceAmount(priceAmount);
						if(priceHolderItem != null) {
							productItemPrice.setPricingTypeCd(priceHolderItem.getPricingType());
							productItemPrice.setRecurrenceCount(priceHolderItem.getRecurrenceCount());
						}
						productItem.setProductItemPriceCharge(productItemPrice);
					}
				}
			}
			//3b. Populate in FFHEquipmentItem List
			it = equipItemsMap.keySet().iterator();
			while (it.hasNext()) {
				String catalogueId = it.next(); 
				FFHEquipmentItemVO equipmentItem = equipItemsMap.get(catalogueId); // This references the same
																					// FFHEquipmentItem instance
				if (equipmentItem != null) {
					PriceHolder priceHolderItem = priceCataloguItemsMap.get(catalogueId);
					MoneyVO priceAmount = new MoneyVO();
					if (priceHolderItem != null && priceHolderItem.getAmount() != null) {
						priceAmount.setValueAmt(priceHolderItem.getAmount().floatValue());
					} else {
						priceAmount.setValueAmt(0f);
					}
					PriceVO equipmentItemPrice = new PriceVO();
					equipmentItemPrice.setBasePriceAmount(priceAmount);
					if(priceHolderItem != null) {
						equipmentItemPrice.setPricingTypeCd(priceHolderItem.getPricingType());
						equipmentItemPrice.setRecurrenceCount(priceHolderItem.getRecurrenceCount());
					}
					//NWLN-10248 - Set installmentOptions
					if(EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType)) {
						InstallmentOptionVO installmentOption = new InstallmentOptionVO();
						MoneyVO installmentAmount= new MoneyVO();
						
						if(equipmentItem.getEquipmentPrice()!= null && equipmentItem.getEquipmentPrice().getInstallmentOptions() != null &&
								equipmentItem.getEquipmentPrice().getInstallmentOptions().size() > 0 && 
								equipmentItem.getEquipmentPrice().getInstallmentOptions().get(0) != null) {
							Integer numberOfInstallments = equipmentItem.getEquipmentPrice().getInstallmentOptions().get(0).getNumberOfInstallments();
							
							if(priceHolderItem != null) {
								logger.debug("Setting installmentOptions for Accessory");
								for(PaymentOption paymentOption : priceHolderItem.getPaymentOptionList()) {
									if(paymentOption.getPaymentInstallmentQty() != null && paymentOption.getPaymentInstallmentQty().intValue() == numberOfInstallments) {
											Double installmentAmountValue = paymentOption.getPaymentInstallmentAmt();
											if(installmentAmountValue != null){
												installmentAmount.setValueAmt(installmentAmountValue.floatValue());
											}		
									}
								}
							}
							MoneyVO downPaymentAmount = new MoneyVO();
							downPaymentAmount.setValueAmt(0f);
							
							installmentOption.setInstallmentOptionCd(EnterpriseWLNSalesServicesConstants.INSTALLMENT_OPTION_CD_OPTIONAL);
							installmentOption.setDownPaymentAmount(downPaymentAmount);
							installmentOption.setInstallmentAmount(installmentAmount);
							installmentOption.setNumberOfInstallments(numberOfInstallments);
							equipmentItemPrice.setInstallmentOptions(Arrays.asList(installmentOption));
						}
					}
		
					equipmentItem.setEquipmentPrice(equipmentItemPrice);	
				}
			}
		}
		
		//4. Populate DeliveryMethods and ModelName from Offer Detail
		if (salesOfferDetailVO != null) {
			Map<String,FFHEquipmentItemVO> equipItemCataloguItemsMap = retrieveEquipItemCatalogueItems(productType, catalogueIdList, salesOfferDetailVO);
			it = equipItemsMap.keySet().iterator();
			while (it.hasNext()) {
				String catalogueId = it.next();
				FFHEquipmentItemVO equipmentItem = equipItemsMap.get(catalogueId); // This references the same FFHEquipmentItem instance
				if (equipmentItem != null) {
					FFHEquipmentItemVO catEquipmentItem = equipItemCataloguItemsMap.get(catalogueId);				
					if(catEquipmentItem!=null){
						equipmentItem.setDeliveryMethods(catEquipmentItem.getDeliveryMethods());
						if (StringUtils.isNotBlank(catEquipmentItem.getModelName())) {
							equipmentItem.setModelName(catEquipmentItem.getModelName());
						}
					}
					
				}
			}
		}

		//5. Build Association
		buildAssociationBetwnProdIems(productItemList, prodItemsMap);
		buildAssociationBetwnEquipProdItems(equipmentItemList, productItemList, equipItemsMap, productType);

		
		//6. Create Response
		ProductItemDelegateResponse response = new ProductItemDelegateResponse();
		response.setProductItems(productItemList);
		response.setEquipmentItems(equipmentItemList);

		return response;
	}
	
	protected Map<String, EquipmentCatalogueItemDO> retrieveEquipments(List<String> materialItemCodes) {
		Map<String, EquipmentCatalogueItemDO> result = new HashMap<String, EquipmentCatalogueItemDO>();
		if (CollectionUtils.isEmpty(materialItemCodes))
			return result;
		
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		for (String miCode : materialItemCodes) {
			result.put(miCode, gridHelper.getEquipmentByProductCode(miCode));			
		}
		
		return result;
	}

	/**
	 * extractEquipmentItems
	 * @param List<AvailableFFHEquipmentType> equipments
	 * @return List<FFHEquipmentItem>
	 */
	public List<FFHEquipmentItemVO> extractEquipmentItems (List<AvailableFFHEquipmentTypeVO> equipments) {
		List<FFHEquipmentItemVO> equipmentItemList = new ArrayList<FFHEquipmentItemVO>();

		if (equipments == null) {
			// empty list
			return equipmentItemList;
		}

		for (AvailableFFHEquipmentTypeVO availEquipType : equipments) {
			List<FFHEquipmentItemVO> buyEquipItemList = availEquipType.getBuyEquipmentList();
			if (CollectionUtils.isNotEmpty(buyEquipItemList)) {
				equipmentItemList.addAll(buyEquipItemList);
			}
			List<FFHEquipmentItemVO> rentEquipItemList = availEquipType.getRentalEquipmentList();
			if (CollectionUtils.isNotEmpty(rentEquipItemList)) {
				equipmentItemList.addAll(rentEquipItemList);
			}

		}
		return equipmentItemList;
	}

	/**
	 * infuseEquipmentItems
	 * @param List<FFHEquipmentItem> equipmentItems
	 * @param List<AvailableFFHEquipmentType> equipments
	 */
	public void infuseEquipmentItems(List<FFHEquipmentItemVO> equipmentItems, List<AvailableFFHEquipmentTypeVO> equipments) {
		for (AvailableFFHEquipmentTypeVO availEquipType : equipments) {
			List<FFHEquipmentItemVO> buyEquipItemList = availEquipType.getBuyEquipmentList();
			for (FFHEquipmentItemVO buyEquipmentItem : buyEquipItemList == null ? Collections.<FFHEquipmentItemVO>emptyList() : buyEquipItemList) {
				String catalogueId = buyEquipmentItem.getProductItemIdentifier().getProductCatalogueId();
				String materialItemCode = buyEquipmentItem.getMaterialItemCode();
				for (FFHEquipmentItemVO equipmentItem : equipmentItems == null ? Collections.<FFHEquipmentItemVO>emptyList() : equipmentItems) {
					if (equipmentItem.getProductItemIdentifier().getProductCatalogueId().equals(catalogueId) &&
						equipmentItem.getMaterialItemCode().equals(materialItemCode)) {
						buyEquipmentItem = equipmentItem;
					}
				}
			}
			List<FFHEquipmentItemVO> rentEquipItemList = availEquipType.getRentalEquipmentList();
			for (FFHEquipmentItemVO rentEquipmentItem : rentEquipItemList == null ? Collections.<FFHEquipmentItemVO>emptyList() : rentEquipItemList) {
				String catalogueId = rentEquipmentItem.getProductItemIdentifier().getProductCatalogueId();
				String materialItemCode = rentEquipmentItem.getMaterialItemCode();
				for (FFHEquipmentItemVO equipmentItem : equipmentItems == null ? Collections.<FFHEquipmentItemVO>emptyList() : equipmentItems) {
					if (equipmentItem.getProductItemIdentifier().getProductCatalogueId().equals(catalogueId) &&
						equipmentItem.getMaterialItemCode().equals(materialItemCode)) {
						rentEquipmentItem = equipmentItem;
					}
				}
			}
		}
	}

	/**
	 * retrieveCatalogueItems
	 * @param List<String> catalogueIdList
	 * @return Map<String,CatalogueItemDO>
	 */
	protected Map<String,CatalogueItemDO> retrieveCatalogueItems(List<String> catalogueIdList) {
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();

		Map<String,CatalogueItemDO> catalogueItemMap = new HashMap<String,CatalogueItemDO>();
		for (String catalogueId : catalogueIdList) {	
			CatalogueItemDO catalogueItem = gridHelper.getCatalogueItemById(StringUtils.substringBefore(catalogueId, "_"));
			if (catalogueItem == null) {
				logger.error("the GRID does not include element: " + catalogueId);
			} else {
				logger.debug("the GRID does contain element: " + catalogueId + ",name=" + catalogueItem.getName() + ",desc=" + catalogueItem.getDescription());
			}
			catalogueItemMap.put(catalogueId, catalogueItem);
		}
		return catalogueItemMap;
	}

	/**
	 * retrievePriceCatalogueItems
	 * @param String productType
	 * @param List<String> catalogueIdList
	 * @param GetSalesOfferDetailResponseVO salesOfferDetailVO
	 * @param GetOffersResponseVOsweetenersVO
	 * @return Map<String,Double>
	 */
	private Map<String, PriceHolder> retrievePriceCatalogueItems(String productType, List<String> catalogueIdList, GetSalesOfferDetailResponseVO2 salesOfferDetailVO, List<SweetenerOfferSummary> sweeteners) {
		Map<String, PriceHolder> priceCatalogueItemMap = new HashMap<String,PriceHolder>();
		if (salesOfferDetailVO != null && salesOfferDetailVO.getOffer() != null && salesOfferDetailVO.getOffer().getOfferProduct() != null) {
			for (WirelineOfferProduct wirelineOfferProduct : salesOfferDetailVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
				if (wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(productType)) {
					//1. Product Component
					for (ProductComponent prodComponent : wirelineOfferProduct.getProductComponentList()) {
						for (ProductCatalogueItem catalogueItem : prodComponent.getProductCatalogueItemList()) {
							String catalogueId = catalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
							Double basePrice = catalogueItem.getProductCatalogueBasePriceAmt();
							PriceHolder value = new PriceHolder();
							value.setAmount(basePrice);
							//No recurrenceCount for addon, feature and product component, always set to PRICING_TYPE_RECURRING.
							value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
							if (catalogueIdList.contains(catalogueId)) {
								priceCatalogueItemMap.put(catalogueId, value);
							}
						}
					}
					//2. Discounts
					if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getDiscountList())) {
						for (Discount discount : wirelineOfferProduct.getDiscountList()) {
							for (DiscountProductCatalogueItem discountProdCatalgItem : discount.getDiscountProductCatalogueItemList()) {
								//discount id should include parent
								String catalogueId = createProductItemIdForDiscount(discountProdCatalgItem.getProductCatalogueIdentifier().getProductCatalogueId(), discountProdCatalgItem.getParentProductCatalogueIdentifier().getProductCatalogueId()); //QC-69953
								Double discountPrice = discountProdCatalgItem.getDiscountPriceAmt();
								
								PriceHolder value = new PriceHolder();
								value.setPromotionId(discount.getPromotionId());
								value.setPromotionPerspectiveDate(discount.getPromotionPerspectiveDate());
								value.setAmount(discountPrice);
								if(discountProdCatalgItem.getDiscountType() != null) {
									value.setDiscountTypeCode(discountProdCatalgItem.getDiscountType().getDiscountTypeCode());
								}
								
								if (discountProdCatalgItem.getRecurringCount() != null 
										&& discountProdCatalgItem.getRecurringCount().intValue() == 0)
									value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_ONE_TIME);
								else
									value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
								if(discountProdCatalgItem.getRecurringCount() != null) {
									value.setRecurrenceCount(discountProdCatalgItem.getRecurringCount().intValue());
								}
								
								if (catalogueIdList.contains(catalogueId)) {
									priceCatalogueItemMap.put(catalogueId, value);
								}
							}
						}
					}
					//3. AddOns
					if (wirelineOfferProduct.getAddOn() != null) {
						if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList())) {
							for (ProductCatalogueItem catalogueItem : wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList()) {
								String catalogueId = catalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
								Double basePrice = catalogueItem.getProductCatalogueBasePriceAmt();
								
								PriceHolder value = new PriceHolder();
								if(catalogueItem.isPackEligibleItemInd() != null) {
									value.setPackeligibleInd(catalogueItem.isPackEligibleItemInd());
								}
								
								value.setAmount(basePrice);
								//No recurrenceCount for addon, feature and product component, always set to PRICING_TYPE_RECURRING.
								value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
								
								if(StringUtils.isNotBlank(catalogueItem.getServiceTypeCode())) {
									value.setSalesCategoryServiceTypeCd(catalogueItem.getServiceTypeCode()); //NWLN-7789 - for price service only
								}
								
								if (catalogueIdList.contains(catalogueId) && basePrice != null) {
									priceCatalogueItemMap.put(catalogueId, value);
								}
							}
						}
						if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList())) {
							for (ProductCatalogueItem catalogueItem: wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList()) {
								String catalogueId = catalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
								Double basePrice = catalogueItem.getProductCatalogueBasePriceAmt();
								
								PriceHolder value = new PriceHolder();
								if(catalogueItem.isPackEligibleItemInd() != null) {
									value.setPackeligibleInd(catalogueItem.isPackEligibleItemInd());
								}
								//set itemRankNum
								if (catalogueItem.getItemRankNum() != null){
									value.setItemRankNum(catalogueItem.getItemRankNum());
								}
								value.setAmount(basePrice);
								//No recurrenceCount for addon, feature and product component, always set to PRICING_TYPE_RECURRING.
								value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
								
								if(StringUtils.isNotBlank(catalogueItem.getServiceTypeCode())) {
									value.setSalesCategoryServiceTypeCd(catalogueItem.getServiceTypeCode()); //NWLN-7789 - for price service only
								}
								
								if (catalogueIdList.contains(catalogueId) && basePrice != null) {
									priceCatalogueItemMap.put(catalogueId, value);
								}
							}
						}
					}
					//4. Equipments
					if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getWirelineEquipmentList())) {
						for (WirelineEquipment wirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()) {
							String productCatalogueId = wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();

							//4a. Rental Equipment List
							List<WirelineEquipmentItem> rentEquipmentItemList = wirelineEquipment.getRentalEquipmentList();
							if (CollectionUtils.isNotEmpty(rentEquipmentItemList)) {
								for (WirelineEquipmentItem wirelineEquipmentItem : rentEquipmentItemList) {
									String materialItemCode = wirelineEquipmentItem.getMaterialItemCode();
									//String modelName = ((SalesWirelineEquipmentItem)wirelineEquipmentItem).getModelName();				
									List<String> deliveryMethodList = wirelineEquipmentItem.getDeliveryMethodList();
									Double rentalPriceAmt = wirelineEquipmentItem.getEquipmentRentalPriceAmt();
									
									PriceHolder value = new PriceHolder();
									value.setAmount(rentalPriceAmt);
									value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
									
									if (catalogueIdList.contains(productCatalogueId+"_"+materialItemCode) && rentalPriceAmt != null) {
										priceCatalogueItemMap.put(productCatalogueId+"_"+materialItemCode, value);
									}
								}
							}

							//4b. Purchase Equipment List
							List<WirelineEquipmentItem> buyEquipmentItemList = wirelineEquipment.getPurchaseEquipmentList();
							if (CollectionUtils.isNotEmpty(buyEquipmentItemList)) {
								for (WirelineEquipmentItem wirelineEquipmentItem : buyEquipmentItemList) {
									String materialItemCode = wirelineEquipmentItem.getMaterialItemCode();
									//String modelName = ((SalesWirelineEquipmentItem)wirelineEquipmentItem).getModelName();
									List<String> deliveryMethodList = wirelineEquipmentItem.getDeliveryMethodList();
									Double purchasePriceAmt = wirelineEquipmentItem.getEquipmentPurchasePriceAmt();
									
									PriceHolder value = new PriceHolder();
									value.setAmount(purchasePriceAmt); 
									
									//NWLN-10248 setting paymentOption List for accessory
									if(EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType)){
										List<PaymentOption> paymentOptionList = wirelineEquipmentItem.getPaymentOptionList();
										//For one time payment
										value.setPaymentOptionList(paymentOptionList); 			
									
										//NWLN-10248 add finance installment equipment to priceCatalogMap - Handle offers with multiple payment options - add Finance payment
										if(wirelineEquipmentItem.getPaymentOptionList() != null) {
											for(PaymentOption paymentOption : paymentOptionList) {
												if(paymentOption.getPaymentInstallmentQty() != null){
													//create a Price holder for each finance payment option
													PriceHolder financeValue = new PriceHolder();
													financeValue.setAmount(purchasePriceAmt); 
													int installmentQty = paymentOption.getPaymentInstallmentQty().intValue();
													financeValue.setPaymentOptionList(Arrays.asList(paymentOption));
													priceCatalogueItemMap.put(productCatalogueId+"_"+materialItemCode+"_"+installmentQty, financeValue);
												}
											}
										}
									}
									
									value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_ONE_TIME);	
									
									if (catalogueIdList.contains(productCatalogueId+"_"+materialItemCode) && purchasePriceAmt != null) {
										priceCatalogueItemMap.put(productCatalogueId+"_"+materialItemCode, value);
									}
								}
							}
						}
					}
					//5. Feeatures
					if (wirelineOfferProduct.getFeature() != null) {
						if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getFeature().getIncludedProductCatalogueItemList())) {
							for (ProductCatalogueItem catalogueItem : wirelineOfferProduct.getFeature().getIncludedProductCatalogueItemList()) {
								String catalogueId = catalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
								Double basePrice = catalogueItem.getProductCatalogueBasePriceAmt();
								
								PriceHolder value = new PriceHolder();
								if(catalogueItem.isPackEligibleItemInd() != null) {
									value.setPackeligibleInd(catalogueItem.isPackEligibleItemInd());
								}
								
								value.setAmount(basePrice);
								//No recurrenceCount for addon, feature and product component, always set to PRICING_TYPE_RECURRING.
								value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
								
								if(StringUtils.isNotBlank(catalogueItem.getServiceTypeCode())) {
									value.setSalesCategoryServiceTypeCd(catalogueItem.getServiceTypeCode()); //NWLN-7789 - for price service only
								}
								if (catalogueIdList.contains(catalogueId) && basePrice != null) {
									priceCatalogueItemMap.put(catalogueId, value);
								}
							}
						}
						if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getFeature().getOptionalProductCatalogueItemList())) {
							for (ProductCatalogueItem catalogueItem: wirelineOfferProduct.getFeature().getOptionalProductCatalogueItemList()) {
								String catalogueId = catalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
								Double basePrice = catalogueItem.getProductCatalogueBasePriceAmt();
								
								PriceHolder value = new PriceHolder();
								if(catalogueItem.isPackEligibleItemInd() != null) {
									value.setPackeligibleInd(catalogueItem.isPackEligibleItemInd());
								}
								value.setAmount(basePrice);
								//No recurrenceCount for addon, feature and product component, always set to PRICING_TYPE_RECURRING.
								value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);
								
								if(StringUtils.isNotBlank(catalogueItem.getServiceTypeCode())) {
									value.setSalesCategoryServiceTypeCd(catalogueItem.getServiceTypeCode()); //NWLN-7789 - for price service only
								}
								if (catalogueIdList.contains(catalogueId) && basePrice != null) {
									priceCatalogueItemMap.put(catalogueId, value);
								}
							}
						}
					}
					
					
				}
			}
		}

		// 5. Sweetener Discount
		if (sweeteners != null && !sweeteners.isEmpty()) {
			for (SweetenerOfferSummary sweeterSummary : sweeteners) {
				for (WirelineOfferProductSummary offerProdSummary : sweeterSummary.getOfferProductSummary()
						.getWirelineOfferProductSummaryList()) {
					for (Discount discount : offerProdSummary.getDiscountList()) {
						for (DiscountProductCatalogueItem discountProdCatalgItem : discount
								.getDiscountProductCatalogueItemList()) {
							//String catalogueId = discountProdCatalgItem.getProductCatalogueIdentifier().getProductCatalogueId();
							//qc-69953
							String catalogueId = null;
							
							if(discountProdCatalgItem.getProductCatalogueIdentifier() != null 
									&& discountProdCatalgItem.getParentProductCatalogueIdentifier() != null) {
								catalogueId = createProductItemIdForDiscount(discountProdCatalgItem.getProductCatalogueIdentifier().getProductCatalogueId(), discountProdCatalgItem.getParentProductCatalogueIdentifier().getProductCatalogueId());
							}
							
							Double discountPrice = discountProdCatalgItem.getDiscountPriceAmt();

							//qc-72146 - add a condition, if discountPrice doesn't exist or 0, then don't return its price plan.
							if (discountPrice != null && discountPrice.doubleValue() != 0) {
							PriceHolder value = new PriceHolder();
							value.setAmount(discountPrice);

							if (discountProdCatalgItem.getRecurringCount() != null
									&& discountProdCatalgItem.getRecurringCount().intValue() == 0)
								value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_ONE_TIME);
							else
								value.setPricingType(EnterpriseWLNSalesServicesConstants.PRICING_TYPE_RECURRING);

							if (discountProdCatalgItem.getRecurringCount() != null) {
								value.setRecurrenceCount(discountProdCatalgItem.getRecurringCount().intValue());
							}
							//qc-72406 add missing discountTypeCode to sweetener
							if (discountProdCatalgItem.getDiscountType() != null) {
								value.setDiscountTypeCode(discountProdCatalgItem.getDiscountType().getDiscountTypeCode());
							}

							if (catalogueIdList.contains(catalogueId)) {
								priceCatalogueItemMap.put(catalogueId, value);
							}
						}
						}
					}
				}
			}
		}

		return priceCatalogueItemMap;
	}
	
	/**
	 * retrieveEquipItemCatalogueItems
	 * @param String productType
	 * @param Lit<String> catalogueIdList
	 * @param GetSalesOfferDetailResponseVO salesOfferDetailVO
	 * @return Map<String,FFHEquipmentItem>
	 */
	private Map<String,FFHEquipmentItemVO> retrieveEquipItemCatalogueItems(String productType, List<String> catalogueIdList, GetSalesOfferDetailResponseVO2 salesOfferDetailVO) {
		Map<String,FFHEquipmentItemVO> equipmentCatalogueItemMap = new HashMap<String,FFHEquipmentItemVO>();
		if (salesOfferDetailVO != null && salesOfferDetailVO.getOffer() != null && salesOfferDetailVO.getOffer().getOfferProduct() != null) {
			for (WirelineOfferProduct wirelineOfferProduct : salesOfferDetailVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
				if (wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(productType)) {
					if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getWirelineEquipmentList())) {
						for (WirelineEquipment wirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()) {
							String productCatalogueId = wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();

							//Rental Equipment List
							List<WirelineEquipmentItem> rentEquipmentItemList = wirelineEquipment.getRentalEquipmentList();
							if (CollectionUtils.isNotEmpty(rentEquipmentItemList)) {
								for (WirelineEquipmentItem wirelineEquipmentItem : rentEquipmentItemList) {
									String materialItemCode = wirelineEquipmentItem.getMaterialItemCode();
									//String modelName = ((SalesWirelineEquipmentItem)wirelineEquipmentItem).getModelName();				
									List<String> deliveryMethodList = wirelineEquipmentItem.getDeliveryMethodList();
									if (catalogueIdList.contains(productCatalogueId+"_"+materialItemCode)) {
										FFHEquipmentItemVO equipmentItem = new FFHEquipmentItemVO();
										equipmentItem.setDeliveryMethods(deliveryMethodList);
										//equipmentItem.setModelName(modelName);
										equipmentCatalogueItemMap.put(productCatalogueId+"_"+materialItemCode, equipmentItem);
									}
								}
							}

							//Purchase Equipment List
							List<WirelineEquipmentItem> buyEquipmentItemList = wirelineEquipment.getPurchaseEquipmentList();
							if (CollectionUtils.isNotEmpty(buyEquipmentItemList)) {
								for (WirelineEquipmentItem wirelineEquipmentItem : buyEquipmentItemList) {
									String materialItemCode = wirelineEquipmentItem.getMaterialItemCode();
									//String modelName = ((SalesWirelineEquipmentItem)wirelineEquipmentItem).getModelName();
									List<String> deliveryMethodList = wirelineEquipmentItem.getDeliveryMethodList();
									if (catalogueIdList.contains(productCatalogueId+"_"+materialItemCode)) {
										FFHEquipmentItemVO equipmentItem = new FFHEquipmentItemVO();
										equipmentItem.setDeliveryMethods(deliveryMethodList);
										//equipmentItem.setModelName(modelName);
										equipmentCatalogueItemMap.put(productCatalogueId+"_"+materialItemCode, equipmentItem);
									}
								}
							}
						}
					}
				}
			}
		}
		return equipmentCatalogueItemMap;
	}


	/**
	 * transformMultilingual
	 * @param String locale
	 * @param String description
	 * @return
	 */
	private List<MultilingualTextVO> transformMultilingual(String locale, String description){
		List<MultilingualTextVO> multilingualTextList = new ArrayList<MultilingualTextVO>();

		if (description != null){
			MultilingualTextVO multilingualText = new MultilingualTextVO();
			multilingualText.setLocaleTxt(locale);
			multilingualText.setValueTxt(description);
			multilingualTextList.add(multilingualText);
		}

		return multilingualTextList;
	}


	private void buildAssociationBetwnProdIems(List<ProductItemVO> productItems, Map<String, ProductItemVO> prodItemsMap) {
		List<ProductItemVO> associatedProductItemList = new ArrayList<ProductItemVO>();
		for (ProductItemVO prodItem : productItems) {
			// Only Discounts can be an Associated Item
			if (prodItem.getMarketOfferClassification() != null &&
				prodItem.getMarketOfferClassification().isDiscountInd() != null &&
				prodItem.getMarketOfferClassification().isDiscountInd()) {
				ProductItemVO parentProdItem = prodItemsMap.get(prodItem.getProductItemIdentifier().getParentProductCatalogueId());
				if (parentProdItem != null ) {
					parentProdItem.addAssociatedProductItemsItem(this.createAssocProdItemFromProductItem(prodItemsMap.get(createProductItemIdForDiscount(prodItem.getProductItemIdentifier().getProductCatalogueId(), prodItem.getProductItemIdentifier().getParentProductCatalogueId())))); //QC-69953
					associatedProductItemList.add(prodItem);
					
					//preselected depending on AssociatedProductItem --- if a productItem has any associatedProductitems 
					//that are includedInd=true or mandatoryInd=true or preselectedInd=true, then set preselectedInd=true.
					if ((prodItem.isIncludedInd() != null && prodItem.isIncludedInd()) || (prodItem.isMandatoryInd() != null && prodItem.isMandatoryInd()) || (prodItem.isPreSelectedInd() != null && prodItem.isPreSelectedInd()))
						parentProdItem.setPreSelectedInd(true);					
				}
			}
		}
		//Remove ProductItems that are Associated from the top-level list
		if (CollectionUtils.isNotEmpty(associatedProductItemList)) {
			productItems.removeAll(associatedProductItemList);
		}
	}

	private void buildAssociationBetwnEquipProdItems(List<FFHEquipmentItemVO> equipmentItems, List<ProductItemVO> productItems, Map<String, FFHEquipmentItemVO> equipItemsMap, String productType) {
		List<ProductItemVO> associatedProductItemList = new ArrayList<ProductItemVO>();
		for (ProductItemVO prodItem : productItems) {
			// Only Discounts can be an Associated Item
			if (prodItem.getMarketOfferClassification() != null &&
				prodItem.getMarketOfferClassification().isDiscountInd() != null &&
				prodItem.getMarketOfferClassification().isDiscountInd()) {
				List<FFHEquipmentItemVO> parentEquipItems = extractEquipmentstWithSameCatalogueId(prodItem.getProductItemIdentifier().getParentProductCatalogueId(), equipItemsMap);
				for (FFHEquipmentItemVO parentEquipmentItem : parentEquipItems) {
					parentEquipmentItem.addAssociatedProductItemsItem(this.createAssocProdItemFromProductItem(prodItem));
					associatedProductItemList.add(prodItem);
				}
			}
		}
		//Remove ProductItems that are Associated from the top-level list
		if (CollectionUtils.isNotEmpty(associatedProductItemList)) {
			productItems.removeAll(associatedProductItemList);
		}
		
		for (final FFHEquipmentItemVO elem : equipmentItems) {
			//NWLN-10248 - build key depending on finance option
			String key;
			if(elem.getEquipmentPrice() != null && elem.getEquipmentPrice().getInstallmentOptions() != null && elem.getEquipmentPrice().getInstallmentOptions().size() > 0 
					&& elem.getEquipmentPrice().getInstallmentOptions().get(0)!= null && elem.getEquipmentPrice().getInstallmentOptions().get(0).getNumberOfInstallments()!= null) {
				int numberOfInstallments = elem.getEquipmentPrice().getInstallmentOptions().get(0).getNumberOfInstallments();
				key = elem.getProductItemIdentifier().getProductCatalogueId() + "_" + elem.getMaterialItemCode() +"_"+ numberOfInstallments;
			}else {
				key = elem.getProductItemIdentifier().getProductCatalogueId() + "_" + elem.getMaterialItemCode();
			}
			FFHEquipmentItemVO vo = equipItemsMap.get(key);
			
			//NWLN-10248 populate accessory equipment
			if (elem.getEquipmentPrice() == null || productType.equalsIgnoreCase(OIS_ACCESSORIES_CD)) {
				elem.setEquipmentPrice(vo.getEquipmentPrice());
			} 
			if (elem.getEquipmentPriceDiscount() == null || productType.equalsIgnoreCase(OIS_ACCESSORIES_CD)) {
				elem.setEquipmentPriceDiscount(vo.getEquipmentPriceDiscount());
			}
		}
	}
	
	private List<FFHEquipmentItemVO> extractEquipmentstWithSameCatalogueId(String catalogueId, Map<String,FFHEquipmentItemVO> equipItemsMap) {
		List<FFHEquipmentItemVO> equipmentItemList = new ArrayList<FFHEquipmentItemVO>();
		Iterator<String> it = equipItemsMap.keySet().iterator();
		while (it.hasNext()) {
			String catalogueId_mic = it.next();
			if (catalogueId_mic.indexOf(catalogueId) > -1) {
				equipmentItemList.add(equipItemsMap.get(catalogueId_mic));
			}
		}
		return equipmentItemList;
	}

	private AssociatedProductItemVO createAssocProdItemFromProductItem(ProductItemVO prodItem) {
		AssociatedProductItemVO associtatedProdItem = new AssociatedProductItemVO();

		associtatedProdItem.setProductItemIdentifier(prodItem.getProductItemIdentifier());
		associtatedProdItem.setDiscountCode(prodItem.getDiscountCode());
		associtatedProdItem.setIncludedInd(prodItem.isIncludedInd());
		associtatedProdItem.setMandatoryInd(prodItem.isMandatoryInd());
		associtatedProdItem.setMarketOfferClassification(prodItem.getMarketOfferClassification());
		associtatedProdItem.setPreSelectedInd(prodItem.isPreSelectedInd());
		associtatedProdItem.setProductCatalogueDescription(prodItem.getProductCatalogueDescription());
		associtatedProdItem.setProductCatalogueName(prodItem.getProductCatalogueName());
		associtatedProdItem.setProductItemPriceCharge(prodItem.getProductItemPriceCharge());
		associtatedProdItem.setProductItemPriceDiscount(prodItem.getProductItemPriceDiscount());
		//associtatedProdItem

		return associtatedProdItem;
	}

	
	private CatalogueItemDO getCatalogItemById(String productCatalogueIdentifier){

		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		CatalogueItemDO result = gridHelper.getCatalogueItemById(productCatalogueIdentifier);

		//TODO temp code
		if (result == null) {
			result = new CatalogueItemDO();
			result.setCatalogueItemId(productCatalogueIdentifier);
		}

		return result;
	}
	
	class PriceHolder {
		Double amount;
		Integer recurrenceCount;
		String pricingType;
		String discountTypeCode;
		boolean isPackeligibleInd;
		String salesCategoryServiceTypeCd; //NWLN-7789 - for price service only
		BigInteger itemRankNum;
		String promotionId;
		Date promotionPerspectiveDate;
		//NWLN-10248
		List<PaymentOption> paymentOptionList;		
		public List<PaymentOption> getPaymentOptionList() {
			return paymentOptionList;
		}
		public void setPaymentOptionList(List<PaymentOption> paymentOptionList) {
			this.paymentOptionList = paymentOptionList;
		}		
		public String getDownPaymentAmount() {
			return downPaymentAmount;
		}
		public void setDownPaymentAmount(String downPaymentAmount) {
			this.downPaymentAmount = downPaymentAmount;
		}
		String downPaymentAmount;
		
		
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Integer getRecurrenceCount() {
			return recurrenceCount;
		}
		public void setRecurrenceCount(Integer recurrenceCount) {
			this.recurrenceCount = recurrenceCount;
		}
		public String getPricingType() {
			return pricingType;
		}
		public void setPricingType(String pricingType) {
			this.pricingType = pricingType;
		}
		public String getDiscountTypeCode() {
			return discountTypeCode;
		}
		public void setDiscountTypeCode(String discountTypeCode) {
			this.discountTypeCode = discountTypeCode;
		}
		public boolean isPackeligibleInd() {
			return isPackeligibleInd;
		}
		public void setPackeligibleInd(boolean isPackeligibleInd) {
			this.isPackeligibleInd = isPackeligibleInd;
		}

		public BigInteger getItemRankNum() {
			return itemRankNum;
		}
		public void setItemRankNum(BigInteger itemRankNum) {
			this.itemRankNum = itemRankNum;
		}
		public String getSalesCategoryServiceTypeCd() {
			return salesCategoryServiceTypeCd;
		}
		public void setSalesCategoryServiceTypeCd(String salesCategoryServiceTypeCd) {
			this.salesCategoryServiceTypeCd = salesCategoryServiceTypeCd;
		}
		public String getPromotionId() {
			return promotionId;
		}
		public void setPromotionId(String promotionId) {
			this.promotionId = promotionId;
		}
		public Date getPromotionPerspectiveDate() {
			return promotionPerspectiveDate;
		}
		public void setPromotionPerspectiveDate(Date promotionPerspectiveDate) {
			this.promotionPerspectiveDate = promotionPerspectiveDate;
		}	
	
	}
	
	private List<String> getProductComponentServiceConstraintCodes(List<ProductItemVO> productItems) {
		List<String> serviceConstraintCodes = new ArrayList<String>();
	
		// All Product Components will have the same ServiceConstraintVO List
		for (ProductItemVO prodItem : productItems) {
			if (isProductComponent(prodItem)) {
				for (ServiceConstraintVO serviceConstraint : prodItem.getServiceConstraints() == null ? Collections.<ServiceConstraintVO>emptyList() : prodItem.getServiceConstraints()) {
					for (String serviceTypeCode : serviceConstraint.getServiceTypeCodeList() == null ? Collections.<String>emptyList() : serviceConstraint.getServiceTypeCodeList()) {
						serviceConstraintCodes.add(serviceTypeCode);
					}
				}
			}			
		}
		return serviceConstraintCodes;
	}
		
	private boolean isProductComponent(ProductItemVO prodItem) {
		if (prodItem.getMarketOfferClassification() == null) {
			return false;
		}
		if (prodItem.getMarketOfferClassification().isProductComponentInd() != null &&
			prodItem.getMarketOfferClassification().isProductComponentInd()) {
			return true;
		}
		return false;
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
	
	//QC-69953
	private String createProductItemIdForDiscount(String productId, String parentProductId) {
		return productId + "_" + parentProductId;
	}

}
