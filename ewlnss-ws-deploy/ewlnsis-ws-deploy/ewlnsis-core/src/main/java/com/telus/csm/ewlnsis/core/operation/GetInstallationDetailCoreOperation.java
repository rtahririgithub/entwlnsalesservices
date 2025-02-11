package com.telus.csm.ewlnsis.core.operation;

import static com.telus.csm.ewlnsis.core.utils.GetAvailableInstallDateUtils.isCollectionHasValues;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.task.FeasibilityServiceTask;
import com.telus.csm.ewlnsc.task.GetAvailableTimeSlotsTask;
import com.telus.csm.ewlnsc.task.ReferencePDSDataServiceTask;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetInstallationDetailCoreResponse;
import com.telus.csm.ewlnsis.core.exceptions.BookingDateCalculationException;
import com.telus.csm.ewlnsis.core.exceptions.LagTimeException;
import com.telus.csm.ewlnsis.core.transformer.GetInstallationDetailCoreTransformer;
import com.telus.csm.ewlnsis.core.utils.GetAvailableInstalLDateConstants;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessage;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import commonj.work.Work;

public class GetInstallationDetailCoreOperation extends GetAvailableInstallDateCoreOperation {
	
	private static final String CLASS_NAME      = "GetInstallationDetailCoreOperation"; 
	private static final LoggerUtil logger      = LoggerUtil.getLogger(GetInstallationDetailCoreOperation.class);
	private boolean isExcludeInstallationDate = false;
	
	
	public GetInstallationDetailCoreResponse execute(GetInstallationDetailCoreRequest request){
		
		String functionName = CLASS_NAME + "::" + "execute";
		GetInstallationDetailCoreResponse response = new GetInstallationDetailCoreResponse();
		
		GetInstallationDetailCoreTransformer transformer   = new GetInstallationDetailCoreTransformer();
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
			response = this.getInstallationDetail(request);
			 
		} catch(LagTimeException mte) { 
			
			logger.error(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR + 
					EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, 
					functionName, mte.getMessage());
			return transformer.transformException(mte, request.getOperationHeader().getSalesTransactionId());
			
		} catch(BookingDateCalculationException bdce){
			
			logger.error(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR + 
					EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, functionName, bdce.getMessage());
			return transformer.transformException(bdce, request.getOperationHeader().getSalesTransactionId());
			
		} catch(RuntimeException ex){
			
			logger.error(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_EXCEPTION_ERROR + 
					EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_EWLNSS_ERROR, functionName, logger.getStackTrace(ex));
			return transformer.transformException(ex, request.getOperationHeader().getSalesTransactionId());
			
		} finally {
			logger.exit(functionName);
		}
		
