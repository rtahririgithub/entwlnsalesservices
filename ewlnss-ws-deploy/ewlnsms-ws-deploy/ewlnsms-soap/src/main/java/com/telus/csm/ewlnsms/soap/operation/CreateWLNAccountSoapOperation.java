package com.telus.csm.ewlnsms.soap.operation;

import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreRequest;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreResponse;
import com.telus.csm.ewlnsms.core.operation.CreateWLNAccountCoreOperation;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateWirelineAccountResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CreateWirelineAccountType;

/**
 * SOAP operation for create wireline account.
 * 
 * @author x145592
 *
 */
public class CreateWLNAccountSoapOperation {

	/**
	 * Entry point in this SOAP operation.
	 * 
	 * @param request
	 * @return
	 * @throws Throwable 
	 */
	public CreateWirelineAccountResponseType execute(final CreateWirelineAccountType request) {
		final CreateWirelineAccountResponseType result = new CreateWirelineAccountResponseType();
		
		final CreateWLNAccountCoreOperation coreOperation = new CreateWLNAccountCoreOperation();
		
		final CreateWLNAccountCoreRequest requestBO = new CreateWLNAccountCoreRequest();
		
		requestBO.setAccountInfo(request.getAccountInfo());
		if (request.getCustomerInfo() != null){
			requestBO.setAddress(request.getCustomerInfo().getMainAddress());
		}
		requestBO.setCustomerId(request.getCustomerId());
		requestBO.setCustomerInfo(request.getCustomerInfo());
		requestBO.setOperationHeader(request.getOperationHeader());
		
		final CreateWLNAccountCoreResponse respVO = coreOperation.execute(requestBO);
		
		if (respVO != null){
			result.setBillingAccountNumber(respVO.getBillingAccountNumber());
			result.setBillCycleCd(respVO.getBillCycleCd());
			result.setCustomerId(respVO.getCustomerId());
			result.setPayChannelNumber(respVO.getPayChannelNumber());
			result.setMatchingCustomerInfoList(respVO.getMatchingCustomerInfoList());
			result.setMessageList(respVO.getMessageList());
		}
		
		return result;
	}

	
}
