package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class AccountNotFoundRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(AccountNotFoundRule.class);
	
	/**
	 * Given: customerId and accountNumber are included in the cart
	 * when: account with a payChanneld cannot be retrieved from CBAM.getBillingAccount
	 * then: return warning message and don't alter the validation result
	 */
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO o,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(o, traces);
		
		boolean isSatisfied=true;
		String functionName="AccountNotFoundRule.execute()";
		ShoppingCartVO shoppingCartVO = o.getShoppingCartVO();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(AccountNotFoundRule.class.getName());
		logger.enter(functionName);
		if(shoppingCartVO.getBillingAccount()!=null && !StringUtils.isBlank(shoppingCartVO.getBillingAccount().getBillingAccountNumber()) 
				&& shoppingCartVO.getCustomer()!=null && !StringUtils.isBlank(shoppingCartVO.getCustomer().getCustomerId())){
			logger.debug(functionName, "billingAccountNumber and customerId are present in shoppingCart, checking CBAM.getBillingAccount response");
			if(o.getBillingAccountVO()!=null && o.getBillingAccountVO().getBillingAccount()!=null 
					&& o.getBillingAccountVO().getBillingAccount().getPayChannel()!=null && o.getBillingAccountVO().getBillingAccount().getPayChannel().getId()!=null){
				logger.debug(functionName, "Paychannel.id was found from CBAM.getBillingAccount");
			}else{
				trace.setValidationPassedInd(isSatisfied);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.PAY_CHANNEL_ID_NOT_FOUND));
				trace.setHasWarning(true);
				traces.add(trace);
			}
		}else{
			logger.debug(functionName, "Skipping rule since billingAccountNumber was not passed");
			isSatisfied=true;
		}
		logger.exit(functionName);
		return isSatisfied;
	}

}
