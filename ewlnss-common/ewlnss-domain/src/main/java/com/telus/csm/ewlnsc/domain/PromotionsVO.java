package com.telus.csm.ewlnsc.domain;

import java.util.Map;

public class PromotionsVO extends CoreResponseBase{

	private GetOffersResponseVO2 accessoryResponse;
	private GetSweetenerOfferListResponseVO2 sweetenerOfferListResponse;
	private Map<String,ProductPromotionDiscountsVO> productPromotionDiscountsOferMap;
	
	public GetOffersResponseVO2 getAccessoryResponse() {
		return accessoryResponse;
	}
	public void setAccessoryResponse(GetOffersResponseVO2 accessoryResponse) {
		this.accessoryResponse = accessoryResponse;
	}
	public GetSweetenerOfferListResponseVO2 getSweetenerOfferListResponse() {
		return sweetenerOfferListResponse;
	}
	public void setSweetenerOfferListResponse(GetSweetenerOfferListResponseVO2 sweetenerOfferListResponse) {
		this.sweetenerOfferListResponse = sweetenerOfferListResponse;
	}
	public Map<String, ProductPromotionDiscountsVO> getProductPromotionDiscountsOferMap() {
		return productPromotionDiscountsOferMap;
	}
	public void setProductPromotionDiscountsOferMap(Map<String, ProductPromotionDiscountsVO> productPromotionDiscountsOferMap) {
		this.productPromotionDiscountsOferMap = productPromotionDiscountsOferMap;
	}

}
