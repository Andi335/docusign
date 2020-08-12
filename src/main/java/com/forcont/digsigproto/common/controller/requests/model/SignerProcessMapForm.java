package com.forcont.digsigproto.common.controller.requests.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public class SignerProcessMapForm
{
    private String processId;
    private List<SignerRoleMapForm> signerRoleMapForms;

    public SignerProcessMapForm()
    {

    }

    public SignerProcessMapForm(String processId, List<SignerRoleMapForm> signerRoleMapForms)
    {
        this.processId = processId;
        this.signerRoleMapForms = signerRoleMapForms;
    }

    @ModelAttribute("signerRoleMapForm")
    public SignerRoleMapForm newSignerRoleMapForm()
    {
        return new SignerRoleMapForm();
    }

    public String getProcessId()
    {
        return processId;
    }

    public void setProcessId(String processId)
    {
        this.processId = processId;
    }

    public List<SignerRoleMapForm> getSignerRoleMapForms()
    {
        return signerRoleMapForms;
    }

    public void setSignerRoleMapForms(List<SignerRoleMapForm> signerRoleMapForms)
    {
        this.signerRoleMapForms = signerRoleMapForms;
    }
}
