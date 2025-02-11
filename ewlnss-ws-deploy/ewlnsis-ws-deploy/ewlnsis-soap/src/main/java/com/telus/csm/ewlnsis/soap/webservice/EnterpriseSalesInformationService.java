package com.telus.csm.ewlnsis.soap.webservice;


import java.util.HashSet;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ewlnsc.aspect.ProfilingVariance;
import com.telus.csm.ewlnsis.ws.EnterpriseSalesInformationServicePortType;
import com.telus.csm.ewlnsis.ws.ServiceException;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.FindBetterOffer;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.FindBetterOfferResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableBundleList;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableBundleListResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableManufactureAndProductResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableManufactureAndProductType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummary;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailablePerkListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailablePerkListType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailablePricePlanResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailablePricePlanType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableProductComponentResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableProductComponentType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableSweetenerSummary;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableSweetenerSummaryResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableTelephoneNumberResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableTelephoneNumberType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableTermsResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableTermsType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetEquipmentInfoResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetEquipmentInfoType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetEquipmentSummaryResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetEquipmentSummaryType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetInstallationDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetInstallationDetailType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetNpaNxxListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetNpaNxxListType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetNpaNxxResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetNpaNxxType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetOfferDetail;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetOfferDetailResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetPricePlanDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetPricePlanDetailType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetPricePlanSummaryResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetPricePlanSummaryType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetProductInventoryResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetProductInventoryType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSalesOfferDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSalesOfferDetailType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSalesRecommendationResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSalesRecommendationType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetServiceCompatibilityListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetServiceCompatibilityListType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSweetenerDetail;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSweetenerDetailResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.PingResponse;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.1
 * 
 */
@WebService(portName = "EnterpriseSalesInformationServicePort", 
serviceName = "EnterpriseSalesInformationService_v5_0", 
targetNamespace = "http://telus.com/wsdl/MSO/ChannelSalesMgmt/EnterpriseSalesInformationService_5", 
wsdlLocation = "/wsdls/EnterpriseSalesInformationService_v5_0.wsdl", 
endpointInterface = "com.telus.csm.ewlnsis.ws.EnterpriseSalesInformationServicePortType")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class EnterpriseSalesInformationService extends SpringBeanAutowiringSupport
implements EnterpriseSalesInformationServicePortType {

	@Autowired
	private EnterpriseSalesInformationServiceImpl svcImpl;
    
	@Override
	public GetNpaNxxResponseType getNpaNxx(GetNpaNxxType parameters) throws ServiceException {
		return null;

	}

	@Override
	public GetNpaNxxListResponseType getNpaNxxList(GetNpaNxxListType parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetAvailableTelephoneNumberResponseType getAvailableTelephoneNumber(
			GetAvailableTelephoneNumberType parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetAvailableTermsResponseType getAvailableTerms(GetAvailableTermsType parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetAvailablePricePlanResponseType getAvailablePricePlan(GetAvailablePricePlanType parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetPricePlanDetailResponseType getPricePlanDetail(GetPricePlanDetailType parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetPricePlanSummaryResponseType getPricePlanSummary(GetPricePlanSummaryType parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetEquipmentInfoResponseType getEquipmentInfo(GetEquipmentInfoType parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetAvailableOfferSummaryResponse getAvailableOfferSummary(GetAvailableOfferSummary parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetOfferDetailResponse getOfferDetail(GetOfferDetail parameters) throws ServiceException {
		return null;
	}

	@Override
	public FindBetterOfferResponse findBetterOffer(FindBetterOffer parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetAvailableSweetenerSummaryResponse getAvailableSweetenerSummary(GetAvailableSweetenerSummary parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetSweetenerDetailResponse getSweetenerDetail(GetSweetenerDetail parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetAvailableManufactureAndProductResponseType getAvailableManufactureAndProduct(
			GetAvailableManufactureAndProductType parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetProductInventoryResponseType getProductInventory(GetProductInventoryType parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetEquipmentSummaryResponseType getEquipmentSummary(GetEquipmentSummaryType parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetServiceCompatibilityListResponseType getServiceCompatibilityList(
			GetServiceCompatibilityListType parameters) throws ServiceException {
		return null;
	}

	@Override
	public GetAvailableBundleListResponse getAvailableBundleList(GetAvailableBundleList parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetSalesRecommendationResponseType getSalesRecommendation(GetSalesRecommendationType parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetAvailablePerkListResponseType getAvailablePerkList(GetAvailablePerkListType parameters)
			throws ServiceException {
		return null;
	}

	@Override
	public GetAvailableInstallDateResponseType getAvailableInstallDate(GetAvailableInstallDateType parameters)
			throws ServiceException {
		return svcImpl.getAvailableInstallDate(parameters);
	}
	
	@Override
	public PingResponse ping(Ping parameters) throws ServiceException {
		return svcImpl.ping(parameters);
	}

	@Override
	public GetInstallationDetailResponseType getInstallationDetail(GetInstallationDetailType parameters)
			throws ServiceException {
		return svcImpl.getInstallationDetail(parameters);
	}

	@Override
	public GetAvailableOfferSummaryListResponseType getAvailableOfferSummaryList(
			GetAvailableOfferSummaryListType parameters) throws ServiceException {
		return svcImpl.getAvailableOfferSummaryList(getProfilingVariance(parameters), parameters);
	}

	private ProfilingVariance getProfilingVariance(GetAvailableOfferSummaryListType parameters) {
		
		HashSet<String> offerTypes = new HashSet<String>();
	
		
		if (parameters.getSalesOfferCriteria() != null) {
			offerTypes.add("sales");
		}

		if (parameters.getSweetenerOfferCriteria() != null) {
			offerTypes.add("sweetener");
		}

		if (parameters.getAccessoryOfferCriteria() != null) {
			offerTypes.add("accessory");
		}
		
		if (!offerTypes.isEmpty()) {
			return new ProfilingVariance("offer=" + offerTypes.toString());
		}
		
		return null;
	}

	@Override
	public GetAvailableProductComponentResponseType getAvailableWirelineProductList(
			GetAvailableProductComponentType parameters) throws ServiceException {
		return svcImpl.getAvailableWirelineProductList(parameters);
	}

	@Override
	public GetSalesOfferDetailResponseType getSalesOfferDetail(GetSalesOfferDetailType parameters)
			throws ServiceException {
		return svcImpl.getSalesOfferDetail(parameters);
	}

	@Override
	public GetWirelineProductComponentListResponseType getWirelineProductComponentList(
			GetWirelineProductComponentListType parameters) throws ServiceException {
		return svcImpl.getWirelineProductComponentList(parameters);
	}


}
