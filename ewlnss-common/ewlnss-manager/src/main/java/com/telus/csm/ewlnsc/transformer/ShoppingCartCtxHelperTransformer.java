package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.domain.AgentProfileVO;
import com.telus.csm.ewlnsc.domain.ApplicationProfileVO;
import com.telus.csm.ewlnsc.domain.BillingAccountVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetPromotionRequestVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.NoteVO;
import com.telus.csm.ewlnsc.domain.OperationHeaderVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductOrderTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.SalesRepAssociatedOutletVO;
import com.telus.csm.ewlnsc.domain.schemasclone.GetAvailableSweetenerOfferListRequestVO;
import com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.schemasclone.OfferProductHeader;
import com.telus.csm.ewlnsc.domain.schemasclone.ProductComponentIdentifier;
import com.telus.csm.ewlnsc.domain.schemasclone.ProductOrderType;
import com.telus.csm.ewlnsc.domain.schemasclone.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.schemasclone.ServiceAddressBase;
import com.telus.csm.ewlnsc.domain.schemasclone.SweetnerOfferFilterCriteria;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineOfferFilter;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineOfferHeaderWithOfferFilter;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineSalesSummary;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOfferFilter;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AgentProfile;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductDiscount;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineSalesOrderSummary;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.SalesRepAssociatedOutletType;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.AccessoryOfferCriteria;


public class ShoppingCartCtxHelperTransformer {

	public static GetAssignedAndPendingProductRequestVO transformConsolidatedAccRequest(ShoppingCartVO shoppingCartVO) {
		GetAssignedAndPendingProductRequestVO assignedAndPendingProductRequestVO = null;
		/**
		 * Inputs: customerId,billingAccountNumber
		 */
		if (shoppingCartVO != null) {
			assignedAndPendingProductRequestVO = new GetAssignedAndPendingProductRequestVO();

			if(shoppingCartVO.getBillingAccount()!=null && !StringUtils.isEmpty(shoppingCartVO.getBillingAccount().getBillingAccountNumber())){
				assignedAndPendingProductRequestVO.setBillingAccountNumber(shoppingCartVO.getBillingAccount().getBillingAccountNumber());
			}
			if(shoppingCartVO.getCustomer()!=null && !StringUtils.isEmpty(shoppingCartVO.getCustomer().getCustomerId())){
				assignedAndPendingProductRequestVO.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
			}
			
			assignedAndPendingProductRequestVO.setOperationHeader(buildOperationHeader(shoppingCartVO));
		}

		return assignedAndPendingProductRequestVO;
	}

