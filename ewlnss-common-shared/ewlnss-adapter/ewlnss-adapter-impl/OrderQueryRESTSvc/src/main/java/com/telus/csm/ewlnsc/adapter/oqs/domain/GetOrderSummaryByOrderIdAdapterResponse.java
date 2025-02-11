package com.telus.csm.ewlnsc.adapter.oqs.domain;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class GetOrderSummaryByOrderIdAdapterResponse extends WlnOPRestSvcResponseBase {

	private ProductOrder productOrderDetail;

	public ProductOrder getProductOrderDetail() {
		return productOrderDetail;
	}

	public void setProductOrderDetail(ProductOrder productOrderDetail) {
		this.productOrderDetail = productOrderDetail;
	}
}