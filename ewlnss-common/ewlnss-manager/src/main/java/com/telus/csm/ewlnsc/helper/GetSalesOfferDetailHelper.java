package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GSOD_INVALID_INPUT;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GSOD_MISSING_MANDATORY_ELEMENTS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GSOD_OFFER_NOT_FOUND;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GSOD_OFFER_NOT_APPLICABLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsc.delegate.CheckProductFeasibilityDelegate;
import com.telus.csm.ewlnsc.delegate.OfferDetailDelegate;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.transformer.GetSalesOfferDetailSchemaIndependentTransformer;
import com.telus.csm.ewlnsc.transformer.GetSalesOfferDetailTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.ExecutionTimer;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProgramPromotion;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

import commonj.work.Work;

@Component
@Scope(SCOPE_PROTOTYPE)
public class GetSalesOfferDetailHelper extends GetAvailableOfferSummaryHelper {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetSalesOfferDetailHelper.class);

	List<Offer> offers = new ArrayList<Offer>();
	ProgramPromotion programPromotion = null;

	@MonitorPerformance
	public GetSalesOfferDetailResponseVO execute(GetSalesOfferDetailRequestVO requestVO) {
		String functionName = "execute";
		ExecutionTimer timer = new ExecutionTimer(this.getClass().getSimpleName(), functionName);

		logger.enter(functionName);
		logger.debug(functionName, "GetSalesOfferDetailRequestVO:\n" + JsonUtil.getJsonFromObjectNonNUll(requestVO));
		
		GetSalesOfferDetailResponseVO response = new GetSalesOfferDetailResponseVO();
	
		/*
		 * Step 1: validate Input
		 */
		List<String> missingElementList = new ArrayList<String>();
		List<String> invalidInputList = new ArrayList<String>();
		List<String> warningList = new ArrayList<String>();

		validateInput(requestVO, missingElementList, invalidInputList, warningList);
		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty() || !warningList.isEmpty()) {
			response.addMessageList(ValidationUtil.generateMessageList(requestVO.getOperationHeader(), missingElementList, invalidInputList, warningList, GSOD_MISSING_MANDATORY_ELEMENTS, GSOD_INVALID_INPUT));
			logger.info(functionName, EnterpriseWLNSalesServicesConstants.INPUT_VALIDATION_FAILED_OR_WARNING_MSG);
		}

		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
			return response;
		}
		
		/*
		 * Step 1a: checking RefPDS status
		 */
		
		List<MessageList> refPDSStatusMsgList = OfferSummaryExceptionHandler.validateRefPDSStatus(requestVO.getOperationHeader(), EnterpriseWLNSalesServicesErrorCodes.GSOD_DOWN_STREAM_ERROR);
		if(refPDSStatusMsgList!=null && !refPDSStatusMsgList.isEmpty()){
			response.setMessageList(refPDSStatusMsgList);
			return response;
		}
		timer.lap("validateInput");

		/*
		 * Step 2: prepareCommonObject
		 */
		Collection<Work> resultTaskList = super.prepareCommonObject(requestVO);
		
		/*
		 * Step 2.1: validate responses from downstream services
		 */
		List<MessageList> parallelCallValidationList = super.processFirstRoundParallelCalls(resultTaskList);
		if(!parallelCallValidationList.isEmpty()){
			response.setMessageList(parallelCallValidationList);
			return response;
		}
		
		/*
		 * Step 2.2: validate if OIS can be called as determined by customerEligibility
		 */
		String errorMessage =null;
		boolean badCustomerInd = super.checkCustomerEligibleStatus();
		if(badCustomerInd){
			errorMessage = "Customer has been flagged as Fraud or in-treatment and has no existing nor pending products.";
			response.addMessageList(GetSalesOfferDetailTransformer.generateMessageList(requestVO, GSOD_OFFER_NOT_APPLICABLE, errorMessage));
			logger.info(functionName, errorMessage);
			return response;
		}
		
		List<MessageList> flowValidationMessageList= OfferSummaryExceptionHandler.prevalidate(commonVO);
		if(flowValidationMessageList!=null && !flowValidationMessageList.isEmpty()){
			response.setMessageList(flowValidationMessageList);
			return response;
		}
		
		commonVO.setCustomerEligibleForRecontract(WLNOfferUtil.isCustomerEligibleForRecontracting(commonVO));
		
		timer.lap("prepareCommonObject");

		/*
		 * Step 3: call for getOfferDetail
		 */
		
		GetOfferListAdapterResponse result = new OfferDetailDelegate().getOfferDetail(commonVO, (GetSalesOfferDetailRequestVO) commonVO.getOffersRequestVO());
		commonVO.setOfferListAdapterResponse(result);
		
		/*
		 * Step 3.5: call for checkFeasibility 
		 */
