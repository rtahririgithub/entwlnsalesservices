package com.telus.csm.ewlnsc.transformer;

import static com.telus.csm.ewlnsc.util.CharacteristicUtils.getByName;
import static com.telus.csm.ewlnsc.util.CharacteristicUtils.getValue;
import static com.telus.csm.ewlnsc.util.CharacteristicUtils.getValueByName;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.telus.csm.ess.common.domain.DescriptionVO;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterResponse;
import com.telus.csm.ewlnsc.delegate.FIFAProductOfferingQualificationDelegate.FIFAOfferCategory;
import com.telus.csm.ewlnsc.delegate.ProductItemConstraintGroupDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.CharacteristicOptionVO;
import com.telus.csm.ewlnsc.domain.CharacteristicVO;
import com.telus.csm.ewlnsc.domain.CustomerVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FIFAProductOfferingsVO;
import com.telus.csm.ewlnsc.domain.ProductTypeBaseVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AddOn;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Category;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Feature;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.IncludedServiceConstraint;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferCategory;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.PaymentOption;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.SalesCategory;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.TransactionType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.schemasclone.ProductCatalogueItemVO2;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.csm.ffpo.rest.domain.CategoryRef;
import com.telus.csm.ffpo.rest.domain.ChannelRef;
import com.telus.csm.ffpo.rest.domain.Place;
import com.telus.csm.ffpo.rest.domain.ProductOfferingQualification;
import com.telus.csm.ffpo.rest.domain.ProductOfferingQualificationItem;
import com.telus.csm.ffpo.rest.domain.QualificationItemRelationship;
import com.telus.csm.ffpo.rest.domain.RelatedPartyRef;
import com.telus.csm.ffpo.rest.domain.TCharacteristic;
import com.telus.csm.ffpo.rest.domain.TCharacteristicOption;
import com.telus.csm.ffpo.rest.domain.TPricing;
import com.telus.csm.ffpo.rest.domain.TProductOffering;
import com.telus.csm.ffpo.rest.domain.TProductOfferingBundledProdOfferOption;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;


/**
 * Refer mapping defined at https://drive.google.com/drive/folders/1F19zDJlg8J9KhoroD24OeBIZYPa94VZT/OQ_OIS_Mapping_V1_25Jun.xlsx
 * @author x134022
 *
 */
public class FIFAProductOfferingQualificationTransformer implements EnterpriseWLNSalesServicesConstants {
	
	private static final String FFIA_CAT_ID_EQUIPEMNT="9148302515313935425";
	private static final String FFIA_CAT_ID_HOME_SECURITY_EQUIPEMNT="9150451420813201788";
	private static final String FIFA_CHAR_ASSOCIATED_OFFER = "9155012898413958349";
	
	private static final String ROLE_CUSTOMER = "customer";
	private static final String ROLE_SERVICEADDRESS = "service address";
	private static final String CATEGORYID_CUSTOMER = "9134661890013196039";
	private static final String CHANNEL_OUTLETID_PREFIX = "CPMS_CURRENTCHANNELOUTLETID_";
	private static final String CATEGORY = "category";
	private static final String WIRELINE = "WIRELINE";
	private static Map<String, String> ttvPackCategoryMap = null;
	private static Map<String, String> singCategoryMap = null;
	private static Map<String, String> deliveryMethodMap=null;
	
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(FIFAProductOfferingQualificationTransformer.class);
	
	static {
		ttvPackCategoryMap = new HashMap<String, String>();
		ttvPackCategoryMap.put("9136528836413413259", "9136080250413413278");
		ttvPackCategoryMap.put("9142418206813327614", "9136201148713458177");
		ttvPackCategoryMap.put("9146943588313806263", "9139557680013312078");
//		selectCategoryMap.put("", "9136201148713458176");
		ttvPackCategoryMap.put("9153357751313013740", "9153364436313016766");
		
		singCategoryMap = new HashMap<String, String>();
		singCategoryMap.put("9142271336313591449", "9136923654113578813");
		
		deliveryMethodMap = new HashMap<String, String> ();
		deliveryMethodMap.put( "9155793538813291983", "Installer_supplied");
		deliveryMethodMap.put( "9155793538813292020", "Self_install");
		deliveryMethodMap.put( "9155793538813292023", "Retailer_supplied");
	}


	
	public static List<MessageList> transformMsgs(List<Message> messageList) {
		// TODO Auto-generated method stub
		return null;
	}

	public static GetFIFAPoductOfferingAdapterRequest transform(ShoppingCartVO cartvo, FIFAOfferCategory offerCategory ) {
		
		GetFIFAPoductOfferingAdapterRequest result = new GetFIFAPoductOfferingAdapterRequest();
		result.setSalesTransactionId(cartvo.getOperationHeader().getSalesTransactionId());

		ProductOfferingQualification poq = transformProductOfferingQualificationCommonInfo( cartvo );
		
		poq.setProductOfferingQualificationItem( convertToProductOfferingQualificationItem(cartvo, offerCategory, result ));
		result.setProductOfferingQualification(poq);

		result.setProductType(offerCategory.getProduct());
		result.setCategoryType(offerCategory.getType());
		result.setCategoryId(offerCategory.getId());
		result.setCategoryName(offerCategory.getName());

		return result;
	}
	
	//for SHS offers
	public static GetFIFAPoductOfferingAdapterRequest transformChildOfferRequest ( ShoppingCartVO cartvo , FIFAOfferCategory offerCategory ) {
		
		GetFIFAPoductOfferingAdapterRequest result = new GetFIFAPoductOfferingAdapterRequest();
		result.setSalesTransactionId(cartvo.getOperationHeader().getSalesTransactionId());

		ProductOfferingQualification poq = transformProductOfferingQualificationCommonInfo( cartvo );

		poq.setProductOfferingQualificationItem( transformForChildOfferingQualificationItems( cartvo, result ));
		result.setProductOfferingQualification(poq);

		result.setProductType(offerCategory.getProduct());
		result.setCategoryType(offerCategory.getType());

		return result;
	}

	//this is for creating new ProductOfferingQualification with common information populated 
	private static ProductOfferingQualification transformProductOfferingQualificationCommonInfo(ShoppingCartVO cartvo) {
		
		ProductOfferingQualification poq = new ProductOfferingQualification();
		poq.addRelatedPartyItem( transform(cartvo.getCustomer()) );
		poq.setChannel( transformToChannel( cartvo.getShoppingProfile().getUserProfile()) );
		poq.setPlace( transformToPlace(cartvo.getServiceAddress()) );
		return poq;
	}

	private static List<ProductOfferingQualificationItem> convertToProductOfferingQualificationItem(
			ShoppingCartVO cartvo, FIFAOfferCategory offerCategory, GetFIFAPoductOfferingAdapterRequest adapterReq ) {
		
		List<ProductOfferingQualificationItem> result = new ArrayList<ProductOfferingQualificationItem>();

		CartItemVO cartItemVO = (cartvo != null && !CollectionUtils.isEmpty(cartvo.getCartItemList()))
				? cartvo.getCartItemList().get(0)
				: null;
		if (cartItemVO != null) {

			String productType = getProductType(cartvo.getCartItemList().get(0));

			if ( ! EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY.equalsIgnoreCase(offerCategory.getType())) {
				// FiFa category is not needed for offer summary
				
				adapterReq.setCategoryId(offerCategory.getId());
				
				CategoryRef category = new CategoryRef().id( offerCategory.getId() );
				TProductOffering tproductOffering = new TProductOffering();
				tproductOffering.setCategory( Arrays.asList(category) );

				ProductOfferingQualificationItem productOfferingQualificationItem = new ProductOfferingQualificationItem();
				productOfferingQualificationItem.setProductOffering(tproductOffering);
				result.add(productOfferingQualificationItem);
				productOfferingQualificationItem.setId( String.valueOf( result.size() ) );
			}

			// offerId
			String offerId = cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId();
			
			if (offerId != null) {
				
				adapterReq.setOfferId(offerId);
				
				ProductOfferingQualificationItem productOfferingQualificationItemOffer = new ProductOfferingQualificationItem();

				TProductOffering tproductOfferingOfferOffer = new TProductOffering();
				tproductOfferingOfferOffer.setId(offerId);
				productOfferingQualificationItemOffer.setProductOffering(tproductOfferingOfferOffer);

				if ( ! EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_SUMMARY.equalsIgnoreCase(offerCategory.getType())) {
					
					//this is for child offering.
					QualificationItemRelationship relationship = new QualificationItemRelationship();
					relationship.setId("1");
					
					//QC83756 fix, to retrieve child offering, the relationshipType shall be "bundledProductOffering", 
					//so that OQ response will contain productOfferingPrice.
					//relationship.setType("withItem");
					relationship.setType("bundledProductOffering");
					List<QualificationItemRelationship> qualificationItemRelationshipList = new ArrayList<QualificationItemRelationship>();
					qualificationItemRelationshipList.add(relationship);
					productOfferingQualificationItemOffer.setQualificationItemRelationship(qualificationItemRelationshipList);

				}
				result.add(productOfferingQualificationItemOffer);
				productOfferingQualificationItemOffer.setId( String.valueOf( result.size() ) );
			}
		}
		return result;
	}

