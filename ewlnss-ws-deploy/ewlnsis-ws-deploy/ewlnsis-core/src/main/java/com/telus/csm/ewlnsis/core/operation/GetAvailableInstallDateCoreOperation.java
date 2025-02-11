package com.telus.csm.ewlnsis.core.operation;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.CUSTOMER_ELIG_MISSING_CHANNEL_ORG_ID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_ORDER_SERVICE_TYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_PLAN_CD_FOR_HSIC;
import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils.isCollectionHasValues;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IReferencePDSDataServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreRequest;
import com.telus.csm.ewlnsc.domain.GetAvailableInstallDateCoreResponse;
import com.telus.csm.ewlnsc.helper.ValidationUtil;
import com.telus.csm.ewlnsc.task.FeasibilityServiceTask;
import com.telus.csm.ewlnsc.task.GetAvailableTimeSlotsTask;
import com.telus.csm.ewlnsc.task.ReferencePDSDataServiceTask;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsis.core.exceptions.BookingDateCalculationException;
import com.telus.csm.ewlnsis.core.exceptions.LagTimeException;
import com.telus.csm.ewlnsis.core.transformer.GetAvailableInstallDateCoreTransformer;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductSummary;
import commonj.work.Work;
/**
 * GetAvailableInstallDateCoreOperation
 * 
 * @author x159148
 *
 */
