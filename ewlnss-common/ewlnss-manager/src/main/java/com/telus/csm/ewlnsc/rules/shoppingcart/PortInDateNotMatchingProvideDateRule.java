package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.AppointmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.AppointmentTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.DisconnectRequestTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneNumberDetailTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.SmartRingTypeVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.rest.time.LocalDate;

public class PortInDateNotMatchingProvideDateRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private CartItemVO cartItem;
	
	public PortInDateNotMatchingProvideDateRule(CartItemVO cartItem){
		this.cartItem = cartItem;
	}
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(PortInDateNotMatchingProvideDateRule.class);
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {

		String functionName="isSatisfiedBy";
		logger.enter(functionName);
		
		DisconnectRequestTypeVO disconnectRequestType = null;
		
		if (cartItem.isDisconnectOrderItem() && cartItem.getDisconnectRequestList() != null) {
			for (DisconnectRequestTypeVO x : cartItem.getDisconnectRequestList()) {
				if (x.getProductServiceType() != null && x.getProductServiceType().contains(EnterpriseWLNSalesServicesConstants.SING)) {
					disconnectRequestType = x;
					break;
				}
			}
		}
		
		if (disconnectRequestType == null) {
			logger.debug(functionName, "Skipping rule since no disconnect for SING product.");
			return true;
		}
		
		HomePhoneProductTypeVO provideHomePhoneProduct = null; 
		
		for (CartItemVO x : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
			if (x.isSalesOrderItem() && x.getProducts().getHomePhoneProduct() != null) {
				provideHomePhoneProduct = x.getProducts().getHomePhoneProduct();
				break;				
			}
		}
		
		if (provideHomePhoneProduct == null || !hasPortIn(provideHomePhoneProduct) || provideHomePhoneProduct.getAppointmentDetail() == null) {
			logger.debug(functionName, "Skipping rule since no other cart item with SING port in request.");
			return true;
		}
		
		Date bookedAppointmentDate = getBookedAppointmentDate(provideHomePhoneProduct.getAppointmentDetail());
		List<Date> requestedAppointmentDates = getRequestedAppointmentDates(provideHomePhoneProduct.getAppointmentDetail());
		
		if (bookedAppointmentDate == null && requestedAppointmentDates.isEmpty()) {
			logger.debug(functionName, "Skipping rule since no appointment date booked or requested.");
			return true;
		}
		
		boolean isSatisfied=true;
		String cartItemRelationId=null;
		
		Date discounnectDate = getDisconnectDate(cartItem);
		
		if(!StringUtils.isBlank(cartItem.getCartItemRelationId())){
			cartItemRelationId = cartItem.getCartItemRelationId();
		}
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(PortInDateNotMatchingProvideDateRule.class.getName());
		
		if (!LocalDate.equals(bookedAppointmentDate, discounnectDate) && !datesContain(requestedAppointmentDates, discounnectDate)) {
			isSatisfied = false;
			trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.PORT_IN_DATE_PROVIDE_DATE_NOT_MATCHED,
					this.getClass().getSimpleName() + ": The disconnectCartItem for SING includes a portIn of a phone number, so the disconnect date must match the provide date."));
			trace.setCartItemRelationId(cartItemRelationId);
			trace.setValidationPassedInd(isSatisfied);
			traces.add(trace);
		}

		logger.exit(functionName);
		return isSatisfied;
	}

	private boolean hasPortIn(HomePhoneProductTypeVO homePhoneProduct) {
		
		if (hasPortIn(homePhoneProduct.getPhoneNumber())) {
			return true;
		}
		
		if (homePhoneProduct.getSmartRingPhoneList() != null) {
			for (SmartRingTypeVO smartRing : homePhoneProduct.getSmartRingPhoneList()) {
				if (hasPortIn(smartRing.getPhone())) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean hasPortIn(HomePhoneNumberDetailTypeVO phoneNumberDetail) {
		
		return (phoneNumberDetail != null  && phoneNumberDetail.getPortRequestType() != null && Boolean.TRUE.equals(phoneNumberDetail.getPortRequestType().isPortinInd()));

	}

	private boolean datesContain(List<Date> list1, Date parm2) {
		
		for (Date x : list1) {
			if (LocalDate.equals(x, parm2)) {
				return true;
			}
		}
		return false;
	}

	private Date getDisconnectDate(CartItemVO cartItem) {
		
		if (CollectionUtils.isEmpty(cartItem.getDisconnectRequestList())) {
			return null;
		}
		
		for (DisconnectRequestTypeVO x : cartItem.getDisconnectRequestList()) {
			if (x.getProductServiceType() != null) {
				for (String y : x.getProductServiceType()) {
					if (EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(y)) {
						return x.getRequestedDisconnectDate();
					}
				}
			}
		}
		
		return null;
	}

	private Date getBookedAppointmentDate(AppointmentDetailTypeVO appointmentDetail) {
		if (appointmentDetail.getBookedAppointmentDate() == null) {
			return null;
		}
		return appointmentDetail.getBookedAppointmentDate().getAppointmentDate();
	}

	private List<Date> getRequestedAppointmentDates(AppointmentDetailTypeVO appointmentDetail) {
		List<Date> result = new ArrayList<Date>();
		
		if (!CollectionUtils.isEmpty(appointmentDetail.getPreferredAppointmentDates())) {
			for (AppointmentTypeVO x : appointmentDetail.getPreferredAppointmentDates()) {
				result.add(x.getAppointmentDate());
			}
		}
		return result;
	}


}
