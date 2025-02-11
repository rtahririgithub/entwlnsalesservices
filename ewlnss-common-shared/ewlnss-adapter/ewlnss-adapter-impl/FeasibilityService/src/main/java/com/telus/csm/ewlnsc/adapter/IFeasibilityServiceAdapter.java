package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IFeasibilityServiceAdapter extends IAdapterBase{

	public CheckProductFeasibilityAdapterResponse checkProductFeasibility(CheckProductFeasibilityAdapterRequest request);
	
}
