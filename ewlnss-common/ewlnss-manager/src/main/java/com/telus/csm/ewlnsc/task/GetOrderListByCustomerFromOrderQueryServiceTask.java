package com.telus.csm.ewlnsc.task;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.UNKNOWN_ACCOUNT;
import static com.telus.csm.ewlnsc.util.SalesServiceConstants.ORDER_QUERY_STATUS_EXECUTING;
import static com.telus.csm.ewlnsc.util.SalesServiceConstants.ORDER_QUERY_STATUS_INITIAL;
import static com.telus.csm.ewlnsc.util.SalesServiceConstants.PROPERTY_NAME_EXTERNAL_ORDER_ID;
import static com.telus.csm.ewlnsc.util.SalesServiceConstants.PROPERTY_NAME_EXTERNAL_ORDER_SOURCE;
import static com.telus.csm.ewlnsc.util.SalesServiceConstants.PROPERTY_NAME_SERVICE_ID;
import static com.telus.csm.ewlnsc.util.SalesServiceConstants.WIRELINE_SALES_EXTERNAL_ORDER_SOURCE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GET_ACCT_PROFILE_ERROR_303005_ID_MISSING_PENDING_ORDER_DETAILS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrdersByCustomerIdAndStatusAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrdersByCustomerIdAndStatusAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductCharacteristicValue;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrderList;
import com.telus.channelsalesmgmt.common.util.LoggerUtil;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.helper.WirelineProductHelper;
import com.telus.ucss.wirelinesales.ejb.OrderManager;

import weblogic.wsee.util.StringUtil;

/**
 * The Class GetProductInstanceListByCustomerTask.
 */
public class GetOrderListByCustomerFromOrderQueryServiceTask extends TaskBase {

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(GetOrderListByCustomerFromOrderQueryServiceTask.class);
	
	/** The customer id. */
	private long customerId;
	
	/** The agent uid. */
	private String agentUid;

	/** The session id. */
	private long sessionId;
	
	/** The sales transaction id. */
	private String txnId;
	
	/** The failed to reconcile vesta order. */
	private boolean failedToReconcileVestaOrder = false;
	
	boolean isFromException = false;
	
	/** The response. */
	private TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>> response = new TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>>();
	
	/** The response for getCustomerSummary*/
	private TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>> summaryResponse = new TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>>();
	
	//RTCA
	private Set<String> reconciledPendingOrderIds =new HashSet<String>();

	private WirelineProductHelper wirelineProductHelper;

	private IOrderQueryRestSvcAdapter orderQueryServiceAdapter;
	
	OrderManager orderManager;
	
	public TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>> getResponse() {
		return response;
	}

	/**
	 * Sets the response.
	 *
	 * @param response the new response
	 */
	public void setResponse(TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>> response) {
		this.response = response;
	}
	
	public TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>> getSummaryResponse() {
		return summaryResponse;
	}

	public void setSummaryResponse(
			TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>> summaryResponse) {
		this.summaryResponse = summaryResponse;
	}

	public Set<String> getReconciledPendingOrderIds() {
		return reconciledPendingOrderIds;
	}

	/**
	 * Instantiates a new gets the product instance list by customer task.
	 *
	 * @param customerId the customer id
	 * @param sessionId the session id
	 */
	public GetOrderListByCustomerFromOrderQueryServiceTask(long customerId, String agentUid, long sessionId, String txnId, IOrderQueryRestSvcAdapter orderQueryServiceAdapter, WirelineProductHelper wirelineProductHelper) {
		super();
		this.customerId = customerId;
		this.sessionId = sessionId;
		this.orderQueryServiceAdapter = orderQueryServiceAdapter;
		this.wirelineProductHelper = wirelineProductHelper;
		this.agentUid = agentUid;
		this.txnId = txnId;
	}

