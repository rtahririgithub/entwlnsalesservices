package com.telus.csm.esdg.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * The Class EsdgInteractionDO_1.
 */
public class EsdgInteractionDO_1 extends EsdgDO {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9009040703277442493L;
	
	/** The logger. */
	static Logger logger = Logger.getLogger(EsdgInteractionDO_1.class);
	
	/** The customer account number. */
	protected String billingAccountNum = null;

	/** The subscriber contact phone number. */
	protected String contactPhoneNum = null;
	
	/** The interaction external ref id. */
	protected String externalSalesRefId;
	
	//protected String createUserID;
	protected String interactionTypeCD;
	protected String channelOrgTypeCD;
	protected String channelOrgID;
	protected String channelPersonTypeCD;
	protected String channelPersonID;
	protected String externalTxnRefID;
	protected String jsonSalesTXNData;
	protected String status;
	
	/** The interaction status change list. */
	protected List<EsdgInteractionStatusHistoryDO> interactionStatusChangeList;

	/** The is write to database. */
	protected boolean isWriteToDatabase = true;

	protected boolean skipUpdateInteration = false;// fix QC74387
	
	public String getExternalSalesRefId() {
		return externalSalesRefId;
	}

	/** The interaction external ref id. */
	public void setExternalSalesRefId(String externalSalesRefId) {
		this.externalSalesRefId = externalSalesRefId;
	}

	public String getBillingAccountNum() {
		return billingAccountNum;
	}

	public void setBillingAccountNum(String billingAccountNum) {
		this.billingAccountNum = billingAccountNum;
	}

	public String getContactPhoneNum() {
		return contactPhoneNum;
	}

	public void setContactPhoneNum(String contactPhoneNum) {
		this.contactPhoneNum = contactPhoneNum;
	}

//	public String getCreateUserID() {
//		return createUserID;
//	}
//
//	public void setCreateUserID(String createUserID) {
//		this.createUserID = createUserID;
//	}

	public String getInteractionTypeCD() {
		return interactionTypeCD;
	}

	public void setInteractionTypeCD(String interactionTypeCD) {
		this.interactionTypeCD = interactionTypeCD;
	}

	public String getChannelOrgTypeCD() {
		return channelOrgTypeCD;
	}

	public void setChannelOrgTypeCD(String channelOrgTypeCD) {
		this.channelOrgTypeCD = channelOrgTypeCD;
	}

	public String getChannelOrgID() {
		return channelOrgID;
	}

	public void setChannelOrgID(String channelOrgID) {
		this.channelOrgID = channelOrgID;
	}

	public String getChannelPersonTypeCD() {
		return channelPersonTypeCD;
	}

	public void setChannelPersonTypeCD(String channelPersonTypeCD) {
		this.channelPersonTypeCD = channelPersonTypeCD;
	}

	public String getChannelPersonID() {
		return channelPersonID;
	}

	public void setChannelPersonID(String channelPersonID) {
		this.channelPersonID = channelPersonID;
	}

	public String getExternalTxnRefID() {
		return externalTxnRefID;
	}

	public void setExternalTxnRefID(String externalTxnRefID) {
		this.externalTxnRefID = externalTxnRefID;
	}

	public String getJsonSalesTXNData() {
		return jsonSalesTXNData;
	}

	public void setJsonSalesTXNData(String jsonSalesTXNData) {
		this.jsonSalesTXNData = jsonSalesTXNData;
	}

	public List<EsdgInteractionStatusHistoryDO> getInteractionStatusChangeList() {
		return interactionStatusChangeList;
	}

	public void setInteractionStatusChangeList(List<EsdgInteractionStatusHistoryDO> interactionStatusChangeList) {
		this.interactionStatusChangeList = interactionStatusChangeList;
	}

	/**
	 * Adds the interaction status.
	 *
	 * @param interStatus the interaction status
	 * @param interUpdtTime the new interaction update time in mills
	 */
	public void addInteractionStatus(final String interStatus, final long interUpdtTime) {
		if( interactionStatusChangeList == null ) {
			interactionStatusChangeList = new ArrayList<EsdgInteractionStatusHistoryDO>();
		}
		final EsdgInteractionStatusHistoryDO statusDO = new EsdgInteractionStatusHistoryDO();
		statusDO.setInteractionStatus(interStatus);
		statusDO.setInteractionStatusTimeInMills(interUpdtTime);
		interactionStatusChangeList.add(statusDO);
	}
	
	/**
	 * Sets the write to database.
	 *
	 * @param isWriteToDatabase the new write to database
	 */
	public void setWriteToDatabase(boolean isWriteToDatabase) {
		this.isWriteToDatabase = isWriteToDatabase;
	}

	/* (non-Javadoc)
	 * @see com.telus.csm.essc.cachestore.ICacheAware#isWriteToDatabase()
	 */
	@Override
	public boolean isWriteToDatabase() {
		return this.isWriteToDatabase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	// fix QC74387
	public boolean isSkipUpdateInteration() {
		return skipUpdateInteration;
	}

	public void setSkipUpdateInteration(boolean skipUpdateInteration) {
		this.skipUpdateInteration = skipUpdateInteration;
	}
	// END fix QC74387

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
		StringBuffer sb = new StringBuffer()
		.append(" externalSalesRefId = ").append( externalSalesRefId )
		.append(" : billingAccountNum = ").append(billingAccountNum)
		.append(" : contactPhoneNum = ").append(contactPhoneNum)
//		.append(" : createUserID = ").append(createUserID)
		.append(" : interactionTypeCD = ").append(interactionTypeCD)
		.append(" : channelOrgTypeCD = ").append(channelOrgTypeCD)
		.append(" : channelOrgID = ").append(channelOrgID)
		.append(" : channelPersonTypeCD = ").append(channelPersonTypeCD)
		.append(" : channelPersonID = ").append(channelPersonID)
		.append(" : externalTxnRefID = ").append(externalTxnRefID)
		.append(" : interactionSaveDT = ").append(df.format(dataGenerationTimeInMills))
		.append(" : externalKey = ").append(externalKey)
		.append(" : contextId = ").append(salesContextId)
		.append(" : interactionDT = ").append(df.format(salesInteractionStartTimeInMills));
		return sb.toString();
	}

	/**
	 * Gets the journal.
	 *
	 * @return the journal
	 */
	public String getJournal() {
		StringBuffer sb = new StringBuffer("--ESDG-- JNL = ").append(salesInteractionId);
		try {
			sb.append(":").append(Long.toString(dataGenerationTimeInMills))
			.append(":").append( billingAccountNum == null ? "" : billingAccountNum )
			.append(":").append( externalSalesRefId == null ? "" : externalSalesRefId )
			.append(":").append( externalTxnRefID == null ? "" : externalTxnRefID)
			.append(":").append( Long.toString(salesInteractionStartTimeInMills) )		
			.append(":").append( jsonSalesTXNData );
		} catch (Exception ex) {}
		return sb.toString();
	}
	
	/**
	 * Log journal.
	 *
	 * @param isErrorLevel the is error level
	 */
	public void logJournal(boolean isErrorLevel) {
		if( StringUtils.isEmpty(this.getJsonSalesTXNData()) ) return;
		if( isErrorLevel ) {
			logger.error(getJournal());
		} else  {
			logger.info(getJournal());
		}
	}
}
