package com.telus.csm.ewlnsis.soap.webservice;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EnterpriseSalesOfferSummary;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SweetenerOfferSummary;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Category;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.CrossProductDependency;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferCategory;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferProductSummary;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProductSummary;

/**
 * @author Jose.Mena
 *
 */
public class GetAvailableOfferSummaryListStub {

	private GetAvailableOfferSummaryListStub() {
		
	} 
	 
	/***************************************************/
	/* building the mock sweetner offers               */
	/***************************************************/
	private static List<SweetenerOfferSummary> buildMockSweetenerOfferSummaryList(){
		List<SweetenerOfferSummary> list = new ArrayList<SweetenerOfferSummary>();
		
		list.add(buildMockSweetenerOfferSummary_1());
		list.add(buildMockSweetenerOfferSummary_2());
		return list;
	}
	
	/***************************************************/
	/*buildDescriptions                                */
	/***************************************************/
	private static List<Description> buildDescriptions(String enDesc, String frDesc){ 
		
		Description descEnglish = new Description();
		descEnglish.setDescriptionText(enDesc);
		descEnglish.setLocale("EN");
		
		Description descFrench = new Description();
		descFrench.setDescriptionText(frDesc);
		descFrench.setLocale("FR");
		
		return Arrays.asList(descEnglish, descFrench);
	}

	/***************************************************/
	/* buildDate                                       */
	/***************************************************/
	private static Date buildDate(String dateStr){
		SimpleDateFormat formatter5=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date date = new Date();
		try{
			date=formatter5.parse(dateStr);  
		} catch(ParseException pe){
			System.out.println("WTF are you doing....");
		}
		return date;
	}
	
	
	/***************************************************/
	/*  build_offerCategoryList                        */
	/***************************************************/
	private static List<OfferCategory> build_offerCategoryList(){
		List<OfferCategory> offerCatList = new ArrayList<OfferCategory>();
		OfferCategory offerCat = new OfferCategory();
		offerCat.setCategoryTypeCode("OFFER_CATEGORY");
		
		List<Category> catList = new ArrayList<Category>();
		Category category = new Category();
		category.setCategoryCode("CYO");
		catList.add(category);
		offerCat.setCategoryList(catList);
		offerCatList.add(offerCat);
		
		return offerCatList;
	}
	
	/***************************************************/
	/*  buildProductCatalogueIdentifier                */
	/***************************************************/
	private static ProductCatalogueIdentifier buildProductCatalogueIdentifier(String productCatalogueId, String externalProductCatalogId){
		ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier();
		productCatalogueIdentifier.setProductCatalogueId(productCatalogueId);
		
		if (externalProductCatalogId != null)
			productCatalogueIdentifier.setExternalProductCatalogId(externalProductCatalogId);
		
		return productCatalogueIdentifier;
	}
	
	
	private static List<ProductCatalogueItem> buildProductCatalogueItem(String productCatalgueId, String externalId){
		
		List<ProductCatalogueItem> productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
		ProductCatalogueItem productCatalogItem = new ProductCatalogueItem();
		ProductCatalogueIdentifier prodCatId = new ProductCatalogueIdentifier();
		prodCatId.setProductCatalogueId(productCatalgueId);
		prodCatId.setExternalProductCatalogId(externalId);
		productCatalogItem.setProductCatalogueIdentifier(prodCatId);
		
		List<Description> prodDesc = new ArrayList<Description>();
		Description desc2 = new Description();
		desc2.setLocale("EN");
		desc2.setDescriptionText("HSEXLF");
		prodDesc.add(desc2);
		productCatalogItem.setProductCatalogueNameList(prodDesc);
		
		ProductCatalogueIdentifier parentProdCatId = new ProductCatalogueIdentifier();
		parentProdCatId.setExternalProductCatalogId("25");
		parentProdCatId.setProductCatalogueId("101082");
		productCatalogItem.setParentProductCatalogueIdentifier(parentProdCatId);
		
		productCatalogueItemList.add(productCatalogItem);

		return productCatalogueItemList;
	}
	
	
	private static List<CrossProductDependency> buildCrossProductDependencyList(){
		
		CrossProductDependency product1 = new CrossProductDependency();
		product1.setContractTermList(Arrays.asList(new BigInteger("12"), new BigInteger("24")));
		product1.setProductCode("HSIC");
		
		
		return Arrays.asList(product1);
		
	}
	
