package com.telus.csm.esdg.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class EsdgOrderDO_1.
 */
public class EsdgOrderDO_1 extends EsdgDO {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9009040703277442493L;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(EsdgOrderDO_1.class);
	
	/** The sales context external ref id. */
	protected String salesContextExternalRefId = null;
	
	/** The brand cd. */
	protected String brandCd = null;
	
	/** The subscriber phone number. */
	protected String subscriberPhoneNumber = null;
	
	/** The order id. */
	protected String orderId = null;        //internal fields for display purpose
	
	/** The order version. */
	protected String orderVersion = null;   //internal fields for display purpose
	
	/** The order status. */
	protected String orderStatus = null;	
	
	/** The fulfillment status. */
	protected String fulfillmentStatus = null; 
	
	/** The fulfillment status time in mills. */
	protected long fulfillmentStatusTimeInMills = 0;
	
	/** The json order. */
	protected String jsonOrder = null;
	
	/** The is write to database. */
	protected boolean isWriteToDatabase = true;
	
	/** The order status change list. */
	protected ArrayList<EsdgOrderStatusHistoryDO> orderStatusChangeList = null;

	/** The order fulfillment status change list. */
	protected ArrayList<EsdgOrderFulfillmentStatusHistoryDO> orderFulfillmentStatusChangeList = null;
	
	/** The order classifier map. */
	protected HashMap<String, String> orderClassifierMap = null;
	
	private boolean isMTSTransitionOrderInd = false;
	
	/** Spruce: require BAN on MTS_SALES_ORDER table */
	protected String BillingAccountNumber = null;
	
	//SAP - Waybill
	private String sapOrderNum;
	private String shipmentStatus;
	private String shipmentTrackingNum;
	
	//Atlas FIFA
	private String typeCode;
	public static final String COMMERCE_CART_ITEM="COMMERCE_CART_ITEM";

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
	 * Gets the brand cd.
	 *
	 * @return the brand cd
	 */
	public String getBrandCd() {
		return brandCd;
	}

	/**
	 * Sets the brand cd.
	 *
	 * @param brandCd the new brand cd
	 */
	public void setBrandCd(String brandCd) {
		this.brandCd = brandCd;
	}

	/**
	 * Gets the subscriber phone number.
	 *
	 * @return the subscriber phone number
	 */
	public String getSubscriberPhoneNumber() {
		return subscriberPhoneNumber;
	}

