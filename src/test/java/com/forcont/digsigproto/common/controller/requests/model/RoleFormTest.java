package com.forcont.digsigproto.common.controller.requests.model;

import com.forcont.digsigproto.common.model.Action;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class RoleFormTest
{
    private List<ActionForm> actions = new ArrayList<>();
    private List<ActionForm> actions2 = new ArrayList<>();

    @Before
    public void generateActions()
    {
        ActionForm actionForm1 = new ActionForm();
        actionForm1.setType(Action.SIGN);
        actionForm1.setDoc("123456789");

        ActionForm actionForm2 = new ActionForm();
        actionForm2.setDoc("987654321");
        actionForm2.setType(Action.ACKN);

        actions.add(actionForm1);
        actions.add(actionForm2);
        actions2.add(actionForm1);
    }

    @Test
    public void getActions()
    {
        RoleForm roleForm = new RoleForm("Signer", actions);
        assertThat(roleForm.getActions(), is(actions));
    }

    @Test
    public void getRole()
    {
        RoleForm roleForm = new RoleForm("Signer", actions);
        assertThat(roleForm.getRole(), is("Signer"));
    }

    @Test
    public void getActionsIsFalse()
    {
        RoleForm roleForm = new RoleForm("Signer", actions);
        assertThat(roleForm.getActions(), is(not(actions2)));
    }

    @Test
    public void getRoleIsFalse()
    {
        RoleForm roleForm = new RoleForm("Signer", actions);
        assertThat(roleForm.getRole(), is(not("Viewer")));
    }
}