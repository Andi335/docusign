package com.forcont.digsigproto.common.controller.requests.model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class CovenantMultiSignerForm
{
    private List<MultipartFile> document;
    private List<SignerForm> signers;
    private boolean parallelSigning;
    private String expirationDays;
    private String expirationReminderDays;

    public CovenantMultiSignerForm(List<MultipartFile> document,
                                   List<SignerForm> signers)
    {
        this.document = document;
        this.signers = signers;
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

    public Boolean getParallelSigning()
    {
        return parallelSigning;
    }

    public void setParallelSigning(Boolean parallelSigning)
    {
        this.parallelSigning = parallelSigning;
    }

    public List<MultipartFile> getDocument()
    {
        return document;
    }

    public void setDocument(List<MultipartFile> document)
    {
        this.document = document;
    }

    @ModelAttribute("signers")
    public SignerForm getNewSigner()
    {
        return new SignerForm();
    }

    public List<SignerForm> getSigners()
    {
        return signers;
    }

    public void setSigners(List<SignerForm> signers)
    {
        this.signers = signers;
    }

    @Override
    public String toString()
    {
        return "CovenantMultiSignerForm{" +
               "document=" + document +
               ", signers=" + signers.toString() +
               ", parallelSigning=" + parallelSigning +
               '}';
    }
}
