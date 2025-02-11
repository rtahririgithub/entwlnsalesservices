package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.delegate.AssignedAndPendingProductDelegate2;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseProxyVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnssproxy.rest.transformer.GetAssignedAndPendingProductsTransformer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class GetAssignedAndPendingProductsRestOperation {
	
	private static LoggerUtil logger = LoggerUtil.getLogger(GetAssignedAndPendingProductsRestOperation.class);
	
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	public GetAssignedAndPendingProductResponseProxyVO execute(String transactionId, String customerId, String billingAccountNumber, String originatorApplicationId, String brandId, String correlationId,
			String salesRepId, String salesRepAssociatedChannelOutletId) {
		
		GetAssignedAndPendingProductResponseProxyVO response = null;
		try {
			List<String> messageList = validateInputs(transactionId, customerId, billingAccountNumber);
			
			if(!messageList.isEmpty()){
				Error err = new Error();
				err.setCode(Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()));
				err.setReason("Mandatory input is missing.");
				err.setTraceId(transactionId);
				err.setStatus("ERROR");
				err.setMessage(GetAssignedAndPendingProductsTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()))); 
				throw new EssRestErrorException(err);
			}   
			AssignedAndPendingProductDelegate2 delegate = new AssignedAndPendingProductDelegate2(wirelineSalesEJBAdapter);
			
			GetAssignedAndPendingProductResponseVO result = delegate.execute(GetAssignedAndPendingProductsTransformer.transform(transactionId, customerId, billingAccountNumber, originatorApplicationId,
					brandId, correlationId, salesRepId, salesRepAssociatedChannelOutletId), null);
			 
			if (result == null || result.isMsgHasError() == true) {
				messageList.add("Unable to get the assigned and pending products.");
				Error err = new Error();
				err.setCode(Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()));
				err.setReason("Unable to get the assigned and pending products.");
				err.setTraceId(transactionId);
				err.setStatus("ERROR");
				err.setMessage(GetAssignedAndPendingProductsTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()))); 
				throw new EssRestErrorException(err);
			}
			
			response = GetAssignedAndPendingProductsTransformer.transformToProxyResponse(result);
						
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
	
	private List<String> validateInputs(String transactionId, String customerId, String billingAccountNumber) {
		
		List<String> missingFieldsList = new ArrayList<String>();
		
		/**
		 * missing applicationId
		 */
		if(StringUtils.isEmpty(transactionId)) {
			missingFieldsList.add("transactionId is missing from the request.");
		}
		/**
		 * missing customerId
		 */
		if(StringUtils.isEmpty(customerId)) {
			missingFieldsList.add("customerId is missing from the request.");
		}
		/**
		 * missing billingAccountNumber
		 */
		if(billingAccountNumber != null && StringUtils.isEmpty(billingAccountNumber)) {
			missingFieldsList.add("billingAccountNumber is missing from the request.");
		}
				
		return missingFieldsList;
	}
}
