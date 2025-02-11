package com.telus.csm.ewlnsis.core.utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSalesOfferDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListResponseType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EnterpriseSalesOfferDetail;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.QuantityAllowed;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesOfferProduct;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesWirelineEquipment;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesWirelineEquipmentItem;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProduct;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.AddOn;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Category;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Feature;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferCategory;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
//import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListResponseType;
//import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesProductComponent;


/****************************************************************************/
/*  Build MOCK response object =>  GetSalesOfferDetailResponseType          */
/****************************************************************************/
public class GetSalesOfferDetailMockBuilder {
  
	
	/************************/
	/* buildDate            */
	/************************/
	private static Date buildDate(String dateStr){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		try{
			Date date = formatter.parse(dateStr);
			return date;
		} catch (Exception e){
			return null;
		}
	}
	
	/*********************************/
	/* buildDescription  2 languages */
	/*********************************/
	private static List<Description> buildDescription(String englishLocale, String englishDesc, String frenchLocale, String frenchDesc){
		List<Description> programDescriptionList = new ArrayList<Description>();
		
		programDescriptionList.addAll(buildDescription(englishLocale, englishDesc));
		programDescriptionList.addAll(buildDescription(frenchLocale, frenchDesc)); 
		return programDescriptionList;
	}
	
	/*********************************/
	/* buildDescription              */
	/*********************************/
	private static List<Description> buildDescription(String locale, String description){
		List<Description> programDescriptionList = new ArrayList<Description>();
		
//		Description enDescription = new Description();
//		enDescription.setLocale(locale);
//		enDescription.setDescriptionText(description);
		
		Description enDescription = buildDescriptionLocale(locale, description);
		programDescriptionList.add(enDescription); 
		
		return programDescriptionList;
	}
	
	private static Description buildDescriptionLocale(String locale, String description){
		
		Description enDescription = new Description();
		enDescription.setLocale(locale);
		enDescription.setDescriptionText(description);
		return enDescription;
		
	}
	
	/*********************************/
	/* buildTransactionTypeList      */
	/*********************************/
	private static List<TransactionType> buildTransactionTypeList(String code){
		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeCode(code);
		return Arrays.asList(transactionType);
	}
	
	/****************************************/
	/* buildProductCatalogueIdentifier      */
	/****************************************/
	private static ProductCatalogueIdentifier buildProductCatalogueIdentifier(String externaId, String catalogueId){
		ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier();
		
		productCatalogueIdentifier.setExternalProductCatalogId(externaId);
		productCatalogueIdentifier.setProductCatalogueId(catalogueId);
		
		return productCatalogueIdentifier;
	}
	
	/****************************************/
	/* buildProductCategueItemList          */
	/****************************************/
	private static List<ProductCatalogueItem> buildProductCategueItemList(String productCatId , String externalId, String description, 
			                                                              String parentProductCatId , String parentExternalId)
	{
		return Arrays.asList(buildProductCatalogueItem(productCatId, externalId, description, parentProductCatId, parentExternalId));
	}
	
	/****************************************/
	/* buildProductCategueItem              */
	/****************************************/
	private static ProductCatalogueItem buildProductCatalogueItem(String productCatId, String externalId, String description, 
			                                                      String parentProductCatId, String parentExternalId)
	{
		ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem(); 
		
		productCatalogueItem.setProductCatalogueIdentifier(buildProductCatalogueIdentifier(externalId, productCatId));
		productCatalogueItem.setProductCatalogueNameList(buildDescription("EN", description));
		productCatalogueItem.setParentProductCatalogueIdentifier(buildProductCatalogueIdentifier(parentExternalId, parentProductCatId));
		
		
		return productCatalogueItem;
	}
	
	
	
	/****************************************/
	/* buildRentalEquipmentList             */
	/****************************************/
	private static List<SalesWirelineEquipmentItem> buildEquipmentList(String matierlItemCode,
	                                                                   String description,
	                                                                   String deliveryMethod,
	                                                                   double purchaseAmt,
	                                                                   double rentalAmt,
	                                                                   double returnAmt,
	                                                                   boolean includedInd){
		
		SalesWirelineEquipmentItem salesWirelineEquipmentItem = new SalesWirelineEquipmentItem();
		
		salesWirelineEquipmentItem.setMaterialItemCode(matierlItemCode);
		salesWirelineEquipmentItem.setEquipmentDescriptionList(buildDescription("EN", description));
		salesWirelineEquipmentItem.setDeliveryMethodList(Arrays.asList(deliveryMethod));
		salesWirelineEquipmentItem.setIncludedInd(includedInd);
		
		if (rentalAmt > 0){
			salesWirelineEquipmentItem.setEquipmentRentalPriceAmt(rentalAmt);
		}
		
		if (purchaseAmt > 0){
			salesWirelineEquipmentItem.setEquipmentPurchasePriceAmt(purchaseAmt);
		}
		if (returnAmt > 0){
			salesWirelineEquipmentItem.setEquipmentLateReturnPriceAmt(returnAmt);
		}
		
		return Arrays.asList(salesWirelineEquipmentItem);
	}
	