	/***************************************************/
	/*  buildDiscountList                              */
	/***************************************************/
	private static List<Discount> buildDiscountList(String productCatalogueId,
										            String externalProductCatalogId,
										            String englishDesc,
										            String frenchDesc,
										            String parentProductCatalogueId,
										            String parentExternalProductCatalogId){
		Discount discount = new Discount();
		
		
		discount.setContractTermList(Arrays.asList(new BigInteger("12"), new BigInteger("24")));
		discount.setCrossProductDependencyList(buildCrossProductDependencyList());
		discount.setDiscountCode("dailySpecial"); 
		discount.setMarketingDescriptionList(buildDescriptions("English Marketing Desc", "French Marketing Desc"));
		discount.setProductCatalogueItemList(buildProductCatalogueItem("137294", "83"));
		discount.setRecontractingInd(false); 
		discount.setTransactionTypeList(buildTransactionTypeList());  
		
		
		
		discount.setIncludedInd(false);
		discount.setStackableInd(false);
		discount.setWinbackInd(false);
		discount.setDiscountProductCatalogueItemList(buildDiscountProductCatalogueItem(productCatalogueId, 
																						externalProductCatalogId,
																						englishDesc,
																						frenchDesc,
																						parentProductCatalogueId,
																						parentExternalProductCatalogId));
		
		return Arrays.asList(discount);
	}

	/***************************************************/
	/*  buildTransactionTypeList                       */
	/***************************************************/
	private static List<TransactionType> buildTransactionTypeList(){
		TransactionType tranasactionTypeActivation = new TransactionType();
		tranasactionTypeActivation.setTransactionTypeCode("activation");
		
		
		TransactionType transactionTypeRenewal = new TransactionType();
		transactionTypeRenewal.setTransactionTypeCode("renewal");
		
		return Arrays.asList(tranasactionTypeActivation, transactionTypeRenewal);
	}
	
