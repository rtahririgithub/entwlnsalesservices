package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterResponse;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.GetServiceAddressDetails;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.GetServiceAddressDetailsResponse;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.SearchServiceAddress;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.SearchServiceAddressResponse;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;


/*************************************************************************/
/* ServiceAddresesMgmSvcTransformer                                       */
/*    Transform from ESS request object to GetServiceAddressDetails       */
/*                                         GetServiceAddressDetails       */
/*    Transform from response object to ESS searchServiceAddressResponse  */
/*                                                                        */
/**************************************************************************/


public class ServiceAddresesMgmSvcTransformer {

	/*************************************************************/
	/* transform To GetServiceAddressDetails                     */ 
	/*              ESS to downstream request                    */
	/*************************************************************/
	public GetServiceAddressDetails transformToGetServiceAddressDetails(GetServiceAddressDetailsAdapterRequest parm){
		GetServiceAddressDetails getServiceAddressDetails = new GetServiceAddressDetails();
		ServiceAddress address = new ServiceAddress();
		address.setAddressId(parm.getAddress().getAddressId());
		address.setProvinceStateCode(parm.getAddress().getProvinceStateCode());
		//Optionals
		address.setMunicipalityName(parm.getAddress().getMunicipalityName());
		address.setPostalZipCode(parm.getAddress().getPostalZipCode());
		address.setStreetName(parm.getAddress().getStreetName());
		address.setStreetNumber(parm.getAddress().getStreetNumber());
		address.setUnitNumber(parm.getAddress().getUnitNumber());
		address.setUnitTypeCode(parm.getAddress().getUnitTypeCode());
		address.setCountryCode(parm.getAddress().getCountryCode());	
		getServiceAddressDetails.setAddress(address);
		return getServiceAddressDetails;
	}
	
	
	/*************************************************************/
	/* transform To GetServiceAddressDetailsAapterResponse           */ 
	/*              downstream response to ESS response          */
	/*************************************************************/
	public GetServiceAddressDetailsAdapterResponse transformGetServiceAddressDetailsResponse(GetServiceAddressDetailsResponse parm){
		GetServiceAddressDetailsAdapterResponse respObject = new GetServiceAddressDetailsAdapterResponse();
		
		respObject.getResponse().setAddress(parm.getAddress());
		respObject.getResponse().setResponseMessageList(parm.getResponseMessageList());
		return respObject;
	}
	
	/*************************************************************/
	/* transform To SearchServiceAddress                         */ 
	/*************************************************************/
	public SearchServiceAddress transformToSearchServiceAddress(SearchServiceAddressAdapterRequest parm){
		SearchServiceAddress searchServiceAddress = new SearchServiceAddress();
		searchServiceAddress.setUserId(parm.getServiceAddress().getUserId());
		searchServiceAddress.setTimestamp(parm.getServiceAddress().getTimestamp());
		searchServiceAddress.setAddress(parm.getServiceAddress().getAddress());
		searchServiceAddress.setMaxRecordCount(parm.getServiceAddress().getMaxRecordCount());
		return searchServiceAddress;
	}

	/*************************************************************/
	/* transform To SearchServiceAddressAdapterResponse          */ 
	/*************************************************************/
	public SearchServiceAddressAdapterResponse transformToAdapterResponse(SearchServiceAddressResponse response) {
		SearchServiceAddressAdapterResponse adapterResponse = new SearchServiceAddressAdapterResponse();
		SearchServiceAddressResponse downstreamResponse = new SearchServiceAddressResponse();
		// Exact Match
		if(!response.getTypeList().isEmpty()){
			for(String typeList : response.getTypeList()){
				if(typeList.equalsIgnoreCase("DETAILS")){
					//Meaning: The Address object with addressId will come in the Response
					downstreamResponse.setAddress(response.getAddress());
				}else if(typeList.equalsIgnoreCase("LIKE_CITIES") && response.getLikeMunicipalityList()!=null){
					//Meaning: no addressId field coming in Response
					downstreamResponse.setLikeMunicipalityList(response.getLikeMunicipalityList());
				}else if(typeList.equalsIgnoreCase("LIKE_HOUSES") && response.getLikeHouseList()!=null){
					downstreamResponse.setLikeHouseList(response.getLikeHouseList());
				}else if(typeList.equalsIgnoreCase("LIKE_STREETS") && response.getLikeStreetList()!=null){
					downstreamResponse.setLikeStreetList(response.getLikeStreetList());
				}else if(typeList.equalsIgnoreCase("LIKE_APTS")){
					downstreamResponse.setLikeApartmentList(response.getLikeApartmentList());
				}	
			}
			downstreamResponse.setTypeList(response.getTypeList());
		}
		if(response.getResponseMessageList()!=null && !response.getResponseMessageList().getResponseMessage().isEmpty()){
			downstreamResponse.setResponseMessageList(response.getResponseMessageList());
		}
		adapterResponse.setResponse(downstreamResponse);
		return adapterResponse;
	}


	public GetServiceAddressDetailsAdapterResponse transformToAdapterResponse(
			GetServiceAddressDetailsResponse response) {
		GetServiceAddressDetailsAdapterResponse adapterResponse = new GetServiceAddressDetailsAdapterResponse();
		 GetServiceAddressDetailsResponse downstreamResponse = new GetServiceAddressDetailsResponse();
		 downstreamResponse.setAddress(response.getAddress());
		 if(response.getResponseMessageList()!=null && !response.getResponseMessageList().getResponseMessage().isEmpty()){
			 downstreamResponse.setResponseMessageList(response.getResponseMessageList());
			 adapterResponse.setMsgHasError(true);
		 }
		 adapterResponse.setResponse(downstreamResponse);
		return adapterResponse;
	}

	
	
	
}
