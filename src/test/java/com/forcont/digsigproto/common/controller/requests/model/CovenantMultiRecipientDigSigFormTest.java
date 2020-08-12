package com.forcont.digsigproto.common.controller.requests.model;

import com.forcont.digsigproto.common.model.Action;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.junit.Assert.*;

public class CovenantMultiRecipientDigSigFormTest
{

    List<SignerForm> signers = new ArrayList<>();
    List<MultipartFile> documents = new ArrayList<>();

    @Before
    public void generateSignerFormList(){

        ActionForm actionForm1 = new ActionForm();
        actionForm1.setDoc("12345678");
        actionForm1.setType(Action.SIGN);

        ActionForm actionForm2 = new ActionForm();
        actionForm2.setDoc("12345699");
        actionForm2.setType(Action.ACKN);

        List<ActionForm> actions1 = new ArrayList<>();
        actions1.add(actionForm1);

        List<ActionForm> actions2 = new ArrayList<>();
        actions1.add(actionForm2);

        SignerForm signerForm1 = new SignerForm();
        signerForm1.setMail("peter@email.de");
        signerForm1.setName("peter");
        signerForm1.setRoutingOrder("2");
        signerForm1.setActions(actions1);

        SignerForm signerForm2 = new SignerForm();
        signerForm2.setMail("felix@email.de");
        signerForm2.setName("felix");
        signerForm2.setRoutingOrder("1");
        signerForm2.setActions(actions2);

        signers.add(signerForm1);
        signers.add(signerForm2);
    }

    @Before
    public void generateDocuments() throws IOException
    {
        FileInputStream inputFile1 = new FileInputStream("src/test/java/com/forcont/digsigproto/common/controller/requests/model/Test1.pdf");
        MockMultipartFile file1 = new MockMultipartFile("file1", "Vertrag1", "multipart/form-data", inputFile1);

        FileInputStream inputFile2 = new FileInputStream("src/test/java/com/forcont/digsigproto/common/controller/requests/model/Test2.pdf");
        MockMultipartFile file2 = new MockMultipartFile("file2", "Vertrag2", "multipart/form-data", inputFile2);

        documents.add(file1);
        documents.add(file2);
    }


    @Test
    public void getParallelSigning()
    {
        CovenantMultiSignerForm covenantMultiSignerForm = new CovenantMultiSignerForm(documents,signers);
        covenantMultiSignerForm.setParallelSigning(true);
        assertThat(covenantMultiSignerForm.getParallelSigning(),is(true));
    }

    @Test
    public void getDocument()
    {
        CovenantMultiSignerForm covenantMultiSignerForm = new CovenantMultiSignerForm(documents,signers);
        assertThat(covenantMultiSignerForm.getDocument(),is(documents));
    }


    @Test
    public void getSigners()
    {
        CovenantMultiSignerForm covenantMultiSignerForm = new CovenantMultiSignerForm(documents,signers);
        assertThat(covenantMultiSignerForm.getSigners(),is(signers));
    }

    @Test
    public void getParallelSigningIsFalse()
    {
        CovenantMultiSignerForm covenantMultiSignerForm = new CovenantMultiSignerForm(documents,signers);
        covenantMultiSignerForm.setParallelSigning(true);
        assertThat(covenantMultiSignerForm.getParallelSigning(),is(not(false)));
    }

    @Test
    public void getDocumentIsFalse()
    {
        CovenantMultiSignerForm covenantMultiSignerForm = new CovenantMultiSignerForm(documents,signers);
        assertThat(covenantMultiSignerForm.getDocument(),is(not(new ArrayList<>())));
    }


    @Test
    public void getSignersIsFalse()
    {
        CovenantMultiSignerForm covenantMultiSignerForm = new CovenantMultiSignerForm(documents,signers);
        assertThat(covenantMultiSignerForm.getSigners(),is(not(new ArrayList<>())));
    }


}