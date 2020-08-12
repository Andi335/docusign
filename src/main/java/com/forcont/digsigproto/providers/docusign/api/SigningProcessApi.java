package com.forcont.digsigproto.providers.docusign.api;

import com.docusign.esign.model.*;
import com.forcont.digsigproto.common.api.features.CovenantExpiration;
import com.forcont.digsigproto.common.api.Feature;
import com.forcont.digsigproto.common.api.features.RecipientSigningOrder;
import com.forcont.digsigproto.common.api.features.WebhookNotification;
import com.forcont.digsigproto.common.config.DefaultValues;
import com.forcont.digsigproto.common.model.*;
import com.forcont.digsigproto.providers.docusign.converter.PaperDigSigDocumentConverterImpl;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


class SigningProcessApi
{
    EnvelopeDefinition createEnvelopeDefinition(GenericEnvelopeDefinition genericEnvelopeDefinition, List<Feature> features)
    {
        EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();


        envelopeDefinition.setEmailSubject(DefaultValues.EMAIL_SUBJECT_SIGN_DOCUMENT);

        envelopeDefinition.setRecipients(genericEnvelopeDefinition.getRecipients());
        envelopeDefinition.setDocuments(genericEnvelopeDefinition.getDocuments());

        for (Feature feature : features)
        {
            if (feature instanceof CovenantExpiration)
            {
                envelopeDefinition.setNotification(this.convertFeature((CovenantExpiration) feature));
            }
            else if (feature instanceof WebhookNotification)
            {
                envelopeDefinition.setEventNotification(this.convertFeature((WebhookNotification) feature));
            }
        }

        envelopeDefinition.setStatus("sent");
        return envelopeDefinition;
    }

    EnvelopeTemplate createEnvelopeTemplateDefinition(GenericEnvelopeDefinition genericEnvelopeDefinition, String templateName, List<Feature> features)
    {
        EnvelopeTemplate envelopeTemplate = new EnvelopeTemplate();

        EnvelopeTemplateDefinition envelopeTemplateDefinition = new EnvelopeTemplateDefinition();
        envelopeTemplateDefinition.setName(templateName);
        envelopeTemplateDefinition.setShared("true"); //TODO
        envelopeTemplate.setEnvelopeTemplateDefinition(envelopeTemplateDefinition);

        envelopeTemplate.setEmailSubject(DefaultValues.EMAIL_SUBJECT_SIGN_DOCUMENT);

        envelopeTemplate.setRecipients(genericEnvelopeDefinition.getRecipients());
        envelopeTemplate.setDocuments(genericEnvelopeDefinition.getDocuments());

        for (Feature feature : features)
        {
            if (feature instanceof CovenantExpiration)
            {
                envelopeTemplate.setNotification(this.convertFeature((CovenantExpiration) feature));
            }
        }

        envelopeTemplate.setStatus("sent");
        return envelopeTemplate;
    }

    private SignPosition determineSignPosition(SignPosition signPosition, int signerCount, int actionCount)
    {
        if (signPosition == null)
        {
            Point tabPosition = new Point(DefaultValues.singingTabOrigin);
            tabPosition.translate(
                    signerCount * (int) DefaultValues.singingTabPadding.getX(),
                    actionCount * (int) DefaultValues.singingTabPadding.getY()
            );
            return new SignPosition(
                    tabPosition,
                    1
            );
        }
        else
        {
            return signPosition;
        }
    }

