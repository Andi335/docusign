package com.forcont.digsigproto.common.controller.requests.model;

public class SignerRoleMapForm
{
    private String role;
    private String name;
    private String email;

    public SignerRoleMapForm()
    {
    }

    public SignerRoleMapForm(String role, String name, String email)
    {
        this.role = role;
        this.name = name;
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
