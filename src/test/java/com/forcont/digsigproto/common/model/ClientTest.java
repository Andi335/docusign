package com.forcont.digsigproto.common.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class ClientTest
{
Client client = new Client("12345678", "87654321");

    @Test
    public void getId()
    {
        assertThat(client.getId(), is("12345678"));
    }

    @Test
    public void getSecret()
    {
        assertThat(client.getSecret(), is("87654321"));
    }

    @Test
    public void getIdIsFalse()
    {
        assertThat(client.getId(), is(not("12435678")));
    }

    @Test
    public void getSecretIsFalse()
    {
        assertThat(client.getSecret(), is(not("87665321")));
    }




}