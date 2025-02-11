package com.telus.csm.esdg.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class EsdgOrderContractDO_1 extends EsdgDO {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9009040703277442493L;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(EsdgOrderContractDO_1.class);
	
	/** The sales context external ref id. */
	protected String salesContextExternalRefId = null;

	/** The order id. */
	protected String orderId = null;        //internal fields for display purpose
	
	/** The order id. */
	protected String orderContractId = null;        //internal fields for display purpose

	/** The order version. */
	protected String orderVersion = null;   //internal fields for display purpose
	
	/** The is write to database. */
	protected boolean isWriteToDatabase = true;		

	/** The json order contract. */
	protected String jsonOrderContract = null;
	
	/** The order status change list. */
	protected ArrayList<EsdgOrderContractDO> orderContractList = null;	

	public ArrayList<EsdgOrderContractDO> getOrderContractList() {
		if(orderContractList==null){
			orderContractList = new ArrayList<EsdgOrderContractDO>();
		}
		return orderContractList;
	}

	public void setOrderContractList(ArrayList<EsdgOrderContractDO> orderContractList) {
		this.orderContractList = orderContractList;
	}

	public String getJsonOrderContract() {
		return jsonOrderContract;
	}

	public void setJsonOrderContract(String jsonOrderContract) {
		this.jsonOrderContract = jsonOrderContract;
	}

	/**
	 * Gets the sales context external ref id.
	 *
	 * @return the sales context external ref id
	 */
	public String getSalesContextExternalRefId() {
		return salesContextExternalRefId;
	}

	/**
	 * Sets the sales context external ref id.
	 *
	 * @param salesContextExternalRefId the new sales context external ref id
	 */
	public void setSalesContextExternalRefId(String salesContextExternalRefId) {
		this.salesContextExternalRefId = salesContextExternalRefId;
	}

	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderContractId() {
		return orderContractId;
	}

	public void setOrderContractId(String orderContractId) {
		this.orderContractId = orderContractId;
	}

	/**
	 * Gets the order version.
	 *
	 * @return the order version
	 */
	public String getOrderVersion() {
		return orderVersion;
	}

	/**
	 * Sets the order version.
	 *
	 * @param orderVersion the new order version
	 */
	public void setOrderVersion(String orderVersion) {
		this.orderVersion = orderVersion;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
		StringBuffer sb = new StringBuffer()
		.append(" orderId = ").append(orderId)
		.append(" : orderVersion = ").append(orderVersion)		
		.append(" : orderSaveDT = ").append(df.format(dataGenerationTimeInMills))
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
		StringBuffer sb = new StringBuffer("--ESDG-- JNL = ").append(salesContextId);
		sb.append(":").append(Long.toString(dataGenerationTimeInMills))
		.append(":").append(externalKey == null ? "" : externalKey)			
		.append(":").append(salesContextExternalRefId == null ? "" : salesContextExternalRefId)
		.append(":").append(salesInteractionId == null ? "" : salesInteractionId)
		.append(":").append(Long.toString(salesInteractionStartTimeInMills))		
		.append(":").append(this.getJsonOrderContract());
		return sb.toString();
	}
	
	/**
	 * Log journal.
	 *
	 * @param isErrorLevel the is error level
	 */
	public void logJournal(boolean isErrorLevel) {
		if( StringUtils.isEmpty(this.getJsonOrderContract()) ) return;
		if( isErrorLevel ) {
			logger.error(getJournal());
		} else  {
			logger.info(getJournal());
		}
	}

}
