package com.telus.csm.ewlnsms.core.domain;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ess.common.domain.CoreResponseBaseVO;
import com.telus.csm.ewlnsc.domain.ProductAppointmentResponseVO;


public class BookAppointmentCoreResponse extends CoreResponseBaseVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean softBookingInd;
	
	private List<ProductAppointmentResponseVO> productAppointmentList;

	public Boolean getSoftBookingInd() {
		return softBookingInd;
	}

	public void setSoftBookingInd(Boolean softBookingInd) {
		this.softBookingInd = softBookingInd;
	}

	public List<ProductAppointmentResponseVO> getProductAppointmentList() {
		return productAppointmentList;
	}

	public void setProductAppointmentList(List<ProductAppointmentResponseVO> productAppointmentList) {
		this.productAppointmentList = productAppointmentList;
	}

}
