package com.telus.csm.ewlnsc.adapter.esfs.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderItem;

public class CreateOrderItemAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
    
	private CreateSalesOrderItem createOrderItemRequest;
	
    
	public CreateSalesOrderItem getCreateOrderItemRequest() {
		return createOrderItemRequest;
	}


	public void setCreateOrderItemRequest(CreateSalesOrderItem createOrderItemRequest) {
		this.createOrderItemRequest = createOrderItemRequest;
	}

}
