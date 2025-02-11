package com.telus.csm.ewlnsc.adapter.wbk.domain;

/**
 * 
 * @author Jose.Mena
 *
 */
public class BookAppointmentAdapterRequest {

	private String stickysessionid;
	private BookAppointmentAdapterRequestBody body;
	private String agent_uid;

	public String getStickysessionid() {
		return stickysessionid;
	}

	public void setStickysessionid(String stickysessionid) {
		this.stickysessionid = stickysessionid;
	}

	public BookAppointmentAdapterRequestBody getBody() {
		if (body == null) {
			body = new BookAppointmentAdapterRequestBody();
		}
		return body;
	}

	public void setBody(BookAppointmentAdapterRequestBody body) {
		this.body = body;
	}

	public String getAgent_uid() {
		return agent_uid;
	}

	public void setAgent_uid(String agent_uid) {
		this.agent_uid = agent_uid;
	}

}
