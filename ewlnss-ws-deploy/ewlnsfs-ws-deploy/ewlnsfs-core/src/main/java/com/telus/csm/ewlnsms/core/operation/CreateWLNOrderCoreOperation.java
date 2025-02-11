package com.telus.csm.ewlnsms.core.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IOfferInformationServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterResponse;
import com.telus.csm.ewlnsc.delegate.ExternalOrderManager;
import com.telus.csm.ewlnsc.helper.OperationHeaderUtil;
import com.telus.csm.ewlnsc.task.FeasibilityServiceTask;
import com.telus.csm.ewlnsc.task.GetFullCustomerInfoTask;
import com.telus.csm.ewlnsc.task.GetProductsByCustomerIdTask;
import com.telus.csm.ewlnsc.task.OfferInformationServiceTask;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsms.core.domain.CreateWLNOrderCoreRequest;
import com.telus.csm.ewlnsms.core.domain.CreateWLNOrderCoreResponse;
import com.telus.csm.ewlnsms.core.domain.ProductAttributes;
import com.telus.csm.ewlnsms.core.domain.SalesOrderCommonObjectDO;
import com.telus.csm.ewlnsms.core.transformer.CreateWLNOrderTransformer;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import commonj.work.Work;

/**
 * @author x145592
 * 
 */
public class CreateWLNOrderCoreOperation {
	
	protected static final Logger LOGGER = Logger.getLogger(CreateWLNOrderCoreOperation.class);
	protected static final LoggerUtil loggerUtil = LoggerUtil.getLogger(CreateWLNOrderCoreOperation.class);
	
	SalesOrderCommonObjectDO salesCommonDO = new SalesOrderCommonObjectDO();
	
	public CreateWLNOrderCoreResponse execute(final CreateWLNOrderCoreRequest request) {
		final CreateWLNOrderCoreResponse result = new CreateWLNOrderCoreResponse();

		valiateInput();
		prepareCommonDO(request);

		final int orderId = createOrder(salesCommonDO);
		Boolean isProccessedInd = false;

		if (orderId != 0) {
//			validateOrder();
			ValidateProductConfigAdapterResponse response = validateOrder(orderId, request);
			if (! response.hasError())
				isProccessedInd = completeOrder(orderId, request); 
		}
		
		result.setOrderId(String.valueOf(orderId));
		result.setProcessInd(isProccessedInd);
		return result;
	}
	
	private void valiateInput() {
		//TODO: Add as validateInput the customerId, and the OfferIdList
		
		//TODO: from Allan: add a validation to throw an exception if any of the 3 appointmentID do not match:
//			televisionProduct.appointmentDetail.appointmentId
//			internetProduct.appointmentDetail.appointmentId
//			singleline.appointmentDetail.appointmentId.
		
	
	}

	private void prepareCommonDO() {
		
	}

	private int createOrder(SalesOrderCommonObjectDO request) {
		int result = 0;
		
		CreateOrderAdapterRequest createOrderAdapterRequest = CreateWLNOrderTransformer.transform(request);
		CreateOrderAdapterResponse createOrderAdapterResponse = ExternalOrderManager.createOrder(request.getOperationHeader(), createOrderAdapterRequest);
		if (createOrderAdapterResponse.hasError()){
			loggerUtil.error("Create order failed.");
		} else {
			loggerUtil.info("createOrder", "Create order executed successfully.");
			result = createOrderAdapterResponse.getId();
		}
		
		return result;
	}

	/*************************************/
	/* validateProductConfig             */
	/*************************************/
	private ValidateProductConfigAdapterResponse validateOrder(int orderId, CreateWLNOrderCoreRequest coreRequest) {
		ValidateProductConfigAdapterRequest request = this.transformValidateProductConfig(coreRequest, orderId);
		ValidateProductConfigAdapterResponse response = ExternalOrderManager.validateProductConfig(coreRequest.getOperationHeader(), request);
		if (response.hasError()){
			loggerUtil.error("validateOrder failed.");
		} else {
			loggerUtil.info("validateProductConfig", "Validation successful");
		}
		
		return response;
	}
	
	/*************************************/
	/* transformValidateProductConfig    */
	/*************************************/
	private ValidateProductConfigAdapterRequest transformValidateProductConfig(CreateWLNOrderCoreRequest coreRequest, int orderid){
		ValidateProductConfigAdapterRequest request = new ValidateProductConfigAdapterRequest();
		request.setOrderId(orderid);
		request.setOutboundChannel(coreRequest.isOutboundChannel());
		request.setReturnOrderDetails(true);
		
		return request;
	}

	private boolean completeOrder(final int orderId, CreateWLNOrderCoreRequest coreRequest) {
		String functionName="completeOrder";
		loggerUtil.enter(functionName);
		//Transform Booking Request
		ConfirmBookingAdapterRequest confirmBookingAdapterRequest = CreateWLNOrderTransformer.transformConfirmBookingRequest(orderId, salesCommonDO,coreRequest);
		//Calling BookingService.confirmBooking
		AdapterFeatureDriver adapterFeatureDriver = new AdapterFeatureDriver();
		adapterFeatureDriver.setSalesPersonRoleCode(coreRequest.getOperationHeader().getSalesPersonRoleCode());
		IWLNBookingRestSvcAdapter bookingAdapter = AdapterFactory.getAdapter(IWLNBookingRestSvcAdapter.class, adapterFeatureDriver);
		ConfirmBookingAdapterResponse confirmBookingResponse = bookingAdapter.confirmBooking(confirmBookingAdapterRequest);
		if (!confirmBookingResponse.hasError() ){
			loggerUtil.info(functionName, "WLNBookingService.confirmBooking call was successful");
		}
		
		SubmitOrderAdapterRequest request = new SubmitOrderAdapterRequest();
		request.setOrderid(String.valueOf(orderId));
		request.setMode("transfertobackoffice");
		SubmitOrderAdapterResponse response = ExternalOrderManager.submitOrder(coreRequest.getOperationHeader(), request);
		if (response.hasError()){
			loggerUtil.error("Submit order failed.");
			return false;
		} else {
			loggerUtil.info("submitOrder", "Order submitted successfully.");
			return true;
		}		
	}
	
