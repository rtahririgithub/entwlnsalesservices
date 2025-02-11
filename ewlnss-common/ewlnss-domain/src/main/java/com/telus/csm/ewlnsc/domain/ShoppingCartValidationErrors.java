package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartValidationErrors {
	
	private static HashMap<ShoppingCartValidationErrorCodes, String> msgMap = new HashMap<ShoppingCartValidationErrorCodes, String>();
	
	static {
		msgMap.put(ShoppingCartValidationErrorCodes.DUPLICATE_INTERNET_PRODUCT_IN_CART, "DistinctProductTypeRule:duplicate internet product in cart");
		msgMap.put(ShoppingCartValidationErrorCodes.DUPLICATE_TV_PRODUCT_IN_CART, "DistinctProductTypeRule: duplicate tv product in cart");
		msgMap.put(ShoppingCartValidationErrorCodes.DUPLICATE_SINGLE_LINE_PRODUCT_IN_CART, "DistinctProductTypeRule: duplicate single line product in cart");
		
		msgMap.put(ShoppingCartValidationErrorCodes.SERVICE_ADDRESS_NOT_PROVIDED, "WirelineOrderWithoutServiceAddressRule: Your cartItem includes a wireline product but the serviceAddress has not been provided.");
		
		msgMap.put(ShoppingCartValidationErrorCodes.CUSTOMER_NOT_FOUND, "CustomerNotFoundRule:customer not found");
		
		msgMap.put(ShoppingCartValidationErrorCodes.PAY_CHANNEL_ID_NOT_FOUND, "pay channel id not found");
		
		msgMap.put(ShoppingCartValidationErrorCodes.SERVICE_ADDRESS_NOT_FOUND, "ServiceAddressNotFoundRule: Your cartItem includes a wireline product but the serviceAddress cannot be retrieved succesfully.");
		
		msgMap.put(ShoppingCartValidationErrorCodes.NON_STACKABLE_MARKET_OFFER_DISCOUNT, "NonStackableDiscountRule: discount code={discountCode} for HSIC from the cartItemRelationId={itemRelationId} is not fround from the market offer={offerId}");
		msgMap.put(ShoppingCartValidationErrorCodes.MARKET_DISCOUNT_CODES_NOT_EXISTING," DiscountExistsInOfferRule: One or more discounts in the cartItem is not found in the market offer");
		
		msgMap.put(ShoppingCartValidationErrorCodes.SWEETENER_OFFER_NOT_FOUND,"SweetenerOfferIsEligibleRule: One or more sweetener discounts in the cartItem is not found in the market offer");
		msgMap.put(ShoppingCartValidationErrorCodes.SWEETENER_DISCOUNT_CODES_NOT_FOUND, "SweetenerOfferDiscountNotEligibleRule: One or more sweetener discounts in the cartItem is not found in the market offer");
		msgMap.put(ShoppingCartValidationErrorCodes.NON_STACKABLE_SWEETENER_DISCOUNT, "NonStackableSweetenerRule: One or more sweetener discounts in the cartItem is not found in the market offer");
		
		msgMap.put(ShoppingCartValidationErrorCodes.INTERNET_PROD_NOT_QUALIFY,"InternetProductComomentQulificationRule: the internet product selected is not qualified");
		msgMap.put(ShoppingCartValidationErrorCodes.INTERNET_PROD_CODE_NOT_FOUND,"InternetProductComomentQulificationRule: the internet product code could not found from grid");
		
		msgMap.put(ShoppingCartValidationErrorCodes.INTERNET_BEST_FEASIBILITY,"InternetWithinBestFeasibleRule: InternetExceedsBestFeasibleRule: This address does not support the internet product component in the cartItem");
		
		msgMap.put(ShoppingCartValidationErrorCodes.INTERNET_PROD_QUALIFICATION_RESP_NULL,"ProductQulificationRespValidationRule: productQualification response is null");
		
		msgMap.put(ShoppingCartValidationErrorCodes.FEASIBILITY_RESP_NULL,"FeasibleResponseValidationRule: feasibility response is null");
		
		msgMap.put(ShoppingCartValidationErrorCodes.MISSING_INPUT_VALIDATION,"MandatoryElementValidationRule: there are missing fields in the request.");
		
		msgMap.put(ShoppingCartValidationErrorCodes.UNSUPPORTED_ELEMENTS_VALIDATION,"UnsupportedElementRule: there are some invalid fields in the request.");
		
		msgMap.put(ShoppingCartValidationErrorCodes.SHIPPED_EQUIPMENT_MISSING_SHIPPING_ADDRESS, "ShippedEquipmentMissingShippingAddress: It is necessary to provide a shipping address when selecting \"SHIPMENT\" delivery method for equipment.");
		
		msgMap.put(ShoppingCartValidationErrorCodes.O_OA_CONFIG, "O_OA_CONFIG Failures");

		msgMap.put(ShoppingCartValidationErrorCodes.PRICING, "Pricing Failures");

		
	}
	
	public static ShoppingCartValidationErrors getErrorForCode(ShoppingCartValidationErrorCodes errorCode) {
		String msg = msgMap.get(errorCode);
		return new ShoppingCartValidationErrors(errorCode, msg);
	}

	public static ShoppingCartValidationErrors getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes errorCode,String message){
		return new ShoppingCartValidationErrors(errorCode,message);
	}
	
	private final String code;
	private List<String> messages = new ArrayList<String>();

	private ShoppingCartValidationErrors(ShoppingCartValidationErrorCodes errorCode, String message) {
		this.code = errorCode.getCode();
		messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void clearMessages(){
		messages.clear();
	}
	
	public void setMessage(String message){
		clearMessages();
		messages.add(message);
	}

	public void addMessage(String message){
		messages.add(message);
	}
	
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		if (messages.size() == 1) {
			return code + ": " + messages.get(0);
		} else {
			return code + ": " + messages;
		}
	}

}