	private static List<ProductOfferingQualificationItem> transformForChildOfferingQualificationItems (	ShoppingCartVO cartvo, GetFIFAPoductOfferingAdapterRequest adapterReq ) {
		
		
		List<ProductOfferingQualificationItem> result = new ArrayList<ProductOfferingQualificationItem>();

		CartItemVO cartItemVO = (cartvo != null && !CollectionUtils.isEmpty(cartvo.getCartItemList()))
				? cartvo.getCartItemList().get(0)
				: null;

		if (cartItemVO != null) {

			// main offer
			String offerId = cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId();
			
			if (offerId != null) { //main offer
				
				StringBuilder sb = new StringBuilder( "mainOfferId=").append( offerId );
				
				adapterReq.setOfferId(offerId);
				
				ProductOfferingQualificationItem productOfferingQualificationItemOffer = newOfferingQualificationItem ( offerId , "bundledProductOffering" );

				result.add(productOfferingQualificationItemOffer);
				productOfferingQualificationItemOffer.setId( String.valueOf( result.size() ) );

				//commitment offering
				if ( CollectionUtils.isNotEmpty( cartItemVO.getRelatedPromotionList() ) ) {
					
					List<String> commitmentOfferIds = new ArrayList<String> ();
					
					for ( RelatedImmediatePromotionVO promo : cartItemVO.getRelatedPromotionList() ) {
					
						ProductOfferingQualificationItem qualificationItem = newOfferingQualificationItem ( promo.getId() , "withItem" );
						commitmentOfferIds.add( promo.getId() +":" + promo.getName() );
						
						result.add(qualificationItem);
						qualificationItem.setId( String.valueOf( result.size() ) );
					}
					
					sb.append( ";  commitmentOffers").append( commitmentOfferIds );
				}
				
				logger.debug("transformForChildOfferingQualificationItems", sb.toString());
			}
			
		}
		
		return result;
	}
	
	private static ProductOfferingQualificationItem newOfferingQualificationItem( String offerId, String relationType ) {
		
		ProductOfferingQualificationItem qualificationItem = new ProductOfferingQualificationItem();

		qualificationItem.setProductOffering ( new TProductOffering().id(offerId) );
		
		if ( StringUtils.isNotBlank(relationType ) ) {

			qualificationItem.addQualificationItemRelationshipItem( new QualificationItemRelationship().id("1").type( relationType ) );
		}
		
		return qualificationItem;
	}

	
	private static List<String> getSweetnerIdList(ProductTypeVO products) {
//		FFHSweetenerTypeVO sweetnerList =  ! CollectionUtils.isEmpty(products.getHomePhoneProduct()) ? products.getHomePhoneProduct().getSweeteners()
//											: ! CollectionUtils.isEmpty(products.getInternetProduct()) ? products.getInternetProduct().getSweeteners()
//													: ! CollectionUtils.isEmpty(products.getTelevisionProduct()) ? products.getTelevisionProduct().getSweeteners()
//															: null;
//		if()
		return null;
	}

	public static String getProductType(CartItemVO cartItemVO) {
		String productType = null; // SING,TTV,HSIC, SHS
		
		if (cartItemVO != null) {
			
			ProductTypeVO productTypeVO = cartItemVO.getProducts();
			if (productTypeVO != null) {

				if ( productTypeVO.getInternetProduct() != null ) {
					
					productType = EnterpriseWLNSalesServicesConstants.HSIC;
					
				} else if (productTypeVO.getHomePhoneProduct() != null ) {
					
					productType =  EnterpriseWLNSalesServicesConstants.SING;
					
				} else if (productTypeVO.getTelevisionProduct() != null ) {
					
					productType =  EnterpriseWLNSalesServicesConstants.TTV;
					
				} else if ( productTypeVO.getHomeSecurityProduct()!=null) {
					
					productType =  EnterpriseWLNSalesServicesConstants.HOME_SECURITY;
				}

			}
		}

		return productType;
	}

	private static ChannelRef transformToChannel(UserProfileVO userProfileVO) {
		ChannelRef channel = new ChannelRef();
		String channelOutletId = CHANNEL_OUTLETID_PREFIX;
		if (userProfileVO.getSalesRepAssociatedOutlet() != null
				&& !userProfileVO.getSalesRepAssociatedOutlet().isEmpty()) {
			channelOutletId += userProfileVO.getSalesRepAssociatedOutlet().get(0).getChannelOutletId();
		}

		channel.setId(channelOutletId); // TODO: confirm with Michael, where it is populated
		channel.setAtReferenceType("External_ID");
		return channel;
	}

	private static Place transformToPlace(ServiceAddressVO serviceAddress) {
		Place place = null;
		if (serviceAddress != null) {
			place = new Place();
			place.setId(serviceAddress.getServiceAddressId());
			place.setRole(ROLE_SERVICEADDRESS);
		}
		return place;
	}

	private static RelatedPartyRef transform(CustomerVO customer) {

		RelatedPartyRef relatedPartyCustomer = new RelatedPartyRef();
		relatedPartyCustomer.setRole(ROLE_CUSTOMER);
		TCharacteristic charecterstics = new TCharacteristic();
		charecterstics.setName(CATEGORY);
		charecterstics.setValue(CATEGORYID_CUSTOMER);
		List<TCharacteristic> characteristicList = new ArrayList<TCharacteristic>();
		characteristicList.add(charecterstics);
		relatedPartyCustomer.setCharacteristic(characteristicList);
		return relatedPartyCustomer;
	}

	public static void populateOfferSummary(FIFAProductOfferingsVO productOfferingsVO,
			GetFIFAPoductOfferingAdapterResponse fifaPoductOfferingAdapterResponse, ProductTypeBaseVO productTypeBaseVO, String productType) {
		
		
		ProductOfferingQualification productOfferingQualification = fifaPoductOfferingAdapterResponse.getProductOfferingQualification();
		
		final String functionName = "populateOfferSummary";
		if (productOfferingsVO == null)
			return;
		ProductOfferingQualificationItem poqItem = productOfferingQualification.getProductOfferingQualificationItem().get(0);

		if (poqItem != null && poqItem.getProductOffering() != null) {
			TProductOffering productOffering = poqItem.getProductOffering();
			List<Description> displayName = populateName(productOffering.getName());
			String productOfferingId = productOffering.getId();
			Offer offer = productOfferingsVO.getOffer() == null ? new Offer() : productOfferingsVO.getOffer();
			productOfferingsVO.setOffer(offer);
			offer.setTypeCode(WIRELINE);
			offer.setOfferId(Long.parseLong(productOfferingId));
			offer.setOfferDescriptionList(displayName);
			OfferCategory offerCategory = new OfferCategory();
			offerCategory.setCategoryTypeCode("OFFER_CATEGORY");
			Category category = new Category();
			category.setCategoryCode("CYO");
			offerCategory.getCategoryList().add(category);

			Category category2 = new Category();
			category2.setCategoryCode(productType);
			category2.setCategoryDescriptionList(displayName);
			offerCategory.getCategoryList().add(category2);

			offerCategory.setCategoryTypeCode("OFFER_PRODUCT_FAMILY_CATEGORY");
			String basePriceAmt = getBasePrice(productOffering.getProductOfferingPrice());
			offer.getOfferCategoryList().add(offerCategory);

			offer.setPaymentSupportTypeCode("subsidy");
			offer.setAssignedOfferInd(false);
			WirelineOfferProduct wirelineOfferProduct = new WirelineOfferProduct();

			OfferProduct product = offer.getOfferProduct() == null ? new OfferProduct() : offer.getOfferProduct();
			offer.setOfferProduct(product);
			offer.getOfferProduct().getWirelineOfferProductList().add(wirelineOfferProduct);
			wirelineOfferProduct.setProductTypeCode(productType);
			if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType)) {
				if (convertToString(productOffering.getCategory()).contains("9150253640113241856")) {
					wirelineOfferProduct.setProductCategoryCode("OPTIK");
				}
				else if (convertToString(productOffering.getCategory()).contains("9146775320213795833")) {
					wirelineOfferProduct.setProductCategoryCode("PIK");
				}
			}
			TransactionType txnType = new TransactionType();
			txnType.setTransactionTypeCode("activation");
			wirelineOfferProduct.getTransactionTypeList().add(txnType);
			wirelineOfferProduct.setProductTemplateProductTypeCode(productType);
			ProductComponent productComponent = new ProductComponent();
			wirelineOfferProduct.getProductComponentList().add(productComponent);
			ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
			productComponent.getProductCatalogueItemList().add(productCatalogueItem);
//			productComponent.setMarketingDescriptionList( populateName("descrtst"));
			ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier();
			productCatalogueItem.setProductCatalogueIdentifier(productCatalogueIdentifier);
			productCatalogueIdentifier.setProductCatalogueId(productOfferingId);
			productCatalogueItem.setProductCatalogueNameList(displayName);

