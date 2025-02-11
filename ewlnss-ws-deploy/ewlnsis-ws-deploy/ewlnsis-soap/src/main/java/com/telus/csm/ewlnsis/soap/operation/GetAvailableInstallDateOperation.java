package com.telus.csm.ewlnsis.soap.operation;

import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreRequest;
import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreResponse;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.operation.GetAvailableInstallDateCoreOperation;
import com.telus.csm.ewlnsis.core.transformer.GetAvailableInstallDateCoreTransformer;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableInstallDateType;

public class GetAvailableInstallDateOperation {

	//TODODONE move this to Constants
	private LoggerUtil logger = LoggerUtil.getLogger("GetAvailableInstallDateOperation");
	
	
	/***************************************************************/
	/*  execute - call GetAvailableInstallDateCoreOperation        */
	/*            to retrieve GetAvailableInstallDateResponseType  */
	/***************************************************************/
	public GetAvailableInstallDateResponseType execute(GetAvailableInstallDateType parameters){
		String functionName = "GetAvailableInstallDateResponseType.execute";
		logger.enter(functionName, parameters.toString());
		
		GetAvailableInstallDateResponseType     response             = new GetAvailableInstallDateResponseType();
		GetAvailableInstallDateCoreTransformer  coreTransformer      = new GetAvailableInstallDateCoreTransformer();
		try {	 
			GetAvailableInstallDateCoreRequest      coreRequest      = null;
			GetAvailableInstallDateCoreOperation    coreOperation    = new GetAvailableInstallDateCoreOperation();
			
			/* Transform input to Core Request Object*/
			coreRequest = coreTransformer.transformToCoreRequest(parameters);
			
			logger.info(functionName, "calling CORE service GetAvailableInstallDateCoreOperation");
			GetAvailableInstallDateCoreResponse coreResponse = coreOperation.execute(coreRequest);
			
			/* Transform to ESS Response Object */
			response = coreTransformer.transformFromCoreResponse(coreResponse);
			
		} catch (Exception throwable) { 
			logger.error(EnterpriseWLNSalesServicesConstants.UNKNOWN_EWLNSS_ERROR, functionName, "Error Calling GetAvailalbeInstallDateCoreOperation.  details -> " + throwable.getMessage());
			coreTransformer.transformException(throwable, parameters.getOperationHeader().getSalesTransactionId());
		} finally {
			logger.exit(functionName);
		}
		
		return response;
		 
	}
	
	
}
