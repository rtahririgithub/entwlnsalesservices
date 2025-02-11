package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterRequest;

import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IWLNBookingRestSvcAdapter extends IAdapterBase{

	public GetAvailableTimeSlotsAdapterResponse getAvailableTimeSlots(final GetAvailableTimeSlotsAdapterRequest param);
	public ConfirmBookingAdapterResponse confirmBooking(final ConfirmBookingAdapterRequest param);
	public BookAppointmentAdapterResponse bookAppointment(final BookAppointmentAdapterRequest param);
    public CancelBookingAdapterResponse cancelBooking(final CancelBookingAdapterRequest param);
    GetBookingRequirementResponse getBookingRequirement(GetBookingRequirementRequest request);
	
}
