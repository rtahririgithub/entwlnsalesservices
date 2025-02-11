package com.telus.csm.ewlnsc.adapter.domain;

public class ZoneInfo
{
    private String zoneType;

    private String zoneName;

    public String getZoneType ()
    {
        return zoneType;
    }

    public void setZoneType (String zoneType)
    {
        this.zoneType = zoneType;
    }

    public String getZoneName ()
    {
        return zoneName;
    }

    public void setZoneName (String zoneName)
    {
        this.zoneName = zoneName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [zoneType = "+zoneType+", zoneName = "+zoneName+"]";
    }
}
