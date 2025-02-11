package com.telus.csm.ewlnsis.core.transformer;

import com.telus.csm.ewlnsis.core.domain.GetWirelineProductComponentListCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetWirelineProductComponentListCoreResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListType;

/**
 * @author Jose.Mena
 *
 */
public class GetWirelineProductComponentListTransformer {
	
	private GetWirelineProductComponentListTransformer() {
		
	}

	public static GetWirelineProductComponentListCoreRequest transform(GetWirelineProductComponentListType param) {
		GetWirelineProductComponentListCoreRequest request = new GetWirelineProductComponentListCoreRequest();
		request.setOperationHeader(param.getOperationHeader());
		request.setSalesProductComponentList(param.getSalesProductComponentList());
		return request;
	}

	/**
	 * @param coreResponse
	 * @return
	 */
	public static GetWirelineProductComponentListResponseType transform(
			GetWirelineProductComponentListCoreResponse coreResponse) {
		GetWirelineProductComponentListResponseType response = new GetWirelineProductComponentListResponseType();
		response.setMessageList(coreResponse.getMessageList());
		response.setSalesProductComponentList(coreResponse.getSalesProductComponentList());
		return response;
	}

	
}
