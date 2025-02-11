package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import java.util.Date;
import java.util.List;

import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.StringHolder;

import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOffering;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.AvailableInternetProductItemVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class OPInternetProvideTransformer extends OPCommonProvideTransformer {

	public static ProductOrderItem transformProvide(OperationHeader operationHeader, CartItemVO cartItemVO, ShoppingCartContextVO shoppingCartContextVO) {
		InternetProductTypeVO internetProduct = cartItemVO.getProducts().getInternetProduct();
		
		WirelineOfferProduct wirelineOfferProduct = findWirelineOfferProduct(shoppingCartContextVO, cartItemVO,
				EnterpriseWLNSalesServicesConstants.HSIC);
		if (wirelineOfferProduct == null) {
			throw new RuntimeException("Cannot find a HSIC WirelineOfferProduct for CartItemRelationId: "
					+ cartItemVO.getCartItemRelationId());
		}

		Date serviceRequiredDate = findServiceRequiredDate(cartItemVO, EnterpriseWLNSalesServicesConstants.HSIC, shoppingCartContextVO);
		ProductOrderItem result = populateCommonProductOrderItem(operationHeader, cartItemVO, wirelineOfferProduct, EnterpriseWLNSalesServicesConstants.HSIC, internetProduct.getProductOrderType().getOrderTypeCd(),
																shoppingCartContextVO, serviceRequiredDate);
		Product product = result.getProduct();

		BooleanHolder hasExistingRentEquip = new BooleanHolder();
		StringHolder deliverMethodHolder = new StringHolder();
		// equipment
		if (internetProduct.getEquipments() != null) {
			for (FFHEquipmentTypeVO wlnEquipment : internetProduct.getEquipments()) {
				ProductComponent prodComp = populateProductEquipment(EnterpriseWLNSalesServicesConstants.HSIC, wirelineOfferProduct, wlnEquipment, shoppingCartContextVO, new StringHolder(), deliverMethodHolder);
				if (prodComp != null) {
					// to accommodate new requirements from OP per Sid
					prodComp.setName(null);
					product.addProductComponent(prodComp);
				}
			}
		}
		// implicit equipment removal
		if (shoppingCartContextVO.getAvailableProductItemDelegateResponse() != null
				&& shoppingCartContextVO.getAvailableProductItemDelegateResponse().getResponse() != null) {
			if (shoppingCartContextVO.getAvailableProductItemDelegateResponse().getResponse() .getInternetProductItems() != null) {
				implicitEquipRemovalHsic(shoppingCartContextVO, shoppingCartContextVO.getAvailableProductItemDelegateResponse().getResponse().getInternetProductItems(), product, hasExistingRentEquip);
			}
		}

		if (hasExistingRentEquip.value) {
			ProductComponent ordActionAttrComp = null;
			for (ProductComponent prodComp : product.getProductComponents()) {
				if (EnterpriseWLNSalesServicesConstants.COMP_ORDER_ACTION_ATTRIBUTES
						.equalsIgnoreCase(prodComp.getName())) {
					ordActionAttrComp = prodComp;
				}
			}
			if (ordActionAttrComp != null) {
				if (Constants.DELIVERY_METHOD_INSTALLER.equalsIgnoreCase(deliverMethodHolder.value)) {
					// INSTALLER - returnMethod is "ReturnedToTechnician"
					ordActionAttrComp.addProductCharacteristic(
							createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_RETURN_METHOD,
									Constants.RETURN_METHOD_RETURNED_TO_TECHNICIAN));
				} else if (Constants.DELIVERY_METHOD_SHIPPED.equalsIgnoreCase(deliverMethodHolder.value)) {
					// SHIPPED - returnMethod is "RecoveryKitByMail" with
					// Shipping Address (there is a routine already that adds
					// the Shipping Address to OP is it is in the ESS Shopping
					// Cart, so you don't need to worry about that)
					ordActionAttrComp.addProductCharacteristic(
							createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_RETURN_METHOD,
									Constants.RETURN_METHOD_RECOVERY_KIT_BY_MAIL));
					// shipping address to be already populated for new
					// equipment
				}
			}
		}

		// price plans
		// product components
		if (internetProduct.getProductComponents() != null) {
			for (ProductComponentVO prodCompVO : internetProduct.getProductComponents()) {
				ProductComponent prodComp = populateProdComp(wirelineOfferProduct, prodCompVO,
						EnterpriseWLNSalesServicesConstants.HSIC, internetProduct.getSelectedContractTermCd(),
						shoppingCartContextVO);
				if (prodComp != null) {
					product.addProductComponent(prodComp);
				}
			}
		}

		// addons
		if (internetProduct.getAddOns() != null) {
			for (FFHProductPlanAddOnTypeVO addon : internetProduct.getAddOns()) {
				ProductComponent addonComp = createAddonProductComponent(wirelineOfferProduct, addon);
				if (addonComp != null) {
					//NWLN-9384 - if its type is PPL, then added to productOffering rather than components (used for exclude "unlimited internet usage", because it is a price plan)
					if(addon != null && addon.getProductCatalogueIdentifier() != null && 
							EnterpriseWLNSalesServicesConstants.PROD_CATLG_ITM_TYPE_CD_PPL.equalsIgnoreCase(getProductCatalogItemTypeCd(addon.getProductCatalogueIdentifier().getProductCatalogueId()))) {
						//NWLN-9384 - add unlimited data usage to product offering
						addInternetPPLAddonToProductOffering(addonComp, addon, shoppingCartContextVO, product, wirelineOfferProduct);
					} else {
						product.addProductComponent(addonComp);
					}
				}
			}
		}

		// productOffering/discounts
		if (internetProduct.getSweeteners() != null) {
			List<SweetenerOfferSummary> sweetenerOffers = shoppingCartContextVO.getSweetnersByProduct(
					EnterpriseWLNSalesServicesConstants.HSIC, cartItemVO.getCartItemRelationId());
			for (FFHSweetenerTypeVO sweetenerVO : internetProduct.getSweeteners()) {
				SweetenerOfferSummary offerSummary = findSweetenerOfferSummary(
						sweetenerVO.findPromotionId(), sweetenerOffers);
				if (offerSummary != null && offerSummary.getOfferProductSummary() != null
						&& offerSummary.getOfferProductSummary().getWirelineOfferProductSummaryList() != null) {
					for (WirelineOfferProductSummary offerProdSummary : offerSummary.getOfferProductSummary()
							.getWirelineOfferProductSummaryList()) {
						addProductOfferings(offerProdSummary.getDiscountList(), sweetenerVO.getDiscounts(), product,
								shoppingCartContextVO);
					}
				}
			}
		}

		addProductOfferings(wirelineOfferProduct.getDiscountList(), internetProduct.getDiscounts(), product,
				shoppingCartContextVO);
		
		// productInformation
		ProductComponent productInformationComp = populateProductInformationCompHSIC(internetProduct, shoppingCartContextVO);
		if (productInformationComp != null) {
			product.addProductComponent(productInformationComp);
		}

		// clearance info
		if (cartItemVO.isSalesOrderItem()) {
			ProductComponent clearanceInfoComp = populateClearanceInfoComponent(internetProduct, product);
			if (clearanceInfoComp != null) {
				product.addProductComponent(clearanceInfoComp);
			}
		}



		return result;
	}

	private static void implicitEquipRemovalHsic(ShoppingCartContextVO shoppingCartContextVO, List<AvailableInternetProductItemVO> availableProductItems, Product product, BooleanHolder hasRent) {
		if(availableProductItems == null) {
			return;
		}
		for(AvailableInternetProductItemVO prodItmVO: availableProductItems) {
			if(prodItmVO.getEquipments() != null) {
				for(AvailableFFHEquipmentTypeVO equipTypeVO: prodItmVO.getEquipments()) {
					if(equipTypeVO.getExistingEquipmentList() != null) {
						for(FFHEquipmentItemVO equipItm: equipTypeVO.getExistingEquipmentList()) {
							if(equipItm.isExisting() && !equipItm.isCarryOver()) {
								ProductComponent removeProdComp = new ProductComponent();
								removeProdComp.setActionType(EnterpriseWLNSalesServicesConstants.RM);
								String externalCatalogueId = equipItm.getProductItemIdentifier().getExternalId();
								removeProdComp.setCatalogId(externalCatalogueId);
								//find a serial number
								if(shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null && shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null) {
									com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod = findOpProductComponentByExtCatId(externalCatalogueId, shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts());
									if(prod != null) {
										removeProdComp.setProductSerialNumber(prod.getProductSerialNumber());
										removeProdComp.setDescription(prod.getDescription());
										String acquisitionTypeVal = findProductCharacteristicValue(prod, Constants.ACQUISITION_TYPE);
										if(OP_ACQUISITION_TYPE_RENT.equalsIgnoreCase(acquisitionTypeVal)) {
											hasRent.value |= true;
										}
									}
								}
								product.addProductComponent(removeProdComp);
							}
						}
					}
				}
			}
		}
	}

	private static ProductComponent populateProductInformationCompHSIC(InternetProductTypeVO internetProduct, ShoppingCartContextVO shoppingCartContextVO) {
		if(internetProduct.getAppointmentDetail() != null
				&& internetProduct.getAppointmentDetail().getBookedAppointmentDate() != null
				&& internetProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate() != null
				&& shoppingCartContextVO.isEligibleForInstallCredit(internetProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate()) 
				&& shoppingCartContextVO.getInstallionCreditSweetener() != null) {
			List<ProductOffering> installCredits = buildInstallationCreditProductOfferings(shoppingCartContextVO.getInstallionCreditSweetener());
			if(installCredits != null && !installCredits.isEmpty()) {
				ProductComponent productInformationComp = new ProductComponent();
				productInformationComp.setName(EnterpriseWLNSalesServicesConstants.COMP_PRODUCT_INFORMATION);
		
				// adding installation credit for sweetener
				for(ProductOffering instCrd: installCredits) {
					productInformationComp.addProductOffering(instCrd);
				}
				return productInformationComp;
			}
		}
		return null;
	}

	private static ProductComponent populateClearanceInfoComponent(InternetProductTypeVO internetProduct, Product product) {
		ProductComponent clearanceInfoComponent = null;

		if ( (internetProduct.getCompetitiveExchangeCarrierInfo() != null) &&
			 (internetProduct.getCompetitiveExchangeCarrierInfo().getCompetitiveExchangeCarrierInd() != null) &&
			 (internetProduct.getCompetitiveExchangeCarrierInfo().getCompetitiveExchangeCarrierInd()) &&
			 (internetProduct.getCompetitiveExchangeCarrierInfo().getClecPhoneNumber() != null) &&
			 (internetProduct.getCompetitiveExchangeCarrierInfo().getClecPhoneNumber().getPhoneNumber() != null) ) {
			clearanceInfoComponent = new ProductComponent();
			clearanceInfoComponent.setName(Constants.CLEARANCE_INFO_COMPONENT_NAME);

			// primary telephone number
			clearanceInfoComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.PRIMARY_TELEPHONE_NUMBER_CHARACTERISTIC_NAME, String.valueOf(internetProduct.getCompetitiveExchangeCarrierInfo().getClecPhoneNumber().getPhoneNumber())));

			// leased loop flag
			if ( (internetProduct.getCompetitiveExchangeCarrierInfo().getCompetitiveLocalExchageCarrierId() != null) &&
				 (Constants.CLEC_SHAW_CARRIER_ID.equalsIgnoreCase(internetProduct.getCompetitiveExchangeCarrierInfo().getCompetitiveLocalExchageCarrierId())) ) {
				clearanceInfoComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.LEASED_LOOP_FLAG_CHARACTERISTIC_NAME, "false"));
			}
			else {
				clearanceInfoComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.LEASED_LOOP_FLAG_CHARACTERISTIC_NAME, "true"));
			}

			//commented out as we set bypassClearanceInd in OPCommonProvideTransformer using the matching logic in Feasibility check
//			for (ProductComponent orderActionAttributesComponent :  product.getProductComponents()) {
//				if ( (orderActionAttributesComponent != null) &&
//					 (EnterpriseWLNSalesServicesConstants.ORDER_ACTION_ATTRIBUTES.equals(orderActionAttributesComponent.getName())) &&
//					 (orderActionAttributesComponent.getProductCharacteristics() != null) &&
//					 (!orderActionAttributesComponent.getProductCharacteristics().isEmpty()) ) {
//					for (ProductCharacteristic productCharacteristic : orderActionAttributesComponent.getProductCharacteristics()) {
//						if ( (productCharacteristic != null) &&
//							 (productCharacteristic.getName() != null) &&
//							 (EnterpriseWLNSalesServicesConstants.BYPASS_CLEARANCE_IND.equals(productCharacteristic.getName())) ) {
//							productCharacteristic.setValue("false");
//
//							break;
//						}
//					}
//
//					break;
//				}
//			}
		}

		return clearanceInfoComponent;
	}

}
