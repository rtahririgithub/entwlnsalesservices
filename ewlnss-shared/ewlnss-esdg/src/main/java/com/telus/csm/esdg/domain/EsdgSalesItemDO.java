package com.telus.csm.esdg.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

/**
 * The Class EsdgSalesItemDO.
 */
public class EsdgSalesItemDO extends EsdgDO {
	
	private static final long serialVersionUID = 1L;

	/** The logger. */
	static Logger logger = Logger.getLogger(EsdgSalesItemDO.class);
	
	protected String itemId;
	protected String itemType;
	protected String status;

	protected String externalTxnReference;
	
	protected String itemTxnDataId;
	protected String jsonSalesTXNData;
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getExternalTxnReference() {
		return externalTxnReference;
	}

	public void setExternalTxnReference(String externalTxnRefId) {
		this.externalTxnReference = externalTxnRefId;
	}

	public String getItemTxnDataId() {
		return itemTxnDataId;
	}

	public void setItemTxnDataId(String itemTxnDataId) {
		this.itemTxnDataId = itemTxnDataId;
	}

	public String getJsonSalesTXNData() {
		return jsonSalesTXNData;
	}

	public void setJsonSalesTXNData(String jsonSalesTXNData) {
		this.jsonSalesTXNData = jsonSalesTXNData;
	}

	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
		StringBuilder sb = new StringBuilder()
		.append(" : itemId = ").append( itemId )
		.append(" : contextId = ").append( salesContextId)
		.append(" : interactionDT = ").append(df.format( salesInteractionStartTimeInMills))
		.append(" : itemType = ").append( itemType )
		.append(" : itemStatus = ").append( status )
		;
		return sb.toString();
	}

//	public String getJournal() {
//		StringBuilder sb = new StringBuilder("--ESDG-- JNL = ").append(salesInteractionId);
//		try {
//			sb.append(":").append(Long.toString(dataGenerationTimeInMills))
//			.append(":").append( Long.toString(salesInteractionStartTimeInMills) )		
//			.append(":").append( jsonSalesTXNData );
//		} catch (Exception ex) {}
//		return sb.toString();
//	}
//	
//	public void logJournal(boolean isErrorLevel) {
//		if( StringUtils.isEmpty(this.getJsonSalesTXNData()) ) return;
//		if( isErrorLevel ) {
//			logger.error(getJournal());
//		} else  {
//			logger.info(getJournal());
//		}
//	}

	@Override
	public boolean isWriteToDatabase() {
		return true;
	}
}
