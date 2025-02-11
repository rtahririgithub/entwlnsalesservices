package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_NOCHANGE;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_SWEETENER_OFFER_FILTER_CRITERIA;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_ASSOCIATED_WRIELINE_SALES_SUMMARY;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_OFFER_HEADER;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_OFFER_ID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_OFFER_PROGRAM_ID;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_OFFER_CODE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_OFFER_PERSPECTIVE_DATE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_OFFER_FILTER;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_ACCOUNT_TYPE_CODE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_PRODUCT_LIST;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_PRODUCT_ORDER_TYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_PRODUCT_ORDER_TYPE_CODE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_INVALID_PRODUCT_ORDER_TYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_PRODUCT_TYPE_CODE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_PRODUCT_COMPONENT;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_PRODUCT_CATALOGUE_IDENTIFIER;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_CONTRACT_TERM_CODE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_SERVICE_ADDRESS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_PROVINCE_CODE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_CITY_CODE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_MISSING_MANDATORY_ELEMENTS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_OFFER_NOT_APPLICABLE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_DOWN_STREAM_ERROR;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GASOL_INVALID_INPUT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.task.GetOfferListByOfferIdentifierListTask;
import com.telus.csm.ewlnsc.task.GetSweetenerListByOfferSummaryListForCustomerTask;
import com.telus.csm.ewlnsc.transformer.GetAvailableOfferSummaryTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SweetnerOfferFilterCriteria;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.WirelineSalesSummary;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;
import commonj.work.Work;

