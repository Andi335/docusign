package com.forcont.digsigproto.common.sandbox.signing;

import com.forcont.digsigproto.common.model.RecipientDigSig;
import com.forcont.digsigproto.common.model.WebhookNotification;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParseWebhooksStatusTest
{

    private List<WebhookNotification> webhookList = new ArrayList<>();

    @Before
    public void generateWebhooks()
    {
        WebhookNotification webhookNotification = new WebhookNotification();
        List<RecipientDigSig> recipientDigSigs = new ArrayList<>();

        RecipientDigSig recipientDigSig1 = new RecipientDigSig("Gene Davidson", "ZHMxNDUxNjM5MTU4MTAw@mailinator.com");
        recipientDigSig1.setStatus("Delivered");

        RecipientDigSig recipientDigSig2 = new RecipientDigSig("Paul Dupuis", "ZHMxNDUxNjM5MTU4MTAw@mailinator.com");
        recipientDigSig2.setStatus("Created");

        recipientDigSigs.add(recipientDigSig1);
        recipientDigSigs.add(recipientDigSig2);


        webhookNotification.setDateTime(DateTime.parse("2016-01-01T01:06:27.1254729"));
        webhookNotification.setEnvelopeId("7a5828cb-2fe2-4c30-aa19-330a24fe780e");
        webhookNotification.setEnvelopeStatus("Sent");
        webhookNotification.setRecipientDigSigs(recipientDigSigs);

        webhookList.add(webhookNotification);
    }

    @Test
    public void parseSignerStatusXML() throws ParserConfigurationException, IOException, org.xml.sax.SAXException
    {
        String xml = "<DocuSignEnvelopeInformation xmlns=\"http://www.docusign.net/API/3.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                     "<EnvelopeStatus>\n" +
                     "<RecipientStatuses>\n" +
                     "<RecipientStatus>\n" +
                     "<Type>Signer</Type>\n" +
                     "<Email>ZHMxNDUxNjM5MTU4MTAw@mailinator.com</Email>\n" +
                     "<UserName>Gene Davidson</UserName>\n" +
                     "<RoutingOrder>1</RoutingOrder>\n" +
                     "<Sent>2016-01-01T01:06:06.947</Sent>\n" +
                     "<Delivered>2016-01-01T01:06:23.367</Delivered>\n" +
                     "<DeclineReason xsi:nil=\"true\"/>\n" +
                     "<Status>Delivered</Status>\n" +
                     "<RecipientIPAddress>87.68.55.196</RecipientIPAddress>\n" +
                     "<CustomFields/>\n" +
                     "<TabStatuses>\n" +
                     "<TabStatus>\n" +
                     "<TabType>SignHere</TabType>\n" +
                     "<Status>Active</Status>\n" +
                     "<XPosition>272</XPosition>\n" +
                     "<YPosition>772</YPosition>\n" +
                     "<TabLabel>signer1sig</TabLabel>\n" +
                     "<TabName>Please sign here</TabName>\n" +
                     "<TabValue/>\n" +
                     "<DocumentID>1</DocumentID>\n" +
                     "<PageNumber>3</PageNumber>\n" +
                     "</TabStatus>\n" +
                     "<TabStatus>\n" +
                     "<TabType>FullName</TabType>\n" +
                     "<Status>Active</Status>\n" +
                     "<XPosition>281</XPosition>\n" +
                     "<YPosition>916</YPosition>\n" +
                     "<TabLabel>Full Name</TabLabel>\n" +
                     "<TabName>Full Name</TabName>\n" +
                     "<TabValue/>\n" +
                     "<DocumentID>1</DocumentID>\n" +
                     "<PageNumber>3</PageNumber>\n" +
                     "</TabStatus>\n" +
                     "<TabStatus>\n" +
                     "<TabType>DateSigned</TabType>\n" +
                     "<Status>Active</Status>\n" +
                     "<XPosition>281</XPosition>\n" +
                     "<YPosition>1041</YPosition>\n" +
                     "<TabLabel>Company</TabLabel>\n" +
                     "<TabName>Date Signed</TabName>\n" +
                     "<TabValue/>\n" +
                     "<DocumentID>1</DocumentID>\n" +
                     "<PageNumber>3</PageNumber>\n" +
                     "</TabStatus>\n" +
                     "<TabStatus>\n" +
                     "<TabType>Custom</TabType>\n" +
                     "<Status>Active</Status>\n" +
                     "<XPosition>281</XPosition>\n" +
                     "<YPosition>975</YPosition>\n" +
                     "<TabLabel>Company</TabLabel>\n" +
                     "<TabName>Company</TabName>\n" +
                     "<TabValue/>\n" +
                     "<DocumentID>1</DocumentID>\n" +
                     "<PageNumber>3</PageNumber>\n" +
                     "<CustomTabType>Text</CustomTabType>\n" +
                     "</TabStatus>\n" +
                     "</TabStatuses>\n" +
                     "<AccountStatus>Active</AccountStatus>\n" +
                     "<EsignAgreementInformation>\n" +
                     "<AccountEsignId>eaaec8c5-3f58-4992-ba83-fec7b875aea4</AccountEsignId>\n" +
                     "<UserEsignId>77d5ccb8-8f9e-4dbd-94b3-6458e0afc086</UserEsignId>\n" +
                     "<AgreementDate>2016-01-01T01:06:23.367</AgreementDate>\n" +
                     "</EsignAgreementInformation>\n" +
                     "<RecipientId>f14468b6-c972-4ed2-983e-0d49ae2d7619</RecipientId>\n" +
                     "</RecipientStatus>\n" +
                     "<RecipientStatus>\n" +
                     "<Type>CarbonCopy</Type>\n" +
                     "<Email>ZHMxNDUxNjM5MTU4MTAw@mailinator.com</Email>\n" +
                     "<UserName>Paul Dupuis</UserName>\n" +
                     "<RoutingOrder>2</RoutingOrder>\n" +
                     "<DeclineReason xsi:nil=\"true\"/>\n" +
                     "<Status>Created</Status>\n" +
                     "<RecipientIPAddress/>\n" +
                     "<CustomFields/>\n" +
                     "<RecipientId>c600783d-be44-4ed8-9e1d-b6dd9a4fd81a</RecipientId>\n" +
                     "</RecipientStatus>\n" +
                     "</RecipientStatuses>\n" +
                     "<TimeGenerated>2016-01-01T01:06:27.1254729</TimeGenerated>\n" +
                     "<EnvelopeID>7a5828cb-2fe2-4c30-aa19-330a24fe780e</EnvelopeID>\n" +
                     "<Subject>Please sign the NDA document</Subject>\n" +
                     "<UserName>Recipe Login</UserName>\n" +
                     "<Email>temp2+recipe@kluger.com</Email>\n" +
                     "<Status>Sent</Status>\n" +
                     "<Created>2016-01-01T01:06:06.18</Created>\n" +
                     "<Sent>2016-01-01T01:06:07.01</Sent>\n" +
                     "<ACStatus>Original</ACStatus>\n" +
                     "<ACStatusDate>2016-01-01T01:06:06.18</ACStatusDate>\n" +
                     "<ACHolder>Recipe Login</ACHolder>\n" +
                     "<ACHolderEmail>temp2+recipe@kluger.com</ACHolderEmail>\n" +
                     "<ACHolderLocation>DocuSign</ACHolderLocation>\n" +
                     "<SigningLocation>Online</SigningLocation>\n" +
                     "<SenderIPAddress>54.145.219.211</SenderIPAddress>\n" +
                     "<EnvelopePDFHash/>\n" +
                     "<CustomFields>\n" +
                     "<CustomField>\n" +
                     "<Name>AccountId</Name>\n" +
                     "<Show>false</Show>\n" +
                     "<Required>false</Required>\n" +
                     "<Value>1374267</Value>\n" +
                     "<CustomFieldType>Text</CustomFieldType>\n" +
                     "</CustomField>\n" +
                     "<CustomField>\n" +
                     "<Name>AccountName</Name>\n" +
                     "<Show>false</Show>\n" +
                     "<Required>false</Required>\n" +
                     "<Value>DocuSign</Value>\n" +
                     "<CustomFieldType>Text</CustomFieldType>\n" +
                     "</CustomField>\n" +
                     "<CustomField>\n" +
                     "<Name>AccountSite</Name>\n" +
                     "<Show>false</Show>\n" +
                     "<Required>false</Required>\n" +
                     "<Value>demo</Value>\n" +
                     "<CustomFieldType>Text</CustomFieldType>\n" +
                     "</CustomField>\n" +
                     "</CustomFields>\n" +
                     "<AutoNavigation>true</AutoNavigation>\n" +
                     "<EnvelopeIdStamping>true</EnvelopeIdStamping>\n" +
                     "<AuthoritativeCopy>false</AuthoritativeCopy>\n" +
                     "<DocumentStatuses>\n" +
                     "<DocumentStatus>\n" +
                     "<ID>1</ID>\n" +
                     "<Name>NDA.pdf</Name>\n" +
                     "<TemplateName/>\n" +
                     "<Sequence>1</Sequence>\n" +
                     "</DocumentStatus>\n" +
                     "</DocumentStatuses>\n" +
                     "</EnvelopeStatus>\n" +
                     "<TimeZone>Pacific Standard Time</TimeZone>\n" +
                     "<TimeZoneOffset>-8</TimeZoneOffset>\n" +
                     "</DocuSignEnvelopeInformation>";

        ParseWebhooksStatus parseWebhooksStatus = new ParseWebhooksStatus();

        List<WebhookNotification> expectedList = parseWebhooksStatus.parseSignerStatusXML(xml).get("7a5828cb-2fe2-4c30-aa19-330a24fe780e");
        assertEquals(expectedList.get(0).getDateTime(), webhookList.get(0).getDateTime());
        assertEquals(expectedList.get(0).getEnvelopeId(), webhookList.get(0).getEnvelopeId());
        assertEquals(expectedList.get(0).getEnvelopeStatus(), webhookList.get(0).getEnvelopeStatus());
        assertEquals(expectedList.get(0).getRecipientDigSigs().get(0).getName(), webhookList.get(0).getRecipientDigSigs().get(0).getName());
        assertEquals(expectedList.get(0).getRecipientDigSigs().get(0).getEmail(), webhookList.get(0).getRecipientDigSigs().get(0).getEmail());
        assertEquals(expectedList.get(0).getRecipientDigSigs().get(0).getStatus(), webhookList.get(0).getRecipientDigSigs().get(0).getStatus());
        assertEquals(expectedList.get(0).getRecipientDigSigs().get(1).getName(), webhookList.get(0).getRecipientDigSigs().get(1).getName());
        assertEquals(expectedList.get(0).getRecipientDigSigs().get(1).getEmail(), webhookList.get(0).getRecipientDigSigs().get(1).getEmail());
        assertEquals(expectedList.get(0).getRecipientDigSigs().get(1).getStatus(), webhookList.get(0).getRecipientDigSigs().get(1).getStatus());
    }
}