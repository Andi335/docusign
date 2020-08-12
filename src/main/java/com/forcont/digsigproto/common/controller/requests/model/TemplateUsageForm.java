package com.forcont.digsigproto.common.controller.requests.model;

import java.util.List;

public class TemplateUsageForm
{
    private String templateId;
    private List<String> role;
    private List<String> name;
    private List<String> email;

    public TemplateUsageForm(String templateId, List<String> role, List<String> name, List<String> email)
    {
        this.templateId = templateId;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    public List<String> getName()
    {
        return name;
    }

    public void setName(List<String> name)
    {
        this.name = name;
    }

    public List<String> getEmail()
    {
        return email;
    }

    public void setEmail(List<String> email)
    {
        this.email = email;
    }

    public String getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId(String templateId)
    {
        this.templateId = templateId;
    }

    public List<String> getRole()
    {
        return role;
    }

    public void setRole(List<String> role)
    {
        this.role = role;
    }

    private String listToString(List<String> list){
        String string = "";
        for (String el : list) {
            string = string.concat(", ").concat(el);
        }
        return string;
    }

    @Override
    public String toString()
    {
        return "SigningSetupTemplate{" +
               "templateId='" + templateId + '\'' +
               ", role=" + this.listToString(role) +
               ", name=" + this.listToString(name) +
               ", email=" + this.listToString(email) +
               '}';
    }
}
