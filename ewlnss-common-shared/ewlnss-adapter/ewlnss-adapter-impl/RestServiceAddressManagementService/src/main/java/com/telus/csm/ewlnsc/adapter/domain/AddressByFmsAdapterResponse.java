package com.telus.csm.ewlnsc.adapter.domain;

public class AddressByFmsAdapterResponse
{
    private Addresses addresses;
    
    private AMSErrorResponse errorResponse;

    public AMSErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(AMSErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public Addresses getAddresses ()
    {
        return addresses;
    }

    public void setAddresses (Addresses addresses)
    {
        this.addresses = addresses;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Addresses = "+addresses+"]";
    }
}