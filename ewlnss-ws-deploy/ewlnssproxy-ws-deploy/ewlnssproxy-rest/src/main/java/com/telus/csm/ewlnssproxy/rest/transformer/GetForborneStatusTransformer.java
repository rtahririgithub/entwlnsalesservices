package com.telus.csm.ewlnssproxy.rest.transformer;

import java.util.List;
import org.apache.log4j.Logger;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterRequest;

public class GetForborneStatusTransformer extends BaseTransformer {
	
	private static Logger logger = Logger.getLogger(GetForborneStatusTransformer.class);

	public static ExchangeForborneStatusAdapterRequest transform(String transactionId, String customerType, List<String> npaNxxList) {
		
		ExchangeForborneStatusAdapterRequest request = new ExchangeForborneStatusAdapterRequest();
		
		request.setSalesTransactionId(transactionId);
		request.setCustomerType(customerType);
		request.setNpaNxxList(npaNxxList);
		
		return request;
	}
}
