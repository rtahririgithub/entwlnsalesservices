package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreateInvoiceAdapterResponse extends WlnOPRestSvcResponseBase {

	private String invoiceNo;

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
}
