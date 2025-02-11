package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

public class Address
{
    private GisAddress gisAddress;

    private FmsAddress fmsAddress;

    private List<Links> links;

    public GisAddress getGisAddress ()
    {
        return gisAddress;
    }

    public void setGisAddress (GisAddress gisAddress)
    {
        this.gisAddress = gisAddress;
    }

    public FmsAddress getFmsAddress ()
    {
        return fmsAddress;
    }

    public void setFmsAddress (FmsAddress fmsAddress)
    {
        this.fmsAddress = fmsAddress;
    }



    public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [gisAddress = "+gisAddress+", fmsAddress = "+fmsAddress+", links = "+links+"]";
    }
}
