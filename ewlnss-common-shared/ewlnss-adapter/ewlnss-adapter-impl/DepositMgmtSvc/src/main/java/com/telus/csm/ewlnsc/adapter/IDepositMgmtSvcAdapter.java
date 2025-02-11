package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.CancelDepositAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CancelDepositAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CreateDepositAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateDepositAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IDepositMgmtSvcAdapter extends IAdapterBase {

	/**
	 * Creates a deposit invoice in the Enabler-AR for the input pay channel and deposit context. 
	 * Returns the corresponding invoiceId and request amount. 
	 * If the deposit already exists for such pay channel and context then simply returns the corresponding invoiceId.
	 * 
	 * @param request
	 * @return
	 * @
	 */
	public CreateDepositAdapterResponse createDeposit(final CreateDepositAdapterRequest request);

	/**
	 * Cancels the deposit corresponding to input invoice. 
	 * If the deposit does not exist or is already paid then throws an exception
	 * 
	 * @param request
	 * @return
	 */
	public CancelDepositAdapterResponse cancelDeposit(final CancelDepositAdapterRequest request);

}
