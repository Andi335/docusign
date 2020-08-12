package com.forcont.digsigproto.common.model;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SigningActionTest
{

    RecipientDigSig recipientDigSig = new RecipientDigSig("Alex", "alex@email.de");
    Paper paper = new Paper("12345678", "DocuSign", new byte[]{32, 2, 45});
    SignPosition signPosition = new SignPosition(new Point(50,50),2);
    SigningAction signingAction = new SigningAction(paper, Action.SIGN, signPosition);

    @Test
    public void getPaper()
    {
        assertThat(signingAction.getPaper(), is(paper));
    }

    @Test
    public void getAction()
    {
        assertThat(signingAction.getAction(), is(Action.SIGN));
    }

    @Test
    public void getPaperIsFalse()
    {
        assertThat(signingAction.getPaper(), is(not(new Paper("12345688", "Arbeitsvertrag", new byte[]{72, 101, 108}))));
    }

    @Test
    public void getActionIsFalse()
    {
        assertThat(signingAction.getAction(), is(not(Action.ACKN)));
    }

    @Test
    public void getSignPosition(){
        assertThat(signingAction.getSignPosition(), is(signPosition));
    }

    @Test
    public void getSignPositionIsFalse(){
        assertThat(signingAction.getSignPosition(),is(not(new SignPosition(new Point(30,30),1))));
    }


}