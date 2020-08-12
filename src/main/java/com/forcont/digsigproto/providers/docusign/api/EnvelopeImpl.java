package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.api.AccountsApi;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.*;
import com.forcont.digsigproto.common.api.CovenantApi;
import com.forcont.digsigproto.common.api.Feature;
import com.forcont.digsigproto.common.config.DefaultValues;
import com.forcont.digsigproto.common.model.*;
import com.forcont.digsigproto.common.model.RecipientDigSig;
import com.forcont.digsigproto.providers.docusign.converter.EnvelopeDigSigEnvelope;
import org.apache.commons.httpclient.HttpException;
import org.joda.time.LocalDate;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class EnvelopeImpl implements CovenantApi
{
    private final ApiClient apiClient;


    EnvelopeImpl(ApiClient apiClient)
    {
        this.apiClient = apiClient;
    }


    @Override
    public String listEnvelopes(Initiator initiator, String envelopeId) throws HttpException
    {
        EnvelopesApi envelopesApi = new EnvelopesApi(this.apiClient);
        String status;
        try
        {
            status = envelopesApi.getEnvelope(initiator.getId(), envelopeId).getStatus();
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }

        if (status.equals("sent"))
        {
            status = "in progress";
        }
        else
        {
            return status;
        }
        return status;
    }

    @Override
    public List<EnvelopeDigSig> getAllEnvelopesStatus(Initiator initiator) throws HttpException
    {
        EnvelopesApi envelopesApi = new EnvelopesApi(this.apiClient);
        EnvelopesApi.ListStatusChangesOptions options = envelopesApi.new ListStatusChangesOptions();
        LocalDate date = LocalDate.now().minusDays(30);
        options.setFromDate(date.toString("yyyy/MM/dd"));
        options.setUserName(initiator.getName());
        options.setEmail(initiator.getEmail());
        EnvelopesInformation results;
        try
        {
            results = envelopesApi.listStatusChanges(initiator.getId(), options);
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }

        EnvelopeDigSigEnvelope envelopeDigSigEnvelope = new EnvelopeDigSigEnvelope();

        List<EnvelopeDigSig> envelopeDigSigs = new ArrayList<>();
        for (int i = 0; i < results.getEnvelopes().size(); i++)
        {
            envelopeDigSigs.add(envelopeDigSigEnvelope.convertFromProvider(results.getEnvelopes().get(i)));
        }
        return envelopeDigSigs;
    }

    @Override
    public List<RecipientDigSig> getAllSignersStatus(Initiator initiator, String id) throws HttpException
    {
        EnvelopesApi envelopesApi = new EnvelopesApi(this.apiClient);

        List<RecipientDigSig> recipientDigSigs = new ArrayList<>();
        try
        {
            Recipients recipients = envelopesApi.listRecipients(initiator.getId(), id);
            for (int i = 0; i < recipients.getSigners().size(); i++)
            {
                String status = recipients.getSigners().get(i).getStatus();
                RecipientDigSig recipientDigSig = new RecipientDigSig(recipients.getSigners().get(i).getName(), recipients.getSigners().get(i).getEmail());
                if (status.equals("sent"))
                {
                    status = "in progress";
                }
                recipientDigSig.setId(recipients.getSigners().get(i).getRecipientId());
                recipientDigSig.setStatus(status);
                recipientDigSigs.add(recipientDigSig);
            }
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }
        return recipientDigSigs;
    }

    @Override
    public Map<String, String> sendSignatureRequest(
            Initiator initiator,
            Map<RecipientDigSig, List<SigningAction>> signerMap,
            List<Feature> features) throws
            IOException
    {
        EnvelopesApi envelopesApi = new EnvelopesApi(this.apiClient);
        SigningProcessApi signingProcessApi = new SigningProcessApi();
        GenericEnvelopeDefinition genericEnvelopeDefinition = signingProcessApi.buildGenericEnvelopeDefinition(signerMap, features);
        EnvelopeSummary results;
        EnvelopeDefinition envelopeDefinition = signingProcessApi.createEnvelopeDefinition(genericEnvelopeDefinition, features);

        try
        {
            results = envelopesApi.createEnvelope(initiator.getId(), envelopeDefinition);
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }

        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("envelopeId", results.getEnvelopeId());
        returnMap.put("url", "<empty>");
        return returnMap;
    }

    @Override
    public Map<String, String> startSigningWithBookmarks(Initiator initiator, Map<RecipientDigSig, List<SigningAction>> signerListMap)
    {
        return null;
    }


    @Override
    public void listQualifiedSignatureProviders(Initiator initiator) throws HttpException
    {
        AccountSignatureProviders accountSignatureProviders = null;
        try
        {
            accountSignatureProviders = new AccountsApi(this.apiClient).listSignatureProviders(initiator.getId());
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }
        System.out.println(accountSignatureProviders.toString());
    }

    @Override
    public void downloadEnvelopeDocument(Initiator initiator, String id, HttpServletResponse response) throws IOException
    {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=download.zip");
        response.setStatus(HttpServletResponse.SC_OK);

        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream()))
        {
            EnvelopesApi envelopesApi = new EnvelopesApi(this.apiClient);
            EnvelopeDocumentsResult docsList = envelopesApi.listDocuments(initiator.getId(), id);
            String status = envelopesApi.getEnvelope(initiator.getId(), id).getStatus();

            if (status.equals("completed"))
            {
                for (EnvelopeDocument doc : docsList.getEnvelopeDocuments()) // last index is certificate
                {
                    byte[] bytes = envelopesApi.getDocument(initiator.getId(), id, doc.getDocumentId()); // if docId = "certificate" -> certificate pdf
                    ZipEntry entry = new ZipEntry(doc.getName());
                    zippedOut.putNextEntry(entry);
                    StreamUtils.copy(bytes, zippedOut);
                    zippedOut.closeEntry();
                }
                zippedOut.finish();
            }
        }
        catch (ApiException e)
        {
            throw new HttpException(DefaultValues.EXCEPTION_DOCU_SIGN_API, e);
        }
    }
}