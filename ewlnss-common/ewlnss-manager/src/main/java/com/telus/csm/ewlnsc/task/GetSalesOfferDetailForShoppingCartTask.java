package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.helper.GetSalesOfferDetailHelper;
import commonj.work.Work;

public class GetSalesOfferDetailForShoppingCartTask extends TaskBase implements Work {
	private GetSalesOfferDetailRequestVO  requestVO;

	private GetSalesOfferDetailResponseVO2   offersResponseVO;
	
	private GetSalesOfferDetailHelper getSalesOfferDetailHelper;

	public GetSalesOfferDetailForShoppingCartTask(GetSalesOfferDetailRequestVO requestVO, GetSalesOfferDetailHelper getSalesOfferDetailHelper) {
		this.requestVO = requestVO;
		this.getSalesOfferDetailHelper = getSalesOfferDetailHelper;
	}

	@Override
	protected void execute() {
		offersResponseVO = getSalesOfferDetailHelper.execute(requestVO);
	}

	public GetSalesOfferDetailResponseVO2 getResult() {
		return this.offersResponseVO;
	}
	
	public GetSalesOfferDetailRequestVO getRequestVO() {
		return requestVO;
	}
}