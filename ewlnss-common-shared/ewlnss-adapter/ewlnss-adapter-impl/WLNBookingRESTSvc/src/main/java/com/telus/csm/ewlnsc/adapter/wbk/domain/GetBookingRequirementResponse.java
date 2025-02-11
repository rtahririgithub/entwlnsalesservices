package com.telus.csm.ewlnsc.adapter.wbk.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GetBookingRequirementResponse extends WlnOPRestSvcResponseBase {
	private OrderBookingRequirement orderBookingRequirement;
	private List<OrderItemBookingRequirement> orderItemBookingRequirements;

	public OrderBookingRequirement getOrderBookingRequirement() {
		return orderBookingRequirement;
	}

	public void setOrderBookingRequirement(OrderBookingRequirement orderBookingRequirement) {
		this.orderBookingRequirement = orderBookingRequirement;
	}

	public List<OrderItemBookingRequirement> getOrderItemBookingRequirements() {
		return orderItemBookingRequirements;
	}

	public void setOrderItemBookingRequirements(List<OrderItemBookingRequirement> orderItemBookingRequirements) {
		this.orderItemBookingRequirements = orderItemBookingRequirements;
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public static class OrderItemBookingRequirement {
		private String id;
		private String bookingRequirementStatus;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBookingRequirementStatus() {
			return bookingRequirementStatus;
		}

		public void setBookingRequirementStatus(String bookingRequirementStatus) {
			this.bookingRequirementStatus = bookingRequirementStatus;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public static class OrderBookingRequirement {
		private String id;
		private String bookingActionStatus;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBookingActionStatus() {
			return bookingActionStatus;
		}

		public void setBookingActionStatus(String bookingActionStatus) {
			this.bookingActionStatus = bookingActionStatus;
		}
	}
}
