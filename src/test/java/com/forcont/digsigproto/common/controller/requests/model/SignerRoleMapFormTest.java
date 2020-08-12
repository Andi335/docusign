package com.forcont.digsigproto.common.controller.requests.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
public class SignerRoleMapFormTest
{

    @Test
    public void getRole()
    {
        SignerRoleMapForm signerRoleMapForm = new SignerRoleMapForm();
        signerRoleMapForm.setRole("Signer");
        assertThat(signerRoleMapForm.getRole(),is("Signer"));
    }

    @Test
    public void getName()
    {
        SignerRoleMapForm signerRoleMapForm = new SignerRoleMapForm();
        signerRoleMapForm.setName("Aragon");
        assertThat(signerRoleMapForm.getName(), is("Aragon"));
    }

    @Test
    public void getEmail()
    {
        SignerRoleMapForm signerRoleMapForm = new SignerRoleMapForm();
        signerRoleMapForm.setEmail("aragon@email.de");
        assertThat(signerRoleMapForm.getEmail(),is("aragon@email.de"));
    }

    @Test
    public void getRoleIsFalse()
    {
        SignerRoleMapForm signerRoleMapForm = new SignerRoleMapForm();
        signerRoleMapForm.setRole("Signer");
        assertThat(signerRoleMapForm.getRole(),is(not("Viewer")));
    }

    @Test
    public void getNameIsFalse()
    {
        SignerRoleMapForm signerRoleMapForm = new SignerRoleMapForm();
        signerRoleMapForm.setName("Aragon");
        assertThat(signerRoleMapForm.getName(), is(not("Boromir")));
    }

    @Test
    public void getEmailIsFalse()
    {
        SignerRoleMapForm signerRoleMapForm = new SignerRoleMapForm();
        signerRoleMapForm.setEmail("aragon@email.de");
        assertThat(signerRoleMapForm.getEmail(),is(not("boromir@email.de")));
    }
}