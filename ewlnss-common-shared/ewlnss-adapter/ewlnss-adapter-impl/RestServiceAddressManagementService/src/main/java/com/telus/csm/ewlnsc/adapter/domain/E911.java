package com.telus.csm.ewlnsc.adapter.domain;

public class E911
{
    private String emergencyServiceZoneNumber;

    private String e911Municipality;

    public String getEmergencyServiceZoneNumber ()
    {
        return emergencyServiceZoneNumber;
    }

    public void setEmergencyServiceZoneNumber (String emergencyServiceZoneNumber)
    {
        this.emergencyServiceZoneNumber = emergencyServiceZoneNumber;
    }

    public String getE911Municipality ()
    {
        return e911Municipality;
    }

    public void setE911Municipality (String e911Municipality)
    {
        this.e911Municipality = e911Municipality;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [emergencyServiceZoneNumber = "+emergencyServiceZoneNumber+", e911Municipality = "+e911Municipality+"]";
    }
}
