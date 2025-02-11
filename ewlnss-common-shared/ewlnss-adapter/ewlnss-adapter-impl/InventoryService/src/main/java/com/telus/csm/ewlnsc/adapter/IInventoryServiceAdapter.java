package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.ReserveTelephoneNumberAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.ReserveTelephoneNumberAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IInventoryServiceAdapter extends IAdapterBase {

	public ReserveTelephoneNumberAdapterResponse reserveTelephoneNumber(ReserveTelephoneNumberAdapterRequest request);

}
