package com.telus.csm.ewlnss.ws;

/**
 *  The new guidance from SOA team is : Web service shall stop throwing PolicyException, instead, a Web service operation's Response shall be 
 *  sub class of ResponseMessage, in which, the operation can populate the error information in case of Exception. 
 *   
 * This Runtime Exception present an situation that a Web service return a ResponseMessage that contain error.
 *
 */
public class EndpointException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final transient Object endpointResponse;
	
	public EndpointException( Object endpointResponse ) {
		this.endpointResponse = endpointResponse;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getEndpointResponse() {
		return (T) endpointResponse;
	}

}
