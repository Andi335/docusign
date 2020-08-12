package com.forcont.digsigproto.common.model;

import java.util.List;

public class EnvelopeDigSig implements DigSigModel
{
    private final String id;
    private String status;
    private String expiration;
    private String reminder;
    private List<RecipientDigSig> recipientDigSigs;
    private List<Paper> papers;

    public EnvelopeDigSig(String id, String status, List<Paper> papers, List<RecipientDigSig> recipientDigSigs)
    {
        this.id = id;
        this.status = status;
        this.papers = papers;
        this.recipientDigSigs = recipientDigSigs;
    }

    public EnvelopeDigSig(String id, String status)
    {
        this.id = id;
        this.status = status;
    }


    public List<RecipientDigSig> getRecipientDigSigs()
    {
        return recipientDigSigs;
    }

    public void setRecipientDigSigs(List<RecipientDigSig> recipientDigSigs)
    {
        this.recipientDigSigs = recipientDigSigs;
    }

    public List<Paper> getPapers()
    {
        return papers;
    }

    public void setPapers(List<Paper> papers)
    {
        this.papers = papers;
    }

    public String getExpiration()
    {
        return expiration;
    }

    public void setExpiration(String expiration)
    {
        this.expiration = expiration;
    }

    public String getReminder()
    {
        return reminder;
    }

    public void setReminder(String reminder)
    {
        this.reminder = reminder;
    }

    public String getId()
    {
        return id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }


}