	@Override
	public void execute() {
		String methodName = "GetOrderListByCustomerFromOrderQueryServiceTask";
		try {
			String omsOrderId = null;
			String vestaChannelTransactionId = null;

			response.setTaskCompletionFlag(false);
			response.setServiceCallResponse(null);
			summaryResponse.setTaskCompletionFlag(false);
			summaryResponse.setServiceCallResponse(null);

			/**
			 * Calling Order Query Service: search order by customer ID
			 */
			List<String> statusList = new ArrayList<String>();
			statusList.add(ORDER_QUERY_STATUS_EXECUTING);
			statusList.add(ORDER_QUERY_STATUS_INITIAL);

			final GetOrdersByCustomerIdAndStatusAdapterRequest adapterRequest = new GetOrdersByCustomerIdAndStatusAdapterRequest();
			adapterRequest.setCustomerid(String.valueOf(customerId));
			adapterRequest.setOrdersStatusesList(statusList);
			
			final List<ProductOrder> productOrderList  = orderQueryServiceAdapter.searchOrdersByCustomerId(customerId, statusList, "", txnId);

			
			
			if ( productOrderList == null || productOrderList.isEmpty()) {
				response.setServiceCallSuccessFlag(true);
				summaryResponse.setServiceCallSuccessFlag(true);
				return;
			}
			
			/**
			 * Calling Order Query Service: get order details
			 */
			List<ProductOrder> orders = new ArrayList<ProductOrder>();
			main:
			for (ProductOrder productOrder : productOrderList) {
				// Should not return order with isShoppingCartOrder = true
				if(productOrder.isShoppingCartOrder()){
					List<ProductCharacteristicValue> prodCharacValueList = productOrder.getProductCharacteristics();					
					for (ProductCharacteristicValue prodCharacValue : prodCharacValueList != null ? prodCharacValueList : Collections.<ProductCharacteristicValue>emptyList()) {
						if ("waitState".equalsIgnoreCase(prodCharacValue.getName()) &&
							(prodCharacValue.getValue() == null || prodCharacValue.getValue().isEmpty())) {
							continue main;							
						}
					}					
				}
				
				String orderId = productOrder.getId();

				final GetOrderSummaryByOrderIdAdapterRequest adapterRequestOrderSummary = new GetOrderSummaryByOrderIdAdapterRequest();
				adapterRequestOrderSummary.setOrderId(orderId);
				adapterRequestOrderSummary.setPriceOffering(true);
				
				final ProductOrder productOrderDetail = orderQueryServiceAdapter.getOrderSummaryByOrderId(orderId, txnId, agentUid);
			
				if( (productOrderDetail == null) ||
					(productOrderDetail.getProductOrderItems() == null) ||
					(productOrderDetail.getProductOrderItems().isEmpty()) ) {
					response.setServiceCallSuccessFlag(false);
					summaryResponse.setServiceCallSuccessFlag(false);
					return;
				}

				
				// populate serviceId Map
				Map<String, String> serviceIdMap = new HashMap<String, String>();

				if (productOrderDetail.getProductOrderItems() != null && productOrderDetail.getProductOrderItems().size() > 0) {
					for (ProductOrderItem orderItem : productOrderDetail.getProductOrderItems()) {
						if (orderItem.getProduct() != null && orderItem.getProduct().getProductComponents() != null && orderItem.getProduct().getProductComponents().size() > 0) {
							
componentLoop:				for (Product component : orderItem.getProduct().getProductComponents()) {
								if (component.getProductCharacteristics() != null && component.getProductCharacteristics().size() > 0) {
									for (ProductCharacteristicValue charValue : component.getProductCharacteristics()) {
										if (PROPERTY_NAME_SERVICE_ID.equalsIgnoreCase(charValue.getName())) {
											serviceIdMap.put(orderItem.getProduct().getProductSerialNumber(), charValue.getValue());
											break componentLoop;
										}
									}
								}
							}

							// set serviceId to orderDetail
							for (Product component : orderItem.getProduct().getProductComponents()) {
								if ("orderActionAttributes".equalsIgnoreCase(component.getName()) && component.getProductCharacteristics() != null) {

									ProductCharacteristicValue charValue = new ProductCharacteristicValue();
									component.getProductCharacteristics().add(charValue);

									charValue.setName(PROPERTY_NAME_SERVICE_ID);
									charValue.setValue(serviceIdMap.get(orderItem.getProduct().getProductSerialNumber()));

									break;
								}
							}
						}
					}
				}

				orders.add(productOrderDetail);
			}
			
			Map<String, ArrayList<SubscribedProductSummaryVO>> summarySubcribedProductArrayMap = new HashMap<String, ArrayList<SubscribedProductSummaryVO>>();
			Map<String, ArrayList<SubscribedProductSummaryVO>> subcribedProductArrayMap = new HashMap<String, ArrayList<SubscribedProductSummaryVO>>();
			ArrayList<SubscribedProductSummaryVO> noSubcribedProductArray = new ArrayList<SubscribedProductSummaryVO>();
			
			summarySubcribedProductArrayMap.put(UNKNOWN_ACCOUNT, noSubcribedProductArray);
			
			for( ProductOrder order : orders ) {
				
				boolean isFromException = false;
				
				if( order == null ) continue;
				
				omsOrderId = order.getId();							
				if (null != wirelineProductHelper) // no need to call EJB for the logic from rest request
					vestaChannelTransactionId = getWirelineSalesOrderId(order);

				if( StringUtil.isEmpty(vestaChannelTransactionId) ) {
					failedToReconcileVestaOrder = true;
					convertOrderToSps(order, summarySubcribedProductArrayMap, UNKNOWN_ACCOUNT);
				} else {				
					try {
						log.debug("EJB call here..................");
						// TODO uncomment when the following method is ready
						ArrayList<SubscribedProductSummaryVO> subcribedProductArray = null;
						// ArrayList<SubscribedProductSummaryVO> subcribedProductArray = wirelineProductHelper.getPendingOrderByChannelTransactionId(vestaChannelTransactionId, sessionId);
						
						if( subcribedProductArray == null || subcribedProductArray.size() < 1 ) {
							failedToReconcileVestaOrder = true;
							String errorMsg = "Failed to find OMS pending offer in VESTA. externalReferenceNum (channel transaction id) = " + vestaChannelTransactionId + " / OMS order id = " + omsOrderId  + " / customer id = " + customerId;;
				 	        LoggerUtil.getLogger(this).error(GET_ACCT_PROFILE_ERROR_303005_ID_MISSING_PENDING_ORDER_DETAILS, methodName, errorMsg);
				 	        
				 	        //Below code to find product list
				 	       convertOrderToSps(order, summarySubcribedProductArrayMap, UNKNOWN_ACCOUNT);
						} else {
							//after reconcile, the accountNumber is in onetimepaychannel, while paychannel is in recurringpaychannel
							reconciledPendingOrderIds.add(vestaChannelTransactionId);
							//why use the sps from vesta order. should use from oms pending order
							String accountNumber = null;
							for(SubscribedProductSummaryVO sps : subcribedProductArray){
								sps.setProductInPendingOrderStatusInOMSInd(true);
							}	
							for(SubscribedProductSummaryVO sps : subcribedProductArray){
								accountNumber  = sps.getOneTimePayChannelNumber();
								if (accountNumber != null) break;
							}
							//we should use OMS order if the order is reconciled and we know the accountNumber
							if (accountNumber != null ) {
								summarySubcribedProductArrayMap.put(accountNumber, new ArrayList<SubscribedProductSummaryVO>());
								convertOrderToSps(order, summarySubcribedProductArrayMap, accountNumber);
							}
							
							if(!failedToReconcileVestaOrder){
								subcribedProductArrayMap.put(vestaChannelTransactionId, subcribedProductArray);
							}
						}
					} catch (Exception ex) {
						failedToReconcileVestaOrder = true;
						isFromException = true;
						String errorMsg = "Exception caught when reconciling missing pending OMS order detail. externalReferenceNum (channel transaction id) = " + vestaChannelTransactionId + " / OMS order id = " + omsOrderId  + " / customer id = " + customerId;;
						LoggerUtil.getLogger(this).error(GET_ACCT_PROFILE_ERROR_303005_ID_MISSING_PENDING_ORDER_DETAILS, methodName, errorMsg, ex);
					}
				}
				
				if (failedToReconcileVestaOrder && isFromException) {
					break;
				}
			}
			
			if( failedToReconcileVestaOrder ) {
				subcribedProductArrayMap.clear();				
			}
			
			response.setServiceCallResponse(subcribedProductArrayMap);
			response.setServiceCallSuccessFlag( true );
			summaryResponse.setServiceCallResponse(summarySubcribedProductArrayMap);
			summaryResponse.setServiceCallSuccessFlag(true);
		} catch (Exception e) {
			response.getErrors().add( e );
			response.setServiceCallSuccessFlag( false );
			summaryResponse.getErrors().add( e );
			summaryResponse.setServiceCallSuccessFlag( false );
		} finally {
			response.setTaskCompletionFlag( true );
			summaryResponse.setTaskCompletionFlag( true );
		}
	}

	
	private void convertOrderToSps(ProductOrder order, Map<String, ArrayList<SubscribedProductSummaryVO>> summarySubcribedProductArrayMap, String account) {
		List<ProductOrderItem> orderItemList = order.getProductOrderItems();
	    if(orderItemList != null && !orderItemList.isEmpty()) {
	       	for(ProductOrderItem orderItem : orderItemList) {
	       		SubscribedProductSummaryVO subscribedProduct = wirelineProductHelper.populateSubcribedProductFromOMSProductInstance(orderItem, sessionId);
	       		if(subscribedProduct != null && subscribedProduct.getProductInstance() != null){
	       			subscribedProduct.setProductInPendingOrderStatusInOMSInd(true);
		       		summarySubcribedProductArrayMap.get(account).add(subscribedProduct);
	       		}
	       	}
	    }
	}
	
