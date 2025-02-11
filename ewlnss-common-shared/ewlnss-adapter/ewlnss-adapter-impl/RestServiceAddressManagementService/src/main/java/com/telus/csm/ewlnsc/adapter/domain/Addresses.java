package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

public class Addresses
{
    private String executionTime;

    private List<Address> address;

    public String getExecutionTime ()
    {
        return executionTime;
    }

    public void setExecutionTime (String executionTime)
    {
        this.executionTime = executionTime;
    }



    public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [executionTime = "+executionTime+", Address = "+address+"]";
    }
}