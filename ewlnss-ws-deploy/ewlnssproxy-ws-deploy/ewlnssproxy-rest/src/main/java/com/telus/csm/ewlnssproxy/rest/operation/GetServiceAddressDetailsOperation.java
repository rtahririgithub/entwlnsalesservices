package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.adapter.IServiceAddressMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnssproxy.csab.domain.ServiceAddress;
import com.telus.csm.ewlnssproxy.rest.transformer.BaseTransformer;
import com.telus.csm.ewlnssproxy.rest.transformer.GetCustomerCreditEligibilityTransformer;
import com.telus.csm.ewlnssproxy.rest.transformer.GetServiceAddressDetailsTransformer;

public class GetServiceAddressDetailsOperation extends BaseOperation {
	
	public ServiceAddress execute(String transactionId, String serviceAddressId, String provinceCd) {
		
		List<String> messageList = validateInputs(transactionId, serviceAddressId);
		
		if(!messageList.isEmpty()){
			Error err = handleProxyError(Response.Status.BAD_REQUEST,
					"transactionId and serviceAddressId are required", null, transactionId,
					BaseTransformer.transformResponseMessages(messageList, "ESS_WLN_PROD_INVE_0001"),
					null);
			throw new EssRestErrorException(err);
		}
		
		com.telus.csm.ewlnssproxy.csab.domain.ServiceAddress response = null;
		
		IServiceAddressMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IServiceAddressMgmtSvcAdapter.class);
		
		GetServiceAddressDetailsAdapterResponse res = adapter.getServiceAddressDetails(GetServiceAddressDetailsTransformer.transform(transactionId, serviceAddressId, provinceCd));
		
		// has error
		if (null==res) {
			messageList.add("No service address details found.");
			Error err = handleProxyError(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getReasonPhrase(),
					Response.Status.NOT_FOUND.toString(), transactionId,
					BaseTransformer.transformResponseMessages(messageList,
							Integer.toString(Response.Status.NOT_FOUND.getStatusCode())),
					null);

			throw new EssRestErrorException(err);				
		} else if (res.isMsgHasError()) {// two levels of errors  
			if (null == res.getAdapterResponseMessage()) { // not ds exception, translate to 404
				String dsErrorMsg = StringUtils.defaultString(
						res.getResponse().getResponseMessageList().getResponseMessage().get(0).getMessageList().getMessage(),
						Response.Status.NOT_FOUND.getReasonPhrase());
				messageList.add(dsErrorMsg);
				Error err = handleProxyError(Response.Status.NOT_FOUND, dsErrorMsg,
						Response.Status.NOT_FOUND.toString(), transactionId,
						BaseTransformer.transformResponseMessages(messageList,
								res.getResponse().getResponseMessageList().getResponseMessage().get(0).getErrorCode()),
						null);
				throw new EssRestErrorException(err);
			}
			
			// downstream exception, translate to 500
			String adapterErrorMsg = StringUtils.defaultString(
					res.getAdapterResponseMessage().getLocaleSpecificMessageDescText(Locale.CANADA),
					"Downstream call error.");
			messageList.add(adapterErrorMsg);
			Error err = handleProxyError(Response.Status.INTERNAL_SERVER_ERROR, adapterErrorMsg,
					Response.Status.INTERNAL_SERVER_ERROR.toString(), transactionId,
					BaseTransformer.transformResponseMessages(messageList,
							res.getAdapterResponseMessage().getMessageCode()),
					null);
			throw new EssRestErrorException(err);				

		}
		
		response = GetServiceAddressDetailsTransformer.transform(res.getResponse().getAddress());
		
		return response;
	}
	

	private List<String> validateInputs(String transactionId, String serviceAddressId) {
		
		
		List<String> missingFieldsList = new ArrayList<String>();

		if(StringUtils.isEmpty(transactionId)) {
			missingFieldsList.add("transactionId is missing from the request.");
		}
		
		/**
		 * missing roleId
		 */
		if(StringUtils.isEmpty(serviceAddressId)) {
			missingFieldsList.add("service address ID is missing from the request.");
		}		
				
		return missingFieldsList;
	}
}
