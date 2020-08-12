package com.forcont.digsigproto.common.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
public class InitiatorTest
{

    Initiator initiator = new Initiator("12345678","Thomas", "thomas@email.de");
    @Test
    public void getId()
    {
        assertThat(initiator.getId(),is("12345678"));
    }

    @Test
    public void getName()
    {
        assertThat(initiator.getName(),is("Thomas"));
    }

    @Test
    public void getEmail()
    {
        assertThat(initiator.getEmail(), is("thomas@email.de"));
    }

    @Test
    public void toString1()
    {
        assertThat("Initiator{" +
                   "id='" + initiator.getId() + '\'' +
                   ", name='" + initiator.getName() + '\'' +
                   ", email='" + initiator.getEmail() + '\'' +
                   '}',is("Initiator{" +
                               "id='" + "12345678" + '\'' +
                               ", name='" + "Thomas" + '\'' +
                               ", email='" + "thomas@email.de" + '\'' +
                               '}'));
    }

    @Test
    public void getIdIsFalse()
    {
        assertThat(initiator.getId(),is(not("12332678")));
    }

    @Test
    public void getNameIsFalse()
    {
        assertThat(initiator.getName(),is(not("Felix")));
    }

    @Test
    public void getEmailIsFalse()
    {
        assertThat(initiator.getEmail(), is(not("felix@email.de")));
    }

    @Test
    public void toStringIsFalse()
    {
        assertThat("Initiator{" +
                   "id='" + initiator.getId() + '\'' +
                   ", name='" + initiator.getName() + '\'' +
                   ", email='" + initiator.getEmail() + '\'' +
                   '}',is(not("Initiator{" +
                          "id='" + "12325678" + '\'' +
                          ", name='" + "Felix" + '\'' +
                          ", email='" + "felix@email.de" + '\'' +
                          '}')));
    }

}