	/***************************************************/
	/*  buildProductComponentList                      */
	/***************************************************/
	private static List<ProductComponent> buildProductComponentList(){
		
		List<ProductComponent> productCompList = new ArrayList<ProductComponent>();
		ProductComponent prComp = new ProductComponent();
		List<ProductCatalogueItem> productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
		ProductCatalogueItem productCatalogItem = new ProductCatalogueItem();
		ProductCatalogueIdentifier prodCatId = new ProductCatalogueIdentifier();
		prodCatId.setProductCatalogueId("700489");
		prodCatId.setExternalProductCatalogId("64905");
		productCatalogItem.setProductCatalogueIdentifier(prodCatId);
		
		List<Description> prodDesc = new ArrayList<Description>();
		Description desc2 = new Description();
		desc2.setLocale("EN");
		desc2.setDescriptionText("HSEXLF");
		prodDesc.add(desc2);
		productCatalogItem.setProductCatalogueNameList(prodDesc);
		
		ProductCatalogueIdentifier parentProdCatId = new ProductCatalogueIdentifier();
		parentProdCatId.setExternalProductCatalogId("25");
		parentProdCatId.setProductCatalogueId("101082");
		productCatalogItem.setParentProductCatalogueIdentifier(parentProdCatId);
		
		productCatalogueItemList.add(productCatalogItem);
		
		prComp.setProductCatalogueItemList(productCatalogueItemList);
		
		List<Description> markDescList = new ArrayList<Description>();
		Description markDescEn1 = new Description();
		markDescEn1.setDescriptionText("TELUS security services");
		markDescEn1.setLocale("EN");
		markDescList.add(markDescEn1);
		
		
		
		Description markDescFr1 = new Description();
		markDescFr1.setDescriptionText("TELUS security services");
		markDescFr1.setLocale("FR");
		markDescList.add(markDescFr1);
		
		
		
		Description markDescEn2 = new Description();
		markDescEn2.setDescriptionText("Up to 100 MB of personal webspace per month");
		markDescEn2.setLocale("EN");
		markDescList.add(markDescEn2);	
		
		
		
		Description markDescFr2 = new Description();
		markDescFr2.setDescriptionText("Up to 100 MB of personal webspace per month");
		markDescFr2.setLocale("FR");
		markDescList.add(markDescFr2);	
		
		
		
		Description markDescEn3 = new Description();
		markDescEn3.setDescriptionText("10 e-mail accounts");
		markDescEn3.setLocale("EN");
		markDescList.add(markDescEn3);	
		
		
		Description markDescFr3 = new Description();
		markDescFr3.setDescriptionText("10 e-mail accounts");
		markDescFr3.setLocale("FR");
		markDescList.add(markDescFr3);	
		
		
		
		Description markDescEn4 = new Description();
		markDescEn4.setDescriptionText("Download at speeds up to 15 Mbps");
		markDescEn4.setLocale("EN");
		markDescList.add(markDescEn4);	
		
		
		
		Description markDescFr4 = new Description();
		markDescFr4.setDescriptionText("Download at speeds up to 15 Mbps");
		markDescFr4.setLocale("FR");
		markDescList.add(markDescFr4);	
		
		
		
		Description markDescEn5 = new Description();
		markDescEn5.setDescriptionText("Up to 1.0 Mbps upload speed");
		markDescEn5.setLocale("EN");
		markDescList.add(markDescEn5);	
		
		
		
		Description markDescFr5 = new Description();
		markDescFr5.setDescriptionText("Up to 1.0 Mbps upload speed");
		markDescFr5.setLocale("FR");
		markDescList.add(markDescFr5);	
		
		
		
		Description markDescEn6 = new Description();
		markDescEn6.setDescriptionText("Additional usage charges will apply");
		markDescEn6.setLocale("EN");
		markDescList.add(markDescEn6);	
		
		
		
		Description markDescFr6 = new Description();
		markDescFr6.setDescriptionText("Additional usage charges will apply");
		markDescFr6.setLocale("FR");
		markDescList.add(markDescFr6);	
		
		
		
		Description markDescEn7 = new Description();
		markDescEn7.setDescriptionText("FREE wireless gateway rental");
		markDescEn7.setLocale("EN");
		markDescList.add(markDescEn7);	
		
		
		
		Description markDescFr7 = new Description();
		markDescFr7.setDescriptionText("FREE wireless gateway rental");
		markDescFr7.setLocale("FR");
		markDescList.add(markDescFr7);	
		
		
		
		Description markDescEn8 = new Description();
		markDescEn8.setDescriptionText("Internet pricing displayed on the confirmation screen and customer service agreement include a $4.95 internet security features fee which is waived at the time of billing");
		markDescEn8.setLocale("EN");
		markDescList.add(markDescEn8);	
		
		
		
		Description markDescFr8 = new Description();
		markDescFr8.setDescriptionText("Internet pricing displayed on the confirmation screen and customer service agreement include a $4.95 internet security features fee which is waived at the time of billing");
		markDescFr8.setLocale("FR");
		markDescList.add(markDescFr8);	
		
		
		
		Description markDescEn9 = new Description();
		markDescEn9.setDescriptionText("Up to 150 GB of upload and download usage");
		markDescEn9.setLocale("EN");
		markDescList.add(markDescEn9);	
		
		
		
		Description markDescFr9 = new Description();
		markDescFr9.setDescriptionText("Up to 150 GB of upload and download usage");
		markDescFr9.setLocale("FR");
		markDescList.add(markDescFr9);	
		
		
		
		Description markDescEn10 = new Description();
		markDescEn10.setDescriptionText("3.0 to 15 Mbps downloading speed");
		markDescEn10.setLocale("EN");
		markDescList.add(markDescEn10);	
		
		
		
		Description markDescFr10 = new Description();
		markDescFr10.setDescriptionText("3.0 to 15 Mbps downloading speed");
		markDescFr10.setLocale("FR");
		markDescList.add(markDescFr10);	
		prComp.setMarketingDescriptionList(markDescList);
		
		
		productCompList.add(prComp);
		
		return productCompList;
	}
	
