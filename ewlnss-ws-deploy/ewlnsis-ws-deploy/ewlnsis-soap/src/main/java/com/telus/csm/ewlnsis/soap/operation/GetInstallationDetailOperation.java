package com.telus.csm.ewlnsis.soap.operation;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreResponse;
import com.telus.csm.ewlnsis.core.operation.GetInstallationDetailCoreOperation;
import com.telus.csm.ewlnsis.core.transformer.GetInstallationDetailCoreTransformer;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetInstallationDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetInstallationDetailType;

public class GetInstallationDetailOperation {

private LoggerUtil logger = LoggerUtil.getLogger("GetInstallationDetailOperation");
	
	
	/***************************************************************/
	/*  execute - call GetInstallationDetailCoreOperation        */
	/*            to retrieve GetInstallationDetailResponseType  */
	/***************************************************************/
	public GetInstallationDetailResponseType execute(GetInstallationDetailType parameters){
		String functionName = "GetInstallationDetailOperation.execute";
		logger.enter(functionName, parameters.toString());
		
		GetInstallationDetailResponseType       response             = new GetInstallationDetailResponseType();
		GetInstallationDetailCoreTransformer    coreTransformer      = new GetInstallationDetailCoreTransformer();
		
		try {	 
			GetInstallationDetailCoreRequest        coreRequest      = null;
			GetInstallationDetailCoreOperation      coreOperation    = new GetInstallationDetailCoreOperation();
			
			/* Transform input to Core Request Object*/
			coreRequest = coreTransformer.transformToCoreRequest(parameters);
			
			logger.info(functionName, "calling CORE service GetInstallationDetailCoreOperation");
			GetInstallationDetailCoreResponse coreResponse = coreOperation.execute(coreRequest);
			
			/* Transform to ESS Response Object */
			response = coreTransformer.transformFromCoreResponse(coreResponse);
			
		} catch (Throwable throwable) { 
			logger.error(EnterpriseWLNSalesServicesConstants.UNKNOWN_EWLNSS_ERROR, functionName, "Error Calling GetInstallationDetailCoreOperation.  details -> " + throwable.getMessage());
			coreTransformer.transformException(throwable, parameters.getOperationHeader().getSalesTransactionId());
		} finally {
			logger.exit(functionName);
		}
		
		return response;
		 
	}
	
}
