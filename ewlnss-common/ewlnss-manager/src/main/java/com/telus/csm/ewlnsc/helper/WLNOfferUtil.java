package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.MTM_TERM;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.ONE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.ONE_YEAR_IN_MONTHS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.PRODUCT_TIER_CD_TVX;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.REFPDS_WLN_SALES_PRODUCT_FAMILY_GROUP_RUL;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.STV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.THREE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.THREE_YEARS_IN_MONTHS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TWO;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TWO_YEARS_IN_MONTHS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.ZERO;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY_MSG;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.PackInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.AccessoryOfferCriteria;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.PromotionCriteria;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.TransactionalProduct;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.PromotionIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductDiscount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Category;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferCategory;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class WLNOfferUtil {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(WLNOfferUtil.class);
	
	public static final String PIK_TV_OMS_OFFER_IDS = ApplicationProperties.getConfigString("${common/getAvailableOfferSummaryListParams/pikTvOmsOfferIds}");
	public static final String NON_SELLABLE_PRODUCT = ApplicationProperties.getConfigString("${common/getAvailableOfferSummaryListParams/nonSellableProduct}");
	private static final int REQUEST_OK = 200;
	private static final String PROD_FAMILY_GROUP_NM = "PROD_FAMILY_GROUP_NM";
	private static final String PROD_CATALOGUE_ID = "PROD_CATALOGUE_ID";
	public static ArrayList<String> ACCESSORY_EQUIPMENT_LIST = new ArrayList<String>();
	
	// NWLN-11291 create a accessory equipment list
	static {
		ACCESSORY_EQUIPMENT_LIST.add(EnterpriseWLNSalesServicesConstants.DWSP);
		ACCESSORY_EQUIPMENT_LIST.add(EnterpriseWLNSalesServicesConstants.DWEP);
		ACCESSORY_EQUIPMENT_LIST.add(EnterpriseWLNSalesServicesConstants.CAXP);
		ACCESSORY_EQUIPMENT_LIST.add(EnterpriseWLNSalesServicesConstants.MAXP);
	}

	public WLNOfferUtil(){
		super();
	}

	public static String getCreditValueCode(SalesOfferCommonVO commonVO){
		String creditValueCd;
		String functionName = "getCreditValueCode";
		logger.enter(functionName);
		if(commonVO.getOffersRequestVO()!=null && commonVO.getOffersRequestVO().getSalesOfferCriteria()!=null && !StringUtils.isBlank(commonVO.getOffersRequestVO().getSalesOfferCriteria().getCustomerId())){
			logger.info(functionName, "Customer Id passed in the Request, trying to get CreditValueCd from CreditProfile response ");
			if(commonVO.getCreditProfileByCustIdResponseVO()!=null){
				GetCreditProfileByCustomerIdAdapterResponse creditProfileByCustIdResponseVO = commonVO.getCreditProfileByCustIdResponseVO();
				if(creditProfileByCustIdResponseVO.getCreditProfile()!=null && creditProfileByCustIdResponseVO.getCreditProfile().getCreditWorthiness()!=null && !StringUtils.isBlank(creditProfileByCustIdResponseVO.getCreditProfile().getCreditWorthiness().getCreditValueCd())){
					creditValueCd = creditProfileByCustIdResponseVO.getCreditProfile().getCreditWorthiness().getCreditValueCd();
					logger.info(functionName, "CreditValueCode found in CreditProfileByCustId Response with value=" + creditValueCd);
					
					if(!EnterpriseWLNSalesServicesConstants.CREDIT_VALUE_ESTABLISHED_CUSTOMER.equalsIgnoreCase(creditValueCd)){
						logger.info(functionName, "CreditValueCd is: " + creditValueCd + " Calling ConsumerCustomerManagementSvc.getFullCustomerInfo ");
						
						GetFullCustomerInfoAdapterResponse fullCustomerAdapterResponse = callToGetFullCustomerInfo(commonVO);
						
						if(fullCustomerAdapterResponse!=null && fullCustomerAdapterResponse.getFullCustomer()!=null && CollectionUtils.isNotEmpty(fullCustomerAdapterResponse.getFullCustomer().getBillingAccountList())){
							if(!customerHasWLNBan(fullCustomerAdapterResponse.getFullCustomer().getBillingAccountList())){
								logger.info(functionName, "Customer does not have wireline accounts as per ConsumerCustomerManagementSvc.getFullCustomerInfo, setting creditValueCd as 'E'");
								creditValueCd = EnterpriseWLNSalesServicesConstants.CREDIT_VALUE_ESTABLISHED_CUSTOMER;
							}
						}
					}
				}else{
					creditValueCd = EnterpriseWLNSalesServicesConstants.NON_CREDIT_RESTRICTED_PROFILE;
					logger.info(functionName, "No creditValueCode was found in CreditProfileByCustId Response");
				}
			}else{
				logger.info(functionName, "No creditValueCode was found in CreditProfileByCustId Response or the call for GetCreditProfileByCustomerId failed, defaulting creditValueCode to 'E'");
				creditValueCd = EnterpriseWLNSalesServicesConstants.NON_CREDIT_RESTRICTED_PROFILE;
			}
		} else {
			logger.info(functionName, "No customerId was passed in Request defaulting technologyType to 'E'");
			creditValueCd = EnterpriseWLNSalesServicesConstants.NON_CREDIT_RESTRICTED_PROFILE;
		}
		logger.exit(functionName);

		return creditValueCd;
	}
	
	private static boolean customerHasWLNBan(List<BillingAccount> billingAccountList) {
		if(CollectionUtils.isNotEmpty(billingAccountList)){
			for(BillingAccount billingAccount : billingAccountList){
				if(EnterpriseSalesServiceUtil.isWirelineBan(String.valueOf(billingAccount.getBillingMasterSourceId()))){
					return true;
				}
			}
		}
		return false;
	}

	private static GetFullCustomerInfoAdapterResponse callToGetFullCustomerInfo(SalesOfferCommonVO commonVO) {
		String functionName="callToGetFullCustomerInfo";
		String customerId = null;
		logger.enter(functionName);
		if(commonVO.getOffersRequestVO().getSalesOfferCriteria()!=null && !StringUtils.isBlank(commonVO.getOffersRequestVO().getSalesOfferCriteria().getCustomerId())){			
			customerId = commonVO.getOffersRequestVO().getSalesOfferCriteria().getCustomerId();
			logger.debug(functionName, "CustomerId passed in salesOfferCriteria with value: " + customerId);
		}
		
		if(commonVO.getOffersRequestVO().getAccessoryOfferCriteria()!=null && !StringUtils.isBlank(commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getCustomerId())){			
			customerId = commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getCustomerId();
			logger.debug(functionName, "CustomerId passed in the accessoryOfferCriteria with value: " + customerId);
		}
		
		if(!StringUtils.isBlank(customerId)){
			GetFullCustomerInfoAdapterRequest fullCustomerRequest = new GetFullCustomerInfoAdapterRequest(Long.valueOf(customerId), commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId());
			
			IConsumerCustomerMgmtSvcAdapter getFullCustomerInfoAdapter = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
			
			logger.exit(functionName);
			return getFullCustomerInfoAdapter.getFullCustomerInfo(fullCustomerRequest);
			
		}
		logger.exit(functionName);
		return null;
	}

	private static boolean isCustomerHasWlnAccount(GetAssignedAndPendingProductResponseVO assignedAndPendingProductsResponseVO) {
		String functionName = "isCustomerHasWlnAccount";
		logger.enter(functionName);
		if(assignedAndPendingProductsResponseVO!=null && assignedAndPendingProductsResponseVO.getPrimaryAccountProfile()!=null){
			if(!StringUtils.isBlank(assignedAndPendingProductsResponseVO.getPrimaryAccountProfile().getAccountMasterSourceId()) && EnterpriseSalesServiceUtil.isWirelineBan(assignedAndPendingProductsResponseVO.getPrimaryAccountProfile().getAccountMasterSourceId())){
				return true;
			}			
		}
		logger.exit(functionName);
		return false;

	}

	public  static String getTechnologyTypeCd(SalesOfferCommonVO commonVO){
		String technologyType = null;
		String functionName="getTechnologyTypeCd";
		logger.enter(functionName);
		if(commonVO!=null && commonVO.getProductQualificationAdapterResponseVO()!=null && commonVO.getProductQualificationAdapterResponseVO().getProductQualificationList()!=null && !commonVO.getProductQualificationAdapterResponseVO().getProductQualificationList().isEmpty()){
			ProductQualification productQualification = commonVO.getProductQualificationAdapterResponseVO().getProductQualificationList().get(0);
			if(productQualification!=null){
				if (productQualification.getGponEttsInd() != null && productQualification.getGponEttsInd()) {
					logger.info(functionName,"gponEttsInd from ProductQualification Response is true, setting TechnologyType to Fiber");
					technologyType = EnterpriseWLNSalesServicesConstants.TECHNOLOGY_TYPE_FIBER;
				}else{
					logger.info(functionName, "gponEttsInd is false or not present in ProductQualification Response, setting Technology type to Copper");
					technologyType = EnterpriseWLNSalesServicesConstants.TECHNOLOGY_TYPE_COPPER;
				}
			}
		}else{
			technologyType = EnterpriseWLNSalesServicesConstants.TECHNOLOGY_TYPE_FIBER;
			logger.info(functionName, "No gponEttsInd was found from ProductQualification response, or this one failed, setting Fiber as technologyType instead.");
		}
		return technologyType;
	}
	
	/**
	 * Method to build the WirelineTransactionalContext element for GetAvailableOfferSummaryList operation
	 * @param commonVO
	 * @param callForRecontractEligibleInd 
	 * @param productCatalogId 
	 * @return WirelineTransactionalContext
	 */
	public static WirelineTransactionalContext getWirelineTransactionalContext(SalesOfferCommonVO commonVO, boolean callForRecontractEligibleInd, String productCatalogId) {
		WirelineTransactionalContext wirelineTransactionalContext = new WirelineTransactionalContext();
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();

		setMergeOffersInd(commonVO, wirelineTransactionalContext);
			
		boolean presentPikTvInd=false;

		if (commonVO.getOffersRequestVO()!=null && commonVO.getOffersRequestVO().getSalesOfferCriteria()!=null && commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter()!=null) {
			WirelineOfferFilter wlnOfferFilter = commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter();
			boolean bundleInd =  wlnOfferFilter.isBundleInd();
			GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO = commonVO.getAssignedAndPendingProductsResponseVO();

			// 2018 Aug release for Pik TV
			// Pik TV upgrade is available
			// if(assignedAndPendingResponseVO!=null){
				//getting the omsOfferId from consolidateAccountProfile response
			//	presentPikTvInd = isPresentPikTv(assignedAndPendingResponseVO,commonVO.getOffersRequestVO().getSalesOfferCriteria());
			// }

			String offerContractTerm = wlnOfferFilter.getOfferContractTermCd();

			// 2018 Aug release for Pik TV
			// Pik TV upgrade is available
			transactionalProductList = getTransactionalProductList(offerContractTerm,wlnOfferFilter,commonVO,callForRecontractEligibleInd,productCatalogId);
			wirelineTransactionalContext.setFetchBundleOfferInd(bundleInd);
	
			/**
			 * October 10, 2018 - Campaign offers
			 * if: SIP "JOIN_SALES_OFFER" is true and UI passes SIP “SUPRESS_CAMPAIGN_OFFER”.
			 * then: set suppressAssignedOffersInd 
			 * 
			 * Apply only for non-promotion code scenario for now.
			 */
			OperationHeader operationHeader = commonVO.getOffersRequestVO().getOperationHeader();
			if(WLNOfferUtil.isJoinedOffers(operationHeader) && !StringUtils.isBlank(getSuppressCampaignOfferInd(operationHeader)) && StringUtils.isBlank(commonVO.getOffersRequestVO().getSalesOfferCriteria().getPromotionCd())){
				wirelineTransactionalContext.setSuppressAssignedOffersInd(Boolean.valueOf(getSuppressCampaignOfferInd(operationHeader)));
			}
			
			if (CollectionUtils.isNotEmpty(transactionalProductList)) {
				wirelineTransactionalContext.setTransactionalProductList(transactionalProductList);
			}
			
			PromotionCriteria promotionCriteria = getPromotionCriteria(commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter());
			if (promotionCriteria != null) {
				wirelineTransactionalContext.setPromotionCriteria(promotionCriteria);
			}
			
		}
		
		return wirelineTransactionalContext;
	}
		
	/**
	 * Method to build the WirelineTransactionalContext element in OIS request, used by GetSalesOfferDetail operation
	 * @param requestVO
	 * @param commonVO
	 * @return WirelineTransactionalContext
	 */
	public static WirelineTransactionalContext getWirelineTransactionalContext(GetSalesOfferDetailRequestVO requestVO,SalesOfferCommonVO commonVO,boolean callForRecontranctInd){
		WirelineTransactionalContext wirelineTransactionalContext = new WirelineTransactionalContext();
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();

		setMergeOffersInd(commonVO, wirelineTransactionalContext);
		
		boolean presentPikTvInd=false;
		if(requestVO!=null && requestVO.getSalesOfferCriteria()!=null && requestVO.getSalesOfferCriteria().getOfferFilter()!=null){
			WirelineOfferFilter wlnOfferFilter = requestVO.getSalesOfferCriteria().getOfferFilter();
			boolean bundleInd =  wlnOfferFilter.isBundleInd();
			GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO = commonVO.getAssignedAndPendingProductsResponseVO();
			
			// 2018 Aug release for Pik TV
			// Pik TV upgrade is available
			// if(assignedAndPendingResponseVO!=null){
				//getting the omsOfferId from consolidateAccountProfile response
			//	presentPikTvInd = isPresentPikTv(assignedAndPendingResponseVO,requestVO.getSalesOfferCriteria());
			// }

			String offerContractTerm = wlnOfferFilter.getOfferContractTermCd();
		
			// 2018 Aug release for Pik TV
			// Pik TV upgrade is available
			transactionalProductList = getTransactionalProductList(offerContractTerm, wlnOfferFilter, commonVO,callForRecontranctInd,null);
			wirelineTransactionalContext.setFetchBundleOfferInd(bundleInd);

			if (CollectionUtils.isNotEmpty(transactionalProductList)) {
				wirelineTransactionalContext.setTransactionalProductList(transactionalProductList);
			} 
			
			PromotionCriteria promotionCriteria = getPromotionCriteria(requestVO.getSalesOfferCriteria().getOfferFilter());
			if (promotionCriteria != null) {
				wirelineTransactionalContext.setPromotionCriteria(promotionCriteria);
			}

		}
		
		
		
		return wirelineTransactionalContext;
}

	private static List<TransactionalProduct> getRecontractingEligibleTransactionalProducts(SalesOfferCommonVO commonVO,boolean callForRecontractEligibleInd){
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();
		if(commonVO.isCustomerEligibleForRecontract() && callForRecontractEligibleInd){
			TransactionalProduct singProduct = new TransactionalProduct();
			singProduct.setProductCode(SING);
			String statusCode = getStatusCodeByProductType(SING, commonVO);
			singProduct.setTransactionTypeCd(statusCode);
			singProduct.setContractTermCnt(BigInteger.ZERO);
			
			// 2018 June Exception release for TTV recontracting
			// retreive recontract offer from OIS for HSIC or HSIC+TTV
			String customerHasHSTerm = getProductTermCodeForExistingCustomer(HSIC, commonVO); 
			String customerHasTTVTerm = getProductTermCodeForExistingCustomer(TTV, commonVO);
			
			// Fix for QC 65443
			if(customerHasHSTerm != null){
				if(customerHasTTVTerm != null){
					// Customer has TTV+HS
					// Call for IOS to get TTV product if re-contracting on TTV
					if (getCustomerEligibleForRecontractingProduct(commonVO).contains(HSIC) ||
							getCustomerEligibleForRecontractingProduct(commonVO).contains(TTV)) {
						TransactionalProduct ttvWithCatalogId = new TransactionalProduct();
						ttvWithCatalogId.setProductCode(TTV);
						ttvWithCatalogId.setTransactionTypeCd(OIS_PRODUCT_INSTANCE_UPGRADE);
									
						String baseTTVComponentId = getBaseTTVComponentId(commonVO);
									
						if(baseTTVComponentId != null){
							List<String> productCatalogIdList = new ArrayList<String>();
							productCatalogIdList.add(baseTTVComponentId);
							ttvWithCatalogId.setProductCatalogIdList(productCatalogIdList ); 
						}
									
						transactionalProductList.add(ttvWithCatalogId);
									
						TransactionalProduct hsicTransactionalProduct = new TransactionalProduct();
						hsicTransactionalProduct.setProductCode(HSIC);
						hsicTransactionalProduct.setTransactionTypeCd(OIS_PRODUCT_INSTANCE_UPGRADE);
						transactionalProductList.add(hsicTransactionalProduct);
					}
				} else {
					// Customer has HS only
					// Call for IOS to get HSIC product if re-contracting on HSIC 
					if (getCustomerEligibleForRecontractingProduct(commonVO).contains(HSIC)) {
						TransactionalProduct hsicTransactionalProduct = new TransactionalProduct(); //This HSIC product with no term will bring all the HSIC offers for different terms for one,two and three years
						hsicTransactionalProduct.setProductCode(HSIC);
						hsicTransactionalProduct.setTransactionTypeCd(OIS_PRODUCT_INSTANCE_UPGRADE);
						transactionalProductList.add(hsicTransactionalProduct);
						
                        // fix for QC66306
						TransactionalProduct ttvTransactionalProduct = new TransactionalProduct();
						ttvTransactionalProduct.setProductCode(TTV);
						ttvTransactionalProduct.setTransactionTypeCd(OIS_PRODUCT_INSTANCE_ACTIVATION);
						ttvTransactionalProduct.setContractTermCnt(BigInteger.ZERO);
						transactionalProductList.add(ttvTransactionalProduct);
					}
				}
			}
					
			transactionalProductList.add(singProduct);
		}
		return transactionalProductList;
	}
	

	// 2018 Aug release for Pik TV
	// Pik TV upgrade is available
	private static List<TransactionalProduct> getTransactionalProductList(String offerContractTerm,
			WirelineOfferFilter wlnOfferFilter, SalesOfferCommonVO commonVO, boolean callForRecontractEligibleInd,String productCatalogId) {
		
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();
		
		//1- when productList is present in the request
		if(wlnOfferFilter!=null && wlnOfferFilter.getProductList()!=null && !wlnOfferFilter.getProductList().isEmpty()){
			// 2018 Aug release for Pik TV
			// Pik TV upgrade is available
			transactionalProductList = getTransactionalProductList(wlnOfferFilter.getProductList(), commonVO,productCatalogId);
		}else{
			//2 - offerContractTerm is Zero
			if(ZERO.equals(offerContractTerm)){
				//3 - OfferContractTerm is zero and customer isCustomerEligibleForRecontract
				if(commonVO.isCustomerEligibleForRecontract() && callForRecontractEligibleInd){
					transactionalProductList = getRecontractingEligibleTransactionalProducts(commonVO, callForRecontractEligibleInd);
				}else{
					// 4- OfferContractTerm is Zero and customer is not eligible for Re-contract
					// 2018 Aug release for Pik TV
					// Pik TV upgrade is available
					transactionalProductList = getTransactionalProductList(offerContractTerm,commonVO);
				}
			}else{
				//5 - No productList being passed in ESS request, no offerContractTerm - Scenario applicable to promoCode scenario
				//This scenario will bring the offers for all terms and all CID (TTV,HSIC,SING)
				// 2018 Aug release for Pik TV
				// Pik TV upgrade is available
							
				transactionalProductList = getTransactionalProductList("",commonVO);	
				
			}
		}
		
		return transactionalProductList;
	}
		
	// update method name to reflect the usage of it
	public static String getOmsOfferCatalogIdFromConsolidatedAccount(String productType,SalesOfferCommonVO commonVO) {
		String omsOfferCatalogId = null;
		if(commonVO!=null && commonVO.getAssignedAndPendingProductsResponseVO()!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = commonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			List<SubscribedProductInfoRestVO> pendingProducts = assignedAndPendingProducts.getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
			
			if(assignedProducts!=null && !assignedProducts.isEmpty()){
				for(SubscribedProductInfoRestVO assignedProduct : assignedProducts){
					if(productType.equalsIgnoreCase(assignedProduct.getProductTypeCd())){
						omsOfferCatalogId = assignedProduct.getProductInstance().get(0).getOmsOfferCatalogId();
						return omsOfferCatalogId;
					}
				}
			}
			else if(pendingProducts!=null && !pendingProducts.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProducts){
					if(productType.equalsIgnoreCase(pendingProduct.getProductTypeCd())){
						omsOfferCatalogId = pendingProduct.getProductInstance().get(0).getOmsOfferCatalogId();
						return omsOfferCatalogId;
					}
				}
			}
		}
		return omsOfferCatalogId;
	}
	
	public static boolean isPresentPikTv(GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO, SalesOfferCriteriaVO salesOfferCriteriaVO) {
		boolean isPikTvInd=false;
		/**
		 * Evaluating if the customer has existing products
		 */
		
		if(salesOfferCriteriaVO!=null && assignedAndPendingResponseVO!=null){
			//Getting the serviceInstance object from within the List
			ServiceAddressVO requestServiceAddress = salesOfferCriteriaVO.getServiceAddress();
			List<SubscribedServiceIdentifierVO> requestSubscribedServiceIdList = salesOfferCriteriaVO.getSubscribedServiceIdentifier();
			List<SubscribedProductInfoRestVO> assignedProductList = assignedAndPendingResponseVO.getAssignedProductListByServiceAddressAndServiceId(requestServiceAddress, requestSubscribedServiceIdList);
			for(SubscribedProductInfoRestVO subscribedProduct : assignedProductList){
				if(TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					List<ProductInstanceInfoRestVO> productInstanceList = subscribedProduct.getProductInstance();
					if(productInstanceList!=null && !productInstanceList.isEmpty()){
						for(ProductInstanceInfoRestVO productInstance : productInstanceList){
							String omsOfferCatalogId = productInstance.getOmsOfferCatalogId();
							if(!StringUtils.isEmpty(omsOfferCatalogId) && isPikTvCatalogId(omsOfferCatalogId)){
								isPikTvInd = true;
								break;
							}
						}
					}
				}
			}
		}
		return isPikTvInd;
	}

	public static boolean isPresentPikTv(GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO, AccessoryOfferCriteria accessoryOfferCriteria) {
		boolean isPikTvInd=false;
		/**
		 * Evaluating if the customer has existing products
		 */
		
		if (accessoryOfferCriteria!=null && assignedAndPendingResponseVO!=null) {
			//Getting the serviceInstance object from within the List
			ServiceAddressBase requestServiceAddress = accessoryOfferCriteria.getServiceAddress();
			List<ServiceIdentifier> requestSubscribedServiceIdList = accessoryOfferCriteria.getSubscribedServiceIdentifierList();
			List<SubscribedProductInfoRestVO> assignedProductList = assignedAndPendingResponseVO.getAssignedProductListByServiceAddressAndServiceId(requestServiceAddress, requestSubscribedServiceIdList);
			for(SubscribedProductInfoRestVO subscribedProduct : assignedProductList){
				if(TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					List<ProductInstanceInfoRestVO> productInstanceList = subscribedProduct.getProductInstance();
					if(productInstanceList!=null && !productInstanceList.isEmpty()){
						for(ProductInstanceInfoRestVO productInstance : productInstanceList){
							String omsOfferCatalogId = productInstance.getOmsOfferCatalogId();
							if(!StringUtils.isEmpty(omsOfferCatalogId) && isPikTvCatalogId(omsOfferCatalogId)){
								isPikTvInd = true;
								break;
							}
						}
					}
				}
			}
		}
		return isPikTvInd;
	}

	public static boolean isPendingPikTv(GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO, SalesOfferCriteriaVO salesOfferCriteriaVO,ServiceAddressResponseVO serviceAddress) {
		boolean result=false;
		/**
		 * Evaluating if the customer has pending TVX products
		 */
		
		if (salesOfferCriteriaVO != null && assignedAndPendingResponseVO != null) {
			ServiceAddressVO requestServiceAddress = salesOfferCriteriaVO.getServiceAddress();
			List<SubscribedProductInfoRestVO> pendingProductList = assignedAndPendingResponseVO
					.getPendingProductListByServiceAddress(requestServiceAddress,salesOfferCriteriaVO.getSubscribedServiceIdentifier(),serviceAddress);
			if (pendingProductList != null && !pendingProductList.isEmpty()) {
				for (SubscribedProductInfoRestVO pendingProduct : pendingProductList) {
					if (pendingProduct != null && pendingProduct.getProductTypeCd().equalsIgnoreCase(TTV)) {
						List<ProductInstanceInfoRestVO> productInstanceList = pendingProduct.getProductInstance();
						if (productInstanceList != null && !productInstanceList.isEmpty()) {
							for (ProductInstanceInfoRestVO productInstance : productInstanceList) {
								String omsOfferCatalogId = productInstance.getOmsOfferCatalogId();
								if (!StringUtils.isEmpty(omsOfferCatalogId) && isPikTvCatalogId(omsOfferCatalogId)) {
									result = true;
									break;
								}
							}
						}
					}
				}
			}
		}

		return result;
	}

	public static boolean isPikTvCatalogId(String omsOfferCatalogId) {
		boolean result=false;
		String[] omsOfferIdList = PIK_TV_OMS_OFFER_IDS.split(","); //TODO: do a HashSet.contains instead of checking length  
		if(omsOfferIdList!=null && omsOfferIdList.length!=0){
			for(String omsOfferId : omsOfferIdList){
				if(omsOfferId.equalsIgnoreCase(omsOfferCatalogId)){
					result=true;
					break;
				}
			}
		}
		return result;
	}

	private static TransactionalProduct getTransactionalProductByProductType(String productType,String offerContractTerm,SalesOfferCommonVO commonVO){
		TransactionalProduct transactionalProduct = new TransactionalProduct();
		String statusCode;
		String existingTerm;
		
		transactionalProduct.setProductCode(productType);
		
		
		statusCode = getStatusCodeByProductType(productType, commonVO);
		
		if(ZERO.equalsIgnoreCase(offerContractTerm)){
			//confirmed with Olivia, for the scenario where offerContractTerm=0
			//And customer has existing products, for the existing products, the existing contract term will be passed to OIS.
			existingTerm = getProductTermCodeForExistingCustomer(productType, commonVO);
			if(!StringUtils.isEmpty(existingTerm)){
				//Customer has existing product, using existing term to call OIS
				transactionalProduct.setTransactionTypeCd(statusCode);
				transactionalProduct.setContractTermCnt(new BigInteger(existingTerm));
			}else{
				//Customer doesn't have existing products, calling OIS with term 0
				transactionalProduct.setTransactionTypeCd(statusCode);
				transactionalProduct.setContractTermCnt(new BigInteger(offerContractTerm));
			}
		}else{
			transactionalProduct.setTransactionTypeCd(statusCode);
		}
		return transactionalProduct;
	}
	
	// 2018 Aug release for Pik TV
	// Pik TV upgrade is available
	private static List<TransactionalProduct> getTransactionalProductList(String offerContractTerm,
			SalesOfferCommonVO commonVO) {
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();

		// 2018 Aug release for Pik TV
		// Pik TV upgrade is available
		// if (!presentPikTvInd) {

			transactionalProductList.add(getTransactionalProductByProductType(HSIC, offerContractTerm, commonVO));
			transactionalProductList.add(getTransactionalProductByProductType(TTV, offerContractTerm, commonVO));
			transactionalProductList.add(getTransactionalProductByProductType(SING, offerContractTerm, commonVO));
			transactionalProductList.add(getTransactionalProductByProductType(OIS_ACCESSORIES_CD, offerContractTerm, commonVO));

		// } else {
		// 	transactionalProductList.add(getTransactionalProductByProductType(SING, offerContractTerm, commonVO));
		// }
			
		return transactionalProductList;
	}
	
	public static String getStatusCodeByProductType(String orderedProductType, SalesOfferCommonVO commonVO) {
		String statusCode=OIS_PRODUCT_INSTANCE_ACTIVATION;

		if (commonVO!=null) {
			boolean customerBadEligibilityInd = isCustomerBadEligibility(commonVO);

			if (!customerBadEligibilityInd) {
				if (commonVO.getAssignedAndPendingProductsResponseVO()!=null) {
					if (commonVO.getOffersRequestVO().getSalesOfferCriteria() != null) {
						ServiceAddressVO requestServiceAddress = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
						List<SubscribedServiceIdentifierVO> requestSubscribedServiceIdList = commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier();
						List<SubscribedProductInfoRestVO> assignedProductList = commonVO.getAssignedAndPendingProductsResponseVO().getAssignedProductListByServiceAddressAndServiceId(requestServiceAddress, requestSubscribedServiceIdList);
						List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());

						if (assignedProductList!=null && !assignedProductList.isEmpty()) {
							for (SubscribedProductInfoRestVO assignedProduct : assignedProductList) {
								if (assignedProduct!=null) {
									String assignedProductType = assignedProduct.getProductTypeCd();
									if (orderedProductType.equalsIgnoreCase(assignedProductType)) {
										statusCode = OIS_PRODUCT_INSTANCE_UPGRADE;
										break;
									}
								}
							}
						}
						else if (pendingProductList!=null && !pendingProductList.isEmpty()) {
							for (SubscribedProductInfoRestVO pendingProduct : pendingProductList) {
								if (pendingProduct!=null){
									String pendingProductType = pendingProduct.getProductTypeCd();
									if (orderedProductType.equalsIgnoreCase(pendingProductType)) {
										statusCode = OIS_PRODUCT_INSTANCE_UPGRADE;
										break;
									}
								}
							}
						}
					}
					else if (commonVO.getOffersRequestVO().getAccessoryOfferCriteria() != null) {
						ServiceAddressBase requestServiceAddress = commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getServiceAddress();
						List<ServiceIdentifier> requestSubscribedServiceIdList = commonVO.getOffersRequestVO().getAccessoryOfferCriteria().getSubscribedServiceIdentifierList();
						List<SubscribedProductInfoRestVO> assignedProductList = commonVO.getAssignedAndPendingProductsResponseVO().getAssignedProductListByServiceAddressAndServiceId(requestServiceAddress, requestSubscribedServiceIdList);
						List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(requestServiceAddress, requestSubscribedServiceIdList, commonVO.getServiceAddressResponseVO());

						if (assignedProductList!=null && !assignedProductList.isEmpty()) {
							for (SubscribedProductInfoRestVO assignedProduct : assignedProductList) {
								if (assignedProduct!=null) {
									String assignedProductType = assignedProduct.getProductTypeCd();
									if (orderedProductType.equalsIgnoreCase(assignedProductType)) {
										statusCode = OIS_PRODUCT_INSTANCE_UPGRADE;
										break;
									}
								}
							}
						}
						else if (pendingProductList!=null && !pendingProductList.isEmpty()) {
							for (SubscribedProductInfoRestVO pendingProduct : pendingProductList) {
								if (pendingProduct!=null){
									String pendingProductType = pendingProduct.getProductTypeCd();
									if (orderedProductType.equalsIgnoreCase(pendingProductType)) {
										statusCode = OIS_PRODUCT_INSTANCE_UPGRADE;
										break;
									}
								}
							}
						}
					}

				}
			}
			else {
				statusCode =OIS_PRODUCT_INSTANCE_UPGRADE;
			}
		}

		return statusCode;
	}
	
	public static String getProductTermCodeForExistingCustomer(String productType,SalesOfferCommonVO commonVO){
		String productTerm = null;
		if(commonVO!=null){
				if(commonVO.getAssignedAndPendingProductsResponseVO()!=null){
					ServiceAddressVO requestServiceAddress = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
					List<SubscribedServiceIdentifierVO> requestSubscribedServiceIdList = commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier();
					List<SubscribedProductInfoRestVO> assignedProductList = commonVO.getAssignedAndPendingProductsResponseVO().getAssignedProductListByServiceAddressAndServiceId(requestServiceAddress, requestSubscribedServiceIdList);
					List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
					if(assignedProductList!=null && !assignedProductList.isEmpty()){
						for(SubscribedProductInfoRestVO assignedProduct : assignedProductList){
							if(assignedProduct!=null){
								String assignedProductType = assignedProduct.getProductTypeCd();
								if(productType.equalsIgnoreCase(assignedProductType)){
									productTerm = assignedProduct.getProductInstance().get(0).getTermCd();
									
									// 2018 Aug release for Pik TV
									// if no productTerm returned and the product is suppressed, the product is HS0
									if(productTerm == null && assignedProduct.getProductInstance().get(0).getProductSuppressionInd()){
										productTerm = MTM_TERM;
									}
									
									break;
								}
							}
						}
					}else if(pendingProductList!=null && !pendingProductList.isEmpty()){
						for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
							if(pendingProduct!=null){
								String pendingProductType = pendingProduct.getProductTypeCd();
								if(productType.equalsIgnoreCase(pendingProductType)){
									productTerm = pendingProduct.getProductInstance().get(0).getTermCd();
									
									// 2018 Aug release for Pik TV
									// if no productTerm returned and the product is suppressed, the product is HS0
									if(productTerm == null && pendingProduct.getProductInstance().get(0).getProductSuppressionInd()){
										productTerm = MTM_TERM;
									}
									
									break;
								}
							}
						}
					}
		}
	}
		return getContracTermInMonthByYears(productTerm);
	}
	
	public static boolean isCustomerBadEligibility(SalesOfferCommonVO commonVO){
		boolean result=false;
		String functionName="isCustomerBadEligibility";
		
		if(commonVO!=null && commonVO.getCreditEligibilityAdapterResponseVO()!=null && !commonVO.getCreditEligibilityAdapterResponseVO().isMsgHasError()){ //TODODONE: check if the response has no error in order to continue
			GetCreditEligibilityAdapterResponse creditEligibilityResponse = commonVO.getCreditEligibilityAdapterResponseVO();
			if(creditEligibilityResponse.getCollectionInd() || creditEligibilityResponse.getFraudInd()){
				logger.info(functionName, "Customer has been flagged as 'in-treatment' or 'fraud', only offers for Upgrade will be displayed");
				result=true;
			}else{
				logger.info(functionName, "Customer has not been flagged as 'fraud' or 'in-treatment'");
			}
		}
		
		return result;
	}

	public static boolean hasPendingProduct(String orderedProductType, SalesOfferCommonVO commonVO) {
		if(commonVO!=null && commonVO.getAssignedAndPendingProductsResponseVO()!=null){
			ServiceAddressVO requestServiceAddress = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
			List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(requestServiceAddress,commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
			if(pendingProductList!=null && !pendingProductList.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
					if(pendingProduct!=null){
						String pendingProductType = pendingProduct.getProductTypeCd();
						if(orderedProductType.equalsIgnoreCase(pendingProductType)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean hasPendingOPTIKProduct(SalesOfferCommonVO commonVO) {
		if(commonVO!=null && commonVO.getAssignedAndPendingProductsResponseVO()!=null){
			ServiceAddressVO requestServiceAddress = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
			List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(requestServiceAddress,commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
			if(pendingProductList!=null && !pendingProductList.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
					if(pendingProduct!=null){
						String pendingProductType = pendingProduct.getProductTypeCd();
						if(HSIC.equalsIgnoreCase(pendingProductType) && ProductSpecificationMapper.isOptik(pendingProduct.getProductTierCd())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private static TransactionalProduct getTransactionalProductByProductType(String productType,OfferProductHeader product,SalesOfferCommonVO commonVO){
		TransactionalProduct transactionalProduct = new TransactionalProduct();
		String transactionTypeCd;
		transactionalProduct.setProductCode(productType);
		
		// 2018 Aug release for "nochange"
		if(product.getProductOrderType() != null &&
				product.getProductOrderType().getProductOrderTypeCd() != null){
			transactionTypeCd = product.getProductOrderType().getProductOrderTypeCd();
		} else {
		transactionTypeCd = getStatusCodeByProductType(productType, commonVO);
		}
		
		transactionalProduct.setTransactionTypeCd(transactionTypeCd);
		transactionalProduct.setRecontractInd(product.isRecontractInd());
		if(StringUtils.isNotBlank(product.getContractTermCd())){
			transactionalProduct.setContractTermCnt(new BigInteger(product.getContractTermCd()));
		} else {
			//2019-08 Bundle builder - pass the current term for existing product but not for HS Zero
			if (!HSIC.equalsIgnoreCase(product.getProductTypeCd())
					|| !WLNOfferUtil.checkProductsHasHSZERO(commonVO, commonVO.getAssignedProductListByServiceAddressAndServiceId())) {
				String existingTerm = getProductTermCodeForExistingCustomer(productType, commonVO);
				if (existingTerm != null && !ZERO.equals(existingTerm)) {
					transactionalProduct.setContractTermCnt(new BigInteger(existingTerm));
				}
			}
		}
		if (product.getProductComponentList() != null && !product.getProductComponentList().isEmpty()) {
			transactionalProduct.setProductCatalogIdList(getProductCatalogIdList(product.getProductComponentList()));
		} else {
			// 2018 Aug release for "nochange"
			// no need to find cid if CP doesn't receive it from previous call for getAvailableOfferSummaryList
		}

		return transactionalProduct;
	}

	private static TransactionalProduct getTransactionalProductByProductType(String productType, WirelineProductDiscount wirelineProductDiscount, SalesOfferCommonVO commonVO){
		TransactionalProduct transactionalProduct = new TransactionalProduct();
		String transactionTypeCd;
		transactionalProduct.setProductCode(productType);
		transactionTypeCd = getStatusCodeByProductType(productType, commonVO);
		transactionalProduct.setTransactionTypeCd(transactionTypeCd);
		transactionalProduct.setRecontractInd(wirelineProductDiscount.isRecontractInd());
		if(wirelineProductDiscount.getContractTermCd()!=null && !StringUtils.isEmpty(wirelineProductDiscount.getContractTermCd())){
			transactionalProduct.setContractTermCnt(new BigInteger(wirelineProductDiscount.getContractTermCd()));
		}
		if (wirelineProductDiscount.getProductComponentList() != null && !wirelineProductDiscount.getProductComponentList().isEmpty()) {
			transactionalProduct.setProductCatalogIdList(getProductCatalogIdList(wirelineProductDiscount.getProductComponentList()));
		}

		return transactionalProduct;
	}

		// 2018 Aug release for Pik TV
	// Pik TV upgrade is available
	private static List<TransactionalProduct> getTransactionalProductList(List<OfferProductHeader> productList,SalesOfferCommonVO commonVO, String productCatalogId) {
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();
		for(OfferProductHeader offerProduct : productList){
			// 2018 Aug release for Pik TV
			// Pik TV upgrade is available
			// if (!isPikTVInd) {
				if (offerProduct.getProductTypeCd().equalsIgnoreCase(HSIC)) {
					transactionalProductList.add(getTransactionalProductByProductType(HSIC, offerProduct, commonVO));
				} else if (offerProduct.getProductTypeCd().equalsIgnoreCase(TTV)) {
					transactionalProductList.add(getTransactionalProductByProductType(TTV, offerProduct, commonVO));
				} else if (offerProduct.getProductTypeCd().equalsIgnoreCase(SING)) {
					transactionalProductList.add(getTransactionalProductByProductType(SING, offerProduct, commonVO));
				} else if (offerProduct.getProductTypeCd().equalsIgnoreCase(OIS_ACCESSORIES_CD)) {
					transactionalProductList.add(getTransactionalProductByProductType(OIS_ACCESSORIES_CD, offerProduct, commonVO));
				}
			//} else {
			//	transactionalProductList.add(getTransactionalProductByProductType(SING, offerProduct, commonVO));
			//}
		}
		
		if(commonVO.getOfferDetailRequestVO()==null){
			enrichOrderedProductList(commonVO, transactionalProductList,productCatalogId);
		}
		
		
		return transactionalProductList;
	}

	//2019-08 Bundle builder - Add HSIC to OIS request when TTV is passed in the request and HSIC is not passed
	private static void enrichOrderedProductList(SalesOfferCommonVO commonVO,
				List<TransactionalProduct> transactionalProductList,String productCatalogId) {
		
		boolean requestHasTTV = false;
		boolean requestHasHSIC = false;

		for (TransactionalProduct x : transactionalProductList) {
			if (TTV.equalsIgnoreCase(x.getProductCode())) {
				requestHasTTV = true;
			} else if (HSIC.equalsIgnoreCase(x.getProductCode())) {
				requestHasHSIC = true;
			}
		}
		
		// TTV requested but not HSIC
		if (requestHasTTV && !requestHasHSIC) {
			TransactionalProduct transactionalProduct = new TransactionalProduct();
			transactionalProduct.setProductCode(HSIC);
			transactionalProductList.add(transactionalProduct);
			if (!isProductExists(HSIC, commonVO)) {
				//1. Customer has no HSIC
				transactionalProduct.setTransactionTypeCd(OIS_PRODUCT_INSTANCE_ACTIVATION);
				
				String hszeroCid = getInternalCidFromProductTierCd(NON_SELLABLE_PRODUCT);
				transactionalProduct.getProductCatalogIdList().add(hszeroCid);	

			} else {
			
				if (checkProductsHasHSZERO(commonVO, commonVO.getAssignedProductListByServiceAddressAndServiceId())
						|| checkProductsHasHSZERO(commonVO, commonVO.getPendingProductListByServiceAddress())) {
					//2. Customer has HS Zero
					transactionalProduct.setTransactionTypeCd(OIS_PRODUCT_INSTANCE_UPGRADE);
					
					String hszeroCid = getInternalCidFromProductTierCd(NON_SELLABLE_PRODUCT);
					transactionalProduct.getProductCatalogIdList().add(hszeroCid);	
				} else {		
					//3. Customer has HSIC other than HS Zero
					transactionalProduct.setTransactionTypeCd(OIS_PRODUCT_INSTANCE_UPGRADE);
			
					/*String existingHsicProductTierCd = getHsicSubscribedProductTierCdFromAssignedAndPendingProductsResponse(commonVO);
					String existingHsicCid = getInternalCidFromProductTierCd(existingHsicProductTierCd);
					transactionalProduct.getProductCatalogIdList().addAll(getAllProductCatalogIdOfSameProductFamilyGroup(existingHsicCid));*/
					if(StringUtils.isNotBlank(productCatalogId)){
						transactionalProduct.getProductCatalogIdList().add(productCatalogId);
					}
				}
			}
		}
		
	}

	public static List<String> getAllProductCatalogIdOfSameProductFamilyGroup(String cid) {
		
		List<String> result = new ArrayList<String>();

		if (StringUtils.isBlank(cid)) {
			return result;
		}
	
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		
		GetReferencePDSResponseDO rsp = refPdsBusDelegate.getReferencePDSTableObjectByName(REFPDS_WLN_SALES_PRODUCT_FAMILY_GROUP_RUL);
		Map<String, Object> refPdsMap = rsp.getRefpdsTable();
		 
		List<Map<String, String>> refWLNProductList = (List<Map<String, String>>)refPdsMap.get(REFPDS_WLN_SALES_PRODUCT_FAMILY_GROUP_RUL);
		
		if (refWLNProductList == null) {
			return result;
		}

		String productFamilyGroupName = null;
		//1. search for the PROD_FAMILY_GROUP_NM value for the given cid
		for (Map<String, String> productMap : refWLNProductList){
			if (cid.equals(productMap.get(PROD_CATALOGUE_ID))) {
				productFamilyGroupName = productMap.get(PROD_FAMILY_GROUP_NM);
				break;
			}
		}
		
		if (productFamilyGroupName == null) {
			return result; 
		}
		
		//2. collect all cids of the same PROD_FAMILY_GROUP_NM
		for (Map<String, String> productMap : refWLNProductList){
			if (productFamilyGroupName.equals(productMap.get(PROD_FAMILY_GROUP_NM))) {
				result.add(productMap.get(PROD_CATALOGUE_ID));
			}
		}

		return result;
	}

	public static List<String> getProductCatalogIdList(List<ProductComponentIdentifier> productComponentList) {
		List<String> productCatalogIdList = new ArrayList<String>();
		if(productComponentList!=null && !productComponentList.isEmpty()){
			for(ProductComponentIdentifier productComponent : productComponentList){
				if(productComponent.getProductCatalogueIdentifier()!=null){
					productCatalogIdList.add(productComponent.getProductCatalogueIdentifier());
				}
			}
		}
		return productCatalogIdList;
	}
	
	public static boolean responseHasError(int statusCode){
		boolean result = false;
		if(statusCode!=REQUEST_OK){
			result = true;
		}
		return result;
	}
	
	public static String getProductCatalogIdForCustomerHSICProduct(SalesOfferCommonVO commonVO){
		String catalogId=null;
		if(commonVO.getAssignedAndPendingProductsResponseVO()!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProductsResponseVO = commonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProductsResponseVO.getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			List<SubscribedProductInfoRestVO> pendingProducts = assignedAndPendingProductsResponseVO.getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
			
			if(pendingProducts!=null && !pendingProducts.isEmpty()){
				for(SubscribedProductInfoRestVO product : pendingProducts){
					if(HSIC.equalsIgnoreCase(product.getProductTypeCd())){
						catalogId = product.getProductInstance().get(0).getProductCatalogId();
						break;
					}
				}
			}else if(assignedProducts!=null && !assignedProducts.isEmpty()){
				for(SubscribedProductInfoRestVO product : assignedProducts){
					if(HSIC.equalsIgnoreCase(product.getProductTypeCd())){
						catalogId = product.getProductInstance().get(0).getProductCatalogId();
						break;
					}
				}
			}
		}
		return catalogId;
	}
	
	public static String getContracTermInMonthByYears(String years){
		String result = null;

		if (years != null) {
			if (years.equals(ZERO)) {
				result = ZERO;
			}
			else if (years.equals(ONE)) {
				result = ONE_YEAR_IN_MONTHS;
			}
			else if (years.equals(TWO)) {
				result = TWO_YEARS_IN_MONTHS;
			}
			else if (years.equals(THREE)) {
				result = THREE_YEARS_IN_MONTHS;
			}
			else if (years.equals(MTM_TERM)){
				result = ZERO;
			}
		}

		return result;
	}
	
	
	
	/*public static boolean isOfferForRecontracting(Offer offer,SalesOfferCommonVO commonVO){
		boolean result=false;
		
		if(offer!=null){
			if (offer.getOfferProduct() != null && offer.getOfferProduct().getWirelineOfferProductList() != null && !offer.getOfferProduct().getWirelineOfferProductList().isEmpty()) {
				for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
					List<BigInteger> temTermList = wirelineOfferProduct.getContractTermList();
					String oisTerm = temTermList.get(0).toString();
					if (!TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && !STV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())) {
						if (HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && !ZERO.equals(oisTerm)) {
							String recontractingProductTierCd = commonVO.getTierCdForRecontracting();
							if(!StringUtils.isEmpty(recontractingProductTierCd)){
								String productCatalogId = getInternalCidFromProductTierCd(recontractingProductTierCd);
								if(!StringUtils.isEmpty(productCatalogId)){
									if(isMatchingProductCatalogIdAndOfferCatalogId(wirelineOfferProduct, productCatalogId)){
										result = true;
										break;
									}
								}
							}
						}
					}else{
						break; //if product is TTV or STV then set offerForRecontracting as false
					}
				}
			}
		}
		return result;
	}
	*/
	
	public static boolean isCustomerEligibleForRecontracting(SalesOfferCommonVO commonVO){
		boolean recontractEligibleInd=false;
		
		if(getCustomerEligibleForRecontractingProduct(commonVO) != null &&
				getCustomerEligibleForRecontractingProduct(commonVO).size() > 0){
			recontractEligibleInd = true;
		}
		
		return recontractEligibleInd;
	}
	
	// 2018 June Exception release for TTV recontracting
	// base on the isCustomerEligibleForRecontracting() to create a new method to return a list of products eligible for recontracting
	// same logic as isCustomerEligibleForRecontracting, but return list of product instead of a flag since the re-contracting can be  both TTV and HSIC
	public static List<String> getCustomerEligibleForRecontractingProduct(SalesOfferCommonVO commonVO){
		List<String> recontractProducts=new ArrayList<String>();
		
//		Logic move from isCustomerEligibleForRecontracting to getCustomerEligibleForRecontractingProduct 
//		/*
//		 * As suggested by Allan K. on April 4th, 2018: re-factoring of method isCustomerEligibleForRecontracting
//		 */
		
		boolean isPendingMTMTTV=false;
		boolean isPendingSTV=false;
		boolean isPendingMTMHsic=false;
		
		boolean isSubscribedMTMTTV=false;
		boolean isSubscribedSTV=false;
		boolean isSubscribedMTMHsic=false;
		
		String pendingHSICCTierCd=null;
		String subscribedHSICTierCd=null;
		
		if(commonVO.getAssignedAndPendingProductsResponseVO()!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = commonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			List<SubscribedProductInfoRestVO> pendingProducts = assignedAndPendingProducts.getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
		
			//Iterate over the pending orders
			if(pendingProducts!=null && !pendingProducts.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProducts){
					if(TTV.equalsIgnoreCase(pendingProduct.getProductTypeCd()) && MTM_TERM.equalsIgnoreCase(pendingProduct.getProductInstance().get(0).getTermCd())){
						isPendingMTMTTV=true;
					}
					if(STV.equalsIgnoreCase(pendingProduct.getProductTypeCd())){
						isPendingSTV=true;
					}
					if(HSIC.equalsIgnoreCase(pendingProduct.getProductTypeCd())){
						// 2018 Aug release for Pik TV
						// also need to know what is the TierCd even though it is not MTM
						if(MTM_TERM.equalsIgnoreCase(pendingProduct.getProductInstance().get(0).getTermCd())){
							isPendingMTMHsic=true;
						}
						pendingHSICCTierCd=pendingProduct.getProductTierCd();
					}
				}
			}
			
			//Iterate over the existing products
			if(assignedProducts!=null && !assignedProducts.isEmpty()){
				for(SubscribedProductInfoRestVO assignedProduct : assignedProducts){
					if(TTV.equalsIgnoreCase(assignedProduct.getProductTypeCd()) && MTM_TERM.equalsIgnoreCase(assignedProduct.getProductInstance().get(0).getTermCd())){
						isSubscribedMTMTTV=true;
					}
					if(STV.equalsIgnoreCase(assignedProduct.getProductTypeCd())){
						isSubscribedSTV=true;
					}
					if(HSIC.equalsIgnoreCase(assignedProduct.getProductTypeCd())){
						// 2018 Aug release for Pik TV
						// also need to know what is the TierCd even though it is not MTM
						if(MTM_TERM.equalsIgnoreCase(assignedProduct.getProductInstance().get(0).getTermCd())){
							isSubscribedMTMHsic=true;
						}
						subscribedHSICTierCd=assignedProduct.getProductTierCd();
					}
				}
			}
			
			//Iterate over the pending orders
			if(pendingProducts!=null && !pendingProducts.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProducts){
					// 2018 June Exception release for TTV recontracting
					// to identify MTM TTV+HS0 for pending order
					if(isPendingMTMTTV && 
							HSIC.equalsIgnoreCase(pendingProduct.getProductTypeCd()) &&
							WLNOfferUtil.checkProductsHasHSZERO(commonVO, commonVO.getPendingProductListByServiceAddress())){
						isPendingMTMHsic=true;
						pendingHSICCTierCd=pendingProduct.getProductTierCd();
					}
				}
			}
			
			//Iterate over the existing products
			if(assignedProducts!=null && !assignedProducts.isEmpty()){
				for(SubscribedProductInfoRestVO assignedProduct : assignedProducts){
					// 2018 June Exception release for TTV recontracting
					// to identify MTM TTV+HS0 for subscribed product
					if(isSubscribedMTMTTV && 
							HSIC.equalsIgnoreCase(assignedProduct.getProductTypeCd()) &&
							WLNOfferUtil.checkProductsHasHSZERO(commonVO, commonVO.getAssignedProductListByServiceAddressAndServiceId())){
						isSubscribedMTMHsic=true;
						subscribedHSICTierCd=assignedProduct.getProductTierCd();
					}
				}
			}
			
			//Evaluating...
			if (!isPendingSTV && !isSubscribedSTV) {
				if(isPendingMTMHsic){
					recontractProducts.add(HSIC);
					commonVO.setTierCdForRecontracting(pendingHSICCTierCd);
				}
				// Fix for QC 65443
				if(isSubscribedMTMHsic){
					recontractProducts.add(HSIC);
					commonVO.setTierCdForRecontracting(subscribedHSICTierCd);
				}
				// Fix for QC 65443
				if(isPendingMTMTTV){
					recontractProducts.add(TTV);
				
					if(!isPendingMTMHsic){
						commonVO.setHsTierCdForTTVRecontracting(pendingHSICCTierCd);
					}
				}				
				// Fix for QC 65443
				if(isSubscribedMTMTTV){
					recontractProducts.add(TTV);
					
					if(!isSubscribedMTMHsic){
						commonVO.setHsTierCdForTTVRecontracting(subscribedHSICTierCd);
					}
				}
			}
	
		}
		return recontractProducts;
	}
	
	// 2018 June Exception release for TTV recontracting
	// new method to identify is the offer eligible for recontracting
	public static boolean isOfferForRecontracting(Offer offer,SalesOfferCommonVO commonVO){
		return !getRecontractEligibleProductCodeList(offer, commonVO, false).isEmpty();
	}
		
	// 2018 June Exception release for TTV recontracting
	// comment out as the modify version should be used
//	public static boolean isOfferForRecontracting(Offer offer,SalesOfferCommonVO commonVO){
//		boolean result=false;
//		
//		boolean offerHasHsicNoZeroTerm=false;;
//		boolean offerHasTTVProduct=false;
//		boolean offerHasSTVProduct=false;
//		boolean offerAndSubscribedProductCidMatchingInd=false;
//		
//		if(offer!=null){
//			if (offer.getOfferProduct() != null && offer.getOfferProduct().getWirelineOfferProductList() != null && !offer.getOfferProduct().getWirelineOfferProductList().isEmpty()) {
//				for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
//					List<BigInteger> oisTermList = wirelineOfferProduct.getContractTermList();
//					String oisTerm = oisTermList.get(0).toString();
//					if(TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
//						offerHasTTVProduct=true;
//					}
//					if(STV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
//						offerHasSTVProduct=true;
//					}
//					if(HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && !ZERO.equals(oisTerm)){
//						offerHasHsicNoZeroTerm=true;
//					}
//				}
//			}
//			
//			String recontractingProductTierCd = commonVO.getTierCdForRecontracting();
//			if(!StringUtils.isEmpty(recontractingProductTierCd)){
//				String productCatalogId = getInternalCidFromProductTierCd(recontractingProductTierCd);
//				if(!StringUtils.isEmpty(productCatalogId)){
//					if(isMatchingProductCatalogIdAndOfferCatalogId(offer, productCatalogId)){
//						offerAndSubscribedProductCidMatchingInd = true;
//					}
//					
//				}
//			}
//			
//			//Comparing the results
//			if(offerHasHsicNoZeroTerm && !offerHasSTVProduct && !offerHasTTVProduct && offerAndSubscribedProductCidMatchingInd){
//				result=true;
//				logger.info("isOfferForRecontracting", "offerId: " +  offer.getOfferId() + " and offer Term: " + offer.getOfferProduct().getWirelineOfferProductList().get(0).getContractTermList().get(0) + " is eligible for Re-contracting");
//			}
//		}
//		return result;
//	}
		
	// 2018 June Exception release for TTV recontracting
	// new method to identify is the offer eligible for like-for-like recontracting
	public static boolean isOfferForLikeForLikeRecontracting(Offer offer,SalesOfferCommonVO commonVO){
		List<String> likeForLikeRecontractingProductCodeList = getRecontractEligibleProductCodeList(offer, commonVO, true);
		if (likeForLikeRecontractingProductCodeList.isEmpty()){
			return false;
		}
		
		if (offerHasProduct(offer, SING, false)){
			return offer.getOfferProduct().getWirelineOfferProductList().size() == (likeForLikeRecontractingProductCodeList.size() + 1);
		} else {
			return offer.getOfferProduct().getWirelineOfferProductList().size() == likeForLikeRecontractingProductCodeList.size();
		}

	}
			
	// 2018 June Exception release for TTV recontracting
	// base on the isCustomerEligibleForRecontracting() to create a new method to return a list of products eligible for recontracting
	public static List<String> getRecontractEligibleProductCodeList(Offer offer,SalesOfferCommonVO commonVO, boolean likeForLikeOnly){
		String methodName = "getRecontractEligibleProductCodeList";
		
		List<String> recontractableProducts = new ArrayList<String>();
		
		boolean offerHasHsicNoZeroTerm=offerHasProduct(offer, HSIC, true);
		boolean offerHasTTVNoZeroTerm=offerHasProduct(offer, TTV, true);
		boolean offerHasSTVProduct=offerHasProduct(offer, STV, false);
		  
		boolean offerAndSubscribedProductCidMatchingIndForHSIC=false;
		boolean offerAndSubscribedProductCidMatchingIndForTTV=false;
		
		if(offer!=null){
			String recontractingProductTierCd = commonVO.getTierCdForRecontracting();
			if(!StringUtils.isEmpty(recontractingProductTierCd) && likeForLikeOnly){
				String productCatalogId = getInternalCidFromProductTierCd(recontractingProductTierCd);
				if(!StringUtils.isEmpty(productCatalogId)){
					if(isMatchingProductCatalogIdAndOfferCatalogId(offer, productCatalogId)){
						offerAndSubscribedProductCidMatchingIndForHSIC = true;
					}
				}
			}
			
			offerAndSubscribedProductCidMatchingIndForTTV = isOfferAndSubscribedProductCidMatchingInd(offer, commonVO);
			
			//Comparing the results
			if (!offerHasSTVProduct) {
				// Fix for QC 65443
				if(offerHasHsicNoZeroTerm && getCustomerEligibleForRecontractingProduct(commonVO).contains(HSIC)){
					if (likeForLikeOnly){
						if (offerAndSubscribedProductCidMatchingIndForHSIC) {
							recontractableProducts.add(HSIC);
							logger.info(methodName, "offerId: " +  offer.getOfferId() + " HSIC is eligible for like-to-like recontracting.");
						}
					} else {
						recontractableProducts.add(HSIC);
						logger.info(methodName, "offerId: " +  offer.getOfferId() + " HSIC is eligible for recontracting.");
					}					
				}
				// Fix for QC 65443
				if (offerHasTTVNoZeroTerm && 
						offerAndSubscribedProductCidMatchingIndForTTV &&
						getCustomerEligibleForRecontractingProduct(commonVO).contains(TTV)){
					recontractableProducts.add(TTV);
					logger.info(methodName, "offerId: " +  offer.getOfferId() + " TTV is eligible for like-to-like recontracting.");
				}
//				logger.info(methodName, "offerId: " +  offer.getOfferId() + " and offer Term: " + offer.getOfferProduct().getWirelineOfferProductList().get(0).getContractTermList().get(0) + " is eligible for Re-contracting");
			}
		}
		return recontractableProducts;
	}
	
	/**
	 * @param offer
	 * @param isNoZeroTerm
	 * @return
	 */
	// 2018 June Exception release for TTV recontracting
	// util method to check is the offer has the specific product
	public static boolean offerHasProduct(Offer offer, String productCode, boolean isNoZeroTerm) {
		if (offer != null && offer.getOfferProduct() != null && offer.getOfferProduct().getWirelineOfferProductList() != null && !offer.getOfferProduct().getWirelineOfferProductList().isEmpty()) {
			for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
				if (productCode.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
					List<BigInteger> oisTermList = wirelineOfferProduct.getContractTermList();
					
					if(oisTermList != null && !oisTermList.isEmpty()){
						String oisTerm = oisTermList.get(0).toString();
					
						if (isNoZeroTerm) {
							return !ZERO.equals(oisTerm);
						} else {
							return true;
						}
					}
				}
			}
		}
		return false;
	}	

	/*public static boolean isCustomerEligibleForRecontracting(SalesOfferCommonVO commonVO){
		boolean result=false;
		if(commonVO.getAssignedAndPendingProductsResponseVO()!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = commonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			List<SubscribedProductInfoRestVO> pendingProducts = assignedAndPendingProducts.getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
			Boolean assignedProductsEligible=null;
			Boolean pendingProductsEligible=null;
			
			*//**
			 * Rules:
			 * 1. Customer must not have subscribed or pending TTV or STV product
			 * 2. Customer must have subscribed or pending HSIC with a MTM term plan
			 * 
			 *//*
			
			if(pendingProducts!=null && !pendingProducts.isEmpty()){
				for(SubscribedProductInfoRestVO pendingProduct : pendingProducts){
					if(!STV.equalsIgnoreCase(pendingProduct.getProductTypeCd()) || !TTV.equalsIgnoreCase(pendingProduct.getProductTypeCd())){ 
						if(HSIC.equalsIgnoreCase(pendingProduct.getProductTypeCd()) && MTM_TERM.equalsIgnoreCase(pendingProduct.getProductTypeCd())){
							pendingProductsEligible=Boolean.TRUE;
							commonVO.setProductCatalogIdForRecontracting(pendingProduct.getProductInstance().get(0).getProductCatalogId());
							break;
						}
					}
				}
			}else if(assignedProducts!=null && !assignedProducts.isEmpty()){
				for(SubscribedProductInfoRestVO assignedProduct : assignedProducts){
					if(!STV.equalsIgnoreCase(assignedProduct.getProductTypeCd()) || !TTV.equalsIgnoreCase(assignedProduct.getProductTypeCd())){
						if(HSIC.equalsIgnoreCase(assignedProduct.getProductTypeCd()) && MTM_TERM.equalsIgnoreCase(assignedProduct.getProductInstance().get(0).getTermCd())){
							assignedProductsEligible=Boolean.TRUE;
							commonVO.setProductCatalogIdForRecontracting(assignedProduct.getProductInstance().get(0).getProductCatalogId());
							break;
						}
					}
				}
			}		
			if((pendingProductsEligible!=null && pendingProductsEligible==Boolean.TRUE) || (assignedProductsEligible!=null && assignedProductsEligible==Boolean.TRUE)){
				//Customer is eligible for Re contracting
				result=true;
			}
		
		}
		return result;
	}*/
	
	private static boolean isMatchingProductCatalogIdAndOfferCatalogId(Offer offer,String productCatalogId){
		boolean result=false;
		if(offer!=null && offer.getOfferProduct()!=null && offer.getOfferProduct().getWirelineOfferProductList()!=null && !offer.getOfferProduct().getWirelineOfferProductList().isEmpty()){
			for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
				if(wirelineOfferProduct!=null && wirelineOfferProduct.getProductComponentList()!=null && !wirelineOfferProduct.getProductComponentList().isEmpty()){
					for(ProductComponent productComponent : wirelineOfferProduct.getProductComponentList()){
						if(productComponent!=null && productComponent.getProductCatalogueItemList()!=null && !productComponent.getProductCatalogueItemList().isEmpty()){
							for(ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()){
								if(productCatalogueItem!=null && productCatalogueItem.getProductCatalogueIdentifier()!=null){
									if(productCatalogId.equals(productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId())){
										result=true;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return result;
	}

	// 2018 June Exception release for TTV recontracting
	// commont out as a modify version should be used
//	public static List<String> getRecontractEligibleProductCodeList(Offer offer, SalesOfferCommonVO commonVO) {
//		List<String> result = new ArrayList<String>();
//		boolean isOfferForRecontracting = WLNOfferUtil.isOfferForRecontracting(offer, commonVO);
//		if(isOfferForRecontracting){
//			result.add(EnterpriseWLNSalesServicesConstants.HSIC); //In the future, other products will be considered as re-contracting product
//		}
//		return result;
//	}
	
	public static String getInternalCidFromProductTierCd(String productTierCd){
		
			ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		Map<String, String> wssProductTierMap = MapUtils.invertMap(refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER));
		String wssProductKey = null;
		String externalCId = null;
		if(wssProductTierMap!=null){
			wssProductKey= wssProductTierMap.get(productTierCd);
		}
		 
		Map<String, String> wssProductOmsCidMap = refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP);
		if(wssProductOmsCidMap!=null){
			externalCId = wssProductOmsCidMap.get(wssProductKey);
		}
		
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		

		CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemByExternalId(externalCId);
		
		if (catalogueItemDO!=null){
			return catalogueItemDO.getCatalogueItemId();
		} else {
			return null;
		}
	}

	public static String mapOmsCode(String productCode) {
		String functionName = "mapOmsCode";
		String cidCode = "";
		String omsCode = ""; 
		Map<String,Object> wssProdOmsCidEntity = null;
		Map<String, String> wssProdOmsCidMap = null;
		Map<String,Object> wssProductTierEntity = null;
		Map<String,String> wssProductTier = null;
		
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		
		GetReferencePDSResponseDO wssProdOmsCidResponse = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP);
		
		if(wssProdOmsCidResponse!=null && wssProdOmsCidResponse.isMsgHasError()){
			for (MessageDO message : wssProdOmsCidResponse.getMsgList()) {
				for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
					logger.warn(functionName, messageDescription.getMessageDescriptionText());
				}
			}
		}
		
		GetReferencePDSResponseDO wssProductTierResponse = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER);
		
		if(wssProductTierResponse!=null && wssProductTierResponse.isMsgHasError()){
			for (MessageDO message : wssProductTierResponse.getMsgList()) {
				for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
					logger.warn(functionName, messageDescription.getMessageDescriptionText());
				}
			}
		}
		
		if(wssProdOmsCidResponse!=null && !wssProdOmsCidResponse.isMsgHasError()){
			wssProdOmsCidEntity = wssProdOmsCidResponse.getRefpdsTable();
			wssProdOmsCidMap = MapUtils.invertMap((Map<String,String>) (wssProdOmsCidEntity.get(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP)));
		}
		
		if(wssProductTierResponse!=null && !wssProductTierResponse.isMsgHasError()){
			wssProductTierEntity = wssProductTierResponse.getRefpdsTable();
			wssProductTier = (Map<String,String>) wssProductTierEntity.get(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER);
		}
		
		if (wssProdOmsCidMap != null) {
			cidCode = wssProdOmsCidMap.get(productCode);
		}
	
		if (cidCode != null){
			if (wssProductTier != null) {
				omsCode = wssProductTier.get(cidCode);
			}
		}
		return omsCode;
	}

	public static boolean isProductQualified(String productConstant, GetProductQualificationAdapterResponse availableProducts) {
		if (availableProducts != null && availableProducts.getProductQualificationList() != null) {
			for (ProductQualification availableProduct : availableProducts.getProductQualificationList()) {
				for (Product prod : availableProduct.getProductList()) {
					if (productConstant.equalsIgnoreCase(prod.getProductTypeCd()) 
							&& isProductTierCdValidForTTV(productConstant, prod)
							&& (prod.getUnavailableInd() == null || !prod.getUnavailableInd())) {
						return true;
					}
				}
			} 
		}
		return false;
	}

	/**
	 * @param productConstant
	 * @param prod
	 * @return
	 */
	private static boolean isProductTierCdValidForTTV(String productConstant, Product prod) {
		if (TTV.equalsIgnoreCase(productConstant) && productConstant.equalsIgnoreCase(prod.getProductTypeCd())) {
			// from Allan: make sure productTier code is not null or empty for TTV, otherwise, it will be considered as "Not Qualified"
			return isTTVProductTierCdNonEmpty(prod.getProductTierCd()); //NWLN-4580
		}
		return true;
	}
	
	//NWLN-4580
	public static boolean isTTVProductTierCdNonEmpty(String productTierCd) {
		return !StringUtils.isEmpty(productTierCd);
	}
	
	public static boolean isTTVProductTierCdNonEmptyAndNotTVX(String productTierCd) {
		return !StringUtils.isEmpty(productTierCd) && !PRODUCT_TIER_CD_TVX.equalsIgnoreCase(productTierCd);
	}

	// 2018 Aug release for Pik TV
	// return maxTvProfile
	public static String getMaxTvProfile(GetProductQualificationAdapterResponse availableProducts) {
		if (availableProducts != null && availableProducts.getProductQualificationList() != null) {
			for (ProductQualification availableProduct : availableProducts.getProductQualificationList()) {
				for (Product prod : availableProduct.getProductList()) {
					if (TTV.equalsIgnoreCase(prod.getProductTypeCd()) 
							&& !StringUtils.isEmpty(prod.getMaxTvProfile())){
						return prod.getMaxTvProfile();
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param productList
	 * @param prodListAvailability
	 * @return
	 */
	public static void checkProductListAvailability(SalesOfferCommonVO commonVO, List<String> prodListNotAvailable) {
		String hsicTierCode = "";
		if (commonVO.getOffersRequestVO() != null && commonVO.getOffersRequestVO().getSalesOfferCriteria() != null 
				&& commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter() != null 
				&& !commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().getProductList().isEmpty()) {
			for (OfferProductHeader prod: commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().getProductList()) {
				if (!isProductQualified(prod.getProductTypeCd(), commonVO.getProductQualificationAdapterResponseVO())) {
					prodListNotAvailable.add(prod.getProductTypeCd());
				} else if (HSIC.equalsIgnoreCase(prod.getProductTypeCd()) && prod.getProductComponentList() != null 
						&& !prod.getProductComponentList().isEmpty() && !StringUtils.isEmpty(prod.getProductComponentList().get(0).getProductCatalogueIdentifier())) {
					// check also if the request product tier code is found in product qualification response
					String productCatalogueIdentifier = prod.getProductComponentList().get(0).getProductCatalogueIdentifier();
					if (!StringUtils.isEmpty(productCatalogueIdentifier)) { // GSOD might have an empty CID
						CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
						CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueIdentifier);
						if (catalogueItemDO != null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())) {
							hsicTierCode = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
							commonVO.setHsicTierCodeForRequestedProduct(hsicTierCode);
							if (!isRequestedHsicTierCodeFound(hsicTierCode, commonVO.getProductQualificationAdapterResponseVO())) {
								prodListNotAvailable.add(prod.getProductTypeCd());
							}
						} else {
							prodListNotAvailable.add(prod.getProductTypeCd());
						}
						
					}
				}
			}
		}
	}

	/**
	 * @param hsicTierCode
	 * @param productQualificationAdapterResponseVO
	 * @return
	 */
	public static boolean isRequestedHsicTierCodeFound(String hsicTierCode,
			GetProductQualificationAdapterResponse productQualificationAdapterResponseVO) {
		if (productQualificationAdapterResponseVO != null && productQualificationAdapterResponseVO.getProductQualificationList() != null && !productQualificationAdapterResponseVO.getProductQualificationList().isEmpty()) {
			for (ProductQualification prodQ : productQualificationAdapterResponseVO.getProductQualificationList()) {
				for (Product prod: prodQ.getProductList()) {
					if (HSIC.equalsIgnoreCase(prod.getProductTypeCd()) && hsicTierCode.equalsIgnoreCase(prod.getProductTierCd())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void checkFeasibilityResponseNoPorts(SalesOfferCommonVO commonVO, Serializable responseVO,String errorCode) {
		// Jose.Mena: fix for Defect 63430: When calling GAOSL or GSOD, if no ports available then include an error message in the response. 
		// Response excludes any offer that has TTV and HSIC offers in response.
		/*if (checkProductFeasibilityContainsNoPortsAvailable(commonVO.getCheckFeasibilityResponseVO())) {
			// New Requirement: if call to checkProductFeasibility contains any of these errors:
			// "FCCP06_E1", "PDPS02_E1","PDPS02_E3","PDPS02_E4"
			// (No ports available)
			// response excludes any offer that has TTV and HSIC offers in response
			if (commonVO.getOfferListAdapterResponse() != null 
					&& commonVO.getOfferListAdapterResponse().getOfferList() != null 
					&& !commonVO.getOfferListAdapterResponse().getOfferList().isEmpty()) {
				Iterator<Offer> offerIterator = commonVO.getOfferListAdapterResponse().getOfferList().iterator();
				while (offerIterator.hasNext()) {
					Offer currOffer = offerIterator.next();
					
					Iterator<WirelineOfferProduct> productIterator = currOffer.getOfferProduct().getWirelineOfferProductList().iterator();
					while (productIterator.hasNext()) {
						WirelineOfferProduct currOfferProd = productIterator.next();
						if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(currOfferProd.getProductTypeCode()) 
								|| EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(currOfferProd.getProductTypeCode())) {
							productIterator.remove();
						}
					}
					if (currOffer.getOfferProduct().getWirelineOfferProductList().isEmpty()) {
						offerIterator.remove();
					}
				}
			}
			
		}*/
		
		if(commonVO.isNoPortsAvailableInd()){
			// log the message in the response
			if (responseVO instanceof GetOffersResponseVO) {
			// GAOSL
			((GetOffersResponseVO) responseVO).getMessageList().addAll(generateWarningMessageNoPortsAvailable(commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId(), 
			errorCode, NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY_MSG));
			} else if (responseVO instanceof GetSalesOfferDetailResponseVO) {
			// GSOD
			((GetSalesOfferDetailResponseVO) responseVO).getMessageList().addAll(generateWarningMessageNoPortsAvailable(commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId(), 
			errorCode, NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY_MSG));
			}
		}
	}
	
	/**
	 * @param checkFeasibilityResponseVO
	 * @return
	 */
	public static boolean checkProductFeasibilityContainsNoPortsAvailable(
			CheckProductFeasibilityAdapterResponse checkFeasibilityResponseVO) {
		if (checkFeasibilityResponseVO != null 
				&& checkFeasibilityResponseVO.getResponseMessageList() != null 
				&& !checkFeasibilityResponseVO.getResponseMessageList().getResponseMessage().isEmpty()) {
			for (ResponseMessage msg: checkFeasibilityResponseVO.getResponseMessageList().getResponseMessage()) {
				if (isPortNotAvailableErrorCode(msg.getErrorCode())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param errorCode
	 * @return
	 */
	private static boolean isPortNotAvailableErrorCode(String errorCode) {
		if (!StringUtils.isEmpty(errorCode)) {
			return EnterpriseWLNSalesServicesConstants.NO_PORTS_AVAILABLE_ERROR_CODES.contains(errorCode);
		}
		return false;
	}
	
	private static List<MessageList> generateWarningMessageNoPortsAvailable(String transactionId, String errorCode, String errorMessage) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList msgList = new MessageList();
		msgList.setDateTimeStamp(new Date());
		msgList.setTransactionId(transactionId);
		msgList.setErrorCode(errorCode);
		msgList.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
		
		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		msg.setMessage(errorMessage);
		msgList.setMessageList(Arrays.asList(msg));
		
		messageList.add(msgList);
		return messageList;
	}

	/**
	 * 
	 * @param offer
	 * @param commonVO
	 * @return
	 */
	 // 2018 June Exception release for TTV recontracting
	 // new method to check is the subscribed/pending product cid is the same is the one in the offer
	 // this is used to check is the subscribed/pending TTV has the same cid as the one in the offer
	public static boolean isOfferAndSubscribedProductCidMatchingInd(Offer offer, SalesOfferCommonVO commonVO) {
		
		List<SubscribedProductInfoRestVO> assignedProducts = null;
		List<SubscribedProductInfoRestVO> pendingProducts = null;
		
		if(commonVO.getAssignedAndPendingProductsResponseVO() != null){
			assignedProducts = commonVO.getAssignedProductListByServiceAddressAndServiceId();
			pendingProducts = commonVO.getPendingProductListByServiceAddress();			
		}
		
		SubscribedProductInfoRestVO subscribedTTVProduct = null;
		List<String> productCatalogIdsFromSubscriber = new ArrayList<String>();
		List<String> productCatalogIdFromOffer = new ArrayList<String>();
		if ((assignedProducts == null || assignedProducts.isEmpty()) && (pendingProducts == null || pendingProducts.isEmpty())) {
			return false;
		}
		else  {
			if (!assignedProducts.isEmpty()) {
				for (SubscribedProductInfoRestVO subscribedProduct : assignedProducts) {
					if (TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
						subscribedTTVProduct = subscribedProduct;
						break;
					}
				}
			}
			if (!pendingProducts.isEmpty() && subscribedTTVProduct==null) {
				for (SubscribedProductInfoRestVO subscribedProduct : pendingProducts) {
					if (TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
						subscribedTTVProduct = subscribedProduct;
						break;
					}
				}
			}
				
		}
	
		productCatalogIdsFromSubscriber = retrieveProductCatalogIdsForTTV(subscribedTTVProduct);
		if (productCatalogIdsFromSubscriber.isEmpty()) {
			return false;
		}
		//productCatalogIdFromOffer = retrieveProductCatalogIdsForTTV(subscribedTTVProduct);
			
		List<WirelineOfferProduct> productComponentList = offer.getOfferProduct().getWirelineOfferProductList();
		if (productComponentList!=null) {
			for (WirelineOfferProduct wirelineOfferProduct : productComponentList) {
				if (TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
					if ( wirelineOfferProduct.getProductComponentList() !=null) {
						for (ProductComponent productComponent : wirelineOfferProduct.getProductComponentList()) {	
							if ( productComponent.getProductCatalogueItemList() !=null) {
								for (ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()) {	
										productCatalogIdFromOffer.add(productCatalogueItem.getProductCatalogueIdentifier().getExternalProductCatalogId());
								}	
							}
						}
					}
				}
			}
		}
		if (productCatalogIdFromOffer.isEmpty()) {
			return false;
		}
		productCatalogIdFromOffer.removeAll(productCatalogIdsFromSubscriber);
		return productCatalogIdFromOffer.isEmpty();
	}
	/**
	 * 
	 * @param subscribedTTVProduct
	 * @return
	 */
	// 2018 June Exception release for TTV recontracting
	// retrieve the cid for TTV product 
	static List<String>  retrieveProductCatalogIdsForTTV(SubscribedProductInfoRestVO subscribedTTVProduct) {
		
		List<String> componentIdsForTTV = new ArrayList<String>();
		List<String> productCatalogIdsForTTV = new ArrayList<String>();
		
		if (subscribedTTVProduct==null || 
				subscribedTTVProduct.getProductInstance()==null 
				|| subscribedTTVProduct.getProductInstance().get(0) == null 
				|| subscribedTTVProduct.getProductInstance().get(0).getTtvComponent() ==null) {
			return  productCatalogIdsForTTV;
		} else {
  
			List<PackInfoRestVO> comboList = subscribedTTVProduct.getProductInstance().get(0).getTtvComponent().getTvComboList();
			if (comboList!=null) {
				for (PackInfoRestVO component : comboList ) {
					componentIdsForTTV.add(component.getComponentId());
					
				}
			}

			List<PackInfoRestVO> packList = subscribedTTVProduct.getProductInstance().get(0).getTtvComponent().getTvPackList();
			if (packList!=null) {
				for (PackInfoRestVO component : packList ) {
					componentIdsForTTV.add(component.getComponentId());
				}
			}
		}
		
		//retrieve productCatalogIds from the Grid
//		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
//		
//		for (String componentId : componentIdsForTTV) {
//			CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemByExternalId(componentId);
//			if (catalogueItemDO!=null) productCatalogIdsForTTV.add(catalogueItemDO.getCatalogueItemId());
//		}
		
		return componentIdsForTTV;
	}
	
    // 2018 June Exception release for TTV recontracting
    // new method to find out is the assigned product is HS0 because TOM HS0 has different transaction type then other HSIC product
	// confirmed with Olivia that we can based on the productsuppressionInd
	public static boolean checkProductsHasHSZERO(SalesOfferCommonVO commonVO, List<SubscribedProductInfoRestVO> products){
		boolean hasHSZERO = false;
		
		if(products != null){
			for(SubscribedProductInfoRestVO subscribedProductInfoRestVO: products){
				if(!hasHSZERO){
					hasHSZERO = checkProductHasHSZERO(subscribedProductInfoRestVO);
				}
			}
		}
		
		return hasHSZERO;
	}
	
    // 2018 June Exception release for TTV recontracting
    // new method to find out is the assigned product is HS0 because TOM HS0 has different transaction type then other HSIC product
	// confirmed with Olivia that we can based on the productsuppressionInd
	private static boolean checkProductHasHSZERO(SubscribedProductInfoRestVO subscribedProductInfoRestVO){
		if(subscribedProductInfoRestVO.getProductTypeCd().equals(EnterpriseWLNSalesServicesConstants.HSIC) &&
				subscribedProductInfoRestVO.getProductInstance() != null &&
				!subscribedProductInfoRestVO.getProductInstance().isEmpty()){
			for(ProductInstanceInfoRestVO productInstanceInfoRestVO: subscribedProductInfoRestVO.getProductInstance()){
				if(productInstanceInfoRestVO.getProductSuppressionInd()){
					// HS0
					return true;
				}
			}
		}
		
		return false;
	}

	public static String getRuleContextInformation(Offer offer) {
		StringBuilder sb = new StringBuilder();

		for (WirelineOfferProduct product : offer.getOfferProduct().getWirelineOfferProductList()) {
			sb.append("ProductType: ");
			sb.append(product.getProductTypeCode());

			if(product.getProductComponentList() != null && !product.getProductComponentList().isEmpty()){
				for (ProductComponent productComponent : product.getProductComponentList()) {
					for (ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()) {
						sb.append(", Product CID: ");
						sb.append(productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId());
					}
				}
			}

			if(product.getContractTermList() != null && !product.getContractTermList().isEmpty()){
				for (BigInteger contractTerm : product.getContractTermList()) {
					sb.append(", Product ContactTerm: ");
					sb.append(contractTerm);
				}
			}

			sb.append(", Offer Id: ");
			sb.append(offer.getOfferId());
			sb.append("; ");
		}

		return sb.toString();
	}
	
	public static boolean feasibilityHasTierNotFeasible(CheckProductFeasibilityAdapterResponse checkFeasibilityResponseVO) {
		boolean result=false;
		if(checkFeasibilityResponseVO.getResponseMessageList()!=null && checkFeasibilityResponseVO.getResponseMessageList().getResponseMessage()!=null && !checkFeasibilityResponseVO.getResponseMessageList().getResponseMessage().isEmpty()){
			for(ResponseMessage response : checkFeasibilityResponseVO.getResponseMessageList().getResponseMessage()){
				if(response!=null && EnterpriseWLNSalesServicesConstants.FEASIBILITY_ERROR_TIER_NOT_FEASIBLE.equalsIgnoreCase(response.getErrorCode())){
					result=true;
					break;
				}
			}
		}
		return result;
	}
	/**
	 *
	 * @return
	 */
	public static String getBaseTTVComponentId(SalesOfferCommonVO commonVO) {
		String essentialsV1Internal = ApplicationProperties.getEssentialsV1InternalCID();
		String essentialsV2Internal = ApplicationProperties.getEssentialsV2InternalCID();
		String tvLiteInternal = ApplicationProperties.getTVLiteInternalCID();
		String pikTVInternal = ApplicationProperties.getPikTVInternalCID();
		
		List<String> ttvProductCatalogIds = retrieveAssignedAndPendingTTVComponents(commonVO);
		String ttvAssignedComponentId = null;
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		
		for(String cid: ttvProductCatalogIds){
			CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemByExternalId(cid);
			if(catalogueItemDO != null && catalogueItemDO.getCatalogueItemId() != null ){
				if(catalogueItemDO.getCatalogueItemId().equals(essentialsV1Internal)){
					ttvAssignedComponentId =  essentialsV1Internal;
				} else if(catalogueItemDO.getCatalogueItemId().equals(essentialsV2Internal)){
					ttvAssignedComponentId =  essentialsV2Internal;
				} else if(catalogueItemDO.getCatalogueItemId().equals(tvLiteInternal)){
					ttvAssignedComponentId =  tvLiteInternal;
				} else if(catalogueItemDO.getCatalogueItemId().equals(pikTVInternal)){
					ttvAssignedComponentId =  pikTVInternal;
				}
			}
		}

		return ttvAssignedComponentId;
	}

	public static List<String> retrieveAssignedAndPendingTTVComponents(SalesOfferCommonVO commonVO){
		List<SubscribedProductInfoRestVO> assignedProducts = null;
		List<SubscribedProductInfoRestVO> pendingProducts = null;

		if(commonVO.getAssignedAndPendingProductsResponseVO() != null){
			assignedProducts = commonVO.getAssignedProductListByServiceAddressAndServiceId();
			pendingProducts = commonVO.getPendingProductListByServiceAddress();
		}

		SubscribedProductInfoRestVO subscribedTTVProduct = null;
		if ((assignedProducts == null || assignedProducts.isEmpty()) && (pendingProducts == null || pendingProducts.isEmpty())) {
			return null;
		}
		else  {
			if (!assignedProducts.isEmpty()) {
				for (SubscribedProductInfoRestVO subscribedProduct : assignedProducts) {
					if (TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
						subscribedTTVProduct = subscribedProduct;
						return retrieveProductCatalogIdsForTTV(subscribedTTVProduct);
					}
				}
			}
			if (!pendingProducts.isEmpty() && subscribedTTVProduct==null) {
				for (SubscribedProductInfoRestVO subscribedProduct : pendingProducts) {
					if (TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
						subscribedTTVProduct = subscribedProduct;
						return retrieveProductCatalogIdsForTTV(subscribedTTVProduct);
					}
				}
			}

		}
		return null;
	}
	
	//NWLN-4541
	public static boolean isManualSweetener(Sweetener sweetener){
		if (sweetener.getOfferCategoryList() != null && 
				!sweetener.getOfferCategoryList().isEmpty()) {
			for(OfferCategory offerCategory : sweetener.getOfferCategoryList()){
				if(EnterpriseWLNSalesServicesConstants.OFFER_CATEGORY.equalsIgnoreCase(offerCategory.getCategoryTypeCode()) &&
						offerCategory.getCategoryList() != null &&
						!offerCategory.getCategoryList().isEmpty()){
					for(Category category : offerCategory.getCategoryList()){
						if(EnterpriseWLNSalesServicesConstants.MANUAL_SWEETENER.equalsIgnoreCase(category.getCategoryCode())){
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean isManualSweetener(SweetenerOfferSummary sweetenerOfferSummary){
		if (sweetenerOfferSummary.getOfferCategoryList() != null && 
				!sweetenerOfferSummary.getOfferCategoryList().isEmpty()) {
			for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferCategory offerCategory : sweetenerOfferSummary.getOfferCategoryList()){
				if(EnterpriseWLNSalesServicesConstants.OFFER_CATEGORY.equalsIgnoreCase(offerCategory.getCategoryTypeCode()) &&
						offerCategory.getCategoryList() != null &&
						!offerCategory.getCategoryList().isEmpty()){
					for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Category category : offerCategory.getCategoryList()){
						if(EnterpriseWLNSalesServicesConstants.MANUAL_SWEETENER.equalsIgnoreCase(category.getCategoryCode())){
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}

	// NWLN-11291 change method to support boost wifi 2.0
	public static boolean isAccessoryEquipment(WirelineEquipment equipment) {
		String serviceType = getServiceType(equipment);
		if (serviceType != null)
			return ACCESSORY_EQUIPMENT_LIST.contains(serviceType);
		else
			return false;
	}
	
	public static String getServiceType(WirelineEquipment equipment) {
		String catalogueId = null;
		
		try {
			catalogueId = equipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();
		} catch (NullPointerException e) {
			catalogueId = null;
			}
		
		if (catalogueId == null) {
			return null;
		}
		
		CatalogueItemDO catalogueItemDO = CommonWLNGridHelper.getInstance().getCatalogueItemById(catalogueId);
		
		if (catalogueItemDO == null) {
			return null;
		}
		String serviceType = catalogueItemDO.getComponentServiceType();
		return serviceType;
	}
	
	/*
	 * 2018 Aug release for "nochange"
	 */
	public static boolean isNoChangeOfferProduct(WirelineOfferProduct product){
		boolean noChangeOfferProduct = false;
		
		if(product != null && product.getTransactionTypeList() != null){
			for (TransactionType t : product.getTransactionTypeList()) {
				if (EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE.equalsIgnoreCase(t.getTransactionTypeCode())) {
					noChangeOfferProduct = true;
				}
			}
		}
		
		return noChangeOfferProduct;
	}
	
	/*
	 * split offers: Alejandro, August 2, 2018
	 */
	public static boolean isJoinedOffers(OperationHeader operationHeader) {
		boolean isJoindOffers = false;

		if (operationHeader!=null && operationHeader.getSystemIntegrationParameterList()!=null && !operationHeader.getSystemIntegrationParameterList().isEmpty()) {
			for (SystemIntegrationParameterList systemIntegrationParamList : operationHeader.getSystemIntegrationParameterList()) {
				if (!StringUtils.isBlank(systemIntegrationParamList.getParameterName()) && !StringUtils.isBlank(systemIntegrationParamList.getParameterValue())) {
					if (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER.equalsIgnoreCase(systemIntegrationParamList.getParameterName()) && 
						EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE.equalsIgnoreCase(systemIntegrationParamList.getParameterValue())) {
						isJoindOffers = true;

						break;
					}
				}
			}
		}

		return isJoindOffers;
	}
	
	public static String getSuppressCampaignOfferInd(OperationHeader operationHeader) {
		String supressCampaignIndValue = null;

		if (operationHeader!=null && operationHeader.getSystemIntegrationParameterList()!=null && !operationHeader.getSystemIntegrationParameterList().isEmpty()) {
			for (SystemIntegrationParameterList systemIntegrationParamList : operationHeader.getSystemIntegrationParameterList()) {
				if (!StringUtils.isBlank(systemIntegrationParamList.getParameterName()) && !StringUtils.isBlank(systemIntegrationParamList.getParameterValue())) {
					if (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_SUPRESS_CAMPAIGN_OFFER.equalsIgnoreCase(systemIntegrationParamList.getParameterName()) && 
						!StringUtils.isEmpty(systemIntegrationParamList.getParameterValue())) {
						supressCampaignIndValue = systemIntegrationParamList.getParameterValue();
						break;
					}
				}
			}
		}
		return supressCampaignIndValue;
	}
	
	public static boolean isBundleOffersRequested(WirelineOfferFilter offerFilter) {
		if(offerFilter!=null && offerFilter.isBundleInd()!=null && offerFilter.isBundleInd()) {
			return true;
		}else {
			return false;
		}
	}

	public static void setMergeOffersInd(SalesOfferCommonVO commonVO, WirelineTransactionalContext wirelineTransactionalContext) {
		if ( (commonVO.getOffersRequestVO().getOperationHeader() != null) &&
			 (commonVO.getOffersRequestVO().getOperationHeader().getSystemIntegrationParameterList() != null) &&
			 (!commonVO.getOffersRequestVO().getOperationHeader().getSystemIntegrationParameterList().isEmpty()) ) {
			for (OperationHeader.SystemIntegrationParameterList systemIntegrationParameter : commonVO.getOffersRequestVO().getOperationHeader().getSystemIntegrationParameterList()) {
				if ( (systemIntegrationParameter != null) &&
					 (systemIntegrationParameter.getParameterName() != null) &&
					 (!systemIntegrationParameter.getParameterName().isEmpty()) &&
					 (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER.equals(systemIntegrationParameter.getParameterName())) &&
					 (systemIntegrationParameter.getParameterValue() != null) &&
					 (!systemIntegrationParameter.getParameterName().isEmpty()) &&
					 (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE.equals(systemIntegrationParameter.getParameterValue())) ) {
					wirelineTransactionalContext.setMergeOffersInd(true);

					break;
				}
			}
		}

		if ( (wirelineTransactionalContext.isMergeOffersInd() == null) ) {
			wirelineTransactionalContext.setMergeOffersInd(false);
		}
	}
	
	public static String getHsicExternalProductCatalogueId(Offer offer) {
		String externalproductCatalogueId = null;

		for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()) {

			if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())) {
				for (ProductComponent productComponent : wirelineOfferProduct.getProductComponentList()) {
					for (ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()) {
						externalproductCatalogueId = productCatalogueItem.getProductCatalogueIdentifier()
								.getExternalProductCatalogId();

						break;
					}
				}
			}
		}
		return externalproductCatalogueId;
	}
	
	public static String getHsicSubscribedProductTierCdFromAssignedAndPendingProductsResponse(SalesOfferCommonVO salesOfferCommonVO) {

		for (SubscribedProductInfoRestVO subscribedProduct : salesOfferCommonVO.getAssignedProductListByServiceAddressAndServiceId()) {
			if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
				return subscribedProduct.getProductTierCd();
			}
		}
	
		for (SubscribedProductInfoRestVO subscribedProduct : salesOfferCommonVO.getPendingProductListByServiceAddress()) {
			if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
				return subscribedProduct.getProductTierCd();
			}
		}
	
		return "";

	}
	
	public static int calculateLowestRankingForPP(String hiSpeedPackCode,SalesOfferCommonVO salesOfferCommonVO) {
		
		int tomOfferPPRanking = 100000;

		if(salesOfferCommonVO
				.getProductQualificationAdapterResponseVO() != null && salesOfferCommonVO
				.getProductQualificationAdapterResponseVO().getProductQualificationList() != null) {

			List<ProductQualification> productQualificationList = salesOfferCommonVO
					.getProductQualificationAdapterResponseVO().getProductQualificationList();


			for (ProductQualification productQualification : productQualificationList) {
				for (Product product : productQualification.getProductList()) {
					if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(product.getProductTypeCd())
							&& hiSpeedPackCode.equalsIgnoreCase(product.getProductTierCd())) {
						if (product.getProductRanking() < tomOfferPPRanking) {
							tomOfferPPRanking = product.getProductRanking();
						}
					}
				}
			}

		}

		return tomOfferPPRanking;
	}
	
	public static String getCurrentHsicTerm(SalesOfferCommonVO salesOfferCommonVO) {
		String term=null;
		if(salesOfferCommonVO!=null && salesOfferCommonVO.getAssignedAndPendingProductsResponseVO()!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProductList = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			for (SubscribedProductInfoRestVO subscribedProduct : assignedProductList) {
				if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
					term = subscribedProduct.getProductInstance().get(0).getTermCd();
					break;
				}
			}
		}
		return term != null ? term : "MTM";
	}
	
	public static boolean banNotFoundError(GetAssignedAndPendingProductResponseVO assignedAndPendingProductsVO) {
		boolean isErrorCodePresent=false;
		if(assignedAndPendingProductsVO!=null && assignedAndPendingProductsVO.getMessageList()!=null && !assignedAndPendingProductsVO.getMessageList().isEmpty()){
			for(MessageList msgList :assignedAndPendingProductsVO.getMessageList()){
				if(msgList.getRelatedMessageList()!=null && !msgList.getRelatedMessageList().isEmpty()){
					for(RelatedMessage relatedMsg : msgList.getRelatedMessageList()){
						if(EnterpriseWLNSalesServicesErrorCodes.GAOL_ACCOUNT_NOT_FOUND_GCAP.equalsIgnoreCase(relatedMsg.getRelatedErrorCd())){
							isErrorCodePresent=true;
							break;
						}
					}
				}
			}
		}
		return isErrorCodePresent;
	}
	
	// NWLN-7598 - add new util method to identify any offer have more than 1 product, and there must be HS and TTV product with the same template Id
	public static boolean isValidMultipleTTVProductOffer(Offer offer) {
		boolean isValidMultipleProductOffer = false;
		String hsicExternalProductCatalogId = "";
		String ttvExternalProductCatalogId = "";
		
		if(offer.getOfferProduct().getWirelineOfferProductList() != null && offer.getOfferProduct().getWirelineOfferProductList().size()>1){
			for(WirelineOfferProduct product : offer.getOfferProduct().getWirelineOfferProductList()){
				if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(product.getProductTypeCode())){
					hsicExternalProductCatalogId = product.getProductTemplateIdentifier().getExternalProductCatalogId();
				}
				if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(product.getProductTypeCode())){
					ttvExternalProductCatalogId = product.getProductTemplateIdentifier().getExternalProductCatalogId();
				}
			}
			if(!StringUtils.isEmpty(hsicExternalProductCatalogId) && 
			   !StringUtils.isEmpty(ttvExternalProductCatalogId)  && 
			    hsicExternalProductCatalogId.equalsIgnoreCase(ttvExternalProductCatalogId)){
				isValidMultipleProductOffer = true;
			}
		}
		return isValidMultipleProductOffer;
	}
	
	// NWLN-7598 - method to find out is the customer have existing TTV product
	public static boolean isTTVExists(SalesOfferCommonVO commonVO){
		boolean existTtvInd = false;
		if(commonVO!=null){
			if(commonVO.getAssignedAndPendingProductsResponseVO()!=null){
				ServiceAddressVO requestServiceAddress = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress();
				List<SubscribedServiceIdentifierVO> requestSubscribedServiceIdList = commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier();
				List<SubscribedProductInfoRestVO> assignedProductList = commonVO.getAssignedAndPendingProductsResponseVO().getAssignedProductListByServiceAddressAndServiceId(requestServiceAddress, requestSubscribedServiceIdList);
				List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
				if(assignedProductList!=null && !assignedProductList.isEmpty()){
					for(SubscribedProductInfoRestVO assignedProduct : assignedProductList){
						if(assignedProduct!=null){
							String assignedProductType = assignedProduct.getProductTypeCd();
							if(TTV.equalsIgnoreCase(assignedProductType)){
								existTtvInd = true;
							}
						}
					}
				}else if(pendingProductList!=null && !pendingProductList.isEmpty()){
					for(SubscribedProductInfoRestVO pendingProduct : pendingProductList){
						if(pendingProduct!=null){
							String pendingProductType = pendingProduct.getProductTypeCd();
							if(TTV.equalsIgnoreCase(pendingProductType)){
								existTtvInd = true;
							}
						}
					}
				}
			}
		}
		return existTtvInd;
	}

	public static boolean isProductExists(String productTypeCd, SalesOfferCommonVO salesOfferCommonVO) {
		
		for (SubscribedProductInfoRestVO subscribedProduct : salesOfferCommonVO.getAssignedProductListByServiceAddressAndServiceId()) {
			if (subscribedProduct.getProductTypeCd().equalsIgnoreCase(productTypeCd)) {
				return true;
			}
		}
	
		for (SubscribedProductInfoRestVO subscribedProduct : salesOfferCommonVO.getPendingProductListByServiceAddress()) {
			if (subscribedProduct.getProductTypeCd().equalsIgnoreCase(productTypeCd)) {
				return true;
			}
		}
	
		return false;

	}

	
	// 2019-07-15
	public static PromotionCriteria getPromotionCriteria(WirelineOfferFilter wlnOfferFilter){
		
		ArrayList<Long> promotionIdList = new ArrayList<Long>(); 
		
		PromotionCriteria promotionCriteria=null;
		
		if(!CollectionUtils.isEmpty(wlnOfferFilter.getPromotionIdentifierList())) {
			
			 promotionCriteria= new PromotionCriteria();
			
			if (wlnOfferFilter.getPromotionIdentifierList().get(0).getPerspectiveDate()!=null) {
				promotionCriteria.setPerspectiveDate(wlnOfferFilter.getPromotionIdentifierList().get(0).getPerspectiveDate());	
			} else {
				promotionCriteria.setPerspectiveDate(new Date());
			}
			
			
			for (PromotionIdentifier promotionId : wlnOfferFilter.getPromotionIdentifierList() ) {				
				promotionIdList.add(Long.parseLong(promotionId.getPromotionId()));
		
			}
			
			if (!CollectionUtils.isEmpty(promotionIdList)) {
				promotionCriteria.setPromotionIdList(promotionIdList);
			}
			
			return promotionCriteria;
		}
		
		return promotionCriteria;
		
	}
}
