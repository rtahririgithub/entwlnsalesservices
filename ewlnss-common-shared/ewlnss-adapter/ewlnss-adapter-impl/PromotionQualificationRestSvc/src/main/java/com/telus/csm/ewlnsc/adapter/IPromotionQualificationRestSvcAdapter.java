package com.telus.csm.ewlnsc.adapter;


import com.telus.csm.ewlnsc.adapter.domain.GetPromotionAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetPromotionAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IPromotionQualificationRestSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param sarch wireline promotions from TOM
	 * @return JsonObject
	 */
	public GetPromotionAdapterResponse getPromotion(GetPromotionAdapterRequest requestDO);
 
}
