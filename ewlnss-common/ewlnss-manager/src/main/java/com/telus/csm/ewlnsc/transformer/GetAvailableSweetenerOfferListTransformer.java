package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.schemasclone.AgentProfile;
import com.telus.csm.ewlnsc.domain.schemasclone.GetAvailableSweetenerOfferListRequestVO;
import com.telus.csm.ewlnsc.domain.schemasclone.GetAvailableSweetenerOfferListResponseVO;
import com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader;
import com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader.AssociatedOriginalSales;
import com.telus.csm.ewlnsc.domain.schemasclone.OperationHeader.SystemIntegrationParameterList;
import com.telus.csm.ewlnsc.domain.schemasclone.ServiceAddressBase;
import com.telus.csm.ewlnsc.domain.schemasclone.ServiceIdentifier;
import com.telus.csm.ewlnsc.domain.schemasclone.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.schemasclone.SweetnerOfferFilterCriteria;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineOfferHeaderWithOfferFilter;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineSalesSummary;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;

public class GetAvailableSweetenerOfferListTransformer {

	public static GetOffersRequestVO transform(GetAvailableSweetenerOfferListRequestVO availableSweetenerOfferListRequestVO) {
		GetOffersRequestVO offersRequestVO = null;

		if (availableSweetenerOfferListRequestVO != null) {
			offersRequestVO = new GetOffersRequestVO();
			offersRequestVO.setOperationHeader(tranform(availableSweetenerOfferListRequestVO.getOperationHeader()));
			offersRequestVO.setSweetenerOfferFilterCriteria(transform(availableSweetenerOfferListRequestVO.getSweetenerOfferFilterCriteria()));
		}

		return offersRequestVO;
	}

	public static com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SweetnerOfferFilterCriteria transform(SweetnerOfferFilterCriteria sweetenerOfferFilterCriteria) {
		com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SweetnerOfferFilterCriteria schemaSweetnerOfferFilterCriteria = null;

		if (sweetenerOfferFilterCriteria != null) {
			schemaSweetnerOfferFilterCriteria = new com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SweetnerOfferFilterCriteria();
			schemaSweetnerOfferFilterCriteria.setBillingAccountNumber(sweetenerOfferFilterCriteria.getBillingAccountNumber());
			schemaSweetnerOfferFilterCriteria.setCustomerId(sweetenerOfferFilterCriteria.getCustomerId());
			schemaSweetnerOfferFilterCriteria.setServiceAddress(transform(sweetenerOfferFilterCriteria.getServiceAddress()));
			schemaSweetnerOfferFilterCriteria.setAssociatedWirelineSalesSummaryList(transformWirelineSalesSummaryList(sweetenerOfferFilterCriteria.getAssociatedWirelineSalesSummaryList()));
			schemaSweetnerOfferFilterCriteria.setSubscribedServiceIdentifierList(transformsubscribedServiceIdentifierList(sweetenerOfferFilterCriteria.getSubscribedServiceIdentifierList()));
		}

		return schemaSweetnerOfferFilterCriteria;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase transform(ServiceAddressBase serviceAddressBase) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase schemaServiceAddressBase = null;

		if (serviceAddressBase != null) {
			schemaServiceAddressBase = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddressBase();

			if (!StringUtils.isEmpty(serviceAddressBase.getServiceAddressId())) {
				schemaServiceAddressBase.setServiceAddressId(serviceAddressBase.getServiceAddressId());
			}

			if (!StringUtils.isEmpty(serviceAddressBase.getProvinceCode())) {
				schemaServiceAddressBase.setProvinceCode(serviceAddressBase.getProvinceCode());
			}

			if (!StringUtils.isEmpty(serviceAddressBase.getCityCode())) {
				schemaServiceAddressBase.setCityCode(serviceAddressBase.getCityCode());
			}
		}

		return schemaServiceAddressBase;
	}

