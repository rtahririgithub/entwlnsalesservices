package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.ShipmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ShippedEquipmentMissingShippingAddress  extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(ShippedEquipmentMissingShippingAddress.class);

	private CartItemVO cartItem;
	private boolean isSatisfied = true;

	public ShippedEquipmentMissingShippingAddress(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCart, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCart, traces);
		
		String functionName="ShippedEquipmentMissingShippingAddress.execute()";
		logger.enter(functionName);

		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ShippedEquipmentMissingShippingAddress.class.getName());
				
		if ( (cartItem != null) && (cartItem.isSalesOrderItem()) && (cartItem.getProducts() != null) ) {
			if (cartItem.getProducts().getInternetProduct() != null) {
				isSatisfied = isShippedEquipmentMissingShippingAddress(cartItem.getProducts().getInternetProduct().getEquipments(), cartItem.getShipmentDetail(), trace);
			}

			if (cartItem.getProducts().getHomePhoneProduct() != null) {
				isSatisfied = isShippedEquipmentMissingShippingAddress(cartItem.getProducts().getHomePhoneProduct().getEquipments(), cartItem.getShipmentDetail(), trace);
			}

			if (cartItem.getProducts().getTelevisionProduct() != null) {
				isSatisfied = isShippedEquipmentMissingShippingAddress(cartItem.getProducts().getTelevisionProduct().getEquipments(), cartItem.getShipmentDetail(), trace);
			}
		}
		
		return isSatisfied;
	}

	private boolean isShippedEquipmentMissingShippingAddress(List<FFHEquipmentTypeVO> equipments, ShipmentDetailTypeVO shipmentDetail, ShoppingCartValidationTraceVO trace) {
		if ( (equipments != null) && (!equipments.isEmpty()) ) {
			for (FFHEquipmentTypeVO equipment : equipments) {
				if ( (equipment != null) &&
					 (equipment.getDeliveryMethodType() != null) &&
					 ("SHIPPED".equalsIgnoreCase(equipment.getDeliveryMethodType())) &&
					 (shipmentDetail == null) ) {
					isSatisfied = false;
					trace.setValidationPassedInd(false);
					trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.SHIPPED_EQUIPMENT_MISSING_SHIPPING_ADDRESS));
					break;
				}
			}
		}

		return isSatisfied;
	}
}