package com.telus.csm.ess.order.rest.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.core.domain.SubmitSalesCoreResponse;
import com.telus.csm.ess.order.core.operation.SubmitSalesCoreOperation2;
import com.telus.csm.ess.rest.domain.ShoppingCartIdentifier;
import com.telus.csm.ess.rest.domain.SubmitSalesRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentErrorResponse;

@Component
@Scope(SCOPE_PROTOTYPE)
public class SubmitSalesRestOperation {

	@Autowired
	SubmitSalesCoreOperation2 submitSalesCoreOperation;
	
	public SubmitSalesResponse execute(final SubmitSalesRequest param) {
		
		SubmitSalesCoreResponse coreResponse = submitSalesCoreOperation.execute(param.getShoppingCartId());
		if(coreResponse.isHasError() && coreResponse.getMessageList()!=null && !coreResponse.getMessageList().isEmpty()) {
			GetAvailableAppointmentErrorResponse err = new GetAvailableAppointmentErrorResponse();
			err.setResponseMessages(EnterpriseWLNCommonTransformer.transformToResponseMessagesFromCore(coreResponse.getMessageList()));
			throw new EssRestErrorException(err);
		}

		return transform(coreResponse);
	}
	
	private SubmitSalesResponse transform(final SubmitSalesCoreResponse response) {
		final SubmitSalesResponse result = new SubmitSalesResponse();
		
		ShoppingCartIdentifier shoppingCartIdentifier = new ShoppingCartIdentifier();
		shoppingCartIdentifier.setShoppingCartId(response.getShoppingCartId());
		result.setShoppingCartIdentifier(shoppingCartIdentifier );
		
		return result;
	}

}
