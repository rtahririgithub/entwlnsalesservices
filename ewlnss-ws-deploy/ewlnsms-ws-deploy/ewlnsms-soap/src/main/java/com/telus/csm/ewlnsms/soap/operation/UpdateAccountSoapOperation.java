package com.telus.csm.ewlnsms.soap.operation;

import com.telus.csm.ewlnsms.core.domain.UpdateAccountCoreRequest;
import com.telus.csm.ewlnsms.core.domain.UpdateAccountCoreResponse;
import com.telus.csm.ewlnsms.core.operation.UpdateAccountCoreOperation;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateAccountResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.UpdateAccountType;

public class UpdateAccountSoapOperation {

	/**
	 * Entry point in this SOAP operation.
	 * 
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	public UpdateAccountResponseType execute(final UpdateAccountType request) {
		final UpdateAccountResponseType result = new UpdateAccountResponseType();

		final UpdateAccountCoreOperation coreOperation = new UpdateAccountCoreOperation();

		final UpdateAccountCoreRequest requestBO = new UpdateAccountCoreRequest();

		requestBO.setBillingAccount(request.getBillingAccount());
		requestBO.setBillNotification(request.getBillNotification());
		requestBO.setBusinessEntityInfo(request.getBusinessEntityInfo());
		requestBO.setCustomer(request.getCustomer());
		requestBO.setOperationHeader(request.getOperationHeader());

		final UpdateAccountCoreResponse respVO = coreOperation.execute(requestBO);

		if (respVO != null) {
			result.setSucessfulUpdateInd(respVO.isSucessfulUpdateInd());
			result.setMessageList(respVO.getMessageList());
		}

		return result;
	}

}
