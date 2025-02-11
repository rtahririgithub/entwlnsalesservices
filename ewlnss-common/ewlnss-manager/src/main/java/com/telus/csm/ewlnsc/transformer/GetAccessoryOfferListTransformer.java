package com.telus.csm.ewlnsc.transformer;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.CREDIT_VALUE_ESTABLISHED_CUSTOMER;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.CREDIT_VALUE_RESTRICTED_CUSTOMER;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.GetAccessoryOfferListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductDiscount;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineSalesOrderSummary;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.BusinessPartnerGroup;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelOrgSummaryInfo;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactAddress;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactDetails;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;
import com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.RequiredService;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.Component;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.Customer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.ProductInstance;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.TransactionalProduct;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;


public class GetAccessoryOfferListTransformer extends BaseOfferTransformer {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAccessoryOfferListTransformer.class);
	
	private GetAccessoryOfferListTransformer() {
	}

	public static ServiceAddressRequestVO transformServiceAddressReq(GetOffersRequestVO requestVO) {
		ServiceAddressRequestVO addressRequestVO = new ServiceAddressRequestVO();

		if (requestVO.getAccessoryOfferCriteria() != null) {
			if (requestVO.getAccessoryOfferCriteria().getServiceAddress() != null) {
				addressRequestVO.setAddressId(requestVO.getAccessoryOfferCriteria().getServiceAddress().getServiceAddressId());
				addressRequestVO.setProvinceCode(requestVO.getAccessoryOfferCriteria().getServiceAddress().getProvinceCode());
			}

			if (requestVO.getOperationHeader() != null) {
				addressRequestVO.setSalesTransactionId(requestVO.getOperationHeader().getSalesTransactionId());
			}
		}

		return addressRequestVO;
	}

	public static GetAvailableServiceInstanceListAdapterRequest transformAvailableServiceInstanceListRequest(GetOffersRequestVO requestVO) {
		GetAvailableServiceInstanceListAdapterRequest availableServiceInstanceListAdapterRequest = new GetAvailableServiceInstanceListAdapterRequest();
		availableServiceInstanceListAdapterRequest.setSalesTransactionId(requestVO.getOperationHeader().getSalesTransactionId());
		availableServiceInstanceListAdapterRequest.setCustomerId(requestVO.getAccessoryOfferCriteria().getCustomerId());
		availableServiceInstanceListAdapterRequest.setApplicationId(EnterpriseWLNSalesServicesConstants.BOLT_ON_OFFER_APPLICATION_ID_CHANNEL);
		availableServiceInstanceListAdapterRequest.setRoleId(EnterpriseWLNSalesServicesConstants.BOLT_ON_OFFER_ROLE_ID_REGULAR);

		return availableServiceInstanceListAdapterRequest;
	}

	public static GetAccessoryOfferListAdapterRequest transformAccessoryOfferList(SalesOfferCommonVO commonVO) {
		GetAccessoryOfferListAdapterRequest accessoryOfferSummaryListAdapterReq = new GetAccessoryOfferListAdapterRequest();

		accessoryOfferSummaryListAdapterReq.setOutlet(transform(commonVO));

		Customer customer = getCustomer(commonVO);

		customer.setProductInstanceList(extractAssignedAndPendingProducts(commonVO));

		accessoryOfferSummaryListAdapterReq.setCustomer(customer);

		accessoryOfferSummaryListAdapterReq.setLineOfBusinessCd(EnterpriseWLNSalesServicesConstants.WLN_OFFER_TYPE_CD);
		accessoryOfferSummaryListAdapterReq.setSalesTransactionId(commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId());
		accessoryOfferSummaryListAdapterReq.setBrandId(Long.valueOf(EnterpriseSalesServiceUtil.getBrandId(commonVO.getOffersRequestVO().getOperationHeader().getBrandCode())));

		accessoryOfferSummaryListAdapterReq.setWirelineTransactionalContext(getWirelineTransactionalContext(commonVO));

		return accessoryOfferSummaryListAdapterReq;
	}

	private static List<ProductInstance> extractAssignedAndPendingProducts(SalesOfferCommonVO commonVO) {
		List<ProductInstance> productInstanceList = null;

		if (commonVO.getAssignedAndPendingProductsResponseVO() != null) {
			List<SubscribedProductInfoRestVO> assignedProductList = commonVO.getAssignedAndPendingProductsResponseVO().getAssignedProductListByServiceAddressAndServiceId(
					commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress(),
					commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getSubscribedServiceIdentifierList());

			List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(
					commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress(),
					commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getSubscribedServiceIdentifierList(),
					commonVO.getServiceAddressResponseVO());

			if ( (assignedProductList != null) && (!assignedProductList.isEmpty()) ) {
				productInstanceList = extractProductInstanceList(commonVO, assignedProductList);
			}

			if ( (pendingProductList != null) && (!pendingProductList.isEmpty()) ) {
				List<ProductInstance> pendingProductInstanceList = extractProductInstanceList(commonVO, pendingProductList);

				if ( (pendingProductInstanceList != null) && (!pendingProductInstanceList.isEmpty()) ) {
					if (productInstanceList == null) {
						productInstanceList = pendingProductInstanceList;
					}
					else {
						productInstanceList.addAll(pendingProductInstanceList);
					}
				}
			}
		}

		return productInstanceList;
	}

	private static List<ProductInstance> extractProductInstanceList(SalesOfferCommonVO commonVO, List<SubscribedProductInfoRestVO> checkedProductList) {
		List<ProductInstance> productInstanceList = new ArrayList<ProductInstance>();

		for (SubscribedProductInfoRestVO subscribedProductInfo : checkedProductList) {
			// Populate only when the serviceAddressIds are same
			//if(!SING.equalsIgnoreCase(subscribedProductInfo.getProductTypeCd())) { //change added on August, 7, 2018: do not pass SING at all in the existing products section
				
			
			ProductInstance product = new ProductInstance();
			product.setProductTypeCode(subscribedProductInfo.getProductTypeCd());
			product.setStatusCode(subscribedProductInfo.getSubscriptionStatusCd());

			if (SING.equalsIgnoreCase(product.getProductTypeCode())) {
				product.setContractTermCnt(BigInteger.ZERO);
			}
			else if (subscribedProductInfo.getProductInstance().get(0).getTermCd() != null) {
				product.setContractTermCnt(BigInteger.valueOf(Long.valueOf(WLNOfferUtil.getContracTermInMonthByYears(subscribedProductInfo.getProductInstance().get(0).getTermCd()))));
			}

			List<Component> componentList = new ArrayList<Component>();

			for (ProductInstanceInfoRestVO subscribedProduct : subscribedProductInfo.getProductInstance()) {
				if ( (subscribedProduct != null) && (subscribedProduct.getProductCatalogId() != null) && (!subscribedProduct.getProductCatalogId().isEmpty()) ) {
					if(!SING.equalsIgnoreCase(subscribedProductInfo.getProductTypeCd())){
						Component component = new Component();
						
						CatalogueItemDO catalogueItemDO = CommonWLNGridHelper.getInstance().getCatalogueItemByExternalId(subscribedProduct.getProductCatalogId());

						if (catalogueItemDO != null && catalogueItemDO.getCatalogueItemId() != null && !catalogueItemDO.getCatalogueItemId().isEmpty()) {
							if(subscribedProductInfo.getProductTypeCd().equalsIgnoreCase(HSIC)) {
								component.setProductCatalogId(Long.valueOf(WLNOfferUtil.getInternalCidFromProductTierCd(subscribedProductInfo.getProductTierCd())));
							}else if(subscribedProductInfo.getProductTypeCd().equalsIgnoreCase(TTV)) {
								if(WLNOfferUtil.getBaseTTVComponentId(commonVO)!=null){
									component.setProductCatalogId(Long.valueOf(WLNOfferUtil.getBaseTTVComponentId(commonVO)));
								}
								
							}else {
								if(!StringUtils.isBlank(catalogueItemDO.getCatalogueItemId())){
									component.setProductCatalogId(Long.valueOf(catalogueItemDO.getCatalogueItemId()));
								}
							}
							
							componentList.add(component);	
						}
					}

				}

				if ( (commonVO.getAvailableServiceInstanceListAdapterResponse() != null) &&
						(commonVO.getAvailableServiceInstanceListAdapterResponse().getServiceList() != null) &&
						(commonVO.getAvailableServiceInstanceListAdapterResponse().getServiceList().size() > 0) ) {
					for (RequiredService service : commonVO.getAvailableServiceInstanceListAdapterResponse().getServiceList()) {
						if (subscribedProduct!=null && !StringUtils.isBlank(subscribedProduct.getServiceInstanceId()) && !StringUtils.isBlank(subscribedProductInfo.getProductTypeCd()) && (subscribedProduct.getServiceInstanceId().equals(service.getServiceInstanceId())) &&
								(subscribedProductInfo.getProductTypeCd().equals(service.getProductTypeCode())) ) {
							product.setReedemedGiftInd(false);
							
							break;								
						}
						else {
							product.setReedemedGiftInd(true);									
						}
					}
				}
				else {
					product.setReedemedGiftInd(true);
				}
			}

			product.setComponentList(componentList);

			productInstanceList.add(product);
		//}

		}
		return productInstanceList;
	}

	private static WirelineTransactionalContext getWirelineTransactionalContext(SalesOfferCommonVO commonVO) {
		WirelineTransactionalContext wirelineTransactionalContext = null;
		List<TransactionalProduct> transactionalProductList = null;

		if (commonVO.getOffersRequestVO() != null &&
			commonVO.getOffersRequestVO().getAccessoryOfferCriteria() != null &&
			commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList() != null &&
			!commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList().isEmpty()) {
			for (WirelineSalesOrderSummary associatedWirelineSalesSummary : commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList()) {
				if (associatedWirelineSalesSummary != null &&
					associatedWirelineSalesSummary.getProductList() != null &&
					!associatedWirelineSalesSummary.getProductList().isEmpty()) {
					transactionalProductList = getTransactionalWirelineProductList(commonVO, associatedWirelineSalesSummary.getProductList());

					if (wirelineTransactionalContext != null) {
						if (wirelineTransactionalContext.getTransactionalProductList() != null && !wirelineTransactionalContext.getTransactionalProductList().isEmpty()) {
							wirelineTransactionalContext.getTransactionalProductList().addAll(transactionalProductList);
						}
						else {
							wirelineTransactionalContext.setTransactionalProductList(transactionalProductList);
						}
					}
					else {
						wirelineTransactionalContext = new WirelineTransactionalContext();
						wirelineTransactionalContext.setTransactionalProductList(transactionalProductList);
					}
				}
			}
		}

		if (wirelineTransactionalContext != null) {
			WLNOfferUtil.setMergeOffersInd(commonVO, wirelineTransactionalContext);
		}

		return wirelineTransactionalContext;
	}

	private static List<TransactionalProduct> getTransactionalWirelineProductList(SalesOfferCommonVO commonVO, List<WirelineProductDiscount> productList) {
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();

//		boolean isNotJoinedOffers = false;
		List<String> eligibleForRecontractingProducts = new ArrayList<String>();
		
//		if (!WLNOfferUtil.isJoinedOffers(commonVO.getOffersRequestVO().getOperationHeader())) {
//			// transaction from OMNI has isJoinedOffers = false
//			isNotJoinedOffers = true;
//			
//		}
		eligibleForRecontractingProducts = getEligibleForRecontractingProducts(commonVO);

		for (WirelineProductDiscount wirelineProductDiscount : productList) {
			if (wirelineProductDiscount != null) {
				TransactionalProduct transactionalProduct = new TransactionalProduct();
				String productTypeCd = wirelineProductDiscount.getProductTypeCd();
				transactionalProduct.setProductCode(productTypeCd);
				transactionalProduct.setProductCode(wirelineProductDiscount.getProductTypeCd());
				transactionalProduct.setTransactionTypeCd(wirelineProductDiscount.getProductOrderType().getProductOrderTypeCd());

				if (wirelineProductDiscount.isRecontractInd() != null) {
					transactionalProduct.setRecontractInd(wirelineProductDiscount.isRecontractInd());
				} else {
					transactionalProduct.setRecontractInd(eligibleForRecontractingProducts.contains(productTypeCd));
//					if (isNotJoinedOffers) {
//												
//					}else {
//						transactionalProduct.setRecontractInd(false);
//					}
				}

				transactionalProduct.setContractTermCnt(new BigInteger(wirelineProductDiscount.getContractTermCd()));
				transactionalProduct.setProductCatalogIdList(WLNOfferUtil.getProductCatalogIdList(wirelineProductDiscount.getProductComponentList()));
				transactionalProductList.add(transactionalProduct);
			}
		}

		return transactionalProductList;
	}
	
