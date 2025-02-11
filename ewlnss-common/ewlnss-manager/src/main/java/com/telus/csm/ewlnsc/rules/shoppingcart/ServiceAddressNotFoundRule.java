package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

/**
 * Given: a service Address in the ShoppingCart
 * when: Service Address information cannot be retrieved from ServiceAddressMgmtSvc
 * then: return error and continue processing rules.
 */
public class ServiceAddressNotFoundRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(ServiceAddressNotFoundRule.class);
	
	
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCart,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCart, traces);
		boolean isSatisfied = true;
		String functionName="ServiceAddressNotFoundRule.execute()";
		logger.enter(functionName);
		String shppoingCartId = shoppingCart.getShoppingCartVO().getShoppingCartId();
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ServiceAddressNotFoundRule.class.getName());
		
		if(shoppingCart.getShoppingCartVO().getServiceAddress()!=null){
			ServiceAddressVO serviceAddress = shoppingCart.getShoppingCartVO().getServiceAddress();
			if(serviceAddressIsProvided(serviceAddress)){
				logger.debug(functionName, "ServiceAddress was provided in ShoppingCart, checking ServiceAddressMgmtSvc response");
				if(shoppingCart.getServiceAddressResponseVO()!=null && shoppingCart.getServiceAddressResponseVO().getServiceAddress()!=null && CollectionUtils.isEmpty(shoppingCart.getServiceAddressResponseVO().getMessageList())){
					logger.debug(functionName, "ServiceAddress was successfully retrieved from ServiceAddressMgmtService.");
					isSatisfied=true;
				}else{
					logger.error(functionName+ " : failed because serviceAddressResponse was not found");
					trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.SERVICE_ADDRESS_NOT_FOUND));
					trace.setValidationPassedInd(false);
					trace.setShoppingCartId(shppoingCartId);
					traces.add(trace);
				}
			}
		}		
		logger.exit(functionName);
		return isSatisfied;
	}

	private boolean serviceAddressIsProvided(ServiceAddressVO serviceAddress) {
		boolean serviceAddressIsProvided=false;
		if(!StringUtils.isBlank(serviceAddress.getCityCode()) && !StringUtils.isBlank(serviceAddress.getProvinceCode())
				&& !StringUtils.isBlank(serviceAddress.getServiceAddressId())){
			serviceAddressIsProvided=true;
		}
		return serviceAddressIsProvided;
	}

	
	
}
