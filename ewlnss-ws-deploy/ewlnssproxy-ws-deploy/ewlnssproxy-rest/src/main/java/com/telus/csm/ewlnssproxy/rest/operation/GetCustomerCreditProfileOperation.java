package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnssproxy.rest.transformer.GetCustomerCreditProfileTransformer;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo;

public class GetCustomerCreditProfileOperation extends BaseOperation {
	private Logger logger = Logger.getLogger(GetAvailableProductsOperation.class);
	
	final String UNABLE_TO_GET_CREDIT_PROFILE_MSG = "WLNCreditProfileMgmtSvcAdapter_WLNP-GEN-012"; 

	@Override
	public Logger getLogger() {
		return logger;
	}
	
	public String execute(String transactionId, String customerId, boolean refreshCacheInd, AuditInfo auditInfo) throws Exception {
	
		logger.info("GetCustomerCreditProfileOperation......Starting Parameter verify");
		List<String> messageList = validateInputs(transactionId, customerId);
		
		// Parameters check
		if (!messageList.isEmpty()) {
			Error err = handleProxyError(Response.Status.BAD_REQUEST,
					"transactionId and customerId are required", null, transactionId,
					GetCustomerCreditProfileTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.BAD_REQUEST.getStatusCode())),
					null);
			throw new EssRestErrorException(err);
		}		

		logger.info("Looking for IWLNCreditProfileMgmtSvcAdapter");
		IWLNCreditProfileMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IWLNCreditProfileMgmtSvcAdapter.class);
		
		logger.info("Creating GetCreditProfileByCustomerIdAdapterRequest");
		GetCreditProfileByCustomerIdAdapterRequest request = new GetCreditProfileByCustomerIdAdapterRequest();
		request.setAuditInfo(auditInfo);
		request.setCustomerId(customerId);
		request.setSalesTransactionId(transactionId);
		request.setRefreshCache(refreshCacheInd);
		
		logger.info("Calling IWLNCreditProfileMgmtSvcAdapter.getCreditProfileByCustomerId");
		GetCreditProfileByCustomerIdAdapterResponse response = adapter.getCreditProfileByCustomerId(request);
		
		logger.info("Verify IWLNCreditProfileMgmtSvcAdapter.getCreditProfileByCustomerId response");
		// Adapter has error
		if (response == null) {
			messageList.add("No eligible credit found.");
			logger.info("No customer credit profile found.");
			Error err = handleProxyError(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getReasonPhrase(),
					 null, transactionId,
					GetCustomerCreditProfileTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.NOT_FOUND.getStatusCode())),
					null);
			throw new EssRestErrorException(err);
		} else if (response.isMsgHasError() || response.getCreditProfile() == null) {
			if (StringUtils.equals(UNABLE_TO_GET_CREDIT_PROFILE_MSG,
					response.getAdapterResponseMessage().getMessageCode())
					&& StringUtils.contains(response.getAdapterResponseMessage().getRelatedMessageList().get(0)
							.getRelatedErrorMessageTxt(), "Unable to get credit profile for customer")) { // translate to 404
						 messageList.add(response.getAdapterResponseMessage().getRelatedMessageList().get(0)
						.getRelatedErrorMessageTxt());
					Error err = handleProxyError(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getReasonPhrase(),
						null, transactionId,
						GetCustomerCreditProfileTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.NOT_FOUND.getStatusCode())),
						null);
					throw new EssRestErrorException(err);
			}
			String dsErrorMsg = CollectionUtils.isNotEmpty(response.getAdapterResponseMessage().getRelatedMessageList())?response.getAdapterResponseMessage().getRelatedMessageList().get(0)
					.getRelatedErrorMessageTxt():"Downstream call error.";
			messageList.add(dsErrorMsg);
			Error err = handleProxyError(Response.Status.INTERNAL_SERVER_ERROR,Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
					null, transactionId,
					GetCustomerCreditProfileTransformer.transformResponseMessages(messageList, Integer.toString(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())),
					null);
			throw new EssRestErrorException(err);
		}
		
		logger.info("Transform adapter response to bridge API");
		// transform response to bridge API
		String result = GetCustomerCreditProfileTransformer.customerCreditProfileTransformResponse(response, "yyyy-MM-dd'T'HH:mm:ss'Z'");

		return result;
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
