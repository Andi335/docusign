package com.forcont.digsigproto.common.model;

import java.util.List;

public class TemplateDigSig implements DigSigModel
{
    private String id;
    private String templateName;
    private String templateResultsName;
    private String description;
    private List<RecipientDigSig> recipientDigSigs;
    private List<Paper> papers;


    public TemplateDigSig () {

    }

    public TemplateDigSig(String id, String templateResultName)
    {
        this.id = id;
        this.templateResultsName = templateResultName;
    }

    public TemplateDigSig(String id, String templateResultName, List<RecipientDigSig> recipientDigSigs, List<Paper> papers)
    {
        this.id = id;
        this.templateResultsName = templateResultName;
        this.recipientDigSigs = recipientDigSigs;
        this.papers = papers;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getTemplateResultsName()
    {
        return templateResultsName;
    }

    public void setTemplateResultsName(String templateResultsName)
    {
        this.templateResultsName = templateResultsName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<RecipientDigSig> getRecipientDigSigs()
    {
        return recipientDigSigs;
    }

    public void setRecipientDigSigs(List<RecipientDigSig> recipientDigSigs)
    {
        this.recipientDigSigs = recipientDigSigs;
    }

    public List<Paper> getPapers()
    {
        return papers;
    }

    public void setPapers(List<Paper> papers)
    {
        this.papers = papers;
    }
}
