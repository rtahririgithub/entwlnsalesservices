package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositRequest;
import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IOrderDepositCalculatorProxySvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param UpdateOwnerIdentityCredentialRequestDO
	 * @return
	 */
	public CalculateDepositResponse calculateDeposit(final CalculateDepositRequest createCustomerRequestDO);

}
