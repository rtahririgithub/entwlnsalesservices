package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.CreateBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateBillingAccountAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.RegisterBillDeliveryMethodAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.RegisterBillDeliveryMethodAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.UpdateBillDeliveryAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateBillDeliveryAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IConsumerBillingAccountManagementServiceAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param requestDO
	 * @return
	 */
	public CreateBillingAccountAdapterResponse createBillingAccount(final CreateBillingAccountAdapterRequest requestDO);

	public GetBillingAccountAdapterResponse getBillingAccount(final GetBillingAccountAdapterRequest requestDO);
	
	public RegisterBillDeliveryMethodAdapterResponse registerBillDeliveryMethod(final RegisterBillDeliveryMethodAdapterRequest requestDO);
	
	//NWLN-10684 - update account bill media type and ebill notification
	public UpdateBillDeliveryAdapterResponse updateBillDelivery(final UpdateBillDeliveryAdapterRequest requestDO);
}
