package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.AppointmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ServiceTypeFeasibilityVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;


public class BookingDateNotProvidedRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private CartItemVO cartItem;
	private static final LoggerUtil logger = LoggerUtil.getLogger(BookingDateNotProvidedRule.class);
	
	public static final List<String> getFwRwList(){		
		return Arrays.asList("FW","RW");
	}
	
	public BookingDateNotProvidedRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		String functionName = "BookingDateNotProvidedRule.isSatisfiedBy";
		logger.enter(functionName);
		boolean isSatisfied=true;
		List<String> errorList = new ArrayList<String>();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(BookingDateNotProvidedRule.class.getName());
		
		List<String> productsFromCartItemList = EnterpriseWLNOrderUtil.getProductsFromCartItem(cartItem);
		if(!CollectionUtils.isEmpty(productsFromCartItemList)){
			//checkProductFeasibility could not retrieve workType or workType of at least one product is FW or RW.
			Map<String, ServiceTypeFeasibilityVO> productWithFeasibilityInfoMap = EnterpriseWLNCommonTransformer.productWithFeasibilityInfo(shoppingCartContextVO.getFeasibilityResponseVO());

			for(String productType : productsFromCartItemList){
				if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productType)){
					validateAppointmentDetailForProduct(cartItem.getProducts().getInternetProduct().getAppointmentDetail(),productWithFeasibilityInfoMap,productType,errorList);
				}
				
				if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType)){
					validateAppointmentDetailForProduct(cartItem.getProducts().getTelevisionProduct().getAppointmentDetail(),productWithFeasibilityInfoMap,productType,errorList);
				}
				
				if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(productType)){
					validateAppointmentDetailForProduct(cartItem.getProducts().getHomePhoneProduct().getAppointmentDetail(),productWithFeasibilityInfoMap,productType,errorList);
				}
			}
			
			if(!CollectionUtils.isEmpty(errorList)){
				isSatisfied = false;
				trace.setValidationPassedInd(isSatisfied);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.BOOKING_DATE_NOT_PROVIDED, this.getClass().getSimpleName() + ": It is necessary to book an appointment or a requested appointment date in order to submit this order. Details: " + EnterpriseWLNOrderUtil.getFormatedStringList(errorList)));
				traces.add(trace);
			}
		}
		
		logger.exit(functionName);
		return isSatisfied;
	}

	private void validateAppointmentDetailForProduct(AppointmentDetailTypeVO appointmentDetail,Map<String, ServiceTypeFeasibilityVO> productWithFeasibilityInfoMap,String productType, List<String> errorList) {
		String functionName="validateAppointmentDetailForProduct";
		ServiceTypeFeasibilityVO serviceTypeFeasibilityVO = productWithFeasibilityInfoMap.get(productType);
		
		if(!workTypeReturnedFromFeasibility(serviceTypeFeasibilityVO) || productIsFwRw(serviceTypeFeasibilityVO)){
			if(appointmentDetail!=null && (appointmentDetail.getBookedAppointmentDate()!=null || !CollectionUtils.isEmpty(appointmentDetail.getPreferredAppointmentDates()))){
				logger.debug(functionName, "Validation rule successfully executed for product="+productType);
			}else{
				errorList.add("Appointment Detail is missing for Product=" + productType);
			}
		}
		
	}

	private boolean workTypeReturnedFromFeasibility(ServiceTypeFeasibilityVO serviceTypeFeasibilityVO) {
		if(serviceTypeFeasibilityVO!=null && !StringUtils.isBlank(serviceTypeFeasibilityVO.getRecommendedInstallType())){
			return true;
		}
		return false;
	}

	private boolean productIsFwRw(ServiceTypeFeasibilityVO serviceTypeFeasibilityVO) {
		if(serviceTypeFeasibilityVO!=null && !StringUtils.isBlank(serviceTypeFeasibilityVO.getRecommendedInstallType()) && getFwRwList().contains(serviceTypeFeasibilityVO.getRecommendedInstallType())){
			return true;
		}
		return false;
	}
	
}
