package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.domain.FIFAProductOfferingsVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.helper.FIFAProductQualificationHelper;
import com.telus.csm.ewlnsc.helper.GetSalesOfferDetailHelper;

import commonj.work.Work;

public class GetAvailableFIFAProductOfferingsTask  extends TaskBase implements Work {
 //shoppingCartVO - request
	//FIFAProductOfferingsVO -- response
	
	private ShoppingCartVO  requestVO;

	private FIFAProductOfferingsVO   fifaProductOfferingsVO;
	
	private FIFAProductQualificationHelper fifaProductQualificationHelper;

	public GetAvailableFIFAProductOfferingsTask(ShoppingCartVO requestVO) {
		this.requestVO = requestVO;
	}

	@Override
	protected void execute() {
		fifaProductOfferingsVO = (new FIFAProductQualificationHelper()).execute(requestVO);
	}

	public GetSalesOfferDetailResponseVO2 getResult() {
		return this.fifaProductOfferingsVO;
	}
	
	public ShoppingCartVO getRequestVO() {
		return requestVO;
	}
}
