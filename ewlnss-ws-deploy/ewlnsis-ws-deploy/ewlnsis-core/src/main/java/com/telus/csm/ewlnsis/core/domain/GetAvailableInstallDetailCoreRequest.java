package com.telus.csm.ewlnsis.core.domain;

import java.io.Serializable;
import java.util.Date;

import com.telus.csm.ess.common.domain.CoreRequestBaseVO;

public class GetAvailableInstallDetailCoreRequest extends CoreRequestBaseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String shoppingCartId = null;
	private Date fromDate = null;
	private Date toDate = null;
	private Boolean combinationRequiredIndicator = null;

	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Boolean getCombinationRequiredIndicator() {
		return combinationRequiredIndicator;
	}

	public void setCombinationRequiredIndicator(Boolean combinationRequiredIndicator) {
		this.combinationRequiredIndicator = combinationRequiredIndicator;
	}

}
