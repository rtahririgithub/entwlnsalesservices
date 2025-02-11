package com.telus.csm.ewlnssproxy.rest.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.ValidationMessage;
import com.telus.csm.ewlnssproxy.cpqb.domain.ProductQualification;
import com.telus.csm.ewlnssproxy.cpib.domain.Message;


public class GetAvailableProductsTransformer extends BaseTransformer{

	public static List<ProductQualification> transformGetAvailableProductsResponse(
			GetProductQualificationAdapterResponse rsp) throws Exception {

		List<ProductQualification> productList = new ArrayList<ProductQualification>();

		if (rsp.getProductQualificationList() != null && rsp.getProductQualificationList().size() > 0) {
			for (com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification product : rsp
					.getProductQualificationList()) {
				productList.add(transformResponse(product, ProductQualification.class));
			}
		}

		return productList;
	}

	public static GetProductQualificationAdapterRequest createProductQualificationRequest(String transactionId,
			String applicationId, String customerId, String accountId, String addressId, String provinceCd, String city,
			String qualByServiceId, String correlationId, String salesRepId, String channelOutletId, String isRefreshInd ) {

		GetProductQualificationAdapterRequest request = new GetProductQualificationAdapterRequest();
		request.setSalesTransactionId(transactionId);
		request.setAddressId(addressId);
		request.setChannelOutletId(channelOutletId);
		request.setCity(city);
		request.setCorrelationId(correlationId);
		request.setProvinceCd(provinceCd);
		request.setQualByServiceId(Boolean.valueOf(qualByServiceId));
		request.setSalesRepId(salesRepId);
		request.setApplicationId(applicationId);
		request.setCustomerId(customerId);
		request.setBillingAccountNumber(accountId);
		if (StringUtils.isNotBlank(isRefreshInd) && isRefreshInd.equalsIgnoreCase("true")) { //QC79739
				request.setRefreshCache(true);
		} else {
				request.setRefreshCache(false);			
		}

		return request;
	}
	
	public static List<Message> transformMessage(List<ValidationMessage> messages) {
		List<Message> msgList = null;
		if (messages != null && messages.size() > 0) {

			msgList = new ArrayList<Message>();

			for (ValidationMessage vMessage : messages) {
				Message msg = new Message();
				msg.setCode(vMessage.getMessageCd());
				msg.setText(vMessage.getMessageTxt());
				msgList.add(msg);
			}
		}
		return msgList;
	}
}
