package com.telus.csm.ewlnsc.adapter.esfs.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateWirelessSalesOrderType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class UpdateSalesOrderAdapterRequest extends AdapterRequestBase{

	private OperationHeader operationHeader;
	private UpdateWirelessSalesOrderType createUpdateWirelessSalesOrderType;

	public OperationHeader getOperationHeader() {
		return operationHeader;
	}

	public void setOperationHeader(OperationHeader operationHeader) {
		this.operationHeader = operationHeader;
	}

	public UpdateWirelessSalesOrderType getCreateUpdateWirelessSalesOrderType() {
		return createUpdateWirelessSalesOrderType;
	}

	public void setCreateUpdateWirelessSalesOrderType(UpdateWirelessSalesOrderType createUpdateWirelessSalesOrderType) {
		this.createUpdateWirelessSalesOrderType = createUpdateWirelessSalesOrderType;
	}



}
