package com.telus.csm.ewlnsms.rest.operation;

import javax.ws.rs.core.UriInfo;

import com.telus.csm.ewlnsc.rest.operation.EntWLNSalesOperationBase;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreRequest;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreResponse;
import com.telus.csm.ewlnsms.core.operation.SearchServiceAddressCoreOperation;
import com.telus.csm.ewlnsms.rest.domain.SearchServiceAddressRestResponse;
import com.telus.csm.ewlnsms.rest.transformer.SearchServiceAddressRestTransformer;

public class SearchServiceAddressRestOperation extends EntWLNSalesOperationBase{



	public boolean validateRequest() {
		return true;
	}
	
	public SearchServiceAddressRestResponse execute() throws Throwable {
		
		validateRequest();
		
		SearchServiceAddressRestTransformer transfomer = new SearchServiceAddressRestTransformer();
		SearchServiceAddressCoreRequest requestBO = transfomer.transformRequest(getUriInfo());
		
		SearchServiceAddressCoreOperation pojoOperation = new SearchServiceAddressCoreOperation();
		SearchServiceAddressCoreResponse responseBO = pojoOperation.execute(requestBO);
		
		return transfomer.transformResponse(responseBO);
	}

}
