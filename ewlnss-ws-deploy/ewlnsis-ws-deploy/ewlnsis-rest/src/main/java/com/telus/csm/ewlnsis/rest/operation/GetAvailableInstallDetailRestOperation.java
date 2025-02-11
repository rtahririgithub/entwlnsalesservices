package com.telus.csm.ewlnsis.rest.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.rest.util.RESTResponseMessageUtil;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreResponse;
import com.telus.csm.ewlnsis.core.operation.GetAvailableInstallDetailCoreOperation;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentErrorResponse;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentRequest;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentResponse;
import com.telus.csm.ewlnsis.rest.domain.ResponseMessage;
import com.telus.csm.ewlnsis.rest.transform.GetAvailableInstallDetailTransformer;

public class GetAvailableInstallDetailRestOperation {
	
	private static LoggerUtil logger = LoggerUtil.getLogger(GetAvailableInstallDetailRestOperation.class);

	public GetAvailableAppointmentResponse execute(GetAvailableAppointmentRequest request) {
		
		try {
		List<String> messageList = validateInputs(request);
		
		if(!messageList.isEmpty()){
			GetAvailableAppointmentErrorResponse err = new GetAvailableAppointmentErrorResponse();
			err.setResponseMessages(GetAvailableInstallDetailTransformer.transformResponseMessages(messageList,"ESS_WLN_INST_DATES_0001")); 
			throw new EssRestErrorException(err);
		}
		
		GetAvailableInstallDetailCoreOperation op = new GetAvailableInstallDetailCoreOperation();		
		
		GetAvailableInstallDetailCoreResponse coreResponse = null;
		GetAvailableInstallDetailCoreRequest coreRequest = GetAvailableInstallDetailTransformer.transformFromRest(request);
		// check if the flow is Compass or FIFA
		if (StringUtils.isEmpty(request.getTechLanguage())) {		
			coreResponse = op.execute(coreRequest);
		} else { // FIFA flow
			coreResponse = op.execute(coreRequest, request.getProjectCode(), request.getTechLanguage());
		}
		if (coreResponse != null && coreResponse.isHasError() && coreResponse.getMessageList()!=null && !coreResponse.getMessageList().isEmpty()) {
			GetAvailableAppointmentErrorResponse err = new GetAvailableAppointmentErrorResponse();
			err.setResponseMessages(EnterpriseWLNCommonTransformer.transformToResponseMessagesFromCore(coreResponse.getMessageList()));
			throw new EssRestErrorException(err);
		}
		
		//transform to Rest
		GetAvailableAppointmentResponse response = null;
		// check if the flow is Compass or FIFA
		if (StringUtils.isEmpty(request.getTechLanguage())) {				
			response = GetAvailableInstallDetailTransformer.transformToRestFromCore(coreResponse);
		} else { // FIFA flow
			response = GetAvailableInstallDetailTransformer.transformToRestFromCoreFifaFlow(coreResponse);
		}
		
		return response;
		
		} catch (EssRestException e) {
			throw e;
		} catch (Exception e) {
			logger.error("error","Unknown Exception", e.getMessage(), e);
			GetAvailableAppointmentErrorResponse errorResponse = new GetAvailableAppointmentErrorResponse();
			ResponseMessage responseMessagesItem = JsonUtil.parseJsonToObject(RESTResponseMessageUtil.getResponseMessageJson(e), ResponseMessage.class);
			errorResponse.addResponseMessagesItem(responseMessagesItem);
			throw new EssRestSystemErrorException(errorResponse);
		}
	}

	private List<String> validateInputs(GetAvailableAppointmentRequest request) {
		
		
		List<String> missingFieldsList = new ArrayList<String>();
		
		/**
		 * missing shoppingCartId
		 */
		if(StringUtils.isEmpty(request.getShoppingCartId())) {
			missingFieldsList.add("shoppingCartId is missing from the request.");
		}
		
		/**
		 * fromDate
		 */
		if(request.getFromDate()==null){
			missingFieldsList.add("fromDate is missing from the request.");
		}
		
		/**
		 * endDate
		 */
		if(request.getToDate()==null){
			missingFieldsList.add("toDate is missing from the request.");
		}
		
		/**
		 * combinationInd
		 */
		if(request.isCombinationRequiredIndicator()==null){
			missingFieldsList.add("combinationInd is missing from the request.");
		}
		
		return missingFieldsList;
	}


}
