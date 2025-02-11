package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterRequest;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IExchangeForborneStatusSvcAdapter extends IAdapterBase {
	
	public ExchangeForborneStatusAdapterResponse getForborneStatusByNpaNxxList(final ExchangeForborneStatusAdapterRequest param);
	
}