    GenericEnvelopeDefinition buildGenericEnvelopeDefinition(
            Map<RecipientDigSig, List<SigningAction>> signerMap,
            List<Feature> features
    )
    {
        PaperDigSigDocumentConverterImpl paperDigSigDocumentConverter = new PaperDigSigDocumentConverterImpl();
        List<Signer> signers = new ArrayList<>();

        boolean isSequentialSigning = false;
        List<RecipientDigSig> recipientDigSigList = new ArrayList<>(signerMap.keySet());
        for (Feature feature : features)
        {
            if (feature instanceof RecipientSigningOrder)
            {
                isSequentialSigning = true;
                recipientDigSigList = ((RecipientSigningOrder) feature).getRecipientOrder();
            }
        }



        for (int i = 0; i < recipientDigSigList.size(); i++)
        {
            RecipientDigSig recipientDigSig = recipientDigSigList.get(i);
            com.docusign.esign.model.Signer signer = new com.docusign.esign.model.Signer();
            signer.setRecipientId(String.valueOf(recipientDigSig.hashCode()));

            if (recipientDigSig.getRole() != null && recipientDigSig.getName() != null && recipientDigSig.getEmail() != null)
            {
                signer.setRoleName(recipientDigSig.getRole());
                signer.setName(recipientDigSig.getName());
                signer.setEmail(recipientDigSig.getEmail());
            }
            else if (recipientDigSig.getName() != null && recipientDigSig.getEmail() != null)
            {
                signer.setName(recipientDigSig.getName());
                signer.setEmail(recipientDigSig.getEmail());
            }
            else if (recipientDigSig.getRole() != null)
            {
                signer.setRoleName(recipientDigSig.getRole());
            }

            if (isSequentialSigning) {
                signer.setRoutingOrder(String.valueOf(i+1));
            }

            Tabs tabs = new Tabs();
            List<SignHere> signHeres = new ArrayList<>();
            List<View> views = new ArrayList<>();
            List<Approve> approves = new ArrayList<>();

            //for (SigningAction signingAction : signerMap.get(signerDigSig))
            for (int k = 0; k < signerMap.get(recipientDigSig).size(); k++)
            {
                SigningAction signingAction = signerMap.get(recipientDigSig).get(k);
                SignPosition signPosition = this.determineSignPosition(
                        signingAction.getSignPosition(),
                        i,
                        k
                );
                switch (signingAction.getAction())
                {
                    case SIGN:
                        SignHere signHere = new SignHere();
                        signHere.setXPosition(String.valueOf((int) signPosition.getLocation().getX()));
                        signHere.setYPosition(String.valueOf((int) signPosition.getLocation().getY()));
                        signHere.setPageNumber(String.valueOf(signPosition.getPageNumber()));
                        signHere.setDocumentId(signingAction.getPaper().getId());
                        signHeres.add(signHere);
                        break;
                    case VIEW:
                        // supplemental documents are not available on sandbox accounts by default
                        //https://stackoverflow.com/questions/54700825/demo-account-issue-account-does-not-have-permission-to-set-display-or-signer
                        //https://stackoverflow.com/questions/41230708/docusign-api-account-lacks-permissions-exception-when-creating-an-enveloppe-wi
                        //document.setDisplay("modal");
                        View view = new View();
                        view.setXPosition(String.valueOf((int) signPosition.getLocation().getX()));
                        view.setYPosition(String.valueOf((int) signPosition.getLocation().getY()));
                        view.setPageNumber(String.valueOf(signPosition.getPageNumber()));
                        view.setDocumentId(signingAction.getPaper().getId());
                        view.required("true");
                        views.add(view);
                        break;
                    case ACKN:
                        Approve approve = new Approve();
                        approve.setXPosition(String.valueOf((int) signPosition.getLocation().getX()));
                        approve.setYPosition(String.valueOf((int) signPosition.getLocation().getY()));
                        approve.setPageNumber(String.valueOf(signPosition.getPageNumber()));
                        approve.setDocumentId(signingAction.getPaper().getId());
                        approves.add(approve);
                        break;
                }
            }

            tabs.setSignHereTabs(signHeres);
            tabs.setViewTabs(views);
            tabs.setApproveTabs(approves);
            signer.setTabs(tabs);


            RecipientEmailNotification recipientEmailNotification = new RecipientEmailNotification();
            recipientEmailNotification.setSupportedLanguage(DefaultValues.LANGUAGE);
            recipientEmailNotification.setEmailSubject(signer.getName() + ": " + DefaultValues.EMAIL_SUBJECT_SIGN_DOCUMENT);
            signer.emailNotification(recipientEmailNotification);


            signers.add(signer);
        }
        Recipients recipients = new Recipients();
        recipients.setSigners(signers);

        Set<Document> documentSet = new HashSet<>();
        for (List<SigningAction> signingActions : signerMap.values())
        {
            documentSet.addAll(signingActions.stream().map(sa -> paperDigSigDocumentConverter.convertToProvider(sa.getPaper())).collect(Collectors.toSet()));
        }

        return new GenericEnvelopeDefinition(
                recipients,
                new ArrayList<>(documentSet)
        );

    }

    private Notification convertFeature(CovenantExpiration covenantExpiration)
    {
        Notification notification = new Notification();
        Expirations expirations = new Expirations();
        expirations.expireEnabled("true").expireAfter(String.valueOf(covenantExpiration.getExpiresAfterDay()));
        notification.setExpirations(expirations);
        Reminders reminders = new Reminders();
        reminders.reminderEnabled("true").reminderDelay(String.valueOf(covenantExpiration.getRemindDaysBeforeExpiration()));
        notification.setReminders(reminders);
        return notification;
    }

    private EventNotification convertFeature(WebhookNotification webhookNotification)
    {
        List<EnvelopeEvent> envelopeEvents = new ArrayList<>();
        envelopeEvents.add(new EnvelopeEvent().envelopeEventStatusCode("delivered"));
        envelopeEvents.add(new EnvelopeEvent().envelopeEventStatusCode("completed"));
        envelopeEvents.add(new EnvelopeEvent().envelopeEventStatusCode("declined"));
        envelopeEvents.add(new EnvelopeEvent().envelopeEventStatusCode("voided"));

        List<RecipientEvent> signerEvents = new ArrayList<>();
        signerEvents.add(new RecipientEvent().recipientEventStatusCode("Sent"));
        signerEvents.add(new RecipientEvent().recipientEventStatusCode("Delivered"));
        signerEvents.add(new RecipientEvent().recipientEventStatusCode("Completed"));
        signerEvents.add(new RecipientEvent().recipientEventStatusCode("Declined"));
        signerEvents.add(new RecipientEvent().recipientEventStatusCode("AuthenticationFailed"));
        signerEvents.add(new RecipientEvent().recipientEventStatusCode("AutoResponded"));

        EventNotification eventNotification = new EventNotification();
        eventNotification.setUrl(webhookNotification.getListenerUrl());
        eventNotification.loggingEnabled("true");
        eventNotification.setIncludeDocuments("false");
        eventNotification.setIncludeCertificateOfCompletion("true");

        eventNotification.setEnvelopeEvents(envelopeEvents);
        eventNotification.setRecipientEvents(signerEvents);

        return eventNotification;
    }

}
