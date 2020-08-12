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

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class TemplateSetupFormTest
{

    private List<MultipartFile> documents = new ArrayList<>();
    private List<RoleForm> roleForms = new ArrayList<>();


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

    @Before
    public void generateLists()
    {
        List<ActionForm> actions = new ArrayList<>();
        List<ActionForm> actions2 = new ArrayList<>();

        ActionForm actionForm1 = new ActionForm();
        actionForm1.setDoc("12345678");
        actionForm1.setType(Action.SIGN);

        ActionForm actionForm2 = new ActionForm();
        actionForm2.setDoc("12345699");
        actionForm2.setType(Action.ACKN);
        actions.add(actionForm1);
        actions.add(actionForm2);

        ActionForm actionForm3 = new ActionForm();
        actionForm3.setDoc("11123454");
        actionForm3.setType(Action.VIEW);
        actions2.add(actionForm3);


        RoleForm roleForm = new RoleForm();
        roleForm.setActions(actions);
        roleForm.setRole("Signer");

        RoleForm roleForm1 = new RoleForm();
        roleForm1.setActions(actions2);
        roleForm1.setRole("Viewer");

        roleForms.add(roleForm);
        roleForms.add(roleForm1);
    }

    private TemplateSetupForm templateSetupForm = new TemplateSetupForm("template1", documents, roleForms, true, "30", "2");

    @Test
    public void getTemplateName()
    {
        assertThat(templateSetupForm.getTemplateName(), is("template1"));
    }

    @Test
    public void getDocument()
    {
        assertThat(templateSetupForm.getDocument(), is(documents));
    }

    @Test
    public void getRoleForms()
    {
        assertThat(templateSetupForm.getRoles(), is(roleForms));
    }

    @Test
    public void isParallelSigning()
    {
        assertThat(templateSetupForm.isParallelSigning(), is(true));
    }

    @Test
    public void getExpirationDays()
    {
        assertThat(templateSetupForm.getExpirationDays(), is("30"));
    }

    @Test
    public void getExpirationReminderDays()
    {
        assertThat(templateSetupForm.getExpirationReminderDays(), is("2"));
    }

    @Test
    public void getTemplateNameIsFalse()
    {
        assertThat(templateSetupForm.getTemplateName(), is(not("template2")));
    }

    @Test
    public void getDocumentIsFalse()
    {
        assertThat(templateSetupForm.getDocument(), is(not(new ArrayList<>())));
    }

    @Test
    public void getRoleFormsIsFalse()
    {
        assertThat(templateSetupForm.getRoles(), is(not(new ArrayList<>())));
    }

    @Test
    public void isParallelSigningIsFalse()
    {
        assertThat(templateSetupForm.isParallelSigning(), is(not(false)));
    }

    @Test
    public void getExpirationDaysIsFalse()
    {
        assertThat(templateSetupForm.getExpirationDays(), is(not("60")));
    }

    @Test
    public void getExpirationReminderDaysIsFalse()
    {
        assertThat(templateSetupForm.getExpirationReminderDays(), is(not("5")));
    }


}