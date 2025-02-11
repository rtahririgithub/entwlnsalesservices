package com.telus.csm.ewlnsc.adapter.domain;

public class Coordinate
{
    private String longitude;

    private String latitude;

    private String srsCode;

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getSrsCode ()
    {
        return srsCode;
    }

    public void setSrsCode (String srsCode)
    {
        this.srsCode = srsCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [longitude = "+longitude+", latitude = "+latitude+", srsCode = "+srsCode+"]";
    }
}
		