package com.telus.csm.ewlnsis.rest.webservices;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.aspect.LogOperationName;
import com.telus.csm.ewlnsc.aspect.MonitorPerformance;
import com.telus.csm.ewlnsis.rest.domain.AvailableProductItemResponse;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentRequest;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentResponse;
import com.telus.csm.ewlnsis.rest.operation.GetAvailableInstallDetailRestOperation;
import com.telus.csm.ewlnsis.rest.operation.GetAvailableProductItemRestOperation;

@Component
@Scope(SCOPE_PROTOTYPE)
@MonitorPerformance
@LogOperationName
public class EnterpriseSalesInformationRESTSvcImpl {


	public AvailableProductItemResponse availableProductItem(String transactionId, String shoppingCartId, String commerceCartitemId) {
		
		GetAvailableProductItemRestOperation operation = new GetAvailableProductItemRestOperation();
		AvailableProductItemResponse response = operation.execute(transactionId, shoppingCartId, commerceCartitemId);
		
		return response;
	}

	public GetAvailableAppointmentResponse getAvailableInstallDetail(GetAvailableAppointmentRequest request) {
		GetAvailableInstallDetailRestOperation operation = new GetAvailableInstallDetailRestOperation();
		GetAvailableAppointmentResponse response = operation.execute(request);
		return response;
	}

}
