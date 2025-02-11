package com.telus.csm.ewlnsms.soap.operation;

import com.telus.csm.ewlnsms.core.domain.CreateWLNOrderCoreRequest;
import com.telus.csm.ewlnsms.core.domain.CreateWLNOrderCoreResponse;
import com.telus.csm.ewlnsms.core.operation.CreateWLNOrderCoreOperation;
import com.telus.csm.ewlnsms.core.transformer.CreateWLNOrderTransformer;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelineSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelineSalesOrderResponseType;

/**
 * SOAP operation for create wireline order.
 * 
 * @author x145592
 *
 */
public class CreateWLNOrderSoapOperation {

	/**
	 * Entry point in this SOAP operation.
	 * 
	 * @param request
	 * @return
	 * @throws Throwable 
	 */
	public CreateWirelineSalesOrderResponseType execute(final CreateWirelineSalesOrder request) {
		final CreateWirelineSalesOrderResponseType cwsoRespType = new CreateWirelineSalesOrderResponseType();
		
		final CreateWLNOrderCoreOperation coreOperation = new CreateWLNOrderCoreOperation();
		
		final CreateWLNOrderCoreRequest requestBO = CreateWLNOrderTransformer.transform(request);
		
		final CreateWLNOrderCoreResponse respVO = coreOperation.execute(requestBO);
		
		if (respVO != null) {
			cwsoRespType.setSalesTransactionId(request.getOperationHeader().getSalesTransactionId());
			cwsoRespType.setSalesId(respVO.getSalesId());
			cwsoRespType.setSuccessfulProcessIndicator(respVO.isProcessInd());
			//result.setMessageList(respVO.getMessageList());
		}
		
		return cwsoRespType;
	}

	
}
