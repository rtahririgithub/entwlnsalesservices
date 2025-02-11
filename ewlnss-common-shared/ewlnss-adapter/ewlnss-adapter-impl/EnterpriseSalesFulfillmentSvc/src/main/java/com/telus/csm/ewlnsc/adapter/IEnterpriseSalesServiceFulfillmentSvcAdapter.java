package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.esfs.domain.CreateOrderItemAdapterRequest;
import com.telus.csm.ewlnsc.adapter.esfs.domain.CreateOrderItemAdapterResponse;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IEnterpriseSalesServiceFulfillmentSvcAdapter extends IAdapterBase {
	
	public CreateOrderItemAdapterResponse createOrderItem(final CreateOrderItemAdapterRequest param);

	public UpdateSalesOrderAdapterResponse updateSalesOrder(UpdateSalesOrderAdapterRequest param);
	
}
