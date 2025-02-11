package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.ISalesCustomerInfoRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnssproxy.cpqb.domain.ProductQualification;
import com.telus.csm.ewlnssproxy.rest.transformer.GetAvailableProductsTransformer;

public class GetAvailableProductsOperation extends BaseOperation {
	private Logger logger = Logger.getLogger(GetAvailableProductsOperation.class);

	@Override
	public Logger getLogger() {
		return logger;
	}

	public List<ProductQualification> execute(String transactionId, String applicationId, String customerId,
			String accountId, String addressId, String provinceCd, String city, String qualByServiceId,
			String correlationId, String salesRepId, String channelOutletId, String isRefreshInd ) throws Exception {

		logger.info("Starting Parameter verify");
		List<String> messageList = validateInputs(transactionId, applicationId);

		// Parameters check
		if (!messageList.isEmpty()) {
			Error err = handleProxyError(Response.Status.BAD_REQUEST,
					"transactionId, applicationId, customerId and accountId are required", null, transactionId,
					GetAvailableProductsTransformer.transformResponseMessages(messageList, "ESS_WLN_PROD_INVE_0001"),
					null);
			throw new EssRestErrorException(err);
		}

		logger.info("Creating GetProductQualificationAdapterRequest");
		GetProductQualificationAdapterRequest request = GetAvailableProductsTransformer
				.createProductQualificationRequest(transactionId, applicationId, customerId, accountId, addressId,
						provinceCd, city, qualByServiceId, correlationId, salesRepId, channelOutletId, isRefreshInd );  // QC79739 new refreshIndicator

		logger.info("Looking for ISalesCustomerInfoRestSvcAdapter");
		ISalesCustomerInfoRestSvcAdapter adapter = AdapterFactory.getAdapter(ISalesCustomerInfoRestSvcAdapter.class);

		logger.info("Calling ISalesCustomerInfoRestSvcAdapter.getProductQualification");
		GetProductQualificationAdapterResponse response = adapter.getProductQualification(request);

		logger.info("Verify ISalesCustomerInfoRestSvcAdapter.getProductQualification response");
		// Adapter has error
		if (response.hasError()) {
			logger.info("Received error from Adapter response");
			Error err = handleProxyError(Response.Status.fromStatusCode(response.getResponseStatus().getStatusCd()),
					response.getResponseStatus().getStatusTxt(), null, transactionId,
					GetAvailableProductsTransformer.transformMessage(response.getResponseStatus().getMessages()), null);
			throw new EssRestSystemErrorException(err);
		}

		logger.info("Transform adapter response to bridge API");
		// transform response
		List<ProductQualification> result = GetAvailableProductsTransformer
				.transformGetAvailableProductsResponse(response);

		return result;
	}

	private List<String> validateInputs(String transactionId, String applicationId) {

		List<String> missingFieldsList = new ArrayList<String>();

		/**
		 * missing transactionId
		 */
		if (StringUtils.isEmpty(transactionId)) {
			missingFieldsList.add("transactionId is missing from the request.");
			logger.info("transactionId is missing from the request");
		}

		/**
		 * missing applicationId
		 */
		if (StringUtils.isEmpty(applicationId)) {
			missingFieldsList.add("applicationId is missing from the request.");
			logger.info("applicationId is missing from the request");
		}

		return missingFieldsList;
	}
}
