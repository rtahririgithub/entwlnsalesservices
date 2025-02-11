package com.telus.csm.ess.order.core.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.core.delegate.SubmitWirelineSalesDelegate;
import com.telus.csm.ess.core.domain.SubmitSalesCoreResponse;
import com.telus.csm.ewlnsc.domain.SubmitWirelineOrderRequestVO;
import com.telus.csm.ewlnsc.domain.SubmitWirelineOrderResponseVO;

@Component
@Scope(SCOPE_PROTOTYPE)
public class SubmitSalesCoreOperation2 {

	@Autowired
	SubmitWirelineSalesDelegate submitWirelineSalesDelegate;

	public SubmitSalesCoreResponse execute(String shoppingCartId) {
		
		final SubmitWirelineOrderRequestVO submitRequestVO = transform(shoppingCartId);
		final SubmitSalesCoreResponse submitOrderResponse = submitWirelineSalesDelegate.execute(submitRequestVO);
		
		return submitOrderResponse;
	}

	private SubmitWirelineOrderRequestVO transform(final String shoppingCartId) {
		
		final SubmitWirelineOrderRequestVO result = new SubmitWirelineOrderRequestVO();
		
		result.setShoppingCartId(shoppingCartId);
		
		return result;
	}

}
