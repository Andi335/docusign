package com.forcont.digsigproto.common.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class PaperTest
{

    @Test
    public void testGetId()
    {
        Paper paper = new Paper("123456789", null,null);
        assertThat(paper.getId(),is("123456789"));
    }

    @Test
    public void testGetIdIsFalse()
    {
        Paper paper = new Paper("123456789", null,null);
        assertThat(paper.getId(),is(not("123456463")));
    }

    @Test
    public void testGetTitle()
    {
        Paper paper = new Paper(null, "Signatur", null);
        assertThat(paper.getTitle(), is("Signatur"));
    }

    @Test
    public void testGetTitleIsFalse()
    {
        Paper paper = new Paper(null, "Signatur", null);
        assertThat(paper.getTitle(), is(not("Unterschrift")));
    }

    @Test
    public void testGetContent()
    {
        byte[] bytes = { 72, 101, 108, 108, 111, 32, 63, 63, 63,
                63, 63, 33 };
        Paper paper = new Paper(null, null, bytes );
        assertArrayEquals( new byte[] { 72, 101, 108, 108, 111, 32, 63, 63, 63,
                63, 63, 33 }, paper.getContent());
    }

    @Test
    public void testGetUri()
    {
        Paper paper = new Paper(null, null, "/paper",null,null);
        assertThat(paper.getUri(), is("/paper"));
    }

    @Test
    public void testGetUriIsFalse()
    {
        Paper paper = new Paper(null, null, "/signing/paper",null,null);
        assertThat(paper.getUri(), is(not("/paper")));
    }

    @Test
    public void getPages()
    {
        Paper paper = new Paper(null, null, null, 2, null);
        assertThat(paper.getPages(), is(2));
    }

    @Test
    public void getPagesIsFalse()
    {
        Paper paper = new Paper(null, null, null, 2, null);
        assertThat(paper.getPages(), is(not(1)));
    }
}