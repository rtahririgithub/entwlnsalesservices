package com.telus.csm.ewlnsc.adapter;


import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IFifaShoppingCartMgmtRestSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param shopping cart id
	 * @return JsonObject
	 */
	public GetFifaShoppingCartAdapterResponse getFifaShoppingCart(GetFifaShoppingCartAdapterRequest requestDO);
 
}
