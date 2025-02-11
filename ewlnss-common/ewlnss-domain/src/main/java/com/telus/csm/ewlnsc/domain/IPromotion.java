package com.telus.csm.ewlnsc.domain;

import java.util.Date;

public interface IPromotion {
	public String getPromotionId() ;
	public Date getPromotionPerspectiveDate();
	public PromotionGroupVO getPromotionGroup() ;
	public PromoCodeRefVO getPromoCodeRef();
	public boolean hasInstallCredit(); // Gary 2019-07-30 WEEKDAY Installation Credit
}
