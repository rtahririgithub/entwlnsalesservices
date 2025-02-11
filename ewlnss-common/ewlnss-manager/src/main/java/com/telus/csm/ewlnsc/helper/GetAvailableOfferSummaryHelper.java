package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_INVALID_INPUT;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_INVALID_PRODUCT_ORDER_TYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_INVALID_PRODUCT_TYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_INVALID_SERVICEIDLIST;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_INVALID_SERVICE_ADDRESS_PROVINCE_CD;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_BUNDLE_IND;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_MANDATORY_ELEMENTS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_OFFER_FILTER;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_OFFER_FILTER_ACCOUNT_TYPE_CD;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_OFFER_FILTER_ELEMENTS_ALL_NULL;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_PRODUCT_COMP_CONTTTERM_OFFERCONTTERM_ARE_NULL;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_PRODUCT_TYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_SALES_OFFER_CRITERIA;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_SERVICE_ADDRESS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_SERVICE_ADDRESS_CITY_CD;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_SERVICE_ADDRESS_ID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_SERVICE_ADDRESS_PROVINCE_CD;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_MISSING_SUBSCRIBED_SERVICE_ID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GAOSL_OFFER_NOT_APPLICABLE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.adapter.IWLNCreditEligibilityProxyServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceIdentifierVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.task.CheckFeasibilityServiceTask;
import com.telus.csm.ewlnsc.task.ForeborneTask;
import com.telus.csm.ewlnsc.task.GetAssignedAndPendingProductTask;
import com.telus.csm.ewlnsc.task.GetCreditEligibilityTask;
import com.telus.csm.ewlnsc.task.GetCreditProfileByCustomerIdTask;
import com.telus.csm.ewlnsc.task.GetOfferListForCustomerTask;
import com.telus.csm.ewlnsc.task.GetProductQualificationTask;
import com.telus.csm.ewlnsc.transformer.GetAvailableOfferSummaryTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.ExecutionTimer;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

import commonj.work.Work;


