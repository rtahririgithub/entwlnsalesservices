package com.telus.csm.ess.rest.webservices;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.rest.domain.CreateCartItem;
import com.telus.csm.ess.rest.domain.CartItemResponse;
import com.telus.csm.ess.rest.domain.CreateShoppingCart;
import com.telus.csm.ess.rest.domain.ShoppingCartResponse;
import com.telus.csm.ess.rest.domain.UpdateShoppingCart;
import com.telus.csm.ess.rest.domain.SubmitSalesRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesResponse;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSResponse;
import com.telus.csm.ess.rest.domain.UpdateSaleRequest;
import com.telus.csm.ess.rest.domain.UpdateSaleResponse;
import com.telus.csm.ess.rest.operation.CancelSalesRestOperation;
import com.telus.csm.ess.rest.operation.CreateCartItemRestOperation;
import com.telus.csm.ess.rest.operation.CreateShoppingCartRestOperation;
import com.telus.csm.ess.rest.operation.GetShoppingCartRestOperation;
import com.telus.csm.ess.rest.operation.SubmitSalesRestOperation;
import com.telus.csm.ess.rest.operation.SubmitSalesToPOSRestOperation;
import com.telus.csm.ess.rest.operation.UpdateSaleRestOperation;
import com.telus.csm.ess.rest.operation.UpdateShoppingCartRestOperation;
import com.telus.csm.ewlnsc.aspect.LogOperationName;
import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsc.aspect.ProfilingVariance;


@Component
@Scope(SCOPE_PROTOTYPE)
@MonitorPerformance
@LogOperationName
public class EnterpriseSalesRESTSvcImpl {
	
	@Autowired
	private BeanFactory beanFactory;
	

	public ShoppingCartResponse createShoppingCart(ProfilingVariance dummy, CreateShoppingCart request) {

		CreateShoppingCartRestOperation op = new CreateShoppingCartRestOperation();

		return op.execute(request);

	}
	

	public CartItemResponse cartItem(String shoppingCartId, CreateCartItem request) {
	
		CreateCartItemRestOperation op = new CreateCartItemRestOperation();

		return op.execute(shoppingCartId, request);

	}


	public ShoppingCartResponse updateShoppingCart(ProfilingVariance dummy, String shoppingCartId, UpdateShoppingCart request) {

		UpdateShoppingCartRestOperation op = new UpdateShoppingCartRestOperation();

		return op.execute(shoppingCartId, request);

	}
	

	public ShoppingCartResponse getShoppingCart(String shoppingCartId) {

		GetShoppingCartRestOperation op = new GetShoppingCartRestOperation();
		return op.execute(shoppingCartId);

	}	
	

	public SubmitSalesResponse submitSales(SubmitSalesRequest request) {
		SubmitSalesRestOperation op = beanFactory.getBean(SubmitSalesRestOperation.class);
		return op.execute(request);

	}	
	

	public UpdateSaleResponse updateSale(ProfilingVariance dummy, String salesId, UpdateSaleRequest request) {
		UpdateSaleRestOperation op = beanFactory.getBean(UpdateSaleRestOperation.class);
		return op.execute(salesId, request);
	}


	public void cancelSales(String shoppingCartId) {
		CancelSalesRestOperation op = beanFactory.getBean(CancelSalesRestOperation.class);
		op.execute(shoppingCartId);
	}


	public SubmitSalesToPOSResponse submitSalesToPOS(SubmitSalesToPOSRequest submitSalesToPOSRequest) {
		SubmitSalesToPOSRestOperation op = beanFactory.getBean(SubmitSalesToPOSRestOperation.class);
		return op.execute(submitSalesToPOSRequest);
	}

}