	/**
	 * Sets the subscriber phone number.
	 *
	 * @param subscriberPhoneNumber the new subscriber phone number
	 */
	public void setSubscriberPhoneNumber(String subscriberPhoneNumber) {
		this.subscriberPhoneNumber = subscriberPhoneNumber;
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
	 * Gets the order status.
	 *
	 * @return the order status
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * Sets the order status.
	 *
	 * @param orderStatus the new order status
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * Gets the order status change list.
	 *
	 * @return the order status change list
	 */
	public ArrayList<EsdgOrderStatusHistoryDO> getOrderStatusChangeList() {
		return orderStatusChangeList;
	}

	/**
	 * Sets the order status change list.
	 *
	 * @param orderStatusChangeList the new order status change list
	 */
	public void setOrderStatusChangeList(ArrayList<EsdgOrderStatusHistoryDO> orderStatusChangeList) {
		this.orderStatusChangeList = orderStatusChangeList;
	}

	/**
	 * Adds the order status.
	 *
	 * @param orderStatus the order status
	 * @param newOrderUpdateTimeInMills the new order update time in mills
	 */
	public void addOrderStatus(String orderStatus, long newOrderUpdateTimeInMills) {
		if( orderStatusChangeList == null ) {
			orderStatusChangeList = new ArrayList<EsdgOrderStatusHistoryDO>();
		}
		EsdgOrderStatusHistoryDO orderStatusHistory = new EsdgOrderStatusHistoryDO();
		orderStatusHistory.setOrderStatus(orderStatus);
		orderStatusHistory.setOrderStatusTimeInMills(newOrderUpdateTimeInMills);
		orderStatusChangeList.add(orderStatusHistory);
	}

	
	/**
	 * Gets the order classifier map.
	 *
	 * @return the order classifier map
	 */
	public HashMap<String, String> getOrderClassifierMap() {
		return orderClassifierMap;
	}

	/**
	 * Sets the order classifier map.
	 *
	 * @param orderClassifierMap the order classifier map
	 */
	public void setOrderClassifierMap(HashMap<String, String> orderClassifierMap) {
		this.orderClassifierMap = orderClassifierMap;
	}

	/**
	 * Gets the fulfillment status.
	 *
	 * @return the fulfillment status
	 */
	public String getFulfillmentStatus() {
		return fulfillmentStatus;
	}

	/**
	 * Sets the fulfillment status.
	 *
	 * @param fulfillmentStatus the new fulfillment status
	 */
	public void setFulfillmentStatus(String fulfillmentStatus) {
		if( StringUtils.isEmpty(fulfillmentStatus) ) return;
		if( fulfillmentStatusTimeInMills == 0 || !fulfillmentStatus.equals(this.fulfillmentStatus) ) {
			fulfillmentStatusTimeInMills = Calendar.getInstance().getTimeInMillis();
		}
		this.fulfillmentStatus = fulfillmentStatus;
	}

	
	/**
	 * Gets the fulfillment status time in mills.
	 *
	 * @return the fulfillment status time in mills
	 */
	public long getFulfillmentStatusTimeInMills() {
		return fulfillmentStatusTimeInMills;
	}

	/**
	 * Sets the fulfillment status time in mills.
	 *
	 * @param fulfillmentStatusTimeInMills the new fulfillment status time in mills
	 */
	public void setFulfillmentStatusTimeInMills(long fulfillmentStatusTimeInMills) {
		this.fulfillmentStatusTimeInMills = fulfillmentStatusTimeInMills;
	}

	/**
	 * Gets the order fulfillment status change list.
	 *
	 * @return the order fulfillment status change list
	 */
	public ArrayList<EsdgOrderFulfillmentStatusHistoryDO> getOrderFulfillmentStatusChangeList() {
		return orderFulfillmentStatusChangeList;
	}

	/**
	 * Sets the order fulfillment status change list.
	 *
	 * @param orderFulfillmentStatusChangeList the new order fulfillment status change list
	 */
	public void setOrderFulfillmentStatusChangeList(
			ArrayList<EsdgOrderFulfillmentStatusHistoryDO> orderFulfillmentStatusChangeList) {
		this.orderFulfillmentStatusChangeList = orderFulfillmentStatusChangeList;
	}

	/**
	 * Adds the order fulfillment status.
	 *
	 * @param orderFulfillmentTypeCd the order fulfillment type cd
	 * @param fulfillmentTransactionJsonData the fulfillment transaction json data
	 * @param newOrderFulfillmentUpdateTimeInMills the new order fulfillment update time in mills
	 * @param isSuccessful the is successful
	 */
	public void addOrderFulfillmentStatus(String orderFulfillmentTypeCd, String fulfillmentTransactionJsonData, long newOrderFulfillmentUpdateTimeInMills, boolean isSuccessful) {
		if( orderFulfillmentStatusChangeList == null ) {
			orderFulfillmentStatusChangeList = new ArrayList<EsdgOrderFulfillmentStatusHistoryDO>();
		}
		EsdgOrderFulfillmentStatusHistoryDO orderFulfillmentStatusHistory = new EsdgOrderFulfillmentStatusHistoryDO();
		orderFulfillmentStatusHistory.setFulfillmentTypeCd(orderFulfillmentTypeCd);
		orderFulfillmentStatusHistory.setFulfillmentStatusTimeInMills(newOrderFulfillmentUpdateTimeInMills);
		orderFulfillmentStatusHistory.setFulfillmentTransactionJsonData(fulfillmentTransactionJsonData);
		orderFulfillmentStatusHistory.setSuccessful(isSuccessful);
		orderFulfillmentStatusChangeList.add(orderFulfillmentStatusHistory);
	}
	
	/**
	 * Gets the json order.
	 *
	 * @return the json order
	 */
	public String getJsonOrder() {
		return jsonOrder;
	}

	/**
	 * Sets the json order.
	 *
	 * @param jsonOrder the new json order
	 */
	public void setJsonOrder(String jsonOrder) {
		this.jsonOrder = jsonOrder;
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
		.append(" : orderStatus = ").append(orderStatus)
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
		.append(":").append(brandCd == null ? "" : brandCd)
		.append(":").append(externalKey == null ? "" : externalKey)
		.append(":").append(fulfillmentStatus == null ? "" : fulfillmentStatus)
		.append(":").append(Long.toString(fulfillmentStatusTimeInMills))
		.append(":").append(orderStatus == null ? "" : orderStatus)
		.append(":").append(salesContextExternalRefId == null ? "" : salesContextExternalRefId)
		.append(":").append(salesInteractionId == null ? "" : salesInteractionId)
		.append(":").append(Long.toString(salesInteractionStartTimeInMills))		
		.append(":").append(this.getJsonOrder());
		return sb.toString();
	}
	
	/**
	 * Log journal.
	 *
	 * @param isErrorLevel the is error level
	 */
	public void logJournal(boolean isErrorLevel) {
		if( StringUtils.isEmpty(this.getJsonOrder()) ) return;
		if( isErrorLevel ) {
			logger.error(getJournal());
		} else  {
			logger.info(getJournal());
		}
	}

	public boolean isMTSTransitionOrderInd() {
		return isMTSTransitionOrderInd;
	}

	public void setMTSTransitionOrderInd(boolean isMTSTransitionOrderInd) {
		this.isMTSTransitionOrderInd = isMTSTransitionOrderInd;
	}

	public String getBillingAccountNumber() {
		return BillingAccountNumber;
	}

	public void setBillingAccountNumber(String billingAccountNumber) {
		BillingAccountNumber = billingAccountNumber;
	}

	public String getSapOrderNum() {
		return sapOrderNum;
	}

	public void setSapOrderNum(String sapOrderNum) {
		this.sapOrderNum = sapOrderNum;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getShipmentTrackingNum() {
		return shipmentTrackingNum;
	}

	public void setShipmentTrackingNum(String shipmentTrackingNum) {
		this.shipmentTrackingNum = shipmentTrackingNum;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}


}
