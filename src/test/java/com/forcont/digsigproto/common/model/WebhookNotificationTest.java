package com.forcont.digsigproto.common.model;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class WebhookNotificationTest
{
    private List<RecipientDigSig> recipientDigSigs = new ArrayList<>();
    private List<RecipientDigSig> recipientDigSigs2 = new ArrayList<>();

    @Before
    public void generateRecipients()
    {
        RecipientDigSig recipientDigSig1 = new RecipientDigSig("Erik", "erik@email.de");
        RecipientDigSig recipientDigSig2 = new RecipientDigSig("Gretel", "gretel@email.de");
        recipientDigSigs.add(recipientDigSig1);
        recipientDigSigs.add(recipientDigSig2);

        recipientDigSigs2.add(recipientDigSig1);

    }


    @Test
    public void getDateTime()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        DateTime dateTime = DateTime.parse("2016-01-01T01:06:27.1254729");
        webhookNotification.setDateTime(dateTime);
        assertThat(webhookNotification.getDateTime(), is(DateTime.parse("2016-01-01T01:06:27.1254729")));
    }

    @Test
    public void getRecipientDigSigs()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        webhookNotification.setRecipientDigSigs(recipientDigSigs);
        assertThat(webhookNotification.getRecipientDigSigs(),is(recipientDigSigs));
    }

    @Test
    public void getEnvelopeId()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        webhookNotification.setEnvelopeId("1234567889");
        assertThat(webhookNotification.getEnvelopeId(), is("1234567889"));
    }

    @Test
    public void getEnvelopeStatus()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        webhookNotification.setEnvelopeStatus("delivered");
        assertThat(webhookNotification.getEnvelopeStatus(),is("delivered"));
    }

    @Test
    public void getDateTimeIsFalse()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        DateTime dateTime = DateTime.parse("2016-01-01T01:06:27.1254729");
        webhookNotification.setDateTime(dateTime);
        assertThat(webhookNotification.getDateTime(), is(not(DateTime.parse("2019-01-01T01:06:27.1254729"))));
    }

    @Test
    public void getRecipientDigSigsIsFalse()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        webhookNotification.setRecipientDigSigs(recipientDigSigs);
        assertThat(webhookNotification.getRecipientDigSigs(),is(not(recipientDigSigs2)));
    }

    @Test
    public void getEnvelopeIdIsFalse()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        webhookNotification.setEnvelopeId("1234567889");
        assertThat(webhookNotification.getEnvelopeId(), is(not("1243967889")));
    }

    @Test
    public void getEnvelopeStatusIsFalse()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        webhookNotification.setEnvelopeStatus("delivered");
        assertThat(webhookNotification.getEnvelopeStatus(),is(not("completed")));
    }
}