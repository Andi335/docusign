package com.forcont.digsigproto.common.sandbox.signing;


import com.forcont.digsigproto.common.api.AbstractBaseApi;
import com.forcont.digsigproto.common.api.Feature;
import com.forcont.digsigproto.common.api.features.CovenantExpiration;
import com.forcont.digsigproto.common.api.features.RecipientSigningOrder;
import com.forcont.digsigproto.common.api.features.WebhookNotification;
import com.forcont.digsigproto.common.config.EndPoints;
import com.forcont.digsigproto.common.controller.accessors.security.AuthGrantCodeAccessor;
import com.forcont.digsigproto.common.controller.bookmarks.Bookmarks;
import com.forcont.digsigproto.common.controller.requests.model.CovenantMultiSignerForm;
import com.forcont.digsigproto.common.controller.requests.model.SignerProcessMapForm;
import com.forcont.digsigproto.common.controller.requests.model.SignerRoleMapForm;
import com.forcont.digsigproto.common.model.Initiator;
import com.forcont.digsigproto.common.model.Paper;
import com.forcont.digsigproto.common.model.RecipientDigSig;
import com.forcont.digsigproto.common.model.SigningAction;
import com.forcont.digsigproto.common.sandbox.SandboxUtils;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class SigningProcessSandbox
{
    private final AbstractBaseApi baseApi;
    private final AuthGrantCodeAccessor authGrantCodeAccessor;
    private final EndPoints endPoints;
    private final Bookmarks bookmarks;

    @Autowired
    public SigningProcessSandbox(AbstractBaseApi baseApi, AuthGrantCodeAccessor authGrantCodeAccessor, EndPoints endPoints, Bookmarks bookmarks)
    {
        this.baseApi = baseApi;
        this.authGrantCodeAccessor = authGrantCodeAccessor;
        this.endPoints = endPoints;
        this.bookmarks = bookmarks;
    }

    public Initiator getInitiator()
    {
        return (Initiator) this.authGrantCodeAccessor.getAuthUser();
    }

    public Map<String, String> signMultipleDocumentsByMultipleUsers(CovenantMultiSignerForm covenantMultiSignerForm) throws IOException
    {
        Map<String, Paper> paperMap = SandboxUtils.converMultipartFilesToPaperMap(covenantMultiSignerForm.getDocument());

        Map<RecipientDigSig, List<SigningAction>> signerMap = SandboxUtils.convertSignerFormsToSignerMap(covenantMultiSignerForm.getSigners(), paperMap);

        List<Feature> features = new ArrayList<>();
        features.add(
                new CovenantExpiration(
                        Integer.parseInt(covenantMultiSignerForm.getExpirationDays()),
                        Integer.parseInt(covenantMultiSignerForm.getExpirationReminderDays())
                )
        );
        features.add(
                new WebhookNotification(
                        this.endPoints.getWithContextPath(EndPoints.Api.WEBHOOK_LISTENER)
                )
        );
        if (!covenantMultiSignerForm.getParallelSigning())
        {
            features.add(
                    new RecipientSigningOrder(new ArrayList<>(signerMap.keySet()))
            );
        }

        return this.baseApi.getCovenantApi().sendSignatureRequest(this.getInitiator(), signerMap, features);
    }

    public void listSignatureProviders()
    {
        Initiator initiator = (Initiator) authGrantCodeAccessor.getAuthUser();
        try
        {
            this.baseApi.getCovenantApi().listQualifiedSignatureProviders(initiator);
        }
        catch (HttpException e)
        {
            e.printStackTrace();
        }
    }

    public Map<String, String> signingWithBookMarks(SignerProcessMapForm signerProcessMapForm)
    {
        List<RecipientDigSig> recipientDigSigs = signerProcessMapForm.getSignerRoleMapForms().stream().map(
                SigningProcessSandbox::convertSignerRoleMapFormToSigner).collect(
                Collectors.toList());
        Map<String, List<SigningAction>> signerMap = bookmarks.cache.get(signerProcessMapForm.getProcessId());
        Map<RecipientDigSig, List<SigningAction>> signerListMap = new HashMap<>();

        Map<String, String> returnMap;
        Initiator initiator = (Initiator) this.authGrantCodeAccessor.getAuthUser();
        for (int i = 0; i < signerMap.size(); i++)
        {
            for (RecipientDigSig recipientDigSig : recipientDigSigs)
            {
                if (signerMap.keySet().contains(recipientDigSig.getRole()))
                {
                    signerListMap.put(recipientDigSig, signerMap.get(recipientDigSig.getRole()));
                }
            }
        }

        try
        {
            returnMap = this.baseApi.getCovenantApi().sendSignatureRequest(initiator, signerListMap, new ArrayList<>());
        }
        catch (IOException e)
        {
            returnMap = null;
            e.printStackTrace();
        }
        return returnMap;
    }

    private static RecipientDigSig convertSignerRoleMapFormToSigner(SignerRoleMapForm signerRoleMapForm)
    {
        return new RecipientDigSig(
                signerRoleMapForm.getName(),
                signerRoleMapForm.getEmail(),
                signerRoleMapForm.getRole()
        );
    }

}
