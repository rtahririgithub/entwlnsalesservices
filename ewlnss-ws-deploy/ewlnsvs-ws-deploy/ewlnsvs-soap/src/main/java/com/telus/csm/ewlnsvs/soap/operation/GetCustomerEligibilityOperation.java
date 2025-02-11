package com.telus.csm.ewlnsvs.soap.operation;

import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsvs.core.operation.GetCustomerEligibilityCoreOperation;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityType;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCustomerEligibilityOperation {
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(GetCustomerEligibilityOperation.class);

	public GetCustomerEligibilityResponseType execute(GetCustomerEligibilityType param) {
		GetCustomerEligibilityResponseType response;
		LOGGER.enter("GetCustomerEligibilityOperation.execute()");
		GetCustomerEligibilityCoreOperation operation = new GetCustomerEligibilityCoreOperation();
		response = operation.execute(param);
		LOGGER.exit("GetCustomerEligibilityOperation.execute()");
		return response;
	}
}
