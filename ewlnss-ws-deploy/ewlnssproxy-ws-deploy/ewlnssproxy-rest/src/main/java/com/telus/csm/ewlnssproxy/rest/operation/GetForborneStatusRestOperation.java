package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnsc.adapter.IExchangeForborneStatusSvcAdapter;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnssproxy.rest.transformer.GetForborneStatusTransformer;
import org.apache.commons.lang.StringUtils;

public class GetForborneStatusRestOperation {
	
	private static LoggerUtil logger = LoggerUtil.getLogger(GetForborneStatusRestOperation.class);
	
	public String execute(String transactionId, String customerType, List<String> npaNxxList) {
		
		try {
			List<String> messageList = validateInputs(transactionId, customerType);
			
			if(!messageList.isEmpty()){
				Error err = new Error();
				err.setCode(Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()));
				err.setReason("Mandatory input is missing.");
				err.setTraceId(transactionId);
				err.setStatus("ERROR");
				err.setMessage(GetForborneStatusTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()))); 
				throw new EssRestErrorException(err);
			}   
			IExchangeForborneStatusSvcAdapter adapter = AdapterFactory.getAdapter(IExchangeForborneStatusSvcAdapter.class);
			ExchangeForborneStatusAdapterResponse result = adapter.getForborneStatusByNpaNxxList(GetForborneStatusTransformer.transform(transactionId, customerType, npaNxxList));
			 
			if (result.getAdapterResponseMessage() != null && "EXCEPTION".equals(result.getAdapterResponseMessage().getMessageType())) {
				Error err = new Error();
				err.setCode(Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()));
				err.setReason("Exceptions happened when calling getForborneStatusByNpaNxxList");
				err.setTraceId(transactionId);
				err.setStatus("ERROR");
				err.setMessage(GetForborneStatusTransformer.getResponseMessageFromException(result.getAdapterResponseMessage().getException())); 
				throw new EssRestSystemErrorException(err);
			}
			
			if (result.getExchangeForborneStatusList() == null || result.getExchangeForborneStatusList().isEmpty()) {
				messageList.add("No valid response when calling downstream for getForborneStatusByNpaNxxList");
				Error err = new Error();
				err.setCode(Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()));
				err.setReason("No valid response when calling downstream for getForborneStatusByNpaNxxList");
				err.setTraceId(transactionId);
				err.setStatus("ERROR");
				err.setMessage(GetForborneStatusTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()))); 
				throw new EssRestErrorException(err);
			}
			
			String response = GetForborneStatusTransformer.transformResponseWithDateFormat(result.getExchangeForborneStatusList(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			
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
	
	private List<String> validateInputs(String transactionId, String customerType) {
		
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
		if(StringUtils.isEmpty(customerType)) {
			missingFieldsList.add("customerType is missing from the request.");
		}
				
		return missingFieldsList;
	}
}
