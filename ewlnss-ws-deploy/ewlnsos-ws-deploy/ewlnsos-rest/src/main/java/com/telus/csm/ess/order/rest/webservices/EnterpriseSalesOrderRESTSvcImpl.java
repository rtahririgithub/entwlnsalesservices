package com.telus.csm.ess.order.rest.webservices;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.order.rest.operation.CancelSalesRestOperation;
import com.telus.csm.ess.order.rest.operation.SubmitSalesRestOperation;
import com.telus.csm.ess.order.rest.operation.SubmitSalesToPOSRestOperation;
import com.telus.csm.ess.order.rest.operation.UpdateSaleRestOperation;
import com.telus.csm.ess.rest.domain.SubmitSalesRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesResponse;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSResponse;
import com.telus.csm.ess.rest.domain.UpdateSaleRequest;
import com.telus.csm.ess.rest.domain.UpdateSaleResponse;
import com.telus.csm.ewlnsc.aspect.LogOperationName;
import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsc.aspect.ProfilingVariance;


@Component
@Scope(SCOPE_PROTOTYPE)
@MonitorPerformance
@LogOperationName
public class EnterpriseSalesOrderRESTSvcImpl {
	
	@Autowired
	private BeanFactory beanFactory;
	

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