private static List<String> getEligibleForRecontractingProducts(SalesOfferCommonVO commonVO) {
		
		String methodName = "getEligibleForRecontractingProducts";
		logger.enter(methodName);
		
		List<String> result = new ArrayList<String>();
		
		// populate SalesOfferCommonVO.OffersRequestVO.SalesOfferCriteriaVO for calling the WLNOfferUtil
		SalesOfferCriteriaVO salesOfferCriteriaVO = new SalesOfferCriteriaVO(); 
		commonVO.getOffersRequestVO().setSalesOfferCriteria(salesOfferCriteriaVO);

		// populate SalesOfferCommonVO.OffersRequestVO.SalesOfferCriteriaVO.ServiceAddressVO
		ServiceAddressVO serviceAddress = new ServiceAddressVO();
		salesOfferCriteriaVO.setServiceAddress(serviceAddress);
		serviceAddress.setCityCode(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getCityCode());
		serviceAddress.setProvinceCode(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getProvinceCode());
		serviceAddress.setServiceAddressId(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getServiceAddressId());
		
		// populate SalesOfferCommonVO.OffersRequestVO.SalesOfferCriteriaVO.SubscribedServiceIdentifier
		List<SubscribedServiceIdentifierVO> subscribedServiceIdentifier = new ArrayList<SubscribedServiceIdentifierVO>();
		salesOfferCriteriaVO.setSubscribedServiceIdentifier(subscribedServiceIdentifier);
		for (ServiceIdentifier serviceIdentifier : commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getSubscribedServiceIdentifierList()) {
			SubscribedServiceIdentifierVO newSubscribedServiceIdentifierVO = new SubscribedServiceIdentifierVO();
			subscribedServiceIdentifier.add(newSubscribedServiceIdentifierVO);
			newSubscribedServiceIdentifierVO.setServiceId(serviceIdentifier.getServiceId());
			newSubscribedServiceIdentifierVO.setServiceReferenceId(serviceIdentifier.getServiceReferenceId());
		}
		
		if (commonVO.getOffersRequestVO() != null &&
				commonVO.getOffersRequestVO().getAccessoryOfferCriteria() != null &&
				commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList() != null &&
				!commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList().isEmpty()) {
			
			
//QC-70993 offer split: the HS/TV are in 2 different assocatedWirelineSalesSummary, we can't just pick the first one.
//			WirelineSalesOrderSummary associatedWirelineSalesSummary = commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList().get(0);
			List<WirelineSalesOrderSummary> associatedWirelineSalesSummaryList = commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList();

			if(associatedWirelineSalesSummaryList!=null && associatedWirelineSalesSummaryList.size()>0) {
				for(WirelineSalesOrderSummary associatedWirelineSalesSummary : associatedWirelineSalesSummaryList) {
					if (associatedWirelineSalesSummary != null &&
							associatedWirelineSalesSummary.getProductList() != null &&
							!associatedWirelineSalesSummary.getProductList().isEmpty()) {

						//populate a fake Offer from the request for calling WLNOfferUtil.getRecontractEligibleProductCodeList
						Offer offer = new Offer();
						offer.setOfferProduct(new OfferProduct());
//						if (associatedWirelineSalesSummary.getSalesOffer()!=null) {
							if(associatedWirelineSalesSummary.getSalesOffer() != null && !StringUtils.isBlank(associatedWirelineSalesSummary.getSalesOffer().getOfferId())){
								offer.setOfferId(Long.parseLong(associatedWirelineSalesSummary.getSalesOffer().getOfferId()));
							}
							if(!CollectionUtils.isEmpty(associatedWirelineSalesSummary.getProductList())){
								for (WirelineProductDiscount requestedProduct : associatedWirelineSalesSummary.getProductList()) {
									WirelineOfferProduct wirelineOfferProduct = new WirelineOfferProduct();
									offer.getOfferProduct().getWirelineOfferProductList().add(wirelineOfferProduct);
									wirelineOfferProduct.setProductTypeCode(requestedProduct.getProductTypeCd());
									wirelineOfferProduct.getContractTermList().add(new BigInteger(requestedProduct.getContractTermCd()));
									
									for (ProductComponentIdentifier requestProductComponentIdentifier : requestedProduct.getProductComponentList()) {
										
										ProductComponent newProductComponent = new ProductComponent();
										wirelineOfferProduct.getProductComponentList().add(newProductComponent);
										ProductCatalogueItem newProductCatalogueItem = new ProductCatalogueItem();
										newProductComponent.getProductCatalogueItemList().add(newProductCatalogueItem);
				
										ProductCatalogueIdentifier newPCI = new ProductCatalogueIdentifier();
										newPCI.setProductCatalogueId(requestProductComponentIdentifier.getProductCatalogueIdentifier());
										newProductCatalogueItem.setProductCatalogueIdentifier(newPCI);
					
										CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
										CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(requestProductComponentIdentifier.getProductCatalogueIdentifier());
										if (catalogueItemDO != null) {
											newPCI.setExternalProductCatalogId(catalogueItemDO.getProductCode());
										}
									}
									
								}
							}

//						}

						// need to make the following call to set up the SalesOfferCommonVO.TierCdForRecontracting
						WLNOfferUtil.getCustomerEligibleForRecontractingProduct(commonVO);
						
						logger.debug(methodName, "Calling WLNOfferUtil.getRecontractEligibleProductCodeList offer:" + JsonUtil.getJsonFromObjectNonNUll(offer));

//						result = WLNOfferUtil.getRecontractEligibleProductCodeList(offer, commonVO, false); //QC-70993
						result.addAll(WLNOfferUtil.getRecontractEligibleProductCodeList(offer, commonVO, false)); //QC-70993
					}
				}
				
			}
		}
		
		return result;
	}

	public static List<AccessoryOffer> getAccessoryOfferSummaryList(SalesOfferCommonVO commonVO) {
		List<AccessoryOffer> accessoryOfferSummaryList = null;

		if ( (commonVO != null) && (commonVO.getOfferListAdapterResponse() != null) ) {
			accessoryOfferSummaryList = getAccessoryOfferSummaryList(commonVO.getOfferListAdapterResponse().getOfferList());
		}

		return accessoryOfferSummaryList;
	}

	private static Customer getCustomer(SalesOfferCommonVO commonVO) {
		Customer customer = new Customer();

		if (commonVO.getOffersRequestVO().getAccessoryOfferCriteria()!=null) {
			if (!StringUtils.isBlank(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getCustomerId())) {
				customer.setCustomerId(Long.valueOf(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getCustomerId()));
			}

			if (commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress() != null) {
				customer.setServiceProvinceCd(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getProvinceCode());
				customer.setServiceCityCd(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getCityCode());
			}

			if (commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getOfferFilter() != null) {
				if (!StringUtils.isBlank(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getOfferFilter().getAccountTypeCode())) {
					customer.setAccountTypeCode(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getOfferFilter().getAccountTypeCode()); 
				}
			}

			if (commonVO.getCreditProfileByCustIdResponseVO() != null &&
				commonVO.getCreditProfileByCustIdResponseVO().getCreditProfile() != null &&
				commonVO.getCreditProfileByCustIdResponseVO().getCreditProfile().getCreditWorthiness() != null) {
				
				customer.setCreditValueCode(WLNOfferUtil.getCreditValueCode(commonVO)); //Oct 27, 2018 - Considering AccountMasterSourceTypeCd from GCAP if creditValueCd is different from E

				if (WLNOfferUtil.getCreditValueCode(commonVO).equalsIgnoreCase(CREDIT_VALUE_RESTRICTED_CUSTOMER)) {
					customer.setBoltOnInd(true);
				}
				else if (commonVO.getCreditProfileByCustIdResponseVO().getCreditProfile().getCreditWorthiness().getProductCategoryQualification() != null) {
					customer.setBoltOnInd(commonVO.getCreditProfileByCustIdResponseVO().getCreditProfile().getCreditWorthiness().getProductCategoryQualification().isBoltOnInd());
				}
			}
			else {
				// In case of a new customer, treat it as an established customer
				customer.setCreditValueCode(CREDIT_VALUE_ESTABLISHED_CUSTOMER);
				customer.setBoltOnInd(true);
			}
		}

		customer.setBrandId(Long.valueOf(EnterpriseSalesServiceUtil.getBrandId(commonVO.getOffersRequestVO().getOperationHeader().getBrandCode())));

		return customer;
	}

	private static OutletInfo transform (SalesOfferCommonVO commonVO) {
		OutletInfo result = new OutletInfo();
		String province = null;

		if (commonVO != null && commonVO.getOffersRequestVO() != null && commonVO.getOffersRequestVO().getOperationHeader() != null) {
			OperationHeader operationHeader = commonVO.getOffersRequestVO().getOperationHeader();
			ChannelOrgSummaryInfo channelOrgSummaryInfo = new ChannelOrgSummaryInfo();

			try {
				if (operationHeader.getUserProfile() != null) {
					channelOrgSummaryInfo.setChannelOrgType(operationHeader.getUserProfile().getChnlOrgTypeCode());
					channelOrgSummaryInfo.setInternalChannelOrgId(operationHeader.getUserProfile().getChnlOrgInternalId());
					channelOrgSummaryInfo.setChannelOrgCode(operationHeader.getUserProfile().getChnlOrgNumber());

					if (operationHeader.getUserProfile().getSalesRepAssociatedOutletList() != null &&
						!operationHeader.getUserProfile().getSalesRepAssociatedOutletList().isEmpty() ) {
						result.setInternalOutletId(operationHeader.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId());
					}

					if (operationHeader.getUserProfile().getOutletAssociatedProvinces() != null &&
						!operationHeader.getUserProfile().getOutletAssociatedProvinces().isEmpty()) {
						province = operationHeader.getUserProfile().getOutletAssociatedProvinces().get(0);
					}
				}
				else if (operationHeader.getAgentProfile() != null) {
					channelOrgSummaryInfo.setChannelOrgType(operationHeader.getAgentProfile().getChannelOrganizationTypeCd());
					channelOrgSummaryInfo.setInternalChannelOrgId(Long.valueOf(operationHeader.getAgentProfile().getChannelOrganizationInternalId()));
					channelOrgSummaryInfo.setChannelOrgCode(operationHeader.getAgentProfile().getChannelOrganizationNum());
				}

				if ( (operationHeader.getAgentProfile() != null) &&
					 (operationHeader.getAgentProfile().getUserPrivilegeRoleCodeList() != null) &&
					 (!operationHeader.getAgentProfile().getUserPrivilegeRoleCodeList().isEmpty()) ) {
					List<BusinessPartnerGroup> assingedGroupList = new ArrayList<BusinessPartnerGroup>();
					for(String roleCode: operationHeader.getAgentProfile().getUserPrivilegeRoleCodeList())
					{
						BusinessPartnerGroup assingedGroup = new BusinessPartnerGroup();
						assingedGroup.setGroupCode(roleCode);
						assingedGroupList.add(assingedGroup);	
					}
					
					
					result.setAssingedGroupList(assingedGroupList);
				}
			}
			catch (Exception e) {
				logger.error("", "transform Outlet", e.getMessage(), e);
			}

			String cityCode=null;
			String serviceAddressId=null;

			if(commonVO.getOffersRequestVO().getAccessoryOfferCriteria()!=null && commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress()!=null){
				cityCode =commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getCityCode();
				serviceAddressId = commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress().getServiceAddressId();
			}

			if (province != null) {
				ContactAddress contactAddress = new ContactAddress();
				contactAddress.setProvince(province);
				long addressId;

				try {
					addressId = Long.valueOf(serviceAddressId);
				}
				catch (NumberFormatException e) {
					addressId = 0;
				}

				contactAddress.setAddressId(addressId);
				contactAddress.setCity(cityCode);
				ContactDetails contactDetails = new ContactDetails();
				contactDetails.setAddress(contactAddress);
				result.getContactDetails().add(contactDetails);
				result.setChannelOrgInfo(channelOrgSummaryInfo);
			}
		}

		return result;
	}

	public static GetAssignedAndPendingProductRequestVO transformConsolidatedAccRequest(GetOffersRequestVO requestVO) {
		GetAssignedAndPendingProductRequestVO result = new GetAssignedAndPendingProductRequestVO();

		result.setOperationHeader(requestVO.getOperationHeader());

		if (requestVO.getAccessoryOfferCriteria() != null) {
			result.setCustomerId(requestVO.getAccessoryOfferCriteria().getCustomerId());
			result.setBillingAccountNumber(requestVO.getAccessoryOfferCriteria().getBillingAccountNumber());
		}

		return result;
	}

	public static GetCreditProfileByCustomerIdAdapterRequest transformCreditProfileByCustId(GetOffersRequestVO requestVO) {
		GetCreditProfileByCustomerIdAdapterRequest adapterRequest = new GetCreditProfileByCustomerIdAdapterRequest();

		adapterRequest.setAuditInfo(getAuditInfoForCreditProfileByCustId(requestVO));

		if (requestVO.getAccessoryOfferCriteria() != null) {
			adapterRequest.setCustomerId(requestVO.getAccessoryOfferCriteria().getCustomerId());
		}

		adapterRequest.setSalesTransactionId(requestVO.getOperationHeader().getSalesTransactionId());

		//TODO: add the refreshInd based on SIP
		return adapterRequest;
	}

	private static AuditInfo getAuditInfoForCreditProfileByCustId(GetOffersRequestVO request) {
		AuditInfo auditInfo = new AuditInfo();

		if (request.getOperationHeader() != null) {
			auditInfo.setOriginatorApplicationId(String.valueOf(request.getOperationHeader().getOriginatorApplicationId()));
			auditInfo.setCorrelationId(request.getOperationHeader().getSalesTransactionId());

			if (request.getOperationHeader().getUserProfile() != null) {
				auditInfo.setChannelOrganizationId(String.valueOf(request.getOperationHeader().getUserProfile().getChnlOrgInternalId()));
				auditInfo.setUserId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setSalesRepresentativeId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));

				if (request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList() != null &&
					!request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().isEmpty() &&
					request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() > 0) {
					auditInfo.setOutletId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
				}
			}
			else if (request.getOperationHeader().getAgentProfile() != null) {
				auditInfo.setChannelOrganizationId(String.valueOf(request.getOperationHeader().getAgentProfile().getChannelOrganizationInternalId()));
			}
		}

		auditInfo.setTimestamp(new Date());

		return auditInfo;
	}

	public static void transform(SalesOfferCommonVO commonVO, GetOffersResponseVO offersResponseVO) {
		if (commonVO.getOfferListAdapterResponse() != null &&
			commonVO.getOfferListAdapterResponse().isSuccessfulProcessInd() &&
			!commonVO.getOfferListAdapterResponse().isMsgHasError()) {
			offersResponseVO.setAccessoryOfferSummaryList(getAccessoryOfferSummaryList(commonVO));
		}
		else {
			BaseOfferTransformer.transform(commonVO, offersResponseVO);
		}
	}
}
