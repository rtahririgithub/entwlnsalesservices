package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.adapter.IWLNCreditEligibilityProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnssproxy.ccb.domain.ConsumerCreditEligibility;
import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnssproxy.rest.transformer.GetCustomerCreditEligibilityTransformer;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo;

public class GetCustomerCreditEligibilityOperation {
	private static LoggerUtil logger = LoggerUtil.getLogger(GetCustomerCreditEligibilityOperation.class);

	public ConsumerCreditEligibility execute(String transactionId, String customerId, boolean refreshCacheInd, AuditInfo auditInfo) {
		
		
		logger.info("GetCustomerCreditEligibilityOperation......Starting Parameter verify", transactionId);
		try {
			List<String> messageList = validateInputs(transactionId, customerId);

			if(!messageList.isEmpty()){
				Error err = new Error();
				err.setStatus(Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()));
				err.setCode(Integer.toString(Response.Status.BAD_REQUEST.getStatusCode()));
				err.setReason(messageList.toString());
				err.setTraceId(transactionId);
				err.setMessage(GetCustomerCreditEligibilityTransformer.transformResponseMessages(messageList,
						Integer.toString(Response.Status.BAD_REQUEST.getStatusCode())));
				throw new EssRestErrorException(err);
			}
			
			logger.info("Calling SOAP IWLNCreditEligibilityProxyServiceAdapter.getCreditEligibility", transactionId);
			GetCreditEligibilityAdapterRequest request = new GetCreditEligibilityAdapterRequest();
			request.setAuditInfo(auditInfo);
			request.setCustomerId(customerId);
			request.setSalesTransactionId(transactionId);
			request.setRefreshCache(refreshCacheInd);
			GetCreditEligibilityAdapterResponse response = AdapterFactory
					.getAdapter(IWLNCreditEligibilityProxyServiceAdapter.class).getCreditEligibility(request);
			
			logger.info("Verify IWLNCreditEligibilityProxyServiceAdapter.getCreditEligibility response", transactionId);

			// has error
			if (null==response) {
				messageList.add("No eligible credit found.");
				Error err = new Error();
				err.setStatus(Integer.toString(Response.Status.NOT_FOUND.getStatusCode()));
				err.setCode(Integer.toString(Response.Status.NOT_FOUND.getStatusCode()));
				err.setReason(Response.Status.NOT_FOUND.getReasonPhrase());
				err.setTraceId(transactionId);
				err.setMessage(GetCustomerCreditEligibilityTransformer.transformResponseMessages(messageList,
						Integer.toString(Response.Status.NOT_FOUND.getStatusCode())));
				throw new EssRestErrorException(err);				
			} else if (response.isMsgHasError()) {
				if (StringUtils.equals("WLNCreditEligibilityProxyServiceAdapter_WLNP-GEN-012",
						response.getAdapterResponseMessage().getMessageCode())
						&& StringUtils.contains(response.getAdapterResponseMessage().getRelatedMessageList().get(0)
								.getRelatedErrorMessageTxt(), "Unable to get credit profile for customer")) { // translate to 404
					messageList.add(response.getAdapterResponseMessage().getRelatedMessageList().get(0)
							.getRelatedErrorMessageTxt());
					Error err = new Error();
					err.setStatus(Integer.toString(Response.Status.NOT_FOUND.getStatusCode()));
					err.setCode(Integer.toString(Response.Status.NOT_FOUND.getStatusCode()));
					err.setReason(response.getAdapterResponseMessage().getRelatedMessageList().get(0)
							.getRelatedErrorMessageTxt());
					err.setTraceId(transactionId);
					err.setMessage(GetCustomerCreditEligibilityTransformer.transformResponseMessages(messageList,
							new Integer(Response.Status.NOT_FOUND.getStatusCode()).toString()));
					throw new EssRestErrorException(err);
				}
				String dsErrorMsg = CollectionUtils.isNotEmpty(response.getAdapterResponseMessage().getRelatedMessageList())?response.getAdapterResponseMessage().getRelatedMessageList().get(0)
						.getRelatedErrorMessageTxt():"Downstream call error.";
				messageList.add(dsErrorMsg);
				Error err = new Error();
				err.setStatus(Integer.toString(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
				err.setCode(Integer.toString(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
				err.setReason(dsErrorMsg);
				err.setTraceId(transactionId);
				err.setMessage(GetCustomerCreditEligibilityTransformer.transformResponseMessages(messageList,
						Integer.toString(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())));
				throw new EssRestErrorException(err);				
			}
			
			logger.info("Transform adapter response to bridge API", transactionId);
			return GetCustomerCreditEligibilityTransformer.transform(response);
			
		} catch (EssRestException e) {
			throw e;
		} catch (Exception ex) {
			logger.error("error","Unknown Exception", ex.getMessage(), ex);
			Error respError = new Error();			
						
            respError.setStatus(Integer.toString(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
            respError.setCode(Integer.toString(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
            respError.setReason(ex.getMessage());
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
		} finally {
			logger.info("END getCustomerCreditEligibility", transactionId);
		}
		
	}
	
	private List<String> validateInputs(String transactionId, String customerId) {
		
		List<String> invalidFields = new ArrayList<String>(0);
		
		/**
		 * missing transactionId
		 */
		if(StringUtils.isBlank(transactionId)) {
			invalidFields.add("transactionId is missing from the request.");
		}
		
		/**
		 * 
		 */
		if(StringUtils.isBlank(customerId)) {//missing customerId
			invalidFields.add("customerId is missing from the request.");
		} else if (!StringUtils.isNumeric(customerId) || 9 < StringUtils.length(customerId)){
			invalidFields.add("customerId is invalid.");
		}
		
		return invalidFields;
	}
}
