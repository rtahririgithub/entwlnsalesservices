package com.telus.csm.esdg.domain;

import java.io.Serializable;

public class EsdgOrderContractDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String salesOrderContractDataID;
	private String salesOrderID;
	private long intractnStartTs;
	private long salesTxnTs;
	private String externalTxnRefID;
	private String salesContractData;   //jason order contract
	private String createUserID;
	private long createTs;
	private String lastUpdtUserID;
	private long lastUpdtTs;
	
	public String getSalesOrderContractDataID() {
		return salesOrderContractDataID;
	}
	public void setSalesOrderContractDataID(String salesOrderContractDataID) {
		this.salesOrderContractDataID = salesOrderContractDataID;
	}
	public String getSalesOrderID() {
		return salesOrderID;
	}
	public void setSalesOrderID(String salesOrderID) {
		this.salesOrderID = salesOrderID;
	}
	public long getIntractnStartTs() {
		return intractnStartTs;
	}
	public void setIntractnStartTs(long intractnStartTs) {
		this.intractnStartTs = intractnStartTs;
	}
	public long getSalesTxnTs() {
		return salesTxnTs;
	}
	public void setSalesTxnTs(long salesTxnTs) {
		this.salesTxnTs = salesTxnTs;
	}
	public String getExternalTxnRefID() {
		return externalTxnRefID;
	}
	public void setExternalTxnRefID(String externalTxnRefID) {
		this.externalTxnRefID = externalTxnRefID;
	}
	public String getSalesContractData() {
		return salesContractData;
	}
	public void setSalesContractData(String salesContractData) {
		this.salesContractData = salesContractData;
	}
	public String getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	public long getCreateTs() {
		return createTs;
	}
	public void setCreateTs(long createTs) {
		this.createTs = createTs;
	}
	public String getLastUpdtUserID() {
		return lastUpdtUserID;
	}
	public void setLastUpdtUserID(String lastUpdtUserID) {
		this.lastUpdtUserID = lastUpdtUserID;
	}
	public long getLastUpdtTs() {
		return lastUpdtTs;
	}
	public void setLastUpdtTs(long lastUpdtTs) {
		this.lastUpdtTs = lastUpdtTs;
	}
	
	
	
}
