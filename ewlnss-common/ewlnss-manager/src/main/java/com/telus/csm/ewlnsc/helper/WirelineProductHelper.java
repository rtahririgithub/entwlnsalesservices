package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.MTM_TERM;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;


import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductCharacteristicValue;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOffering;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOfferingPrice;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.adapter.scis.domain.AddressRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.AddressVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.EquipmentInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.FeatureInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.InternetInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.PackInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.PromotionInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.PromotionVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.EquipmentList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.InternetComponent;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.FeaturesPackList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackIdList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.TollPlanList.TollPlan;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.EquipmentAcquisitionTypeVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.PriceDiscountVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductItemIdentifierVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.AssociatedProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.product.FFHEquipmentItemVO;
import com.telus.csm.ewlnsc.domain.product.MarketOfferClassificationVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemHierarchyDO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.TtvComponent;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscriptionNumberInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.TvInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.WirelineComponentVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SalesProductLineVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SingleLineInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Offer;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;



public class WirelineProductHelper {

	private static final String CLASS_NAME      = "WirelineProductHelper";
	private static final LoggerUtil logger = LoggerUtil.getLogger(WirelineProductHelper.class);
	static final String SERVICE_TYPE_HIGH_SPEED_COMPONENT = "ADSL";
	static final String SERVICE_TYPE_HIGH_SPEED_PACK = "ADSP";
	
	protected static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	protected static final String SIMPLE_DATE_FORMAT2 = "yyyy/MM/dd HH:mm:ss";

	protected static final String PROPERTY_NAME_LISTING_TYPE = "Listing Type";
	protected static final String PROPERTY_NAME_LISTED = "Listed";
	protected static final String PROPERTY_NAME_PACKS = "Packs";
	protected static final String PROPERTY_NAME_COMBOS = "combos";
	protected static final String PROPERTY_NAME_EQUIPMENT = "equipment";
	protected static final String PROPERTY_NAME_PRODUCTINFORMATION = "productInformation";
	protected static final String PROPERTY_NAME_PACKID="packID";
	protected static final String PROPERTY_VALUE_ESSENTIALS="-1";
	protected static final String PROPERTY_NAME_PRIMARY_PRICE_PLAN = "primary price plan";

	protected static final String COMMITMENT_PERIOD_IN_YEARS = "Commitment Period (in years)";
	protected static final String COMMITMENT_PERIOD_IN_YEARS_2 = "commitmentPeriodInYears";
	protected static final String SERVICE_REQUIRED_DATE = "ServiceRequiredDate";
	
	private static  ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();

	//HSIC and TTV products are required to reference single line TN (primary)
	//  - HSIC product refers to SING product in ProductReference[]
	//  - TTV product refers to HSIC product in ProductReference[]
	/** The single line product instance id to tn map. */
	private HashMap<String, SubscriptionNumberList> singleLineProductInstanceIdToTNMap = new HashMap<String, SubscriptionNumberList>();

	
	   // OMS product
    /** The Constant OMS_PRODUCT_SING. */
    public final static String OMS_PRODUCT_SING = "SING";

    /** The Constant OMS_PRODUCT_TTV. */
    public final static String OMS_PRODUCT_TTV = "TTV";

    /** The Constant OMS_PRODUCT_STV. */
    public final static String OMS_PRODUCT_STV = "STV";

    /** The Constant OMS_PRODUCT_HSIC. */
    public final static String OMS_PRODUCT_HSIC = "HSIC";
    
    /** The Constant SALES_PACK_HS_ZERO_NAME. */
    public final static String SALES_PACK_HS_ZERO_NAME = "HS0";
//
    /** The Constant SALES_PACK_HS_INTERNET_NAME. */
    public final static String SALES_PACK_HS_INTERNET_NAME = "HSR";
//
//    /** The Constant SALES_PACK_HS_LITE_NAME. */
//    public final static String SALES_PACK_HS_LITE_NAME = "High Speed Lite";
//
//    /** The Constant SALES_PACK_HS_TURBO25_NAME. */
//    public final static String SALES_PACK_HS_TURBO25_NAME = "High Speed Turbo 25";
//
    /** The Constant SALES_PACK_HS_ENHANCED_NAME. */
    public final static String SALES_PACK_HS_ENHANCED_NAME = "HSEN";
//
//    /** The Constant SALES_PACK_HS_OPTIK_TURBO_NAME. */
//    public final static String SALES_PACK_HS_OPTIK_TURBO_NAME = "TELUS Internet 25";
//
    /** The Constant SALES_PACK_HS_EXTREME_NAME. */
    public final static String SALES_PACK_HS_EXTREME_NAME = "HSEX";

    /** The Constant SALES_PACK_HS_TURBO50_NAME. */
    public final static String SALES_PACK_HS_TURBO50_NAME = "HST50";

    /** The Constant SALES_PACK_HS_OPTIK_TURBO50_NAME. */
    public final static String SALES_PACK_HS_OPTIK_TURBO50_NAME = "HSOPT50";

  //2018 April Exception
  	public final static String PROPERTY_NAME_REF_SERVICE_ID = "refServiceId";
  	
  	public final static String PROPERTY_NAME_PRODUCT_DEPOSIT_AMOUNT = "productDepositAmount";
  	
  	public final static String PROPERTY_NAME_SERVICE_ID = "serviceId";
	public final static String PROPERTY_NAME_EXTERNAL_ORDER_SOURCE = "externalRefSrc";
	public final static String PROPERTY_NAME_EXTERNAL_ORDER_ID = "externalRefNum";
	public final static String PROPERTY_NAME_OFFER_CATALOG_ID = "offerCatalogId";
	public final static String PROPERTY_NAME_SERVICE_INSTANCE_ID = "serviceInstanceId";
	
    /** The Constant salesProducttoProductLineMap. */
    public static HashMap<String, String> salesProducttoProductLineMap;

    /** The Constant salesProducttoOMSProductMap. */
    public static HashMap<String, String> salesProducttoOMSProductMap;

    /** The Constant OMSProducttoSalesProductMap. */
    public static final HashMap<String, String> OMSProducttoSalesProductMap;
 
    /** The Constant OMSHighSpeedPackCIDtoSalesProductMap. */
    public static HashMap<String, String> OMSHighSpeedPackCIDtoSalesProductMap;
    static {
    	
    }

    /** The Constant defaultSalesProductHighSpeedRankMap. */
    public static HashMap<String, Long> defaultSalesProductHighSpeedRankMap;

    /** The Constant highSpeedDowngradeExceptionMap. */
    public static HashMap<String, String> highSpeedDowngradeExceptionMap;

    /** The Constant defaultTtvProfileRankMap. */
    public static HashMap<String, Long> defaultTtvProfileRankMap;

    public static Hashtable<String, String> internetProductTypeTable;

    /** The Constant productTypeAndProductLineToSalesProductMap. */
    public static HashMap<String, String> productTypeAndProductLineToSalesProductMap;
    
    /** The Constant productTypeAndProductLineToSalesProductMap. */
    public static HashMap<String, String> productOfferCategoryMap;

	/** The Constant wssProductToProductTierMap. */
    public static HashMap<String, String> wssProductToProductTierMap;

    // Sales product type
    /** The Constant SALES_PRODUCT_TYPE_HOME. */
    public final static String SALES_PRODUCT_TYPE_HOME = "SL";

    /** The Constant SALES_PRODUCT_TYPE_SAT_TV. */
    public final static String SALES_PRODUCT_TYPE_SAT_TV = "STV";

    /** The Constant SALES_PRODUCT_TYPE_OPT_TV. */
    public final static String SALES_PRODUCT_TYPE_OPT_TV = "TTV";

    public final static String SALES_PRODUCT_TYPE_SINGLE_LINE = "SING";
    
    private final static CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();

    
	static Map<String, String> productCatalogMap = new HashMap<String, String>();
	static {
		productCatalogMap.put(SALES_PRODUCT_TYPE_SINGLE_LINE, "65");
		productCatalogMap.put(OMS_PRODUCT_HSIC, "51285");
		productCatalogMap.put(SALES_PRODUCT_TYPE_OPT_TV, "111");
		productCatalogMap.put(SALES_PRODUCT_TYPE_SAT_TV, "62754");
	}
   static {
	    OMSProducttoSalesProductMap = new HashMap<String, String>();
	    OMSProducttoSalesProductMap.put(OMS_PRODUCT_SING, SALES_PRODUCT_TYPE_HOME);
	    OMSProducttoSalesProductMap.put(OMS_PRODUCT_STV, SALES_PRODUCT_TYPE_SAT_TV);
	    OMSProducttoSalesProductMap.put(OMS_PRODUCT_TTV, SALES_PRODUCT_TYPE_OPT_TV);
	    OMSProducttoSalesProductMap.put(OMS_PRODUCT_HSIC, OMS_PRODUCT_HSIC);
    }
	public SubscribedProductSummaryVO populateSubcribedProductFromOMSProductInstance(ProductOrderItem productOrderItem, long sessionId)  {
		String methodName = "*" + sessionId + "* " + "populateSubcribedProductFromOMSProductInstance";

		if( productOrderItem==null || productOrderItem.getProduct() == null ) {
			LoggerUtil.getLogger(this).warn(methodName, "WARN: Ignore product without complete info");
			return null;
		}
		
		Product product = productOrderItem.getProduct();

		SubscribedProductSummaryVO subscribedProductSummary = new SubscribedProductSummaryVO();

		String productInstanceId = product.getProductSerialNumber();
		
		subscribedProductSummary.setServiceAddress(getServiceAddress(product));

		String omsProductType = product.getName();
		if( StringUtils.isEmpty(omsProductType) ) {
			LoggerUtil.getLogger(this).warn(methodName, "WARN: Ignore product without serviceType definition. Product instance = " + productInstanceId);
			return null;
		}

		String productDescription = product.getDescription();

		String productType = OMSProducttoSalesProductMap.get(omsProductType);
		if( StringUtils.isEmpty(productType) ) {
			LoggerUtil.getLogger(this).warn(methodName, "WARN: Ignore product without supported serviceType definition. Unresolved oms product type = " + omsProductType + "/ product instance = " + productInstanceId);
			return null;
		}

		if( OMS_PRODUCT_HSIC.equalsIgnoreCase(omsProductType) ) {
			productType = getProductTypeByCid(getHiSpeedPackCatalogId(product.getProductComponents()));
			if( productType == null ) {
				LoggerUtil.getLogger(this).warn(methodName, "WARN: Invalid assigned product data for unresolved high speed pack cid. Default it to " + SALES_PACK_HS_INTERNET_NAME +". Product instance = " + productInstanceId);
				productType = SALES_PACK_HS_INTERNET_NAME;
			}
		}
        
		String productLine = this.getProductLineByProductType(productType);

		subscribedProductSummary.setServiceType(product.getServiceType());
		subscribedProductSummary.setProductType(productType);
		subscribedProductSummary.setRecurringPayChannelNumber(product.getPayChannelId());

		if (productLine != null) {
			subscribedProductSummary.setProductLine(SalesProductLineVO.fromValue(productLine));
		}

		ProductInstance productInstance = new ProductInstance();
		subscribedProductSummary.setProductInstance(productInstance);

		productInstance.setProductInstanceId(productInstanceId);
		productInstance.setProductName(productDescription);
		productInstance.setServiceId(getServiceId(product));
		productInstance.setProductCatalogId(productCatalogMap.get(omsProductType));
		productInstance.setOfferCatalogId(getOfferCatalogId(product));
		productInstance.setServiceInstanceId(getServiceInstanceId(product));
		productInstance.setDepositAmmount(getDepositAmount(product));
		productInstance.setProductSuppressionInd(setProductSuppressionIndicator(omsProductType,productType));
		productInstance.setTermCd(getTermCode(product));

		List<PromotionVO> promotionList = new ArrayList<PromotionVO>();
		
		recursivelyAddPromotions(product, promotionList);

		setProductComponentInformation(product, subscribedProductSummary,promotionList);

		// populate commitment start date
		if (product.getProductComponents() != null ) {
			for (Product component : product.getProductComponents()) {
				List<ProductCharacteristicValue> productCharacteristicsList = component.getProductCharacteristics();
				if (productCharacteristicsList != null) {
					for (ProductCharacteristicValue charValue : productCharacteristicsList) {
						String strDate = null;
						String name = charValue.getName();
						if (SERVICE_REQUIRED_DATE.equalsIgnoreCase(name)) {
							strDate = charValue.getValue();
							if (strDate != null) {

								// format the string date, eg 2015-10-23T06:00:00Z
								strDate = strDate.replace('T', ' ');
								if (strDate.endsWith("Z")) {
									strDate = strDate.substring(0, strDate.length() - 1);
								}

								DateFormat df = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
								XMLGregorianCalendar xmlDate = null;

								try {
									Date date = df.parse(strDate);

									GregorianCalendar c = new GregorianCalendar();
									c.setTime(date);
									xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
								}
								catch (Exception e) {
									continue;
								}

								productInstance.setCommitmentPeriodStartDate(xmlDate);
								break;
							}
						}
					}
				}
			}
		}

		
		return subscribedProductSummary;
	}
	/**
	 * 
	 * @param product
	 * @param sessionId
	 * @return
	 * @throws ServiceException
	 */
	public SubscribedProductSummaryVO populateSubcribedProductFromOMSProductList(Product product, long sessionId)  {
		String methodName = "*" + sessionId + "* " + "populateSubcribedProductFromOMSProductInstance";

		if( product==null) {
			LoggerUtil.getLogger(this).warn(methodName, "WARN: Ignore product without complete info");
			return null;
		}

		SubscribedProductSummaryVO subscribedProductSummary = new SubscribedProductSummaryVO();

		String productInstanceId = product.getProductSerialNumber();

		subscribedProductSummary.setServiceAddress(getServiceAddress(product));

		String omsProductType = product.getName();
		if( StringUtils.isEmpty(omsProductType) ) {
			LoggerUtil.getLogger(this).warn(methodName, "WARN: Ignore product without serviceType definition. Product instance = " + productInstanceId);
			return null;
		}

		String productDescription = product.getDescription();

		String productType = OMSProducttoSalesProductMap.get(omsProductType);
		if( StringUtils.isEmpty(productType) ) {
			LoggerUtil.getLogger(this).warn(methodName, "WARN: Ignore product without supported serviceType definition. Unresolved oms product type = " + omsProductType + "/ product instance = " + productInstanceId);
			return null;
		}

		if( OMS_PRODUCT_HSIC.equalsIgnoreCase(omsProductType) ) {
			productType = getProductTypeByCid(getHiSpeedPackCatalogId(product.getProductComponents()));
			if( productType == null ) {
				LoggerUtil.getLogger(this).warn(methodName, "WARN: Invalid assigned product data for unresolved high speed pack cid. Default it to " + SALES_PACK_HS_INTERNET_NAME +". Product instance = " + productInstanceId);
				productType = SALES_PACK_HS_INTERNET_NAME;
			}
		}
		String productLine = this.getProductLineByProductType(productType);

		if (productLine != null) {
			subscribedProductSummary.setProductLine(SalesProductLineVO.fromValue(productLine));
		}
		subscribedProductSummary.setServiceType(product.getServiceType());
		subscribedProductSummary.setProductType(productType);
		subscribedProductSummary.setRecurringPayChannelNumber(product.getPayChannelId());

		

		ProductInstance productInstance = new ProductInstance();
		subscribedProductSummary.setProductInstance(productInstance);

		//HSIC/main component id for HSIC product might be stored in CODS
		if( OMS_PRODUCT_HSIC.equalsIgnoreCase(omsProductType) ) {
			String id= getHighSpeedComponentInstanceId(product);
			if (id !=null)
				productInstance.setProductInstanceId(productInstanceId + "," + id );
			else {
				productInstance.setProductInstanceId(productInstanceId);
			}
		} else {
			productInstance.setProductInstanceId(productInstanceId);
		}

		productInstance.setProductName(productDescription);
		productInstance.setServiceId(getServiceId(product));
		productInstance.setProductCatalogId(productCatalogMap.get(omsProductType));
		productInstance.setOfferCatalogId(getOfferCatalogId(product));
		productInstance.setServiceInstanceId(getServiceInstanceId(product));
		productInstance.setDepositAmmount(getDepositAmount(product));
		productInstance.setProductSuppressionInd(setProductSuppressionIndicator(omsProductType,productType));
		productInstance.setTermCd(getTermCode(product));
		
		//2018 April Exception
		productInstance.setRefServiceId(getRefServiceId(product));

		List<PromotionVO> promotionList = new ArrayList<PromotionVO>();
		
		recursivelyAddPromotions(product, promotionList);
		setProductComponentInformation(product, subscribedProductSummary,promotionList);

		productInstance.setCommitmentPeriodStartDate(getCommitmentPeriodStartDate(product));
		
		

		return subscribedProductSummary;
	}
	
