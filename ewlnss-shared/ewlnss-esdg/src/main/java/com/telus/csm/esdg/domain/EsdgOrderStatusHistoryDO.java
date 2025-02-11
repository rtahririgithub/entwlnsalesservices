package com.telus.csm.esdg.domain;

import java.io.Serializable;

public class EsdgOrderStatusHistoryDO implements Serializable {
	
	private static final long serialVersionUID = 8841874477441830346L;
	
	protected String orderStatus;
	protected long orderStatusTimeInMills;
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public long getOrderStatusTimeInMills() {
		return orderStatusTimeInMills;
	}
	public void setOrderStatusTimeInMills(long orderStatusTimeInMills) {
		this.orderStatusTimeInMills = orderStatusTimeInMills;
	}
}
