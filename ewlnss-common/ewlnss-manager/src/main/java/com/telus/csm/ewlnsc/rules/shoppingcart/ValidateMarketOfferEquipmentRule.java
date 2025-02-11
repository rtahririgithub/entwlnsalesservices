package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.AccessoryProductTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ExistingFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ValidateMarketOfferEquipmentRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private WirelineOfferProduct wirelineOfferProduct;
	private CartItemVO cartItem;
	private String offerId;
	//map from product type to map of the catalogueId for equipment's parent component to list of existing equipment
	private Map<String, Map<String, List<ExistingFFHEquipmentTypeVO>>> existingEquipmentList;
	private static final LoggerUtil logger = LoggerUtil.getLogger(ValidateMarketOfferEquipmentRule.class);
	
	public ValidateMarketOfferEquipmentRule(WirelineOfferProduct wirelineOfferProduct, CartItemVO cartItemVO,String offerId) {
		this.wirelineOfferProduct = wirelineOfferProduct;
		this.cartItem = cartItemVO;
		this.offerId = offerId;
	}


	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied = true;
		String functionName="MarketOfferEquipmentValidationRule";
		logger.enter(functionName);
		
		
		List<String> nonComplianceEquipmentList = new ArrayList<String>();
		List<String> equipmentNotFoundInOfferList = new ArrayList<String>();
		
		if(cartItem.isSalesOrderItem() && cartContainsEquipment(cartItem)){
			
			if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getInternetProduct()!=null){
				validateInternetEquipmentProduct(wirelineOfferProduct,cartItem.getProducts().getInternetProduct(),nonComplianceEquipmentList,equipmentNotFoundInOfferList);
			}
			
			if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getTelevisionProduct()!=null){
				validateTelevisionEquipmentProduct(wirelineOfferProduct,cartItem.getProducts().getTelevisionProduct(),nonComplianceEquipmentList,equipmentNotFoundInOfferList);
			}
			
			if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getHomePhoneProduct()!=null){
				validateHomePhoneEquipmentProduct(wirelineOfferProduct,cartItem.getProducts().getHomePhoneProduct(),nonComplianceEquipmentList,equipmentNotFoundInOfferList);
			}
			
			if(EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getAccessoryProduct()!=null){
				existingEquipmentList = EnterpriseWLNOrderUtil.buildExistingEquipmentList(shoppingCartContextVO); //only HS has existing Addon accessory.
				//validate accessory equipment min/max in offer matches the shopping cart.
				validateAccessoryEquipmentProduct(wirelineOfferProduct,cartItem.getProducts().getAccessoryProduct(),nonComplianceEquipmentList,equipmentNotFoundInOfferList);
			}
			
		}else{
			logger.debug(functionName, " rule skipped since there is no equipment present in the cartItem or cart is not a market offer");
		}
		
		//checking the results.
		
		if(!CollectionUtils.isEmpty(nonComplianceEquipmentList)){
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferEquipmentRule.class.getName());
			isSatisfied = false;
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.EQUIPMENT_WITHIN_MARKET_OFFER, this.getClass().getSimpleName() + ": "+ "The equipment added to the cartItem is not within the min / max quantity defined in the market offer. Details: " + EnterpriseWLNOrderUtil.getFormatedStringList(nonComplianceEquipmentList));
			addTrace(shoppingCartValidationError, trace, traces);
		}
		
		if(!CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferEquipmentRule.class.getName());
			isSatisfied = false;
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.EQUIPMENT_NOT_FOUND_IN_MARKET_OFFER, this.getClass().getSimpleName() + ": the equipment added to the cartItem is not found in the market offer. Details: " + EnterpriseWLNOrderUtil.getFormatedStringList(equipmentNotFoundInOfferList));
			addTrace(shoppingCartValidationError, trace, traces);
		}
		
		logger.exit(functionName);
		return isSatisfied;
	}
	
	
	
	private void validateHomePhoneEquipmentProduct(WirelineOfferProduct wirelineOfferProduct,HomePhoneProductTypeVO homePhoneProduct, List<String> nonComplianceEquipmentList,List<String> equipmentNotFoundInOfferList) {
		if(!CollectionUtils.isEmpty(homePhoneProduct.getEquipments())){
			for(FFHEquipmentTypeVO cartEquipment : homePhoneProduct.getEquipments()){
					//comparing if the equipment passed in the ShoppingCart exist in the marketOffer
					if(!CollectionUtils.isEmpty(wirelineOfferProduct.getWirelineEquipmentList())){
						boolean isEquipmentFoundInd=false;
						for(WirelineEquipment offerWirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()){														
							if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction()) && cartEquipmentExistInOfferEquipment(cartEquipment,offerWirelineEquipment)){
								isEquipmentFoundInd=true;
								//checking the min and max quantity
								if(homePhoneProduct.getEquipments().size()> offerWirelineEquipment.getMaxQty().intValue()){
									nonComplianceEquipmentList.add(" Home Phone equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the max quantity defined by the market offer with offerId =" + offerId + " , offer equipment max quantity=" + offerWirelineEquipment.getMaxQty().intValue() + ", Home Phone cartItem equipment List = " + homePhoneProduct.getEquipments().size());
								}
								if(homePhoneProduct.getEquipments().size() < offerWirelineEquipment.getMinQty().intValue()){
									nonComplianceEquipmentList.add(" Home Phone equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the minimum quantity defined by the market offer with offerId = " + offerId + ", offer equipment min quantity=" + offerWirelineEquipment.getMinQty().intValue() + ", Home Phone cartItem equipment List = " + homePhoneProduct.getEquipments().size());
								}
								break;
							}
						}if(!isEquipmentFoundInd && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction())){
							equipmentNotFoundInOfferList.add(" Home Phone equipment with productCatalogueId=" + cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId() + " cannot be found within the discounts of the market offer with offerId = " + offerId);
						}
					}
				if(!CollectionUtils.isEmpty(nonComplianceEquipmentList) || !CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
					break;
				}
			}
		}else{
			logger.debug("validateHomePhoneEquipmentProduct", "There is no equipment listed in cartItemId = " + cartItem.getCartItemId()  + " for Internet product");
		}
	}


	private void validateTelevisionEquipmentProduct(WirelineOfferProduct wirelineOfferProduct,TelevisionProductTypeVO televisionProduct, List<String> nonComplianceEquipmentList,List<String> equipmentNotFoundInOfferList) {
		if(!CollectionUtils.isEmpty(televisionProduct.getEquipments())){
			for(FFHEquipmentTypeVO cartEquipment : televisionProduct.getEquipments()){
					//comparing if the equipment passed in the ShoppingCart exist in the marketOffer
					if(!CollectionUtils.isEmpty(wirelineOfferProduct.getWirelineEquipmentList())){
						boolean isEquipmentFoundInd=false;
						for(WirelineEquipment offerWirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()){
							if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction()) && cartEquipmentExistInOfferEquipment(cartEquipment,offerWirelineEquipment)){
								isEquipmentFoundInd=true;
								//checking the min and max quantity
								if(televisionProduct.getEquipments().size()> offerWirelineEquipment.getMaxQty().intValue()){
									nonComplianceEquipmentList.add(" Television equipment added is not within the max quantity defined by the market offer with offerId = "+ offerId + ", offer equipment max quantity=" + offerWirelineEquipment.getMaxQty().intValue() + ", Television cartItem equipment List = " + televisionProduct.getEquipments().size());
								}
								if(televisionProduct.getEquipments().size() < offerWirelineEquipment.getMinQty().intValue()){
									nonComplianceEquipmentList.add(" Television equipment added is not within the minimum quantity defined by the market offer with offerId = " + offerId + ", offer equipment min quantity=" + offerWirelineEquipment.getMinQty().intValue() + ", Television cartItem equipment List = " + televisionProduct.getEquipments().size());																
								}
								break;
							}
						}if(!isEquipmentFoundInd && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction())){
							equipmentNotFoundInOfferList.add(" Television equipment with productCatalogueId=" + cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId() + " cannot be found within the discounts of the market offer with offerId = " + offerId);
						}
					}
				if(!CollectionUtils.isEmpty(nonComplianceEquipmentList) || !CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
					break;
				}
			}
		}else{
			logger.debug("validateTelevisionEquipmentProduct", "There is no equipment listed in cartItemId = " + cartItem.getCartItemId()  + " for Internet product");
		}
		
	}


	private void validateInternetEquipmentProduct(WirelineOfferProduct wirelineOfferProduct,InternetProductTypeVO internetProduct, List<String> nonComplianceEquipmentList,List<String> equipmentNotFoundInOfferList) {
		
		if(!CollectionUtils.isEmpty(internetProduct.getEquipments())){
			for(FFHEquipmentTypeVO cartEquipment : internetProduct.getEquipments()){
					if(!CollectionUtils.isEmpty(wirelineOfferProduct.getWirelineEquipmentList())){
						boolean isEquipmentFoundInd = false;
						for(WirelineEquipment offerWirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()){
							if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction()) && cartEquipmentExistInOfferEquipment(cartEquipment,offerWirelineEquipment)){
								isEquipmentFoundInd = true;
								//checking the minimum and max quantity
								if(internetProduct.getEquipments().size()> offerWirelineEquipment.getMaxQty().intValue()){
									nonComplianceEquipmentList.add(" Internet equipment is not within the max quantity defined by the market offer with offerId=" + offerId + ", equipment max quantity=" + offerWirelineEquipment.getMaxQty().intValue() + ", Internet cartItem equipment List = " + internetProduct.getEquipments().size());
								}
								if(internetProduct.getEquipments().size() < offerWirelineEquipment.getMinQty().intValue()){
									nonComplianceEquipmentList.add(" Internet equipment added  is not within the minimum quantity defined by the market offer with offerId = " + offerId + ", offer equipment min quantity=" + offerWirelineEquipment.getMinQty().intValue() + ", Internet cartItem equipment List = " + internetProduct.getEquipments().size());																
								}
								break;
							}
						}
						if(!isEquipmentFoundInd && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction())){
							equipmentNotFoundInOfferList.add(" Internet equipment with productCatalogueId=" + cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId() + " cannot be found within the discounts of the market offer with offerId = " + offerId);
						}
					}
				if(!CollectionUtils.isEmpty(nonComplianceEquipmentList) || !CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
					break;
				}
			}
		}else{
			logger.debug("validateInternetEquipmentProduct", "There is no equipment listed in cartItemId = " + cartItem.getCartItemId()  + " for Internet product");
		}
	}
	
	private void validateAccessoryEquipmentProduct(WirelineOfferProduct wirelineOfferProduct,AccessoryProductTypeVO accessoryProduct, List<String> nonComplianceEquipmentList,List<String> equipmentNotFoundInOfferList) {
		
		if(!CollectionUtils.isEmpty(accessoryProduct.getEquipments())){
			
			Map<String, Integer> equipmentInCart = countEquipmentInCart(accessoryProduct.getEquipments());
			String templateProductType = wirelineOfferProduct.getProductTemplateProductTypeCode();
			
			for(FFHEquipmentTypeVO cartEquipment : accessoryProduct.getEquipments()){
					if(!CollectionUtils.isEmpty(wirelineOfferProduct.getWirelineEquipmentList())){
						boolean isEquipmentFoundInd = false;
						for(WirelineEquipment offerWirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()){
							if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction()) && cartEquipmentExistInOfferEquipment(cartEquipment,offerWirelineEquipment)){
								isEquipmentFoundInd = true;
								//NWLN-10229 count equipments in cart
								String productCatalogueId = cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId();
								String externalProductCatalogueId = offerWirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getExternalProductCatalogId();
								
								Integer numberInCart = equipmentInCart.get(productCatalogueId);
								Integer numberExisting = 0;
								Integer minQty = 0;
								Integer maxQty = 0;
								
								if(offerWirelineEquipment.getMinQty() != null)
									minQty = offerWirelineEquipment.getMinQty().intValue();
								
								if(offerWirelineEquipment.getMaxQty() != null)
									maxQty = offerWirelineEquipment.getMaxQty().intValue();
								
								if (existingEquipmentList.containsKey(templateProductType)) {
									Map<String, List<ExistingFFHEquipmentTypeVO>> existingEquipmentMap = existingEquipmentList.get(templateProductType);
									if (existingEquipmentMap.containsKey(externalProductCatalogueId)) {
										numberExisting = existingEquipmentMap.get(externalProductCatalogueId).size();
									}
								}
								
								Integer totalNumberEquipment = numberInCart + numberExisting;

								logger.info("validateAccessoryEquipmentProduct", "Total:" + totalNumberEquipment + " InCart:" + numberInCart
										+ " Existing:" + numberExisting + " MinQty:" + minQty + " MaxQty:" + maxQty);

								//checking the minimum and max quantity
								if(totalNumberEquipment > maxQty){
									String msg = " Accessory equipment is not within the max quantity defined by the market offer with offerId=" + offerId + ", equipment max quantity=" + maxQty + ", Accessory cartItem equipment quantity = " + numberInCart + " existing equipment quantity = " + numberExisting;
									logger.info("validateAccessoryEquipmentProduct", msg);
									nonComplianceEquipmentList.add(msg);
								}
								if(totalNumberEquipment < minQty){
									String msg = " Accessory equipment added  is not within the minimum quantity defined by the market offer with offerId = " + offerId + ", offer equipment min quantity=" + minQty + ", Accessory cartItem equipment quantity = " + numberInCart + " existing equipment quantity = " + numberExisting;
									logger.info("validateAccessoryEquipmentProduct", msg);
									nonComplianceEquipmentList.add(msg);																
								}
								break;
							}
						}
						if(!isEquipmentFoundInd && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction())){
							String msg = " Accessory equipment with productCatalogueId=" + cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId() + " cannot be found within the discounts of the market offer with offerId = " + offerId;
							logger.info("validateAccessoryEquipmentProduct", msg);
							equipmentNotFoundInOfferList.add(msg);
						}
					}
				if(!CollectionUtils.isEmpty(nonComplianceEquipmentList) || !CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
					break;
				}
			}
			
			//NWLN-10229 check any mandatory equipment is not in cart or existing 
			for(WirelineEquipment offerWirelineEquipment : wirelineOfferProduct.getWirelineEquipmentList()){
				String productCatalogueId = offerWirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();
				String externalProductCatalogueId = offerWirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getExternalProductCatalogId();
				
				if(offerWirelineEquipment.getMinQty() != null) {
					Integer minQty = offerWirelineEquipment.getMinQty().intValue();
	
					if(minQty > 0 && !equipmentInCart.containsKey(productCatalogueId)) {
						Integer numberExisting = 0;
	
						if (existingEquipmentList.containsKey(templateProductType)) {
							Map<String, List<ExistingFFHEquipmentTypeVO>> existingEquipmentMap = existingEquipmentList.get(templateProductType);
							if (existingEquipmentMap.containsKey(externalProductCatalogueId)) {
								numberExisting = existingEquipmentMap.get(externalProductCatalogueId).size();
							}
						}
						
						if(numberExisting < minQty){
							String msg = " Accessory equipment is not within the minimum quantity defined by the market offer with offerId = " + offerId + ", offer equipment min quantity=" + minQty + ", Accessory cartItem equipment quantity = 0,  existing equipment quantity = " + numberExisting;
							logger.info("validateAccessoryEquipmentProduct", msg);
							nonComplianceEquipmentList.add(msg);																
						}
					}
				}
			}
		}else{
			logger.debug("validateAccessoryEquipmentProduct", "There is no equipment listed in cartItemId = " + cartItem.getCartItemId()  + " for Accessory product");
		}
	}

	private boolean cartContainsEquipment(CartItemVO cartItem) {
		boolean containsEquipmentInd=false;
		if(cartItem.getProducts()!=null){
			if(cartItem.getProducts().getInternetProduct()!=null){
				InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
				if(!CollectionUtils.isEmpty(internetProduct.getEquipments())){
					containsEquipmentInd=true;
				}
			}
			
			if(cartItem.getProducts().getHomePhoneProduct()!=null){
				HomePhoneProductTypeVO homePhoneProduct = cartItem.getProducts().getHomePhoneProduct();
				if(!CollectionUtils.isEmpty(homePhoneProduct.getEquipments())){
					containsEquipmentInd=true;
				}
			}
			
			if(cartItem.getProducts().getTelevisionProduct()!=null){
				TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();
				if(!CollectionUtils.isEmpty(televisionProduct.getEquipments())){
					containsEquipmentInd=true;
				}
			}
			//NWLN-10229 add accessory
			if(cartItem.getProducts().getAccessoryProduct()!=null){
				AccessoryProductTypeVO accessoryProduct = cartItem.getProducts().getAccessoryProduct();
				if(!CollectionUtils.isEmpty(accessoryProduct.getEquipments())){
					containsEquipmentInd=true;
				}
			}
		}
		return containsEquipmentInd;
	}
	
	private boolean cartEquipmentExistInOfferEquipment(FFHEquipmentTypeVO cartEquipment,WirelineEquipment wirelineEquipment) {
		boolean cartEquipmentExistInOfferEquipmentInd=false;
		String offerProductCatalogueId = null;
		String offerParentProductCatalogueId = null;
				if(wirelineEquipment.getProductCatalogueItem()!=null){
					if(wirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier()!=null){
						if(!StringUtils.isBlank(wirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier().getProductCatalogueId())){
							offerParentProductCatalogueId = wirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier().getProductCatalogueId();
						}
					}
					
					if(wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier()!=null){
						if(!StringUtils.isBlank(wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId())){
							offerProductCatalogueId = wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();
						}
					}
					
					logger.info("cartEquipmentExistInOfferEquipment", "offerProductCatalogueId="+offerProductCatalogueId+" offerParentProductCatalogueId="+offerParentProductCatalogueId);
					
					if(cartEquipment.getProductCatalogueIdentifier()!=null && !StringUtils.isBlank(cartEquipment.getProductCatalogueIdentifier().getParentProductCatalogueId()) && !StringUtils.isBlank(cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId())){
						
						logger.info("cartEquipmentExistInOfferEquipment", "cartProductCatalogueId="+cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId()+" cartParentProductCatalogueId="+cartEquipment.getProductCatalogueIdentifier().getParentProductCatalogueId());
						
						//comparing the offer catalog id's with the catalog id's from the cartItem
						if(cartEquipment.getProductCatalogueIdentifier().getParentProductCatalogueId().equals(offerParentProductCatalogueId) && cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId().equals(offerProductCatalogueId)){
							//checking the conditions to check from the rule: EquipmentNotFoundInOffer
							/**
							 * 1. If marketOffer does not contain this equipment MIC 
							 * 2. Or marketOffer does not contain the catalogueId for this equipment
							 * 3. Or marketOffer contains the equipment MIC but not the selected deliveryMethod
							 * 4. Or marketOffer contains the equipment MIC but the BUY vs RENT acquisitionType doesn’t match
							 */
							
							if(cartEquipment.getAcquisitionType()!=null){
								if(cartEquipment.getAcquisitionType().isBuyIndicator()!=null && cartEquipment.getAcquisitionType().isBuyIndicator()){
									logger.info("cartEquipmentExistInOfferEquipment", "checking purchase list");
									//check in the Offerequipment.getPurchaseEquipmentList
									//checking the mic code that matches
									if(!CollectionUtils.isEmpty(wirelineEquipment.getPurchaseEquipmentList()) && materialItemCodeMathces(cartEquipment,wirelineEquipment.getPurchaseEquipmentList()) && deliveryMethodMatches(cartEquipment,wirelineEquipment.getPurchaseEquipmentList()) ){
										cartEquipmentExistInOfferEquipmentInd=true;
									}
								}else if(cartEquipment.getAcquisitionType().isRentalEquipmentIndicator()!=null && cartEquipment.getAcquisitionType().isRentalEquipmentIndicator()){
									logger.info("cartEquipmentExistInOfferEquipment", "checking rental list");
									//check in the Offerequipment.getRentalEquipmentList
									//checking the mic code that matches and deliveryMethod matches
									if(!CollectionUtils.isEmpty(wirelineEquipment.getRentalEquipmentList()) && materialItemCodeMathces(cartEquipment,wirelineEquipment.getRentalEquipmentList()) && deliveryMethodMatches(cartEquipment,wirelineEquipment.getRentalEquipmentList()) ){
										cartEquipmentExistInOfferEquipmentInd=true;
									}					
								}else if(cartEquipment.getAcquisitionType().isCustomerOwnedIndicator()!=null && cartEquipment.getAcquisitionType().isCustomerOwnedIndicator()){
									logger.info("cartEquipmentExistInOfferEquipment", "checking customer list");
									//check in the Offerequipment.getRentalEquipmentList
									//checking the mic code that matches and deliveryMethod matches
									if(!CollectionUtils.isEmpty(wirelineEquipment.getByodEquipmentList()) && materialItemCodeMathces(cartEquipment,wirelineEquipment.getByodEquipmentList())){
										cartEquipmentExistInOfferEquipmentInd=true;
									}
								}
							}else {
								logger.info("cartEquipmentExistInOfferEquipment", "cart acquisition type is empty");
							}
						}else {
							logger.info("cartEquipmentExistInOfferEquipment", "Offer ProductCatalogueId or ParentProductCatalogueId not match with Cart equipment");
						}
					}else {
						logger.info("cartEquipmentExistInOfferEquipment", "No ProductCatalogueId or ParentProductCatalogueId in Cart equipment");
					}
				}	
				
				logger.info("cartEquipmentExistInOfferEquipment", "cartEquipmentExistInOfferEquipmentInd=" + cartEquipmentExistInOfferEquipmentInd);
		return cartEquipmentExistInOfferEquipmentInd;
	}
	
	private boolean materialItemCodeMathces(FFHEquipmentTypeVO cartEquipment,List<WirelineEquipmentItem> rentalEquipmentList) {
		logger.info("materialItemCodeMathces", "cart material code=" + cartEquipment.getMaterialItemCode());
		boolean materialItemCodeMatchInd=false;
		for(WirelineEquipmentItem wirelineEquipmentItem : rentalEquipmentList){
			logger.info("materialItemCodeMathces", "offer material code=" + wirelineEquipmentItem.getMaterialItemCode());
			if(cartEquipment.getMaterialItemCode().equalsIgnoreCase(wirelineEquipmentItem.getMaterialItemCode())){
				materialItemCodeMatchInd=true;
				break;
			}
		}
		logger.info("materialItemCodeMathces", "materialItemCodeMatchInd=" + materialItemCodeMatchInd);
		return materialItemCodeMatchInd;
	}
	
	private boolean deliveryMethodMatches(FFHEquipmentTypeVO cartEquipment,List<WirelineEquipmentItem> rentalEquipmentList) {
		logger.info("deliveryMethodMatches", "cart delivery method=" + cartEquipment.getDeliveryMethodType());
		boolean deliveryMethodMatchesInd=false;
		for(WirelineEquipmentItem wirelineEquipmentItem : rentalEquipmentList){
			if(cartEquipment.getMaterialItemCode().equalsIgnoreCase(wirelineEquipmentItem.getMaterialItemCode())){
				for(String deliveryMethod : wirelineEquipmentItem.getDeliveryMethodList()) {
					logger.info("deliveryMethodMatches", "offer delivery method=" + deliveryMethod);
					if(cartEquipment.getDeliveryMethodType().equalsIgnoreCase(deliveryMethod)) {
						deliveryMethodMatchesInd=true;
						break;
					}
				}
			}
		}
		logger.info("deliveryMethodMatches", "deliveryMethodMatchesInd=" + deliveryMethodMatchesInd);
		return deliveryMethodMatchesInd;
	}
	
	private void addTrace(ShoppingCartValidationErrors validationError,ShoppingCartValidationTraceVO trace,List<ShoppingCartValidationTraceVO> traces){
		trace.setValidationPassedInd(false);
		trace.setCartItemRelationId(cartItem.getCartItemRelationId());
		trace.setShoppingCartItemId(cartItem.getCartItemId());
		trace.setErrors(validationError);							
		traces.add(trace);
	}
	
	// NWLN-10229 count number of equipments in shopping cart
	private Map<String, Integer> countEquipmentInCart(List<FFHEquipmentTypeVO> equipments) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		if (equipments != null) {
			for (FFHEquipmentTypeVO equipment : equipments) {
				if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(equipment.getProductCatalogueIdentifier().getAction())) {
					String componentId = equipment.getProductCatalogueIdentifier().getProductCatalogueId();
					if (map.containsKey(componentId)) {
						Integer number = map.get(componentId);
						map.put(componentId, number + 1);
					} else {
						map.put(componentId, 1);
					}
				}
			}
		}

		return map;
	}
}
