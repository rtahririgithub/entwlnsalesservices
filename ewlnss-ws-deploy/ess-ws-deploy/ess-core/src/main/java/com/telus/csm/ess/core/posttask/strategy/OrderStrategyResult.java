package com.telus.csm.ess.core.posttask.strategy;

import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;

public class OrderStrategyResult {
	private Boolean orderSubmitted;
	private OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponse;
	private Integer opOrderId;
	
	OrderStrategyResult(Integer opOrderId, OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponse, Boolean orderSubmitted) {
		this.opOrderId = opOrderId;
		this.opShoppingCartDelegateResponse = opShoppingCartDelegateResponse;
		this.orderSubmitted = orderSubmitted;
	}
	
	public Boolean getOrderSubmited() {
		return orderSubmitted;
	}
	public Integer getOpOrderId() {
		return opOrderId;
	}
	public OPShoppingCartDelegateResponseVO getOpShoppingCartDelegateResponse() {
		return opShoppingCartDelegateResponse;
	}

}
