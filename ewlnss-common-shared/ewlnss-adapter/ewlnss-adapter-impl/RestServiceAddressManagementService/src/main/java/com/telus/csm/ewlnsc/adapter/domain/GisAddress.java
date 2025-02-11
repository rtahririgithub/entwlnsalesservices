package com.telus.csm.ewlnsc.adapter.domain;

public class GisAddress
{
    private String fmsMunicipality;

    private E911 e911;

    private String dirPrefix;

    private Coordinate coordinate;

    private String dirSuffix;

    private String streetName;

    private String fmsStreetName;

    private String suffix;

    private String addressId;

    private String city;

    private String serviceable;

    private String unit;

    private ZoneInfo zoneInfo;

    private String postalCode;

    private String floor;

    private String fmsId;

    private String prefix;

    private String province;

    private String streetNumber;

    private String buildingType;

    private String lpdsId;

    private String streetType;

    private String buildingId;

    public String getFmsMunicipality ()
    {
        return fmsMunicipality;
    }

    public void setFmsMunicipality (String fmsMunicipality)
    {
        this.fmsMunicipality = fmsMunicipality;
    }

    public E911 getE911 ()
    {
        return e911;
    }

    public void setE911 (E911 e911)
    {
        this.e911 = e911;
    }

    public String getDirPrefix ()
    {
        return dirPrefix;
    }

    public void setDirPrefix (String dirPrefix)
    {
        this.dirPrefix = dirPrefix;
    }

    public Coordinate getCoordinate ()
    {
        return coordinate;
    }

    public void setCoordinate (Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    public String getDirSuffix ()
    {
        return dirSuffix;
    }

    public void setDirSuffix (String dirSuffix)
    {
        this.dirSuffix = dirSuffix;
    }

    public String getStreetName ()
    {
        return streetName;
    }

    public void setStreetName (String streetName)
    {
        this.streetName = streetName;
    }

    public String getFmsStreetName ()
    {
        return fmsStreetName;
    }

    public void setFmsStreetName (String fmsStreetName)
    {
        this.fmsStreetName = fmsStreetName;
    }

    public String getSuffix ()
    {
        return suffix;
    }

    public void setSuffix (String suffix)
    {
        this.suffix = suffix;
    }

    public String getAddressId ()
    {
        return addressId;
    }

    public void setAddressId (String addressId)
    {
        this.addressId = addressId;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getServiceable ()
    {
        return serviceable;
    }

    public void setServiceable (String serviceable)
    {
        this.serviceable = serviceable;
    }

    public String getUnit ()
    {
        return unit;
    }

    public void setUnit (String unit)
    {
        this.unit = unit;
    }

    public ZoneInfo getZoneInfo ()
    {
        return zoneInfo;
    }

    public void setZoneInfo (ZoneInfo zoneInfo)
    {
        this.zoneInfo = zoneInfo;
    }

    public String getPostalCode ()
    {
        return postalCode;
    }

    public void setPostalCode (String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getFloor ()
    {
        return floor;
    }

    public void setFloor (String floor)
    {
        this.floor = floor;
    }

    public String getFmsId ()
    {
        return fmsId;
    }

    public void setFmsId (String fmsId)
    {
        this.fmsId = fmsId;
    }

    public String getPrefix ()
    {
        return prefix;
    }

    public void setPrefix (String prefix)
    {
        this.prefix = prefix;
    }

    public String getProvince ()
    {
        return province;
    }

    public void setProvince (String province)
    {
        this.province = province;
    }

    public String getStreetNumber ()
    {
        return streetNumber;
    }

    public void setStreetNumber (String streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public String getBuildingType ()
    {
        return buildingType;
    }

    public void setBuildingType (String buildingType)
    {
        this.buildingType = buildingType;
    }

    public String getLpdsId ()
    {
        return lpdsId;
    }

    public void setLpdsId (String lpdsId)
    {
        this.lpdsId = lpdsId;
    }

    public String getStreetType ()
    {
        return streetType;
    }

    public void setStreetType (String streetType)
    {
        this.streetType = streetType;
    }

    public String getBuildingId ()
    {
        return buildingId;
    }

    public void setBuildingId (String buildingId)
    {
        this.buildingId = buildingId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fmsMunicipality = "+fmsMunicipality+", e911 = "+e911+", dirPrefix = "+dirPrefix+", coordinate = "+coordinate+", dirSuffix = "+dirSuffix+", streetName = "+streetName+", fmsStreetName = "+fmsStreetName+", suffix = "+suffix+", addressId = "+addressId+", city = "+city+", serviceable = "+serviceable+", unit = "+unit+", zoneInfo = "+zoneInfo+", postalCode = "+postalCode+", floor = "+floor+", fmsId = "+fmsId+", prefix = "+prefix+", province = "+province+", streetNumber = "+streetNumber+", buildingType = "+buildingType+", lpdsId = "+lpdsId+", streetType = "+streetType+", buildingId = "+buildingId+"]";
    }
}
