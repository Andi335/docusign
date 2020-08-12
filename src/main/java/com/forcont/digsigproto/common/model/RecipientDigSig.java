package com.forcont.digsigproto.common.model;

public class RecipientDigSig implements DigSigModel
{
    private String id;
    private String name;
    private String email;
    private String language;
    private String role;
    private String status;
    private int routingOrder = -1;



    public RecipientDigSig(String name, String email)
    {
        this.name = name;
        this.email = email;
    }


    public RecipientDigSig(String role)
    {
        this.role = role;
    }

    public RecipientDigSig(String name, String email, String role)
    {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
