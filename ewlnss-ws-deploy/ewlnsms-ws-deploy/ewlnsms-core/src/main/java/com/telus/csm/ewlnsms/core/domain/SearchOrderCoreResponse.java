package com.telus.csm.ewlnsms.core.domain;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.csm.ewlnsc.domain.OrderItemSummaryVO;


public class SearchOrderCoreResponse extends CoreResponseBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<OrderItemSummaryVO> orderItemSummaryList;
	
	public List<OrderItemSummaryVO> getOrderItemSummaryList() {
		return orderItemSummaryList;
	}
	public void setOrderItemSummaryList(List<OrderItemSummaryVO> orderItemSummaryList) {
		this.orderItemSummaryList = orderItemSummaryList;
	}

}