			productComponent.setMarketingDescriptionList(populateName(productOffering.getDescription()));
			if (basePriceAmt != null) {
				productCatalogueItem.setProductCatalogueBasePriceAmt(new Double(basePriceAmt));
				productComponent.setMarketingProductPriceAmt(new Double(basePriceAmt));
			}
			productCatalogueItem.setParentProductCatalogueIdentifier(new ProductCatalogueIdentifier()); // TODO: remove
																										// after testing
			productCatalogueItem.getParentProductCatalogueIdentifier().setProductCatalogueId("1");

			Map<String, TCharacteristic> prodSpecCharValueUseMap = buildProdSpecCharValueUse(
					productOffering.getProdSpecCharValueUse());
			
			//FIFA-SHS
			if (EnterpriseWLNSalesServicesConstants.HOME_SECURITY.equals(productType) ) {
				
				populateHomeSecurityIncludedServiceConstraint( productComponent, prodSpecCharValueUseMap );
				
				//acquire from  
				CharacteristicVO acquiredFrom = getProductCharacteristics( prodSpecCharValueUseMap, FIFA_CHAR_ACQUIRED_FROM);
				//set selected value from shopping cart
				acquiredFrom.setValue( getValueByName( productTypeBaseVO.getCharacteristics(), "acquireFrom" ) );
				productComponent.addCharacteristic( acquiredFrom );
				
				//delivery method 
				CharacteristicVO deliveryMethod = getProductCharacteristics( prodSpecCharValueUseMap, FIFA_CHAR_DELIVERY_METHOD );
				translateDeliveryMethod( deliveryMethod );
				//set selected value from shopping cart
				deliveryMethod.setValue( getValueByName( productTypeBaseVO.getCharacteristics(), "deliveryMethod" ) );
				productComponent.addCharacteristic( deliveryMethod );
				
			} else {
				
				Map<String, String> packCategoryMap = null;
				if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType) ) {
					packCategoryMap = ttvPackCategoryMap ;
				}
				else if (EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(productType) ) {
					packCategoryMap = singCategoryMap ;
				}
				
				if ( packCategoryMap != null ) {
	
					for (String prodSpecName : packCategoryMap.keySet()) {
						TCharacteristic prodSpecCharValueUse = prodSpecCharValueUseMap.get(prodSpecName);
						String dispName = null;
	
						if (prodSpecCharValueUse != null && prodSpecCharValueUse.getValue() != null) {
							dispName = prodSpecCharValueUse.getDisplayName();
							IncludedServiceConstraint includedServiceConstraint = new IncludedServiceConstraint();
							SalesCategory salesCategory = new SalesCategory();
							salesCategory.setSalesCategoryCode(packCategoryMap.get(prodSpecName));
							salesCategory.setSalesCategoryNameList(populateName(dispName));
							includedServiceConstraint.setSalesCategory(salesCategory);
							productComponent.getIncludedServiceConstraintList().add(includedServiceConstraint);
							includedServiceConstraint.getServiceTypeCodeList()
									.add(packCategoryMap.get(prodSpecName) + "_ELIG");
							
							try {
								includedServiceConstraint.setMinItemQty(new BigInteger(prodSpecCharValueUse.getValue()));
								includedServiceConstraint.setMaxItemQty(includedServiceConstraint.getMinItemQty());
							}catch(Throwable th) {
								logger.error("", functionName, "error in setting min and max for service constraints", th);
							}
						}
	
					}
	
				}
			}
			ProductCatalogueIdentifier productCatalogueIdentifier2 = new ProductCatalogueIdentifier();
			productCatalogueIdentifier2.setProductCatalogueId(productOfferingId);
			wirelineOfferProduct.setMainComponentIdentifier(productCatalogueIdentifier2);
		}

	}

	private static void translateDeliveryMethod(CharacteristicVO deliveryMethod) {
		for ( CharacteristicOptionVO option: deliveryMethod.getOptions() ) {
			option.setValue( deliveryMethodMap.get(  option.getValue() ) );
		}
	}

	private static Map<String, TCharacteristic> buildProdSpecCharValueUse(List<TCharacteristic> prodSpecCharValueUse) {
		if (prodSpecCharValueUse == null || CollectionUtils.isEmpty(prodSpecCharValueUse))
			return null;

		Map<String, TCharacteristic> result = new HashMap<String, TCharacteristic>();

		for (TCharacteristic tChars : prodSpecCharValueUse) {
			result.put(tChars.getName(), tChars);
		}
		return result;
	}

	private static String getBasePrice(List<TPricing> productOfferingPrice) {
		String basePriceAmt = null;
		if (productOfferingPrice == null)
			return null;
		if (!CollectionUtils.isEmpty(productOfferingPrice)) {
			for (TPricing price : productOfferingPrice) {
				if ("Recurrent".equalsIgnoreCase(price.getPriceType()) && price.getPrice() != null
						&& price.getPrice().getDutyFreeAmount() != null) {
					basePriceAmt = price.getPrice().getDutyFreeAmount().getValue() + "";
				}
			}
		}
		return basePriceAmt;
	}

	private static TCharacteristic getSpecCharValueUse(List<TCharacteristic> prodSpecCharValueUseList,
			String nameValue) {
		if (prodSpecCharValueUseList == null)
			return null;

		for (TCharacteristic ele : prodSpecCharValueUseList) {
			if (nameValue.equalsIgnoreCase(ele.getName())) {
				return ele;
			}
		}
		return null;
	}

	public static void populateAddOnPlan(FIFAProductOfferingsVO productOfferingsVO,
			GetFIFAPoductOfferingAdapterResponse productOfferingQualificationResponse, List<String> includeProductOfferingsList) {
		
		ProductOfferingQualification productOfferingQualification = productOfferingQualificationResponse.getProductOfferingQualification();

		if (productOfferingsVO == null || productOfferingQualification == null
				|| CollectionUtils.isEmpty(productOfferingQualification.getProductOfferingQualificationItem()))
			return;
		
		Offer offer = productOfferingsVO.getOffer() == null ? new Offer() : productOfferingsVO.getOffer();
		productOfferingsVO.setOffer(offer);

		OfferProduct product = offer.getOfferProduct() == null ? new OfferProduct() : offer.getOfferProduct();
		offer.setOfferProduct(product);
		if (CollectionUtils.isEmpty(offer.getOfferProduct().getWirelineOfferProductList())) {
			offer.getOfferProduct().getWirelineOfferProductList().add(new WirelineOfferProduct());
		}
		WirelineOfferProduct wirelineOfferProduct = offer.getOfferProduct().getWirelineOfferProductList().get(0);
		if (wirelineOfferProduct.getAddOn() == null) {
			wirelineOfferProduct.setAddOn(new AddOn());
		}
		List<IncludedServiceConstraint> includedServiceConstraint = CollectionUtils
				.isEmpty(wirelineOfferProduct.getProductComponentList()) ? null
						: wirelineOfferProduct.getProductComponentList().get(0).getIncludedServiceConstraintList();

		String productTypeCode = wirelineOfferProduct.getProductTypeCode(); // SING/HSIC/TTV
		String productCategoryCode = wirelineOfferProduct.getProductCategoryCode();// OPTIK/PIK
		List<ProductOfferingQualificationItem> poqItemList = productOfferingQualification
				.getProductOfferingQualificationItem();
		for (ProductOfferingQualificationItem poqItem : poqItemList) {
			TProductOffering productOffering = poqItem.getProductOffering();
//			List<CategoryRef> prodOfferingCategoryList = productOffering.getCategory();
//			if (contains(prodOfferingCategoryList, featureFifaCategoryId)) {
//				continue;
//			}
			String productOfferingId = productOffering.getId();

			List<Description> displayName = populateName(productOffering.getName());
			List<Description> displayDescription = populateDescription(productOffering.getDescription());
			boolean isIncluded = includeProductOfferingsList.contains(productOfferingId);
			String basePriceAmt = getBasePrice(productOffering.getProductOfferingPrice());

			ProductCatalogueItemVO2 productCatalogueItem = null;
			List<String> serviceConstraintsCategories = new ArrayList<String>();
			for (IncludedServiceConstraint constraint : includedServiceConstraint) {
				serviceConstraintsCategories.add(constraint.getSalesCategory().getSalesCategoryCode());
			}
			if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productTypeCode)) {
				
				productCatalogueItem = getTtvAddon(productOfferingId, displayName, basePriceAmt,
						serviceConstraintsCategories, productOffering.getCategory(),displayDescription
						,productOfferingQualificationResponse.getCategoryId() );
			} else {
				productCatalogueItem = getAddOn(productOfferingId, displayName, basePriceAmt, serviceConstraintsCategories,
						productOffering.getCategory(),displayDescription
						,productOfferingQualificationResponse.getCategoryId() );
			}

			if (productCatalogueItem != null) {
				if (isIncluded) {
					wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList().add(productCatalogueItem);

				} else {
					wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList().add(productCatalogueItem);
				}
			}
		}

	}

	private static ProductCatalogueItemVO2 getTtvAddon(String productOfferingId, List<Description> displayName,
			String basePriceAmt, List<String> serviceConstraintsCategories, List<CategoryRef> categories, List<Description> displayDescription
			,String categroyId ) {

		ProductCatalogueItemVO2 pcItem = new ProductCatalogueItemVO2();
		String categoryIdsConcatString = convertToString(categories);
		ProductCatalogueIdentifier catIdentifier = new ProductCatalogueIdentifier();
		catIdentifier.setProductCatalogueId(productOfferingId);
		pcItem.setProductCatalogueIdentifier(catIdentifier);
		pcItem.getProductCatalogueNameList().add(displayName.get(0));

		//Fix for name/description
		pcItem.setProductCatalogueDescriptionList(displayDescription);
		
		pcItem.setParentProductCatalogueIdentifier(new ProductCatalogueIdentifier());
		pcItem.getParentProductCatalogueIdentifier()
				.setExternalProductCatalogId(convertToString(categories));
		
		//Response Grouping control: set 1 to force it to group[OTHER]], otherwise use categoryId
		pcItem.getParentProductCatalogueIdentifier().setProductCatalogueId("1"); 
		//pcItem.getParentProductCatalogueIdentifier().setProductCatalogueId(categroyId); 
		
		if(basePriceAmt != null) {
			pcItem.setProductCatalogueBasePriceAmt(new Double(basePriceAmt));
		}
		
		for (String serviceCategory : ttvPackCategoryMap.values()) {
			if (categoryIdsConcatString.indexOf(serviceCategory) > -1
					&& serviceConstraintsCategories.contains(serviceCategory)) {
				pcItem.setServiceTypeCode(serviceCategory + "_ELIG");
				pcItem.setPackEligibleItemInd(true);
			}

		}
		return pcItem;
	}



	public static void populateFeatures(FIFAProductOfferingsVO productOfferingsVO,
			GetFIFAPoductOfferingAdapterResponse productOfferingQualificationResponse, List<String> includeProductOfferingsList ) {
		
		ProductOfferingQualification productOfferingQualification = productOfferingQualificationResponse.getProductOfferingQualification();
		
		if (productOfferingsVO == null)
			return;

		Offer offer = productOfferingsVO.getOffer() == null ? new Offer() : productOfferingsVO.getOffer();
		productOfferingsVO.setOffer(offer);

		OfferProduct product = offer.getOfferProduct() == null ? new OfferProduct() : offer.getOfferProduct();
		offer.setOfferProduct(product);
		if (CollectionUtils.isEmpty(offer.getOfferProduct().getWirelineOfferProductList())) {
			offer.getOfferProduct().getWirelineOfferProductList().add(new WirelineOfferProduct());
		}
		WirelineOfferProduct wirelineOfferProduct = offer.getOfferProduct().getWirelineOfferProductList().get(0);
		if (wirelineOfferProduct.getFeature() == null) {
			wirelineOfferProduct.setFeature(new Feature());
		}
		List<IncludedServiceConstraint> includedServiceConstraint = CollectionUtils
				.isEmpty(wirelineOfferProduct.getProductComponentList()) ? null
						: wirelineOfferProduct.getProductComponentList().get(0).getIncludedServiceConstraintList();
		List<ProductOfferingQualificationItem> poqItemList = productOfferingQualification
				.getProductOfferingQualificationItem();
		for (ProductOfferingQualificationItem poqItem : poqItemList) {
			TProductOffering productOffering = poqItem.getProductOffering();
//			List<CategoryRef> prodOfferingCategoryList = productOffering.getCategory();
//			if (!contains(prodOfferingCategoryList, featureFifaCategoryId)) {
//				continue;
//			}
			String productOfferingId = productOffering.getId();
			boolean isIncluded = includeProductOfferingsList.contains(productOfferingId);

			List<Description> displayName = populateName(productOffering.getName());
			List<Description> displayDescription = populateName(productOffering.getDescription());
			String basePriceAmt = getBasePrice(productOffering.getProductOfferingPrice());

			List<String> serviceConstraintsCategories = new ArrayList<String>();
			for (IncludedServiceConstraint constraint : includedServiceConstraint) {
				serviceConstraintsCategories.add(constraint.getSalesCategory().getSalesCategoryCode());
			}
			
			ProductCatalogueItemVO2 productCatalogueItem = getAddOn(productOfferingId, displayName, basePriceAmt,
					serviceConstraintsCategories, productOffering.getCategory(),displayDescription
					,productOfferingQualificationResponse.getCategoryId() );

			if (isIncluded) {
				wirelineOfferProduct.getFeature().getIncludedProductCatalogueItemList().add(productCatalogueItem);

			} else {
				wirelineOfferProduct.getFeature().getOptionalProductCatalogueItemList().add(productCatalogueItem);
			}
		}

	}

	private static ProductCatalogueItemVO2 getAddOn(String productOfferingId, List<Description> displayName,
			String basePriceAmt, List<String> serviceConstraintsCategories,
			List<CategoryRef> categoryRefList, List<Description> displayDescription
			,String categoryId ) {
		if (StringUtils.isEmpty(productOfferingId))
			return null;

		ProductCatalogueItemVO2 productCatalogueItem = new ProductCatalogueItemVO2();
		String categoryIdsConcatString = convertToString(categoryRefList);
		
		productCatalogueItem.setProductCatalogueIdentifier(new ProductCatalogueIdentifier());
		productCatalogueItem.getProductCatalogueIdentifier().setProductCatalogueId(productOfferingId);
		productCatalogueItem.setParentProductCatalogueIdentifier(new ProductCatalogueIdentifier());
		productCatalogueItem.getParentProductCatalogueIdentifier().setExternalProductCatalogId( categoryIdsConcatString );
		
		//Response Grouping control: set 1 to force it to group[OTHER], otherwise use categoryId
		productCatalogueItem.getParentProductCatalogueIdentifier().setProductCatalogueId("1"); 
		//productCatalogueItem.getParentProductCatalogueIdentifier().setProductCatalogueId(categoryId); 
		
		productCatalogueItem.setProductCatalogueNameList(displayName);
		productCatalogueItem.setProductCatalogueDescriptionList(displayDescription);
		if (basePriceAmt != null) {
			productCatalogueItem.setProductCatalogueBasePriceAmt(new Double(basePriceAmt));
		}

		if ( serviceConstraintsCategories.size() > 0) { //SING
			
			for (String serviceCategory : singCategoryMap.values()) {
				if (categoryIdsConcatString.indexOf(serviceCategory) > -1
						&& serviceConstraintsCategories.contains(serviceCategory)) {
					productCatalogueItem.setServiceTypeCode(serviceCategory + "_ELIG");
					productCatalogueItem.setPackEligibleItemInd(true);
				}

			}
			
		}
		else { //HSIC
			String categoriesConcatinatedString = convertToString(categoryRefList);
			for(String serviceTypeCd : new String[] {"9153404018813388418","9153308192313298032","9153308192313298034"}) {
				if( categoriesConcatinatedString.indexOf(serviceTypeCd) > -1) {
					productCatalogueItem.setServiceTypeCode(serviceTypeCd);
				}
			}
			productCatalogueItem.setPackEligibleItemInd(false);
		}

		

		return productCatalogueItem;
	}

	private static String convertToString(List<CategoryRef> categoryRefList) {
		if (CollectionUtils.isEmpty(categoryRefList))
			return null;
		StringBuilder sb = new StringBuilder();
		for (CategoryRef cat : categoryRefList) {
			sb.append(cat.getId()).append(",");
		}
		return sb.toString();
	}

	private static List<Description> populateName(String name) {
		if (name == null)
			return null;

		Description desc = new Description();
		desc.setDescriptionText(name);
		desc.setLocale("EN");

		List<Description> result = new ArrayList<Description>();
		result.add(desc);

		return result;

	}
	
	private static List<Description> populateDescription(String description) {
		if (description == null)
			return null;

		Description desc = new Description();
		desc.setDescriptionText(description);
		desc.setLocale("EN");

		List<Description> result = new ArrayList<Description>();
		result.add(desc);

		return result;

	}

	public static void populateSelectedEquipment(FIFAProductOfferingsVO productOfferingsVO,
			List<FFHEquipmentTypeVO> selectedEqupments) {

		if (CollectionUtils.isEmpty(selectedEqupments))
			return;

		if (productOfferingsVO.getOffer() != null && productOfferingsVO.getOffer().getOfferProduct() != null
				&& productOfferingsVO.getOffer().getOfferProduct().getWirelineOfferProductList().get(0) != null) {

			for (FFHEquipmentTypeVO equip : selectedEqupments) {
				WirelineEquipment equipment = new WirelineEquipment();
				// equipment.setProductCatalogueItem(new ProductCatalogueItem());
				// equipment.getProductCatalogueItem().getParentProductCatalogueIdentifier();
				ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
				equipment.setProductCatalogueItem(productCatalogueItem);
				ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier();
				productCatalogueIdentifier
						.setProductCatalogueId(equip.getProductCatalogueIdentifier().getProductCatalogueId());
				productCatalogueItem.setProductCatalogueIdentifier(productCatalogueIdentifier);
				productCatalogueItem.setParentProductCatalogueIdentifier(productCatalogueIdentifier);

				equipment.setMaxQty(new BigInteger("1")); // TODO
				equipment.setMinQty(new BigInteger("1")); // TODO
				WirelineEquipmentItem wirelineEquipmentItem = new WirelineEquipmentItem();
				if (equip.getAcquisitionType().isRentalEquipmentIndicator() != null
						&& equip.getAcquisitionType().isRentalEquipmentIndicator()) {
					equipment.getRentalEquipmentList().add(wirelineEquipmentItem);
				} else if (equip.getAcquisitionType().isBuyIndicator() != null
						&& equip.getAcquisitionType().isBuyIndicator()) {
					equipment.getPurchaseEquipmentList().add(wirelineEquipmentItem);
				}

				wirelineEquipmentItem.setMaterialItemCode(equip.getMaterialItemCode());
				if (equip.getPrice() != null && equip.getPrice().getBasePriceAmount() != null
						&& equip.getPrice().getBasePriceAmount().getValueAmt() != null) {
					wirelineEquipmentItem.setEquipmentPurchasePriceAmt(
							new Double(equip.getPrice().getBasePriceAmount().getValueAmt()));
				}
				wirelineEquipmentItem.setEquipmentDescriptionList(populateDesc(equip.getDescription()));
				wirelineEquipmentItem.setIncludedInd(true);
				if (equip.getDeliveryMethodType() != null) {
					wirelineEquipmentItem
							.setDeliveryMethodList(Arrays.asList(new String[] { equip.getDeliveryMethodType() }));
				}

				productOfferingsVO.getOffer().getOfferProduct().getWirelineOfferProductList().get(0)
						.getWirelineEquipmentList().add(equipment);
			}
		}
	}

	private static List<Description> populateDesc(DescriptionVO descriptionVO) {
		List<Description> descList = null;
		if (descriptionVO != null && descriptionVO.getDescriptionText() != null) {
			descList = new ArrayList<Description>();
			Description desc = new Description();
			desc.setLocale("EN");
			desc.setDescriptionText(descriptionVO.getDescriptionText());
			descList.add(desc);
		}
		return descList;
	}


	private static AddOn getTtvAddon(TProductOffering productOffering) {
		AddOn addOn = new AddOn();
		List<ProductCatalogueItem> pcItemList = new ArrayList<ProductCatalogueItem>();
		ProductCatalogueItem pcItem = new ProductCatalogueItem();

		ProductCatalogueIdentifier catIdentifier = new ProductCatalogueIdentifier();
		catIdentifier.setProductCatalogueId(productOffering.getId());
		pcItem.setProductCatalogueIdentifier(catIdentifier);

		Description desc = new Description();
		desc.setDescriptionText(productOffering.getName());
		desc.setLocale(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
		pcItem.getProductCatalogueNameList().add(desc);

		pcItem.setProductCatalogueBasePriceAmt(new Double(getBasePrice(productOffering.getProductOfferingPrice())));

		// TODO: did not understood the mapping document regarding this field
		/*
		 * "FIFA OQ PACKS / ADDONS Call Response: productOffering->category
		 * 9136080250413413278 - 9136080250413413278_ELIG if
		 * includedServiceConstraintList->salesCategory has Theme Packs Category
		 * 9136201148713458177 - 9136201148713458177_ELIG if
		 * includedServiceConstraintList->salesCategory has Movies Category
		 * 9139557680013312078 - 9139557680013312078_ELIG if
		 * includedServiceConstraintList->salesCategory has Add-Ons Category (Channels)
		 * 9136201148713458176 - 9136201148713458176_ELIG if
		 * includedServiceConstraintList->salesCategory has Premium Packs Category
		 * 9153364436313016766 - 9153364436313016766_ELIG if
		 * includedServiceConstraintList->salesCategory has Premium Category"
		 */
		pcItem.setServiceTypeCode(null);

		pcItemList.add(pcItem);
		addOn.setOptionalProductCatalogueItemList(pcItemList);

		return addOn;
	}

	private static List<ProductComponent> addProductComponent(TProductOffering productOffering,
			String fifaProductCategoryId, String categoryType, String subCategoryType) {

		List<ProductComponent> productComponentList = new ArrayList<ProductComponent>();

		ProductComponent productComponent = new ProductComponent();

		List<ProductCatalogueItem> productCatItemList = new ArrayList<ProductCatalogueItem>();
		ProductCatalogueItem prodCatItem = new ProductCatalogueItem();
		ProductCatalogueIdentifier catIdentifier = new ProductCatalogueIdentifier();
		catIdentifier.setProductCatalogueId(productOffering.getId());

		// productCatalogueIdentifier

		prodCatItem.setProductCatalogueIdentifier(catIdentifier);

		List<Description> descList = new ArrayList<Description>();
		Description desc = new Description();
		desc.setDescriptionText(productOffering.getOriginalDisplayName());
		desc.setLocale(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
		descList.add(desc);

		// productCatalogueNameList
		prodCatItem.setProductCatalogueNameList(descList);

		productCatItemList.add(prodCatItem);
		productComponent.setProductCatalogueItemList(productCatItemList);

		// marketingDescriptionList
		List<Description> markDescList = new ArrayList<Description>();
		Description markDesc = new Description();
		markDesc.setDescriptionText(productOffering.getDescription());
		markDesc.setLocale(EnterpriseWLNSalesServicesConstants.LOCALE_EN_CA);
		markDescList.add(markDesc);
		productComponent.setMarketingDescriptionList(markDescList);

		// TODO: the productComponentList.productCatalogueItemList.productCatalogueBase
		// Price

		if (CollectionUtils.isNotEmpty(productOffering.getProductOfferingPrice())) {
			for (TPricing pricing : productOffering.getProductOfferingPrice()) {
				if ("Recurrent".equalsIgnoreCase(pricing.getPriceType())) {
					productComponent.setMarketingProductPriceAmt(
							pricing.getPrice().getDutyFreeAmount().getValue().doubleValue());
				}
			}
		}

		// IncludedServiceConstraints
		productComponent.getIncludedServiceConstraintList()
				.add(addIncludedConstraint(productOffering, fifaProductCategoryId, "9136528836413413259"));
		productComponent.getIncludedServiceConstraintList()
				.add(addIncludedConstraint(productOffering, fifaProductCategoryId, "9142418206813327614"));
		productComponent.getIncludedServiceConstraintList()
				.add(addIncludedConstraint(productOffering, fifaProductCategoryId, "9146943588313806263"));
		productComponent.getIncludedServiceConstraintList()
				.add(addIncludedConstraint(productOffering, fifaProductCategoryId, "9153357751313013740"));

		productComponentList.add(productComponent);

		return productComponentList;
	}

	private static IncludedServiceConstraint addIncludedConstraint(TProductOffering productOffering,
			String fifaProductCategoryId, String id) {
		IncludedServiceConstraint includedServiceConstraint = new IncludedServiceConstraint();

		TCharacteristic charSpec = getSpecCharValueUse(productOffering.getProdSpecCharValueUse(), id);
		if (charSpec != null) {

			SalesCategory salesCategory = new SalesCategory();
			includedServiceConstraint.setSalesCategory(salesCategory);
			salesCategory.setSalesCategoryCode(fifaProductCategoryId);
			Description catName = new Description();
			catName.setDescriptionText(charSpec.getName());
			catName.setLocale("EN");
			salesCategory.getSalesCategoryNameList().add(catName);
			includedServiceConstraint.getServiceTypeCodeList().add(salesCategory.getSalesCategoryCode() + "_ELIG");
			includedServiceConstraint.setMinItemQty(new BigInteger(charSpec.getValue()));
			includedServiceConstraint.setMaxItemQty(new BigInteger(charSpec.getValue()));
		} else {
			return null;
		}

		return includedServiceConstraint;
	}

	private static List<TransactionType> addTransactionType(String tranType) {
		List<TransactionType> list = new ArrayList<TransactionType>();
		TransactionType t = new TransactionType();
		t.setTransactionTypeCode(tranType);
		list.add(t);
		return list;
	}


	//This is not tested as of July 26
	public static void populateEquipments(FIFAProductOfferingsVO productOfferingsVO, 
			GetFIFAPoductOfferingAdapterResponse productOfferingQualificationResponse,
			List<String> includeProductOfferingsList,
			List<FFHEquipmentTypeVO> selectedEqupments) {
		
		ProductOfferingQualification productOfferingQualification = productOfferingQualificationResponse.getProductOfferingQualification();

		Map<String, List<FFHEquipmentTypeVO>> selectEquipmentMap = convertToMap( selectedEqupments );
	
		List<ProductOfferingQualificationItem> poqItemList = productOfferingQualification.getProductOfferingQualificationItem();	
		
		for (ProductOfferingQualificationItem poqItem : poqItemList) {
			
			TProductOffering productOffering = poqItem.getProductOffering();
			
			if ( isEquipmentOffer ( productOffering ) ) {
			
				WirelineEquipment equipment = transformToEqupment(productOfferingQualificationResponse.getCategoryId(),
						0,
						productOffering, selectEquipmentMap);
	
				productOfferingsVO.getOffer().getOfferProduct().getWirelineOfferProductList().get(0).getWirelineEquipmentList().add(equipment);
			}
		}
	}

	private static WirelineEquipment transformToEqupment(String parentCatalogueId,	int contractTerm, TProductOffering productOffering, Map<String, List<FFHEquipmentTypeVO>> selectEquipmentMap) {
		
		WirelineEquipment equipment = new WirelineEquipment();
		ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();

		productCatalogueItem.setProductCatalogueIdentifier( new ProductCatalogueIdentifier() );
		productCatalogueItem.getProductCatalogueIdentifier().setProductCatalogueId( productOffering.getId() );

		productCatalogueItem.setParentProductCatalogueIdentifier( new ProductCatalogueIdentifier());
		productCatalogueItem.getParentProductCatalogueIdentifier().setProductCatalogueId( parentCatalogueId );

		equipment.setProductCatalogueItem(productCatalogueItem);
		
		TProductOfferingBundledProdOfferOption bundledProdOfferingOption = productOffering.getBundledProdOfferOption();
		if ( bundledProdOfferingOption==null ) {

			equipment.setMaxQty(new BigInteger("1")); // default
			equipment.setMinQty(new BigInteger("1")); // default
		} else {
			equipment.setMaxQty( BigInteger.valueOf(bundledProdOfferingOption.getNumberRelOfferUpperLimit().longValue() ) ); 
			equipment.setMinQty( BigInteger.valueOf(bundledProdOfferingOption.getNumberRelOfferLowerLimit().longValue() ) );
		}
		
		WirelineEquipmentItem equipmentItem = new WirelineEquipmentItem();
		
		//populates from OQ response
		EquipmentCharacteristics equipCharac = new EquipmentCharacteristics( productOffering.getProdSpecCharValueUse() );
		
		if  (Boolean.TRUE.equals( equipCharac.isRental() ) ) {
			
			equipment.getRentalEquipmentList().add(equipmentItem);
			equipmentItem.setEquipmentRentalPriceAmt( getRecurrentPriceAmount(productOffering) );
			
		} else if ( Boolean.TRUE.equals( equipCharac.isBuy() ) ) {
			
			equipment.getPurchaseEquipmentList().add(equipmentItem);
			
			PaymentOption payOption = equipCharac.getPaymentOption();
			if ( payOption==null) {
				
				equipmentItem.setEquipmentPurchasePriceAmt(	getOneTimeCharge( productOffering ) );
				
			} else {
				//easyPay
				payOption.setPaymentInstallmentAmt( getRecurrentPriceAmount(productOffering) );
				payOption.setPaymentInstallmentQty( BigInteger.valueOf( contractTerm ) ); 
				equipmentItem.setPaymentOptionList( Arrays.asList(payOption) );
			}
		} else {
			logger.warn("transformToEqupment", "equipment [" + productOffering.getName() + "] offering.id=" + productOffering.getId() + " has NO acquisition type.");
		}
		equipmentItem.setMaterialItemCode( equipCharac.getMaterialCode() );
		equipmentItem.setEquipmentDescriptionList( populateDescription(productOffering.getName()) );
		equipmentItem.setIncludedInd( equipCharac.isIncluded() );
		equipmentItem.setDeliveryMethodList( equipCharac.getDeliveryMethodList() );
		
		//the shoppingCart equipments with same offerId 
		List<FFHEquipmentTypeVO> cartEquipmentList = selectEquipmentMap.get(productOffering.getId());
		if ( CollectionUtils.isNotEmpty(cartEquipmentList) ) {
			
			logger.debug("transformToEqupment", "found matching equipment in shopping cart, id= "+ productOffering.getId() + ", name=" + productOffering.getName() + "; count=" + cartEquipmentList.size() );

			//this is the first equipment element with same offerId
			equipmentItem.setIncludedInd(true);
			populateCharracteristics( equipmentItem, cartEquipmentList.get(0) ) ;
			
			if (cartEquipmentList.size()>1 ) {
				
				//if have more than one same equipment, then replicate the equipment item and put into buy or rental list  
				for( int i=1; i<cartEquipmentList.size(); i++) {
					
					WirelineEquipmentItem itemClone = cloneEquipmentItem( equipmentItem );
					populateCharracteristics( equipmentItem, cartEquipmentList.get(i) );

					if (Boolean.TRUE.equals( equipCharac.isRental()) ) {
						
						equipment.getRentalEquipmentList().add(itemClone);
						
					} else if ( Boolean.TRUE.equals( equipCharac.isBuy() ) ) {
					
						equipment.getPurchaseEquipmentList().add(itemClone);
					}
				}
			}
		}
		
		//logger.debug("transformToEqupment", JsonUtil.getJsonFromObjectNonEmpty(equipment) );

		return equipment;
	}
	
	
	private static void populateCharracteristics( WirelineEquipmentItem equipmentItem, FFHEquipmentTypeVO ffhEquipmentTypeVO ) {
		
		if (CollectionUtils.isNotEmpty( ffhEquipmentTypeVO.getCharacteristics() ) ) {
			
			Object isYourPick = getByName( ffhEquipmentTypeVO.getCharacteristics(), FIFA_CHAR_YOUR_PICK );
			if ( isYourPick !=null ) {
				CharacteristicVO characteristicVO= new CharacteristicVO();
				
				characteristicVO.setName(FIFA_CHAR_YOUR_PICK);
				characteristicVO.setDisplayName("isYouPick");
				characteristicVO.setValue( getValue ( isYourPick ) );
				
				equipmentItem.addCharacteristic(characteristicVO);
			}
		}
	}

	private static WirelineEquipmentItem cloneEquipmentItem( WirelineEquipmentItem sourceItem ) {
		WirelineEquipmentItem target = new WirelineEquipmentItem();
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(target, sourceItem);
		} catch (Exception e) {
			throw new RuntimeException ( e );
		}
		return target;
	}
	

	private static Double getOneTimeCharge(TProductOffering productOffering) {
		Double basePriceAmt = null;
		if (CollectionUtils.isNotEmpty( productOffering.getProductOfferingPrice())) {
			for (TPricing price : productOffering.getProductOfferingPrice()) {
				if ("One_Time".equalsIgnoreCase(price.getPriceType()) && price.getPrice() != null
						&& price.getPrice().getDutyFreeAmount() != null) {
					String value = price.getPrice().getDutyFreeAmount().getValue() + "";
					if (StringUtils.isNoneBlank(value)) {
						basePriceAmt = new Double( value );
					}
				}
			}
		}
		return basePriceAmt;
	}
	
	private static Double getRecurrentPriceAmount(TProductOffering productOffering) {
		
		Double basePrice = null;
		String basePriceStr = getBasePrice(productOffering.getProductOfferingPrice());
		
		if (basePriceStr!=null) {
			basePrice = new Double(basePriceStr);
		}
		return basePrice;
	}
	
	private static boolean isEquipmentOffer(TProductOffering productOffering) {
		
		boolean result = false;
		
		for( CategoryRef categoryRef: productOffering.getCategory()) {
			if (FFIA_CAT_ID_EQUIPEMNT.equals( categoryRef.getId()) ) {
				result = true;
			}
		}
		return result;
	}

	private static boolean isHomeSecurityEquipmentOffer(TProductOffering productOffering) {
		
		boolean result = false;
		
		for( CategoryRef categoryRef: productOffering.getCategory()) {
			if (FFIA_CAT_ID_HOME_SECURITY_EQUIPEMNT.equals( categoryRef.getId()) ) {
				result = true;
			}
		}
		return result;
	}
	
	private static IncludedServiceConstraint findMatchingServiceConstraint( TProductOffering productOffering, List<IncludedServiceConstraint> includedServiceConstraints) {
		
		IncludedServiceConstraint  result = null;
		if (includedServiceConstraints!=null ) {
			for( CategoryRef categoryRef: productOffering.getCategory()) {
				for( IncludedServiceConstraint constraint: includedServiceConstraints ) {
					if ( constraint.getSalesCategory().getSalesCategoryCode().equals( categoryRef.getId() ) ) {
						result = constraint;
						break;
					}
				}
			}
		}
		return result;
	}	
	
	public static void populateHomeSecurityChildOfferings(FIFAProductOfferingsVO productOfferingsVO,  GetFIFAPoductOfferingAdapterResponse productOfferingQualificationResponse,
			List<String> includeProductOfferingsList, List<FFHEquipmentTypeVO> selectedEqupments, int contractTerm ) {

		String functionName = "populateChildOfferings";
		
		List<ProductOfferingQualificationItem> poqItemList = productOfferingQualificationResponse.getProductOfferingQualification().getProductOfferingQualificationItem();	
		
		Map<String, List<FFHEquipmentTypeVO>> selectEquipmentMap = convertToMap(selectedEqupments);
		
		int i=0 ;
		
		List<WirelineEquipment> wirelineEquipmentList = productOfferingsVO.getOffer().getOfferProduct().getWirelineOfferProductList().get(0).getWirelineEquipmentList();

		for (ProductOfferingQualificationItem poqItem : poqItemList) {
			
			TProductOffering productOffering = poqItem.getProductOffering();
			
			//add every child offer to the addon
			populateHomeSecurityAddOn( productOfferingsVO, productOffering, includeProductOfferingsList, selectEquipmentMap.keySet() );
			
			if ( isHomeSecurityEquipmentOffer ( productOffering ) ) {
				
				if ( CollectionUtils.isNotEmpty( productOffering.getProductOfferingPrice() ) ) {

					logger.debug(functionName, "(" + i + ")  transform equipment [" + productOffering.getName() + "] offering.id=" + productOffering.getId() );
					
					WirelineEquipment equipment = transformToEqupment( productOfferingQualificationResponse.getCategoryId(), contractTerm, productOffering, selectEquipmentMap );
		
					wirelineEquipmentList.add(equipment);
					
				} else {

					//offer without price is dummy parent offer , used to group two or more offer together for same equipment
					logger.info(functionName, "(" + i + ")  suppress no pricing SHS equipment [" + productOffering.getName() + "] offering.id=" + productOffering.getId() );
				}
			} else {
				logger.info(functionName, "(" + i + ")  NOT SHS equipment [" + productOffering.getName() + "] offering.id=" + productOffering.getId() );
			}
			
			i++;
		}
		
		logger.debug( functionName, "total number of child offer=" + i + "; qualified SHS equipment offers=" + wirelineEquipmentList.size() ) ;
	}

	private static Map<String, List<FFHEquipmentTypeVO>> convertToMap(List<FFHEquipmentTypeVO> selectedEqupments) {
		
		Map<String, List<FFHEquipmentTypeVO>> selectEquipmentMap = new HashMap<String, List<FFHEquipmentTypeVO>> ();
		
		if (CollectionUtils.isNotEmpty(selectedEqupments) ) {
			
			for( FFHEquipmentTypeVO ffhEquipment : selectedEqupments ) {
				
				String key = ffhEquipment.getProductCatalogueIdentifier().getProductCatalogueId();
				List<FFHEquipmentTypeVO> list = selectEquipmentMap.get(key);
				if ( list==null) {
					
					list = new ArrayList<FFHEquipmentTypeVO> ();
					selectEquipmentMap.put( key, list );
					
				} 
				list.add( ffhEquipment );
			}
		}
		return selectEquipmentMap;
	}
	
	public static class EquipmentCharacteristics {
		
		private List<TCharacteristic> characteristics ;
		private String acquisitionType;
		
		public EquipmentCharacteristics( List<TCharacteristic> characList ) {
			characteristics = characList;
			acquisitionType = getValueByName ( characteristics, FIFA_EQUIPMENT_OFFER_CHARAC_ACQUISISION_TYPE);
		}
		
		public boolean isBuy() {
			return FIFA_EQUIPMENT_OFFER_ACQUISISION_TYPE_PURCHASED.equals(acquisitionType);
		}

		public boolean isRental() {
			return FIFA_EQUIPMENT_OFFER_ACQUISISION_TYPE_RENTED.equals(acquisitionType);
		}

		String getMaterialCode( ) {
			return getValueByName ( characteristics , FIFA_EQUIPMENT_OFFER_CHARAC_MIC_CODE ); 
		}
		
		boolean isIncluded () {
			
			return FIFA_EQUIPMENT_OFFER_AUTO_ADD_TRUE.equalsIgnoreCase( getValueByName ( characteristics , FIFA_EQUIPMENT_OFFER_CHARAC_AUTO_ADD_IND ) );
		}
		
		List<String> getDeliveryMethodList() {
			List<String> methodList = new ArrayList<String> ();
			if (characteristics!=null ) {
				for( TCharacteristic charac : characteristics ) {
					if ( FIFA_EQUIPMENT_OFFER_CHARAC_DELIVERY_MOD.equalsIgnoreCase( charac.getName() ) ) {
						for(  TCharacteristicOption option: charac.getValueOptions() ) {
							methodList.add( option.getValue() );
						}
					}
				}
			}
			return methodList;
		}
		PaymentOption getPaymentOption( ) {
			PaymentOption result = null;
			if ( FIFA_EQUIPMENT_OFFER_PURCHASE_TYPE_EASYPAY.equals( getValueByName ( characteristics , FIFA_EQUIPMENT_OFFER_CHARAC_PURCHASE_TYPE ) ) ) { 
				result = new PaymentOption();
				result.setPaymentOptionTypeCd( PAYMENT_OPTION_TYPE_CD_FINANCE );
			}
			return result;
		}
		
	}
	
	private static AssociatedOfferings[]  parseAssociatedOfferings( String value ) {
		return parseJson( value, AssociatedOfferings[].class);
	}
	
	private static <T> T parseJson( String value, Class<T> klass ) {
		
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T response = null;
		try {
			response = mapper.readValue(value, klass);
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static class AssociatedOfferings {
		
		@JsonProperty (value = "CategoryID")
		private String categoryId;
		
		@JsonProperty (value = "Max")
		private String max;

		public String getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public String getMax() {
			return max;
		}

		public void setMax(String max) {
			this.max = max;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("AssociatedOfferings [categoryId=").append(categoryId).append(", max=").append(max).append("]");
			return builder.toString();
		}
		
	}

	private static void populateHomeSecurityIncludedServiceConstraint( ProductComponent productComponent,	Map<String, TCharacteristic> prodSpecCharValueUseMap) {
		
		TCharacteristic associatedOffersChar = prodSpecCharValueUseMap.get( FIFA_CHAR_ASSOCIATED_OFFER );
		
		if (associatedOffersChar != null && associatedOffersChar.getValue() != null) {

			AssociatedOfferings[] constraints = parseAssociatedOfferings(associatedOffersChar.getValue());
			
			logger.enter( "transformAssociatedOffers", "associatedOfferings: " + Arrays.deepToString(constraints) );
			
			if ( constraints!=null ) {
				
				for( AssociatedOfferings associatedOfferings: constraints  ) {
				
					SalesCategory salesCategory = new SalesCategory();
					salesCategory.setSalesCategoryCode( associatedOfferings.getCategoryId());
					salesCategory.setSalesCategoryNameList( populateName( associatedOffersChar.getDisplayName()) );

					IncludedServiceConstraint includedServiceConstraint = new IncludedServiceConstraint();
					includedServiceConstraint.setSalesCategory(salesCategory);
					includedServiceConstraint.setMaxItemQty( new BigInteger (associatedOfferings.getMax() ) );
					includedServiceConstraint.setMinItemQty(includedServiceConstraint.getMaxItemQty());
					
					includedServiceConstraint.getServiceTypeCodeList().add( salesCategory.getSalesCategoryCode() + "_ELIG");
				
					productComponent.getIncludedServiceConstraintList().add(includedServiceConstraint);
				}
			}
		}
	}

	private static CharacteristicVO getProductCharacteristics( Map<String, TCharacteristic> prodSpecCharValueUseMap, String characteristicId) {
		
		CharacteristicVO target = null;

		TCharacteristic source = prodSpecCharValueUseMap.get( characteristicId );
		if (source!=null) {
			
			target = new CharacteristicVO();
			target.setName( source.getName() );
			target.setDisplayName( source.getDisplayName());
			
			List<CharacteristicOptionVO> options = new ArrayList<CharacteristicOptionVO>();
			for (  TCharacteristicOption srcOpt: source.getValueOptions() ) {
				CharacteristicOptionVO targetOpt = new CharacteristicOptionVO();
				
				targetOpt.setName(srcOpt.getName() );
				targetOpt.setValue(srcOpt.getValue());
				targetOpt.setDefault( Boolean.TRUE.equals( srcOpt.getIsDefault() ) );
				options.add(targetOpt);
			}
			target.setOptions( options);
		}
		
		logger.debug( "transformAcquireFromOptions", "" + target );
		
		return target;
	}

	/**
	 * @see ProductItemConstraintGroupDelegate.getProductItemConstraintGroupList
	 * 
	 */
	public static void populateHomeSecurityAddOn( FIFAProductOfferingsVO productOfferingsVO, TProductOffering productOffering, List<String> includeProductOfferingsList
			,Set<String> cartEquipmentIdList ) {
		
		Offer offer = productOfferingsVO.getOffer() ;
		if ( offer== null ) {
			offer = new Offer() ;
			productOfferingsVO.setOffer(offer);
		}

		OfferProduct product = offer.getOfferProduct();
		if (product==null) {
			product=new OfferProduct();
			offer.setOfferProduct(product);
		}
		
		if ( CollectionUtils.isEmpty(offer.getOfferProduct().getWirelineOfferProductList()) ) {
			offer.getOfferProduct().getWirelineOfferProductList().add(new WirelineOfferProduct());
		}
		
		WirelineOfferProduct wirelineOfferProduct = offer.getOfferProduct().getWirelineOfferProductList().get(0);
		if (wirelineOfferProduct.getAddOn() == null) {
			wirelineOfferProduct.setAddOn( new AddOn() );
		}
		
		List<IncludedServiceConstraint> includedServiceConstraint = CollectionUtils.isEmpty(wirelineOfferProduct.getProductComponentList()) ? null
						: wirelineOfferProduct.getProductComponentList().get(0).getIncludedServiceConstraintList();

		String productOfferingId = productOffering.getId();

		List<Description> displayName = populateName(productOffering.getName());
		List<Description> displayDescription = populateDescription(productOffering.getDescription());
		
		String basePriceAmt = getBasePrice(productOffering.getProductOfferingPrice());

		ProductCatalogueItemVO2 productCatalogueItem = newAddOn( productOfferingId, displayName, basePriceAmt, productOffering.getCategory(),displayDescription);
		
		Map<String, TCharacteristic> prodSpecCharValueUseMap = buildProdSpecCharValueUse(productOffering.getProdSpecCharValueUse());

		CharacteristicVO yourPick = getProductCharacteristics( prodSpecCharValueUseMap, FIFA_CHAR_YOUR_PICK );
		productCatalogueItem.addCharacteristic( yourPick );


			
		//check if this offer's categoryId belong to any includedServiceConstraint
		IncludedServiceConstraint serviceConstraint= findMatchingServiceConstraint( productOffering, includedServiceConstraint );
		if ( serviceConstraint!=null ) {
			//if so, populate these following properties, so that later on, this productCatalgueItem can be picked up by 
			//ProductItemConstraintGroupDelegate.getProductItemConstraintGroupList and added into 
			//GAPIResponse.smartHomeSecurityProductItems[0].productItemConstraintGroupList[0].productItemConstraintList[n].productItemList
			String serviceTypeCode = serviceConstraint.getServiceTypeCodeList().get(0);
			productCatalogueItem.setServiceTypeCode( serviceTypeCode );
			productCatalogueItem.setPackEligibleItemInd(true);
			
		}

		//because all child offers( addon and equipment) are to addon list, so we need check if the offer is in shopping cart's addon list or equipment list
		//if so added it as included product list
		if ( includeProductOfferingsList.contains(productOfferingId) 
			|| cartEquipmentIdList.contains( productOfferingId)	) {  
			logger.debug("populateAddOn",  "found match offer in shopping cart:  id" + productOffering.getId()+ ", name=" + productOffering.getName());
			wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList().add(productCatalogueItem);

		} else {
			wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList().add(productCatalogueItem);
		}

	}
	
	private static ProductCatalogueItemVO2 newAddOn( String productOfferingId, List<Description> displayName,
			String basePriceAmt, List<CategoryRef> categoryRefList, List<Description> displayDescription
			) {
		
		if (StringUtils.isEmpty(productOfferingId))
			return null;

		ProductCatalogueItemVO2 productCatalogueItem = new ProductCatalogueItemVO2();
		String categoryIdsConcatString = convertToString(categoryRefList);
		
		productCatalogueItem.setProductCatalogueIdentifier(new ProductCatalogueIdentifier());
		productCatalogueItem.getProductCatalogueIdentifier().setProductCatalogueId(productOfferingId);
		productCatalogueItem.setParentProductCatalogueIdentifier(new ProductCatalogueIdentifier());
		productCatalogueItem.getParentProductCatalogueIdentifier().setExternalProductCatalogId( categoryIdsConcatString );
		
		//Response Grouping control: set 1 to force it to group[OTHER], otherwise use categoryId
		productCatalogueItem.getParentProductCatalogueIdentifier().setProductCatalogueId("1"); 
		
		productCatalogueItem.setProductCatalogueNameList(displayName);
		productCatalogueItem.setProductCatalogueDescriptionList(displayDescription);
		if (basePriceAmt != null) {
			productCatalogueItem.setProductCatalogueBasePriceAmt(new Double(basePriceAmt));
		}

		return productCatalogueItem;
	}
	
}
