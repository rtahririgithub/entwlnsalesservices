package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.domain.CharacteristicVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.InstallmentOptionVO;
import com.telus.csm.ewlnsc.domain.MoneyVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.product.AvailableAccessoryProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.AvailableHomePhoneProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableHomeSecurityProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableInternetProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableProductItemResponseVO;
import com.telus.csm.ewlnsc.domain.product.AvailableTelevisionProductItemVO;
import com.telus.csm.ewlnsc.domain.product.ContextAttributeVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.GetAvailableProductItemErrorResponseVO;
import com.telus.csm.ewlnsc.domain.product.MarketOfferClassificationVO;
import com.telus.csm.ewlnsc.domain.product.MessageTypeVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.QuantityAllowedVO;
import com.telus.csm.ewlnsc.domain.product.ResponseMessageVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.PaymentOption;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.domain.schemasclone.ProductCatalogueItemVO2;
import com.telus.csm.ewlnsc.helper.ShoppingCartHelper;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;

public class GetAvailableProductItemCoreTransformer {
	
	private final String ACQUISITION_TYPE_RENT = "RENT";
	private final String ACQUISITION_TYPE_BUY = "BUY";
	private final String ACQUISITION_TYPE_BYOD = "BYOD";
	private final String PRICING_TYPE_RECURRING = "RECURRING";
	private final String PRICING_TYPE_ONE_TIME = "ONE_TIME"; 
	private final String PRICE_TYPE_DISCOUNT = "DISCOUNT";
	
