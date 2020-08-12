package com.forcont.digsigproto.common.controller.requests.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SignerProcessMapFormTest
{

    private List<SignerRoleMapForm> signerRoleMapForms = new ArrayList<>();
    @Before
    public void generateSignerRoleMapForms()
    {
        SignerRoleMapForm signerRoleMapForm = new SignerRoleMapForm();
        signerRoleMapForm.setName("Paulina");
        signerRoleMapForm.setEmail("paulina@email.de");
        signerRoleMapForm.setRole("Signer");

        SignerRoleMapForm signerRoleMapForm1 = new SignerRoleMapForm();
        signerRoleMapForm1.setName("Achmed");
        signerRoleMapForm1.setEmail("achmed@email.de");
        signerRoleMapForm1.setRole("Viewer");

        signerRoleMapForms.add(signerRoleMapForm);
        signerRoleMapForms.add(signerRoleMapForm1);
    }

    @Test
    public void getProcessId()
    {
        SignerProcessMapForm signerProcessMapForm = new SignerProcessMapForm();
        signerProcessMapForm.setProcessId("123456789");
        assertThat(signerProcessMapForm.getProcessId(),is("123456789"));
    }

    @Test
    public void getSignerRoleMapForms()
    {
        SignerProcessMapForm signerProcessMapForm = new SignerProcessMapForm();
        signerProcessMapForm.setSignerRoleMapForms(signerRoleMapForms);
        assertThat(signerProcessMapForm.getSignerRoleMapForms(),is(signerRoleMapForms));
    }
}