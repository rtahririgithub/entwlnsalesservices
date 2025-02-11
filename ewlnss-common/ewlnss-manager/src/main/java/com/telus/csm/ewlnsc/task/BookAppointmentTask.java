package com.telus.csm.ewlnsc.task;


import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterResponse;
import com.telus.csm.ewlnsc.domain.ProductAppointmentRequestVO;

public class BookAppointmentTask extends TaskBase{
	
	private BookAppointmentAdapterRequest input;
	private BookAppointmentAdapterResponse result;
	private IWLNBookingRestSvcAdapter adapter;
	private  ProductAppointmentRequestVO productAppointmentRequest;

	public BookAppointmentTask(IWLNBookingRestSvcAdapter adapter,BookAppointmentAdapterRequest input){
		this.adapter = adapter;
		this.input = input;
	}
	
	public BookAppointmentAdapterRequest getInput() {
		return input;
	}

	public BookAppointmentAdapterResponse getResult() {
		return result;
	}
	
	public Exception getRuntimeException(){
		if (this.runtimeException != null)
			return this.runtimeException;
		else
			return null;
	}
	
	@Override
	protected void execute() {
		result = adapter.bookAppointment(input);
	}

	public ProductAppointmentRequestVO getProductAppointmentRequest() {
		return productAppointmentRequest;
	}

	public void setProductAppointmentRequest(ProductAppointmentRequestVO productAppointmentRequest) {
		this.productAppointmentRequest = productAppointmentRequest;
	}

	

}