	/**************************************************************   H S I C   *************************************************************************/
	
	/****************************************/
	/* buildWirelineEquipmentList           */
	/****************************************/
	private static List<SalesWirelineEquipment> buildWirelineEquipmentList_HSIC(){
		
		SalesWirelineEquipment salesWirelineEquipment = new SalesWirelineEquipment();
		
		ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
		productCatalogueItem.setProductCatalogueIdentifier(buildProductCatalogueIdentifier("34", "101104"));
		productCatalogueItem.setParentProductCatalogueIdentifier(buildProductCatalogueIdentifier("1", "101102"));
		productCatalogueItem.setProductCatalogueNameList(buildDescription("EN", "Internet Equipment"));
		
		
		
		salesWirelineEquipment.setProductCatalogueItem(productCatalogueItem);
		salesWirelineEquipment.setMinQty(new BigInteger("1"));
		salesWirelineEquipment.setMaxQty(new BigInteger("1"));
		salesWirelineEquipment.setRentalEquipmentList(buildEquipmentList("2368850",
		                                                                 "Advanced Wi-Fi Modem",
		                                                                 "INSTALLER",
		                                                                 -1.0,
		                                                                 150.0,
		                                                                 150.0,
		                                                                 false));
		
		return Arrays.asList(salesWirelineEquipment);
	}
	
	
	/****************************************/
	/* buildWirelineOfferProduct            */
	/****************************************/
	private static WirelineOfferProduct buildWirelineOfferProduct_HSIC(){
		
		WirelineOfferProduct product = new WirelineOfferProduct();
		
		product.setProductTypeCode("HSIC");
		product.setTransactionTypeList(buildTransactionTypeList("upgrade"));
		product.setContractTermList(Arrays.asList(new BigInteger("0")));
		product.setProductTemplateIdentifier(buildProductCatalogueIdentifier("21175459", "1219000"));
		
		// Build productComponent
		ProductComponent productComponent = new ProductComponent(); 
		productComponent.setProductCatalogueItemList(buildProductCategueItemList("700491",
				                                                                 "64945", 
				                                                                 "high speed Zero",
				                                                                 "101082",
				                                                                 "25"));
		product.setProductComponentList(Arrays.asList(productComponent));
		
		
		product.setProductCatalogSystemId("10289");
		product.setMainComponentIdentifier(buildProductCatalogueIdentifier("22", "101097"));
		
		
		product.setWirelineEquipmentList(buildWirelineEquipmentList_HSIC()); 
		
		return product;
	}
	
	
	
	/**************************************************************   S I N G   *************************************************************************/
	
	/****************************************/
	/* buildWirelineOfferProduct_SING       */
	/****************************************/
	private static WirelineOfferProduct buildWirelineOfferProduct_SING(){
		WirelineOfferProduct product = new WirelineOfferProduct();
		
		product.setProductTypeCode("SING");
		product.setTransactionTypeList(buildTransactionTypeList("upgrade"));
		product.setContractTermList(Arrays.asList(new BigInteger("0")));
		product.setProductTemplateIdentifier(buildProductCatalogueIdentifier("83", "101412"));
		
		// Build productComponent
		ProductComponent productComponent = new ProductComponent();
		productComponent.setMarketingDescriptionList(buildMarketingDescriptionList_SING());
		productComponent.setProductCatalogueItemList(buildProductCategueItemList("977001",
				                                                                 "20969178", 
				                                                                 "TELUS Home Phone (Non-Forborne)",
				                                                                 "101402",
				                                                                 "44"));
		product.setProductComponentList(Arrays.asList(productComponent));
		
		
		product.setProductCatalogSystemId("10289");
		product.setMainComponentIdentifier(buildProductCatalogueIdentifier("44", "101402"));
		
		product.setAddOn(buildAddOn_SING());
		product.setDiscountList(buildDiscountList_SING());
		product.setFeature(buildFeature_SING());
		
		return product;
	}
	