public class GetAvailableInstallDateCoreOperation {
 
	
	private static final String CLASS_NAME      = "GetAvailableInstallDateCoreOperation";
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableInstallDateCoreOperation.class);
	
	/**
	 * execute
	 * 
	 * @param request
	 * @return GetAvailableInstallDateCoreResponse
	 */
	public GetAvailableInstallDateCoreResponse execute(GetAvailableInstallDateCoreRequest request){
		
		String functionName = CLASS_NAME + "::" + "execute";
		GetAvailableInstallDateCoreResponse response = new GetAvailableInstallDateCoreResponse();
		
		GetAvailableInstallDateCoreTransformer transformer = new GetAvailableInstallDateCoreTransformer();
		List<String> missingElementList                    = new ArrayList<String>();
		List<String> invalidInputList                      = new ArrayList<String>();
		logger.enter(functionName);
		
		try{
			
			/***********************************/
			/* step 1 - perform validation     */
			/***********************************/
			this.validateInput(request, missingElementList, invalidInputList);
			
			if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
				response.setMessageList(generateMessageList(missingElementList, invalidInputList, new ArrayList<String>(), request.getOperationHeader()));
				logger.info(functionName, "Input Validation failed."); 
				return response;
			}

			/**************************************/
			/* step 2 - getAvailableInstallDate   */
			/**************************************/
			response = this.getAvailableInstallDates(request);
			 
		} catch(LagTimeException mte) { 
			
			logger.error(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR + 
					EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, 
					functionName, mte.getMessage());
			return transformer.transformException(mte, request.getOperationHeader().getSalesTransactionId());
			
		} catch(BookingDateCalculationException bdce){
			
			logger.error(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR + 
					EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, functionName, bdce.getMessage());
			return transformer.transformException(bdce, request.getOperationHeader().getSalesTransactionId());
			
		} catch(Exception ex){
			
			logger.error(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR + 
					EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, functionName, ex.getMessage());
			return transformer.transformException(ex, request.getOperationHeader().getSalesTransactionId());
			
		} finally {
			logger.exit(functionName);
		}
		
		return response;
	}
	
	
	
	/********************************************/
	/*  getAvailableInstallDates                */
	/********************************************/
	private GetAvailableInstallDateCoreResponse getAvailableInstallDates(GetAvailableInstallDateCoreRequest request) throws LagTimeException,
	                                                                                                                        BookingDateCalculationException{
		
		String functionName = CLASS_NAME + "::" + "getAvailableInstallDates";
		logger.enter(functionName);
		GetAvailableInstallDateCoreResponse response;
		
		/********************************************/
		/*  setup and call feasibilityAdapter       */
		/********************************************/
		
		/**
		 * Step 1 - Prepare first round of Parallel Calls - FeasibilitService and ReferencePDSService
		 */
		CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse = new CheckProductFeasibilityAdapterResponse();
		GetReferencePDSDataServiceAdapterResponse referencePDSAdapterResponse         = new GetReferencePDSDataServiceAdapterResponse();
		GetAvailableInstallDateCoreTransformer transformer                            = new GetAvailableInstallDateCoreTransformer();
				
		List<Work> workList = prepareFirstRoundAsyncTask(request);
		
		/* Step 1 - a : Process Async Tasks*/
		Collection<Work> firstRoundResponseTaskList = null;
        try{
               ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
               firstRoundResponseTaskList = commonJMgr.processTasks(workList);
        } catch (Exception e){ 
               return transformer.transformException(e, request.getOperationHeader().getSalesTransactionId());
        }
        
        /**
         *  Step 2: Getting the results from Async Tasks processing
         */
        for(Work resultTask : firstRoundResponseTaskList){
        	if(resultTask!=null){
        		if(resultTask instanceof ReferencePDSDataServiceTask){
            		referencePDSAdapterResponse =((ReferencePDSDataServiceTask) resultTask).getResult();
            	}if(resultTask instanceof FeasibilityServiceTask){
            		checkProductFeasibilityAdapterResponse =((FeasibilityServiceTask) resultTask).getResult();
            	}
        	}	
        }
		
		/**
		 * Step 3 - check response from feasibilityAdapter, return if error found
		 */
		response = this.validateFeasibilityAdapterResponse(checkProductFeasibilityAdapterResponse, transformer, request);
		if (response.hasError()){
			return response;
		}
		
		
		/**
		 * Step 4 - Checking the Response for ReferencePDSDataService
		 */
		Map<String,String> nguiInstallLagTime;
		if(referencePDSAdapterResponse!=null && !referencePDSAdapterResponse.getReferencePDSTableMap().isEmpty()){
			nguiInstallLagTime = referencePDSAdapterResponse.getReferencePDSTableMap();
		}else{
			//Logic to return Error in case the RefPDS Response is not found
			throw new LagTimeException("Exception happened when trying to Get ReferencePDSDataService Response");
		}
				
		this.validateRefPdsResponse(nguiInstallLagTime);
		
		
		/**************************************************/
		/*  setup WorkManager for BookingServiceAdapter   */
		/**************************************************/
		
		/**
		 * Step 5 - build list of RW/FW GetAvailableTimeSlotsAdapterRequest
		 */
		List<GetAvailableTimeSlotsAdapterRequest> bookingServiceRequests = transformer.buildGetTimeSlotRequests(request, checkProductFeasibilityAdapterResponse, nguiInstallLagTime);
		
		
		List<GetAvailableTimeSlotsTask> getAvailableTimeSlotsTaskResult = new ArrayList<GetAvailableTimeSlotsTask>();
		if (bookingServiceRequests.size() > 0) {
			/**
			 * Step 6 - get adapter
			 */
			List<Work> tasksList = new ArrayList<Work>();
			IWLNBookingRestSvcAdapter adapter = AdapterFactory.getAdapter(IWLNBookingRestSvcAdapter.class, new AdapterFeatureDriver());
			
			
			/**
			 * Step 7 - add tasks to work manager
			 */  
			for(GetAvailableTimeSlotsAdapterRequest getAvailableTimeSlotsAdapterRequest : bookingServiceRequests){  
				tasksList.add(new GetAvailableTimeSlotsTask(adapter, getAvailableTimeSlotsAdapterRequest));  
			}
			 
			
			/**
			 * Step 8 - call WorkManager to perform parallel process of BookingServices
			 */
			long start = System.currentTimeMillis();
			Collection<Work> responseTaskList = null;
	        try{
	               ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
	               responseTaskList = commonJMgr.processTasks(tasksList);
	        } catch (Exception e){ 
	               return transformer.transformException(e, request.getOperationHeader().getSalesTransactionId());
	        }
	        long end = System.currentTimeMillis();
	        NumberFormat formatter = new DecimalFormat("#0.00000");
	        logger.info(functionName, "\n**********************************\n* Total BookingService execution time is " + formatter.format((end - start) / 1000d) + " seconds *\n**********************************");
	        
	        /**
	         * Step 9 - get input and result from executed tasks
	         * 
	         * Need to get the task instead of just result.  The result does not have list of serviceType
	         */
	        
	        for (Work workElement : responseTaskList){
	        	if (workElement instanceof GetAvailableTimeSlotsTask ){
	        		getAvailableTimeSlotsTaskResult.add((GetAvailableTimeSlotsTask) workElement);
	        	}
	        }
		}  
		
        /**
         * step 10 - transform bookingService tasks to GetAvailableInstallDateCoreResponse 
         */
        response = transformer.transformToGetAvailableInstallDateResponse(getAvailableTimeSlotsTaskResult,    
        		                                                          request,
        		                                                          checkProductFeasibilityAdapterResponse,request.getOperationHeader().getSalesTransactionId()); 

		 
		
        logger.exit(functionName);
		return response;
	}
	
	
	protected void validateRefPdsResponse(Map<String,String> nguiInstallLagTime) throws LagTimeException{
		//Assume if table is empty, error occurred
		String functionName="validateRefPdsResponse";
		if (nguiInstallLagTime.isEmpty()){
			throw new LagTimeException("Fail to get lagtime from RefPDS");
		} 
		Set<String> keySet = nguiInstallLagTime.keySet();
		for (String key : keySet) {
			String lagTimeStr = nguiInstallLagTime.get(key);
			if (!GetAvailableInstallDateUtils.isInteger(lagTimeStr)) {
				logger.error(null, CLASS_NAME + functionName, "lag time (" + lagTimeStr + ") is not valid integer");
				throw new LagTimeException("lag time (" + lagTimeStr + ") is not valid integer");
			}
		}
	}
	
	protected FeasibilityServiceTask prepareFeasibilityRequestTask(GetAvailableInstallDateCoreRequest request){
		String functionName="prepareFirstRoundAsyncTask";
		logger.enter(functionName);
		//Call Feasibility Service and ReferencePDSService in this round of parallel calls
		GetAvailableInstallDateCoreTransformer transformer = new GetAvailableInstallDateCoreTransformer();
		CheckProductFeasibilityAdapterRequest checkProductFeasibilityAdapterRequest = transformer.transformCoreRequestToFeastibilityRequest(request);
		IFeasibilityServiceAdapter feasibilityAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class, new AdapterFeatureDriver());
		 
		//Preparing Task for FeasibilityService
		
		FeasibilityServiceTask feasibilitTask = new FeasibilityServiceTask(feasibilityAdapter, checkProductFeasibilityAdapterRequest);
	
		return feasibilitTask;
	}
	
	protected ReferencePDSDataServiceTask prepareRefPdsTask(GetAvailableInstallDateCoreRequest request){
		 IReferencePDSDataServiceAdapter refPDSAdapter = AdapterFactory.getAdapter(IReferencePDSDataServiceAdapter.class, new AdapterFeatureDriver());
		
		String appId = EnterpriseWLNSalesServicesConstants.EWLNSS_APP_ID;
		
		GetReferencePDSDataServiceAdapterRequest adapterRequest = new GetReferencePDSDataServiceAdapterRequest(appId);
		
		ReferencePDSDataServiceTask refPDSTask = new ReferencePDSDataServiceTask(refPDSAdapter, adapterRequest, EnterpriseWLNSalesServicesConstants.REFPDS_LAG_TIME_TABLE);
		 
		
		return refPDSTask;
	}
	
	
	protected List<Work> prepareFirstRoundAsyncTask(GetAvailableInstallDateCoreRequest request){
		String functionName="prepareFirstRoundAsyncTask";
		logger.enter(functionName);
		
		List<Work> tasksList = new ArrayList<Work>();
		tasksList.add(this.prepareFeasibilityRequestTask(request));
		tasksList.add(this.prepareRefPdsTask(request));
		
		
//		//Call Feasibility Service and ReferencePDSService in this round of parallel calls
//		GetAvailableInstallDateCoreTransformer transformer = new GetAvailableInstallDateCoreTransformer();
//		CheckProductFeasibilityAdapterRequest checkProductFeasibilityAdapterRequest = transformer.transformCoreRequestToFeastibilityRequest(request);
//		IFeasibilityServiceAdapter feasibilityAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class, new AdapterFeatureDriver());
//		List<Work> tasksList = new ArrayList<Work>();
//		
//		//Preparing Task for FeasibilityService
//		
//		FeasibilityServiceTask feasibilitTask = new FeasibilityServiceTask(feasibilityAdapter, checkProductFeasibilityAdapterRequest);
//	
//		tasksList.add(feasibilitTask);
//		
//		//Preparing Task for RefPDS 
//		
//		IReferencePDSDataServiceAdapter refPDSAdapter = AdapterFactory.getAdapter(IReferencePDSDataServiceAdapter.class, new AdapterFeatureDriver());
//		
//		String appId = EnterpriseWLNSalesServicesConstants.EWLNSS_APP_ID;
//		
//		GetReferencePDSDataServiceAdapterRequest adapterRequest = new GetReferencePDSDataServiceAdapterRequest(appId);
//		
//		ReferencePDSDataServiceTask refPDSTask = new ReferencePDSDataServiceTask(refPDSAdapter, adapterRequest, EnterpriseWLNSalesServicesConstants.REFPDS_LAG_TIME_TABLE);
//		
//		tasksList.add(refPDSTask);
		logger.exit(functionName);
		return tasksList;
	}
	
	/*****************************************/
	/* validateFeasibilityAdapterResponse    */
	/*****************************************/
	protected GetAvailableInstallDateCoreResponse validateFeasibilityAdapterResponse(CheckProductFeasibilityAdapterResponse feasibilityResponse,
			                                                                       GetAvailableInstallDateCoreTransformer transformer,
			                                                                       GetAvailableInstallDateCoreRequest     request){
		
		/**
		 * feasibilityResponse would only consider ERROR if there is an exception thrown or no productFeasibilityInfoList return.
		 * Otherwise, even if there are ERROR messages, it will continue to process
		 */
		String functionName = "handleFeasibilityAdapterResponse";
		GetAvailableInstallDateCoreResponse response = new GetAvailableInstallDateCoreResponse();
		
		/**
		 * Step 1 - transform response message
		 */
		if (feasibilityResponse.getResponseMessageList()  != null && isCollectionHasValues(feasibilityResponse.getResponseMessageList().getResponseMessage()) && feasibilityResponse.getProductFeasibilityInfoList()==null){
			
			List<ResponseMessage> responseMsg = feasibilityResponse.getResponseMessageList().getResponseMessage();
			
			List<SalesResponseMessage.MessageList> msgList = 
					transformer.transformResponseMessageToSalesResponseMessage(responseMsg, request.getOperationHeader().getSalesTransactionId());
			
			response.setMessageList(msgList);
		} 
		
		/**
		 * Step 2 - transform exception to response and set msgHasError to true
		 */
		if (feasibilityResponse.getAdapterResponseMessage() != null){
			SalesResponseMessage.MessageList msg = new SalesResponseMessage.MessageList();
			
			AdapterResponseMessage rspMsg = feasibilityResponse.getAdapterResponseMessage();
			
			msg.setContextData(rspMsg.getContextData());
			msg.setErrorCode(rspMsg.getMessageCode());
			
			if (isCollectionHasValues(rspMsg.getMesssageDescriptionTextList())){
				
				List<Message> msgList = new ArrayList<Message>();
				for (AdapterResponseMessageDesc rspMsgDesc : rspMsg.getMesssageDescriptionTextList()){
					Message newMsg = new Message();
					newMsg.setLocale(rspMsgDesc.getLocale().getDisplayName());
					newMsg.setMessage(rspMsgDesc.getMessageDescriptionText());
					msgList.add(newMsg);
				}
				msg.setMessageList(msgList);
			}
			
			msg.setMessageType(rspMsg.getMessageType()); 
			msg.setTransactionId(request.getOperationHeader().getSalesTransactionId());
			
			response.setMsgHasError(true);
			response.setTransactionId(request.getOperationHeader().getSalesTransactionId());
			response.addMsg(msg);
		}
		
		/**
		 * Step 3 - check response - if the response is empty or null, return error
		 *          message should already been set in step 1
		 */
		if (feasibilityResponse.getProductFeasibilityInfoList() == null || feasibilityResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo() == null ||
				feasibilityResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo().isEmpty())
		{
			response.setMsgHasError(true);
		}
		
		
		if (feasibilityResponse.isMsgHasError())
			logger.info(functionName, "Encounter exception from downstream");

		return response;
	}

	/*****************************************/
	/* validateStartEndDate                  */
	/*****************************************/
	protected void validateStartEndDate(GetAvailableInstallDateCoreRequest rq, List<String> missingList, List<String> invalidInputList) {
		
		String functionName =  CLASS_NAME + "." + "validateStartEndDate";
		logger.enter(functionName);
		
		/*****************************/
		/* missing startDate         */ 
		/*****************************/
		if (rq.getStartDate() == null || validateDate(rq.getStartDate())) {
 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_START_DATE);
		}
		
		/*****************************/
		/* missing endDate           */ 
		/*****************************/
		if (rq.getEndDate() == null || validateDate(rq.getEndDate())) {
 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_END_DATE);
		}
		
		/*****************************/
		/* validate start/end date   */
		/*****************************/
		
		Date currentDay = EnterpriseSalesServiceUtil.removeTime(new Date());
		if(rq.getStartDate()!=null && rq.getEndDate()!=null){
			
		
		Date startDate  = EnterpriseSalesServiceUtil.removeTime(rq.getStartDate());
		Date endDate    = EnterpriseSalesServiceUtil.removeTime(rq.getEndDate());
		
		// startDate must be on or after SYSDATE + 1
		Date tomorrow = EnterpriseSalesServiceUtil.addDays(currentDay, 1);
		if ( startDate != null && tomorrow.compareTo(startDate) > 0){
 			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_START_DATE);
		}
		
		// end date must be after start date
		if (endDate != null && endDate.compareTo(startDate) < 0){
 			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_END_DATE);
		}
		
		// end date must be on or before startDate + 31d
		Date startDatePlusMaxDays = EnterpriseSalesServiceUtil.addDays(startDate, GetAvailableInstalLDateConstants.getMaxDays());
		if (endDate != null && endDate.compareTo(startDatePlusMaxDays) >= 0){
			
			String msg = EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_DATE_RANGE;
 			invalidInputList.add(String.format(msg, GetAvailableInstalLDateConstants.getMaxDays() ));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if (!missingList.isEmpty()){
			sb.append("Valdation fail missing following:  ");
			for (String msg : missingList){
				sb.append("[" + msg + "]");
			}
		}
		
		if (!invalidInputList.isEmpty()){
			sb.append("Valdation fail invalid input:  ");
			for (String msg : invalidInputList){
				sb.append("[" + msg + "]");
			}
		}
		
		logger.error(null, "validateInput", sb.toString());
		logger.exit(functionName);
	}
	
	/*****************************************/
	/* validateInput                         */
	/*****************************************/
	protected void validateInput(GetAvailableInstallDateCoreRequest rq, List<String> missingList, List<String> invalidInputList) {
		this.validateCoreInput(rq, missingList, invalidInputList);
		this.validateStartEndDate(rq, missingList, invalidInputList);
	}
 
	/*****************************************/
	/* validateCoreInput                     */
	/*****************************************/
	protected void validateCoreInput(GetAvailableInstallDateCoreRequest rq, List<String> missingList, List<String> invalidInputList) {
		
		String functionName =  CLASS_NAME + "." + "validateCoreInput";
		logger.enter(functionName);
		
		//Using ValidationUtil.java to validate fields in OP header
		ValidationUtil.validateHeader(rq.getOperationHeader(), missingList, invalidInputList);
		if(missingList.contains(EnterpriseWLNSalesServicesErrorCodes.MISSING_OP_HEADER)){
			return;
		}
		
		/***************************/
		/* missing Channel Org Id  */
		/***************************/
		if (rq.getOperationHeader().getUserProfile() != null
				&& StringUtils.isBlank(rq.getOperationHeader().getUserProfile().getChnlOrgNumber())) {
 			missingList.add(CUSTOMER_ELIG_MISSING_CHANNEL_ORG_ID);
		}
		
		/*****************************/
		/* missing Channel Outlet Id */
		/*****************************/
		if (rq.getOperationHeader().getUserProfile() != null
				&& rq.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList() != null
				&& !rq.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
				&& StringUtils.isBlank(rq.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0)
						.getSalesRepAssociatedChannelOutletId())) {
 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_CHANNEL_OUTLET_ID);
		}
		
		
		/*****************************/
		/* missing Sales Rep Id      */
		/*****************************/
		if (rq.getOperationHeader().getUserProfile() != null
				&& StringUtils.isBlank(rq.getOperationHeader().getUserProfile().getSalesRepId())) {
 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SALES_REP_ID);
		}
		
		/*****************************/
		/* missing timestamp         */
		/*****************************/
		if (rq.getOperationHeader().getSalesTransactionTimestamp() == null) {
 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_TRANSACTION_TIMESTAMP);
		}
		
	
		/*****************************/
		/* brand code must be TELUS  */
		/*****************************/
		if (! rq.getOperationHeader().getBrandCode().equalsIgnoreCase(Constants.TELUS)){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_BRAND_CODE);
		}
		
