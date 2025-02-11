package com.telus.csm.ewlnsms.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.csm.ewlnsc.domain.ProductAppointmentRequestVO;

public class BookAppointmentCoreRequest extends CoreRequestBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String shoppingCartId;
	
	private String canBeReachedPhoneNumber;
	
	private List<ProductAppointmentRequestVO> productAppointmentRequest = new ArrayList<ProductAppointmentRequestVO>();
	
	
	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public List<ProductAppointmentRequestVO> getProductAppointmentRequest() {
		return productAppointmentRequest;
	}

	public void setProductAppointmentRequest(List<ProductAppointmentRequestVO> productAppointmentRequest) {
		this.productAppointmentRequest = productAppointmentRequest;
	}

	public String getCanBeReachedPhoneNumber() {
		return canBeReachedPhoneNumber;
	}

	public void setCanBeReachedPhoneNumber(String canBeReachedPhoneNumber) {
		this.canBeReachedPhoneNumber = canBeReachedPhoneNumber;
	}

	

}
