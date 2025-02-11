package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.domain.RelatedCartItemVO;

public class CreateOpOrderRequestWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private CreateOrderAdapterRequest createOrderAdapterRequest;
	private List<RelatedCartItemVO> relatedCartItemList;
	
	public CreateOrderAdapterRequest getCreateOrderAdapterRequest() {
		return createOrderAdapterRequest;
	}
	public void setCreateOrderAdapterRequest(CreateOrderAdapterRequest createOrderAdapterRequest) {
		this.createOrderAdapterRequest = createOrderAdapterRequest;
	}
	public List<RelatedCartItemVO> getRelatedCartItemList() {
		return relatedCartItemList;
	}
	public void setRelatedCartItemList(List<RelatedCartItemVO> relatedCartItemList) {
		this.relatedCartItemList = relatedCartItemList;
	}
	
}
