package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.StringHolder;

import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristic;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOffering;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;;

public class OPTvProvideTransformer extends OPCommonProvideTransformer {
	private static final LoggerUtil logger = LoggerUtil.getLogger(OPTvProvideTransformer.class);

	public static ProductOrderItem transform(OperationHeader operationHeader, CartItemVO cartItemVO, ShoppingCartContextVO shoppingCartContextVO) {
		TelevisionProductTypeVO televisionProduct = cartItemVO.getProducts().getTelevisionProduct();
		WirelineOfferProduct wirelineOfferProduct = findWirelineOfferProduct(shoppingCartContextVO, cartItemVO,
				EnterpriseWLNSalesServicesConstants.TTV);
		if (wirelineOfferProduct == null) {
			throw new RuntimeException("Cannot find a TTV WirelineOfferProduct for CartItemRelationId: "
					+ cartItemVO.getCartItemRelationId());
		}

		Date serviceRequiredDate = findServiceRequiredDate(cartItemVO,
				EnterpriseWLNSalesServicesConstants.TTV,
				shoppingCartContextVO);
		ProductOrderItem result = populateCommonProductOrderItem(operationHeader, cartItemVO, wirelineOfferProduct,
				EnterpriseWLNSalesServicesConstants.TTV, televisionProduct.getProductOrderType().getOrderTypeCd(),
				shoppingCartContextVO, serviceRequiredDate);
		Product product = result.getProduct();

		// product components
		if (televisionProduct.getProductComponents() != null) {
			for (ProductComponentVO prodCompVO : televisionProduct.getProductComponents()) {
				ProductComponent prodComp = populateProdComp(wirelineOfferProduct, prodCompVO,
						EnterpriseWLNSalesServicesConstants.TTV, televisionProduct.getSelectedContractTermCd(),
						shoppingCartContextVO);
				if (prodComp != null) {
					product.addProductComponent(prodComp);
				}
			}
		}

		// productInformation
		ProductComponent productInformationComp = populateProductInformationCompTTV(televisionProduct, shoppingCartContextVO);
		product.addProductComponent(productInformationComp);

		// equipment
		doEquipment(shoppingCartContextVO, televisionProduct, wirelineOfferProduct, product);

		// addons
		if (televisionProduct.getAddOns() != null) {
			for (FFHProductPlanAddOnTypeVO addon : televisionProduct.getAddOns()) {
				ProductComponent addonComp = createAddonProductComponent(wirelineOfferProduct, addon);
				if (addonComp != null) {
					product.addProductComponent(addonComp);
				}
			}
		}

		// ttvQuestionnaire
		product.addProductComponent(getTtvQuestionnaireComponent());

		// productOffering/discounts
		if (televisionProduct.getSweeteners() != null) {
			List<SweetenerOfferSummary> sweetenerOffers = shoppingCartContextVO
					.getSweetnersByProduct(EnterpriseWLNSalesServicesConstants.TTV, cartItemVO.getCartItemRelationId());
			for (FFHSweetenerTypeVO sweetenerVO : televisionProduct.getSweeteners()) {
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

		addProductOfferings(wirelineOfferProduct.getDiscountList(), televisionProduct.getDiscounts(), product, shoppingCartContextVO);

		return result;
	}

	private static void doEquipment(ShoppingCartContextVO shoppingCartContextVO,
			TelevisionProductTypeVO televisionProduct, WirelineOfferProduct wirelineOfferProduct, Product product) {
		if (televisionProduct.getEquipments() != null) {
			for (FFHEquipmentTypeVO wlnEquipment : televisionProduct.getEquipments()) {
				ProductComponent equipComp = new ProductComponent();
				equipComp.setName(Constants.REGISTRATION_NUMBER);
				equipComp.setActionType(findTopActionType(televisionProduct.getProductOrderType().getOrderTypeCd()));
				product.addProductComponent(equipComp);

				// if BYOD TTV
				if (wlnEquipment.getAcquisitionType().isCustomerOwnedIndicator() != null
						&& wlnEquipment.getAcquisitionType().isCustomerOwnedIndicator()) {
					equipComp.setCatalogId(Constants.TTVREGISTRATIONNUMBERCID);// 33
					{
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(
								Constants.SALES_CHANNEL + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
										+ Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(
								Constants.SALES_CHANNEL + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
										+ Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = String.valueOf(AcquisitionType.POE);
						equipComp
								.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
					}
					{// setTopBoxModel
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.SET_TOP_BOX_MODEL
								+ Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
								+ Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.SET_TOP_BOX_MODEL
								+ Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
								+ Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = "MediaBox";
						equipComp
								.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
					}
					{// setTopBoxMake
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.SET_TOP_BOX_MAKE
								+ Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
								+ Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.SET_TOP_BOX_MAKE
								+ Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
								+ Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = "TVX";
						equipComp
								.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
					}
				} else {
					StringHolder catalogueIdHolder = new StringHolder();
					ProductComponent prodComp = populateProductEquipment(EnterpriseWLNSalesServicesConstants.TTV, wirelineOfferProduct, wlnEquipment, shoppingCartContextVO, catalogueIdHolder, new StringHolder());
					if (prodComp != null) {
						equipComp.addProductComponent(prodComp);
					}
					if (StringUtils.isEmpty(equipComp.getCatalogId())) {
						equipComp.setCatalogId(catalogueIdHolder.value);
					}
					String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(
							Constants.SET_TOP_BOX_MODEL + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
									+ Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
					String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(
							Constants.SET_TOP_BOX_MODEL + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
									+ Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
					String value = "";
					// set value to a model name
					if (equipComp.getProductComponents() != null) {
						String materialCodeCharName = PRODUCT_CHARACTERISTCS_PROPS.getProperty(
								Constants.MIC + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV
										+ Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						for (ProductComponent prod : equipComp.getProductComponents()) {
							if (prod.getProductCharacteristics() != null) {
								for (ProductCharacteristic prodChar : prod.getProductCharacteristics()) {
									if (materialCodeCharName.equalsIgnoreCase(prodChar.getName())) {
										EquipmentCatalogueItemDO equipDO = CommonWLNGridHelper.getInstance()
												.getEquipmentByProductCode(prodChar.getValue());
										if (equipDO != null) {
											value = equipDO.getModelName();
											break;
										}
									}
								}
							}
						}
					}
					equipComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
				}
			}
		}
	}

	private static ProductComponent populateProductInformationCompTTV(TelevisionProductTypeVO televisionProduct, ShoppingCartContextVO shoppingCartContextVO) {
		ProductComponent productInformationComp = new ProductComponent();
		productInformationComp.setName(EnterpriseWLNSalesServicesConstants.COMP_PRODUCT_INFORMATION);
		
		//contract term
		String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.CONTRACT_TERM + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV + Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
		String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.CONTRACT_TERM + Constants.UNDERSCORE + EnterpriseWLNSalesServicesConstants.TTV + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
		String cotractTermMonths = televisionProduct.getSelectedContractTermCd();
		String value = contractTermMonthsToOpYears(cotractTermMonths);
		productInformationComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));

		// adding installation credit for sweetener
		if(televisionProduct.getAppointmentDetail() != null
				&& televisionProduct.getAppointmentDetail().getBookedAppointmentDate() != null
				&& televisionProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate() != null
				&& shoppingCartContextVO.isEligibleForInstallCredit(televisionProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate()) 
				&& shoppingCartContextVO.getInstallionCreditSweetener() != null) {
			List<ProductOffering> installCredits = buildInstallationCreditProductOfferings(shoppingCartContextVO.getInstallionCreditSweetener());
			if(installCredits != null && !installCredits.isEmpty()) {
				for(ProductOffering instCrd: installCredits) {
					productInformationComp.addProductOffering(instCrd);
				}
			}
		}

		return productInformationComp;
	}

	private static ProductComponent getTtvQuestionnaireComponent() {
		//TODO currently hardcoded
		ProductComponent ttvQuestionnaire = new ProductComponent();
		ttvQuestionnaire.setName("ttvQuestionnaire");
		
		ProductComponent technicalQuestionnaireTTV = new ProductComponent();
		technicalQuestionnaireTTV.setCatalogId("50380");
		technicalQuestionnaireTTV.setName("TechnicalQuestionnaireTTV");
		technicalQuestionnaireTTV.addProductCharacteristic(createProductCharacteristic("83488", "Rent_Or_Own", "Own"));
		technicalQuestionnaireTTV.addProductCharacteristic(createProductCharacteristic("83510", "On_Site_Manager", "Yes"));
		technicalQuestionnaireTTV.addProductCharacteristic(createProductCharacteristic("20410308", "Adult_Presence", "Yes"));
		technicalQuestionnaireTTV.addProductCharacteristic(createProductCharacteristic("20410318", "TV_Sets", "1"));
		
		ttvQuestionnaire.addProductComponent(technicalQuestionnaireTTV);
		return ttvQuestionnaire;
	}

}