	private void recursivelyAddPromotions(Product product, List<PromotionVO> promotionList) {
		if (product == null) {
			return;
		}
		
		List<ProductOffering> offerings = product.getProductOffering();
		
		if (offerings != null) {
			for (ProductOffering offering : offerings) {
				if (offering == null) {
					continue;
				}
				
				List<ProductOfferingPrice> productOfferingPriceList = offering.getProductOfferingPrice();
				if (productOfferingPriceList != null) {
					for (ProductOfferingPrice productOfferingPrice : productOfferingPriceList) {
						if (productOfferingPrice != null) {
							String amount = productOfferingPrice.getAmount();
							if (amount != null && amount.startsWith("-") && offering.getCatalogId() != null) {// this checks for negative amount which will be considered for promotion list
								PromotionVO promotionVO = new PromotionVO();
								promotionVO.setPromotionId(offering.getCatalogId());
								promotionVO.setPromotionName(offering.getName());
								if (!promotionList.contains(promotionVO)) {
									promotionList.add(promotionVO);
								}
								break;
							}
						}
					}
				}
			}
		}
		
		List<Product> components = product.getProductComponents();
		
		if (components != null) {
			for (Product component : components) {
				recursivelyAddPromotions(component, promotionList);
			}
		}
	}

	private XMLGregorianCalendar getCommitmentPeriodStartDate(Product product) {
		String productType = product.getName();
		List<Product> components = product.getProductComponents();
		if(OMS_PRODUCT_HSIC.equalsIgnoreCase(productType)){
			if(components!=null && !components.isEmpty()){
				String commitmentStartDate = null;
				for (Product component : components) {
					if ("hsPack".equalsIgnoreCase(component.getName()) && StringUtils.isEmpty(commitmentStartDate)) {// fix OR3 defect 59473
						List<Product> subCom = component.getProductComponents();
						if (subCom != null && subCom.size() > 0) {
							for (Product p : subCom) {
								if (p.getProductCharacteristics() != null && p.getProductCharacteristics().size() > 0) {
									for (ProductCharacteristicValue charValue : p.getProductCharacteristics()) {
										if ("commitmentStartDate".equalsIgnoreCase(charValue.getName())) {
											if (!StringUtils.isEmpty(charValue.getValue())) {
												commitmentStartDate = charValue.getValue();
											}
										}
									}
								}
							}
						}
					}
					if ("highSpeedComponent".equalsIgnoreCase(component.getName())) {
						List<Product> subCom = component.getProductComponents();
						if (subCom != null && subCom.size() > 0) {
							for (Product p : subCom) {
								if (p.getProductCharacteristics() != null && p.getProductCharacteristics().size() > 0) {
									for (ProductCharacteristicValue charValue : p.getProductCharacteristics()) {
										if ("commitmentStartDate".equalsIgnoreCase(charValue.getName())) {
											if (!StringUtils.isEmpty(charValue.getValue())) {
												commitmentStartDate = charValue.getValue();
											}
										}
									}
								}
							}
						}
					}
				}
				if (!StringUtils.isEmpty(commitmentStartDate)) { // fix OR3 defect 59473
					return parseDate(commitmentStartDate);
				}
			}
		}else{
			if (components != null && components.size() > 0) {
				for (Product component : components) {
					if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if ("commitmentStartDate".equalsIgnoreCase(charValue.getName())) {
								if (!StringUtils.isEmpty(charValue.getValue())) {
									return parseDate(charValue.getValue());
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	private XMLGregorianCalendar parseDate(String value){
		DateFormat df = new SimpleDateFormat(SIMPLE_DATE_FORMAT2);
		try {
			Date date = df.parse(value);

			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		}
		catch (Exception e) {
			return null;
		}
	}
	private String getHighSpeedComponentInstanceId(Product product) {
		List<Product> products = product.getProductComponents();
		if (products!=null) {
			for (Product subProduct : products) {
				if ("highSpeedComponent".equals(subProduct.getName())) {
					List<Product> subCom = subProduct.getProductComponents();
					if(subCom!=null && !subCom.isEmpty()){
						for(Product s : subCom){
							if(SERVICE_TYPE_HIGH_SPEED_COMPONENT.equalsIgnoreCase(s.getServiceType())){
								return s.getProductSerialNumber();
							}
						}
					}
				}
			}
		}
		return null;
	}

	private String getServiceId(Product product) {
		List<Product> components = product.getProductComponents();
		if (components != null && components.size() > 0) {
			for (Product component : components) {
					if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
						if ("generalProductInfo".equalsIgnoreCase(component.getName())) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (charValue != null && PROPERTY_NAME_SERVICE_ID.equalsIgnoreCase(charValue.getName())) {
								return charValue.getValue();
							}
						}
					}
				}
			}
		}

		return null;
	}

	private String getHiSpeedPackCatalogId(List<Product> productComponents){
		if(productComponents!=null){
			for(Product p : productComponents){
				if("hsPack".equalsIgnoreCase(p.getName())){
					List<Product> subProductComponents = p.getProductComponents();
					if(subProductComponents!=null){
						for(Product sub: subProductComponents){
							if(SERVICE_TYPE_HIGH_SPEED_PACK.equalsIgnoreCase(sub.getServiceType())){
								return sub.getCatalogId();
							}
						}
					}
				}
			}
		}
		return null;
	}

	private String getTermCode(Product product) {
		String productType = product.getName();
		List<Product> components = product.getProductComponents();
		//QC defect 54677
		if(OMS_PRODUCT_HSIC.equalsIgnoreCase(productType)){
			if(components!=null && !components.isEmpty()){
				for (Product component : components) {
					if ("highSpeedComponent".equalsIgnoreCase(component.getName())) {
						List<Product> subCom = component.getProductComponents();
						if (subCom != null && subCom.size() > 0) {
							for (Product p : subCom) {
								if (p.getProductCharacteristics() != null && p.getProductCharacteristics().size() > 0) {
									for (ProductCharacteristicValue charValue : p.getProductCharacteristics()) {
										if (COMMITMENT_PERIOD_IN_YEARS_2.equalsIgnoreCase(charValue.getName())) {
											if (!StringUtils.isEmpty(charValue.getValue())) {
												return charValue.getValue();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}else{
			if (components != null && components.size() > 0) {
				for (Product component : components) {
					if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (COMMITMENT_PERIOD_IN_YEARS_2.equalsIgnoreCase(charValue.getName())) {
								if (!StringUtils.isEmpty(charValue.getValue())) {
									return charValue.getValue();
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	private AddressVO getServiceAddress(Product productInProductOrderItem){
		AddressVO address = new AddressVO();
		List<ProductCharacteristicValue> productCharacteristics = new ArrayList<ProductCharacteristicValue>();
		if(productInProductOrderItem!=null){
			for(Product p : productInProductOrderItem.getProductComponents()){
				if("serviceAddress".equalsIgnoreCase(p.getName())){
					productCharacteristics = p.getProductCharacteristics();
				}
			}
		}
		if(productCharacteristics!=null){
			for(ProductCharacteristicValue p : productCharacteristics){
				if("apartment".equalsIgnoreCase(p.getName())){
					address.setUnit(p.getValue());
				}
				if("streetNumber".equalsIgnoreCase(p.getName())){
					address.setStreetNumber(p.getValue());
				}
				if("streetName".equalsIgnoreCase(p.getName())){
					address.setStreetName(p.getValue());
				}
				if("city".equalsIgnoreCase(p.getName())){
					address.setCity(p.getValue());
				}
				if("state".equalsIgnoreCase(p.getName()) && !StringUtils.isEmpty(p.getValue())){
					address.setProvince(p.getValue());
					address.setProvinceStateCode(p.getValue());
				}
				if("country".equalsIgnoreCase(p.getName())){
					address.setCountry(p.getValue());
				}
				if("postcode".equalsIgnoreCase(p.getName())){
					address.setPostalCode(p.getValue());
				}
				if("fmsAddressId".equalsIgnoreCase(p.getName()) && !StringUtils.isEmpty(p.getValue())){
					address.setAddressId(Long.valueOf(p.getValue()));
				}
				if("vector".equalsIgnoreCase(p.getName())){
					address.setStreetVector(p.getValue());
				}
				if("coId".equalsIgnoreCase(p.getName())){
					address.setCoid(p.getValue());
				}
				if("rateCentre".equalsIgnoreCase(p.getName())){
					address.setRateCenter(p.getValue());
				}
/*				if("ils/blockedAddressRemarks/blockedAddressIndicator/servingSwitch/cityClliCode".equalsIgnoreCase(p.getName())){
					address.set(p.getValue());
				}
*/			}
		}
		return address;
	}

	private String getOfferCatalogId(Product product){
		List<Product> components = product.getProductComponents();
		if (components != null && components.size() > 0) {
			for (Product component : components) {
					if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
						if ("generalProductInfo".equalsIgnoreCase(component.getName())) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (charValue != null && PROPERTY_NAME_OFFER_CATALOG_ID.equalsIgnoreCase(charValue.getName())) {
								return charValue.getValue();
							}
						}
					}
				}
			}
		}
		return null;
	}
	private String getServiceInstanceId(Product product){
		List<Product> components = product.getProductComponents();
		if (components != null && components.size() > 0) {
			for (Product component : components) {
				if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
					if ("generalProductInfo".equalsIgnoreCase(component.getName())) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (charValue != null && PROPERTY_NAME_SERVICE_INSTANCE_ID.equalsIgnoreCase(charValue.getName())) {
								return charValue.getValue();
							}
						}
					}
				}
			}
		}
		return null;
	}
	private Long getDepositAmount(Product product){
		List<Product> components = product.getProductComponents();
		if (components != null && components.size() > 0) {
			for (Product component : components) {
				if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
					if ("generalProductInfo".equalsIgnoreCase(component.getName())) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (charValue != null && PROPERTY_NAME_PRODUCT_DEPOSIT_AMOUNT.equalsIgnoreCase(charValue.getName())&& charValue.getValue()!=null) {
								return Double.valueOf(charValue.getValue()).longValue();
							}
						}
					}
				}
			}
		}
		return null;
	}
	private Boolean setProductSuppressionIndicator(String omsProductType,String productType){
		if (OMS_PRODUCT_HSIC.equalsIgnoreCase(omsProductType)) {
			if (SALES_PACK_HS_ZERO_NAME.equalsIgnoreCase(productType)) {
				return Boolean.TRUE;
			}
		}
		//RWU: missing SING and price plan in test data
		/*if (OMS_PRODUCT_SING.equalsIgnoreCase(omsProductType)) {
			List<com.telus.tmi.xmlschema.xsd.customer.customerorder.ordermgmttypes_v6.PricePlan> pricePlanList = omsProduct.getPricePlanList();
			if (pricePlanList!=null) {
				for (com.telus.tmi.xmlschema.xsd.customer.customerorder.ordermgmttypes_v6.PricePlan plan : pricePlanList) {
					if (plan.getPricePlanHeader()!=null && SING_TTV_NO_LOCAL_PRICE_PLAN_NAME.equalsIgnoreCase(plan.getPricePlanHeader().getName())) {
						associatedProduct.getProductInstance().setProductSuppressionInd(true);
						return;
					}
				}
			}
		}*/
		return Boolean.FALSE;
	}
	private void setIsListed(Product product, SingleLineComponent singleLineComponent) {
		// logic to define SingleLineNumberListedInd is correct?
		List<Product> components = product.getProductComponents();
		if (components != null && components.size() > 0) {
			for (Product component : components) {
				if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
					if (PROPERTY_NAME_PRODUCTINFORMATION.equalsIgnoreCase(component.getName())) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (charValue != null && PROPERTY_NAME_LISTING_TYPE.equalsIgnoreCase(charValue.getName())) {
									if(PROPERTY_NAME_LISTED.equalsIgnoreCase(charValue.getDisplayValue())){
										singleLineComponent.setSingleLineNumberListedInd(true);
										return;
									}else{
										singleLineComponent.setSingleLineNumberListedInd(false);
										return;
									}
							}
						}
					}
				}
			}
		}
	}
	private void populateInternetResourceId(Product product, SubscribedProductSummaryVO associatedProduct) {
		if( product!=null && !StringUtils.isEmpty(product.getResourceID()) ) {
			associatedProduct.getProductInstance().setResourceId(product.getResourceID());
		}
	}
	private void populateResourceId(Product product, SubscribedProductSummaryVO associatedProduct) {
		if( product!=null && !StringUtils.isEmpty(product.getResourceID()) ) {
			associatedProduct.getProductInstance().setResourceId(product.getResourceID());
		}
	}
	private void setProductComponentInformation(Product product, SubscribedProductSummaryVO associatedProduct,List<PromotionVO> promotionList) {
//		if (product.getCatalogItem()==null || product.getCatalogItem().getCatalogItemHeader()==null || product.getCatalogItem().getCatalogItemHeader().getCatalogItemType()==null) {
//			return;
//		}
		String productType = product.getName();

		if (OMS_PRODUCT_SING.equalsIgnoreCase(productType) ) {
			// RWU: added
			populateResourceId(product, associatedProduct);
			populateSingleLineComponents(product, associatedProduct,promotionList);
			// JG: missing
			//populateSingleLineTNReference(product, associatedProduct);
		} else if (OMS_PRODUCT_HSIC.equalsIgnoreCase(productType) ) {
			// RWU: added
			populateInternetResourceId(product, associatedProduct);
			// JG: internet promotions are missing
			populateInternetComponents(product, associatedProduct,promotionList);
			// RWU: added
			populateEquipmentList(product, associatedProduct);
			// JG: missing
			//populateHSICSingleLineTNReference(product, associatedProduct);
		} else if (OMS_PRODUCT_TTV.equalsIgnoreCase(productType) ) {
			// RWU: added
			populateResourceId(product, associatedProduct);
			populateTtvComponents(product, associatedProduct,promotionList);
			// RWU: added
			populateEquipmentList(product, associatedProduct);
			// JG: missing
			//populateTTVSingleLineTNReference(product, associatedProduct);
		} else if (OMS_PRODUCT_STV.equalsIgnoreCase(productType) ) {
			// RWU: added
			populateResourceId(product, associatedProduct);
			// JG: missing
			populateEquipmentList(product, associatedProduct);
		}
	}

	/**
	 * Populate single line components.
	 *
	 * @param omsProduct the oms product
	 * @param associatedProduct the associated product
	 */
	private void populateSingleLineComponents(Product product, SubscribedProductSummaryVO associatedProduct,List<PromotionVO> promotionList) {
		// create SingleLineComponent
		SingleLineComponent singleLineComponent = new SingleLineComponent();
		// set Listing type to SingleLineComponent
		setIsListed(product, singleLineComponent);
		// set SingleLineComponent to Product Instance
		associatedProduct.getProductInstance().setSingleLineComponent(singleLineComponent);

		SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.PromotionList singleLinePromotionList = new SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.PromotionList();

		singleLinePromotionList.setPromotion(promotionList);
		
		SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackList homePhonePackList = new SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackList();
		SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackIdList homePhonePackIdList = new SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.HomePhonePackIdList();
		List<String> homePhonePack = new ArrayList<String>();
		List<String> homePhonePackId = new ArrayList<String>();

		List<Product> components = product.getProductComponents();
		// set SubscriptionNumberList to SingleLineComponent
		SubscriptionNumberList subscriptionNumberList = new SubscriptionNumberList();
		// set Toll Plan to SingleLineComponent
		TollPlanList tollPlanList = new TollPlanList();
		// set calling feature to SingleLineComponent
		CallingFeatureList callingFeatureList = new CallingFeatureList();
		// RWU: added
		if (components!=null) {
			for(Product p : components){
				// home phone pack list
				homePhonePackList.setHomePhonePack(homePhonePack);
				//TODO: Khalil: disabled this line as homePhonePackId is no long supported. I need to follw up with the team
				//homePhonePackList.(homePhonePackId);
				homePhonePackIdList.setHomePhonePackId(homePhonePackId);
				//duplicate SN type found in sample response
				if("slPack".equalsIgnoreCase(p.getName())){
					if(p.getProductComponents()!=null){
						List<Product> subComponents  = p.getProductComponents();
						for (Product subProduct : subComponents) {
							if("SFPK".equalsIgnoreCase(subProduct.getServiceType())){
								homePhonePack.add(subProduct.getName());
								homePhonePackId.add(subProduct.getCatalogId());
							}
						}
					}
				}
				if("primarySubscriptionNumber".equalsIgnoreCase(p.getName())){
					List<ProductCharacteristicValue> productCharacteristics = p.getProductCharacteristics();
					if(productCharacteristics!=null){
						for(ProductCharacteristicValue charValue : productCharacteristics){
							if("subscriptionNumber".equalsIgnoreCase(charValue.getName())){
								SubscriptionNumber subscriptionNumber = new SubscriptionNumber();
								subscriptionNumber.setCode(p.getDescription());
								subscriptionNumber.setPhoneNumber(charValue.getValue());
								subscriptionNumberList.getSubscriptionNumber().add(subscriptionNumber);
								singleLineComponent.setSubscriptionNumberList(subscriptionNumberList);
								break;
							}
						}
					}
				}
				if(p.getProductComponents()!=null){
					List<Product> subComponents  = p.getProductComponents();
					if("tollPlans".equalsIgnoreCase(p.getName())){
						for(Product subProduct : subComponents){
							TollPlan tollPlan = new TollPlan();
							tollPlan.setTollPlanName(subProduct.getName());
							tollPlan.setTollCategoryName(p.getDescription());
							tollPlanList.getTollPlan().add(tollPlan);
							singleLineComponent.setTollPlanList(tollPlanList);
						}
					}
					if("callingFeatures".equalsIgnoreCase(p.getName())){
						for(Product subProduct : subComponents){
							CallingFeature feature = new CallingFeature();
							//hard code since not found similar one
							feature.setCallingFeatureCode("Calling Features");
							feature.setCallingFeatureName(subProduct.getName());
							//TODO: khalil disabled this code as setCallingFeatureId is not supported anymore. need to follow up with the team
							//hongbo added: recover it because of requirement need
							feature.setCallingFeatureId(subProduct.getCatalogId());
							callingFeatureList.getCallingFeature().add(feature);
							singleLineComponent.setCallingFeatureList(callingFeatureList);
						}
					}

				}
			}
		}
		singleLineComponent.setHomePhonePackList(homePhonePackList);
		singleLineComponent.setPromotionList(singleLinePromotionList);
		singleLineComponent.setHomePhonePackIdList(homePhonePackIdList);
	}

	private void populateInternetComponents(Product product, SubscribedProductSummaryVO associatedProduct,List<PromotionVO> promotionList) {
		InternetComponent internetComponent = new InternetComponent();
		associatedProduct.getProductInstance().setInternetComponent(internetComponent);

		SubscribedProductSummaryVO.ProductInstance.InternetComponent.PromotionList internetPromotionList = new SubscribedProductSummaryVO.ProductInstance.InternetComponent.PromotionList();

		List<Product> components = product.getProductComponents();
		// RWU: added
		internetPromotionList.setPromotion(promotionList);

		internetComponent.setInternetTypeCd(getInternetTypeCd(components));

		internetComponent.setPromotionList(internetPromotionList);
		
		populateEquipmentsAddOnPacks(internetComponent, product );
	}
	/**
	 * 
	 * @param product
	 * @return
	 */
	//AddOnPack element is is misisng in ESS. need to follow up with the team
	private void populateEquipmentsAddOnPacks(InternetComponent internetComponent, Product product) {
//		List<Product> components = product.getProductComponents();
//		List<AddOnPackVO> addOnPacks = new ArrayList<AddOnPackVO>();
//		if(components!=null && !components.isEmpty()){
//			for (Product component : components) {
//				if ("distributedWifi".equalsIgnoreCase(component.getName())) {
//					List<Product> subCom = component.getProductComponents();
//					if(subCom!=null && !subCom.isEmpty()){
//						for(Product subProduct : subCom){
//							if(subProduct!=null && "INEQ".equalsIgnoreCase(subProduct.getServiceType())){
//								Product parentProduct = getProductByCalatogId(product, subProduct.getParentCatalogId());
//								Product grandParentProduct = getProductByCalatogId(product, parentProduct.getParentCatalogId());
//								if (parentProduct!=null && grandParentProduct!=null) {
//									internetComponent.addAddOnEquipment(subProduct, parentProduct, grandParentProduct);
//								}	
//							}
//						}	
//					}
//				}
//			}
//		}		
	}
	/**
	 * 
	 * @param product
	 * @param productCatalogId
	 * @return
	 */
	private Product getProductByCalatogId(Product product, String productCatalogId){
		if (product == null) return null;
		else if (productCatalogId.equalsIgnoreCase(product.getCatalogId())) {
			return product;
		} else if (product.getProductComponents() == null){
			return null;
		} else {
			for(Product subProduct : product.getProductComponents()){
				Product foundProduct = getProductByCalatogId(subProduct, productCatalogId);
				if (foundProduct != null) {
					return foundProduct;
				}
			}
		}
		return null;
	}
	/**
	 * 
	 * @param components
	 * @return
	 */
	private String getInternetTypeCd(List<Product> components){
		if(components!=null && !components.isEmpty()){
			for (Product component : components) {
				if ("highSpeedComponent".equalsIgnoreCase(component.getName())) {
					List<Product> subCom = component.getProductComponents();
					if(subCom!=null && !subCom.isEmpty()){
						for(Product subProduct : subCom){
							if(subProduct!=null && "High Speed Component".equalsIgnoreCase(subProduct.getName())){
								for(ProductCharacteristicValue charValue: subProduct.getProductCharacteristics()){
									if(charValue!=null && "localServiceProvider".equalsIgnoreCase(charValue.getName())){
										return charValue.getValue();
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * Populate ttv components.
	 *
	 * @param omsProduct the oms product
	 * @param associatedProduct the associated product
	 */
	private void populateTtvComponents(Product product, SubscribedProductSummaryVO associatedProduct,List<PromotionVO> promotionList) {
		TtvComponent ttvComponent = new TtvComponent();
		associatedProduct.getProductInstance().setTtvComponent(ttvComponent);


		// TV pack list, combo list, promotion list
		SubscribedProductSummaryVO.ProductInstance.TtvComponent.PromotionList ttvPromotionList = new SubscribedProductSummaryVO.ProductInstance.TtvComponent.PromotionList();

		SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvPackList ttvPackList = new SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvPackList();
		List<WirelineComponentVO> packList = new ArrayList<WirelineComponentVO>();

		SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvComboList ttvComboList = new SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvComboList();
		List<WirelineComponentVO> comboList = new ArrayList<WirelineComponentVO>();

		boolean flag = false;
		List<Product> omsComponents = product.getProductComponents();
		if( omsComponents!=null ) {
			for (Product component : omsComponents) {
				if (component == null)
					continue;
				// HD flag
				// JG: HD Flag is missing
				// boolean assignedIndicator = isPendingOrder || component.getComponentHeader().isAssignedInd();
				// String componentServiceType = getOMSComponentServiceType(component);
				// if( assignedIndicator && (SERVICE_TYPE_TVPC.equalsIgnoreCase(componentServiceType) || SERVICE_TYPE_TVCG.equalsIgnoreCase(componentServiceType)) ) {
				// String hdFlag = getItemPropertyValue(component, PRODUCT_INSTANCE_TTV_PROPERTY_HDFLAG);
				// if( hdFlag != null && PROPERTY_VALUE_YES.equalsIgnoreCase(hdFlag.trim())) {
				// flag = true;
				// }
				// }

				// ttv pack list and ttv combo list
				ttvPackList.setTvPack(packList);
				ttvComboList.setTvCombo(comboList);
				
				//Ignore equipment incase of pack list and combo list
				if(PROPERTY_NAME_EQUIPMENT.equalsIgnoreCase(component.getName())){
					continue;
				}

				if (component.getName()!=null && component.getName().contains(PROPERTY_NAME_COMBOS)) {
					addChildComoponents(component,comboList);
				} else if (component.getName()!=null && PROPERTY_NAME_PRODUCTINFORMATION.equalsIgnoreCase(component.getName())) {
					addEssentialss(component,comboList);
				}else{
					addChildComoponents(component,packList);
				}

				// ttv promotion list = product offering list
				// RWU: added
				ttvPromotionList.setPromotion(promotionList);
			}
		}

		// HD Channel Ind
		if( flag ) {
			ttvComponent.setHdChannelInd(Boolean.TRUE);
		} else {
			ttvComponent.setHdChannelInd(Boolean.FALSE);
		}

		ttvComponent.setTvPackList(ttvPackList);
		ttvComponent.setTvComboList(ttvComboList);
		ttvComponent.setPromotionList(ttvPromotionList);

	}

	/**
	 * Check if compenent characterstics have packID as -1 if so mean ProductOfferings will added in packlist 
	 * 
	 * @param component
	 * @param packList
	 */
	private void addEssentialss(Product component, List<WirelineComponentVO> packList) {
		if (component != null && component.getProductOffering() != null && !component.getProductOffering().isEmpty() && component.getProductCharacteristics() != null
				&& !component.getProductCharacteristics().isEmpty()) {
			boolean isEssentailsExist = false;
			List<ProductCharacteristicValue> prodChars = component.getProductCharacteristics();
			for (ProductCharacteristicValue prodChar : prodChars) {
				if (prodChar != null && PROPERTY_NAME_PACKID.equalsIgnoreCase(prodChar.getName())) {
					if (PROPERTY_VALUE_ESSENTIALS.equals(prodChar.getValue())) {
						isEssentailsExist = true;
						break;
					}
				}
			}

			if (isEssentailsExist) {
				List<ProductOffering> prodOfferings = component.getProductOffering();
				for (ProductOffering prodOffering : prodOfferings) {
					//Ignore offering name primary price plan
					if(prodOffering != null && prodOffering.getName() != null && !prodOffering.getName().contains(PROPERTY_NAME_PRIMARY_PRICE_PLAN)){
						WirelineComponentVO wirelineComponentVO = new WirelineComponentVO();
						wirelineComponentVO.setComponentId(prodOffering.getCatalogId());
						//In some case description comes as . for those case we take name - this need to validated with OP team .
						wirelineComponentVO.setComponentName((".".equals(prodOffering.getDescription()))? prodOffering.getName() : prodOffering.getDescription());
						packList.add(wirelineComponentVO);
					}
				}
			}
		}
	}

	/**
	 * Add child components to list is any
	 * @param component
	 * @param list
	 */
	private void addChildComoponents(Product component,List<WirelineComponentVO> list) {
		if(component !=null){
			List<Product> childComponents = component.getProductComponents();
			if(childComponents!= null && !childComponents.isEmpty()){
				for (Product childComponent : childComponents) {
					WirelineComponentVO wirelineComponentVO = new WirelineComponentVO();
					wirelineComponentVO.setComponentId(childComponent.getCatalogId());
					wirelineComponentVO.setComponentName(childComponent.getDescription());
					list.add(wirelineComponentVO);
				}
			}
		}
	}

	private void populateEquipmentList(Product product, SubscribedProductSummaryVO associatedProduct) {
		String productType = product.getName();
		List<Equipment> equipmentArrayList = new ArrayList<Equipment>();

		if (OMS_PRODUCT_HSIC.equalsIgnoreCase(productType)) {
			populateHiSpeedEquipment(equipmentArrayList, product);
		}
		if (OMS_PRODUCT_TTV.equalsIgnoreCase(productType)) {
			populateTTVEquipment(equipmentArrayList, product);
		}
		if (OMS_PRODUCT_STV.equalsIgnoreCase(productType)) {
			populateSTVEquipment(equipmentArrayList, product);
		}

		if( equipmentArrayList.size() > 0 ) {
			EquipmentList equipmentList = new EquipmentList();
			equipmentList.getEquipment().clear();
			equipmentList.getEquipment().addAll(equipmentArrayList);
			associatedProduct.getProductInstance().setEquipmentList(equipmentList);
		}
	}

	/**
	 * Populate stv equipment.
	 *
	 * @param equipmentArrayList the equipment array list
	 * @param omsProduct the oms product
	 */
	private void populateSTVEquipment(List<Equipment> equipmentArrayList, Product product) {
		populateTTVEquipment(equipmentArrayList, product, "STV Equipment");
	}

	/**
	 * Populate ttv equipment.
	 *
	 * @param equipmentArrayList the equipment array list
	 * @param omsProduct the oms product
	 */
	private void populateTTVEquipment(List<Equipment> equipmentArrayList, Product product) {
		populateTTVEquipment(equipmentArrayList, product, "TV Equipment");
	}
	
	/**
	 * Populate ttv equipment.
	 *
	 * @param equipmentArrayList the equipment array list
	 * @param omsProduct the oms product
	 */
	private void populateTTVEquipment(List<Equipment> equipmentArrayList, Product product, String equipmentIdName) {
		List<Product> products = product.getProductComponents();
		for (Product component : products) {
			if ("equipment".equalsIgnoreCase(component.getName()) && component.getProductComponents() !=null) {
				for (Product subCom : component.getProductComponents()) {
					if( ( "equipment".equalsIgnoreCase(subCom.getName()) || equipmentIdName.equalsIgnoreCase(subCom.getName()) ) && subCom.getProductCharacteristics()!=null){
						//equipment name = equipment make name + model name
						//acquisition type = most possible value found in sample response
						Equipment equipment = new Equipment();
						for(ProductCharacteristicValue charValue: subCom.getProductCharacteristics()){
							if(charValue!=null && "description".equalsIgnoreCase(charValue.getName())){
								equipment.setEquipmentName(charValue.getDisplayValue());
							}
							if(charValue!=null && "acquisitionType".equalsIgnoreCase(charValue.getName())){
								equipment.setEquipmentAcquisitionType(charValue.getValue());
							}
						}
						equipmentArrayList.add(equipment);
					}
				}
			}
		}
	}
	/**
	 * Populate hi speed equipment.
	 *
	 * @param equipmentArrayList the equipment array list
	 * @param omsProduct the oms product
	 */
	private void populateHiSpeedEquipment(List<Equipment> equipmentArrayList, Product product) {
		List<Product> products = product.getProductComponents();
		for (Product component : products) {
			if ("hsEquipment".equalsIgnoreCase(component.getName()) && component.getProductComponents() !=null) {
				List<Product> subCom = component.getProductComponents();
				if(subCom!=null && !subCom.isEmpty()){
					for(Product subProduct : subCom){
						Equipment equipment = new Equipment();
						equipment.setEquipmentAcquisitionType(subProduct.getServiceType());
						if(subProduct!=null && "Internet Equipment".equalsIgnoreCase(subProduct.getName())){
							for(ProductCharacteristicValue charValue: subProduct.getProductCharacteristics()){
								if(charValue!=null && Long.valueOf("31163").equals(charValue.getCatalogAttributeId())){
									equipment.setEquipmentName(charValue.getValue());
									equipmentArrayList.add(equipment);
									return;
								}
							}
						}

					}
				}
			}
		}

	}
	/**
	 * 
	 * @param product
	 * @return
	 */
	private String getRefServiceId(Product product) {
		List<Product> components = product.getProductComponents();
		if (components != null && components.size() > 0) {
			for (Product component : components) {
					if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
						if ("generalProductInfo".equalsIgnoreCase(component.getName())) {
						for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
							if (charValue != null && PROPERTY_NAME_REF_SERVICE_ID.equalsIgnoreCase(charValue.getName())) {
								return charValue.getValue();
							}
						}
					}
				}
			}
		}

		return null;
	}		
	/**
	 * 
	 * @param subscribedProductList
	 * @return
	 */
	 public static List<SubscribedProductInfoRestVO> transformSubscribedProductListVOToSubscribedProductInfoRestVO(List<SubscribedProductSummaryVO>  subscribedProductList){
	    	if(subscribedProductList==null)
	    		return null;
	    		List<SubscribedProductInfoRestVO> subscribedProductVO = null;         
	          if (subscribedProductList != null && !subscribedProductList.isEmpty())
	          {
	                 subscribedProductVO = new ArrayList<SubscribedProductInfoRestVO>();
	                 for (int i = 0; i < subscribedProductList.size(); i++)
	                 {
	                        SubscribedProductInfoRestVO subscribedProduct= new SubscribedProductInfoRestVO();
	                        SubscribedProductSummaryVO  subscribedProductSummaryVO = subscribedProductList.get(i);
	                        if(subscribedProductSummaryVO==null)
	                        	continue;
	                        subscribedProduct.setOneTimePayChannelNum(subscribedProductSummaryVO.getOneTimePayChannelNumber());
	                        //If product pending status in OMS is TRUE and / or product pending status in Wireline Sales is TRUE, then the field Product in Pending Order Status is set to TRUE.
	                        boolean flag1 = subscribedProductSummaryVO.getProductInPendingOrderStatusInOMSInd()!=null?
	                        		subscribedProductSummaryVO.getProductInPendingOrderStatusInOMSInd():false; 
	                        boolean flag2 = subscribedProductSummaryVO.getProductInPendingOrderStatusInVestaInd()!=null?
	                        		subscribedProductSummaryVO.getProductInPendingOrderStatusInVestaInd():false; 
	                        boolean flag = flag1 ||flag2;
	                        subscribedProduct.setProductInPendingOrderStatusInd(flag);
	                        List<ProductInstanceInfoRestVO> productInstances = new ArrayList<ProductInstanceInfoRestVO>();
	                        productInstances.add(transformProductInstanceToProductInstanceInfoRestVO(subscribedProductSummaryVO.getProductInstance()));
	                        subscribedProduct.setProductInstance(productInstances);
	                        subscribedProduct.setProductLine(subscribedProductSummaryVO.getProductLine()!=null?subscribedProductSummaryVO.getProductLine().value():"");
	                        if ("TTV".equalsIgnoreCase(subscribedProductSummaryVO.getProductType())) {
	                        	 subscribedProduct.setProductTierCd(subscribedProductSummaryVO.getProductType());
	                        } else {
	                        	subscribedProduct.setProductTierCd(mapWSSProductToProductTier(subscribedProductSummaryVO.getProductType()));
	                        }
	                        subscribedProduct.setRecurringPayChannelNum(subscribedProductSummaryVO.getRecurringPayChannelNumber());
	                        subscribedProduct.setServiceAddress(mapAddressRestVO(subscribedProductSummaryVO.getServiceAddress()));
	                        subscribedProduct.setProductTypeCd(subscribedProductSummaryVO.getServiceType());
	                        subscribedProduct.setSubscriptionStatusCd(subscribedProductSummaryVO.getSubscriptionStatus());
	                        subscribedProduct.setPricePlanId(subscribedProductSummaryVO.getPricePlanIdList());
	               
	                        subscribedProductVO.add(subscribedProduct);
	                 }    
	          }
	     return subscribedProductVO;
	    }
	 /**
	  * 
	  * @param wssProduct
	  * @return
	  */
		protected static String mapWSSProductToProductTier(String wssProduct)
		{
			Map<String,String> wssProductTier = refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER);

			String productType = "";
	    		    	if (wssProductTier!= null && wssProductTier.keySet().contains(wssProduct)) {
	    		productType = wssProductTier.get(wssProduct);
	    	}
	    	
	    	return productType;
		}
	 /**
	  * 
	  * @param vo
	  * @return
	  */
		private static AddressRestVO mapAddressRestVO(AddressVO vo)
		{
			if(vo == null)
				return null;
			AddressRestVO restVO = new AddressRestVO();
			
			restVO.setStreetNum(vo.getStreetNumber());
			restVO.setStreetName(vo.getStreetName());
			restVO.setUnitTypeCd(vo.getUnitTypeCode());
			restVO.setUnitNum(vo.getUnitNumber());
			restVO.setCity(vo.getCity());
			restVO.setProvinceCd(vo.getProvinceStateCode());
			restVO.setCountryCd(vo.getCountryCode());
			restVO.setPostalCd(vo.getPostalCode());
			restVO.setAddressTypeCd(vo.getAddressTypeCode());
			restVO.setPoBoxStationName(vo.getPoBox());;
			restVO.setPoBoxNum(vo.getPostOfficeBoxNumber());
			restVO.setRrNum(vo.getRuralRouteNumber());
			restVO.setServiceAddressId(vo.getServiceAddressId());
			return restVO;
		}
		/**
		 * 
		 * @param productInstance
		 * @return
		 */
	   private static ProductInstanceInfoRestVO transformProductInstanceToProductInstanceInfoRestVO(SubscribedProductSummaryVO.ProductInstance productInstance){
	    	if(productInstance==null)
	    		return null;
	          ProductInstanceInfoRestVO productInstanceInfoRestVO = new ProductInstanceInfoRestVO();
	          productInstanceInfoRestVO.setCommitmentPeriodStartDt(toDateStr(productInstance.getCommitmentPeriodStartDate()));
	          
	          if(productInstance.getDepositAmmount() != null){
	        	  productInstanceInfoRestVO.setDepositAmt((double) productInstance.getDepositAmmount());  
	          } else {
	        	  productInstanceInfoRestVO.setDepositAmt(0.0);
	          }
	           productInstanceInfoRestVO.setIncludedForDepositCalcInd(productInstance.getIncludedForDepositCalculationInd());
	           productInstanceInfoRestVO.setOmsOfferCatalogId(productInstance.getOfferCatalogId());
	           productInstanceInfoRestVO.setProductCatalogId(productInstance.getProductCatalogId());
	           productInstanceInfoRestVO.setProductInstanceId(productInstance.getProductInstanceId());
	           productInstanceInfoRestVO.setProductSuppressionInd(productInstance.getProductSuppressionInd());
	           productInstanceInfoRestVO.setProductName(productInstance.getProductName());
	           productInstanceInfoRestVO.setResourceId(productInstance.getResourceId());
	           productInstanceInfoRestVO.setServiceId(productInstance.getServiceId());
	           productInstanceInfoRestVO.setServiceInstanceId(productInstance.getServiceInstanceId());
	           productInstanceInfoRestVO.setTermCd(productInstance.getTermCd());    
	           productInstanceInfoRestVO.setSingleLineComponent(mapSingleLineComponent(productInstance.getSingleLineComponent()));
	           productInstanceInfoRestVO.setInternetComponent(mapInternetComponent(productInstance.getInternetComponent()));
	           productInstanceInfoRestVO.setTtvComponent(mapTtvComponent(productInstance.getTtvComponent()));
	           productInstanceInfoRestVO.setEquipmentList(mapEquipmentList(productInstance.getEquipmentList()));
	           
	           //2018 April Exception Release
	           //TODO : Khalil setRefServiceId was added in April 2018 for CSS but available in ESS.
	           //productInstanceInfoRestVO.setRefServiceId(productInstance.getRefServiceId());
	          return productInstanceInfoRestVO;
	    }	 
	   /**
	    * 
	    * @param vo
	    * @return
	    */
	   private static SingleLineInfoRestVO mapSingleLineComponent(SingleLineComponent vo)
	    {
	    	if(vo== null)
	    		return null;
	    	SingleLineInfoRestVO restVO = new SingleLineInfoRestVO();
	    	restVO.setWholesaleAdslInd(vo.getWholesaleAdslInd());
	    	restVO.setSingleLineNumListedInd(vo.getSingleLineNumberListedInd());
	    	restVO.setSubscriptionNumList(mapSubscriptionNumberList(vo.getSubscriptionNumberList()));
	    	restVO.setCallingFeatureList(mapCallingFeatureList(vo.getCallingFeatureList()));
	    	restVO.setFeaturesPackList(mapFeaturesPackList(vo.getFeaturesPackList()));
	    	restVO.setHomePhonePackList(mapHomePhonePackList(vo.getHomePhonePackList()));
	    	//TODO: Khalil to fix this later
	    	restVO.setHomePhonePackIdList(mapHomePhonePackIdList(vo.getHomePhonePackIdList()));
	    	restVO.setTollPlanList(mapTollPlanList(vo.getTollPlanList()));
	    	restVO.setPromotionList(mapPromotionList(vo.getPromotionList()));
	    	
	    	return restVO;
	    }
	   /**
	    * 
	    * @param promotionList
	    * @return
	    */
	   private static List<PromotionInfoRestVO> mapPromotionList(com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.PromotionList promotionList)
	    {
	    	if( promotionList ==null)
	    		return null;
	    	ArrayList<PromotionInfoRestVO> restVoList = new ArrayList<PromotionInfoRestVO>();
	    	if( promotionList.getPromotion()!= null)
	    	{
		    	for(int i=0; i<promotionList.getPromotion().size(); i++)
		    	{
		    		if(promotionList.getPromotion().get(i)==null)
		    			continue;
		    	
		    		PromotionInfoRestVO restvo = new PromotionInfoRestVO();
		    		restvo.setPromotionId(promotionList.getPromotion().get(i).getPromotionId());
		    		restvo.setPromotionName(promotionList.getPromotion().get(i).getPromotionName());
		    		restVoList.add(restvo);
		    	}
	    	}
	    	return restVoList;
	    }
	
	   /**
	    * 
	    * @param list
	    * @return
	    */
	   private static List<PromotionInfoRestVO> mapPromotionList3(SubscribedProductSummaryVO.ProductInstance.TtvComponent.PromotionList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<PromotionInfoRestVO> restVoList = new ArrayList<PromotionInfoRestVO>();
	    	if( list.getPromotion()!= null)
	    	{
		    	for(int i=0; i<list.getPromotion().size(); i++)
		    	{
		    		if(list.getPromotion().get(i)==null)
		    			continue;
		    	
		    		PromotionInfoRestVO restvo = new PromotionInfoRestVO();
		    		restvo.setPromotionId(list.getPromotion().get(i).getPromotionId());
		    		restvo.setPromotionName(list.getPromotion().get(i).getPromotionName());
		    		restVoList.add(restvo);
		    	}
	    	}
	    	return restVoList;
	    }	   
	   /**
	    * 
	    * @param list
	    * @return
	    */
	   private static List<FeatureInfoRestVO> mapCallingFeatureList(CallingFeatureList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<FeatureInfoRestVO> restVoList = new ArrayList<FeatureInfoRestVO>();
	    	if( list.getCallingFeature()!= null)
	    	{
		    	for(int i=0; i<list.getCallingFeature().size(); i++)
		    	{
		    		if(list.getCallingFeature().get(i)==null)
		    			continue;
		    		FeatureInfoRestVO restVo = new FeatureInfoRestVO();
		    		restVo.setCategoryName(list.getCallingFeature().get(i).getCallingFeatureCode());
		    		restVo.setName(list.getCallingFeature().get(i).getCallingFeatureName());
		    		//TODO: Khalil ESS logic doesn't have id defined 
		    		//hongbo added - recover it because the requirement needs
		    		restVo.setId(list.getCallingFeature().get(i).getCallingFeatureId());
		    		restVoList.add(restVo);
		    	}
	    	}
	    	return restVoList;
	    }
	   /**
	    * 
	    * @param list
	    * @return
	    */
	   private static List<String> mapHomePhonePackList(HomePhonePackList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<String> restVoList = new ArrayList<String>();
	    	if( list.getHomePhonePack()!= null)
	    	{
		    	for(int i=0; i<list.getHomePhonePack().size(); i++)
		    	{
		    		if(list.getHomePhonePack().get(i)==null)
		    			continue;
		    		restVoList.add((String)list.getHomePhonePack().get(i));	    		
		    	}
	    	}
	    	return restVoList;
	    }
	   /**
	    * 
	    * @param list
	    * @return
	    */
	   private static List<FeatureInfoRestVO> mapTollPlanList(TollPlanList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<FeatureInfoRestVO> restVoList = new ArrayList<FeatureInfoRestVO>();
	    	if( list.getTollPlan()!= null)
	    	{
		    	for(int i=0; i<list.getTollPlan().size(); i++)
		    	{
		    		if(list.getTollPlan().get(i)==null)
		    			continue;
		    	
		    		FeatureInfoRestVO restvo = new FeatureInfoRestVO();
		    		restvo.setCategoryName(list.getTollPlan().get(i).getTollCategoryName());
		    		restvo.setName(list.getTollPlan().get(i).getTollPlanName());
		    		restVoList.add(restvo);
		    	}
	    	}
	    	return restVoList;
	    }
	   /**
	    * 
	    * @param list
	    * @return
	    */
	    private static List<String> mapHomePhonePackIdList(HomePhonePackIdList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<String> restVoList = new ArrayList<String>();
	    	if( list.getHomePhonePackId()!= null)
	    	{
		    	for(int i=0; i<list.getHomePhonePackId().size(); i++)
		    	{
		    		if(list.getHomePhonePackId().get(i)==null)
		    			continue;
		    		restVoList.add((String)list.getHomePhonePackId().get(i));	    		
		    	}
	    	}
	    	return restVoList;
	    }
	    /**
	     * 
	     * @param list
	     * @return
	     */
	    private static List<String> mapFeaturesPackList(FeaturesPackList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<String> restVoList = new ArrayList<String>();
	    	if( list.getFeaturesPack()!= null)
	    	{
		    	for(int i=0; i<list.getFeaturesPack().size(); i++)
		    	{
		    		if(list.getFeaturesPack().get(i)==null)
		    			continue;
		    	
		    		String s1 = list.getFeaturesPack().get(i).getFeaturesPackCode();
		    		restVoList.add(s1);
		    		String s2 = list.getFeaturesPack().get(i).getFeaturesPackName();
		    		restVoList.add(s2);
		    	}
	    	}
	    	return restVoList;
	    }
	    /**
	     * 
	     * @param list
	     * @return
	     */
	   private static List<SubscriptionNumberInfoRestVO> mapSubscriptionNumberList( SubscribedProductSummaryVO.ProductInstance.SingleLineComponent.SubscriptionNumberList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<SubscriptionNumberInfoRestVO> restVoList = new ArrayList<SubscriptionNumberInfoRestVO>();
	    	if( list.getSubscriptionNumber()!= null)
	    	{
		    	for(int i=0; i<list.getSubscriptionNumber().size(); i++)
		    	{
		    		if(list.getSubscriptionNumber().get(i)==null)
		    			continue;
		    		SubscriptionNumberInfoRestVO restVo = new SubscriptionNumberInfoRestVO();
		    		restVo.setPhoneNum(list.getSubscriptionNumber().get(i).getPhoneNumber());
		    		restVo.setSubscriptionNumTxt((list.getSubscriptionNumber().get(i).getCode()));
		    		restVoList.add(restVo);
		    	}
	    	}
	    	return restVoList;
	    }
	   /**
	    * 
	    * @param vo
	    * @return
	    */
	   private static InternetInfoRestVO mapInternetComponent(SubscribedProductSummaryVO.ProductInstance.InternetComponent vo)
	    {
	    	if(vo== null)
	    		return null;
	    	InternetInfoRestVO restVO = new InternetInfoRestVO();
	    	restVO.setInternetTypeCd(vo.getInternetTypeCd());
	    	restVO.setPromotionList(mapPromotionList2(vo.getPromotionList()));
	    	//TODO : Khalil disable it for now as it's not sipported in ESS and may not be needed 
	    	//restVO.setAddOnPacks(vo.getAddOnPackList());
	    	return restVO;
	    }
	   /**
	    * 
	    * @param list
	    * @return
	    */
	   private static List<PromotionInfoRestVO> mapPromotionList2(SubscribedProductSummaryVO.ProductInstance.InternetComponent.PromotionList list)
	    {
	    	if( list ==null)
	    		return null;
	    	ArrayList<PromotionInfoRestVO> restVoList = new ArrayList<PromotionInfoRestVO>();
	    	if( list.getPromotion()!= null)
	    	{
		    	for(int i=0; i<list.getPromotion().size(); i++)
		    	{
		    		if(list.getPromotion().get(i)==null)
		    			continue;
		    	
		    		PromotionInfoRestVO restvo = new PromotionInfoRestVO();
		    		restvo.setPromotionId(list.getPromotion().get(i).getPromotionId());
		    		restvo.setPromotionName(list.getPromotion().get(i).getPromotionName());
		    		restVoList.add(restvo);
		    	}
	    	}
	    	return restVoList;
	    }	
	   /**
	    * 
	    * @param vo
	    * @return
	    */
	   private static List<PackInfoRestVO> mapTvPackList(SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvPackList vo)
	    {
	    	if(vo== null)
	    		return null;
	    	List<PackInfoRestVO> restVoList = new ArrayList<PackInfoRestVO>();
	    	if( vo.getTvPack()!= null)
	    	{
		    	for(int i=0; i<vo.getTvPack().size(); i++)
		    	{
		    		if(vo.getTvPack().get(i)==null)
		    			continue;
		    	
		    		PackInfoRestVO restvo = new PackInfoRestVO();
		    		restvo.setComponentId(vo.getTvPack().get(i).getComponentId());
		    		restvo.setComponentName(vo.getTvPack().get(i).getComponentName());
		    		restVoList.add(restvo);
		    	}
	    	}
	    	return restVoList;
	    }
	   /**
	    * 
	    * @param vo
	    * @return
	    */
	   private static TvInfoRestVO mapTtvComponent(SubscribedProductSummaryVO.ProductInstance.TtvComponent vo)
	    {
	    	if(vo== null)
	    		return null;
	    	TvInfoRestVO restVO = new TvInfoRestVO();
	    	restVO.setHdChannelInd(vo.getHdChannelInd());
	    	restVO.setTvComboList(mapTvComboList(vo.getTvComboList()));
	    	restVO.setTvPackList(mapTvPackList(vo.getTvPackList()));
	    	restVO.setPromotionList(mapPromotionList3(vo.getPromotionList()));
	    	return restVO;
	    }   
	   /**
	    * 
	    * @param vo
	    * @return
	    */
	   private static List<PackInfoRestVO> mapTvComboList(SubscribedProductSummaryVO.ProductInstance.TtvComponent.TvComboList vo)
	    {
	    	if(vo== null)
	    		return null;
	    	List<PackInfoRestVO> restVoList = new ArrayList<PackInfoRestVO>();
	    	if( vo.getTvCombo()!= null)
	    	{
		    	for(int i=0; i<vo.getTvCombo().size(); i++)
		    	{
		    		if(vo.getTvCombo().get(i)==null)
		    			continue;
		    	
		    		PackInfoRestVO restvo = new PackInfoRestVO();
		    		restvo.setComponentId(vo.getTvCombo().get(i).getComponentId());
		    		restvo.setComponentName(vo.getTvCombo().get(i).getComponentName());
		    		restVoList.add(restvo);
		    	}
	    	}
	    	return restVoList;
	    }
	   /**
	    * 
	    * @param vo
	    * @return
	    */
	   private static List<EquipmentInfoRestVO> mapEquipmentList(SubscribedProductSummaryVO.ProductInstance.EquipmentList vo)
	    {
	    	if(vo== null)
	    		return null;
	    	
	    	List<EquipmentInfoRestVO> restVoList = new ArrayList<EquipmentInfoRestVO>();  	
	    	List<SubscribedProductSummaryVO.ProductInstance.EquipmentList.Equipment> volist = vo.getEquipment();
	    	if(volist!= null)
	    	{
		    	for(int i=0; i<volist.size(); i++)
		    	{
		    		if(volist.get(i)==null)
		    			continue;
		    	
		    		EquipmentInfoRestVO restvo = new EquipmentInfoRestVO();
		    		restvo.setEquipmentAcquisitionTypeCd(volist.get(i).getEquipmentAcquisitionType());
		    		restvo.setEquipmentName(volist.get(i).getEquipmentName());
		    		restVoList.add(restvo);
		    	}
	    	}
	    	return restVoList;
	    }
	   /**
	    * 
	    * @param calendar
	    * @return
	    */
		public static String toDateStr(XMLGregorianCalendar calendar){
	        if(calendar == null) {
	            return null;
	        }
	        java.util.Date date = calendar.toGregorianCalendar().getTime();
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	    	String dateStr = format.format(date);
	    	return dateStr;
	    }
		/**
		 * 
		 * @param calendar
		 * @param formatString
		 * @return
		 */
		public static String toDateStr(XMLGregorianCalendar calendar, String formatString){
			if(calendar == null) {
				return null;
			}
			java.util.Date date = calendar.toGregorianCalendar().getTime();
			SimpleDateFormat format = new SimpleDateFormat(formatString);
			String dateStr = format.format(date);
			return dateStr;
		}
		
		public String getProductTypeByCid(String cidCode) {
			String functionName = "getProductTypeByCid";
			String productType = "";
			Map <String, String> wssProdOmsCidMap;
			if (refPdsBusDelegate == null) {
				refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
			}
			wssProdOmsCidMap = refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP);
			
			productType = (String)MapUtils.invertMap(wssProdOmsCidMap).get(cidCode);

			return productType;
		
		}
		public String getProductLineProductType(String cidCode) {
			String functionName = "getProductLineByProductType";
			String productType = "";
			Map <String, String> wssProdOmsCidMap;
			if (refPdsBusDelegate == null) {
				refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
			}
			wssProdOmsCidMap = refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP);
			
			productType = (String)MapUtils.invertMap(wssProdOmsCidMap).get(cidCode);

			return productType;
		
		}
		public String getProductLineByProductType(String cidCode) {
			String functionName = "getProductLineByProductType";
			String productType = "";
			Map <String, String> wssProdOmsCidMap;
			if (refPdsBusDelegate == null) {
				refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
			}
			wssProdOmsCidMap = refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_X_PROD_LINE);
			
			productType = (String)wssProdOmsCidMap.get(cidCode);

			return productType;
		
		}
		
		private static Product findComponent(ProductItemIdentifierVO productId, List<Product> products ) {
			if (products != null && productId.getParentProductCatalogueId() != null) {
				for (Product product : products) {
					if (productId.getProductCatalogueId().equalsIgnoreCase(product.getCatalogId())
							&& product.getParentCatalogId().equalsIgnoreCase(productId.getParentProductCatalogueId())) {
						return product;
					} 
					if (product.getProductComponents() != null) {
							findComponent(productId, product.getProductComponents());
					}
				}
			}
			return null;
		}
		/**
		 * 
		 * @param productCatalogId
		 * @param products
		 * @param productCataloguesFromGrid 
		 * @return
		 */
		private static Product findComponent(String productCatalogId, List<Product> products, Map<String, CatalogueItemDO> productCataloguesFromGrid ) {
			if (products != null &&  productCatalogId != null) {
				Product matchedProduct = null;
				for (Product product : products) {
					matchedProduct = findComponent(productCatalogId, product, productCataloguesFromGrid);
					if (matchedProduct != null) return matchedProduct;
				}
			}
			return null;
		}
		private static Product findComponent(String productCatalogId, Product product, Map<String, CatalogueItemDO> productCataloguesFromGrid) {
			if (product != null && !StringUtils.isEmpty(productCatalogId)) {
				CatalogueItemDO catalogIdDAO = productCataloguesFromGrid.get(product.getCatalogId());
				if (catalogIdDAO != null && productCatalogId.equalsIgnoreCase(catalogIdDAO.getCatalogueItemId())) {
					return product;
				}  
				List<Product> childComponents = product.getProductComponents();
				if (childComponents != null) {
					for (Product childComponent : childComponents) {
						Product matchedComponent = findComponent(productCatalogId, childComponent, productCataloguesFromGrid);
						if (matchedComponent != null) {
							return matchedComponent;
						}
					}				
				}
			}
			return null;
		}

	/**
	 * 
	 * @param product  specific product from all assigned product
	 * @param availableEquipmentList  availabe equipment list from shopping cart/offer
	 * @param likeToLike
	 * @param productCataloguesFromGrid   product catalogue id for the assigned product
	 */
	public static void getAssignedInternetEquipment(Product product, AvailableFFHEquipmentTypeVO availableEquipmentList, boolean likeToLike, Map<String, CatalogueItemDO> productCataloguesFromGrid, String prodType) {
		if (availableEquipmentList != null && product != null) {
			if (Constants.INEQ.equalsIgnoreCase(product.getServiceType()) || (Constants.TVEQ.equalsIgnoreCase(product.getServiceType()))) {
				FFHEquipmentItemVO equipment = new FFHEquipmentItemVO();
				equipment.setExisting(true);
				String acquisitionType = null;
				String materialItemCode = null;
				String equipmentName = null;
				//NWLN-10262 existing accessory equipment is always carry over
                if (likeToLike || EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(prodType)) {
                	equipment.setCarryOver(true);
                }
				for (ProductCharacteristicValue charValue : product.getProductCharacteristics()) {
					if (charValue != null && Long.valueOf(Constants.EQUIPMENT_NAME_ATTRIBUTE_ID).equals(charValue.getCatalogAttributeId())) {
						equipmentName = charValue.getValue();
					} else if (Constants.EQUIPMENT_NAME_DESCRIPTION_ATTRIBUTE.equalsIgnoreCase(charValue.getName())) {
						equipmentName = charValue.getValue();
					} else if (Constants.EQUIPMENT_MIC_ATTRIBUTE_ID.equalsIgnoreCase(charValue.getName())) {
						materialItemCode = charValue.getValue();
					} else if ( Constants.EQUIPMENT_ACQUISITION_TYPE_ATTRIBUTE_ID.equalsIgnoreCase(charValue.getName())) {
						acquisitionType = charValue.getValue();
					}
				}
				equipment.setModelName(equipmentName);
				equipment.setMaterialItemCode(materialItemCode);
				ProductItemIdentifierVO productItemIdentifier = new ProductItemIdentifierVO();
				productItemIdentifier.setParentProductCatalogueId(product.getParentCatalogId());
				CatalogueItemDO catalogIdVO = productCataloguesFromGrid.get(product.getCatalogId());
				CatalogueItemDO parentCatalogIdVO = productCataloguesFromGrid.get(product.getParentCatalogId());
				if (catalogIdVO != null ) {
					productItemIdentifier.setProductCatalogueId(catalogIdVO.getCatalogueItemId());
				}
				if (parentCatalogIdVO != null ) {
					productItemIdentifier.setParentProductCatalogueId(parentCatalogIdVO.getCatalogueItemId());
					productItemIdentifier.setExternalId(product.getCatalogId());
				}
				equipment.setProductItemIdentifier(productItemIdentifier);
				
				//NWLN-10262 Boost equipment no long need to show up in HSIC existing equipment, it will show up in CPE
				if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(prodType)
						&& (EnterpriseWLNSalesServicesConstants.BOOST_WIFI_STARTER_PRODUCT_CATALOGUE_ID
								.equalsIgnoreCase(productItemIdentifier.getParentProductCatalogueId())
								|| EnterpriseWLNSalesServicesConstants.BOOST_WIFI_EXPANSION_PRODUCT_CATALOGUE_ID
										.equalsIgnoreCase(productItemIdentifier.getParentProductCatalogueId()))) {
					return;
				}
				
				if (acquisitionType != null) {
					EquipmentAcquisitionTypeVO acquisitionTypeVO = new EquipmentAcquisitionTypeVO();
					if (Constants.EQUIPMENT_ACQUISITION_TYPE_BUY.equalsIgnoreCase(acquisitionType)) {
						acquisitionTypeVO.setBuyIndicator(true);
						if (availableEquipmentList.getBuyEquipmentList() == null) {
							List<FFHEquipmentItemVO> buyEquipmentList = new ArrayList<FFHEquipmentItemVO>();
							availableEquipmentList.setBuyEquipmentList(buyEquipmentList);
						}
						mergeWithAvailableEquipmentList(equipment, availableEquipmentList.getBuyEquipmentList(), isAvailableEquipment(availableEquipmentList), prodType);
					} else if (Constants.EQUIPMENT_ACQUISITION_TYPE_RENTAL.equalsIgnoreCase(acquisitionType)) {
						acquisitionTypeVO.setRentalEquipmentIndicator(true);
						if (availableEquipmentList.getRentalEquipmentList() == null) {
							List<FFHEquipmentItemVO> rentalEquipmentList = new ArrayList<FFHEquipmentItemVO>();
							availableEquipmentList.setRentalEquipmentList(rentalEquipmentList );
						}
						mergeWithAvailableEquipmentList(equipment, availableEquipmentList.getRentalEquipmentList(), isAvailableEquipment(availableEquipmentList), prodType);
					}
					
				}
				//if (availableEquipmentList.getExistingEquipmentList() == null) {
					//List<FFHEquipmentItemVO> existingEquipmentList = new ArrayList<FFHEquipmentItemVO>();
					//availableEquipmentList.setExistingEquipmentList(existingEquipmentList);
				//}
				availableEquipmentList.getExistingEquipmentList().add(equipment);
			}
			List<Product> childs = product.getProductComponents();
			for (Product child : childs) {
				getAssignedInternetEquipment(child, availableEquipmentList, likeToLike, productCataloguesFromGrid, prodType);
			}
		}
	}
	/**
	 * 
	 * @param availableEquipmentList
	 * @return
	 */
	private static boolean isAvailableEquipment(AvailableFFHEquipmentTypeVO availableEquipmentList) {
		if (availableEquipmentList == null) {
			return false;
		} else { 
			return (isAvailableEquimpment(availableEquipmentList.getBuyEquipmentList()) ||
					isAvailableEquimpment(availableEquipmentList.getClientOwnedEquipmentList()) ||
					isAvailableEquimpment(availableEquipmentList.getRentalEquipmentList())
					) ;
		}
	}
	/**
	 * 
	 * @param equipmentList
	 * @return
	 */
	private static boolean isAvailableEquimpment(List<FFHEquipmentItemVO> equipmentList) {
		if (equipmentList == null) {
			return false;
		} else {
			return !equipmentList.isEmpty();
		}
	}
	/**
	 * 
	 * @param equipment
	 * @param equipmentList
	 */
	private static void mergeWithAvailableEquipmentList(FFHEquipmentItemVO equipment,
			List<FFHEquipmentItemVO>  equipmentList, boolean isAvailableEquipment, String prodType) {
		// NWLN-10262 For CPE, not merge existing equipment to available equipment list
		// so that we wouln't add the existing equipment back after they were filtered by offer rule
		if(EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(prodType)) return;
		
		if (equipment != null ) {
			if (equipmentList == null) {
				equipmentList = new ArrayList<FFHEquipmentItemVO>();
				equipmentList.add(equipment);
			} else {
				boolean foundMatchingEquipment = false;
				FFHEquipmentItemVO matchedEquipment = null;
				for (FFHEquipmentItemVO availableEquipment : equipmentList) {
					if (availableEquipment.getMaterialItemCode() != null) {
						if (availableEquipment.getMaterialItemCode().equalsIgnoreCase(equipment.getMaterialItemCode())) {
							availableEquipment.setCarryOver(true);
							availableEquipment.setExisting(true);
							foundMatchingEquipment = true;
							matchedEquipment = availableEquipment;
							break;
						}
					}
				}
				if (!foundMatchingEquipment) {
					if (!isAvailableEquipment) {
						equipment.setCarryOver(true);
					}
						equipmentList.add(equipment); 
				} else {
					matchedEquipment.setExisting(true);
				}
			}
		}
	}
	
	/**
	 * recursively traverse through assigned product and either add existing product item or update existing/carry indicator in product item
	 * @param product assignedInternetProduct ?
	 * @param assignedInternetProduct
	 * @param productItemList  product items for response
	 * @param productTemlateId
	 * @param isLikeToLikeContract
	 * @param productCatogueFromGridMap
	 */
	public static void getProductComponentItems(Product product, Product assignedInternetProduct, List<ProductItemVO> productItemList,
			String productTemlateId, boolean isLikeToLikeContract, Map<String, CatalogueItemDO> productCatogueFromGridMap) {
		if (product != null && productItemList != null) {
			if (product.getCatalogId() != null && !isSuppressed(product) && !isPseudoComponent(product)) {
				ProductItemVO selectedProductItem = findComponent(product.getCatalogId(), product.getParentCatalogId(),
						productItemList, productCatogueFromGridMap);
				ProductItemVO selectedParentProductItem = findComponent(product.getParentCatalogId(), null,
						productItemList, productCatogueFromGridMap);
				if (selectedProductItem == null) {
					// Component not found in available items
					ProductItemVO productItem = buildProductItem(product, productCatogueFromGridMap);
					if (isLikeToLikeContract) {
						productItem.setCarryOverInd(true);
					} else 	if (selectedParentProductItem != null && !selectedParentProductItem.isCarryOverInd()) {
						productItem.setCarryOverInd(false);
					} else if (isInProductTemplate(productItem, productTemlateId) && !isProductComponent(product)) {
						productItem.setCarryOverInd(true);
					} else {
						productItem.setCarryOverInd(false);
					}
					productItemList.add(productItem);
				} else {
					selectedProductItem.setExistingInd(true);
					if (isLikeToLikeContract) {
						selectedProductItem.setCarryOverInd(true);
					} else if (selectedParentProductItem != null && !selectedParentProductItem.isCarryOverInd()) {
						selectedProductItem.setCarryOverInd(false);
					} else if (isInProductTemplate(selectedProductItem, productTemlateId)) {
						selectedProductItem.setCarryOverInd(true);
					} else {
						selectedProductItem.setCarryOverInd(false);
					}
				}
			}
			getProductOffering(product, assignedInternetProduct, productItemList, productTemlateId, productCatogueFromGridMap);
		}
		if(product!=null){
			List<Product> childs = product.getProductComponents();
			if (childs != null) {
				for (Product child : childs) {
					getProductComponentItems(child, assignedInternetProduct, productItemList, productTemlateId, isLikeToLikeContract, productCatogueFromGridMap);

				}
			}
		}

	}
	/**
	 * 
	 * @param product
	 * @return
	 */
	private static boolean isPseudoComponent(Product product) {
		return (Constants.INEQ.equalsIgnoreCase(product.getServiceType())
				|| Constants.HSIC.equalsIgnoreCase(product.getServiceType())
				|| Constants.TTV.equalsIgnoreCase(product.getServiceType())
				|| EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(product.getServiceType())
				|| Constants.HSIC_PACKS_SERVICE_CODE.equalsIgnoreCase(product.getServiceType())
				|| StringUtils.isEmpty(product.getCatalogId())
				);
	}
	/**
	 * 
	 * @return
	 */
	private static boolean isSuppressed(Product product) {
		// TODO Auto-generated method stub
		return false;
	}
	private static boolean isProductComponent(Product product) {
		return Constants.ADSP.equalsIgnoreCase(product.getServiceType())||Constants.SFPK.equalsIgnoreCase(product.getServiceType());
	}
	private static ProductItemVO buildProductItem(Product product, Map<String, CatalogueItemDO> productCatogueFromGridMap) {
		logger.debug("buildProductItem", "product.getParentCatalogId()=" + product.getParentCatalogId());
		logger.debug("buildProductItem", "product.getCatalogId()=" + product.getCatalogId());
		ProductItemVO productItem = new ProductItemVO();
		ProductItemIdentifierVO productItemIdentifier = new ProductItemIdentifierVO();
		
		// MJ: Add map key check to avoid product is not in Grid map and gives NPE exception
		if(productCatogueFromGridMap.containsKey(product.getParentCatalogId()))
			productItemIdentifier.setParentProductCatalogueId(productCatogueFromGridMap.get(product.getParentCatalogId()).getCatalogueItemId());
		if(productCatogueFromGridMap.containsKey(product.getCatalogId()))
			productItemIdentifier.setProductCatalogueId(productCatogueFromGridMap.get(product.getCatalogId()).getCatalogueItemId()); 
		
		productItemIdentifier.setExternalId(product.getCatalogId());
		productItem.setProductItemIdentifier(productItemIdentifier);
		productItem.setServiceTypeCode(product.getServiceType());
		MarketOfferClassificationVO marketOfferClassification = new MarketOfferClassificationVO();
		if (isProductComponent(product)) {
			marketOfferClassification.setProductComponentInd(true);
			productItem.setMarketOfferClassification(marketOfferClassification);
		}
		productItem.setExistingInd(true);
		productItem.setMarketOfferClassification(marketOfferClassification);
		productItem.setProductSerialNumber(product.getProductSerialNumber());
		
		// MJ: Add map key check to avoid product is not in Grid map and gives NPE exception
		if (productCatogueFromGridMap.containsKey(product.getCatalogId())) {
			List<MultilingualTextVO> productCatalogueNames = new ArrayList<MultilingualTextVO>();
			MultilingualTextVO productCatalogueName = new MultilingualTextVO();
			productCatalogueName.setLocaleTxt(Constants.ENGLISH);
			productCatalogueName.setValueTxt(productCatogueFromGridMap.get(product.getCatalogId()).getName());
			productCatalogueNames.add(productCatalogueName);
			productItem.setProductCatalogueName(productCatalogueNames);
			
			List<MultilingualTextVO> productCatalogueDescs = new ArrayList<MultilingualTextVO>();
			MultilingualTextVO productCatalogueDesc = new MultilingualTextVO();
			productCatalogueDesc.setLocaleTxt(Constants.ENGLISH);
			productCatalogueDesc.setValueTxt(productCatogueFromGridMap.get(product.getCatalogId()).getDescription());
			productCatalogueDescs.add(productCatalogueDesc);
			productItem.setProductCatalogueDescription(productCatalogueDescs);
		}
		
		return productItem;
		
	}
	/**
	 * 
	 * @param productItemVO
	 * @param productTemlateId
	 * @return
	 */
	private static boolean isInProductTemplate(ProductItemVO productItemVO, String productTemlateId) {
		if (productItemVO != null && productItemVO.getProductItemIdentifier() != null) {
			ProductItemIdentifierVO productIdentifier = productItemVO.getProductItemIdentifier();
		    List<CatalogueItemHierarchyDO> catalogItemDetailList = gridHelper.getCategoryItemDetails(productTemlateId, productIdentifier.getProductCatalogueId(), productIdentifier.getParentProductCatalogueId());
			if (catalogItemDetailList != null && !catalogItemDetailList.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param product
	 * @param productItemList
	 * @param productTemplateId
	 */
	private static void getProductOffering(Product product, Product assignedProduct, List<ProductItemVO> productItemList, String productTemplateId, Map<String, CatalogueItemDO> productCatogueFromGridMap) {
		List<ProductOffering> offerings = product.getProductOffering();
		ProductItemVO selectedParentProductItem = findComponent(product.getCatalogId(), product.getParentCatalogId(), productItemList, productCatogueFromGridMap);
		if (offerings != null) {
			for (ProductOffering offering : offerings) {
				if (offering != null){
				    ProductItemVO selectedProductItem  = findComponent(offering.getCatalogId(), product.getCatalogId(), productItemList, productCatogueFromGridMap);
				      if (selectedProductItem != null) {
					        	selectedProductItem.setExistingInd(true);
					        	selectedProductItem.setCarryOverInd(true);
					        	productItemList.add(selectedProductItem);
				      
				      } else  {
				    	    if (selectedParentProductItem == null) {
				    	    	ProductItemVO productItemVO = buildProductItem(product, product, offering, productCatogueFromGridMap);
				    	    	productItemVO.setCarryOverInd(true);
				    	    	productItemList.add(productItemVO);
				    	    } else {
				    	    	List<AssociatedProductItemVO> associatedProductItems = new ArrayList<AssociatedProductItemVO>();
				    	    	AssociatedProductItemVO associatedProductItemVO = new AssociatedProductItemVO();
				    	    	ProductItemIdentifierVO productItemIdentifier = new ProductItemIdentifierVO();
				    	    	productItemIdentifier.setProductCatalogueId(getProductCatalogIdFromExternalReference(offering.getCatalogId()));
				    	    	String parentCatalogId = product.getCatalogId();
				    	    	
				    	    	if (StringUtils.isEmpty(product.getCatalogId())) {
				    	    		parentCatalogId = assignedProduct.getCatalogId();
				    	    	}
				    	    	productItemIdentifier.setParentProductCatalogueId(getProductCatalogIdFromExternalReference(parentCatalogId));
								associatedProductItemVO.setProductItemIdentifier(productItemIdentifier );
								associatedProductItems.add(associatedProductItemVO);
								List<MultilingualTextVO> productCatalogueNames = new ArrayList<MultilingualTextVO>();
								MultilingualTextVO productCatalogueName = new MultilingualTextVO();
								productCatalogueName.setLocaleTxt(Constants.ENGLISH);
								productCatalogueName.setValueTxt(offering.getName());
								productCatalogueNames.add(productCatalogueName);
								associatedProductItemVO.setProductCatalogueName(productCatalogueNames);

								List<MultilingualTextVO> productCatalogueDescs = new ArrayList<MultilingualTextVO>();
								MultilingualTextVO productCatalogueDesc = new MultilingualTextVO();
								productCatalogueDesc.setLocaleTxt(Constants.ENGLISH);
								productCatalogueDesc.setValueTxt(offering.getDescription());
								productCatalogueDescs.add(productCatalogueDesc);
								associatedProductItemVO.setProductCatalogueDescription(productCatalogueDescs);
								//bugfix: not overwrite the associated production items of the parent
								if(selectedParentProductItem.getAssociatedProductItems()!=null) {
									//QC80860 check duplicate associatedProductItem by productCatalogueId, flag as carryOver item.
									if (!isDuplicateAssociatedProductItem(selectedParentProductItem.getAssociatedProductItems(),
											associatedProductItemVO, selectedParentProductItem.isCarryOverInd())) {
										selectedParentProductItem.getAssociatedProductItems().addAll(associatedProductItems);
									}
								}else {
									selectedParentProductItem.setAssociatedProductItems(associatedProductItems);
								}
								if (!selectedParentProductItem.isCarryOverInd()) {
									associatedProductItemVO.setCarryOverInd(false);
								} 
				    	    }
				      }
				}
			}	
		}
	}	
	
	private static boolean isDuplicateAssociatedProductItem(List<AssociatedProductItemVO> list, AssociatedProductItemVO newItem, boolean carryOverInd) {
		String productCatalogueId = newItem.getProductItemIdentifier().getProductCatalogueId();
		
		if(productCatalogueId !=null && list != null && !list.isEmpty()) {
			for(AssociatedProductItemVO item: list) {
    			if(productCatalogueId.equals(item.getProductItemIdentifier().getProductCatalogueId())){
    				return true;
    			}
    		}
		}
		return false;
	}
		
	
	private static ProductItemVO buildProductItem(Product product, Product assignedProduct, ProductOffering offering, Map<String, CatalogueItemDO> productCatalohuesMap ) {
		ProductItemVO productItemVO = new ProductItemVO();
		ProductItemIdentifierVO productItemIdentifier = new ProductItemIdentifierVO();
		String parentCatalogId = product.getCatalogId();
    	if (StringUtils.isEmpty(product.getCatalogId())) {
    		parentCatalogId = assignedProduct.getCatalogId();
    	}
    	CatalogueItemDO parentCatalogue = productCatalohuesMap.get(parentCatalogId); 
    	CatalogueItemDO productCatalogue = productCatalohuesMap.get(parentCatalogId);
		if (parentCatalogue != null) {
			productItemIdentifier.setParentProductCatalogueId(parentCatalogue.getCatalogueItemId());
		}  
		if (productCatalogue != null) {
			productItemIdentifier.setProductCatalogueId(productCatalogue.getCatalogueItemId());
		}
		productItemIdentifier.setExternalId(offering.getCatalogId());
		productItemVO.setProductItemIdentifier(productItemIdentifier);
		productItemVO.setServiceTypeCode(product.getServiceType());
		productItemVO.setExistingInd(true);
		List<MultilingualTextVO> productCatalogueNames = new ArrayList<MultilingualTextVO>();
		MultilingualTextVO productCatalogueName = new MultilingualTextVO();
		productCatalogueName.setLocaleTxt(Constants.ENGLISH);
		productCatalogueName.setValueTxt(offering.getName());
		productCatalogueNames.add(productCatalogueName);
		productItemVO.setProductCatalogueName(productCatalogueNames);
		
		List<MultilingualTextVO> productCatalogueDescs = new ArrayList<MultilingualTextVO>();
		MultilingualTextVO productCatalogueDesc = new MultilingualTextVO();
		productCatalogueDesc.setLocaleTxt(Constants.ENGLISH);
		productCatalogueDesc.setValueTxt(offering.getDescription());
		productCatalogueDescs.add(productCatalogueDesc);
		productItemVO.setProductCatalogueDescription(productCatalogueDescs);

		List<ProductOfferingPrice> productOfferingPriceList = offering.getProductOfferingPrice();
		if (productOfferingPriceList != null) {
			for (ProductOfferingPrice productOfferingPrice : productOfferingPriceList) {
				if (productOfferingPrice != null) {
					String amount = productOfferingPrice.getAmount();
					if (amount != null && amount.startsWith("-") && offering.getCatalogId() != null) {// this checks for negative amount which will be considered for promotion list
						MarketOfferClassificationVO marketOfferClassification = new MarketOfferClassificationVO();
						marketOfferClassification.setDiscountInd(true);
						productItemVO.setMarketOfferClassification(marketOfferClassification);
						productItemVO.setDiscountCode(offering.getCatalogId());
						PriceDiscountVO productItemPriceDiscount = new PriceDiscountVO();
						productItemPriceDiscount.setPriceAlterationTypeCd(Constants.DISCOUNT);
						productItemPriceDiscount.setPricingTypeCd(Constants.RECURRING);
						productItemVO.setExistingInd(true);
						productItemVO.setCarryOverInd(true);
						break;
					} 
				}
			}
		}
		if (productItemVO.getMarketOfferClassification() == null) {
			 MarketOfferClassificationVO marketOfferClassification = new MarketOfferClassificationVO();
			 marketOfferClassification.setProductComponentInd(true);
		}	 
 		return productItemVO;
		
	}
	private static ProductItemVO findComponent(String productCatalogId, String parentCatalogId, List<ProductItemVO> productItemList, Map<String, CatalogueItemDO> productCatogueFromGridMap) {

		CatalogueItemDO catalogItem = null;
		CatalogueItemDO parentCatalogItem = null;
		
		if (productCatalogId != null) {
			catalogItem = productCatogueFromGridMap.get(productCatalogId);
		}
		if (parentCatalogId != null) {
			parentCatalogItem = productCatogueFromGridMap.get(parentCatalogId);
		}
		
		for (ProductItemVO productItem: productItemList) {
			if (catalogItem != null && parentCatalogItem != null) {
					if (parentCatalogItem.getCatalogueItemId().equalsIgnoreCase(productItem.getProductItemIdentifier().getParentProductCatalogueId()) && catalogItem.getCatalogueItemId().equalsIgnoreCase(productItem.getProductItemIdentifier().getProductCatalogueId())) {
						return productItem;
					}
				
			} else if (productCatalogId != null) {
				if (productCatalogId.equalsIgnoreCase(productItem.getProductItemIdentifier().getProductCatalogueId())) {
					return productItem;
				}
			}
		}	
		return null;
	}

	private static String getProductCatalogIdFromExternalReference(String parentCatalogId) {
		CatalogueItemDO catalogItem = gridHelper.getCatalogueItemByExternalId(parentCatalogId);
		//CatalogueItemHierarchyDO categoryItemDetail = gridHelper.getCategoryItemDetails(catalogItem.getCatalogueItemId());
		if (catalogItem != null) {
			return catalogItem.getCatalogueItemId();
		} else {
			return null;	
		} 
	}
	
	/**
	 * 
	 * @param scContextVO
	 * @param selectedPiList
	 * @param productType
	 * @return
	 */
	public List<ProductItemVO> getAssignedProductItems(final ShoppingCartContextVO scContextVO, final List<ProductItemVO> selectedPiList, final String productType, Map<String, CatalogueItemDO> productCatogueFromGridMap) {
		List<Product> assignedProductList = scContextVO.getAssignedProducts();
		if (assignedProductList != null && !assignedProductList.isEmpty()) {
			Product product = getAssignedProducts(assignedProductList, productType);
			getProductComponentItems(product, product, selectedPiList,  "", false, productCatogueFromGridMap );
		}
		return selectedPiList;
	}
	public static  Map<String, CatalogueItemDO> getProductIdsFromGrid(List<Product> products) {
		Set<String> productCatalogueIds = new HashSet<String>();
		getProductCatalogIds(products, productCatalogueIds);
		return gridHelper.getCatalogueItemsByExternalId(productCatalogueIds);
	}
	
	private static void getProductCatalogIds(Product product, Set<String> prouctCatalogueIds) {
		if (!StringUtils.isEmpty(product.getCatalogId())) {
			prouctCatalogueIds.add(product.getCatalogId());
		}
		if (product.getProductOffering() != null) {
			for (ProductOffering productOffering :  product.getProductOffering()) {
				if (!StringUtils.isEmpty(productOffering.getCatalogId())) {
					prouctCatalogueIds.add(productOffering.getCatalogId());
				}	
			}
		}
		if (product.getProductComponents() != null) {
			for (Product p : product.getProductComponents()) {
				getProductCatalogIds(p, prouctCatalogueIds);
			}
		}
	}
	private static void getProductCatalogIds(List<Product> products, Set<String> prouctCatalogueIds) {
		for (Product product : products) {
			getProductCatalogIds(product, prouctCatalogueIds);
		}
	}	
	
	/**
	 * assignedProductList
	 * @param scContextVO
	 * @param productType
	 * @return
	 */
	public List<FFHEquipmentItemVO> getAssignedEquipments(final ShoppingCartContextVO scContextVO, final String productType, boolean likeToLike, Map<String, CatalogueItemDO> productCataloguesFromGrid) {
		List<Product> assignedProductList = scContextVO.getAssignedProducts();
		Product product = getAssignedProducts(assignedProductList, productType);
		AvailableFFHEquipmentTypeVO availableFFHEquipmentTypeVO = new AvailableFFHEquipmentTypeVO();
		getAssignedInternetEquipment(product, availableFFHEquipmentTypeVO , likeToLike, productCataloguesFromGrid, productType);
		return availableFFHEquipmentTypeVO.getExistingEquipmentList();
	}

	public static Product getAssignedProducts(List<Product> assignedProducts, String productType) {
		   for (Product product : assignedProducts) {
			   if (productType.equalsIgnoreCase(product.getServiceType())) {
				   return product;
			   }
		   }
		   return null;
	}
	public static String getTemplateId(Offer offer, String productType) {
		if (offer != null) {
			for (WirelineOfferProduct offerProduct : offer.getOfferProduct().getWirelineOfferProductList()){
				if (productType.equalsIgnoreCase(offerProduct.getProductTypeCode())) {
					return offerProduct.getProductTemplateIdentifier().getProductCatalogueId();
			
				}
      
			}
		}
		return null;
	}
	
	public static boolean isLikeToLikeReContracting(String productType, ShoppingCartContextVO shoppingCartContextVO,
			Map<String, CatalogueItemDO> productCataloguesFromGrid) {

		String currentTerm = getAssignedPendingProductTerm(productType, shoppingCartContextVO);
		String selectedTerm = getSelectedTerm(productType, shoppingCartContextVO);

		return MTM_TERM.equals(currentTerm) && !"0".equals(selectedTerm) && isComponentsMatch(productType, shoppingCartContextVO, productCataloguesFromGrid);
	}
	
	private static String getSelectedTerm(String productType, ShoppingCartContextVO shoppingCartContextVO) {
		if(productType == null || shoppingCartContextVO == null || shoppingCartContextVO.getShoppingCartVO() ==  null || shoppingCartContextVO.getShoppingCartVO().getCartItemList() == null) {
			return null;
		}
		
		for( CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList() ) {
			if (cartItem.getProducts() != null) {
				HomePhoneProductTypeVO singProduct = cartItem.getProducts().getHomePhoneProduct();
				InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
				TelevisionProductTypeVO tvProduct = cartItem.getProducts().getTelevisionProduct();
				
				if(SING.equals(productType) && singProduct != null) {
					return singProduct.getSelectedContractTermCd();
				}
				
				if(HSIC.equals(productType) && internetProduct != null) {
					return internetProduct.getSelectedContractTermCd();
				}
				
				if(TTV.equals(productType) && tvProduct != null) {
					return tvProduct.getSelectedContractTermCd();
				}
			}
			
		}
		return null;
	}

	/**
	 * Get current term for a given product type.
	 * @param productType
	 * @param shoppingCartContextVO
	 * @return termCd or null if no product instance found.
	 */
	public static String getAssignedPendingProductTerm(String productType, ShoppingCartContextVO shoppingCartContextVO) {
		ProductInstanceInfoRestVO  productInstanceInfoRestVO = findProductInstanceInfoRestVO(productType, shoppingCartContextVO);
		
		if (productInstanceInfoRestVO == null ) {
			return null;
		}
		return productInstanceInfoRestVO.getTermCd();
	}
	
	public static ProductInstanceInfoRestVO findProductInstanceInfoRestVO(String productType, ShoppingCartContextVO shoppingCartContextVO) {
			
			if (productType == null ) {
				return null;
			}
			
			List<SubscribedProductInfoRestVO> productList = new ArrayList<SubscribedProductInfoRestVO>();
			
			GetAssignedAndPendingProductResponseVO opAssignedAndPendingProductResponseVO = shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO();
			
			if( opAssignedAndPendingProductResponseVO != null && opAssignedAndPendingProductResponseVO.getSubscribedProductList() != null ) {
				productList.addAll(opAssignedAndPendingProductResponseVO.getSubscribedProductList());
			}
	
			if( opAssignedAndPendingProductResponseVO != null && opAssignedAndPendingProductResponseVO.getPendingProductList() != null ) {
				productList.addAll(opAssignedAndPendingProductResponseVO.getPendingProductList());
			}
			
			if ( !productList.isEmpty()) {
				for (SubscribedProductInfoRestVO product : productList) {
					if (productType.equalsIgnoreCase(product.getProductTypeCd())) {
						if(product.getProductInstance() != null && !product.getProductInstance().isEmpty()) {
							return product.getProductInstance().get(0);
						}
					}
				}
			}
	
			return null;
		}
	
	private static List<ProductComponentVO> getAllCartProductComponents(ShoppingCartContextVO shoppingCartContextVO){
		List<ProductComponentVO> cartProductComponents = new ArrayList<ProductComponentVO>();
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO(); 
		if(shoppingCartVO == null || shoppingCartVO.getCartItemList() == null) {
			return cartProductComponents;
		}
		
		for( CartItemVO cartItem : shoppingCartVO.getCartItemList() ) {
			if(cartItem.getProducts() == null) {
				continue;
			}
			HomePhoneProductTypeVO singProduct = cartItem.getProducts().getHomePhoneProduct();
			InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
			TelevisionProductTypeVO tvProduct = cartItem.getProducts().getTelevisionProduct();
			
			if(singProduct != null && singProduct.getProductComponents() != null) {
				cartProductComponents.addAll(singProduct.getProductComponents());
			}
			
			if(internetProduct != null && internetProduct.getProductComponents() != null) {
				cartProductComponents.addAll(internetProduct.getProductComponents());
			}
			
			if(tvProduct != null && tvProduct.getProductComponents() != null) {
				cartProductComponents.addAll(tvProduct.getProductComponents());
			}
			
		}
		
		return cartProductComponents;
	}
	
	private static List<ProductComponentVO> getProductComponents(String productType, ShoppingCartContextVO shoppingCartContextVO){
		List<ProductComponentVO> cartProductComponents = new ArrayList<ProductComponentVO>();
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO(); 
		if(shoppingCartVO == null || shoppingCartVO.getCartItemList() == null) {
			return cartProductComponents;
		}
		
		for( CartItemVO cartItem : shoppingCartVO.getCartItemList() ) {
			if(cartItem.getProducts() == null) {
				continue;
			}
			HomePhoneProductTypeVO singProduct = cartItem.getProducts().getHomePhoneProduct();
			InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
			TelevisionProductTypeVO tvProduct = cartItem.getProducts().getTelevisionProduct();
			
			if(singProduct != null && singProduct.getProductComponents() != null && SING.equals(productType)) {
				cartProductComponents.addAll(singProduct.getProductComponents());
			}
			
			if(internetProduct != null && internetProduct.getProductComponents() != null && HSIC.equals(productType)) {
				cartProductComponents.addAll(internetProduct.getProductComponents());
			}
			
			if(tvProduct != null && tvProduct.getProductComponents() != null && TTV.equals(productType)) {
				cartProductComponents.addAll(tvProduct.getProductComponents());
			}
			
		}
		
		return cartProductComponents;
	}
	
	/**
	 * is the product component in shopping cart for given product type matching existing Product
	 * @param productType
	 * @param shoppingCartContextVO
	 * @param productCataloguesFromGrid 
	 * @return true if match or no existing product
	 */
	private static boolean isComponentsMatch(String productType, ShoppingCartContextVO shoppingCartContextVO, Map<String, CatalogueItemDO> productCataloguesFromGrid) {
		List<ProductComponentVO> productsComponentsInTheCart = getProductComponents(productType, shoppingCartContextVO);
		List<Product> assignedProducts =shoppingCartContextVO.getAssignedProducts();
		if (productsComponentsInTheCart == null || productsComponentsInTheCart.isEmpty() || assignedProducts == null || assignedProducts.isEmpty()) {
		 return false;
		} else {
			for (ProductComponentVO selectedProductComponent : productsComponentsInTheCart) {
				if (assignedProducts != null) {
					Product productCompoent = findComponent(selectedProductComponent.getProductCatalogueId(), assignedProducts, productCataloguesFromGrid);
					if (productCompoent == null) {
						return false;

					}
				}
			}
		}
		return true;
	}

	
	}
