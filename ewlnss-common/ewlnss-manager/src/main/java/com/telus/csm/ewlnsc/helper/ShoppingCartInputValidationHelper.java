package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.List;


import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationResultVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.shoppingcart.ShoppingCartInputValidationRule;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ShoppingCartInputValidationHelper {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ShoppingCartContextHelper.class);
	
	private ShoppingCartContextVO shoppingCartContextVO;
	boolean isValid = true;
	private ShoppingCartValidationResultVO validationResultVO;
	private List<ShoppingCartValidationTraceVO> resultList;	
	
	public ShoppingCartInputValidationHelper(){
	}
	
	public ShoppingCartValidationResultVO execute(ShoppingCartVO shoppingCartVO) {
			
			shoppingCartContextVO = new ShoppingCartContextVO(shoppingCartVO);
			validationResultVO = new ShoppingCartValidationResultVO();
			resultList = new ArrayList<ShoppingCartValidationTraceVO>();
						
			ShoppingCartInputValidationRule rule = new ShoppingCartInputValidationRule();
			rule.isSatisfiedBy(shoppingCartContextVO,resultList);
			
						
			if(!resultList.isEmpty()){
				for(ShoppingCartValidationTraceVO trace : resultList){
					if(!trace.isValidationPassedInd()){
						isValid=false;
						break;
					}
				}
			}
			
			if (!isValid){
				validationResultVO.setValidationResults(resultList);
			}
			validationResultVO.setValid(isValid);
			
			return validationResultVO;
		}	
}
