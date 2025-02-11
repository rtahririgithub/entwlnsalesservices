package com.telus.csm.ewlnsc.delegate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.cpq.rest.domain.BillingAccountRef;
import com.telus.csm.cpq.rest.domain.BundledProductOffering;
import com.telus.csm.cpq.rest.domain.ChannelRef;
import com.telus.csm.cpq.rest.domain.Characteristic;
import com.telus.csm.cpq.rest.domain.CommerceChannel;
import com.telus.csm.cpq.rest.domain.ContractTerm;
import com.telus.csm.cpq.rest.domain.CustomerRef;
import com.telus.csm.cpq.rest.domain.PlaceRef;
import com.telus.csm.cpq.rest.domain.Product;
import com.telus.csm.cpq.rest.domain.ProductCharacteristic;
import com.telus.csm.cpq.rest.domain.ProductOffering;
import com.telus.csm.cpq.rest.domain.ProductSpecificationRef;
import com.telus.csm.cpq.rest.domain.PromoCodeRef;
import com.telus.csm.cpq.rest.domain.Promotion;
import com.telus.csm.cpq.rest.domain.PromotionPattern;
import com.telus.csm.cpq.rest.domain.PromotionQualification;
import com.telus.csm.cpq.rest.domain.PromotionQualificationItem;
import com.telus.csm.cpq.rest.domain.Quantity;
import com.telus.csm.cpq.rest.domain.RelatedPartyRef;
import com.telus.csm.ewlnsc.adapter.IPromotionQualificationRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetPromotionAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetPromotionAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.AccessoryProductTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO2;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.GetSweetenerOfferListResponseVO2;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductPromotionDiscountsVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.PromotionsVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.AccessoryOffer;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary.OfferProductSummary;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.transformer.PromotionQualificationDelegateTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnss.rest.time.DateTime;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import weblogic.auddi.util.Logger;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;


public class PromotionQualificationDelegate {
	public static final String PROMOTION_TYPE_GWP="GWP";
	public static final String PROMOTION_TYPE_REGULAR="REGULAR";
	public static final String PROMOTION_TYPE_MANUAL="MANUAL";

	/** Declaring the Logger **/
	private static final LoggerUtil logger = LoggerUtil.getLogger(PromotionQualificationDelegate.class);
	
	public PromotionQualificationDelegate(){
		super();
	}
	
	public PromotionsVO getPromotions(ShoppingCartContextVO request, boolean isPostTask, boolean promoInd){
				
		String functionName="getPromotions";
		
		logger.enter(functionName);
		PromotionsVO promotionsVO = new PromotionsVO();
		/** Step 1: Building GetPromotionAdapterRequest **/
		
		GetPromotionAdapterRequest adapterRequest = transform(request, isPostTask, promoInd);
		
		/** Step 2: Getting Adapter from Adapter Factory **/
		IPromotionQualificationRestSvcAdapter adapter = AdapterFactory.getAdapter(IPromotionQualificationRestSvcAdapter.class);
		
		/** Step 3: Calling Adapter implementation **/
		GetPromotionAdapterResponse adapterResponse = adapter.getPromotion(adapterRequest);
		PromotionQualification pqResponse = null ;
		if(adapterResponse != null) {
			if( ! adapterResponse.hasError()) {
			pqResponse = adapterResponse.getPromotionQualification();
			if(pqResponse != null && pqResponse.getPromotionQualificationItem().size() >0 ) {
					buildPromotionsVO(promotionsVO,pqResponse.getPromotionQualificationItem(), request,adapterRequest.getPromotionQualification());
			}
			else {
					
					logger.info(functionName, "NO promotions returned");
			}
			
		}
		else {
				//adapter errors
				promotionsVO.setMessageList(PromotionQualificationDelegateTransformer.transformMsgs(adapterResponse.getMessageList()));
			}
		
		}
		else {//response from adapter is NULL
			MessageList messageList = new MessageList();
			RelatedMessage relMsg = new RelatedMessage();
			relMsg.setRelatedErrorCd(EnterpriseWLNSalesServicesConstants.ESS_ERROR_CODE_UNKNOWN_ERROR);
			relMsg.setRelatedErrorMessageTxt(EnterpriseWLNSalesServicesConstants.UNKNOWN_EWLNSS_ERROR);
			relMsg.setRelatedErrorTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
			messageList.getRelatedMessageList().add(relMsg);
			promotionsVO.getMessageList().add(messageList);
		
		}
		
		// 2020-07-09 Update SweetenerOfferList with data from FFHSweetenerTypeVO.
		//addSweetenersFromCommerceSC(request, promotionsVO);
		
		return promotionsVO;
	}


	private PromotionsVO buildPromotionsVO(PromotionsVO promotionsVO ,List<PromotionQualificationItem> promotionQualificationItemList,ShoppingCartContextVO request, PromotionQualification reqPromotionQualification) {
		final String functionName = "buildPromotionsVO";
		Map<String,ProductPromotionDiscountsVO> productPromotionDiscountsOferMap = new HashMap<String,ProductPromotionDiscountsVO>();
		promotionsVO.setProductPromotionDiscountsOferMap(productPromotionDiscountsOferMap);
		HashMap<String, String> orderTypeMap = getOrderTypeMap(request);
		for(PromotionQualificationItem  pqitem: promotionQualificationItemList) {
			//value={REGULAR (any promotions), GWP (for OMNI must exclude this type), MANUAL (manual promotions for D2C. N/A for OMNI)}
			if( pqitem.getImmediatePromotion() != null && pqitem.getImmediatePromotion().getPromotion() != null) {
				Promotion promotion = pqitem.getImmediatePromotion().getPromotion();
				if("ACCESSORY".equalsIgnoreCase(promotion.getPromotionType())) {
					for(PromotionPattern pattern : promotion.getPattern()) {
						if(PROMOTION_TYPE_GWP.equals(pattern.getProductType())) {
							AccessoryOffer accessoryOffer= PromotionQualificationDelegateTransformer.transform2GWP(promotion,pattern);
							if(promotionsVO.getAccessoryResponse() == null) {
								promotionsVO.setAccessoryResponse(new GetOffersResponseVO2());
								promotionsVO.getAccessoryResponse().setAccessoryOfferSummaryList(new ArrayList<AccessoryOffer>());
							}
					promotionsVO.getAccessoryResponse().getAccessoryOfferSummaryList().add(accessoryOffer);
				}
						else {
							logger.info(functionName, "ignore accessory promotion as productType="+ pattern.getProductType());
						}
					}
				}
				else if( promotion.getEmpowerment() ) {
					SweetenerOfferSummary sweetenerSummary= PromotionQualificationDelegateTransformer.transform2Sweetener(promotion, orderTypeMap);
					if(promotionsVO.getSweetenerOfferListResponse() == null) {
					promotionsVO.setSweetenerOfferListResponse(new GetSweetenerOfferListResponseVO2());
					promotionsVO.getSweetenerOfferListResponse().setSweetenerOfferSummaryList(new ArrayList<SweetenerOfferSummary>());
					}
					promotionsVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList().add(sweetenerSummary);
				}
				else { //Discount
					PromotionQualificationDelegateTransformer.populateDiscountToList(promotion,productPromotionDiscountsOferMap,pqitem.getProductOffering(), getOfferList(request),reqPromotionQualification, orderTypeMap );
				}
			}
			else {
				Logger.info("No promotion found.");
			}

		}
		return promotionsVO;
	}

