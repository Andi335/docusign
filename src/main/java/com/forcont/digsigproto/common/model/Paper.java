package com.forcont.digsigproto.common.model;

/**
 * Common model for documents.
 */
public class Paper implements DigSigModel
{

    private final String id;
    private final String title;

    private  byte[] content;
    private String uri;
    private Integer pages;


    /**
     * @param id    Document id.
     * @param title Document name.
     * @param uri   Link to the document content.
     * @param pages Number of pages.
     */
    public Paper(String id, String title, String uri, Integer pages, byte[] content)
    {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.pages = pages;
        this.content = content;
    }

    public Paper(String id, String title, String uri, Integer pages)
    {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.pages = pages;
    }

    public Paper(String id, String title, byte[] content)
    {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public byte[] getContent()
    {
        return this.content;
    }

    public String getUri()
    {
        return uri;
    }

    public Integer getPages()
    {
        return pages;
    }
}
