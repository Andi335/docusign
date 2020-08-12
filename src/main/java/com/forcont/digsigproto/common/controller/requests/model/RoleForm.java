package com.forcont.digsigproto.common.controller.requests.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public class RoleForm
{
    private String role;
    private List<ActionForm> actions;

    public RoleForm() {

    }

    public RoleForm(String role, List<ActionForm> actions)
    {
        this.role = role;
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


    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "RoleForm{" +
               "role='" + role + '\'' +
               ", actions=" + actions +
               '}';
    }
}