//		/*****************************/
//		/* missing startDate         */ 
//		/*****************************/
//		if (rq.getStartDate() == null || validateDate(rq.getStartDate())) {
// 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_START_DATE);
//		}
//		
//		/*****************************/
//		/* missing endDate           */ 
//		/*****************************/
//		if (rq.getEndDate() == null || validateDate(rq.getEndDate())) {
// 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_END_DATE);
//		}
		 
		/*****************************/
		/* missing orderProductList  */ 
		/*****************************/
		if (rq.getOrderProductList() == null || rq.getOrderProductList().isEmpty()) {
 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_ORDER_PRODUCT_LIST);
		}
		
		/*****************************/
		/* missing serviceAddress    */ 
		/*****************************/
		if (rq.getServiceAddress() == null ) {
 			missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS);
		}
		
		/*****************************/
		/* missing address elements  */ 
		/*****************************/
		if (rq.getServiceAddress() != null ) {
			
			ServiceAddress serviceAddress = rq.getServiceAddress();
			
			/*****************************/
			/* missing serviceAddressId  */ 
			/*****************************/
			if (StringUtils.isBlank(serviceAddress.getAddressId())){
 				missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_ID);
			}
			
			/*****************************/
			/* missing municipalityName  */ 
			/*****************************/
			if (StringUtils.isBlank(serviceAddress.getMunicipalityName())){
 				missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_MUNICIPALITY_NAME);
			}
			
			/*****************************/
			/* missing provinceStateCd   */ 
			/*****************************/
			if (StringUtils.isBlank(serviceAddress.getProvinceStateCode())){
 				missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_PROVINCE_STATE_CODE);
			}
			
			/*****************************/
			/* missing streetName        */ 
			/*****************************/
			if (StringUtils.isBlank(serviceAddress.getStreetName())){
 				missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_ADDRESS_STREET_NAME);
			}
		}
		
		
		
		
		/*****************************/
		/* validate orderProducts    */ 
		/*****************************/
		if (rq.getOrderProductList() != null && !rq.getOrderProductList().isEmpty()){
			
			for (WirelineProductSummary product : rq.getOrderProductList()){
				String actionTypeCd = product.getActionTypeCd();
				String serviceTypeCd = product.getServiceType();
				
				/*****************************/
				/* missing actionTypeCd      */
				/*****************************/
				if(StringUtils.isBlank(actionTypeCd)){
					missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_ACTION_TYPE_CD);
				}else if(!GetAvailableInstalLDateConstants.PROVIDE.equalsIgnoreCase(actionTypeCd) && !GetAvailableInstalLDateConstants.CHANGE.equalsIgnoreCase(actionTypeCd)){
					invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_ACTION_TYPE_CD);
				}
				
				if (actionTypeCd.equalsIgnoreCase(GetAvailableInstalLDateConstants.MODIFY)
						|| actionTypeCd.equalsIgnoreCase(GetAvailableInstalLDateConstants.CHANGE)) {

					if (rq.getSubscriberProductList() == null || rq.getSubscriberProductList().isEmpty()) {
						// If orderActionTypeCd is Modify or change, then
						// existingProduct(subscribedProductList) must be
						// present in ESS request
						missingList.add(
								EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SUBSCRIBER_PRODUCTS);
					}else{
						//Iterating over the subscriberProductList
						List<String> existingProductList = new ArrayList<String>();
						for (WirelineProductSummary existingProduct : rq.getSubscriberProductList()){
							existingProductList.add(existingProduct.getServiceType());
						}
						if(!existingProductList.contains(serviceTypeCd)){
							//throw error
							missingList.add(
									EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SUBSCRIBER_PRODUCT_SERVICE_TYPE);
						}
					}
				}
				
				/*****************************/
				/* missing productType       */
				/*****************************/
				if (product.getProductType() == null || product.getProductType().trim().length() == 0){
					missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_PRODUCT_TYPE);
 				}
				
				/*****************************/
				/* missing serviceType       */
				/*****************************/
				if (product.getServiceType() == null || product.getServiceType().trim().length() == 0){
					missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_TYPE);
 				}
				/*****************************/
				/* valid serviceType         */
				/*****************************/
				else if (!serviceTypeList().contains(product.getServiceType())){
					invalidInputList.add(GET_AVAIL_INST_DATE_INVALID_ORDER_SERVICE_TYPE);
 				}
								
				/***************************************************/
				/* missing servicePlanCd  if serviceType is HSIC   */
				/***************************************************/
				if (!StringUtils.isBlank(product.getServiceType()) && product.getServiceType().equalsIgnoreCase(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS) && StringUtils.isBlank(product.getServicePlanCd())){
					missingList.add(GET_AVAIL_INST_DATE_MISSING_SERVICE_PLAN_CD_FOR_HSIC);
				}
			}
		}
		
		/*****************************************/
		/* validate SubscriberService.serviceId  */
		/*****************************************/
		if (rq.getSubscriberProductList() != null && !rq.getSubscriberProductList().isEmpty()){
			for (WirelineProductSummary product : rq.getSubscriberProductList()){
				
				/*****************************/
				/* missing serviceId         */
				/*****************************/
				if (product.getProductInstance() == null || product.getProductInstance().getServiceId() == null || product.getProductInstance().getServiceId().trim().length() == 0){
					missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SERVICE_ID);
 				}
				/*****************************/
				/* missing productCatalogId         */
				/*****************************/
				if(product.getProductInstance()!=null && StringUtils.isBlank(product.getProductInstance().getProductCatalogId())){
					missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_PRODUCT_CATALOG_ID);
				}
				
				/********************************/
				/* missing/invalid serviceType  */
				/********************************/
				if (product.getServiceType() == null || product.getServiceType().trim().length() == 0){
					missingList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SUB_PROD_SERVICE_TYPE);
 				}
				List<String> availableServiceTypes = serviceTypeList();
				
				if(product.getServiceType() != null && 
				   product.getServiceType().trim().length() > 0 && 
				   !availableServiceTypes.contains(product.getServiceType()))
				{
					invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_MISSING_SUB_PROD_INV_SERVICE_TYPE);
					
				}
				
			}
		}
		
