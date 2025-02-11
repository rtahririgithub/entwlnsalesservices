package com.telus.csm.ewlnsc.delegate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.ProductInstanceMapper;
import com.telus.csm.ewlnsc.helper.ProductSpecificationMapper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.AccessCriteria;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.Message;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductInstanceList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductSpecificationList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessage;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessageList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;

import weblogic.wsee.util.StringUtil;

/**
 * @author Jose.Mena
 *
 */
public class CheckProductFeasibilityDelegate {

	private static final LoggerUtil logger = LoggerUtil.getLogger(CheckProductFeasibilityDelegate.class);

	public CheckProductFeasibilityAdapterResponse checkProductFeasibility(SalesOfferCommonVO commonVO) {
		
		String functionName = "checkProductFeasibility";
		logger.enter(functionName);
		SalesOfferCriteriaVO salesOfferCriteria = commonVO.getOffersRequestVO().getSalesOfferCriteria();
		ServiceAddressResponseVO serviceAddress = commonVO.getServiceAddressResponseVO();
		GetAssignedAndPendingProductResponseVO consolidatedAccProfileResp = commonVO.getAssignedAndPendingProductsResponseVO();
		GetProductQualificationAdapterResponse availableProducts = commonVO.getProductQualificationAdapterResponseVO();
		OperationHeader operationHeader = commonVO.getOffersRequestVO().getOperationHeader();
		
		if (commonVO != null) {
			if (commonVO.getOffersRequestVO() != null) {
				salesOfferCriteria = commonVO.getOffersRequestVO().getSalesOfferCriteria();
				operationHeader = commonVO.getOffersRequestVO().getOperationHeader();
			}
			serviceAddress = commonVO.getServiceAddressResponseVO();
			consolidatedAccProfileResp = commonVO.getAssignedAndPendingProductsResponseVO();
			availableProducts = commonVO.getProductQualificationAdapterResponseVO();
		}
		
		WirelineOfferFilter offerFilter = new WirelineOfferFilter();
		ServiceAddressVO serviceAddressInRequest = null;
		List<SubscribedServiceIdentifierVO> subscribedServiceIdentifier = null;
		String customerId = "";
		if (salesOfferCriteria != null) {
			offerFilter = salesOfferCriteria.getOfferFilter();
			customerId = salesOfferCriteria.getCustomerId();
			serviceAddressInRequest = salesOfferCriteria.getServiceAddress();
			subscribedServiceIdentifier = salesOfferCriteria.getSubscribedServiceIdentifier();
		}
		
		CheckProductFeasibilityAdapterResponse response = new CheckProductFeasibilityAdapterResponse();

		//get existingProducts
		List<SubscribedProductInfoRestVO> existingProducts = new ArrayList<SubscribedProductInfoRestVO>();
		populateExistingProductsList(consolidatedAccProfileResp, serviceAddressInRequest, subscribedServiceIdentifier,
				existingProducts,serviceAddress);
		
		// 2018 Aug release for Pik TV
		// Pik TV - looking for existing Pik TV
		boolean isCustomerHasPikTV = WLNOfferUtil.isPresentPikTv(consolidatedAccProfileResp, commonVO.getOffersRequestVO().getSalesOfferCriteria());
		
		boolean isTriggerCheckFeasibility = true;
		// Validate serviceAddress (It is mandatory)
		if (serviceAddress == null || serviceAddress.getServiceAddress() == null) {
			response.setResponseMessageList(generateMessageListForFeasibility(operationHeader.getSalesTransactionId(), SERVICE_ADDRESS_IS_MANDATORY));
			return response;
		}
		
		// Logic before calling feasibility service operation
		if (availableProducts==null || availableProducts.getProductQualificationList() == null
				|| availableProducts.getProductQualificationList().isEmpty()) {
			response.setResponseMessageList(generateMessageListForFeasibility(operationHeader.getSalesTransactionId(), PRODUCT_QUALIFICATION_LIST_IS_EMPTY));
			return response;
		}

		// Determine which products to send in request to checkProductFeasibility() service operation
		Boolean addAllProducts = false;
		
		Boolean requestContainsHSIC = false;
		Boolean requestContainsTTV = false;
		Boolean requestContainsSING = false;
		boolean isRequestHSICOptik = false;

		// NWLN-7598 - move the variable out from the if loop so it can be used for both Join and Split offer scenario
		OfferProductHeader requestHSICProduct = null;
		String requestHsicProductCatalogueIdentifier = "";
		String requestHsicTierCode = "";
		
		Deque<Product> sortedHSICProducts = new ArrayDeque<Product>();
		Deque<Product> sortedTTVProducts = new ArrayDeque<Product>();
		Deque<Product> sortedSINGProducts = new ArrayDeque<Product>();
		
		addAllProducts = isAddAllProducts(offerFilter);
		if (!addAllProducts) {
			for (OfferProductHeader product : offerFilter.getProductList()) {
				if (HSIC.equalsIgnoreCase(product.getProductTypeCd())) {
					requestContainsHSIC = true;
					requestHSICProduct = product;


					
				}
				if (TTV.equalsIgnoreCase(product.getProductTypeCd())) {
					requestContainsTTV = true;
				}
				if (SING.equalsIgnoreCase(product.getProductTypeCd())) {
					requestContainsSING = true;
				}
			}
			
			if(requestContainsTTV){
				if(requestContainsHSIC){
					// Joined Offer
					// 2018 Aug release for Pik TV
					// In order to determine the type of TV Product is Pik TV or TTV, we need to identify based on the HSIC type
					// If HSIC is Optik HSIC, if there is TV Product, that TV product is Optik TV
					// If HSIC is NOT Optik HSIC, if there is TV Product, that TV product is NOT Optik TV, which would be Pik TV
					if(requestHSICProduct.getProductOrderType() != null && 
							OIS_PRODUCT_INSTANCE_NOCHANGE.equalsIgnoreCase(requestHSICProduct.getProductOrderType().getProductOrderTypeCd()) &&
							(requestHSICProduct.getProductComponentList().isEmpty() || 
									(!requestHSICProduct.getProductComponentList().isEmpty() && 
									StringUtil.isEmpty(requestHSICProduct.getProductComponentList().get(0).getProductCatalogueIdentifier())))){
						for(SubscribedProductInfoRestVO existingProduct: existingProducts){
							if (existingProduct.getServiceAddress() != null && serviceAddress != null 
									&& ProductSpecificationMapper.addressesAreEqual(existingProduct.getServiceAddress(), serviceAddress)
									&& existingProduct.getProductTypeCd() != null
									&& existingProduct.getProductTypeCd().trim().equalsIgnoreCase(HSIC)) {
								// ProductType matched
								isRequestHSICOptik = ProductSpecificationMapper.isOptik(existingProduct.getProductTierCd());
							}
						}
					} else {
						if(!requestHSICProduct.getProductComponentList().isEmpty()){
							requestHsicProductCatalogueIdentifier = requestHSICProduct.getProductComponentList().get(0).getProductCatalogueIdentifier();
						}
						
						CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
						CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(requestHsicProductCatalogueIdentifier);
						
						if (catalogueItemDO != null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())) {
							requestHsicTierCode = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
							isRequestHSICOptik = ProductSpecificationMapper.isOptik(requestHsicTierCode);
						}
					}
				} else {
					// Split Offer
					// NWLN-7598 - use the same logic used for Joined Offer to find out is the existing customer have optik hsic when request didn't pass the TTV product catalogueidentifier
					for(SubscribedProductInfoRestVO existingProduct: existingProducts){
						if (existingProduct.getServiceAddress() != null && serviceAddress != null 
								&& ProductSpecificationMapper.addressesAreEqual(existingProduct.getServiceAddress(), serviceAddress)
								&& existingProduct.getProductTypeCd() != null
								&& existingProduct.getProductTypeCd().trim().equalsIgnoreCase(HSIC)) {
							// ProductType matched
							isRequestHSICOptik = ProductSpecificationMapper.isOptik(existingProduct.getProductTierCd());
						}
					}
				}
			}
			
			
		}
		
//		1. productQualification Response => all the products should be availalble 
//		2. crose check the products from request => the product should be in the productList or productList is null 
		//
		// Determine if we have to filter out: Regular or Optik
		boolean isTTVQualified = WLNOfferUtil.isProductQualified(TTV, availableProducts);
		