	/****************************************/
	/* buildMarketingDescriptionList_SING   */
	/****************************************/
	private static List<Description> buildMarketingDescriptionList_SING(){
		
		List<Description> descriptionList = new ArrayList<Description>();
		
		descriptionList.addAll(buildDescription("EN", "Add a TELUS Long Distance plan and enjoy great long distance rates from your home phone to calls within Canada, to the US, and internationally", 
				                                "FR", "Add a TELUS Long Distance plan and enjoy great long distance rates from your home phone to calls within Canada, to the US, and internationally - FR"));
		
		descriptionList.addAll(buildDescription("EN", "911 charges are included on the price displayed on the confirmation screen and customer service agreement", 
				                                "FR", "911 charges are included on the price displayed on the confirmation screen and customer service agreement - FR"));		
		
		descriptionList.addAll(buildDescription("EN", "Includes local phone line and 9 TELUS calling features", 
				                                "FR", "Includes local phone line and 9 TELUS calling features - FR")); 
		
		return descriptionList;
	}
	
	
	
	
	/****************************************/
	/* buildAddOn_SING                      */
	/****************************************/
	private static AddOn buildAddOn_SING(){
		AddOn addon = new AddOn();
		List<ProductCatalogueItem> list = new ArrayList<ProductCatalogueItem>();
		
		list.add(buildProductCatalogueItem("1270100", "40638241", "World 60 Unlimited LD", "101043", "1227"));
        list.add(buildProductCatalogueItem("751188", "20381828", "Pay-per-minute Long Distance", "101043", "1227"));
        list.add(buildProductCatalogueItem("935002", "20941078", "LD Philippines 100", "101043", "1227"));
        list.add(buildProductCatalogueItem("751004", "20382268", "Asia Long Distance", "101043", "1227"));
        list.add(buildProductCatalogueItem("1457101", "40732011", "Call India 5000 minutes", "101043", "1227"));
        list.add(buildProductCatalogueItem("751001", "20382498", "Unlimited India Long Distance", "101043", "1227"));
        list.add(buildProductCatalogueItem("751194", "20381528", "300 minutes in US and Canada Long Distance", "101043", "1227"));
        list.add(buildProductCatalogueItem("101043", "1227", "Standard Toll Plans", "101402", "44")); 
        
        addon.setIncludedProductCatalogueItemList(list);
        return addon;
	}
	 
	/****************************************/
	/* buildDiscountList                    */
	/****************************************/
	private static List<Discount> buildDiscountList_SING(){
		Discount discount = new Discount();
		 
		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeCode("upgrade");
		discount.setTransactionTypeList(Arrays.asList(transactionType));
		
		discount.setContractTermList(Arrays.asList(new BigInteger("0")));
		 
		ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
		productCatalogueItem.setProductCatalogueIdentifier(buildProductCatalogueIdentifier("20523978", "705447"));
		
		
		List<Description> descriptions = buildDescription("EN", "MEP Waiver");
		descriptions.addAll(buildDescription("EN", "Connection and processing waiver"));
		productCatalogueItem.setProductCatalogueNameList(descriptions);
		
		productCatalogueItem.setParentProductCatalogueIdentifier(buildProductCatalogueIdentifier("44", "101402"));
		
		discount.setProductCatalogueItemList(Arrays.asList(productCatalogueItem));
		
		discount.setIncludedInd(false);
		discount.setStackableInd(true);
		discount.setWinbackInd(false);
		
		discount.setMarketingDescriptionList(buildDescription("EN", "MEP Waiver", "FR", "MEP Waiver - FR"));
		return Arrays.asList(discount);
	}
	
