package com.telus.csm.ewlnsc.domain;

import java.util.List;

import com.telus.csm.ewlnsc.domain.product.AvailableProductItemResponseVO;
import com.telus.csm.ewlnsc.domain.product.GetAvailableProductItemErrorResponseVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;

public class GetAvailableProductItemDelegateResponse {

	private AvailableProductItemResponseVO response;
	private GetAvailableProductItemErrorResponseVO errorResponse;
	private List<ProductItemVO> productItems;

	public List<ProductItemVO> getProductItems() {
		return productItems;
	}

	public void setProductItems(
			List<ProductItemVO> productItems) {
		this.productItems = productItems;
	}
	
	public void setResponse(AvailableProductItemResponseVO response) {
		this.response = response;
	}

	public AvailableProductItemResponseVO getResponse() {
		return response;
	}

	public GetAvailableProductItemErrorResponseVO getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(GetAvailableProductItemErrorResponseVO errorResponse) {
		this.errorResponse = errorResponse;		
	}

}