		// 2018 Aug release for Pik TV
		// find MaxTvProfile
		String maxTvProfile = WLNOfferUtil.getMaxTvProfile(availableProducts);
		Boolean filterOutRegular = false;
		boolean noCallToFeasibility = false;
		boolean isRequestConstainsTVX = false;
//		if (requestContainsTTV || addAllProducts) {
//			filterOutRegular = true;
//		}
		// Next if condition is based on Allan's and team pseudo-code
		if (addAllProducts) {
			if (isTTVQualified) {
				// 2018 Aug release for Pik TV
				// if existing customer has Pik TV, filter out optic internet
				if(isCustomerHasPikTV){
					// Existing Pik TV
					filterOutRegular = false;
				} else {
					if(TVX.equalsIgnoreCase(maxTvProfile)){
						// TVX
						filterOutRegular = false;
					} else {
						// TTV
						filterOutRegular = true;
					}
				}
			} else {
				filterOutRegular = false;
			}
		} else if (requestContainsTTV) { //allow the split offer to follow the join offer logic
			if (isTTVQualified) {
				if(isRequestHSICOptik){
					// TTV
					filterOutRegular = true;
				} else {
					// TVX
					filterOutRegular = false;
					isRequestConstainsTVX = true;
				}
			} else {
				// no call to feasibility service
				noCallToFeasibility = true;
			}
		}
		
