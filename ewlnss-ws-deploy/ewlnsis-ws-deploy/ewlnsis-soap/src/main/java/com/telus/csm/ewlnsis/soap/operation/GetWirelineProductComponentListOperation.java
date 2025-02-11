package com.telus.csm.ewlnsis.soap.operation;

import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.domain.GetWirelineProductComponentListCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetWirelineProductComponentListCoreResponse;
import com.telus.csm.ewlnsis.core.operation.GetWirelineProductComponentListCoreOperation;
import com.telus.csm.ewlnsis.core.transformer.GetWirelineProductComponentListTransformer;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetWirelineProductComponentListType;

/**
 *
 * @author Jose.Mena
 *
 */
public class GetWirelineProductComponentListOperation {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetWirelineProductComponentListOperation.class);

	public GetWirelineProductComponentListResponseType execute(GetWirelineProductComponentListType param) {
		String functionName = "execute()";
		logger.enter(functionName);
		
		GetWirelineProductComponentListCoreRequest coreRequest = GetWirelineProductComponentListTransformer.transform(param);
		
		GetWirelineProductComponentListCoreOperation operation = new GetWirelineProductComponentListCoreOperation();
		GetWirelineProductComponentListCoreResponse coreResponse = operation.execute(coreRequest);
		
		GetWirelineProductComponentListResponseType response = GetWirelineProductComponentListTransformer.transform(coreResponse);
		
		logger.exit(functionName);
		return response;
	}
}
