package com.telus.csm.ewlnsc.helper;

import com.telus.csm.ewlnsc.delegate.PromotionQualificationDelegate;
import com.telus.csm.ewlnsc.domain.GetPromotionRequestVO;
import com.telus.csm.ewlnsc.domain.PromotionsVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class PromotionQualificationHelper {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(PromotionQualificationHelper.class);

	public PromotionQualificationHelper(){
	}
	
	public PromotionsVO execute(final ShoppingCartContextVO promotionRequestVO, boolean isPostTask, boolean promoInd) {
		final String functionName = "execute";
//		LOGGER.info(functionName, "start getting available promotions with coupon code indicator=" + promotionRequestVO.isCouponInd());
		
		PromotionsVO result;
		
		final PromotionQualificationDelegate promoDelegate = new PromotionQualificationDelegate();
		
		result = promoDelegate.getPromotions(promotionRequestVO, isPostTask, promoInd);
		
		return result;
	}	
}
