package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.helper.GetAccessoryOfferListHelper;

public class GetAvailableAccessoryTask extends TaskBase {

	private GetOffersRequestVO offersRequestVO;
	private GetOffersResponseVO result;

	public GetAvailableAccessoryTask(GetOffersRequestVO offersRequestVO) {
		this.offersRequestVO = offersRequestVO;
	}

	@Override
	protected void execute() {
		GetAccessoryOfferListHelper accessoryOfferHelper = new GetAccessoryOfferListHelper();
		result = accessoryOfferHelper.execute(offersRequestVO);
	}

	public GetOffersResponseVO getResult() {
		return result;
	}
}