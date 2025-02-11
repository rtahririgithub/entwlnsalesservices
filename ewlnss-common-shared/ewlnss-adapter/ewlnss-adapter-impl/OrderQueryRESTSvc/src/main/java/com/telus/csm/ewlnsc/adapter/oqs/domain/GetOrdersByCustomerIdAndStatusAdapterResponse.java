package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class GetOrdersByCustomerIdAndStatusAdapterResponse extends WlnOPRestSvcResponseBase {

	private static final long serialVersionUID = 1L;
	
	private List<ProductOrder> productOrderList;

	public List<ProductOrder> getProductOrderList() {
		return productOrderList;
	}

	public void setProductOrderList(List<ProductOrder> productOrderList) {
		this.productOrderList = productOrderList;
	}
}