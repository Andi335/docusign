package com.forcont.digsigproto.common.controller.requests.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DocumentUploadForm
{
    private List<MultipartFile> document;

    public DocumentUploadForm(List<MultipartFile> document)
    {
        this.document = document;
    }

    public List<MultipartFile> getDocument()
    {
        return document;
    }

    public void setDocument(List<MultipartFile> document)
    {
        this.document = document;
    }
}
