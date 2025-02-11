package com.telus.csm.ewlnsms.core.transformer;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterResponse;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreRequest;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreResponse;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeApartmentList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeHouseList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeMunicipalityList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeStreetList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ResponseMessage;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.SearchServiceAddress;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.LikeApartmentItem;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.LikeHouseItem;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.LikeMunicipalityItem;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.LikeStreetItem;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.MatchingServiceAddress;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class SearchServiceAddressTransformer {

	public SearchServiceAddressAdapterRequest transformRequestSearchAddress(final SearchServiceAddressCoreRequest requestBO) {
		SearchServiceAddressAdapterRequest adapterRequest = new SearchServiceAddressAdapterRequest();
		SearchServiceAddress serviceAddress = new SearchServiceAddress();
		ServiceAddress address = new ServiceAddress();
		address.setMunicipalityName(requestBO.getServiceAddress().getMunicipalityName());
		address.setPostalZipCode(requestBO.getServiceAddress().getPostalZipCode());
		address.setProvinceStateCode(requestBO.getServiceAddress().getProvinceStateCode());
		address.setStreetName(requestBO.getServiceAddress().getStreetName());
		address.setStreetNumber(requestBO.getServiceAddress().getStreetNumber());
		address.setUnitNumber(requestBO.getServiceAddress().getUnitName());
		address.setUnitTypeCode(requestBO.getServiceAddress().getUnitTypeCode());
		address.setCountryCode(requestBO.getServiceAddress().getCountryCode());	
		serviceAddress.setAddress(address);
		if(requestBO.getMaxMatchingLikeAddressCount()!=null){
			serviceAddress.setMaxRecordCount(BigInteger.valueOf(requestBO.getMaxMatchingLikeAddressCount()));
		}else{
			serviceAddress.setMaxRecordCount(BigInteger.valueOf(30));
		}
		adapterRequest.setServiceAddress(serviceAddress);
		adapterRequest.setSalesTransactionId(requestBO.getOperationHeader().getSalesTransactionId());
		return adapterRequest;
	}

	public SearchServiceAddressCoreResponse transformResponse(final AdapterResponseBase responseBO) {
		SearchServiceAddressCoreResponse searchServiceAddressCoreResponse = new SearchServiceAddressCoreResponse();
		MatchingServiceAddress matchingServiceAddress= new MatchingServiceAddress();
		if(responseBO!=null && responseBO instanceof GetServiceAddressDetailsAdapterResponse){
			GetServiceAddressDetailsAdapterResponse addressDetailsAdapterResponse = new GetServiceAddressDetailsAdapterResponse();
			addressDetailsAdapterResponse = (GetServiceAddressDetailsAdapterResponse) responseBO;
			if(addressDetailsAdapterResponse.getResponse().getResponseMessageList()!=null && !addressDetailsAdapterResponse.getResponse().getResponseMessageList().getResponseMessage().isEmpty()){
				searchServiceAddressCoreResponse.setMessageList(transformMessage(addressDetailsAdapterResponse.getResponse().getResponseMessageList().getResponseMessage()));
			}else{
			matchingServiceAddress.setAddress(transformAddress(addressDetailsAdapterResponse.getResponse().getAddress()));
			searchServiceAddressCoreResponse.setMatchingServiceAddress(matchingServiceAddress);
			}
		}else if(responseBO!=null && responseBO instanceof SearchServiceAddressAdapterResponse){
			SearchServiceAddressAdapterResponse searchServiceAddressAdapterResponse = new SearchServiceAddressAdapterResponse();
			searchServiceAddressAdapterResponse = (SearchServiceAddressAdapterResponse) responseBO;
			if(searchServiceAddressAdapterResponse.getResponse().getResponseMessageList()!=null && !searchServiceAddressAdapterResponse.getResponse().getResponseMessageList().getResponseMessage().isEmpty()){
				searchServiceAddressCoreResponse.setMessageList(transformMessage(searchServiceAddressAdapterResponse.getResponse().getResponseMessageList().getResponseMessage()));
			}else{
			if(searchServiceAddressAdapterResponse.getResponse().getLikeApartmentList()!=null){
				matchingServiceAddress.setStartIndexNum(searchServiceAddressAdapterResponse.getResponse().getLikeApartmentList().getStartIndex());
				matchingServiceAddress.setLikeApartmentItemList(transformLikeApartment(searchServiceAddressAdapterResponse.getResponse().getLikeApartmentList()));
				matchingServiceAddress.setAddress(transformAddress(searchServiceAddressAdapterResponse.getResponse().getLikeApartmentList().getAddress()));
			}else if(searchServiceAddressAdapterResponse.getResponse().getLikeHouseList()!=null){
				matchingServiceAddress.setStartIndexNum(searchServiceAddressAdapterResponse.getResponse().getLikeHouseList().getStartIndex());
				matchingServiceAddress.setLikeHouseItemList(transformLikeHouse(searchServiceAddressAdapterResponse.getResponse().getLikeHouseList()));
				matchingServiceAddress.setAddress(transformAddress(searchServiceAddressAdapterResponse.getResponse().getLikeHouseList().getAddress()));
			}else if(searchServiceAddressAdapterResponse.getResponse().getLikeMunicipalityList()!=null){
				matchingServiceAddress.setStartIndexNum(searchServiceAddressAdapterResponse.getResponse().getLikeMunicipalityList().getStartIndex());
				matchingServiceAddress.setAddress(transformAddress(searchServiceAddressAdapterResponse.getResponse().getLikeMunicipalityList().getAddress()));
				matchingServiceAddress.setLikeMunicipalityItemList(transformLikeMunicipality(searchServiceAddressAdapterResponse.getResponse().getLikeMunicipalityList()));
			}else if(searchServiceAddressAdapterResponse.getResponse().getLikeStreetList()!=null){
				matchingServiceAddress.setStartIndexNum(searchServiceAddressAdapterResponse.getResponse().getLikeStreetList().getStartIndex());
				matchingServiceAddress.setLikeStreetItemList(transformLikeStreet(searchServiceAddressAdapterResponse.getResponse().getLikeStreetList()));
				matchingServiceAddress.setAddress(transformAddress(searchServiceAddressAdapterResponse.getResponse().getLikeStreetList().getAddress()));
			}else if(searchServiceAddressAdapterResponse.getResponse().getAddress()!=null){
				matchingServiceAddress.setAddress(transformAddress(searchServiceAddressAdapterResponse.getResponse().getAddress()));
			}
			searchServiceAddressCoreResponse.setMatchingServiceAddress(matchingServiceAddress);
			}
		}
		return searchServiceAddressCoreResponse;
	}
	
	private List<LikeStreetItem> transformLikeStreet(LikeStreetList likeStreetList) {
		List<LikeStreetItem> likeStreetItemList = new ArrayList<LikeStreetItem>();
		List<com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeStreetItem> likeStreetItems = likeStreetList.getLikeStreetItem();
		if(!likeStreetItems.isEmpty()){
			for(com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeStreetItem likeStreetItem : likeStreetItems){
				LikeStreetItem streetItem = new LikeStreetItem();
				streetItem.setFirstHouseNum(likeStreetItem.getFirstHouseNumber());
				streetItem.setLastHouseNum(likeStreetItem.getLastHouseNumber());
				streetItem.setStreetTxt(likeStreetItem.getStreet());
				streetItem.setTreatmentCd(likeStreetItem.getTreatmentCode());
				streetItem.setVectorTxt(likeStreetItem.getVector());
				likeStreetItemList.add(streetItem);
			}
		}
		return likeStreetItemList;
	}

	private List<LikeMunicipalityItem> transformLikeMunicipality(LikeMunicipalityList likeMunicipalityList) {
		List<LikeMunicipalityItem> likeMunicipalityItemList = new ArrayList<LikeMunicipalityItem>();
		List<com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeMunicipalityItem> likeMunicipalityItems = likeMunicipalityList.getLikeMunicipalityItem(); 
		if(!likeMunicipalityItems.isEmpty()){
			for(com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeMunicipalityItem municipalityItem : likeMunicipalityItems){
				LikeMunicipalityItem likeMunicipalityItem = new LikeMunicipalityItem();
				likeMunicipalityItem.setMunicipalityName(municipalityItem.getMunicipalityName());
				likeMunicipalityItem.setProvinceStateCd(municipalityItem.getProvinceStateCode());
				likeMunicipalityItemList.add(likeMunicipalityItem);
			}
		}
		return likeMunicipalityItemList;
	}

	private List<LikeHouseItem> transformLikeHouse(LikeHouseList likeHouseList) {
		List<LikeHouseItem> likeHouseItemList = new ArrayList<LikeHouseItem>();
		List<com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeHouseItem> likeHouseItems = likeHouseList.getLikeHouseItem();
		if(!likeHouseItems.isEmpty()){
			for(com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeHouseItem likeHouseItem : likeHouseItems){
				LikeHouseItem houseItem = new LikeHouseItem();
				houseItem.setHouseNum(likeHouseItem.getHouseNumber());
				houseItem.setHouseSuffixNum(likeHouseItem.getHouseSuffix());
				likeHouseItemList.add(houseItem);
			}
		}
		return likeHouseItemList;
	}

	private List<LikeApartmentItem> transformLikeApartment(LikeApartmentList likeApartmentList) {
		List<LikeApartmentItem> likeApartmentItemList = new ArrayList<LikeApartmentItem>();
		List<com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeApartmentItem> likeApartmentItems = likeApartmentList.getLikeApartmentItem();
		if(!likeApartmentItems.isEmpty()){
			for(com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.LikeApartmentItem likeApartmentItem : likeApartmentItems){
				LikeApartmentItem apartmentItem = new LikeApartmentItem();
				apartmentItem.setUnitNum(likeApartmentItem.getUnitNumber());
				likeApartmentItemList.add(apartmentItem);
			}
		}
		return likeApartmentItemList;
	}

	private List<MessageList> transformMessage(List<ResponseMessage> responseMessage) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		for(ResponseMessage respMessage : responseMessage){
			MessageList message = new MessageList();
			message.setContextData(respMessage.getContextData());
			//message.setDateTimeStamp(respMessage.getDateTimeStamp());
			message.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.SEARCH_ADDRESS_DOWNSTREAM_ERROR);
			message.setMessageList(transformMessageList(respMessage.getMessageList()));
			message.setMessageType(respMessage.getMessageType());
			message.setTransactionId(respMessage.getTransactionId());
			messageList.add(message);
		}
		return messageList;
	}

	private List<Message> transformMessageList(
			com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.Message messageList) {
		List<Message> messageItems = new ArrayList<Message>();
		Message msg = new Message();
		msg.setLocale(messageList.getLocale());
		msg.setMessage(messageList.getMessage());
		messageItems.add(msg);
		return messageItems;
	}

	private com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddress transformAddress(
			ServiceAddress address) {
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddress serviceAddress = new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddress();
		serviceAddress.setXCoordinate(address.getXCoordinate());
		serviceAddress.setYCoordinate(address.getYCoordinate());
		serviceAddress.setGeocodingcalculationMethodCode(""); 
		serviceAddress.setGeocodingMatchTypeCode(""); //Are this and the above coming from Rest downstream?
		serviceAddress.setAddressId(address.getAddressId());
		serviceAddress.setAddressTypeCode(address.getAddressTypeCode());
		serviceAddress.setAdditionalAddressInformationText(address.getAdditionalAddressInformation());
		serviceAddress.setRenderedAddressText(address.getRenderedAddress());
		serviceAddress.setAddressAssignmentId(address.getAddressAssignmentId());
		serviceAddress.setAddressNumberPrefixText(address.getStreetNumberSuffix());
		serviceAddress.setAddressAssignmentSubTypeCode(address.getAddressAssignmentSubTypeCode());
		serviceAddress.setAddressAssignmentTypeCode(address.getAddressAssignmentTypeCode());
		serviceAddress.setAddressMatchingStatusCode(address.getAddressMatchingStatusCode());
		serviceAddress.setAddressSearchText(address.getAddressSearchText());
		serviceAddress.setCountryCode(address.getCountryCode());
		serviceAddress.setEmailAddressText(address.getEmailAddressText());
		serviceAddress.setExternalAddressId(address.getExternalAddressId());
		serviceAddress.setExternalAddressSourceId(address.getExternalAddressSourceId());
		serviceAddress.setExternalServiceAddressId(address.getExternalServiceAddressId());
		serviceAddress.setMailingTypeCode(address.getMailingTypeCode());
		serviceAddress.setMunicipalityName(address.getMunicipalityName());
		serviceAddress.setPostalZipCode(address.getPostalZipCode());
		serviceAddress.setProvinceStateCode(address.getProvinceStateCode());
		serviceAddress.setRelateAddressAssignmentId(address.getRelateAddressAssignmentId());
		serviceAddress.setStreetDirectionSuffixCode(address.getStreetNumberSuffix());
		serviceAddress.setStreetName(address.getStreetName());
		serviceAddress.setStreetNumber(address.getStreetNumber());
		serviceAddress.setUnitName(address.getUnitNumber());
		serviceAddress.setValidateAddressIndicator(address.getValidateAddressIndicator());
		serviceAddress.setUnitTypeCode(address.getUnitTypeCode());
		serviceAddress.setPoBox(address.getPostOfficeBoxNumber());
		//street vector: is this from the Rest response?
		serviceAddress.setCOID(address.getCOID());
		serviceAddress.setClliCd(address.getCLLICode());
		serviceAddress.setMunicipalityClliCd(address.getMunicipalityClli());
		serviceAddress.setPrewireDate(address.getPrewireDate());
		serviceAddress.setEnterPhoneNum(address.getEnterPhone());
		serviceAddress.setJurisdictionCd(address.getJurisdiction());
		serviceAddress.setBaseRateAreaCd(address.getBaseRateAreaCode());
		serviceAddress.setTransmissionZoneCd(address.getTransmissionZone());
		serviceAddress.setNnxCodeSpecialTxt(address.getNnxCodeSpecial());
		serviceAddress.setRateCenterCd(address.getRateCenter());
		serviceAddress.setSwitchNumber(address.getSwitchNumber());
		serviceAddress.setSwitchTypeCd(address.getSwitchType());
		serviceAddress.setSwitchId(address.getSwitchId());
		serviceAddress.setTerminalCd(address.getTerminalCode());
		serviceAddress.setTerminalNumber(address.getTerminalNumber());
		serviceAddress.setNpaCd(address.getNpa());
		serviceAddress.setLowestNxxCd(address.getLowestNxx());
		//QC58162 
		if (!StringUtils.isBlank(address.getServiceCount())) {
			serviceAddress.setServiceCount(Long.valueOf(address.getServiceCount()).longValue()); //TODO: check alpha numeric values when converting to Long
		}
		serviceAddress.setServiceAddressIndividualLineServiceCode(address.getServiceAddressIndividualLineServiceCode());
		serviceAddress.setPortabilityCd(address.getPortabilityCode());
		serviceAddress.setLocalRoutingNumber(address.getLocalRoutingNumber());
		serviceAddress.setRatingNpaNxxCd(address.getRatingNpaNxx());
		serviceAddress.setRateCentreRemarksTxt(address.getRateCentreRemarks());
		serviceAddress.setLegalLandDescriptionTxt(address.getLegalLandDescription());
		serviceAddress.setStreetNumberSuffixCd(address.getStreetNumberSuffix());
		serviceAddress.setDropFacilityCd(address.getDropFacilityCode());
		serviceAddress.setDropTypeCd(address.getDropTypeCode());
		serviceAddress.setDropLengthNum(address.getDropLength());
		serviceAddress.setDropExceptionCd(address.getDropExceptionCode());
		serviceAddress.setCurrentTransportTypeCd(address.getCurrentTransportTypeCode());
		serviceAddress.setFutureTransportTypeCd(address.getFutureTransportTypeCode());
		serviceAddress.setFuturePlantReadyDate(address.getFuturePlantReadyDate());
		serviceAddress.setFutureTransportRemarkTypeCd(address.getFutureTransportRemarkTypeCode());
		serviceAddress.setGponBuildTypeCd(address.getGponBuildTypeCode());
		serviceAddress.setProvisioningSystemCd(address.getProvisioningSystemCode());
		serviceAddress.setLocationPdsId(""); //Must be from Rest downstream
		serviceAddress.setGisId(""); //Comes from downstream
		return serviceAddress;
	}

	public GetServiceAddressDetailsAdapterRequest transformRequestgetDetails(SearchServiceAddressCoreRequest requestBO){
		GetServiceAddressDetailsAdapterRequest adapterRequest = new GetServiceAddressDetailsAdapterRequest();
		ServiceAddress address = new ServiceAddress();
		//Required
		address.setAddressId(requestBO.getServiceAddress().getAddressId());
		address.setProvinceStateCode(requestBO.getServiceAddress().getProvinceStateCode());
		adapterRequest.setAddress(address);
		adapterRequest.setSalesTransactionId(requestBO.getOperationHeader().getSalesTransactionId());
		return adapterRequest;
	}
	
}
