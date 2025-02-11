package com.telus.csm.ewlnsc.adapter.domain;

public class AMSErrorResponse
{
    private String errMessage;

    private String errId;

    private String status;

    public String getErrMessage ()
    {
        return errMessage;
    }

    public void setErrMessage (String errMessage)
    {
        this.errMessage = errMessage;
    }

    public String getErrId ()
    {
        return errId;
    }

    public void setErrId (String errId)
    {
        this.errId = errId;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errMessage = "+errMessage+", errId = "+errId+", status = "+status+"]";
    }
}