	/****************************************/
	/* buildFeature_Sing                    */
	/****************************************/
	private static Feature buildFeature_SING(){
		Feature feature = new Feature();
		
		feature.setMinQty(new BigInteger("1"));
		feature.setMaxQty(new BigInteger("9"));
		
		List<ProductCatalogueItem> list = new ArrayList<ProductCatalogueItem>();
		
        list.add(buildProductCatalogueItem("101415", "45", "Call Display", "101403", "41"));
        list.add(buildProductCatalogueItem("101418", "49", "Caller Reveal", "101403", "41"));
        list.add(buildProductCatalogueItem("101396", "10820", "Voice Mail", "101403", "41"));
        list.add(buildProductCatalogueItem("101436", "48", "Call Return", "101403", "41"));
        list.add(buildProductCatalogueItem("101427", "672", "Call Forwarding Variable", "101403", "41"));
        list.add(buildProductCatalogueItem("101419", "51", "Call Waiting", "101403", "41"));
        list.add(buildProductCatalogueItem("101422", "54", "SmartRing", "101403", "41"));
        list.add(buildProductCatalogueItem("101435", "55", "3 Way Calling", "101403", "41"));
        list.add(buildProductCatalogueItem("101423", "166", "Call Screen", "101403", "41"));
        
        feature.setOptionalProductCatalogueItemList(list);
		return feature;
	}
	
	

	
	/**************************************************************   T T V  *************************************************************************/
	
	/****************************************/
	/* buildWirelineOfferProduct_TTV        */
	/****************************************/
	private static WirelineOfferProduct buildWirelineOfferProduct_TTV(){
		
		WirelineOfferProduct product = new WirelineOfferProduct();
		
		product.setProductTypeCode("TTV");
		product.setTransactionTypeList(buildTransactionTypeList("upgrade"));
		product.setContractTermList(Arrays.asList(new BigInteger("24")));
		product.setProductTemplateIdentifier(buildProductCatalogueIdentifier("21175459", "1219000"));
		
		// Build productComponent
		ProductComponent productComponent = new ProductComponent(); 
		productComponent.setProductCatalogueItemList(buildProductCategueItemList("1218031",
				                                                                 "21141969", 
				                                                                 "Essentials2Pack",
				                                                                 "101108",
				                                                                 "62" ));
		product.setProductComponentList(Arrays.asList(productComponent));
		
		
		product.setProductCatalogSystemId("10289");
		product.setMainComponentIdentifier(buildProductCatalogueIdentifier("62", "101108"));
		
		
		product.setWirelineEquipmentList(buildWirelineEquipmentList_TTV());
		
		product.setAddOn(buildAddOn_TTV()); 
		
		return product;
	}
	
	
	
	
	
	/****************************************/
	/* buildWirelineEquipmentList_TTV       */
	/****************************************/
	private static List<SalesWirelineEquipment> buildWirelineEquipmentList_TTV(){
		
		SalesWirelineEquipment salesWirelineEquipment = new SalesWirelineEquipment();
		
		ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
		productCatalogueItem.setProductCatalogueIdentifier(buildProductCatalogueIdentifier("51360", "101111"));
		productCatalogueItem.setParentProductCatalogueIdentifier(buildProductCatalogueIdentifier("33", "101110"));
		productCatalogueItem.setProductCatalogueNameList(buildDescription("EN", "TV Equipment"));
		
		
		
		salesWirelineEquipment.setProductCatalogueItem(productCatalogueItem);
		salesWirelineEquipment.setMinQty(new BigInteger("1"));
		salesWirelineEquipment.setMaxQty(new BigInteger("6"));
		
		salesWirelineEquipment.setPurchaseEquipmentList(buildPurchaseEquipmentItems_TTV());
		
		salesWirelineEquipment.setRentalEquipmentList(buildRentalEquipmentItems_TTV());
		
		return Arrays.asList(salesWirelineEquipment);
	}

	
	/****************************************/
	/* buildPurchaseEquipmentItems_TTV      */
	/****************************************/
	private static List<SalesWirelineEquipmentItem> buildPurchaseEquipmentItems_TTV(){
		List<SalesWirelineEquipmentItem> list = new ArrayList<SalesWirelineEquipmentItem>();
		
		list.addAll(buildEquipmentList("1638252", "Optik TV HD digital box", "INSTALLER", 150.0, -1.0, -1.0, false));
		list.addAll(buildEquipmentList("2314989", "Optik TV 4K Wireless box", "INSTALLER", 250.0, -1.0, -1.0, false));
		list.addAll(buildEquipmentList("1638336", "Optik TV HD PVR", "INSTALLER", 250.0, -1.0, -1.0, false));
		list.addAll(buildEquipmentList("1726174", "Optik TV Wireless HD digital box", "INSTALLER", 150.0, -1.0, -1.0, false));
		list.addAll(buildEquipmentList("2314985", "Optik TV 4K PVR", "INSTALLER", 450.0, -1.0, -1.0, false));
		return list;
	}
	
