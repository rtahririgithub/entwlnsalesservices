package com.telus.csm.ewlnsc.adapter.ccm.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

public class SubmitTransactionToPOSAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	
	private String shoppingCartId;
	
	private String posToken;

	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getPosToken() {
		return posToken;
	}

	public void setPosToken(String posToken) {
		this.posToken = posToken;
	}
}
