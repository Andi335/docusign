package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.*;
import com.forcont.digsigproto.common.model.Initiator;
import com.forcont.digsigproto.common.model.RecipientDigSig;


import java.util.ArrayList;
import java.util.List;

public class SigningFromTemplateApi
{
    private String message;
    private final EnvelopesApi envelopesApi;



    SigningFromTemplateApi(ApiClient apiClient)
    {
        this.envelopesApi = new EnvelopesApi(apiClient);

    }


    String createEnvelopeFromTemplate(Initiator initiator, List<RecipientDigSig> recipientDigSigs, String id) throws ApiException
    {
        EnvelopeDefinition envelope = createEnvelope(recipientDigSigs, id);
        EnvelopeSummary result = envelopesApi.createEnvelope(initiator.getId(), envelope);
        // process result
        setMessage("The envelope has been created and sent!<br/>Envelope ID " + result.getEnvelopeId() + ".");
        return result.getEnvelopeId();
    }


    private EnvelopeDefinition createEnvelope(List<RecipientDigSig> recipientDigSigs, String id)
    {

        List<TemplateRole> templateRoles = new ArrayList<>();

        EnvelopeDefinition env = new EnvelopeDefinition();
        env.setTemplateId(id);

        for (RecipientDigSig recipientDigSig : recipientDigSigs)
        {
            TemplateRole signer1 = new TemplateRole();
            signer1.setRoleName(recipientDigSig.getRole());
            signer1.setName(recipientDigSig.getName());
            signer1.setEmail(recipientDigSig.getEmail());
            templateRoles.add(signer1);
        }


//        Notification notification = new Notification();
//        Expirations expirations = new Expirations();
//        expirations.expireEnabled("true").expireAfter(multipleSigningAction.getExpirationDays());
//        notification.setExpirations(expirations);
//
//        Reminders reminders = new Reminders();
//        reminders.reminderEnabled("true").reminderDelay(multipleSigningAction.getExpirationDays());
//
//        notification.setReminders(reminders);
//        env.setNotification(notification);




        env.setTemplateRoles(templateRoles);
        env.setStatus("sent");
        return env;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }


}