	/****************************************/
	/* buildRentalEquipmentItems_TTV        */
	/****************************************/
	private static List<SalesWirelineEquipmentItem> buildRentalEquipmentItems_TTV(){
		List<SalesWirelineEquipmentItem> list = new ArrayList<SalesWirelineEquipmentItem>();
		
		list.addAll(buildEquipmentList("2314990", "Optik TV 4K Wireless box", "INSTALLER", -1.0, 250.0, 250.0, false));
		list.addAll(buildEquipmentList("1638338", "Optik TV HD PVR", "INSTALLER", -1.0, 250.0, 250.0, false));
		list.addAll(buildEquipmentList("1638254", "Optik TV HD digital box", "INSTALLER", -1.0, 150.0, 150.0, false));
		list.addAll(buildEquipmentList("1726175", "Optik TV Wireless HD digital box", "INSTALLER", -1.0, 150.0, 150.0, false));
		list.addAll(buildEquipmentList("2314986", "Optik TV 4K PVR", "INSTALLER", -1.0, 500.0, 500.0, false));
		return list;
	}
	
	
	/****************************************/
	/* buildAddOn_TTV                       */
	/****************************************/
	private static AddOn buildAddOn_TTV(){
		AddOn addon = new AddOn();
		List<ProductCatalogueItem> list = new ArrayList<ProductCatalogueItem>();
		
        list.add(buildProductCatalogueItem("1234015", "21152428", "Disney Junior", "101143", "51041"));
        list.add(buildProductCatalogueItem("101270", "50791", "CNN", "101143", "51041"));
        list.add(buildProductCatalogueItem("705959", "20648948", "Hollywood Suite 90s", "101143", "51041"));
        list.add(buildProductCatalogueItem("101258", "50909", "Showcase", "101143", "51041"));
        list.add(buildProductCatalogueItem("1409104", "41995703", "Sportsnet Pack", "101143", "51041"));
        list.add(buildProductCatalogueItem("101309", "50823", "Family Channel", "101143", "51041"));
        list.add(buildProductCatalogueItem("101265", "50916", "Space", "101143", "51041"));
        list.add(buildProductCatalogueItem("816000", "20846288", "Cartoon Network", "101143", "51041"));
        list.add(buildProductCatalogueItem("700329", "65591", "AMC", "101143", "51041"));
        list.add(buildProductCatalogueItem("705967", "20649238", "Hollywood Suite 2000s", "101143", "51041"));
        list.add(buildProductCatalogueItem("101266", "50918", "Paramount Network", "101143", "51041"));
        list.add(buildProductCatalogueItem("705960", "20648908", "Hollywood Suite 80s", "101143", "51041"));
        list.add(buildProductCatalogueItem("101319", "50868", "Slice", "101143", "51041"));
        list.add(buildProductCatalogueItem("101175", "50883", "Crime + Investigation", "101143", "51041"));
        list.add(buildProductCatalogueItem("101259", "50932", "TLC", "101143", "51041"));
        list.add(buildProductCatalogueItem("101272", "50799", "CTV News Channel", "101143", "51041"));
        list.add(buildProductCatalogueItem("1409107", "42004203", "TSN Pack", "101143", "51041"));
        list.add(buildProductCatalogueItem("101321", "50923", "E!", "101143", "51041"));
        list.add(buildProductCatalogueItem("101260", "50939", "W Network", "101143", "51041"));
        list.add(buildProductCatalogueItem("101255", "50760", "A&E", "101143", "51041"));
        list.add(buildProductCatalogueItem("101262", "50806", "Discovery", "101143", "51041"));
        list.add(buildProductCatalogueItem("101152", "50798", "Investigation Discovery", "101143", "51041"));
        list.add(buildProductCatalogueItem("101167", "50869", "MovieTime", "101143", "51041"));
        list.add(buildProductCatalogueItem("101314", "50933", "Treehouse", "101143", "51041"));
        list.add(buildProductCatalogueItem("101256", "50778", "Bravo", "101143", "51041"));
        list.add(buildProductCatalogueItem("101324", "55113", "History Television", "101143", "51041"));
        list.add(buildProductCatalogueItem("1409101", "42001203", "Sportsnet ONE Pack", "101143", "51041"));
        list.add(buildProductCatalogueItem("1277100", "40640621", "Disney Channel", "101143", "51041"));
        list.add(buildProductCatalogueItem("1432100", "40725671", "Super Channel (V1)", "101240", "50480"));
        list.add(buildProductCatalogueItem("101253", "64", "Theme Packs", "101108", "62"));
        list.add(buildProductCatalogueItem("1310102", "40662431", "Hollywood Suite", "101253", "64"));
        list.add(buildProductCatalogueItem("984001", "20977148", "Fairchild 2 HD", "101113", "51040"));
        list.add(buildProductCatalogueItem("703431", "68495", "Desi Starter Pack", "101113", "51040"));
        list.add(buildProductCatalogueItem("1158004", "21122868", "Mandarin Pack", "101113", "51040"));
        list.add(buildProductCatalogueItem("1019000", "21019858", "Chinese Super Pack", "101113", "51040"));
        list.add(buildProductCatalogueItem("703430", "68506", "Punjabi Pack", "101113", "51040"));
        list.add(buildProductCatalogueItem("703429", "68507", "Desi Mega Pack", "101113", "51040"));
        list.add(buildProductCatalogueItem("101113", "51040", "Worldview", "101108", "62"));
        list.add(buildProductCatalogueItem("701110", "67740", "Cantonese Pack", "101113", "51040"));
        list.add(buildProductCatalogueItem("1442100", "40729011", "TMN Combo with CraveTV", "101195", "322"));
        list.add(buildProductCatalogueItem("620392", "62138", "Super Channel", "101240", "50480"));
		
		
		addon.setIncludedProductCatalogueItemList(list);
		
		
		return addon;
	}
	
	
	
