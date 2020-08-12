package com.forcont.digsigproto.common.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class TemplateDigSigTest
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

//        List<Paper> papers = new ArrayList<>();
        papers.add(paper1);
        papers.add(paper2);
//        return papers;
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

//        List<Signer> signers = new ArrayList<>();
        recipientDigSigs.add(recipientDigSig1);
        recipientDigSigs.add(recipientDigSig2);

//        return signers;
    }


    @Test
    public void testGetTemplateDigSig()
    {
        TemplateDigSig templateDigSig = new TemplateDigSig();
        templateDigSig.setId("12345678");
        templateDigSig.setTemplateName("new Template");
        templateDigSig.setTemplateResultsName("resulting Template");
        templateDigSig.setPapers(papers);
        templateDigSig.setRecipientDigSigs(recipientDigSigs);
        templateDigSig.setDescription("Beschreibung");

        assertThat(templateDigSig.getId(), is("12345678"));
        assertThat(templateDigSig.getTemplateName(), is("new Template"));
        assertThat(templateDigSig.getTemplateResultsName(), is("resulting Template"));
        assertThat(templateDigSig.getPapers(),is(papers));
        assertThat(templateDigSig.getRecipientDigSigs(), is(recipientDigSigs));
        assertThat(templateDigSig.getDescription(), is("Beschreibung"));
    }

    @Test
    public void testGetTemplateDigSigIsFalse()
    {
        TemplateDigSig templateDigSig = new TemplateDigSig();
        templateDigSig.setId("12345678");
        templateDigSig.setTemplateName("new Template");
        templateDigSig.setTemplateResultsName("resulting Template");
        templateDigSig.setPapers(papers);
        templateDigSig.setRecipientDigSigs(recipientDigSigs);
        templateDigSig.setDescription("Beschreibung");

        assertThat(templateDigSig.getId(), is(not("12312678")));
        assertThat(templateDigSig.getTemplateName(), is(not("new Template1")));
        assertThat(templateDigSig.getTemplateResultsName(), is(not("resulting Template1")));
        assertThat(templateDigSig.getPapers(), is(not(new ArrayList<>())));
        assertThat(templateDigSig.getRecipientDigSigs(), is(not(new ArrayList<>())));
        assertThat(templateDigSig.getDescription(), is(not("Beschreibung1")));
    }
}