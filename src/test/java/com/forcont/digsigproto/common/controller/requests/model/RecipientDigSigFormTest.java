package com.forcont.digsigproto.common.controller.requests.model;

import com.forcont.digsigproto.common.model.Action;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class RecipientDigSigFormTest
{


    private List<ActionForm> actions = new ArrayList<>();

    @Before
    public void generateActions()
    {
        ActionForm actionForm1 = new ActionForm();
        actionForm1.setType(Action.SIGN);
        actionForm1.setDoc("12345678");

        ActionForm actionForm2 = new ActionForm();
        actionForm2.setType(Action.ACKN);
        actionForm2.setDoc("12345699");

        actions.add(actionForm1);
        actions.add(actionForm2);
    }


    @Test
    public void getActions()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setActions(actions);

        assertThat(signerForm.getActions(), is(actions));

    }

    @Test
    public void getMail()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setMail("peter@email.de");

        assertThat(signerForm.getMail(), is("peter@email.de"));

    }

    @Test
    public void getName()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setName("Ben");

        assertThat(signerForm.getName(), is("Ben"));
    }

    @Test
    public void getRoutingOrder()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setRoutingOrder("3");

        assertThat(signerForm.getRoutingOrder(), is("3"));
    }

    @Test
    public void getActionsIsFalse()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setActions(actions);

        assertThat(signerForm.getActions(), is(not(new ArrayList<>())));

    }

    @Test
    public void getMailIsFalse()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setMail("peter@email.de");

        assertThat(signerForm.getMail(), is(not("felix@email.de")));

    }

    @Test
    public void getNameIsFalse()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setName("Ben");

        assertThat(signerForm.getName(), is(not("Lisa")));
    }

    @Test
    public void getRoutingOrderIsFalse()
    {
        SignerForm signerForm = new SignerForm();
        signerForm.setRoutingOrder("3");

        assertThat(signerForm.getRoutingOrder(), is(not("1")));
    }



}