	private String flow;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableProductItemCoreTransformer.class);
	
	public GetAvailableProductItemDelegateResponse transform(String prodType
												  , Offer offer
												  , List<SweetenerOfferSummary> sweeteners, boolean isLiketoLikeRecontract, ShoppingCartContextVO shoppingCartContextVO){
		
		GetAvailableProductItemDelegateResponse result = new GetAvailableProductItemDelegateResponse();
		
		//Validate VO and DO and return an error ResponseMessage if there is any error in CoreResponseBase or DO.
		GetAvailableProductItemErrorResponseVO errResp = new GetAvailableProductItemErrorResponseVO();
		if (offer == null) {
			errResp.addResponseMessagesItem(this.createRespMsg("5001", "ERROR", "EN", "GetSalesOfferDetailResponseVO is null", null));
		}

		if (CollectionUtils.isNotEmpty(errResp.getResponseMessages())) {
			result.setErrorResponse(errResp);
			return result;
		}
		
		AvailableProductItemResponseVO response = new AvailableProductItemResponseVO();
		result.setResponse(response);

		if (offer.getOfferProduct() != null 
				&& CollectionUtils.isNotEmpty(offer.getOfferProduct().getWirelineOfferProductList())) {
			
			AvailableProductItemHolder holder = transformProductItemList(prodType, offer, sweeteners, isLiketoLikeRecontract, shoppingCartContextVO);		
			result.setProductItems(holder.getProductItems());

			//HSIC
			if (prodType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.HSIC)) {
				List<AvailableInternetProductItemVO> availProdItems = new ArrayList<AvailableInternetProductItemVO>();
				AvailableInternetProductItemVO availableProductItem = new AvailableInternetProductItemVO();
				availableProductItem.setEquipments(holder.getEquipments());								
				availProdItems.add(availableProductItem);
				response.setInternetProductItems(availProdItems);
			} 
	
			//TTV
			if (prodType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.TTV)) {
				List<AvailableTelevisionProductItemVO> availProdItems = new ArrayList<AvailableTelevisionProductItemVO>();
				AvailableTelevisionProductItemVO availableProductItem = new AvailableTelevisionProductItemVO();
				availableProductItem.setEquipments(holder.getEquipments());								
				availProdItems.add(availableProductItem);
				response.setTelevisionProductItems(availProdItems);
			}
			
			//SING
			if (prodType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SING)) {
				List<AvailableHomePhoneProductItemVO> availProdItems = new ArrayList<AvailableHomePhoneProductItemVO>();
				AvailableHomePhoneProductItemVO availableProductItem = new AvailableHomePhoneProductItemVO();				
				availProdItems.add(availableProductItem);				
				response.setHomePhoneProductItems(availProdItems);
			}
			
			//CPE if the CPE offer holder has equipment, add them as one of accessory item in gAPI response
			if (prodType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD)) {
				List<AvailableAccessoryProductItemVO> availProdItems = new ArrayList<AvailableAccessoryProductItemVO>();
				AvailableAccessoryProductItemVO availableProductItem = new AvailableAccessoryProductItemVO();	
				availableProductItem.setEquipments(holder.getEquipments());
				availProdItems.add(availableProductItem);				
				response.setAccessoryProductItems(availProdItems);
			}
				 
			//FIFA-SHS
			if (prodType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.HOME_SECURITY)) {
				List<AvailableHomeSecurityProductItemVO> availProdItems = new ArrayList<AvailableHomeSecurityProductItemVO>();
				AvailableHomeSecurityProductItemVO availableProductItem = new AvailableHomeSecurityProductItemVO();
				availableProductItem.setEquipments(holder.getEquipments());								
				availProdItems.add(availableProductItem);
				response.setHomeSecurityProductItems(availProdItems);
			}
			
		}
				
		return result;
	}
	
	public ResponseMessageVO createRespMsg(String errCode, String errType, String locale, String msgTxt, String context) {
		ResponseMessageVO errMsg = new ResponseMessageVO();
		errMsg.setErrorCode(errCode);
		errMsg.setMessageTypeCd(errType);
		MessageTypeVO messagesItem = new MessageTypeVO();
		messagesItem.setLocaleCd(locale);
		messagesItem.setMessageTxt(msgTxt);
		errMsg.addMessagesItem(messagesItem);
		
		if (context != null) {
			ContextAttributeVO contextAttribute = new ContextAttributeVO();
			contextAttribute.setAttributeTypeTxt("Context");
			contextAttribute.setAttributeValueTxt(context);
			errMsg.addContextDataAttributesItem(contextAttribute);
		}
		
		if (errType.equalsIgnoreCase("WARN"))
			logger.warn("", errMsg.toString());
		else if (errType.equalsIgnoreCase("ERROR"))
			logger.error(errCode, "", errMsg.toString());
	
		return errMsg;
	}
	
	private QuantityAllowedVO transformQuantityAllowed(Integer minQty, Integer maxQty){
		QuantityAllowedVO quantityAllowed = new QuantityAllowedVO(); 
		 
		quantityAllowed.setMaximumQty(maxQty);
		quantityAllowed.setMinimumQty(minQty);
		 
		return quantityAllowed;
	}	
	
	/***************************************************/
	/*     transformDesciption                         */
	/*     equipmentItem has only one MultilingualText */
	/***************************************************/
	private List<MultilingualTextVO> transformDesciption(List<Description> descriptionList){ 
		List<MultilingualTextVO> multilingualTextList = new ArrayList<MultilingualTextVO>();		
		
		if(CollectionUtils.isNotEmpty(descriptionList)) {
			for (Description description : descriptionList){
				if (description != null){
					MultilingualTextVO multilingualText = new MultilingualTextVO();
					multilingualText.setLocaleTxt(description.getLocale());
					multilingualText.setValueTxt(description.getDescriptionText());			
					multilingualTextList.add(multilingualText);
				}				
			}
		}
		
		
		return multilingualTextList;
	}
	
	
	/***********************************************/
	/*     transformAvailableFFHEquipmentType      */
	/**
	 * @param shoppingCartContextVO *********************************************/
	private AvailableFFHEquipmentTypeVO transformAvailableFFHEquipmentType(String productCatalogueId,			                                                                  
			                                                                  String parentProductCatalogueId,
			                                                                  String materialItemCode,
			                                                                  String modelName,
																              boolean isIncludedInd,
																              List<Description> descriptionList,
																              List<String> deliveryMethodList,
																              Double purchasePriceAmt,
																              Double rentalPriceAmt,
																              Double returnPriceAmt,
																              String acquisitionType, List<PaymentOption> paymentOptionList,
																              List<CharacteristicVO> equipmentCharacteristic,
																              ShoppingCartContextVO shoppingCartContextVO)
    { 
		AvailableFFHEquipmentTypeVO equipmentType = new AvailableFFHEquipmentTypeVO();
		FFHEquipmentItemVO equipmentItem = new FFHEquipmentItemVO();
		
		equipmentItem.setDeliveryMethods(deliveryMethodList);
		equipmentItem.setIncludedInd(new Boolean(isIncludedInd).toString());
		equipmentItem.setMaterialItemCode(materialItemCode);
		if (StringUtils.isNotBlank(modelName)) {
			equipmentItem.setModelName(modelName);
		}
		
		ProductItemIdentifierVO productItemIdentifier = new ProductItemIdentifierVO();
		productItemIdentifier.setParentProductCatalogueId(parentProductCatalogueId);
		productItemIdentifier.setProductCatalogueId(productCatalogueId);		
		equipmentItem.setProductItemIdentifier(productItemIdentifier);
		
		equipmentItem.setEquipmentDescription(transformDesciption(descriptionList));
		
		equipmentItem.setCharacteristics(equipmentCharacteristic);
				
		PriceVO equipmentPrice = new PriceVO();
		MoneyVO basePriceAmount = new MoneyVO();
		basePriceAmount.setValueAmt(new Float("0").floatValue());
		
		if (ACQUISITION_TYPE_BUY.equalsIgnoreCase(acquisitionType)) {
			equipmentPrice.setPricingTypeCd(PRICING_TYPE_ONE_TIME);			
			if (purchasePriceAmt != null) {
				basePriceAmount.setValueAmt(purchasePriceAmt.floatValue());
			} 
			if (equipmentType.getBuyEquipmentList() == null) {
				equipmentType.setBuyEquipmentList(new ArrayList<FFHEquipmentItemVO>());
			}
			equipmentType.getBuyEquipmentList().add(equipmentItem);
		} else if (ACQUISITION_TYPE_RENT.equalsIgnoreCase(acquisitionType)){ 
			equipmentPrice.setPricingTypeCd(PRICING_TYPE_RECURRING);
			if (rentalPriceAmt != null) {
				basePriceAmount.setValueAmt(rentalPriceAmt.floatValue());
			} 
			if (equipmentType.getRentalEquipmentList() == null) {
				equipmentType.setRentalEquipmentList(new ArrayList<FFHEquipmentItemVO>());
			}
			equipmentType.getRentalEquipmentList().add(equipmentItem);
		} else {
			if (equipmentType.getClientOwnedEquipmentList() == null) {
				equipmentType.setClientOwnedEquipmentList(new ArrayList<FFHEquipmentItemVO>());
			}
			equipmentType.getClientOwnedEquipmentList().add(equipmentItem);
		}
		equipmentPrice.setBasePriceAmount(basePriceAmount);
		
		
		//NWLN-10255 IntallmentOption convert from offer to gAPI response
		List<InstallmentOptionVO> installmentOptionList = new ArrayList<InstallmentOptionVO>();
		for(PaymentOption paymentOption: paymentOptionList) {
			// Only add finance payment option to installment option, down payment is 0
			if(EnterpriseWLNSalesServicesConstants.PAYMENT_OPTION_TYPE_CD_FINANCE.equals(paymentOption.getPaymentOptionTypeCd())) {
				MoneyVO downPaymentAmount = new MoneyVO();
				downPaymentAmount.setValueAmt(0f); //zero since offer don't have downpayment now

				InstallmentOptionVO installmentOption = new InstallmentOptionVO();
				installmentOption.setInstallmentOptionCd(EnterpriseWLNSalesServicesConstants.INSTALLMENT_OPTION_CD_OPTIONAL);
				installmentOption.setDownPaymentAmount(downPaymentAmount);
				
				if(paymentOption.getPaymentInstallmentQty() != null)
					installmentOption.setNumberOfInstallments(paymentOption.getPaymentInstallmentQty().intValue());
				
				if(paymentOption.getPaymentInstallmentAmt() != null) {
					MoneyVO installmentAmount = new MoneyVO();
					installmentAmount.setValueAmt(paymentOption.getPaymentInstallmentAmt().floatValue());
					installmentOption.setInstallmentAmount(installmentAmount);
				}

				installmentOptionList.add(installmentOption);
			}
		}
		if(!installmentOptionList.isEmpty())
			equipmentPrice.setInstallmentOptions(installmentOptionList);
		
		equipmentItem.setEquipmentPrice(equipmentPrice);
		
		return equipmentType;
	}
	
	
	/***********************************************/
	/*      Transform Product Item                 */
	/***********************************************/
	public AvailableProductItemHolder transformProductItemList(String productType
											, Offer offer
											, List<SweetenerOfferSummary> sweeteners, boolean isLiketoLikeRecontract, ShoppingCartContextVO shoppingCartContextVO) {
				
		List<ProductItemVO> productItems = new ArrayList<ProductItemVO>();
		List<AvailableFFHEquipmentTypeVO> equipments = new ArrayList<AvailableFFHEquipmentTypeVO>();
			
		for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()){
			if (wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(productType)) {
				//Build product component item.
				if(!isLiketoLikeRecontract) {
					for (ProductComponent prodComponent : wirelineOfferProduct.getProductComponentList()) {					
						for (ProductCatalogueItem catalogueItem : prodComponent.getProductCatalogueItemList()) {						
							ProductItemVO prodItem = this.createProductItemForCatalogue(catalogueItem, true, false, true, false);
							prodItem.setProductCatalogueDescription(this.transformDesciption(prodComponent.getMarketingDescriptionList()));
							//FIFA-SHS
							if(EnterpriseWLNSalesServicesConstants.HOME_SECURITY.equals( productType )) {
								prodItem.setCharacteristics( prodComponent.getCharacteristics() );
							}

							productItems.add(prodItem);
						}					
					}
				}
			
				
				//Build discount items
				if (CollectionUtils.isNotEmpty(wirelineOfferProduct.getDiscountList())) {
					List<String> filteredOutDiscountCodeListIsNonStackable = new ArrayList<String>(); 
					List<String> filteredOutDiscountCodeListNonStackableInCart = new ArrayList<String>();//shopping cart has nonstackable already
					List<String> filteredOutDiscountCodeListInCompatible = new ArrayList<String>();
					
					//NWLN-7957
					List<ProductCatalogueIdentifier> inCompatibleDiscountList = ShoppingCartHelper.getInCompatibleDiscountList(shoppingCartContextVO);
					
					//NWLN-7815
					boolean isExistingDiscountInShoppingCart = false;
					Discount existingNonStackableDiscount = null;
					List<FFHDiscountTypeVO> existingDiscountListInShoppingCart = ShoppingCartHelper.getExistingDiscountListInShoppingCart(shoppingCartContextVO, wirelineOfferProduct.getProductTypeCode());
					
					if (CollectionUtils.isNotEmpty(existingDiscountListInShoppingCart)) {
						isExistingDiscountInShoppingCart = true;
						for (FFHDiscountTypeVO existingDisount : existingDiscountListInShoppingCart) {
							for (Discount discount : wirelineOfferProduct.getDiscountList()) {
								if(existingDisount.getDiscountCode().equalsIgnoreCase(discount.getDiscountCode()) && EnterpriseWLNCommonTransformer.catalogueIdMatches(discount,existingDisount)) {
									if(!discount.isStackableInd()) {
										existingNonStackableDiscount = discount;
										break;
									}
								}
							}
						}
					}
					
					if(existingNonStackableDiscount==null) { //NWLN-7815 As discussed with Martin, this filter rule apply to non ADDON discountComponentTypes(PRODUCT and EQUIPMENT). 
						for (Discount discount : wirelineOfferProduct.getDiscountList()) {
							if(!isExistingDiscountInShoppingCart || 
									!EnterpriseWLNSalesServicesConstants.DISCOUNT_TYPE_CD_ADD_ON.equals(discount.getDiscountedComponentTypeCd()) //don't apply to non ADDON
									|| (isExistingDiscountInShoppingCart && discount.isStackableInd())) { //NWLN-7815
								if(!isDiscountInCompatible(discount,inCompatibleDiscountList)) { //NWLN-7957
										List<ProductItemVO> prodItems = this.buildProdItemsByDiscount(discount, false, isLiketoLikeRecontract, shoppingCartContextVO);
										if(!prodItems.isEmpty()) {
											//this discount should be returned 
											productItems.addAll(prodItems);
										}
								}else {
									filteredOutDiscountCodeListInCompatible.add(discount.getDiscountCode());
								}
							} else {
								//for logging anything addon filtered
								filteredOutDiscountCodeListIsNonStackable.add(discount.getDiscountCode());
							}
						}							
					}else {
						for (Discount discount : wirelineOfferProduct.getDiscountList()) {
							if(!discount.getDiscountCode().equalsIgnoreCase(existingNonStackableDiscount.getDiscountCode()) && 
									(EnterpriseWLNSalesServicesConstants.DISCOUNT_TYPE_CD_ADD_ON.equals(discount.getDiscountedComponentTypeCd()) || isCPEDiscount(discount.getProductCriteriaValue()))) {
								//filter out every addon discount since there is none stackable in shopping cart
								//NWLN-10301 CPE promotion need to filter out too
								filteredOutDiscountCodeListNonStackableInCart.add(discount.getDiscountCode());
							}else {
								List<ProductItemVO> prodItems = this.buildProdItemsByDiscount(discount, false, isLiketoLikeRecontract, shoppingCartContextVO);
								if(!prodItems.isEmpty()) {
									productItems.addAll(prodItems);
								}								
							}
						}
					}

					if(CollectionUtils.isNotEmpty(filteredOutDiscountCodeListNonStackableInCart)) {
						logger.debug("Filter out add-on promotion due to there is an existing non-stackable promotion in the Cart ", "The Filtered out Discount Code List" + ": " + filteredOutDiscountCodeListNonStackableInCart);
					}
					if(CollectionUtils.isNotEmpty(filteredOutDiscountCodeListIsNonStackable)) {
						logger.debug("Filter out add-on promotion due to it is a non-stackable promotion ", "The Filtered out Discount Code List" + ": " + filteredOutDiscountCodeListIsNonStackable);
					}
					if(CollectionUtils.isNotEmpty(filteredOutDiscountCodeListInCompatible)) {
						logger.debug("Filter out add-on promotion due to it is not compatible with GWP in the Cart ", "The Filtered out Discount Code List" + ": " + filteredOutDiscountCodeListInCompatible);
					}
				}
				
				
			
				//Build equipments.
				for (WirelineEquipment wirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()){
					//qc-72141 to filter out BOOST WIFI STARTER AND EXPANSION equipments.
					//NWLN-10255 skip Boost Wifi from HS offer, since it will be in accessory offer
					//reformat the line and add limitation to only HS product so that CPE product will bypass the logic
					if (productType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.HSIC)
							&& (EnterpriseWLNSalesServicesConstants.BOOST_WIFI_STARTER_PRODUCT_CATALOGUE_ID
									.equalsIgnoreCase(wirelineEquipment.getProductCatalogueItem()
											.getProductCatalogueIdentifier().getProductCatalogueId())
									|| EnterpriseWLNSalesServicesConstants.BOOST_WIFI_EXPANSION_PRODUCT_CATALOGUE_ID
											.equalsIgnoreCase(wirelineEquipment.getProductCatalogueItem()
													.getProductCatalogueIdentifier().getProductCatalogueId()))) {
						continue;
					}
					
					String productCatalogueId       = wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();
					String parentProductCatalogueId = wirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier().getProductCatalogueId();
					
					AvailableFFHEquipmentTypeVO equipmentMain = new AvailableFFHEquipmentTypeVO();					
					Integer minQty = new Integer(wirelineEquipment.getMinQty().intValue());
					Integer maxQty = new Integer(wirelineEquipment.getMaxQty().intValue());
					QuantityAllowedVO quantityAllowed =transformQuantityAllowed(minQty, maxQty);
					equipmentMain.setQuantityAllowed(quantityAllowed);
					equipments.add(equipmentMain);
					
					Map<String,List<WirelineEquipmentItem>> equipmentItemListMap = new HashMap<String,List<WirelineEquipmentItem>>();
					List<WirelineEquipmentItem> rentEquipmentItemList = wirelineEquipment.getRentalEquipmentList();
					if (CollectionUtils.isNotEmpty(rentEquipmentItemList)) {
						equipmentItemListMap.put(ACQUISITION_TYPE_RENT, rentEquipmentItemList);
						equipmentMain.setRentalEquipmentList(new ArrayList<FFHEquipmentItemVO>());
					}
					List<WirelineEquipmentItem> buyEquipmentList = wirelineEquipment.getPurchaseEquipmentList();
					if (CollectionUtils.isNotEmpty(buyEquipmentList)) {
						equipmentItemListMap.put(ACQUISITION_TYPE_BUY, buyEquipmentList);
						equipmentMain.setBuyEquipmentList(new ArrayList<FFHEquipmentItemVO>());
					}
					List<WirelineEquipmentItem> byodEquipmentList = wirelineEquipment.getByodEquipmentList();
					if (CollectionUtils.isNotEmpty(byodEquipmentList)) {
						equipmentItemListMap.put(ACQUISITION_TYPE_BYOD, byodEquipmentList);
						equipmentMain.setClientOwnedEquipmentList(new ArrayList<FFHEquipmentItemVO>());
					}
					
					Iterator<String> it = equipmentItemListMap.keySet().iterator();
					while (it.hasNext()) {
						String acquisitionType = it.next();
						List<WirelineEquipmentItem> wirelineEquipmentItemList = equipmentItemListMap.get(acquisitionType);
						for (WirelineEquipmentItem wirelineEquipmentItem : wirelineEquipmentItemList) {
						
							String materialItemCode           = wirelineEquipmentItem.getMaterialItemCode();
							String modelName				  = ""; //((SalesWirelineEquipmentItem)wirelineEquipmentItem).getModelName();
							boolean isIncludedInd             = wirelineEquipmentItem.isIncludedInd();
							List<Description> descriptionList = wirelineEquipmentItem.getEquipmentDescriptionList();
							List<String> deliveryMethodList   = wirelineEquipmentItem.getDeliveryMethodList();
							Double purchasePriceAmt           = wirelineEquipmentItem.getEquipmentPurchasePriceAmt();
							Double rentalPriceAmt             = wirelineEquipmentItem.getEquipmentRentalPriceAmt();
							Double returnPriceAmt             = wirelineEquipmentItem.getEquipmentLateReturnPriceAmt();	
							List<PaymentOption> paymentOptionList = wirelineEquipmentItem.getPaymentOptionList();
							//FIFA-SHS
							List<CharacteristicVO> equipmentCharacteristic = wirelineEquipmentItem.getCharacteristics();
						
							AvailableFFHEquipmentTypeVO equipmentTemp = transformAvailableFFHEquipmentType(productCatalogueId,
					                                                                              parentProductCatalogueId,
					                                                                              materialItemCode,
					                                                                              modelName,
		                                                                                          isIncludedInd,
		                                                                                          descriptionList,
		                                                                                          deliveryMethodList,
		                                                                                          purchasePriceAmt,
		                                                                                          rentalPriceAmt,
		                                                                                          returnPriceAmt,
		                                                                                          acquisitionType,
		                                                                                          paymentOptionList,
		                                                                                          equipmentCharacteristic,//FIFA-SHS
		                                                                                          shoppingCartContextVO);
							if (ACQUISITION_TYPE_RENT.equals(acquisitionType)) {
								if (equipmentTemp.getRentalEquipmentList() != null) {
									equipmentMain.getRentalEquipmentList().addAll(equipmentTemp.getRentalEquipmentList());
								}
							} else if (ACQUISITION_TYPE_BUY.equals(acquisitionType)) {
								if (equipmentTemp.getBuyEquipmentList() != null) {
									equipmentMain.getBuyEquipmentList().addAll(equipmentTemp.getBuyEquipmentList());
								}
							} else if (ACQUISITION_TYPE_BYOD.equals(acquisitionType)) {
								if (equipmentTemp.getClientOwnedEquipmentList() != null) {
									equipmentMain.getClientOwnedEquipmentList().addAll(equipmentTemp.getClientOwnedEquipmentList());
								}
							} 
						}
					}								
				}
				
				//build ADDONs product item.
				if (wirelineOfferProduct.getAddOn() != null) {
					//return add-ons for HS recontract (still filter add-ons for TV recontract) - according to the email on 13/05/2019 with Ada
					List<ProductItemVO> addonProductItems = new ArrayList<ProductItemVO>();
					if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())) {
						addonProductItems.addAll(this.buildProdItemsForAddonOrFeature(wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList(), true, true, false));					
						addonProductItems.addAll(this.buildProdItemsForAddonOrFeature(wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList(), false, true, false));					
						//Add all the addon items into this product's product item list.
						productItems.addAll(addonProductItems);
					} else if (!isLiketoLikeRecontract){
						addonProductItems.addAll(this.buildProdItemsForAddonOrFeature(wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList(), true, true, false));					
						addonProductItems.addAll(this.buildProdItemsForAddonOrFeature(wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList(), false, true, false));					
						//Add all the addon items into this product's product item list.
						productItems.addAll(addonProductItems);
					}
				}
				
				//build feature product item.
				if (wirelineOfferProduct.getFeature() != null && !isLiketoLikeRecontract) {
					List<ProductItemVO> featureProductItems = new ArrayList<ProductItemVO>();
					featureProductItems.addAll(this.buildProdItemsForAddonOrFeature(wirelineOfferProduct.getFeature().getIncludedProductCatalogueItemList(), true, false, true));					
					featureProductItems.addAll(this.buildProdItemsForAddonOrFeature(wirelineOfferProduct.getFeature().getOptionalProductCatalogueItemList(), false, false, true));					
					
					//Add all the feature items into this product's product item list.
					productItems.addAll(featureProductItems);
				}
			}
		}
		
		//productItem for discount can also be taken from the offerProductSummary in the Sweetener offer response
		List<ProductItemVO> sweeterProductItems = new ArrayList<ProductItemVO>();
		if (sweeteners != null) {
			for (SweetenerOfferSummary sweeterSummary : sweeteners) {
				for (WirelineOfferProductSummary offerProdSummary : sweeterSummary.getOfferProductSummary().getWirelineOfferProductSummaryList()) {
					for (Discount discount : offerProdSummary.getDiscountList()) {
						List<ProductItemVO> prodItems = this.buildProdItemsByDiscount(discount, true, isLiketoLikeRecontract, shoppingCartContextVO);						
						sweeterProductItems.addAll(prodItems);	
					}
				}
			}
		}

		//Add all the sweeter discount product items into this product's product item list. 
		productItems.addAll(sweeterProductItems);		
		
		AvailableProductItemHolder holder = new AvailableProductItemHolder();
		holder.setEquipments(equipments);
		holder.setProductItems(productItems); 
		
		return holder;
	}
	
	//NWLN-7957
	private boolean isDiscountInCompatible(Discount discount, List<ProductCatalogueIdentifier> inCompatibleDiscountList) {
		List<String> inCompatibleDiscountIdList = new ArrayList<String>();
		List<String> discountProductCatalogueIdList = new ArrayList<String>();
		
		if(!CollectionUtils.isEmpty(inCompatibleDiscountList)){
			for(ProductCatalogueIdentifier pid : inCompatibleDiscountList){
				if(StringUtils.isNotBlank(pid.getProductCatalogueId())){
					inCompatibleDiscountIdList.add(pid.getProductCatalogueId());
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(discount.getDiscountProductCatalogueItemList())){
			for(DiscountProductCatalogueItem discountProductComponent : discount.getDiscountProductCatalogueItemList()){
				if(discountProductComponent.getProductCatalogueIdentifier()!=null && StringUtils.isNotBlank(discountProductComponent.getProductCatalogueIdentifier().getProductCatalogueId())){
					discountProductCatalogueIdList.add(discountProductComponent.getProductCatalogueIdentifier().getProductCatalogueId());
				}
			}
		}
		
		return inCompatibleDiscountIdList.containsAll(discountProductCatalogueIdList);
  }
	
	protected List<ProductItemVO> buildProdItemsForAddonOrFeature(List<ProductCatalogueItem> prodCatalogItems, boolean isIncluded, boolean isAddon, boolean isFeature) {	
		List<ProductItemVO> productItems = new ArrayList<ProductItemVO>();	

		if (CollectionUtils.isEmpty(prodCatalogItems))
			return productItems;
		
		for (ProductCatalogueItem catalogueItem : prodCatalogItems) {
			
			ProductItemVO prodItem = this.createProductItemForCatalogue(catalogueItem, isIncluded, isAddon, false, isFeature);
			if(!"FIFA".equalsIgnoreCase(flow)) {
				prodItem.setProductCatalogueDescription(prodItem.getProductCatalogueName());
			}
			
			productItems.add(prodItem);
		}
	
		return productItems;
	}
	
	protected ProductItemVO createProductItemForCatalogue(ProductCatalogueItem catalogueItem, boolean isIncluded, boolean isAddon, boolean isProdComp, boolean isFeature) {
		final String smartRingCatalogId = ApplicationProperties.getConfigString("${common/featureCatalogIds/smartRing}");
		ProductItemVO prodItem = new ProductItemVO();
		ProductItemIdentifierVO productItemIdentifier = new ProductItemIdentifierVO();
		productItemIdentifier.setProductCatalogueId(catalogueItem.getProductCatalogueIdentifier().getProductCatalogueId());
		productItemIdentifier.setParentProductCatalogueId(catalogueItem.getParentProductCatalogueIdentifier().getProductCatalogueId());
		prodItem.setProductItemIdentifier(productItemIdentifier);						
		prodItem.setProductCatalogueName(this.transformDesciption(catalogueItem.getProductCatalogueNameList()));
		if( ! StringUtils.isEmpty( catalogueItem.getParentProductCatalogueIdentifier().getExternalProductCatalogId() )) {
			//comma seperated category ids
			String categories = catalogueItem.getParentProductCatalogueIdentifier().getExternalProductCatalogId();
			prodItem.setCategoryList( categories.split(","));
		}
		
		if(catalogueItem instanceof ProductCatalogueItemVO2) {
			ProductCatalogueItemVO2 pc = (ProductCatalogueItemVO2) catalogueItem;
			prodItem.setProductCatalogueDescription(this.transformDesciption(pc.getProductCatalogueDescriptionList()));
			this.flow="FIFA";
			prodItem.setCharacteristics( pc.getCharacteristics() );//FIFA-SHS
		}
		

		//smart-ring
		if(catalogueItem.getProductCatalogueIdentifier().getProductCatalogueId().equals(smartRingCatalogId)) {
			prodItem.setSmartRingInd(true);
		}else {
			prodItem.setSmartRingInd(false);
		}

		if (isIncluded) {
			prodItem.setIncludedInd(true);
			prodItem.setPreSelectedInd(true);
			prodItem.setMandatoryInd(true);
		} else {
			prodItem.setIncludedInd(false);
			prodItem.setPreSelectedInd(false);
			prodItem.setMandatoryInd(false);			
		}

		MarketOfferClassificationVO marketOfferClassification = new MarketOfferClassificationVO();
		marketOfferClassification.setAddOnInd(isAddon); 
		marketOfferClassification.setProductComponentInd(isProdComp);
		marketOfferClassification.setCallingFeatureInd(isFeature);
		prodItem.setMarketOfferClassification(marketOfferClassification);

		PriceVO productItemPrice = new PriceVO();
		//No recurrenceCount for addon, feature and product component, always set to PRICING_TYPE_RECURRING.
		productItemPrice.setPricingTypeCd(PRICING_TYPE_RECURRING);
			
		MoneyVO basePriceAmount = new MoneyVO();
		if (catalogueItem.getProductCatalogueBasePriceAmt() != null)
			basePriceAmount.setValueAmt(catalogueItem.getProductCatalogueBasePriceAmt().floatValue());
		else
			basePriceAmount.setValueAmt(0f);
		productItemPrice.setBasePriceAmount(basePriceAmount);
		prodItem.setProductItemPriceCharge(productItemPrice);
		
		//Gary 2018-09-26
		prodItem.setPackEligibleItemInd(catalogueItem.isPackEligibleItemInd());
		//TV pkg3.0
		if(StringUtils.isNotBlank(catalogueItem.getServiceTypeCode())) {
			prodItem.setSalesCategoryServiceTypeCd(catalogueItem.getServiceTypeCode());
		}
		//TODO: copy ranking from offer to product item
		return prodItem;
	}
	
	protected List<ProductItemVO> buildProdItemsByDiscount(Discount discount, boolean isSweeter, boolean isLikeToLikeRecontract, ShoppingCartContextVO shoppingCartContextVO) {
		List<ProductItemVO> prodItems = new ArrayList<ProductItemVO>();
		//If re-contrtact only return discounts having recontractInd.
		if( isLikeToLikeRecontract && ( discount.isRecontractingInd() == null || !discount.isRecontractingInd() )){
			return prodItems;
		}

		if (CollectionUtils.isNotEmpty(discount.getDiscountProductCatalogueItemList())) {			
			for (DiscountProductCatalogueItem discountProdCatalgItem : discount.getDiscountProductCatalogueItemList()) {
				if (discountProdCatalgItem.getProductCatalogueIdentifier() == null || discountProdCatalgItem.getParentProductCatalogueIdentifier() == null) {
					continue;
				}
				ProductItemVO prodItem = new ProductItemVO();
									
				ProductItemIdentifierVO productItemIdentifier = new ProductItemIdentifierVO();
				productItemIdentifier.setProductCatalogueId(discountProdCatalgItem.getProductCatalogueIdentifier().getProductCatalogueId());
				productItemIdentifier.setParentProductCatalogueId(discountProdCatalgItem.getParentProductCatalogueIdentifier().getProductCatalogueId());
				prodItem.setProductItemIdentifier(productItemIdentifier);						
				prodItem.setProductCatalogueName(this.transformDesciption(discountProdCatalgItem.getProductCatalogueNameList()));				
				
				if (discount.isIncludedInd() || ShoppingCartHelper.isDiscountInShoppingCart(discount, shoppingCartContextVO)) {
					prodItem.setIncludedInd(true);
					prodItem.setPreSelectedInd(true);
					prodItem.setMandatoryInd(true);
				} else  {
					prodItem.setIncludedInd(false);
					prodItem.setPreSelectedInd(false);
					prodItem.setMandatoryInd(false);
				}
				
				//QC-72297 if this discount is preSelected, then set pre-selected ind true
				if (discount.isPreSelectedDiscountInd() != null && Boolean.TRUE.equals(discount.isPreSelectedDiscountInd())) {
					prodItem.setPreSelectedInd(true);
				}
				
				prodItem.setDiscountCode(discount.getDiscountCode());
				prodItem.setProductCatalogueDescription(this.transformDesciption(discount.getMarketingDescriptionList()));
				
				MarketOfferClassificationVO marketOfferClassification = new MarketOfferClassificationVO();
				marketOfferClassification.setDiscountInd(true);
				marketOfferClassification.setSweetenerInd(isSweeter);
				//TODO You might also need to set the discountInd for those Discount ProductItem
				//"marketOfferClassification": { "addOnInd": true, "sweetenerInd": true, "callingFeatureInd": true, "productComponentInd": true, "discountInd": true }, 
				prodItem.setMarketOfferClassification(marketOfferClassification);
		
				PriceDiscountVO productItemPriceDiscount = new PriceDiscountVO();
				productItemPriceDiscount.setPromotionId(discount.getPromotionId());
				productItemPriceDiscount.setPerspectiveDate(discount.getPromotionPerspectiveDate());
				prodItem.setProductItemPriceDiscount(productItemPriceDiscount);
									
				if (discountProdCatalgItem.getRecurringCount() != null 
						&& discountProdCatalgItem.getRecurringCount().intValue() == 0)
					productItemPriceDiscount.setPricingTypeCd(PRICING_TYPE_ONE_TIME);
				else
					productItemPriceDiscount.setPricingTypeCd(PRICING_TYPE_RECURRING);
					
				productItemPriceDiscount.setPriceAlterationTypeCd(PRICE_TYPE_DISCOUNT);
				if (discountProdCatalgItem.getRecurringCount() != null) {
					productItemPriceDiscount.setRecurrenceCount(discountProdCatalgItem.getRecurringCount().intValue());
				}
				
				// not to return the price amount when it is not available or the discount type = "P"
				if ((discountProdCatalgItem.getDiscountType() == null ||
						!EnterpriseWLNSalesServicesConstants.DISCOUNT_TYPE_P.equalsIgnoreCase(discountProdCatalgItem.getDiscountType().getDiscountTypeCode())) 
						&& discountProdCatalgItem.getDiscountPriceAmt() != null) {
					MoneyVO basePriceAmount = new MoneyVO();
					basePriceAmount.setValueAmt(discountProdCatalgItem.getDiscountPriceAmt().floatValue());
					productItemPriceDiscount.setBasePriceAmount(basePriceAmount);
				}
				prodItems.add(prodItem);
			}				
		}

		return prodItems;
	}
	
	private boolean isCPEDiscount(String criteriaValue) {
		boolean result = false;
		
		if(!StringUtils.isBlank(criteriaValue) && criteriaValue.contains(EnterpriseWLNSalesServicesConstants.CPE)) {
			result = true;
		}
		
		return result;
	}
}
