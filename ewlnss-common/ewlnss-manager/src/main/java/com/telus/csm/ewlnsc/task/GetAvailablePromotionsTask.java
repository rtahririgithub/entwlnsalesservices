package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.domain.PromotionsVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.helper.PromotionQualificationHelper;

public class GetAvailablePromotionsTask extends TaskBase {

	private ShoppingCartContextVO promotionRequestVO;
	private PromotionsVO result;
	private boolean isPostTask;
	private boolean promoInd;

	public GetAvailablePromotionsTask(final ShoppingCartContextVO promotionRequestVO, final boolean isPostTask, final boolean promoInd) {
		this.promotionRequestVO = promotionRequestVO;
		this.promoInd = promoInd;
	}

	@Override
	protected void execute() {
		PromotionQualificationHelper pqHelper = new PromotionQualificationHelper();
		result = pqHelper.execute(promotionRequestVO, isPostTask, promoInd);
	}

	public PromotionsVO getResult() {
		return result;
	}

	public boolean isPostTask() {
		return isPostTask;
	}
}