	/****************************************        OfferHeader          *********************************************************/ 
	
	/*********************************************/
	/*   buildOfferHeader                        */            
	/*********************************************/ 
	private static OfferHeader buildOfferHeader() {
		OfferHeader offerHeader             = new OfferHeader();
		
		offerHeader.setProgramId(1005571);
		offerHeader.setProgramCode("ANYTIME-FFH-BUNDLE");
		offerHeader.setProgramDescriptionList(buildDescription("EN", "ANYTIME FFH Bundle", "FR", "ANYTIME FFH Bundle - FR")); 
		offerHeader.setPerspectiveDate(buildDate("2018-02-22 17:22:33"));
		offerHeader.setEffectiveDate(buildDate("2018-02-21 17:22:33"));
		offerHeader.setExpiryDate(buildDate("9999-01-01 23:59:59"));
		offerHeader.setBrandId(1);
		offerHeader.setTypeCode("WIRELINE");
		offerHeader.setOfferId(1008321);
		offerHeader.setOfferDescriptionList(buildDescription("EN", "ANYTIME FFH Bundle", "FR", "ANYTIME FFH Bundle - FR"));
		
		
		
		//List of Category - categoryList
		Category category = new Category();
		category.setCategoryCode("Bundle");
		List<Category> catelgoryList = Arrays.asList(category);
		
		// OfferCategory - offerCategory
		OfferCategory offerCategory = new OfferCategory();
		offerCategory.setCategoryTypeCode("OFFER_CATEGORY");
		offerCategory.setCategoryList(catelgoryList); 
		offerHeader.setOfferCategoryList(Arrays.asList(offerCategory));
		
		offerHeader.setBasePriceAmt(200.0);
		offerHeader.setPaymentSupportTypeCode("subsidy");
		return offerHeader;
	}
	
	/*********************************************/
	/*   public getStubSalesOfferDetail          */
	/*********************************************/ 
	public static GetSalesOfferDetailResponseType getStubSalesOfferDetail(){
		
		GetSalesOfferDetailResponseType resp = new GetSalesOfferDetailResponseType();
		
		EnterpriseSalesOfferDetail offer = new EnterpriseSalesOfferDetail();
		
		/*********************************************/
		/*   OfferHeader                             */
		/*********************************************/ 
		offer.setOfferHeader(buildOfferHeader());
		
		/*********************************************/
		/*   SalesOfferProduct                       */
		/*********************************************/
		SalesOfferProduct salesOfferProduct = new SalesOfferProduct();
		salesOfferProduct.setWirelineOfferProductList(Arrays.asList(buildWirelineOfferProduct_HSIC(),
																buildWirelineOfferProduct_TTV(),
																buildWirelineOfferProduct_SING()));
		offer.setOfferProduct(salesOfferProduct);
		
		
		/*********************************************/
		/*   ProgramPromotion                        */
		/*********************************************/
//		ProgramPromotion programPromotion   = new ProgramPromotion();
//		offer.setPromotionCodeOfferInfo(programPromotion);
		
		
		resp.setOfferDetail(offer);
		return resp;
	}
	
	
	/********************************************************************************************************************************************************************************************/
	
