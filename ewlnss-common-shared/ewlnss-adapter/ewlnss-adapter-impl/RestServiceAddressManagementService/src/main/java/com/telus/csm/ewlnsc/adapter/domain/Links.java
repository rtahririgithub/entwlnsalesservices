package com.telus.csm.ewlnsc.adapter.domain;

public class Links
{
    private String link;

    private String rel;

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getRel ()
    {
        return rel;
    }

    public void setRel (String rel)
    {
        this.rel = rel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [link = "+link+", rel = "+rel+"]";
    }
}