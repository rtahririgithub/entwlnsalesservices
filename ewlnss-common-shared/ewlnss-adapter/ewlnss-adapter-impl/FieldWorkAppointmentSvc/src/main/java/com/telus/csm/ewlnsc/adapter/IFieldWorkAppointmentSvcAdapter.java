package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IFieldWorkAppointmentSvcAdapter extends IAdapterBase {
	
	public FieldWorkAppointmentAdapterResponse searchAvailableAppointmentList(final FieldWorkAppointmentAdapterRequest param);
	
}
