package com.telus.csm.ewlnsc.adapter.wbk.domain;

public class InstallationRequirementsList
{
    private String requestedWorkTime;

    private String requestedInstallType;

    private String requestedBucketType;

    private String productServiceType;

    private String actionType;

    public String getRequestedWorkTime ()
    {
        return requestedWorkTime;
    }

    public void setRequestedWorkTime (String requestedWorkTime)
    {
        this.requestedWorkTime = requestedWorkTime;
    }

    public String getRequestedInstallType ()
    {
        return requestedInstallType;
    }

    public void setRequestedInstallType (String requestedInstallType)
    {
        this.requestedInstallType = requestedInstallType;
    }

    public String getRequestedBucketType ()
    {
        return requestedBucketType;
    }

    public void setRequestedBucketType (String requestedBucketType)
    {
        this.requestedBucketType = requestedBucketType;
    }

    public String getProductServiceType ()
    {
        return productServiceType;
    }

    public void setProductServiceType (String productServiceType)
    {
        this.productServiceType = productServiceType;
    }

    public String getActionType ()
    {
        return actionType;
    }

    public void setActionType (String actionType)
    {
        this.actionType = actionType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [requestedWorkTime = "+requestedWorkTime+", requestedInstallType = "+requestedInstallType+", requestedBucketType = "+requestedBucketType+", productServiceType = "+productServiceType+", actionType = "+actionType+"]";
    }
}