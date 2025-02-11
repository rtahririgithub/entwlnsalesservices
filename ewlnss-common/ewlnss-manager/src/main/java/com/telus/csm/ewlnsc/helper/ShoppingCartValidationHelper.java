package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.NoteVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationResultVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.shoppingcart.CartItemValidationRule;
import com.telus.csm.ewlnsc.rules.shoppingcart.ShoppingCartValidationRule;
import com.telus.csm.ewlnsc.task.ShoppingCartValidationTask;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;

import commonj.work.Work;

public class ShoppingCartValidationHelper {
	
	private ICommonJWorkManager workManager;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ShoppingCartContextHelper.class);
	private ShoppingCartVO shoppingCartVO;
	private ShoppingCartContextVO shoppingCartContextVO;
	boolean isValid = true;
	private ShoppingCartValidationResultVO validationResultVO;
	private List<ShoppingCartValidationTraceVO> resultList;
	private List<Work> validationTaskList = new ArrayList<Work>();
	
	public ShoppingCartValidationHelper(){
	}
	
	public ShoppingCartValidationResultVO execute(ShoppingCartContextVO shoppingCartContextVO) {
			
			validationResultVO = new ShoppingCartValidationResultVO();
			resultList = new ArrayList<ShoppingCartValidationTraceVO>();
			
			this.shoppingCartContextVO = shoppingCartContextVO;
			//if input validation fails 
			// of input validation passes, but bypassValidationInd=true
			if (isBypassValidation() ){
				validationResultVO.setValid(isValid);
				return validationResultVO;
			}
			
			//step 3: apply shopping cart validation rules 
			resultList = applyShoppingCartValidationRules();
			validationResultVO.setValidationResults(resultList);
			validationResultVO.setValid(isValid);
			
			return validationResultVO;
		}	
	
	
	private List<ShoppingCartValidationTraceVO> applyShoppingCartValidationRules() {
		
		List<ShoppingCartValidationTraceVO> ruleResultList = new ArrayList<ShoppingCartValidationTraceVO>();
		
		if (shoppingCartContextVO != null){
	
				//apply shopping cart level rules
				ShoppingCartValidationRule validateShoppingCartRule = new ShoppingCartValidationRule();
				validationTaskList.add(new ShoppingCartValidationTask(validateShoppingCartRule, shoppingCartContextVO));
				
				//apply cartItem level rules
				if(!CollectionUtils.isEmpty(shoppingCartContextVO.getShoppingCartVO().getCartItemList())){
					for(CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
						CartItemValidationRule validateCartItemRule = new CartItemValidationRule(cartItem,shoppingCartContextVO);
						validationTaskList.add(new ShoppingCartValidationTask(validateCartItemRule, shoppingCartContextVO));
					}
				}
				

				Collection<Work> validationResultList = null;
				try {
					workManager = WorkManagerFactory.getCommonJWorkManager();
					validationResultList = workManager.processTasks(validationTaskList);
				} catch (Exception e) {
					// TODO Auto-generated catch block add error codes
					e.printStackTrace();
				}
				
				if (validationResultList != null){
					for(Work validationResult : validationResultList) {
						ruleResultList.addAll(((ShoppingCartValidationTask)validationResult).getResult());
					}
				}

				for(ShoppingCartValidationTraceVO result : ruleResultList) {
					if(!result.isValidationPassedInd() || result.isHasWarning()) {
						resultList.add(result);
						if (!result.isValidationPassedInd()){
							isValid = false;
						}
					}
				}
			
		}else{
			//TODO: populate the response
			isValid=false;
		}
		return resultList;
	}

	private boolean isBypassValidation() {

		boolean isBypass = false;
		shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		if (!CollectionUtils.isEmpty(shoppingCartVO.getNote())) {
			for (NoteVO x : shoppingCartVO.getNote()) {
			//public static final String NOTE_BYPASS_VALIDATION_IND = "BYPASS_VALIDATION_IND"; from EnterpriseWLNSalesServicesConstants
				if (EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_BY_PASS_VALIDATION.equalsIgnoreCase(x.getType())
						&& EnterpriseWLNSalesServicesConstants.SYSTEM_INTEGRATION_PARAMETER_BYPASSIND_VALUE.equalsIgnoreCase(x.getText())) {
					isBypass = true;
				}
			}
		}
		return isBypass;
	}
}