	private String getWirelineSalesOrderId(ProductOrder order) {
		String externalOrderSource = null;
		String externalOrderId = null;
		
		if (order.getPartyInteractionRole() != null && order.getPartyInteractionRole().getProductCharacteristics() != null) {
			for (ProductCharacteristicValue productCharacteristicValue : order.getPartyInteractionRole().getProductCharacteristics()) {
				String name = productCharacteristicValue.getName();
				if (PROPERTY_NAME_EXTERNAL_ORDER_ID.equalsIgnoreCase(name)) {
					externalOrderId = productCharacteristicValue.getValue();
				} else if (PROPERTY_NAME_EXTERNAL_ORDER_SOURCE.equalsIgnoreCase(name)) {
					externalOrderSource = productCharacteristicValue.getValue();
				}
			}
		}
		List<ProductOrderItem> orderItemList = order.getProductOrderItems();
	    if(orderItemList != null && !orderItemList.isEmpty()) {
	       	for(ProductOrderItem orderItem : orderItemList) {
                  if (orderItem!=null)	{
                	  if (orderItem.getPartyInteractionRole()!=null){
                		  List<ProductCharacteristicValue> productCharacteritics = orderItem.getPartyInteractionRole().getProductCharacteristics();
                		  if (productCharacteritics!=null) {
	                		  for (ProductCharacteristicValue productCharacteristicValue : productCharacteritics) {
	                			  String name = productCharacteristicValue.getName();
	                			  if (PROPERTY_NAME_EXTERNAL_ORDER_SOURCE.equalsIgnoreCase(name)) {
	              					externalOrderSource = productCharacteristicValue.getValue();
	              				  }
	                		  }
                		  }
                	  }
                  }
	       	}
	    }
		

		if (WIRELINE_SALES_EXTERNAL_ORDER_SOURCE.equals(externalOrderSource)) {
			return externalOrderId;
		} else {
			return null;
		}
	}	
	
}
