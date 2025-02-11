package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryDiscount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryGift;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryGroup;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryGroupDiscountProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryGroupProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryOfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryPrintToReceipt;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AccessoryTier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AddOn;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.BillCredit;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Category;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ContractCustomization;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DeviceBalanceWaiver;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountDeliveryType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.EquipmentBasePrice;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.EquipmentProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Feature;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.GiftDiscountPaymentPlan;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.IncludedServiceConstraint;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.MinimumMonthlyServiceCommitment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferCategory;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.PaymentOption;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductEligiblity;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProgramPromotion;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.PurchaseDiscount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.SalesCategory;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ServiceDiscount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.TransactionType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelessEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelessEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelessOfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelessPricePlanSet;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelessServiceSet;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.schemasclone.OfferProductHeader;
import com.telus.csm.ewlnsc.domain.schemasclone.ProductComponentIdentifier;
import com.telus.csm.ewlnsc.domain.schemasclone.ProductOrderType;
import com.telus.csm.ewlnsc.domain.schemasclone.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineOfferFilter;
import com.telus.csm.ewlnsc.util.LoggerUtil;


public class GetSalesOfferDetailSchemaIndependentTransformer {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetSalesOfferDetailSchemaIndependentTransformer.class);

	private GetSalesOfferDetailSchemaIndependentTransformer() {
		
	}

	public static com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO transform(GetSalesOfferDetailRequestVO requestVO) {
		com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO schemaSalesOfferDetailRequestVO = null;

		if (requestVO != null) {
			schemaSalesOfferDetailRequestVO = new com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO();
			schemaSalesOfferDetailRequestVO.setOfferCode(requestVO.getOfferCode());
			schemaSalesOfferDetailRequestVO.setOfferId(requestVO.getOfferId());
			schemaSalesOfferDetailRequestVO.setOfferInstanceId(requestVO.getOfferInstanceId());
			schemaSalesOfferDetailRequestVO.setOfferProgramId(requestVO.getOfferProgramId());
			schemaSalesOfferDetailRequestVO.setOperationHeader(GetAvailableSweetenerOfferListTransformer.tranform(requestVO.getOperationHeader()));
			schemaSalesOfferDetailRequestVO.setPerspectiveDate(requestVO.getPerspectiveDate());
			schemaSalesOfferDetailRequestVO.setSalesOfferCriteria(transform(requestVO.getSalesOfferCriteria()));
			schemaSalesOfferDetailRequestVO.setSystemID(requestVO.getSystemID());
		}

		return schemaSalesOfferDetailRequestVO;
	}

	public static com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO transform(SalesOfferCriteriaVO salesOfferCriteria) {
		com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO schemaSalesOfferCriteriaVO = null;

		if (salesOfferCriteria != null) {
			schemaSalesOfferCriteriaVO = new com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO();
			schemaSalesOfferCriteriaVO.setBillingAccountNumber(salesOfferCriteria.getBillingAccountNumber());
			schemaSalesOfferCriteriaVO.setCustomerId(salesOfferCriteria.getCustomerId());
			schemaSalesOfferCriteriaVO.setOfferFilter(transform(salesOfferCriteria.getOfferFilter()));
			schemaSalesOfferCriteriaVO.setPromotionCd(salesOfferCriteria.getPromotionCd());
			schemaSalesOfferCriteriaVO.setServiceAddress(salesOfferCriteria.getServiceAddress());
			schemaSalesOfferCriteriaVO.setSubscribedServiceIdentifier(salesOfferCriteria.getSubscribedServiceIdentifier());
		}
		
		return schemaSalesOfferCriteriaVO;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter transform(WirelineOfferFilter offerFilter) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter schemaWirelineOfferFilter = null;

		if (offerFilter != null) {
			schemaWirelineOfferFilter = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter();
			schemaWirelineOfferFilter.setAccountTypeCode(offerFilter.getAccountTypeCode());
			schemaWirelineOfferFilter.setAccountSubTypeCode(offerFilter.getAccountSubTypeCode());
			schemaWirelineOfferFilter.setOfferContractTermCd(offerFilter.getOfferContractTermCd());
			schemaWirelineOfferFilter.setBundleInd(offerFilter.isBundleInd());
			schemaWirelineOfferFilter.setNpaNxx(offerFilter.getNpaNxx());
			schemaWirelineOfferFilter.setProductList(transformProductList(offerFilter.getProductList()));
		}

		return schemaWirelineOfferFilter;
	}

	public static List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader> transformProductList(List<OfferProductHeader> productList) {
		List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader> schemaProductList = null;

		if ( (productList != null) && (!productList.isEmpty()) ) {
			schemaProductList = new ArrayList<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader>();

			for (OfferProductHeader offerProductHeader : productList) {
				com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader schemaOfferProductHeader = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader();
				schemaOfferProductHeader.setProductOrderType(transform(offerProductHeader.getProductOrderType()));
				schemaOfferProductHeader.setProductTypeCd(offerProductHeader.getProductTypeCd());
				schemaOfferProductHeader.setContractTermCd(offerProductHeader.getContractTermCd());
				schemaOfferProductHeader.setWinBackInd(offerProductHeader.isWinBackInd());
				schemaOfferProductHeader.setRecontractInd(offerProductHeader.isRecontractInd());
				schemaOfferProductHeader.setProductComponentList(transformProductComponentIdentifierList(offerProductHeader.getProductComponentList()));
				schemaProductList.add(schemaOfferProductHeader);
			}
		}
		/*
		for (ProductTypeVO productType : cartItem.getProducts()) {
			if (productType.getInternetProduct() != null) {
				OfferProductHeader offerProductHeader = new OfferProductHeader();
				offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);
				offerProductHeader.setContractTermCd(productType.getInternetProduct().getSelectedContractTermCd());
				buildProductComponents(offerProductHeader.getProductComponentList(), productType.getInternetProduct().getProductComponents());
				result.getProductList().add(offerProductHeader);
			}
			
			if (productType.getTelevisionProduct() != null) {
				OfferProductHeader offerProductHeader = new OfferProductHeader();
				offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);
				offerProductHeader.setContractTermCd(productType.getTelevisionProduct().getSelectedContractTermCd());
				buildProductComponents(offerProductHeader.getProductComponentList(), productType.getTelevisionProduct().getProductComponents());
				result.getProductList().add(offerProductHeader);
			}

			if (productType.getHomePhoneProduct() != null) {
				OfferProductHeader offerProductHeader = new OfferProductHeader();
				offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.SING);
				offerProductHeader.setContractTermCd(productType.getHomePhoneProduct().getSelectedContractTermCd());
				buildProductComponents(offerProductHeader.getProductComponentList(), productType.getHomePhoneProduct().getProductComponents());
				result.getProductList().add(offerProductHeader);
			}
			*/

		return schemaProductList;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType transform(ProductOrderType productOrderType) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType schemaProductOrderType = null;

		if (productOrderType != null) {
			schemaProductOrderType = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType();
			schemaProductOrderType.setProductOrderTypeCd(productOrderType.getProductOrderTypeCd());
			schemaProductOrderType.setProductOrderSubTypeCd(productOrderType.getProductOrderSubTypeCd());	
		}

		return schemaProductOrderType;
	}

	private static List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier> transformProductComponentIdentifierList(List<ProductComponentIdentifier> productComponentList) {
		List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier> schemaProductComponentList = null;

		if ( (productComponentList != null) && (!productComponentList.isEmpty()) ) {
			schemaProductComponentList = new ArrayList<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier>();

			for (ProductComponentIdentifier productComponent : productComponentList) {
				com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier schemaProductComponent = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier();
				schemaProductComponent.setProductCatalogueIdentifier(productComponent.getProductCatalogueIdentifier());
				schemaProductComponent.setParentProductCatalogueIdentifier(productComponent.getParentProductCatalogueIdentifier());
				schemaProductComponentList.add(schemaProductComponent);
			}
		}

		return schemaProductComponentList;
	}


	public static GetSalesOfferDetailResponseVO2 transform(com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO schemaResponseVO) {
		GetSalesOfferDetailResponseVO2 responseVO = null;

		if (schemaResponseVO != null) {
			responseVO = new GetSalesOfferDetailResponseVO2();
			responseVO.setErrorCode(schemaResponseVO.getErrorCode());
			responseVO.setMessageList(schemaResponseVO.getMessageList());
			responseVO.setMsgHasError(schemaResponseVO.isMsgHasError());
			responseVO.setMsgHasMandatory(schemaResponseVO.isMsgHasMandatory());
			responseVO.setMsgHasWarning(schemaResponseVO.isMsgHasWarning());
			responseVO.setOffer(transform(schemaResponseVO.getOffer()));
			responseVO.setProgramPromotion(transform(schemaResponseVO.getProgramPromotion()));
			responseVO.setRecontractEligibleProductCodeList(schemaResponseVO.getRecontractEligibleProductCodeList());
			responseVO.setTransactionId(schemaResponseVO.getTransactionId());
			responseVO.setCartItemOfferId(schemaResponseVO.getCartItemOfferId());
		}

		return responseVO;
	}

	private static ProgramPromotion transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProgramPromotion programPromotion) {
		ProgramPromotion result = null;
		if(programPromotion != null) {
			result = new ProgramPromotion();
			result.setPromotionCode(programPromotion.getPromotionCode());
			result.setPromotionId(programPromotion.getPromotionId());
			result.setProgramId(programPromotion.getProgramId());
			result.setChallengeQuestionList(programPromotion.getChallengeQuestionList());
			result.setMandatoryResponseInd(programPromotion.isMandatoryResponseInd());
			result.setResponseTypeId(programPromotion.getResponseTypeId());
			result.setResponseFormatTypeId(programPromotion.getResponseFormatTypeId());
			result.setResponseMaxLengthCnt(programPromotion.getResponseMaxLengthCnt());
			result.setChallengeQuestionInd(programPromotion.isChallengeQuestionInd());
		}
		return result;
	}

	private static Offer transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer offer) {
		Offer result = null;
		if(offer != null) {
			result = new Offer();
			result.setOfferProduct(transform(offer.getOfferProduct()));
			result.setOfferId(offer.getOfferId());
			result.setOfferReferenceId(offer.getOfferReferenceId());
			result.setOfferDescriptionList(offer.getOfferDescriptionList());
			result.setOfferTierId(offer.getOfferTierId());
		    result.setOfferCategoryList(transformOfferCategory(offer.getOfferCategoryList()));
		    result.setBasePriceAmt(offer.getBasePriceAmt());
		    result.setWirelessOfferCode(offer.getWirelessOfferCode());
		    result.setPaymentSupportTypeCode(offer.getPaymentSupportTypeCode());
		    result.setRankingNum(offer.getRankingNum());
		    result.setProductEligiblityList(transformProductEligiblity(offer.getProductEligiblityList()));
		    result.setAssignedOfferInd(offer.isAssignedOfferInd());
		    result.setOfferMarketingMessageList(offer.getOfferMarketingMessageList());
		}
		return result;
	}

	private static List<ProductEligiblity> transformProductEligiblity(List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductEligiblity> productEligiblityList) {
		List<ProductEligiblity> result = null;
		if(productEligiblityList != null) {
			result = new ArrayList<ProductEligiblity>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductEligiblity peV3: productEligiblityList) {
				ProductEligiblity pe = transform(peV3);
				if(pe != null) {
					result.add(pe);
				}
			}
		}
		return result;
	}

	private static ProductEligiblity transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductEligiblity peV3) {
		ProductEligiblity result = null;
		if(peV3 != null) {
			result = new ProductEligiblity();
			//new attribute form OIS3.7
			result.setProductEnforcementGroupCd(peV3.getProductEnforcementGroupCd());
			result.setProductTypeCd(peV3.getProductTypeCd());
			result.setProductTemplateIdentifier(transform(peV3.getProductTemplateIdentifier()));
			result.setProductComponentIdentifierList(transformProductCatalogueIdentifierList(peV3.getProductComponentIdentifierList()));
			result.setTransactionType(transform(peV3.getTransactionType()));
			result.setContractTermList(peV3.getContractTermList());
		}
		return result;
	}

	private static TransactionType transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType transactionType) {
		TransactionType result = null;
		if(transactionType != null) {
			result = new TransactionType();
			result.setTransactionTypeCode(transactionType.getTransactionTypeCode());
			result.setTransactionSubTypeCode(transactionType.getTransactionSubTypeCode());
		}
		return result;
	}

	private static List<ProductCatalogueIdentifier> transformProductCatalogueIdentifierList(List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier> productComponentIdentifierList) {
		List<ProductCatalogueIdentifier> result = null;
		if(productComponentIdentifierList != null) {
			result = new ArrayList<ProductCatalogueIdentifier>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier pciV3: productComponentIdentifierList) {
				ProductCatalogueIdentifier pci = transform(pciV3);
				if(pci != null) {
					result.add(pci);
				}
			}
		}
		return result;
	}

	private static ProductCatalogueIdentifier transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier productTemplateIdentifier) {
		ProductCatalogueIdentifier result = null;
		if(productTemplateIdentifier != null) {
			result = new ProductCatalogueIdentifier();
			result.setProductCatalogueId(productTemplateIdentifier.getProductCatalogueId());
			result.setExternalProductCatalogId(productTemplateIdentifier.getExternalProductCatalogId());
		}
		return result;
	}

	private static List<OfferCategory> transformOfferCategory(List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferCategory> offerCategoryList) {
		List<OfferCategory> result = null;
		if(offerCategoryList != null) {
			result = new ArrayList<OfferCategory>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferCategory ocV3: offerCategoryList) {
				OfferCategory oc = transform(ocV3);
				if(oc != null) {
					result.add(oc);
				}
			}
		}
		return result;
	}

	private static OfferCategory transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferCategory ocV3) {
		OfferCategory result = null;
		if(ocV3 != null) {
			result = new OfferCategory();
			result.setCategoryTypeCode(ocV3.getCategoryTypeCode());
			result.setCategoryList(transformCategory(ocV3.getCategoryList()));

		}
		return result;
	}

	private static List<Category> transformCategory(List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Category> categoryList) {
		List<Category> result = null;
		if(categoryList != null) {
			result = new ArrayList<Category>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Category cV3: categoryList) {
				Category c = transform(cV3);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static Category transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Category cV3) {
		Category result = null;
		if(cV3 != null) {
			result = new Category();
		    result.setCategoryCode(cV3.getCategoryCode());
		    result.setCategoryDescriptionList(cV3.getCategoryDescriptionList());
		}
		return result;
	}

	private static OfferProduct transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferProduct offerProduct) {
		OfferProduct result = null;
		if(offerProduct != null) {
			result = new OfferProduct();
			result.setWirelessOfferProduct(transform(offerProduct.getWirelessOfferProduct()));
			result.setWirelineOfferProductList(transformWirelineOfferProductList(offerProduct.getWirelineOfferProductList()));
			result.setAccessoryOfferProduct(transform(offerProduct.getAccessoryOfferProduct()));
		}
		return result;
	}

	private static AccessoryOfferProduct transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryOfferProduct accessoryOfferProduct) {

		AccessoryOfferProduct result = null;
		
		if(accessoryOfferProduct != null) {
			result = new AccessoryOfferProduct();
			
			result.setPrintToReceipt(transform(accessoryOfferProduct.getPrintToReceipt()));
			result.setGiftList(transformGiftList(accessoryOfferProduct.getGiftList()));
			result.setDiscountList(transformAccessoryDiscountList(accessoryOfferProduct.getDiscountList()));
			result.setAccessoryOfferCode(accessoryOfferProduct.getAccessoryOfferCode());
			result.setEligibilityTypeCd(accessoryOfferProduct.getEligibilityTypeCd());
			result.setStackableInd(accessoryOfferProduct.isStackableInd());
			result.setRecursiveInd(accessoryOfferProduct.isRecursiveInd());
			result.setProductCatalogSystemId(accessoryOfferProduct.getProductCatalogSystemId());
			result.setContractTermList(accessoryOfferProduct.getContractTermList());
			result.setIncompatibleDiscountIdList(transformIncompatibleDiscountIdList(accessoryOfferProduct.getIncompatibleDiscountIdList()));
			result.setContractEnforcedProductCdList(accessoryOfferProduct.getContractEnforcedProductCdList());
		}

		return result;
	}

	private static List<ProductCatalogueIdentifier> transformIncompatibleDiscountIdList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier> incompatibleDiscountIdList) {
		List<ProductCatalogueIdentifier> result = null;
		if(incompatibleDiscountIdList != null) {
			result = new ArrayList<ProductCatalogueIdentifier>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier incompatibleDiscountId: incompatibleDiscountIdList) {
				ProductCatalogueIdentifier c = transform(incompatibleDiscountId);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static List<AccessoryDiscount> transformAccessoryDiscountList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryDiscount> discountList) {
		List<AccessoryDiscount> result = null;
		if(discountList != null) {
			result = new ArrayList<AccessoryDiscount>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryDiscount cV3: discountList) {
				AccessoryDiscount c = transform(cV3);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static AccessoryDiscount transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryDiscount accessoryDiscount) {
		AccessoryDiscount result = null;
		
		if(accessoryDiscount != null) {
			result = new AccessoryDiscount();
			
			result.setDiscountSubTypeCd(accessoryDiscount.getDiscountSubTypeCd());
			result.setEqualOrLessValueInd(accessoryDiscount.isEqualOrLessValueInd());
			result.setItemQty(accessoryDiscount.getItemQty());
			result.setEndDiscountTypeCd(accessoryDiscount.getEndDiscountTypeCd());
			result.setDiscountType(transform(accessoryDiscount.getDiscountType()));
			result.setDiscountAmt(accessoryDiscount.getDiscountAmt());
			result.setGroupList(transformGroupList(accessoryDiscount.getGroupList()));

		}
		return result;
	}

	private static List<AccessoryGroup> transformGroupList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroup> groupList) {
		List<AccessoryGroup> result = null;
		if(groupList != null) {
			result = new ArrayList<AccessoryGroup>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroup accessoryGroup: groupList) {
				AccessoryGroup c = transform(accessoryGroup);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static AccessoryGroup transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroup accessoryGroup) {
		AccessoryGroup result = null;
		
		if(accessoryGroup != null) {
			result = new AccessoryGroup();
			
			result.setItemQty(accessoryGroup.getItemQty());
			result.setDiscountLevelCd(accessoryGroup.getDiscountLevelCd());
			result.setDiscountType(transform(accessoryGroup.getDiscountType()));
			result.setDiscountAmt(accessoryGroup.getDiscountAmt());
			result.setProductList(transformAccessoryGroupDiscountProductList(accessoryGroup.getProductList()));
			result.setTierList(transformTierList(accessoryGroup.getTierList()));
			result.setExcludedProductList(transformExcludedProductList(accessoryGroup.getExcludedProductList()));

		}
		
		
		return result;
	}

	private static List<AccessoryGroupProduct> transformExcludedProductList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroupProduct> excludedProductList) {
		List<AccessoryGroupProduct> result = null;
		if(excludedProductList != null) {
			result = new ArrayList<AccessoryGroupProduct>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroupProduct product: excludedProductList) {
				AccessoryGroupProduct c = transform(product);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static AccessoryGroupProduct transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroupProduct product) {
		AccessoryGroupProduct result = null;
		
		if(product != null) {
			result = new AccessoryGroupProduct();
			
			result.setProductCategoryId(product.getProductCategoryId());
			result.setProductSubCategoryId(product.getProductSubCategoryId());
			result.setProductClassId(product.getProductClassId());
			result.setProductVendorId(product.getProductVendorId());
			result.setProductManufacturerId(product.getProductManufacturerId());
			result.setProductCode(product.getProductCode());

		}
		return result;
	}

	private static List<AccessoryTier> transformTierList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryTier> tierList) {
		List<AccessoryTier> result = null;
		if(tierList != null) {
			result = new ArrayList<AccessoryTier>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryTier tier: tierList) {
				AccessoryTier c = transform(tier);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static AccessoryTier transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryTier tier) {
		AccessoryTier result = null;
		
		if(tier != null) {
			result = new AccessoryTier();
			
			result.setMinItemQty(tier.getMinItemQty());
			result.setMaxItemQty(tier.getMaxItemQty());
			result.setDiscountType(transform(tier.getDiscountType()));
			result.setDiscountAmt(tier.getDiscountAmt());

		}
		return result;
	}

	private static List<AccessoryGroupDiscountProduct> transformAccessoryGroupDiscountProductList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroupDiscountProduct> productList) {
		List<AccessoryGroupDiscountProduct> result = null;
		if(productList != null) {
			result = new ArrayList<AccessoryGroupDiscountProduct>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroupDiscountProduct product: productList) {
				AccessoryGroupDiscountProduct c = transform(product);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static AccessoryGroupDiscountProduct transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGroupDiscountProduct product) {
		AccessoryGroupDiscountProduct result = null;
		
		if(product != null) {
			result = new AccessoryGroupDiscountProduct();
			
		    result.setDiscountType(transform(product.getDiscountType()));
		    result.setDiscountAmt(product.getDiscountAmt());
		    result.setProductCategoryId(product.getProductCategoryId());
		    result.setProductSubCategoryId(product.getProductSubCategoryId());
		    result.setProductClassId(product.getProductClassId());
		    result.setProductVendorId(product.getProductVendorId());
		    result.setProductManufacturerId(product.getProductManufacturerId());
		    result.setProductCode(product.getProductCode());

		}
		return result;
	}

	private static List<AccessoryGift> transformGiftList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGift> giftList) {
		List<AccessoryGift> result = null;
		if(giftList != null) {
			result = new ArrayList<AccessoryGift>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGift cV3: giftList) {
				AccessoryGift c = transform(cV3);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static AccessoryGift transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryGift accessoryGift) {
		AccessoryGift result = null;
		
		if(accessoryGift != null) {
			
			result = new AccessoryGift();
			
			result.setTypeCode(accessoryGift.getTypeCode());
			result.setSystemId(accessoryGift.getSystemId());
			result.setProductCode(accessoryGift.getProductCode());
			result.setValidDayCount(accessoryGift.getValidDayCount());
			result.setGiftAmount(accessoryGift.getGiftAmount());
			result.setGiftDiscountPaymentPlanList(transformGiftDiscountPaymentPlanList(accessoryGift.getGiftDiscountPaymentPlanList()));

		}
		return result;
	}

	private static List<GiftDiscountPaymentPlan> transformGiftDiscountPaymentPlanList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.GiftDiscountPaymentPlan> giftDiscountPaymentPlanList) {
		List<GiftDiscountPaymentPlan> result = null;
		if(giftDiscountPaymentPlanList != null) {
			result = new ArrayList<GiftDiscountPaymentPlan>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.GiftDiscountPaymentPlan giftDiscountPaymentPlan: giftDiscountPaymentPlanList) {
				GiftDiscountPaymentPlan c = transform(giftDiscountPaymentPlan);
				if(c != null) {
					result.add(c);
				}
			}
		}
		return result;
	}

	private static GiftDiscountPaymentPlan transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.GiftDiscountPaymentPlan giftDiscountPaymentPlan) {
		GiftDiscountPaymentPlan result = null;
		
		if(giftDiscountPaymentPlan != null) {
			result = new GiftDiscountPaymentPlan();
			
			result.setPaymentTypeCd(giftDiscountPaymentPlan.getPaymentTypeCd());
			result.setPaymentAmt(giftDiscountPaymentPlan.getPaymentAmt());
			result.setPaymentInstallmentCnt(giftDiscountPaymentPlan.getPaymentInstallmentCnt());
			result.setImmediatePaymentInd(giftDiscountPaymentPlan.isImmediatePaymentInd());
		}
			
		return result;
	}

	private static AccessoryPrintToReceipt transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AccessoryPrintToReceipt printToReceipt) {
		AccessoryPrintToReceipt result = null;
		
		if(printToReceipt != null) {
			result = new AccessoryPrintToReceipt();
			
			result.setPrintDescriptionList(printToReceipt.getPrintDescriptionList());
		}
		return result;
	}

	private static List<WirelineOfferProduct> transformWirelineOfferProductList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct> wirelineOfferProductList) {
		List<WirelineOfferProduct> result = null;
		if(wirelineOfferProductList != null) {
			result = new ArrayList<WirelineOfferProduct>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct itmV3: wirelineOfferProductList) {
				WirelineOfferProduct itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static WirelineOfferProduct transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct wirelineOfferProduct) {
		WirelineOfferProduct result = null;
		if(wirelineOfferProduct != null) {
			result = new WirelineOfferProduct();
			result.setWirelineEquipmentList(transformWirelineEquipmentList(wirelineOfferProduct.getWirelineEquipmentList()));
			result.setAddOn(transform(wirelineOfferProduct.getAddOn()));
			//result.setDiscountList(transformDiscountList(wirelineOfferProduct.getDiscountList()));//Don't set discounts from the OIS we will get discounts/promotions from Promotion Qualification now.
			result.setFeature(transform(wirelineOfferProduct.getFeature()));

			result.setProductTypeCode(wirelineOfferProduct.getProductTypeCode());
			result.setProductTemplateProductTypeCode(wirelineOfferProduct.getProductTemplateProductTypeCode());
			result.setProductCategoryCode(wirelineOfferProduct.getProductCategoryCode());
			result.setTransactionTypeList(transformTransactionTypeList(wirelineOfferProduct.getTransactionTypeList()));
			result.setContractTermList(wirelineOfferProduct.getContractTermList());
		    result.setProductTemplateIdentifier(transform(wirelineOfferProduct.getProductTemplateIdentifier()));
		    result.setProductComponentList(transformProductComponentList(wirelineOfferProduct.getProductComponentList()));
		    result.setProductCatalogSystemId(wirelineOfferProduct.getProductCatalogSystemId());
		    result.setMainComponentIdentifier(transform(wirelineOfferProduct.getMainComponentIdentifier()));
		}
		return result;
	}

	private static List<ProductComponent> transformProductComponentList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent> productComponentList) {
		List<ProductComponent> result = null;
		if(productComponentList != null) {
			result = new ArrayList<ProductComponent>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent productComponent : productComponentList) {
				ProductComponent itm = transform(productComponent);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static ProductComponent transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent productComponent) {
		ProductComponent result = null;
		
		if(productComponent != null) {
			result = new ProductComponent();
			
			result.setProductCatalogueItemList(transfromProductCatalogueItemList(productComponent.getProductCatalogueItemList()));
			result.setMarketingDescriptionList(productComponent.getMarketingDescriptionList());
			result.setMarketingProductPriceAmt(productComponent.getMarketingProductPriceAmt());
			result.setIncludedServiceConstraintList(transformIncludedServiceConstraintList(productComponent.getIncludedServiceConstraintList()));

		}
		return result;
	}

	private static List<IncludedServiceConstraint> transformIncludedServiceConstraintList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.IncludedServiceConstraint> includedServiceConstraintList) {
		List<IncludedServiceConstraint> result = null;
		if(includedServiceConstraintList != null) {
			result = new ArrayList<IncludedServiceConstraint>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.IncludedServiceConstraint productComponent : includedServiceConstraintList) {
				IncludedServiceConstraint itm = transform(productComponent);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static IncludedServiceConstraint transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.IncludedServiceConstraint includedServiceConstraint) {
		IncludedServiceConstraint result = null;
		
		if(includedServiceConstraint != null) {
			result = new IncludedServiceConstraint();
			
			result.setSalesCategory(transform(includedServiceConstraint.getSalesCategory()));
			result.setServiceTypeCodeList(includedServiceConstraint.getServiceTypeCodeList());
			result.setMinItemQty(includedServiceConstraint.getMinItemQty());
			result.setMaxItemQty(includedServiceConstraint.getMaxItemQty());

			
		}
		return result;
	}

	private static SalesCategory transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.SalesCategory salesCategory) {
		SalesCategory result = null;
		
		if(salesCategory != null) {
			result = new SalesCategory();
			
			result.setSalesCategoryCode(salesCategory.getSalesCategoryCode());
			result.setSalesCategoryNameList(salesCategory.getSalesCategoryNameList());

		}
		return result;
	}

	private static List<ProductCatalogueItem> transfromProductCatalogueItemList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem> productCatalogueItemList) {
		List<ProductCatalogueItem> result = null;
		if(productCatalogueItemList != null) {
			result = new ArrayList<ProductCatalogueItem>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem productComponent : productCatalogueItemList) {
				ProductCatalogueItem itm = transform(productComponent);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static Feature transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Feature feature) {
		Feature result = null;
		
		if(feature != null) {
			result = new Feature();
			
			result.setMinQty(feature.getMinQty());
			result.setMaxQty(feature.getMaxQty());
			result.setIncludedProductCatalogueItemList(transformProductCatalogueItemList(feature.getIncludedProductCatalogueItemList()));
			result.setOptionalProductCatalogueItemList(transformProductCatalogueItemList(feature.getOptionalProductCatalogueItemList()));

		}
		return result;
	}

	private static AddOn transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AddOn addOn) {
		AddOn result = null;
		
		if(addOn != null) {
			result = new AddOn();
			
			result.setIncludedProductCatalogueItemList(transformProductCatalogueItemList(addOn.getIncludedProductCatalogueItemList()));
		    result.setOptionalProductCatalogueItemList(transformProductCatalogueItemList(addOn.getOptionalProductCatalogueItemList()));

		}
		return result;
	}

	private static List<ProductCatalogueItem> transformProductCatalogueItemList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem> productCatalogueItemList) {
		List<ProductCatalogueItem> result = null;
		if(productCatalogueItemList != null) {
			result = new ArrayList<ProductCatalogueItem>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem item: productCatalogueItemList) {
				ProductCatalogueItem itm = transform(item);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

//	private static List<Discount> transformDiscountList(
//			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount> discountList) {
//		List<Discount> result = null;
//		if(discountList != null) {
//			result = new ArrayList<Discount>();
//			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount itmV3: discountList) {
//				Discount itm = transform(itmV3);
//				if(itm != null) {
//					result.add(itm);
//				}
//			}
//		}
//		return result;
//	}

//	private static Discount transform(
//			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount discount) {
//		Discount result = null;
//		if(discount != null) {
//			result = new Discount();
//			result.setTransactionTypeList(transformTransactionTypeList(discount.getTransactionTypeList()));
//			result.setContractTermList(discount.getContractTermList());
//		    result.setProductCatalogueItemList(transformProductCatalogueItemList(discount.getProductCatalogueItemList()));
//		    result.setIncludedInd(discount.isIncludedInd());
//		    result.setStackableInd(discount.isStackableInd());
//		    result.setWinbackInd(discount.isWinbackInd());
//		    result.setRecontractingInd(discount.isRecontractingInd());
//		    result.setCrossProductDependencyList(transformCrossProductDependencyList(discount.getCrossProductDependencyList()));
//		    result.setMarketingDescriptionList(discount.getMarketingDescriptionList());
//		    result.setDiscountCode(discount.getDiscountCode());
//		    result.setDiscountProductCatalogueItemList(transformDiscountProductCatalogueItemList(discount.getDiscountProductCatalogueItemList()));
//		    result.setMarketingNameList(discount.getMarketingNameList());
//		    result.setDiscountedComponentTypeCd(discount.getDiscountedComponentTypeCd());
//		    result.setPreSelectedDiscountInd(discount.isPreSelectedDiscountInd());
//		}
//
//		return result;
//	}

//	private static List<DiscountProductCatalogueItem> transformDiscountProductCatalogueItemList(
//			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountProductCatalogueItem> discountProductCatalogueItemList) {
//		List<DiscountProductCatalogueItem> result = null;
//		if(discountProductCatalogueItemList != null) {
//			result = new ArrayList<DiscountProductCatalogueItem>();
//			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountProductCatalogueItem discountProductCatalogueItem: discountProductCatalogueItemList) {
//				DiscountProductCatalogueItem itm = transform(discountProductCatalogueItem);
//				if(itm != null) {
//					result.add(itm);
//				}
//			}
//		}
//		return result;
//	}

//	private static DiscountProductCatalogueItem transform(
//			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountProductCatalogueItem discountProductCatalogueItem) {
//		DiscountProductCatalogueItem result = null;
//		
//		if(discountProductCatalogueItem != null) {
//			result = new DiscountProductCatalogueItem();
//			
//			result.setDiscountPriceAmt(discountProductCatalogueItem.getDiscountPriceAmt());
//			result.setRecurringCount(discountProductCatalogueItem.getRecurringCount());
//			result.setDiscountType(transform(discountProductCatalogueItem.getDiscountType()));
//			result.setProductCatalogueIdentifier(transform(discountProductCatalogueItem.getProductCatalogueIdentifier()));
//			result.setProductCatalogueNameList(discountProductCatalogueItem.getProductCatalogueNameList());
//			result.setParentProductCatalogueIdentifier(transform(discountProductCatalogueItem.getParentProductCatalogueIdentifier()));
//			result.setProductCatalogueBasePriceAmt(discountProductCatalogueItem.getProductCatalogueBasePriceAmt());
//			result.setPackEligibleItemInd(discountProductCatalogueItem.isPackEligibleItemInd());
//			result.setServiceTypeCode(discountProductCatalogueItem.getServiceTypeCode());
//			result.setItemRankNum(discountProductCatalogueItem.getItemRankNum());
//		}
//		return result;
//	}

//	private static List<CrossProductDependency> transformCrossProductDependencyList(
//			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.CrossProductDependency> crossProductDependencyList) {
//	List<CrossProductDependency> result = null;
//	if(crossProductDependencyList != null) {
//		result = new ArrayList<CrossProductDependency>();
//		for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.CrossProductDependency crossProductDependency: crossProductDependencyList) {
//			CrossProductDependency itm = transform(crossProductDependency);
//			if(itm != null) {
//				result.add(itm);
//			}
//		}
//	}
//	return result;
//	}

//	private static CrossProductDependency transform(
//			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.CrossProductDependency crossProductDependency) {
//		CrossProductDependency result = null;
//		
//		if(crossProductDependency != null) {
//			result = new CrossProductDependency();
//			
//			result.setProductCode(crossProductDependency.getProductCode());
//			result.setContractTermList(crossProductDependency.getContractTermList());
//
//		}
//		return result;
//	}

	private static List<TransactionType> transformTransactionTypeList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType> transactionTypeList) {
		
		List<TransactionType>  result = null;
		
		if(transactionTypeList != null) {
			result = new ArrayList<TransactionType>();
			
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType transactionTypeIn : transactionTypeList) {
				TransactionType transactionTypeOut = new TransactionType();
				
				if(transactionTypeIn != null) {
					
					transactionTypeOut.setTransactionTypeCode(transactionTypeIn.getTransactionTypeCode());
					transactionTypeOut.setTransactionSubTypeCode(transactionTypeIn.getTransactionSubTypeCode());

					result.add(transactionTypeOut);
				}
			}
		}
		
		return result;
	}

	private static List<WirelineEquipment> transformWirelineEquipmentList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment> wirelineEquipmentList) {
		List<WirelineEquipment> result = null;
		if(wirelineEquipmentList != null) {
			result = new ArrayList<WirelineEquipment>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment itmV3: wirelineEquipmentList) {
				WirelineEquipment itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static WirelineEquipment transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment wirelineEquipment) {
		WirelineEquipment result = null;
		if(wirelineEquipment != null) {
			result = new WirelineEquipment();
			result.setProductCatalogueItem(transform(wirelineEquipment.getProductCatalogueItem()));
			result.setMinQty(wirelineEquipment.getMinQty());
			result.setMaxQty(wirelineEquipment.getMaxQty());
			result.setPurchaseEquipmentList(transformWirelineEquipmentItemList(wirelineEquipment.getPurchaseEquipmentList()));
			result.setRentalEquipmentList(transformWirelineEquipmentItemList(wirelineEquipment.getRentalEquipmentList()));
			result.setByodEquipmentList(transformWirelineEquipmentItemList(wirelineEquipment.getByodEquipmentList()));
		}
		return result;
	}

	private static List<WirelineEquipmentItem> transformWirelineEquipmentItemList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem> itmV3List) {
		List<WirelineEquipmentItem> result = null;
		if(itmV3List != null) {
			result = new ArrayList<WirelineEquipmentItem>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem itmV3: itmV3List) {
				WirelineEquipmentItem itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static WirelineEquipmentItem transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem wirelineEquipmentItem) {
		WirelineEquipmentItem result = null;
		
		if(wirelineEquipmentItem != null) {
			result = new WirelineEquipmentItem();
			
			result.setMaterialItemCode(wirelineEquipmentItem.getMaterialItemCode());
			result.setEquipmentPurchasePriceAmt(wirelineEquipmentItem.getEquipmentPurchasePriceAmt());
			result.setEquipmentRentalPriceAmt(wirelineEquipmentItem.getEquipmentRentalPriceAmt());
			result.setEquipmentLateReturnPriceAmt(wirelineEquipmentItem.getEquipmentLateReturnPriceAmt());
			result.setEquipmentDescriptionList(wirelineEquipmentItem.getEquipmentDescriptionList());
			result.setDeliveryMethodList(wirelineEquipmentItem.getDeliveryMethodList());
			result.setIncludedInd(wirelineEquipmentItem.isIncludedInd());
			result.setPurchaseDiscountList(transformPurchaseDiscount(wirelineEquipmentItem.getPurchaseDiscountList()));
			result.setPaymentOptionList(transformPaymentOptionList(wirelineEquipmentItem.getPaymentOptionList()));
		}
		return result;
	}

	private static List<PurchaseDiscount> transformPurchaseDiscount(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.PurchaseDiscount> purchaseDiscountList) {
		List<PurchaseDiscount> result = null;
		if(purchaseDiscountList != null) {
			result = new ArrayList<PurchaseDiscount>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.PurchaseDiscount purchaseDiscount: purchaseDiscountList) {
				PurchaseDiscount itm = transform(purchaseDiscount);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}
	

	private static PurchaseDiscount transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.PurchaseDiscount purchaseDiscount) {
		PurchaseDiscount result = null;
		
		if (purchaseDiscount != null) {
			result = new PurchaseDiscount();
			
			result.setPurchaseDiscountCode(purchaseDiscount.getPurchaseDiscountCode());
			result.setContractTermId(purchaseDiscount.getContractTermId());
		}
		return result;
	}
	
	private static List<PaymentOption> transformPaymentOptionList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.PaymentOption> paymentOptionList) {
		List<PaymentOption> result = null;
		if(paymentOptionList != null) {
			result = new ArrayList<PaymentOption>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.PaymentOption paymentOption: paymentOptionList) {
				PaymentOption itm = transform(paymentOption);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static PaymentOption transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.PaymentOption paymentOption) {
		PaymentOption result = null;
		
		if (paymentOption != null) {
			result = new PaymentOption();
			
			result.setPaymentOptionTypeCd(paymentOption.getPaymentOptionTypeCd());
			result.setPaymentAmt(paymentOption.getPaymentAmt());
			result.setPaymentInstallmentQty(paymentOption.getPaymentInstallmentQty());
			result.setPaymentInstallmentAmt(paymentOption.getPaymentInstallmentAmt());
			result.setPaymentPlanId(transform(paymentOption.getPaymentPlanId()));
			result.setPaymentPlanParentId(transform(paymentOption.getPaymentPlanParentId()));
		}
		return result;
	}
	

	private static ProductCatalogueItem transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem productCatalogueItem) {
		ProductCatalogueItem result = null;
		if(productCatalogueItem != null) {
			result = new ProductCatalogueItem();
			result.setProductCatalogueIdentifier(transform(productCatalogueItem.getProductCatalogueIdentifier()));
			result.setProductCatalogueNameList(productCatalogueItem.getProductCatalogueNameList());
			result.setParentProductCatalogueIdentifier(transform(productCatalogueItem.getParentProductCatalogueIdentifier()));
			result.setProductCatalogueBasePriceAmt(productCatalogueItem.getProductCatalogueBasePriceAmt());
			result.setPackEligibleItemInd(productCatalogueItem.isPackEligibleItemInd());
			result.setServiceTypeCode(productCatalogueItem.getServiceTypeCode());
			result.setItemRankNum(productCatalogueItem.getItemRankNum());
		}
		return result;
	}

	private static WirelessOfferProduct transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessOfferProduct wirelessOfferProduct) {
		WirelessOfferProduct result = null;
		if(wirelessOfferProduct != null) {
			result = new WirelessOfferProduct();
			result.setWirelessEquipment(transform(wirelessOfferProduct.getWirelessEquipment()));
			result.setWirelessPricePlanSet(transform(wirelessOfferProduct.getWirelessPricePlanSet()));
			result.setWirelessServiceSetList(transformWirelessServiceSetList(wirelessOfferProduct.getWirelessServiceSetList()));
			result.setMinimumServiceCommitmentList(transform(wirelessOfferProduct.getMinimumServiceCommitmentList()));
			result.setDeviceBalanceWaiver(transform(wirelessOfferProduct.getDeviceBalanceWaiver()));
			result.setBillCreditList(transformBillCreditList(wirelessOfferProduct.getBillCreditList()));
			result.setServiceDiscountList(transformServiceDiscountList(wirelessOfferProduct.getServiceDiscountList()));

			result.setTransactionType(transform(wirelessOfferProduct.getTransactionType()));
			result.setEquipmentContextInd(wirelessOfferProduct.isEquipmentContextInd());
			result.setContractTermId(wirelessOfferProduct.getContractTermId());
			result.setClientOwnEquipmentInd(wirelessOfferProduct.isClientOwnEquipmentInd());
			result.setKeepExistingPlanId(wirelessOfferProduct.getKeepExistingPlanId());
			result.setFinanceCode(wirelessOfferProduct.getFinanceCode());
			result.setOptionTypeId(wirelessOfferProduct.getOptionTypeId());
			result.setContractCustomization(transform(wirelessOfferProduct.getContractCustomization()));
			result.setProductCatalogSystemId(wirelessOfferProduct.getProductCatalogSystemId());
		}
		return result;
	}

	private static ContractCustomization transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ContractCustomization contractCustomization) {
		ContractCustomization result = null;
		if(contractCustomization != null) {
			result = new ContractCustomization();
			result.setForcedContractStartDate(contractCustomization.getForcedContractStartDate());
			result.setForcedCommitmentMonthCount(contractCustomization.getForcedCommitmentMonthCount());
		}
		return result;
	}

	private static List<ServiceDiscount> transformServiceDiscountList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ServiceDiscount> serviceDiscountList) {
		List<ServiceDiscount> result = null;
		if(serviceDiscountList != null) {
			result = new ArrayList<ServiceDiscount>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ServiceDiscount itmV3: serviceDiscountList) {
				ServiceDiscount itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static ServiceDiscount transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ServiceDiscount serviceDiscount) {
		ServiceDiscount result = null;
		if(serviceDiscount != null) {
			result = new ServiceDiscount();
			result.setDiscountedServiceCode(serviceDiscount.getDiscountedServiceCode());
			result.setUseDefaultMonthsInd(serviceDiscount.isUseDefaultMonthsInd());
			result.setMonthCount(serviceDiscount.getMonthCount());
		}
		return result;
	}

	private static List<BillCredit> transformBillCreditList(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.BillCredit> billCreditList) {
		List<BillCredit> result = null;
		if(billCreditList != null) {
			result = new ArrayList<BillCredit>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.BillCredit itmV3: billCreditList) {
				BillCredit itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static BillCredit transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.BillCredit billCredit) {
		BillCredit result = null;
		if(billCredit != null) {
			result = new BillCredit();
			result.setCreditReasonCode(billCredit.getCreditReasonCode());
			result.setCreditReverseReasonCode(billCredit.getCreditReverseReasonCode());
			result.setCreditAmt(billCredit.getCreditAmt());
			result.setRecurringCount(billCredit.getRecurringCount());
			result.setImmediateBalanceImpactInd(billCredit.isImmediateBalanceImpactInd());
			result.setDeviceBalanceRelatedInd(billCredit.isDeviceBalanceRelatedInd());
			result.setActivationPortinInd(billCredit.isActivationPortinInd());
		}
		return result;
	}

	private static DeviceBalanceWaiver transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DeviceBalanceWaiver deviceBalanceWaiver) {
		DeviceBalanceWaiver result = null;
		if(deviceBalanceWaiver != null) {
			result = new DeviceBalanceWaiver();
		    result.setWaiveAmt(deviceBalanceWaiver.getWaiveAmt());
		    result.setWaiveType(transform(deviceBalanceWaiver.getWaiveType()));
		    result.setMaximumDaysRemainingCount(deviceBalanceWaiver.getMaximumDaysRemainingCount());
		}
		return result;
	}

	private static List<MinimumMonthlyServiceCommitment> transform(
			List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.MinimumMonthlyServiceCommitment> minimumServiceCommitmentList) {
		List<MinimumMonthlyServiceCommitment> result = null;
		if(minimumServiceCommitmentList != null) {
			result = new ArrayList<MinimumMonthlyServiceCommitment>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.MinimumMonthlyServiceCommitment itmV3: minimumServiceCommitmentList) {
				MinimumMonthlyServiceCommitment itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static MinimumMonthlyServiceCommitment transform(
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.MinimumMonthlyServiceCommitment commitment) {
		MinimumMonthlyServiceCommitment result = null;
		if(commitment != null) {
			result = new MinimumMonthlyServiceCommitment();
		    result.setMinimumCommitmentTypeCode(commitment.getMinimumCommitmentTypeCode());
		    result.setMinimumCommitmentAmt(commitment.getMinimumCommitmentAmt());
		}
		return result;
	}

	private static List<WirelessServiceSet> transformWirelessServiceSetList(List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessServiceSet> wirelessServiceSetList) {
		List<WirelessServiceSet> result = null;
		if(wirelessServiceSetList != null) {
			result = new ArrayList<WirelessServiceSet>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessServiceSet itmV3: wirelessServiceSetList) {
				WirelessServiceSet itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static WirelessServiceSet transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessServiceSet wirelessServiceSet) {
		WirelessServiceSet result = null;
		if(wirelessServiceSet != null) {
			result = new WirelessServiceSet();
			result.setEnforcementId(wirelessServiceSet.getEnforcementId());
			result.setRestrictionId(wirelessServiceSet.getRestrictionId());
			result.setServiceCodeList(wirelessServiceSet.getServiceCodeList());
			result.setServiceGroupCodeList(wirelessServiceSet.getServiceGroupCodeList());
		}
		return result;
	}

	private static WirelessPricePlanSet transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessPricePlanSet wirelessPricePlanSet) {
		WirelessPricePlanSet result = null;
		if(wirelessPricePlanSet != null) {
			result = new WirelessPricePlanSet();
			result.setEnforcementId(wirelessPricePlanSet.getEnforcementId());
			result.setPricePlanCodeList(wirelessPricePlanSet.getPricePlanCodeList());
			result.setPricePlanGroupCodeList(wirelessPricePlanSet.getPricePlanGroupCodeList());
		}
		return result;
	}

	private static WirelessEquipment transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessEquipment wirelessEquipment) {
		WirelessEquipment result = null;
		if(wirelessEquipment != null) {
			result = new WirelessEquipment();
		    result.setAllEquipmentDiscountDeliveryType(transform(wirelessEquipment.getAllEquipmentDiscountDeliveryType()));
		    result.setAllEquipmentOfferDiscountAmt(wirelessEquipment.getAllEquipmentOfferDiscountAmt());
		    result.setAllEquipmentDiscountType(transform(wirelessEquipment.getAllEquipmentDiscountType()));
		    result.setAllEquipmentInd(wirelessEquipment.isAllEquipmentInd());
		    result.setWirelessEquipmentItemList(transformWirelessEquipmentItemList(wirelessEquipment.getWirelessEquipmentItemList()));
		}
		return result;
	}

	private static List<WirelessEquipmentItem> transformWirelessEquipmentItemList(List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessEquipmentItem> wirelessEquipmentItemList) {
		List<WirelessEquipmentItem> result = null;
		if(wirelessEquipmentItemList != null) {
			result = new ArrayList<WirelessEquipmentItem>();
			for(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessEquipmentItem itmV3: wirelessEquipmentItemList) {
				WirelessEquipmentItem itm = transform(itmV3);
				if(itm != null) {
					result.add(itm);
				}
			}
		}
		return result;
	}

	private static WirelessEquipmentItem transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelessEquipmentItem itmV3) {
		WirelessEquipmentItem result = null;
		if(itmV3 != null) {
			result = new WirelessEquipmentItem();
		    result.setEquipment(transform(itmV3.getEquipment()));
		    result.setDiscountDeliveryType(transform(itmV3.getDiscountDeliveryType()));
		    result.setDiscountType(transform(itmV3.getDiscountType()));
		    result.setDiscountAmt(itmV3.getDiscountAmt());
		    result.setEquipmentBasePrice(transform(itmV3.getEquipmentBasePrice()));
		}
		return result;
	}

	private static EquipmentBasePrice transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.EquipmentBasePrice equipmentBasePrice) {
		EquipmentBasePrice result = null;
		if(equipmentBasePrice != null) {
			result = new EquipmentBasePrice();
			result.setBasePriceAmt(equipmentBasePrice.getBasePriceAmt());
		}
		return result;
	}

	private static EquipmentProduct transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.EquipmentProduct equipment) {
		EquipmentProduct result = null;
		if(equipment != null) {
			result = new EquipmentProduct();
			result.setEquipmentProductCode(equipment.getEquipmentProductCode());
			result.setEquipmentProductId(equipment.getEquipmentProductId());
		}
		return result;
	}

	private static DiscountType transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountType discountType) {
		DiscountType result = null;
		if(discountType != null) {
			result = new DiscountType();
			result.setDiscountTypeCode(discountType.getDiscountTypeCode());
		}
		return result;
	}

	private static DiscountDeliveryType transform(com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountDeliveryType discountDeliveryType) {
		DiscountDeliveryType result = null;
		if(discountDeliveryType != null) {
			result = new DiscountDeliveryType();
			result.setDiscountDeliveryTypeCode(discountDeliveryType.getDiscountDeliveryTypeCode());
		}
		return result;
	}
}