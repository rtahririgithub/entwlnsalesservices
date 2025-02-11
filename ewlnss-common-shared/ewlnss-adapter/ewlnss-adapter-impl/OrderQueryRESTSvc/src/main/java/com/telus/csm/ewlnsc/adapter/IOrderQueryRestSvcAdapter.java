package com.telus.csm.ewlnsc.adapter;

import java.util.List;

import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IOrderQueryRestSvcAdapter extends IAdapterBase{

	public GetProductsByCustomerIdAdapterResponse getProductsByCustomerId(final GetProductsByCustomerIdAdapterRequest param);
	
	public ProductOrder getOrderSummaryByOrderId(String orderId, String txnId, String agentUid);

	public GetOrderSummaryByOrderIdAdapterResponse getOrderSummaryByOrderId(final GetOrderSummaryByOrderIdAdapterRequest param);
	
	public List<ProductOrder> searchOrdersByCustomerId(long customerId, List<String> statusList, String agentUid, String txnId);
}