package com.telus.csm.ewlnsc.domain;

import java.util.List;


public class CartItemDisconnectOrderVO extends CartItemVO {

	private List<DisconnectRequestTypeVO> disconnectRequestList = null;

	public List<DisconnectRequestTypeVO> getDisconnectRequestList() {
		return disconnectRequestList;
	}

	public void setDisconnectRequestList(List<DisconnectRequestTypeVO> disconnectRequestList) {
		this.disconnectRequestList = disconnectRequestList;
	}

	public boolean isSalesOrderIem() {
		
		return false;
	}

	public boolean isPerkOrderIem() {
		
		return false;
	}

	public boolean isDisconnectOrderIem() {
		
		return true;
	}


}
  