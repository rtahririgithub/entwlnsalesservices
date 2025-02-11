/**
 * 
 */
package com.telus.csm.ewlnsms.core.domain;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;

/**
 * @author x145592
 *
 */
public class CreateWLNOrderCoreResponse extends CoreResponseBase {

	private static final long serialVersionUID = 1L;

    private String ban;
    private String salesId;
    private String orderId;
    private boolean processInd;

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isProcessInd() {
		return processInd;
	}

	public void setProcessInd(boolean processInd) {
		this.processInd = processInd;
	}
    
	
}
