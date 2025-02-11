package com.telus.csm.ewlnsms.rest.webservices;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.aspect.LogOperationName;
import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentRequest;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentResponse;
import com.telus.csm.ewlnsms.rest.domain.PriceResponse;
import com.telus.csm.ewlnsms.rest.operation.BookAppointmentRestOperation;
import com.telus.csm.ewlnsms.rest.operation.GetPriceRestOperation;

@Component
@Scope(SCOPE_PROTOTYPE)
@MonitorPerformance
@LogOperationName
public class EnterpriseSalesManagementRESTSvcImpl {
    
	private @Autowired BeanFactory beanFactory;
	

	public PriceResponse price(String shoppingCartId, String transactionId) {
		PriceResponse result = null;
				
		//final GetPriceRestOperation restOperation = new GetPriceRestOperation();
		GetPriceRestOperation restOperation = beanFactory.getBean(GetPriceRestOperation.class);
		result = restOperation.execute(shoppingCartId, transactionId);
		
		return result;
	}


	public BookAppointmentResponse bookAppointment(BookAppointmentRequest bookAppointmentRequest) {
		BookAppointmentResponse result = null;
		
		final BookAppointmentRestOperation restOperation = new BookAppointmentRestOperation();
		result =  restOperation.execute(bookAppointmentRequest);
		
		return result;
		
	}

}
