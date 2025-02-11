package com.telus.csm.ewlnsc.adapter.wbk.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

/**
 * 
 * @author Jose.Mena
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BookAppointmentAdapterResponse extends WlnOPRestSvcResponseBase {

	private List<BookingInfo> bookingInfoList = new ArrayList<BookingInfo>();

	public List<BookingInfo> getBookingInfoList() {
		return bookingInfoList;
	}

	public void setBookingInfoList(List<BookingInfo> bookingInfoList) {
		this.bookingInfoList = bookingInfoList;
	}

}
