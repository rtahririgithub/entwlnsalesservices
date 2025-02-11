/**
 * 
 */
package com.telus.csm.ess.core.operation;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_MISSING_MANDATORY_ELEMENTS;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.ESS_FFH_RECORD_NOT_FOUND;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ess.core.helper.AccessoryOfferItemHelper;
import com.telus.csm.ess.rest.domain.CartItemResponse;
import com.telus.csm.ess.rest.domain.ContextAttribute;
import com.telus.csm.ess.rest.domain.CreateCartItem;
import com.telus.csm.ess.rest.domain.MessageType;
import com.telus.csm.ess.rest.domain.OperationHeader;
import com.telus.csm.ess.rest.domain.ResponseMessage;
import com.telus.csm.ewlnsc.delegate.SalesContextDelegate;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

/**
 * @author x145592
 *
 */
public class CreateCartItemCoreOperation {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(CreateCartItemCoreOperation.class);
	private	static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	private static SalesContextDelegate salesContextDelegate = SalesContextDelegate.getInstance();

	public CartItemResponse execute(String shoppingCartId, CreateCartItem cartItemRequest) {
		
		CartItemResponse result = new CartItemResponse();
		
		/**
		 * Step 1) Input validation
		 */
		List<ResponseMessage> responseMessages = validateInput(cartItemRequest);
		
		if(!responseMessages.isEmpty()){
			result.setResponseMessages(responseMessages);
			return result;
		}
		
		/**
		 * Step 2) Transformation to internal classes
		 */
		//CartItemVO cartItemVO = new CartItemVO();
				
		//CartItemTransformer.transformToCartItemVO(cartItemRequest,shoppingCartId,cartItemVO);

		//validate input		
		if (!isRequestValid(shoppingCartId, cartItemRequest, result)) {
			return result;
		}

		OperationHeader operationHeader = cartItemRequest.getOperationHeader();
		String tranId = null;
		if (operationHeader != null) {
			tranId = operationHeader.getSalesTransactionId();
		}

		//process accessoryOfferItem
		CartItemVO salesItemVO = new CartItemVO();
		salesItemVO.setSalesOrderItem(true);
// TODO Petru
//		SalesIdentifier salesIdentifier = salesItemVO.getSalesIdentifier();
//		salesIdentifier.setSalesId(shoppingCartId);
//		salesIdentifier.setSalesTransactionId(tranId);
		new AccessoryOfferItemHelper().execute(salesItemVO, result);
		
		return result;
	}

	private List<ResponseMessage> validateInput(CreateCartItem cartItemRequest) {
		List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
		
		return responseMessageList;
	}

	private boolean isRequestValid(String shoppingCartId, CreateCartItem cartItemRequest, CartItemResponse result) {
		
		//salesId
		//ShoppingCartVO salesVO = salesContextDelegate.getShoppingCartByShoppingCartId(shoppingCartId);
		ShoppingCartVO salesVO = ShoppingCartDelegate.getInstance().getShoppingCart(shoppingCartId);
		
		if (salesVO == null ) {
			String txt = "shoppingCartId " + shoppingCartId + " not found";
			ResponseMessage msg = new ResponseMessage();
			msg.setErrorCode(ESS_FFH_RECORD_NOT_FOUND);
			msg.setMessageTypeCd(MESSAGE_TYPE_ERROR);
			MessageType messagesItem = new MessageType();
			messagesItem.setLocaleCd(Locale.ENGLISH.toString());
			messagesItem.setMessageTxt("Record not found");
			ContextAttribute contextDataAttributesItem = new ContextAttribute();
			contextDataAttributesItem.setAttributeTypeTxt("INPUT_FIELD");
			contextDataAttributesItem.setAttributeValueTxt("shoppingCartId");
			msg.addContextDataAttributesItem(contextDataAttributesItem);
			msg.addMessagesItem(messagesItem);
			result.addResponseMessagesItem(msg);
			LOGGER.info("execute", txt);
			return false;
		}
		
		ArrayList<String> missingFields = new ArrayList<String>();
		
		//cartItemRequest
		if (cartItemRequest == null) {
			missingFields.add("cartItemRequest");
		} else {

			//cartItemRequest
			if (cartItemRequest.getOperationHeader() == null) {
				missingFields.add("operationHeader");
			} else {
				//salesTransactionId
				if (StringUtils.isEmpty(cartItemRequest.getOperationHeader().getSalesTransactionId())) {
					missingFields.add("operationHeader.salesTransactionId");
				}
			}
			
			// cartItem
			if (cartItemRequest.getCartItem() == null) {
				missingFields.add("cartItem");
			} else {
				// accessoryOfferItem TODO Petru
//				if (cartItemRequest.getCartItem().getAccessoryOfferItem() == null) {
//					missingFields.add("cartItem.accessoryOfferItem");
//				} else {
//					if (cartItemRequest.getCartItem().getAccessoryOfferItem().getSelectedAccessoryOffers() != null
//							&& !cartItemRequest.getCartItem().getAccessoryOfferItem().getSelectedAccessoryOffers().isEmpty()) {
//						for (AccessoryOffer accessoryOffer : cartItemRequest.getCartItem().getAccessoryOfferItem()
//								.getSelectedAccessoryOffers()) {
//							if (accessoryOffer.getOfferHeader() != null) {
//								if (accessoryOffer.getOfferHeader().getOfferId() == null) {
//									missingFields.add(
//											"cartItem.accessoryOfferItem.selectedAccessoryOffers.offerHeader.offerId");
//								}
//							}
//						}
//					}
//				}
			}
		}
		if (missingFields.isEmpty()) {
			return true;
		}
		
		LOGGER.info("execute", "Mandatory field missing: " + missingFields);
		
		ResponseMessage msg = new ResponseMessage();
		msg.setErrorCode(ESS_FFH_MISSING_MANDATORY_ELEMENTS);
		msg.setMessageTypeCd(MESSAGE_TYPE_ERROR);
		MessageType messagesItem = new MessageType();
		messagesItem.setLocaleCd(Locale.ENGLISH.toString());
		messagesItem.setMessageTxt("Mandatory field missing");
		for (String fieldName : missingFields) {
			ContextAttribute contextDataAttributesItem = new ContextAttribute();
			contextDataAttributesItem.setAttributeTypeTxt("INPUT_FIELD");
			contextDataAttributesItem.setAttributeValueTxt(fieldName);
			msg.addContextDataAttributesItem(contextDataAttributesItem);
		}
		msg.addMessagesItem(messagesItem);
		result.addResponseMessagesItem(msg);

		return false;
	}
}
