package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.api.TemplatesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.EnvelopeTemplateResult;
import com.docusign.esign.model.EnvelopeTemplateResults;
import com.forcont.digsigproto.common.model.Initiator;

import java.util.List;


class CreateTemplateApi
{
    private final TemplatesApi templatesApi;

    CreateTemplateApi(ApiClient apiClient)
    {
        this.templatesApi = new TemplatesApi(apiClient);
    }

    List<EnvelopeTemplateResult> getTemplates(Initiator initiator) throws ApiException
    {
        TemplatesApi.ListTemplatesOptions listTemplatesOptions = this.templatesApi.new ListTemplatesOptions();
        listTemplatesOptions.setUserFilter("owned_by_me");
        listTemplatesOptions.setInclude("recipients,documents");
        EnvelopeTemplateResults envelopeTemplateResults = this.templatesApi.listTemplates(initiator.getId(), listTemplatesOptions);
        return envelopeTemplateResults.getEnvelopeTemplates();
    }
}
