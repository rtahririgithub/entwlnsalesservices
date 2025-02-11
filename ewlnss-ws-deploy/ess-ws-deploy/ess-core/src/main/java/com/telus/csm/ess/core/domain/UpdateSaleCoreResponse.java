package com.telus.csm.ess.core.domain;

import java.io.Serializable;

import com.telus.csm.ess.common.domain.CoreResponseBaseVO;

public class UpdateSaleCoreResponse extends CoreResponseBaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	String shoppingCartId;
	
	String saleStatus;

	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	
}
