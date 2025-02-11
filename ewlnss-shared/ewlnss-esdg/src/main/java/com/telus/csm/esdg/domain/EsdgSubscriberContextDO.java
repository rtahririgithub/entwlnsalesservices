package com.telus.csm.esdg.domain;

import org.apache.log4j.Logger;

/**
 * The Class EsdgSubscriberContextDO.
 */
public class EsdgSubscriberContextDO extends EsdgDO {
	
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger( EsdgOrderDO_1.class );
	
	private String subscriberContextId;
	private String cacheKey;
	private String accountNumber;
	private String phoneNumber;
	private String type;
	private String status;
	private String txnDataId;
	private String jsonSalesTXNData;

	public static String composeCacheKey( String salesId, String type ) {
		return salesId + "_" + type;
	}
	
	public static String[] parse( String cacheKey ) {
		
		String[] result = new String[2];
		
		int endIndex = cacheKey.indexOf("_"); //the first  occurrence of "_" 
		if (endIndex>0) {
			result[0] = cacheKey.substring(0, endIndex);
			result[1] = cacheKey.substring( endIndex+1 );
		} else {
			throw new RuntimeException ( "unable the parse EsdgSubscriberContextDO cacheKey: " + cacheKey );
		}
		
		return result;
	}
	
	public String getCacheKey() {
		if (cacheKey==null) {
			cacheKey = composeCacheKey(getSalesContextId(), type);
		}
		return cacheKey;
	}
	
	public void setCacheKey(String key) {
		cacheKey = key;
	}
	
	public String getSubscriberContextId() {
		return subscriberContextId;
	}

	public void setSubscriberContextId(String subscriberContextId) {
		this.subscriberContextId = subscriberContextId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxnDataId() {
		return txnDataId;
	}

	public void setTxnDataId(String subCtxTxnDataId) {
		this.txnDataId = subCtxTxnDataId;
	}

	public String getJsonSalesTXNData() {
		return jsonSalesTXNData;
	}

	public void setJsonSalesTXNData(String jsonSalesTXNData) {
		this.jsonSalesTXNData = jsonSalesTXNData;
	}

	@Override
	public boolean isWriteToDatabase() {
		return true;
	}

	public String getJournal() {
		
		StringBuilder sb = new StringBuilder("--ESDG-- SubcriberContext JNL = ")
		.append(salesContextId)
		.append(":").append( type )
		.append(":").append( Long.toString(salesInteractionStartTimeInMills) )		
		.append(":").append( accountNumber )
		.append(":").append( phoneNumber )
		.append(":").append( status )
		.append(":").append( jsonSalesTXNData );
		
		return sb.toString();
	}
	
	public void logJournal(boolean isErrorLevel) {
		if( this.jsonSalesTXNData==null ) return;
		if( isErrorLevel ) {
			logger.error(getJournal());
		} else  {
			logger.info(getJournal());
		}
	}
}
