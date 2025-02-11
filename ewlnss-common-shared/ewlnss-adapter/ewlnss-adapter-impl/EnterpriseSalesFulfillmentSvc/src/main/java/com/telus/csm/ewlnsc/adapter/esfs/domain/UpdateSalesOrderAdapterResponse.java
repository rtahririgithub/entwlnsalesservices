package com.telus.csm.ewlnsc.adapter.esfs.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateSalesOrderResponse;

public class UpdateSalesOrderAdapterResponse extends AdapterResponseBase{

	UpdateSalesOrderResponse updateSalesOrderResponse;

	public UpdateSalesOrderResponse getUpdateSalesOrderResponse() {
		return updateSalesOrderResponse;
	}

	public void setUpdateSalesOrderResponse(UpdateSalesOrderResponse updateSalesOrderResponse) {
		this.updateSalesOrderResponse = updateSalesOrderResponse;
	}
	
}