	private List<ProductOffering> getOfferList(ShoppingCartContextVO request) {
		List<ProductOffering> productOfferingList = new ArrayList<ProductOffering>();
	
		for(CartItemVO cartItemVO: request.getShoppingCartVO().getCartItemList()) {
			if(cartItemVO.isSalesOrderItem()) {
				ProductOffering productMarketOffering = new ProductOffering();
				FFHOfferHeaderVO offerHeader = cartItemVO.getProductMarketOffering().getOfferHeader();
				
				productMarketOffering.setId(offerHeader.getOfferId());
				if(offerHeader.getPerspectiveDate() != null) {
					productMarketOffering.setPerspectiveDate(new DateTime(offerHeader.getPerspectiveDate()).toStringZ());
				}
				productMarketOffering.setSourceSystemId(offerHeader.getSystemId());
				
				productOfferingList.add(productMarketOffering);
			}
		}
		return productOfferingList;
	}

	private HashMap<String, String> getOrderTypeMap(ShoppingCartContextVO request) {
		HashMap<String, String> orderTypeMap = new HashMap<String, String>();
		
		for(CartItemVO cartItem: request.getShoppingCartVO().getCartItemList()) {
			if(cartItem.getProducts() != null) {
				if(cartItem.getProducts().getHomePhoneProduct() != null) {
					orderTypeMap.put("SING", cartItem.getProducts().getHomePhoneProduct().getProductOrderType().getOrderTypeCd());
				}
				if(cartItem.getProducts().getTelevisionProduct() != null) {
					orderTypeMap.put("TTV", cartItem.getProducts().getTelevisionProduct().getProductOrderType().getOrderTypeCd());
				}
				if(cartItem.getProducts().getInternetProduct() != null) {
					orderTypeMap.put("HSIC", cartItem.getProducts().getInternetProduct().getProductOrderType().getOrderTypeCd());
				}
				if(cartItem.getProducts().getAccessoryProduct() != null) {
					orderTypeMap.put("CPE", cartItem.getProducts().getAccessoryProduct().getProductOrderType().getOrderTypeCd());
				}
			}
		}
		
		return orderTypeMap;
	}

