package com.forcont.digsigproto.common.model;
import org.joda.time.DateTime;

import java.util.List;

public class WebhookNotification implements Comparable<WebhookNotification>
{
    private DateTime dateTime;
    private String envelopeId;
    private String envelopeStatus;
    private List<RecipientDigSig> recipientDigSigs;

    public DateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(DateTime datetime)
    {
        this.dateTime = datetime;
    }

    @Override
    public int compareTo(WebhookNotification o)
    {
        return getDateTime().compareTo(o.getDateTime());
    }

    public List<RecipientDigSig> getRecipientDigSigs()
    {
        return recipientDigSigs;
    }

    public void setRecipientDigSigs(List<RecipientDigSig> recipientDigSigs)
    {
        this.recipientDigSigs = recipientDigSigs;
    }

    public String getEnvelopeId()
    {
        return envelopeId;
    }

    public void setEnvelopeId(String envelopeId)
    {
        this.envelopeId = envelopeId;
    }

    public String getEnvelopeStatus()
    {
        return envelopeStatus;
    }

    public void setEnvelopeStatus(String envelopeStatus)
    {
        this.envelopeStatus = envelopeStatus;
    }


}

