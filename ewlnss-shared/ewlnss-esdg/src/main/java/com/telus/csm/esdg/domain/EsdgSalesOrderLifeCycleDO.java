package com.telus.csm.esdg.domain;

import java.io.Serializable;
import java.util.Date;

public class EsdgSalesOrderLifeCycleDO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String salesContextId;
	private String salesOrderStatusCd;
	private Date lastUpdtTs;

	public String getSalesContextId() {
		return salesContextId;
	}
	public void setSalesContextId(String salesContextId) {
		this.salesContextId = salesContextId;
	}
	public String getSalesOrderStatusCd() {
		return salesOrderStatusCd;
	}
	public void setSalesOrderStatusCd(String salesOrderStatusCd) {
		this.salesOrderStatusCd = salesOrderStatusCd;
	}
	public Date getLastUpdtTs() {
		return lastUpdtTs;
	}
	public void setLastUpdtTs(Date lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}
	
	
}
