package com.forcont.digsigproto.common.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


public class RecipientDigSigTest
{

    @Test
    public void testSetGetSignerNameAndEmailIsOK()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig("Peter", "peter.hans@email.de");
        assertThat(recipientDigSig.getName(), is("Peter"));
        assertThat(recipientDigSig.getEmail(), is("peter.hans@email.de"));
    }

    @Test
    public void testSetGetSignerNameAndEmailIsFalse()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig("Peter", "peter.hans@email.de");
        assertThat(recipientDigSig.getName(), is(not("Hans")));
        assertThat(recipientDigSig.getEmail(), is(not("hans.peter@email.de")));
    }

    @Test
    public void testSetGetSignerIdIsOk()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setId("123456789");
        assertThat(recipientDigSig.getId(), is("123456789"));
    }

    @Test
    public void testSetGetSignerIdIsFalse()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setId("123456789");
        assertThat(recipientDigSig.getId(), is(not("123456779")));
    }

    @Test
    public void testSetGetSignerLanguageIsOk()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setLanguage("de");
        assertThat(recipientDigSig.getLanguage(), is("de"));
    }

    @Test
    public void testSetGetSignerLanguageIsFalse()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setLanguage("de");
        assertThat(recipientDigSig.getLanguage(), is(not("en")));
    }

    @Test
    public void testSetGetSignerStatusIsOk()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setStatus("completed");
        assertThat(recipientDigSig.getStatus(), is("completed"));
    }

    @Test
    public void testSetGetSignerStatusIsFalse()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setStatus("completed");
        assertThat(recipientDigSig.getStatus(), is(not("voided")));
    }

    @Test
    public void testSetGetSignerRoleIsOk()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setRole("signer");
        assertThat(recipientDigSig.getRole(), is("signer"));
    }

    @Test
    public void testSetGetSignerRoleIsFalse()
    {
        RecipientDigSig recipientDigSig = new RecipientDigSig(null, null);
        recipientDigSig.setRole("signer");
        assertThat(recipientDigSig.getRole(), is(not("signer1")));
    }
}