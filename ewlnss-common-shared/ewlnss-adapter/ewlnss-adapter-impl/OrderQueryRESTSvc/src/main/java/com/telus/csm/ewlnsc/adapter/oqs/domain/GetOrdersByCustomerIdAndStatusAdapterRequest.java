package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GetOrdersByCustomerIdAndStatusAdapterRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String customerid;
	private List<String> ordersStatusesList = new ArrayList<String>();

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public List<String> getOrdersStatusesList() {
		return ordersStatusesList;
	}

	public void setOrdersStatusesList(List<String> ordersStatusesList) {
		this.ordersStatusesList = ordersStatusesList;
	}
}