	public static List<com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelineSalesSummary> transformWirelineSalesSummaryList(List<WirelineSalesSummary> associatedWirelineSalesSummaryList) {
		List<com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelineSalesSummary> schemaWirelineSalesSummaryList = null;

		if ( (associatedWirelineSalesSummaryList != null) && (!associatedWirelineSalesSummaryList.isEmpty()) ) {
			schemaWirelineSalesSummaryList = new ArrayList<com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelineSalesSummary>();
			
			for (WirelineSalesSummary associatedWirelineSalesSummary : associatedWirelineSalesSummaryList) {
				com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelineSalesSummary schemaAssociatedWirelineSalesSummary = new com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelineSalesSummary();
				schemaAssociatedWirelineSalesSummary.setOfferHeader(transformOfferHeader(associatedWirelineSalesSummary.getOfferHeader()));
				schemaAssociatedWirelineSalesSummary.setProductList(GetSalesOfferDetailSchemaIndependentTransformer.transformProductList(associatedWirelineSalesSummary.getProductList()));
				schemaWirelineSalesSummaryList.add(schemaAssociatedWirelineSalesSummary);
			}
		}
		
		return schemaWirelineSalesSummaryList;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferHeaderWithOfferFilter transformOfferHeader(WirelineOfferHeaderWithOfferFilter offerHeader) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferHeaderWithOfferFilter schemaWirelineOfferHeaderWithOfferFilter = null;

		if (offerHeader != null) {
			schemaWirelineOfferHeaderWithOfferFilter = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferHeaderWithOfferFilter();
			schemaWirelineOfferHeaderWithOfferFilter.setOfferCode(offerHeader.getOfferCode());
			schemaWirelineOfferHeaderWithOfferFilter.setOfferFilter(GetSalesOfferDetailSchemaIndependentTransformer.transform(offerHeader.getOfferFilter()));
			schemaWirelineOfferHeaderWithOfferFilter.setOfferId(offerHeader.getOfferId());
			schemaWirelineOfferHeaderWithOfferFilter.setOfferInstanceId(offerHeader.getOfferInstanceId());
			schemaWirelineOfferHeaderWithOfferFilter.setOfferProgramId(offerHeader.getOfferProgramId());
			schemaWirelineOfferHeaderWithOfferFilter.setPerspectiveDate(offerHeader.getPerspectiveDate());
			schemaWirelineOfferHeaderWithOfferFilter.setPromotionCode(offerHeader.getPromotionCode());
			schemaWirelineOfferHeaderWithOfferFilter.setSystemId(offerHeader.getSystemId());
		}

		return schemaWirelineOfferHeaderWithOfferFilter;
	}

	public static List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier> transformsubscribedServiceIdentifierList(List<ServiceIdentifier> subscribedServiceIdentifierList) {
		List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier> schemaServiceIdentifierList = null;

		if ( (subscribedServiceIdentifierList != null) && (!subscribedServiceIdentifierList.isEmpty()) ) {
			schemaServiceIdentifierList = new ArrayList<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier>();

			for (ServiceIdentifier serviceIdentifier : subscribedServiceIdentifierList) {
				com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier schemaServiceIdentifier = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier();
				schemaServiceIdentifier.setServiceId(serviceIdentifier.getServiceId());
				schemaServiceIdentifier.setServiceReferenceId(serviceIdentifier.getServiceReferenceId());
				schemaServiceIdentifierList.add(schemaServiceIdentifier);
			}
		}

		return schemaServiceIdentifierList;
	}

	public static GetAvailableSweetenerOfferListResponseVO transform(GetOffersResponseVO offersResponseVO) {
		GetAvailableSweetenerOfferListResponseVO availableSweetenerOfferListResponseVO = null;

		if ( (offersResponseVO != null) && (offersResponseVO.getSweetenerOfferSummaryList() != null) && (!offersResponseVO.getSweetenerOfferSummaryList().isEmpty()) ) {
			availableSweetenerOfferListResponseVO = new GetAvailableSweetenerOfferListResponseVO();
			availableSweetenerOfferListResponseVO.setSweetenerOfferSummaryList(transformSweetenerOfferSummaryList(offersResponseVO.getSweetenerOfferSummaryList()));
		}

		return availableSweetenerOfferListResponseVO;
	}

