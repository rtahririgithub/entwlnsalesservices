package com.telus.csm.ewlnsis.core.operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IFieldWorkAppointmentSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IFifaShoppingCartMgmtRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IReferencePDSDataServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.domain.BookingServiceVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.task.FeasibilityServiceTask;
import com.telus.csm.ewlnsc.task.FieldWorkAppointmentTask;
import com.telus.csm.ewlnsc.task.FifaShoppingCartTask;
import com.telus.csm.ewlnsc.task.GetAvailableTimeSlotsTask;
import com.telus.csm.ewlnsc.task.ReferencePDSDataServiceTask;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.FeasibilitySvcTransformer;
import com.telus.csm.ewlnsc.transformer.FieldWorkAppointmentSvcTransformer;
import com.telus.csm.ewlnsc.transformer.WLNBookingSvcTransformer;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreRequest;
import com.telus.csm.ewlnsis.core.domain.GetAvailableInstallDetailCoreResponse;
import com.telus.csm.ewlnsis.core.transformer.GetAvailableInstallDateCoreTransformer;
import com.telus.csm.ewlnsis.core.transformer.GetAvailableInstallDetailCoreTransformer;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.AvailableAppointment;

import commonj.work.Work;

public class GetAvailableInstallDetailCoreOperation {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableInstallDateCoreOperation.class);
	
	CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse = new CheckProductFeasibilityAdapterResponse();
	GetReferencePDSDataServiceAdapterResponse referencePDSAdapterResponse         = new GetReferencePDSDataServiceAdapterResponse();
	GetFifaShoppingCartAdapterResponse getFifaShoppingCartAdapterResponse = new GetFifaShoppingCartAdapterResponse(); 
	
	public GetAvailableInstallDetailCoreResponse execute(GetAvailableInstallDetailCoreRequest request) {
		String functionName="GetAvailableInstallDetailCoreOperation.execute()";
		logger.enter(functionName);
		GetAvailableInstallDetailCoreResponse response = new GetAvailableInstallDetailCoreResponse();
	
		List<MessageVO> messageVOList;
		
		List<String> validationMsgList = validateInputValidation(request);
		
		if(validationMsgList!=null && !validationMsgList.isEmpty()){
			response.setMessageList(EnterpriseWLNCommonTransformer.getMessageVOListFromList(validationMsgList,"ESS_WLN_INST_DATES_0001"));
			response.setHasError(true);
			return response;
		}
		
		/*
		 * 1. feasibility check
		 * 2. booking service
		 */

			List<GetAvailableTimeSlotsTask> getAvailableTimeSlotsTaskResult = new ArrayList<GetAvailableTimeSlotsTask>();
			
			/*
			 * 1. Call ShoppingCartContextHelper
			 */
			ShoppingCartContextHelper ctxHelper = new ShoppingCartContextHelper();
			ShoppingCartContextVO ctxVO = ctxHelper.execute(request.getShoppingCartId());
			
			if(ctxVO==null){
				response.setHasError(true);
				response.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_WLN_INST_DATES_0003","ShoppingCartContextVO is null"));
				return response;
			}
			
	        //NWLN-10429- No Booking required if SC only has CPE products
	        if(ctxVO.getOfferList() != null && !ctxVO.getOfferList().isEmpty()) {
	        	boolean isShoppingCartCPEonly = true;
	        	logger.debug(functionName,"Verifying if Shopping cart contains CPE products only");
	        	for(GetSalesOfferDetailResponseVO2 offerList : ctxVO.getOfferList()) {
	        		if(offerList.getOffer() != null && offerList.getOffer().getOfferProduct() != null && offerList.getOffer().getOfferProduct().getWirelineOfferProductList() != null){
	        			for(WirelineOfferProduct offerProduct : offerList.getOffer().getOfferProduct().getWirelineOfferProductList() ) {
	        				if(!EnterpriseWLNSalesServicesConstants.CPE.equals(offerProduct.getProductTypeCode())){
	        					isShoppingCartCPEonly = false;
	        				}
	        			}
	        		}
	        	}
	        	if(isShoppingCartCPEonly) {
					logger.debug(functionName,"Shopping Cart contains only CPE products, setting installationRequiredIndicator to false");
		        	response.setHasError(false);
		        	response.setInstallationRequiredIndicator(Boolean.FALSE);
					return response;
	        	}
	        }
			
			/*
			 * 2. call FeasibilitySvcTransformer and refPDS
			 */
			
			List<Work> workList = new ArrayList<Work>();
			
			CheckProductFeasibilityAdapterRequest feasibilitySvcRequest = FeasibilitySvcTransformer.transform(ctxVO);
			
			IFeasibilityServiceAdapter feasibilityAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class, new AdapterFeatureDriver());
			
			workList.add(new FeasibilityServiceTask(feasibilityAdapter, feasibilitySvcRequest));
			
			workList.add(prepareRefPdsTask());
			
			/*
			 * 3. Execute parallel calls
			 */
			
			Collection<Work> responseTaskList = executeParallelCalls(workList);
	        
			/*
			 * 4. Process the result from the parallel calls.
			 */
			processParallelCalls(responseTaskList);
			
			/*
			 * 5. Validate the responses from the parallel call.
			 */
			
	        messageVOList = validateRefPDSResponse(checkProductFeasibilityAdapterResponse,referencePDSAdapterResponse);
			
	        if(!messageVOList.isEmpty()){
				response.setMessageList(messageVOList);
				response.setHasError(true);
				return response;
			}
	        
	        /*
	         * 5.5. check if FeasibilitySvc has a valid response, if no, then return requiredInstallationInd as true but no dates.
	         */
	        
	        boolean isFeasibilityResponseValid = EnterpriseWLNOrderUtil.isFeasibilityResponseValid(checkProductFeasibilityAdapterResponse);
	        
	        if(!isFeasibilityResponseValid){
	        	response.setInstallationRequiredIndicator(Boolean.TRUE);
	        	response.setHasError(false);
	        	List<String> orderedProductsList = EnterpriseWLNOrderUtil.getOrderedProductsForFeasibility(ctxVO);
	        	
	        	GetAvailableInstallDateCoreTransformer availableInstallDateCoreTransformer =new GetAvailableInstallDateCoreTransformer();
	        	
	        	List<List<String>> serviceTypeCombinationLists = availableInstallDateCoreTransformer.generateCombination(request.getCombinationRequiredIndicator(), orderedProductsList);
	        	response.setOrderedProductCombination(serviceTypeCombinationLists);
	        	
	        	logger.error("CheckProductFeasibilityResponse is not valid, setting installationRequiredInd as true but no returning dates.");
	        	
	        	return response;
	        	
	        }
			
			/*
			 * 6. prepare BookingServiceVO to call BookingSvcTransformer and get the booking service request(s).
			 */
	        
			//get LAG_TIME_DAYS from RefPDS response.
			Map<String,String> nguiInstallLagTime = null;
			if(referencePDSAdapterResponse!=null && !referencePDSAdapterResponse.getReferencePDSTableMap().isEmpty()){
				nguiInstallLagTime = referencePDSAdapterResponse.getReferencePDSTableMap();
			}
			
	        
			BookingServiceVO bookingVO = GetAvailableInstallDetailCoreTransformer.transformToBookingVO(request,feasibilitySvcRequest,checkProductFeasibilityAdapterResponse,ctxVO,nguiInstallLagTime);
	        
	        WLNBookingSvcTransformer wlnBookingTransformer = new WLNBookingSvcTransformer();
	        
	        List<GetAvailableTimeSlotsAdapterRequest> timeSlotsRequestList = wlnBookingTransformer.execute(bookingVO);   
	        if(timeSlotsRequestList!=null && !timeSlotsRequestList.isEmpty()){
	        	IWLNBookingRestSvcAdapter adapter = AdapterFactory.getAdapter(IWLNBookingRestSvcAdapter.class, new AdapterFeatureDriver());
	        	List<Work> tasksList = new ArrayList<Work>();
	        	for(GetAvailableTimeSlotsAdapterRequest timeSlotAdapterRequest : timeSlotsRequestList){
	        		
	    			tasksList.add(new GetAvailableTimeSlotsTask(adapter, timeSlotAdapterRequest));  
	        	}
	        	
	        	Collection<Work> bookingTaskList = executeParallelCalls(tasksList);
		        
		        for (Work workElement : bookingTaskList){
		        	if (workElement instanceof GetAvailableTimeSlotsTask ){
		        		getAvailableTimeSlotsTaskResult.add((GetAvailableTimeSlotsTask) workElement);
		        	}
		        }
		        
	        }
	        
	        messageVOList  = GetAvailableInstallDetailCoreTransformer.validateBookingServiceResponse(getAvailableTimeSlotsTaskResult); 
	        
	        if(messageVOList!=null && !messageVOList.isEmpty()){
	        	response.setHasError(true);
	        	response.setMessageList(messageVOList);
	        	return response;
	        }
	        
	        response = GetAvailableInstallDetailCoreTransformer.transformToCoreResponse(getAvailableTimeSlotsTaskResult,bookingVO,checkProductFeasibilityAdapterResponse,ctxVO,feasibilitySvcRequest);
	        logger.exit(functionName);
		return response;
	}
	
	public GetAvailableInstallDetailCoreResponse execute(final GetAvailableInstallDetailCoreRequest request, final String projectCode, final String techLanguage) {
		final String functionName="GetAvailableInstallDetailCoreOperation.execute(str,str)";
		logger.enter(functionName);
		GetAvailableInstallDetailCoreResponse response = new GetAvailableInstallDetailCoreResponse();
	
		final List<String> validationMsgList = validateInputValidation(request);
		
		if (validationMsgList!=null && !validationMsgList.isEmpty()) {
			response.setMessageList(EnterpriseWLNCommonTransformer.getMessageVOListFromList(validationMsgList,"ESS_WLN_INST_DATES_0001"));
			response.setHasError(true);
			return response;
		}

		final List<Work> workList = new ArrayList<Work>();
		workList.add(prepareFifaTask(request));
		workList.add(prepareRefPdsTask());
		final Collection<Work> responseTaskList = executeParallelCalls(workList);
		processParallelCalls(responseTaskList);
		
		List<MessageVO> messageVOList = validateRefPDSResponse(referencePDSAdapterResponse);
		
        if (!messageVOList.isEmpty()) {
			response.setMessageList(messageVOList);
			response.setHasError(true);
			return response;
		}

		//get LAG_TIME_DAYS from RefPDS response.
		Map<String,String> nguiInstallLagTime = null;
		if (referencePDSAdapterResponse != null && !referencePDSAdapterResponse.getReferencePDSTableMap().isEmpty()) {
			nguiInstallLagTime = referencePDSAdapterResponse.getReferencePDSTableMap();
		}
		String fifaAll = nguiInstallLagTime.get("FIFA_ALL");
		int fifaAllnumber = 1;
		if (fifaAll != null) {
			fifaAllnumber = Integer.valueOf(fifaAll).intValue();
		}
		Date start = request.getFromDate();
		Date today = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
		
		if (cal.getTime().before(today)) {
			start = addDays(today, fifaAllnumber);
		}
		
		Date end = request.getToDate();
		Date newStart = start;
		Date newEnd = new Date(start.getTime());
		newEnd.setDate(start.getDate() + 28);

		final List<Work> wfmWorkList = new ArrayList<Work>();

		do {
			if (newEnd.after(end)) {
				newEnd = end;
			}
			
			// transform from FIFA shopping cart response to FieldWorkAppointment request
			final FieldWorkAppointmentAdapterRequest fwaReq =  FieldWorkAppointmentSvcTransformer.transform( request.getOperationHeader(), 
					newStart, newEnd, getFifaShoppingCartAdapterResponse.getShoppingCart(), projectCode, techLanguage);
			
			final IFieldWorkAppointmentSvcAdapter fwaAdapter = AdapterFactory.getAdapter(IFieldWorkAppointmentSvcAdapter.class, new AdapterFeatureDriver());
			
			final FieldWorkAppointmentTask task = new FieldWorkAppointmentTask(fwaAdapter, fwaReq);
			wfmWorkList.add(task);
			
			newStart = newEnd;
			newStart.setDate(newEnd.getDate() + 1);
			newEnd = new Date(newStart.getTime());
			newEnd.setDate(newStart.getDate() + 28);
		} while (newStart.before(end));

		final Collection<Work> responseWFMTaskList = executeParallelCalls(wfmWorkList);
		final FieldWorkAppointmentAdapterResponse fwaResp = processParallelWFMCalls(responseWFMTaskList);

		if (fwaResp.isMsgHasError()) {
			messageVOList = EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_WLN_INST_DATES_0005", "WFM service returned error.");
	        if (messageVOList != null && !messageVOList.isEmpty()) {
	        	response.setHasError(true);
	        	response.setMessageList(messageVOList);
	        	return response;
	        }
		}
		
        response = GetAvailableInstallDetailCoreTransformer.transformToCoreResponse(fwaResp);
        logger.exit(functionName);
		return response;
	}

	private FifaShoppingCartTask prepareFifaTask(final GetAvailableInstallDetailCoreRequest request) {
		final IFifaShoppingCartMgmtRestSvcAdapter adapter = AdapterFactory.getAdapter(IFifaShoppingCartMgmtRestSvcAdapter.class, new AdapterFeatureDriver());
		
		final GetFifaShoppingCartAdapterRequest req = new GetFifaShoppingCartAdapterRequest();
		req.setShoppingCartId(request.getShoppingCartId());
		req.setSalesTransactionId(request.getOperationHeader().getSalesTransactionId());
		
		final FifaShoppingCartTask task = new FifaShoppingCartTask(adapter, req);
		
		return task;
	}
	

	private List<String> validateInputValidation(GetAvailableInstallDetailCoreRequest request) {
		List<String> messageList = new ArrayList<String>();
		
		/**
		 * validate Date related fields.
		 * 
		 * a) startDate should be SYSDATE + 1
		 * b) range of days cannot be more than 90 days.
		 * c) endDate should be after startDate
		 */
		
		Date currentDay = EnterpriseSalesServiceUtil.removeTime(new Date());
		
		if(request.getFromDate()!=null && request.getToDate()!=null){
			
			Date startDate = EnterpriseSalesServiceUtil.removeTime(request.getFromDate());
			
			Date endDate = EnterpriseSalesServiceUtil.removeTime(request.getToDate());
			
			//NWLN-8372 Remove the validation rule that check the start date must be after the current date
//			if ( startDate != null && tomorrow.compareTo(startDate) > 0){
//				messageList.add("Invalid Start Date");
//			}
			
			if(endDate != null && endDate.compareTo(startDate) < 0){
				messageList.add("Invalid End Date");
			}
			
			//end date must be on or before startDate + 90d
			Date startDatePlusMaxDays = EnterpriseSalesServiceUtil.addDays(startDate, 90);
			
			if(endDate.compareTo(startDatePlusMaxDays) >= 0){
				messageList.add("The maximum range of dates available is 90 days");
			}
			
		}
		
		return messageList;
	}

	private void processParallelCalls(Collection<Work> responseTaskList) {
		for(Work resultTask : responseTaskList){
    		if(resultTask instanceof ReferencePDSDataServiceTask){
        		referencePDSAdapterResponse =((ReferencePDSDataServiceTask) resultTask).getResult();
        	} else if(resultTask instanceof FeasibilityServiceTask){
        		checkProductFeasibilityAdapterResponse =((FeasibilityServiceTask) resultTask).getResult();
        	} else if (resultTask instanceof FifaShoppingCartTask) {
        		getFifaShoppingCartAdapterResponse = ((FifaShoppingCartTask) resultTask).getResult();
        	}
    }
	}

	private Collection<Work> executeParallelCalls(List<Work> workList) {
		Collection<Work> responseTaskList = new ArrayList<Work>();
		try{
            ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
            responseTaskList = commonJMgr.processTasks(workList);
     } catch (Exception e){ 
            logger.error(e);
     }
		return responseTaskList;
	}
	
	private ReferencePDSDataServiceTask prepareRefPdsTask(){
		IReferencePDSDataServiceAdapter refPDSAdapter = AdapterFactory.getAdapter(IReferencePDSDataServiceAdapter.class, new AdapterFeatureDriver());
		
		String appId = EnterpriseWLNSalesServicesConstants.EWLNSS_APP_ID;
		
		GetReferencePDSDataServiceAdapterRequest adapterRequest = new GetReferencePDSDataServiceAdapterRequest(appId);
		
		return new ReferencePDSDataServiceTask(refPDSAdapter, adapterRequest, EnterpriseWLNSalesServicesConstants.REFPDS_LAG_TIME_TABLE);
	}
	
	public static List<MessageVO> validateRefPDSResponse(
			CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse,
			GetReferencePDSDataServiceAdapterResponse referencePDSAdapterResponse) {
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		
		String functionName="validateRefPDSResponse";
		
		//Check feasibilityService response
		
//		messageVOList = FeasibilitySvcTransformer.validateFeasibilityResponse(checkProductFeasibilityAdapterResponse,"ESS_WLN_INST_DATES_0004"); //move this validate to EnterpriseWLNCommonTransformer
//		
//		if(!messageVOList.isEmpty()){
//			return messageVOList;
//		}
//		
//		logger.debug(functionName, "Feasibility response didn't returned errors. Checking RefPDS response");
		
		if(referencePDSAdapterResponse!=null && !referencePDSAdapterResponse.getReferencePDSTableMap().isEmpty()){
			logger.debug(functionName, "NGUI_LAG_TIME map was returned from refPDS response.");
		}else{
			messageVOList = EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_WLN_INST_DATES_0004", "ReferencePDSService is down or didn't returned LAG_TIME map."); //move this validate to EnterpriseWLNCommonTransformer
			return messageVOList;
		}
		
		return messageVOList;
	}
	
	public static List<MessageVO> validateRefPDSResponse(GetReferencePDSDataServiceAdapterResponse referencePDSAdapterResponse) {
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		
		String functionName="validateRefPDSResponse";
		
		if(referencePDSAdapterResponse!=null && !referencePDSAdapterResponse.getReferencePDSTableMap().isEmpty()){
			logger.debug(functionName, "NGUI_LAG_TIME map was returned from refPDS response.");
		}else{
			messageVOList = EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_WLN_INST_DATES_0004", "ReferencePDSService is down or didn't returned LAG_TIME map."); //move this validate to EnterpriseWLNCommonTransformer
			return messageVOList;
		}
		
		return messageVOList;
	}
	
	private FieldWorkAppointmentAdapterResponse processParallelWFMCalls(final Collection<Work> responseTaskList) {
		final FieldWorkAppointmentAdapterResponse result = new FieldWorkAppointmentAdapterResponse();
		
		for (final Work resultTask : responseTaskList) {
    		if (resultTask instanceof FieldWorkAppointmentTask){
    			FieldWorkAppointmentAdapterResponse resp = ((FieldWorkAppointmentTask) resultTask).getResult();
    			// only use the first work order in this loop
    			if (result.getWorkOrder() == null) {
    				result.setWorkOrder(resp.getWorkOrder());
    			}
    			// add Available Appointment list from each element in the loop to the final result
    			if (result.getAvailableAppointmentList() == null) {
    				result.setAvailableAppointmentList(new ArrayList<AvailableAppointment>());
    			}
    			
    			if (resp.getAvailableAppointmentList() != null) {
    				result.getAvailableAppointmentList().addAll(resp.getAvailableAppointmentList());
    			}
        	} 
		}
		
		return result;
	}	
	
	public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); 
        return cal.getTime();
    }
	
}
