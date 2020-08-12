package com.forcont.digsigproto.common.model;

/**
 * An person who is bound to an DocuSign account
 * which associated with given client id and secret.
 * Therefore an Initiator can start and manage signing processes.
 */
public class Initiator implements DigSigModel
{
    private final String id;
    private final String name;
    private final String email;

    /**
     * @param id    Account id
     * @param name  Full name
     * @param email eMail address
     */
    public Initiator(String id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return "Initiator{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
