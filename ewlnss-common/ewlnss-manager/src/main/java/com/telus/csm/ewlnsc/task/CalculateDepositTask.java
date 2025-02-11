package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.delegate.CalculateDepositDelegate;
import com.telus.csm.ewlnsc.domain.CalculateDepositRequestVO;
import com.telus.csm.ewlnsc.domain.CalculateDepositResponseVO;

   public class CalculateDepositTask extends TaskBase {

	CalculateDepositRequestVO calculateDepositRequestVO;
	CalculateDepositResponseVO calculateDepositResponsetVO;
	CalculateDepositDelegate delegate;
	
	public CalculateDepositTask(CalculateDepositDelegate delegate, CalculateDepositRequestVO calculateDepositRequestVO, CalculateDepositResponseVO calculateDepositResponsetVO ) {
		this.delegate = delegate;
		this.calculateDepositRequestVO = calculateDepositRequestVO;
		this.calculateDepositResponsetVO = calculateDepositResponsetVO;
	}			
	@Override
	protected void execute() {
		calculateDepositResponsetVO = delegate.execute(calculateDepositRequestVO);

	}

	
	

}
