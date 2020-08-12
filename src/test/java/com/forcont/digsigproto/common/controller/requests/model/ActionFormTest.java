package com.forcont.digsigproto.common.controller.requests.model;

import com.forcont.digsigproto.common.model.Action;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


public class ActionFormTest
{

    @Test
    public void getDoc()
    {
        ActionForm actionForm = new ActionForm();
        actionForm.setDoc("12345678");

        assertThat(actionForm.getDoc(),is("12345678") );
    }

    @Test
    public void getType()
    {
        ActionForm actionForm = new ActionForm();
        actionForm.setType(Action.SIGN);

        assertThat(actionForm.getType(),is(Action.SIGN));
    }

    @Test
    public void getDocIsFalse()
    {
        ActionForm actionForm = new ActionForm();
        actionForm.setDoc("12345678");

        assertThat(actionForm.getDoc(),is(not("12345699")) );
    }

    @Test
    public void getTypeIsFalse()
    {
        ActionForm actionForm = new ActionForm();
        actionForm.setType(Action.SIGN);

        assertThat(actionForm.getType(),is(not(Action.ACKN)));
    }
}