package com.telus.csm.ewlnsc.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductList;
import com.telus.channelsalesmgmt.common.util.LoggerUtil;
import com.telus.csm.ewlnsc.adapter.scis.domain.SalesProductLineVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.helper.WirelineProductHelper;



/**
 * The Class GetProductInstanceListByCustomerTask.
 */
public class GetProductInstanceListByCustomerFromOrderQueryServiceTask extends TaskBase {

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(GetProductInstanceListByCustomerFromOrderQueryServiceTask.class);
		
	/** The customer id. */
	private long customerId;
	
	/** The agent uid. */
	private String agentUid;
	
	/** The session id. */
	private long sessionId;
	
	/** The transaction id. */
	private String salesTransactionId;
	
	/** The response. */
	private TaskExecutionResult<Map<String, SubscribedProductSummaryVO>> response = new TaskExecutionResult<Map<String, SubscribedProductSummaryVO>>();

	private WirelineProductHelper wirelineProductHelper;

	private IOrderQueryRestSvcAdapter orderQueryServiceAdapter;
	
	private GetProductsByCustomerIdAdapterResponse getProductsByCustomerIdAdapterResponse;
	
	public GetProductsByCustomerIdAdapterResponse getGetProductsByCustomerIdAdapterResponse() {
		return getProductsByCustomerIdAdapterResponse;
	}

	
	public TaskExecutionResult<Map<String, SubscribedProductSummaryVO>> getResponse() {
		return response;
	}
	
	/**
	 * Sets the response.
	 *
	 * @param response the response
	 */
	public void setResponse(TaskExecutionResult<Map<String, SubscribedProductSummaryVO>> response){
		this.response = response;
	}
	
	/**
	 * Instantiates a new gets the product instance list by customer task.
	 *
	 * @param customerId the customer id
	 * @param sessionId the session id
			*/
	public GetProductInstanceListByCustomerFromOrderQueryServiceTask(long customerId, String agentUid, long sessionId, String salesTransactionId, IOrderQueryRestSvcAdapter orderQueryServiceAdapter, WirelineProductHelper wirelineProductHelper) {
		super();
		this.customerId = customerId;
		this.sessionId = sessionId;
		this.orderQueryServiceAdapter = orderQueryServiceAdapter;
		this.wirelineProductHelper = wirelineProductHelper;
		this.agentUid = agentUid;
		this.salesTransactionId = salesTransactionId;
	}

	@Override
	public void execute() {
		String methodName = "GetProductInstanceListByCustomerFromOrderQueryServiceTask";
		try {
			 
			response.setTaskCompletionFlag(false);
			response.setServiceCallResponse(null);
			LoggerUtil.getLogger(this).info(methodName, "Start");
			GetProductsByCustomerIdAdapterRequest getProductsByCustomerIdAdapterRequest = new GetProductsByCustomerIdAdapterRequest();
			getProductsByCustomerIdAdapterRequest.setCustomerid(customerId+"");
			getProductsByCustomerIdAdapterRequest.setSalesTransactionId(salesTransactionId);
			getProductsByCustomerIdAdapterRequest.setIncludedetails(true);
			GetProductsByCustomerIdAdapterResponse getProductsByCustomerIdAdapterResponse = orderQueryServiceAdapter.getProductsByCustomerId(getProductsByCustomerIdAdapterRequest);
			this.getProductsByCustomerIdAdapterResponse = getProductsByCustomerIdAdapterResponse;
			
			if( getProductsByCustomerIdAdapterResponse == null || getProductsByCustomerIdAdapterResponse.getProducts() == null) {
				response.setServiceCallSuccessFlag(true);
				return;
			}
			
			List<Product> subProducts = getProductsByCustomerIdAdapterResponse.getProducts();
			if( subProducts == null || subProducts.size() < 1 ) {
				response.setServiceCallSuccessFlag(true);
				return;
			}
			
			//this map is to reconcile product instance from CODS
			HashMap<String, SubscribedProductSummaryVO> subcribedProductMap = new HashMap<String, SubscribedProductSummaryVO>();
			for( Product product : subProducts) {
				LoggerUtil.getLogger(this).debug(methodName, "**Start loop in Product list.");
				SubscribedProductSummaryVO subcribedProduct = null;
				subcribedProduct = wirelineProductHelper.populateSubcribedProductFromOMSProductList(product, sessionId);
				if( subcribedProduct != null ) {					
					if( SalesProductLineVO.HIGH_SPEED_INTERNET.equals(subcribedProduct.getProductLine()) ) {
						String[] ids = subcribedProduct.getProductInstance().getProductInstanceId().split(",");						
						if( ids != null && ids.length > 0 ) {
							//restore product instance id
							subcribedProduct.getProductInstance().setProductInstanceId(ids[0]);
							LoggerUtil.getLogger(this).debug(methodName, "**subcribedProduct.getProductInstance() id is "+subcribedProduct.getProductInstance().getProductInstanceId());
							for( int i = 0; i < ids.length; i++ ) {			
								//HSIC product instance id in CODS could be product instance id or main component id in OMS
								subcribedProductMap.put(ids[i], subcribedProduct);
							}
						}
					} else {
						subcribedProductMap.put(subcribedProduct.getProductInstance().getProductInstanceId(), subcribedProduct);
					}
				}
				LoggerUtil.getLogger(this).debug(methodName, "**Loop finished.");
			}
			//resolve related TN reference of assigned products under the same customer
			// TODO uncomment when the following method is ready 
			// wirelineProductHelper.resolveTNReferenceForHSICandTTV();
			
			response.setServiceCallResponse(subcribedProductMap);
			response.setServiceCallSuccessFlag( true );
		} catch (Exception e) {
			response.getErrors().add( e );
			response.setServiceCallSuccessFlag( false );
			LoggerUtil.getLogger(this).error("", methodName, e.getMessage(), e);
		} finally {
			response.setTaskCompletionFlag( true );
			LoggerUtil.getLogger(this).info(methodName, "Finished");
		}
	}
}
