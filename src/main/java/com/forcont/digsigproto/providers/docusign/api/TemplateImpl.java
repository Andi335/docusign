package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.api.TemplatesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.Document;
import com.docusign.esign.model.EnvelopeTemplateResult;
import com.docusign.esign.model.Signer;
import com.docusign.esign.model.TemplateSummary;
import com.forcont.digsigproto.common.api.Feature;
import com.forcont.digsigproto.common.api.SubmissionApi;
import com.forcont.digsigproto.common.config.DefaultValues;
import com.forcont.digsigproto.common.model.*;
import com.forcont.digsigproto.providers.docusign.converter.PaperDigSigDocumentConverterImpl;
import com.forcont.digsigproto.providers.docusign.converter.SignerDigSigSignerConverterImpl;
import com.forcont.digsigproto.providers.docusign.converter.TemplateDigSigTemplateImpl;
import org.apache.commons.httpclient.HttpException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateImpl implements SubmissionApi
{
    private final ApiClient apiClient;

    TemplateImpl(ApiClient apiClient)
    {
        this.apiClient = apiClient;
    }

    @Override
    public Map<String, String> createTemplate(
            Initiator initiator,
            String templateName,
            Map<RecipientDigSig, List<SigningAction>> signerMap,
            List<Feature> features
    ) throws HttpException
    {
        TemplatesApi templateApi = new TemplatesApi(this.apiClient);
        SigningProcessApi signingProcessApi = new SigningProcessApi();
        GenericEnvelopeDefinition genericEnvelopeDefinition = signingProcessApi.buildGenericEnvelopeDefinition(signerMap, features);
        TemplateSummary results;
        try
        {
            results = templateApi.createTemplate(initiator.getId(), signingProcessApi.createEnvelopeTemplateDefinition(genericEnvelopeDefinition, templateName, features));
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("templateId", results.getTemplateId());
        returnMap.put("url", "<empty>");

        return returnMap;
    }

    @Override
    public List<TemplateDigSig> getTemplates(Initiator initiator) throws HttpException
    {
        CreateTemplateApi createTemplateApi = new CreateTemplateApi(this.apiClient);
        List<TemplateDigSig> templateDigSigs = new ArrayList<>();
        TemplateDigSigTemplateImpl templateDigSigTemplate = new TemplateDigSigTemplateImpl();
        SignerDigSigSignerConverterImpl signerDigSigSignerConverter = new SignerDigSigSignerConverterImpl();
        PaperDigSigDocumentConverterImpl paperDigSigDocumentConverter = new PaperDigSigDocumentConverterImpl();
        try
        {
            for (EnvelopeTemplateResult envelopeTemplateResult : createTemplateApi.getTemplates(initiator))
            {
                List<RecipientDigSig> recipientDigSigs = new ArrayList<>();
                List<Paper> papers = new ArrayList<>();
                if (envelopeTemplateResult.getRecipients() != null)
                {
                    for (Signer signer : envelopeTemplateResult.getRecipients().getSigners())
                    {
                        recipientDigSigs.add(signerDigSigSignerConverter.convertFromProvider(signer));
                    }
                }
                for (Document document : envelopeTemplateResult.getDocuments())
                {
                    papers.add(paperDigSigDocumentConverter.convertFromProvider(document));
                }
                TemplateDigSig templateDigSig = templateDigSigTemplate.convertFromProvider(envelopeTemplateResult);
                templateDigSig.setRecipientDigSigs(recipientDigSigs);
                templateDigSig.setPapers(papers);
                templateDigSigs.add(templateDigSig);
            }

        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }
        return templateDigSigs;
    }

    @Override
    public String useTemplate(Initiator initiator, List<RecipientDigSig> recipientDigSigs, String id) throws HttpException
    {
        SigningFromTemplateApi signingFromTemplateApi = new SigningFromTemplateApi(this.apiClient);
        String envelopeId;
        try
        {
            envelopeId = signingFromTemplateApi.createEnvelopeFromTemplate(initiator, recipientDigSigs, id);
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }

        return envelopeId;
    }
}