	/***************************************************/
	/*  buildOfferProductSummary                       */
	/***************************************************/
	private static SweetenerOfferSummary.OfferProductSummary buildOfferProductSummary(String productTypeCode,
            String templateProductCatalogueId,
            String productCatalogSystemId,
            String mainProductCatalogueId,
            String mainExternalProductCatalogId){

		SweetenerOfferSummary.OfferProductSummary offerProductSummary = new SweetenerOfferSummary.OfferProductSummary(); 
		
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary wirelineOfferProductSummary = 
		new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary();

		
		wirelineOfferProductSummary.setContractTermList(Arrays.asList(new BigInteger("24"), new BigInteger("12")));
		wirelineOfferProductSummary.setDiscountList(buildDiscountList(  "1494113", 
														                "40742031",
														                "3 MO - Optik TV combo discount 2.0 - $30",
														                "FR 3 MO - Optik TV combo discount 2.0 - $30",
														                "101108",
														                "62"));
		wirelineOfferProductSummary.setMainComponentIdentifier(buildProductCatalogueIdentifier(mainProductCatalogueId, mainExternalProductCatalogId));
		wirelineOfferProductSummary.setProductCatalogSystemId(productCatalogSystemId);
		wirelineOfferProductSummary.setProductComponentList(buildProductComponentList());
		
		if (templateProductCatalogueId != null)
		wirelineOfferProductSummary.setProductTemplateIdentifier(buildProductCatalogueIdentifier(templateProductCatalogueId, null)); 
		
		wirelineOfferProductSummary.setProductTypeCode(productTypeCode);
		wirelineOfferProductSummary.setTransactionTypeList(buildTransactionTypeList());
		
		offerProductSummary.setWirelineOfferProductSummaryList(Arrays.asList(wirelineOfferProductSummary));
		
		return offerProductSummary;
	}
	
	/***************************************************/
	/*  buildDiscountProductCatalogueItem              */
	/***************************************************/
	private static List<DiscountProductCatalogueItem> buildDiscountProductCatalogueItem(String productCatalogueId,
            String externalProductCatalogId,
            String englishDesc,
            String frenchDesc,
            String parentProductCatalogueId,
            String parentExternalPRoductCatalogId){
		DiscountProductCatalogueItem item = new DiscountProductCatalogueItem();
		
		
		
		item.setParentProductCatalogueIdentifier(buildProductCatalogueIdentifier(productCatalogueId, externalProductCatalogId));
		item.setProductCatalogueNameList(buildDescriptions(englishDesc, frenchDesc));
		item.setParentProductCatalogueIdentifier(buildProductCatalogueIdentifier(parentProductCatalogueId, parentExternalPRoductCatalogId));
		
		
		return Arrays.asList(item);
	}
	
	
	
	/***************************************************/
	/*  First sweetner offer                           */
	/***************************************************/
	private static SweetenerOfferSummary buildMockSweetenerOfferSummary_1(){
		SweetenerOfferSummary summary = new SweetenerOfferSummary();

		summary.setOfferCategoryList(build_offerCategoryList());
		summary.setStackableInd(true);
		summary.setProgramId(1017552);
		summary.setProgramCode("QA_TESTING");
		summary.setProgramDescriptionList(buildDescriptions("English Program Description", "French Program Description"));
		summary.setPerspectiveDate(buildDate("2017-03-27 11:22:33"));
		summary.setEffectiveDate(buildDate("2017-03-26 11:22:33"));
		summary.setExpiryDate(buildDate("9999-01-01 23:59:59"));
		summary.setBrandId(1);
		summary.setTypeCode("SWEETENER");
		summary.setOfferId(1014566);
		summary.setOfferDescriptionList(buildDescriptions("English Offer Description", "French Offer Description"));
		summary.setOfferProductSummary(buildOfferProductSummary("TTV", null, "10289", "101108", "62" ));

		
		return summary;
	}
	
	/***************************************************/
	/*  First sweetner offer                           */
	/***************************************************/
	private static SweetenerOfferSummary buildMockSweetenerOfferSummary_2(){
		SweetenerOfferSummary summary = new SweetenerOfferSummary();

		summary.setOfferCategoryList(build_offerCategoryList());
		summary.setStackableInd(true);
		summary.setProgramId(1017553);
		summary.setProgramCode("MOCK_TESTING");
		summary.setProgramDescriptionList(buildDescriptions("English Program Description", "French Program Description"));
		summary.setPerspectiveDate(buildDate("2018-03-27 11:22:33"));
		summary.setEffectiveDate(buildDate("2019-03-26 11:22:33"));
		summary.setExpiryDate(buildDate("9999-01-01 23:59:59"));
		summary.setBrandId(1);
		summary.setTypeCode("SWEETENER");
		summary.setOfferId(1014567);
		summary.setOfferDescriptionList(buildDescriptions("English Offer Description", "French Offer Description"));
		summary.setOfferProductSummary(buildOfferProductSummary("TTV", null, "10289", "101108", "62" ));

		
		return summary;
	}
	