@Component
@Scope(SCOPE_PROTOTYPE)
public class GetAvailableSweetenerOfferListHelper {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetAvailableSweetenerOfferListHelper.class);

	protected SalesOfferCommonVO commonVO = new SalesOfferCommonVO();

	private ICommonJWorkManager workManager;

	
	public GetAvailableSweetenerOfferListHelper(){
		try {
			workManager = WorkManagerFactory.getCommonJWorkManager();
		}
		catch (Exception e) {
			logger.error(e);
		}
	}

	@MonitorPerformance
	public GetOffersResponseVO execute(GetOffersRequestVO requestVO) {
		String functionName = "execute";

		logger.enter(functionName);
		logger.debug(functionName, "GetOffersRequestVO:\n" + JsonUtil.getJsonFromObjectNonNUll(requestVO));

		GetOffersResponseVO getOffersResponseVO = new GetOffersResponseVO();
		
		/*
		 * Step 1: validate Input
		 */
		List<String> missingElementList = new ArrayList<String>();
		List<String> invalidInputList = new ArrayList<String>();
		List<String> warningList = new ArrayList<String>();
		
		validateInput(requestVO, missingElementList, invalidInputList);

		if ( (!missingElementList.isEmpty()) || (!invalidInputList.isEmpty()) ) {
			getOffersResponseVO.setMessageList(ValidationUtil.generateMessageList(requestVO.getOperationHeader(), missingElementList, invalidInputList, warningList, GASOL_MISSING_MANDATORY_ELEMENTS, GASOL_INVALID_INPUT));
			logger.info(functionName, EnterpriseWLNSalesServicesConstants.INPUT_VALIDATION_FAILED_OR_WARNING_MSG);
		}
		
		if ( (!missingElementList.isEmpty()) || (!invalidInputList.isEmpty()) ) {
			return getOffersResponseVO;
		}


		/*
		 * Step 2: prepareCommonObject
		 */
		Collection<Work> resultTaskList = prepareCommonObject(requestVO);


		/*
		 * Step 3: validate responses from downstream services
		 */
		List<MessageList> exceptionMessageList = processFirstRoundParallelCalls(resultTaskList);
		if (!exceptionMessageList.isEmpty()) {
			getOffersResponseVO.setMessageList(exceptionMessageList);
			return getOffersResponseVO;
		}


		/*
		 * Step 4: applyFilters
		 */
		if ( (commonVO.getOfferListAdapterResponse() != null) &&
			 (commonVO.getOfferListAdapterResponse().isSuccessfulProcessInd()) &&
			 (!commonVO.getOfferListAdapterResponse().isMsgHasError()) &&
			 (commonVO.getOfferListAdapterResponse().getSweetenerList() != null) &&
			 (!commonVO.getOfferListAdapterResponse().getSweetenerList().isEmpty()) &&
			 (commonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse() != null) &&
			 (commonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse().isSuccessfulProcessInd()) &&
			 (!commonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse().isMsgHasError()) &&
			 (commonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse().getOfferList() != null) &&
			 (!commonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse().getOfferList().isEmpty()) ) {
			List<TraceVO> traces = applyFilters();
		}

		GetAvailableOfferSummaryTransformer.transform(commonVO, getOffersResponseVO);
				
		logger.exit(functionName);
		
		return getOffersResponseVO;
	}

	protected Collection<Work> prepareCommonObject(GetOffersRequestVO requestVO) {
		String functionName = "prepareCommonObject";

		logger.enter(functionName);
		
		commonVO.setOffersRequestVO(requestVO);
		
		List<Work> workTaskList = new ArrayList<Work>();
		
		// Prepare call for GetSweetenerListByOfferSummaryListForCustomer
		GetSweetenerListByOfferSummaryListForCustomerTask sweetenerListByOfferSummaryListForCustomerTask = new GetSweetenerListByOfferSummaryListForCustomerTask(commonVO);
		workTaskList.add(sweetenerListByOfferSummaryListForCustomerTask);

		// Prepare call for GetOfferListByOfferIdentifierList
		if ( (requestVO.getSweetenerOfferFilterCriteria().getOfferFilter() != null) &&
			 (requestVO.getSweetenerOfferFilterCriteria().getOfferFilter().getSelectedAccessoryOfferList() != null) &&
			 (!requestVO.getSweetenerOfferFilterCriteria().getOfferFilter().getSelectedAccessoryOfferList().isEmpty()) ) {
			GetOfferListByOfferIdentifierListTask offerListByOfferIdentifierListTask = new GetOfferListByOfferIdentifierListTask(commonVO);
			workTaskList.add(offerListByOfferIdentifierListTask);
		}

		logger.exit(functionName);

		return executeParallelCall(workTaskList);
	}

	private Collection<Work> executeParallelCall(List<Work> taskList) {
		String functionName = "executeParallelCall";

		logger.enter(functionName);

		Collection<Work> responseTaskList = new ArrayList<Work>();

		try {
			responseTaskList = workManager.processTasks(taskList);
		}
		catch(Exception e) {
			logger.error("",functionName,e.getMessage(),e);

			throw new RuntimeException(e);
		}

		logger.exit(functionName);

		return responseTaskList;
	}

	protected void validateInput(GetOffersRequestVO requestVO, List<String> missingElementList, List<String> invalidInputList) {
		String functionName = "validateInput()";
		logger.enter(functionName);

		SweetnerOfferFilterCriteria sweetnerOfferFilterCriteria = requestVO.getSweetenerOfferFilterCriteria();

		if (sweetnerOfferFilterCriteria == null) {
			missingElementList.add(GASOL_MISSING_SWEETENER_OFFER_FILTER_CRITERIA);
		}
		else {
			if ( (sweetnerOfferFilterCriteria.getAssociatedWirelineSalesSummaryList() == null) || (sweetnerOfferFilterCriteria.getAssociatedWirelineSalesSummaryList().size() < 1) ) {
				missingElementList.add(GASOL_MISSING_ASSOCIATED_WRIELINE_SALES_SUMMARY);
			}
			else {
				for (WirelineSalesSummary associatedWirelineSalesSummary : sweetnerOfferFilterCriteria.getAssociatedWirelineSalesSummaryList()) {
					if (associatedWirelineSalesSummary == null) {
						missingElementList.add(GASOL_MISSING_ASSOCIATED_WRIELINE_SALES_SUMMARY);
					}
					else {
						if (associatedWirelineSalesSummary.getOfferHeader() == null) {
							missingElementList.add(GASOL_MISSING_OFFER_HEADER);
						}
						else {
							if ( (associatedWirelineSalesSummary.getOfferHeader().getOfferId() == null) || (associatedWirelineSalesSummary.getOfferHeader().getOfferId().isEmpty()) ) {
								missingElementList.add(GASOL_MISSING_OFFER_ID);
							}

							if ( (associatedWirelineSalesSummary.getOfferHeader().getOfferProgramId() == null) || (associatedWirelineSalesSummary.getOfferHeader().getOfferProgramId().isEmpty()) ) {
								missingElementList.add(GASOL_MISSING_OFFER_PROGRAM_ID);
							}

							if ( (associatedWirelineSalesSummary.getOfferHeader().getOfferCode() == null) || (associatedWirelineSalesSummary.getOfferHeader().getOfferCode().isEmpty()) ) {
								missingElementList.add(GASOL_MISSING_OFFER_CODE);
							}

							if (associatedWirelineSalesSummary.getOfferHeader().getPerspectiveDate() == null) {
								missingElementList.add(GASOL_MISSING_OFFER_PERSPECTIVE_DATE);
							}

							if (associatedWirelineSalesSummary.getOfferHeader().getOfferFilter() == null) {
								missingElementList.add(GASOL_MISSING_OFFER_FILTER);
							}
							else {
								if ( (associatedWirelineSalesSummary.getOfferHeader().getOfferFilter().getAccountTypeCode() == null) || (associatedWirelineSalesSummary.getOfferHeader().getOfferFilter().getAccountTypeCode().isEmpty()) ) {
									missingElementList.add(GASOL_MISSING_ACCOUNT_TYPE_CODE);
								}
							}
						}

						if ( (associatedWirelineSalesSummary.getProductList() == null) || (associatedWirelineSalesSummary.getProductList().size() < 1) ) {
							missingElementList.add(GASOL_MISSING_PRODUCT_LIST);
						}
						else {
							for (OfferProductHeader product : associatedWirelineSalesSummary.getProductList()) {
								if (product.getProductOrderType() == null) {
									missingElementList.add(GASOL_MISSING_PRODUCT_ORDER_TYPE);
								}
								else {
									if ( (product.getProductOrderType().getProductOrderTypeCd() == null) || (product.getProductOrderType().getProductOrderTypeCd().isEmpty()) ) {
										missingElementList.add(GASOL_MISSING_PRODUCT_ORDER_TYPE_CODE);
									}
									else {
										if ( (!OIS_PRODUCT_INSTANCE_ACTIVATION.equalsIgnoreCase(product.getProductOrderType().getProductOrderTypeCd())) &&
											 (!OIS_PRODUCT_INSTANCE_UPGRADE.equalsIgnoreCase(product.getProductOrderType().getProductOrderTypeCd()) &&
											 (!OIS_PRODUCT_INSTANCE_NOCHANGE.equalsIgnoreCase(product.getProductOrderType().getProductOrderTypeCd()))) ) {
											invalidInputList.add(GASOL_INVALID_PRODUCT_ORDER_TYPE);
										}
									}
								}

								if ( (product.getProductTypeCd() == null) || (product.getProductTypeCd().isEmpty()) ) {
									missingElementList.add(GASOL_MISSING_PRODUCT_TYPE_CODE);
								}

								if ( (product.getContractTermCd() == null) || (product.getContractTermCd().isEmpty()) ) {
									missingElementList.add(GASOL_MISSING_CONTRACT_TERM_CODE);
								}

								if(!OIS_PRODUCT_INSTANCE_NOCHANGE.equalsIgnoreCase(product.getProductOrderType().getProductOrderTypeCd())){
									if ( (product.getProductComponentList() == null) || (product.getProductComponentList().size() < 1) ) {
										missingElementList.add(GASOL_MISSING_PRODUCT_COMPONENT);
									}
									else {
										for (ProductComponentIdentifier productComponent : product.getProductComponentList()) {
											if ( (productComponent.getProductCatalogueIdentifier() == null) || (productComponent.getProductCatalogueIdentifier().isEmpty()) ) {
												missingElementList.add(GASOL_MISSING_PRODUCT_CATALOGUE_IDENTIFIER);
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if (sweetnerOfferFilterCriteria.getServiceAddress() == null) {
				missingElementList.add(GASOL_MISSING_SERVICE_ADDRESS);
			}
			else {
				if ( (sweetnerOfferFilterCriteria.getServiceAddress().getProvinceCode() == null) || (sweetnerOfferFilterCriteria.getServiceAddress().getProvinceCode().isEmpty()) ) {
					missingElementList.add(GASOL_MISSING_PROVINCE_CODE);
				}

				if ( (sweetnerOfferFilterCriteria.getServiceAddress().getCityCode() == null) || (sweetnerOfferFilterCriteria.getServiceAddress().getCityCode().isEmpty()) ) {
					missingElementList.add(GASOL_MISSING_CITY_CODE);
				}
			}
		}

		logger.exit(functionName);
	}

	protected List<MessageList> processFirstRoundParallelCalls(Collection<Work> responseTaskList) {
		String functionName="processFirstRoundParallelCalls";

		logger.enter(functionName);

		List<MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
		GetSweetenerListByOfferSummaryListForCustomerTask sweetenerListByOfferSummaryListForCustomerTask = null;
		GetOfferListByOfferIdentifierListTask offerListByOfferIdentifierListTask = null;

        for (Work resultTask : responseTaskList) {
        	if (resultTask != null) {
				if (resultTask instanceof GetSweetenerListByOfferSummaryListForCustomerTask) {
					sweetenerListByOfferSummaryListForCustomerTask = (GetSweetenerListByOfferSummaryListForCustomerTask) resultTask;
				}
        		else if (resultTask instanceof GetOfferListByOfferIdentifierListTask) {
        			offerListByOfferIdentifierListTask = (GetOfferListByOfferIdentifierListTask) resultTask;
        		}
        	}
        }

        if (sweetenerListByOfferSummaryListForCustomerTask != null) {
			if (sweetenerListByOfferSummaryListForCustomerTask.getRuntimeException() != null) {
				String errorMessage = "Error happened while retrieving information from IOfferInformationServiceAdapter.getSweetenerListByOfferSummaryListForCustomer, check RelatedMessageList for detail.";

				messageList.addAll(GetAvailableOfferSummaryTransformer.generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), GASOL_DOWN_STREAM_ERROR, errorMessage, null));

				return messageList;
			}
			else if ( (sweetenerListByOfferSummaryListForCustomerTask.getResult() == null) ||
					  (sweetenerListByOfferSummaryListForCustomerTask.getResult().getSweetenerList() == null) ||
					  (sweetenerListByOfferSummaryListForCustomerTask.getResult().getSweetenerList().isEmpty()) ) {
				messageList.addAll(GetAvailableOfferSummaryTransformer.generateMessageList(commonVO.getOffersRequestVO().getOperationHeader(), GASOL_OFFER_NOT_APPLICABLE, "No sweetener offers found.", null));

				return  messageList;
			}
			else {
				logger.info(functionName, "No Exception was found on GetSweetenerListByOfferSummaryListForCustomerTask");

				commonVO.setOfferListAdapterResponse(sweetenerListByOfferSummaryListForCustomerTask.getResult());
			}
        }

        if (offerListByOfferIdentifierListTask != null) {
			if ( (offerListByOfferIdentifierListTask.getRuntimeException() == null) &&
				 (offerListByOfferIdentifierListTask.getResult() != null) &&
				 (offerListByOfferIdentifierListTask.getResult().getOfferList() != null) &&
				 (!offerListByOfferIdentifierListTask.getResult().getOfferList().isEmpty()) ) {
				logger.info(functionName, "No Exception was found on GetOfferListByOfferIdentifierListTask");

				commonVO.setAccessoryOfferListByOfferIdentifierListAdapterResponse(offerListByOfferIdentifierListTask.getResult());
			}
        }

		logger.exit(functionName);

		return messageList;
	}

	private List<TraceVO> applyFilters() {
		final OfferFilterHelper offerFilterHelper = new OfferFilterHelper(commonVO);
		
		final List<TraceVO> traces = new ArrayList<TraceVO>();
		
		final List<Sweetener> inputSweetenerOfferList = commonVO.getOfferListAdapterResponse().getSweetenerList();

		final List<Sweetener> outputSweetenerOfferList = offerFilterHelper.filterSweetenerOfferByAccessoryOfferList(inputSweetenerOfferList, traces);
				
		commonVO.getOfferListAdapterResponse().setSweetenerList(outputSweetenerOfferList);
		
		final StringBuilder logMsg = new StringBuilder();
		
		for (TraceVO trace: traces) {
			logMsg.append("\n"+ trace.toLogMessage()); 
		}

		logger.info("applyFilters", "Trace from OfferFilterHelper.filterSweetenerOfferByAccessoryOfferList" + logMsg.toString()); 

		return traces;
	}
}
