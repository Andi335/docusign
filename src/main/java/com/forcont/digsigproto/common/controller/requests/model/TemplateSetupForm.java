package com.forcont.digsigproto.common.controller.requests.model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class TemplateSetupForm
{


    private String templateName;
    private List<MultipartFile> document;
    private List<RoleForm> roles;
    private boolean parallelSigning;
    private String expirationDays;
    private String expirationReminderDays;

    public TemplateSetupForm(String templateName, List<MultipartFile> document,
                             List<RoleForm> roles, boolean parallelSigning, String expirationDays, String expirationReminderDays)
    {
        this.templateName = templateName;
        this.document = document;
        this.roles = roles;
        this.parallelSigning = parallelSigning;
        this.expirationDays = expirationDays;
        this.expirationReminderDays = expirationReminderDays;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public List<MultipartFile> getDocument()
    {
        return document;
    }

    public void setDocument(List<MultipartFile> document)
    {
        this.document = document;
    }

    public boolean isParallelSigning()
    {
        return parallelSigning;
    }

    public void setParallelSigning(boolean parallelSigning)
    {
        this.parallelSigning = parallelSigning;
    }

    public String getExpirationDays()
    {
        return expirationDays;
    }

    public void setExpirationDays(String expirationDays)
    {
        this.expirationDays = expirationDays;
    }

    public String getExpirationReminderDays()
    {
        return expirationReminderDays;
    }

    public void setExpirationReminderDays(String expirationReminderDays)
    {
        this.expirationReminderDays = expirationReminderDays;
    }

    @ModelAttribute("roles")
    public RoleForm getNewRole()
    {
        return new RoleForm();
    }

    public List<RoleForm> getRoles()
    {
        return roles;
    }

    public void setRoles(List<RoleForm> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return "CovenantMultiSignerForm{" +
               "document=" + document +
               ", signers=" + roles.toString() +
               ", parallelSigning=" + parallelSigning +
               '}';
    }
}
