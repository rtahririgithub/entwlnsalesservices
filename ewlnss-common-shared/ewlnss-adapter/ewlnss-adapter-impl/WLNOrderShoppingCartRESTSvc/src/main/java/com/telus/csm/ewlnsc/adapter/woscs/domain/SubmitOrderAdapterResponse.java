package com.telus.csm.ewlnsc.adapter.woscs.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SubmitOrderAdapterResponse extends WlnOPRestSvcResponseBase {

	private String status;

	public SubmitOrderAdapterResponse() {

	}

	public SubmitOrderAdapterResponse(String result) {
		setStatus(result);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
