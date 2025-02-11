package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateProductConfigAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateProductConfigAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IWLNOrderShoppingCartRestSvcAdapter extends IAdapterBase {

	public CreateOrderAdapterResponse createOrder(final CreateOrderAdapterRequest param);
	
	public SubmitOrderAdapterResponse submitOrder(final SubmitOrderAdapterRequest param);
	
	public ValidateProductConfigAdapterResponse validateProductConfig(final ValidateProductConfigAdapterRequest param);
	
	public UpdateOrderAdapterResponse updateOrder(UpdateOrderAdapterRequest param);
	
}