		if (!noCallToFeasibility) {
			//
			for (ProductQualification availableProduct : availableProducts.getProductQualificationList()) {
				if (isProductListEmpty(availableProduct)) {
					continue;
				}

				// extract HSIC products from available products, and sort them based on their ranks
				sortedHSICProducts = new ArrayDeque<Product>();
				sortedTTVProducts = new ArrayDeque<Product>();
				sortedSINGProducts = new ArrayDeque<Product>();	
				
				Map<Long, Product> hsicProductMap = new HashMap<Long, Product>();
				Map<Long, Product> ttvProductMap = new HashMap<Long, Product>();
				Map<Long, Product> singProductMap = new HashMap<Long, Product>();
				for (Product prod : availableProduct.getProductList()) {
					// skip unqualified products
					if (isProdUnavailable(prod)) {
						continue;
					}

					//TODODONE: Filter out Optik plans if TTV product is unqualified or ommitted from offerFilter.
					//TODODONE:  Filter out non optik plans if TTV product is qualified and included in offerFilter.
					// WSS_PRODUCT_OPTIK 
					// tododone: Treat empty offerFilter as ALL products should be included.
					
					String productTypeCd = prod.getProductTypeCd();
					
					if (shouldIncludeCurrentProduct(addAllProducts, requestContainsHSIC, HSIC, productTypeCd, prod, filterOutRegular)) {
						hsicProductMap.put(Long.valueOf(prod.getProductRanking()), prod);
					} else if (shouldIncludeCurrentProduct(addAllProducts, requestContainsTTV, TTV, productTypeCd, prod, filterOutRegular)) {
						// 2018 Aug release for Pik TV
						// if existing customer has Pik TV, only add TVX to the ttvProductMap for FeasibilityService
						if(isCustomerHasPikTV){
							// Existing Pik TV
							if(prod.getProductTierCd() != null && prod.getProductTierCd().equals(PRODUCT_TIER_CD_TVX)){
								ttvProductMap.put(Long.valueOf(prod.getProductRanking()), prod);
							}
						} else {
							ttvProductMap.put(Long.valueOf(prod.getProductRanking()), prod);
						}
					} else if (shouldIncludeCurrentProduct(addAllProducts, requestContainsSING, SING, productTypeCd, prod, filterOutRegular)) {
						singProductMap.put(Long.valueOf(prod.getProductRanking()), prod);
					}
				}

				// skip feasibility check if there is no HS product qualified
				if (hsicProductMap.isEmpty()) {
					response.setResponseMessageList(generateMessageListForFeasibility(operationHeader.getSalesTransactionId(), SKIP_FEASIBILITY_IF_NO_HS_PRODUCT_QUALIFIED));
					logger.info(functionName, SKIP_FEASIBILITY_IF_NO_HS_PRODUCT_QUALIFIED);
					isTriggerCheckFeasibility = false;
				} else {
					sortAllAvailableProductsByRank(sortedHSICProducts, sortedTTVProducts, sortedSINGProducts,
							hsicProductMap, ttvProductMap, singProductMap);
				}

			}
//			TTV - one or none => this week => cross check the existing proudct and determine the txnsction type = crete or modify. // TOASK: What to do with "the txnsction type = crete or modify" here ?
//			SING - one or nonn
//			HSIC - list with Regular and Optik => pop (one hsic )
//			
//			Here comes the logic
//			if TTV avaialble from productQualification and (TTV in ESS Request or prouctLsit is empty)=> filter out Regular
//			else TTV not available or not in the productList (productList not null)=> filter out the optik
//			
//			Now list of optick or Regular => pick the highest ranking one
			
			if (isTriggerCheckFeasibility) {
				
				List<Product> productsToCheck = populateProducts(sortedHSICProducts, sortedTTVProducts, sortedSINGProducts, commonVO.getHsicTierCodeForRequestedProduct(), isRequestConstainsTVX);
			
				response = callFeasibilityService(serviceAddress, existingProducts, availableProducts.getProductQualificationList().get(0), operationHeader, customerId, productsToCheck);
			}
		} else {
			// no call to feasibility
			response.setResponseMessageList(generateMessageListForFeasibility(operationHeader.getSalesTransactionId(), SKIP_FEASIBILITY_IF_NO_TTV_QUALIFIED));
			logger.info(functionName, SKIP_FEASIBILITY_IF_NO_TTV_QUALIFIED);
		}
		logger.exit(functionName);
		return response;
	}

	/**
	 * @param addAllProducts
	 * @param requestContainsProduct
	 * @param constantProductTypeCd
	 * @param productTypeCd
	 * @param prod
	 * @param filterOutRegular
	 * @return
	 */
	private boolean shouldIncludeCurrentProduct(Boolean addAllProducts, boolean requestContainsProduct,
			String constantProductTypeCd, String productTypeCd, Product prod, Boolean filterOutRegular) {
		boolean includeProductCond1 = includeThisSpecificProd(addAllProducts, requestContainsProduct, constantProductTypeCd, productTypeCd, prod);
		if (HSIC.equalsIgnoreCase(constantProductTypeCd)) {
			return includeProductCond1 && filterProduct(filterOutRegular, prod.getProductTierCd()); // ensures optik and non-optik (Regular) are filtered out accordingly.
		} else {
			return includeProductCond1;
		}
	}

	/**
	 * @param addAllProducts
	 * @param requestContainsProduct
	 * @param constantProductTypeCd: eg.: HSIC, TTV, SING
	 * @param productTypeCd
	 * @param prod
	 * @return
	 */
	private boolean includeThisSpecificProd(Boolean addAllProducts, boolean requestContainsProduct,
			String constantProductTypeCd, String productTypeCd, Product prod) {
		// For TTV productTierCode must not be TVX //NWLN-4580 For TTV productTierCode can be TVX
		return (addAllProducts || requestContainsProduct) 
				&& (constantProductTypeCd.equalsIgnoreCase(productTypeCd) /* && isNotTVX(productTypeCd, prod)*/)
				&& (prod.getProductRanking() >= 0);
	}

	/**
	 * @param constantProductTypeCd
	 * @param productTypeCd
	 * @param prod
	 * @return
	 */

	// 2018 Aug release for Pik TV	