	public static List<SweetenerOfferSummary> transformSweetenerOfferSummaryList(List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SweetenerOfferSummary> schemaSweetenerOfferSummaryList) {
		List<SweetenerOfferSummary> sweetenerOfferSummaryList = null;

		if ( (schemaSweetenerOfferSummaryList != null) && (!schemaSweetenerOfferSummaryList.isEmpty()) ) {
			sweetenerOfferSummaryList = new ArrayList<SweetenerOfferSummary>();

			for (com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SweetenerOfferSummary schemaSweetenerOfferSummary : schemaSweetenerOfferSummaryList) {
				SweetenerOfferSummary sweetenerOfferSummary = new SweetenerOfferSummary();
				sweetenerOfferSummary.setProgramCode(schemaSweetenerOfferSummary.getProgramCode());
				sweetenerOfferSummary.setBasePriceAmt(schemaSweetenerOfferSummary.getBasePriceAmt());
				sweetenerOfferSummary.setOfferCategoryList(schemaSweetenerOfferSummary.getOfferCategoryList());
				// TODO resolve below compilation error
//				sweetenerOfferSummary.setOfferCd(schemaSweetenerOfferSummary.getOfferCd());
				sweetenerOfferSummary.setOfferDescriptionList(schemaSweetenerOfferSummary.getOfferDescriptionList());
				sweetenerOfferSummary.setOfferId(schemaSweetenerOfferSummary.getOfferId());
				sweetenerOfferSummary.setOfferProductSummary(transformOfferProductSummary(schemaSweetenerOfferSummary.getOfferProductSummary()));
				sweetenerOfferSummary.setOfferTierId(schemaSweetenerOfferSummary.getOfferTierId());
				sweetenerOfferSummary.setPaymentSupportTypeCode(schemaSweetenerOfferSummary.getPaymentSupportTypeCode());
				sweetenerOfferSummary.setPerspectiveDate(schemaSweetenerOfferSummary.getPerspectiveDate());
				sweetenerOfferSummary.setProductEligiblityList(schemaSweetenerOfferSummary.getProductEligiblityList());
				sweetenerOfferSummary.setRankingNum(schemaSweetenerOfferSummary.getRankingNum());
				sweetenerOfferSummary.setStackableInd(schemaSweetenerOfferSummary.isStackableInd());
				// TODO resolve below compilation error
//				sweetenerOfferSummary.setSystemId(schemaSweetenerOfferSummary.getSystemId());
				sweetenerOfferSummary.setWirelessOfferCode(schemaSweetenerOfferSummary.getWirelessOfferCode());

				sweetenerOfferSummaryList.add(sweetenerOfferSummary);
			}
		}

		return sweetenerOfferSummaryList;
	}

	public static SweetenerOfferSummary.OfferProductSummary transformOfferProductSummary(com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SweetenerOfferSummary.OfferProductSummary schemaOfferProductSummary) {
		SweetenerOfferSummary.OfferProductSummary offerProductSummary = null;

		if (schemaOfferProductSummary != null) {
			offerProductSummary = new SweetenerOfferSummary.OfferProductSummary();
			offerProductSummary.setWirelineOfferProductSummaryList(transformWirelineOfferProductSummaryList(schemaOfferProductSummary.getWirelineOfferProductSummaryList()));
		}

		return offerProductSummary;
	}

