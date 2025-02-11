package com.telus.csm.esdg.domain;

import java.io.Serializable;
import java.util.Date;

import com.telus.csm.essc.cachestore.ICacheAware;

public final class EsdgReferenceDataDO implements ICacheAware, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3667672807651194356L;
	
//	SALES_CONTEXT_TXN_DATA_ID - Table Sequence number <PK>
	private int salesContextTxnDataId;
	
//	INTRACTN_START_TS - record creation timestamp <PK>
	private Date interactStartTS = new Date();
	
//	SALES_CONTEXT_ID (Not used)
//	private long salesContextId;
	
//	SALES_TXN_TS (Not used)
//	private Date salesTxnTS;
	
//	EXTERNAL_TXN_REF_ID - cache key
	private String externalTxnRefId;
	
//	SALES_CONTEXT_DATA_PARTITION_CD - LAIRD_ESS/ QIDC_ESS/ QIDC_REST
	private String salesContextDataPartitionCd;
	
//	SALES_CONTEXT_DATA_TYPE_CD
	private String salesContextDataTypeCd;
	
//	USAGE_COUNT
	private int usageCount;
	
//	CREATE_USER_ID
	private String createUserId;
	
//	CREATE_TS
	private Date createTS;
	
//	LAST_UPDT_USER_ID
	private String lastUpdateUserId;
	
//	LAST_UPDT_TS
	private Date lastUpdateTS = new Date();
	
//	LAST_READ_TS
	private Date lastReadTS;
	
//	SALES_TXN_DATA - the blob
	private byte[] salesTxnData;
	
	public int getSalesContextTxnDataId() {
		return salesContextTxnDataId;
	}
	public void setSalesContextTxnDataId(int salesContextTxnDataId) {
		this.salesContextTxnDataId = salesContextTxnDataId;
	}
	public Date getInteractStartTS() {
		return interactStartTS;
	}
	public void setInteractStartTS(Date interactStartTS) {
		this.interactStartTS = interactStartTS;
	}
	public String getExternalTxnRefId() {
		return externalTxnRefId;
	}
	public void setExternalTxnRefId(String externalTxnRefId) {
		this.externalTxnRefId = externalTxnRefId;
	}
	public String getSalesContextDataPartitionCd() {
		return salesContextDataPartitionCd;
	}
	public void setSalesContextDataPartitionCd(String salesContextDataPartitionCd) {
		this.salesContextDataPartitionCd = salesContextDataPartitionCd;
	}
	public String getSalesContextDataTypeCd() {
		return salesContextDataTypeCd;
	}
	public void setSalesContextDataTypeCd(String salesContextDataTypeCd) {
		this.salesContextDataTypeCd = salesContextDataTypeCd;
	}
	public byte[] getSalesTxnData() {
		return salesTxnData;
	}
	public void setSalesTxnData(byte[] salesTxnData) {
		this.salesTxnData = salesTxnData;
	}
	public int getUsageCount() {
		return usageCount;
	}
	public void setUsageCount(int usageCount) {
		this.usageCount = usageCount;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTS() {
		return createTS;
	}
	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}
	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(String lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public Date getLastUpdateTS() {
		return lastUpdateTS;
	}
	public void setLastUpdateTS(Date lastUpdateTS) {
		this.lastUpdateTS = lastUpdateTS;
	}
	public Date getLastReadTS() {
		return lastReadTS;
	}
	public void setLastReadTS(Date lastReadTS) {
		this.lastReadTS = lastReadTS;
	}

	public String getLogInfo() {
		return "Key=" + getExternalTxnRefId() + " Partition=" + getSalesContextDataPartitionCd()
		+ " InteractStartTS="+ getInteractStartTS() + " LastReadTS=" + getLastReadTS()
		+ " LastUpdateTS=" + getLastUpdateTS() + " UsageCount=" + getUsageCount();
	}
	@Override
	public boolean isWriteToDatabase() {
		return true;
	}
}
