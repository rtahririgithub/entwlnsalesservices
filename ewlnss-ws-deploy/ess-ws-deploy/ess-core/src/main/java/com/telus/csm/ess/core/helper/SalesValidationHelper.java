/**
 * 
 */
package com.telus.csm.ess.core.helper;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.common.domain.SalesValidationHelperResult;
import com.telus.csm.ess.common.domain.ValidationMessage;
import com.telus.csm.ewlnsc.delegate.SalesContextDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.GetAccessoryOfferListHelper;
import com.telus.csm.ewlnsc.rules.base.Specification;
import com.telus.csm.ewlnsc.rules.business.ValidateAccessoryOfferRule;
import com.telus.csm.ewlnsc.transformer.GetAvailableOfferSummaryTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetAvailableOfferSummaryListType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

/**
 * @author x145592
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class SalesValidationHelper {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(SalesValidationHelper.class);
	boolean salesValidInd;
	
	GetAvailableOfferSummaryListType getAvailableOfferSummaryListType = new GetAvailableOfferSummaryListType();
	private boolean isOfferIneligible = false;
	
	public SalesValidationHelperResult validate(CartItemVO cartItemVO){
		
		SalesValidationHelperResult salesValidationHelperResult = new SalesValidationHelperResult();
		
		/*
		 * Step 1: prepareCommonObject
		 */
		prepareCommonObject(cartItemVO);
		
		
		/*
		 * Step 2: call GetAccessoryOfferHelper
		 */
		
		final GetOffersRequestVO offersRequestVO = GetAvailableOfferSummaryTransformer.transform(getAvailableOfferSummaryListType);
		
		GetAccessoryOfferListHelper accessoryOfferHelper = new GetAccessoryOfferListHelper();
		GetOffersResponseVO offersResponseVO = accessoryOfferHelper.execute(offersRequestVO);
		
		List<MessageList> messageList = adjustFFHErrorCode(offersResponseVO.getMessageList());
		
		if(!messageList.isEmpty()){			
			salesValidationHelperResult.setHelperMessageList(messageList);
			return salesValidationHelperResult;
		}
		
		// For error ESS_GAOL_00004, return a Validation message
		
		if (isOfferIneligible) {
			salesValidationHelperResult.setSalesItemValidationInd(false);
			List<ValidationMessage> validationMessageList = new ArrayList<ValidationMessage>();
			salesValidationHelperResult.setValidationMessageList(validationMessageList);
//TODO Petru			
//			for(com.telus.csm.ess.rest.domain.AccessoryOffer accessoryOffer : cartItemVO.getCartItem().getAccessoryOfferItem().getSelectedAccessoryOffers()) {
//				String accessoryOfferId = accessoryOffer.getOfferHeader().getOfferId() + "";
//				ValidationMessage validationMessage = new ValidationMessage();
//				validationMessage.setOfferId(accessoryOfferId);
//				validationMessage.setAction(EnterpriseWLNSalesServicesConstants.OFFER_VALIDATION_RULE_MESSAGE);
//				validationMessage.setReason(EnterpriseWLNSalesServicesConstants.OFFER_VALIDATION_RULE_MESSAGE);
//				validationMessage.setValid(false);
//				validationMessage.setRuleName("ValidateAccessoryOfferRule");
//				validationMessageList.add(validationMessage);
//			}
			return salesValidationHelperResult;
		}
		
		/*
		 * Step 3: apply Validation rules
		 */
		List<TraceVO> traces = new ArrayList<TraceVO>();
		if(offersResponseVO.getAccessoryOfferSummaryList()!=null && !offersResponseVO.getAccessoryOfferSummaryList().isEmpty()){
			 traces = applyFilters(offersResponseVO,cartItemVO);
		}else{
			logger.warn("validate()", "No accessory offers were returned by TOM.");
		}
		
		/*
		 * Step 4: populate Response
		 */
		populateResponse(offersResponseVO,traces,salesValidationHelperResult);
		
		return salesValidationHelperResult;
		
	}

	private List<MessageList> adjustFFHErrorCode(List<MessageList> messageList) {
		
		List<MessageList> result = new ArrayList<MessageList>();
		
		if (messageList == null) {
			return result;
		}
		
		for(MessageList msgList : messageList){
			
			if(msgList.getErrorCode().equalsIgnoreCase(EnterpriseWLNSalesServicesErrorCodes.GAOL_OFFER_NOT_APPLICABLE)) {
				isOfferIneligible  = true;
				continue;
			}
			
			if(msgList.getErrorCode().equalsIgnoreCase(EnterpriseWLNSalesServicesErrorCodes.GAOL_MISSING_MANDATORY_ELEMENTS)){
				msgList.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_MISSING_MANDATORY_ELEMENTS);
			}else if(msgList.getErrorCode().equalsIgnoreCase(EnterpriseWLNSalesServicesErrorCodes.GAOL_INVALID_INPUT)){
				msgList.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_INVALID_INPUT);
			}else if(msgList.getErrorCode().equalsIgnoreCase(EnterpriseWLNSalesServicesErrorCodes.GAOL_DOWN_STREAM_ERROR)){
				msgList.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SALES_VALIDATION_HELPER_ERROR);
			}
			else{
				if(msgList.getErrorCode().startsWith("ESS_GAOL")){
					msgList.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SALES_VALIDATION_HELPER_ERROR);
				}
			}
			
			result.add(msgList);
		}
		
		return result;
	}

	private List<TraceVO> applyFilters(GetOffersResponseVO offersResponseVO,CartItemVO cartItemVO) {
		List<TraceVO> traceList = new ArrayList<TraceVO>();
		
		Specification<CartItemVO,TraceVO> salesItemRule = new ValidateAccessoryOfferRule(offersResponseVO);
		
		if(salesItemRule.isSatisfiedBy(cartItemVO, traceList)){
			logger.info("applyFilters", "applyFilter passed");
			salesValidInd=true;
		}else{
			salesValidInd=false;
		}

		final StringBuilder logMsg = new StringBuilder();
		
		if(!traceList.isEmpty()){
			for(TraceVO trace : traceList){
				logMsg.append("\n"+ trace.toLogMessage()); 
			}
		}
		
		logger.info("applyFilters", "Trace from OfferFilterHelper.filterAccessoryOfferSummary" + logMsg.toString()); 
		
		return traceList;
	}

	private void populateResponse(GetOffersResponseVO offersResponseVO, List<TraceVO> traces, SalesValidationHelperResult salesValidationHelperResult) {
		//From the Trace list we have collected the accessoryOfferId's that are not passing the validation.
		List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer> accessoryOfferSummaryList = new ArrayList<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer>();
		List<ValidationMessage> validationMessageList = new ArrayList<ValidationMessage>();
		if(!traces.isEmpty()){
			for(TraceVO trace : traces){
				//logic to add to the validationMessageList the in-eligible offers for the customer
				String filteredOfferId = trace.getOfferId();
				ValidationMessage validationMessage = new ValidationMessage();
				validationMessage.setOfferId(filteredOfferId);
				validationMessage.setAction(trace.getReason());
				validationMessage.setReason(trace.getReason());
				validationMessage.setValid(false);
				validationMessage.setRuleName(trace.getRuleName());
				validationMessageList.add(validationMessage);
			}
		}
			//Validation was executed correctly.
			if(offersResponseVO.getAccessoryOfferSummaryList()!=null && !offersResponseVO.getAccessoryOfferSummaryList().isEmpty()){
				accessoryOfferSummaryList.addAll(offersResponseVO.getAccessoryOfferSummaryList());
				 
				for(com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer accessoryOffer : offersResponseVO.getAccessoryOfferSummaryList()){
					ValidationMessage availableOfferValidation = new ValidationMessage();
					availableOfferValidation.setOfferId(accessoryOffer.getOfferHeader().getOfferId());
					availableOfferValidation.setValid(true);
					availableOfferValidation.setReason("Offer :"+accessoryOffer.getOfferHeader().getOfferId() + " is eligible for the customer");
					availableOfferValidation.setRuleName("ValidateAccessoryOfferRule");
					validationMessageList.add(availableOfferValidation);
				}
			}
		
		salesValidationHelperResult.setAccessoryOfferSummaryList(accessoryOfferSummaryList);
		salesValidationHelperResult.setValidationMessageList(validationMessageList);
		salesValidationHelperResult.setSalesItemValidationInd(salesValidInd);
	}

	/**
	 * This method will try to get from the cache the SalesVO object and then populate main object to call 
	 * AccessoryOfferHelper and get Offers.
	 * 
	 * @param cartItemVO
	 * 
	 */
	private void prepareCommonObject(CartItemVO cartItemVO) {
		SalesContextDelegate salesCtxDelegate = SalesContextDelegate.getInstance();
// TODO Petru
//		if(cartItemVO.getSalesIdentifier() != null && !StringUtils.isEmpty(cartItemVO.getSalesIdentifier().getSalesId())){
//			ShoppingCartVO salesVO = salesCtxDelegate.getShoppingCartBySalesId(cartItemVO.getSalesIdentifier().getSalesId());
//			if(salesVO!=null){
//				logger.info("prepareCommonObject", "SalesVO object was found from the cache.");
//				populateOffersRequestVO(cartItemVO,salesVO);
//				
//			}
//		}
		
	}

	private void populateOffersRequestVO(CartItemVO salesItemVO, ShoppingCartVO salesVO) {
// TODO Petru
//		getAvailableOfferSummaryListType.setOperationHeader(ESSSchemaTransformer.translateOperationHeader(salesVO,salesItemVO.getSalesIdentifier().getSalesTransactionId()));
//		getAvailableOfferSummaryListType.setAccessoryOfferCriteria(ESSSchemaTransformer.translateAccessoryOffer(salesVO,salesItemVO));
	}
	
}