	public static List<WirelineOfferProductSummary> transformWirelineOfferProductSummaryList(List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary> schemaWirelineOfferProductSummaryList) {
		List<WirelineOfferProductSummary> wirelineOfferProductSummaryList = null;

		if ( (schemaWirelineOfferProductSummaryList != null) && (!schemaWirelineOfferProductSummaryList.isEmpty()) ) {
			wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();

			for (com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProductSummary schemaWirelineOfferProductSummary :schemaWirelineOfferProductSummaryList) {
				WirelineOfferProductSummary wirelineOfferProductSummary = new WirelineOfferProductSummary();
				wirelineOfferProductSummary.setDiscountList(schemaWirelineOfferProductSummary.getDiscountList());
				wirelineOfferProductSummary.setProductTypeCode(schemaWirelineOfferProductSummary.getProductTypeCode());
				wirelineOfferProductSummaryList.add(wirelineOfferProductSummary);
			}
		}

		return wirelineOfferProductSummaryList;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader tranform(OperationHeader operationHeader) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader schemaOperationHeader = null;

		if (operationHeader !=  null) {
			schemaOperationHeader = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader();
			schemaOperationHeader.setSalesType(operationHeader.getSalesType());
			schemaOperationHeader.setOrderType(operationHeader.getOrderType());
			schemaOperationHeader.setOrderSubType(operationHeader.getOrderSubType());
			schemaOperationHeader.setOriginatorApplicationId(operationHeader.getOriginatorApplicationId());
			schemaOperationHeader.setSalesTransactionId(operationHeader.getSalesTransactionId());
			schemaOperationHeader.setSalesTransactionTimestamp(operationHeader.getSalesTransactionTimestamp());
			schemaOperationHeader.setSalesId(operationHeader.getSalesId());
			schemaOperationHeader.setETransactionId(operationHeader.getETransactionId());
			schemaOperationHeader.setAssociatedOriginalSales(transform(operationHeader.getAssociatedOriginalSales()));
			schemaOperationHeader.setUserProfile(operationHeader.getUserProfile());
			schemaOperationHeader.setAgentProfile(transform(operationHeader.getAgentProfile()));
			schemaOperationHeader.setSalesPersonRoleCode(operationHeader.getSalesPersonRoleCode());
			schemaOperationHeader.setBrandCode(operationHeader.getBrandCode());
			schemaOperationHeader.setManagerOverrideIndicator(operationHeader.isManagerOverrideIndicator());
			schemaOperationHeader.setAuthorizingUserProfile(transform(operationHeader.getAuthorizingUserProfile()));
			schemaOperationHeader.setSalesRecommendationIndicator(operationHeader.isSalesRecommendationIndicator());
			schemaOperationHeader.setRefreshInd(operationHeader.isRefreshInd());
			schemaOperationHeader.setSystemIntegrationParameterList(transformSystemIntegrationParameterList(operationHeader.getSystemIntegrationParameterList()));
		}

		return schemaOperationHeader;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.AssociatedOriginalSales transform(AssociatedOriginalSales associatedOriginalSales) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.AssociatedOriginalSales schemaAssociatedOriginalSales = null;

		if (associatedOriginalSales != null) {
			schemaAssociatedOriginalSales = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.AssociatedOriginalSales();
			schemaAssociatedOriginalSales.setETransactionId(associatedOriginalSales.getETransactionId());
			schemaAssociatedOriginalSales.setSalesId(associatedOriginalSales.getSalesId());			
		}

		return schemaAssociatedOriginalSales;
	}

	public static com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AgentProfile transform(AgentProfile agentProfile) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AgentProfile schemaAgentProfile = null;

		if (agentProfile != null) {
			schemaAgentProfile = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AgentProfile();
			schemaAgentProfile.setChannelOrganizationTypeCd(agentProfile.getChannelOrganizationTypeCd());
			schemaAgentProfile.setChannelOrganizationInternalId(agentProfile.getChannelOrganizationInternalId());
			schemaAgentProfile.setChannelOrganizationNum(agentProfile.getChannelOrganizationNum());
			schemaAgentProfile.setEmployeeId(agentProfile.getEmployeeId());
			schemaAgentProfile.setLoginId(agentProfile.getLoginId());
			schemaAgentProfile.setPortalRoleId(agentProfile.getPortalRoleId());
			schemaAgentProfile.setKnowbilityId(agentProfile.getKnowbilityId());
			schemaAgentProfile.setUserPrivilegeRoleCodeList(agentProfile.getUserPrivilegeRoleCodeList());
		}

		return schemaAgentProfile;
	}

	public static List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList> transformSystemIntegrationParameterList(List<SystemIntegrationParameterList> systemIntegrationParameterList) {
		List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList> schemaSystemIntegrationParameterList = null;

		if ( (systemIntegrationParameterList != null) && (!systemIntegrationParameterList.isEmpty()) ) {
			schemaSystemIntegrationParameterList = new ArrayList<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList>();

			for (SystemIntegrationParameterList systemIntegrationParameter : systemIntegrationParameterList) {
				com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList schemaSystemIntegrationParameter = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader.SystemIntegrationParameterList();
				schemaSystemIntegrationParameter.setParameterName(systemIntegrationParameter.getParameterName());
				schemaSystemIntegrationParameter.setParameterValue(systemIntegrationParameter.getParameterValue());
				schemaSystemIntegrationParameterList.add(schemaSystemIntegrationParameter);
			}
		}

		return schemaSystemIntegrationParameterList;
	}
}