//	private boolean isNotTVX(String productTypeCd, Product prod) {
//		// for HSIC and SING it should return true
//		// for TTV it should return
//		// true : if productTypeCd is TTV and productTierCd is not TVX and productTierCd is not null, otherwise: false.
//		if (HSIC.equalsIgnoreCase(productTypeCd) || SING.equalsIgnoreCase(productTypeCd)) {
//			return true;
//		} else {
//			return (TTV.equalsIgnoreCase(productTypeCd) && WLNOfferUtil.isTTVProductTierCdNonEmptyAndNotTVX(prod.getProductTierCd()));
//		}
//	}
	
	private void sortAllAvailableProductsByRank(Deque<Product> sortedHSICProducts, Deque<Product> sortedTTVProducts,
			Deque<Product> sortedSINGProducts, Map<Long, Product> hsicProductMap, Map<Long, Product> ttvProductMap,
			Map<Long, Product> singProductMap) {
		// sort HS products by rank
		sortAvailableProductsByRank(sortedHSICProducts, hsicProductMap);
		
		// TTV
		if (!ttvProductMap.isEmpty()) {
			// sort TTV products by rank
			sortAvailableProductsByRank(sortedTTVProducts, ttvProductMap);
		}
		
		// SING
		if (!singProductMap.isEmpty()) {
			// sort SING products by rank
			sortAvailableProductsByRank(sortedSINGProducts, singProductMap);
		}
	}

	/**
	 * @param prod
	 * @return
	 */
	private boolean isProdUnavailable(Product prod) {
		return prod.getUnavailableInd() != null && prod.getUnavailableInd();
	}

	/**
	 * @param availableProduct
	 * @return
	 */
	private boolean isProductListEmpty(ProductQualification availableProduct) {
		return (availableProduct == null
				|| availableProduct.getProductList() == null
				|| availableProduct.getProductList().isEmpty());
	}

	/**
	 * @param offerFilter
	 * @return
	 */
	private Boolean isAddAllProducts(WirelineOfferFilter offerFilter) {
		return (offerFilter == null || offerFilter.getProductList() == null || offerFilter.getProductList().isEmpty());
	}

	private void populateExistingProductsList(GetAssignedAndPendingProductResponseVO consolidatedAccProfileResp,
			ServiceAddressVO serviceAddressInRequest, List<SubscribedServiceIdentifierVO> subscribedServiceIdentifier,
			List<SubscribedProductInfoRestVO> existingProducts, ServiceAddressResponseVO serviceAddress) {
		if (consolidatedAccProfileResp != null) {
			List<SubscribedProductInfoRestVO> pendingProductList = consolidatedAccProfileResp.getPendingProductListByServiceAddress(serviceAddressInRequest,subscribedServiceIdentifier,serviceAddress);
			List<SubscribedProductInfoRestVO> assignedProductList = consolidatedAccProfileResp.getAssignedProductListByServiceAddressAndServiceId(serviceAddressInRequest, subscribedServiceIdentifier);
			if (pendingProductList != null && !pendingProductList.isEmpty()) {
				existingProducts.addAll(pendingProductList);
			}
			if (assignedProductList != null && !assignedProductList.isEmpty()) {
				existingProducts.addAll(assignedProductList);
			}
		}
	}

	private List<Product> populateProducts(Deque<Product> sortedCompleteHSICProducts,
			Deque<Product> sortedCompleteTTVProducts, Deque<Product> sortedCompleteSINGProducts, String productTierCodeFromRequestForHSIC, boolean isRequestConstainsTVX) {
		List<Product> list = new ArrayList<Product>();

		//NWLN-4580
		// 2018 Aug release for Pik TV
		// check is the TV product add the the list for calling FeasiblilityService is pik tv or not
		boolean isPikTVProduct = false;
		if (!sortedCompleteTTVProducts.isEmpty()) {
			if(!isRequestConstainsTVX){
				for(Product p: sortedCompleteTTVProducts){
					if(!STV.equalsIgnoreCase(p.getProductTypeCd())){
						if(p.getProductTierCd() != null && list.isEmpty()){
							list.add(p);
							if(PRODUCT_TIER_CD_TVX.equalsIgnoreCase(p.getProductTierCd())){
								isPikTVProduct = true;
							}
						}
					}
				}
			} else {
				for(Product p: sortedCompleteTTVProducts){
					if(PRODUCT_TIER_CD_TVX.equalsIgnoreCase(p.getProductTierCd())){
						list.add(p);
						isPikTVProduct = true;
					}
				}
			}
		}
		
		if (!sortedCompleteHSICProducts.isEmpty()) {
			//NWLN-4580
			Product hsicProd = null;
			do{ 
				hsicProd = sortedCompleteHSICProducts.pop();
			}while(!((isPikTVProduct && !ProductSpecificationMapper.isOptik(hsicProd.getProductTierCd())) ||
				    (!isPikTVProduct && ProductSpecificationMapper.isOptik(hsicProd.getProductTierCd()))) && 
					sortedCompleteHSICProducts.size()>0);
			
			Product newProd = new Product();
			if(hsicProd!=null){
				newProd.setCallingFeature(hsicProd.getCallingFeature());
				newProd.setMaxTvProfile(hsicProd.getMaxTvProfile());
				newProd.setProductLine(hsicProd.getProductLine());
				newProd.setProductRanking(hsicProd.getProductRanking());
				newProd.setProductTierCd(hsicProd.getProductTierCd());
				newProd.setProductTypeCd(hsicProd.getProductTypeCd());
				newProd.setUnavailableInd(hsicProd.getUnavailableInd());
				newProd.setUnavailableReasonCd(hsicProd.getUnavailableReasonCd());
				newProd.setUnqualifiedReasonCd(hsicProd.getUnqualifiedReasonCd());
				if (!StringUtils.isEmpty(productTierCodeFromRequestForHSIC)) {
					newProd.setProductTierCd(productTierCodeFromRequestForHSIC);
				}
			}
			list.add(newProd);
		}
		
		if (!sortedCompleteSINGProducts.isEmpty()) {
			list.add(sortedCompleteSINGProducts.pop());
		}
		return list;
	}

	private void sortAvailableProductsByRank(Deque<Product> sortedProducts, Map<Long, Product> productMap) {
		List<Long> ranks0 = new ArrayList<Long>();
		ranks0.addAll(productMap.keySet());
		Collections.sort(ranks0, Collections.reverseOrder());

		for (int i = 0; i < ranks0.size(); i++) {
			sortedProducts.push(productMap.get(ranks0.get(i)));
		}
	}

	/**
	 * 
	 * @param filterOutRegular
	 * @param productTierCd
	 * @return <b>true</b>: Include current product in the list. <b>false</b>: Do NOT include current product in the list
	 */
	private boolean filterProduct(Boolean filterOutRegular, String productTierCd) {
//		filteredOutRegular		isOptik()	Include this Product
//			TRUE				TRUE		TRUE
//								FALSE		FALSE
//			FALSE				TRUE		FALSE
//								FALSE		TRUE
		if (filterOutRegular && ProductSpecificationMapper.isOptik(productTierCd)) {
			return true;
		}
		return (!filterOutRegular && !ProductSpecificationMapper.isOptik(productTierCd));
	}

	private ResponseMessageList generateMessageListForFeasibility(String transactionId, String errorMessage) {
		ResponseMessageList msgList = new ResponseMessageList();
		msgList.setResponseMessage(generateResponseMsgForFeasibility(transactionId, errorMessage));
		return msgList;
	}

	/**
	 * @return
	 */
	private List<ResponseMessage> generateResponseMsgForFeasibility(String transactionId, String errorMessage) {
		List<ResponseMessage> msgList = new ArrayList<ResponseMessage>();
		ResponseMessage msg = new ResponseMessage();
		msg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GAOSL_GENERIC_ERROR_CODE);
		msg.setDateTimeStamp(new Date());
		msg.setMessageType(MESSAGE_TYPE_ERROR);
		msg.setTransactionId(transactionId);
		
		Message theMessage = new Message();
		theMessage.setLocale(LANG_EN.toUpperCase());
		theMessage.setMessage(errorMessage);
		
		msg.setMessageList(theMessage);
		return msgList;
	}

	private CheckProductFeasibilityAdapterResponse callFeasibilityService(ServiceAddressResponseVO serviceAddress, List<SubscribedProductInfoRestVO> existingProducts, 
			ProductQualification availableProducts, OperationHeader operationHeader, String customerId, List<Product> productsToCheck) {
		
		CheckProductFeasibilityAdapterRequest request;
		
		// build the Feasibility Request
		request = buildCheckFeasibilityRequestForAvailableProducts(serviceAddress, existingProducts, availableProducts, operationHeader, customerId, productsToCheck);
		
		// call Feasibility Service
		
		IFeasibilityServiceAdapter feasibilityServiceAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class);
		return feasibilityServiceAdapter.checkProductFeasibility(request);
		
		
	}

	private CheckProductFeasibilityAdapterRequest buildCheckFeasibilityRequestForAvailableProducts(ServiceAddressResponseVO serviceAddress, List<SubscribedProductInfoRestVO> existingProducts, 
			ProductQualification availableProducts, OperationHeader operationHeader, String customerId, List<Product> productsToCheck) {
		String functionName = "buildCheckFeasibilityRequestForAvailableProducts";
		logger.enter(functionName);
		CheckProductFeasibilityAdapterRequest checkProductFeasibility = new CheckProductFeasibilityAdapterRequest();

		Boolean changeOfTechnologyYes = availableProducts.getChangeOfTechnologyInd().equalsIgnoreCase(YES);
		
		checkProductFeasibility.setCotInd(changeOfTechnologyYes);
		
		checkProductFeasibility.setSalesTransactionId(operationHeader.getSalesTransactionId());
		
		
		// String hsServicePlan = EnterpriseWLNSalesServicesConstants.SERVICE_PLAN
		String productRequestId = new SimpleDateFormat(Constants.FORMAT_SHORT_DATE_AND_TIME_MIL).format(new Date());

		// Product Specification List
		ProductSpecificationList productSpecificationList = ProductSpecificationMapper.mapToProductSpecificationListForAvailableProducts(changeOfTechnologyYes, productRequestId, existingProducts, serviceAddress, productsToCheck,null);
		checkProductFeasibility.setProductSpecificationList(productSpecificationList);

		// Product Instance List // ESS Requirement
		if (!changeOfTechnologyYes && !existingProducts.isEmpty()) {
			ProductInstanceList productInstanceList = ProductInstanceMapper.mapToProductInstanceForAvailableProducts(productRequestId, existingProducts);
			checkProductFeasibility.setProductInstanceList(productInstanceList);
		}
		
		//3. Set the address
		checkProductFeasibility.setAddress(mapServiceAddress(serviceAddress));
		
		//4. set the user / customer and timestamp
		checkProductFeasibility.setUserId(getSalesRepresentativeId(operationHeader));
		checkProductFeasibility.setCustomerId(customerId);
		
		checkProductFeasibility.setTimeStamp(new Timestamp(new Date().getTime()));
		
		//5. set AccessCriteria
		checkProductFeasibility.setAccessCriteria(mapAccessCriteria());
		
		logger.exit(functionName);
		return checkProductFeasibility;
	}

	/**
	 * @return
	 */
	private AccessCriteria mapAccessCriteria() {
		AccessCriteria resp = new AccessCriteria();
		
		resp.setMultiUnitDwellingInd(false);
		resp.setNewResidentAtServiceAddressInd(true);
		resp.setPrimaryLineHolderInd(true);
		resp.setBypassClearanceInd(false);
		return resp;
	}

	/**
	 * @param operationHeader
	 * @return
	 */
	private String getSalesRepresentativeId(OperationHeader operationHeader) {
		if (operationHeader != null && operationHeader.getUserProfile() != null) {
			return operationHeader.getUserProfile().getSalesRepId();
		}
		return "";
	}

	/**
	 * @param serviceAddress
	 * @return
	 */
	private com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress mapServiceAddress(
			ServiceAddressResponseVO sourceAddr) {
		com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress destAddr = new com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress();
		
		ServiceAddress param = sourceAddr.getServiceAddress();
		
		destAddr.setCOID(param.getCOID());
		destAddr.setMunicipalityName(param.getMunicipalityName());
		destAddr.setProvinceStateCode(param.getProvinceStateCode());
		destAddr.setAddressId(param.getAddressId());
		destAddr.setStreetName(param.getStreetName());
		destAddr.setStreetNumber(param.getStreetNumber());
		destAddr.setStreetTypeCode(param.getStreetTypeCode());
		destAddr.setUnitNumber(param.getUnitNumber());
		destAddr.setVector(param.getVector());
		destAddr.setStreetNumberSuffix(param.getStreetNumberSuffix());
		destAddr.setCLLICode(param.getCLLICode());
		destAddr.setCountryCode(param.getCountryCode());
		destAddr.setPostalZipCode((StringUtils.isEmpty(param.getPostalZipCode()) ? "" : param.getPostalZipCode().trim()));
		
		return destAddr;
	}

}