		return response;
	}
	
	/********************************************/
	/*  getInstallationDetail                   */
	/********************************************/
	private GetInstallationDetailCoreResponse getInstallationDetail(GetInstallationDetailCoreRequest request) throws LagTimeException,
	                                                                                                                 BookingDateCalculationException{
		
		String functionName = CLASS_NAME + "::" + "getInstallationDetail";
		logger.enter(functionName);
		GetInstallationDetailCoreResponse response;
		
		/********************************************/
		/*  setup and call feasibilityAdapter       */
		/********************************************/
		
		/**
		 * Step 1 - Prepare first round of Parallel Calls - FeasibilitService and ReferencePDSService
		 */
		CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse = new CheckProductFeasibilityAdapterResponse();
		GetReferencePDSDataServiceAdapterResponse referencePDSAdapterResponse         = new GetReferencePDSDataServiceAdapterResponse();
		GetInstallationDetailCoreTransformer transformer                              = new GetInstallationDetailCoreTransformer();
		
		List<Work> workList = new ArrayList<Work>();
		
		workList.add(super.prepareFeasibilityRequestTask(request));
		
		if (! isExcludeInstallationDate){
			workList.add(super.prepareRefPdsTask(request));
		}
		
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
            	} else if(resultTask instanceof FeasibilityServiceTask){
            		checkProductFeasibilityAdapterResponse =((FeasibilityServiceTask) resultTask).getResult();
            	}
        	}	
        }
		
		/**
		 * Step 3 - check response from feasibilityAdapter, return if error found
		 */      
        response = validateFeasibilityAdapterResponse(checkProductFeasibilityAdapterResponse, transformer, request);
		if (response.hasError()){
			return response;
		} 
		 
		transformer.transformFeasibilityResponse(checkProductFeasibilityAdapterResponse, response);
		
		if (!isExcludeInstallationDate){
			/**
			 * Step 4 - Checking the Response for ReferencePDSDataServices
			 */
			// may not have refPDS response, need to handle that here
			Map<String,String> nguiInstallLagTime = new HashMap<String,String>() ;
			if (!isExcludeInstallationDate){
				if(referencePDSAdapterResponse!=null && !referencePDSAdapterResponse.getReferencePDSTableMap().isEmpty()){
					nguiInstallLagTime = referencePDSAdapterResponse.getReferencePDSTableMap();
					this.validateRefPdsResponse(nguiInstallLagTime);
				}else{
					//Logic to return Error in case the RefPDS Response is not found
					if (referencePDSAdapterResponse != null && referencePDSAdapterResponse.getAdapterResponseMessage() != null && referencePDSAdapterResponse.getAdapterResponseMessage().getException() != null){
						SalesResponseMessage.MessageList msg = new SalesResponseMessage.MessageList();
						
						AdapterResponseMessage rspMsg = referencePDSAdapterResponse.getAdapterResponseMessage();
						
						buildExceptionMessage(request, response, msg, rspMsg);
						return response;
					} else {
						throw new LagTimeException("Exception happened when trying to Get ReferencePDSDataService Response");
					}
				}
			}
			
			/**************************************************/
			/*  setup WorkManager for BookingServiceAdapter   */
			/**************************************************/
			
			/**
			 * Step 5 - build list of RW/FW GetAvailableTimeSlotsAdapterRequest
			 */
			List<GetAvailableTimeSlotsAdapterRequest> bookingServiceRequests = new ArrayList<GetAvailableTimeSlotsAdapterRequest>();
			if (!isExcludeInstallationDate) {
				bookingServiceRequests = transformer.buildGetTimeSlotRequests(request, checkProductFeasibilityAdapterResponse, nguiInstallLagTime);
			}
			
			
			List<GetAvailableTimeSlotsTask> getAvailableTimeSlotsTaskResult = new ArrayList<GetAvailableTimeSlotsTask>();
			if (!bookingServiceRequests.isEmpty()) {
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
			if (!isExcludeInstallationDate){
				transformer.transformToGetInstallationDetailResponse(getAvailableTimeSlotsTaskResult, request, checkProductFeasibilityAdapterResponse, request.getOperationHeader().getSalesTransactionId(), response);
			}

		}
		  
        logger.exit(functionName);
		return response;
	}

	/********************************************/
	/* buildExceptionMessage                    */
	/********************************************/
	private void buildExceptionMessage(GetInstallationDetailCoreRequest request,
										GetInstallationDetailCoreResponse response,
										SalesResponseMessage.MessageList msg, AdapterResponseMessage rspMsg) 
	{
		if (rspMsg.getMessageCode().equals(EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR)){
			msg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_DOWNSTREAM_EXCEPTION);
		}
		else {
			msg.setErrorCode(rspMsg.getMessageCode());
		}
		
		/**
		 * setup relatedMessage
		 */
		List<RelatedMessage> relatedMessageList = new ArrayList<RelatedMessage>();
		RelatedMessage relatedMessage = new RelatedMessage();
		
		relatedMessage.setRelatedErrorCd(rspMsg.getMessageCode());
		relatedMessage.setRelatedErrorMessageTxt(rspMsg.getContextData());
		
		/**
		 * if exception, change to error
		 */
		if (rspMsg.getMessageType().equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION)){
			relatedMessage.setRelatedErrorTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		}
		else {
			relatedMessage.setRelatedErrorTypeCd(rspMsg.getMessageType());
		}
		
		relatedMessageList.add(relatedMessage);
		msg.setRelatedMessageList(relatedMessageList); 
		
		msg.setMessageType(rspMsg.getMessageType());
		if (rspMsg.getMessageType().equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION)) {
			msg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		}
			 
		msg.setTransactionId(request.getOperationHeader().getSalesTransactionId());
		
		List<Message> messageList = new ArrayList<Message>();
		Message message = new Message();
		message.setMessage(EnterpriseWLNSalesServicesConstants.DOWNSTREAM_ERROR_SEE_RELATED_MSG);
		message.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		messageList.add(message);
		msg.setMessageList(messageList);
		
		response.setMsgHasError(true);
		response.setTransactionId(request.getOperationHeader().getSalesTransactionId());
		response.addMsg(msg);
	}
	
	/***********************************************/
	/* validateInput                               */
	/***********************************************/
	private void validateInput(GetInstallationDetailCoreRequest rq, List<String> missingList, List<String> invalidInputList) {
		this.validateCoreInput(rq, missingList, invalidInputList);
		this.validateAttributeExclusionList(rq, invalidInputList);
		if (! this.hasExclusion(rq)) {
			this.validateStartEndDate(rq, missingList, invalidInputList);
		}
	}
	
	/***********************************************/
	/* hasExclusion                                */
	/***********************************************/
	private boolean hasExclusion(GetInstallationDetailCoreRequest rq){
		if (!rq.getAttributeExclusionList().isEmpty() && rq.getAttributeExclusionList().size() == 1 &&
				rq.getAttributeExclusionList().get(0).equals(GetAvailableInstalLDateConstants.INSTALL_DATES_EXCLUSION)){
			isExcludeInstallationDate = true;
			return true;
		} else {
			isExcludeInstallationDate = false;
			return false;
		}
	}
	
	/***********************************************/
	/* validateAttributeExclusionList              */
	/***********************************************/
	private void validateAttributeExclusionList(GetInstallationDetailCoreRequest rq, List<String> invalidInputList){ 
		
		/****************************************************************/
		/* exclusionList can be empty or AvailableInstallationDateList  */
		/****************************************************************/
		if (rq.getAttributeExclusionList().isEmpty()){
			return;
		}
			
		
		if (rq.getAttributeExclusionList().size() > 1){
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_EXCLUSION_LIST); // TODO: Should we force that this list contain just one element ?
		}
		
		if (! rq.getAttributeExclusionList().get(0).equals(GetAvailableInstalLDateConstants.INSTALL_DATES_EXCLUSION)){ //TODO: Should we expect INTALL_DATES be the first element ?
			invalidInputList.add(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_INVALID_EXCLUSION_ELEMENT);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (!invalidInputList.isEmpty()){
			sb.append("Valdation fail invalid input:  ");
			for (String msg : invalidInputList){
				sb.append("[" + msg + "]");
			}
		}
		
		logger.error(null, "validateAttributeExclusionList", sb.toString()); 
	}
	
	public GetInstallationDetailCoreResponse validateFeasibilityAdapterResponse(CheckProductFeasibilityAdapterResponse feasibilityResponse,
            GetInstallationDetailCoreTransformer transformer,
            GetInstallationDetailCoreRequest     request){
		
		/**
		 * feasibilityResponse would only consider ERROR if there is an exception thrown or no productFeasibilityInfoList return.
		 * Otherwise, even if there are ERROR messages, it will continue to process
		 */
		String functionName = "handleFeasibilityAdapterResponse";
		GetInstallationDetailCoreResponse response = new GetInstallationDetailCoreResponse();
		
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
		 * Step 1a - From SunnyDay Path #5, new info message - Gary 2018-01-10
		 */
		if (feasibilityResponse.getProductFeasibilityInfoList() != null && feasibilityResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo() != null){
			StringBuffer context_sb = new StringBuffer();
			
			for (ProductFeasibilityInfo info : feasibilityResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo()){
				String result = info.getFeasibilityResult();
				String serviceType = info.getProductSpecification().getName();	
				
				if ("false".equalsIgnoreCase(result)){
					context_sb = context_sb.append(", " + serviceType);
				}
			}
			
			String context = context_sb.toString();
			
			if (context.length() > 0){
				SalesResponseMessage.MessageList msg = new SalesResponseMessage.MessageList();
				msg.setContextData(context.substring(1, context.length()));
				msg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INSTALL_DATE_NOT_FEASIBLE_SERVICE);
				msg.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO);
				List<Message> messageList = new ArrayList<Message>();
				Message message = new Message();
				message.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
				message.setMessage(EnterpriseWLNSalesServicesErrorCodes.GET_AVAIL_INST_DATE_NOT_FEASIBLE_MSG);
				messageList.add(message);
				msg.setMessageList(messageList);
				response.addMsg(msg);
			}
			 
			
		}
		
		/**
		 * Step 2 - transform exception to response and set msgHasError to true
		 */
		if (feasibilityResponse.getAdapterResponseMessage() != null){
			SalesResponseMessage.MessageList msg = new SalesResponseMessage.MessageList();
			
			AdapterResponseMessage rspMsg = feasibilityResponse.getAdapterResponseMessage();
			
			buildExceptionMessage(request, response, msg, rspMsg);
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
		
		
		if (feasibilityResponse.isMsgHasError()){
			logger.info(functionName, "Encounter exception from downstream");
		}

		return response;
	}
	
}
