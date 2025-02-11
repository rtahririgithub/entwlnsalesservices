package com.telus.csm.ess.core.helper;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ess.rest.domain.ContextAttribute;
import com.telus.csm.ess.rest.domain.MessageType;
import com.telus.csm.ess.rest.domain.OrderResponseMessage;
import com.telus.csm.ess.rest.domain.ResponseMessage;
import com.telus.csm.ess.rest.domain.ShoppingCartIdentifier;
import com.telus.csm.ess.rest.domain.CartItemResponse;
import com.telus.csm.ess.common.domain.SalesValidationHelperResult;
import com.telus.csm.ess.rest.domain.CartValidationResult;
import com.telus.csm.ess.common.domain.ValidationMessage;
import com.telus.csm.ewlnsc.delegate.SalesContextDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.RelatedMessage;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class AccessoryOfferItemHelper {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(AccessoryOfferItemHelper.class);
	private static SalesContextDelegate salesContextDelegate = SalesContextDelegate.getInstance();

	public void execute(CartItemVO salesItemVO, CartItemResponse response) {
// TODO Petru		
//		SalesIdentifier salesIdentifier = salesItemVO.getSalesIdentifier();
//		
//		SalesValidationHelperResult helperResult = new SalesValidationHelper().validate(salesItemVO);
//		LOGGER.info("execute", "SalesValidationHelperResult.isSalesItemValidationInd()=" + helperResult.isSalesItemValidationInd());
//		
//		boolean saveItem = populateResponseMessage(response, helperResult);
//		
//		CartValidationResult salesValidationResponse = populateValidationResponse(salesIdentifier, helperResult);
//		response.setCartValidationResult(salesValidationResponse);
//
//		if (saveItem) {
//			//Put SalesItem to cache
//			String salesItemId = salesContextDelegate.putCartItem(salesItemVO);
//			
//			//Save SalesItemId to response
//			salesIdentifier.setSalesItemId(salesItemId);
////			response.setSalesIdentifier(salesIdentifier); TODO Petru
//		} else {
//			response.getStatus().setStatusCd("400");
//			response.getStatus().setStatusTxt("Error");
//		}
//
//		return;

	}

	private CartValidationResult populateValidationResponse(ShoppingCartIdentifier salesIdentifier, SalesValidationHelperResult helperResult) {
		
		CartValidationResult result = new CartValidationResult();
		result.setValidIndicator((helperResult.isSalesItemValidationInd()));

		Hashtable<String, OrderResponseMessage> vMsgTable = new Hashtable<String, OrderResponseMessage>();
		HashSet<String> failedRuleNames = new HashSet<String>();
		HashSet<String> executedRuleNames = new HashSet<String>();
		
		//Populate Validation Message
		if (helperResult.getValidationMessageList() != null) {
			for (ValidationMessage helperVmsg : helperResult.getValidationMessageList()) {
				LOGGER.info("execute", "SalesValidationHelper.validationMessage: " + helperVmsg.toString());
				
				String ruleName = helperVmsg.getRuleName();
				executedRuleNames.add(ruleName);
				
				if (!helperVmsg.isValid()) {
					failedRuleNames.add(ruleName);
					String reason = helperVmsg.getReason();
					String ruleNameAndReason = ruleName + ":" + reason;
					OrderResponseMessage vMsg = vMsgTable.get(ruleNameAndReason);
					if (vMsg == null) {
						vMsg = new OrderResponseMessage();
						result.addValidationResponseMessagesItem(vMsg);
						vMsgTable.put(ruleNameAndReason, vMsg);
						vMsg.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SALES_VALIDATION_HELPER_ERROR);
						vMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
//						vMsg.setSalesIdentifier(salesIdentifier); TODO Petru
						vMsg.setValidationContextTypeCd("OFFER");
						vMsg.setValidationContextSubTypeCd("ACCESSORY");
						MessageType messagesItem = new MessageType();
						messagesItem.setLocaleCd(Locale.ENGLISH.toString());
						messagesItem.setMessageTxt("Rule: " + ruleName + " failed. Reason: " + reason);
						vMsg.addMessagesItem(messagesItem);
					}

					ContextAttribute ctxAttr = new ContextAttribute();
					ctxAttr.setAttributeTypeTxt("OFFER_ID");
					ctxAttr.setAttributeValueTxt(helperVmsg.getOfferId());
					vMsg.addContextDataAttributesItem(ctxAttr);
				}
			}
		}
		
		for (String executedRuleName : executedRuleNames) {
			if (!failedRuleNames.contains(executedRuleName)) {
				OrderResponseMessage vMsg = new OrderResponseMessage();
				result.addValidationResponseMessagesItem(vMsg);
				vMsg.setErrorCode("INFO");
				vMsg.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO);
//				vMsg.setSalesIdentifier(salesIdentifier); TODO Petru
				vMsg.setValidationContextTypeCd("OFFER");
				vMsg.setValidationContextSubTypeCd("ACCESSORY");
				MessageType messagesItem = new MessageType();
				messagesItem.setLocaleCd(Locale.ENGLISH.toString());
				messagesItem.setMessageTxt("Rule: " + executedRuleName + " passed." );
				vMsg.addMessagesItem(messagesItem);
			}
		}

		return result;
	}

	private boolean populateResponseMessage(CartItemResponse response, SalesValidationHelperResult helperResult) {
		boolean saveItem = true;

		//add an error message if the sales item failed validation
		if (!helperResult.isSalesItemValidationInd()) {
			saveItem = false;
			ResponseMessage salesMessagesItem = new ResponseMessage();
			salesMessagesItem.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_SALES_VALIDATION_HELPER_ERROR);
			salesMessagesItem.setMessageTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
			MessageType messagesItem = new MessageType();
			messagesItem.setLocaleCd(Locale.ENGLISH.toString());
			messagesItem.setMessageTxt("SalesItem failed validation");
			salesMessagesItem.addMessagesItem(messagesItem);
			response.addResponseMessagesItem(salesMessagesItem);
		}
		
		//Populate response Message
		if (helperResult.getHelperMessageList() != null && !helperResult.getHelperMessageList().isEmpty()) {
			for (MessageList helperMsgList : helperResult.getHelperMessageList()) {
				
				ResponseMessage salesMessagesItem = new ResponseMessage();
				salesMessagesItem.setErrorCode(helperMsgList.getErrorCode());
				salesMessagesItem.setMessageTypeCd(helperMsgList.getMessageType());
				if ("ERROR".equalsIgnoreCase(helperMsgList.getMessageType())) {
					saveItem = false;
				}
				for (Message helperMsg : helperMsgList.getMessageList()) {					
					MessageType messagesItem = new MessageType();
					messagesItem.setLocaleCd(helperMsg.getLocale());
					messagesItem.setMessageTxt(helperMsg.getMessage());
					salesMessagesItem.addMessagesItem(messagesItem);
				}

				for (RelatedMessage x : helperMsgList.getRelatedMessageList()) {
					MessageType messagesItem = new MessageType();
					messagesItem.setLocaleCd(Locale.ENGLISH.toString());
					messagesItem.setMessageTxt("Related Error Code=" + x.getRelatedErrorCd() + "  Error Type=" + x.getRelatedErrorTypeCd() + "  Message=" + x.getRelatedErrorMessageTxt());
					salesMessagesItem.addMessagesItem(messagesItem);					
				}

				if (!StringUtils.isEmpty(helperMsgList.getContextData())) {
					ContextAttribute contextDataAttributesItem = new ContextAttribute();
					contextDataAttributesItem.setAttributeTypeTxt("CONTEXT_DATA");
					contextDataAttributesItem.setAttributeValueTxt(helperMsgList.getContextData());
					salesMessagesItem.addContextDataAttributesItem(contextDataAttributesItem);
				}
				response.addResponseMessagesItem(salesMessagesItem);
			}
		}
		return saveItem;
	}

}