//		/*****************************/
//		/* validate start/end date   */
//		/*****************************/
//		
//		Date currentDay = EnterpriseSalesServiceUtil.removeTime(new Date());
//		if(rq.getStartDate()!=null && rq.getEndDate()!=null){
//			
//		
//		Date startDate  = EnterpriseSalesServiceUtil.removeTime(rq.getStartDate());
//		Date endDate    = EnterpriseSalesServiceUtil.removeTime(rq.getEndDate());
//		
//		// startDate must be on or after SYSDATE + 1
//		Date tomorrow = EnterpriseSalesServiceUtil.addDays(currentDay, 1);
//		if ( startDate != null && tomorrow.compareTo(startDate) > 0){
// 			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_START_DATE);
//		}
//		
//		// end date must be after start date
//		if (endDate != null && endDate.compareTo(startDate) < 0){
// 			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_END_DATE);
//		}
//		
//		// end date must be on or before startDate + 31d
//		Date startDatePlusTwentyEight = EnterpriseSalesServiceUtil.addDays(startDate, GetAvailableInstalLDateConstants.getMaxDays());
//		if (endDate != null && endDate.compareTo(startDatePlusTwentyEight) >= 0){
// 			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_END_DATE);
//		}
//		}
		StringBuilder sb = new StringBuilder();
		if (!missingList.isEmpty()){
			sb.append("Valdation fail missing following:  ");
			for (String msg : missingList){
				sb.append("[" + msg + "]");
			}
		}
		
		if (!invalidInputList.isEmpty()){
			sb.append("Valdation fail invalid input:  ");
			for (String msg : invalidInputList){
				sb.append("[" + msg + "]");
			}
		}
		
		logger.info("validateCoreInput", sb.toString());
		logger.exit(functionName);
	}
	
	private List<String> serviceTypeList(){
		List<String> availableServiceTypes = new ArrayList<String>();
		availableServiceTypes.add(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS);
		availableServiceTypes.add(Constants.CUSTOMER_ODS_PRODUCT_TYPE_SL);
		availableServiceTypes.add(Constants.CUSTOMER_ODS_PRODUCT_TYPE_STV);
		availableServiceTypes.add(Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV);
		return availableServiceTypes;
	}
	
	private boolean validateDate(Date date) {
		String functionName="validateDate";
		logger.enter(functionName);
		boolean hasError=false;
		try{
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		calendar.setTime(date);
		hasError=false;
		}catch(IllegalArgumentException e){
			logger.error("", functionName, "Exception happen when validating entered Date: " + e.getMessage(),e);
			hasError=true;
		}catch(NullPointerException e){
			hasError=true;
			logger.error("", functionName, "Exception happen when validating entered Date: " + e.getMessage(),e);
		}
		logger.exit(functionName);
		return hasError;
	}



	/*****************************************/
	/* generateMessageList                   */
	/*****************************************/
	protected List<MessageList> generateMessageList(List<String> missingElementList, List<String> invalidInputList, List<String> errorMsgList, OperationHeader operaionHeader) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbException = new StringBuilder();
		
		//iterate over missing element list
		for (String msg: missingElementList){
			sbMissing.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		//iterate over invalid input
		for (String msg: invalidInputList){
			sbInvalid.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		
		//iterate over exception errors
		for (String msg: errorMsgList){
			sbException.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
				
				 
		if (!missingElementList.isEmpty()){
			buildMessageList(messageList, sbMissing, EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_MISSING_MANDATORY_ELEMENTS,  operaionHeader);
		}
		
		if (!invalidInputList.isEmpty()){
			buildMessageList(messageList, sbInvalid, EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_INVALID_INPUT,  operaionHeader);
		}
		
		if (!errorMsgList.isEmpty()){
			buildMessageList(messageList, sbException, EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR + EnterpriseWLNSalesServicesConstants.UNKNOWN_EWLNSS_ERROR,  operaionHeader);
		}
		
		return messageList;
	}


	private void buildMessageList(List<MessageList> messageList,
			                      StringBuilder sbMissing,
			                      String errorCode,
			                      OperationHeader operationHeader) {
		MessageList messageMissing = new MessageList();
		if(sbMissing.toString().contains(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_END_DATE) ||
				sbMissing.toString().startsWith("GET_AVAIL_INST_DATE_INVALID_DATE_RANGE")){
			//The Error code for this combination will be 007
			messageMissing.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_INVALID_END_DATE);
		}else{
			messageMissing.setErrorCode(errorCode);
		}
		messageMissing.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		
		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		
		if (errorCode.equals(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_INVALID_INPUT))
			msg.setMessage(EnterpriseWLNSalesServicesConstants.INVALID_INPUT_MESSAGE_TEXT);
		else
			msg.setMessage(EnterpriseWLNSalesServicesConstants.MANDATORY_INPUT_MESSAGE_TEXT);
		
		messageMissing.setMessageList(Arrays.asList(msg));
		
		//set context data
		messageMissing.setContextData(sbMissing.toString());
		
		messageMissing.setTransactionId(operationHeader.getSalesTransactionId());
		
		messageList.add(messageMissing);
	}
}
