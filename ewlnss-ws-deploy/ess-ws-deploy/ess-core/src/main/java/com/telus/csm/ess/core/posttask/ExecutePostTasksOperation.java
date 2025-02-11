package com.telus.csm.ess.core.posttask;

import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.telus.csm.ess.core.delegate.SubmitWirelineSalesDelegate;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ExecutePostTasksOperation extends SpringBeanAutowiringSupport {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	@Autowired
	SubmitWirelineSalesDelegate submitWirelineSalesDelegate;
	
	public void execute(String shoppingCartId) {
		logger.info("execute", "Executing post tasks for shopping cart: " + shoppingCartId);
		ShoppingCartContextVO shoppingCartContextVO = new ShoppingCartContextHelper().execute(shoppingCartId, true);
		
		StringHolder errorHolder = new StringHolder();
		if(!OPShoppingCartDelegate.canCreateOPShoppingCart(shoppingCartContextVO, errorHolder)) {
			logger.warn("execute", "Will switch to Manual order. Cannot proceed with the post task of creating OP order because of: " + errorHolder.value);
			submitWirelineSalesDelegate.doManualOrder(shoppingCartContextVO.getShoppingCartVO(), shoppingCartContextVO.getOrderId(), "Customer create Failure");
		} else {
			IPostTaskStrategy strategy = PostTaskStrategyFactory.getPostTaskStrategy(shoppingCartContextVO);
			if(strategy != null) {
				strategy.execute(shoppingCartContextVO);
			} else {
				throw new RuntimeException("No PostTaskStrategy is found for the shopping cart: " + shoppingCartId);
			}
		}
	}

}
