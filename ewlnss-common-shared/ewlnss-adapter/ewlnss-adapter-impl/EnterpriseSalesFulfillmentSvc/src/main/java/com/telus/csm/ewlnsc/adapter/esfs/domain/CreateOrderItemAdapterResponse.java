package com.telus.csm.ewlnsc.adapter.esfs.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderItemResponse;

public class CreateOrderItemAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;

	private CreateSalesOrderItemResponse createSalesOrderItemResponse;

	public CreateSalesOrderItemResponse getCreateSalesOrderItemResponse() {
		return createSalesOrderItemResponse;
	}

	public void setCreateSalesOrderItemResponse(CreateSalesOrderItemResponse createSalesOrderItemResponse) {
		this.createSalesOrderItemResponse = createSalesOrderItemResponse;
	}


	
}
