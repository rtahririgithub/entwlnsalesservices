package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
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

public class MarketOfferEquipmentValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private CartItemVO cartItem;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(MarketOfferEquipmentValidationRule.class);
	
	public MarketOfferEquipmentValidationRule(CartItemVO cartItem){
		this.cartItem=cartItem;
	}
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		String functionName="EquipmentIsWithinMarketOfferMinMaxRule.isSatisfiedBy()";
		logger.enter(functionName);
		boolean isSatisfied=true;
		String cartItemRelationId=null;
		String selectedOfferId =null;
		String shoppingCartId = shoppingCartContextVO.getShoppingCartVO().getShoppingCartId();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(MarketOfferEquipmentValidationRule.class.getName());
		
		List<String> nonComplianceEquipmentList = new ArrayList<String>();
		List<String> equipmentNotFoundInOfferList = new ArrayList<String>();
		if(cartItem.getProductMarketOffering()!=null && cartItem.getProductMarketOffering().getOfferHeader()!=null && !StringUtils.isBlank(cartItem.getProductMarketOffering().getOfferHeader().getOfferId())){
			selectedOfferId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId();
		}
		GetSalesOfferDetailResponseVO2 offerResponseVO=null;
		/**
		 * 1. if cart contains equipment, execute this rule.
		 */
		
		
		if(cartItem!=null && cartItem.isSalesOrderItem()){
			
			if(!StringUtils.isBlank(cartItem.getCartItemRelationId())){
				cartItemRelationId = cartItem.getCartItemRelationId();
			}
			
			if(cartContainsEquipment(cartItem)){
				
				//checking if the selected offer contains equipment
				if(!StringUtils.isBlank(selectedOfferId)){
					offerResponseVO = shoppingCartContextVO.getOfferByCartItemOfferId(selectedOfferId);
					
					if(offerResponseVO!=null && offerResponseVO.getOffer()!=null){
						if(cartItem.getProducts().getInternetProduct()!=null){
							InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
							if(!CollectionUtils.isEmpty(internetProduct.getEquipments())){
								for(FFHEquipmentTypeVO cartEquipment : internetProduct.getEquipments()){
									
									if(offerResponseVO.getOffer()!=null && offerResponseVO.getOffer().getOfferProduct()!=null && !CollectionUtils.isEmpty(offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())){
										for(WirelineOfferProduct wirelineProduct : offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
											if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
												//comparing if the equipment passed in the ShoppingCart exist in the marketOffer
												if(!CollectionUtils.isEmpty(wirelineProduct.getWirelineEquipmentList())){
													boolean isEquipmentFoundInd = false;
													for(WirelineEquipment offerWirelineEquipment : wirelineProduct.getWirelineEquipmentList()){
														if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction()) && cartEquipmentExistInOfferEquipment(cartEquipment,offerWirelineEquipment)){
															isEquipmentFoundInd = true;
															//checking the min and max quantity
															if(internetProduct.getEquipments().size()> offerWirelineEquipment.getMaxQty().intValue()){
																nonComplianceEquipmentList.add(" Internet equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the max quantity defined by the market offer with Offerid=" + selectedOfferId + ", offer equipment max quantity=" + offerWirelineEquipment.getMaxQty().intValue() + ", Internet cartItem equipment List = " + internetProduct.getEquipments().size());
															}
															if(internetProduct.getEquipments().size() < offerWirelineEquipment.getMinQty().intValue()){
																nonComplianceEquipmentList.add(" Internet equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the minimum quantity defined by the market offer with Offerid=" + selectedOfferId + ", offer equipment min quantity=" + offerWirelineEquipment.getMinQty().intValue() + ", Internet cartItem equipment List = " + internetProduct.getEquipments().size());																
															}
															break;
														}
													}
													if(!isEquipmentFoundInd && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction())){
														equipmentNotFoundInOfferList.add(" Internet equipment with productCatalogueId=" + cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId() + " cannot be found within the discounts of the market offer with offerId=" + selectedOfferId);
													}
												}
											}
										}
									}
									if(!CollectionUtils.isEmpty(nonComplianceEquipmentList) || !CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
										break;
									}									
								}
							}else{
								logger.debug(functionName, "There is no equipment listed in cartItemId = " + cartItem.getCartItemId()  + " for Internet product");
							}
						}
						
						if(cartItem.getProducts().getTelevisionProduct()!=null){
							TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();
							if(!CollectionUtils.isEmpty(televisionProduct.getEquipments())){
								for(FFHEquipmentTypeVO cartEquipment : televisionProduct.getEquipments()){
									
									if(offerResponseVO.getOffer()!=null && offerResponseVO.getOffer().getOfferProduct()!=null && !CollectionUtils.isEmpty(offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())){
										for(WirelineOfferProduct wirelineProduct : offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
											if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
												//comparing if the equipment passed in the ShoppingCart exist in the marketOffer
												if(!CollectionUtils.isEmpty(wirelineProduct.getWirelineEquipmentList())){
													boolean isEquipmentFoundInd=false;
													for(WirelineEquipment offerWirelineEquipment : wirelineProduct.getWirelineEquipmentList()){
														if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction()) && cartEquipmentExistInOfferEquipment(cartEquipment,offerWirelineEquipment)){
															isEquipmentFoundInd=true;
															//checking the min and max quantity
															if(televisionProduct.getEquipments().size()> offerWirelineEquipment.getMaxQty().intValue()){
																nonComplianceEquipmentList.add(" Television equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the max quantity defined by the market offer with Offerid=" + selectedOfferId + ", offer equipment max quantity=" + offerWirelineEquipment.getMaxQty().intValue() + ", Television cartItem equipment List = " + televisionProduct.getEquipments().size());
															}
															if(televisionProduct.getEquipments().size() < offerWirelineEquipment.getMinQty().intValue()){
																nonComplianceEquipmentList.add(" Television equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the minimum quantity defined by the market offer with Offerid=" + selectedOfferId + ", offer equipment min quantity=" + offerWirelineEquipment.getMinQty().intValue() + ", Television cartItem equipment List = " + televisionProduct.getEquipments().size());																
															}
															break;
														}
													}if(!isEquipmentFoundInd && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction())){
														equipmentNotFoundInOfferList.add(" Television equipment with productCatalogueId=" + cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId() + " cannot be found within the discounts of the market offer with offerId=" + selectedOfferId);
													}
												}
											}
										}
									}
									if(!CollectionUtils.isEmpty(nonComplianceEquipmentList) || !CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
										break;
									}
								}
							}else{
								logger.debug(functionName, "There is no equipment listed in cartItemId = " + cartItem.getCartItemId()  + " for Internet product");
							}
						}
						
						if(cartItem.getProducts().getHomePhoneProduct()!=null){
							HomePhoneProductTypeVO homePhoneProduct = cartItem.getProducts().getHomePhoneProduct();
							if(!CollectionUtils.isEmpty(homePhoneProduct.getEquipments())){
								for(FFHEquipmentTypeVO cartEquipment : homePhoneProduct.getEquipments()){
									
									if(offerResponseVO.getOffer()!=null && offerResponseVO.getOffer().getOfferProduct()!=null && !CollectionUtils.isEmpty(offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())){
										for(WirelineOfferProduct wirelineProduct : offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
											if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
												//comparing if the equipment passed in the ShoppingCart exist in the marketOffer
												if(!CollectionUtils.isEmpty(wirelineProduct.getWirelineEquipmentList())){
													boolean isEquipmentFoundInd=false;
													for(WirelineEquipment offerWirelineEquipment : wirelineProduct.getWirelineEquipmentList()){														
														if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction()) && cartEquipmentExistInOfferEquipment(cartEquipment,offerWirelineEquipment)){
															isEquipmentFoundInd=true;
															//checking the min and max quantity
															if(homePhoneProduct.getEquipments().size()> offerWirelineEquipment.getMaxQty().intValue()){
																nonComplianceEquipmentList.add(" Home Phone equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the max quantity defined by the market offer with Offerid=" + selectedOfferId + ", offer equipment max quantity=" + offerWirelineEquipment.getMaxQty().intValue() + ", Home Phone cartItem equipment List = " + homePhoneProduct.getEquipments().size());
															}
															if(homePhoneProduct.getEquipments().size() < offerWirelineEquipment.getMinQty().intValue()){
																nonComplianceEquipmentList.add(" Home Phone equipment added for CartItemId=" + cartItem.getCartItemId() + " is not within the minimum quantity defined by the market offer with Offerid=" + selectedOfferId + ", offer equipment min quantity=" + offerWirelineEquipment.getMinQty().intValue() + ", Home Phone cartItem equipment List = " + homePhoneProduct.getEquipments().size());
															}
															break;
														}
													}if(!isEquipmentFoundInd && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartEquipment.getProductCatalogueIdentifier().getAction())){
														equipmentNotFoundInOfferList.add(" Home Phone equipment with productCatalogueId=" + cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId() + " cannot be found within the discounts of the market offer with offerId=" + selectedOfferId);
													}
												}
											}
										}
									}
									if(!CollectionUtils.isEmpty(nonComplianceEquipmentList) || !CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
										break;
									}
								}
							}else{
								logger.debug(functionName, "There is no equipment listed in cartItemId = " + cartItem.getCartItemId()  + " for Internet product");
							}
						}
						
					}else{
						logger.debug(functionName, "No offer was found for cartItemRelationId: " + cartItemRelationId);
					}
				}else{
					logger.debug(functionName, "cartItem.productMarketOffering.offerHeader.offerId is missing from the ShoppingCart.");
				}				
			}else{
				logger.debug(functionName, " rule skipped since there is no equipment present in the cartItem");
			}
			
			//checking the results.
			
			if(!CollectionUtils.isEmpty(nonComplianceEquipmentList)){
				isSatisfied = false;
				trace.setValidationPassedInd(isSatisfied);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.EQUIPMENT_WITHIN_MARKET_OFFER, this.getClass().getSimpleName() + ": "
						+ "The equipment added to the cartItem is not within the min / max quantity defined in the market offer. Details: " + EnterpriseWLNOrderUtil.getFormatedStringList(nonComplianceEquipmentList)));
				trace.setShoppingCartId(shoppingCartId);
				trace.setCartItemRelationId(cartItemRelationId);
				traces.add(trace);
			}
			
			if(!CollectionUtils.isEmpty(equipmentNotFoundInOfferList)){
				isSatisfied = false;
				trace.setValidationPassedInd(isSatisfied);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.EQUIPMENT_NOT_FOUND_IN_MARKET_OFFER, this.getClass().getSimpleName() + ": the equipment added to the cartItem with CartItemRelationId="+cartItemRelationId + " is not found in the market offer with offerId=" + selectedOfferId + " Details: " + EnterpriseWLNOrderUtil.getFormatedStringList(equipmentNotFoundInOfferList)));
				trace.setShoppingCartId(shoppingCartId);
				trace.setCartItemRelationId(cartItemRelationId);
				traces.add(trace);
			}
			
		}else{
			logger.debug(functionName, "Rule skipped since the carItem is not a market offer.");
		}
		
		
		return isSatisfied;
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
					
					if(cartEquipment.getProductCatalogueIdentifier()!=null && !StringUtils.isBlank(cartEquipment.getProductCatalogueIdentifier().getParentProductCatalogueId()) && !StringUtils.isBlank(cartEquipment.getProductCatalogueIdentifier().getProductCatalogueId())){
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
									//check in the Offerequipment.getPurchaseEquipmentList
									//checking the mic code that matches
									if(!CollectionUtils.isEmpty(wirelineEquipment.getPurchaseEquipmentList()) && materialItemCodeMathces(cartEquipment,wirelineEquipment.getPurchaseEquipmentList()) && deliveryMethodMatches(cartEquipment,wirelineEquipment.getPurchaseEquipmentList()) ){
										cartEquipmentExistInOfferEquipmentInd=true;
									}
									}else if(cartEquipment.getAcquisitionType().isRentalEquipmentIndicator()!=null && cartEquipment.getAcquisitionType().isRentalEquipmentIndicator()){
										//check in the Offerequipment.getRentalEquipmentList
										//checking the mic code that matches and deliveryMethod matches
										if(!CollectionUtils.isEmpty(wirelineEquipment.getRentalEquipmentList()) && materialItemCodeMathces(cartEquipment,wirelineEquipment.getRentalEquipmentList()) && deliveryMethodMatches(cartEquipment,wirelineEquipment.getRentalEquipmentList()) ){
											cartEquipmentExistInOfferEquipmentInd=true;
										}					
								}
							}
						}
					}
				}	
		return cartEquipmentExistInOfferEquipmentInd;
	}
	
	private boolean deliveryMethodMatches(FFHEquipmentTypeVO cartEquipment,List<WirelineEquipmentItem> rentalEquipmentList) {
		boolean deliveryMethodMatchesInd=false;
		for(WirelineEquipmentItem wirelineEquipmentItem : rentalEquipmentList){
			if(cartEquipment.getMaterialItemCode().equalsIgnoreCase(wirelineEquipmentItem.getMaterialItemCode())){
				for(String deliveryMethod : wirelineEquipmentItem.getDeliveryMethodList()) {
					if(cartEquipment.getDeliveryMethodType().equalsIgnoreCase(deliveryMethod)) {
						deliveryMethodMatchesInd=true;
						break;
					}
				}
			}
		}
		return deliveryMethodMatchesInd;
	}

	private boolean materialItemCodeMathces(FFHEquipmentTypeVO cartEquipment,List<WirelineEquipmentItem> rentalEquipmentList) {
		boolean materialItemCodeMatchInd=false;
		for(WirelineEquipmentItem wirelineEquipmentItem : rentalEquipmentList){
			if(cartEquipment.getMaterialItemCode().equalsIgnoreCase(wirelineEquipmentItem.getMaterialItemCode())){
				materialItemCodeMatchInd=true;
				break;
			}
		}
		return materialItemCodeMatchInd;
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
		}
		return containsEquipmentInd;
	}

	
	
}
