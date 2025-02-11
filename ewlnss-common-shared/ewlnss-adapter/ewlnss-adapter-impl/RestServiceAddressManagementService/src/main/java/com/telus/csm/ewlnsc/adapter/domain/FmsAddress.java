package com.telus.csm.ewlnsc.adapter.domain;

public class FmsAddress
{
    private String servAddrHouse;

    private String saCityProvinceCode;

    private String saCLLICode;

    private String saNo;

    private String servAddrStreetName;

    private String servAddrAptNo;

    private String saPostalCode;

    private String resrcCd;

    public String getServAddrHouse ()
    {
        return servAddrHouse;
    }

    public void setServAddrHouse (String servAddrHouse)
    {
        this.servAddrHouse = servAddrHouse;
    }

    public String getSaCityProvinceCode ()
    {
        return saCityProvinceCode;
    }

    public void setSaCityProvinceCode (String saCityProvinceCode)
    {
        this.saCityProvinceCode = saCityProvinceCode;
    }

    public String getSaCLLICode ()
    {
        return saCLLICode;
    }

    public void setSaCLLICode (String saCLLICode)
    {
        this.saCLLICode = saCLLICode;
    }

    public String getSaNo ()
    {
        return saNo;
    }

    public void setSaNo (String saNo)
    {
        this.saNo = saNo;
    }

    public String getServAddrStreetName ()
    {
        return servAddrStreetName;
    }

    public void setServAddrStreetName (String servAddrStreetName)
    {
        this.servAddrStreetName = servAddrStreetName;
    }

    public String getServAddrAptNo ()
    {
        return servAddrAptNo;
    }

    public void setServAddrAptNo (String servAddrAptNo)
    {
        this.servAddrAptNo = servAddrAptNo;
    }

    public String getSaPostalCode ()
    {
        return saPostalCode;
    }

    public void setSaPostalCode (String saPostalCode)
    {
        this.saPostalCode = saPostalCode;
    }

    public String getResrcCd ()
    {
        return resrcCd;
    }

    public void setResrcCd (String resrcCd)
    {
        this.resrcCd = resrcCd;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [servAddrHouse = "+servAddrHouse+", saCityProvinceCode = "+saCityProvinceCode+", saCLLICode = "+saCLLICode+", saNo = "+saNo+", servAddrStreetName = "+servAddrStreetName+", servAddrAptNo = "+servAddrAptNo+", saPostalCode = "+saPostalCode+", resrcCd = "+resrcCd+"]";
    }
}
