package com.telus.csm.ewlnsms.rest.webservices;

import javax.ws.rs.core.UriInfo;

import com.telus.csm.ewlnsms.rest.domain.CreateAccountRestRequest;
import com.telus.csm.ewlnsms.rest.domain.CreateAccountRestResponse;
import com.telus.csm.ewlnsms.rest.domain.SearchServiceAddressRestResponse;
import com.telus.csm.ewlnsms.rest.operation.CreateWLNAccountRestOperation;
import com.telus.csm.ewlnsms.rest.operation.SearchServiceAddressRestOperation;


/**
 * @author x145592
 *
 */
public class EnterpriseWlnSalesManagementRestServiceImpl{


	public SearchServiceAddressRestResponse searchServiceAddress(UriInfo uriInfo) {
		
		SearchServiceAddressRestResponse response = new SearchServiceAddressRestResponse();
		try{
			SearchServiceAddressRestOperation operation = new SearchServiceAddressRestOperation();
			operation.setUriInfo(uriInfo);
			response = operation.execute();
		}catch(Throwable e){
			
		}
		return response;
	}

	public CreateAccountRestResponse createWLNAccount(UriInfo uriInfo,CreateAccountRestRequest request) {
		CreateAccountRestResponse response = new CreateAccountRestResponse();
		try{
			CreateWLNAccountRestOperation operation = new CreateWLNAccountRestOperation();
			operation.setUriInfo(uriInfo);
			operation.setPayLoad(request);
			response = operation.execute();
		}catch(Throwable e){
			
		}
		return response;
	}
	


}