@Component
@Scope(SCOPE_PROTOTYPE)
public class GetAvailableOfferSummaryHelper {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableOfferSummaryHelper.class);

	protected SalesOfferCommonVO commonVO = new SalesOfferCommonVO();
	
	private ICommonJWorkManager workManager;
	
	
	@Autowired
	private IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	public GetAvailableOfferSummaryHelper(){
		try {
			workManager = WorkManagerFactory.getCommonJWorkManager();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	@MonitorPerformance
	public GetOffersResponseVO execute(GetOffersRequestVO requestVO) {

		String functionName = "execute";
		ExecutionTimer timer = new ExecutionTimer(this.getClass().getSimpleName(), functionName);
		logger.enter(functionName);
		
		GetOffersResponseVO getOffersResponseVO = new GetOffersResponseVO();
		
		/*
		 * Step 1: validate Input
		 */
		List<String> missingElementList = new ArrayList<String>();
		List<String> invalidInputList = new ArrayList<String>();
		List<String> warningList = new ArrayList<String>();
		
		validateInput(requestVO, missingElementList, invalidInputList, warningList);
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty() || !warningList.isEmpty()) {
			getOffersResponseVO.setMessageList(ValidationUtil.generateMessageList(requestVO.getOperationHeader(), missingElementList, invalidInputList, warningList, GAOSL_MISSING_MANDATORY_ELEMENTS, GAOSL_INVALID_INPUT));
			logger.info(functionName, EnterpriseWLNSalesServicesConstants.INPUT_VALIDATION_FAILED_OR_WARNING_MSG);
		}
		
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
			return getOffersResponseVO;
		}
		
		/*
		 * Step 1a: checking RefPDS status
		 */
		
		List<MessageList> refPDSStatusMsgList = OfferSummaryExceptionHandler.validateRefPDSStatus(requestVO.getOperationHeader(), EnterpriseWLNSalesServicesErrorCodes.GAOSL_DOWN_STREAM_ERROR);
		if(refPDSStatusMsgList!=null && !refPDSStatusMsgList.isEmpty()){
			getOffersResponseVO.setMessageList(refPDSStatusMsgList);
			return getOffersResponseVO;
		}
		
		timer.lap("ValidateRefPDSStatus");

		/*
		 * Step 2: prepareCommonObject
		 */
		Collection<Work> resultTaskList = prepareCommonObject(requestVO);

		/*
		 * Step 2.1: validate responses from downstream services
		 */
		List<MessageList> exceptionMessageList = processFirstRoundParallelCalls(resultTaskList);
		if(!exceptionMessageList.isEmpty()){
			getOffersResponseVO.setMessageList(exceptionMessageList);
			return getOffersResponseVO;
		}
		
		/*
		 * Step 2.2 validate if OIS can be called as determined by customerEligibility
		 */
		String errorMessage;
		boolean badEligibleCustomerInd = checkCustomerEligibleStatus();
		if(badEligibleCustomerInd){
			errorMessage = "Customer has been flagged as 'fraud' or 'in-treatment' and has no subscribed nor pending products";
			getOffersResponseVO.setMessageList(GetAvailableOfferSummaryTransformer.generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), GAOSL_OFFER_NOT_APPLICABLE, errorMessage,null));
			return getOffersResponseVO;
		}
		
		List<MessageList> messageList= OfferSummaryExceptionHandler.prevalidate(commonVO);
		
		if(messageList!=null && !messageList.isEmpty()){
			getOffersResponseVO.setMessageList(messageList);
			return getOffersResponseVO;
		}
		
		timer.lap("PrepareCommonObject");
	
		/*
		 * Step 3: getAvailableOffer
		 */
		secondParallelCall();
		
		/*
		 * Step 3.1: checking if call to OIS returned exception
		 */
		if(commonVO.getOfferListAdapterResponse()!=null && commonVO.getOfferListAdapterResponse().isMsgHasError() && commonVO.getOfferListAdapterResponse().getOfferList()==null){
			populateResponse(getOffersResponseVO);
			return getOffersResponseVO;
		}
		
		timer.lap("SecondParallelCall");

		/*
		 * Step 4: applyFilters
		 */
		List<TraceVO> traces = applyFilters(requestVO, getOffersResponseVO);
		
		/*
		 * Step 4.1: checking if there are any offers to be returned
		 */
		if(commonVO.getOfferListAdapterResponse().getOfferList().isEmpty()){
			if(traces!=null && !traces.isEmpty()){
				errorMessage = "All Offers were filtered out.";
			}else{
				errorMessage = "No offers found.";
			}
			getOffersResponseVO.setMessageList(GetAvailableOfferSummaryTransformer.generateMessageList(requestVO.getOperationHeader(), GAOSL_OFFER_NOT_APPLICABLE, errorMessage,traces));
		}

		timer.lap("ApplyFilters");		
		
		/*
		 * Step 5: populateResponse
		 */
		populateResponse(getOffersResponseVO);
		
		timer.stop();
		
		logger.exit(functionName);
		
		return getOffersResponseVO;
	}

	

	protected boolean checkCustomerEligibleStatus(){
		boolean result=false;
		/**
		 * Checking if the customer has been flagged as 'in-treatment' or 'fraud' from CreditEligibilityResponse
		 */
		boolean customerBadEligibilityInd = WLNOfferUtil.isCustomerBadEligibility(commonVO);
		if(commonVO.getAssignedAndPendingProductsResponseVO()!=null){
			List<SubscribedProductInfoRestVO> assignedProductList = commonVO.getAssignedAndPendingProductsResponseVO().getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			List<SubscribedProductInfoRestVO> pendingProductList = commonVO.getAssignedAndPendingProductsResponseVO().getPendingProductListByServiceAddress(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),commonVO.getServiceAddressResponseVO());
			if(customerBadEligibilityInd && assignedProductList.isEmpty() && pendingProductList.isEmpty()){
				//Return error in this scenario - end the flow
				result=true;
			}
		}
		return result;
	}
	
	private void populateResponse(GetOffersResponseVO offersResponseVO) {
		String functionName = "populateResponse";
		logger.enter(functionName);
		if (commonVO.getOfferListAdapterResponse() != null) {
			GetAvailableOfferSummaryTransformer.transform(commonVO, offersResponseVO);
		}
		logger.exit(functionName);
	}

	private List<TraceVO> applyFilters(GetOffersRequestVO requestVO, GetOffersResponseVO getOffersResponseVO) {
		final OfferFilterHelper offerFilterHelper = new OfferFilterHelper(commonVO);
		
		final List<TraceVO> traces = new ArrayList<TraceVO>();
		final List<String> requestedProductList = requestVO.getProductFromRequest();
		
		final List<Offer> inputOfferList = commonVO.getOfferListAdapterResponse().getOfferList();
		
		final List<Offer> outputOfferList = offerFilterHelper.filterOfferSummary(inputOfferList, requestedProductList, traces);
		
		// Jose.Mena: Defect 63430: When calling GAOSL or GSOD, if no ports available then include an error message in the response.
		WLNOfferUtil.checkFeasibilityResponseNoPorts(commonVO, getOffersResponseVO, GAOSL_NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY);
		
		commonVO.getOfferListAdapterResponse().setOfferList(outputOfferList);
		
		final StringBuilder logMsg = new StringBuilder();
		if(!CollectionUtils.isEmpty(traces)){
			for (TraceVO trace: traces) {
				logMsg.append("\n"+ trace.toLogMessage()); 
			}
		}
		logger.info("applyFilters", "Trace from OfferFilterHelper.filterOfferSummary" + logMsg.toString()); 		
		return traces;
	}

	private void secondParallelCall() {
		String functionName="getAvailableOffers";
		logger.enter(functionName);
		
		List<Work> workTaskList = new ArrayList<Work>();
		
		/**
		 * Need to check if ESS is going to call OIS multiple times
		 * 
		 *  Scenarios for this
		 * 		* TTV is requested in the request productList without HSIC
		 * 		* HSIC is a existing product and is not HS Zero.
		 * 		* If two conditions above fulfill, then make one OIS call per each catalogId to include for HSIC (Existing catalogId + all the same family of the existing HSIC, this is done via refPDS)
		 * 
		 * 	This will only be happening when productList is present in the request
		 * 
		 * 	- offerContractTerm = 0 will not be accounted, promotion being passed in the request will not execute this logic
		 */
		
		if(multipleCallsToGetOffers()){
			
			List<GetOfferListForCustomerTask> offerRequestList = getOfferRequests();
			workTaskList.addAll(offerRequestList);
			
		}else{
			GetOfferListForCustomerTask offerSummaryListTask = new GetOfferListForCustomerTask(commonVO,false,null); //The reason why we are sending the parameter 'callForRecontractEligibleInd' as false for the GetOfferListForCustomerTask is because we always want to get the "regular" call to get the offers, the re-contract eligible has no importance at this stage
			
			workTaskList.add(offerSummaryListTask);
		}

		// 2019-07-15 Feasibility Check is relaxed as it is not required for Bundle Builder
		
		/* if(commonVO.isCallCheckFeasibilityInd()){
			// Check product Feasibility Task
			CheckFeasibilityServiceTask feasibilityTask = new CheckFeasibilityServiceTask(commonVO);
			workTaskList.add(feasibilityTask);
		}
		*/
		
		if(commonVO.isCustomerEligibleForRecontract() && EnterpriseWLNSalesServicesConstants.ZERO.equals(commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().getOfferContractTermCd())){
			//Preparing second call to OIS for re-contracting scenario
			logger.info("secondParallelCall", "Customer is eligible for re-contracting, preparing second call to OIS");
			GetOfferListForCustomerTask offerSummaryListForRecontracting = new GetOfferListForCustomerTask(commonVO,true,null); //At this stage, if the customer is re-contract eligible, this will be the second call to OIS, therefore we need to pass the parameter'. 
			workTaskList.add(offerSummaryListForRecontracting);														       //We cannot use the salesOfferCommonVO class since we are building both request's with the same object, and if we add another parameter to that class it will be override and we will call twice OIS for the same request.
		}
		
		Collection<Work> responseTaskList = executeParallelCall(workTaskList);
		List<GetOfferListAdapterResponse> offerResultList = new ArrayList<GetOfferListAdapterResponse>();
		for (Work resultTask : responseTaskList) {
			if (resultTask != null && resultTask instanceof GetOfferListForCustomerTask) {
				GetOfferListAdapterResponse result = ((GetOfferListForCustomerTask) resultTask).getResult();
				offerResultList.add(result);
				commonVO.setOfferListAdapterResponse(result);
			}else if (resultTask instanceof CheckFeasibilityServiceTask) {
					commonVO.setCheckFeasibilityResponseVO(((CheckFeasibilityServiceTask) resultTask).getResult());
				}

		}
		
		//Merging the results from parallel calls to OIS
		commonVO.setOfferListAdapterResponse(getMergedOffer(offerResultList));
		
		logger.exit(functionName);
	}
	
	protected List<GetOfferListForCustomerTask> getOfferRequests() {
		
		List<GetOfferListForCustomerTask> requestOfferList = new ArrayList<GetOfferListForCustomerTask>();
		
		String existingHsicProductTierCd = WLNOfferUtil.getHsicSubscribedProductTierCdFromAssignedAndPendingProductsResponse(commonVO);
		String existingHsicCid = WLNOfferUtil.getInternalCidFromProductTierCd(existingHsicProductTierCd);
		List<String> productCatalogIdList = WLNOfferUtil.getAllProductCatalogIdOfSameProductFamilyGroup(existingHsicCid);
		
		if(CollectionUtils.isNotEmpty(productCatalogIdList)){
			for(String catalogId : productCatalogIdList){
				GetOfferListForCustomerTask offerSummaryListTask = new GetOfferListForCustomerTask(commonVO,false,catalogId);
				requestOfferList.add(offerSummaryListTask);
			}
		}
		
		return requestOfferList;
	}

	protected boolean multipleCallsToGetOffers() {
		String functionName="multipleCallsToGetOffers";
		logger.enter(functionName);
		
		boolean requestHasTTV = false;
		boolean requestHasHSIC = false;
		
		if(commonVO.getOffersRequestVO().getSalesOfferCriteria()!=null && commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter()!=null &&
				CollectionUtils.isNotEmpty(commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().getProductList())){
					logger.debug(functionName, "Request has Products, checking if it has TTV Product ordered without HSIC");
					
					List<OfferProductHeader> productList = commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().getProductList();
					
					for(OfferProductHeader product : productList){
						if (TTV.equalsIgnoreCase(product.getProductTypeCd())) {
							requestHasTTV = true;
						} else if (HSIC.equalsIgnoreCase(product.getProductTypeCd())) {
							requestHasHSIC = true;
						}
					}
			}
		
		// TTV requested but not HSIC
		if (requestHasTTV && !requestHasHSIC) {
			
			if (WLNOfferUtil.isProductExists(HSIC, commonVO) && !(WLNOfferUtil.checkProductsHasHSZERO(commonVO, commonVO.getAssignedProductListByServiceAddressAndServiceId())
					|| WLNOfferUtil.checkProductsHasHSZERO(commonVO, commonVO.getPendingProductListByServiceAddress()))) {
				logger.debug(functionName, "Customer has Existing HSIC different of HS Zero. Calling OIS Multiple times");
				return true;
			}
			
		}
		
		
		return false;
	}

	private GetOfferListAdapterResponse getMergedOffer(List<GetOfferListAdapterResponse> offerResultList) {
		GetOfferListAdapterResponse resultOffers = new GetOfferListAdapterResponse();
		List<Offer> offerList = new ArrayList<Offer>();
		List<String> offerKeys = new ArrayList<String>();
		String key=null;
		for(GetOfferListAdapterResponse offerResponse : offerResultList){
			if(!offerResponse.isMsgHasError()){
				for(Offer offer : offerResponse.getOfferList()){
					key = String.valueOf(offer.getOfferId());
					for(WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()){
						if(wirelineOfferProduct.getContractTermList() != null && !wirelineOfferProduct.getContractTermList().isEmpty()){
							key += wirelineOfferProduct.getProductTypeCode() +  wirelineOfferProduct.getContractTermList().get(0) + wirelineOfferProduct.getTransactionTypeList().get(0).getTransactionTypeCode();	
						} else {
							if(wirelineOfferProduct.getTransactionTypeList() != null && wirelineOfferProduct.getTransactionTypeList().size() > 0){
								key += wirelineOfferProduct.getProductTypeCode() + wirelineOfferProduct.getTransactionTypeList().get(0).getTransactionTypeCode();	
							} else {
								key += wirelineOfferProduct.getProductTypeCode() + "_";	
							}
						}
					}
					if(!offerKeys.contains(key)){
						offerList.add(offer);
						offerKeys.add(key);
						logger.debug("getMergedOffer", "Adding Offer with key: " + key);
					}
				}
			}else{
				resultOffers = offerResponse;
				return resultOffers; //Return if one of the call to OIS failed
			}
		}
		resultOffers.setOfferList(offerList);
		return resultOffers;
	}

	protected Collection<Work> prepareCommonObject(GetOffersRequestVO requestVO) {
		String functionName = "prepareCommonObject";
		
		logger.enter(functionName);
		
		commonVO.setOffersRequestVO(requestVO);
		
		List<Work> workTaskList = new ArrayList<Work>();
		
		if (requestVO.getSalesOfferCriteria() != null) {

			// preparing call for GetConsolidatedAccountProfile if account is provided
			// NWLN-7743 - call consolidatedAccountTask even if isServiceIdPresentForSplit(requestVO) = true for if the originatorApplicationId is 9922 or 9919.
			if (requestVO.getSalesOfferCriteria() != null && 
					!StringUtils.isBlank(requestVO.getSalesOfferCriteria().getBillingAccountNumber()) && 
					(isServiceIdPresentForSplit(requestVO) || 
					 (requestVO.getOperationHeader()!=null && 
					  requestVO.getOperationHeader().getOriginatorApplicationId()!=null && 
					  (EnterpriseWLNSalesServicesConstants.ORIGINATOR_APPLICATION_ID_9919.equals(requestVO.getOperationHeader().getOriginatorApplicationId()) || 
					   EnterpriseWLNSalesServicesConstants.ORIGINATOR_APPLICATION_ID_9922.equals(requestVO.getOperationHeader().getOriginatorApplicationId())
					  )
					 )
					)
			  )
			{
				GetAssignedAndPendingProductRequestVO consolidatedaccProfileAdapterReq = GetAvailableOfferSummaryTransformer.transformConsolidatedAccRequest(requestVO);

				if ((requestVO.getOperationHeader()==null ||
						  requestVO.getOperationHeader().getOriginatorApplicationId()==null ||  
						  (EnterpriseWLNSalesServicesConstants.ORIGINATOR_APPLICATION_ID_9919.equals(requestVO.getOperationHeader().getOriginatorApplicationId()) || 
						   EnterpriseWLNSalesServicesConstants.ORIGINATOR_APPLICATION_ID_9922.equals(requestVO.getOperationHeader().getOriginatorApplicationId())
						  )
						 )) {
						//QC74221 - GetAssignedAndPendingProductTask2 refractoring
						GetAssignedAndPendingProductTask consolidatedAccountTask = new GetAssignedAndPendingProductTask(consolidatedaccProfileAdapterReq);
						workTaskList.add(consolidatedAccountTask);
				} else {
					//QC74221 - GetAssignedAndPendingProductTask2 refractoring
					GetAssignedAndPendingProductTask consolidatedAccountTask = new GetAssignedAndPendingProductTask(consolidatedaccProfileAdapterReq, commonVO.getOffersRequestVO(), wirelineSalesEJBAdapter);
					workTaskList.add(consolidatedAccountTask);
				}
				
				
			}
			/**
			 * If serviceAddressId is passed in ESS request invoke GetProductQualificationTask
			 */
			// Preparing call for ProductQualificationTask
			if(requestVO.getSalesOfferCriteria().getServiceAddress()!=null && !StringUtils.isBlank(requestVO.getSalesOfferCriteria().getServiceAddress().getServiceAddressId())){
				GetProductQualificationAdapterRequest adapterRequest = GetAvailableOfferSummaryTransformer.transformProductQualificationRequest(requestVO);
				GetProductQualificationTask productQualificationTask = new GetProductQualificationTask(adapterRequest);
				workTaskList.add(productQualificationTask);
			}


			// Prepare call to GetCreditProfileByCustomerId if customerId is passed
			if (!StringUtils.isBlank(requestVO.getSalesOfferCriteria().getCustomerId())) {
				// Prepare GetCreditProfileByCustomerIdTask
				GetCreditProfileByCustomerIdAdapterRequest creditProfileByCustomerIdAdapterReq = GetAvailableOfferSummaryTransformer.transformCreditProfileByCustId(requestVO);
				// Adapter for GetCreditProfileByCustomerId
				GetCreditProfileByCustomerIdTask getCreditProfileByCustomerIdTask = new GetCreditProfileByCustomerIdTask(creditProfileByCustomerIdAdapterReq);
				workTaskList.add(getCreditProfileByCustomerIdTask);

				// Prepare task for CreditEligibility
				GetCreditEligibilityAdapterRequest creditEligibilityAdapterReq = GetAvailableOfferSummaryTransformer.transformCreditEligibilityRequest(requestVO);
				IWLNCreditEligibilityProxyServiceAdapter creditEligibilityAdapter = AdapterFactory.getAdapter(IWLNCreditEligibilityProxyServiceAdapter.class);
				GetCreditEligibilityTask creditEligibilityTask = new GetCreditEligibilityTask(creditEligibilityAdapter,creditEligibilityAdapterReq);
				workTaskList.add(creditEligibilityTask);
			}

			// Prepare call to GetServiceAddressTask
			ServiceAddressRequestVO serviceAddressRequest = GetAvailableOfferSummaryTransformer.transformServiceAddressReq(requestVO);
			ForeborneTask foreborneTask = new ForeborneTask(serviceAddressRequest);
			workTaskList.add(foreborneTask);
		}
		
		logger.exit(functionName);
		return executeParallelCall(workTaskList);
	}
	
	private boolean isServiceIdPresentForSplit(GetOffersRequestVO requestVO) {
		if(!WLNOfferUtil.isJoinedOffers(requestVO.getOperationHeader()) && CollectionUtils.isEmpty(requestVO.getSalesOfferCriteria().getSubscribedServiceIdentifier())){
			return false;
		}
		return true;
	}

	private Collection<Work> executeParallelCall(List<Work> taskList){
		String functionName = "executeParallelCall";
		logger.enter(functionName);
		Collection<Work> responseTaskList = new ArrayList<Work>();
		try{
			responseTaskList = workManager.processTasks(taskList);
		}catch(Exception e){
			logger.error("",functionName,e.getMessage(),e);
			throw new RuntimeException(e);
		}
		logger.exit(functionName);
		return responseTaskList;
	}
	
	protected List<MessageList> processFirstRoundParallelCalls(Collection<Work> responseTaskList) {
		String functionName="processFirstRoundParallelCalls";
		logger.enter(functionName);
		List<MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
		GetAssignedAndPendingProductTask assignedAndPendingProductTask = null;
		GetProductQualificationTask productQualificationTask=null;
		GetCreditProfileByCustomerIdTask creditProfileByCustomerIdTask=null;
		GetCreditEligibilityTask creditEligibilityTask=null;
		ForeborneTask foreborneTask=null;
        for(Work resultTask : responseTaskList){
        	if(resultTask!=null){
				if (resultTask instanceof GetAssignedAndPendingProductTask) {
					assignedAndPendingProductTask = (GetAssignedAndPendingProductTask) resultTask;
				}
        		else if(resultTask instanceof GetProductQualificationTask){
        			productQualificationTask = (GetProductQualificationTask) resultTask;
        		}else if(resultTask instanceof GetCreditProfileByCustomerIdTask){
        			creditProfileByCustomerIdTask = (GetCreditProfileByCustomerIdTask) resultTask;
        		}else if(resultTask instanceof GetCreditEligibilityTask){
        			creditEligibilityTask = (GetCreditEligibilityTask) resultTask;
        		}else if(resultTask instanceof ForeborneTask){
        			foreborneTask = (ForeborneTask) resultTask;
        		}
        	}
        }
        
        //processing the tasks results
        if(foreborneTask!=null){
			final ServiceAddressResponseVO serviceAddressResponse = foreborneTask.getResult();
			if(serviceAddressResponse.getServiceAddress()==null){
				commonVO.setCallCheckFeasibilityInd(false);
			}else{
				commonVO.setCallCheckFeasibilityInd(true);
			}
			commonVO.setServiceAddressResponseVO(serviceAddressResponse);
        }if(assignedAndPendingProductTask!=null){
			if (assignedAndPendingProductTask.getRuntimeException() != null) {
				GetAssignedAndPendingProductResponseVO assignedAndPendingProductsVO = OfferSummaryExceptionHandler.transformExceptionForAssignedAndPendingProducts(assignedAndPendingProductTask.getRuntimeException(),commonVO.getOffersRequestVO().getOperationHeader());
				if(WLNOfferUtil.banNotFoundError(assignedAndPendingProductsVO) && !CollectionUtils.isEmpty(commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier())) {
					messageList = assignedAndPendingProductsVO.getMessageList();
					return messageList;
				}
			} else {
				logger.info(functionName, "No Exception was found on GetAssignedAndPendingProductsTask");
				
				
				commonVO.setAssignedAndPendingProductsResponseVO(assignedAndPendingProductTask.getResult());
				//checking if customer is eligible for re-contracting and preparing parallel call
				
				commonVO.setCustomerEligibleForRecontract(WLNOfferUtil.isCustomerEligibleForRecontracting(commonVO));
			}
        }if(productQualificationTask!=null){
        	if(productQualificationTask.getRuntimeException()!=null){
				/**
				 * If ProductQualification is null or has exception -> skip checkFeasibilityTask, continue the flow
				 */
				commonVO.setCallCheckFeasibilityInd(false);
				logger.info(functionName, "Exception happened while retrieving information from SalesCustomerInfoSvc.productQualification, setting callCheckFeasibilityInd as false");
			}else{
				
				commonVO.setProductQualificationAdapterResponseVO(productQualificationTask.getResult());
				// If request.offerFilter.productList is not empty, these products must be available in ProductQualificationAdapterResponseVO,
				// for HSIC, we must map the productCatalogIdentifier to a productTier, and compare if this productTier exist in ProductQualificationAdapterResponseVO
				// If a matching HSIC product tier code is not found in ProductQualificationAdapterResponseVO, then we will stop the flow and throw exception.
				List<String> prodListNotAvailable = new ArrayList<String>();
				WLNOfferUtil.checkProductListAvailability(commonVO, prodListNotAvailable);
				if (prodListNotAvailable.isEmpty()) {
					commonVO.setCallCheckFeasibilityInd(true);
				} else {
					commonVO.setCallCheckFeasibilityInd(false);
					messageList.addAll(generateMessageListForReqProdsNotQualified(prodListNotAvailable, commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId(), GAOSL_INVALID_INPUT));
				}
			}
        }if(creditProfileByCustomerIdTask!=null){
        	commonVO.setCreditProfileByCustIdResponseVO(creditProfileByCustomerIdTask.getResult());
        }if(creditEligibilityTask!=null){
        	commonVO.setCreditEligibilityAdapterResponseVO(creditEligibilityTask.getResult());
        }
        
		logger.exit(functionName);
		return messageList;
	}


	/**
	 * @param prodListNotAvailable
	 * @return
	 */
	public static List<MessageList> generateMessageListForReqProdsNotQualified(
			List<String> prodListNotAvailable, String transactionId, String errorMessageCode) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList msgList = new MessageList();
		msgList.setDateTimeStamp(new Date());
		msgList.setTransactionId(transactionId);
		msgList.setErrorCode(errorMessageCode);
		msgList.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		
		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		msg.setMessage(EnterpriseWLNSalesServicesConstants.REQUESTED_PRODUCTS_ARE_NOT_AVAILABLE + prodListNotAvailable.toString());
		msgList.setMessageList(Arrays.asList(msg));
		
		messageList.add(msgList);
		return messageList;
	}

	protected void validateInput(GetOffersRequestVO requestVO, List<String> missingElementList, List<String> invalidInputList, List<String> warningList) {
		String functionName = "validateInput()";
		logger.enter(functionName);
		
		// validate Header
		ValidationUtil.validateHeader(requestVO.getOperationHeader(), missingElementList, invalidInputList);
		
		if (requestVO.getSalesOfferCriteria() == null) {
			missingElementList.add(GAOSL_MISSING_SALES_OFFER_CRITERIA);
		}else {
			SalesOfferCriteriaVO salesOfferCriteria = requestVO.getSalesOfferCriteria();
			
			validateProvinceCdCityCdAndServiceAddressId(missingElementList, invalidInputList, salesOfferCriteria);
			
			validateServiceId(missingElementList, salesOfferCriteria);
			
			if (salesOfferCriteria.getOfferFilter() == null) {
				missingElementList.add(GAOSL_MISSING_OFFER_FILTER);
			} else {
				if (StringUtils.isEmpty(salesOfferCriteria.getOfferFilter().getAccountTypeCode())) {
					missingElementList.add(GAOSL_MISSING_OFFER_FILTER_ACCOUNT_TYPE_CD);
				}
				if(salesOfferCriteria.getOfferFilter().isBundleInd()==null){
					missingElementList.add(GAOSL_MISSING_BUNDLE_IND);
				}
//				if(!StringUtils.isEmpty(salesOfferCriteria.getCustomerId()) && (StringUtils.isEmpty(salesOfferCriteria.getBillingAccountNumber()))){
//					missingElementList.add(GAOSL_MISSING_BILLING_ACCOUNT_NUMBER);
//				}
//				if(!StringUtils.isEmpty(salesOfferCriteria.getBillingAccountNumber()) && (StringUtils.isEmpty(salesOfferCriteria.getCustomerId()))){
//					missingElementList.add(GAOSL_MISSING_CUSTOMER_ID);
//				}
				if(salesOfferCriteria.getCustomerId()==null && salesOfferCriteria.getBillingAccountNumber()==null && salesOfferCriteria.getSubscribedServiceIdentifier()!=null && !salesOfferCriteria.getSubscribedServiceIdentifier().isEmpty()){
					invalidInputList.add(GAOSL_INVALID_SERVICEIDLIST);
				}
				// add productOrderType validation => accepted values are 'activation' or 'renewal' ignore Case
				if (!salesOfferCriteria.getOfferFilter().getProductList().isEmpty()) {
					for (OfferProductHeader prod : salesOfferCriteria.getOfferFilter().getProductList()) {
						ProductOrderType productOrderType = prod.getProductOrderType();
						if (!isProductOrderTypeValid(productOrderType)) {
							invalidInputList.add(GAOSL_INVALID_PRODUCT_ORDER_TYPE);
							
						}
						// for each product,  productComponentList and (contractTerm or offerContractTerm) can not be all null
						/*if (prod.getProductComponentList().isEmpty() && StringUtils.isEmpty(prod.getContractTermCd()) && StringUtils.isEmpty(salesOfferCriteria.getOfferFilter().getOfferContractTermCd())) {
							missingElementList.add(GAOSL_MISSING_PRODUCT_COMP_CONTTTERM_OFFERCONTTERM_ARE_NULL + " ProductType: " + prod.getProductTypeCd());
						}*/
						
						// validate productType, must be one of these: HSIC, TTV, SING, STV
						if (StringUtils.isEmpty(prod.getProductTypeCd())) {
							missingElementList.add(GAOSL_MISSING_PRODUCT_TYPE);
						} else if (!isProductTypeValid(prod.getProductTypeCd())){
							invalidInputList.add(GAOSL_INVALID_PRODUCT_TYPE);
						}
					}
				}
			}
			
			if (StringUtils.isEmpty(salesOfferCriteria.getPromotionCd()) && areAllNull(salesOfferCriteria.getOfferFilter())) {
				missingElementList.add(GAOSL_MISSING_OFFER_FILTER_ELEMENTS_ALL_NULL);
			}
			
//			if (duplicateServiceIds(salesOfferCriteria.getSubscribedServiceIdentifier())) {
//				warningList.add(GAOSL_INVALID_DUPLICATE_SERVICE_IDS);
//			}
		}
		logger.exit(functionName);
	}

	/**
	 * @param productType
	 * @return
	 */
	private boolean isProductTypeValid(String productType) {
		if (!StringUtils.isEmpty(productType)) {
			return EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productType) 
					|| EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType) 
					|| EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(productType) 
					|| EnterpriseWLNSalesServicesConstants.STV.equalsIgnoreCase(productType)
					|| EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(productType) ;
		}
		return false;
	}

	/**
	 * @param productOrderType
	 * @return
	 */
	private boolean isProductOrderTypeValid(ProductOrderType productOrderType) {
		if (productOrderType != null) {
			return EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(productOrderType.getProductOrderTypeCd()) 
					|| EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(productOrderType.getProductOrderTypeCd())
					|| EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE.equalsIgnoreCase(productOrderType.getProductOrderTypeCd());
		}
		return true;
	}

	private void validateServiceId(List<String> missingElementList, SalesOfferCriteriaVO salesOfferCriteria) {
		if (salesOfferCriteria.getSubscribedServiceIdentifier() != null 
				&& !salesOfferCriteria.getSubscribedServiceIdentifier().isEmpty()) {
			for (SubscribedServiceIdentifierVO si : salesOfferCriteria.getSubscribedServiceIdentifier()) {
				if (StringUtils.isEmpty(si.getServiceId())) {
					missingElementList.add(GAOSL_MISSING_SUBSCRIBED_SERVICE_ID);
				}
			}
		}
	}

	private void validateProvinceCdCityCdAndServiceAddressId(List<String> missingElementList, List<String> invalidInputList, SalesOfferCriteriaVO salesOfferCriteria) {
		if (salesOfferCriteria.getServiceAddress() == null) {
			missingElementList.add(GAOSL_MISSING_SERVICE_ADDRESS);
		}else {
			if (StringUtils.isEmpty(salesOfferCriteria.getServiceAddress().getProvinceCode())) {
				missingElementList.add(GAOSL_MISSING_SERVICE_ADDRESS_PROVINCE_CD);
			} else {
				boolean isValidProvince = ValidationUtil.validateProvinceCode(salesOfferCriteria.getServiceAddress().getProvinceCode());
				if (!isValidProvince) {
					invalidInputList.add(GAOSL_INVALID_SERVICE_ADDRESS_PROVINCE_CD);
				}
			}
			if (StringUtils.isEmpty(salesOfferCriteria.getServiceAddress().getCityCode())) {
				missingElementList.add(GAOSL_MISSING_SERVICE_ADDRESS_CITY_CD);
			}
			// validate serviceAddressId
			if (StringUtils.isEmpty(salesOfferCriteria.getServiceAddress().getServiceAddressId())) {
				missingElementList.add(GAOSL_MISSING_SERVICE_ADDRESS_ID);
			}
		}
	}

	private boolean duplicateServiceIds(List<SubscribedServiceIdentifierVO> subscribedServiceIdentifier) {
		List<String> siList = new ArrayList<String>();
		if (subscribedServiceIdentifier != null && !subscribedServiceIdentifier.isEmpty()) {
			for (SubscribedServiceIdentifierVO si: subscribedServiceIdentifier) {
				siList.add(si.getServiceId());
			}
			Set<String> setList = new HashSet<String>(siList);
			return setList.size() < siList.size();
		}
		
		return false;
	}

	private boolean areAllNull(WirelineOfferFilter offerFilter) {
		return offerFilter == null || (StringUtils.isEmpty(offerFilter.getOfferContractTermCd()) && (offerFilter.getProductList() == null || offerFilter.getProductList().isEmpty()) && offerFilter.isBundleInd() == null);
	}


}
