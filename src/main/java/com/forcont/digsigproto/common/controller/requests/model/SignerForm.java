package com.forcont.digsigproto.common.controller.requests.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public class SignerForm
{
    private String mail;
    private String name;
    private String routingOrder;
    private List<ActionForm> actions;

    public SignerForm()
    {

    }

    public SignerForm(String mail, String name, String routingOrder, List<ActionForm> actions)
    {
        this.mail = mail;
        this.name = name;
        this.routingOrder = routingOrder;
        this.actions = actions;
    }

    public SignerForm(String mail, String name, List<ActionForm> actions)
    {
        this.mail = mail;
        this.name = name;
        this.actions = actions;
    }

    @ModelAttribute("actions")
    public ActionForm getNewAction()
    {
        return new ActionForm();
    }

    public List<ActionForm> getActions()
    {
        return actions;
    }

    public void setActions(List<ActionForm> actions)
    {
        this.actions = actions;
    }


    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRoutingOrder()
    {
        return routingOrder;
    }

    public void setRoutingOrder(String routingOrder)
    {
        this.routingOrder = routingOrder;
    }

    @Override
    public String toString()
    {
        return "SignerForm{" +
               "mail='" + mail + '\'' +
               ", name='" + name + '\'' +
               ", actions=" + actions.toString() +
               '}';
    }
}