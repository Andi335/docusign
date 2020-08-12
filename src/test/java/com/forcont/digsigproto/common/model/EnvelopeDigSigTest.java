package com.forcont.digsigproto.common.model;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.junit.Assert.*;

public class EnvelopeDigSigTest
{

    List<Paper> papers = new ArrayList<>();
    List<RecipientDigSig> recipientDigSigs = new ArrayList<>();

    @Before
    public void generatePaperList()
    {
        byte[] bytes1 = {72, 101, 108, 108, 111, 32, 63, 63, 63,
                63, 63, 33};
        byte[] bytes2 = {72, 101, 108, 108, 111, 32, 63, 63, 63,
                63, 63, 21};

        Paper paper1 = new Paper("12345678", "Ein Paper", "signing/paper", 3, bytes1);
        Paper paper2 = new Paper("12345612", "Ein Paper2", "signing/paper2", 2, bytes2);

        papers.add(paper1);
        papers.add(paper2);

    }

    @Before
    public void generateSignerList()
    {

        RecipientDigSig recipientDigSig1 = new RecipientDigSig("Fritz", "fritz.müller@mail.de");
        recipientDigSig1.setRole("signer1");
        recipientDigSig1.setStatus("in progress");
        recipientDigSig1.setId("12345678");
        recipientDigSig1.setLanguage("de");


        RecipientDigSig recipientDigSig2 = new RecipientDigSig("Peter", "Peter.Schmitd@mail.de");
        recipientDigSig2.setRole("signer2");
        recipientDigSig2.setStatus("completed");
        recipientDigSig2.setId("12345632");
        recipientDigSig2.setLanguage("en");


        recipientDigSigs.add(recipientDigSig1);
        recipientDigSigs.add(recipientDigSig2);


    }


    @Test
    public void TestGetEnvelopeDigSig()
    {
        EnvelopeDigSig envelopeDigSig = new EnvelopeDigSig("12345678", "in progress", papers, recipientDigSigs);
        envelopeDigSig.setExpiration("30");
        envelopeDigSig.setReminder("2");

        assertThat(envelopeDigSig.getId(), is("12345678"));
        assertThat(envelopeDigSig.getStatus(), is("in progress"));
        assertThat(envelopeDigSig.getExpiration(), is("30"));
        assertThat(envelopeDigSig.getReminder(), is("2"));
        assertThat(envelopeDigSig.getPapers(), is(papers));
        assertThat(envelopeDigSig.getRecipientDigSigs(), is(recipientDigSigs));
    }

    @Test
    public void TestGetEnvelopeDigSigIsFalse()
    {
        EnvelopeDigSig envelopeDigSig = new EnvelopeDigSig("12345678", "in progress", papers, recipientDigSigs);
        envelopeDigSig.setExpiration("30");
        envelopeDigSig.setReminder("2");

        assertThat(envelopeDigSig.getId(), is(not("12345438")));
        assertThat(envelopeDigSig.getStatus(), is(not("completed")));
        assertThat(envelopeDigSig.getExpiration(), is(not("31")));
        assertThat(envelopeDigSig.getReminder(), is(not("1")));
        assertThat(envelopeDigSig.getPapers(), is(not(new ArrayList<>())));
        assertThat(envelopeDigSig.getRecipientDigSigs(), is(not(new ArrayList<>())));
    }


}