package com.telus.csm.ewlnsc.transformer;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByPromotionCodeForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetSweetenerOfferListForCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.domain.AssociatedWirelessSalesSummaryVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.PhoneNumberVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.AccessoryOfferCriteria;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SalesAvailableOfferFilterCriteria;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelessSalesOrderSummary;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelineSalesSummary;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.Customer;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.TransactionalProduct;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOfferHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EnterpriseSalesOfferSummary;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RecontractEligibleProduct;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SweetenerOfferSummary;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelOrgSummaryInfo;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactAddress;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactDetails;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferProductSummary;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProductSummary;

public class GetAvailableOfferSummaryTransformer extends BaseOfferTransformer {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableOfferSummaryTransformer.class);
	
	private GetAvailableOfferSummaryTransformer(){
		
	}

	public static GetOffersRequestVO transform(GetAvailableOfferSummaryListType parameters) {
		GetOffersRequestVO requestVO = new GetOffersRequestVO();
		requestVO.setOperationHeader(parameters.getOperationHeader());
		requestVO.setAssociatedWirelessSummary(getAssociatedWlsSummary(parameters.getAccessoryOfferCriteria()));

		if (parameters.getSalesOfferCriteria() != null) {
			requestVO.setSalesOfferCriteria(getSalesOfferCriteria(parameters.getSalesOfferCriteria()));
		}

		if (parameters.getSweetenerOfferCriteria() != null) {
			requestVO.setSweetenerOfferFilterCriteria(parameters.getSweetenerOfferCriteria());
		}

		if (parameters.getAccessoryOfferCriteria() != null) {
			requestVO.setAccessoryOfferCriteria(parameters.getAccessoryOfferCriteria());
		}

		return requestVO;
	}

	private static SalesOfferCriteriaVO getSalesOfferCriteria(SalesAvailableOfferFilterCriteria salesOfferCriteria) {
		SalesOfferCriteriaVO salesOfferCriteriaVO = null;

		if (salesOfferCriteria != null) {
			salesOfferCriteriaVO = new SalesOfferCriteriaVO();
			salesOfferCriteriaVO.setBillingAccountNumber(salesOfferCriteria.getBillingAccountNumber());
			salesOfferCriteriaVO.setCustomerId(salesOfferCriteria.getCustomerId());

			if (salesOfferCriteria.getServiceAddress() != null) {
				salesOfferCriteriaVO.setServiceAddress(getServiceAddress(salesOfferCriteria.getServiceAddress()));	
			}

			salesOfferCriteriaVO.setOfferFilter(salesOfferCriteria.getOfferFilter());
			salesOfferCriteriaVO.setPromotionCd(salesOfferCriteria.getPromotionCd());
			salesOfferCriteriaVO.setSubscribedServiceIdentifier(getServiceIdentifier(salesOfferCriteria.getSubscribedServiceIdentifierList()));
		}

		return salesOfferCriteriaVO;
	}

	public static List<SubscribedServiceIdentifierVO> getServiceIdentifier(List<ServiceIdentifier> serviceIdentifierList) {
		List<SubscribedServiceIdentifierVO> serviceIdentifierVOList = new ArrayList<SubscribedServiceIdentifierVO>();
		if(!serviceIdentifierList.isEmpty()){
			for(ServiceIdentifier serviceIdentifier : serviceIdentifierList){
				SubscribedServiceIdentifierVO serviceIdentifierVO = new SubscribedServiceIdentifierVO();
				serviceIdentifierVO.setServiceId(serviceIdentifier.getServiceId());
				serviceIdentifierVO.setServiceReferenceId(serviceIdentifier.getServiceReferenceId());
				serviceIdentifierVOList.add(serviceIdentifierVO);
			}
			
		}
		return serviceIdentifierVOList;
	}

	public static ServiceAddressVO getServiceAddress(ServiceAddressBase serviceAddress) {
		ServiceAddressVO serviceAddressVO = new ServiceAddressVO();
		serviceAddressVO.setServiceAddressId(serviceAddress.getServiceAddressId());
		serviceAddressVO.setProvinceCode(serviceAddress.getProvinceCode());
		serviceAddressVO.setCityCode(serviceAddress.getCityCode());
		return serviceAddressVO;
	}

	private static AssociatedWirelessSalesSummaryVO getAssociatedWlsSummary(AccessoryOfferCriteria accessoryOfferCriteria) {
		AssociatedWirelessSalesSummaryVO associatedWlsSalesSummaryVO = null;

		if ( (accessoryOfferCriteria != null) && (accessoryOfferCriteria.getAssociatedWirelessSalesSummary() != null) ) {
			WirelessSalesOrderSummary associatedWirelessSalesSummary = accessoryOfferCriteria.getAssociatedWirelessSalesSummary();

			associatedWlsSalesSummaryVO = new AssociatedWirelessSalesSummaryVO();
			associatedWlsSalesSummaryVO.setBillingAccountNumber(associatedWirelessSalesSummary.getBillingAccountNumber());
			PhoneNumberVO phoneNumberVO = new PhoneNumberVO();
			phoneNumberVO.setTelephoneNumber(associatedWirelessSalesSummary.getPhoneNumber());
			phoneNumberVO.setTelephoneNumberType("WLN");
			associatedWlsSalesSummaryVO.setPhoneNumber(phoneNumberVO);
			associatedWlsSalesSummaryVO.setOfferHeader(associatedWirelessSalesSummary.getOfferHeader());
			associatedWlsSalesSummaryVO.setHandsetProductCd(associatedWirelessSalesSummary.getHandsetProductCd());
		}

		return associatedWlsSalesSummaryVO;
	}

	public static GetAssignedAndPendingProductRequestVO transformConsolidatedAccRequest(GetOffersRequestVO requestVO) {
		GetAssignedAndPendingProductRequestVO result = new GetAssignedAndPendingProductRequestVO();
		
		result.setOperationHeader(requestVO.getOperationHeader());

		if (requestVO.getSalesOfferCriteria() != null) {
			result.setCustomerId(requestVO.getSalesOfferCriteria().getCustomerId());
			result.setBillingAccountNumber(requestVO.getSalesOfferCriteria().getBillingAccountNumber());
		}

		if (requestVO.getAccessoryOfferCriteria() != null) {
			result.setCustomerId(requestVO.getAccessoryOfferCriteria().getCustomerId());
			result.setBillingAccountNumber(requestVO.getAccessoryOfferCriteria().getBillingAccountNumber());
		}
		
		return result;
	}

	public static GetCreditProfileByCustomerIdAdapterRequest transformCreditProfileByCustId(
			GetOffersRequestVO requestVO) {
		GetCreditProfileByCustomerIdAdapterRequest adapterRequest = new GetCreditProfileByCustomerIdAdapterRequest();
		adapterRequest.setAuditInfo(getAuditInfoForCreditProfileByCustId(requestVO));

		if (requestVO.getSalesOfferCriteria() != null) {
			adapterRequest.setCustomerId(requestVO.getSalesOfferCriteria().getCustomerId());
		}
		else if (requestVO.getAccessoryOfferCriteria() != null) {
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
				auditInfo.setUserId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setSalesRepresentativeId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setChannelOrganizationId(String.valueOf(request.getOperationHeader().getUserProfile().getChnlOrgInternalId()));
				if (request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList() != null 
						&& !request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
						&& request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() > 0){
					auditInfo.setOutletId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
				}
			}
		}
		auditInfo.setTimestamp(new Date());
		return auditInfo;
	}
	
	private static com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo getAuditInfoGetCreditElig(
			GetOffersRequestVO request) {
		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo auditInfo = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo();
		if (request.getOperationHeader() != null) {
			auditInfo.setOriginatorApplicationId(String.valueOf(request.getOperationHeader().getOriginatorApplicationId()));
			auditInfo.setCorrelationId(request.getOperationHeader().getSalesTransactionId());
			if (request.getOperationHeader().getUserProfile() != null) {
				auditInfo.setUserId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setSalesRepresentativeId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setChannelOrganizationId(String.valueOf(request.getOperationHeader().getUserProfile().getChnlOrgInternalId()));
				if (request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList() != null 
						&& !request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
						&& request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() > 0){
					auditInfo.setOutletId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
				}
			}
		}
		auditInfo.setTimestamp(new Date());
		return auditInfo;
	}

	public static GetCreditEligibilityAdapterRequest transformCreditEligibilityRequest(GetOffersRequestVO requestVO) {
		GetCreditEligibilityAdapterRequest adapterRequest = new GetCreditEligibilityAdapterRequest();
		adapterRequest.setAuditInfo(getAuditInfoGetCreditElig(requestVO));
		adapterRequest.setCustomerId(requestVO.getSalesOfferCriteria().getCustomerId());
		adapterRequest.setSalesTransactionId(requestVO.getOperationHeader().getSalesTransactionId());
		return adapterRequest;
	}

	public static GetOfferListForCustomerAdapterRequest transformOfferListForCustomer(
			SalesOfferCommonVO commonVO, boolean callForRecontractEligibleInd, String productCatalogId) {
		GetOfferListForCustomerAdapterRequest adapterRequest = new GetOfferListForCustomerAdapterRequest();
		adapterRequest.setCustomer(getCustomer(commonVO)); 
		adapterRequest.setOutlet(transform(commonVO));
		adapterRequest.setWirelineTransactionalContext(WLNOfferUtil.getWirelineTransactionalContext(commonVO,callForRecontractEligibleInd,productCatalogId));
		adapterRequest.setSalesTransactionId(commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId());
		return adapterRequest;
	}

	private static Customer getCustomer(SalesOfferCommonVO commonVO) {
		Customer customer = new Customer();

		if (commonVO.getOffersRequestVO().getSalesOfferCriteria()!=null) {
			if (!StringUtils.isBlank(commonVO.getOffersRequestVO().getSalesOfferCriteria().getCustomerId())) {
				customer.setCustomerId(Long.valueOf(commonVO.getOffersRequestVO().getSalesOfferCriteria().getCustomerId()));
			}
			if (commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress()!=null) {
				customer.setCustomerBillingProvinceCd(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress().getProvinceCode());
				customer.setCustomerBillingCityCd(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress().getCityCode());
			}
			if (commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter()!=null) {
				if(!StringUtils.isBlank(commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().getAccountTypeCode())){
					customer.setAccountTypeCode(commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().getAccountTypeCode());
				}
			}
			customer.setForborneInd(commonVO.getServiceAddressResponseVO().isExchangeForborneStatusInd());
		}
		else if (commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria()!=null) {
			if (!StringUtils.isBlank(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getCustomerId())) {
				customer.setCustomerId(Long.valueOf(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getCustomerId()));
			}
			if (commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress()!=null) {
				customer.setCustomerBillingProvinceCd(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress().getProvinceCode());
				customer.setCustomerBillingCityCd(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress().getCityCode());
			}
			if (commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria()!=null) {
				if (!StringUtils.isBlank(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList().get(0).getOfferHeader().getOfferFilter().getAccountTypeCode())) { //TODO: this shall be inside a List??
					customer.setAccountTypeCode(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList().get(0).getOfferHeader().getOfferFilter().getAccountTypeCode()); 
				}
			}
		}

		customer.setBrandId(Long.valueOf(EnterpriseSalesServiceUtil.getBrandId(commonVO.getOffersRequestVO().getOperationHeader().getBrandCode())));

		return customer;
	}
	
	
	private static OutletInfo transform (SalesOfferCommonVO commonVO) {
		OutletInfo result = new OutletInfo();

		if (commonVO != null && commonVO.getOffersRequestVO() != null && commonVO.getOffersRequestVO().getOperationHeader() != null) {
			OperationHeader operationHeader = commonVO.getOffersRequestVO().getOperationHeader();
			ChannelOrgSummaryInfo channelOrgSummaryInfo = new ChannelOrgSummaryInfo();
			channelOrgSummaryInfo.setChannelOrgType(operationHeader.getUserProfile().getChnlOrgTypeCode());
			channelOrgSummaryInfo.setInternalChannelOrgId(operationHeader.getUserProfile().getChnlOrgInternalId());
			channelOrgSummaryInfo.setChannelOrgCode(operationHeader.getUserProfile().getChnlOrgNumber());

			try {
				if (operationHeader.getUserProfile()!=null && !operationHeader.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()){
					result.setInternalOutletId(operationHeader.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId());
				}
			}
			catch (Exception e) {
				logger.error("", "transform Outlet", e.getMessage(), e);
			}

			String province = null;

			try {
				province = operationHeader.getUserProfile().getOutletAssociatedProvinces().get(0);
			}
			catch (Exception e) {
				logger.error("", "transform Outlet", e.getMessage(), e);
			}

			String cityCode=null;
			String serviceAddressId=null;

			if (commonVO.getOffersRequestVO().getSalesOfferCriteria()!=null && commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress()!=null){
				cityCode = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress().getCityCode();
				serviceAddressId = commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress().getServiceAddressId();
			}
			else if(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria()!=null && commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress()!=null){
				cityCode =commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress().getCityCode();
				serviceAddressId = commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getServiceAddress().getServiceAddressId();
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

	
	private static List<EnterpriseSalesOfferSummary> getSalesOfferSummaryList(SalesOfferCommonVO commonVO) {
		List<EnterpriseSalesOfferSummary> enterpriseSalesOfferSummaryList = new ArrayList<EnterpriseSalesOfferSummary>();

		List<Offer> offerList = commonVO.getOfferListAdapterResponse().getOfferList();

		if (offerList != null && !offerList.isEmpty()) {
			for (Offer offer : offerList) {
				EnterpriseSalesOfferSummary enterpriseSalesOfferSummary = new EnterpriseSalesOfferSummary();
				enterpriseSalesOfferSummary.setBasePriceAmt(offer.getBasePriceAmt());
				enterpriseSalesOfferSummary.setBrandId(offer.getBrandId());
				enterpriseSalesOfferSummary.setEffectiveDate(offer.getEffectiveDate());
				enterpriseSalesOfferSummary.setExpiryDate(offer.getExpiryDate());
				enterpriseSalesOfferSummary.setOfferCategoryList(offer.getOfferCategoryList());
				enterpriseSalesOfferSummary.setOfferDescriptionList(offer.getOfferDescriptionList());
				enterpriseSalesOfferSummary.setOfferId(offer.getOfferId());
				enterpriseSalesOfferSummary.setOfferProductSummary(getOfferProductSummary(offer));
				enterpriseSalesOfferSummary.setOfferTierId(offer.getOfferTierId());
				enterpriseSalesOfferSummary.setPaymentSupportTypeCode(offer.getPaymentSupportTypeCode());
				enterpriseSalesOfferSummary.setPerspectiveDate(offer.getPerspectiveDate());
				enterpriseSalesOfferSummary.setProgramCode(offer.getProgramCode());
				enterpriseSalesOfferSummary.setProgramDescriptionList(offer.getProgramDescriptionList());
				enterpriseSalesOfferSummary.setProgramId(offer.getProgramId());
				enterpriseSalesOfferSummary.setPromotionCode(offer.getPromotionCode());
				enterpriseSalesOfferSummary.setRankingNum(offer.getRankingNum());
				enterpriseSalesOfferSummary.setSalesItemDescriptionList(null); //From where is this going to be populated?
				enterpriseSalesOfferSummary.setTypeCode(offer.getTypeCode());
				enterpriseSalesOfferSummary.setWirelessOfferCode(offer.getWirelessOfferCode());
				enterpriseSalesOfferSummary.setProductEligiblityList(offer.getProductEligiblityList());
				enterpriseSalesOfferSummary.setOfferReferenceId(offer.getOfferReferenceId());
				enterpriseSalesOfferSummary.setAssignedOfferInd(offer.isAssignedOfferInd());
				enterpriseSalesOfferSummary.setOfferMarketingMessageList(offer.getOfferMarketingMessageList());
				enterpriseSalesOfferSummary.setSourceSystemId(offer.getSourceSystemId());
			    // 2018 June Exception release for TTV recontracting
				if(commonVO.getAssignedAndPendingProductsResponseVO() != null){
					enterpriseSalesOfferSummary.setRecontractEligibleProductList(getRecontractEligibleProductList(offer,commonVO));	
				}
				
				enterpriseSalesOfferSummaryList.add(enterpriseSalesOfferSummary);
			}
		}

		return enterpriseSalesOfferSummaryList;
	}

	private static List<SweetenerOfferSummary> getSweetenerOfferSummaryList(SalesOfferCommonVO commonVO) {
		List<SweetenerOfferSummary> sweetenerOfferSummaryList = new ArrayList<SweetenerOfferSummary>();

		List<Sweetener> sweetenerList = commonVO.getOfferListAdapterResponse().getSweetenerList();

		if (sweetenerList != null && !sweetenerList.isEmpty()) {
			for (Sweetener sweetener : sweetenerList) {
				SweetenerOfferSummary sweetenerOfferSummary = new SweetenerOfferSummary();
				sweetenerOfferSummary.setBasePriceAmt(sweetener.getBasePriceAmt());
				sweetenerOfferSummary.setBrandId(sweetener.getBrandId());
				sweetenerOfferSummary.setEffectiveDate(sweetener.getEffectiveDate());
				sweetenerOfferSummary.setExpiryDate(sweetener.getExpiryDate());
				sweetenerOfferSummary.setOfferCategoryList(sweetener.getOfferCategoryList());
				sweetenerOfferSummary.setOfferDescriptionList(sweetener.getOfferDescriptionList());
				sweetenerOfferSummary.setOfferId(sweetener.getOfferId());
				sweetenerOfferSummary.setOfferProductSummary(getOfferProductSummary(sweetener));
				sweetenerOfferSummary.setOfferTierId(sweetener.getOfferTierId());
				sweetenerOfferSummary.setPaymentSupportTypeCode(sweetener.getPaymentSupportTypeCode());
				sweetenerOfferSummary.setPerspectiveDate(sweetener.getPerspectiveDate());
				sweetenerOfferSummary.setProgramCode(sweetener.getProgramCode());
				sweetenerOfferSummary.setProgramDescriptionList(sweetener.getProgramDescriptionList());
				sweetenerOfferSummary.setProgramId(sweetener.getProgramId());
				sweetenerOfferSummary.setPromotionCode(sweetener.getPromotionCode());
				sweetenerOfferSummary.setRankingNum(sweetener.getRankingNum());
				sweetenerOfferSummary.setStackableInd(sweetener.isStackableInd());
				sweetenerOfferSummary.setTypeCode(sweetener.getTypeCode());
				sweetenerOfferSummary.setWirelessOfferCode(sweetener.getWirelessOfferCode());
				sweetenerOfferSummary.setProductEligiblityList(sweetener.getProductEligiblityList());
				sweetenerOfferSummary.setOfferReferenceId(sweetener.getOfferReferenceId());
				sweetenerOfferSummary.setAssignedOfferInd(sweetener.isAssignedOfferInd());
				sweetenerOfferSummary.setOfferMarketingMessageList(sweetener.getOfferMarketingMessageList());
				sweetenerOfferSummaryList.add(sweetenerOfferSummary);
			}
		}

		return sweetenerOfferSummaryList;
	}

	private static List<RecontractEligibleProduct> getRecontractEligibleProductList(Offer offer, SalesOfferCommonVO commonVO) {
		List<RecontractEligibleProduct> recontractEligibleProductList = new ArrayList<RecontractEligibleProduct>();
		
		// 2018 June Exception release for TTV recontracting
		// change parameters to call WLNOfferUtil.getRecontractEligibleProductCodeList
		List<String> recontractEligibleProductCodeList = WLNOfferUtil.getRecontractEligibleProductCodeList(offer, commonVO, false);
		for (String productCd : recontractEligibleProductCodeList) {
			RecontractEligibleProduct recontractEligibleProduct = new RecontractEligibleProduct();
			recontractEligibleProduct.setProductTypeCd(productCd);
			recontractEligibleProductList.add(recontractEligibleProduct);		
		}
		return recontractEligibleProductList;
	}

	private static OfferProductSummary getOfferProductSummary(Offer offer) {
		OfferProductSummary offerProductSummary = new OfferProductSummary();
		if(offer.getOfferProduct()!=null){
			offerProductSummary.setWirelessOfferProductSummary(offer.getOfferProduct().getWirelessOfferProduct());
			offerProductSummary.setWirelineOfferProductSummaryList(getWirelineOfferProductList(offer.getOfferProduct().getWirelineOfferProductList()));
			offerProductSummary.setAccessoryOfferProductSummary(offer.getOfferProduct().getAccessoryOfferProduct());
		}
		return offerProductSummary;
	}
	
	private static List<WirelineOfferProductSummary> getWirelineOfferProductList(List<WirelineOfferProduct> wirelineOfferProductList) {
		List<WirelineOfferProductSummary> wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();
		if(wirelineOfferProductList!=null && !wirelineOfferProductList.isEmpty()){
			for(WirelineOfferProduct wirelineOfferProduct : wirelineOfferProductList){
				WirelineOfferProductSummary wirelineOfferProductSummary = new WirelineOfferProductSummary();
				wirelineOfferProductSummary.setProductTypeCode(wirelineOfferProduct.getProductTypeCode());
				wirelineOfferProductSummary.setTransactionTypeList(wirelineOfferProduct.getTransactionTypeList());
				wirelineOfferProductSummary.setContractTermList(wirelineOfferProduct.getContractTermList());
				wirelineOfferProductSummary.setProductTemplateIdentifier(wirelineOfferProduct.getProductTemplateIdentifier());
				wirelineOfferProductSummary.setProductComponentList(wirelineOfferProduct.getProductComponentList());
				wirelineOfferProductSummary.setProductCatalogSystemId(wirelineOfferProduct.getProductCatalogSystemId());
				wirelineOfferProductSummary.setMainComponentIdentifier(wirelineOfferProduct.getMainComponentIdentifier());
				wirelineOfferProductSummary.setProductCategoryCode(wirelineOfferProduct.getProductCategoryCode());
				wirelineOfferProductSummaryList.add(wirelineOfferProductSummary);
			}
		}
		return wirelineOfferProductSummaryList;
	}

	private static SweetenerOfferSummary.OfferProductSummary getOfferProductSummary(Sweetener sweetener) {
		SweetenerOfferSummary.OfferProductSummary offerProductSummary = new SweetenerOfferSummary.OfferProductSummary();

		if (sweetener.getOfferProduct() != null) {
			offerProductSummary.setWirelineOfferProductSummaryList(getSweetenerWirelineOfferProductList(sweetener.getOfferProduct().getWirelineOfferProductList(),false));
		}

		return offerProductSummary;
	}

	private static List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary> getSweetenerWirelineOfferProductList(List<WirelineOfferProduct> wirelineOfferProductList, boolean manualSweetenerInd) {
		List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary> wirelineOfferProductSummaryList = new ArrayList<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary>();
		if(wirelineOfferProductList!=null && !wirelineOfferProductList.isEmpty()){
			for(WirelineOfferProduct wirelineOfferProduct : wirelineOfferProductList){
				com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary wirelineOfferProductSummary = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary();
				wirelineOfferProductSummary.setProductTypeCode(wirelineOfferProduct.getProductTypeCode());
				wirelineOfferProductSummary.setTransactionTypeList(wirelineOfferProduct.getTransactionTypeList());
				wirelineOfferProductSummary.setContractTermList(wirelineOfferProduct.getContractTermList());
				wirelineOfferProductSummary.setProductTemplateIdentifier(wirelineOfferProduct.getProductTemplateIdentifier());
				wirelineOfferProductSummary.setProductComponentList(wirelineOfferProduct.getProductComponentList());
				wirelineOfferProductSummary.setProductCatalogSystemId(wirelineOfferProduct.getProductCatalogSystemId());
				wirelineOfferProductSummary.setMainComponentIdentifier(wirelineOfferProduct.getMainComponentIdentifier());
				wirelineOfferProductSummary.setDiscountList(wirelineOfferProduct.getDiscountList());
				wirelineOfferProductSummaryList.add(wirelineOfferProductSummary);
			}
		}
		return wirelineOfferProductSummaryList;
	}

	public static GetAvailableOfferSummaryListResponseType transform(GetOffersResponseVO offersResponseVO) {
		GetAvailableOfferSummaryListResponseType result = new GetAvailableOfferSummaryListResponseType();
		if (offersResponseVO != null) {
			if (!offersResponseVO.isMsgHasError()) {
				result.setAccessoryOfferSummaryList(offersResponseVO.getAccessoryOfferSummaryList());
				result.setSalesOfferSummaryList(offersResponseVO.getSalesOfferSummaryList());
				result.setSweetenerOfferSummaryList(offersResponseVO.getSweetenerOfferSummaryList());
			}
			if (!offersResponseVO.getMessageList().isEmpty()) {
				//result.setMessageList(offersResponseVO.getMessageList());
				result.setMessageList(getMessageList(offersResponseVO.getMessageList()));
			}
		}
		return result;
	}

	private static List<com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType.MessageList> getMessageList(
			List<MessageList> messageList) {
		// TODO Auto-generated method stub
		List<com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType.MessageList> resultMsgList = new ArrayList<GetAvailableOfferSummaryListResponseType.MessageList>();

		for (int i = 0; i < messageList.size(); i++) {
			com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType.MessageList msg = new com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType.MessageList();
			
			msg.setContextData(messageList.get(i).getContextData());
			msg.setDateTimeStamp(messageList.get(i).getDateTimeStamp());
			msg.setErrorCode(messageList.get(i).getErrorCode());
			msg.setMessageList(messageList.get(i).getMessageList());
			msg.setMessageType(messageList.get(i).getMessageType());
			msg.setRelatedMessageList(messageList.get(i).getRelatedMessageList());
			msg.setTransactionId(messageList.get(i).getTransactionId());

			resultMsgList.add(msg);
		}

		return resultMsgList;
	}

	public static ServiceAddressRequestVO transformServiceAddressReq(GetOffersRequestVO requestVO) {
		ServiceAddressRequestVO addressRequestVO = new ServiceAddressRequestVO();
		if (requestVO.getSalesOfferCriteria() != null) {
			if(requestVO.getSalesOfferCriteria().getServiceAddress()!=null){
				
			addressRequestVO.setAddressId(requestVO.getSalesOfferCriteria().getServiceAddress().getServiceAddressId());
			addressRequestVO.setProvinceCode(requestVO.getSalesOfferCriteria().getServiceAddress().getProvinceCode());
			}
			if (requestVO.getOperationHeader()!=null) {
				addressRequestVO.setSalesTransactionId(requestVO.getOperationHeader().getSalesTransactionId());
			}
			//getting the npaNxx from request 
			if(requestVO.getSalesOfferCriteria().getOfferFilter()!=null){
				addressRequestVO.setNpaNxx(requestVO.getSalesOfferCriteria().getOfferFilter().getNpaNxx());
			}
		}
		return addressRequestVO;
	}

	public static GetOfferListByPromotionCodeForCustomerAdapterRequest transformOfferListForCustomerByPromoCd(
			SalesOfferCommonVO offerCommonVO, boolean callForRecontractEligibleInd, String productCatalogId) {
		GetOfferListByPromotionCodeForCustomerAdapterRequest adapterRequest = new GetOfferListByPromotionCodeForCustomerAdapterRequest();
		adapterRequest.setCustomer(getCustomer(offerCommonVO));
		adapterRequest.setOutlet(transform(offerCommonVO));
		if(offerCommonVO.getOfferDetailRequestVO()!=null){
			//set the WirelineTransactionalContext based on getOfferDetail request
			adapterRequest.setWirelineTransactionalContext(WLNOfferUtil.getWirelineTransactionalContext(offerCommonVO.getOfferDetailRequestVO(),offerCommonVO,callForRecontractEligibleInd));
		}else{
			adapterRequest.setWirelineTransactionalContext(WLNOfferUtil.getWirelineTransactionalContext(offerCommonVO,callForRecontractEligibleInd,productCatalogId));
		}
		adapterRequest.setPromotionCode(offerCommonVO.getOffersRequestVO().getSalesOfferCriteria().getPromotionCd());
		adapterRequest.setSalesTransactionId(offerCommonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId());
		return adapterRequest;
	}

	public static void transform (SalesOfferCommonVO commonVO, GetOffersResponseVO offersResponseVO) {
		if (commonVO.getOfferListAdapterResponse()!=null && !commonVO.getOfferListAdapterResponse().isMsgHasError()) {
			offersResponseVO.setSalesOfferSummaryList(getSalesOfferSummaryList(commonVO));
			offersResponseVO.setSweetenerOfferSummaryList(getSweetenerOfferSummaryList(commonVO));
		}
		else {
			BaseOfferTransformer.transform(commonVO, offersResponseVO);
		}
	}


	public static GetSweetenerOfferListForCustomerAdapterRequest transformOfferListForSweetener(SalesOfferCommonVO commonVO) {
		GetSweetenerOfferListForCustomerAdapterRequest adapterRequest = new GetSweetenerOfferListForCustomerAdapterRequest();
		adapterRequest.setCustomer(getCustomer(commonVO));
		adapterRequest.setOutlet(transform(commonVO));
		adapterRequest.setWirelineTransactionalContext(getWirelineTransactionalContextForSweeteners(commonVO));
		adapterRequest.setSalesTransactionId(commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId());
		adapterRequest.setOfferHeaderList(getOfferHeaderList(commonVO));
		return adapterRequest;
	}

	private static List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader> getOfferHeaderList(
			SalesOfferCommonVO commonVO) {
		List<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader> offerHeaderList = new ArrayList<com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader>();
		String brandId = EnterpriseSalesServiceUtil.getBrandId(commonVO.getOffersRequestVO().getOperationHeader().getBrandCode());
		if(commonVO!=null && commonVO.getOffersRequestVO()!=null && commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria()!=null){
			if(commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList()!=null && !commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList().isEmpty()){
				List<WirelineSalesSummary> associatedWirelineSalesSummary = commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList();
				for(WirelineSalesSummary wirelineSalesSummary : associatedWirelineSalesSummary){
					if(wirelineSalesSummary.getOfferHeader()!=null){
						com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader offerHeader = new com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader();
						offerHeader.setOfferId(Long.valueOf(wirelineSalesSummary.getOfferHeader().getOfferId()));
						offerHeader.setBrandId(Long.valueOf(brandId));
						offerHeader.setTypeCode(EnterpriseWLNSalesServicesConstants.WLN_OFFER_TYPE_CD);
						offerHeader.setProgramId(Long.valueOf(wirelineSalesSummary.getOfferHeader().getOfferProgramId()));
						offerHeader.setProgramCode(wirelineSalesSummary.getOfferHeader().getOfferCode());
						offerHeader.setPerspectiveDate(wirelineSalesSummary.getOfferHeader().getPerspectiveDate());
						offerHeaderList.add(offerHeader);
					}
				}
			}
		}
		return offerHeaderList;
	}

	private static WirelineTransactionalContext getWirelineTransactionalContextForSweeteners(SalesOfferCommonVO commonVO) {
		WirelineTransactionalContext wirelineTransactionalContext = new WirelineTransactionalContext();
		List<TransactionalProduct> transactionalProductList = new ArrayList<TransactionalProduct>();

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

		if(commonVO.getOffersRequestVO()!=null && commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria()!=null && commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList()!=null && !commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList().isEmpty()){
			List<WirelineSalesSummary> associatedWirelineSalesSummary = commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList();
			for(WirelineSalesSummary wirelineSalesSummary : associatedWirelineSalesSummary){
				if(wirelineSalesSummary.getProductList()!=null && !wirelineSalesSummary.getProductList().isEmpty()){
					for(OfferProductHeader offerProductHeader : wirelineSalesSummary.getProductList()){
						TransactionalProduct transactionalProduct = new TransactionalProduct();
						transactionalProduct.setProductCode(offerProductHeader.getProductTypeCd());
						transactionalProduct.setContractTermCnt(new BigInteger(offerProductHeader.getContractTermCd()));
						transactionalProduct.setTransactionTypeCd(offerProductHeader.getProductOrderType().getProductOrderTypeCd().toLowerCase());
						if(offerProductHeader.getProductComponentList()!=null && !offerProductHeader.getProductComponentList().isEmpty()){
							transactionalProduct.setProductCatalogIdList(WLNOfferUtil.getProductCatalogIdList(offerProductHeader.getProductComponentList()));
						} else {
							// 2018 Aug release for "nochange"
							// CP should filter out "nochange" product 
						}
						transactionalProductList.add(transactionalProduct);
					}
				}
			}
		}
		
		wirelineTransactionalContext.setTransactionalProductList(transactionalProductList);
		wirelineTransactionalContext.setFetchBundleOfferInd(false);
		
		return wirelineTransactionalContext;
	}

	public static GetOfferListByOfferIdentifierListAdapterRequest transformOfferListForAccessoryOfferListByOfferIdentifierList(SalesOfferCommonVO commonVO) {
		GetOfferListByOfferIdentifierListAdapterRequest adapterRequest = new GetOfferListByOfferIdentifierListAdapterRequest();
		adapterRequest.setBrandId(Long.valueOf(EnterpriseSalesServiceUtil.getBrandId(commonVO.getOffersRequestVO().getOperationHeader().getBrandCode())));
		adapterRequest.setOutlet(transform(commonVO));
		adapterRequest.setSalesTransactionId(commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId());
		adapterRequest.setOfferIdentifierList(extractOfferIdentifierList(commonVO));
		return adapterRequest;
	}

	private static List<OfferIdentifier> extractOfferIdentifierList(SalesOfferCommonVO commonVO) {
		List<OfferIdentifier> offerIdentifierList = null;

		if ( (commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria() != null) &&
			 (commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getOfferFilter() != null) &&
			 (commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getOfferFilter().getSelectedAccessoryOfferList() != null) &&
			 (!commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getOfferFilter().getSelectedAccessoryOfferList().isEmpty()) ) {
			offerIdentifierList = new ArrayList<OfferIdentifier>();

			for (AccessoryOfferHeader accessoryOfferHeader : commonVO.getOffersRequestVO().getSweetenerOfferFilterCriteria().getOfferFilter().getSelectedAccessoryOfferList()) {
				if ( (accessoryOfferHeader != null) && (accessoryOfferHeader.getOfferHeader() != null) &&
					 (accessoryOfferHeader.getOfferHeader().getOfferId() != null) && (!accessoryOfferHeader.getOfferHeader().getOfferId().isEmpty()) ) {
					OfferIdentifier offerIdentifier = new OfferIdentifier();
					offerIdentifier.setOfferId(Long.valueOf(accessoryOfferHeader.getOfferHeader().getOfferId()));
					offerIdentifier.setProgramId(Long.valueOf(accessoryOfferHeader.getOfferHeader().getOfferProgramId()));
					offerIdentifier.setPerspectiveDate(accessoryOfferHeader.getOfferHeader().getPerspectiveDate());
					offerIdentifier.setOfferTypeCode(EnterpriseWLNSalesServicesConstants.OFFER_TYPE_CODE_ACCESSORY_WLN_GWP);

					offerIdentifierList.add(offerIdentifier);
				}
			}
		}

		return offerIdentifierList;
	}

	public static GetProductQualificationAdapterRequest transformProductQualificationRequest(
			GetOffersRequestVO request) {

		OperationHeader operationHeader = request.getOperationHeader();
		
		GetProductQualificationAdapterRequest result = new GetProductQualificationAdapterRequest();

		result.setSalesTransactionId(operationHeader.getSalesTransactionId());
		result.setCorrelationId(operationHeader.getSalesId());

		if (!operationHeader.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()) {
			result.setChannelOutletId(operationHeader.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedChannelOutletId());
		}

		if ( (operationHeader.isRefreshInd() != null) && (operationHeader.isRefreshInd())) {
			result.setRefreshCache(operationHeader.isRefreshInd());
		}

		result.setSalesRepId(operationHeader.getUserProfile().getSalesRepId());
		
		SalesOfferCriteriaVO salesOfferCriteria = request.getSalesOfferCriteria();
		if (salesOfferCriteria != null) {
			ServiceAddressVO serviceAddress = salesOfferCriteria.getServiceAddress();
			if (serviceAddress != null) {
				result.setAddressId(serviceAddress.getServiceAddressId());
				result.setCity(serviceAddress.getCityCode());
				result.setProvinceCd(serviceAddress.getProvinceCode());				
			}
			if (StringUtils.isNotBlank(salesOfferCriteria.getCustomerId())) {
				result.setCustomerId(salesOfferCriteria.getCustomerId());
			}
			if (StringUtils.isNotBlank(salesOfferCriteria.getBillingAccountNumber())) {
				result.setBillingAccountNumber(salesOfferCriteria.getBillingAccountNumber());
			}	
		}

		result.setQualByServiceId(false);

		return result;
	}
}
