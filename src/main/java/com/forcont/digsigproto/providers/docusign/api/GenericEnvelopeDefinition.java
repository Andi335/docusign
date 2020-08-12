package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.model.Document;
import com.docusign.esign.model.Recipients;

import java.util.List;

class GenericEnvelopeDefinition
{
    private final Recipients recipients;
    private final List<Document> documents;

    GenericEnvelopeDefinition(Recipients recipients, List<Document> documents)
    {
        this.recipients = recipients;
        this.documents = documents;
    }

    Recipients getRecipients()
    {
        return recipients;
    }

    List<Document> getDocuments()
    {
        return documents;
    }
}
