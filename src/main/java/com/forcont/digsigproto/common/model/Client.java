package com.forcont.digsigproto.common.model;

/**
 * Model class providing authentication infos
 */
public class Client implements DigSigModel
{
    /**
     * Client id or integrator key, obtained by signing provider.
     */
    private final String id;

    /**
     * Secret key, obtained by signing provider.
     */
    private final String secret;

    public Client(String id, String secret)
    {
        this.secret = secret;
        this.id = id;
    }


    public String getId()
    {
        return id;
    }

    public String getSecret()
    {
        return secret;
    }
}
