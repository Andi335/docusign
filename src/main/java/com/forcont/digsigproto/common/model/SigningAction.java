package com.forcont.digsigproto.common.model;

import java.awt.*;

public class SigningAction
{
    private final Paper paper;
    private final Action action;
    private SignPosition signPosition;

    public SigningAction(Paper paper, Action action)
    {
        this.paper = paper;
        this.action = action;
    }

    public SigningAction(Paper paper, Action action, SignPosition signPosition)
    {
        this.paper = paper;
        this.action = action;
        this.signPosition = signPosition;
    }

    public SigningAction(Paper paper, Action action, int left, int top, int pageNumber)
    {
        this.paper = paper;
        this.action = action;
        this.signPosition = new SignPosition(new Point(left, top), pageNumber);
    }

    public Paper getPaper()
    {
        return paper;
    }

    public Action getAction()
    {
        return action;
    }

    public SignPosition getSignPosition()
    {
        return signPosition;
    }

    public void setSignPosition(SignPosition signPosition)
    {
        this.signPosition = signPosition;
    }

    public void setSignPosition(int left, int top, int pageNumber) {
        this.signPosition = new SignPosition(new Point(left, top), pageNumber);
    }

}
