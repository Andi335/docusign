package com.forcont.digsigproto.common.controller.requests.model;

import com.forcont.digsigproto.common.model.Action;

public class ActionForm
{
    private String doc;
    private Action type;

    public ActionForm()
    {
    }

    public String getDoc()
    {
        return doc;
    }

    public void setDoc(String doc)
    {
        this.doc = doc;
    }

    public Action getType()
    {
        return type;
    }

    public void setType(Action type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ActionForm{" +
               "doc='" + doc + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}