	/*********************************************/
	/*   getStubWirelineProductComponentList     */
	/*********************************************/ 
	public static GetWirelineProductComponentListResponseType getStubWirelineProductComponentList(){
		GetWirelineProductComponentListResponseType resp = new GetWirelineProductComponentListResponseType(); 
		
		/********************************/
		/*   First component List       */
		/********************************/
		WirelineProductCatalogueItem component1 = buildSalesProductComponent("21175459", "1219000", "MediaroomTV-HS2.0", "", "OFF", "1", "1", " ", "");
		WirelineProductCatalogueItem component1A = buildSalesProductComponent("51285", "101096", "High Speed Internet", "", "SPC", "1", "1", "", "");
		WirelineProductCatalogueItem component1B = buildSalesProductComponent("22", "101097", "High Speed Internet Services", ".", "CMP", "", "", "HSIC", "true");
		WirelineProductCatalogueItem component1C = buildSalesProductComponent("1", "101102", "High Speed Component", "..", "CMP", "0", "1", "ADSL", "");
		WirelineProductCatalogueItem component1D = buildSalesProductComponent("34", "101104", "Internet Equipment", ".", "CMP", "0", "1", "INEQ", "");
		WirelineProductCatalogueItem component1E = buildSalesProductComponent("25", "101082", "Packs", ".", "CMP", "0", "1", "PCKS", "");
		WirelineProductCatalogueItem component1F = buildSalesProductComponent("64905", "700489", "HSEXLF", ".", "HSEXLF", "0", "1", "ADSP", "");
		 
		
		component1E.getComponentList().add(component1F);
		component1C.getComponentList().add(component1D);
		component1B.getComponentList().add(component1C);
		component1B.getComponentList().add(component1E);
		component1A.getComponentList().add(component1B);
		component1.getComponentList().add(component1A);
		
		resp.getSalesProductComponentList().add(component1);
		

		/********************************/
		/*   Second component List      */
		/********************************/
		String temp = "Offering the very best and current Cantonese entertainment, Fairchild 2 HD features the latest TVB Cantonese dramas - no waiting, no delays, same day as they air in Hong Kong, along with the most current and popular variety shows, and other diverse programming ";
		 
		
		WirelineProductCatalogueItem component2 = buildSalesProductComponent("21175459", "1219000", "MediaroomTV-HS2.0", "", "OFF", "1", "1", " ", "");
		WirelineProductCatalogueItem component2A = buildSalesProductComponent("111", "101107", "TELUS TV", ".", "SPC", "1", "1", "", "");
		WirelineProductCatalogueItem component2B = buildSalesProductComponent("62", "101108", "TELUS TV", ",", "CMP", "1", "1", "TTV", "");
		WirelineProductCatalogueItem component2C = buildSalesProductComponent("51040", "101113", "Worldview", ".", "CMP", "1", "1", "TVCG", "");
		WirelineProductCatalogueItem component2D = buildSalesProductComponent("20977148", "984001", "Fairchild 2 HD", temp, "CMP", "", "", "TTVC", "");
		WirelineProductCatalogueItem component2E = buildSalesProductComponent("33", "101110", "Registration Number", ".", "CMP", "0", "1", "REG", "");
		WirelineProductCatalogueItem component2F = buildSalesProductComponent("51360", "101111", "TV Equipment", ",", "CMP", "0", "1", "TVEQ", "");
		WirelineProductCatalogueItem component2G = buildSalesProductComponent("66992", "705787", "Combos", ".", "CMP", "0", "1", "VP", "");
		WirelineProductCatalogueItem component2H = buildSalesProductComponent("21140889", "1218010", "FavouritesPlusMovies", ".", "CMP", "0", "1", "VPC", "");
		WirelineProductCatalogueItem component2I = buildSalesProductComponent("21141969", "1218031", "Essentials2Pack", ".", "CMP", "0", "1", "TVES", "");
		
		component2G.getComponentList().add(component2H);
		component2E.getComponentList().add(component2F);
		component2C.getComponentList().add(component2D);
		component2B.getComponentList().add(component2C);
		component2B.getComponentList().add(component2E);
		component2B.getComponentList().add(component2G);
		component2B.getComponentList().add(component2I);
		component2A.getComponentList().add(component2B);
		component2.getComponentList().add(component2A);
		
		
		resp.getSalesProductComponentList().add(component2);
		
		
		 
		/********************************/
		/*   Third component List       */
		/********************************/
		
		
		WirelineProductCatalogueItem component3 = buildSalesProductComponent("83", "101412", "Single Line", "", "OFF", "1", "1", " ", "");
		WirelineProductCatalogueItem component3A = buildSalesProductComponent("65", "101401", "Single Line", ".", "SPC", "1", "1", "", "");
		WirelineProductCatalogueItem component3B = buildSalesProductComponent("44", "101402", "Single Line", ".", "CMP", "0", "1", "SING", "");
		WirelineProductCatalogueItem component3C = buildSalesProductComponent("21072768", "1066005", "TELUS Home Phone (Forborne) (V1)", ".", "CMP", "0", "1", "SFPK", "");
		WirelineProductCatalogueItem component3D = buildSalesProductComponent("1227", "101043", "Standard Toll Plans", ".", "CMP", "0", "1", "TCTG", "");
		WirelineProductCatalogueItem component3E = buildSalesProductComponent("40638241", "1270100", "World60UnlimitedLD", ".", "CMP", "0", "1", "TPLN", "");
		WirelineProductCatalogueItem component3F = buildSalesProductComponent("41", "101403", "Calling Features", ".", "CMP", "0", "1", "FTR", "");
		WirelineProductCatalogueItem component3G = buildSalesProductComponent("45", "101415", "Call Display", ".", "CMP", "0", "1", "SF", ""); 
		
		
		component3F.getComponentList().add(component3G);
		component3D.getComponentList().add(component3E);
		component3B.getComponentList().add(component3C);
		component3B.getComponentList().add(component3D);
		component3B.getComponentList().add(component3F);
		component3A.getComponentList().add(component3B);
		component3.getComponentList().add(component3A);
		
		
		resp.getSalesProductComponentList().add(component3);
		
		return resp;
	}
	
	
	/*********************************************/
	/*   buildProductCatalogueItemIdentifier     */
	/*********************************************/ 
	private static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductCatalogueIdentifier buildProductCatalogueItemIdentifier(String externalProductCatalogId, String productCatalogueId){
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductCatalogueIdentifier productCatalogueItemIdentifier 
		= new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductCatalogueIdentifier();
		
		productCatalogueItemIdentifier.setExternalProductCatalogueItemId(externalProductCatalogId);
		productCatalogueItemIdentifier.setProductCatalogueItemId(productCatalogueId);
		
		return productCatalogueItemIdentifier;
	}
	
