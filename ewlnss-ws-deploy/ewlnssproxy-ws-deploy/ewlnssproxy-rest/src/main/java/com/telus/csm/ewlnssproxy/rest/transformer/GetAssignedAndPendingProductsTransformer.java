package com.telus.csm.ewlnssproxy.rest.transformer;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseProxyVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class GetAssignedAndPendingProductsTransformer extends BaseTransformer {
	
	private static Logger logger = Logger.getLogger(GetAssignedAndPendingProductsTransformer.class);

	public static GetAssignedAndPendingProductRequestVO transform(String transactionId, String customerId, String billingAccountNumber, String originatorApplicationId, String brandId, 
			String correlationId, String salesRepId, String salesRepAssociatedChannelOutletId) {
		
		GetAssignedAndPendingProductRequestVO request = new GetAssignedAndPendingProductRequestVO();
		request.setCustomerId(customerId);
		request.setBillingAccountNumber(billingAccountNumber);
		OperationHeader header = new OperationHeader();
		if (!StringUtils.isEmpty(originatorApplicationId)){
			header.setOriginatorApplicationId(Long.valueOf(originatorApplicationId));
		}
		header.setSalesTransactionId(transactionId);
		
		request.setOperationHeader(header);
		
		return request;
	}
	
	public static GetAssignedAndPendingProductResponseProxyVO transformToProxyResponse(GetAssignedAndPendingProductResponseVO source) throws Exception {
		logger.info("inside transformToProxyResponse()");
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			GetAssignedAndPendingProductResponseProxyVO response = mapper.convertValue(source, GetAssignedAndPendingProductResponseProxyVO.class);
			
			//QC74800 convert commitmentPeriodStartDt format to "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
			convertDateFormatInResponse(response);
			
			if (source.getGetProductsByCustomerIdAdapterResponse()!=null && source.getGetProductsByCustomerIdAdapterResponse().getProducts()!=null
					&& !source.getGetProductsByCustomerIdAdapterResponse().getProducts().isEmpty()) {
				List<Product> customerProducts = new ArrayList<Product>();
				customerProducts.addAll(source.getGetProductsByCustomerIdAdapterResponse().getProducts());
				response.setCustomerProducts(customerProducts);
			}
			return response;
		} catch (Exception e) {
			throw e;
		}

	}
}