	private GetPromotionAdapterRequest transform(ShoppingCartContextVO request, boolean isPostTask, boolean promoInd) {
		GetPromotionAdapterRequest getPromotionAdapterRequest = new GetPromotionAdapterRequest();
		
		PromotionQualification promotionQualification = new PromotionQualification();
		
		promotionQualification.setRelatedParty(transfromRelatedParty(request, isPostTask));
		promotionQualification.setChannel(transformChannel(request.getShoppingCartVO().getShoppingProfile().getUserProfile()));
		promotionQualification.setPlace(transformPlace(request.getShoppingCartVO().getServiceAddress()));
		promotionQualification.setPromotionQualificationItem(transformPromotionQualificationItem(request, promoInd));
		promotionQualification.setQualificationType("CART");
		
		getPromotionAdapterRequest.setPromotionQualification(promotionQualification);
		getPromotionAdapterRequest.setSalesTransactionId(request.getShoppingCartVO().getOperationHeader().getSalesTransactionId());
		getPromotionAdapterRequest.setOriginatorApplicationId(request.getShoppingCartVO().getShoppingProfile().getApplicationProfile().getOriginatorApplicationId());
		getPromotionAdapterRequest.setRefreshCache(false);
		
		return getPromotionAdapterRequest;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<PromotionQualificationItem> transformPromotionQualificationItem(ShoppingCartContextVO request, boolean promoInd) {
		Map<String, PromotionQualificationItem> promotionQualificationItemList = new HashMap<String, PromotionQualificationItem>();
		PromotionQualificationItem promotionQualificationItem = null;
		
		List<SubscribedProductInfoRestVO> subscribedProductInfoRestVOList = new ArrayList<SubscribedProductInfoRestVO>();
		if(request.getAssignedAndPendingProductResponseVO() != null) {
			subscribedProductInfoRestVOList = request.getAssignedAndPendingProductResponseVO().getSubscribedProductList();
		}
		String provinceCd = request.getShoppingCartVO().getServiceAddress().getProvinceCode();
		String cityCd = request.getShoppingCartVO().getServiceAddress().getCityCode();

		boolean isHomePhoneAdded = false;
		boolean isInternetAdded = false;
		boolean isTvAdded = false;
		boolean isAccessoryAdded = false;
		
		for(CartItemVO cartItemVO: request.getShoppingCartVO().getCartItemList()) {
			if(cartItemVO.isSalesOrderItem()) {
				
				String contextTypeList = buildContextTypeList(cartItemVO);

				if(cartItemVO.getProducts().getHomePhoneProduct() != null) {
					promotionQualificationItem = new PromotionQualificationItem();

					String orderTypeCd =  cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType().getOrderTypeCd();
					Boolean isWinback = cartItemVO.getProducts().getHomePhoneProduct().getWinback();
					promotionQualificationItem.setProduct(createProduct(findServiceId("SING", subscribedProductInfoRestVOList), "Home Phone", "SING", provinceCd, cityCd, orderTypeCd, contextTypeList, isWinback));
					promotionQualificationItem.setProductOffering(transformProductOffering(cartItemVO, request));
					if (promoInd) {
						promotionQualificationItem.setPromoCode(findPromoCode(request));
					}
					promotionQualificationItem.setContractTerm(transformProductOfferingTerm(cartItemVO.getProducts().getHomePhoneProduct().getSelectedContractTermCd()));

					promotionQualificationItemList.put("SING", promotionQualificationItem);
					isHomePhoneAdded = true;
				}
				if(cartItemVO.getProducts().getInternetProduct() != null) {
					promotionQualificationItem = new PromotionQualificationItem();
					String orderTypeCd =  computeHsicOrderTypeCd(cartItemVO.getProducts().getInternetProduct(), 
							subscribedProductInfoRestVOList, 
							request.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId()));
					Boolean isWinback = cartItemVO.getProducts().getInternetProduct().getWinback();
					promotionQualificationItem.setProduct(createProduct(findServiceId("HSIC", subscribedProductInfoRestVOList), "Internet", "HSIC", provinceCd, cityCd, orderTypeCd, contextTypeList, isWinback));
					promotionQualificationItem.setProductOffering(transformProductOffering(cartItemVO, request));
					if (promoInd) {
						promotionQualificationItem.setPromoCode(findPromoCode(request));
					}
					promotionQualificationItem.setContractTerm(transformProductOfferingTerm(cartItemVO.getProducts().getInternetProduct().getSelectedContractTermCd()));
					
					promotionQualificationItemList.put("HSIC", promotionQualificationItem);
					isInternetAdded = true;
				}
				
				if(cartItemVO.getProducts().getTelevisionProduct() != null) {
					promotionQualificationItem = new PromotionQualificationItem();

					String orderTypeCd =  computeTtvOrderTypeCd(cartItemVO.getProducts().getTelevisionProduct(), 
							subscribedProductInfoRestVOList, 
							request.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId()));
					Boolean isWinback = cartItemVO.getProducts().getTelevisionProduct().getWinback();
					promotionQualificationItem.setProduct(createProduct(findServiceId("TTV", subscribedProductInfoRestVOList), "TV", "TTV", provinceCd, cityCd, orderTypeCd, contextTypeList, isWinback));
					promotionQualificationItem.setProductOffering(transformProductOffering(cartItemVO, request));
					if (promoInd) {
						promotionQualificationItem.setPromoCode(findPromoCode(request));
					}
					promotionQualificationItem.setContractTerm(transformProductOfferingTerm(cartItemVO.getProducts().getTelevisionProduct().getSelectedContractTermCd()));
					
					promotionQualificationItemList.put("TTV", promotionQualificationItem);
					isTvAdded = true;
				}
				// NWLN-10301 build promotionQualificationItem for CPE
				if(cartItemVO.getProducts().getAccessoryProduct() != null) {
					if(promotionQualificationItemList.containsKey("CPE")) {
						// Already has accessory product, support multiple CPE offer in shopping cart, only add product offering to list
						promotionQualificationItem = promotionQualificationItemList.get("CPE");
						if(promotionQualificationItem.getProductOffering() != null) {
							promotionQualificationItem.getProductOffering().addAll(transformProductOffering(cartItemVO, request));
						}else {
							promotionQualificationItem.setProductOffering(transformProductOffering(cartItemVO, request));
						}
					}else {
						promotionQualificationItem = new PromotionQualificationItem();
						String orderTypeCd =  computeCpeOrderTypeCd(cartItemVO.getProducts().getAccessoryProduct(), 
								subscribedProductInfoRestVOList, 
								request.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId()));
						Boolean isWinback = cartItemVO.getProducts().getAccessoryProduct().getWinback();
						promotionQualificationItem.setProduct(createProduct(findServiceId("CPE", subscribedProductInfoRestVOList), "Accessory", "CPE", provinceCd, cityCd, orderTypeCd, contextTypeList, isWinback));
						promotionQualificationItem.setProductOffering(transformProductOffering(cartItemVO, request));
						if (promoInd) {
							promotionQualificationItem.setPromoCode(findPromoCode(request));
						}
						promotionQualificationItem.setContractTerm(transformProductOfferingTerm(cartItemVO.getProducts().getAccessoryProduct().getSelectedContractTermCd()));
						
						promotionQualificationItemList.put("CPE", promotionQualificationItem);
					}
					isAccessoryAdded = true;
				}
			}
		}
		
		for(SubscribedProductInfoRestVO subscribedProductInfoRestVO : subscribedProductInfoRestVOList) {

			if(!isHomePhoneAdded && "SING".equals(subscribedProductInfoRestVO.getProductTypeCd())){
				promotionQualificationItem = new PromotionQualificationItem();
				promotionQualificationItem.setProduct(createProduct(findServiceId("SING", subscribedProductInfoRestVOList), "Home Phone", "SING", provinceCd, cityCd, null, null, null));
				promotionQualificationItemList.put("SING", promotionQualificationItem);
			}
			
			if(!isTvAdded && "TTV".equals(subscribedProductInfoRestVO.getProductTypeCd())){
				promotionQualificationItem = new PromotionQualificationItem();
				promotionQualificationItem.setProduct(createProduct(findServiceId("TTV", subscribedProductInfoRestVOList), "TV", "TTV", provinceCd, cityCd, null, null, null));
				promotionQualificationItemList.put("TTV", promotionQualificationItem);
			}
			
			if(!isInternetAdded && "HSIC".equals(subscribedProductInfoRestVO.getProductTypeCd())){
				promotionQualificationItem = new PromotionQualificationItem();
				promotionQualificationItem.setProduct(createProduct(findServiceId("HSIC", subscribedProductInfoRestVOList), "Internet", "HSIC", provinceCd, cityCd, null, null, null));
				promotionQualificationItemList.put("HSIC", promotionQualificationItem);
			}
			
			if(!isAccessoryAdded && "CPE".equals(subscribedProductInfoRestVO.getProductTypeCd())){
				promotionQualificationItem = new PromotionQualificationItem();
				promotionQualificationItem.setProduct(createProduct(findServiceId("CPE", subscribedProductInfoRestVOList), "Accessory", "CPE", provinceCd, cityCd, null, null, null));
				promotionQualificationItemList.put("CPE", promotionQualificationItem);
			}
		}
		return new ArrayList(promotionQualificationItemList.values());
	}

	private String computeHsicOrderTypeCd(InternetProductTypeVO internetProduct,
			List<SubscribedProductInfoRestVO> subscribedProductInfoRestVOList, GetSalesOfferDetailResponseVO2 getSalesOfferDetailResponseVO2) {

		String hsicOrderTypeCd = internetProduct.getProductOrderType().getOrderTypeCd();

		String offerProductCatalogueId = null;
		if(getSalesOfferDetailResponseVO2 != null 
				&& getSalesOfferDetailResponseVO2.getOffer() != null
				&& getSalesOfferDetailResponseVO2.getOffer().getOfferProduct() != null) {
			for(WirelineOfferProduct wirelineProduct : getSalesOfferDetailResponseVO2.getOffer().getOfferProduct().getWirelineOfferProductList()){
				if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
					for(ProductComponent component : wirelineProduct.getProductComponentList()) {
						for(ProductCatalogueItem productCatalogueItem: component.getProductCatalogueItemList()) {
							offerProductCatalogueId = productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
						}
					}
				}
			}
		}

		for(SubscribedProductInfoRestVO subscribedProductInfoRestVO : subscribedProductInfoRestVOList) {
			if(EnterpriseWLNSalesServicesConstants.HSIC.equals(subscribedProductInfoRestVO.getProductTypeCd())){
				for(ProductInstanceInfoRestVO existingInternetProduct : subscribedProductInfoRestVO.getProductInstance())
				if(! "0".equals(internetProduct.getSelectedContractTermCd())
						&& EnterpriseWLNSalesServicesConstants.MTM_TERM.equals(existingInternetProduct.getTermCd())
						&& offerProductCatalogueId != null
						&& offerProductCatalogueId.equals(WLNOfferUtil.getInternalCidFromProductTierCd(subscribedProductInfoRestVO.getProductTierCd())) ){
					hsicOrderTypeCd = EnterpriseWLNSalesServicesConstants.RENEWAL;
					
				}
			}
		}
		return hsicOrderTypeCd;
	}
	
	// NWLN-10301 compute CPE order type code
	private String computeCpeOrderTypeCd(AccessoryProductTypeVO accessoryProduct,
			List<SubscribedProductInfoRestVO> subscribedProductInfoRestVOList, GetSalesOfferDetailResponseVO2 getSalesOfferDetailResponseVO2) {

		String cpeOrderTypeCd = accessoryProduct.getProductOrderType().getOrderTypeCd();

		String offerProductCatalogueId = null;
		if(getSalesOfferDetailResponseVO2 != null 
				&& getSalesOfferDetailResponseVO2.getOffer() != null
				&& getSalesOfferDetailResponseVO2.getOffer().getOfferProduct() != null) {
			for(WirelineOfferProduct wirelineProduct : getSalesOfferDetailResponseVO2.getOffer().getOfferProduct().getWirelineOfferProductList()){
				if(EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
					for(ProductComponent component : wirelineProduct.getProductComponentList()) {
						for(ProductCatalogueItem productCatalogueItem: component.getProductCatalogueItemList()) {
							offerProductCatalogueId = productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
						}
					}
				}
			}
		}

		for(SubscribedProductInfoRestVO subscribedProductInfoRestVO : subscribedProductInfoRestVOList) {
			if(EnterpriseWLNSalesServicesConstants.CPE.equals(subscribedProductInfoRestVO.getProductTypeCd())){
				for(ProductInstanceInfoRestVO existingInternetProduct : subscribedProductInfoRestVO.getProductInstance())
				if(! "0".equals(accessoryProduct.getSelectedContractTermCd())
						&& EnterpriseWLNSalesServicesConstants.MTM_TERM.equals(existingInternetProduct.getTermCd())
						&& offerProductCatalogueId != null
						&& offerProductCatalogueId.equals(WLNOfferUtil.getInternalCidFromProductTierCd(subscribedProductInfoRestVO.getProductTierCd())) ){
					cpeOrderTypeCd = EnterpriseWLNSalesServicesConstants.RENEWAL;
					
				}
			}
		}
		return cpeOrderTypeCd;
	}

	private String computeTtvOrderTypeCd(TelevisionProductTypeVO televisionProductTypeVO,
			List<SubscribedProductInfoRestVO> subscribedProductInfoRestVOList, GetSalesOfferDetailResponseVO2 getSalesOfferDetailResponseVO2) {

		String ttvOrderTypeCd = televisionProductTypeVO.getProductOrderType().getOrderTypeCd();

		for(SubscribedProductInfoRestVO subscribedProductInfoRestVO : subscribedProductInfoRestVOList) {
			if(EnterpriseWLNSalesServicesConstants.TTV.equals(subscribedProductInfoRestVO.getProductTypeCd())){
				for(ProductInstanceInfoRestVO existingTtvProduct : subscribedProductInfoRestVO.getProductInstance())
				if(! "0".equals(televisionProductTypeVO.getSelectedContractTermCd())
						&& EnterpriseWLNSalesServicesConstants.MTM_TERM.equals(existingTtvProduct.getTermCd()) ){
					ttvOrderTypeCd = EnterpriseWLNSalesServicesConstants.RENEWAL;
					
				}
			}
		}
		return ttvOrderTypeCd;
	}
	private String findServiceId(String productTypeCd, List<SubscribedProductInfoRestVO> subscribedProductInfoRestVOList) {
		
		String serviceId = null;
		
		for(SubscribedProductInfoRestVO subscribedProductInfoRestVO : subscribedProductInfoRestVOList) {
			if(productTypeCd.equals(subscribedProductInfoRestVO.getProductTypeCd())){
				for(ProductInstanceInfoRestVO product : subscribedProductInfoRestVO.getProductInstance()) {
					serviceId = product.getServiceId();
				}
			}
		}
		return serviceId;
	}

	private List<SubscribedProductInfoRestVO> getAssignedAndPendingServiceIds(ShoppingCartContextVO request,
			CartItemVO cartItemVO) {
		List<SubscribedProductInfoRestVO> subscribedProductInfoRestVOList = new ArrayList<SubscribedProductInfoRestVO>();
		
		if(request.getAssignedAndPendingProductResponseVO() != null
				&& request.getAssignedAndPendingProductResponseVO().getSubscribedProductList() != null) {
//				&& request.getAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null
//				&& request.getAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts() != null)
			ServiceAddressVO serviceAddressVO = new ServiceAddressVO();
			serviceAddressVO.setServiceAddressId(request.getServiceAddressResponseVO().getServiceAddress().getAddressId());
			serviceAddressVO.setCityCode(request.getServiceAddressResponseVO().getServiceAddress().getMunicipalityName());
			serviceAddressVO.setProvinceCode(request.getServiceAddressResponseVO().getServiceAddress().getProvinceStateCode());
			
			List<SubscribedServiceIdentifierVO> subscribedServiceIdentifierVOList = new ArrayList<SubscribedServiceIdentifierVO>();

			for(SubscribedServiceVO existingServiceIdentifier : cartItemVO.getExistingServiceIdentifier()) {
				SubscribedServiceIdentifierVO subscribedServiceIdentifierVO = new SubscribedServiceIdentifierVO();
				subscribedServiceIdentifierVO.setServiceId(existingServiceIdentifier.getServiceId());
				subscribedServiceIdentifierVO.setServiceReferenceId(existingServiceIdentifier.getServiceReferenceId());
				
				subscribedServiceIdentifierVOList.add(subscribedServiceIdentifierVO);
			}

			//request.getAssignedAndPendingProductResponseVO().getPendingProductListByServiceAddress(serviceAddressVO, subscribedServiceList, serviceAddress)
			subscribedProductInfoRestVOList = request.getAssignedAndPendingProductResponseVO().getAssignedProductListByServiceAddressAndServiceId(serviceAddressVO, subscribedServiceIdentifierVOList);
		}
		return subscribedProductInfoRestVOList;
	}

	private PromoCodeRef findPromoCode(ShoppingCartContextVO request) {
		PromoCodeRef promoCode = new PromoCodeRef();
		for(CartItemVO cartItemVO: request.getShoppingCartVO().getCartItemList()) {
			if(cartItemVO.isGwpOrderItem()) {
				for(RelatedImmediatePromotionVO relatedPromotion : cartItemVO.getRelatedPromotionList()) {
					if(relatedPromotion.getSelectedCoupon() != null) {
						promoCode.setPromoCode(relatedPromotion.getSelectedCoupon().getId());
					}
				}
				
			} else if(cartItemVO.isSalesOrderItem() && cartItemVO.getRelatedPromotionList() != null) {
				
				for(RelatedImmediatePromotionVO relatedPromotion : cartItemVO.getRelatedPromotionList()) {
					if(relatedPromotion.getSelectedCoupon() != null) {
						if(promoCode.getPromoCode() == null)
							promoCode.setPromoCode(relatedPromotion.getSelectedCoupon().getId());
					}
				}
				if(promoCode.getPromoCode() == null && cartItemVO.getProducts().getHomePhoneProduct() != null)
					promoCode.setPromoCode(findSweetenerCouponCode(cartItemVO.getProducts().getHomePhoneProduct().getSweeteners()));
				if(promoCode.getPromoCode() == null && cartItemVO.getProducts().getInternetProduct() != null)
					promoCode.setPromoCode(findSweetenerCouponCode(cartItemVO.getProducts().getInternetProduct().getSweeteners()));
				if(promoCode.getPromoCode() == null && cartItemVO.getProducts().getTelevisionProduct() != null)
					promoCode.setPromoCode(findSweetenerCouponCode(cartItemVO.getProducts().getTelevisionProduct().getSweeteners()));
			}
		}
		
		return promoCode;
	}
	
	private String findSweetenerCouponCode(List<FFHSweetenerTypeVO> sweeteners) {
		String couponCode = null;
		if(sweeteners != null) {
			for(FFHSweetenerTypeVO sweetener : sweeteners) {
				for(RelatedImmediatePromotionVO relatedPromo : sweetener.getRelatedPromotionList()) {
					if(relatedPromo.getSelectedCoupon() != null) {
						couponCode = relatedPromo.getSelectedCoupon().getId();
					}
				}
			}
		}
		return couponCode;
	}

	private Product createProduct(String serviceId, String productName, String productTypeCd, String provinceCd, String city, String orderTypeCd, String contextTypeList, Boolean isWinback) {
		Product product = new Product();
		
		product.setId(serviceId);
		product.setName(productName);
		
		ProductSpecificationRef productSpecificationRef = new ProductSpecificationRef();
		productSpecificationRef.setId(productTypeCd);
		productSpecificationRef.setName("productTypeCd");		
		product.setProductSpecification(Arrays.asList(productSpecificationRef));
		
		PlaceRef placeRef = new PlaceRef();
		placeRef.setName("MARKET_PROVINCE");
		placeRef.setRole("MARKET_PROVINCE");
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(createCharacteristic("PROVINCE", provinceCd));
		characteristics.add(createCharacteristic("CITY", city));
		placeRef.setCharacteristics(characteristics);
		
		product.setPlace(Arrays.asList(placeRef));
		
		List<ProductCharacteristic> productCharacteristics = new ArrayList<ProductCharacteristic>();

		if(orderTypeCd != null) {
			productCharacteristics.add(createProductCharacteristic("orderType", orderTypeCd));
		}
		if(contextTypeList != null) {
			productCharacteristics.add(createProductCharacteristic("contextTypeList", contextTypeList));
		}
		if(isWinback != null) {
			productCharacteristics.add(createProductCharacteristic("winbackind", isWinback.toString()));
		}
		
		product.setCharacteristic(productCharacteristics);
		
		return product;
	}
	
	
	private List<ProductOffering> transformProductOffering(CartItemVO cartItemVO, ShoppingCartContextVO request) {
		List<ProductOffering> productOfferings = new ArrayList<ProductOffering>();
				
		if(cartItemVO.getProducts().getHomePhoneProduct() != null) {
			String singPodsTemplateId = findPodsTemplateId(request, "SING");
			productOfferings.add(createProductMarketOffering(cartItemVO, singPodsTemplateId, request, "SING"));

			for(FFHProductPlanAddOnTypeVO addOn : cartItemVO.getProducts().getHomePhoneProduct().getAddOns()) {
				productOfferings.add(createProductOffering(addOn.getProductCatalogueIdentifier(), singPodsTemplateId, "ADDON"));
			}

			for(FFHFeatureTypeVO feature : cartItemVO.getProducts().getHomePhoneProduct().getFeatures()) {
				productOfferings.add(createProductOffering(feature.getProductCatalogueIdentifier(), singPodsTemplateId, "FEATURE"));
			}

			for(FFHEquipmentTypeVO equipment : cartItemVO.getProducts().getHomePhoneProduct().getEquipments()) {
				productOfferings.add(createProductOffering(equipment.getProductCatalogueIdentifier(), singPodsTemplateId, 
						"EQUIPMENT"));
			}
		}

		if(cartItemVO.getProducts().getInternetProduct() != null) {
			String hsicPodsTemplateId = findPodsTemplateId(request, "HSIC");
			productOfferings.add(createProductMarketOffering(cartItemVO, hsicPodsTemplateId, request, "HSIC"));
			
			for(FFHProductPlanAddOnTypeVO addOn : cartItemVO.getProducts().getInternetProduct().getAddOns()) {
				productOfferings.add(createProductOffering(addOn.getProductCatalogueIdentifier(), hsicPodsTemplateId, "ADDON"));
			}

			for(FFHEquipmentTypeVO equipment : cartItemVO.getProducts().getInternetProduct().getEquipments()) {
				productOfferings.add(createProductOffering(equipment.getProductCatalogueIdentifier(), hsicPodsTemplateId, 
						"EQUIPMENT"));
			}
		}

		if(cartItemVO.getProducts().getTelevisionProduct() != null) {
			String ttvPodsTemplateId = findPodsTemplateId(request, "TTV");
			productOfferings.add(createProductMarketOffering(cartItemVO, ttvPodsTemplateId, request, "TTV"));
			
			for(FFHProductPlanAddOnTypeVO addOn : cartItemVO.getProducts().getTelevisionProduct().getAddOns()) {
				productOfferings.add(createProductOffering(addOn.getProductCatalogueIdentifier(), ttvPodsTemplateId, "ADDON"));
			}

			for(FFHEquipmentTypeVO equipment : cartItemVO.getProducts().getTelevisionProduct().getEquipments()) {
				productOfferings.add(createProductOffering(equipment.getProductCatalogueIdentifier(), ttvPodsTemplateId, 
						"EQUIPMENT"));
			}
		}
		
		// NWLN-10301 transfer CPE product offering
		if (cartItemVO.getProducts().getAccessoryProduct() != null) {
			String cpePodsTemplateId = findPodsTemplateId(request, "CPE");
			ProductOffering productOffering = createProductMarketOffering(cartItemVO, cpePodsTemplateId, request,
					"CPE");
			productOfferings.add(productOffering);

			if (cartItemVO.getProducts().getAccessoryProduct().getEquipments() != null
					&& cartItemVO.getProducts().getAccessoryProduct().getEquipments().size() > 0) {
				populateBundledProductoffering(productOffering,
						cartItemVO.getProducts().getAccessoryProduct().getEquipments(), "EQUIPMENT");
			}
		}

		return productOfferings;
	}

	private ProductOffering createProductMarketOffering(CartItemVO cartItemVO, String podsTemplateId, ShoppingCartContextVO request, String productType) {
		ProductOffering productMarketOffering = new ProductOffering();
		FFHOfferHeaderVO offerHeader = cartItemVO.getProductMarketOffering().getOfferHeader();
		
		productMarketOffering.setId(offerHeader.getOfferId());
		productMarketOffering.setSourceSystemId(offerHeader.getSystemId());
		
		if(offerHeader.getPerspectiveDate() != null) {
			productMarketOffering.setPerspectiveDate(new DateTime(offerHeader.getPerspectiveDate()).toStringZ());
		}
		
		List<ProductCharacteristic> characteristics = new ArrayList<ProductCharacteristic>();
		characteristics.add(createProductCharacteristic("OfferingGroupType", "OFFER"));
		characteristics.add(createProductCharacteristic("PODS_TEMPLATE_ID", podsTemplateId));
		
		if ("HSIC".equalsIgnoreCase(productType)) {
			final String hsicProdCatId = findHsicProductCatalogueId(request);
			if (hsicProdCatId != null) {
				characteristics.add(createProductCharacteristic("productComponentCatalogId", hsicProdCatId));
			} else {
				logger.error("no product catalogue id found in the offer for product HSIC");
			}
		}
		
		productMarketOffering.setCharacteristics(characteristics);

		return productMarketOffering;
	}

	private ProductOffering createProductOffering(ProductComponentVO productComponentVO, String singPodsTemplateId, String offeringGroupType) {
		ProductOffering productOffering = new ProductOffering();
		
		productOffering.setId(productComponentVO.getProductCatalogueId());
		productOffering.setSourceSystemId("10289");
		
		List<ProductCharacteristic> characteristics = new ArrayList<ProductCharacteristic>();
		
		characteristics.add(createProductCharacteristic("PODS_TEMPLATE_ID", singPodsTemplateId));
		characteristics.add(createProductCharacteristic("PARENT_CATALOGUE_ID", productComponentVO.getParentProductCatalogueId()));
		characteristics.add(createProductCharacteristic("OfferingGroupType", offeringGroupType));
		
		productOffering.setCharacteristics(characteristics);
		
		return productOffering;
	}
	
	//NWLN-10656 include payment option in Bundled product offering
	private void populateBundledProductoffering(ProductOffering productOffering, List<FFHEquipmentTypeVO> equipments, String offeringGroupType) {
		List<BundledProductOffering> bundledProductOfferingList = new ArrayList<BundledProductOffering>();
		
		for(FFHEquipmentTypeVO equipment: equipments) {
			BundledProductOffering bundledProductOffering = new BundledProductOffering();
			ProductOffering productOffer = new ProductOffering();
			productOffer.setId(equipment.getMaterialItemCode());
			
			List<ProductCharacteristic> characteristics = new ArrayList<ProductCharacteristic>();
			characteristics.add(createProductCharacteristic("OfferingGroupType", offeringGroupType));
			if(equipment.getPaymentOption() != null) {
				characteristics.add(createProductCharacteristic("PaymentOption", equipment.getPaymentOption().getPayOptionType()));
			}
			
			productOffer.setCharacteristics(characteristics);
			bundledProductOffering.setProductOffering(productOffer);
			bundledProductOfferingList.add(bundledProductOffering);
		}
		
		productOffering.setBundledProductOffering(bundledProductOfferingList);
	}

	private ProductOffering createProductOffering(ProductComponentVO productComponentVO, String singPodsTemplateId, String offeringGroupType, ShoppingCartContextVO request) {
		ProductOffering productOffering = createProductOffering(productComponentVO, singPodsTemplateId, offeringGroupType);
		
		productOffering.getCharacteristics().add(createProductCharacteristic("productComponentCatalogId", findHsicProductCatalogueId(request)));
		
		return productOffering;
	}
	
	private ContractTerm transformProductOfferingTerm(String selectedContractTermCd) {
		ContractTerm contractTerm = new ContractTerm();
		Quantity duration = new Quantity();
		duration.setUnits("MONTH");
		duration.setAmount(Integer.valueOf(selectedContractTermCd));
		contractTerm.setDuration(duration);
		return contractTerm;
	}

	private String buildContextTypeList(CartItemVO cartItemVO) {
		StringBuilder contextTypesStr = new StringBuilder();
		for(String contextType : cartItemVO.getCartItemContextTypeList())
		{
			contextTypesStr = contextTypesStr.length() > 0 ? contextTypesStr.append(",").append(contextType) : contextTypesStr.append(contextType);
		}
		return contextTypesStr.toString();
	}

	private List<PlaceRef> transformPlace(ServiceAddressVO serviceAddress) {
		PlaceRef place = new PlaceRef();
		
		place.setId(serviceAddress.getServiceAddressId());
		place.setName("SERVICE_ADDRESS");
		place.setRole("SERVICE_ADDRESS");
		
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		
		characteristics.add(createCharacteristic("SYSTEM_ID", "FMS"));
		characteristics.add(createCharacteristic("PROVINCE", serviceAddress.getProvinceCode()));
		characteristics.add(createCharacteristic("CITY", serviceAddress.getCityCode()));

		place.setCharacteristics(characteristics);
		
		return Arrays.asList(place);
	}

	private List<ChannelRef> transformChannel(UserProfileVO userProfileVO) {
		CommerceChannel commerceChannel = new CommerceChannel();
		
		commerceChannel.setChannelOrganizationTypeCd(userProfileVO.getChannelOrganizationTypeCd());
		commerceChannel.setChannelOrganizationInternalId(userProfileVO.getChannelOrganizationInternalId());
		commerceChannel.setChannelOrganizationNumber(userProfileVO.getChannelOrganizationNumber());
		commerceChannel.setSalesPersonRoleCd(userProfileVO.getSalesPersonRoleCd());
		commerceChannel.setSalesRepId(userProfileVO.getSalesRepId());
		commerceChannel.setSalesRepInternalId(userProfileVO.getSalesRepInternalId());
		commerceChannel.setChannelOutletProvinceCd(getOutletAssociatedProvinceCode(userProfileVO));
		commerceChannel.setChannelOutletInternalId(userProfileVO.getSalesRepAssociatedOutlet().get(0).getOutletInternalId());
		commerceChannel.setChannelOutletId(userProfileVO.getSalesRepAssociatedOutlet().get(0).getChannelOutletId());
		commerceChannel.setLoginId(userProfileVO.getLoginId());
		commerceChannel.setRole("SalesChannel_UserProfile");
		return Arrays.asList((ChannelRef)commerceChannel);
	}

	private List<RelatedPartyRef> transfromRelatedParty(ShoppingCartContextVO request, boolean isPostTask) {
		List<RelatedPartyRef> relatedPartyList = new ArrayList<RelatedPartyRef>();

		CustomerRef relatedPartyCustomer = new CustomerRef();
		relatedPartyList.add(relatedPartyCustomer);

		//QC74236 - Pass Account to related party for Anonymous customer 
		BillingAccountRef relatedPartyAccount = new BillingAccountRef();	
		relatedPartyList.add(relatedPartyAccount);
		relatedPartyAccount.setRole("ACCOUNT");

		List<Characteristic> characteristcs = new ArrayList<Characteristic>();
		relatedPartyAccount.setCharacteristic(characteristcs);
		characteristcs.add(createCharacteristic("BRAND_CD", "TELUS"));

		if(request.getShoppingCartVO().getBillingAccount() != null) {
			characteristcs.add(createCharacteristic("ACCOUNT_TYPE", request.getShoppingCartVO().getBillingAccount().getAccountTypeCd()));
			characteristcs.add(createCharacteristic("MASTER_SOURCE_CD", request.getShoppingCartVO().getBillingAccount().getAccountMasterSourceTypeCd()));
		}
		
		if(request.getShoppingCartVO().getCustomer() != null
				&& request.getShoppingCartVO().getCustomer().getCustomerId() != null
				&& ! "".equals(request.getShoppingCartVO().getCustomer().getCustomerId())
				&& (!request.getShoppingCartVO().isWirelineProspectCustomer()
						|| request.getShoppingCartVO().isCustomerCreditCompleted()
						|| isPostTask
					)
				) {
			relatedPartyCustomer.setId(request.getShoppingCartVO().getCustomer().getCustomerId());
			relatedPartyCustomer.setRole("CUSTOMER");
			
			if(request.getShoppingCartVO().getBillingAccount() != null) {
				relatedPartyAccount.setId(request.getShoppingCartVO().getBillingAccount().getBillingAccountNumber());
			}

		} else {
			relatedPartyCustomer.setRole("ANONYMOUS_CUSTOMER");
		}

		return relatedPartyList;
	}

	private Characteristic createCharacteristic(String name, String value) {
		Characteristic characteristic = new Characteristic();
		
		characteristic.setName(name);
		characteristic.setValue(value);
		
		return characteristic;
	}

	private ProductCharacteristic createProductCharacteristic(String name, String value) {
		ProductCharacteristic productCharacteristic = new ProductCharacteristic();
		
		productCharacteristic.setName(name);
		productCharacteristic.setValue(value);
		
		return productCharacteristic;
	}

	private String findPodsTemplateId(ShoppingCartContextVO request, String productTypeCode) {
		String podsTemplateId = null;

		for(GetSalesOfferDetailResponseVO2 offer :request.getOfferList()) {
			if (offer.getOffer() != null) {
				for (WirelineOfferProduct wirelineOffer : offer.getOffer().getOfferProduct().getWirelineOfferProductList()){
					if(productTypeCode.equals(wirelineOffer.getProductTypeCode())){
						if(wirelineOffer.getProductTemplateIdentifier() != null)
							podsTemplateId = wirelineOffer.getProductTemplateIdentifier().getProductCatalogueId();
					}
				}
			}
		}
		return podsTemplateId;
	}
	
	private String findHsicProductCatalogueId(ShoppingCartContextVO request) {
		String result = null;

		for (final GetSalesOfferDetailResponseVO2 offer : request.getOfferList()) {
			if (offer.getOffer() != null) {
				for (final WirelineOfferProduct elem : offer.getOffer().getOfferProduct().getWirelineOfferProductList()) {
					if ("HSIC".equals(elem.getProductTypeCode())) {
						if (elem.getProductComponentList().size() > 0 && 
								elem.getProductComponentList().get(0).getProductCatalogueItemList().size() > 0 &&
								elem.getProductComponentList().get(0).getProductCatalogueItemList().get(0).getProductCatalogueIdentifier() != null) {
							result = elem.getProductComponentList().get(0).getProductCatalogueItemList().get(0).getProductCatalogueIdentifier().getProductCatalogueId();
						}
					}
				}
			}
		}
		
		return result;
	}
	
	String getOutletAssociatedProvinceCode(UserProfileVO userProfileVO) {
		String provinceCd = null;
		
		for(String outletAssociatedProvince : userProfileVO.getOutletAssociatedProvinces()) {
			provinceCd = outletAssociatedProvince;
		}
		return provinceCd;
	}
	
	// Add Sweeteners from Commerce Shopping Cart to SweetenerOfferSummaryList
	// when PriceVO from FFHSwetenerTypeVO is not null
	private void addSweetenersFromCommerceSC(ShoppingCartContextVO request,
			PromotionsVO promotionsVO) {
		for (CartItemVO cartItem : request.getShoppingCartVO().getCartItemList()) {
			ProductTypeVO products = cartItem.getProducts();
			if (products != null) {
				if (products.getHomePhoneProduct() != null) {
					if (products.getHomePhoneProduct().getSweeteners() != null
							&& CollectionUtils.isNotEmpty(products.getHomePhoneProduct().getSweeteners())) {
						for (FFHSweetenerTypeVO sweetener : products.getHomePhoneProduct().getSweeteners()) {
							if (sweetener.getPrice() != null) {
								checkForNullorEmptySweetenerLists(promotionsVO);
								// prepare SweetenerOfferSummary
								SweetenerOfferSummary sweetenerOfferSummary = new SweetenerOfferSummary();
								// prepare WirelineOfferProductSummary
								WirelineOfferProductSummary wirelineOfferProductSummary = new WirelineOfferProductSummary();
								wirelineOfferProductSummary
										.setProductTypeCode(products.getHomePhoneProduct().getProductTypeCd());
								// prepare Discount list
								List<Discount> discountList = new ArrayList<Discount>();
								Discount discount = new Discount();
								discount.setIncludedInd(true);
								// prepare Description
								List<Description> descriptionList = new ArrayList<Description>();
								if (sweetener.getRelatedPromotionList() != null
										&& CollectionUtils.isNotEmpty(sweetener.getRelatedPromotionList())) {
									for (RelatedImmediatePromotionVO relatedImmediatePromotion : sweetener
											.getRelatedPromotionList()) {
										Description description = new Description();
										description.setLocale("EN");
										description.setDescriptionText(relatedImmediatePromotion.getName());
										descriptionList.add(description);
									}
								}
								if (descriptionList != null && CollectionUtils.isNotEmpty(descriptionList)) {
									discount.setMarketingDescriptionList(descriptionList);
								}
								// prepare discountProductCatalogueItemList
								List<DiscountProductCatalogueItem> discountProductCatalogueItemList = new ArrayList<DiscountProductCatalogueItem>();
								DiscountProductCatalogueItem discountProductCatalogueItem = new DiscountProductCatalogueItem();
								// set Discount Product Catalogue Item
								discountProductCatalogueItem.setDiscountPriceAmt(
										(double) sweetener.getPrice().getBasePriceAmount().getValueAmt());
								discountProductCatalogueItem.setRecurringCount(
										BigInteger.valueOf(sweetener.getPrice().getRecurrenceCount()));
								// prepare discountType
								DiscountType discountType = new DiscountType();
								discountType.setDiscountTypeCode(sweetener.getPrice().getPricingTypeCd());
								discountProductCatalogueItem.setDiscountType(discountType);															
								discountProductCatalogueItemList.add(discountProductCatalogueItem);

								discount.setDiscountProductCatalogueItemList(discountProductCatalogueItemList);

								// prepare Product catalogue Item list
								List<ProductCatalogueItem> productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
								for (FFHDiscountTypeVO fhhDiscountType : sweetener.getDiscounts()) {
									if(fhhDiscountType.getProductCatalogueIdentifiers()!= null && 
										CollectionUtils.isNotEmpty(fhhDiscountType.getProductCatalogueIdentifiers())) {
										for( ProductComponentVO productComponent: fhhDiscountType.getProductCatalogueIdentifiers()) {
											ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
											ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier();
											ProductCatalogueIdentifier parentProductCatalogueIdentifier = new ProductCatalogueIdentifier();
											
											productCatalogueIdentifier.setProductCatalogueId(productComponent.getProductCatalogueId());
											parentProductCatalogueIdentifier.setProductCatalogueId(productComponent.getParentProductCatalogueId());
											
											productCatalogueItem.setProductCatalogueIdentifier(productCatalogueIdentifier);
											productCatalogueItem.setParentProductCatalogueIdentifier(parentProductCatalogueIdentifier);
											
											productCatalogueItemList.add(productCatalogueItem);
											
										}
								    }								
								}

								if ( productCatalogueItemList != null && CollectionUtils.isNotEmpty(productCatalogueItemList)) {
									discount.setProductCatalogueItemList(productCatalogueItemList);
								}
								
								
								// add discount to discountList
								discountList.add(discount);

								// set discountList to WirelineOfferProductSummary
								wirelineOfferProductSummary.setDiscountList(discountList);

								// set wirelineOfferProductSummaryList
								List<WirelineOfferProductSummary> wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();
								if (wirelineOfferProductSummary != null) {

									wirelineOfferProductSummaryList.add(wirelineOfferProductSummary);
								}
								// set offerProductSummary
								OfferProductSummary offerProductSummary = new OfferProductSummary();

								if (wirelineOfferProductSummaryList != null
										&& CollectionUtils.isNotEmpty(wirelineOfferProductSummaryList)) {
									offerProductSummary
											.setWirelineOfferProductSummaryList(wirelineOfferProductSummaryList);
								}

								if (offerProductSummary != null) {
									sweetenerOfferSummary.setOfferProductSummary(offerProductSummary);
								}

								// Add sweetenerOfferSummary to
								// PromotionVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList()
								if (sweetenerOfferSummary != null) {
									promotionsVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList()
											.add(sweetenerOfferSummary);
								}
							}

						}
					}

                    // Internet
					if (products.getInternetProduct().getSweeteners() != null
							&& CollectionUtils.isNotEmpty(products.getInternetProduct().getSweeteners())) {
						for (FFHSweetenerTypeVO sweetener : products.getInternetProduct().getSweeteners()) {
							if (sweetener.getPrice() != null) {
								checkForNullorEmptySweetenerLists(promotionsVO);
								// prepare SweetenerOfferSummary
								SweetenerOfferSummary sweetenerOfferSummary = new SweetenerOfferSummary();
								// prepare WirelineOfferProductSummary
								WirelineOfferProductSummary wirelineOfferProductSummary = new WirelineOfferProductSummary();
								wirelineOfferProductSummary.setProductTypeCode(null);
								// prepare Discount list
								List<Discount> discountList = new ArrayList<Discount>();
								Discount discount = new Discount();
								discount.setIncludedInd(true);
								// prepare Description
								List<Description> descriptionList = new ArrayList<Description>();
								if (sweetener.getRelatedPromotionList() != null
										&& CollectionUtils.isNotEmpty(sweetener.getRelatedPromotionList())) {
									for (RelatedImmediatePromotionVO relatedImmediatePromotion : sweetener
											.getRelatedPromotionList()) {
										Description description = new Description();
										description.setLocale("EN");
										description.setDescriptionText(relatedImmediatePromotion.getName());
										descriptionList.add(description);
									}
								}
								if (descriptionList != null && CollectionUtils.isNotEmpty(descriptionList)) {
									discount.setMarketingDescriptionList(descriptionList);
								}
								// prepare discountProductCatalogueItemList
								List<DiscountProductCatalogueItem> discountProductCatalogueItemList = new ArrayList<DiscountProductCatalogueItem>();
								DiscountProductCatalogueItem discountProductCatalogueItem = new DiscountProductCatalogueItem();
								discountProductCatalogueItem.setDiscountPriceAmt(
										(double) sweetener.getPrice().getBasePriceAmount().getValueAmt());
								discountProductCatalogueItem.setRecurringCount(
										BigInteger.valueOf(sweetener.getPrice().getRecurrenceCount()));
								// prepare discountType
								DiscountType discountType = new DiscountType();
								discountType.setDiscountTypeCode(sweetener.getPrice().getPricingTypeCd());
								discountProductCatalogueItem.setDiscountType(discountType);
								discountProductCatalogueItemList.add(discountProductCatalogueItem);

								discount.setDiscountProductCatalogueItemList(discountProductCatalogueItemList);

								// prepare Product catalogue Item list
								List<ProductCatalogueItem> productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
								for (FFHDiscountTypeVO fhhDiscountType : sweetener.getDiscounts()) {
									if(fhhDiscountType.getProductCatalogueIdentifiers()!= null && 
										CollectionUtils.isNotEmpty(fhhDiscountType.getProductCatalogueIdentifiers())) {
										for( ProductComponentVO productComponent: fhhDiscountType.getProductCatalogueIdentifiers()) {
											ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
											ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier();
											ProductCatalogueIdentifier parentProductCatalogueIdentifier = new ProductCatalogueIdentifier();
											
											productCatalogueIdentifier.setProductCatalogueId(productComponent.getProductCatalogueId());
											parentProductCatalogueIdentifier.setProductCatalogueId(productComponent.getParentProductCatalogueId());
											
											productCatalogueItem.setProductCatalogueIdentifier(productCatalogueIdentifier);
											productCatalogueItem.setParentProductCatalogueIdentifier(parentProductCatalogueIdentifier);
											
											productCatalogueItemList.add(productCatalogueItem);
											
										}
								    }								
								}

								if ( productCatalogueItemList != null && CollectionUtils.isNotEmpty(productCatalogueItemList)) {
									discount.setProductCatalogueItemList(productCatalogueItemList);
								}
								
								// add discount to discountList
								discountList.add(discount);

								// set discountList to WirelineOfferProductSummary
								wirelineOfferProductSummary.setDiscountList(discountList);

								// set wirelineOfferProductSummaryList
								List<WirelineOfferProductSummary> wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();
								if (wirelineOfferProductSummary != null) {

									wirelineOfferProductSummaryList.add(wirelineOfferProductSummary);
								}
								// set offerProductSummary
								OfferProductSummary offerProductSummary = new OfferProductSummary();

								if (wirelineOfferProductSummaryList != null
										&& CollectionUtils.isNotEmpty(wirelineOfferProductSummaryList)) {
									offerProductSummary
											.setWirelineOfferProductSummaryList(wirelineOfferProductSummaryList);
								}

								if (offerProductSummary != null) {
									sweetenerOfferSummary.setOfferProductSummary(offerProductSummary);
								}

								// Add sweetenerOfferSummary to
								// PromotionVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList()
								if (sweetenerOfferSummary != null) {
									promotionsVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList()
											.add(sweetenerOfferSummary);
								}
							}

						}
					}

					// TTV 

					if (products.getTelevisionProduct().getSweeteners() != null
							&& CollectionUtils.isNotEmpty(products.getTelevisionProduct().getSweeteners())) {
						for (FFHSweetenerTypeVO sweetener : products.getTelevisionProduct().getSweeteners()) {
							if (sweetener.getPrice() != null) {
								checkForNullorEmptySweetenerLists(promotionsVO);
								// prepare SweetenerOfferSummary
								SweetenerOfferSummary sweetenerOfferSummary = new SweetenerOfferSummary();
								// prepare WirelineOfferProductSummary
								WirelineOfferProductSummary wirelineOfferProductSummary = new WirelineOfferProductSummary();
								wirelineOfferProductSummary.setProductTypeCode(null);
								// prepare Discount list
								List<Discount> discountList = new ArrayList<Discount>();
								Discount discount = new Discount();
								discount.setIncludedInd(true);
								// prepare Description
								List<Description> descriptionList = new ArrayList<Description>();
								if (sweetener.getRelatedPromotionList() != null
										&& CollectionUtils.isNotEmpty(sweetener.getRelatedPromotionList())) {
									for (RelatedImmediatePromotionVO relatedImmediatePromotion : sweetener
											.getRelatedPromotionList()) {
										Description description = new Description();
										description.setLocale("EN");
										description.setDescriptionText(relatedImmediatePromotion.getName());
										descriptionList.add(description);
									}
								}
								if (descriptionList != null && CollectionUtils.isNotEmpty(descriptionList)) {
									discount.setMarketingDescriptionList(descriptionList);
								}
								// prepare discountProductCatalogueItemList
								List<DiscountProductCatalogueItem> discountProductCatalogueItemList = new ArrayList<DiscountProductCatalogueItem>();
								DiscountProductCatalogueItem discountProductCatalogueItem = new DiscountProductCatalogueItem();
								discountProductCatalogueItem.setDiscountPriceAmt(
										(double) sweetener.getPrice().getBasePriceAmount().getValueAmt());
								discountProductCatalogueItem.setRecurringCount(
										BigInteger.valueOf(sweetener.getPrice().getRecurrenceCount()));
								// prepare discountType
								DiscountType discountType = new DiscountType();
								discountType.setDiscountTypeCode(sweetener.getPrice().getPricingTypeCd());
								discountProductCatalogueItem.setDiscountType(discountType);
								discountProductCatalogueItemList.add(discountProductCatalogueItem);

								discount.setDiscountProductCatalogueItemList(discountProductCatalogueItemList);

								// prepare Product catalogue Item list
								List<ProductCatalogueItem> productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
								for (FFHDiscountTypeVO fhhDiscountType : sweetener.getDiscounts()) {
									if(fhhDiscountType.getProductCatalogueIdentifiers()!= null && 
										CollectionUtils.isNotEmpty(fhhDiscountType.getProductCatalogueIdentifiers())) {
										for( ProductComponentVO productComponent: fhhDiscountType.getProductCatalogueIdentifiers()) {
											ProductCatalogueItem productCatalogueItem = new ProductCatalogueItem();
											ProductCatalogueIdentifier productCatalogueIdentifier = new ProductCatalogueIdentifier();
											ProductCatalogueIdentifier parentProductCatalogueIdentifier = new ProductCatalogueIdentifier();
											
											productCatalogueIdentifier.setProductCatalogueId(productComponent.getProductCatalogueId());
											parentProductCatalogueIdentifier.setProductCatalogueId(productComponent.getParentProductCatalogueId());
											
											productCatalogueItem.setProductCatalogueIdentifier(productCatalogueIdentifier);
											productCatalogueItem.setParentProductCatalogueIdentifier(parentProductCatalogueIdentifier);
											
											productCatalogueItemList.add(productCatalogueItem);
											
										}
								    }								
								}

								if ( productCatalogueItemList != null && CollectionUtils.isNotEmpty(productCatalogueItemList)) {
									discount.setProductCatalogueItemList(productCatalogueItemList);
								}
								
								// add discount to discountList
								discountList.add(discount);

								// set discountList to WirelineOfferProductSummary
								wirelineOfferProductSummary.setDiscountList(discountList);

								// set wirelineOfferProductSummaryList
								List<WirelineOfferProductSummary> wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();
								if (wirelineOfferProductSummary != null) {

									wirelineOfferProductSummaryList.add(wirelineOfferProductSummary);
								}
								// set offerProductSummary
								OfferProductSummary offerProductSummary = new OfferProductSummary();

								if (wirelineOfferProductSummaryList != null
										&& CollectionUtils.isNotEmpty(wirelineOfferProductSummaryList)) {
									offerProductSummary
											.setWirelineOfferProductSummaryList(wirelineOfferProductSummaryList);
								}

								if (offerProductSummary != null) {
									sweetenerOfferSummary.setOfferProductSummary(offerProductSummary);
								}

								// Add sweetenerOfferSummary to
								// PromotionVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList()
								if (sweetenerOfferSummary != null) {
									promotionsVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList()
											.add(sweetenerOfferSummary);
								}
							}

						}
					}
					
				}			
			}			
		}
	}
	
	private void checkForNullorEmptySweetenerLists(PromotionsVO promotionsVO) {
		if (promotionsVO.getSweetenerOfferListResponse() == null) {
			promotionsVO.setSweetenerOfferListResponse(new GetSweetenerOfferListResponseVO2());
			promotionsVO.getSweetenerOfferListResponse().setSweetenerOfferSummaryList(new ArrayList<SweetenerOfferSummary>());
		} else if (promotionsVO.getSweetenerOfferListResponse().getSweetenerOfferSummaryList() == null) {
			promotionsVO.getSweetenerOfferListResponse().setSweetenerOfferSummaryList(new ArrayList<SweetenerOfferSummary>());
		}
	}
}
