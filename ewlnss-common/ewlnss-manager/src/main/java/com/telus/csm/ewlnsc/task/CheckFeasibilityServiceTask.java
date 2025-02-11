package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.delegate.CheckProductFeasibilityDelegate;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;

/**
 * @author Jose.Mena
 *
 */
public class CheckFeasibilityServiceTask extends TaskBase {

	private CheckProductFeasibilityAdapterResponse result;
	private SalesOfferCommonVO commonVO;

	public CheckProductFeasibilityAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public CheckFeasibilityServiceTask(SalesOfferCommonVO commonVO) {
		this.commonVO = commonVO;
	}

	@Override
	protected void execute() {
		result = new CheckProductFeasibilityDelegate().checkProductFeasibility(commonVO);
	}

}