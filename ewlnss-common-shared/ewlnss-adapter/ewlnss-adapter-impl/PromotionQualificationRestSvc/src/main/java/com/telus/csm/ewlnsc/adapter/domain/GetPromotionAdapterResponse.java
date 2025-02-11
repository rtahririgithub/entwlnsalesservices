package com.telus.csm.ewlnsc.adapter.domain;




import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

import com.telus.csm.cpq.rest.domain.PromotionQualification;

public class GetPromotionAdapterResponse extends WlnOPRestSvcResponseBase {

	private static final long serialVersionUID = 1L;
	private PromotionQualification promotionQualification;
	public PromotionQualification getPromotionQualification() {
		return promotionQualification;
	}
	public void setPromotionQualification(PromotionQualification promotionQualification) {
		this.promotionQualification = promotionQualification;
	}

}