// NWLN-7743 - remove the call Feasibility Service for getSalesOfferDetail. 		
//		List<MessageList> messageList = callFeasibilitySvc(response);
//		if (!messageList.isEmpty()) {
//			response.setMessageList(messageList);
//			return response;
//		}
		
		timer.lap("getOfferDetail");
		
		/*
		 * Step 4: check response from getOfferDetail
		 */
		GetOfferListAdapterResponse getOfferListAdapterResponse = commonVO.getOfferListAdapterResponse();
		boolean noOfferFound = (getOfferListAdapterResponse.getOfferList() == null || getOfferListAdapterResponse.getOfferList().isEmpty());
		if (noOfferFound) {
			response.addMessageList(generateNoOfferMessageList(requestVO));
			logger.info(functionName, "No Offer found.");
			return response;
		}

		offers = getOfferListAdapterResponse.getOfferList();
		programPromotion = getOfferListAdapterResponse.getProgramPromotion();

		/*
		 * Step 4.5: if OMNI flow, enrich getOffersRequestVO.productList with the response of OIS.
		 */
		// NWLN-7605 - for split offer scenario, only call enrichOfferRequest if the request doesn't contain a productList
		if(!WLNOfferUtil.isJoinedOffers(requestVO.getOperationHeader()) && 
			(requestVO.getSalesOfferCriteria().getOfferFilter() == null ||
			 (requestVO.getSalesOfferCriteria().getOfferFilter() != null && 
			  (requestVO.getSalesOfferCriteria().getOfferFilter().getProductList() == null ||
			   requestVO.getSalesOfferCriteria().getOfferFilter().getProductList().size() == 0)))){
			logger.info(functionName, "OMNI flow, setting GetSalesOfferDetailRequest.productList with the products returned by OIS.");
			List<OfferProductHeader> offerProductList = new ArrayList<OfferProductHeader>();
			logger.debug(functionName, "**********Request ProductList before enriching it with the products from offer");
			logger.debug(functionName, getProductListFromRequest(commonVO.getOffersRequestVO().getSalesOfferCriteria()));
			enrichOfferRequest(offerProductList,commonVO);
			if(commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter()==null){
				WirelineOfferFilter offerFilter = new WirelineOfferFilter();
				offerFilter.setProductList(offerProductList);
				commonVO.getOffersRequestVO().getSalesOfferCriteria().setOfferFilter(offerFilter);
			}else{
				commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter().setProductList(offerProductList);
			}
			logger.debug(functionName, "**********Request ProductList after enriching it with the products from offer");
			logger.debug(functionName, getProductListFromRequest(commonVO.getOffersRequestVO().getSalesOfferCriteria()));				
		}

		
		timer.lap("getOfferDetailResponse");

		/*
		 * Step 5: applyFilters
		 */
		List<TraceVO> traces = applyOfferDetailFilters();

		timer.lap("applyFilters");

		/*
		 * Step 6: populateResponse
		 */
		Offer offer = null;
		if (!offers.isEmpty()) {
			offer = offers.get(0);
			if (offers.size() > 1) {
				logger.warn("execute", "More than one offer found, returning the first one."); 
			}
		}

		response.setOffer(offer);
		response.setProgramPromotion(programPromotion);
		
		// 2018 June Exception release for TTV recontracting
		// change parameters to call WLNOfferUtil.getRecontractEligibleProductCodeList
		response.setRecontractEligibleProductCodeList(WLNOfferUtil.getRecontractEligibleProductCodeList(offer, commonVO, false));

		// Jose.Mena: Defect 63430: When calling GAOSL or GSOD, if no ports available then include an error message in the response.
		WLNOfferUtil.checkFeasibilityResponseNoPorts(commonVO, response, EnterpriseWLNSalesServicesErrorCodes.GSOD_NO_PORTS_AVAILABLE_IN_PRODUCT_FEASIBILITY);
				
		
		GetSalesOfferDetailTransformer.transform(commonVO, offer,	traces, response);
		
		timer.stop();

		logger.exit(functionName);

		return response;
	}

	private String getProductListFromRequest(SalesOfferCriteriaVO salesOfferCriteria) {
		StringBuilder sb = new StringBuilder();
		if(salesOfferCriteria.getOfferFilter()!=null){
			if(CollectionUtils.isEmpty(salesOfferCriteria.getOfferFilter().getProductList())){
				sb.append("ProductList was not passed in the ESS request");
			}else{
				for(OfferProductHeader product : salesOfferCriteria.getOfferFilter().getProductList()){
					// NWLN-7605 - fix nullpointer issue when productOrdertype is null
					if (product.getProductOrderType() != null){
						sb.append("\n" + " productTypeCd=" + product.getProductTypeCd() + " productOrderType.productOrderTypeCd=" + product.getProductOrderType().getProductOrderTypeCd()
								+ " contractTermCd=" + product.getContractTermCd() + " productComponentList=" + getProductComponentList(product.getProductComponentList()));
					} else {
						sb.append("\n" + " productTypeCd=" + product.getProductTypeCd() + " productOrderType= null"
								+ " contractTermCd=" + product.getContractTermCd() + " productComponentList=" + getProductComponentList(product.getProductComponentList()));
					}
					
				}
				
			}
			
		}
		return sb.toString();
	}

	private String getProductComponentList(List<ProductComponentIdentifier> productComponentList) {
		StringBuilder sb = new StringBuilder();
		if(!CollectionUtils.isEmpty(productComponentList)){
			for(ProductComponentIdentifier productComponent : productComponentList){
				sb.append("\n" + " productCatalogueIdentifier=" +productComponent.getProductCatalogueIdentifier());
			}
		}
		return sb.toString();
	}

	private void enrichOfferRequest(List<OfferProductHeader> offerProductList, SalesOfferCommonVO commonVO) {
		String functionName="enrichOfferRequest";
		logger.enter(functionName);
		GetSalesOfferDetailRequestVO request = (GetSalesOfferDetailRequestVO) commonVO.getOffersRequestVO();
		String requestedOfferId = request.getOfferId();
		if(commonVO.getOfferListAdapterResponse()!=null){
			GetOfferListAdapterResponse offerDetailResponse = commonVO.getOfferListAdapterResponse();
			if(!CollectionUtils.isEmpty(offerDetailResponse.getOfferList())){
				if(!CollectionUtils.isEmpty(offerDetailResponse.getOfferList())){
					Offer offer  = offerDetailResponse.getOfferList().get(0); //getting the first offer returned.
					List<String> recontractEligibleProductList = WLNOfferUtil.getRecontractEligibleProductCodeList(offer, commonVO, false);
					if(requestedOfferId.contentEquals(String.valueOf(offer.getOfferId()))){
						logger.debug(functionName, "requested offerId: " + requestedOfferId + " was found in OIS.getOfferListByOfferIdentifierList response, extracting the products to enrich ESS Request.productList");
						if(!CollectionUtils.isEmpty(offer.getOfferProduct().getWirelineOfferProductList())){
							for(WirelineOfferProduct wirelineProduct : offer.getOfferProduct().getWirelineOfferProductList()){
								OfferProductHeader offerProduct = new OfferProductHeader();
								if(!CollectionUtils.isEmpty(wirelineProduct.getContractTermList())){
									offerProduct.setContractTermCd(String.valueOf(wirelineProduct.getContractTermList().get(0)));
								}
								if(!CollectionUtils.isEmpty(wirelineProduct.getTransactionTypeList())){
									offerProduct.setProductOrderType(getProductTransactionTypeFromOffer(wirelineProduct.getTransactionTypeList()));
								}
								offerProduct.setProductTypeCd(wirelineProduct.getProductTypeCode());
								offerProduct.setProductComponentList(getProductComponentListFromOffer(wirelineProduct.getProductComponentList()));
								//recontractInd
								if(recontractEligibleProductList.contains(wirelineProduct.getProductTypeCode())){
										offerProduct.setRecontractInd(Boolean.TRUE);
								}
								
								// NWLN-7605 - add back the winbackInd from the original request
								for(OfferProductHeader offerProductHeader: request.getSalesOfferCriteria().getOfferFilter().getProductList()){
									if(offerProductHeader.getProductTypeCd().equalsIgnoreCase(offerProduct.getProductTypeCd())){
										offerProduct.setWinBackInd(offerProductHeader.isWinBackInd());
									}
								}
								
								offerProductList.add(offerProduct);
							}
						}
					}
			}
			}
		}
		
	}

	private List<ProductComponentIdentifier> getProductComponentListFromOffer(
			List<ProductComponent> productComponentList) {
		List<ProductComponentIdentifier> essProductComponentIdentifierList = new ArrayList<ProductComponentIdentifier>();
		if(!CollectionUtils.isEmpty(productComponentList)){
			for(ProductComponent offerProductComponent : productComponentList){
				if(!CollectionUtils.isEmpty(offerProductComponent.getProductCatalogueItemList())){
					for(ProductCatalogueItem productCatalogueItem : offerProductComponent.getProductCatalogueItemList()){
						if(productCatalogueItem.getProductCatalogueIdentifier()!=null && !StringUtils.isEmpty(productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId())){
							ProductComponentIdentifier essProductComponentIdentifier = new ProductComponentIdentifier();
							essProductComponentIdentifier.setProductCatalogueIdentifier(productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId());
							essProductComponentIdentifierList.add(essProductComponentIdentifier);
						}
					}
				}
			}
		}
		return essProductComponentIdentifierList;
	}

	private ProductOrderType getProductTransactionTypeFromOffer(List<TransactionType> transactionTypeList) {
		
		ProductOrderType productOrderType = new ProductOrderType();
		TransactionType transactionType = transactionTypeList.get(0);
		if(!StringUtils.isEmpty(transactionType.getTransactionTypeCode())){
			productOrderType.setProductOrderTypeCd(transactionType.getTransactionTypeCode());
		}
		if(!StringUtils.isEmpty(transactionType.getTransactionSubTypeCode())){
			productOrderType.setProductOrderSubTypeCd(transactionType.getTransactionSubTypeCode());
		}
		return productOrderType;
	}
	
	private List<MessageList> callFeasibilitySvc(GetSalesOfferDetailResponseVO response) {
		String functionName="callFeasibilitySvc";
		logger.enter(functionName);
		List<MessageList> messageList = new ArrayList<SalesResponseMessage.MessageList>();
		CheckProductFeasibilityAdapterResponse feasibilityResponse;
		// Check product Feasibility Task
		
		// If request.offerFilter.productList is not empty, these products must be available in ProductQualificationAdapterResponseVO,
		// for HSIC, we must map the productCatalogIdentifier to a productTier, and compare if this productTier exist in ProductQualificationAdapterResponseVO
		// If a matching HSIC product tier code is not found in ProductQualificationAdapterResponseVO, then we will stop the flow and throw exception.
		List<String> prodListNotAvailable = new ArrayList<String>();
		WLNOfferUtil.checkProductListAvailability(commonVO, prodListNotAvailable);
		if (prodListNotAvailable.isEmpty()) {
			//Call FeasibilitySvc
			feasibilityResponse = new CheckProductFeasibilityDelegate().checkProductFeasibility(commonVO);
			commonVO.setCheckFeasibilityResponseVO(feasibilityResponse);
		} else {
			messageList.addAll(generateMessageListForReqProdsNotQualified(prodListNotAvailable, commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId(), GSOD_INVALID_INPUT));
			return messageList;
		}		
		
		logger.exit(functionName);
		return messageList;
	}
	
	private List<MessageList> generateNoOfferMessageList(GetSalesOfferDetailRequestVO requestVO) {
		String salesTransactionId = "";
		if (requestVO.getOperationHeader() != null && requestVO.getOperationHeader().getSalesTransactionId() != null) {
			salesTransactionId = requestVO.getOperationHeader().getSalesTransactionId();
		}
		
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList message = new MessageList();
		message.setDateTimeStamp(new Date());
		message.setTransactionId(salesTransactionId);
		message.setErrorCode(GSOD_OFFER_NOT_FOUND);
		message.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		msg.setMessage("Offer not found");
		message.setMessageList(Arrays.asList(msg));

		// set context data
		StringBuilder context = new StringBuilder();
		context.append("Offer Id: " + requestVO.getOfferId());
		
		if (!StringUtils.isEmpty(requestVO.getPromotionCode())) {
			context.append("; Promotion Code: " + requestVO.getPromotionCode());	
		}
		message.setContextData(context.toString());

		messageList.add(message);

		return messageList;
	}

	private List<TraceVO> applyOfferDetailFilters() {
		OfferFilterHelper offerFilterHelper = new OfferFilterHelper(commonVO);
		
		List<TraceVO> traces = new ArrayList<TraceVO>();
		offers = offerFilterHelper.filterOfferDetail(offers, traces);

		StringBuilder logMsg = new StringBuilder();
		
		for (TraceVO trace: traces) {
			logMsg.append("\n"+ trace.toLogMessage()); 
		}
		logger.info("applyOfferDetailFilters", "Trace from OfferFilterHelper.filterOfferDetail" + logMsg.toString());
		
		return traces;
		
	}

	protected void validateInput(GetSalesOfferDetailRequestVO requestVO, List<String> missingElementList,
			List<String> invalidInputList, List<String> warningList) {
		String functionName = "validateInput()";
		logger.enter(functionName);
		
		super.validateInput(requestVO, missingElementList, invalidInputList, warningList);
		
		//offerId 
		if (StringUtils.isEmpty(requestVO.getOfferId())) {
			missingElementList.add("OfferId");
		}

		logger.exit(functionName);
	}

	public GetSalesOfferDetailResponseVO2 execute(
			com.telus.csm.ewlnsc.domain.schemasclone.GetSalesOfferDetailRequestVO requestVO) {
		GetSalesOfferDetailResponseVO2 offersResponseVO = GetSalesOfferDetailSchemaIndependentTransformer.transform(execute(GetSalesOfferDetailSchemaIndependentTransformer.transform(requestVO)));

		return offersResponseVO;
	}

}
