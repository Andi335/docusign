package com.forcont.digsigproto.common.model;

import com.forcont.digsigproto.common.controller.requests.model.ActionForm;
import com.forcont.digsigproto.common.controller.requests.model.SignerForm;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.junit.Assert.*;

public class MultipleSigningActionTest
{

//     List<SignerForm> signers = new ArrayList<>();
//     List<Paper> papers = new ArrayList<>();
//
//
//    @Before
//    public void generatePaperList()
//    {
//        byte[] bytes1 = {72, 101, 108, 108, 111, 32, 63, 63, 63,
//                63, 63, 33};
//        byte[] bytes2 = {72, 101, 108, 108, 111, 32, 63, 63, 63,
//                63, 63, 21};
//
//        Paper paper1 = new Paper("12345678", "Ein Paper", "signing/paper", 3, bytes1);
//        Paper paper2 = new Paper("12345612", "Ein Paper2", "signing/paper2", 2, bytes2);
//
//        papers.add(paper1);
//        papers.add(paper2);
//
//    }
//
//
//    @Before
//    public void generateSignerFormList(){
//
//        ActionForm actionForm1 = new ActionForm();
//        actionForm1.setDoc("12345678");
//        actionForm1.setType(Action.SIGN);
//
//        ActionForm actionForm2 = new ActionForm();
//        actionForm2.setDoc("12345699");
//        actionForm2.setType(Action.ACKN);
//
//        List<ActionForm> actions1 = new ArrayList<>();
//        actions1.add(actionForm1);
//
//        List<ActionForm> actions2 = new ArrayList<>();
//        actions1.add(actionForm2);
//
//        SignerForm signerForm1 = new SignerForm();
//        signerForm1.setMail("peter@email.de");
//        signerForm1.setName("peter");
//        signerForm1.setRoutingOrder("2");
//        signerForm1.setActions(actions1);
//
//        SignerForm signerForm2 = new SignerForm();
//        signerForm2.setMail("felix@email.de");
//        signerForm2.setName("felix");
//        signerForm2.setRoutingOrder("1");
//        signerForm2.setActions(actions2);
//
//        signers.add(signerForm1);
//        signers.add(signerForm2);
//    }
//
//
//
//
//    @Test
//    public void getSetSigners()
//    {
//        MultipleSigningAction multipleSigningAction = new MultipleSigningAction();
//        multipleSigningAction.setSigners(signers);
//
//        assertThat(multipleSigningAction.getSigners(),is(signers));
//    }
//
//
//
//    @Test
//    public void getSetPapers()
//    {
//        MultipleSigningAction multipleSigningAction = new MultipleSigningAction();
//        multipleSigningAction.setPapers(papers);
//
//        assertThat(multipleSigningAction.getPapers(), is(papers));
//    }
//
//
//
//    @Test
//    public void isSetParallelsigning()
//    {
//        MultipleSigningAction multipleSigningAction = new MultipleSigningAction();
//        multipleSigningAction.setParallelsigning(true);
//
//        assertThat(multipleSigningAction.isParallelsigning(),is(true));
//    }
//
//
//    @Test
//    public void getSetSignersIsFalse()
//    {
//        MultipleSigningAction multipleSigningAction = new MultipleSigningAction();
//        multipleSigningAction.setSigners(signers);
//
//        assertThat(multipleSigningAction.getSigners(),is(not(new ArrayList())));
//    }
//
//
//
//    @Test
//    public void getSetPapersIsFalse()
//    {
//        MultipleSigningAction multipleSigningAction = new MultipleSigningAction();
//        multipleSigningAction.setPapers(papers);
//
//        assertThat(multipleSigningAction.getPapers(), is(not(new ArrayList())));
//    }
//
//
//
//    @Test
//    public void isSetParallelsigningIsFalse()
//    {
//        MultipleSigningAction multipleSigningAction = new MultipleSigningAction();
//        multipleSigningAction.setParallelsigning(true);
//
//        assertThat(multipleSigningAction.isParallelsigning(),is(not(false)));
//    }
//


}