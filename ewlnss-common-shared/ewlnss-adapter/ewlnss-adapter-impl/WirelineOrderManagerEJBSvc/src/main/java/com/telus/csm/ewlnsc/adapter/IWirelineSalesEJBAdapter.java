package com.telus.csm.ewlnsc.adapter;

import java.util.ArrayList; 
import java.util.Map;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;
import com.telus.ucss.wirelinesales.domain.Order;
import com.telus.ucss.wirelinesales.domain.OrderStatus;
import com.telus.ucss.wirelinesales.domain.UpdateOMSOrderRequest;
import com.telus.ucss.wirelinesales.exception.WLNSalesApplicationException;

public interface IWirelineSalesEJBAdapter extends IAdapterBase {

	/**
	 * 
	 * @param channelTransactionId
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public abstract ArrayList<SubscribedProductSummaryVO> getPendingOrderByChannelTransactionId(String channelTransactionId, long sessionId) throws Exception;
	/**
	 * 
	 * @param customerId
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public abstract Map<String, ArrayList<SubscribedProductSummaryVO>> getPendingOrdersByCustomerId(long customerId, long sessionId) throws Exception;
	
	/**
	 * 
	 * @param vestaOrderId
	 * @param newComment
	 * @param commentType
	 * @throws WLNSalesApplicationException
	 */
	void insertComment(String vestaOrderId, String newComment, String commentType) throws WLNSalesApplicationException;

	/**
	 * 
	 * @param vestaOrderId
	 * @param orderStatus
	 * @param oldStatus
	 * @throws WLNSalesApplicationException
	 */
	boolean updateOrderStatus(String vestaOrderId, OrderStatus orderStatus, String oldStatus) throws WLNSalesApplicationException;
	
	/**
	 * 
	 * @param updateOMSRequest
	 * @return
	 * @throws WLNSalesApplicationException
	 */
	boolean updateOMSOrderStatus(UpdateOMSOrderRequest updateOMSRequest)  throws WLNSalesApplicationException;
	
	/**
	 * 	
	 * @return
	 * @throws WLNSalesApplicationException
	 */
	Order getOrder(Long orderId) throws WLNSalesApplicationException;
	/**
	 * 
	 * @param orderId
	 * @param invoiceNumber
	 * @return
	 * @throws WLNSalesApplicationException
	 */
	boolean updateOrderDepositInvoiceNumber(String orderId, Double amount, String invoiceNumber) throws WLNSalesApplicationException;
	/**
	 * 
	 * @param orderId
	 * @param invoiceNumber
	 * @return
	 * @throws WLNSalesApplicationException
	 */
	boolean addNewVestaToSfdcStg(long orderId) throws WLNSalesApplicationException;
	/**
	 * 
	 * @param orderId
	 * @param invoiceNumber
	 * @return
	 * @throws WLNSalesApplicationException
	 */
	Order getOrderByTransactionId(String orderId) throws WLNSalesApplicationException;	
	
}
