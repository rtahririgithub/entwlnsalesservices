package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterResponse;
import com.telus.csm.ewlnsc.delegate.AvailableServiceInstanceListDelegate;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnssproxy.rest.transformer.GetAvailableBoltOnServiceTransformer;

public class GetAvailableBoltOnServiceRessOperation {
	private static LoggerUtil logger = LoggerUtil.getLogger(GetAvailableBoltOnServiceRessOperation.class);

	public List<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService> execute(String transactionId, String applicationId, String customerId, String roleId) {
		List<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService> response = null;
		
		try {
			List<String> messageList = validateInputs(transactionId, applicationId, customerId, roleId);
			
			if(!messageList.isEmpty()){
				Error err = new Error();
				err.setCode(new Integer(Response.Status.BAD_REQUEST.getStatusCode()).toString()); 
				err.setReason("Invalid input."); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				err.setMessage(GetAvailableBoltOnServiceTransformer.transformResponseMessages(messageList,new Integer(Response.Status.BAD_REQUEST.getStatusCode()).toString())); 
				throw new EssRestErrorException(err);
			}
			
			GetAvailableServiceInstanceListAdapterRequest availableServiceInstanceListAdapterRequest = GetAvailableBoltOnServiceTransformer.transformAvailableServiceInstanceListRequest(transactionId, applicationId, customerId, roleId);
			
			GetAvailableServiceInstanceListAdapterResponse result = AvailableServiceInstanceListDelegate.execute(availableServiceInstanceListAdapterRequest);
			
			if (result == null || result.isMsgHasError()==true) {
				messageList.add("Unable to get the available service instances.");
				Error err = new Error();
				err.setCode(new Integer(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).toString());
				err.setReason("Unable to get the available service instances."); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				err.setMessage(GetAvailableBoltOnServiceTransformer.transformResponseMessages(messageList, new Integer(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).toString())); 
				throw new EssRestErrorException(err);				
			}
			
			if(result!=null && (result.getServiceList()!=null && result.getServiceList().size()>0)){
			    response = GetAvailableBoltOnServiceTransformer.transformResponse(result.getServiceList());
			}
						
			return response;
			
		} catch (EssRestException e) {
			throw e;
		} catch (Exception ex) {
			logger.error("error","Unknown Exception", ex.getMessage(), ex);
			Error respError = new Error();			
						
            respError.setCode(new Integer(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).toString());
            respError.setReason(ex.getMessage());
            respError.setStatus("ERROR");
            respError.setTraceId(transactionId); 

            StringBuilder msgBuilder = new StringBuilder(ex.toString());
            if(ex.getCause() != null) {
                  msgBuilder.append("; ").append(ex.getCause().toString());
            }
            Throwable traceEx = ex.getCause() != null? ex.getCause(): ex;
            msgBuilder.append(" ").append(Arrays.toString(traceEx.getStackTrace()));
            com.telus.csm.ewlnssproxy.cpib.domain.Message msg = new com.telus.csm.ewlnssproxy.cpib.domain.Message();
            msg.setType("ERROR");
            msg.setText(msgBuilder.toString());
            respError.setMessage(Arrays.asList(msg));			
			
			throw new EssRestSystemErrorException(respError);
		}
	}
	
    private List<String> validateInputs(String transactionId, String applicationId, String customerId, String roleId) {
		
		
		List<String> missingFieldsList = new ArrayList<String>();
		
		/**
		 * missing applicationId
		 */
		if(StringUtils.isEmpty(applicationId)) {
			missingFieldsList.add("applicationId is missing from the request.");
		}
		
		/**
		 * missing customerId
		 */
		if(StringUtils.isEmpty(customerId)) {
			missingFieldsList.add("customerId is missing from the request.");
		}
		
		/**
		 * missing transactionId
		 */
		if(StringUtils.isEmpty(transactionId)) {
			missingFieldsList.add("transactionId is missing from the request.");
		}
		
		/**
		 * missing roleId
		 */
		if(StringUtils.isEmpty(roleId)) {
			missingFieldsList.add("roleId is missing from the request.");
		}		
				
		return missingFieldsList;
	}
}