	public static ServiceAddressRequestVO transformServiceAddressRequest(ShoppingCartVO shoppingCartVO) {
		ServiceAddressRequestVO serviceAddressRequestVO = null;

		if (shoppingCartVO != null ) {
			serviceAddressRequestVO = new ServiceAddressRequestVO();

			if(shoppingCartVO.getServiceAddress()!=null){
				if(!StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getServiceAddressId())){
					serviceAddressRequestVO.setAddressId(shoppingCartVO.getServiceAddress().getServiceAddressId());
				}
				if(!StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getProvinceCode())){
					serviceAddressRequestVO.setProvinceCode(shoppingCartVO.getServiceAddress().getProvinceCode());
				}
				if(shoppingCartVO.getOperationHeader()!=null){
					serviceAddressRequestVO.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
				}
			}
		}

		return serviceAddressRequestVO;
	}
	
	public static OperationHeader buildOperationHeader(ShoppingCartVO shoppingCartVO){
		OperationHeader operationHeader = null;

		if ( (shoppingCartVO != null) && (shoppingCartVO.getOperationHeader() != null) ) {
			operationHeader = new OperationHeader();

			operationHeader.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
			operationHeader.setBrandCode(EnterpriseWLNSalesServicesConstants.BRAND_CODE_TELUS);

			if(shoppingCartVO.getShoppingProfile().getUserProfile()!=null){
				operationHeader.setUserProfile(getUserProileFromVO(shoppingCartVO.getShoppingProfile().getUserProfile()));
			}
			if(shoppingCartVO.getShoppingProfile().getAgentProfile()!=null){
				operationHeader.setAgentProfile(getAgentProfileFromVO(shoppingCartVO.getShoppingProfile().getAgentProfile()));
			}

			operationHeader.setSalesTransactionTimestamp(new Date());			
		}
		
		if (shoppingCartVO.getNote() != null) {
			for (NoteVO x : shoppingCartVO.getNote()) {
				if (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER.equalsIgnoreCase(x.getType())
						&& EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE.equalsIgnoreCase(x.getText())) {
					com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList sip = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList();
					sip.setParameterName(EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER);
					sip.setParameterValue(EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE);
					operationHeader.getSystemIntegrationParameterList().add(sip);
				}
			}
		}
		
		ApplicationProfileVO applicationProfile = shoppingCartVO.getShoppingProfile().getApplicationProfile();
		if (applicationProfile != null && !StringUtils.isBlank(applicationProfile.getOriginatorApplicationId())) {
			try {
				operationHeader.setOriginatorApplicationId(Long.valueOf(applicationProfile.getOriginatorApplicationId()));
			} catch (NumberFormatException e) {
			}
		}

		return operationHeader;
	}

	private static AgentProfile getAgentProfileFromVO(AgentProfileVO agentProfileVO) {
		AgentProfile agentProfile = null;

		if (agentProfileVO != null) {
			agentProfile = new AgentProfile();

			if(!StringUtils.isBlank(agentProfileVO.getChannelOrganizationTypeCd())){
				agentProfile.setChannelOrganizationTypeCd(agentProfileVO.getChannelOrganizationTypeCd());
			}
			if(!StringUtils.isBlank(agentProfileVO.getChannelOrganizationInternalId())){
				agentProfile.setChannelOrganizationInternalId(agentProfileVO.getChannelOrganizationInternalId());
			}
			if(!StringUtils.isBlank(agentProfileVO.getChannelOrganizationNumber())){
				agentProfile.setChannelOrganizationNum(agentProfileVO.getChannelOrganizationNumber());
			}
			if(!StringUtils.isBlank(agentProfileVO.getEmployeeId())){
				agentProfile.setEmployeeId(agentProfileVO.getEmployeeId());
			}
			if(agentProfileVO.getUserPrivilegeRoleCodes()!=null && !agentProfileVO.getUserPrivilegeRoleCodes().isEmpty()){
				agentProfile.setUserPrivilegeRoleCodeList(agentProfileVO.getUserPrivilegeRoleCodes());
			}
		}

		return agentProfile;
	}

	private static ChannelPartnerUserProfileType getUserProileFromVO(UserProfileVO userProfileVO) {
		ChannelPartnerUserProfileType userProfile = null;

		if (userProfileVO != null) {
			userProfile = new ChannelPartnerUserProfileType();

			if(!StringUtils.isBlank(userProfileVO.getChannelOrganizationTypeCd())){
				userProfile.setChnlOrgTypeCode(userProfileVO.getChannelOrganizationTypeCd());
			}
			if(!StringUtils.isBlank(userProfileVO.getChannelOrganizationInternalId())){
				userProfile.setChnlOrgInternalId(Long.valueOf(userProfileVO.getChannelOrganizationInternalId()));
			}
			if(!StringUtils.isBlank(userProfileVO.getChannelOrganizationNumber())){
				userProfile.setChnlOrgNumber(userProfileVO.getChannelOrganizationNumber());
			}
			if(userProfileVO.getOutletAssociatedProvinces()!=null && !userProfileVO.getOutletAssociatedProvinces().isEmpty()){
				userProfile.setOutletAssociatedProvinces(userProfileVO.getOutletAssociatedProvinces());
			}
			if(!StringUtils.isBlank(userProfileVO.getLoginId())){
				userProfile.setLoginId(userProfileVO.getLoginId());
			}
			if(!StringUtils.isBlank(userProfileVO.getSalesRepId())){
				userProfile.setSalesRepId(userProfileVO.getSalesRepId());
			}
			if(!StringUtils.isBlank(userProfileVO.getSalesRepInternalId())){
				userProfile.setSalesRepInternalId(Long.valueOf(userProfileVO.getSalesRepInternalId()));
			}
			if(userProfileVO.getSalesRepAssociatedOutlet()!=null && !userProfileVO.getSalesRepAssociatedOutlet().isEmpty()){
				userProfile.setSalesRepAssociatedOutletList(getSalesRepAssociatedOutletFromVO(userProfileVO.getSalesRepAssociatedOutlet()));
			}
		}

		return userProfile;
	}

	private static List<SalesRepAssociatedOutletType> getSalesRepAssociatedOutletFromVO(List<SalesRepAssociatedOutletVO> salesRepAssociatedOutletVOList) {
		List<SalesRepAssociatedOutletType> salesRepAssociatedOutletList = null;

		if (salesRepAssociatedOutletVOList != null && !salesRepAssociatedOutletVOList.isEmpty()) {
			salesRepAssociatedOutletList = new ArrayList<SalesRepAssociatedOutletType>();

			for(SalesRepAssociatedOutletVO salesRepAssociatedVO : salesRepAssociatedOutletVOList){
				SalesRepAssociatedOutletType salesRepAssociatedOutlet = new SalesRepAssociatedOutletType();
				if(!StringUtils.isBlank(salesRepAssociatedVO.getChannelOutletId())){
					salesRepAssociatedOutlet.setSalesRepAssociatedChannelOutletId(salesRepAssociatedVO.getChannelOutletId());
				}
				if(!StringUtils.isBlank(salesRepAssociatedVO.getOutletInternalId())){
					salesRepAssociatedOutlet.setSalesRepAssociatedOutletInternalId(Long.valueOf(salesRepAssociatedVO.getOutletInternalId()));
				}
				salesRepAssociatedOutletList.add(salesRepAssociatedOutlet);
			}
		}

		return salesRepAssociatedOutletList;
	}

	public static GetProductQualificationAdapterRequest transformToProductQualificationRequest(ShoppingCartVO shoppingCartVO) {
		GetProductQualificationAdapterRequest adapterRequest = new GetProductQualificationAdapterRequest();

		if (shoppingCartVO.getOperationHeader() != null) {
			adapterRequest.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
			adapterRequest.setCorrelationId(shoppingCartVO.getShoppingCartId());
		}
		
		if(shoppingCartVO.getShoppingProfile()!=null && shoppingCartVO.getShoppingProfile().getUserProfile()!=null){
			UserProfileVO userProfile = shoppingCartVO.getShoppingProfile().getUserProfile();
			adapterRequest.setSalesRepId(userProfile.getSalesRepId());
			if(userProfile.getSalesRepAssociatedOutlet()!=null && !userProfile.getSalesRepAssociatedOutlet().isEmpty()){
				adapterRequest.setChannelOutletId(userProfile.getSalesRepAssociatedOutlet().get(0).getChannelOutletId());
			}
		}
		
		adapterRequest.setRefreshCache(false); //TODO: check this with Catherine
		
		if(shoppingCartVO.getServiceAddress()!=null){
			ServiceAddressVO serviceAddress = shoppingCartVO.getServiceAddress();
			adapterRequest.setAddressId(serviceAddress.getServiceAddressId());
			adapterRequest.setProvinceCd(serviceAddress.getProvinceCode());
			adapterRequest.setCity(serviceAddress.getCityCode());
		}
		
		adapterRequest.setQualByServiceId(false);

		return adapterRequest;
	}

	public static GetAvailableSweetenerOfferListRequestVO tranformShoppingCartVOToGetOffersRequestVO(ShoppingCartVO shoppingCartVO, boolean isPostTask) {
		GetAvailableSweetenerOfferListRequestVO availableSweetenerOfferListRequestVO = null;

		if (shoppingCartVO != null) {
			availableSweetenerOfferListRequestVO = new GetAvailableSweetenerOfferListRequestVO();

			if (shoppingCartVO.getOperationHeader() != null) {
				availableSweetenerOfferListRequestVO.setOperationHeader(buildSchemaIndependentOperationHeader(shoppingCartVO));
			}

			SweetnerOfferFilterCriteria sweetnerOfferFilterCriteria = new SweetnerOfferFilterCriteria();

			if (!shoppingCartVO.isWirelineProspectCustomer() || isPostTask || shoppingCartVO.isCustomerCreditCompleted()) {
				if ( (shoppingCartVO.getCustomer() != null) && (shoppingCartVO.getCustomer().getCustomerId() != null) && (!shoppingCartVO.getCustomer().getCustomerId().isEmpty()) ) {
					sweetnerOfferFilterCriteria.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
				}
	
				if ( (shoppingCartVO.getBillingAccount() != null) && (shoppingCartVO.getBillingAccount().getBillingAccountNumber() != null) &&
					 (!shoppingCartVO.getBillingAccount().getBillingAccountNumber().isEmpty())) {
					sweetnerOfferFilterCriteria.setBillingAccountNumber(shoppingCartVO.getBillingAccount().getBillingAccountNumber());
				}
			}

			if (shoppingCartVO.getServiceAddress() != null) {
				sweetnerOfferFilterCriteria.setServiceAddress(ShoppingCartCtxHelperTransformer.transformShoppingCartServiceAddressToServiceAddressBase(shoppingCartVO.getServiceAddress()));
			}
			
			/*

			if ( (shoppingCartVO.getCartItemList() != null) && (!shoppingCartVO.getCartItemList().isEmpty()) ) {
				List<WirelineSalesSummary> associatedWirelineSalesSummaryList = new ArrayList<WirelineSalesSummary>();

				for (CartItemVO cartItemVO : shoppingCartVO.getCartItemList()) {
					if (cartItemVO != null && cartItemVO.isSalesOrderIem()) {
						WirelineSalesSummary associatedWirelineSalesSummary = new WirelineSalesSummary();
						CartItemVO cartItemSalesOrderVO = (CartItemVO)cartItemVO;
						if ( (cartItemSalesOrderVO.getProductMarketOffering() != null) && (cartItemSalesOrderVO.getProductMarketOffering().getOfferHeader() != null) ) {
							associatedWirelineSalesSummary.setOfferHeader(transformCartItemProductMarketOfferingToOfferHeader(cartItemVO));
						}

						if  (cartItemSalesOrderVO.getProducts() != null)  {
							associatedWirelineSalesSummary.setProductList(transformCartItemProductsToOfferHeaderProductList(cartItemSalesOrderVO.getProducts()));
						}

						associatedWirelineSalesSummaryList.add(associatedWirelineSalesSummary);
					}
				}

				sweetnerOfferFilterCriteria.setAssociatedWirelineSalesSummaryList(associatedWirelineSalesSummaryList);
			}

			availableSweetenerOfferListRequestVO.setSweetenerOfferFilterCriteria(sweetnerOfferFilterCriteria);
			
			*/
			
			if ( shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty() ) {
				//WirelineSalesSummary per cart item.
				List<WirelineSalesSummary> wirelineSalesSummaryList = new ArrayList<WirelineSalesSummary>();
				
				for (CartItemVO cartItemVO : shoppingCartVO.getCartItemList()) {
					WirelineSalesSummary wirelineSalesSummary = new WirelineSalesSummary();
					//OfferHeader
					WirelineOfferHeaderWithOfferFilter wirelineOfferHeader = new WirelineOfferHeaderWithOfferFilter();
					
					if (cartItemVO.isSalesOrderItem()) {
					
					FFHOfferHeaderVO offerHeader = cartItemVO.getProductMarketOffering().getOfferHeader();
					if (offerHeader.getOfferId() != null) {
						wirelineOfferHeader.setOfferId(offerHeader.getOfferId().toString());
					}
					wirelineOfferHeader.setOfferCode(offerHeader.getOfferCode());
					
					if (offerHeader.getOfferProgramId() != null) {
						wirelineOfferHeader.setOfferProgramId(offerHeader.getOfferProgramId().toString());
					}

					if (offerHeader.getPerspectiveDate() != null) {
						wirelineOfferHeader.setPerspectiveDate(offerHeader.getPerspectiveDate());
					} else{
						wirelineOfferHeader.setPerspectiveDate(new Date());
					}
					
					if (offerHeader.getSystemId() != null) {
						wirelineOfferHeader.setSystemId(offerHeader.getSystemId().toString());
					}
					wirelineOfferHeader.setOfferFilter(buildOfferFilter(shoppingCartVO, cartItemVO));

					wirelineSalesSummary.setOfferHeader(wirelineOfferHeader);
					
					//ProductList
					InternetProductTypeVO internetProductType = cartItemVO.getProducts().getInternetProduct();
					if (internetProductType != null) {
						OfferProductHeader offerProductHeader = new OfferProductHeader();
						offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);
						//offerProductHeader.setProductOrderType(buildProductOrderType(internetProductType.getProductOrderType()));						
						offerProductHeader.setProductOrderType(buildProductOrderType(internetProductType.getProductOrderType()));
						offerProductHeader.setContractTermCd(internetProductType.getSelectedContractTermCd());
						for (ProductComponentVO productComponent : internetProductType.getProductComponents()) {
							ProductComponentIdentifier productComponentIdentifier = new ProductComponentIdentifier();
							productComponentIdentifier.setProductCatalogueIdentifier(productComponent.getProductCatalogueId());
							productComponentIdentifier.setParentProductCatalogueIdentifier(productComponent.getParentProductCatalogueId());
							offerProductHeader.getProductComponentList().add(productComponentIdentifier);
						}
						wirelineSalesSummary.getProductList().add(offerProductHeader);
					}	
					
					TelevisionProductTypeVO televisionProduct = cartItemVO.getProducts().getTelevisionProduct();
					if (televisionProduct != null) {
						OfferProductHeader offerProductHeader = new OfferProductHeader();
						offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);
						//offerProductHeader.setProductOrderType(buildProductOrderType(televisionProduct.getProductOrderType()));						
						offerProductHeader.setProductOrderType(buildProductOrderType(televisionProduct.getProductOrderType()));
						offerProductHeader.setContractTermCd(televisionProduct.getSelectedContractTermCd());
						for (ProductComponentVO productComponent : televisionProduct.getProductComponents()) {
							ProductComponentIdentifier productComponentIdentifier = new ProductComponentIdentifier();
							productComponentIdentifier.setProductCatalogueIdentifier(productComponent.getProductCatalogueId());
							productComponentIdentifier.setParentProductCatalogueIdentifier(productComponent.getParentProductCatalogueId());
							offerProductHeader.getProductComponentList().add(productComponentIdentifier);
						}
						wirelineSalesSummary.getProductList().add(offerProductHeader);
					}	
					
					HomePhoneProductTypeVO homePhoneProduct = cartItemVO.getProducts().getHomePhoneProduct();
					if (homePhoneProduct != null) {
						OfferProductHeader offerProductHeader = new OfferProductHeader();
						offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.SING);
						//offerProductHeader.setProductOrderType(buildProductOrderType(homePhoneProduct.getProductOrderType()));						
						offerProductHeader.setProductOrderType(buildProductOrderType(homePhoneProduct.getProductOrderType()));
						offerProductHeader.setContractTermCd(homePhoneProduct.getSelectedContractTermCd());
						for (ProductComponentVO productComponent : homePhoneProduct.getProductComponents()) {
							ProductComponentIdentifier productComponentIdentifier = new ProductComponentIdentifier();
							productComponentIdentifier.setProductCatalogueIdentifier(productComponent.getProductCatalogueId());
							productComponentIdentifier.setParentProductCatalogueIdentifier(productComponent.getParentProductCatalogueId());
							offerProductHeader.getProductComponentList().add(productComponentIdentifier);
						}
						wirelineSalesSummary.getProductList().add(offerProductHeader);
					}	
					
					wirelineSalesSummaryList.add(wirelineSalesSummary);
					}
				}
				sweetnerOfferFilterCriteria.setAssociatedWirelineSalesSummaryList(wirelineSalesSummaryList);
				availableSweetenerOfferListRequestVO.setSweetenerOfferFilterCriteria(sweetnerOfferFilterCriteria);
			}
		}
		
		return availableSweetenerOfferListRequestVO;
	}
	
	private static ProductOrderType buildProductOrderTypeForInternetSweetener(InternetProductTypeVO internetProductType) {
		ProductOrderType productOrderType = new ProductOrderType();
		if(!CollectionUtils.isEmpty(internetProductType.getSweeteners())){
			for(FFHSweetenerTypeVO sweetener : internetProductType.getSweeteners()){
				if(!CollectionUtils.isEmpty(sweetener.getDiscounts())){
					FFHDiscountTypeVO ffhDiscountTypeVO = sweetener.getDiscounts().get(0);
					if(!CollectionUtils.isEmpty(ffhDiscountTypeVO.getProductCatalogueIdentifiers())){
						for(ProductComponentVO productComponentVO : ffhDiscountTypeVO.getProductCatalogueIdentifiers()){
							if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(productComponentVO.getAction())){
								productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.ACTIVATION.toLowerCase());								
							}
							if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_NO_CHANGE.equalsIgnoreCase(productComponentVO.getAction())){
								productOrderType.setProductOrderTypeCd(productComponentVO.getAction());
							}
							if(internetProductType.getProductOrderType()!=null){
								productOrderType.setProductOrderSubTypeCd(internetProductType.getProductOrderType().getOrderSubTypeCd());
							}
							
							break;
						}
					}
				}
			}
		}
		return productOrderType;
	}
	
	private static ProductOrderType buildProductOrderTypeForHomePhoneSweetener(HomePhoneProductTypeVO homePhoneProductTypeVO) {
		ProductOrderType productOrderType = new ProductOrderType();
		if(!CollectionUtils.isEmpty(homePhoneProductTypeVO.getSweeteners())){
			for(FFHSweetenerTypeVO sweetener : homePhoneProductTypeVO.getSweeteners()){
				if(!CollectionUtils.isEmpty(sweetener.getDiscounts())){
					FFHDiscountTypeVO ffhDiscountTypeVO = sweetener.getDiscounts().get(0);
					if(!CollectionUtils.isEmpty(ffhDiscountTypeVO.getProductCatalogueIdentifiers())){
						for(ProductComponentVO productComponentVO : ffhDiscountTypeVO.getProductCatalogueIdentifiers()){
							if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(productComponentVO.getAction())){
								productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.ACTIVATION.toLowerCase());								
							}
							if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_NO_CHANGE.equalsIgnoreCase(productComponentVO.getAction())){
								productOrderType.setProductOrderTypeCd(productComponentVO.getAction());
							}
							if(homePhoneProductTypeVO.getProductOrderType()!=null){
								productOrderType.setProductOrderSubTypeCd(homePhoneProductTypeVO.getProductOrderType().getOrderSubTypeCd());
							}
							
							break;
						}
					}
				}
			}
		}
		return productOrderType;
	}
	
	private static ProductOrderType buildProductOrderTypeForTelevisionSweetener(TelevisionProductTypeVO televisionProductTypeVO) {
		ProductOrderType productOrderType = new ProductOrderType();
		if(!CollectionUtils.isEmpty(televisionProductTypeVO.getSweeteners())){
			for(FFHSweetenerTypeVO sweetener : televisionProductTypeVO.getSweeteners()){
				if(!CollectionUtils.isEmpty(sweetener.getDiscounts())){
					FFHDiscountTypeVO ffhDiscountTypeVO = sweetener.getDiscounts().get(0);
					if(!CollectionUtils.isEmpty(ffhDiscountTypeVO.getProductCatalogueIdentifiers())){
						for(ProductComponentVO productComponentVO : ffhDiscountTypeVO.getProductCatalogueIdentifiers()){
							if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(productComponentVO.getAction())){
								productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.ACTIVATION.toLowerCase());								
							}
							if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_NO_CHANGE.equalsIgnoreCase(productComponentVO.getAction())){
								productOrderType.setProductOrderTypeCd(productComponentVO.getAction());
							}
							if(televisionProductTypeVO.getProductOrderType()!=null){
								productOrderType.setProductOrderSubTypeCd(televisionProductTypeVO.getProductOrderType().getOrderSubTypeCd());
							}
							
							break;
						}
					}
				}
			}
		}
		return productOrderType;
	}

	private static ProductOrderType buildProductOrderType(ProductOrderTypeVO productOrderType) {
		if (productOrderType == null) {
			return null;
		}
		ProductOrderType result = new ProductOrderType();
		if(EnterpriseWLNOrderUtil.isRecontractingProduct(productOrderType)){ //Alejandro, Jan 8, 2018, for recontracting scenarios, productOrderType must contains 'nochange' instead of NO_CHANGE
			result.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE);	
		}else{
			result.setProductOrderTypeCd(productOrderType.getOrderTypeCd());
		}
		
		result.setProductOrderSubTypeCd(productOrderType.getOrderSubTypeCd());
		return result;
		
	}

	public static ServiceAddressBase transformShoppingCartServiceAddressToServiceAddressBase(ServiceAddressVO serviceAddressVO) {
		ServiceAddressBase serviceAddressBase = null;

		if (serviceAddressVO != null) {
			serviceAddressBase = new ServiceAddressBase();

			if (!StringUtils.isEmpty(serviceAddressVO.getServiceAddressId())) {
				serviceAddressBase.setServiceAddressId(serviceAddressVO.getServiceAddressId());
			}

			if (!StringUtils.isEmpty(serviceAddressVO.getProvinceCode())) {
				serviceAddressBase.setProvinceCode(serviceAddressVO.getProvinceCode());
			}

			if (!StringUtils.isEmpty(serviceAddressVO.getCityCode())) {
				serviceAddressBase.setCityCode(serviceAddressVO.getCityCode());
			}
		}

		return serviceAddressBase;
	}

	public static List<OfferProductHeader> transformCartItemProductsToOfferHeaderProductList(ProductTypeVO productTypeVO) {
		List<OfferProductHeader> productList = new ArrayList<OfferProductHeader>();

		if (productTypeVO.getHomePhoneProduct() != null) {
			OfferProductHeader offerProductHeader = new OfferProductHeader();
			offerProductHeader.setContractTermCd(productTypeVO.getHomePhoneProduct().getSelectedContractTermCd());
			offerProductHeader.setProductOrderType(transformCartItemProductOrderTypeToOfferHeaderProductOrderType(productTypeVO.getHomePhoneProduct().getProductOrderType()));
			offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.SING);
			offerProductHeader.setProductComponentList(transformCartItemProductComponentsToProductComponentIdentifierList(productTypeVO.getHomePhoneProduct().getProductComponents()));
			productList.add(offerProductHeader);
		}

		if (productTypeVO.getInternetProduct() != null) {
			OfferProductHeader offerProductHeader = new OfferProductHeader();
			offerProductHeader.setContractTermCd(productTypeVO.getInternetProduct().getSelectedContractTermCd());
			offerProductHeader.setProductOrderType(transformCartItemProductOrderTypeToOfferHeaderProductOrderType(productTypeVO.getInternetProduct().getProductOrderType()));
			offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);
			offerProductHeader.setProductComponentList(transformCartItemProductComponentsToProductComponentIdentifierList(productTypeVO.getInternetProduct().getProductComponents()));
			productList.add(offerProductHeader);
		}

		if (productTypeVO.getTelevisionProduct() != null) {
			OfferProductHeader offerProductHeader = new OfferProductHeader();
			offerProductHeader.setContractTermCd(productTypeVO.getTelevisionProduct().getSelectedContractTermCd());
			offerProductHeader.setProductOrderType(transformCartItemProductOrderTypeToOfferHeaderProductOrderType(productTypeVO.getTelevisionProduct().getProductOrderType()));
			offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);
			offerProductHeader.setProductComponentList(transformCartItemProductComponentsToProductComponentIdentifierList(productTypeVO.getTelevisionProduct().getProductComponents()));
			productList.add(offerProductHeader);
		}

		return productList;
	}

	public static WirelineOfferHeaderWithOfferFilter transformCartItemProductMarketOfferingToOfferHeader(CartItemVO cartItemVO) {
		WirelineOfferHeaderWithOfferFilter offerHeader = null;

		if ( (cartItemVO != null) && (cartItemVO.getProductMarketOffering() != null) && (cartItemVO.getProductMarketOffering().getOfferHeader() != null) ) {
			offerHeader = new WirelineOfferHeaderWithOfferFilter();
			offerHeader.setOfferCode(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferCode());
			offerHeader.setOfferId(String.valueOf(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId()));
			offerHeader.setOfferProgramId(String.valueOf(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferProgramId()));
			offerHeader.setPerspectiveDate(cartItemVO.getProductMarketOffering().getOfferHeader().getPerspectiveDate());
			offerHeader.setSystemId(String.valueOf(cartItemVO.getProductMarketOffering().getOfferHeader().getSystemId()));
		}

		return offerHeader;
	}

	public static List<ProductComponentIdentifier> transformCartItemProductComponentsToProductComponentIdentifierList(List<ProductComponentVO> productComponentVOList) {
		List<ProductComponentIdentifier> productComponentList = null;

		if ( (productComponentVOList != null) && (!productComponentVOList.isEmpty()) ) {
			productComponentList = new ArrayList<ProductComponentIdentifier>();

			for (ProductComponentVO productComponentVO : productComponentVOList) {
				ProductComponentIdentifier productComponentIdentifier = new ProductComponentIdentifier();
				productComponentIdentifier.setParentProductCatalogueIdentifier(productComponentVO.getParentProductCatalogueId());
				productComponentIdentifier.setProductCatalogueIdentifier(productComponentVO.getProductCatalogueId());
				productComponentList.add(productComponentIdentifier);
			}			
		}

		return productComponentList;
	}

	public static ProductOrderType transformCartItemProductOrderTypeToOfferHeaderProductOrderType(ProductOrderTypeVO productOrderTypeVO) {
		ProductOrderType productOrderType = null;

		if (productOrderTypeVO != null) {
			productOrderType = new ProductOrderType();
			productOrderType.setProductOrderTypeCd(productOrderTypeVO.getOrderTypeCd());
			productOrderType.setProductOrderSubTypeCd(productOrderTypeVO.getOrderSubTypeCd());
		}

		return productOrderType;
	}

	public static GetSalesOfferDetailRequestVO tranformShoppingCartVOToGetSalesOfferDetailRequestVO(ShoppingCartVO shoppingCartVO, CartItemVO cartItemVO, boolean isPostTask) {
		GetSalesOfferDetailRequestVO salesOfferDetailRequestVO = null;

		if (shoppingCartVO != null) {
			salesOfferDetailRequestVO = new GetSalesOfferDetailRequestVO();

			if (shoppingCartVO.getOperationHeader() != null) {
				salesOfferDetailRequestVO.setOperationHeader(buildSchemaIndependentOperationHeader(shoppingCartVO));
			}

			SalesOfferCriteriaVO salesOfferCriteria = new SalesOfferCriteriaVO();

			if (!shoppingCartVO.isWirelineProspectCustomer() || isPostTask || shoppingCartVO.isCustomerCreditCompleted()) {
				if ( (shoppingCartVO.getCustomer() != null) && (shoppingCartVO.getCustomer().getCustomerId() != null) && (!shoppingCartVO.getCustomer().getCustomerId().isEmpty()) ) {
					salesOfferCriteria.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
				}
	
				if ( (shoppingCartVO.getBillingAccount() != null) && (shoppingCartVO.getBillingAccount().getBillingAccountNumber() != null) &&
					 (!shoppingCartVO.getBillingAccount().getBillingAccountNumber().isEmpty())) {
					salesOfferCriteria.setBillingAccountNumber(shoppingCartVO.getBillingAccount().getBillingAccountNumber());
				}
			}

			if (shoppingCartVO.getServiceAddress() != null) {
				salesOfferCriteria.setServiceAddress(transformShoppingCartServiceAddressToServiceAddressVO(shoppingCartVO.getServiceAddress()));
			}

			if (cartItemVO != null) {
				if ( (cartItemVO.getProductMarketOffering() != null) && (cartItemVO.getProductMarketOffering().getOfferHeader() != null) ) {
					FFHOfferHeaderVO offerHeader = cartItemVO.getProductMarketOffering().getOfferHeader();

					if (offerHeader.getOfferId() != null) {
						salesOfferDetailRequestVO.setOfferId(offerHeader.getOfferId().toString());
					}

					if (offerHeader.getOfferCode() != null) {
						salesOfferDetailRequestVO.setOfferCode(offerHeader.getOfferCode());
					}

					if (offerHeader.getOfferProgramId() != null) {
						salesOfferDetailRequestVO.setOfferProgramId(offerHeader.getOfferProgramId().toString());
					}

					if (offerHeader.getOfferCode() != null) {
						salesOfferDetailRequestVO.setPerspectiveDate(offerHeader.getPerspectiveDate());
					}

					if (offerHeader.getSystemId() != null) {
						salesOfferDetailRequestVO.setSystemID(offerHeader.getSystemId().toString());
					}
				}

				salesOfferCriteria.setOfferFilter(buildOfferFilter(shoppingCartVO, cartItemVO));
//				salesOfferCriteria.setPromotionCd(promotionCd);
				salesOfferCriteria.setSubscribedServiceIdentifier(buildSubscribedServiceIdentifierVOList(cartItemVO));
			}

			salesOfferDetailRequestVO.setSalesOfferCriteria(salesOfferCriteria);			
		}
		
		return salesOfferDetailRequestVO;
	}

	public static com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader buildSchemaIndependentOperationHeader(ShoppingCartVO shoppingCartVO){
		com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader operationHeader = null;

		if (shoppingCartVO != null) {
			operationHeader = new com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader();

			if (shoppingCartVO.getOperationHeader() != null) {
				operationHeader.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
			}

			operationHeader.setBrandCode(EnterpriseWLNSalesServicesConstants.BRAND_CODE_TELUS);

			if ( (shoppingCartVO.getShoppingProfile() != null) && (shoppingCartVO.getShoppingProfile().getUserProfile() != null) ) {
				operationHeader.setUserProfile(getUserProileFromVO(shoppingCartVO.getShoppingProfile().getUserProfile()));
			}

			if ( (shoppingCartVO.getShoppingProfile() != null) && (shoppingCartVO.getShoppingProfile().getAgentProfile() != null) ) {
				operationHeader.setAgentProfile(getSchemaIndependentAgentProfileFromVO(shoppingCartVO.getShoppingProfile().getAgentProfile()));
			}

			operationHeader.setSalesTransactionTimestamp(new Date());
		}
		
		if (shoppingCartVO.getNote() != null) {
			for (NoteVO x : shoppingCartVO.getNote()) {
				if (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER.equalsIgnoreCase(x.getType())
						&& EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE.equalsIgnoreCase(x.getText())) {
					com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader.SystemIntegrationParameterList sip = new com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader.SystemIntegrationParameterList();
					sip.setParameterName(EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_JOIN_SALES_OFFER);
					sip.setParameterValue(EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_VALUE_TRUE);
					operationHeader.getSystemIntegrationParameterList().add(sip);
				}
			}
		}
		
		ApplicationProfileVO applicationProfile = shoppingCartVO.getShoppingProfile().getApplicationProfile();
		if (applicationProfile != null && !StringUtils.isBlank(applicationProfile.getOriginatorApplicationId())) {
			try {
				operationHeader.setOriginatorApplicationId(Long.valueOf(applicationProfile.getOriginatorApplicationId()));
			} catch (NumberFormatException e) {
			}
		}

		return operationHeader;
	}

	public static com.telus.csm.ewlnsc.domain.schemasclone.AgentProfile getSchemaIndependentAgentProfileFromVO(AgentProfileVO agentProfileVO) {
		com.telus.csm.ewlnsc.domain.schemasclone.AgentProfile agentProfile = null;

		if (agentProfileVO != null) {
			agentProfile = new com.telus.csm.ewlnsc.domain.schemasclone.AgentProfile();

			if(!StringUtils.isBlank(agentProfileVO.getChannelOrganizationTypeCd())){
				agentProfile.setChannelOrganizationTypeCd(agentProfileVO.getChannelOrganizationTypeCd());
			}
			if(!StringUtils.isBlank(agentProfileVO.getChannelOrganizationInternalId())){
				agentProfile.setChannelOrganizationInternalId(agentProfileVO.getChannelOrganizationInternalId());
			}
			if(!StringUtils.isBlank(agentProfileVO.getChannelOrganizationNumber())){
				agentProfile.setChannelOrganizationNum(agentProfileVO.getChannelOrganizationNumber());
			}
			if(!StringUtils.isBlank(agentProfileVO.getEmployeeId())){
				agentProfile.setEmployeeId(agentProfileVO.getEmployeeId());
			}
			if(agentProfileVO.getUserPrivilegeRoleCodes()!=null && !agentProfileVO.getUserPrivilegeRoleCodes().isEmpty()){
				agentProfile.setUserPrivilegeRoleCodeList(agentProfileVO.getUserPrivilegeRoleCodes());
			}
		}

		return agentProfile;
	}

	public static ServiceAddressVO transformShoppingCartServiceAddressToServiceAddressVO (com.telus.csm.ewlnsc.domain.ServiceAddressVO schemaServiceAddressVO) {
		ServiceAddressVO serviceAddressVO = null;

		if (schemaServiceAddressVO != null) {
			serviceAddressVO = new ServiceAddressVO();

			if ( (schemaServiceAddressVO.getCityCode() != null) && (!schemaServiceAddressVO.getCityCode().isEmpty()) ) {
				serviceAddressVO.setCityCode(schemaServiceAddressVO.getCityCode());
			}

			if ( (schemaServiceAddressVO.getProvinceCode() != null) && (!schemaServiceAddressVO.getProvinceCode().isEmpty()) ) {
				serviceAddressVO.setProvinceCode(schemaServiceAddressVO.getProvinceCode());
			}

			if ( (schemaServiceAddressVO.getServiceAddressId() != null) && (!schemaServiceAddressVO.getServiceAddressId().isEmpty()) ) {
				serviceAddressVO.setServiceAddressId(schemaServiceAddressVO.getServiceAddressId());
			}
		}
		
		return serviceAddressVO;
	}

	public static WirelineOfferFilter buildOfferFilter(ShoppingCartVO shoppingCartVO, CartItemVO cartItem) {
		WirelineOfferFilter result = null;

		if ((shoppingCartVO != null) && (shoppingCartVO.getBillingAccount() != null)) {
			result = new WirelineOfferFilter();
			BillingAccountVO ac = shoppingCartVO.getBillingAccount();

			if (ac != null) {
				result.setAccountTypeCode(ac.getAccountTypeCd());
				result.setAccountSubTypeCode(ac.getAccountSubTypeCd());
			}

			if (cartItem.getProducts() == null) {
				return result;
			}
			if (cartItem.getProducts().getInternetProduct() != null) {
				OfferProductHeader offerProductHeader = new OfferProductHeader();
				offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);
				offerProductHeader.setContractTermCd(cartItem.getProducts().getInternetProduct().getSelectedContractTermCd());
				offerProductHeader.setWinBackInd(cartItem.getProducts().getInternetProduct().getWinback());
				offerProductHeader.setProductOrderType(buildProductOrderType(cartItem.getProducts().getInternetProduct().getProductOrderType()));
				buildProductComponents(offerProductHeader.getProductComponentList(), cartItem.getProducts().getInternetProduct().getProductComponents());
				result.getProductList().add(offerProductHeader);

			}

			if (cartItem.getProducts().getTelevisionProduct() != null) {
				OfferProductHeader offerProductHeader = new OfferProductHeader();
				offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);
				offerProductHeader.setContractTermCd(cartItem.getProducts().getTelevisionProduct().getSelectedContractTermCd());
				offerProductHeader.setWinBackInd(cartItem.getProducts().getTelevisionProduct().getWinback());
				offerProductHeader.setProductOrderType(buildProductOrderType(cartItem.getProducts().getTelevisionProduct().getProductOrderType()));
				buildProductComponents(offerProductHeader.getProductComponentList(), cartItem.getProducts().getTelevisionProduct().getProductComponents());
				result.getProductList().add(offerProductHeader);
			}

			if (cartItem.getProducts().getHomePhoneProduct() != null) {
				OfferProductHeader offerProductHeader = new OfferProductHeader();
				offerProductHeader.setProductTypeCd(EnterpriseWLNSalesServicesConstants.SING);
				offerProductHeader.setContractTermCd(cartItem.getProducts().getHomePhoneProduct().getSelectedContractTermCd());
				offerProductHeader.setWinBackInd(cartItem.getProducts().getHomePhoneProduct().getWinback());
				offerProductHeader.setProductOrderType(buildProductOrderType(cartItem.getProducts().getHomePhoneProduct().getProductOrderType()));
				buildProductComponents(offerProductHeader.getProductComponentList(), cartItem.getProducts().getHomePhoneProduct().getProductComponents());
				result.getProductList().add(offerProductHeader);
			}

			result.setBundleInd(Boolean.FALSE);
		}

		return result;
	}
	
	private static ProductOrderType buildProductOrderTypeForInternet(InternetProductTypeVO internetProduct) {
		ProductOrderType productOrderType = new ProductOrderType();
		if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(internetProduct.getProductOrderType().getOrderTypeCd())) {
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION);
		}else {
			if(noChangeComponents(internetProduct.getProductComponents())) {
				productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE);
			}else {
				productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE);
			}
		}
		return productOrderType;
	}

	//TODO:: For now, base the logic og no change based on product component action as no change.
	//TODO:: Check with the team if we need to use logic in liek for like re-contracting here?
	private static boolean noChangeComponents(List<ProductComponentVO> productComponents) {
		if(productComponents == null) {
			return true;
		}
		for (ProductComponentVO productComponentVO : productComponents) {
			if(productComponentVO.getAction() != null) {
				if(EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(productComponentVO.getAction())){
					return false;
				}
			}else {
				//Null action not defaulted to no change.
				return false;
			}
		}
		return true;
	}

	private static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType buildProductOrderTypeForInternetAccessory(InternetProductTypeVO internetProduct) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType productOrderType = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType();
		if(EnterpriseWLNCommonTransformer.internetIsNewProduct(internetProduct)){
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION);
		}else{
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE);
		}
		return productOrderType;
	}
	
	private static ProductOrderType buildProductOrderTypeForTelevision(TelevisionProductTypeVO televisionProduct) {
		ProductOrderType productOrderType = new ProductOrderType();
		if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(televisionProduct.getProductOrderType().getOrderTypeCd())) {
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION);
		}else {
			if(noChangeComponents(televisionProduct.getProductComponents())) {
				productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE);
			}else {
				productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE);
			}
		}
		return productOrderType;
	}
	
	private static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType buildProductOrderTypeForTelevisionAccessory(TelevisionProductTypeVO televisionProduct) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType productOrderType = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType();
		if(EnterpriseWLNCommonTransformer.televisionIsNewProduct(televisionProduct)){
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION);
		}else{
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE);
		}
		return productOrderType;
	}
	
	private static ProductOrderType buildProductOrderTypeForHomePhone(HomePhoneProductTypeVO homePhoneProduct) {
		ProductOrderType productOrderType = new ProductOrderType();
		if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(homePhoneProduct.getProductOrderType().getOrderTypeCd())) {
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION);
		}else {
			if(noChangeComponents(homePhoneProduct.getProductComponents())) {
				productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE);
			}else {
				productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE);
			}
		}
		return productOrderType;
	}
	
	private static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType buildProductOrderTypeForHomePhoneAccessory(HomePhoneProductTypeVO homePhoneProduct) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType productOrderType = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType();
		if(EnterpriseWLNCommonTransformer.homePhoneIsNewProduct(homePhoneProduct)){
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION);
		}else{
			productOrderType.setProductOrderTypeCd(EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE);
		}
		return productOrderType;
	}
	
	private static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType buildProductOrderTypeForAccessory(ProductOrderTypeVO productOrderTypeVO) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType productOrderType = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType();
		if(productOrderTypeVO!=null && !StringUtils.isBlank(productOrderTypeVO.getOrderTypeCd())){
			productOrderType.setProductOrderTypeCd(productOrderTypeVO.getOrderTypeCd());
		}
		return productOrderType;
	}
	
	private static void buildProductComponents(List<ProductComponentIdentifier> productComponentList,
			List<ProductComponentVO> productComponents) {
		
		for (ProductComponentVO x : productComponents) {
			ProductComponentIdentifier productComponentIdentifier = new ProductComponentIdentifier();
			productComponentIdentifier.setParentProductCatalogueIdentifier(x.getParentProductCatalogueId());
			productComponentIdentifier.setProductCatalogueIdentifier(x.getProductCatalogueId());
			productComponentList.add(productComponentIdentifier);
		}
	}

	public static List<SubscribedServiceIdentifierVO> buildSubscribedServiceIdentifierVOList(CartItemVO cartItem) {
		List<SubscribedServiceIdentifierVO> subscribedServiceIdentifierVOList = null;

		if ( (cartItem != null) && (cartItem.getExistingServiceIdentifier() != null) && (!cartItem.getExistingServiceIdentifier().isEmpty()) ) {
			subscribedServiceIdentifierVOList = new ArrayList<SubscribedServiceIdentifierVO>();

			for (SubscribedServiceVO subscribedServiceVO : cartItem.getExistingServiceIdentifier()) {
				SubscribedServiceIdentifierVO subscribedServiceIdentifierVO = new SubscribedServiceIdentifierVO();
				subscribedServiceIdentifierVO.setServiceId(subscribedServiceVO.getServiceId());
				subscribedServiceIdentifierVO.setServiceReferenceId(subscribedServiceVO.getServiceReferenceId());
				subscribedServiceIdentifierVOList.add(subscribedServiceIdentifierVO);				
			}
		}

		return subscribedServiceIdentifierVOList;
	}

	public static GetBillingAccountAdapterRequest transformGetBillingAccount(ShoppingCartVO shoppingCartVO) {
		GetBillingAccountAdapterRequest req = new GetBillingAccountAdapterRequest();
		if (shoppingCartVO.getBillingAccount()!=null) {
			
			if (!StringUtils.isBlank(shoppingCartVO.getBillingAccount().getBillingAccountNumber())) {
				req.setBillingAccountNumber(shoppingCartVO.getBillingAccount().getBillingAccountNumber());
			}
			if ( !StringUtils.isBlank(shoppingCartVO.getBillingAccount().getAccountMasterSourceTypeCd())){
				req.setBillingSystemId(Integer.parseInt(shoppingCartVO.getBillingAccount().getAccountMasterSourceTypeCd()));
			}
		}
		if(shoppingCartVO.getOperationHeader()!=null){
			req.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		}
		return req;
	}

	public static GetOffersRequestVO transformAvailableAccessoryRequest(ShoppingCartVO shoppingCartVO, boolean isPostTask) {
		GetOffersRequestVO offersRequestVO = new GetOffersRequestVO();
		AccessoryOfferCriteria accessoryOfferCriteria = new AccessoryOfferCriteria();
		offersRequestVO.setAccessoryOfferCriteria(accessoryOfferCriteria);
		offersRequestVO.setOperationHeader(buildOperationHeader(shoppingCartVO));

			if ((!shoppingCartVO.isWirelineProspectCustomer() || isPostTask || shoppingCartVO.isCustomerCreditCompleted()) && (shoppingCartVO.getCustomer() != null) && (shoppingCartVO.getCustomer().getCustomerId() != null) && (!shoppingCartVO.getCustomer().getCustomerId().isEmpty()) ) {
				accessoryOfferCriteria.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
			}
			
			if (shoppingCartVO.getBillingAccount() != null) {
				if ((!shoppingCartVO.isWirelineProspectCustomer() || isPostTask || shoppingCartVO.isCustomerCreditCompleted()) && (shoppingCartVO.getBillingAccount().getBillingAccountNumber() != null) && (!shoppingCartVO.getBillingAccount().getBillingAccountNumber().isEmpty()) ) {
					accessoryOfferCriteria.setBillingAccountNumber(shoppingCartVO.getBillingAccount().getBillingAccountNumber());
				}
	
				if ( (shoppingCartVO.getBillingAccount().getAccountTypeCd() != null) && (!shoppingCartVO.getBillingAccount().getAccountTypeCd().isEmpty()) ) {
					AccessoryOfferFilter accessoryOfferFilter = new AccessoryOfferFilter();
					accessoryOfferFilter.setAccountTypeCode(shoppingCartVO.getBillingAccount().getAccountTypeCd());
					accessoryOfferFilter.setAccountSubTypeCode(shoppingCartVO.getBillingAccount().getAccountSubTypeCd());
					accessoryOfferCriteria.setOfferFilter(accessoryOfferFilter);
				}
			}

		if (shoppingCartVO.getServiceAddress() != null) {
			com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase serviceAddressBase = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase();

			if (!StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getServiceAddressId())) {
				serviceAddressBase.setServiceAddressId(shoppingCartVO.getServiceAddress().getServiceAddressId());
			}

			if (!StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getProvinceCode())) {
				serviceAddressBase.setProvinceCode(shoppingCartVO.getServiceAddress().getProvinceCode());
			}

			if (!StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getCityCode())) {
				serviceAddressBase.setCityCode(shoppingCartVO.getServiceAddress().getCityCode());
			}

			accessoryOfferCriteria.setServiceAddress(serviceAddressBase);
		}

		if ( (shoppingCartVO.getCartItemList() != null) && (!shoppingCartVO.getCartItemList().isEmpty()) ) {
			List<WirelineSalesOrderSummary> associatedWirelineSalesSummaryList = new ArrayList<WirelineSalesOrderSummary>();
			List<ServiceIdentifier> serviceIdentifierList = new ArrayList<ServiceIdentifier>();
			for (CartItemVO cartItemVO : shoppingCartVO.getCartItemList()) {
				if ( (cartItemVO.getProductMarketOffering() != null) && (cartItemVO.getProductMarketOffering().getOfferHeader() != null) ) {
					WirelineSalesOrderSummary wirelineSalesOrderSummary = new WirelineSalesOrderSummary();
					WirelineOfferHeader wirelineOfferHeader = new WirelineOfferHeader();
					wirelineSalesOrderSummary.setSalesOffer(wirelineOfferHeader);
					wirelineOfferHeader.setOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());
					wirelineOfferHeader.setOfferCode(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferCode());
					wirelineOfferHeader.setOfferProgramId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferProgramId());
					wirelineOfferHeader.setSystemId(cartItemVO.getProductMarketOffering().getOfferHeader().getSystemId());
					wirelineOfferHeader.setPerspectiveDate(cartItemVO.getProductMarketOffering().getOfferHeader().getPerspectiveDate());

					if (cartItemVO.getProducts() != null) {
						wirelineSalesOrderSummary.setProductList(transformCartItemProductsToWirelineProductDiscountListSchemaDependent(cartItemVO.getProducts()));
					}

					associatedWirelineSalesSummaryList.add(wirelineSalesOrderSummary);
				}
				
				//November 16th, 2018 - adding subscribedServiceIdentifier to the accessoryOfferCriteria
				getSubscribedServiceIdentifierFromVO(cartItemVO,serviceIdentifierList);
			}
			accessoryOfferCriteria.setSubscribedServiceIdentifierList(serviceIdentifierList);
			accessoryOfferCriteria.setAssociatedWirelineSalesSummaryList(associatedWirelineSalesSummaryList);
		}

		return offersRequestVO;
	}

	public static GetPromotionRequestVO transformAvailablePromotionsRequest(final ShoppingCartVO shoppingCartVO, final boolean couponInd) {
		final GetPromotionRequestVO result = new GetPromotionRequestVO();
		
		result.setCustomer(shoppingCartVO.getCustomer());
		result.setBillingAccount(shoppingCartVO.getBillingAccount());
		result.setServiceAddress(shoppingCartVO.getServiceAddress());
		result.setCartItemList(shoppingCartVO.getCartItemList());
		result.setRelatedParty(shoppingCartVO.getRelatedParty());
		result.setOperationHeaderVO(shoppingCartVO.getOperationHeader());
		result.setCouponInd(couponInd);		

		return result;
	}
	
	private static void getSubscribedServiceIdentifierFromVO(CartItemVO cartItem,List<ServiceIdentifier> serviceIdentifierList) {		
		if ( (cartItem != null) && cartItem.isGwpOrderItem() && CollectionUtils.isNotEmpty(cartItem.getExistingServiceIdentifier())) {
			for (SubscribedServiceVO subscribedServiceVO : cartItem.getExistingServiceIdentifier()) {
				if(!subscribedProductExists(subscribedServiceVO,serviceIdentifierList)){
					ServiceIdentifier serviceIdentifier = new ServiceIdentifier();
					serviceIdentifier.setServiceId(subscribedServiceVO.getServiceId());
					serviceIdentifier.setServiceReferenceId(subscribedServiceVO.getServiceReferenceId());
					serviceIdentifierList.add(serviceIdentifier);
				}
				
			}
		}		
	}

	private static boolean subscribedProductExists(SubscribedServiceVO subscribedServiceVO,List<ServiceIdentifier> serviceIdentifierList) {
		if(CollectionUtils.isNotEmpty(serviceIdentifierList)){
			for(ServiceIdentifier serviceIdentifier : serviceIdentifierList){
				if(subscribedServiceVO.getServiceId().equals(serviceIdentifier.getServiceId())){
					return true;
				}
			}
		}
		return false;
	}

	public static List<WirelineProductDiscount> transformCartItemProductsToWirelineProductDiscountListSchemaDependent(ProductTypeVO productTypeVO) {
		List<WirelineProductDiscount> productList = new ArrayList<WirelineProductDiscount>();

		if (productTypeVO.getHomePhoneProduct() != null) {
			WirelineProductDiscount wirelineProductDiscount = new WirelineProductDiscount();
			wirelineProductDiscount.setContractTermCd(productTypeVO.getHomePhoneProduct().getSelectedContractTermCd());
			//wirelineProductDiscount.setProductOrderType(transformCartItemProductOrderTypeToOfferHeaderProductOrderTypeSchemaDependent(productTypeVO.getHomePhoneProduct().getProductOrderType()));
			wirelineProductDiscount.setProductOrderType(buildProductOrderTypeForAccessory(productTypeVO.getHomePhoneProduct().getProductOrderType()));
			wirelineProductDiscount.setProductTypeCd(EnterpriseWLNSalesServicesConstants.SING);
			wirelineProductDiscount.setProductComponentList(transformCartItemProductComponentsToProductComponentIdentifierListSchemaDependent(productTypeVO.getHomePhoneProduct().getProductComponents()));
			productList.add(wirelineProductDiscount);
		}

		if (productTypeVO.getInternetProduct() != null) {
			WirelineProductDiscount wirelineProductDiscount = new WirelineProductDiscount();
			wirelineProductDiscount.setContractTermCd(productTypeVO.getInternetProduct().getSelectedContractTermCd());
			//wirelineProductDiscount.setProductOrderType(transformCartItemProductOrderTypeToOfferHeaderProductOrderTypeSchemaDependent(productTypeVO.getInternetProduct().getProductOrderType()));
			wirelineProductDiscount.setProductOrderType(buildProductOrderTypeForAccessory(productTypeVO.getInternetProduct().getProductOrderType()));
			wirelineProductDiscount.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);			
			wirelineProductDiscount.setProductComponentList(transformCartItemProductComponentsToProductComponentIdentifierListSchemaDependent(productTypeVO.getInternetProduct().getProductComponents()));
			productList.add(wirelineProductDiscount);
		}

		if (productTypeVO.getTelevisionProduct() != null) {
			WirelineProductDiscount wirelineProductDiscount = new WirelineProductDiscount();
			wirelineProductDiscount.setContractTermCd(productTypeVO.getTelevisionProduct().getSelectedContractTermCd());
			//wirelineProductDiscount.setProductOrderType(transformCartItemProductOrderTypeToOfferHeaderProductOrderTypeSchemaDependent(productTypeVO.getTelevisionProduct().getProductOrderType()));
			wirelineProductDiscount.setProductOrderType(buildProductOrderTypeForAccessory(productTypeVO.getTelevisionProduct().getProductOrderType()));
			wirelineProductDiscount.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);
			wirelineProductDiscount.setProductComponentList(transformCartItemProductComponentsToProductComponentIdentifierListSchemaDependent(productTypeVO.getTelevisionProduct().getProductComponents()));
			productList.add(wirelineProductDiscount);
		}

		return productList;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType transformCartItemProductOrderTypeToOfferHeaderProductOrderTypeSchemaDependent(ProductOrderTypeVO productOrderTypeVO) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType productOrderType = null;

		if (productOrderTypeVO != null) {
			productOrderType = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType();
			productOrderType.setProductOrderTypeCd(productOrderTypeVO.getOrderTypeCd());
			productOrderType.setProductOrderSubTypeCd(productOrderTypeVO.getOrderSubTypeCd());
		}

		return productOrderType;
	}

	public static List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier> transformCartItemProductComponentsToProductComponentIdentifierListSchemaDependent(List<ProductComponentVO> productComponentVOList) {
		List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier> productComponentList = null;

		if ( (productComponentVOList != null) && (!productComponentVOList.isEmpty()) ) {
			productComponentList = new ArrayList<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier>();

			for (ProductComponentVO productComponentVO : productComponentVOList) {
				com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier productComponentIdentifier = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier();
				productComponentIdentifier.setParentProductCatalogueIdentifier(productComponentVO.getParentProductCatalogueId());
				productComponentIdentifier.setProductCatalogueIdentifier(productComponentVO.getProductCatalogueId());
				productComponentList.add(productComponentIdentifier);
			}			
		}

		return productComponentList;
	}

	public static GetCreditProfileByCustomerIdAdapterRequest transformToGetCreditProfileByCustomerIdAdapterRequest(ShoppingCartVO shoppingCartVO) {
		GetCreditProfileByCustomerIdAdapterRequest request = new GetCreditProfileByCustomerIdAdapterRequest();
		request.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
		
		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo auditInfo = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo();
		auditInfo.setCorrelationId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		if(shoppingCartVO.getShoppingProfile() != null && shoppingCartVO.getShoppingProfile().getApplicationProfile() != null) {
			auditInfo.setOriginatorApplicationId(shoppingCartVO.getShoppingProfile().getApplicationProfile().getOriginatorApplicationId());
		}
		if(shoppingCartVO.getShoppingProfile() != null && shoppingCartVO.getShoppingProfile().getUserProfile() != null) {
			auditInfo.setUserId(String.valueOf(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepInternalId()));
			auditInfo.setSalesRepresentativeId(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepInternalId());
			auditInfo.setChannelOrganizationId(shoppingCartVO.getShoppingProfile().getUserProfile().getChannelOrganizationInternalId());
			if(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepAssociatedOutlet() != null && !shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepAssociatedOutlet().isEmpty()) {
				auditInfo.setOutletId(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepAssociatedOutlet().get(0).getChannelOutletId());
			}
		}
		auditInfo.setTimestamp(new Date());
		request.setAuditInfo(auditInfo);
		return request;
	}

	public static GetCreditEligibilityAdapterRequest transformToGetCreditEligibilityAdapterRequest(ShoppingCartVO shoppingCartVO) {
		GetCreditEligibilityAdapterRequest request = new GetCreditEligibilityAdapterRequest();
		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo auditInfo = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo();
		auditInfo.setCorrelationId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		if(shoppingCartVO.getShoppingProfile() != null && shoppingCartVO.getShoppingProfile().getApplicationProfile() != null) {
			auditInfo.setOriginatorApplicationId(shoppingCartVO.getShoppingProfile().getApplicationProfile().getOriginatorApplicationId());
		}
		if(shoppingCartVO.getShoppingProfile() != null && shoppingCartVO.getShoppingProfile().getUserProfile() != null) {
			auditInfo.setUserId(String.valueOf(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepInternalId()));
			auditInfo.setSalesRepresentativeId(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepInternalId());
			auditInfo.setChannelOrganizationId(shoppingCartVO.getShoppingProfile().getUserProfile().getChannelOrganizationInternalId());
			if(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepAssociatedOutlet() != null && !shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepAssociatedOutlet().isEmpty()) {
				auditInfo.setOutletId(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepAssociatedOutlet().get(0).getChannelOutletId());
			}
		}
		auditInfo.setTimestamp(new Date());
		request.setAuditInfo(auditInfo);
		request.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
		request.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		return request;
	}
}