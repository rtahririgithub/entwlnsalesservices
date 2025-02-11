package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.domain.schemasclone.GetAvailableSweetenerOfferListResponseVO;
import com.telus.csm.ewlnsc.domain.schemasclone.GetAvailableSweetenerOfferListRequestVO;
import com.telus.csm.ewlnsc.helper.GetAvailableSweetenerOfferListSchemaIndependentHelper;

import commonj.work.Work;

public class GetAvailableSweetenerOfferListTask extends TaskBase implements Work {

	private GetAvailableSweetenerOfferListRequestVO requestVO;
	private GetAvailableSweetenerOfferListResponseVO offersResponseVO;

	public GetAvailableSweetenerOfferListTask(GetAvailableSweetenerOfferListRequestVO requestVO) {
		this.requestVO = requestVO;
	}

	@Override
	protected void execute() {
		GetAvailableSweetenerOfferListSchemaIndependentHelper getAvailableSweetenerOfferListSchemaIndependentHelper = new GetAvailableSweetenerOfferListSchemaIndependentHelper();

		offersResponseVO = getAvailableSweetenerOfferListSchemaIndependentHelper.execute(requestVO);
	}

	public GetAvailableSweetenerOfferListResponseVO getResult() {
		return this.offersResponseVO;
	}
}