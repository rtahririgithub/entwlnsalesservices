package com.telus.csm.ewlnsc.rules.shoppingcart;


import java.util.List;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class PortsAvailableRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(PortsAvailableRule.class);
	
	private CartItemVO cartItemVO;
	
	public PortsAvailableRule(CartItemVO cartItemVO) {
		this.cartItemVO = cartItemVO;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO contextVO, List<ShoppingCartValidationTraceVO> traces){
		super.isSatisfiedBy(contextVO, traces);
		
		boolean isSatisfiedBy = true;
		
		String functionName = "PortsAvailableRule.execute()";
		logger.enter(functionName);
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(PortsAvailableRule.class.getName());
		
		boolean isInternetPresentInd = false;
		boolean isTelevisionPresentInd = false;
		
		if (cartItemVO!=null && cartItemVO.isSalesOrderItem()) {
		   if (cartItemVO.getProducts()!= null) {
			   if (cartItemVO.getProducts().getInternetProduct()!=null) {
				   isInternetPresentInd=true;
				   }
			   if (cartItemVO.getProducts().getTelevisionProduct()!=null) {
				   isTelevisionPresentInd=true;
				   }
			   
			   }
		   if (isInternetPresentInd || isTelevisionPresentInd) {
			   if (contextVO.getFeasibilityResponseVO()!=null) {
				  if (WLNOfferUtil.checkProductFeasibilityContainsNoPortsAvailable(contextVO.getFeasibilityResponseVO())){
					  isSatisfiedBy = false;
					  trace.setValidationPassedInd(isSatisfiedBy);
					  trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.NO_PORTS_AVAILABLE, "Feasiability Response returned NO PORTS AVAILABLE ERROR CODE" ));
					  traces.add(trace); 
				  }  
			   }   
		   
		   }   
		   
		} else {
			logger.debug(functionName, "Skipping rule since cart Item is not a marketOffer");
		}
		
		
		logger.exit(functionName);
		return isSatisfiedBy;
		
	}
}
