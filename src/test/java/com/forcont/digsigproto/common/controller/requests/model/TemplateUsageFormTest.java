package com.forcont.digsigproto.common.controller.requests.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class TemplateUsageFormTest
{

    private List<String> roles = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> emails = new ArrayList<>();


    @Before
    public void genrateLists()
    {
        String role = "Signer1";
        String role1 = "Signer2";
        this.roles.add(role);
        this.roles.add(role1);

        String name1 = "Lena";
        String name2 = "Ben";
        this.names.add(name1);
        this.names.add(name2);

        String email1 = "lena@email.de";
        String email2 = "ben@email.de";
        this.emails.add(email1);
        this.emails.add(email2);

    }

    TemplateUsageForm templateUsageForm = new TemplateUsageForm("12345678",roles,names,emails);

    @Test
    public void getName()
    {
       assertThat(templateUsageForm.getName(),is(names));
    }

    @Test
    public void getEmail()
    {
        assertThat(templateUsageForm.getEmail(),is(emails));
    }

    @Test
    public void getTemplateId()
    {
        assertThat(templateUsageForm.getTemplateId(),is("12345678"));
    }

    @Test
    public void getRole()
    {
        assertThat(templateUsageForm.getRole(),is(roles));
    }

    @Test
    public void getNameIsFalse()
    {
        assertThat(templateUsageForm.getName(),is(not(new ArrayList<>())));
    }

    @Test
    public void getEmailIsFalse()
    {
        assertThat(templateUsageForm.getEmail(),is(not(new ArrayList<>())));
    }

    @Test
    public void getTemplateIdIsFalse()
    {
        assertThat(templateUsageForm.getTemplateId(),is(not("12345699")));
    }

    @Test
    public void getRoleIsFalse()
    {
        assertThat(templateUsageForm.getRole(),is(not(new ArrayList<>())));
    }

}