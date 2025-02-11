package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteRequest;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IWLNOrderChargingRestSvcAdapter extends IAdapterBase {

	public CreateInvoiceAdapterResponse createInvoice(final CreateInvoiceAdapterRequest param);
	
	public QuoteResponse getQuote(final QuoteRequest param);
	
	public GetDepositInfoResponse getDepositInfo(final GetDepositInfoRequest param);
	
}
