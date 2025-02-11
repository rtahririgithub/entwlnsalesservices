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
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.helper.WirelineProductHelper;


// TODO: Auto-generated Javadoc
/**
 * QC 59376
 * The Class GetProductInstanceIdsListByCustomerTask.
 */
public class GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask extends TaskBase {

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask.class);

	/** The customer id. */
	private long customerId;

	/** The agent uid. */
	private String agentUid;

	/** The product id List. */
	List<String> idsList;

	/** The session id. */
	private long sessionId;

	/** The response. */
	private TaskExecutionResult<Map<String, SubscribedProductSummaryVO>> response = new TaskExecutionResult<Map<String, SubscribedProductSummaryVO>>();

	private WirelineProductHelper wirelineProductHelper;

	private IOrderQueryRestSvcAdapter orderQueryServiceAdapter;

	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.presale.task.PreSalesTask#getResponse()
	 */
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
	public GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask(long customerId, String agentUid, long sessionId,List<String> idsList, IOrderQueryRestSvcAdapter orderQueryServiceAdapter, WirelineProductHelper wirelineProductHelper) {
		super();
		this.customerId = customerId;
		this.sessionId = sessionId;
		this.orderQueryServiceAdapter = orderQueryServiceAdapter;
		this.wirelineProductHelper = wirelineProductHelper;
		this.agentUid = agentUid;
		this.idsList = idsList;
	}

	@Override
	public void execute() {
		String methodName = "GetProductInstanceIdsListByCustomerFromOrderQueryServiceTask";
		try {

			response.setTaskCompletionFlag(false);
			response.setServiceCallResponse(null);
			LoggerUtil.getLogger(this).info(methodName, "Start");
			GetProductsByCustomerIdAdapterRequest getProductsByCustomerIdAdapterRequest = new GetProductsByCustomerIdAdapterRequest();
			getProductsByCustomerIdAdapterRequest.setIncludedetails(true);
			getProductsByCustomerIdAdapterRequest.setInstanceidlist(idsList);
			getProductsByCustomerIdAdapterRequest.setCustomerid(customerId+"");
			
			//ProductList products = orderQueryServiceAdapter.retrieveAssignedProductDetailsWithIdsByCustomerId(customerId,agentUid,idsList);
			GetProductsByCustomerIdAdapterResponse products = orderQueryServiceAdapter.getProductsByCustomerId(getProductsByCustomerIdAdapterRequest );
			if( products == null || products.getProducts() == null ) {
				response.setServiceCallSuccessFlag(true);
				return;
			}

			List<Product> subProducts = products.getProducts();
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