	/***************************************************/
	/*  getSweetenerOfferSummaryMockup                  */
	/***************************************************/
	public static GetAvailableOfferSummaryListResponseType getSweetnerOfferSummaryMockup(){
		
		GetAvailableOfferSummaryListResponseType result = new GetAvailableOfferSummaryListResponseType();
		
		result.setSweetenerOfferSummaryList(buildMockSweetenerOfferSummaryList());

		return result;
	}
	
	
	public static GetAvailableOfferSummaryListResponseType getOfferSummaryMockup() {
		GetAvailableOfferSummaryListResponseType result = new GetAvailableOfferSummaryListResponseType();
		List<EnterpriseSalesOfferSummary> salesOfferSummaryList = new ArrayList<EnterpriseSalesOfferSummary>();
		EnterpriseSalesOfferSummary entOfferSummary = new EnterpriseSalesOfferSummary();
		entOfferSummary.setProgramId(1004969L);
		entOfferSummary.setProgramCode("ANYTIME-FAVPLUS-MOVIE&amp;SPC");
		Description desc = new Description();
		desc.setLocale("EN");
		desc.setDescriptionText("Favourites Plus &amp; Movies (Super Channel) with TELUS Internet 15");
		List<Description> prDescList = new ArrayList<Description>();
		Description desc1 = new Description();
		desc1.setLocale("EN");
		desc1.setDescriptionText("Favourites Plus &amp; Movies (Super Channel) with TELUS Internet 15 - FR5");
		prDescList.add(desc1);
		prDescList.add(desc);
		entOfferSummary.setProgramDescriptionList(prDescList);
		try {
			entOfferSummary.setPerspectiveDate(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse("2018-02-21 16:39:18"));
			entOfferSummary.setEffectiveDate(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse("2018-02-20 11:08:42"));
			entOfferSummary.setExpiryDate(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse("9999-01-01 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		entOfferSummary.setBrandId(1);
		entOfferSummary.setTypeCode("WIRELINE");
		entOfferSummary.setOfferId(1007776);
		List<Description> offerDescriptionList = new ArrayList<Description>();
		Description offerDescriptionEN = new Description();
		offerDescriptionEN.setLocale("EN");
		offerDescriptionEN.setDescriptionText("Favourites Plus &amp; Movies (Super Channel) with TELUS Internet 15");
		Description offerDescriptionFR = new Description();
		offerDescriptionFR.setLocale("FR");
		offerDescriptionFR.setDescriptionText("Favourites Plus &amp; Movies (Super Channel) with TELUS Internet 15 - FR");
		offerDescriptionList.add(offerDescriptionEN);
		offerDescriptionList.add(offerDescriptionFR);
		entOfferSummary.setOfferDescriptionList(offerDescriptionList);
		
		List<OfferCategory> offerCatList = new ArrayList<OfferCategory>();
		OfferCategory offerCat = new OfferCategory();
		offerCat.setCategoryTypeCode("OFFER_CATEGORY");
		
		List<Category> catList = new ArrayList<Category>();
		Category category = new Category();
		category.setCategoryCode("CYO");
		catList.add(category);
		offerCat.setCategoryList(catList);
		offerCatList.add(offerCat);
		entOfferSummary.setOfferCategoryList(offerCatList);
		
		entOfferSummary.setBasePriceAmt(161.0);
		entOfferSummary.setPaymentSupportTypeCode("subsidy");
		
		OfferProductSummary offerProdSummary = new OfferProductSummary();
		List<WirelineOfferProductSummary> wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();
		WirelineOfferProductSummary hsicOfferProdSummary = new WirelineOfferProductSummary();
		hsicOfferProdSummary.setProductTypeCode("HSIC");
		List<TransactionType> hsicTransactionTypeList = new ArrayList<TransactionType>();
		TransactionType hsicTransactionTypeUp = new TransactionType();
		hsicTransactionTypeUp.setTransactionTypeCode("upgrade");
		hsicTransactionTypeList.add(hsicTransactionTypeUp);
		
		TransactionType hsicTransactionTypeAc = new TransactionType();
		hsicTransactionTypeAc.setTransactionTypeCode("activation");
		hsicTransactionTypeList.add(hsicTransactionTypeAc);
		
		hsicOfferProdSummary.setTransactionTypeList(hsicTransactionTypeList);
		
		List<BigInteger> hsicContTermList = new ArrayList<BigInteger>();
		hsicContTermList.add(new BigInteger("0"));
		hsicContTermList.add(new BigInteger("24")); 
		hsicOfferProdSummary.setContractTermList(hsicContTermList);
		
		ProductCatalogueIdentifier catIdentifier = new ProductCatalogueIdentifier();
		catIdentifier.setProductCatalogueId("1219000");
		catIdentifier.setExternalProductCatalogId("21175459");
		hsicOfferProdSummary.setProductTemplateIdentifier(catIdentifier);
		
		List<ProductComponent> productCompList = new ArrayList<ProductComponent>();
		ProductComponent prComp = new ProductComponent();
		List<ProductCatalogueItem> productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
		ProductCatalogueItem productCatalogItem = new ProductCatalogueItem();
		ProductCatalogueIdentifier prodCatId = new ProductCatalogueIdentifier();
		prodCatId.setProductCatalogueId("700489");
		prodCatId.setExternalProductCatalogId("64905");
		productCatalogItem.setProductCatalogueIdentifier(prodCatId);
		
		List<Description> prodDesc = new ArrayList<Description>();
		Description desc2 = new Description();
		desc2.setLocale("EN");
		desc2.setDescriptionText("HSEXLF");
		prodDesc.add(desc2);
		productCatalogItem.setProductCatalogueNameList(prodDesc);
		
		ProductCatalogueIdentifier parentProdCatId = new ProductCatalogueIdentifier();
		parentProdCatId.setExternalProductCatalogId("25");
		parentProdCatId.setProductCatalogueId("101082");
		productCatalogItem.setParentProductCatalogueIdentifier(parentProdCatId);
		
		productCatalogueItemList.add(productCatalogItem);
		
		prComp.setProductCatalogueItemList(productCatalogueItemList);
		
		List<Description> markDescList = new ArrayList<Description>();
		Description markDescEn1 = new Description();
		markDescEn1.setDescriptionText("TELUS security services");
		markDescEn1.setLocale("EN");
		markDescList.add(markDescEn1);
		
		
		
		Description markDescFr1 = new Description();
		markDescFr1.setDescriptionText("TELUS security services");
		markDescFr1.setLocale("FR");
		markDescList.add(markDescFr1);
		
		
		
		Description markDescEn2 = new Description();
		markDescEn2.setDescriptionText("Up to 100 MB of personal webspace per month");
		markDescEn2.setLocale("EN");
		markDescList.add(markDescEn2);	
		
		
		
		Description markDescFr2 = new Description();
		markDescFr2.setDescriptionText("Up to 100 MB of personal webspace per month");
		markDescFr2.setLocale("FR");
		markDescList.add(markDescFr2);	
		
		
		
		Description markDescEn3 = new Description();
		markDescEn3.setDescriptionText("10 e-mail accounts");
		markDescEn3.setLocale("EN");
		markDescList.add(markDescEn3);	
		
		
		Description markDescFr3 = new Description();
		markDescFr3.setDescriptionText("10 e-mail accounts");
		markDescFr3.setLocale("FR");
		markDescList.add(markDescFr3);	
		
		
		
		Description markDescEn4 = new Description();
		markDescEn4.setDescriptionText("Download at speeds up to 15 Mbps");
		markDescEn4.setLocale("EN");
		markDescList.add(markDescEn4);	
		
		
		
		Description markDescFr4 = new Description();
		markDescFr4.setDescriptionText("Download at speeds up to 15 Mbps");
		markDescFr4.setLocale("FR");
		markDescList.add(markDescFr4);	
		
		
		
		Description markDescEn5 = new Description();
		markDescEn5.setDescriptionText("Up to 1.0 Mbps upload speed");
		markDescEn5.setLocale("EN");
		markDescList.add(markDescEn5);	
		
		
		
		Description markDescFr5 = new Description();
		markDescFr5.setDescriptionText("Up to 1.0 Mbps upload speed");
		markDescFr5.setLocale("FR");
		markDescList.add(markDescFr5);	
		
		
		
		Description markDescEn6 = new Description();
		markDescEn6.setDescriptionText("Additional usage charges will apply");
		markDescEn6.setLocale("EN");
		markDescList.add(markDescEn6);	
		
		
		
		Description markDescFr6 = new Description();
		markDescFr6.setDescriptionText("Additional usage charges will apply");
		markDescFr6.setLocale("FR");
		markDescList.add(markDescFr6);	
		
		
		
		Description markDescEn7 = new Description();
		markDescEn7.setDescriptionText("FREE wireless gateway rental");
		markDescEn7.setLocale("EN");
		markDescList.add(markDescEn7);	
		
		
		
		Description markDescFr7 = new Description();
		markDescFr7.setDescriptionText("FREE wireless gateway rental");
		markDescFr7.setLocale("FR");
		markDescList.add(markDescFr7);	
		
		
		
		Description markDescEn8 = new Description();
		markDescEn8.setDescriptionText("Internet pricing displayed on the confirmation screen and customer service agreement include a $4.95 internet security features fee which is waived at the time of billing");
		markDescEn8.setLocale("EN");
		markDescList.add(markDescEn8);	
		
		
		
		Description markDescFr8 = new Description();
		markDescFr8.setDescriptionText("Internet pricing displayed on the confirmation screen and customer service agreement include a $4.95 internet security features fee which is waived at the time of billing");
		markDescFr8.setLocale("FR");
		markDescList.add(markDescFr8);	
		
		
		
		Description markDescEn9 = new Description();
		markDescEn9.setDescriptionText("Up to 150 GB of upload and download usage");
		markDescEn9.setLocale("EN");
		markDescList.add(markDescEn9);	
		
		
		
		Description markDescFr9 = new Description();
		markDescFr9.setDescriptionText("Up to 150 GB of upload and download usage");
		markDescFr9.setLocale("FR");
		markDescList.add(markDescFr9);	
		
		
		
		Description markDescEn10 = new Description();
		markDescEn10.setDescriptionText("3.0 to 15 Mbps downloading speed");
		markDescEn10.setLocale("EN");
		markDescList.add(markDescEn10);	
		
		
		
		Description markDescFr10 = new Description();
		markDescFr10.setDescriptionText("3.0 to 15 Mbps downloading speed");
		markDescFr10.setLocale("FR");
		markDescList.add(markDescFr10);	
		prComp.setMarketingDescriptionList(markDescList);
		
		
		productCompList.add(prComp);
		
		hsicOfferProdSummary.setProductComponentList(productCompList);
		
		hsicOfferProdSummary.setProductCatalogSystemId("10289");
		
		ProductCatalogueIdentifier mainProdCatId = new ProductCatalogueIdentifier();
		mainProdCatId.setProductCatalogueId("101097");
		mainProdCatId.setExternalProductCatalogId("22");
		
		hsicOfferProdSummary.setMainComponentIdentifier(mainProdCatId);
		
		wirelineOfferProductSummaryList.add(hsicOfferProdSummary);
		
		WirelineOfferProductSummary ttvOfferProdSummary = new WirelineOfferProductSummary();
		ttvOfferProdSummary.setProductTypeCode("TTV");
		
		List<TransactionType> ttvTransactionTypeList = new ArrayList<TransactionType>();
		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeCode("activation");
		ttvTransactionTypeList.add(transactionType);
		
		
		ttvOfferProdSummary.setTransactionTypeList(ttvTransactionTypeList);
		
		List<BigInteger> ttvContTermList = new ArrayList<BigInteger>();
		ttvContTermList.add(new BigInteger("0"));
		ttvContTermList.add(new BigInteger("24")); 
		ttvOfferProdSummary.setContractTermList(ttvContTermList);
		
		ProductCatalogueIdentifier ttvCatIdentifier = new ProductCatalogueIdentifier();
		ttvCatIdentifier.setProductCatalogueId("1219000");
		ttvCatIdentifier.setExternalProductCatalogId("21175459");
		ttvOfferProdSummary.setProductTemplateIdentifier(ttvCatIdentifier);
		
		List<ProductComponent> ttvProductCompList = new ArrayList<ProductComponent>();
		ProductComponent ttvPrComp = new ProductComponent();
		List<ProductCatalogueItem> ttvProductCatalogueItemList = new ArrayList<ProductCatalogueItem>();
		ProductCatalogueItem ttvProductCatalogItem = new ProductCatalogueItem();
		ProductCatalogueIdentifier ttvProdCatId = new ProductCatalogueIdentifier();
		ttvProdCatId.setProductCatalogueId("1218031");
		ttvProdCatId.setExternalProductCatalogId("21141969");
		ttvProductCatalogItem.setProductCatalogueIdentifier(ttvProdCatId);
		
		List<Description> ttvProdDesc = new ArrayList<Description>();
		Description desc3 = new Description();
		desc3.setLocale("EN");
		desc3.setDescriptionText("Essentials2Pack");
		ttvProdDesc.add(desc3);
		ttvProductCatalogItem.setProductCatalogueNameList(ttvProdDesc);
		
		ProductCatalogueIdentifier ttvParentProdCatId = new ProductCatalogueIdentifier();
		ttvParentProdCatId.setExternalProductCatalogId("62");
		ttvParentProdCatId.setProductCatalogueId("101108");
		ttvProductCatalogItem.setParentProductCatalogueIdentifier(ttvParentProdCatId);
		ttvProductCatalogueItemList.add(ttvProductCatalogItem);
		ttvPrComp.setProductCatalogueItemList(ttvProductCatalogueItemList);
		
		ttvProductCompList.add(ttvPrComp);
		
		ProductComponent ttvFavPrComp = new ProductComponent();
		List<ProductCatalogueItem> ttvFavProductCatalogueItemList = new ArrayList<ProductCatalogueItem>();
		ProductCatalogueItem ttvFavProductCatalogItem = new ProductCatalogueItem();
		ProductCatalogueIdentifier ttvFavProdCatId = new ProductCatalogueIdentifier();
		ttvFavProdCatId.setProductCatalogueId("1218010");
		ttvFavProdCatId.setExternalProductCatalogId("21140889");
		ttvFavProductCatalogItem.setProductCatalogueIdentifier(ttvProdCatId);
		List<Description> ttvFavProdDesc = new ArrayList<Description>();
		Description desc4 = new Description();
		desc4.setLocale("EN");
		desc4.setDescriptionText("FavouritesPlusMovies");
		ttvFavProdDesc.add(desc4);
		ttvFavProductCatalogItem.setProductCatalogueNameList(ttvFavProdDesc);
		
		ProductCatalogueIdentifier ttvFavParentProdCatId = new ProductCatalogueIdentifier();
		ttvFavParentProdCatId.setExternalProductCatalogId("66992");
		ttvFavParentProdCatId.setProductCatalogueId("705787");
		ttvFavProductCatalogItem.setParentProductCatalogueIdentifier(ttvFavParentProdCatId);
		ttvFavProductCatalogueItemList.add(ttvFavProductCatalogItem);
		ttvFavPrComp.setProductCatalogueItemList(ttvFavProductCatalogueItemList);
		
		ttvProductCompList.add(ttvFavPrComp);

		ttvOfferProdSummary.setProductComponentList(ttvProductCompList);
		ttvOfferProdSummary.setProductCatalogSystemId("10289");
		
		ProductCatalogueIdentifier ttvMainProdCatId = new ProductCatalogueIdentifier();
		ttvMainProdCatId.setProductCatalogueId("101108");
		ttvMainProdCatId.setExternalProductCatalogId("62");
		
		ttvOfferProdSummary.setMainComponentIdentifier(ttvMainProdCatId);
		
		wirelineOfferProductSummaryList.add(ttvOfferProdSummary);
		
		offerProdSummary.setWirelineOfferProductSummaryList(wirelineOfferProductSummaryList);
		
		entOfferSummary.setOfferProductSummary(offerProdSummary);
		
		salesOfferSummaryList.add(entOfferSummary);
		
		result.setSalesOfferSummaryList(salesOfferSummaryList);
		
		return result;
	}
	
	}
