package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;

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

import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsc.delegate.OfferSummaryListDelegate;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.task.GetAssignedAndPendingProductTask;
import com.telus.csm.ewlnsc.task.GetAvailableServiceInstanceListTask;
import com.telus.csm.ewlnsc.task.GetCreditProfileByCustomerIdTask;
import com.telus.csm.ewlnsc.task.GetServiceAddressDetailsTask;
import com.telus.csm.ewlnsc.transformer.GetAccessoryOfferListTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.AccessoryOfferCriteria;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.ProductCategoryQualification;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.ConsumerCreditProfile;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.CreditWorthiness;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineSalesOrderSummary;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ServiceIdentifier;

import commonj.work.Work;


@Component
@Scope(SCOPE_PROTOTYPE)
public class GetAccessoryOfferListHelper {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAccessoryOfferListHelper.class);

	protected SalesOfferCommonVO commonVO = new SalesOfferCommonVO();
	
	private ICommonJWorkManager workManager;
	
	@Autowired
	private com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	public GetAccessoryOfferListHelper(){
		try {
			workManager = WorkManagerFactory.getCommonJWorkManager();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@MonitorPerformance
	public GetOffersResponseVO execute(GetOffersRequestVO requestVO) {
		String functionName = "execute";
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
			getOffersResponseVO.setMessageList(ValidationUtil.generateMessageList(requestVO.getOperationHeader(), missingElementList, invalidInputList, warningList, GAOL_MISSING_MANDATORY_ELEMENTS, GAOL_INVALID_INPUT));
			logger.info(functionName, INPUT_VALIDATION_FAILED_OR_WARNING_MSG);
		}
		
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
			return getOffersResponseVO;
		}
		
		/*
		 * Step 2: prepareCommonObject
		 */
		Collection<Work> resultTaskList = prepareCommonObject(requestVO);
		
		/*
		 * Step 2.1: validate responses from downstream services
		 */
		List<MessageList> exceptionMessageList = processFirstRoundParallelCalls(resultTaskList);
		if (!exceptionMessageList.isEmpty()) {
			getOffersResponseVO.setMessageList(exceptionMessageList);
			return getOffersResponseVO;
		}

		/*
		 * Step 3: getAvailableOffer
		 */
		GetOfferListAdapterResponse offerListAdapterResponse = new OfferSummaryListDelegate().getAccessoryOfferList(commonVO);
		commonVO.setOfferListAdapterResponse(offerListAdapterResponse);
		
		/*
		 * Step 3.1: checking if call to OIS returned exception
		 */
		if (offerListAdapterResponse != null) {
			if ( !offerListAdapterResponse.isSuccessfulProcessInd() ||
				 offerListAdapterResponse.isMsgHasError() ||
				 offerListAdapterResponse.getOfferList() == null ||
				 offerListAdapterResponse.getOfferList().isEmpty() ) {
				getOffersResponseVO.setMessageList(GetAccessoryOfferListTransformer.generateMessageList(requestVO.getOperationHeader(), GAOL_OFFER_NOT_APPLICABLE, "No offers found.", null));
			}
			else {
				/*
				 * Step 4: applyFilters
				 */
				List<TraceVO> traces = applyFilters(getOffersResponseVO);

				GetAccessoryOfferListTransformer.transform(commonVO, getOffersResponseVO);
			}
		}

		logger.exit(functionName);

		return getOffersResponseVO;
	}

	protected Collection<Work> prepareCommonObject(GetOffersRequestVO requestVO) {
		String functionName = "prepareCommonObject";

		logger.enter(functionName);
		
		commonVO.setOffersRequestVO(requestVO);
		
		List<Work> workTaskList = new ArrayList<Work>();
		
		// Prepare call for GetConsolidatedAccountProfile if account is provided
		if (requestVO.getAccessoryOfferCriteria() != null && !StringUtils.isBlank(requestVO.getAccessoryOfferCriteria().getBillingAccountNumber()) && isServiceIdPresentForSplit(requestVO)) {
			GetAssignedAndPendingProductRequestVO consolidatedaccProfileAdapterReq = GetAccessoryOfferListTransformer.transformConsolidatedAccRequest(requestVO);
			
			
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
				GetAssignedAndPendingProductTask consolidatedAccountTask = new GetAssignedAndPendingProductTask(consolidatedaccProfileAdapterReq,commonVO.getOffersRequestVO(), wirelineSalesEJBAdapter);
				workTaskList.add(consolidatedAccountTask);
			}
			
		}

		if (!StringUtils.isBlank(requestVO.getAccessoryOfferCriteria().getCustomerId())) {
			// Prepare call to GetCreditProfileByCustomerId if customerId is passed
			GetCreditProfileByCustomerIdAdapterRequest creditProfileByCustomerIdAdapterReq = GetAccessoryOfferListTransformer.transformCreditProfileByCustId(requestVO);
			GetCreditProfileByCustomerIdTask getCreditProfileByCustomerIdTask = new GetCreditProfileByCustomerIdTask(creditProfileByCustomerIdAdapterReq);
			workTaskList.add(getCreditProfileByCustomerIdTask);

			// Prepare call to GetAvailableServiceInstanceList if customerId is passed
			GetAvailableServiceInstanceListAdapterRequest availableServiceInstanceListAdapterRequest = GetAccessoryOfferListTransformer.transformAvailableServiceInstanceListRequest(requestVO);
			GetAvailableServiceInstanceListTask availableServiceInstanceListTask = new GetAvailableServiceInstanceListTask(availableServiceInstanceListAdapterRequest);
			workTaskList.add(availableServiceInstanceListTask);
		}

		// Prepare call for GetServiceAddressDetails if serviceAddressId is provided
		if ( (requestVO.getAccessoryOfferCriteria().getServiceAddress() != null) && (requestVO.getAccessoryOfferCriteria().getServiceAddress().getServiceAddressId() != null) ) {
			ServiceAddressRequestVO serviceAddressRequestVO = GetAccessoryOfferListTransformer.transformServiceAddressReq(requestVO);
			GetServiceAddressDetailsTask serviceAddressDetailsTask = new GetServiceAddressDetailsTask(serviceAddressRequestVO);
			workTaskList.add(serviceAddressDetailsTask);
		}
		
		logger.exit(functionName);

		return executeParallelCall(workTaskList);
	}
	
	private boolean isServiceIdPresentForSplit(GetOffersRequestVO requestVO) {
		if(!WLNOfferUtil.isJoinedOffers(requestVO.getOperationHeader()) && CollectionUtils.isEmpty(requestVO.getAccessoryOfferCriteria().getSubscribedServiceIdentifierList())){
			return false;
		}
		return true;
	}
	
	private Collection<Work> executeParallelCall(List<Work> taskList){
		String functionName = "executeParallelCall";
		logger.enter(functionName);
		Collection<Work> responseTaskList = new ArrayList<Work>();
		try {
			responseTaskList = workManager.processTasks(taskList);
		}
		catch(Exception e){
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
		GetCreditProfileByCustomerIdTask creditProfileByCustomerIdTask = null;
		GetAvailableServiceInstanceListTask availableServiceInstanceListTask = null;
		GetServiceAddressDetailsTask serviceAddressDetailsTask = null;

        for (Work resultTask : responseTaskList) {
        	if (resultTask != null) {
				if (resultTask instanceof GetAssignedAndPendingProductTask) {
					assignedAndPendingProductTask = (GetAssignedAndPendingProductTask) resultTask;
				}
        		else if (resultTask instanceof GetCreditProfileByCustomerIdTask) {
        			creditProfileByCustomerIdTask = (GetCreditProfileByCustomerIdTask) resultTask;
        		}
        		else if (resultTask instanceof GetAvailableServiceInstanceListTask) {
        			availableServiceInstanceListTask = (GetAvailableServiceInstanceListTask) resultTask;
        		}
        		else if (resultTask instanceof GetServiceAddressDetailsTask) {
        			serviceAddressDetailsTask = (GetServiceAddressDetailsTask) resultTask;
        		}
        	}
        }

        if (assignedAndPendingProductTask != null) {
			if (assignedAndPendingProductTask.getRuntimeException() != null) {
				GetAssignedAndPendingProductResponseVO assignedAndPendingProductsVO = OfferSummaryExceptionHandler.transformExceptionForAssignedAndPendingProducts(assignedAndPendingProductTask.getRuntimeException(), commonVO.getOffersRequestVO().getOperationHeader(), EnterpriseWLNSalesServicesErrorCodes.GAOL_DOWN_STREAM_ERROR);
				if (WLNOfferUtil.banNotFoundError(assignedAndPendingProductsVO)) {
					logger.info(functionName, "Error: BAN not found was retrieved from GCAP. Flow will continue.");
				} else {
					messageList = assignedAndPendingProductsVO.getMessageList();
					return messageList;

				}
			}
			else {
				logger.info(functionName, "No Exception was found on GetAssignedAndPendingProductsTask");

				commonVO.setAssignedAndPendingProductsResponseVO(assignedAndPendingProductTask.getResult());
			}
        }

        if (creditProfileByCustomerIdTask != null) {
        	if ( (creditProfileByCustomerIdTask.getResult() == null) || (creditProfileByCustomerIdTask.getResult().isMsgHasError()) ||
        		 (!creditProfileByCustomerIdTask.getResult().isSuccessfulProcessInd()) ) {
        		// If getCreditProfileByCustomerId returns error, treat it as an established customer.
        		// Credit value = E, boltonInd = true, meaning customer can still get gifts. Do not return error.
        		GetCreditProfileByCustomerIdAdapterResponse creditProfileByCustIdResponseVO = new GetCreditProfileByCustomerIdAdapterResponse();
        		ConsumerCreditProfile creditProfile = new ConsumerCreditProfile();
        		CreditWorthiness creditWorthiness = new CreditWorthiness();
        		creditWorthiness.setCreditValueCd(CREDIT_VALUE_ESTABLISHED_CUSTOMER);
        		ProductCategoryQualification productCategoryQualification = new ProductCategoryQualification ();
        		productCategoryQualification.setBoltOnInd(true);
        		creditWorthiness.setProductCategoryQualification(productCategoryQualification);
        		creditProfile.setCreditWorthiness(creditWorthiness);
        		creditProfileByCustIdResponseVO.setCreditProfile(creditProfile);
        		commonVO.setCreditProfileByCustIdResponseVO(creditProfileByCustIdResponseVO);
        	}
        	else if ( (creditProfileByCustomerIdTask.getResult().getCreditProfile() == null) ||
        			  (creditProfileByCustomerIdTask.getResult().getCreditProfile().getCreditWorthiness() == null) ||
        			  (creditProfileByCustomerIdTask.getResult().getCreditProfile().getCreditWorthiness().getProductCategoryQualification() == null) ) {
        		ConsumerCreditProfile creditProfile = new ConsumerCreditProfile();
        		CreditWorthiness creditWorthiness = new CreditWorthiness();
        		creditWorthiness.setCreditValueCd(CREDIT_VALUE_ESTABLISHED_CUSTOMER);
        		ProductCategoryQualification productCategoryQualification = new ProductCategoryQualification ();
        		productCategoryQualification.setBoltOnInd(true);
        		creditWorthiness.setProductCategoryQualification(productCategoryQualification);
        		creditProfile.setCreditWorthiness(creditWorthiness);
        		creditProfileByCustomerIdTask.getResult().setCreditProfile(creditProfile);
        		commonVO.setCreditProfileByCustIdResponseVO(creditProfileByCustomerIdTask.getResult());
        	}
        	else {
        		commonVO.setCreditProfileByCustIdResponseVO(creditProfileByCustomerIdTask.getResult());
        	}
        }

        if (availableServiceInstanceListTask != null) {
			if (availableServiceInstanceListTask.getRuntimeException() != null) {
				String errorMessage = "Error happened while retrieving information from IBoltOnOfferSvc.getAvailableServiceInstanceList, check RelatedMessageList for detail.";

				messageList.addAll(GetAccessoryOfferListTransformer.generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), GAOL_DOWN_STREAM_ERROR, errorMessage, null));

				return messageList;
			}
			else {
				logger.info(functionName, "No Exception was found on GetAvailableServiceInstanceListTask");

	        	commonVO.setAvailableServiceInstanceListAdapterResponse(availableServiceInstanceListTask.getResult());
			}
        }

        if (serviceAddressDetailsTask != null) {
        	if (serviceAddressDetailsTask.getRuntimeException() != null) {
        		String errorMessage = "Error happened while retrieving information from IServiceAddressMgmtSvcAdapter.getServiceAddressDetails, check RelatedMessageList for detail.";

        		messageList.addAll(GetAccessoryOfferListTransformer.generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), GAOL_DOWN_STREAM_ERROR, errorMessage, null));

        		return messageList;
        	}
        	else if ( (serviceAddressDetailsTask.getResult() == null) ||
        			  (serviceAddressDetailsTask.getResult().getServiceAddress() == null) ) {
        		String errorMessage = "Empty response came from IServiceAddressMgmtSvcAdapter.getServiceAddressDetails.";

        		messageList.addAll(GetAccessoryOfferListTransformer.generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), GAOL_NO_SERVICE_ADDRESS_FOUND, errorMessage, null));

        		return messageList;
        	}
        	else {
        		logger.info(functionName, "No Exception was found on GetServiceAddressDetailsTask");

        		commonVO.setServiceAddressResponseVO(serviceAddressDetailsTask.getResult());
        	}
        }

		logger.exit(functionName);

		return messageList;
	}

	/**
	 * @param prodListNotAvailable
	 * @return
	 */
	public static List<MessageList> generateMessageListForReqProdsNotQualified(List<String> prodListNotAvailable, String transactionId, String errorMessageCode) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList msgList = new MessageList();
		msgList.setDateTimeStamp(new Date());
		msgList.setTransactionId(transactionId);
		msgList.setErrorCode(errorMessageCode);
		msgList.setMessageType(MESSAGE_TYPE_ERROR);
		
		Message msg = new Message();
		msg.setLocale(CANADIAN_ENGLISH_LOCALE);
		msg.setMessage(REQUESTED_PRODUCTS_ARE_NOT_AVAILABLE + prodListNotAvailable.toString());
		msgList.setMessageList(Arrays.asList(msg));
		
		messageList.add(msgList);
		return messageList;
	}

	protected void validateInput(GetOffersRequestVO requestVO, List<String> missingElementList, List<String> invalidInputList, List<String> warningList) {
		String functionName = "validateInput()";

		logger.enter(functionName);
		
		// validate Header
		ValidationUtil.validateHeader(requestVO.getOperationHeader(), missingElementList, invalidInputList);

		// Relaxed on June, 26th 2018 - only apply this input validation rule if AgentProfile is present in the request.
		/*
		if (requestVO.getOperationHeader().getAgentProfile() != null) {
			ValidationUtil.validateHeaderAgentProfileUserPrivilegeRoleCodeList(requestVO.getOperationHeader(), missingElementList);
		}
		*/

		AccessoryOfferCriteria accessoryOfferCriteria = requestVO.getAccessoryOfferCriteria();

		validateProvinceCdCityCdAndServiceAddressId(missingElementList, invalidInputList, accessoryOfferCriteria);

		validateServiceId(missingElementList, accessoryOfferCriteria);

		if (accessoryOfferCriteria.getOfferFilter() == null) {
			missingElementList.add(GAOL_MISSING_OFFER_FILTER);
		}
		else {
			if (StringUtils.isEmpty(accessoryOfferCriteria.getOfferFilter().getAccountTypeCode())) {
				missingElementList.add(GAOL_MISSING_OFFER_FILTER_ACCOUNT_TYPE_CD);
			}
		}

		/* Rule relaxed on June 22nd, 2018 - Alejandro
		if ( (StringUtils.isEmpty(accessoryOfferCriteria.getCustomerId())) && (StringUtils.isEmpty(accessoryOfferCriteria.getBillingAccountNumber())) ) {
			missingElementList.add(GAOL_MISSING_CUSTOMER_ID_AND_BILLING_ACCOUNT_NUMBER);
		}
		*/

		if (!StringUtils.isEmpty(accessoryOfferCriteria.getBillingAccountNumber()) && StringUtils.isEmpty(accessoryOfferCriteria.getCustomerId())) {
			missingElementList.add(GAOL_MISSING_CUSTOMER_ID);
		}

		//Rule relaxed on July 10 
		/*if ( (!StringUtils.isEmpty(accessoryOfferCriteria.getBillingAccountNumber())) && 
			 ( (accessoryOfferCriteria.getSubscribedServiceIdentifierList() == null) || (accessoryOfferCriteria.getSubscribedServiceIdentifierList().isEmpty()) ) ) {
			missingElementList.add(GAOL_MISSING_SERVICEIDLIST);
		}*/

		if ( (accessoryOfferCriteria.getAssociatedWirelineSalesSummaryList() == null) ||
				(accessoryOfferCriteria.getAssociatedWirelineSalesSummaryList().isEmpty()) ) {
			missingElementList.add(GAOL_MISSING_ASSOCIATED_WIRELINE_SALES_SUMMARY_LIST);
		}
		else {
			for (WirelineSalesOrderSummary wirelineSalesOrderSummary : accessoryOfferCriteria.getAssociatedWirelineSalesSummaryList()) {
				if ( (wirelineSalesOrderSummary.getProductList() == null) || (wirelineSalesOrderSummary.getProductList().isEmpty()) ) {
					missingElementList.add(GAOL_MISSING_ASSOCIATED_WIRELINE_SALES_SUMMARY_LIST_PRODUCT_LIST);
				}
				else {
					for (OfferProductHeader prod : wirelineSalesOrderSummary.getProductList()) {
						ProductOrderType productOrderType = prod.getProductOrderType();

						// add productOrderType validation => accepted values are 'activation' or 'renewal' ignore Case
						if (!isProductOrderTypeValid(productOrderType)) {
							invalidInputList.add(GAOL_INVALID_PRODUCT_ORDER_TYPE);
						}

						// for each product,  productComponentList and (contractTerm or offerContractTerm) can not be all null

						// separate into 2 different validations
						if ( (prod.getProductComponentList().isEmpty()) && (StringUtils.isEmpty(prod.getContractTermCd())) )  {
							missingElementList.add(GAOL_MISSING_CONTRACT_TERM_AND_PRODUCT_COMPONENT_LIST_ARE_NULL + " ProductType: " + prod.getProductTypeCd());
						}

						// validate productType, must be one of these: HSIC, TTV, SING, STV
						if (StringUtils.isEmpty(prod.getProductTypeCd())) {
							missingElementList.add(GAOL_MISSING_PRODUCT_TYPE);
						}
						else if (!isProductTypeValid(prod.getProductTypeCd())){
							invalidInputList.add(GAOL_INVALID_PRODUCT_TYPE);
						}
					}
				}
			}
		}

//		if (duplicateServiceIds(accessoryOfferCriteria.getSubscribedServiceIdentifierList())) {
//			warningList.add(GACOSL_INVALID_DUPLICATE_SERVICE_IDS);
//		}

		logger.exit(functionName);
	}

	/**
	 * @param productType
	 * @return
	 */
	private boolean isProductTypeValid(String productType) {
		if (!StringUtils.isEmpty(productType)) {
			return HSIC.equalsIgnoreCase(productType) 
					|| TTV.equalsIgnoreCase(productType) 
					|| SING.equalsIgnoreCase(productType) 
					|| STV.equalsIgnoreCase(productType);
		}
		return false;
	}

	/**
	 * @param productOrderType
	 * @return
	 */
	private boolean isProductOrderTypeValid(ProductOrderType productOrderType) {
		if (productOrderType != null) {
			return ACTIVATION.equalsIgnoreCase(productOrderType.getProductOrderTypeCd()) 
					|| UPGRADE.equalsIgnoreCase(productOrderType.getProductOrderTypeCd());
		}
		return true;
	}

	private void validateServiceId(List<String> missingElementList, AccessoryOfferCriteria accessoryOfferCriteria) {
		if (accessoryOfferCriteria.getSubscribedServiceIdentifierList() != null 
				&& !accessoryOfferCriteria.getSubscribedServiceIdentifierList().isEmpty()) {
			for (ServiceIdentifier si : accessoryOfferCriteria.getSubscribedServiceIdentifierList()) {
				if (StringUtils.isEmpty(si.getServiceId())) {
					missingElementList.add(GAOL_MISSING_SUBSCRIBED_SERVICE_ID);
				}
			}
		}
	}

	private void validateProvinceCdCityCdAndServiceAddressId(List<String> missingElementList, List<String> invalidInputList, AccessoryOfferCriteria accessoryOfferCriteria) {
		if (accessoryOfferCriteria.getServiceAddress() == null) {
			missingElementList.add(GAOL_MISSING_SERVICE_ADDRESS);
		}else {
			if (StringUtils.isEmpty(accessoryOfferCriteria.getServiceAddress().getProvinceCode())) {
				missingElementList.add(GAOL_MISSING_SERVICE_ADDRESS_PROVINCE_CD);
			} else {
				boolean isValidProvince = ValidationUtil.validateProvinceCode(accessoryOfferCriteria.getServiceAddress().getProvinceCode());
				if (!isValidProvince) {
					invalidInputList.add(GAOL_INVALID_SERVICE_ADDRESS_PROVINCE_CD);
				}
			}
			if (StringUtils.isEmpty(accessoryOfferCriteria.getServiceAddress().getCityCode())) {
				missingElementList.add(GAOL_MISSING_SERVICE_ADDRESS_CITY_CD);
			}
			// validate serviceAddressId
			if (StringUtils.isEmpty(accessoryOfferCriteria.getServiceAddress().getServiceAddressId())) {
				missingElementList.add(GAOL_MISSING_SERVICE_ADDRESS_ID);
			}
		}
	}

	private boolean duplicateServiceIds(List<ServiceIdentifier> serviceIdentifierList) {
		List<String> siList = new ArrayList<String>();
		if (serviceIdentifierList != null && !serviceIdentifierList.isEmpty()) {
			for (ServiceIdentifier si: serviceIdentifierList) {
				siList.add(si.getServiceId());
			}
			Set<String> setList = new HashSet<String>(siList);
			return setList.size() < siList.size();
		}
		
		return false;
	}

	private List<TraceVO> applyFilters(GetOffersResponseVO getOffersResponseVO) {
		final OfferFilterHelper offerFilterHelper = new OfferFilterHelper(commonVO);
		
		final List<TraceVO> traces = new ArrayList<TraceVO>();
		
		final List<Offer> inputOfferList = commonVO.getOfferListAdapterResponse().getOfferList();

		final List<Offer> outputOfferList = offerFilterHelper.filterAccessoryOfferSummary(inputOfferList, traces);
				
		commonVO.getOfferListAdapterResponse().setOfferList(outputOfferList);
		
		final StringBuilder logMsg = new StringBuilder();
		
		if(!CollectionUtils.isEmpty(traces)){
			for (TraceVO trace: traces) {
				logMsg.append("\n"+ trace.toLogMessage()); 
			}
		}

		logger.info("applyFilters", "Trace from OfferFilterHelper.filterAccessoryOfferSummary" + logMsg.toString()); 

		return traces;
	}
}