	private void prepareCommonDO(final CreateWLNOrderCoreRequest request) {
		String functionName = "prepareCommonDO";
		loggerUtil.enter(functionName);
		
		//Setting the SalesOrder to the SalescommonObjectDO
		salesCommonDO.setSalesOrder(request.getCreateWirelineSalesOrder().getSalesOrder());
		salesCommonDO.setOperationHeader(request.getOperationHeader());
		
		//Prepare the parallel task to later populate the commonDO object
		List<Work> tasksList = new ArrayList<Work>();
		
		//GetProductsByCustomerIdTask
		IOrderQueryRestSvcAdapter orderQueryAdapter = AdapterFactory.getAdapter(IOrderQueryRestSvcAdapter.class, OperationHeaderUtil.getAdapterFeatureDriver(request.getOperationHeader()));
		GetProductsByCustomerIdAdapterRequest productByCustomerIdRequest = CreateWLNOrderTransformer.transformGetProductByCustomerIdRequest(request);
		GetProductsByCustomerIdTask productsByCustomerIdTask = new GetProductsByCustomerIdTask(orderQueryAdapter, productByCustomerIdRequest);
		tasksList.add(productsByCustomerIdTask);
		
		//GetProductAttributeMapTask -eventually will come from RefPDS, will be hard coded for now 
		populateProductAttributeMap(request);
		
		//GetOfferDetailTask
		IOfferInformationServiceAdapter offerInformationServiceAdapter = AdapterFactory.getAdapter(IOfferInformationServiceAdapter.class);
		GetOfferListByOfferIdentifierListAdapterRequest getOfferListByOfferIdListAdapterRequest = CreateWLNOrderTransformer.transformOfferInformationRequest(request);
		OfferInformationServiceTask offerInformationTask = new OfferInformationServiceTask(offerInformationServiceAdapter, getOfferListByOfferIdListAdapterRequest);
		tasksList.add(offerInformationTask);
	
		//GetFullCustomerInfoTask - TODO: Add cache for this adapter
		IConsumerCustomerMgmtSvcAdapter consumerCustomerServiceAdapter = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
		GetFullCustomerInfoAdapterRequest fullCustomerInfoAdapterRequest = CreateWLNOrderTransformer.transformGetFullCustomerInfoRequest(request);
		GetFullCustomerInfoTask getFullCustomerInfoTask = new GetFullCustomerInfoTask(fullCustomerInfoAdapterRequest, consumerCustomerServiceAdapter);
		tasksList.add(getFullCustomerInfoTask);
		
		//CheckProductFeasibilityTask
/*		IFeasibilityServiceAdapter feasibilityServiceAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class);
		CheckProductFeasibilityAdapterRequest checkProductFeasibilityAdapterRequest = CreateWLNOrderTransformer.transformCheckProductFeasibility(request);
		FeasibilityServiceTask feasibilityServiceTask = new FeasibilityServiceTask(feasibilityServiceAdapter, checkProductFeasibilityAdapterRequest);
		tasksList.add(feasibilityServiceTask);*/
		
		
		/**
		 * Running parallel tasks
		 */
		
		executeParallelCalls(tasksList);
		
	}

	private void populateProductAttributeMap(CreateWLNOrderCoreRequest request) {
		
		Map<String,ProductAttributes> productAttributesMap = CreateWLNOrderTransformer.populateProductAttributeMap(request);
		
		salesCommonDO.setProductAttributesMap(productAttributesMap);
	}

	private void executeParallelCalls(List<Work> tasksList) {
		String functionName="executeParallelCalls";
		loggerUtil.enter(functionName);
		Collection<Work> responseTaskList = null;
        try{
               ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
               responseTaskList = commonJMgr.processTasks(tasksList);
        } catch (Exception e){ 
        	loggerUtil.error(functionName, e.getMessage(), loggerUtil.getStackTrace(e));
        	throw new RuntimeException(e);
        }
        
        for(Work resultTask : responseTaskList){
        	if(resultTask!=null){
        		if(resultTask instanceof GetProductsByCustomerIdTask){
        			salesCommonDO.setProductsDO(((GetProductsByCustomerIdTask) resultTask).getResult());
        		}else if(resultTask instanceof OfferInformationServiceTask){
        			salesCommonDO.setOfferDO(((OfferInformationServiceTask) resultTask).getResult());
        		}else if(resultTask instanceof GetFullCustomerInfoTask){
        			salesCommonDO.setCustomerInfoDO(((GetFullCustomerInfoTask) resultTask).getResult());
        		}else if(resultTask instanceof FeasibilityServiceTask){
        			salesCommonDO.setProductFeasibilityDO(((FeasibilityServiceTask) resultTask).getResult());
        		}
        	}
        }
		loggerUtil.exit(functionName);
	}

}