	/*********************************************/
	/*   buildDescriptionV8                      */
	/*********************************************/ 
	private static com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description buildDescriptionV8(String locale, String description){
		
		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description enDescription 
			= new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description();
		
		enDescription.setLocale(locale);
		enDescription.setDescriptionText(description);
		return enDescription;
		
	}
 
	/*********************************************/
	/*   buildSalesProductComponent              */
	/*********************************************/ 
	private static WirelineProductCatalogueItem buildSalesProductComponent(String externalProductCatalogueItemId,
			                                                               String productCatalogueItemId,
			                                                               String productCatalogueNameTxtDescription,
			                                                               String productCatalogueDescription,
			                                                               String catalogueItemTypeCode,
			                                                               String quantityMin,
			                                                               String quantityMax,
			                                                               String serviceTypeCode,
			                                                               String defaultInd)
	{
		
		WirelineProductCatalogueItem component = new WirelineProductCatalogueItem();
		
		component.setProductCatalogueItemIdentifier(buildProductCatalogueItemIdentifier(externalProductCatalogueItemId, productCatalogueItemId));
		
		component.setProductCatalogueNameTxt(buildDescriptionV8("EN",  productCatalogueNameTxtDescription));
		component.setProductCatalogueDescription(buildDescriptionV8("EN", productCatalogueDescription));
		component.setCatalogueItemTypeCode(catalogueItemTypeCode);
		
		if (quantityMax.length() > 0 && quantityMin.length() > 0){
			QuantityAllowed quantity = new QuantityAllowed();
			quantity.setMaxNum(new BigInteger(quantityMax));
			quantity.setMinNum(new BigInteger(quantityMin));
			component.setQuantity(quantity);
		}
		
		if (serviceTypeCode.length() > 0 )
			component.setServiceTypeCode(serviceTypeCode);
		
		if (defaultInd.length() > 0)
			component.setDefaultInd(new Boolean(defaultInd));
		
		return component;
	}
	
 
}
