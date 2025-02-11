package com.telus.csm.ewlnsc.adapter;


import com.telus.csm.ewlnsc.adapter.domain.GetCommerceShoppingCartAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCommerceShoppingCartAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface ICommerceShoppingCartMgmtRestSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param sarch wireline promotions from TOM
	 * @return JsonObject
	 */
	public GetCommerceShoppingCartAdapterResponse getCommerceShoppingCart(GetCommerceShoppingCartAdapterRequest requestDO);
 
}
